/*
    The JavaScript side of Libraries.Multitasking.Lines: takes tasks and puts
    them in lines, now or later, and keeps track of the tasks it was given.

    Wait, WaitForAny and Pause are waiting actions: they return promises,
    which the compiled code awaits.

    With one thread, one general-line task runs at a time, so the most at
    once is always 1 and SetMaximumAtOnce does nothing.
*/
function plugins_quorum_Libraries_Multitasking_Lines_(quorumLines) {
    var LineQueue = plugins_quorum_Libraries_Multitasking_LineQueue_;
    var TaskPlugin = plugins_quorum_Libraries_Multitasking_Task_;
    var self = this;

    var unfinished = 0;         // tasks given to this object that aren't done
    var finishedCount = 0;
    var firstError = null;      // the first error no Wait has passed on yet
    var finishedNotSeen = [];   // finished tasks WaitForAny hasn't returned, in order
    var repeating = new Map();  // repeating task -> how to stop its turns
    var linesUsed = [];         // every line this object has used, in order first used

    this.me_ = quorumLines;

    // The general line.
    this.LineUp$quorum_Libraries_Multitasking_Task = function (task) {
        LineUpOn(task, LineQueue.General());
    };

    // A particular line. Any line's code may line up a task on any line.
    this.LineUp$quorum_Libraries_Multitasking_Task$quorum_Libraries_Multitasking_Line = function (task, line) {
        LineUpOn(task, QueueOf(line));
    };

    function QueueOf(line) {
        return line.plugin_.GetQueue();
    }

    function NewEntry(task, state, line) {
        return { task: task, state: state, lines: self, line: line };
    }

    function LineUpOn(task, line) {
        var state = Reserve(task, line);
        line.Add(NewEntry(task, state, line));
    }

    // Counts the task as given to this object and as waiting, before it is in
    // a line. LineUpAfter reserves now and adds the task to its line later.
    function Reserve(task, line) {
        if (linesUsed.indexOf(line) < 0) {
            linesUsed.push(line);
        }
        var state = TaskPlugin.PluginOf(task);
        state.LinedUp(line);
        unfinished = unfinished + 1;
        return state;
    }

    // Lines up a task after a delay. It counts as waiting from now, so Wait,
    // Task:Wait and IsFinished include it.
    this.LineUpAfter$quorum_Libraries_Multitasking_Task$quorum_number = function (task, milliseconds) {
        LineUpAfterOn(task, LineQueue.General(), milliseconds);
    };

    this.LineUpAfter$quorum_Libraries_Multitasking_Task$quorum_Libraries_Multitasking_Line$quorum_number = function (task, line, milliseconds) {
        LineUpAfterOn(task, QueueOf(line), milliseconds);
    };

    function LineUpAfterOn(task, line, milliseconds) {
        var state = Reserve(task, line);
        LineQueue.After(milliseconds, function () {
            line.Add(NewEntry(task, state, line));
        });
    }

    // Lines up a task once another task is done. It counts as waiting from
    // now. If the other task stops with an error, this one doesn't run: it is
    // done, with an error saying why, which Wait passes on.
    this.LineUpWhenDone$quorum_Libraries_Multitasking_Task$quorum_Libraries_Multitasking_Task = function (task, before) {
        LineUpWhenDoneOn(task, LineQueue.General(), before);
    };

    this.LineUpWhenDone$quorum_Libraries_Multitasking_Task$quorum_Libraries_Multitasking_Line$quorum_Libraries_Multitasking_Task = function (task, line, before) {
        LineUpWhenDoneOn(task, QueueOf(line), before);
    };

    function LineUpWhenDoneOn(task, line, before) {
        var state = Reserve(task, line);
        TaskPlugin.PluginOf(before).WhenDone(function (failed) {
            if (failed) {
                FailWithoutRunning(task, state, "This " + LineQueue.TaskName(task)
                        + " did not run, because the " + LineQueue.TaskName(before)
                        + " it waited for stopped with an error.");
            } else {
                line.Add(NewEntry(task, state, line));
            }
        });
    }

    function FailWithoutRunning(task, state, message) {
        var error = LineQueue.NewError(message);
        state.SetError(error);
        state.SetDone();
        self.TaskDone(task, error);
        LineQueue.NotifyChanged();
    }

    // Lines up a task again and again, every so many milliseconds, until
    // StopRepeating. A turn is skipped if the task from the last turn is
    // still waiting or running, so one task never runs twice at once.
    // Between turns the task is done, so Wait doesn't wait forever.
    this.LineUpEvery$quorum_Libraries_Multitasking_Task$quorum_number = function (task, milliseconds) {
        LineUpEveryOn(task, LineQueue.General(), milliseconds);
    };

    this.LineUpEvery$quorum_Libraries_Multitasking_Task$quorum_Libraries_Multitasking_Line$quorum_number = function (task, line, milliseconds) {
        LineUpEveryOn(task, QueueOf(line), milliseconds);
    };

    function LineUpEveryOn(task, line, milliseconds) {
        self.StopRepeating$quorum_Libraries_Multitasking_Task(task);
        var state = TaskPlugin.PluginOf(task);
        var stop = LineQueue.Every(milliseconds, function () {
            if (!state.IsPending()) {
                LineUpOn(task, line);
            }
        });
        repeating.set(task, stop);
    }

    // Stops lining the task up again. A turn already in line still runs.
    this.StopRepeating$quorum_Libraries_Multitasking_Task = function (task) {
        var stop = repeating.get(task);
        if (stop !== undefined) {
            repeating.delete(task);
            stop();
        }
    };

    this.TaskDone = function (task, error) {
        if (error !== null && firstError === null) {
            firstError = error;
        }
        finishedNotSeen.push(task);
        finishedCount = finishedCount + 1;
        unfinished = unfinished - 1;
    };

    this.GetMain = function () {
        return LineQueue.Main().GetHandle();
    };

    // The lines this object has put tasks in, like an array: 0 is the first
    // line used.
    this.GetLine$quorum_integer = function (index) {
        if (index < 0 || index >= linesUsed.length) {
            var error = new quorum_Libraries_Language_Errors_InvalidLocationError_();
            error.SetErrorMessage$quorum_text("There is no line " + index + "; this Lines object has used " + linesUsed.length + ".");
            throw error;
        }
        return linesUsed[index].GetHandle();
    };

    this.GetSize = function () {
        return linesUsed.length;
    };

    this.IsFinished = function () {
        return unfinished === 0;
    };

    this.GetFinishedCount = function () {
        return finishedCount;
    };

    // Waits for every task this object was given, including ones given while
    // waiting, then passes on the first error from them, once.
    this.Wait = function () {
        return LineQueue.WaitUntil(self.IsFinished, -1).then(function () {
            PassOnError();
        });
    };

    this.Wait$quorum_number = function (milliseconds) {
        return LineQueue.WaitUntil(self.IsFinished, milliseconds).then(function (done) {
            if (!done) {
                return false;
            }
            PassOnError();
            return true;
        });
    };

    function PassOnError() {
        var failure = firstError;
        firstError = null;
        if (failure !== null) {
            throw failure;
        }
    }

    // Waits for the next task to finish and gives it: each finished task
    // once, in the order they finished. Gives undefined when every task this
    // object was given has been given back already.
    this.WaitForAny = function () {
        return WaitForAnyWithin(-1);
    };

    this.WaitForAny$quorum_number = function (milliseconds) {
        return WaitForAnyWithin(milliseconds);
    };

    function WaitForAnyWithin(milliseconds) {
        return LineQueue.WaitUntil(function () {
            return finishedNotSeen.length > 0 || self.IsFinished();
        }, milliseconds).then(function () {
            return finishedNotSeen.length > 0 ? finishedNotSeen.shift() : null;
        });
    }

    this.Pause$quorum_number = function (milliseconds) {
        return LineQueue.Pause(milliseconds);
    };

    this.SetMaximumAtOnce$quorum_integer = function (count) {
    };

    this.GetMaximumAtOnce = function () {
        return 1;
    };

    // One thread: one task at a time, whatever SetMaximumAtOnce is given.
    this.GetPlatformMaximumAtOnce = function () {
        return 1;
    };
}
