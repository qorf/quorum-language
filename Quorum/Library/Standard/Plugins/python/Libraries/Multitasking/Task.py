# The Python plugins for Libraries.Multitasking.Task, with the scheduler
# every multitasking plugin uses first. The library copies in one plugin file
# per class that has system actions, and the scheduler isn't a Quorum class,
# so it lives here, in the plugin of the class every task is. Lines, Line and
# Key use Task, so a program that uses any of them has this file.
#
# The scheduler: the lines, and the one driver that serves them.
#
# It uses only what a LEGO SPIKE hub (v3 firmware) has: async def, await,
# and the runloop module's run, sleep_ms and until. Not asyncio, which the
# hub doesn't have. On a desktop computer, which has no runloop module, a
# stand-in built on asyncio takes its place (_MT_DesktopRunloop).
#
# runloop can't start code after the program has started, so a program
# whose Main waits starts several pieces of code side by side (_MT_Run): the
# main program, the scheduler's driver (Drive), and a few runners for
# outside work (Outside). The driver runs every task itself: a task's Run
# and Then are one coroutine, which the driver steps with send() until the
# task waits (it hands the driver a _MT_Waiter) or finishes. So any number of
# tasks can be waiting at once, with nothing started for each.
#
# The driver does one thing per turn:
# - resume one piece of code whose wait is over (or whose time ran out), or
# - start one task from a line that may start one.
# Then that code runs until it waits again or finishes, before the next turn.
# Because only the driver resumes or starts code, it always knows which
# line's code is running ("current"). Keys and the rules for helping need
# that. The main program waits with runloop.until, for the driver to decide
# its wait is over; the driver then lets it run before its next turn.
#
# Outside work is something the platform does and Python awaits, such as a
# SPIKE motor turning (_MT_WaitFor). A task can't await it itself, since the
# driver, not runloop, resumes tasks; so the runners await it, and the task
# waits for the runner with an ordinary wait.
#
# The three kinds of line, as on Java:
# - The main line. Its tasks start only while the main program waits.
# - A line of your own (a Line the program made). One task at a time, in
#   order. The next task starts only when the one before has finished,
#   including any waits inside it, so nobody cuts in line.
# - The general line. Tasks start in the order they lined up. While a
#   general-line task waits, the next one may start, the same "helping" a
#   waiting counter does on Java.
#
# Waiting actions are coroutines, which the compiled code awaits.

import time

_MT_MAIN = "main"
_MT_NAMED = "named"
_MT_GENERAL = "general"
_MT_FOREVER = float("inf")
_MT_LONGEST_NAP = 250      # the driver looks again at least this often, in milliseconds
_MT_OUTSIDE_RUNNERS = 8    # how much outside work can go on at once


# A stand-in for SPIKE's runloop module on a desktop computer, built on
# asyncio, with the three functions multitasking uses. asyncio is imported
# only here, so a hub, which has runloop, never imports it.
class _MT_DesktopRunloop:
    def run(self, *coroutines):
        import asyncio

        async def All():
            return await asyncio.gather(*coroutines)

        return asyncio.run(All())

    # The hub's sleep_ms(0) never returns, so asking for less than a
    # millisecond is an error here, where tests can catch it.
    async def sleep_ms(self, milliseconds):
        import asyncio
        if milliseconds < 1:
            raise ValueError("runloop.sleep_ms needs at least 1 millisecond: on a SPIKE hub, sleep_ms(0) never returns.")
        await asyncio.sleep(milliseconds / 1000.0)

    # Like the hub's: returns when condition() is true, or when "timeout"
    # milliseconds have passed (0 means no time limit). As on the hub,
    # "timeout" can only be given by name. It checks the condition every
    # turn of the event loop at first, then every millisecond, so a long
    # wait doesn't keep the processor busy.
    async def until(self, condition, *, timeout=0):
        import asyncio
        started = time.time()
        checks = 0
        while not condition():
            if timeout > 0 and (time.time() - started) * 1000.0 >= timeout:
                return
            checks = checks + 1
            await asyncio.sleep(0 if checks < 50 else 0.001)


try:
    import runloop
except ImportError:
    runloop = _MT_DesktopRunloop()


# Milliseconds from some fixed moment. MicroPython (the hub) has
# time.ticks_ms; desktop Python has time.time.
def _MT_Now():
    if hasattr(time, "ticks_ms"):
        return float(time.ticks_ms())
    return time.time() * 1000.0


