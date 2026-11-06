package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    A platform adapter. Only the Mac's, MacosSubclassingAdapter, is here.
*/
public abstract class Adapter implements AutoCloseable {
    public abstract void updateIfActive(TreeUpdateSupplier updateSupplier);

    public abstract void updateViewFocusState(boolean isFocused);
}
