/*
    The JavaScript plugins for Libraries.Multitasking.Task, with the
    scheduler every multitasking plugin uses (LineQueue) first. The library
    copies in one plugin file per class that has system actions, and
    LineQueue isn't a Quorum class, so it lives here, in the plugin of the
    class every task is.
*/

/*
    The JavaScript side of multitasking: the lines, and the one scheduler
    that serves them. Not a Quorum class; the Task, Lines, Line and Key
    plugins use it.

    A browser has one thread, so every line takes turns on the event loop.
    The scheduler does one thing per turn:
    - resume one piece of code whose wait is over (or whose time ran out), or
    - start one task from a line that may start one.
    Then it lets that code run until it waits again or finishes, before the
    next turn. Because only the scheduler resumes or starts code, it always
    knows which line's code is running ("current"). Keys and the rules for
    helping need that.

    There are three kinds of line, as on Java:
    - The main line. Its tasks start only while the main program waits.
    - A line of your own (a Line the program made). One task at a time, in
      order. The next task starts only when the one before has finished,
      including any waits inside it, so nobody cuts in line.
    - The general line. Tasks start in the order they lined up. While a
      general-line task waits, the next one may start: the same "helping" a
      waiting counter does on Java, so tasks that wait for other tasks on the
      general line can't stall it.

    Waiting actions return promises, which the compiled code awaits.
*/
var plugins_quorum_Libraries_Multitasking_LineQueue_ = (function () {
    var MAIN = "main";
    var NAMED = "named";
    var GENERAL = "general";

    // Who is running: the main program, or a task on a line.
    function Fiber(line, isMainProgram) {
        this.line = line;
        this.isMainProgram = isMainProgram;
        this.waiting = false;
    }

    function LineQueue(kind, name) {
        this.kind = kind;
        this.name = name;
        this.queue = [];          // tasks waiting in line, first in front
        this.unfinished = 0;      // waiting or running
        this.busy = false;        // a line of your own: a task is running
        this.handle = null;       // the line's one Quorum Line object
        allLines.push(this);
    }

    var allLines = [];
    var namedCount = 0;
    var mainLine = new LineQueue(MAIN, "main");
    var generalLine = new LineQueue(GENERAL, "general");
    var mainProgram = new Fiber(mainLine, true);
    var current = null;           // the fiber whose code is running now
    var waiters = [];             // code that is waiting: {fiber, done, deadline, resolve}
    var ready = [];               // waiters whose wait is over, to resume one per turn: {waiter, result}
    var timersPending = 0;        // LineUpAfter and LineUpEvery timers that may still line up tasks
    var pumpScheduled = false;
    var wakeTimer = null;
    var nextLine = 0;             // where to start looking for a task to start, so lines take turns

    // Node.js keeps running while a timer is set; a browser has no unref.
    function Unref(timer) {
        if (timer && typeof timer.unref === "function") {
            timer.unref();
        }
        return timer;
    }

    /*
        Runs "work" on a later turn of the event loop, as soon as possible.
        Node.js has setImmediate. Browsers don't, and they hold back
        setTimeout(0) to about 4 milliseconds once timers nest, which would
        make every turn slow; a message to oneself on a MessageChannel comes
        back right away instead.
    */
    var laterChannel = null;
    var laterWork = [];

    function Later(work) {
        if (typeof setImmediate === "function") {
            setImmediate(work);
        } else if (typeof MessageChannel === "function") {
            if (laterChannel === null) {
                laterChannel = new MessageChannel();
                laterChannel.port1.onmessage = function () {
                    var next = laterWork.shift();
                    if (next !== undefined) {
                        next();
                    }
                };
            }
            laterWork.push(work);
            laterChannel.port2.postMessage(null);
        } else {
            setTimeout(work, 0);
        }
    }

    function SchedulePump() {
        if (!pumpScheduled) {
            pumpScheduled = true;
            Later(Pump);
        }
    }

    function Current() {
        return current === null ? mainProgram : current;
    }

    LineQueue.prototype.GetHandle = function () {
        if (this.handle === null) {
            var made = new quorum_Libraries_Multitasking_Line_();
            made.plugin_.SetQueue(this);
            this.handle = made;
        }
        return this.handle;
    };

    // A Line the program made becomes its line's one Quorum object.
    LineQueue.prototype.SetHandle = function (line) {
        if (this.handle === null) {
            this.handle = line;
        }
    };

    LineQueue.prototype.GetWaitingCount = function () {
        return this.queue.length;
    };

    LineQueue.prototype.IsFinished = function () {
        return this.unfinished === 0;
    };

    // Never runs the task right away: it waits for a later turn.
    LineQueue.prototype.Add = function (entry) {
        this.unfinished = this.unfinished + 1;
        this.queue.push(entry);
        SchedulePump();
    };

    LineQueue.prototype.CanStart = function () {
        if (this.queue.length === 0) {
            return false;
        }
        if (this.kind === NAMED) {
            return !this.busy;
        }
        if (this.kind === MAIN) {
            return mainProgram.waiting;
        }
        return true;   // the general line: nothing else is running now
    };

    // Run, then Then, then report the task done. An error in Run skips Then
    // and is kept for Task:Wait and Lines:Wait to pass on to the code that
    // waits; the library doesn't print it.
    async function RunEntry(entry, fiber) {
        var failure = null;
        entry.state.SetRunning();
        try {
            await entry.task.Run();
            await entry.task.Then();
        } catch (error) {
            failure = error;
            entry.state.SetError(error);
        }
        entry.state.SetDone();
        entry.line.unfinished = entry.line.unfinished - 1;
        if (entry.line.kind === NAMED) {
            entry.line.busy = false;
        }
        entry.lines.TaskDone(entry.task, failure);
        if (current === fiber) {
            current = null;
        }
        SchedulePump();
    }

    function Start(line) {
        var entry = line.queue.shift();
        if (line.kind === NAMED) {
            line.busy = true;
        }
        var fiber = new Fiber(line, false);
        current = fiber;
        RunEntry(entry, fiber);
    }

    function Resume(waiter, result) {
        waiter.fiber.waiting = false;
        current = waiter.fiber;
        waiter.resolve(result);
    }

    /*
        One turn: resume one waiter or start one task, then come back.

        Every waiter's outcome is decided at the same moment, then they are
        resumed one per turn. Otherwise code resumed first could run long
        enough to make a later waiter's time run out, though what it waited
        for had already happened.

        "Time ran out" is checked before "done": if both are true, the time
        limit passed while other code was running, and what that code did
        most likely happened at its end, after the limit.
    */
    function Pump() {
        pumpScheduled = false;
        current = null;
        var i;

        if (ready.length === 0) {
            var now = Date.now();
            var stillWaiting = [];
            for (i = 0; i < waiters.length; i++) {
                var waiter = waiters[i];
                if (waiter.deadline <= now) {
                    ready.push({ waiter: waiter, result: false });
                } else if (waiter.done()) {
                    ready.push({ waiter: waiter, result: true });
                } else {
                    stillWaiting.push(waiter);
                }
            }
            waiters = stillWaiting;
        }
        if (ready.length > 0) {
            var next = ready.shift();
            Resume(next.waiter, next.result);
            SchedulePump();
            return;
        }

        var count = allLines.length;
        for (i = 0; i < count; i++) {
            var line = allLines[(nextLine + i) % count];
            if (line.CanStart()) {
                nextLine = (nextLine + i + 1) % count;
                Start(line);
                SchedulePump();
                return;
            }
        }

        // Nothing to do now. If code waits with a time limit, or a timer may
        // still line up a task, look again later. Otherwise nothing can
        // change, and the program is left to finish.
        if (wakeTimer !== null) {
            clearTimeout(wakeTimer);
            wakeTimer = null;
        }
        if (waiters.length === 0) {
            return;
        }
        var soonest = Infinity;
        for (i = 0; i < waiters.length; i++) {
            soonest = Math.min(soonest, waiters[i].deadline);
        }
        if (soonest === Infinity && timersPending === 0) {
            return;
        }
        var delay = soonest === Infinity ? 250 : Math.max(0, Math.min(soonest - now, 250));
        wakeTimer = setTimeout(function () {
            wakeTimer = null;
            SchedulePump();
        }, delay);
    }

    /*
        Waits until "done" is true, or the time runs out (milliseconds < 0
        means no limit). The promise gives whether "done" became true. While
        the main program waits, main-line tasks may start; while a
        general-line task waits, the next general-line task may start.
    */
    function WaitUntil(done, milliseconds) {
        if (done()) {
            return Promise.resolve(true);
        }
        if (milliseconds >= 0 && milliseconds <= 0) {
            return Promise.resolve(false);
        }
        var fiber = Current();
        fiber.waiting = true;
        var deadline = milliseconds < 0 ? Infinity : Date.now() + milliseconds;
        return new Promise(function (resolve) {
            waiters.push({ fiber: fiber, done: done, deadline: deadline, resolve: resolve });
            SchedulePump();
        });
    }

    // Pausing is waiting for nothing, for a while; other lines get turns.
    function Pause(milliseconds) {
        if (!(milliseconds > 0)) {
            return Promise.resolve();
        }
        return WaitUntil(function () { return false; }, milliseconds).then(function () {});
    }

    // Lines up a task once the time is up. The timer doesn't keep Node.js
    // running by itself, like Java's daemon threads. A timer can fire a
    // little early by the clock, so it checks and waits the rest if so: the
    // task never lines up before its time.
    function After(milliseconds, work) {
        var due = Date.now() + Math.max(0, milliseconds);
        timersPending = timersPending + 1;
        function Check() {
            var left = due - Date.now();
            if (left > 0) {
                Unref(setTimeout(Check, left));
                return;
            }
            timersPending = timersPending - 1;
            work();
        }
        Unref(setTimeout(Check, Math.max(0, milliseconds)));
    }

    // Calls "work" every so many milliseconds until the returned stop is
    // called.
    function Every(milliseconds, work) {
        timersPending = timersPending + 1;
        var timer = Unref(setInterval(work, Math.max(1, milliseconds)));
        var stopped = false;
        return function () {
            if (!stopped) {
                stopped = true;
                clearInterval(timer);
                timersPending = timersPending - 1;
            }
        };
    }

    // Something changed that a waiter may be waiting for.
    function NotifyChanged() {
        if (waiters.length > 0) {
            SchedulePump();
        }
    }

    // Runs the main program: code that runs outside any task is the main
    // program's, and it may line up tasks and wait for them.
    function RunMainProgram(work) {
        current = mainProgram;
        return work();
    }

    function SimpleName(names) {
        if (names === undefined || names === null || names.length === 0) {
            return "Task";
        }
        var full = names[names.length - 1];
        var dot = full.lastIndexOf(".");
        return dot < 0 ? full : full.substring(dot + 1);
    }

    function TaskName(task) {
        return SimpleName(task.parentNames_);
    }

    /*
        The error as a Quorum programmer would want to read it. Its kind is
        named unless it is a plain Error: "CastError: ..." but not
        "Error: ...".
    */
    function Describe(error) {
        var kind;
        var message;
        if (error !== null && error !== undefined && error.parentNames_ !== undefined) {
            kind = SimpleName(error.parentNames_);
            message = error.GetErrorMessage();
        } else if (error instanceof Error) {
            kind = error.name;
            message = error.message;
        } else {
            kind = "Error";
            message = String(error);
        }
        if (message === null || message === undefined || message === "") {
            return kind;
        }
        if (kind === "Error") {
            return message;
        }
        return kind + ": " + message;
    }

    // A Quorum error with a message, to throw or keep.
    function NewError(message) {
        var error = new quorum_Libraries_Language_Errors_Error_();
        error.SetErrorMessage$quorum_text(message);
        return error;
    }

    return {
        Main: function () { return mainLine; },
        General: function () { return generalLine; },
        NewNamed: function () {
            namedCount = namedCount + 1;
            return new LineQueue(NAMED, "line-" + namedCount);
        },
        IsMainKind: function (line) { return line.kind === MAIN; },
        Current: Current,
        WaitUntil: WaitUntil,
        Pause: Pause,
        After: After,
        Every: Every,
        NotifyChanged: NotifyChanged,
        RunMainProgram: RunMainProgram,
        TaskName: TaskName,
        Describe: Describe,
        NewError: NewError
    };
})();