# Who is running: the main program, or a task on a line. A task's fiber holds
# its coroutine, which the driver steps.
class _MT_Fiber:
    def __init__(self, line, is_main_program):
        self.line = line
        self.is_main_program = is_main_program
        self.waiting = False
        self.coroutine = None


# Code that waits until "done" is true or the deadline passes. The driver
# decides which, and gives the result to the code.
class _MT_Waiter:
    def __init__(self, fiber, done, deadline):
        self.fiber = fiber
        self.done = done
        self.deadline = deadline
        self.decided = False     # the driver has decided how the wait ends
        self.result = False      # whether "done" became true
        self.resumed = False     # the main program has run again after it


# What a task awaits to hand its waiter to the driver: it comes back with the
# waiter's result. Both __await__ and __iter__, since MicroPython's await may
# use either.
class _MT_Suspend:
    def __init__(self, waiter):
        self.waiter = waiter

    def __await__(self):
        result = yield self.waiter
        return result

    __iter__ = __await__


# Outside work: an awaitable a runner awaits for a task's plugin.
class _MT_Outside:
    def __init__(self, awaitable, state):
        self.awaitable = awaitable
        self.state = state


class _MT_LineQueue:
    def __init__(self, kind, name):
        self.kind = kind
        self.name = name
        self.queue = []          # tasks waiting in line, first in front
        self.unfinished = 0      # waiting or running
        self.busy = False        # a line of your own: a task is running
        self.handle = None       # the line's one Quorum Line object
        _MT.all_lines.append(self)

    # The line's Quorum Line object, made the first time it is asked for
    # unless the program made one first (see SetHandle).
    def GetHandle(self):
        if self.handle is None:
            made = quorum_Libraries_Multitasking_Line_()
            made.plugin_.SetQueue(self)
            self.handle = made
        return self.handle

    # A Line the program made becomes its line's one Quorum object.
    def SetHandle(self, line):
        if self.handle is None:
            self.handle = line

    def GetWaitingCount(self):
        return len(self.queue)

    def IsFinished(self):
        return self.unfinished == 0

    # Never runs the task right away: it waits for a later turn.
    def Add(self, entry):
        self.unfinished = self.unfinished + 1
        self.queue.append(entry)
        _MT.NotifyChanged()

    def CanStart(self):
        if len(self.queue) == 0:
            return False
        if self.kind == _MT_NAMED:
            return not self.busy
        if self.kind == _MT_MAIN:
            return _MT.main_program.waiting
        return True   # the general line: nothing else is running now


# A LineUpAfter or LineUpEvery timer. "period" is 0 for a timer that calls
# its work once.
class _MT_Timer:
    def __init__(self, due, period, work):
        self.due = due
        self.period = period
        self.work = work


