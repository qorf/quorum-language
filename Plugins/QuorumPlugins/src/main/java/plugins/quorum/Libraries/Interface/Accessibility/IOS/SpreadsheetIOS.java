package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerDataTable;
import org.robovm.apple.uikit.UIAccessibilityContainerDataTableCell;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;
import org.robovm.apple.uikit.UIAccessibilityScrollDirection;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Game.IOSDisplay;
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Interface.Controls.Spreadsheet_;
import quorum.Libraries.Interface.Selections.SpreadsheetSelection_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    A spreadsheet (Quorum's TABLE code on a Spreadsheet) is a container of
    type DataTable, named by the spreadsheet's name, such as its file's name.
    It is not an element itself. Its cells are not Quorum items: the
    spreadsheet draws them, and they are made here from its values, as
    CalendarIOS makes its days. Only what is on screen gets elements, in
    reading order: the column letters (ColumnHeader), then each visible row,
    its number first (RowHeader), then its cells (Cell).

    iOS has no role description, so each part's words for its role are in
    its label, as the Mac's role descriptions say them: a cell reads its
    text, or "blank", then Spreadsheet:GetCellRoleText ("71, C2"; the
    current cell of a range also says the range, "E5, D3 through E5
    selected"); a row's number reads "2, row" and a column's letters "C,
    column". Each is Selected while the selection holds it (a header while
    its whole row or column is), which VoiceOver says itself. A cell gives
    VoiceOver its row and
    column (UIAccessibilityContainerDataTableCell), so VoiceOver can say
    them and move by row and column. VoiceOver landing on a cell makes it
    the current cell; a double tap on the current cell edits it (F2). A
    three-finger swipe moves the current cell by a screenful, which scrolls.

    Elements are kept by row and column, not by place on screen, so
    VoiceOver stays on the same element while the grid scrolls. Rebuild
    (from IOSAccessibility.NativeSpreadsheetChanged) makes the elements for
    what is on screen now; each element reads the spreadsheet whenever
    VoiceOver asks.

    The edit field, while a cell is edited, is an item of the spreadsheet
    with its own element (TextFieldIOS) in the container the spreadsheet is
    in, right after it.
 */
public class SpreadsheetIOS extends ItemIOS implements UIAccessibilityContainer, UIAccessibilityContainerDataTable {
    private NSMutableArray<UIAccessibilityElement> items = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    // The elements on screen, by row and column (CellKey).
    private Map<Long, Cell> cells = new HashMap<>();
    private Map<Integer, ColumnHeader> columnHeaders = new HashMap<>();
    private Map<Integer, RowHeader> rowHeaders = new HashMap<>();

    public SpreadsheetIOS(UIAccessibilityContainer container) {
        super(container);
    }

    public Spreadsheet_ GetSpreadsheet() {
        return (Spreadsheet_) GetItem();
    }

    @Override
    public void Initialize(quorum.Libraries.Interface.Item_ item) {
        super.Initialize(item);
        Rebuild();
    }

    private static long CellKey(int row, int column) {
        return (((long) row) << 32) | (column & 0xFFFFFFFFL);
    }

    /*
        Makes the elements for the cells and headers on screen now, keeping
        those still on screen, and puts them in reading order. Elements that
        left the screen are dropped (VoiceOver no longer on them). Returns
        true if the elements on screen changed.
     */
    public boolean Rebuild() {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet == null) {
            return false;
        }
        int firstRow = sheet.GetFirstVisibleRow();
        int lastRow = sheet.GetLastVisibleRow();
        int firstColumn = sheet.GetFirstVisibleColumn();
        int lastColumn = sheet.GetLastVisibleColumn();
        if (sheet.GetDisplayedRowSize() <= 0 || sheet.GetDisplayedColumnSize() <= 0) {
            lastRow = firstRow - 1;
            lastColumn = firstColumn - 1;
        }

        Map<Long, Cell> newCells = new HashMap<>();
        Map<Integer, ColumnHeader> newColumnHeaders = new HashMap<>();
        Map<Integer, RowHeader> newRowHeaders = new HashMap<>();
        List<UIAccessibilityElement> order = new ArrayList<>();
        String name = sheet.GetName();

        for (int column = firstColumn; column <= lastColumn; column++) {
            ColumnHeader header = columnHeaders.get(column);
            if (header == null) {
                header = new ColumnHeader(this, column);
                header.Initialize(sheet);
                header.setAccessibilityIdentifier(name + " column " + sheet.GetColumnLabel(column));
            }
            newColumnHeaders.put(column, header);
            order.add(header);
        }
        for (int row = firstRow; row <= lastRow; row++) {
            RowHeader header = rowHeaders.get(row);
            if (header == null) {
                header = new RowHeader(this, row);
                header.Initialize(sheet);
                header.setAccessibilityIdentifier(name + " row " + (row + 1));
            }
            newRowHeaders.put(row, header);
            order.add(header);
            for (int column = firstColumn; column <= lastColumn; column++) {
                long key = CellKey(row, column);
                Cell cell = cells.get(key);
                if (cell == null) {
                    cell = new Cell(this, row, column);
                    cell.Initialize(sheet);
                    cell.setAccessibilityIdentifier(name + " " + sheet.GetCellName(row, column));
                }
                newCells.put(key, cell);
                order.add(cell);
            }
        }

        boolean changed = newCells.size() != cells.size() || newColumnHeaders.size() != columnHeaders.size()
                || newRowHeaders.size() != rowHeaders.size();
        for (Map.Entry<Long, Cell> entry : cells.entrySet()) {
            if (!newCells.containsKey(entry.getKey())) {
                ItemIOS.ClearVoiceOverFocus(entry.getValue());
                changed = true;
            }
        }
        for (Map.Entry<Integer, ColumnHeader> entry : columnHeaders.entrySet()) {
            if (!newColumnHeaders.containsKey(entry.getKey())) {
                ItemIOS.ClearVoiceOverFocus(entry.getValue());
                changed = true;
            }
        }
        for (Map.Entry<Integer, RowHeader> entry : rowHeaders.entrySet()) {
            if (!newRowHeaders.containsKey(entry.getKey())) {
                ItemIOS.ClearVoiceOverFocus(entry.getValue());
                changed = true;
            }
        }
        cells = newCells;
        columnHeaders = newColumnHeaders;
        rowHeaders = newRowHeaders;

        items.clear();
        items.addAll(order);
        return changed;
    }

    // Every cell and header element on screen, for clearing VoiceOver's
    // place when the spreadsheet is removed.
    public List<ItemIOS> GetParts() {
        List<ItemIOS> parts = new ArrayList<>();
        parts.addAll(columnHeaders.values());
        parts.addAll(rowHeaders.values());
        parts.addAll(cells.values());
        return parts;
    }

    // Whether an element is one of this spreadsheet's cells or headers now.
    public boolean HasPart(ItemIOS element) {
        if (element instanceof Cell) {
            Cell cell = (Cell) element;
            return cells.get(CellKey(cell.row, cell.column)) == cell;
        }
        if (element instanceof ColumnHeader) {
            return columnHeaders.get(((ColumnHeader) element).column) == element;
        }
        if (element instanceof RowHeader) {
            return rowHeaders.get(((RowHeader) element).row) == element;
        }
        return false;
    }

    // The element of a cell, or null if it isn't on screen.
    public Cell GetCellElement(int row, int column) {
        return cells.get(CellKey(row, column));
    }

    /*
        Where VoiceOver goes for the spreadsheet
        (Spreadsheet:GetAccessibilityFocusPart): the current row's number
        while whole rows are selected, the current column's letters while
        whole columns are, otherwise the current cell. If that isn't on
        screen (or there is none), the first element on screen, or null.
     */
    public UIAccessibilityElement GetCurrentCellElement() {
        Spreadsheet_ sheet = GetSpreadsheet();
        SpreadsheetSelection_ selection = sheet == null ? null : sheet.GetSelection();
        if (selection != null && !selection.IsEmpty()) {
            int part = sheet.GetAccessibilityFocusPart();
            if (part == 2) {
                RowHeader header = rowHeaders.get(selection.GetRow());
                if (header != null) {
                    return header;
                }
            } else if (part == 3) {
                ColumnHeader header = columnHeaders.get(selection.GetColumn());
                if (header != null) {
                    return header;
                }
            }
            Cell cell = GetCellElement(selection.GetRow(), selection.GetColumn());
            if (cell != null) {
                return cell;
            }
        }
        if (sheet != null && !cells.isEmpty()) {
            Cell first = GetCellElement(sheet.GetFirstVisibleRow(), sheet.GetFirstVisibleColumn());
            if (first != null) {
                return first;
            }
        }
        return items.isEmpty() ? null : items.get(0);
    }

    // ItemIOS.RefreshText gives the container the spreadsheet's name (and
    // its description as a hint), which VoiceOver says entering the table.

    /*
        The frame of a part of the spreadsheet, from its pixels: left and top
        from the spreadsheet's top left (after scrolling), as Spreadsheet's
        GetCellLeft and GetCellTop give them. The part is clipped to the
        area from clipLeft and clipTop (in the same pixels) to the
        spreadsheet's right and bottom edges, so a cell partly under the
        column letters or row numbers covers only what shows.
     */
    CGRect GetPartFrame(double left, double top, double width, double height, double clipLeft, double clipTop) {
        CGRect whole = getAccessibilityFrame();
        Spreadsheet_ sheet = GetSpreadsheet();
        double sheetWidth = sheet.GetWidth();
        double sheetHeight = sheet.GetHeight();
        if (sheetWidth <= 0 || sheetHeight <= 0) {
            return new CGRect(whole.getX(), whole.getY(), 0, 0);
        }
        double scaleX = whole.getWidth() / sheetWidth;
        double scaleY = whole.getHeight() / sheetHeight;

        double x0 = Math.max(left, clipLeft);
        double y0 = Math.max(top, clipTop);
        double x1 = Math.min(left + width, sheetWidth);
        double y1 = Math.min(top + height, sheetHeight);
        if (x1 < x0) {
            x1 = x0;
        }
        if (y1 < y0) {
            y1 = y0;
        }
        // UIKit's frames count down from the top, as these pixels do.
        return new CGRect(whole.getX() + x0 * scaleX, whole.getY() + y0 * scaleY,
                (x1 - x0) * scaleX, (y1 - y0) * scaleY);
    }

    CGRect GetCellFrame(int row, int column) {
        Spreadsheet_ sheet = GetSpreadsheet();
        return GetPartFrame(sheet.GetCellLeft(column), sheet.GetCellTop(row),
                sheet.GetColumnWidthInPixels(column), sheet.GetRowHeightInPixels(),
                sheet.GetRowLabelWidthInPixels(), sheet.GetHeaderHeightInPixels());
    }

    CGRect GetColumnHeaderFrame(int column) {
        Spreadsheet_ sheet = GetSpreadsheet();
        return GetPartFrame(sheet.GetCellLeft(column), 0,
                sheet.GetColumnWidthInPixels(column), sheet.GetHeaderHeightInPixels(),
                sheet.GetRowLabelWidthInPixels(), 0);
    }

    CGRect GetRowHeaderFrame(int row) {
        Spreadsheet_ sheet = GetSpreadsheet();
        return GetPartFrame(0, sheet.GetCellTop(row),
                sheet.GetRowLabelWidthInPixels(), sheet.GetRowHeightInPixels(),
                0, sheet.GetHeaderHeightInPixels());
    }

    /*
        VoiceOver moved onto a part of the spreadsheet: the spreadsheet takes
        Quorum's focus, as a touch gives it. Not while a cell is edited: the
        edit field keeps it.
     */
    void FocusSpreadsheet() {
        Spreadsheet_ sheet = GetSpreadsheet();
        if (sheet == null || sheet.IsEditing()) {
            return;
        }
        if (!sheet.IsFocused()) {
            sheet.Focus();
        }
    }

    @Override
    public boolean isAccessibilityElement() {
        return false;
    }

    @Override
    public NSMutableArray<UIAccessibilityElement> getAccessibilityElements() {
        return items;
    }

    // As in IOSAccessibilityContainer: the array must stay an NSMutableArray.
    @Override
    public void setAccessibilityElements(NSArray<UIAccessibilityElement> v) {
        items = (NSMutableArray<UIAccessibilityElement>) v;
    }

    @Override
    public UIAccessibilityContainerType getAccessibilityContainerType() {
        return UIAccessibilityContainerType.DataTable;
    }

    @Override
    public void setAccessibilityContainerType(UIAccessibilityContainerType v) {
    }

    @Override
    public long getAccessibilityElementCount() {
        return items.size();
    }

    @Override
    public UIAccessibilityElement getAccessibilityElement(long index) {
        return items.get((int) index);
    }

    @Override
    public long indexOfAccessibilityElement(UIAccessibilityElement element) {
        return items.indexOf(element);
    }

    @Override
    public NSArray<?> getAutomationElements() {
        if (automationElements != null)
            return automationElements;
        return items;
    }

    @Override
    public void setAutomationElements(NSArray<?> v) {
        automationElements = v;
    }

    // UIAccessibilityContainerDataTable: the whole spreadsheet's size, so
    // VoiceOver's row and column numbers are the spreadsheet's own.

    @Override
    public UIAccessibilityContainerDataTableCell provideElement(long row, long column) {
        if (row > Integer.MAX_VALUE || column > Integer.MAX_VALUE) {
            return null;
        }
        return GetCellElement((int) row, (int) column);
    }

    @Override
    public long accessibilityRowCount() {
        Spreadsheet_ sheet = GetSpreadsheet();
        return sheet == null ? 0 : Math.max(0, sheet.GetDisplayedRowSize());
    }

    @Override
    public long accessibilityColumnCount() {
        Spreadsheet_ sheet = GetSpreadsheet();
        return sheet == null ? 0 : Math.max(0, sheet.GetDisplayedColumnSize());
    }

    @Override
    public NSArray<?> accessibilityHeaderElementsForRow(long row) {
        NSMutableArray<UIAccessibilityElement> headers = new NSMutableArray<>();
        RowHeader header = row > Integer.MAX_VALUE ? null : rowHeaders.get((int) row);
        if (header != null) {
            headers.add(header);
        }
        return headers;
    }

    @Override
    public NSArray<?> accessibilityHeaderElementsForColumn(long column) {
        NSMutableArray<UIAccessibilityElement> headers = new NSMutableArray<>();
        ColumnHeader header = column > Integer.MAX_VALUE ? null : columnHeaders.get((int) column);
        if (header != null) {
            headers.add(header);
        }
        return headers;
    }

    /*
        One cell on screen. Its label is its text, or "blank", then its name
        (Spreadsheet:GetCellRoleText), as "71, C2"; it is Selected while the
        selection holds it.
     */
    public static class Cell extends ItemIOS implements UIAccessibilityContainerDataTableCell {
        private final SpreadsheetIOS table;
        final int row;
        final int column;

        Cell(SpreadsheetIOS table, int row, int column) {
            super(table);
            this.table = table;
            this.row = row;
            this.column = column;
        }

        public int GetRow() {
            return row;
        }

        public int GetColumn() {
            return column;
        }

        Spreadsheet_ GetSpreadsheet() {
            return (Spreadsheet_) GetItem();
        }

        boolean IsCurrent() {
            SpreadsheetSelection_ selection = GetSpreadsheet().GetSelection();
            return selection != null && !selection.IsEmpty()
                    && selection.GetRow() == row && selection.GetColumn() == column;
        }

        @Override
        public void RefreshText() {
            setAccessibilityLabel(null);
            setAccessibilityValue(null);
            setAccessibilityHint(null);
        }

        @Override
        public String getAccessibilityLabel() {
            Spreadsheet_ sheet = GetSpreadsheet();
            String text = sheet.GetText(row, column);
            if (text == null || text.isEmpty()) {
                text = "blank";
            }
            return text + ", " + sheet.GetCellRoleText(row, column);
        }

        @Override
        public String getAccessibilityValue() {
            return null;
        }

        @Override
        public String getAccessibilityHint() {
            return null;
        }

        @Override
        public UIAccessibilityTraits getAccessibilityTraits() {
            SpreadsheetSelection_ selection = GetSpreadsheet().GetSelection();
            long traits = UIAccessibilityTraits.None.value();
            if (selection != null && !selection.IsEmpty() && selection.Contains(row, column)) {
                traits = traits | UIAccessibilityTraits.Selected.value();
            }
            return new UIAccessibilityTraits(traits);
        }

        @Override
        public CGRect getAccessibilityFrame() {
            return table.GetCellFrame(row, column);
        }

        @Override
        public NSRange accessibilityRowRange() {
            return new NSRange(row, 1);
        }

        @Override
        public NSRange accessibilityColumnRange() {
            return new NSRange(column, 1);
        }

        /*
            VoiceOver moved here: the spreadsheet takes the focus and this
            becomes the current cell, so the focus ring follows VoiceOver.
            Not while a cell is edited.
         */
        @Override
        public void didBecomeFocused() {
            focused = true;
            voiceOverFocus = this;
            if (IOSAccessibility.debug) {
                System.out.println("VoiceOver moved to spreadsheet cell \"" + getAccessibilityLabel() + "\"");
            }
            Spreadsheet_ sheet = GetSpreadsheet();
            if (sheet.IsEditing()) {
                return;
            }
            table.FocusSpreadsheet();
            if (!IsCurrent()) {
                sheet.MoveTo(row, column, false);
            }
            IOSDisplay.RequestFrame();
        }

        // A double tap: the current cell is edited, as F2 does; another
        // cell becomes current.
        @Override
        public boolean activate() {
            Spreadsheet_ sheet = GetSpreadsheet();
            if (sheet.IsEditing()) {
                return false;
            }
            table.FocusSpreadsheet();
            if (IsCurrent()) {
                sheet.BeginEditing(null);
                if (IOSAccessibility.debug) {
                    System.out.println("VoiceOver edits cell " + sheet.GetCellName(row, column));
                }
            } else {
                sheet.MoveTo(row, column, false);
            }
            IOSDisplay.RequestFrame();
            return true;
        }

        /*
            A three-finger swipe: the current cell moves by what is on
            screen, which scrolls the spreadsheet. Up shows the rows below,
            as a page down does, and left the columns to the right.
            VoiceOver then goes to the new current cell
            (IOSAccessibility.NativeSpreadsheetChanged).
         */
        @Override
        public boolean scroll(UIAccessibilityScrollDirection direction) {
            Spreadsheet_ sheet = GetSpreadsheet();
            if (sheet.IsEditing()) {
                return false;
            }
            int rows = Math.max(1, sheet.GetLastVisibleRow() - sheet.GetFirstVisibleRow());
            int columns = Math.max(1, sheet.GetLastVisibleColumn() - sheet.GetFirstVisibleColumn());
            int rowChange = 0;
            int columnChange = 0;
            if (direction == UIAccessibilityScrollDirection.Up || direction == UIAccessibilityScrollDirection.Next) {
                rowChange = rows;
            } else if (direction == UIAccessibilityScrollDirection.Down || direction == UIAccessibilityScrollDirection.Previous) {
                rowChange = -rows;
            } else if (direction == UIAccessibilityScrollDirection.Left) {
                columnChange = columns;
            } else if (direction == UIAccessibilityScrollDirection.Right) {
                columnChange = -columns;
            } else {
                return false;
            }
            int startRow = row;
            int startColumn = column;
            SpreadsheetSelection_ selection = sheet.GetSelection();
            if (selection != null && !selection.IsEmpty()) {
                startRow = selection.GetRow();
                startColumn = selection.GetColumn();
            }
            int newRow = Math.max(0, Math.min(sheet.GetDisplayedRowSize() - 1, startRow + rowChange));
            int newColumn = Math.max(0, Math.min(sheet.GetDisplayedColumnSize() - 1, startColumn + columnChange));
            if (newRow == startRow && newColumn == startColumn) {
                return false;
            }
            table.FocusSpreadsheet();
            sheet.MoveTo(newRow, newColumn, false);
            IOSDisplay.RequestFrame();
            return true;
        }
    }

    /*
        A column's letters, read "C, column". Selected while the whole column
        is; a double tap selects the column, as clicking its letters does.
        Not the Header trait: VoiceOver would add "heading" to "column".
     */
    public static class ColumnHeader extends ItemIOS {
        private final SpreadsheetIOS table;
        final int column;

        ColumnHeader(SpreadsheetIOS table, int column) {
            super(table);
            this.table = table;
            this.column = column;
        }

        Spreadsheet_ GetSpreadsheet() {
            return (Spreadsheet_) GetItem();
        }

        @Override
        public void RefreshText() {
            setAccessibilityLabel(null);
            setAccessibilityValue(null);
            setAccessibilityHint(null);
        }

        @Override
        public String getAccessibilityLabel() {
            return GetSpreadsheet().GetColumnLabel(column) + ", column";
        }

        @Override
        public String getAccessibilityValue() {
            return null;
        }

        @Override
        public String getAccessibilityHint() {
            return null;
        }

        @Override
        public UIAccessibilityTraits getAccessibilityTraits() {
            long traits = UIAccessibilityTraits.None.value();
            if (GetSpreadsheet().IsWholeColumnSelected(column)) {
                traits = traits | UIAccessibilityTraits.Selected.value();
            }
            return new UIAccessibilityTraits(traits);
        }

        @Override
        public CGRect getAccessibilityFrame() {
            return table.GetColumnHeaderFrame(column);
        }

        @Override
        public void didBecomeFocused() {
            focused = true;
            voiceOverFocus = this;
            if (IOSAccessibility.debug) {
                System.out.println("VoiceOver moved to spreadsheet column \"" + getAccessibilityLabel() + "\"");
            }
            table.FocusSpreadsheet();
            IOSDisplay.RequestFrame();
        }

        @Override
        public boolean activate() {
            Spreadsheet_ sheet = GetSpreadsheet();
            if (sheet.IsEditing()) {
                return false;
            }
            table.FocusSpreadsheet();
            sheet.SelectColumns(column, column);
            IOSDisplay.RequestFrame();
            return true;
        }
    }

    /*
        A row's number, read "2, row". Selected while the whole row is; a
        double tap selects the row, as clicking its number does. Not the
        Header trait: VoiceOver would add "heading" to "row".
     */
    public static class RowHeader extends ItemIOS {
        private final SpreadsheetIOS table;
        final int row;

        RowHeader(SpreadsheetIOS table, int row) {
            super(table);
            this.table = table;
            this.row = row;
        }

        Spreadsheet_ GetSpreadsheet() {
            return (Spreadsheet_) GetItem();
        }

        @Override
        public void RefreshText() {
            setAccessibilityLabel(null);
            setAccessibilityValue(null);
            setAccessibilityHint(null);
        }

        @Override
        public String getAccessibilityLabel() {
            return (row + 1) + ", row";
        }

        @Override
        public String getAccessibilityValue() {
            return null;
        }

        @Override
        public String getAccessibilityHint() {
            return null;
        }

        @Override
        public UIAccessibilityTraits getAccessibilityTraits() {
            long traits = UIAccessibilityTraits.None.value();
            if (GetSpreadsheet().IsWholeRowSelected(row)) {
                traits = traits | UIAccessibilityTraits.Selected.value();
            }
            return new UIAccessibilityTraits(traits);
        }

        @Override
        public CGRect getAccessibilityFrame() {
            return table.GetRowHeaderFrame(row);
        }

        @Override
        public void didBecomeFocused() {
            focused = true;
            voiceOverFocus = this;
            if (IOSAccessibility.debug) {
                System.out.println("VoiceOver moved to spreadsheet row \"" + getAccessibilityLabel() + "\"");
            }
            table.FocusSpreadsheet();
            IOSDisplay.RequestFrame();
        }

        @Override
        public boolean activate() {
            Spreadsheet_ sheet = GetSpreadsheet();
            if (sheet.IsEditing()) {
                return false;
            }
            table.FocusSpreadsheet();
            sheet.SelectRows(row, row);
            IOSDisplay.RequestFrame();
            return true;
        }
    }
}
