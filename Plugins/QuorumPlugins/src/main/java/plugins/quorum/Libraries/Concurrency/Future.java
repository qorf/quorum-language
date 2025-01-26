package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.FutureBehavior_;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Future {
    public java.lang.Object me_ = null;
    private FutureBehavior_ behavior = null;
    private java.util.concurrent.Future<quorum.Libraries.Language.Object> javaFuture = null;
    public quorum.Libraries.Language.Object_ Get() throws ExecutionException, InterruptedException {
        return javaFuture.get();
    }
    public quorum.Libraries.Language.Object_ Get(double milliseconds)
            throws ExecutionException, InterruptedException, TimeoutException {
        return javaFuture.get((long)milliseconds, TimeUnit.MILLISECONDS);
    }

    public java.util.concurrent.Future getJavaFuture() {
        return javaFuture;
    }

    public void setJavaFuture(java.util.concurrent.Future javaFuture) {
        this.javaFuture = javaFuture;
    }

    public FutureBehavior_ getBehavior() {
        return behavior;
    }

    public void setBehavior(FutureBehavior_ behavior) {
        this.behavior = behavior;
    }
}
