package plugins.quorum.Libraries.Multitasking;

import quorum.Libraries.Multitasking.Line_;
import quorum.Libraries.Multitasking.Task_;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/*
    The Java side of Libraries.Multitasking.Lines: takes tasks and puts them
    in lines, now or later, and keeps track of the tasks it was given.
*/
public class Lines {
    public java.lang.Object me_ = null;

    // One timer thread for the whole program, for LineUpAfter and
    // LineUpEvery. It only lines tasks up; it never runs them.
    private static final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(work -> {
        Thread thread = new Thread(work, "timer");
        thread.setDaemon(true);
        return thread;
    });

    // Tasks given to this object that aren't done: waiting, running, or
    // waiting for their time (LineUpAfter).
    private final AtomicInteger unfinished = new AtomicInteger();
    private final AtomicInteger finishedCount = new AtomicInteger();

    // The first error from this object's tasks that no Wait has passed on yet.
    private final AtomicReference<Throwable> firstError = new AtomicReference<>();

    // Finished tasks that WaitForAny hasn't returned yet, in the order they
    // finished.
    private final ConcurrentLinkedQueue<Task_> finishedNotSeen = new ConcurrentLinkedQueue<>();

    // A repeating task's turns. "stopped" is checked under the lock inside
    // each turn, so once StopRepeating returns no turn can line the task up
    // again.
    private static final class Turns {
        ScheduledFuture<?> future = null;
        boolean stopped = false;
    }

    // Repeating tasks, so StopRepeating can stop them.
    private final Map<Task_, Turns> repeating = new ConcurrentHashMap<>();

    // Every line this object has put a task in, in the order first used.
    // Guarded by "this". Never handed out; GetLine and GetSize read it.
    private final ArrayList<LineQueue> linesUsed = new ArrayList<>();

    // The general line: whichever counter is free first.
    public void LineUp(Task_ task) {
        LineUpOn(task, LineQueue.General());
    }

    // A particular line. Any thread may line up a task on any line.
    public void LineUp(Task_ task, Line_ line) {
        LineUpOn(task, QueueOf(line));
    }

    private static LineQueue QueueOf(Line_ line) {
        Line handle = ((quorum.Libraries.Multitasking.Line) line).plugin_;
        return handle.GetQueue();
    }

    private void LineUpOn(Task_ task, LineQueue line) {
        Task state = Reserve(task, line);
        line.Add(new LineQueue.Entry(task, state, this, line));
    }

    // Counts the task as given to this object and as waiting, before it is in
    // a line. LineUpAfter reserves now and adds the task to its line later.
    private Task Reserve(Task_ task, LineQueue line) {
        synchronized (this) {
            if (!linesUsed.contains(line)) {
                linesUsed.add(line);
            }
        }
        Task state = Task.PluginOf(task);
        state.LinedUp(line);
        unfinished.incrementAndGet();
        return state;
    }

    // Lines up a task after a delay. The task counts as waiting from now, so
    // Wait, Task:Wait and IsFinished include it.
    public void LineUpAfter(Task_ task, double milliseconds) {
        LineUpAfterOn(task, LineQueue.General(), milliseconds);
    }

    public void LineUpAfter(Task_ task, Line_ line, double milliseconds) {
        LineUpAfterOn(task, QueueOf(line), milliseconds);
    }

    private void LineUpAfterOn(Task_ task, LineQueue line, double milliseconds) {
        Task state = Reserve(task, line);
        timer.schedule(() -> line.Add(new LineQueue.Entry(task, state, this, line)),
                Math.max(0, (long) milliseconds), TimeUnit.MILLISECONDS);
    }

    // Lines up a task once another task is done. It counts as waiting from
    // now. If the other task stops with an error, this one doesn't run: it is
    // done, with an error saying why, which Wait passes on.
    public void LineUpWhenDone(Task_ task, Task_ before) {
        LineUpWhenDoneOn(task, LineQueue.General(), before);
    }

    public void LineUpWhenDone(Task_ task, Line_ line, Task_ before) {
        LineUpWhenDoneOn(task, QueueOf(line), before);
    }

    private void LineUpWhenDoneOn(Task_ task, LineQueue line, Task_ before) {
        Task state = Reserve(task, line);
        Task.PluginOf(before).WhenDone(failed -> {
            if (failed) {
                FailWithoutRunning(task, state, "This " + task.getClass().getSimpleName()
                        + " did not run, because the " + before.getClass().getSimpleName()
                        + " it waited for stopped with an error.");
            } else {
                line.Add(new LineQueue.Entry(task, state, this, line));
            }
        });
    }

