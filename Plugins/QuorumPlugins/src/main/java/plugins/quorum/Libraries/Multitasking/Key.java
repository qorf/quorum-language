package plugins.quorum.Libraries.Multitasking;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/*
    The Java side of Libraries.Multitasking.Key: a ReentrantLock. Every
    in-sync class written in Quorum uses this one plugin, instead of each
    having its own.

    Reentrant: a thread that already holds the key may take it again, and
    must return it once per time it took it. Returning it from a thread that
    doesn't hold it is an error (IllegalMonitorStateException).

    ReturnAndWait is what other languages call waiting on a condition: give
    the key back, wait until another line returns it (so it may have changed
    something), then take it again. "returns" counts the times the key was
    given back, so a waiter can tell that someone used it. Waiting goes
    through LineQueue.WaitUntil, so a waiting main program still runs
    main-line tasks.
*/
public class Key {
    public java.lang.Object me_ = null;

    private final ReentrantLock lock = new ReentrantLock();
    private volatile long returns = 0;
    private final AtomicInteger waiting = new AtomicInteger();

    public void Take() {
        lock.lock();
    }

    public void Return() {
        returns = returns + 1;   // only the holder writes it
        lock.unlock();
        if (waiting.get() > 0) {
            LineQueue.NotifyChanged();
        }
    }

    public void ReturnAndWait() {
        ReturnAndWaitWithin(-1);
    }

    public boolean ReturnAndWait(double milliseconds) {
        return ReturnAndWaitWithin(milliseconds);
    }

    // Returns true if another line used the key, false if the time ran out.
    // Either way the caller holds the key again, as many times as before.
    private boolean ReturnAndWaitWithin(double milliseconds) {
        int held = lock.getHoldCount();
        if (held == 0) {
            throw new IllegalMonitorStateException("ReturnAndWait needs the key: call Take first.");
        }
        waiting.incrementAndGet();
        long seen = returns;
        for (int i = 0; i < held; i++) {
            lock.unlock();
        }
        boolean used;
        try {
            used = LineQueue.WaitUntil(() -> returns != seen, milliseconds);
        } finally {
            for (int i = 0; i < held; i++) {
                lock.lock();
            }
            waiting.decrementAndGet();
        }
        return used;
    }
}
