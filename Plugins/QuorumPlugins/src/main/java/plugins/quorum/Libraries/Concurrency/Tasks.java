package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.Task_;
import quorum.Libraries.Concurrency.TaskStatus_;

import java.util.concurrent.*;

public class Tasks {
    public java.lang.Object me_ = null;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void SetToSingleThread() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void SetThreadPoolSize(int size) {
        if(size <= 1) {
            executor = Executors.newSingleThreadExecutor();
        } else {
            executor = Executors.newFixedThreadPool(size);
        }
    }
    public TaskStatus_ Do(Task_ behave) {
        QuorumCallable callable = new QuorumCallable();
        callable.setFutureBehavior(behave);
        java.util.concurrent.Future future = executor.submit(callable);

        quorum.Libraries.Concurrency.TaskStatus quorumFuture = new quorum.Libraries.Concurrency.TaskStatus();
        TaskStatus plugin = quorumFuture.plugin_;
        plugin.setTask(behave);
        plugin.setJavaFuture(future);
        return quorumFuture;
    }

    public boolean Wait() {
        executor.shutdown();

        try {
            // Wait forever until all tasks are completed
            return executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Wait(double milliseconds) {
        executor.shutdown();

        try {
            // Wait forever until all tasks are completed
            return executor.awaitTermination((long)milliseconds, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
