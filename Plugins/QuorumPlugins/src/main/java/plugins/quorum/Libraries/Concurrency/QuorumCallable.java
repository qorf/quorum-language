package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Interface.Behaviors.Behavior_;

import java.util.concurrent.Callable;

public class QuorumCallable implements Callable {
    private Behavior_ behavior = null;
    @Override
    public Object call() throws Exception {
        behavior.Run(null);
        return null;
    }

    public Behavior_ getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior_ behavior) {
        this.behavior = behavior;
    }
}
