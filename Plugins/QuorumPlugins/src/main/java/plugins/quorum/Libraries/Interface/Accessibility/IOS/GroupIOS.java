package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;
import quorum.Libraries.Interface.Forms.Banner_;

/*
    A group (a Forms Grouping or Banner, Quorum's GROUP code) is a container
    of type SemanticGroup holding its controls' elements. It is not an
    element itself: VoiceOver visits the controls. A grouping's name is its
    label, which VoiceOver can say on entering it ("Role"); a banner has
    none, since its title label, inside it, says it.
 */
public class GroupIOS extends ItemIOS implements UIAccessibilityContainer {
    private NSMutableArray<UIAccessibilityElement> items = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    public GroupIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public boolean isAccessibilityElement() {
        return false;
    }

    @Override
    public void RefreshText() {
        setAccessibilityLabel(GetItem() instanceof Banner_ ? null : GetItem().GetName());
        setAccessibilityValue(null);
        setAccessibilityHint(null);
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
