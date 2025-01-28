package plugins.quorum.Libraries.Concurrency;

import quorum.Libraries.Concurrency.Task_;

import java.util.concurrent.Callable;

public class QuorumCallable implements Callable {
    private Task_ behavior = null;
    @Override
    public quorum.Libraries.Language.Object_ call() throws Exception {
        behavior.Run();
        behavior.Then();
        behavior.SetFinished(true);
        return behavior.GetResult();
    }

    public Task_ getFutureBehavior() {
        return behavior;
    }

    public void setFutureBehavior(Task_ behavior) {
        this.behavior = behavior;
    }
}
