package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.ArrayList;
import java.util.Collections;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.DesktopDisplay_;
import quorum.Libraries.Game.Layer3D_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;

public class ItemKit {
    /*
        Node ids 1 to 15 are kept for MacAccessibility's own nodes (the root
        is 1, the announcers 2 and 3). Every kit gets the next number, so no
        two kits share an id, which item hash codes couldn't promise. Text
        runs use (kit id << 32) | line, which never meets a kit id.
    */
    private static final long FIRST_ITEM_NODE_ID = 16;
    private static long nextNodeId = FIRST_ITEM_NODE_ID;

    private final NodeId id = NewNodeID();
    private Role role = null;
    private Item_ item = null;
    private ItemKit parent = null;
    private int indexInParent = -1;
    private ArrayList<ItemKit> children = new ArrayList<ItemKit>();

    public ItemKit() {
        SetRole(Role.UNKNOWN);
    }

    private static synchronized NodeId NewNodeID() {
        NodeId result = new NodeId(nextNodeId);
        nextNodeId = nextNodeId + 1;
        return result;
    }

    /*
        Needs to be replaced for general items.
     */
    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if (item != null) {
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    /*
        Sends what VoiceOver needs but AccessKit's macOS adapter leaves out
        (MacAccessibilityExtras), each time this kit's node is built. Most
        kits have nothing to send.
    */
    public void SendExtras() {
    }

    // A node with only this kit's role and children (MacAccessibility.SafeBuild).
    public final Node BuildFallbackNode() {
        Node node = new Node(GetRole());
        BuildChildren(node);
        return node;
    }

    protected void BuildChildren(Node builder) {
        for (NodeId child : GetInternalChildren()) {
            builder.addChild(child);
        }
        for (ItemKit child : children) {
            builder.addChild(child.GetNodeID());
        }
    }

    public NodeId GetNodeID() {
        return id;
    }

    public Item_ GetItem() {
        return item;
    }

    public void SetItem(Item_ item) {
        this.item = item;
    }

    public Role GetRole() {
        return role;
    }

    public void SetRole(Role role) {
        this.role = role;
    }

    public final ItemKit GetParent() {
        return parent;
    }

    public final int GetIndexInParent() {
        return indexInParent;
    }

    /*
        A copy of the children, so building nodes can't be disturbed if
        something adds or removes a kit meanwhile (everything runs on the
        main thread, so a copy is all that is needed).
    */
    public final ArrayList<ItemKit> GetChildrenCopy() {
        return new ArrayList<ItemKit>(children);
    }

    public final void AddChild(ItemKit child) {
        int index = children.size();
        children.add(child);
        child.parent = this;
        child.indexInParent = index;
    }

    public void RemoveFromParent() {
        if (parent.children.get(indexInParent) != this) {
            throw new RuntimeException("corrupt parent/child structure");
        }
        parent.children.remove(indexInParent);
        for (int i = indexInParent; i < parent.children.size(); i++) {
            parent.children.get(i).indexInParent = i;
        }
        parent = null;
    }

    public Rect GetBoundingRectangle()
    {
        DesktopDisplay_ display = (DesktopDisplay_) GameStateManager.display;

        if (display == null)
        {
            return new Rect(0,0,0,0);
        }

        /*
        We need to know the height to correct for the difference in y
        direction between the Mac (0 is at the top in AccessKit) and Quorum
        (0 is at the bottom).
        */
        double windowHeight = display.GetHeight();

        if (item instanceof Item2D_)
        {
            Item2D_ item2D = (Item2D_) item;
            double itemX = item2D.GetScreenX();
            double itemY = item2D.GetScreenY();

            if (Double.isNaN(itemX)) {
                itemX = 0;
            }

            if (Double.isNaN(itemY)) {
                itemY = 0;
            }

            return new Rect(
                    itemX,
                    windowHeight - itemY - item2D.GetHeight(),
                    item2D.GetWidth() + itemX,
                    windowHeight - itemY
            );
        }
        else if (item instanceof Item3D_)
        {
            // A placeholder (step 8 of the plan fixes 3D bounds).
            Item3D_ item3D = ((Item3D_) item);
            Rectangle_ rectangle = item3D.GetScreenBounds();

            Layer3D_ layer3D = item3D.GetLayer();
            int viewX = layer3D.GetViewportX();
            int viewY = layer3D.GetViewportY();

            double x1 = viewX + rectangle.GetX();
            double x2 = viewX + rectangle.GetWidth() + rectangle.GetX();
            double y1 = windowHeight - rectangle.GetY() - viewY - rectangle.GetHeight();
            double y2 = windowHeight - rectangle.GetY() - viewY;
            return new Rect( x1, y1, x2, y2);
        }
        return new Rect(0,0,0,0);
    }

    public Iterable<NodeId> GetInternalChildren() {
        return Collections.emptySet();
    }

    public Iterable<NodeId> GetDirtyInternalChildren() {
        return Collections.emptySet();
    }

    public void ClearDirtyInternalChildren() {}

    public Node BuildInternalChild(NodeId id) {
        throw new IllegalStateException("not implemented in this class");
    }
}
