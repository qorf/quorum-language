package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import quorum.Libraries.Interface.Forms.Banner_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item_;

/*
    A label reads its text, as static text. A banner's title is also a
    heading, so VoiceOver's headings rotor finds it.
 */
public class LabelIOS extends ItemIOS {
    public LabelIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public void Initialize(Item_ item) {
        long traits = UIAccessibilityTraits.StaticText.value();
        if (IsBannerTitle(item)) {
            traits = traits | UIAccessibilityTraits.Header.value();
        }
        setAccessibilityTraits(new UIAccessibilityTraits(traits));
        super.Initialize(item);
    }

    private boolean IsBannerTitle(Item_ item) {
        if (!(item instanceof Item2D_)) {
            return false;
        }
        Item2D_ parent = ((Item2D_) item).GetParent();
        return parent instanceof Banner_ && ((Banner_) parent).GetTitleLabel() == item;
    }

    // A Label's description is its text unless it was given another.
    @Override
    public void RefreshText() {
        setAccessibilityLabel(GetItem().GetDescription());
        setAccessibilityValue(null);
        setAccessibilityHint(null);
    }
}
