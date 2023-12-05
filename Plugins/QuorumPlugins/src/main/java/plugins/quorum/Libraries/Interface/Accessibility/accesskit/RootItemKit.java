package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.NodeId;
import dev.accesskit.Role;

public class RootItemKit extends ItemKit {
    private static final NodeId ID = new NodeId(1);

    public RootItemKit() {
        SetRole(Role.WINDOW);
    }

    @Override
    public NodeId GetNodeID() {
        return ID;
    }
}
