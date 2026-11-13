package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Game.IOSDisplay;
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Interface.Controls.Calendar_;

/*
    A calendar's month title, such as "November 2026", as the value of an
    adjustable element: a swipe up shows the next month and a swipe down the
    previous one, moving the current day by a month as Page Down and Page Up
    do. VoiceOver reads the new value after each swipe.
 */
public class CalendarTitleIOS extends ItemIOS {
    private CalendarIOS calendarElement;

    public CalendarTitleIOS(CalendarIOS container) {
        super(container);
        calendarElement = container;
    }

    Calendar_ GetCalendar() {
        return (Calendar_) GetItem();
    }

    @Override
    public void RefreshText() {
        setAccessibilityLabel(null);
        setAccessibilityValue(null);
        setAccessibilityHint(null);
    }

    @Override
    public String getAccessibilityValue() {
        return GetCalendar().GetName();
    }

    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        return UIAccessibilityTraits.Adjustable;
    }

    @Override
    public CGRect getAccessibilityFrame() {
        return calendarElement.GetTitleFrame();
    }

    // Swipe up.
    @Override
    public void increment() {
        MoveMonth(1);
    }

    // Swipe down.
    @Override
    public void decrement() {
        MoveMonth(-1);
    }

    private void MoveMonth(int months) {
        GetCalendar().MoveCurrentMonth(months);
        IOSDisplay.RequestFrame();
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver swipe on calendar month: " + GetCalendar().GetName());
        }
    }
}
