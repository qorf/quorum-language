package plugins.quorum.Libraries.Multitasking;

import quorum.Libraries.Multitasking.Task_;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.concurrent.ConcurrentHashMap;

/*
    The Java side of Libraries.Multitasking.Task: whether a task is waiting,
    done, or stopped with an error. "state" and "error" are volatile, so the
    line that ran the task and the line asking about it agree. Seeing the
    task as done also makes everything the task did (its fields, so its
    getters) visible to the line that asked.
*/
public class Task {
    public java.lang.Object me_ = null;

    private static final int NEW = 0;       // never lined up
    private static final int WAITING = 1;   // lined up, not started yet
    private static final int RUNNING = 3;   // Run or Then is running
    private static final int DONE = 2;      // Run and Then done, or Run stopped with an error

    private volatile int state = NEW;
    private volatile Throwable error = null;
    private volatile LineQueue line = null;   // the line it was last lined up on

    // What to do when this task is done (Lines:LineUpWhenDone): each is
    // told whether the task stopped with an error. Guarded by "this".
    private final ArrayList<Consumer<Boolean>> whenDone = new ArrayList<>();

    // How to reach the Task part of each kind of task class, found once.
    private static final ConcurrentHashMap<Class<?>, Method> parentGetters = new ConcurrentHashMap<>();

    /*
        The Task plugin of any task. A plain Task is a
        quorum.Libraries.Multitasking.Task. A class that is a Task keeps its
        Task part in a field, which the compiler gives a getter named
        parentLibraries_Multitasking_Task_.
    */
    static Task PluginOf(Task_ task) {
        if (task instanceof quorum.Libraries.Multitasking.Task) {
            return ((quorum.Libraries.Multitasking.Task) task).plugin_;
        }
        try {
            Method getter = parentGetters.computeIfAbsent(task.getClass(), type -> {
                try {
                    return type.getMethod("parentLibraries_Multitasking_Task_");
                } catch (NoSuchMethodException e) {
                    throw new IllegalStateException(type.getName() + " has no Task part", e);
                }
            });
            quorum.Libraries.Multitasking.Task part = (quorum.Libraries.Multitasking.Task) getter.invoke(task);
            return part.plugin_;
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Could not reach the Task part of " + task.getClass().getName(), e);
        }
    }

    // Lines calls this when the task lines up. Lining it up again starts over.
    void LinedUp(LineQueue line) {
        error = null;
        this.line = line;
        state = WAITING;
    }

    // The line the task is in, is running on, or last ran on; undefined if
    // it was never lined up.
    public quorum.Libraries.Multitasking.Line_ GetLine() {
        LineQueue current = line;
        return current == null ? null : current.GetHandle();
    }

    void SetRunning() {
        state = RUNNING;
    }

    // Whether the task is lined up or running: something to wait for.
    boolean IsPending() {
        int now = state;
        return now == WAITING || now == RUNNING;
    }

    public boolean IsRunning() {
        return state == RUNNING;
    }

    public void Pause(double milliseconds) {
        LineQueue.Pause(milliseconds);
    }

    void SetError(Throwable error) {
        this.error = error;
    }

    void SetDone() {
        ArrayList<Consumer<Boolean>> next;
        synchronized (this) {
            state = DONE;
            next = new ArrayList<>(whenDone);
            whenDone.clear();
        }
        boolean failed = error != null;
        for (Consumer<Boolean> step : next) {
            step.accept(failed);
        }
    }

    // Runs "next" when this task is done, or right away if it already is.
    void WhenDone(Consumer<Boolean> next) {
        boolean doneAlready;
        synchronized (this) {
            doneAlready = state == DONE;
            if (!doneAlready) {
                whenDone.add(next);
            }
        }
        if (doneAlready) {
            next.accept(error != null);
        }
    }

    public boolean IsFinished() {
        return state == DONE;
    }

    // Whether the task is done and stopped with an error. "state" is read
    // first: the error is set before the task is marked done, so once it
    // reads as done the error is there to see.
    public boolean HasError() {
        return state == DONE && error != null;
    }

    // Waits until the task is done. A task that was never lined up has
    // nothing to wait for. An error in the task's Run is passed on.
    public void Wait() throws quorum.Libraries.Language.Errors.Error {
        LineQueue.WaitUntil(() -> !IsPending(), -1);
        PassOnError();
    }

    public boolean Wait(double milliseconds) throws quorum.Libraries.Language.Errors.Error {
        if (!LineQueue.WaitUntil(() -> !IsPending(), milliseconds)) {
            return false;
        }
        PassOnError();
        return true;
    }

    private void PassOnError() throws quorum.Libraries.Language.Errors.Error {
        LineQueue.PassOn(error);
    }
}
