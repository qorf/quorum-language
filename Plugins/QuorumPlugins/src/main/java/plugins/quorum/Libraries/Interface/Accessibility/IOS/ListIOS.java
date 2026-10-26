package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;

/*
    A list is a container of type List holding its items' elements, so
    VoiceOver says "list start" and "list end" around them. It is not an
    element itself: VoiceOver visits the items. Its label is the list's name.
 */
public class ListIOS extends ItemIOS implements UIAccessibilityContainer {
    private NSMutableArray<UIAccessibilityElement> items = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    public ListIOS(UIAccessibilityContainer container) {
        super(container);
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
        return UIAccessibilityContainerType.List;
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
