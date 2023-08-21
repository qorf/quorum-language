package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.NodeBuilder;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.DesktopDisplay_;
import quorum.Libraries.Game.Graphics.Font_;
import quorum.Libraries.Game.Graphics.Label_;
import quorum.Libraries.Game.Layer3D_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;

public class LabelKit extends ItemKit{
    public LabelKit() {
        SetRole(Role.LABEL_TEXT);
    }

    public Node Build() {
        NodeBuilder builder = new NodeBuilder(GetRole());
        Item_ item = GetItem();

        if (item != null && item instanceof Label_) {
            Label_ label = (Label_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            builder.setName(label.GetText());
        } else {
            builder.setName(item.GetName() + ", " + item.GetDescription());
        }
        BuildChildren(builder);
        return builder.build();
    }

    public Rect GetBoundingRectangle()
    {
        Item_ check = GetItem();
        Label_ item = null;
        if(check != null && check instanceof Label_) {
            item = (Label_) check;
        } else {
            return super.GetBoundingRectangle();
        }
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

            Font_ font = item.GetFont();
            int descent = font.GetMaximumDescent();
            int ascent = font.GetMaximumDescent();
            //bounding boxes require a slightly different approach than UIA.
            double x1 = itemX;
            double x2 = ((Item2D_) item).GetWidth() + itemX;
            double y1 = windowHeight - itemY - ((Item2D_) item).GetHeight();
            if(item.IsPositioningOnBaseLine()) {
                y1 = y1 - (descent * 2.0);
            }
            double y2 = windowHeight - itemY;
            rect = new Rect( x1, y1, x2, y2);
        }
        else
        {
            return super.GetBoundingRectangle();
        }

        return rect;
    }
}
