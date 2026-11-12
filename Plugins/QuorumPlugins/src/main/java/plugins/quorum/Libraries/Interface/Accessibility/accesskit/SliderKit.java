package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Action;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.ActionRequest;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Rect;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Controls.Slider_;
import quorum.Libraries.Interface.Item_;

/*
    A Slider for VoiceOver: its range, value, step and page step, and its
    value as text ("40 degrees"). VoiceOver's increment and decrement
    (VO-Up and VO-Down, or a swipe) step the value as the arrow keys do,
    and a value VoiceOver sets is taken as if typed. Each runs from
    MacAccessibility.NativeUpdate, on the game's side, so the slider's
    listeners run as they do for a key press.
*/
public class SliderKit extends ItemKit {
    public SliderKit() {
        SetRole(Role.SLIDER);
    }

    @Override
    public Node Build() {
        Item_ item = GetItem();
        if (item == null) {
            return null;
        }
        Rect rect = GetBoundingRectangle();
        Node builder = new Node(GetRole());
        builder.setBounds(rect);
        builder.setLabel(item.GetName());
        builder.setDescription(item.GetDescription());
        // The code can be set on any item, so check it is a slider.
        if (item instanceof Slider_) {
            Slider_ slider = (Slider_) item;
            builder.setMinNumericValue(slider.GetMinimum());
            builder.setMaxNumericValue(slider.GetMaximum());
            builder.setNumericValue(slider.GetValue());
            builder.setNumericValueStep(slider.GetStep());
            builder.setNumericValueJump(slider.GetPageStep());
            builder.setValue(slider.GetValueText());
        }
        return builder;
    }

    @Override
    protected void AddActions(Node node, Item_ item) {
        super.AddActions(node, item);
        if (item instanceof Slider_) {
            node.addAction(Action.INCREMENT);
            node.addAction(Action.DECREMENT);
            node.addAction(Action.SET_VALUE);
        }
    }

    @Override
    public void DoAction(ActionRequest request) {
        Item_ item = GetItem();
        if (!(item instanceof Slider_)) {
            super.DoAction(request);
            return;
        }
        Slider_ slider = (Slider_) item;
        if (request.action == Action.INCREMENT) {
            slider.StepUp();
        } else if (request.action == Action.DECREMENT) {
            slider.StepDown();
        } else if (request.action == Action.SET_VALUE) {
            if (request.hasNumericValue) {
                slider.ChangeValue(request.numericValue, true);
            } else if (request.value != null) {
                try {
                    slider.ChangeValue(Double.parseDouble(request.value.trim()), true);
                } catch (NumberFormatException e) {
                    // Not a number; ignored.
                }
            }
        } else {
            super.DoAction(request);
        }
    }
}
