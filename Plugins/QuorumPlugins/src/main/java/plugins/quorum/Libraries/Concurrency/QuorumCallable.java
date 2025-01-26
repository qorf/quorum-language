package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.FutureBehavior_;

import java.util.concurrent.Callable;

public class QuorumCallable implements Callable {
    private FutureBehavior_ behavior = null;
    @Override
    public quorum.Libraries.Language.Object_ call() throws Exception {
        behavior.Run(null);
        behavior.Then();
        return behavior.GetResult();
    }

    public FutureBehavior_ getFutureBehavior() {
        return behavior;
    }

    public void setFutureBehavior(FutureBehavior_ behavior) {
        this.behavior = behavior;
    }
}
