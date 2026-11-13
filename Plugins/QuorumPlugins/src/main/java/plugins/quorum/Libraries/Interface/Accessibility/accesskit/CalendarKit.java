package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.ArrayList;
import java.util.Collections;
import quorum.Libraries.Interface.Controls.Calendar_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.System.CalendarDate_;

/*
    A calendar: a grid (AXTable) named by the month shown, such as
    "November 2026", with six rows of seven days. The days are drawn by the
    calendar itself, so they are nodes of this kit (internal children), not
    items: each row is a ROW node, and each day a GRID_CELL node labelled
    with its full spoken date (Calendar:GetDaySpokenText), such as
    "Thursday, November 12, 2026, today, selected". The chosen day's cell
    is selected.

    AccessKit's macOS adapter gives a grid no table facts (rows, columns,
    headers), so VoiceOver can't move by row and column on its own; each
    day says its whole date instead. While the calendar has the focus,
    AccessKit's focus is on the current day's cell (GetFocusTarget), so
    VoiceOver reads each day as the arrow keys move to it.

    The previous and next month buttons are the calendar's own Buttons, with
    their own nodes (ButtonKit).

    Node ids: (kit id << 32) | number, as for text runs, which never meets a
    kit id. Rows use the numbers 1 to 6 and days 16 to 57, plus 256 in the
    second set (below).

    Two sets of ids: when the days shown change (another month, or another
    first day of the week), the grid takes the other set, so the current
    day's cell is a new node even when it is at the same place as before,
    and VoiceOver reads the new day. Without that, Page Up between two
    months that start on the same weekday would leave the focus on the same
    node, and VoiceOver would say nothing. The set changes only in
    SyncDays, which MacAccessibility calls as it marks the calendar to be
    built again, so the focus never names a node that wasn't sent.
*/
public class CalendarKit extends ItemKit {
    // The calendar's layout, in pixels before the interface scale
    // (Calendar.quorum): the days are a 6 by 7 grid of square cells at the
    // bottom, PADDING from the left, right and bottom edges.
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final double WIDTH = 268;
    private static final double HEIGHT = 292;
    private static final double PADDING = 8;
    private static final double CELL_SIZE = 36;

    private static final long FIRST_ROW = 1;
    private static final long FIRST_DAY = 16;
    private static final long SECOND_SET = 256;
    private static final long NUMBER_MASK = 0xFFFFFFFFL;

    // Which set of ids the grid uses, 0 or 1 (SyncDays).
    private int set = 0;

    // The day number of the first day shown when the set was last checked,
    // or Long.MIN_VALUE before the first check.
    private long firstShownDay = Long.MIN_VALUE;

    public CalendarKit() {
        SetRole(Role.GRID);
    }

    private Calendar_ GetCalendar() {
        Item_ item = GetItem();
        return item instanceof Calendar_ ? (Calendar_) item : null;
    }

    /*
        Called when the calendar changed, just before its node is marked to
        be built again: if the days shown changed, the grid takes the other
        set of ids.
    */
    public void SyncDays() {
        Calendar_ calendar = GetCalendar();
        if (calendar == null) {
            return;
        }
        CalendarDate_ first = calendar.GetShownDay(0, 0);
        if (first == null) {
            return;
        }
        long dayNumber = first.GetDayNumber();
        if (firstShownDay != Long.MIN_VALUE && dayNumber != firstShownDay) {
            set = 1 - set;
        }
        firstShownDay = dayNumber;
    }

    private long Base() {
        return (GetNodeID().value << 32) | (set == 0 ? 0 : SECOND_SET);
    }

    private NodeId RowID(int row) {
        return new NodeId(Base() | (FIRST_ROW + row));
    }

    private NodeId DayID(int row, int column) {
        return new NodeId(Base() | (FIRST_DAY + row * COLUMNS + column));
    }

    // The place of a day node in the grid, row * COLUMNS + column, or -1 if
    // the id isn't one of this set's days.
    private int DayIndex(NodeId id) {
        if ((id.value >>> 32) != GetNodeID().value) {
            return -1;
        }
        long number = id.value & NUMBER_MASK;
        long setBit = set == 0 ? 0 : SECOND_SET;
        if ((number & SECOND_SET) != setBit) {
            return -1;
        }
        long index = (number & ~SECOND_SET) - FIRST_DAY;
        if (index < 0 || index >= ROWS * COLUMNS) {
            return -1;
        }
        return (int) index;
    }

    // The row of a row node, or -1 if the id isn't one of this set's rows.
    private int RowIndex(NodeId id) {
        if ((id.value >>> 32) != GetNodeID().value) {
            return -1;
        }
        long number = id.value & NUMBER_MASK;
        long setBit = set == 0 ? 0 : SECOND_SET;
        if ((number & SECOND_SET) != setBit) {
            return -1;
        }
        long row = (number & ~SECOND_SET) - FIRST_ROW;
        if (row < 0 || row >= ROWS) {
            return -1;
        }
        return (int) row;
    }

