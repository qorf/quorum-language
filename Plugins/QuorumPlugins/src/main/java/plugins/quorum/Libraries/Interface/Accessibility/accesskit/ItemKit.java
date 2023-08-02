package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Role;
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

    public double[] GetBoundingRectangle()
    {
        // The order of values in the bounding box is left, top, width, height.
        double[] bounds = new double[4];

        DesktopDisplay_ display = (DesktopDisplay_) GameStateManager.display;

        if (display == null)
        {
            for (int i = 0; i < bounds.length; i++)
                bounds[i] = 0;

            return bounds;
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

            bounds[0] = windowX + itemX;
            bounds[1] = windowY + (windowHeight - (itemY + ((Item2D_) item).GetHeight()));
            bounds[2] = ((Item2D_) item).GetWidth();
            bounds[3] = ((Item2D_) item).GetHeight();
        }
        else if (item instanceof Item3D_)
        {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.

            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();

            bounds[0] = windowX + rectangle.GetX();
            bounds[1] = windowY + (windowHeight - (rectangle.GetY() + rectangle.GetHeight()));
            bounds[2] = rectangle.GetWidth();
            bounds[3] = rectangle.GetHeight();
        }
        else
        {
            // If we don't know what it is, we set the values to 0 to provide a safe default.
            for (int i = 0; i < bounds.length; i++)
                bounds[i] = 0;
        }

        return bounds;
    }
}
