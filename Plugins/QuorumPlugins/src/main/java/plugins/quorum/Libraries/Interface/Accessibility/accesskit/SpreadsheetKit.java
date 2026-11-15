package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import quorum.Libraries.Interface.Controls.Spreadsheet_;
import quorum.Libraries.Interface.Selections.SpreadsheetSelection_;
import quorum.Libraries.Interface.Item_;

/*
    A spreadsheet: a grid (AXTable) named by the spreadsheet's name, such as
    its file's name. Its cells are not items: the spreadsheet draws them, and
    this kit makes their nodes from its values, as CalendarKit does for days.
    Only what is on screen gets nodes: a header row of COLUMN_HEADER nodes
    (the column letters), then a ROW node per visible row, each starting with
    a ROW_HEADER node (the row number) and then a GRID_CELL node per visible
    column. A cell's label is its text, or "blank", and its role description
    is its name in place of "cell", so VoiceOver says "71, C2"; the current
    cell of a range adds the range ("48, E5, D3 through E5 selected"). Row
    numbers and column letters say "2, row" and "C, column", with ", selected"
    when the whole row or column is. AccessKit tells VoiceOver nothing about
    the selected state of cells and headers, so it is in the words. While the
    spreadsheet has the focus, AccessKit's focus is on the current cell's node,
    or on the current row's number or column's letters when whole rows or
    columns are selected (GetFocusTarget), so VoiceOver follows the keys.

    AccessKit's macOS adapter gives a grid no table facts (row and column
    counts, indexes, headers, selected cells), so Mac.so adds them from the
    facts sent here (SendExtras, SendFacts). VoiceOver follows a table
    through its selected cells, as for a native table, so the grid tells it
    when the selection moves (SELECTED_CELLS_CHANGED), and VoiceOver
    choosing cells or a row moves the current cell (Mac.so turns it into
    FOCUS on that cell, DoInternalChildAction).

    A cell's node id comes from its row and column, not its place on screen,
    so it stays the same node while the grid scrolls. Ids: (kit id << 32) |
    number, where the number's top two bits say what the node is (cell,
    row, column header, row header), then 18 bits of the row and 12 of the
    column. Rows 262144 apart share ids, but never both on screen.

    The edit field, while a cell is edited, is an item of the spreadsheet,
    with its own kit (TextFieldKit), added after the rows.
*/
public class SpreadsheetKit extends ItemKit {
    private static final long KIND_CELL = 0L;
    private static final long KIND_ROW = 1L;
    private static final long KIND_COLUMN_HEADER = 2L;
    private static final long KIND_ROW_HEADER = 3L;
    private static final long ROW_MASK = 0x3FFFFL;
    private static final long COLUMN_MASK = 0xFFFL;
    private static final long NUMBER_MASK = 0xFFFFFFFFL;
    // The header row's number: a column header's kind with every other bit set.
    private static final long HEADER_ROW = (KIND_COLUMN_HEADER << 30) | 0x3FFFFFFFL;

    // The internal nodes Mac.so holds table facts for, so those that
    // scrolled away are forgotten (SendExtras).
    private HashSet<Long> factsSent = new HashSet<Long>();
    private HashSet<Long> factsNow = new HashSet<Long>();

    // The selection when the grid was last built, to tell VoiceOver when it
    // moves (SendExtras), or null before the first build.
    private String sentSelection = null;

    public SpreadsheetKit() {
        SetRole(Role.GRID);
    }

