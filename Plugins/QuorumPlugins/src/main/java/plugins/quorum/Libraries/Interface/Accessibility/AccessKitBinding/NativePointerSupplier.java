package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    What native code calls (AccessKitJNI.m) to get a TreeUpdate's pointer,
    which the TreeUpdate gives up. 0 means there is none.
*/
interface NativePointerSupplier {
    public long get();
}
