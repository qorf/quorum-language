package plugins.quorum.Libraries.Interface.Accessibility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWNativeCocoa;
import plugins.quorum.Libraries.Game.DesktopDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Tree;
import plugins.quorum.Libraries.Interface.Accessibility.accesskit.*;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Interface.Controls.*;
import quorum.Libraries.Interface.Events.ControlActivationEvent_;
import quorum.Libraries.Interface.Events.FocusEvent_;
import quorum.Libraries.Interface.Events.MenuChangeEvent_;
import quorum.Libraries.Interface.Events.ProgressBarValueChangedEvent_;
import quorum.Libraries.Interface.Events.SelectionEvent_;
import quorum.Libraries.Interface.Events.TextChangeEvent_;
import quorum.Libraries.Interface.Events.TreeChangeEvent_;
import quorum.Libraries.Interface.Events.TreeTableChangeEvent_;
import quorum.Libraries.Interface.Events.WindowFocusEvent_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Selections.ListSelection_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TreeSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Language.Object_;

/**
 *
 * @author andreasstefik
 */
public class MacAccessibility {
    public java.lang.Object me_ = null;
    private MacosSubclassingAdapter adapter;
    private final RootItemKit root = new RootItemKit();
    private final HashMap<NodeId, ItemKit> items = new HashMap<NodeId, ItemKit>();

    // Each item's kit, found by the item itself (not its hash code).
    private final IdentityHashMap<Item_, ItemKit> kitsByItem = new IdentityHashMap<Item_, ItemKit>();

    // Two live regions for Notify, used in turn (see AnnouncerKit).
    private final AnnouncerKit[] announcers = { new AnnouncerKit(2), new AnnouncerKit(3) };
    private int currentAnnouncer = 0;

    private NodeId focus = root.GetNodeID();
    private final HashSet<NodeId> dirtyNodes = new HashSet<NodeId>();

    // The node AccessKit's focus was last set to: focus's kit's
    // GetFocusTarget, which is focus itself unless a kit moves it inside.
    private NodeId sentFocus = null;
    private boolean isFocusDirty = false;

    // Kits whose children all need rebuilding (a tab's siblings, when it is selected).
    private final HashSet<NodeId> refreshChildren = new HashSet<NodeId>();

    // The item Select last reported, so it is rebuilt when the selection moves on.
    private Item_ lastSelected = null;

    // True until a whole tree has reached AccessKit. If building the first
    // tree fails, AccessKit keeps an empty placeholder and won't ask again,
    // and it ends the program unless the next update carries the whole tree.
    private boolean needsFullTree = true;

    /*
        VoiceOver's requests, queued by the ActionHandler while AppKit
        answers VoiceOver (AccessKit may have its tree borrowed then), and
        carried out at the start of NativeUpdate, where changing Quorum's
        items, and so the tree, is safe.
    */
    private final ConcurrentLinkedQueue<ActionRequest> pendingActions = new ConcurrentLinkedQueue<ActionRequest>();

    // Whether the window has the focus, as last told to AccessKit.
    private boolean windowFocused = false;

    // The kits of items in 3D scenes, which NativeUpdate checks a few times
    // a second, the focused one every frame (Item3DKit.HasChanged), and
    // when it last checked them all.
    private final ArrayList<Item3DKit> kits3D = new ArrayList<Item3DKit>();
    private long lastCheck3D = 0;
    private static final long CHECK_3D_MILLISECONDS = 100;
    private boolean warned3D = false;
    private boolean warnedDetached = false;

    public MacAccessibility() {
        items.put(root.GetNodeID(), root);
        for (AnnouncerKit announcer : announcers) {
            items.put(announcer.GetNodeID(), announcer);
            root.AddChild(announcer);
        }

        // AccessKit's natives are in Mac.so, which Game.java loads before
        // DesktopApplication makes this, so there is nothing to load here.
        long handle = GLFWNativeCocoa.glfwGetCocoaWindow(DesktopDisplay.window);
        adapter = MacosSubclassingAdapter.forWindow(handle, new ActivationHandler() {
            @Override
            public TreeUpdate requestInitialTree() {
                TreeUpdate update = BuildFullTree();
                needsFullTree = false;
                return update;
            }
        }, new ActionHandler() {
            @Override
            public void requestAction(ActionRequest request) {
                // Only queued: see pendingActions.
                pendingActions.add(request);
            }
        });
        // AccessKit assumes the window isn't focused until told;
        // WindowFocusChanged and CheckWindowFocus keep it up to date.
        SetWindowFocus(IsWindowFocused());
    }

    private static boolean IsWindowFocused() {
        return GLFW.glfwGetWindowAttrib(DesktopDisplay.window, GLFW.GLFW_FOCUSED) == GLFW.GLFW_TRUE;
    }

    private void SetWindowFocus(boolean focused) {
        windowFocused = focused;
        adapter.updateViewFocusState(focused);
    }

    /*
        GLFW's focus event reaches WindowFocusChanged through GameInput and
        the game's Accessibility. If one is ever missed (for example while
        the game has no Accessibility yet), AccessKit would keep thinking
        the window has no focus, and no element would be keyboard focused.
        GLFW's own flag is cheap to read, so each update checks it.
    */
    private void CheckWindowFocus() {
        boolean focused = IsWindowFocused();
        if (focused != windowFocused) {
            SetWindowFocus(focused);
        }
    }

