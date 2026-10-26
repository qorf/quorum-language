package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;

/*
    One tree item and its open children. In UIKit an object is either an
    element VoiceOver stops on or a container, never both, so a tree item is
    a group: first the item's own element (TreeItemIOS), then the groups of
    its children while it is open, in the tree's order
    (IOSAccessibility.RebuildGroup).
 */
public class TreeItemGroupIOS extends ItemIOS implements UIAccessibilityContainer {
    private NSMutableArray<UIAccessibilityElement> elements = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    public TreeItemGroupIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public boolean isAccessibilityElement() {
        return false;
    }

    // The item's frame and its open children's together, so touch
    // exploration finds the children inside it.
    @Override
    public CGRect getAccessibilityFrame() {
        CGRect frame = null;
        for (UIAccessibilityElement element : elements) {
            CGRect next = element.getAccessibilityFrame();
            // An empty frame (not laid out yet) would stretch the union to
            // its origin.
            if (next == null || next.isEmpty()) {
                continue;
            }
            frame = frame == null ? next : frame.union(next);
        }
        return frame == null ? super.getAccessibilityFrame() : frame;
    }

    @Override
    public NSMutableArray<UIAccessibilityElement> getAccessibilityElements() {
        return elements;
    }

    // As in IOSAccessibilityContainer: the array must stay an NSMutableArray.
    @Override
    public void setAccessibilityElements(NSArray<UIAccessibilityElement> v) {
        elements = (NSMutableArray<UIAccessibilityElement>) v;
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
        return elements.size();
    }

    @Override
    public UIAccessibilityElement getAccessibilityElement(long index) {
        return elements.get((int) index);
    }

    @Override
    public long indexOfAccessibilityElement(UIAccessibilityElement element) {
        return elements.indexOf(element);
    }

    @Override
    public NSArray<?> getAutomationElements() {
        if (automationElements != null)
            return automationElements;
        return elements;
    }

    @Override
    public void setAutomationElements(NSArray<?> v) {
        automationElements = v;
    }
}
