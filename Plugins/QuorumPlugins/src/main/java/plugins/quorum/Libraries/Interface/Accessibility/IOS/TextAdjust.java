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
}
