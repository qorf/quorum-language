package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import java.util.ArrayList;
import java.util.Collections;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.DesktopDisplay_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Language.Object_;

public class ItemKit {
    private Role role = null;
    private Item_ item = null;
    private ItemKit parent = null;
    private int indexInParent = -1;
    private ArrayList<ItemKit> children = new ArrayList<ItemKit>();

    public ItemKit() {
        SetRole(Role.UNKNOWN);
    }
    /*
        Needs to be replaced for general items.
     */
    public Node Build() {
        NodeBuilder builder = new NodeBuilder(GetRole());
        Item_ item = GetItem();
        if (item != null) {
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            builder.setName(item.GetName() + ", " + item.GetDescription());
        }
        BuildChildren(builder);
        return builder.build();
    }

    protected void BuildChildren(NodeBuilder builder) {
        for (NodeId child : GetInternalChildren()) {
            builder.addChild(child);
        }
        for (ItemKit child : children) {
            builder.addChild(child.GetNodeID());
        }
    }

    public NodeId GetNodeID() {
        return GetNodeID(item);
    }

    public static NodeId GetNodeID(Item_ item) {
        return new NodeId(item.GetHashCode());
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

    public final Iterable<ItemKit> GetChildren() {
        return children;
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
        // The order of values in the bounding box is left, top, width, height.
        Rect rect;
        double[] bounds = new double[4];

        DesktopDisplay_ display = (DesktopDisplay_) GameStateManager.display;

        if (display == null)
        {
            rect = new Rect(0,0,0,0);
            return rect;
        }

        double windowX = display.GetDisplayX();
        double windowY = display.GetDisplayY();

        /*
        We need to know the height to correct for the difference in y
        direction between Windows and Quorum (0 is at the top in Windows, and
        and the bottom in Quorum).
        */
        double windowHeight = display.GetHeight();

        if (item instanceof Item2D_)
        {
            double itemX = ((Item2D_)item).GetScreenX();
            double itemY = (((Item2D_) item).GetScreenY());

            if (itemX == Double.NaN) {
                itemX = 0;
            }

            if (itemY == Double.NaN) {
                itemY = 0;
            }

            //bounding boxes require a slightly different approach than UIA.
            rect = new Rect(
                    itemX,
                    windowHeight - itemY - ((Item2D_) item).GetHeight(),
                    ((Item2D_) item).GetWidth() + itemX,
                    windowHeight - itemY
            );
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.

            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();
//            rect = new Rect(
//                    windowX + rectangle.GetX(),
//                    windowY + (windowHeight - (rectangle.GetY() + rectangle.GetHeight())),
//                    rectangle.GetWidth(),
//                    rectangle.GetHeight()
//            );

            rect = new Rect(
                    rectangle.GetX(),
                    windowHeight - (rectangle.GetY() - rectangle.GetHeight()),
                    rectangle.GetWidth() + rectangle.GetX(),
                    windowHeight - rectangle.GetY()
                    );
        }
        else
        {
            rect = new Rect(0,0,0,0);
        }

        return rect;
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
