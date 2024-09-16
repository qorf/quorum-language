package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.FutureBehavior_;

public class Future {
    public java.lang.Object me_ = null;
    private FutureBehavior_ behavior = null;
    private java.util.concurrent.Future javaFuture = null;
    public void Wait() throws InterruptedException {
        javaFuture.wait();
    }
    public void Wait(double milliseconds) throws InterruptedException {
        javaFuture.wait((long)milliseconds);
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
