package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    One accessibility node, holding the native node's pointer until it is
    added to a TreeUpdate (which takes it; this object is then used up).
    drop() frees a node that was never added; it does nothing to one that
    was. Every other method throws IllegalStateException once the node is
    used up.

    Strings go to AccessKit as UTF-8. A null string or array clears the
    property.
*/
public final class Node {
    long ptr;

    public Node(Role role) {
        ptr = nativeNew(role.value);
    }

    public void drop() {
        if (ptr != 0) {
            nativeDrop(ptr);
            ptr = 0;
        }
    }

    public void addAction(Action action) {
        checkActive();
        nativeAddAction(ptr, action.value);
    }

    public void setLabel(String value) {
        checkActive();
        nativeSetLabel(ptr, Util.bytesFromString(value));
    }

    public void setDescription(String value) {
        checkActive();
        nativeSetDescription(ptr, Util.bytesFromString(value));
    }

    public void setValue(String value) {
        checkActive();
        nativeSetValue(ptr, Util.bytesFromString(value));
    }

    /*
        What VoiceOver calls the item instead of its role's name, such as
        "terrain" or "block" (Item:GetAccessibilityRoleDescription).
    */
    public void setRoleDescription(String value) {
        checkActive();
        nativeSetRoleDescription(ptr, Util.bytesFromString(value));
    }

    public void setAccessKey(String value) {
        checkActive();
        nativeSetAccessKey(ptr, Util.bytesFromString(value));
    }

    public void setKeyboardShortcut(String value) {
        checkActive();
        nativeSetKeyboardShortcut(ptr, Util.bytesFromString(value));
    }

    /*
        Any text the program likes. AccessKit's macOS adapter gives it to
        VoiceOver as the element's identifier; Quorum puts the node id there
        (see MacAccessibilityExtras).
    */
    public void setAuthorId(String value) {
        checkActive();
        nativeSetAuthorId(ptr, Util.bytesFromString(value));
    }

    public void setBounds(Rect value) {
        checkActive();
        nativeSetBounds(ptr, value.x0, value.y0, value.x1, value.y1);
    }

    public void addChild(NodeId id) {
        checkActive();
        nativeAddChild(ptr, id.value);
    }

    public void clearChildren() {
        checkActive();
        nativeClearChildren(ptr);
    }

    public void setChildren(Iterable<NodeId> ids) {
        clearChildren();
        for (NodeId id : ids) {
            addChild(id);
        }
    }

    /*
        Names a node whose text describes this one, such as a control's open
        tooltip. The native side needs Mac.so built with nativeAddDescribedBy;
        with an older Mac.so the relation is left out, once noted, and
        everything else still works.
    */
    public void addDescribedBy(NodeId id) {
        checkActive();
        if (!describedByAvailable) {
            return;
        }
        try {
            nativeAddDescribedBy(ptr, id.value);
        } catch (UnsatisfiedLinkError error) {
            describedByAvailable = false;
            java.util.logging.Logger.getLogger(Node.class.getName()).log(java.util.logging.Level.WARNING,
                "Mac.so has no nativeAddDescribedBy, so tooltips aren't tied to their controls for VoiceOver. Rebuild the native library to add it.");
        }
    }

    // False once an older Mac.so turns out not to have nativeAddDescribedBy.
    private static boolean describedByAvailable = true;

    public void setToggled(Toggled value) {
        checkActive();
        nativeSetToggled(ptr, value.value);
    }

    public void setLive(Live value) {
        checkActive();
        nativeSetLive(ptr, value.value);
    }

    public void setTextDirection(TextDirection value) {
        checkActive();
        nativeSetTextDirection(ptr, value.value);
    }

    public void setNumericValue(double value) {
        checkActive();
        nativeSetNumericValue(ptr, value);
    }

    public void setMinNumericValue(double value) {
        checkActive();
        nativeSetMinNumericValue(ptr, value);
    }

    public void setMaxNumericValue(double value) {
        checkActive();
        nativeSetMaxNumericValue(ptr, value);
    }

