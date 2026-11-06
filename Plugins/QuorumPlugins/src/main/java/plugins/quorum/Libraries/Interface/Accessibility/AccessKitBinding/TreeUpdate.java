package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
    A set of node changes for AccessKit, holding the native update's pointer
    until it is handed over.

    Ownership: add() moves a Node's pointer into the update (the Node is
    then used up). When an update is returned from an ActivationHandler or
    a TreeUpdateSupplier, AccessKit takes its pointer and this object is
    used up. drop() frees an update that was never handed over; it does
    nothing to one that was.
*/
public final class TreeUpdate {
    long ptr;

    // The focus this update carries, remembered for lastGivenFocus.
    long focus;

    /*
        The focus of the last update AccessKit took. AccessKit's tree
        checked that this node exists, so an empty update with it as the
        focus is always valid; native code sends one if a supplier fails.
        Updates happen on the main thread, but volatile costs nothing here.
    */
    static volatile long lastGivenFocus = 0;

    private TreeUpdate(long ptr, long focus) {
        this.ptr = ptr;
        this.focus = focus;
    }

    public static TreeUpdate withFocus(NodeId focus) {
        return new TreeUpdate(nativeWithFocus(focus.value), focus.value);
    }

    public void drop() {
        if (ptr != 0) {
            nativeDrop(ptr);
            ptr = 0;
        }
    }

    /* The update takes the node's native data; node can't be used again. */
    public void add(NodeId id, Node node) {
        checkActive();
        node.checkActive();
        nativeAddNode(ptr, id.value, node.ptr);
        node.ptr = 0;
    }

    public void setTree(Tree tree) {
        checkActive();
        nativeSetTree(ptr, tree.root.value);
    }

    public void clearTree() {
        checkActive();
        nativeClearTree(ptr);
    }

    public void setFocus(NodeId focus) {
        checkActive();
        nativeSetFocus(ptr, focus.value);
        this.focus = focus.value;
    }

    void checkActive() {
        Util.checkActive(ptr);
    }

    /* Gives up the pointer to native code, which hands it to AccessKit. */
    long giveUp() {
        checkActive();
        long result = ptr;
        ptr = 0;
        lastGivenFocus = focus;
        return result;
    }

    /*
        Wraps a supplier for native code. Nothing is thrown into native code:
        a failure is logged and gives 0, which becomes an empty update with
        the last focus (see AccessKitJNI.m, Supply).
    */
    static NativePointerSupplier makeNativeSupplier(final TreeUpdateSupplier supplier) {
        return () -> {
            try {
                TreeUpdate update = supplier.get();
                return update == null ? 0 : update.giveUp();
            } catch (Throwable error) {
                Logger.getLogger(TreeUpdate.class.getName()).log(Level.WARNING,
                    "An accessibility update failed; VoiceOver keeps the previous tree.", error);
                return 0;
            }
        };
    }

    /*
        Wraps an activation handler for native code. A failure is logged and
        gives 0, which AccessKit reads as "not ready".
    */
    static NativePointerSupplier makeNativeSupplier(final ActivationHandler handler) {
        return () -> {
            try {
                TreeUpdate update = handler.requestInitialTree();
                return update == null ? 0 : update.giveUp();
            } catch (Throwable error) {
                Logger.getLogger(TreeUpdate.class.getName()).log(Level.WARNING,
                    "Building the accessibility tree failed; VoiceOver sees an empty window.", error);
                return 0;
            }
        };
    }

    private static native long nativeWithFocus(long focus);

    private static native void nativeDrop(long ptr);

    private static native void nativeAddNode(long ptr, long id, long node);

    private static native void nativeSetTree(long ptr, long root);

    private static native void nativeClearTree(long ptr);

    private static native void nativeSetFocus(long ptr, long focus);
}
