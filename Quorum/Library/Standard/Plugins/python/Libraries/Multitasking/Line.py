# The Python side of Libraries.Multitasking.Line: one line. A Line the
# program makes is a new line of its own, created when first used. Every line
# has exactly one Quorum Line object, which Task:GetLine, Lines:GetLine and
# Lines:GetMain all return, so lines compare with =.
#
# Python plugins aren't told which Quorum object they belong to, so a line
# the program made learns its Quorum Line when it is first lined up on
# (Lines calls SetHandle).


class plugins_quorum_Libraries_Multitasking_Line_:
    def __init__(self):
        self.queue = None

    def SetQueue(self, line):
        self.queue = line

    def GetQueue(self):
        if self.queue is None:
            self.queue = _MT.NewNamed()
        return self.queue

    def GetName(self):
        return self.GetQueue().name

    def IsMain(self):
        return self.GetQueue().kind == _MT_MAIN

    # Waits for every task on this line, whoever lined it up.
    async def Wait(self):
        line = self.GetQueue()
        await _MT.WaitUntil(line.IsFinished, -1)

    async def Wait__quorum_number(self, milliseconds):
        line = self.GetQueue()
        return await _MT.WaitUntil(line.IsFinished, milliseconds)

    # How many tasks are waiting in this line, not counting any running.
    def GetSize(self):
        return self.GetQueue().GetWaitingCount()

    def IsFinished(self):
        return self.GetQueue().IsFinished()
