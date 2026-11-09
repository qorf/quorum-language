package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.Arrays;
import java.util.Collections;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;

/*
    An item in a 3D scene with no standard role (the codes ITEM and CUSTOM),
    such as the scene editor's terrain, models, lights and sounds.

    - Role: a graphics object (AXGroup, with no subrole), not AXUnknown,
      which VoiceOver reads as "unknown". The item's role description
      ("terrain", "model"; Item:SetAccessibilityRoleDescription) is what
      VoiceOver says instead of "group" (ItemKit.BuildNode).
    - Its description is its value (AXValue), not its help (AXHelp). A
      group's value isn't read by VoiceOver (plan 15), so DESCRIPTION_AS_VALUE
      may still be turned off.
    - A keyboard cursor inside the item (Item3D:GetAccessibilityCursor, such
      as the terrain cursor): the item becomes an outline (AXOutline) with
      two rows. One is selected and holds the cursor's reading; the other
      is blank. Each move swaps them: the other row gets the new reading
      and is selected, and the one that was selected is emptied. AccessKit
      posts AXSelectedRowsChanged on the outline and VoiceOver reads the
      newly selected row, while the focus stays on the item: a selection
      change, as in an outline, not a focus change and not an announcement.
      The outline is marked UNCOUNTED (SendExtras), so Mac.so gives it no
      AXRows and VoiceOver has nothing to count ("1 of 2"). The item's own
      description is then its help.
    - Bounds: nothing reports a 3D item moving on the screen (the camera,
      the item or the window can move it), and nothing reports its
      description or cursor changing, so MacAccessibility asks HasChanged:
      every frame for the focused item, a few times a second for the rest.
      The node is rebuilt only when something changed.
*/
public class Item3DKit extends ItemKit {
    /*
        true: the description is the value. false: it goes after the name in
        the label, for the case where VoiceOver turns out not to read a
        group's value (see the plan's test list). One switch, so the test
        can try both. An item with a cursor uses neither: its description
        is its help.
    */
    private static final boolean DESCRIPTION_AS_VALUE = true;

    // What VoiceOver calls the cursor's row, instead of "outline row".
    private static final String CURSOR_ROLE_DESCRIPTION = "terrain cursor";

    /*
        The shortest time between two new cursor rows, while the cursor
        glides with a key held. A change sooner waits for this (HasChanged
        is asked every frame for the focused item), so the last place is
        always read.
    */
    private static final long CURSOR_MOVE_MILLISECONDS = 250;

    // What was last sent or checked, for HasChanged.
    private Rect lastBounds = null;
    // True if the last HasChanged found the box moved (TakeBoundsMoved).
    private boolean boundsMoved = false;
    private String lastName = null;
    private String lastDescription = null;

    /*
        Which of the cursor's two rows is selected (CursorRowID), and the
        reading it has now, or null with no cursor (and no rows).
    */
    private int cursorRow = 0;
    private String cursorText = null;
    private long lastCursorMove = 0;

    // True while Mac.so holds the UNCOUNTED fact for this node (SendExtras).
    private boolean sentUncounted = false;

    public Item3DKit() {
        SetRole(Role.GRAPHICS_OBJECT);
    }

    @Override
    public Node Build() {
        Item_ item = GetItem();
        boolean hasCursor = cursorText != null;
        Node builder = new Node(hasCursor ? Role.TREE : GetRole());
        if (item != null) {
            Rect rect = GetBoundingRectangle();
            String name = item.GetName();
            String description = item.GetDescription();
            lastBounds = rect;
            lastName = name;
            lastDescription = description;
            // An item that doesn't show gets no bounds, so VoiceOver doesn't
            // outline a point in the corner of the window.
            if (!IsEmpty(rect)) {
                builder.setBounds(rect);
            }
            boolean hasDescription = description != null && !description.isEmpty();
            if (hasCursor) {
                // The cursor's row says where it is; the item's own
                // description, such as a whole terrain's, is its help,
                // which VoiceOver reads after a pause.
                builder.setLabel(name);
                if (hasDescription) {
                    builder.setDescription(description);
                }
            } else if (DESCRIPTION_AS_VALUE) {
                builder.setLabel(name);
                if (hasDescription) {
                    builder.setValue(description);
                }
            } else if (hasDescription) {
                builder.setLabel(name == null || name.isEmpty() ? description : name + ", " + description);
            } else {
                builder.setLabel(name);
            }
        }
        BuildChildren(builder);
        return builder;
    }

    // The cursor's two rows, always both, while there is a cursor.
    @Override
    public Iterable<NodeId> GetInternalChildren() {
        if (cursorText == null) {
            return Collections.emptySet();
        }
        return Arrays.asList(RowID(0), RowID(1));
    }

