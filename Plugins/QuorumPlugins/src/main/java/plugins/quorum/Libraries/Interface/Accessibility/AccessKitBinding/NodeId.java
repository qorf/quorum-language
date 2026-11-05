package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* A node's id. AccessKit reads it as an unsigned 64-bit number. */
public final class NodeId {
    public final long value;

    public NodeId(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof NodeId)) {
            return false;
        }
        NodeId id = (NodeId) other;
        return value == id.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return "NodeId(" + Long.toString(value) + ")";
    }
}
