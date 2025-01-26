package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.FutureBehavior_;
import quorum.Libraries.Concurrency.Future_;
import quorum.Libraries.Interface.Behaviors.Behavior_;

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
    public Future_ Do(FutureBehavior_ behave) {
        QuorumCallable callable = new QuorumCallable();
        callable.setFutureBehavior(behave);
        java.util.concurrent.Future future = executor.submit(callable);

        quorum.Libraries.Concurrency.Future quorumFuture = new quorum.Libraries.Concurrency.Future();
        plugins.quorum.Libraries.Concurrency.Future plugin = quorumFuture.plugin_;
        plugin.setBehavior(behave);
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
