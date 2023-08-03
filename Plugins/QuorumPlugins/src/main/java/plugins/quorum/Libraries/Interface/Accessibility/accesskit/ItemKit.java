package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
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

    /*
        Needs to be replaced for general items.
     */
    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            return builder.build();
        }
        return null;
    }

    public NodeId GetNodeID() {
        NodeId id = new NodeId(item.GetHashCode());
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

            if (itemX == Double.NaN)
                itemX = 0;

            if (itemY == Double.NaN)
                itemY = 0;

            rect = new Rect(
            windowX + itemX,
            windowY + (windowHeight - (itemY + ((Item2D_) item).GetHeight())),
                ((Item2D_) item).GetWidth(),
                ((Item2D_) item).GetHeight()
                            );
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.

            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();
            rect = new Rect(
                    windowX + rectangle.GetX(),
                    windowY + (windowHeight - (rectangle.GetY() + rectangle.GetHeight())),
                    rectangle.GetWidth(),
                    rectangle.GetHeight()
            );
        }
        else
        {
            rect = new Rect(0,0,0,0);
        }

        return rect;
    }
}
