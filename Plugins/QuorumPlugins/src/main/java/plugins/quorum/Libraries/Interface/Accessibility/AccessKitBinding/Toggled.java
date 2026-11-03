package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* enum accesskit_toggled in accesskit.h 0.23.2. */
public enum Toggled {
    FALSE(0),
    TRUE(1),
    MIXED(2);

    /* The C value, which the natives get (not ordinal()). */
    final int value;

    Toggled(int value) {
        this.value = value;
    }
}
