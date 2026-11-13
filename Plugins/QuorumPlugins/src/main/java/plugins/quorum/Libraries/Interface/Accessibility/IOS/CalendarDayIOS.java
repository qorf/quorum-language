package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Game.IOSDisplay;
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Interface.Controls.Calendar_;
import quorum.Libraries.System.CalendarDate_;

/*
    One place in a calendar's grid of days. It reads whichever day the
    calendar shows there now: the date in words as its label (and "today"
    on today), with the Button trait, Selected on the
    chosen day and Not Enabled on a day outside the allowed range. A double
    tap moves the current day there and chooses it.

    VoiceOver landing on a day of the month shown moves the calendar's
    current day there, so the focus ring follows VoiceOver. Days of the
    months before and after are left alone, so swiping across them doesn't
    change the month under VoiceOver.
 */
public class CalendarDayIOS extends ItemIOS {
    private CalendarIOS calendarElement = null;
    private int row = 0;
    private int column = 0;

    public CalendarDayIOS(CalendarIOS container) {
        super(container);
    }

    void SetPlace(CalendarIOS calendar, int row, int column) {
        this.calendarElement = calendar;
        this.row = row;
        this.column = column;
    }

    Calendar_ GetCalendar() {
        return (Calendar_) GetItem();
    }

    // The day shown at this place now.
    public CalendarDate_ GetDate() {
        return GetCalendar().GetShownDay(row, column);
    }

    @Override
    public void RefreshText() {
        setAccessibilityLabel(null);
        setAccessibilityValue(null);
        setAccessibilityHint(null);
    }

    @Override
    public String getAccessibilityLabel() {
        CalendarDate_ date = GetDate();
        if (date == null) {
            return null;
        }
        // The date in words, and "today": not the calendar's own words for
        // selected and unavailable, which VoiceOver already says from the
        // Selected and Not Enabled traits.
        String words = date.ToSpokenText();
        quorum.Libraries.System.CalendarDate today = new quorum.Libraries.System.CalendarDate();
        today.SetToToday();
        if (words != null && date.GetDayNumber() == today.GetDayNumber()) {
            words = words + ", today";
        }
        return words;
    }

    @Override
    public String getAccessibilityValue() {
        return null;
    }

    @Override
    public String getAccessibilityHint() {
        return null;
    }

    boolean IsChosen(CalendarDate_ date) {
        CalendarDate_ chosen = GetCalendar().GetDate();
        return chosen != null && date != null && chosen.GetDayNumber() == date.GetDayNumber();
    }

    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        CalendarDate_ date = GetDate();
        long traits = UIAccessibilityTraits.Button.value();
        if (IsChosen(date)) {
            traits = traits | UIAccessibilityTraits.Selected.value();
        }
        if (!GetCalendar().IsAvailable(date)) {
            traits = traits | UIAccessibilityTraits.NotEnabled.value();
        }
        return new UIAccessibilityTraits(traits);
    }

    @Override
    public CGRect getAccessibilityFrame() {
        if (calendarElement == null) {
            return super.getAccessibilityFrame();
        }
        return calendarElement.GetDayFrame(row, column);
    }

    @Override
    public void didBecomeFocused() {
        focused = true;
        voiceOverFocus = this;
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver moved to " + getClass().getSimpleName() + " \"" + getAccessibilityLabel() + "\"");
        }

        Calendar_ calendar = GetCalendar();
        if (!calendar.IsFocused()) {
            calendar.Focus();
        }
        CalendarDate_ date = GetDate();
        if (date != null && calendar.IsInDisplayedMonth(date) && calendar.IsAvailable(date)) {
            int days = calendar.GetCurrentDay().DaysUntil(date);
            if (days != 0) {
                calendar.MoveCurrentDay(days);
            }
        }
        IOSDisplay.RequestFrame();
    }

    // A double tap: the current day moves here, and it is chosen.
    @Override
    public boolean activate() {
        Calendar_ calendar = GetCalendar();
        CalendarDate_ date = GetDate();
        if (date == null || !calendar.IsAvailable(date)) {
            return false;
        }
        if (!calendar.IsFocused()) {
            calendar.Focus();
        }
        int days = calendar.GetCurrentDay().DaysUntil(date);
        if (days != 0) {
            calendar.MoveCurrentDay(days);
        }
        calendar.ChooseCurrentDay();
        IOSDisplay.RequestFrame();
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver chose day \"" + calendar.GetDaySpokenText(date) + "\"");
        }
        return true;
    }
}
