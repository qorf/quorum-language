package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import plugins.quorum.Libraries.Interface.Accessibility.MacAccessibility;
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

            int size = MacAccessibility.GetMenuItemSetSize(control);
            int position = MacAccessibility.GetMenuItemSetPosition(control);
            String mnemonic = control.Get_Libraries_Interface_Controls_MenuItem__mnemonic_();
            String shortcut = control.GetShortcut();
            boolean expanded = control.IsOpen();

            // A menu item has no mnemonic unless the program sets one.
            if (mnemonic != null) {
                builder.setAccessKey(mnemonic);
            }
            if (shortcut != null) {
                builder.setKeyboardShortcut(shortcut);
            }
            //this may need to change as access kit changes. It feels like this
            //should be setValue, but that may be an incorrect assumption.
            builder.setLabel(control.GetName());
            builder.setDescription(control.GetDescription());
        } else {
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }
}
