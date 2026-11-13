package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Controls.Tooltip_;
import quorum.Libraries.Interface.Item_;

/*
    A Tooltip for VoiceOver: a tooltip node whose label is the tooltip's
    text. It hangs from the window's root, since the tooltip floats in a
    dialog of its own that VoiceOver doesn't see. A tooltip with buttons has
    them as its children, so VoiceOver can reach them; the focus moves to
    the first one when the keyboard opens it. The control's own node names
    this one as describing it while it shows (ItemKit.SetDescribedBy).
*/
public class TooltipKit extends ItemKit {
    public TooltipKit() {
        SetRole(Role.TOOLTIP);
    }

    @Override
    public Node Build() {
        Item_ item = GetItem();
        if (item == null) {
            return null;
        }
        Node builder = new Node(GetRole());
        builder.setBounds(GetBoundingRectangle());
        // The code can be set on any item, so check it is a tooltip.
        if (item instanceof Tooltip_) {
            builder.setLabel(((Tooltip_) item).GetText());
        } else {
            builder.setLabel(item.GetName());
        }
        BuildChildren(builder);
        return builder;
    }
}
