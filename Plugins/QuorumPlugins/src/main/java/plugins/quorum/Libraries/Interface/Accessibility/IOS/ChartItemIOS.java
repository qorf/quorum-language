package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Interface.Controls.Charts.ChartItem_;
import quorum.Libraries.Interface.Controls.Charts.Chart_;

/*
    A part of a chart (CHART_SECTION or CHART_ITEM): a bar reads its name and
    then its description ("Monday bar", "42, 1 of 5 bars."); a section (the
    chart area, an axis, the legend) its name and then its summary. When
    VoiceOver moves onto a part, the chart selects it, so Quorum's selection
    box and keyboard navigation follow VoiceOver. As with the desktop's
    keyboard, a double tap enters a part (the chart area's bars), the
    two-finger scrub escapes back out, and swiping up and down on a bar moves
    by value (Page Up and Page Down on the desktop).
 */
public class ChartItemIOS extends ItemIOS {
    public ChartItemIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public void RefreshText() {
        String name = GetItem().GetName();
        setAccessibilityLabel(name == null ? null : Trim(name));

        String value = null;
        if (GetItem() instanceof ChartItem_) {
            value = ((ChartItem_) GetItem()).GetDisplayName();
        }
        if (value == null || value.isEmpty()) {
            value = GetItem().GetDescription();
        }
        setAccessibilityValue(value == null || value.isEmpty() ? null : value);
        setAccessibilityHint(null);
    }

    // Chart names are built to be spoken before a description ("Monday bar, ").
    static String Trim(String name) {
        String trimmed = name.trim();
        while (trimmed.endsWith(",")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1).trim();
        }
        return trimmed;
    }

    Chart_ GetChart() {
        return GetItem() instanceof ChartItem_ ? ((ChartItem_) GetItem()).GetContainer() : null;
    }

    // Adjustable (swipe up and down) when the part has neighbors by value.
    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        UIAccessibilityTraits traits = super.getAccessibilityTraits();
        if (GetItem() instanceof ChartItem_) {
            ChartItem_ part = (ChartItem_) GetItem();
            if (part.GetUpItem() != null || part.GetDownItem() != null) {
                return new UIAccessibilityTraits(traits.value() | UIAccessibilityTraits.Adjustable.value());
            }
        }
        return traits;
    }

    // Double tap: into this part's group, such as the chart area's bars.
    @Override
    public boolean activate() {
        Chart_ chart = GetChart();
        ChartItem_ part = (ChartItem_) GetItem();
        boolean handled = false;
        if (chart != null && part.GetEnterItem() != null) {
            chart.Select(part);
            chart.SelectionEnter();
            IOSAccessibility.ChartLevelChanged(chart);
            handled = true;
        }
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver double tap on \"" + part.GetName() + "\": enters \""
                + NameOf(part.GetEnterItem()) + "\", handled " + handled);
        }
        return handled;
    }

    // The two-finger scrub: back out to the containing group.
    @Override
    public boolean performEscape() {
        Chart_ chart = GetChart();
        ChartItem_ part = (ChartItem_) GetItem();
        boolean handled = false;
        if (chart != null && part.GetEscapeItem() != null) {
            chart.Select(part);
            chart.SelectionEscape();
            IOSAccessibility.ChartLevelChanged(chart);
            handled = true;
        }
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver scrub on \"" + part.GetName() + "\": escapes to \""
                + NameOf(part.GetEscapeItem()) + "\", handled " + handled);
        }
        return handled;
    }

    // A part's name for the debug lines, or "none".
    private String NameOf(ChartItem_ part) {
        if (part == null)
            return "none";
        return part.GetName();
    }

    // Swipe up and down: the next bar by value.
    @Override
    public void increment() {
        Chart_ chart = GetChart();
        if (chart != null) {
            chart.Select((ChartItem_) GetItem());
            chart.SelectionUp();
        }
    }

    @Override
    public void decrement() {
        Chart_ chart = GetChart();
        if (chart != null) {
            chart.Select((ChartItem_) GetItem());
            chart.SelectionDown();
        }
    }

    @Override
    public void didBecomeFocused() {
        super.didBecomeFocused();
        if (GetItem() instanceof ChartItem_) {
            ChartItem_ part = (ChartItem_) GetItem();
            Chart_ chart = part.GetContainer();
            if (chart != null) {
                chart.Select(part);
            }
        }
    }
}
