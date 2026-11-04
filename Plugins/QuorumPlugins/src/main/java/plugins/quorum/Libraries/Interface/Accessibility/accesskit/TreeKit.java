package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.MacAccessibilityExtras;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;

public class TreeKit extends ItemKit{
    public TreeKit() {
        SetRole(Role.TREE);
    }

    // An outline whose rows are all its shown items, and whose row count
    // is theirs (both worked out in Mac.so; MacAccessibilityExtras).
    @Override
    public void SendExtras() {
        MacAccessibilityExtras.setInfo(GetNodeID(), MacAccessibilityExtras.OUTLINE, 0, -1, -1, 0, null);
    }
}
