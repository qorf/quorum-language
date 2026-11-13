package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;
import quorum.Libraries.Interface.Controls.Calendar_;
import quorum.Libraries.System.CalendarDate_;

/*
    A calendar (Quorum's CALENDAR code) is a container of type SemanticGroup
    holding, in reading order: the previous month button, the month title
    (CalendarTitleIOS, adjustable: swipe up or down for the next or previous
    month), the next month button, then the 42 days of the grid
    (CalendarDayIOS), week by week. It is not an element itself.

    The title and days are made here, not from Quorum items (the calendar
    draws its days itself). They always read the calendar as it is now, so
    a change to the calendar only needs VoiceOver told where to go
    (IOSAccessibility.NativeCalendarChanged).
 */
public class CalendarIOS extends ItemIOS implements UIAccessibilityContainer {
    /*
        Calendar.quorum's sizes, in points before the interface scale: the
        same numbers as its constants, which must be kept the same by hand
        (as the Mac's CalendarKit and Android's calendar nodes do).
    */
    static final double CALENDAR_WIDTH = 268;
    static final double CALENDAR_HEIGHT = 292;
    static final double CALENDAR_PADDING = 8;
    static final double CALENDAR_HEADER_HEIGHT = 36;
    static final double CALENDAR_CELL_SIZE = 36;
    static final double CALENDAR_MONTH_BUTTON_SIZE = 28;
    static final int CALENDAR_ROWS = 6;
    static final int CALENDAR_COLUMNS = 7;

    private NSMutableArray<UIAccessibilityElement> items = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    private CalendarTitleIOS title = null;
    private CalendarDayIOS[] days = new CalendarDayIOS[0];

    public CalendarIOS(UIAccessibilityContainer container) {
        super(container);
    }

    public Calendar_ GetCalendar() {
        return (Calendar_) GetItem();
    }

    // Makes the title and one element for each place in the grid.
    @Override
    public void Initialize(quorum.Libraries.Interface.Item_ item) {
        super.Initialize(item);
        Calendar_ calendar = (Calendar_) item;

        title = new CalendarTitleIOS(this);
        title.Initialize(item);
        title.setAccessibilityIdentifier(item.GetName() + " month");

        int rows = GetRows();
        int columns = GetColumns();
        days = new CalendarDayIOS[rows * columns];
        for (int index = 0; index < days.length; index++) {
            CalendarDayIOS day = new CalendarDayIOS(this);
            day.Initialize(item);
            day.SetPlace(this, index / columns, index % columns);
            day.setAccessibilityIdentifier(item.GetName() + " day " + index);
            days[index] = day;
        }
    }

    public CalendarTitleIOS GetTitle() {
        return title;
    }

    public CalendarDayIOS[] GetDays() {
        return days;
    }

    public int GetRows() {
        return CALENDAR_ROWS;
    }

    public int GetColumns() {
        return CALENDAR_COLUMNS;
    }

    // The day element showing a date, or null if the date isn't shown.
    public CalendarDayIOS GetDayElement(CalendarDate_ date) {
        if (date == null || days.length == 0) {
            return null;
        }
        CalendarDate_ first = GetCalendar().GetShownDay(0, 0);
        if (first == null) {
            return null;
        }
        int index = first.DaysUntil(date);
        if (index < 0 || index >= days.length) {
            return null;
        }
        return days[index];
    }

    // The current day's element: where VoiceOver goes for the calendar.
    public UIAccessibilityElement GetCurrentDayElement() {
        CalendarDayIOS day = GetDayElement(GetCalendar().GetCurrentDay());
        if (day != null) {
            return day;
        }
        return title;
    }

    // The title says the month, so the container says nothing.
    @Override
    public void RefreshText() {
        setAccessibilityLabel(null);
        setAccessibilityValue(null);
        setAccessibilityHint(null);
    }

    /*
        The frame of a part of the calendar, given as fractions of its width
        and height, measured from its bottom left as Quorum places it.
     */
    CGRect GetPartFrame(double left, double bottom, double width, double height) {
        CGRect whole = getAccessibilityFrame();
        double partWidth = whole.getWidth() * width;
        double partHeight = whole.getHeight() * height;
        double x = whole.getX() + whole.getWidth() * left;
        // UIKit's frames count down from the top.
        double y = whole.getY() + whole.getHeight() - whole.getHeight() * bottom - partHeight;
        return new CGRect(x, y, partWidth, partHeight);
    }

    /*
        The frame of the day at a row (0 is the top week) and column, from
        the grid's place in Calendar.quorum: the padding at the left and
        bottom, then seven columns and six rows of square cells.
     */
    CGRect GetDayFrame(int row, int column) {
        Calendar_ calendar = GetCalendar();
        double width = CALENDAR_WIDTH;
        double height = CALENDAR_HEIGHT;
        double padding = CALENDAR_PADDING;
        double cell = CALENDAR_CELL_SIZE;
        int rows = GetRows();
        return GetPartFrame((padding + column * cell) / width, (padding + (rows - 1 - row) * cell) / height,
                cell / width, cell / height);
    }

    // The title's frame: the header, between the two month buttons.
    CGRect GetTitleFrame() {
        Calendar_ calendar = GetCalendar();
        double width = CALENDAR_WIDTH;
        double height = CALENDAR_HEIGHT;
        double padding = CALENDAR_PADDING;
        double header = CALENDAR_HEADER_HEIGHT;
        double button = CALENDAR_MONTH_BUTTON_SIZE;
        double side = (padding + button) / width;
        return GetPartFrame(side, (height - padding - header) / height, 1 - 2 * side, header / height);
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
        return UIAccessibilityContainerType.SemanticGroup;
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
}
