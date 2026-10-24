package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityCustomRotor;

public abstract class TextAdjust extends ItemIOS {
    public TextAdjust(UIAccessibilityContainer container)
    {
        super(container);
    }
    public abstract void Insert(String string);
    public abstract void DeleteBackward();

    public abstract String GetText();

    /*
        True when typing into this control should use secure entry (a password
        field). The hidden text field that receives the keyboard uses it.
     */
    public boolean IsSecure() {
        return false;
    }

    /*
        The text VoiceOver reads as this control's value. A secure control
        hides its text, so VoiceOver hears one dot per character instead.
     */
    public String GetAccessibleText() {
        String text = GetText();
        if (text == null || !IsSecure()) {
            return text;
        }

        StringBuilder dots = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            dots.append('•');
        }
        return dots.toString();
    }
}