    private ItemKit GetKit(Item_ item) {
        if (item == null) {
            return null;
        }
        return kitsByItem.get(item);
    }

    /*
        A kit's node, or, if building it fails, a plain node with the kit's
        role and children. One broken node then can't stop every update
        (an update that throws reaches AccessKit as nothing at all), and
        the children stay listed, which AccessKit requires.
    */
    private static Node SafeBuild(ItemKit kit) {
        try {
            Node node = kit.BuildNode();
            if (node != null) {
                return node;
            }
        } catch (RuntimeException error) {
            Logger.getLogger(MacAccessibility.class.getName()).log(Level.WARNING,
                "Building an accessibility node failed: " + kit.getClass().getSimpleName(), error);
        }
        return kit.BuildFallbackNode();
    }

    /*
        A kit's node, as SafeBuild makes it, with its node id as its author
        id, and the kit's extras sent (MacAccessibilityExtras). A failure in
        the extras costs only the extras.
    */
    private static Node BuildNode(ItemKit kit) {
        Node node = Identify(kit.GetNodeID(), SafeBuild(kit));
        try {
            kit.SendExtras();
        } catch (RuntimeException error) {
            Logger.getLogger(MacAccessibility.class.getName()).log(Level.WARNING,
                "Sending VoiceOver extras failed: " + kit.getClass().getSimpleName(), error);
        }
        return node;
    }

    /*
        Gives a node its id, in decimal, as its author id. AccessKit hands
        it to VoiceOver as the element's identifier, which is how Mac.so
        finds the element (MacAccessibilityExtras).
    */
    private static Node Identify(NodeId id, Node node) {
        node.setAuthorId(Long.toUnsignedString(id.value));
        return node;
    }

    private static void Traverse(ItemKit itemKit, final TreeUpdate update) {
        update.add(itemKit.GetNodeID(), BuildNode(itemKit));
        for (ItemKit child : itemKit.GetChildrenCopy()) {
            Traverse(child, update);
        }
        for (NodeId child : itemKit.GetInternalChildren()) {
            update.add(child, Identify(child, itemKit.BuildInternalChild(child)));
        }
    }

    private TreeUpdate BuildFullTree() {
        NodeId target = items.containsKey(focus) ? FocusTarget() : root.GetNodeID();
        TreeUpdate update = TreeUpdate.withFocus(target);
        sentFocus = target;
        Traverse(root, update);
        update.setTree(new Tree(root.GetNodeID()));
        return update;
    }

    private void SetItemToDirty(Item_ item) {
        ItemKit kit = GetKit(item);
        if(kit != null) {
            dirtyNodes.add(kit.GetNodeID());
        }
    }

    // Rebuilds every child of this item's kit, on the next update.
    private void SetChildrenToDirty(ItemKit kit) {
        if (kit != null) {
            refreshChildren.add(kit.GetNodeID());
        }
    }

    public void  NameChanged(Item_ item) {
        SetItemToDirty(item);
    }

    public void  DescriptionChanged(Item_ item) {
        SetItemToDirty(item);
    }

    public void  BoundsChanged(Item_ item) {
        SetItemToDirty(item);
    }

    public void  TextFieldUpdatePassword(TextField_ item) {
        SetItemToDirty(item);
    }

    /*
        Carries out VoiceOver's queued requests. A request for a node that
        is gone is dropped. Quorum's usual events (focus, selection, text,
        toggles, trees opening) then mark what changed; the target is
        rebuilt too, for what no event reports (a list row opened by its
        list's behavior).
    */
    private void RunPendingActions() {
        ActionRequest request = pendingActions.poll();
        while (request != null) {
            ItemKit kit = items.get(request.target);
            if (kit != null && request.action != null) {
                try {
                    kit.DoAction(request);
                    if (items.containsKey(request.target)) {
                        dirtyNodes.add(request.target);
                    }
                } catch (RuntimeException error) {
                    Logger.getLogger(MacAccessibility.class.getName()).log(Level.WARNING,
                        "A VoiceOver action failed: " + request.action, error);
                }
            }
            request = pendingActions.poll();
        }
    }

    /*
        Checks the 3D items' bounds, names, descriptions and cursors, once
        AccessKit has a tree, and rebuilds the ones that changed
        (Item3DKit): the focused item every frame, the others at most 10
        times a second. This runs after the game's update (Game:ContinueGame
        calls Accessibility:Update after UpdateAll, Update and the cameras),
        so the bounds use this frame's cursor and camera, the ones about to
        be drawn.
    */
    private void Check3DItems() {
        if (needsFullTree || kits3D.isEmpty()) {
            return;
        }
        ItemKit focusedKit = items.get(focus);
        Item3DKit focused3D = focusedKit instanceof Item3DKit ? (Item3DKit) focusedKit : null;
        long time = System.currentTimeMillis();
        if (time - lastCheck3D < CHECK_3D_MILLISECONDS) {
            if (focused3D != null) {
                Check3DItem(focused3D, true);
            }
            return;
        }
        lastCheck3D = time;
        for (Item3DKit kit : kits3D) {
            Check3DItem(kit, kit == focused3D);
        }
    }

