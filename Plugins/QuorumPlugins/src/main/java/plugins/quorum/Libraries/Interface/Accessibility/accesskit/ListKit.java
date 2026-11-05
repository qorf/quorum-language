package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.MacAccessibilityExtras;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Controls.List_;
import quorum.Libraries.Interface.Item_;

public class ListKit extends ItemKit{
    // A list drawn as a tree (List:HasTreeAccessibility) is an outline.
    private final boolean tree;

    public ListKit() {
        this(false);
    }

    public ListKit(boolean tree) {
        this.tree = tree;
        SetRole(tree ? Role.TREE : Role.LIST);
    }

    /*
        A tree-like list is an outline, like a Tree (TreeKit): Mac.so works
        out its rows and row count. A plain list sends its row count, all
        its rows, from List:GetSize. Sent when the list is rebuilt
        (MacAccessibility.Select rebuilds it as the selection moves).
    */
    @Override
    public void SendExtras() {
        if (tree) {
            MacAccessibilityExtras.setInfo(GetNodeID(), MacAccessibilityExtras.OUTLINE, 0, -1, -1, 0, null);
            return;
        }
        int count = -1;
        Item_ item = GetItem();
        if (item instanceof List_) {
            count = ((List_) item).GetSize();
        }
        MacAccessibilityExtras.setInfo(GetNodeID(), MacAccessibilityExtras.LIST, 0, -1, count, 0, null);
    }
}
