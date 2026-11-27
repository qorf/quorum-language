package plugins.quorum.Libraries.Multitasking;

import quorum.Libraries.Multitasking.Task_;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;

/*
    The tasks waiting in one line, and the threads that serve them. Not a
    Quorum class; Lines and Line use it. There are three kinds:

    - The main line. Nothing serves it on its own: the main program's thread
      runs its tasks only while it waits (Lines:Wait, Line:Wait,
      Task:Wait). Which thread that is, the platform tells Lines
      (Lines.SetStartingThread). Another thread with
      no line, one the scheduler didn't start, is then "outside": it waits
      by blocking and runs no line's tasks.
    - A named line (a Line the program made). One task at a time, in order,
      on a thread of its own. The thread starts when a task arrives and
      stops when the line is empty, so idle lines cost no thread.
    - The general line (Lines:LineUp with no line). One line served by
      several counters, like a bank: the next task goes to whichever
      counter is free first. Tasks start in the order they lined up, but
      several run at once. Counters start as tasks arrive, up to the count,
      and stop after a while with nothing to do.

    Thread safety, since Quorum has no synchronized:
    - ConcurrentLinkedQueue is safe to add to and take from at once, and
      whatever a thread did before adding a task is visible to the thread
      that takes it off.
    - Taking from an empty ConcurrentLinkedQueue returns null rather than
      blocking, so threads sleep on Java monitors until woken.
    - "changed" is one monitor for everyone waiting on tasks: it is notified
      whenever a task is added or finishes.
*/
final class LineQueue {
    enum Kind { MAIN, NAMED, GENERAL }

    // One task waiting in a line, and where to report when it is done.
    static final class Entry {
        final Task_ task;
        final Task state;
        final Lines lines;
        final LineQueue line;

        Entry(Task_ task, Task state, Lines lines, LineQueue line) {
            this.task = task;
            this.state = state;
            this.lines = lines;
            this.line = line;
        }
    }

    // How long an idle counter waits for work before it stops.
    private static final long COUNTER_IDLE_MILLISECONDS = 30_000;

    private static final Object changed = new Object();
    private static final AtomicInteger namedCount = new AtomicInteger();
    private static final ThreadLocal<LineQueue> currentLine = new ThreadLocal<>();
    private static final LineQueue mainLine = new LineQueue(Kind.MAIN, "main");
    private static final LineQueue generalLine = new LineQueue(Kind.GENERAL, "general");

    final Kind kind;
    final String name;
    private final ConcurrentLinkedQueue<Entry> queue = new ConcurrentLinkedQueue<>();
    private final AtomicInteger unfinished = new AtomicInteger();
    private final Object lock = new Object();

    // Named line: whether its thread is running. General line: how many
    // counters may run, how many are running, and how many are idle.
    // All guarded by "lock".
    private boolean running = false;
    private int counterCount = DefaultCounterCount();
    private int counters = 0;
    private int idleCounters = 0;
    private int countersMade = 0;

    // The one Quorum Line object for this line, so every way of getting the
    // line (Task:GetLine, Lines:GetLine, Lines:GetMain, the Line the program
    // made) gives the same object, and = works on lines. Guarded by "lock".
    private quorum.Libraries.Multitasking.Line_ handle = null;

    private LineQueue(Kind kind, String name) {
        this.kind = kind;
        this.name = name;
    }

    static LineQueue Main() {
        return mainLine;
    }

    static LineQueue General() {
        return generalLine;
    }

    static LineQueue NewNamed() {
        return new LineQueue(Kind.NAMED, "line-" + namedCount.incrementAndGet());
    }

    // Whether the calling thread is the main program's: the one the platform
    // told Lines (see Lines.SetStartingThread), or any thread while nothing
    // has told it.
    static boolean OnMainProgramThread() {
        Thread main = Lines.GetMainThread();
        return main == null || main == Thread.currentThread();
    }

    // The line the calling code is running on: its task's line; the main line
    // for the main program, which is not running any line's task; or null
    // for an outside thread, one the scheduler didn't start that isn't the
    // main program's.
    static LineQueue Current() {
        LineQueue line = currentLine.get();
        if (line != null) {
            return line;
        }
        return OnMainProgramThread() ? mainLine : null;
    }

