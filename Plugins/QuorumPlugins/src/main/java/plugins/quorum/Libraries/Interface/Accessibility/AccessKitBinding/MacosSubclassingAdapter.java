package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    AccessKit's macOS adapter, which makes an NSView answer VoiceOver.
    Natives in Mac.so (quorum-natives/src/mac/AccessKitJNI.m), which
    Game.java has already loaded.

    It must be used on the main thread, and closed at most once; close()
    after close() does nothing.

    VoiceOver's action requests go to the ActionHandler, if one is given;
    without one they are dropped.
*/
public final class MacosSubclassingAdapter extends Adapter {
    long ptr;

    /* view is an NSView pointer. */
    public MacosSubclassingAdapter(long view, ActivationHandler activationHandler) {
        this(view, activationHandler, null);
    }

    /* view is an NSView pointer; actionHandler may be null. */
    public MacosSubclassingAdapter(long view, ActivationHandler activationHandler, ActionHandler actionHandler) {
        NativePointerSupplier supplier = TreeUpdate.makeNativeSupplier(activationHandler);
        ptr = nativeNew(view, supplier, receiverFor(actionHandler));
    }

    MacosSubclassingAdapter(long ptr) {
        this.ptr = ptr;
    }

    /* window is an NSWindow pointer, for example from GLFWNativeCocoa.glfwGetCocoaWindow. */
    public static MacosSubclassingAdapter forWindow(long window, ActivationHandler activationHandler) {
        return forWindow(window, activationHandler, null);
    }

    /* The same, with VoiceOver's action requests going to actionHandler (may be null). */
    public static MacosSubclassingAdapter forWindow(long window, ActivationHandler activationHandler,
            ActionHandler actionHandler) {
        NativePointerSupplier supplier = TreeUpdate.makeNativeSupplier(activationHandler);
        return new MacosSubclassingAdapter(nativeForWindow(window, supplier, receiverFor(actionHandler)));
    }

    private static NativeActionReceiver receiverFor(ActionHandler actionHandler) {
        return actionHandler == null ? null : new NativeActionReceiver(actionHandler);
    }

    @Override
    public void close() {
        if (ptr != 0) {
            nativeDrop(ptr);
            ptr = 0;
        }
    }

    /*
        If VoiceOver is active, gets an update from the supplier and gives it
        to AccessKit; otherwise the supplier isn't called. If the supplier
        fails, AccessKit gets an empty update with the last focus it was
        given, which changes nothing.
    */
    @Override
    public void updateIfActive(TreeUpdateSupplier updateSupplier) {
        checkActive();
        NativePointerSupplier supplier = TreeUpdate.makeNativeSupplier(updateSupplier);
        nativeUpdateIfActive(ptr, TreeUpdate.lastGivenFocus, supplier);
    }

    @Override
    public void updateViewFocusState(boolean isFocused) {
        checkActive();
        nativeUpdateViewFocusState(ptr, isFocused);
    }

    void checkActive() {
        Util.checkActive(ptr);
    }

    private static native long nativeNew(long view, NativePointerSupplier activationSupplier,
        NativeActionReceiver actionReceiver);

    private static native long nativeForWindow(long window, NativePointerSupplier activationSupplier,
        NativeActionReceiver actionReceiver);

    private static native void nativeDrop(long ptr);

    private static native void nativeUpdateIfActive(long ptr, long fallbackFocus, NativePointerSupplier updateSupplier);

    private static native void nativeUpdateViewFocusState(long ptr, boolean isFocused);
}
