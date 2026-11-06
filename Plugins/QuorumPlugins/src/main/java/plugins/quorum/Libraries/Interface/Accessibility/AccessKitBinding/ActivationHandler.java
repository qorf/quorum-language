package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    Builds the whole tree, the first time VoiceOver looks at the window.
    Returning null means "not ready"; AccessKit then doesn't ask again, and
    the next update must carry the whole tree (see MacosSubclassingAdapter).
*/
public interface ActivationHandler {
    public TreeUpdate requestInitialTree();
}
