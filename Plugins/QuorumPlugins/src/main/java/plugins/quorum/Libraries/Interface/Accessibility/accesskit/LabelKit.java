package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.MacAccessibilityExtras;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.DesktopDisplay_;
import quorum.Libraries.Game.Graphics.Font_;
import quorum.Libraries.Game.Graphics.Label_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item_;

public class LabelKit extends ItemKit{
    public LabelKit() {
        SetRole(Role.LABEL);
    }

    // The label's heading level (Label:SetHeadingLevel), or 0.
    private int GetHeadingLevel() {
        //This may need to go back in if we can figure out
        //how to make the rotor respect it on mac.
//        Item_ item = GetItem();
//        if (item instanceof Label_) {
//            return ((Label_) item).GetHeadingLevel();
//        }
        return 0;
    }

    public Node Build() {
        Item_ item = GetItem();
        int headingLevel = GetHeadingLevel();
        Node builder = new Node(headingLevel > 0 ? Role.HEADING : GetRole());

        if (item != null && item instanceof Label_) {
            Label_ label = (Label_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            if (headingLevel > 0) {
                // A heading's text is its title, and its value is its level,
                // as in WebKit (AccessKit gives a number value as an NSNumber).
                builder.setLabel(label.GetText());

                //This is experimental and currently
                //does not work with the rotor
                builder.setNumericValue(headingLevel);
                builder.setLevel(headingLevel);
            } else {
                builder.setValue(label.GetText());
            }
            builder.setDescription(label.GetDescription());
        } else {
            builder.setValue(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    // A heading is listed in the window's Headings rotor (MacAccessibilityExtras).
    @Override
    public void SendExtras() {
        int headingLevel = GetHeadingLevel();
        if (headingLevel > 0) {
            MacAccessibilityExtras.setInfo(GetNodeID(), MacAccessibilityExtras.HEADING, headingLevel, -1, -1, 0, null);
        } else {
            MacAccessibilityExtras.remove(GetNodeID());
        }
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
        Rect rect;
        DesktopDisplay_ display = (DesktopDisplay_) GameStateManager.display;

        if (display == null)
        {
            rect = new Rect(0,0,0,0);
            return rect;
        }

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

            if (Double.isNaN(itemX)) {
                itemX = 0;
            }

            if (Double.isNaN(itemY)) {
                itemY = 0;
            }

            Font_ font = item.GetFont();
            int descent = font.GetMaximumDescent();
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