    /*
        The id of one of the cursor's two rows, 0 or 1. Like a text run's, an
        id is (kit id << 32) | number, which never meets a kit id.
    */
    private NodeId RowID(int row) {
        return new NodeId((GetNodeID().value << 32) | (row + 1));
    }

    // The id of the selected row.
    private NodeId CursorRowID() {
        return RowID(cursorRow);
    }

    // True once after HasChanged found the box moved.
    public boolean TakeBoundsMoved() {
        boolean moved = boundsMoved;
        boundsMoved = false;
        return moved;
    }

    // The cursor's selected row, or null with no cursor.
    public NodeId GetCursorRowID() {
        return cursorText == null ? null : CursorRowID();
    }

    // Both rows are rebuilt with the item: a swap changes both, and their bounds are the item's.
    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        return GetInternalChildren();
    }

    /*
        One of the cursor's rows: an outline row (AXRow). The selected one is
        labeled with the cursor's reading and outlined where the item is.
        The other is blank, not selected and has no bounds: it never says
        anything that is no longer true, should VoiceOver's own cursor reach
        it, and the next move fills it.
    */
    @Override
    public Node BuildInternalChild(NodeId id) {
        Node row = new Node(Role.TREE_ITEM);
        // VoiceOver reads a row's role description after its text.
        row.setRoleDescription(CURSOR_ROLE_DESCRIPTION);
        boolean selected = id.equals(CursorRowID());
        row.setSelected(selected);
        if (selected && cursorText != null) {
            row.setLabel(cursorText);
        }
        // Both rows keep the cursor ring's bounds, so VoiceOver's box stays
        // on the ring whichever row its own cursor is on.
        if (lastBounds != null && !IsEmpty(lastBounds)) {
            row.setBounds(lastBounds);
        }
        return row;
    }

    /*
        While there is a cursor, the outline is UNCOUNTED: Mac.so gives it no
        AXRows, so VoiceOver can't read "1 of 2" for its rows. The rows send
        nothing, so they have no AXIndex, level or set position either.
    */
    @Override
    public void SendExtras() {
        if (cursorText != null) {
            MacAccessibilityExtras.setInfo(GetNodeID(), MacAccessibilityExtras.UNCOUNTED | GetMenuFlag(), 0, -1, -1, 0, null);
            sentUncounted = true;
            return;
        }
        if (sentUncounted) {
            MacAccessibilityExtras.remove(GetNodeID());
            sentUncounted = false;
        }
        super.SendExtras();
    }

    /*
        Works out the bounds, name, description and cursor again and returns
        true if any of them changed since they were last sent or checked:
        the bounds by half a pixel or more.

        focused is true if this item has Quorum's focus (MacAccessibility's).
    */
    public boolean HasChanged(boolean focused) {
        Item_ item = GetItem();
        if (item == null) {
            return false;
        }
        Rect rect = GetBoundingRectangle();
        String name = item.GetName();
        String description = item.GetDescription();
        boolean changed = lastBounds == null || Moved(lastBounds, rect)
                || !Same(lastName, name) || !Same(lastDescription, description);
        boundsMoved = lastBounds != null && Moved(lastBounds, rect);
        lastBounds = rect;
        lastName = name;
        lastDescription = description;
        if (CheckCursor(item, focused)) {
            changed = true;
        }
        return changed;
    }

    /*
        Reads the cursor again and returns true if its rows changed. While
        the item has the focus, a new reading goes to the other row, which
        is selected, so VoiceOver reads it; a new reading sooner than
        CURSOR_MOVE_MILLISECONDS after the last waits. Otherwise the
        selected row changes in place, which VoiceOver doesn't read.
    */
    private boolean CheckCursor(Item_ item, boolean focused) {
        String text = item instanceof Item3D_ ? ((Item3D_) item).GetAccessibilityCursor() : null;
        if (text != null && text.isEmpty()) {
            text = null;
        }
        if (Same(text, cursorText)) {
            return false;
        }
        if (text == null || cursorText == null || !focused) {
            // The cursor came or went, or the item isn't focused.
            cursorText = text;
            return true;
        }
        long time = System.currentTimeMillis();
        if (time - lastCursorMove < CURSOR_MOVE_MILLISECONDS) {
            return false;
        }
        lastCursorMove = time;
        cursorRow = 1 - cursorRow;
        cursorText = text;
        return true;
    }

    private static boolean Moved(Rect a, Rect b) {
        return Math.abs(a.x0 - b.x0) >= 0.5 || Math.abs(a.y0 - b.y0) >= 0.5
                || Math.abs(a.x1 - b.x1) >= 0.5 || Math.abs(a.y1 - b.y1) >= 0.5;
    }

    private static boolean Same(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }
}