class _MT_Scheduler:
    def __init__(self):
        self.all_lines = []
        self.named_count = 0
        self.current = None        # the task's fiber whose code is running now; None for the main program
        self.waiters = []          # code that is waiting
        self.ready = []            # (waiter, result): wait over, to resume one per turn
        self.timers = []           # LineUpAfter and LineUpEvery timers that may still line up tasks
        self.outside = []          # outside work no runner has taken yet
        self.outside_running = 0   # outside work a runner is awaiting
        self.changed = False       # something changed since the driver last looked
        self.main_finished = False
        self.next_line = 0         # where to start looking for a task to start, so lines take turns
        self.main_program = None
        self.main_line = None
        self.general_line = None

    def Setup(self):
        self.main_line = _MT_LineQueue(_MT_MAIN, "main")
        self.general_line = _MT_LineQueue(_MT_GENERAL, "general")
        self.main_program = _MT_Fiber(self.main_line, True)

    def Current(self):
        return self.main_program if self.current is None else self.current

    def NewNamed(self):
        self.named_count = self.named_count + 1
        return _MT_LineQueue(_MT_NAMED, "line-" + str(self.named_count))

    # Something changed that a waiter may be waiting for, or a task was lined
    # up: the driver looks again.
    def NotifyChanged(self):
        self.changed = True

    # Runs beside the main program until it finishes. When Main finishes,
    # tasks still waiting or running stop part way: a program waits for its
    # tasks before it finishes, as on every target.
    async def Drive(self):
        while not self.main_finished:
            self.changed = False
            waiter = self.Turn()
            if waiter is not None:
                # The main program's wait is over: let it run until it waits
                # again or finishes, before the next turn.
                await runloop.until(lambda: waiter.resumed or self.main_finished)
                continue
            if self.changed:
                # Give the main program and the runners a turn too. On the
                # hub, sleep_ms(0) never returns and until() with a condition
                # that is already true returns without letting anything else
                # run, so this sleeps the shortest time that does.
                await runloop.sleep_ms(1)
                continue
            self.CheckStuck()
            # timeout by name: the hub's until takes it only that way
            await runloop.until(lambda: self.changed or self.main_finished, timeout=self.Nap())

    # Awaits outside work, one piece at a time, until Main finishes. Several
    # of these run side by side (_MT_OUTSIDE_RUNNERS), so several pieces can
    # go on at once, such as two motors turning.
    async def Outside(self):
        while not self.main_finished:
            await runloop.until(lambda: len(self.outside) > 0 or self.main_finished)
            if self.main_finished or len(self.outside) == 0:
                continue
            work = self.outside.pop(0)
            self.outside_running = self.outside_running + 1
            try:
                work.state.result = await work.awaitable
            except Exception as error:
                work.state.SetError(error)
            self.outside_running = self.outside_running - 1
            work.state.SetDone()

    # One turn: resume one waiter or start one task. Returns the main
    # program's waiter if its wait is over, so the driver lets it run.
    #
    # Every waiter's outcome is decided at the same moment, then they are
    # resumed one per turn. "Time ran out" is checked before "done": if both
    # are true, the time limit passed while other code was running, and what
    # that code did most likely happened at its end, after the limit.
    def Turn(self):
        self.current = None
        self.RunTimers()

        if len(self.ready) == 0:
            now = _MT_Now()
            still_waiting = []
            for waiter in self.waiters:
                if waiter.deadline <= now:
                    self.ready.append((waiter, False))
                elif waiter.done():
                    self.ready.append((waiter, True))
                else:
                    still_waiting.append(waiter)
            self.waiters = still_waiting
        if len(self.ready) > 0:
            waiter, result = self.ready.pop(0)
            waiter.result = result
            waiter.decided = True
            waiter.fiber.waiting = False
            self.changed = True
            if waiter.fiber.is_main_program:
                return waiter
            self.Step(waiter.fiber, result)
            return None

        count = len(self.all_lines)
        for i in range(count):
            line = self.all_lines[(self.next_line + i) % count]
            if line.CanStart():
                self.next_line = (self.next_line + i + 1) % count
                self.Start(line)
                self.changed = True
                return None
        return None

    def Start(self, line):
        entry = line.queue.pop(0)
        if line.kind == _MT_NAMED:
            line.busy = True
        fiber = _MT_Fiber(line, False)
        fiber.coroutine = self.RunEntry(entry)
        self.Step(fiber, None)

    # Runs a task's code until it waits again or finishes. A task can only
    # wait through multitasking's waiting actions: anything else it awaits
    # that hands the driver something other than a waiter is an error in
    # the task.
    def Step(self, fiber, value):
        self.current = fiber
        try:
            handed = fiber.coroutine.send(value)
            while not isinstance(handed, _MT_Waiter):
                handed = fiber.coroutine.throw(_MT_NewError(
                    "A task waited for something multitasking can't wait for."))
        except StopIteration:
            pass
        finally:
            self.current = None

    # Calls the work of every timer whose time has come.
    def RunTimers(self):
        if len(self.timers) == 0:
            return
        now = _MT_Now()
        for timer in list(self.timers):
            if timer.due <= now and timer in self.timers:
                if timer.period > 0:
                    timer.due = now + timer.period
                else:
                    self.timers.remove(timer)
                timer.work()

    # How long the driver may wait before it must look again: until the
    # soonest time limit or timer, at most _MT_LONGEST_NAP. 0 means until
    # something changes (runloop.until's "no time limit").
    def Nap(self):
        soonest = _MT_FOREVER
        for waiter in self.waiters:
            soonest = min(soonest, waiter.deadline)
        for timer in self.timers:
            soonest = min(soonest, timer.due)
        if soonest == _MT_FOREVER:
            return 0
        return int(max(1.0, min(soonest - _MT_Now() + 1.0, float(_MT_LONGEST_NAP))))

    # Everything is waiting with no time limit, no timer can line up a task,
    # no outside work is going on, and no line can start a task: nothing can
    # ever wake the main program. End the program, rather than hang. Every
    # task that has started is waiting here, since the driver runs each until
    # it waits or finishes.
    def CheckStuck(self):
        if not self.main_program.waiting or len(self.timers) > 0:
            return
        if len(self.outside) > 0 or self.outside_running > 0:
            return
        for waiter in self.waiters:
            if waiter.deadline != _MT_FOREVER:
                return
        raise SystemExit("Main never finished: everything was waiting, and nothing could wake it.")

    # Run, then Then, then report the task done. An error in Run skips Then
    # and is kept for Task:Wait and Lines:Wait to pass on.
    async def RunEntry(self, entry):
        failure = None
        entry.state.SetRunning()
        try:
            await _MT_Await(entry.task.Run())
            await _MT_Await(entry.task.Then())
        except Exception as error:
            failure = error
            entry.state.SetError(error)
        entry.state.SetDone()
        entry.line.unfinished = entry.line.unfinished - 1
        if entry.line.kind == _MT_NAMED:
            entry.line.busy = False
        entry.lines.TaskDone(entry.task, failure)
        self.NotifyChanged()

    # Waits until "done" is true, or the time runs out (milliseconds < 0
    # means no limit). Returns whether "done" became true. While the main
    # program waits, main-line tasks may start; while a general-line task
    # waits, the next general-line task may start.
    async def WaitUntil(self, done, milliseconds):
        if done():
            return True
        if milliseconds == 0:
            return False
        fiber = self.Current()
        fiber.waiting = True
        deadline = _MT_FOREVER if milliseconds < 0 else _MT_Now() + milliseconds
        waiter = _MT_Waiter(fiber, done, deadline)
        self.waiters.append(waiter)
        self.NotifyChanged()
        if fiber.is_main_program:
            await runloop.until(lambda: waiter.decided)
            waiter.resumed = True
            self.current = None
            return waiter.result
        return await _MT_Suspend(waiter)

    # Pausing is waiting for nothing, for a while; other lines get turns.
    async def Pause(self, milliseconds):
        if milliseconds > 0:
            await self.WaitUntil(lambda: False, milliseconds)

    # Calls "work" once the time is up, never before (checked by the clock).
    def After(self, milliseconds, work):
        self.timers.append(_MT_Timer(_MT_Now() + max(0.0, milliseconds), 0, work))
        self.NotifyChanged()

    # Calls "work" every so many milliseconds until the returned stop is
    # called.
    def Every(self, milliseconds, work):
        period = max(1.0, milliseconds)
        timer = _MT_Timer(_MT_Now() + period, period, work)
        self.timers.append(timer)
        self.NotifyChanged()

        def Stop():
            if timer in self.timers:
                self.timers.remove(timer)

        return Stop