    // The line a waiting thread may run tasks from: its own, except a named
    // line's thread, which just blocks, and an outside thread, which helps no
    // line. Asked on every pass of a wait, since the platform may tell Lines
    // the main thread while a thread is already waiting.
    private static LineQueue HelpWith(LineQueue here) {
        if (here == null || here.kind == Kind.NAMED) {
            return null;
        }
        if (here == mainLine && !OnMainProgramThread()) {
            return null;
        }
        return here;
    }

    // One fewer counter than the computer has processor cores, leaving one
    // for the main program, but at least one.
    private static int DefaultCounterCount() {
        return Math.max(1, Runtime.getRuntime().availableProcessors() - 1);
    }

    // The line's Quorum Line object, made the first time it is asked for
    // unless the program made one first (see SetHandle).
    quorum.Libraries.Multitasking.Line_ GetHandle() {
        synchronized (lock) {
            if (handle == null) {
                quorum.Libraries.Multitasking.Line made = new quorum.Libraries.Multitasking.Line();
                made.plugin_.SetQueue(this);
                handle = made;
            }
            return handle;
        }
    }

    // A Line the program made becomes its line's one Quorum object.
    void SetHandle(quorum.Libraries.Multitasking.Line_ line) {
        synchronized (lock) {
            if (handle == null) {
                handle = line;
            }
        }
    }

    // How many tasks are waiting in the line, not counting ones running.
    int GetWaitingCount() {
        return queue.size();
    }

    boolean IsFinished() {
        return unfinished.get() == 0;
    }

    // Any thread may add a task.
    void Add(Entry entry) {
        unfinished.incrementAndGet();   // before the offer, so IsFinished can't be true while it waits
        queue.offer(entry);

        if (kind == Kind.NAMED) {
            synchronized (lock) {
                if (!running) {
                    running = true;
                    StartThread(name, this::NamedLoop);
                }
            }
        } else if (kind == Kind.GENERAL) {
            synchronized (lock) {
                if (idleCounters > 0) {
                    lock.notify();
                } else if (counters < counterCount) {
                    counters = counters + 1;
                    countersMade = countersMade + 1;
                    StartThread("general-" + countersMade, this::CounterLoop);
                }
            }
        }
        NotifyChanged();   // a waiting thread may have work, or a task to help with
    }

    private static void StartThread(String threadName, Runnable loop) {
        Thread thread = new Thread(loop, threadName);
        thread.setDaemon(true);   // a line never keeps the program alive
        thread.start();
    }

    // A named line's thread: run tasks as they come; stop when the line is
    // empty. Add starts a new thread when the next task arrives.
    private void NamedLoop() {
        currentLine.set(this);
        while (true) {
            Entry entry = queue.poll();
            if (entry != null) {
                Run(entry);
                continue;
            }
            synchronized (lock) {
                // Checked inside the lock: Add offers before it takes the
                // lock, so a task added now is either seen here or makes Add
                // start a new thread.
                if (queue.isEmpty()) {
                    running = false;
                    return;
                }
            }
        }
    }