    private void Check3DItem(Item3DKit kit, boolean focused) {
        try {
            if (kit.HasChanged(focused)) {
                dirtyNodes.add(kit.GetNodeID());
                // AccessKit sends nothing when only a box moves, so tell
                // VoiceOver, for the focused item and its cursor's row. Both
                // are needed: with only the row, VoiceOver neither moves its
                // box nor reads the move (plan 20's tests).
                if (focused && kit.TakeBoundsMoved()) {
                    MacAccessibilityExtras.queueNotice(kit.GetNodeID(), null, MacAccessibilityExtras.MOVED);
                    NodeId row = kit.GetCursorRowID();
                    if (row != null) {
                        MacAccessibilityExtras.queueNotice(row, kit.GetNodeID(), MacAccessibilityExtras.MOVED);
                    }
                }
            }
        } catch (RuntimeException error) {
            if (!warned3D) {
                warned3D = true;
                Logger.getLogger(MacAccessibility.class.getName()).log(Level.WARNING,
                    "Checking a 3D item's bounds failed; further failures are not logged.", error);
            }
        }
    }

    // Where AccessKit's focus goes: the focused kit's GetFocusTarget.
    private NodeId FocusTarget() {
        ItemKit kit = items.get(focus);
        return kit == null ? focus : kit.GetFocusTarget();
    }

    /*
        True if this kit hangs from the root. A kit whose parent (or one of
        its parents) was removed is gone from AccessKit's tree too, and an
        update that names it stops the app (accesskit_consumer's "neither
        in the current tree nor a child of another node").
    */
    private boolean IsAttached(ItemKit kit) {
        ItemKit current = kit;
        while (current != null) {
            if (current == root) {
                return true;
            }
            current = current.GetParent();
        }
        return false;
    }

    // Says once which kit was left behind, so its cause can be found.
    private void WarnDetached(ItemKit kit) {
        if (warnedDetached) {
            return;
        }
        warnedDetached = true;
        Item_ item = kit.GetItem();
        String what = item == null ? kit.getClass().getSimpleName()
                : kit.getClass().getSimpleName() + " for " + item.getClass().getName() + " \"" + item.GetName() + "\"";
        Logger.getLogger(MacAccessibility.class.getName()).log(Level.WARNING,
            "Skipped a node left behind after its parent was removed: " + what
            + ". Further ones are not logged.");
    }