# Starts a program whose Main waits: the main program, the driver and the
# runners for outside work, side by side. "main" is the coroutine of the
# program's SuperMain; the compiler writes the call to this. When Main
# finishes, the rest stop too, and runloop.run returns.
def _MT_Run(main):
    async def MainProgram():
        try:
            await main
        finally:
            _MT.main_finished = True

    pieces = [MainProgram(), _MT.Drive()]
    for i in range(_MT_OUTSIDE_RUNNERS):
        pieces.append(_MT.Outside())
    runloop.run(*pieces)


# Starts outside work for a library plugin: "awaitable" is what the
# platform gives to await (such as a SPIKE motor's run_for_time), and "task"
# is a Quorum Task that is running until the work is done. The plugin's
# Quorum code then waits for the task (Task:Wait), which passes on any
# error, and can ask for the work's result (_MT_ResultOf).
def _MT_WaitFor(awaitable, task):
    state = _MT_PluginOf(task)
    state.error = None
    state.result = None
    state.state = _MT_RUNNING
    _MT.outside.append(_MT_Outside(awaitable, state))
    _MT.NotifyChanged()


# What the outside work for a task gave back (see _MT_WaitFor).
def _MT_ResultOf(task):
    return _MT_PluginOf(task).result


# Awaits a value if it can be awaited; Run and Then are coroutines when they
# can wait, plain calls otherwise.
async def _MT_Await(value):
    if hasattr(value, "__await__") or type(value).__name__ in ("coroutine", "generator"):
        return await value
    return value


