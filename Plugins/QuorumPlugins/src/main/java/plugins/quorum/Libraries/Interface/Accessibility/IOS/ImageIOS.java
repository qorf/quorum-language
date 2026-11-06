package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import quorum.Libraries.Interface.Item_;

/*
    An icon is read as an image. Its description is its alternative text;
    without one, its name.
 */
public class ImageIOS extends ItemIOS {
    public ImageIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public void Initialize(Item_ item) {
        setAccessibilityTraits(UIAccessibilityTraits.Image);
        super.Initialize(item);
    }

    @Override
    public void RefreshText() {
        String description = GetItem().GetDescription();
        if (description != null && !description.isEmpty()) {
            setAccessibilityLabel(description);
        } else {
            setAccessibilityLabel(GetItem().GetName());
        }
        setAccessibilityValue(null);
        setAccessibilityHint(null);
    }
}