    /*
        The grid's table facts (Mac.so): AccessKit gives VoiceOver a grid's
        rows only when they are list-like, and no table facts at all, so
        without these VoiceOver's keys find nothing to move to. The rows',
        cells' and headers' facts are sent as they are built
        (BuildInternalChild); those sent last time and not this time are
        forgotten here, before the next build.
    */
    @Override
    public void SendExtras() {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet != null) {
            MacAccessibilityExtras.setInfo(GetNodeID(), MacAccessibilityExtras.TABLE | GetMenuFlag(),
                sheet.GetDisplayedColumnSize(), -1, sheet.GetDisplayedRowSize(), 0, null);
        } else {
            super.SendExtras();
        }
        // VoiceOver follows a table through its selected cells, as for a
        // native table, so it is told when the selection or current cell
        // moves; the cells' selected and current facts are sent as they
        // are built, before the notice is posted after the update.
        String selection = DescribeSelection(sheet);
        if (sentSelection != null && !sentSelection.equals(selection)) {
            MacAccessibilityExtras.queueNotice(GetNodeID(), null, MacAccessibilityExtras.SELECTED_CELLS_CHANGED);
        }
        sentSelection = selection;
        for (Long id : factsSent) {
            if (!factsNow.contains(id)) {
                MacAccessibilityExtras.remove(new NodeId(id));
            }
        }
        factsSent = factsNow;
        factsNow = new HashSet<Long>();
    }

    // The selection as text, to compare with the last one sent.
    private String DescribeSelection(Spreadsheet_ sheet) {
        if (sheet == null) {
            return "";
        }
        SpreadsheetSelection_ selection = sheet.GetSelection();
        if (selection == null || selection.IsEmpty()) {
            return "";
        }
        return selection.GetKind() + ":" + selection.GetRow() + "," + selection.GetColumn() + ":"
            + selection.GetFirstRow() + "," + selection.GetFirstColumn() + ":"
            + selection.GetLastRow() + "," + selection.GetLastColumn();
    }

    // Sends one internal node's table facts and remembers it was sent.
    private void SendFacts(NodeId id, int flags, int index, int position) {
        MacAccessibilityExtras.setInfo(id, flags, 0, index, -1, position, null);
        factsNow.add(id.value);
        factsSent.add(id.value);
    }

    private Spreadsheet_ GetSpreadsheet() {
        Item_ item = GetItem();
        return item instanceof Spreadsheet_ ? (Spreadsheet_) item : null;
    }

    /*
        Called when the spreadsheet changed, just before its node is marked
        to be built again. The ids don't depend on what is on screen, so
        nothing needs to change here; it is kept for MacAccessibility, as
        CalendarKit.SyncDays is.
    */
    public void SyncVisible() {
    }

    private long Base() {
        return GetNodeID().value << 32;
    }

    private NodeId CellID(int row, int column) {
        long number = (KIND_CELL << 30) | ((row & ROW_MASK) << 12) | (column & COLUMN_MASK);
        return new NodeId(Base() | number);
    }

    private NodeId RowID(int row) {
        long number = (KIND_ROW << 30) | ((row & ROW_MASK) << 12);
        return new NodeId(Base() | number);
    }

    private NodeId HeaderRowID() {
        return new NodeId(Base() | HEADER_ROW);
    }

    private NodeId ColumnHeaderID(int column) {
        long number = (KIND_COLUMN_HEADER << 30) | (column & COLUMN_MASK);
        return new NodeId(Base() | number);
    }

    private NodeId RowHeaderID(int row) {
        long number = (KIND_ROW_HEADER << 30) | ((row & ROW_MASK) << 12);
        return new NodeId(Base() | number);
    }

    // The number part of an id of this kit, or -1 if the id isn't this kit's.
    private long Number(NodeId id) {
        if ((id.value >>> 32) != GetNodeID().value) {
            return -1;
        }
        return id.value & NUMBER_MASK;
    }

    // The visible row whose low bits match, or -1.
    private int FindRow(long rowBits, Spreadsheet_ sheet) {
        int first = sheet.GetFirstVisibleRow();
        int last = sheet.GetLastVisibleRow();
        for (int row = first; row <= last; row++) {
            if ((row & ROW_MASK) == rowBits) {
                return row;
            }
        }
        return -1;
    }

    // The visible column whose low bits match, or -1.
    private int FindColumn(long columnBits, Spreadsheet_ sheet) {
        int first = sheet.GetFirstVisibleColumn();
        int last = sheet.GetLastVisibleColumn();
        for (int column = first; column <= last; column++) {
            if ((column & COLUMN_MASK) == columnBits) {
                return column;
            }
        }
        return -1;
    }

    @Override
    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if (item != null) {
            builder.setBounds(GetBoundingRectangle());
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    /*
        The header row and its column headers, then each visible row with
        its row header and cells.
    */
    @Override
    public Iterable<NodeId> GetInternalChildren() {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet == null) {
            return Collections.emptySet();
        }
        int firstRow = sheet.GetFirstVisibleRow();
        int lastRow = sheet.GetLastVisibleRow();
        int firstColumn = sheet.GetFirstVisibleColumn();
        int lastColumn = sheet.GetLastVisibleColumn();
        ArrayList<NodeId> ids = new ArrayList<NodeId>();
        ids.add(HeaderRowID());
        for (int column = firstColumn; column <= lastColumn; column++) {
            ids.add(ColumnHeaderID(column));
        }
        for (int row = firstRow; row <= lastRow; row++) {
            ids.add(RowID(row));
            ids.add(RowHeaderID(row));
            for (int column = firstColumn; column <= lastColumn; column++) {
                ids.add(CellID(row, column));
            }
        }
        return ids;
    }

    /*
        Everything on screen is built again with the spreadsheet: any change
        can scroll, change values, or move the selection.
    */
    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        return GetInternalChildren();
    }

    /*
        The grid's children are the header row and the visible rows; then the
        spreadsheet's own items (the edit field, while editing).
    */
    @Override
    protected void BuildChildren(Node builder) {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet != null) {
            builder.addChild(HeaderRowID());
            int firstRow = sheet.GetFirstVisibleRow();
            int lastRow = sheet.GetLastVisibleRow();
            for (int row = firstRow; row <= lastRow; row++) {
                builder.addChild(RowID(row));
            }
        }
        for (ItemKit child : GetChildrenCopy()) {
            builder.addChild(child.GetNodeID());
        }
    }

    @Override
    public Node BuildInternalChild(NodeId id) {
        Spreadsheet_ sheet = GetSpreadsheet();
        long number = Number(id);
        if (number < 0) {
            throw new IllegalArgumentException("unexpected child ID");
        }
        if (sheet == null) {
            return new Node(Role.GRID_CELL);
        }
        int firstColumn = sheet.GetFirstVisibleColumn();
        int lastColumn = sheet.GetLastVisibleColumn();
        double[] grid = Grid(sheet);

        if (number == HEADER_ROW) {
            Node node = new Node(Role.ROW);
            if (grid != null) {
                node.setBounds(new Rect(grid[0], grid[1], grid[0] + sheet.GetWidth() * grid[2], grid[1] + sheet.GetHeaderHeightInPixels() * grid[3]));
            }
            for (int column = firstColumn; column <= lastColumn; column++) {
                node.addChild(ColumnHeaderID(column));
            }
            SendFacts(id, MacAccessibilityExtras.TABLE_HEADER_ROW, -1, -1);
            return node;
        }

        long kind = (number >>> 30) & 3L;
        long rowBits = (number >>> 12) & ROW_MASK;
        long columnBits = number & COLUMN_MASK;

        if (kind == KIND_COLUMN_HEADER) {
            int column = FindColumn(columnBits, sheet);
            Node node = new Node(Role.COLUMN_HEADER);
            if (column >= 0) {
                node.setLabel(sheet.GetColumnLabel(column));
                // "C, column", or "C, column, selected" (VoiceOver isn't told
                // a cell or header is selected, so it is in the words).
                boolean whole = IsWholeColumnSelected(sheet, column);
                node.setRoleDescription(whole ? "column, selected" : "column");
                node.setSelected(whole);
                SendFacts(id, MacAccessibilityExtras.TABLE_CELL | MacAccessibilityExtras.COLUMN_HEADER
                    | (whole ? MacAccessibilityExtras.TABLE_SELECTED : 0), -1, column);
                if (grid != null) {
                    double left = grid[0] + sheet.GetCellLeft(column) * grid[2];
                    double width = sheet.GetColumnWidthInPixels(column) * grid[2];
                    node.setBounds(new Rect(left, grid[1], left + width, grid[1] + sheet.GetHeaderHeightInPixels() * grid[3]));
                }
                node.addAction(Action.CLICK);
            }
            return node;
        }

        int row = FindRow(rowBits, sheet);
        if (kind == KIND_ROW) {
            Node node = new Node(Role.ROW);
            if (row >= 0) {
                if (grid != null) {
                    double top = grid[1] + sheet.GetCellTop(row) * grid[3];
                    node.setBounds(new Rect(grid[0], top, grid[0] + sheet.GetWidth() * grid[2], top + sheet.GetRowHeightInPixels() * grid[3]));
                }
                node.addChild(RowHeaderID(row));
                for (int column = firstColumn; column <= lastColumn; column++) {
                    node.addChild(CellID(row, column));
                }
                SendFacts(id, MacAccessibilityExtras.TABLE_ROW
                    | (IsWholeRowSelected(sheet, row) ? MacAccessibilityExtras.TABLE_SELECTED : 0), row, -1);
            }
            return node;
        }

        if (kind == KIND_ROW_HEADER) {
            Node node = new Node(Role.ROW_HEADER);
            if (row >= 0) {
                node.setLabel("" + (row + 1));
                // "2, row", or "2, row, selected".
                boolean whole = IsWholeRowSelected(sheet, row);
                node.setRoleDescription(whole ? "row, selected" : "row");
                node.setSelected(whole);
                SendFacts(id, MacAccessibilityExtras.ROW_HEADER, row, -1);
                if (grid != null) {
                    double top = grid[1] + sheet.GetCellTop(row) * grid[3];
                    node.setBounds(new Rect(grid[0], top, grid[0] + sheet.GetRowLabelWidthInPixels() * grid[2], top + sheet.GetRowHeightInPixels() * grid[3]));
                }
                node.addAction(Action.CLICK);
            }
            return node;
        }

        Node node = new Node(Role.GRID_CELL);
        int column = FindColumn(columnBits, sheet);
        if (row >= 0 && column >= 0) {
            // The text, or "blank"; the cell's name, such as "C2", in place
            // of the role ("cell"). The current cell of a range also says
            // the range: "E5, D3 through E5 selected".
            String text = sheet.GetText(row, column);
            node.setLabel(text == null || text.isEmpty() ? "blank" : text);
            SpreadsheetSelection_ selection = sheet.GetSelection();
            String role = sheet.GetCellName(row, column);
            if (selection != null && !selection.IsEmpty() && selection.IsRange()
                    && selection.GetKind() == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__CELLS_()
                    && selection.GetRow() == row && selection.GetColumn() == column) {
                role = role + ", " + sheet.GetCellName(selection.GetFirstRow(), selection.GetFirstColumn())
                        + " through " + sheet.GetCellName(selection.GetLastRow(), selection.GetLastColumn()) + " selected";
            }
            node.setRoleDescription(role);
            boolean selected = selection != null && !selection.IsEmpty() && selection.Contains(row, column);
            boolean current = selection != null && !selection.IsEmpty()
                && selection.GetRow() == row && selection.GetColumn() == column;
            node.setSelected(selected);
            int flags = MacAccessibilityExtras.TABLE_CELL;
            if (selected || current) {
                flags = flags | MacAccessibilityExtras.TABLE_SELECTED;
            }
            if (current) {
                flags = flags | MacAccessibilityExtras.TABLE_CURRENT;
            }
            SendFacts(id, flags, row, column);
            if (grid != null) {
                double left = grid[0] + sheet.GetCellLeft(column) * grid[2];
                double top = grid[1] + sheet.GetCellTop(row) * grid[3];
                node.setBounds(new Rect(left, top, left + sheet.GetColumnWidthInPixels(column) * grid[2], top + sheet.GetRowHeightInPixels() * grid[3]));
            }
            node.addAction(Action.FOCUS);
            node.addAction(Action.CLICK);
        }
        return node;
    }

    /*
        The spreadsheet's top left in AccessKit's coordinates (from the top
        left of the window), and how many of those units a pixel of the
        spreadsheet is across and down: left, top, scale across, scale down.
        Null if the spreadsheet has no area.
    */
    private double[] Grid(Spreadsheet_ sheet) {
        Rect rect = GetBoundingRectangle();
        if (IsEmpty(rect) || sheet.GetWidth() <= 0 || sheet.GetHeight() <= 0) {
            return null;
        }
        double scaleX = (rect.x1 - rect.x0) / sheet.GetWidth();
        double scaleY = (rect.y1 - rect.y0) / sheet.GetHeight();
        return new double[] { rect.x0, rect.y0, scaleX, scaleY };
    }

    // True if whole rows are selected, or everything, and the row is among them.
    private boolean IsWholeRowSelected(Spreadsheet_ sheet, int row) {
        SpreadsheetSelection_ selection = sheet.GetSelection();
        if (selection == null || selection.IsEmpty()) {
            return false;
        }
        int kind = selection.GetKind();
        if (kind == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__EVERYTHING_()) {
            return true;
        }
        return kind == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__ROWS_()
                && row >= selection.GetFirstRow() && row <= selection.GetLastRow();
    }

    // True if whole columns are selected, or everything, and the column is among them.
    private boolean IsWholeColumnSelected(Spreadsheet_ sheet, int column) {
        SpreadsheetSelection_ selection = sheet.GetSelection();
        if (selection == null || selection.IsEmpty()) {
            return false;
        }
        int kind = selection.GetKind();
        if (kind == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__EVERYTHING_()) {
            return true;
        }
        return kind == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__COLUMNS_()
                && column >= selection.GetFirstColumn() && column <= selection.GetLastColumn();
    }

    /*
        The current cell's node while the spreadsheet has the focus (this is
        only asked of the focused kit), or the grid if the current cell isn't
        on screen.
    */
    @Override
    public NodeId GetFocusTarget() {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet == null) {
            return GetNodeID();
        }
        SpreadsheetSelection_ selection = sheet.GetSelection();
        if (selection == null || selection.IsEmpty()) {
            return GetNodeID();
        }
        int row = selection.GetRow();
        int column = selection.GetColumn();
        boolean rowVisible = row >= sheet.GetFirstVisibleRow() && row <= sheet.GetLastVisibleRow();
        boolean columnVisible = column >= sheet.GetFirstVisibleColumn() && column <= sheet.GetLastVisibleColumn();
        // Whole rows selected: the current row's number ("2, row, selected");
        // whole columns: the current column's letters.
        if (IsWholeRowSelected(sheet, row) && selection.GetKind() == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__ROWS_()) {
            return rowVisible ? RowHeaderID(row) : GetNodeID();
        }
        if (IsWholeColumnSelected(sheet, column) && selection.GetKind() == selection.Get_Libraries_Interface_Selections_SpreadsheetSelection__COLUMNS_()) {
            return columnVisible ? ColumnHeaderID(column) : GetNodeID();
        }
        if (!rowVisible || !columnVisible) {
            return GetNodeID();
        }
        return CellID(row, column);
    }

    /*
        A request for a cell or a header (MacAccessibility.RunPendingActions).
        FOCUS or CLICK on a cell makes it current, so "keyboard focus follows
        VoiceOver cursor" works. CLICK on a column letter or row number
        selects the column or row, as clicking it does.
    */
    @Override
    public void DoInternalChildAction(ActionRequest request) {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet == null || request.action == null) {
            return;
        }
        long number = Number(request.target);
        if (number < 0 || number == HEADER_ROW) {
            return;
        }
        long kind = (number >>> 30) & 3L;
        long rowBits = (number >>> 12) & ROW_MASK;
        long columnBits = number & COLUMN_MASK;
        if (sheet.IsFocusable() && !sheet.IsFocused()) {
            sheet.Focus();
        }
        if (kind == KIND_COLUMN_HEADER) {
            int column = FindColumn(columnBits, sheet);
            if (column >= 0 && request.action == Action.CLICK) {
                sheet.SelectColumns(column, column);
            }
            return;
        }
        int row = FindRow(rowBits, sheet);
        if (row < 0) {
            return;
        }
        if (kind == KIND_ROW_HEADER) {
            if (request.action == Action.CLICK) {
                sheet.SelectRows(row, row);
            }
            return;
        }
        if (kind == KIND_CELL) {
            int column = FindColumn(columnBits, sheet);
            if (column >= 0 && (request.action == Action.CLICK || request.action == Action.FOCUS)) {
                sheet.MoveTo(row, column, false);
            }
        }
    }
}
