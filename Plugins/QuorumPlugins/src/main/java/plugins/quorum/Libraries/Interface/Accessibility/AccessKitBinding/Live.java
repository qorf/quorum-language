package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* enum accesskit_live in accesskit.h 0.23.2. */
public enum Live {
    OFF(0),
    POLITE(1),
    ASSERTIVE(2);

    /* The C value, which the natives get (not ordinal()). */
    final int value;

    Live(int value) {
        this.value = value;
    }
}