/*
    The JavaScript side of Libraries.Multitasking.Task: whether a task is
    waiting, running, done, or stopped with an error, and what to do when it
    is done.

    Wait and Pause are waiting actions: they return promises, which the
    compiled code awaits.
*/
function plugins_quorum_Libraries_Multitasking_Task_(quorumTask) {
    var NEW = 0;        // never lined up
    var WAITING = 1;    // lined up, not started yet
    var RUNNING = 3;    // Run or Then is running
    var DONE = 2;       // Run and Then done, or Run stopped with an error

    var LineQueue = plugins_quorum_Libraries_Multitasking_LineQueue_;
    var self = this;
    var state = NEW;
    var error = null;
    var line = null;        // the line it was last lined up on
    var whenDone = [];      // what to do when done; each is told whether it failed

    this.me_ = quorumTask;

    // Lines calls this when the task lines up. Lining it up again starts over.
    this.LinedUp = function (queue) {
        error = null;
        line = queue;
        state = WAITING;
    };

    // The line the task is in, is running on, or last ran on; undefined if
    // it was never lined up.
    this.GetLine = function () {
        return line === null ? null : line.GetHandle();
    };

    this.SetRunning = function () {
        state = RUNNING;
    };

    // Whether the task is lined up or running: something to wait for.
    this.IsPending = function () {
        return state === WAITING || state === RUNNING;
    };

    this.IsRunning = function () {
        return state === RUNNING;
    };

    this.IsFinished = function () {
        return state === DONE;
    };

    this.Pause$quorum_number = function (milliseconds) {
        return LineQueue.Pause(milliseconds);
    };

    this.SetError = function (failure) {
        error = failure;
    };

    this.HasError = function () {
        return error !== null;
    };

    this.SetDone = function () {
        state = DONE;
        var next = whenDone;
        whenDone = [];
        var failed = error !== null;
        for (var i = 0; i < next.length; i++) {
            next[i](failed);
        }
        LineQueue.NotifyChanged();
    };

    // Runs "next" when this task is done, or right away if it already is.
    this.WhenDone = function (next) {
        if (state === DONE) {
            next(error !== null);
        } else {
            whenDone.push(next);
        }
    };

    // Waits until the task is done. A task that was never lined up has
    // nothing to wait for. An error in the task's Run is passed on.
    this.Wait = function () {
        return LineQueue.WaitUntil(function () { return !self.IsPending(); }, -1).then(function () {
            PassOnError();
        });
    };

    this.Wait$quorum_number = function (milliseconds) {
        return LineQueue.WaitUntil(function () { return !self.IsPending(); }, milliseconds).then(function (done) {
            if (!done) {
                return false;
            }
            PassOnError();
            return true;
        });
    };

    function PassOnError() {
        if (error !== null) {
            throw error;
        }
    }
}

/*
    The Task plugin of any task. A plain Task has the plugin itself. A class
    that is a Task keeps its Task part in a field whose name ends in "__"
    (Libraries_Multitasking_Task__ for a direct child); deeper classes are
    searched the same way.
*/
plugins_quorum_Libraries_Multitasking_Task_.PluginOf = function (task) {
    if (task.plugin_ instanceof plugins_quorum_Libraries_Multitasking_Task_) {
        return task.plugin_;
    }
    var direct = task.Libraries_Multitasking_Task__;
    if (direct !== undefined && direct !== null) {
        return direct.plugin_;
    }
    for (var field in task) {
        if (field !== "prototype" && field.length > 2 && field.substring(field.length - 2) === "__") {
            var part = task[field];
            if (part !== null && typeof part === "object") {
                var found = plugins_quorum_Libraries_Multitasking_Task_.PluginOf(part);
                if (found !== null) {
                    return found;
                }
            }
        }
    }
    return null;
};