def _MT_SimpleName(kind):
    name = type(kind).__name__ if not isinstance(kind, str) else kind
    if name.startswith("quorum_"):
        name = name[len("quorum_"):]
    if name.endswith("_"):
        name = name[:-1]
    if "_" in name:
        name = name[name.rfind("_") + 1:]
    return name


def _MT_TaskName(task):
    return _MT_SimpleName(task)


# A Quorum error with a message, to raise or keep.
def _MT_NewError(message, kind="quorum_Libraries_Language_Errors_Error_"):
    made = globals().get(kind)
    if made is None:
        made = quorum_Libraries_Language_Errors_Error_
    error = made()
    error.SetErrorMessage__quorum_text(message)
    return error


_MT = _MT_Scheduler()
_MT.Setup()


# The Python side of Libraries.Multitasking.Task: whether a task is waiting,
# running, done, or stopped with an error, and what to do when it is done.
#
# Wait and Pause are waiting actions: coroutines, which the compiled code
# awaits.

_MT_NEW = 0        # never lined up
_MT_WAITING = 1    # lined up, not started yet
_MT_RUNNING = 3    # Run or Then is running, or outside work (_MT_WaitFor)
_MT_DONE = 2       # Run and Then done, or Run stopped with an error


class plugins_quorum_Libraries_Multitasking_Task_:
    def __init__(self):
        self.state = _MT_NEW
        self.error = None
        self.result = None      # what outside work gave back (_MT_WaitFor)
        self.line = None        # the line it was last lined up on
        self.when_done = []     # what to do when done; each is told whether it failed

    # Lines calls this when the task lines up. Lining it up again starts over.
    def LinedUp(self, line):
        self.error = None
        self.line = line
        self.state = _MT_WAITING

    # The line the task is in, is running on, or last ran on; undefined if
    # it was never lined up.
    def GetLine(self):
        return None if self.line is None else self.line.GetHandle()

    def SetRunning(self):
        self.state = _MT_RUNNING

    # Whether the task is lined up or running: something to wait for.
    def IsPending(self):
        return self.state == _MT_WAITING or self.state == _MT_RUNNING

    def IsRunning(self):
        return self.state == _MT_RUNNING

    def IsFinished(self):
        return self.state == _MT_DONE

    # Whether the task is done and stopped with an error (Task:HasError).
    def HasError(self):
        return self.state == _MT_DONE and self.error is not None

    async def Pause__quorum_number(self, milliseconds):
        await _MT.Pause(milliseconds)

    def SetError(self, error):
        self.error = error

    def SetDone(self):
        self.state = _MT_DONE
        steps = self.when_done
        self.when_done = []
        failed = self.error is not None
        for step in steps:
            step(failed)
        _MT.NotifyChanged()

    # Runs "step" when this task is done, or right away if it already is.
    def WhenDone(self, step):
        if self.state == _MT_DONE:
            step(self.error is not None)
        else:
            self.when_done.append(step)

    # Waits until the task is done. A task that was never lined up has
    # nothing to wait for. An error in the task's Run is passed on.
    async def Wait(self):
        await _MT.WaitUntil(lambda: not self.IsPending(), -1)
        self.PassOnError()

    async def Wait__quorum_number(self, milliseconds):
        if not await _MT.WaitUntil(lambda: not self.IsPending(), milliseconds):
            return False
        self.PassOnError()
        return True

    def PassOnError(self):
        if self.error is not None:
            raise self.error


# The Task plugin of any task. A plain Task has the plugin itself. A class
# that is a Task keeps its Task part in a field whose name ends in "__"
# (Libraries_Multitasking_Task__ for a direct child); deeper classes are
# searched the same way.
def _MT_PluginOf(task):
    plugin = getattr(task, "plugin_", None)
    if isinstance(plugin, plugins_quorum_Libraries_Multitasking_Task_):
        return plugin
    part = getattr(task, "Libraries_Multitasking_Task__", None)
    if part is not None:
        return part.plugin_
    for field, value in task.__dict__.items():
        if field.endswith("__") and value is not None and value is not task and hasattr(value, "__dict__"):
            found = _MT_PluginOf(value)
            if found is not None:
                return found
    return None
