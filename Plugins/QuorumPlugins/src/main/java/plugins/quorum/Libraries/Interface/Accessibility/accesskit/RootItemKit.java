package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.NodeId;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;

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
