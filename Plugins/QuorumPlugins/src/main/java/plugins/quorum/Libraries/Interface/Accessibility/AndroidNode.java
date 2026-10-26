package plugins.quorum.Libraries.Interface.Accessibility;

import android.graphics.Rect;

/*
    One accessible item as TalkBack sees it, copied from Quorum's item on the
    game thread (AndroidAccessibility.PublishChanges) and read on Android's
    main thread (AndroidAccessibilityProvider). It never changes after it is
    made, so the two threads can share it.
 */
public class AndroidNode {
    // The item's hash code; the virtual view's id.
    public int id;
    // The containing node's id, or HOST for the top level.
    public int parentId;
    public int[] childIds = new int[0];

    public String className = "android.view.View";
    public String text = null;
    public String hint = null;
    public String stateDescription = null;
    public Rect bounds = new Rect();

    public boolean clickable = false;
    public boolean checkable = false;
    public boolean checked = false;
    public boolean selected = false;
    public boolean editable = false;
    public boolean heading = false;

    // A group's title, said when TalkBack enters it; the group itself isn't
    // a stop (iOS's GroupIOS isn't an element either).
    public String containerTitle = null;
    public boolean readerFocusable = true;
    // What a double tap does, said as "double tap to …".
    public String clickLabel = null;

    public static final int HOST = android.view.View.NO_ID;
}
