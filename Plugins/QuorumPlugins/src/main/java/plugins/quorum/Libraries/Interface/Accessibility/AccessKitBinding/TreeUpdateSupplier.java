package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    Builds an update, while Adapter.updateIfActive runs. Called only when
    VoiceOver is active.
*/
public interface TreeUpdateSupplier {
    public TreeUpdate get();
}