    private void FailWithoutRunning(Task_ task, Task state, String message) {
        quorum.Libraries.Language.Errors.Error error = new quorum.Libraries.Language.Errors.Error();
        error.SetErrorMessage(message);
        state.SetError(error);
        state.SetDone();
        TaskDone(task, error);
        LineQueue.NotifyChanged();
    }

    // Lines up a task again and again, every so many milliseconds, until
    // StopRepeating. A turn is skipped if the task from the last turn is
    // still waiting or running, so one task never runs twice at once.
    // Between turns the task is done, so Wait doesn't wait forever.
    public void LineUpEvery(Task_ task, double milliseconds) {
        LineUpEveryOn(task, LineQueue.General(), milliseconds);
    }

    public void LineUpEvery(Task_ task, Line_ line, double milliseconds) {
        LineUpEveryOn(task, QueueOf(line), milliseconds);
    }

    private void LineUpEveryOn(Task_ task, LineQueue line, double milliseconds) {
        StopRepeating(task);
        long period = Math.max(1, (long) milliseconds);
        Task state = Task.PluginOf(task);
        Turns turns = new Turns();
        synchronized (turns) {
            turns.future = timer.scheduleAtFixedRate(() -> {
                synchronized (turns) {
                    if (!turns.stopped && !state.IsPending()) {
                        LineUpOn(task, line);
                    }
                }
            }, period, period, TimeUnit.MILLISECONDS);
        }
        repeating.put(task, turns);
    }

    // Stops lining the task up again. A turn already in line still runs.
    public void StopRepeating(Task_ task) {
        Turns turns = repeating.remove(task);
        if (turns != null) {
            synchronized (turns) {
                turns.stopped = true;
                turns.future.cancel(false);
            }
        }
    }

    void TaskDone(Task_ task, Throwable error) {
        if (error != null) {
            firstError.compareAndSet(null, error);
        }
        finishedNotSeen.offer(task);
        finishedCount.incrementAndGet();
        unfinished.decrementAndGet();
    }

    public Line_ GetMain() {
        return LineQueue.Main().GetHandle();
    }

    // The lines this object has put tasks in, like an array: 0 is the first
    // line used.
    public synchronized Line_ GetLine(int index) throws quorum.Libraries.Language.Errors.InvalidLocationError {
        if (index < 0 || index >= linesUsed.size()) {
            quorum.Libraries.Language.Errors.InvalidLocationError error = new quorum.Libraries.Language.Errors.InvalidLocationError();
            error.SetErrorMessage("There is no line " + index + "; this Lines object has used " + linesUsed.size() + ".");
            throw error;
        }
        return linesUsed.get(index).GetHandle();
    }

    public synchronized int GetSize() {
        return linesUsed.size();
    }

    public boolean IsFinished() {
        return unfinished.get() == 0;
    }

    public int GetFinishedCount() {
        return finishedCount.get();
    }

    // Waits for every task this object was given, including ones given while
    // waiting, then passes on the first error from them, once.
    public void Wait() throws quorum.Libraries.Language.Errors.Error {
        LineQueue.WaitUntil(this::IsFinished, -1);
        PassOnError();
    }

    public boolean Wait(double milliseconds) throws quorum.Libraries.Language.Errors.Error {
        if (!LineQueue.WaitUntil(this::IsFinished, milliseconds)) {
            return false;
        }
        PassOnError();
        return true;
    }

    private void PassOnError() throws quorum.Libraries.Language.Errors.Error {
        LineQueue.PassOn(firstError.getAndSet(null));
    }

    // Waits for the next task to finish and returns it: each finished task
    // once, in the order they finished. Returns undefined when every task
    // this object was given has been returned already.
    public Task_ WaitForAny() {
        return WaitForAnyWithin(-1);
    }

    public Task_ WaitForAny(double milliseconds) {
        return WaitForAnyWithin(milliseconds);
    }

    private Task_ WaitForAnyWithin(double milliseconds) {
        LineQueue.WaitUntil(() -> !finishedNotSeen.isEmpty() || IsFinished(), milliseconds);
        return finishedNotSeen.poll();
    }

    public void Pause(double milliseconds) {
        LineQueue.Pause(milliseconds);
    }

    // How many general-line tasks may run at once, for the whole program:
    // the number of counters serving it. Java has no cap above 1; the
    // JavaScript and Python plugins keep it at 1.
    public void SetMaximumAtOnce(int count) {
        LineQueue.General().SetCounterCount(count);
    }

    public int GetMaximumAtOnce() {
        return LineQueue.General().GetCounterCount();
    }

    // Java has no cap of its own on how many tasks run at once.
    public int GetPlatformMaximumAtOnce() {
        return Integer.MAX_VALUE;
    }
}
