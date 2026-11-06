package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* A place in a text run: the run's id and a character index in it. */
public final class TextPosition {
    public NodeId node;
    public int characterIndex;

    public TextPosition(NodeId node, int characterIndex) {
        this.node = node;
        this.characterIndex = characterIndex;
    }
}
