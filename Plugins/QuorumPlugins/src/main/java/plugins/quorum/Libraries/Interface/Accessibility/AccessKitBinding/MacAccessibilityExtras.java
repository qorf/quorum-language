package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    What VoiceOver needs but AccessKit's macOS adapter (accesskit_macos
    0.27.2) leaves out. Mac.so adds it to AccessKit's own elements at run
    time (quorum-natives/src/mac/AccessKitRuntime.m), from the facts sent
    here, kept by node id. It finds each element by its identifier, which
    AccessKit takes from the node's author id, so every node's author id
    must be its node id in decimal (MacAccessibility.Identify).

    Main thread only, like the rest of the binding. Facts are sent while an
    update is built; notices are posted after the next update reaches
    AccessKit, and dropped if VoiceOver isn't active.
*/
public final class MacAccessibilityExtras {
    /* Flags for setInfo. The same numbers as AccessKitRuntime.m. */

    // A menu bar's own item (File, Edit): role AXMenuBarItem, not AXMenuItem.
    public static final int MENU_BAR_ITEM = 1;
    // A tree: its rows are all its shown items, nested ones included.
    public static final int OUTLINE = 2;
    // A tree item: a row with an index, a level and an expanded state.
    public static final int OUTLINE_ROW = 4;
    // A list item: its index is its place in the list.
    public static final int LIST_ROW = 8;
    // It opens and closes.
    public static final int EXPANDABLE = 16;
    // It is open.
    public static final int EXPANDED = 32;
    // A menu item with a check mark.
    public static final int MARKED = 64;
    // A list: its count is its number of rows, shown or not.
    public static final int LIST = 128;
    // A heading: role AXHeading, listed in the window's Headings rotor.
    public static final int HEADING = 256;
    // It has a context menu (Control:GetPopupMenu): VoiceOver may show it (VO-Shift-M).
    public static final int HAS_MENU = 512;
    // VoiceOver may open or close it (VO-\, setting AXDisclosing): an
    // EXPANDABLE row whose kit can carry out EXPAND and COLLAPSE.
    public static final int CAN_DISCLOSE = 1024;
    // An outline whose rows VoiceOver must not count ("1 of 2"): a 3D
    // item's cursor (Item3DKit). Mac.so gives it no AXRows.
    public static final int UNCOUNTED = 2048;

    /* Notices for queueNotice. The same numbers as AccessKitRuntime.m. */

    // AXMenuOpened, on a menu (MenuItemKit's MENU node).
    public static final int MENU_OPENED = 1;
    // AXMenuClosed, on a menu that MENU_OPENED announced.
    public static final int MENU_CLOSED = 2;
    // AXRowExpanded, on a tree item.
    public static final int ROW_EXPANDED = 3;
    // AXRowCollapsed, on a tree item.
    public static final int ROW_COLLAPSED = 4;
    // AXRowCountChanged, on a tree.
    public static final int ROW_COUNT_CHANGED = 5;
    // AXMoved and AXLayoutChanged, on an element whose box moved (a 3D
    // item's, as its camera or cursor moves). AccessKit sends nothing when
    // only bounds change, so VoiceOver would keep the old box.
    public static final int MOVED = 6;

    private MacAccessibilityExtras() {
    }

    /*
        The facts for one node, replacing any sent before. level: 0 for a
        tree's top items, 1 for the items under them, and so on. index: the
        node's place in its list, from 0, or -1. count: a list's number of
        rows, or a row's number of rows at its depth under the same parent,
        or -1 (an outline's row count is counted in Mac.so). position: a
        row's place among those rows, from 1, or 0. shortcut: a menu item's
        shortcut as MenuItem:GetShortcut gives it ("Command + Shift + S"),
        or null.
    */
    public static void setInfo(NodeId node, int flags, int level, int index, int count, int position, String shortcut) {
        nativeSetInfo(node.value, flags, level, index, count, position, Util.bytesFromString(shortcut));
    }

    /* Forgets a node that left the tree. */
    public static void remove(NodeId node) {
        nativeRemove(node.value);
    }

    /*
        Asks for a notice on a node after the next update. near is a node
        whose children include it, which finds it faster, or null.
    */
    public static void queueNotice(NodeId node, NodeId near, int notice) {
        nativeQueueNotice(node.value, near == null ? 0 : near.value, notice);
    }

    private static native void nativeSetInfo(long node, int flags, int level, int index, int count, int position, byte[] shortcut);

    private static native void nativeRemove(long node);

    private static native void nativeQueueNotice(long node, long near, int notice);
}
