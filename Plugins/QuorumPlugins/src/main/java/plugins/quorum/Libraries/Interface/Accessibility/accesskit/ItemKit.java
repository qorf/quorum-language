package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.ArrayList;
import java.util.Collections;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.DesktopDisplay_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Behaviors.Behavior_;
import quorum.Libraries.Interface.Behaviors.Blocks.CodeEditorOpenPopupMenuBehavior;
import quorum.Libraries.Interface.Behaviors.Controls.OpenPopupMenuBehavior;
import quorum.Libraries.Interface.Behaviors.Controls.TextBoxOpenPopupMenuBehavior;
import quorum.Libraries.Interface.Controls.Blocks.CodeEditor_;
import quorum.Libraries.Interface.Controls.Control_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Events.BehaviorEvent;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;

public class ItemKit {
    /*
        Node ids 1 to 15 are kept for MacAccessibility's own nodes (the root
        is 1, the announcers 2 and 3). Every kit gets the next number, so no
        two kits share an id, which item hash codes couldn't promise. Text
        runs use (kit id << 32) | line, which never meets a kit id.
    */
    private static final long FIRST_ITEM_NODE_ID = 16;
    private static long nextNodeId = FIRST_ITEM_NODE_ID;

    private final NodeId id = NewNodeID();
    private Role role = null;
    private Item_ item = null;
    private ItemKit parent = null;
    private int indexInParent = -1;
    private ArrayList<ItemKit> children = new ArrayList<ItemKit>();

    // True while Mac.so holds a HAS_MENU fact for this node (SendExtras).
    private boolean sentMenuFlag = false;

    // The open tooltip's node while this item's tooltip shows, else null.
    private NodeId describedBy = null;

    public ItemKit() {
        SetRole(Role.UNKNOWN);
    }

    private static synchronized NodeId NewNodeID() {
        NodeId result = new NodeId(nextNodeId);
        nextNodeId = nextNodeId + 1;
        return result;
    }

