# The Python side of Libraries.Multitasking.Lines: takes tasks and puts them
# in lines, now or later, and keeps track of the tasks it was given.
#
# Wait, WaitForAny and Pause are waiting actions: coroutines, which the
# compiled code awaits.
#
# With one thread, one general-line task runs at a time, so the most at once
# is always 1 and SetMaximumAtOnce does nothing.


class _MT_Entry:
    def __init__(self, task, state, lines, line):
        self.task = task
        self.state = state
        self.lines = lines
        self.line = line


class plugins_quorum_Libraries_Multitasking_Lines_:
    def __init__(self):
        self.unfinished = 0          # tasks given to this object that aren't done
        self.finished_count = 0
        self.first_error = None      # the first error no Wait has passed on yet
        self.finished_not_seen = []  # finished tasks WaitForAny hasn't returned, in order
        self.repeating = {}          # id of a repeating task -> how to stop its turns
        self.lines_used = []         # every line this object has used, in order first used

    # The general line.
    def LineUp__quorum_Libraries_Multitasking_Task(self, task):
        self.LineUpOn(task, _MT.general_line)

    # A particular line. Any line's code may line up a task on any line.
    def LineUp__quorum_Libraries_Multitasking_Task__quorum_Libraries_Multitasking_Line(self, task, line):
        self.LineUpOn(task, self.QueueOf(line))

    # The line a Quorum Line stands for. The first time a line the program
    # made is used, it learns its Quorum Line.
    def QueueOf(self, line):
        queue = line.plugin_.GetQueue()
        queue.SetHandle(line)
        return queue

    def LineUpOn(self, task, line):
        state = self.Reserve(task, line)
        line.Add(_MT_Entry(task, state, self, line))

    # Counts the task as given to this object and as waiting, before it is in
    # a line. LineUpAfter reserves now and adds the task to its line later.
    def Reserve(self, task, line):
        if line not in self.lines_used:
            self.lines_used.append(line)
        state = _MT_PluginOf(task)
        state.LinedUp(line)
        self.unfinished = self.unfinished + 1
        return state

    # Lines up a task after a delay. It counts as waiting from now, so Wait,
    # Task:Wait and IsFinished include it.
    def LineUpAfter__quorum_Libraries_Multitasking_Task__quorum_number(self, task, milliseconds):
        self.LineUpAfterOn(task, _MT.general_line, milliseconds)

    def LineUpAfter__quorum_Libraries_Multitasking_Task__quorum_Libraries_Multitasking_Line__quorum_number(self, task, line, milliseconds):
        self.LineUpAfterOn(task, self.QueueOf(line), milliseconds)

    def LineUpAfterOn(self, task, line, milliseconds):
        state = self.Reserve(task, line)
        _MT.After(milliseconds, lambda: line.Add(_MT_Entry(task, state, self, line)))

    # Lines up a task once another task is done. It counts as waiting from
    # now. If the other task stops with an error, this one doesn't run: it is
    # done, with an error saying why, which Wait passes on.
    def LineUpWhenDone__quorum_Libraries_Multitasking_Task__quorum_Libraries_Multitasking_Task(self, task, before):
        self.LineUpWhenDoneOn(task, _MT.general_line, before)

    def LineUpWhenDone__quorum_Libraries_Multitasking_Task__quorum_Libraries_Multitasking_Line__quorum_Libraries_Multitasking_Task(self, task, line, before):
        self.LineUpWhenDoneOn(task, self.QueueOf(line), before)

    def LineUpWhenDoneOn(self, task, line, before):
        state = self.Reserve(task, line)

        def Next(failed):
            if failed:
                self.FailWithoutRunning(task, state, "This " + _MT_TaskName(task)
                                        + " did not run, because the " + _MT_TaskName(before)
                                        + " it waited for stopped with an error.")
            else:
                line.Add(_MT_Entry(task, state, self, line))

        _MT_PluginOf(before).WhenDone(Next)

    def FailWithoutRunning(self, task, state, message):
        error = _MT_NewError(message)
        state.SetError(error)
        state.SetDone()
        self.TaskDone(task, error)
        _MT.NotifyChanged()

    # Lines up a task again and again, every so many milliseconds, until
    # StopRepeating. A turn is skipped if the task from the last turn is still
    # waiting or running, so one task never runs twice at once. Between turns
    # the task is done, so Wait doesn't wait forever.
    def LineUpEvery__quorum_Libraries_Multitasking_Task__quorum_number(self, task, milliseconds):
        self.LineUpEveryOn(task, _MT.general_line, milliseconds)

    def LineUpEvery__quorum_Libraries_Multitasking_Task__quorum_Libraries_Multitasking_Line__quorum_number(self, task, line, milliseconds):
        self.LineUpEveryOn(task, self.QueueOf(line), milliseconds)

    def LineUpEveryOn(self, task, line, milliseconds):
        self.StopRepeating__quorum_Libraries_Multitasking_Task(task)
        state = _MT_PluginOf(task)

        def Turn():
            if not state.IsPending():
                self.LineUpOn(task, line)

        self.repeating[id(task)] = _MT.Every(milliseconds, Turn)

    # Stops lining the task up again. A turn already in line still runs.
    def StopRepeating__quorum_Libraries_Multitasking_Task(self, task):
        stop = self.repeating.pop(id(task), None)
        if stop is not None:
            stop()

    def TaskDone(self, task, error):
        if error is not None and self.first_error is None:
            self.first_error = error
        self.finished_not_seen.append(task)
        self.finished_count = self.finished_count + 1
        self.unfinished = self.unfinished - 1

    def GetMain(self):
        return _MT.main_line.GetHandle()

    # The lines this object has put tasks in, like an array: 0 is the first
    # line used.
    def GetLine__quorum_integer(self, index):
        if index < 0 or index >= len(self.lines_used):
            raise _MT_NewError("There is no line " + str(index) + "; this Lines object has used "
                               + str(len(self.lines_used)) + ".",
                               "quorum_Libraries_Language_Errors_InvalidLocationError_")
        return self.lines_used[index].GetHandle()

    def GetSize(self):
        return len(self.lines_used)

    def IsFinished(self):
        return self.unfinished == 0

    def GetFinishedCount(self):
        return self.finished_count

    # Waits for every task this object was given, including ones given while
    # waiting, then passes on the first error from them, once.
    async def Wait(self):
        await _MT.WaitUntil(self.IsFinished, -1)
        self.PassOnError()

    async def Wait__quorum_number(self, milliseconds):
        if not await _MT.WaitUntil(self.IsFinished, milliseconds):
            return False
        self.PassOnError()
        return True

    def PassOnError(self):
        failure = self.first_error
        self.first_error = None
        if failure is not None:
            raise failure

    # Waits for the next task to finish and returns it: each finished task
    # once, in the order they finished. Returns undefined when every task this
    # object was given has been returned already.
    async def WaitForAny(self):
        return await self.WaitForAnyWithin(-1)

    async def WaitForAny__quorum_number(self, milliseconds):
        return await self.WaitForAnyWithin(milliseconds)

    async def WaitForAnyWithin(self, milliseconds):
        await _MT.WaitUntil(lambda: len(self.finished_not_seen) > 0 or self.IsFinished(), milliseconds)
        return self.finished_not_seen.pop(0) if len(self.finished_not_seen) > 0 else None

    async def Pause__quorum_number(self, milliseconds):
        await _MT.Pause(milliseconds)

    def SetMaximumAtOnce__quorum_integer(self, count):
        pass

    def GetMaximumAtOnce(self):
        return 1

    # One thread: one task at a time, whatever SetMaximumAtOnce is given.
    def GetPlatformMaximumAtOnce(self):
        return 1
