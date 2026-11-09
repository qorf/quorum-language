package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Node;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.Role;
import quorum.Libraries.Interface.Controls.Tab_;
import quorum.Libraries.Interface.Item_;

public class TabKit extends ClickableKit {
    public TabKit() {
        SetRole(Role.TAB);
    }

    /*
        AccessKit shows a tab as a radio button whose value is its selected
        state, so VoiceOver says which tab is showing.
    */
    @Override
    public Node Build() {
        Node builder = super.Build();
        Item_ item = GetItem();
        if (item instanceof Tab_) {
            Tab_ tab = (Tab_) item;
            builder.setSelected(tab.IsActivated());
        }
        return builder;
    }
}
