package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import quorum.Libraries.Interface.Controls.ColorWell_;

/*
    A color well for VoiceOver: a button whose value is its color in words,
    such as "#4A5A6E, dim gray" ("Fill color, #4A5A6E, dim gray, button").
    A double tap opens the picker, as a click does. The well's description
    is the same words, so it isn't read again as the hint.
 */
public class ColorWellIOS extends ButtonIOS {
    public ColorWellIOS(UIAccessibilityContainer container) {
        super(container);
    }

    public ColorWell_ GetColorWell() {
        return (ColorWell_) GetItem();
    }

    @Override
    public void RefreshText() {
        super.RefreshText();
        String hint = super.getAccessibilityHint();
        String words = GetColorWell().GetColorText();
        if (hint != null && hint.equals(words)) {
            setAccessibilityHint(null);
        }
    }

    @Override
    public String getAccessibilityValue() {
        ColorWell_ well = GetColorWell();
        if (well == null) {
            return null;
        }
        return well.GetColorText();
    }
}
