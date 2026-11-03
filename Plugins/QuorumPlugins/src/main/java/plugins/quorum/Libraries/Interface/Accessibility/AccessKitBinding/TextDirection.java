package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* enum accesskit_text_direction in accesskit.h 0.23.2. */
public enum TextDirection {
    LEFT_TO_RIGHT(0),
    RIGHT_TO_LEFT(1),
    TOP_TO_BOTTOM(2),
    BOTTOM_TO_TOP(3);

    /* The C value, which the natives get (not ordinal()). */
    final int value;

    TextDirection(int value) {
        this.value = value;
    }
}