    /*
        Needs to be replaced for general items.
     */
    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if (item != null) {
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    /*
        What MacAccessibility sends: the kit's Build, plus what every item
        has: a role description for items with no standard role (the codes
        ITEM and CUSTOM, such as a Block or a scene editor's 3D item), and
        the actions VoiceOver may ask for. Labels set a role description of
        their own ("Label") for other platforms; AccessKit's "text" is
        right on the Mac, so standard roles keep theirs.
    */
    public final Node BuildNode() {
        Node node = Build();
        Item_ item = GetItem();
        if (node != null && item != null) {
            int code = item.GetAccessibilityCode();
            if (code == item.Get_Libraries_Interface_Item__ITEM_() || code == item.Get_Libraries_Interface_Item__CUSTOM_()) {
                String roleDescription = item.GetAccessibilityRoleDescription();
                if (roleDescription != null && !roleDescription.isEmpty()) {
                    node.setRoleDescription(roleDescription);
                }
            }
            AddActions(node, item);
            String help = GetTooltipHelp(item);
            if (help != null) {
                node.setDescription(help);
            }
            if (describedBy != null) {
                node.addDescribedBy(describedBy);
            }
        }
        return node;
    }

    /*
        A control's tooltip words, when its tooltip is on, else null. AccessKit
        gives a node's description to VoiceOver as its help (AXHelp), and a
        Mac control's tooltip is its help: VoiceOver reads it as a hint after
        a pause, or with VO-Shift-H, and says nothing when the tooltip itself
        pops up, as with AppKit's own tooltips. So the tooltip's words replace
        the description each kit set.
    */
    private static String GetTooltipHelp(Item_ item) {
        if (!(item instanceof Control_)) {
            return null;
        }
        Control_ control = (Control_) item;
        if (!control.IsTooltipEnabled() || !control.HasTooltipContent()) {
            return null;
        }
        String words = control.GetTooltipWords();
        if (words == null || words.isEmpty()) {
            return null;
        }
        return words;
    }

    /*
        The node of this item's open tooltip, which describes it, or null
        when the tooltip closes (MacAccessibility.NativeTooltipShown).
    */
    public final void SetDescribedBy(NodeId id) {
        describedBy = id;
    }

    public final NodeId GetDescribedBy() {
        return describedBy;
    }

    /*
        The actions this item supports. Each one listed here must be
        carried out by DoAction. A kit that overrides this should call it.
        A context menu isn't an AccessKit action on the Mac: Mac.so offers
        it from the HAS_MENU fact (SendExtras).
    */
    protected void AddActions(Node node, Item_ item) {
        if (item.IsFocusable()) {
            node.addAction(Action.FOCUS);
        }
        // A control with a tooltip offers to show it, or to hide it while
        // it shows.
        if (item instanceof Control_) {
            Control_ control = (Control_) item;
            if (control.IsTooltipEnabled() && control.HasTooltipContent()) {
                node.addAction(control.IsTooltipShowing() ? Action.HIDE_TOOLTIP : Action.SHOW_TOOLTIP);
            }
        }
    }

    /*
        Carries out a VoiceOver request, from MacAccessibility.NativeUpdate
        (never while AccessKit or AppKit is calling back). A kit that
        overrides this should call it for the actions it doesn't handle.
    */
    public void DoAction(ActionRequest request) {
        Item_ item = GetItem();
        if (item == null) {
            return;
        }
        if (request.action == Action.FOCUS && item.IsFocusable()) {
            item.Focus();
        } else if (request.action == Action.SHOW_CONTEXT_MENU) {
            ShowPopupMenu(item);
        } else if (request.action == Action.SHOW_TOOLTIP && item instanceof Control_) {
            ((Control_) item).ShowTooltip();
        } else if (request.action == Action.HIDE_TOOLTIP && item instanceof Control_) {
            ((Control_) item).HideTooltip();
        }
    }

    /*
        Opens an item's context menu the way Shift+F10 does: the behavior
        DefaultInputSets gives that kind of control runs with the item and
        no mouse event, so the menu opens at the item (or at the caret of
        a text control).
    */
    protected static void ShowPopupMenu(Item_ item) {
        if (!HasPopupMenu(item)) {
            return;
        }
        Behavior_ behavior;
        if (item instanceof CodeEditor_) {
            behavior = new CodeEditorOpenPopupMenuBehavior();
        } else if (item instanceof TextBox_ || item instanceof TextField_) {
            behavior = new TextBoxOpenPopupMenuBehavior();
        } else {
            behavior = new OpenPopupMenuBehavior();
        }
        BehaviorEvent event = new BehaviorEvent();
        event.SetItem(item);
        behavior.Run(event);
    }

    protected static boolean HasPopupMenu(Item_ item) {
        return item instanceof Control_ && ((Control_) item).GetPopupMenu() != null;
    }

    // MacAccessibilityExtras.HAS_MENU if the item has a context menu, else 0.
    protected final int GetMenuFlag() {
        return HasPopupMenu(GetItem()) ? MacAccessibilityExtras.HAS_MENU : 0;
    }

    /*
        Sends what VoiceOver needs but AccessKit's macOS adapter leaves out
        (MacAccessibilityExtras), each time this kit's node is built. Kits
        that send facts of their own add GetMenuFlag to their flags; this
        one sends only whether there is a context menu, and only when that
        changes.
    */
    public void SendExtras() {
        int flags = GetMenuFlag();
        if (flags != 0) {
            MacAccessibilityExtras.setInfo(GetNodeID(), flags, 0, -1, -1, 0, null);
            sentMenuFlag = true;
        } else if (sentMenuFlag) {
            MacAccessibilityExtras.remove(GetNodeID());
            sentMenuFlag = false;
        }
    }

    // A node with only this kit's role and children (MacAccessibility.SafeBuild).
    public final Node BuildFallbackNode() {
        Node node = new Node(GetRole());
        BuildChildren(node);
        return node;
    }

    protected void BuildChildren(Node builder) {
        for (NodeId child : GetInternalChildren()) {
            builder.addChild(child);
        }
        for (ItemKit child : children) {
            builder.addChild(child.GetNodeID());
        }
    }

    public NodeId GetNodeID() {
        return id;
    }

    /*
        The node AccessKit's focus goes to while this item has Quorum's
        focus: its own node, or a node inside it (Item3DKit's cursor row,
        when that kit moves the focus to it).
    */
    public NodeId GetFocusTarget() {
        return GetNodeID();
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

    /*
        A copy of the children, so building nodes can't be disturbed if
        something adds or removes a kit meanwhile (everything runs on the
        main thread, so a copy is all that is needed).
    */
    public final ArrayList<ItemKit> GetChildrenCopy() {
        return new ArrayList<ItemKit>(children);
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
        DesktopDisplay_ display = (DesktopDisplay_) GameStateManager.display;

        if (display == null)
        {
            return new Rect(0,0,0,0);
        }

        /*
        We need to know the height to correct for the difference in y
        direction between the Mac (0 is at the top in AccessKit) and Quorum
        (0 is at the bottom).
        */
        double windowHeight = display.GetHeight();

        if (item instanceof Item2D_)
        {
            Item2D_ item2D = (Item2D_) item;
            double itemX = item2D.GetScreenX();
            double itemY = item2D.GetScreenY();

            if (Double.isNaN(itemX)) {
                itemX = 0;
            }

            if (Double.isNaN(itemY)) {
                itemY = 0;
            }

            return new Rect(
                    itemX,
                    windowHeight - itemY - item2D.GetHeight(),
                    item2D.GetWidth() + itemX,
                    windowHeight - itemY
            );
        }
        else if (item instanceof Item3D_)
        {
            // The part of the window the item covers, in pixels from the
            // bottom left like an Item2D's screen position, viewport
            // included and clipped to it, or undefined if none of it shows
            // (Item3D:GetScreenBounds).
            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();
            if (rectangle == null)
            {
                return new Rect(0,0,0,0);
            }
            double left = rectangle.GetX();
            double bottom = rectangle.GetY();
            return new Rect(
                    left,
                    windowHeight - bottom - rectangle.GetHeight(),
                    left + rectangle.GetWidth(),
                    windowHeight - bottom
            );
        }
        return new Rect(0,0,0,0);
    }

    // True for a rectangle with no area (a 3D item that doesn't show).
    public static boolean IsEmpty(Rect rect) {
        return rect.x1 <= rect.x0 || rect.y1 <= rect.y0;
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

    /*
        Carries out a VoiceOver request for one of this kit's internal
        children (request.target), such as a calendar's day. Internal
        children have no kit of their own, so MacAccessibility hands the
        request to the kit that holds them. Most kits offer no actions on
        them, so this does nothing.
    */
    public void DoInternalChildAction(ActionRequest request) {
    }
}
