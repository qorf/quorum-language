package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;

/*
    A chart (Quorum's CHART code) is a container of type SemanticGroup
    holding, in reading order, a summary element (ChartSummaryIOS: its title
    and summary, and Apple's Audio Graph), then its parts (ChartItemIOS: the
    chart area, bars, axes, legend). The order follows the chart's own
    navigation graph, built for keyboard users, and is rebuilt from it
    whenever a part comes or goes (IOSAccessibility.RebuildChart). It is not
    an element itself.
 */
public class ChartIOS extends ItemIOS implements UIAccessibilityContainer {
    private NSMutableArray<UIAccessibilityElement> items = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    public ChartIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public boolean isAccessibilityElement() {
        return false;
    }

    // The element read first: the chart's title and summary.
    private ChartSummaryIOS summary = null;

    public ChartSummaryIOS GetSummary() {
        return summary;
    }

    public void SetSummary(ChartSummaryIOS summary) {
        this.summary = summary;
    }

    // The summary element says the chart's name, so the container doesn't.
    @Override
    public void RefreshText() {
        setAccessibilityLabel(null);
        setAccessibilityValue(null);
        setAccessibilityHint(null);
        if (summary != null) {
            summary.RefreshText();
        }
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
