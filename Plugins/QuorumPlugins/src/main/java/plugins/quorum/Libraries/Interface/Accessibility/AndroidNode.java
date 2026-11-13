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
    // A text control with Quorum's focus, so TalkBack says "editing".
    public boolean focused = false;
    // A chart part's way back out (TalkBack's Actions menu), and whether it
    // moves by value (swipe up and down).
    public String escapeLabel = null;
    public boolean adjustable = false;
    // A slider (a SeekBar to TalkBack): its range and value. Its value
    // text ("40 degrees") is the state description.
    public boolean slider = false;
    public float rangeMinimum = 0;
    public float rangeMaximum = 0;
    public float rangeValue = 0;
    // A control whose tooltip is on: TalkBack can show or hide it (its
    // Actions menu). The tooltip's words, or null when they would repeat
    // the text or hint. While an interactive tooltip shows, the names of its
    // buttons, each one an action on this node.
    public boolean hasTooltip = false;
    public boolean tooltipShowing = false;
    public String tooltipText = null;
    public String[] tooltipButtons = new String[0];

    // Said in place of the text, as a View's content description is.
    public String contentDescription = null;
    // False for something that can't be used now, such as a day outside a
    // calendar's allowed range.
    public boolean enabled = true;
    // A combo box: it can open (expand) or close (collapse) its list.
    public boolean expandable = false;
    public boolean expanded = false;
    // A grid (a calendar's days): its rows and columns, or 0 for none.
    public int collectionRows = 0;
    public int collectionColumns = 0;
    // A cell in a grid: its row and column, or -1 for none.
    public int collectionRow = -1;
    public int collectionColumn = -1;
    // A calendar's day: a virtual node with no Quorum item, in its
    // calendar's node.
    public boolean calendarDay = false;

    public static final int HOST = android.view.View.NO_ID;
}