    @Override
    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if (item != null) {
            if (firstShownDay == Long.MIN_VALUE) {
                SyncDays();
            }
            builder.setBounds(GetBoundingRectangle());
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    // The six rows, before the month buttons (BuildChildren).
    @Override
    public Iterable<NodeId> GetInternalChildren() {
        if (GetCalendar() == null) {
            return Collections.emptySet();
        }
        ArrayList<NodeId> rows = new ArrayList<NodeId>();
        for (int row = 0; row < ROWS; row++) {
            rows.add(RowID(row));
        }
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                rows.add(DayID(row, column));
            }
        }
        return rows;
    }

    /*
        Every row and day is built again with the calendar: any change can
        move the days (another month), change their words (today, selected)
        or their bounds.
    */
    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        return GetInternalChildren();
    }

    /*
        GetInternalChildren lists the rows first, then the days, so that
        BuildChildren gives the grid only its rows, each row its days.
    */
    @Override
    protected void BuildChildren(Node builder) {
        if (GetCalendar() != null) {
            for (int row = 0; row < ROWS; row++) {
                builder.addChild(RowID(row));
            }
        }
        for (ItemKit child : GetChildrenCopy()) {
            builder.addChild(child.GetNodeID());
        }
    }

    @Override
    public Node BuildInternalChild(NodeId id) {
        Calendar_ calendar = GetCalendar();
        int row = RowIndex(id);
        if (row >= 0) {
            Node node = new Node(Role.ROW);
            Rect bounds = RowBounds(row);
            if (bounds != null) {
                node.setBounds(bounds);
            }
            for (int column = 0; column < COLUMNS; column++) {
                node.addChild(DayID(row, column));
            }
            return node;
        }
        int index = DayIndex(id);
        if (index < 0) {
            throw new IllegalArgumentException("unexpected child ID");
        }
        Node node = new Node(Role.GRID_CELL);
        if (calendar == null) {
            return node;
        }
        int dayRow = index / COLUMNS;
        int column = index % COLUMNS;
        CalendarDate_ date = calendar.GetShownDay(dayRow, column);
        Rect bounds = DayBounds(dayRow, column);
        if (bounds != null) {
            node.setBounds(bounds);
        }
        if (date != null) {
            node.setLabel(calendar.GetDaySpokenText(date));
            CalendarDate_ chosen = calendar.GetDate();
            node.setSelected(chosen != null && chosen.Equals(date));
            node.addAction(Action.FOCUS);
            if (calendar.IsAvailable(date)) {
                node.addAction(Action.CLICK);
            }
        }
        return node;
    }

    /*
        The days' grid, in AccessKit's coordinates (from the top left of the
        window): x0, top, x1, bottom. Null if the calendar has no area.
    */
    private double[] GridBounds() {
        Rect rect = GetBoundingRectangle();
        if (IsEmpty(rect)) {
            return null;
        }
        double width = rect.x1 - rect.x0;
        double height = rect.y1 - rect.y0;
        double left = rect.x0 + width * PADDING / WIDTH;
        double right = left + width * COLUMNS * CELL_SIZE / WIDTH;
        double bottom = rect.y1 - height * PADDING / HEIGHT;
        double top = bottom - height * ROWS * CELL_SIZE / HEIGHT;
        return new double[] { left, top, right, bottom };
    }

    private Rect RowBounds(int row) {
        double[] grid = GridBounds();
        if (grid == null) {
            return null;
        }
        double rowHeight = (grid[3] - grid[1]) / ROWS;
        double top = grid[1] + row * rowHeight;
        return new Rect(grid[0], top, grid[2], top + rowHeight);
    }

    private Rect DayBounds(int row, int column) {
        double[] grid = GridBounds();
        if (grid == null) {
            return null;
        }
        double cellWidth = (grid[2] - grid[0]) / COLUMNS;
        double cellHeight = (grid[3] - grid[1]) / ROWS;
        double left = grid[0] + column * cellWidth;
        double top = grid[1] + row * cellHeight;
        return new Rect(left, top, left + cellWidth, top + cellHeight);
    }

    /*
        The current day's cell, while the calendar has the focus (this is
        only asked of the focused kit), or the calendar itself if the
        current day isn't shown.
    */
    @Override
    public NodeId GetFocusTarget() {
        Calendar_ calendar = GetCalendar();
        if (calendar == null || firstShownDay == Long.MIN_VALUE) {
            return GetNodeID();
        }
        CalendarDate_ first = calendar.GetShownDay(0, 0);
        CalendarDate_ current = calendar.GetCurrentDay();
        if (first == null || current == null) {
            return GetNodeID();
        }
        int index = first.DaysUntil(current);
        if (index < 0 || index >= ROWS * COLUMNS) {
            return GetNodeID();
        }
        return DayID(index / COLUMNS, index % COLUMNS);
    }

    /*
        A request for one of the days (MacAccessibility.RunPendingActions).
        CLICK chooses the day, as clicking it does: the calendar takes the
        focus, the current day moves there and is chosen, unless the day
        can't be chosen. FOCUS moves the current day there, so "keyboard
        focus follows VoiceOver cursor" works on the days. A request for a
        day of a set no longer shown is dropped.
    */
    @Override
    public void DoInternalChildAction(ActionRequest request) {
        Calendar_ calendar = GetCalendar();
        if (calendar == null || request.action == null) {
            return;
        }
        int index = DayIndex(request.target);
        if (index < 0) {
            return;
        }
        CalendarDate_ target = calendar.GetShownDay(index / COLUMNS, index % COLUMNS);
        CalendarDate_ current = calendar.GetCurrentDay();
        if (target == null || current == null) {
            return;
        }
        if (request.action == Action.CLICK) {
            if (!calendar.IsAvailable(target)) {
                return;
            }
            if (calendar.IsFocusable() && !calendar.IsFocused()) {
                calendar.Focus();
            }
            calendar.MoveCurrentDay(current.DaysUntil(target));
            calendar.ChooseCurrentDay();
        } else if (request.action == Action.FOCUS) {
            if (calendar.IsFocusable() && !calendar.IsFocused()) {
                calendar.Focus();
            }
            calendar.MoveCurrentDay(current.DaysUntil(target));
        }
    }
}
