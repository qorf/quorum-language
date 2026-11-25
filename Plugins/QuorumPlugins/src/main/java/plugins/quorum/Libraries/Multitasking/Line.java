package plugins.quorum.Libraries.Multitasking;

/*
    The Java side of Libraries.Multitasking.Line: one line. A Line the program
    makes is a new line of its own, created when first used. Every line has
    exactly one Quorum Line object, which Task:GetLine, Lines:GetLine and
    Lines:GetMain all return, so lines can be compared with =.
*/
public class Line {
    public java.lang.Object me_ = null;

    private LineQueue queue = null;

    synchronized void SetQueue(LineQueue line) {
        queue = line;
    }

    synchronized LineQueue GetQueue() {
        if (queue == null) {
            queue = LineQueue.NewNamed();
            queue.SetHandle((quorum.Libraries.Multitasking.Line_) me_);
        }
        return queue;
    }

    public String GetName() {
        return GetQueue().name;
    }

    public boolean IsMain() {
        return GetQueue().kind == LineQueue.Kind.MAIN;
    }

    // Waits for every task on this line, whoever lined it up.
    public void Wait() {
        LineQueue line = GetQueue();
        LineQueue.WaitUntil(line::IsFinished, -1);
    }

    public boolean Wait(double milliseconds) {
        LineQueue line = GetQueue();
        return LineQueue.WaitUntil(line::IsFinished, milliseconds);
    }

    // How many tasks are waiting in this line, not counting any running.
    public int GetSize() {
        return GetQueue().GetWaitingCount();
    }

    public boolean IsFinished() {
        return GetQueue().IsFinished();
    }
}
