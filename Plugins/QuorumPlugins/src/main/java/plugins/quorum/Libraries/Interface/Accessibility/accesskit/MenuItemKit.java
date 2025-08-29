package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import plugins.quorum.Libraries.Interface.AccessibilityManager;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.MenuItem_;
import quorum.Libraries.Interface.Item_;

public class MenuItemKit extends ItemKit{
    public MenuItemKit() {
        SetRole(Role.MENU_ITEM);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof MenuItem_) { //technically not compiler guaranteed. You can set the code to anything.
            MenuItem_ control = (MenuItem_) item;
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);

            int size = AccessibilityManager.GetMenuItemSetSize(control);
            int position = AccessibilityManager.GetMenuItemSetPosition(control);
            String mnemonic = control.Get_Libraries_Interface_Controls_MenuItem__mnemonic_();
            String shortcut = control.GetShortcut();
            boolean expanded = control.IsOpen();

            //this may need to change as access kit changes. It feels like this
            //should be setValue, but that may be an incorrect assumption.
            builder.setLabel(control.GetName());
        } else {
            builder.setLabel(item.GetName() + ", " + item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }
}
