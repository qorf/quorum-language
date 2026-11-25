/*
    The JavaScript side of Libraries.Multitasking.Line: one line. A Line the
    program makes is a new line of its own, created when first used. Every
    line has exactly one Quorum Line object, which Task:GetLine,
    Lines:GetLine and Lines:GetMain all return, so lines compare with =.
*/
function plugins_quorum_Libraries_Multitasking_Line_(quorumLine) {
    var LineQueue = plugins_quorum_Libraries_Multitasking_LineQueue_;
    var queue = null;

    this.me_ = quorumLine;

    this.SetQueue = function (line) {
        queue = line;
    };

    this.GetQueue = function () {
        if (queue === null) {
            queue = LineQueue.NewNamed();
            queue.SetHandle(quorumLine);
        }
        return queue;
    };

    this.GetName = function () {
        return this.GetQueue().name;
    };

    this.IsMain = function () {
        return LineQueue.IsMainKind(this.GetQueue());
    };

    // Waits for every task on this line, whoever lined it up.
    this.Wait = function () {
        var line = this.GetQueue();
        return LineQueue.WaitUntil(function () { return line.IsFinished(); }, -1).then(function () {});
    };

    this.Wait$quorum_number = function (milliseconds) {
        var line = this.GetQueue();
        return LineQueue.WaitUntil(function () { return line.IsFinished(); }, milliseconds);
    };

    // How many tasks are waiting in this line, not counting any running.
    this.GetSize = function () {
        return this.GetQueue().GetWaitingCount();
    };

    this.IsFinished = function () {
        return this.GetQueue().IsFinished();
    };
}
