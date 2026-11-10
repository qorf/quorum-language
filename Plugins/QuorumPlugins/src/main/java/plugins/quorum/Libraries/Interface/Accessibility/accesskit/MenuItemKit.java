package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.Collections;
import quorum.Libraries.Interface.Controls.MenuItem_;
import quorum.Libraries.Interface.Item_;

public class MenuItemKit extends ClickableKit {
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

            String mnemonic = control.GetMnemonic();
            String shortcut = control.GetShortcut();

            // A menu item has no mnemonic unless the program sets one.
            // AccessKit keeps the access key and shortcut, but its macOS
            // adapter doesn't pass them on; Mac.so gives VoiceOver the
            // shortcut itself (SendExtras).
            if (mnemonic != null) {
                builder.setAccessKey(mnemonic);
            }
            if (shortcut != null) {
                builder.setKeyboardShortcut(shortcut);
            }
            builder.setLabel(control.GetName());
            builder.setDescription(control.GetDescription());

            // Selected (the highlighted path): VoiceOver reads it, and it
            // makes the menu's selected children right.
            builder.setSelected(control.IsSelected());
        } else {
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    /*
        An open menu's items sit in a MENU node under it, as a Mac menu bar
        item holds an AXMenu: VoiceOver then knows it is in a menu and can
        count its items. The MENU node is this kit's own (an internal
        child), rebuilt whenever this kit is.
    */
    public NodeId GetMenuID() {
        return new NodeId(GetNodeID().value << 32);
    }

    private boolean HasOpenMenu() {
        Item_ item = GetItem();
        if (item instanceof MenuItem_) {
            MenuItem_ control = (MenuItem_) item;
            return control.IsMenu() && control.IsOpen();
        }
        return false;
    }

    @Override
    public Iterable<NodeId> GetInternalChildren() {
        if (HasOpenMenu()) {
            return Collections.singleton(GetMenuID());
        }
        return Collections.emptySet();
    }

    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        return GetInternalChildren();
    }

    @Override
    protected void BuildChildren(Node builder) {
        if (HasOpenMenu()) {
            builder.addChild(GetMenuID());
        } else {
            super.BuildChildren(builder);
        }
    }

    /*
        A menu bar's own items are AXMenuBarItems, as in a native menu bar.
        The shortcut goes the native way (AXMenuItemCmdChar and
        AXMenuItemCmdModifiers), so VoiceOver says "Command S". Quorum's
        menu items have no checked state yet, so none is MARKED.
    */
    @Override
    public void SendExtras() {
        int flags = 0;
        if (GetParent() instanceof MenuBarKit) {
            flags = MacAccessibilityExtras.MENU_BAR_ITEM;
        }
        String shortcut = null;
        Item_ item = GetItem();
        if (item instanceof MenuItem_) {
            shortcut = ((MenuItem_) item).GetShortcut();
        }
        MacAccessibilityExtras.setInfo(GetNodeID(), flags, 0, -1, -1, 0, shortcut);
    }

    @Override
    public Node BuildInternalChild(NodeId id) {
        if (!id.equals(GetMenuID())) {
            throw new IllegalArgumentException("unexpected child ID");
        }
        Node menu = new Node(Role.MENU);
        Item_ item = GetItem();
        if (item != null) {
            menu.setLabel(item.GetName());
        }
        for (ItemKit child : GetChildrenCopy()) {
            menu.addChild(child.GetNodeID());
        }
        return menu;
    }
}
