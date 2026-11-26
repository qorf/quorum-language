# The Python side of Libraries.Multitasking.Key. Every in-sync class written
# in Quorum uses this one plugin.
#
# With one thread, only one line's code runs at a time, so taking a free key
# never waits. The key still records which line holds it:
# - The holder may take it again, and must return it once per time it took
#   it.
# - Returning a key you don't hold is an error.
# - A line must not wait while it holds a key (except in ReturnAndWait, which
#   gives it back first). If another line then tried to take it, that would
#   need to wait, and Take is not a waiting action; so that is an error, with
#   a message saying why.
#
# ReturnAndWait is a waiting action: give the key back, wait until another
# line returns it (so it may have changed something), then take it again.


class plugins_quorum_Libraries_Multitasking_Key_:
    def __init__(self):
        self.holder = None   # the line's code holding the key
        self.holds = 0       # how many times the holder took it
        self.returns = 0     # how many times the key was given back

    def Take(self):
        here = _MT.Current()
        if self.holds > 0 and self.holder is not here:
            raise _MT_NewError("Another line holds this key while it waits. "
                               + "A line must give a key back before it waits.")
        self.holder = here
        self.holds = self.holds + 1

    def Return(self):
        if self.holds == 0 or self.holder is not _MT.Current():
            raise _MT_NewError("Return needs the key: call Take first.")
        self.holds = self.holds - 1
        if self.holds == 0:
            self.holder = None
        self.returns = self.returns + 1
        _MT.NotifyChanged()

    async def ReturnAndWait(self):
        await self.ReturnAndWaitWithin(-1)

    async def ReturnAndWait__quorum_number(self, milliseconds):
        return await self.ReturnAndWaitWithin(milliseconds)

    # True if another line used the key, false if the time ran out. Either way
    # the caller holds the key again, as many times as before.
    async def ReturnAndWaitWithin(self, milliseconds):
        here = _MT.Current()
        if self.holds == 0 or self.holder is not here:
            raise _MT_NewError("ReturnAndWait needs the key: call Take first.")
        held = self.holds
        seen = self.returns
        self.holds = 0
        self.holder = None
        used = await _MT.WaitUntil(lambda: self.returns != seen and self.holds == 0, milliseconds)
        self.holder = here
        self.holds = held
        return used