    public void setNumericValueStep(double value) {
        checkActive();
        nativeSetNumericValueStep(ptr, value);
    }

    public void setNumericValueJump(double value) {
        checkActive();
        nativeSetNumericValueJump(ptr, value);
    }

    public void setTextSelection(TextSelection value) {
        checkActive();
        nativeSetTextSelection(ptr,
            value.anchor.node.value, value.anchor.characterIndex,
            value.focus.node.value, value.focus.characterIndex);
    }

    /* Each character's length in UTF-8 bytes (a text run). */
    public void setCharacterLengths(byte[] value) {
        checkActive();
        nativeSetCharacterLengths(ptr, value);
    }

    /*
        The index of the character where each word starts, counting
        characters as in setCharacterLengths, in increasing order (a text
        run). Read as unsigned bytes, so up to 255. Replaces the old
        setWordLengths.
    */
    public void setWordStarts(byte[] value) {
        checkActive();
        nativeSetWordStarts(ptr, value);
    }

    public void setCharacterPositions(float[] value) {
        checkActive();
        nativeSetCharacterPositions(ptr, value);
    }

    public void setCharacterWidths(float[] value) {
        checkActive();
        nativeSetCharacterWidths(ptr, value);
    }

    /* 1-based. */
    public void setPositionInSet(int value) {
        checkActive();
        nativeSetPositionInSet(ptr, value);
    }

    public void setSizeOfSet(int value) {
        checkActive();
        nativeSetSizeOfSet(ptr, value);
    }

    /* The depth in a tree or the level of a heading, 1-based. */
    public void setLevel(int value) {
        checkActive();
        nativeSetLevel(ptr, value);
    }

    public void setExpanded(boolean value) {
        checkActive();
        nativeSetExpanded(ptr, value);
    }

    public void setSelected(boolean value) {
        checkActive();
        nativeSetSelected(ptr, value);
    }

    void checkActive() {
        Util.checkActive(ptr);
    }

    private static native long nativeNew(int role);

    private static native void nativeDrop(long ptr);

    private static native void nativeAddAction(long ptr, int action);

    private static native void nativeSetLabel(long ptr, byte[] value);

    private static native void nativeSetDescription(long ptr, byte[] value);

    private static native void nativeSetValue(long ptr, byte[] value);

    private static native void nativeSetRoleDescription(long ptr, byte[] value);

    private static native void nativeSetAccessKey(long ptr, byte[] value);

    private static native void nativeSetKeyboardShortcut(long ptr, byte[] value);

    private static native void nativeSetAuthorId(long ptr, byte[] value);

    private static native void nativeSetBounds(long ptr, double x0, double y0, double x1, double y1);

    private static native void nativeAddChild(long ptr, long id);

    private static native void nativeClearChildren(long ptr);
    private static native void nativeAddDescribedBy(long ptr, long id);

    private static native void nativeSetToggled(long ptr, int value);

    private static native void nativeSetLive(long ptr, int value);

    private static native void nativeSetTextDirection(long ptr, int value);

    private static native void nativeSetNumericValue(long ptr, double value);

    private static native void nativeSetMinNumericValue(long ptr, double value);

    private static native void nativeSetMaxNumericValue(long ptr, double value);

    private static native void nativeSetNumericValueStep(long ptr, double value);

    private static native void nativeSetNumericValueJump(long ptr, double value);

    private static native void nativeSetTextSelection(long ptr, long anchorNode, int anchorIndex, long focusNode, int focusIndex);

    private static native void nativeSetCharacterLengths(long ptr, byte[] value);

    private static native void nativeSetWordStarts(long ptr, byte[] value);

    private static native void nativeSetCharacterPositions(long ptr, float[] value);

    private static native void nativeSetCharacterWidths(long ptr, float[] value);

    private static native void nativeSetPositionInSet(long ptr, int value);

    private static native void nativeSetSizeOfSet(long ptr, int value);

    private static native void nativeSetLevel(long ptr, int value);

    private static native void nativeSetExpanded(long ptr, boolean value);

    private static native void nativeSetSelected(long ptr, boolean value);
}