    public void  NativeUpdate() {
        RunPendingActions();
        CheckWindowFocus();
        RestoreFocus();
        Check3DItems();
        for (NodeId id : refreshChildren) {
            ItemKit parent = items.get(id);
            if (parent != null) {
                for (ItemKit child : parent.GetChildrenCopy()) {
                    dirtyNodes.add(child.GetNodeID());
                }
            }
        }
        refreshChildren.clear();
        final NodeId target = FocusTarget();
        if (!target.equals(sentFocus)) {
            isFocusDirty = true;
        }
        if (needsFullTree || !dirtyNodes.isEmpty() || isFocusDirty) {
            if (!items.containsKey(focus)) {
                // Try again on the next frame.
                return;
            }
            adapter.updateIfActive(new TreeUpdateSupplier() {
                @Override
                public TreeUpdate get() {
                    if (needsFullTree) {
                        TreeUpdate update = BuildFullTree();
                        needsFullTree = false;
                        return update;
                    }
                    try {
                        TreeUpdate update = TreeUpdate.withFocus(target);
                        sentFocus = target;
                        for (NodeId id : dirtyNodes) {
                            ItemKit kit = items.get(id);
                            if (!IsAttached(kit)) {
                                WarnDetached(kit);
                                continue;
                            }
                            update.add(id, BuildNode(kit));
                            for (NodeId child : kit.GetDirtyInternalChildren()) {
                                update.add(child, Identify(child, kit.BuildInternalChild(child)));
                            }
                        }
                        return update;
                    } catch (RuntimeException error) {
                        // Nothing of this update reaches AccessKit, but the
                        // dirty marks are cleared below: send everything next time.
                        needsFullTree = true;
                        throw error;
                    }
                }
            });
            for (NodeId id : dirtyNodes) {
                ItemKit kit = items.get(id);
                kit.ClearDirtyInternalChildren();
            }
            dirtyNodes.clear();
            isFocusDirty = false;
        }
    }

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ item) {
        SetItemToDirty(item.GetProgressBar());
    }

    public void  SelectionChanged(SelectionEvent_ event) {
    }

    public void TextSelectionChanged(TextBoxSelection_ selection)
    {
        SetItemToDirty(selection.GetTextBox());
    }

    public void TextSelectionChanged(TextFieldSelection_ selection)
    {
        SetItemToDirty(selection.GetTextField());
    }

    public boolean Select(Item_ item)
    {
        /*
        Not sure what we do in access kit here, but this is when the selection has changed, for example to a tree item.
        I don't think this is directly a focus call, it's a selection call, so this may be different at the access kit level.

        This particular accessibility manager always fires this, regardless of what kind of object it is.
        It may not be enough, but I think even updating it "could" be enough, depending on how selection is implemented.
         */

        // A selection moves VoiceOver's focus only inside the control that
        // has the keyboard focus (or in an open menu). Lists and trees
        // elsewhere also select rows, such as the Team tab restoring its
        // selection when Studio gets the focus back (TeamTab.quorum:1213),
        // and that used to take AccessKit's focus off the focused row.
        if (IsInKeyboardFocus(item)) {
            ChangeFocusNextFrame(item);
        }

        // The kits send each item's selected state; rebuild the item that
        // was selected before and the one selected now.
        if (lastSelected != null && lastSelected != item) {
            SetItemToDirty(lastSelected);
        }
        SetItemToDirty(item);
        lastSelected = item;

        // A list's row count can change without any row it shows being
        // added (rows out of view get no node), so it is sent again as the
        // selection moves (ListKit.SendExtras).
        if (item instanceof ListItem_) {
            SetItemToDirty(((ListItem_) item).GetList());
        }

        return false;
    }

    // The item that has Quorum's keyboard focus, or null.
    private static Item_ GetKeyboardFocus() {
        return GameStateManager.game == null ? null : GameStateManager.game.GetFocus();
    }

    /*
        True if a selected item is where the keyboard is: the focused item
        itself, a row of the focused tree or list, an item inside the
        focused item, or a menu item (an open menu has the keyboard). Also
        true when nothing has the focus.
    */
    private static boolean IsInKeyboardFocus(Item_ item) {
        if (item == null) {
            return false;
        }
        if (item instanceof MenuItem_) {
            return true;
        }
        Item_ focused = GetKeyboardFocus();
        if (focused == null || focused == item) {
            return true;
        }
        if (item instanceof ListItem_ && ((ListItem_) item).GetList() == focused) {
            return true;
        }
        if (item instanceof TreeItem_ && ((TreeItem_) item).GetTree() == focused) {
            return true;
        }
        Item_ above = item.GetAccessibleParent();
        for (int depth = 0; above != null && depth < 64; depth++) {
            if (above == focused) {
                return true;
            }
            above = above.GetAccessibleParent();
        }
        return false;
    }

    /*
        When the focused node leaves the tree, the focus falls back to the
        root (NativeRemove), and nothing else would move it back: Quorum's
        focus didn't change, so no FocusChanged comes. That happens when
        Studio rebuilds the Projects tree after files change on disk, with
        the tree focused. So while the focus is on the root, it goes back to
        Quorum's focus (the selected row of a focused tree or list) as soon
        as that has a node.
    */
    private void RestoreFocus() {
        if (!focus.equals(root.GetNodeID())) {
            return;
        }
        Item_ focused = GetKeyboardFocus();
        if (focused == null) {
            return;
        }
        Item_ selected = GetSelectedItem(focused);
        ChangeFocusNextFrame(selected != null ? selected : focused);
    }

    private void ChangeFocusNextFrame(Item_ item) {
        ItemKit kit = GetKit(item);
        if (kit == null && item != null && NativeAdd(item)) {
            // An item that was hidden when it was added (a list row
            // scrolled out of view, see ListRenderer.RefreshContents) got
            // no kit, and Show doesn't tell accessibility. It is showing
            // now that it is selected or focused, so add it.
            kit = GetKit(item);
        }
        if(kit != null && !kit.GetNodeID().equals(focus)) {
            focus = kit.GetNodeID();
            isFocusDirty = true;
        }
    }

    public void  ButtonActivated(Button_ button) {
        // Activating a tab selects it and deselects the others.
        if (button instanceof Tab_) {
            ItemKit kit = GetKit(button);
            if (kit != null) {
                SetChildrenToDirty(kit.GetParent());
            }
        }
    }

    public void  ToggleButtonToggled(ToggleButton_ button) {
        SetItemToDirty(button);
    }

    /*
        A tree or list that gains focus has already reported its selection
        (Tree.GainedFocus forces a selection event), and this runs later in
        the same frame (Accessibility.Update). Focusing the container would
        undo that, leaving VoiceOver on the whole tree, so the selected item
        keeps the focus.
    */
    public void  FocusChanged(FocusEvent_ event) {
        Item_ item = event.GetNewFocus();
        if(item != null) {
            Item_ selected = GetSelectedItem(item);
            ChangeFocusNextFrame(selected != null ? selected : item);
        }
    }

    // The selected item of a tree or list, or null.
    private static Item_ GetSelectedItem(Item_ item) {
        if (item instanceof Tree_) {
            TreeSelection_ selection = ((Tree_) item).GetSelection();
            if (selection != null && !selection.IsEmpty()) {
                return selection.GetTreeItem();
            }
        } else if (item instanceof List_) {
            ListSelection_ selection = ((List_) item).GetSelection();
            if (selection != null && !selection.IsEmpty()) {
                return selection.GetListItem();
            }
        }
        return null;
    }

    /*
        A row of a List. The list's renderer hides the rows that are out of
        view only to skip drawing them, and Show doesn't tell accessibility,
        so hidden rows get a node too: VoiceOver counts a list's rows by its
        children, so a row without a node is missing from "x of y".
    */
    private static boolean IsListRow(Item_ item) {
        return item instanceof ListItem_ && ((ListItem_) item).GetList() != null;
    }

    // A List drawn as a tree (List:SetTreeAccessibility).
    private static boolean IsTreeList(Item_ item) {
        return item instanceof List_ && ((List_) item).HasTreeAccessibility();
    }

    /*
        The row a tree-like list draws this row under: the nearest row above
        it with a smaller level (ListItem:GetAccessibilityLevel), or null for
        a top row. Its kit goes under that row's kit, so VoiceOver finds its
        siblings as Tree items' (the Projects tree reads "2 of 5" that way).
    */
    private static Item_ GetParentRow(ListItem_ row) {
        List_ list = row.GetList();
        if (list == null || !list.HasTreeAccessibility()) {
            return null;
        }
        int level = row.GetAccessibilityLevel();
        if (level <= 0) {
            return null;
        }
        int index = list.GetIndexOf(row);
        for (int i = index - 1; i >= 0; i--) {
            ListItem_ other = list.GetListItem(i);
            if (other.GetAccessibilityLevel() < level) {
                return other;
            }
        }
        return null;
    }

    private ItemKit GetKitFromCode(Item_ item) {
        int code = item.GetAccessibilityCode();
        ItemKit itemKit = null;
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_() || (!item.IsShowing() && !IsListRow(item))) {
            return null;
        } else if (code == item.Get_Libraries_Interface_Item__ITEM_()) {
            itemKit = item instanceof Item3D_ ? new Item3DKit() : new ItemKit();
        } else if (code == item.Get_Libraries_Interface_Item__CUSTOM_()) {
            itemKit = item instanceof Item3D_ ? new Item3DKit() : new ItemKit();
        } else if (code == item.Get_Libraries_Interface_Item__CHECKBOX_()) {
            CheckboxKit kit = new CheckboxKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__RADIO_BUTTON_()) {
            RadioButtonKit kit = new RadioButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            ButtonKit kit = new ButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TOGGLE_BUTTON_()) {
            ToggleButtonKit kit = new ToggleButtonKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()) {
            TextboxKit kit = new TextboxKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__MENU_BAR_()) {
            MenuBarKit kit = new MenuBarKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__MENU_ITEM_()) {
            MenuItemKit kit = new MenuItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__PANE_()) {
            PaneKit kit = new PaneKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_()) {
            TreeKit kit = new TreeKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_ITEM_()) {
            TreeItemKit kit = new TreeItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TOOLBAR_()) {
            ToolbarKit kit = new ToolbarKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TAB_()) {
            TabKit kit = new TabKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TAB_PANE_()) {
            TabPaneKit kit = new TabPaneKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TABLE_()) {
            TableKit kit = new TableKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CELL_()) {
            CellKit kit = new CellKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()) {
            TextFieldKit kit = new TextFieldKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_()) {
            ListKit kit = new ListKit(IsTreeList(item));
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_ITEM_()) {
            ListItemKit kit = new ListItemKit(item instanceof ListItem_ && IsTreeList(((ListItem_) item).GetList()));
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_()) {
            TreeTableKit kit = new TreeTableKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__DIALOG_()) {
            DialogKit kit = new DialogKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__POPUP_MENU_()) {
            PopupMenuKit kit = new PopupMenuKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__PROGRESS_BAR_()) {
            ProgressBarKit kit = new ProgressBarKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_CELL_()) {
            TreeTableCellKit kit = new TreeTableCellKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__GROUP_()) {
            GroupKit kit = new GroupKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_()) {
            ChartKit kit = new ChartKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_SECTION_()) {
            ChartSectionKit kit = new ChartSectionKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_ITEM_()) {
            ChartItemKit kit = new ChartItemKit();
            itemKit = kit;
        } else if (code == item.Get_Libraries_Interface_Item__LABEL_()) {
            LabelKit kit = new LabelKit();
            itemKit = kit;
        }
        return itemKit;
    }
    public boolean NativeAdd(Item_ item)
    {
        //first has this item already been added
        ItemKit existingKit = GetKit(item);
        if(existingKit != null) {
            return true;
        }

        //second, before we possibly recurse into the parent, see if the
        //item even has an accessible representation
        ItemKit itemKit = GetKitFromCode(item);
        if (itemKit == null) {
            return false;
        }

        //third, if it's been added, accesskit seems to require parent hierarchies, at least in this design
        //be ordered parent to child. That might be dangerous quorum side, because that's definitely a contract
        //someone could violate, but we'll give it a try for the end of the hackathon.
        //as such, try the parent next
        Item_ parent = GetParentForKit(item);
        ItemKit parentKit;
        if (parent != null) {
            parentKit = GetKit(parent);

            if(parentKit == null) { //the parent isn't actually a node, it needs to be added to the hierarchy
                NativeAdd(parent);
                parentKit = GetKit(parent); //null if the parent has no kit.
                //if the parent kit is still null, just add it to the root
                if(parentKit == null) {
                    parentKit = root;
                }
            }
        } else {
            parentKit = root;
        }

        itemKit.SetItem(item);
        NodeId id = itemKit.GetNodeID();
        items.put(id, itemKit);
        kitsByItem.put(item, itemKit);
        if (itemKit instanceof Item3DKit) {
            kits3D.add((Item3DKit) itemKit);
        }
        dirtyNodes.add(id);
        parentKit.AddChild(itemKit);
        dirtyNodes.add(parentKit.GetNodeID());
        return true;
    }

    /*
        The item whose kit holds this one: the accessible parent, except
        that a submenu's items go under their menu item (MenuItemKit puts
        them in a menu), not straight under the menu bar, where
        GetAccessibleParent puts them (a MenuItem isn't an accessible
        parent, so its panel's items skip it).
    */
    private Item_ GetParentForKit(Item_ item) {
        if (item instanceof ListItem_) {
            Item_ row = GetParentRow((ListItem_) item);
            if (row != null) {
                return row;
            }
        }
        if (item instanceof MenuItem_) {
            MenuItem_ menu = ((MenuItem_) item).GetParentMenu();
            if (menu != null && GetKit(menu) != null) {
                return menu;
            }
        }
        return item.GetAccessibleParent();
    }

    public boolean NativeRemove(Item_ item)
    {
        ItemKit kit = GetKit(item);
        if(kit != null) {
            NodeId id = kit.GetNodeID();
            items.remove(id);
            kitsByItem.remove(item);
            MacAccessibilityExtras.remove(id);
            if (kit instanceof MenuItemKit) {
                MacAccessibilityExtras.remove(((MenuItemKit) kit).GetMenuID());
            }
            ItemKit parent = kit.GetParent();
            NodeId parentID = parent.GetNodeID();
            if (items.containsKey(parentID)) {
                dirtyNodes.add(parentID);
            }
            dirtyNodes.remove(id);
            if (kit instanceof Item3DKit) {
                kits3D.remove(kit);
            }
            kit.RemoveFromParent();
            // Rows a tree-like list drew under this row (GetParentForKit) are
            // its kit's children but not its Quorum children; take them out
            // too, so none is left under a node that is gone. They come back
            // when shown or selected.
            if (kit instanceof ListItemKit) {
                for (ItemKit child : kit.GetChildrenCopy()) {
                    NativeRemove(child.GetItem());
                }
            }
            if (id.equals(focus)) {
                // AccessKit needs a focus that is in the tree, and
                // NativeUpdate sends nothing while the focus is missing.
                // RestoreFocus moves it back once Quorum's focus has a node.
                focus = root.GetNodeID();
                isFocusDirty = true;
            }
            if (item == lastSelected) {
                lastSelected = null;
            }
        }
        return false;
    }

    /*
        A menu opened or closed: its menu (MenuItemKit) appears or goes, and
        VoiceOver is told, as a native menu tells it (AXMenuOpened,
        AXMenuClosed), after the update that adds or removes the menu.
    */
    public void  MenuChanged(MenuChangeEvent_ event) {
        MenuItem_ item = event.GetMenuItem();
        SetItemToDirty(item);
        ItemKit kit = GetKit(item);
        if (kit instanceof MenuItemKit) {
            NodeId menu = ((MenuItemKit) kit).GetMenuID();
            int type = event.GetEventType();
            if (type == event.Get_Libraries_Interface_Events_MenuChangeEvent__OPENED_()) {
                MacAccessibilityExtras.queueNotice(menu, kit.GetNodeID(), MacAccessibilityExtras.MENU_OPENED);
            } else if (type == event.Get_Libraries_Interface_Events_MenuChangeEvent__CLOSED_()) {
                MacAccessibilityExtras.queueNotice(menu, kit.GetNodeID(), MacAccessibilityExtras.MENU_CLOSED);
            }
        }
    }

    /*
        A tree item opened or closed (a PropertiesSectionItem too). Its
        items arrive or leave through NativeAdd and NativeRemove; the item
        is rebuilt for its expanded state, and VoiceOver is told, as
        NSOutlineView tells it (AXRowExpanded or AXRowCollapsed on the row,
        AXRowCountChanged on the outline).
    */
    public void  TreeChanged(TreeChangeEvent_ event) {
        SetItemToDirty(event.GetTree());
        TreeItem_ item = event.GetTreeItem();
        SetItemToDirty(item);
        ItemKit itemKit = GetKit(item);
        if (itemKit != null) {
            int type = event.GetEventType();
            if (type == event.Get_Libraries_Interface_Events_TreeChangeEvent__OPENED_()) {
                MacAccessibilityExtras.queueNotice(itemKit.GetNodeID(), null, MacAccessibilityExtras.ROW_EXPANDED);
            } else if (type == event.Get_Libraries_Interface_Events_TreeChangeEvent__CLOSED_()) {
                MacAccessibilityExtras.queueNotice(itemKit.GetNodeID(), null, MacAccessibilityExtras.ROW_COLLAPSED);
            }
        }
        ItemKit treeKit = GetKit(event.GetTree());
        if (treeKit != null) {
            MacAccessibilityExtras.queueNotice(treeKit.GetNodeID(), null, MacAccessibilityExtras.ROW_COUNT_CHANGED);
        }
    }

    public void  TreeTableChanged(TreeTableChangeEvent_ event) {
//        SetItemToDirty(event.GetTreeTable());
    }

    public void  ControlActivated(ControlActivationEvent_ event) {
        /*
        I don't know what this one does exactly and the documentation Quorum side is vague. UIA doesn't
        have an implementation for it, so it may not do anything. I'm leaving it blank for now.
         */
    }

    public void  TextChanged(TextChangeEvent_ event) {
        Control_ control = event.GetControl();
        SetItemToDirty(control);
        ItemKit kit = GetKit(control);
        if(kit == null) {
            return;
        }
        if (kit instanceof TextFieldKit) {
            TextFieldKit fieldKit = (TextFieldKit)kit;
            fieldKit.SetTextDirty();
        } else if(kit instanceof TextboxKit && control instanceof TextBox_) {
            TextboxKit boxKit = (TextboxKit)kit;
            int type = event.GetEventType();
            int index = event.GetIndex();
            TextBox_ box = (TextBox_) control;
            int line = box.GetLineIndexOfCharacter(index); //the line number of the index.
            final int ADDED = event.Get_Libraries_Interface_Events_TextChangeEvent__ADDED_();
            final int DELETED = event.Get_Libraries_Interface_Events_TextChangeEvent__DELETED_();
            final int MODIFIED = event.Get_Libraries_Interface_Events_TextChangeEvent__MODIFIED_();
            if(type == ADDED) { //text added
                String input = event.GetAddedText();
                boolean isMultiLine = IsMultiline(input);
                if(!isMultiLine) { //just update the line
                    boxKit.MarkLineDirty(line);
                } else { //invalidate the line and any line after it.
                    boxKit.MarkLinesDirty(line);
                }
            } else  if(type == DELETED) {
                String input = event.GetDeletedText();
                boolean isMultiLine = IsMultiline(input);
                if(!isMultiLine) { //just update the line
                    boxKit.MarkLineDirty(line);
                } else { //invalidate the line and any line after it.
                    boxKit.MarkLinesDirty(line);
                }
            /*
            If the text is modified, it means that the content at the cursor was deleted and this is
            get deleted text. The content it was replaced with it get added text. If either one is multi-line
            we will need to refresh the lines after it.
             */
            } else if (type == MODIFIED) {
                String textDeletedAtCaret = event.GetDeletedText();
                String textReplacedAtCaret = event.GetAddedText();
                boolean isMultiLine = IsMultiline(textDeletedAtCaret) || IsMultiline(textReplacedAtCaret);
                if(!isMultiLine) { //just update the line
                    boxKit.MarkLineDirty(line);
                } else { //invalidate the line and any line after it.
                    boxKit.MarkLinesDirty(line);
                }
            }

        }
    }

    public boolean IsMultiline(String input) {
        if(input == null) {
            return false;
        } else {
            return input.contains("\n") || input.contains("\r\n") || input.contains("\r");
        }
    }

    public void  WindowFocusChanged(WindowFocusEvent_ event) {
        if (event != null) {
            SetWindowFocus(event.IsFocused());
        }
    }

    /*
        Asks VoiceOver to say value, without moving focus: the text goes
        into a live region (AnnouncerKit). The two announcers take turns,
        and the other one is emptied, so a repeated message is spoken again.
    */
    public void  Notify(Item_ item, String value) {
        Announce(value, Live.ASSERTIVE);
    }

    /*
        notificationType is one of AccessibilityManager's constants. An aborted
        action interrupts VoiceOver; anything else waits for it to finish
        speaking.
    */
    public void  Notify(Item_ item, String value, int notificationType) {
        final int ACTION_ABORTED_NOTIFICATION = 1;  // AccessibilityManager.quorum
        Announce(value, notificationType == ACTION_ABORTED_NOTIFICATION ? Live.ASSERTIVE : Live.POLITE);
    }

    private void Announce(String value, Live live) {
        if (value == null || value.isEmpty()) {
            return;
        }
        AnnouncerKit now = announcers[currentAnnouncer];
        AnnouncerKit other = announcers[1 - currentAnnouncer];
        now.SetText(value, live);
        other.SetText(null, live);
        dirtyNodes.add(now.GetNodeID());
        dirtyNodes.add(other.GetNodeID());
        currentAnnouncer = 1 - currentAnnouncer;
    }

    public void  Shutdown() {
        try { //I think this is ok. Just shut down.
            adapter.close();
        } catch(Exception e) {
        }
    }

    public static int GetCellRowIndex(Object_ object)
    {
        if (object instanceof Cell_)
        {
            Cell_ cell = (Cell_)object;
            Column_ column = cell.GetColumn();

            if (column != null)
            {
                return column.GetCellIndex(cell);
            }
        }
        else if (object instanceof TreeTableCell_)
        {
            TreeTableCell_ cell = (TreeTableCell_)object;
            TreeTableColumn_ column = cell.GetColumn();

            if (column != null)
            {
                return column.GetCellIndex(cell);
            }
        }

        // If we can't retrieve the correct position, return -1 to indicate failure.
        return -1;
    }

    public static int GetCellColumnIndex(Object_ object)
    {
        if (object instanceof Cell_)
        {
            Cell_ cell = (Cell_)object;
            Column_ column = cell.GetColumn();

            if (column != null)
            {
                Spreadsheet_ spreadsheet = cell.GetSpreadsheet();
                if (spreadsheet != null)
                    return spreadsheet.GetColumnIndex(column);
            }
        }
        else if (object instanceof TreeTableCell_)
        {
            TreeTableCell_ cell = (TreeTableCell_)object;
            TreeTableColumn_ column = cell.GetColumn();

            if (column != null)
            {
                TreeTable_ treeTable = cell.GetTreeTable();
                if (treeTable != null)
                    return treeTable.GetColumnIndex(column);
            }
        }

        // If we can't retrieve the correct position, return -1 to indicate failure.
        return -1;
    }

    public static int GetTreeItemSetSize(TreeItem_ item)
    {
        TreeItem_ parent = item.GetParentTreeItem();
        if (parent == null)
        {
            Tree_ tree = item.GetTree();
            if (tree == null)
                return 1;
            else
                return tree.GetSize();
        }
        else
        {
            return parent.GetSize();
        }
    }

    /*
    Used by the native layer. Given a TreeItem_ object, returns the 1-indexed
    position of that TreeItem within its set.
    */
    public static int GetTreeItemSetPosition(TreeItem_ item)
    {
        Array_ treeItems;
        TreeItem_ parent = item.GetParentTreeItem();

        if (parent != null)
            treeItems = parent.Get_Libraries_Interface_Controls_TreeItem__treeItems_();
        else
        {
            Tree_ tree = item.GetTree();
            if (tree != null)
                treeItems = tree.Get_Libraries_Interface_Controls_Tree__treeItems_();
            else
                return 1;
        }

        for (int i = 0; i < treeItems.GetSize(); i++)
        {
            if (treeItems.Get(i) == item)
                return i + 1;
        }

        return 1;
    }

    public static int GetTableColumnsSize(Object_ object)
    {
        Spreadsheet_ spreadsheet = null;
        TreeTable_ treeTable = null;

        if (object instanceof Spreadsheet_)
        {
            spreadsheet = (Spreadsheet_)object;
        }
        else if (object instanceof TreeTable_)
        {
            treeTable = (TreeTable_)object;
        }
        else if (object instanceof Cell_)
        {
            spreadsheet = ((Cell_)object).GetSpreadsheet();
        }
        else if (object instanceof TreeTableCell_)
        {
            treeTable = ((TreeTableCell_)object).GetTreeTable();
        }

        if (spreadsheet != null)
        {
            return spreadsheet.GetColumnsSize();
        }
        else if (treeTable != null)
        {
            return treeTable.GetColumnsSize();
        }

        // If we can't retrieve the size, return -1 to indicate failure.
        return -1;
    }

    /*
    Used by the native layer. Returns the total number of rows in a TreeTable
    or Spreadsheet. If a Cell or TreeTableCell is given, gives information about
    the table structure that contains that cell. If the value can't be retrieved,
    this returns -1.
    */
    public static int GetTableRowsSize(Object_ object)
    {
        Spreadsheet_ spreadsheet = null;
        TreeTable_ treeTable = null;

        if (object instanceof Spreadsheet_)
        {
            spreadsheet = (Spreadsheet_)object;
        }
        else if (object instanceof TreeTable_)
        {
            treeTable = (TreeTable_)object;
        }
        else if (object instanceof Cell_)
        {
            spreadsheet = ((Cell_)object).GetSpreadsheet();
        }
        else if (object instanceof TreeTableCell_)
        {
            treeTable = ((TreeTableCell_)object).GetTreeTable();
        }

        if (spreadsheet != null)
        {
            return spreadsheet.GetRowsSize();
        }
        else if (treeTable != null)
        {
            /*
            TO-DO: The TreeTable doesn't currently have a way to calculate its number of rows.
            It's not clear if this should be number of top-most rows, currently visible rows, or all total rows.
            */
            return 0;
        }

        // If we can't retrieve the size, return -1 to indicate failure.
        return -1;
    }

    public static int GetMenuItemSetSize(MenuItem_ item)
    {
        MenuItem_ parent = item.GetParentMenu();
        if (parent == null)
        {
            MenuRoot_ root = item.GetMenuRoot();
            if (root == null)
                return 1;
            else
                return root.GetSize();
        }
        else
        {
            return parent.GetSize();
        }
    }

    /*
    Used by the native layer. Given a MenuItem_ object, returns the 1-indexed
    position of that MenuItem within its set.
    */
    public static int GetMenuItemSetPosition(MenuItem_ item)
    {
        MenuItem_ parent = item.GetParentMenu();

        if (parent != null)
        {
            Array_ menuItems = parent.Get_Libraries_Interface_Controls_MenuItem__menuItems_();
            for (int i = 0; i < menuItems.GetSize(); i++)
            {
                if (menuItems.Get(i) == item)
                    return i + 1;
            }
        }
        else
        {
            MenuRoot_ root = item.GetMenuRoot();
            if (root != null)
            {
                Iterator_ iterator = root.GetIterator();
                int i = 0;
                while (iterator.HasNext())
                {
                    i = i + 1;
                    if (iterator.Next() == item)
                        return i;
                }
            }
        }

        return 1;
    }
}
