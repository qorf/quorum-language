package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.Task_;
import quorum.Libraries.Concurrency.TaskStatus_;

import java.util.concurrent.*;

public class Tasks {
    public java.lang.Object me_ = null;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    int poolSize = 1;

    public void SetToSingleThread() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void SetThreadPoolSize(int size) {
        if(size <= 1) {
            executor = Executors.newSingleThreadExecutor();
            poolSize = 1;
        } else {
            executor = Executors.newFixedThreadPool(size);
            poolSize = size;
        }
    }

    public int GetThreadPoolSize() {
        return poolSize;
    }
    public TaskStatus_ Do(Task_ behave) {
        QuorumCallable callable = new QuorumCallable();
        callable.setFutureBehavior(behave);
        java.util.concurrent.Future future = executor.submit(callable);

        quorum.Libraries.Concurrency.TaskStatus quorumFuture = new quorum.Libraries.Concurrency.TaskStatus();
        quorumFuture.SetTask(behave);

        TaskStatus plugin = quorumFuture.plugin_;
        plugin.setJavaFuture(future);
        return quorumFuture;
    }

    public void Shutdown() {
        executor.shutdown();
    }

    public void ShutdownNow() {
        executor.shutdownNow();
    }

    public boolean IsShutdown() {
        return executor.isShutdown();
    }

    public boolean IsFinished() {
        return executor.isTerminated();
    }

    public boolean Wait() {
        try {
            // Wait forever until all tasks are completed
            return executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Wait(double milliseconds) {

        try {
            // Wait forever until all tasks are completed
            return executor.awaitTermination((long)milliseconds, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
