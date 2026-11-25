/*
    The JavaScript side of Libraries.Multitasking.Key. Every in-sync class
    written in Quorum uses this one plugin.

    With one thread, only one line's code runs at a time, so taking a free
    key never waits. The key still records which line holds it:
    - The holder may take it again, and must return it once per time it
      took it.
    - Returning a key you don't hold is an error.
    - A line must not wait while it holds a key (except in ReturnAndWait,
      which gives it back first). If another line then tried to take it,
      that would need to wait, and Take is not a waiting action; so that is
      an error, with a message saying why.

    ReturnAndWait is a waiting action: give the key back, wait until another
    line returns it (so it may have changed something), then take it again.
*/
function plugins_quorum_Libraries_Multitasking_Key_(quorumKey) {
    var LineQueue = plugins_quorum_Libraries_Multitasking_LineQueue_;
    var holder = null;      // the line's code holding the key
    var holds = 0;          // how many times the holder took it
    var returns = 0;        // how many times the key was given back

    this.me_ = quorumKey;

    this.Take = function () {
        var here = LineQueue.Current();
        if (holds > 0 && holder !== here) {
            throw LineQueue.NewError("Another line holds this key while it waits. "
                    + "A line must give a key back before it waits.");
        }
        holder = here;
        holds = holds + 1;
    };

    this.Return = function () {
        if (holds === 0 || holder !== LineQueue.Current()) {
            throw LineQueue.NewError("Return needs the key: call Take first.");
        }
        holds = holds - 1;
        if (holds === 0) {
            holder = null;
        }
        returns = returns + 1;
        LineQueue.NotifyChanged();
    };

    this.ReturnAndWait = function () {
        return ReturnAndWaitWithin(-1).then(function () {});
    };

    this.ReturnAndWait$quorum_number = function (milliseconds) {
        return ReturnAndWaitWithin(milliseconds);
    };

    // Gives true if another line used the key, false if the time ran out.
    // Either way the caller holds the key again, as many times as before.
    function ReturnAndWaitWithin(milliseconds) {
        var here = LineQueue.Current();
        if (holds === 0 || holder !== here) {
            throw LineQueue.NewError("ReturnAndWait needs the key: call Take first.");
        }
        var held = holds;
        var seen = returns;
        holds = 0;
        holder = null;
        return LineQueue.WaitUntil(function () {
            return returns !== seen && holds === 0;
        }, milliseconds).then(function (used) {
            holder = here;
            holds = held;
            return used;
        });
    }
}