    // A counter on the general line: take the next task in line, run it,
    // repeat. With nothing to do, wait a while, then stop.
    private void CounterLoop() {
        currentLine.set(this);
        while (true) {
            Entry entry = queue.poll();
            if (entry != null) {
                Run(entry);
                continue;
            }
            synchronized (lock) {
                if (!queue.isEmpty()) {
                    continue;
                }
                if (counters > counterCount) {   // the count was lowered
                    counters = counters - 1;
                    return;
                }
                idleCounters = idleCounters + 1;
                try {
                    lock.wait(COUNTER_IDLE_MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                idleCounters = idleCounters - 1;
                if (queue.isEmpty()) {
                    counters = counters - 1;
                    return;
                }
            }
        }
    }

    void SetCounterCount(int count) {
        synchronized (lock) {
            counterCount = Math.max(1, count);
            lock.notifyAll();   // idle counters above the new count can stop
        }
    }

    int GetCounterCount() {
        synchronized (lock) {
            return counterCount;
        }
    }

    // Runs one task off this line on the calling thread, as if it were this
    // line's. Returns false if the line was empty.
    private boolean RunNext() {
        Entry entry = queue.poll();
        if (entry == null) {
            return false;
        }
        LineQueue previous = currentLine.get();
        currentLine.set(this);
        try {
            Run(entry);
        } finally {
            currentLine.set(previous);
        }
        return true;
    }

    // Run, then Then, then report the task done. An error in Run skips Then
    // and is kept for Task:Wait and Lines:Wait to pass on to the code that
    // waits; the library doesn't print it.
    private static void Run(Entry entry) {
        Throwable failure = null;
        entry.state.SetRunning();
        try {
            entry.task.Run();
            entry.task.Then();
        } catch (Throwable error) {
            failure = error;
            entry.state.SetError(error);
        }
        entry.state.SetDone();
        entry.line.unfinished.decrementAndGet();
        entry.lines.TaskDone(entry.task, failure);
        NotifyChanged();
    }

    // Pausing is waiting for nothing, for a while: a thread that pauses helps
    // with its own kind of line meanwhile, as in WaitUntil.
    static void Pause(double milliseconds) {
        if (milliseconds > 0) {
            WaitUntil(() -> false, milliseconds);
        }
    }

    static void NotifyChanged() {
        synchronized (changed) {
            changed.notifyAll();
        }
    }

    /*
        Passes on an error from a task's Run to the code that waited for the
        task, as it was, so detect gets the error itself and its message. A
        Quorum error (from alert, or one of the Error classes) is a Java
        Throwable that isn't a RuntimeException, so it is declared and thrown
        as itself; wrapping it would hide its message. Other Java errors are
        thrown as they are, or wrapped if they are checked.
    */
    static void PassOn(Throwable failure) throws quorum.Libraries.Language.Errors.Error {
        if (failure == null) {
            return;
        }
        if (failure instanceof quorum.Libraries.Language.Errors.Error) {
            throw (quorum.Libraries.Language.Errors.Error) failure;
        }
        if (failure instanceof RuntimeException) {
            throw (RuntimeException) failure;
        }
        if (failure instanceof java.lang.Error) {
            throw (java.lang.Error) failure;
        }
        throw new RuntimeException(failure);
    }

    /*
        Waits until "done" is true, or the time runs out (milliseconds < 0
        means no limit). Returns whether "done" became true.

        While waiting, a thread helps with its own kind of line where it can:
        - the main program (and a main-line task) runs main-line tasks;
        - a counter runs general-line tasks, so counters that all wait on
          other general-line tasks can't stall the line;
        - a named line's thread just blocks;
        - an outside thread (see Current) just blocks.
    */
    static boolean WaitUntil(BooleanSupplier done, double milliseconds) {
        LineQueue here = Current();
        long deadline = milliseconds < 0 ? Long.MAX_VALUE
                : System.nanoTime() + (long) (milliseconds * 1_000_000);

        while (!done.getAsBoolean()) {
            // The same answer for the help below and the check for work in
            // the lock, so a thread that may not help never spins on work it
            // won't run.
            LineQueue helpWith = HelpWith(here);
            if (helpWith != null && helpWith.RunNext()) {
                continue;
            }
            // Counted in nanoseconds and rounded up to whole milliseconds, so
            // a wait never ends early: rounding down would stop with up to a
            // millisecond still to go.
            long remainingNanos = deadline - System.nanoTime();
            if (remainingNanos <= 0) {
                return false;
            }
            long remaining = (remainingNanos + 999_999) / 1_000_000;
            synchronized (changed) {
                // Checked again inside the lock: adding or finishing a task
                // notifies inside this lock, so it can't slip in between this
                // check and the wait.
                boolean hasWork = helpWith != null && !helpWith.queue.isEmpty();
                if (!done.getAsBoolean() && !hasWork) {
                    try {
                        changed.wait(Math.min(remaining, 100));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
