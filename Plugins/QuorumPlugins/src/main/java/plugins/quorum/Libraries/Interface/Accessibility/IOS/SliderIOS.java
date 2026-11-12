package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Interface.Controls.Slider_;
import quorum.Libraries.Interface.Item_;

/*
    A Slider for VoiceOver: adjustable, so a swipe up or down steps it as
    the arrow keys do, and its value is read as the slider's value text,
    such as "40 degrees". VoiceOver says the new value after each swipe.
 */
public class SliderIOS extends ItemIOS {
    public SliderIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public void Initialize(Item_ item) {
        setAccessibilityTraits(UIAccessibilityTraits.Adjustable);
        super.Initialize(item);
    }

    // Adjustable, with whatever else an item has (zoom, for example).
    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        UIAccessibilityTraits traits = super.getAccessibilityTraits();
        return new UIAccessibilityTraits(traits.value() | UIAccessibilityTraits.Adjustable.value());
    }

    @Override
    public String getAccessibilityValue() {
        Item_ item = GetItem();
        if (item instanceof Slider_) {
            return ((Slider_) item).GetValueText();
        }
        return null;
    }

    // Swipe up.
    @Override
    public void increment() {
        Item_ item = GetItem();
        if (item instanceof Slider_) {
            ((Slider_) item).StepUp();
            if (IOSAccessibility.debug) {
                System.out.println("VoiceOver swipe up on slider \"" + item.GetName() + "\": " + ((Slider_) item).GetValueText());
            }
        }
    }

    // Swipe down.
    @Override
    public void decrement() {
        Item_ item = GetItem();
        if (item instanceof Slider_) {
            ((Slider_) item).StepDown();
            if (IOSAccessibility.debug) {
                System.out.println("VoiceOver swipe down on slider \"" + item.GetName() + "\": " + ((Slider_) item).GetValueText());
            }
        }
    }
}
