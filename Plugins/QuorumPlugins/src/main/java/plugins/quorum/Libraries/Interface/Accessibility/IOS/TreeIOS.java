package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityContainerType;
import org.robovm.apple.uikit.UIAccessibilityElement;

/*
    A tree is a container holding only its top-level items' groups
    (TreeItemGroupIOS); each group holds its item and that item's open
    children's groups. VoiceOver walks them in the tree's order. It isn't
    an element itself: when Quorum focuses the tree, VoiceOver goes to its
    selected item, or its first (IOSAccessibility.GetVoiceOverTarget). The
    arrays are rebuilt from Quorum's tree whenever an item comes or goes
    (IOSAccessibility.RebuildTree).
 */
public class TreeIOS extends ItemIOS implements UIAccessibilityContainer {
    private NSMutableArray<UIAccessibilityElement> groups = new NSMutableArray<>();
    private NSArray<?> automationElements = null;

    public TreeIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public boolean isAccessibilityElement() {
        return false;
    }

    @Override
    public NSMutableArray<UIAccessibilityElement> getAccessibilityElements() {
        return groups;
    }

    // As in IOSAccessibilityContainer: the array must stay an NSMutableArray.
    @Override
    public void setAccessibilityElements(NSArray<UIAccessibilityElement> v) {
        groups = (NSMutableArray<UIAccessibilityElement>) v;
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
        return groups.size();
    }

    @Override
    public UIAccessibilityElement getAccessibilityElement(long index) {
        return groups.get((int) index);
    }

    @Override
    public long indexOfAccessibilityElement(UIAccessibilityElement element) {
        return groups.indexOf(element);
    }

    @Override
    public NSArray<?> getAutomationElements() {
        if (automationElements != null)
            return automationElements;
        return groups;
    }

    @Override
    public void setAutomationElements(NSArray<?> v) {
        automationElements = v;
    }
}
