package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Role;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Item_;

public class ItemKit {
    private Role role = null;
    private Item_ item = null;

    public Item_ GetItem() {
        return item;
    }

    public void SetItem(Item_ item) {
        this.item = item;
    }

    public Role GetRole() {
        return role;
    }

    public void SetRole(Role role) {
        this.role = role;
    }
}
