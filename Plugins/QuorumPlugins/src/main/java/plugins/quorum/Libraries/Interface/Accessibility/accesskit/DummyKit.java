package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.*;
import quorum.Libraries.Interface.Item_;

public class DummyKit extends ItemKit {
    private int dummyNodeID = 2;
    private String name = "Dummy";

    public DummyKit() {
        SetRole(Role.UNKNOWN);
    }

    public Node Build() {
        NodeBuilder builder = new NodeBuilder(GetRole());
        Item_ item = GetItem();
        if (item != null) {
            Rect rect = GetBoundingRectangle();
            builder.setBounds(rect);
            builder.setName(name);
        } else {
            builder.setName("Dummy");
        }
        return builder.build();
    }

    public NodeId GetNodeID() {
        return new NodeId(dummyNodeID);
    }

    protected void BuildChildren(NodeBuilder builder) {
        //intentionally does nothing. May need to change.
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public int GetDummyNodeID() {
        return dummyNodeID;
    }

    public void SetDummyNodeID(int dummyNodeID) {
        this.dummyNodeID = dummyNodeID;
    }
}
