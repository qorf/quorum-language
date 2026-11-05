package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* A text selection, from where it was started (anchor) to the caret (focus). */
public final class TextSelection {
    public TextPosition anchor;
    public TextPosition focus;

    public TextSelection(TextPosition anchor, TextPosition focus) {
        this.anchor = anchor;
        this.focus = focus;
    }
}
