package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import quorum.Libraries.Interface.Controls.Control_;
import quorum.Libraries.Interface.Item_;

/*
    A control VoiceOver can press (VO-Space: accessibilityPerformPress,
    which AccessKit sends as CLICK). Pressing it runs the control's
    Activate, as a click or the Space key does: a button's behavior, a
    toggle's change, a tab's selection, a menu item's command. Windows'
    Invoke does the same (ButtonControl::Invoke).
*/
public class ClickableKit extends ItemKit {
    @Override
    protected void AddActions(Node node, Item_ item) {
        super.AddActions(node, item);
        if (item instanceof Control_) {
            node.addAction(Action.CLICK);
        }
    }

    @Override
    public void DoAction(ActionRequest request) {
        Item_ item = GetItem();
        if (request.action == Action.CLICK && item instanceof Control_) {
            Control_ control = (Control_) item;
            control.Activate();
        } else {
            super.DoAction(request);
        }
    }
}
