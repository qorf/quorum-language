package plugins.quorum.Libraries.Interface.Accessibility;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

import java.util.HashMap;
import java.util.Map;

/*
    What TalkBack reads: one virtual view per accessible Quorum item, from
    the latest snapshot (AndroidAccessibility.PublishChanges). Runs on
    Android's main thread. TalkBack's focus and double taps are passed to
    the game's thread; explore by touch finds the node under the finger.
 */
public class AndroidAccessibilityProvider extends AccessibilityNodeProvider {
    public static final String TAG = "QuorumAccessibility";

    private static AndroidAccessibilityProvider provider = null;

    // The custom "Back to the group" action on chart parts.
    public static final int ACTION_CHART_ESCAPE = 0x7f0b0001;
    // Chart parts that move by value (TalkBack's Actions menu).
    public static final int ACTION_CHART_UP = 0x7f0b0002;
    public static final int ACTION_CHART_DOWN = 0x7f0b0003;
    // An interactive tooltip's buttons, on its control: the first button's
    // action, then one more for each next button.
    public static final int ACTION_TOOLTIP_BUTTON = 0x7f0b0100;
    public static final int MAXIMUM_TOOLTIP_BUTTONS = 64;

    private final View view;
    // The snapshot, by id, and the top-level ids in reading order.
    private volatile Map<Integer, AndroidNode> nodes = new HashMap<>();
    private volatile int[] rootIds = new int[0];
    // The node TalkBack's focus is on, and the one under the finger.
    private volatile int accessibilityFocusId = AndroidNode.HOST;
    private int hoverId = AndroidNode.HOST;
    // The focused item's active part, whose node has the input focus
    // (MoveToActivePart), or HOST when there is none.
    private int activePartId = AndroidNode.HOST;

    private AndroidAccessibilityProvider(View view) {
        this.view = view;
    }

    // The provider for the game's view (GLSurfaceView20 or VulkanSurfaceView).
    public static AndroidAccessibilityProvider Get(View view) {
        if (provider == null || provider.view != view) {
            provider = new AndroidAccessibilityProvider(view);
        }
        return provider;
    }

    public static AndroidAccessibilityProvider GetCurrent() {
        return provider;
    }

    public int GetAccessibilityFocusId() {
        return accessibilityFocusId;
    }

    /*
        A new snapshot, from the game thread. TalkBack is told on the main
        thread that the content changed.
     */
    public void Publish(final Map<Integer, AndroidNode> newNodes, final int[] newRootIds) {
        view.post(new Runnable() {
            public void run() {
                Map<Integer, AndroidNode> oldNodes = nodes;
                nodes = newNodes;
                rootIds = newRootIds;
                if (accessibilityFocusId != AndroidNode.HOST && !nodes.containsKey(accessibilityFocusId)) {
                    accessibilityFocusId = AndroidNode.HOST;
                }
                // A part that is gone, or no longer has the input focus (its
                // owner lost Quorum's focus), isn't the active part anymore.
                AndroidNode part = nodes.get(activePartId);
                if (activePartId != AndroidNode.HOST && (part == null || !part.focused)) {
                    activePartId = AndroidNode.HOST;
                }
                AccessibilityEvent event = new AccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
                event.setContentChangeTypes(AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE);
                Send(event, AndroidNode.HOST);
                AnnounceSliderValue(oldNodes);
            }
        });
    }

    /*
        If TalkBack is on a slider whose value changed, tells it so it
        says the new value, as it does for a SeekBar.
     */
    private void AnnounceSliderValue(Map<Integer, AndroidNode> oldNodes) {
        int id = accessibilityFocusId;
        if (id == AndroidNode.HOST) {
            return;
        }
        AndroidNode now = nodes.get(id);
        AndroidNode before = oldNodes == null ? null : oldNodes.get(id);
        if (now == null || !now.slider || before == null || before.rangeValue == now.rangeValue) {
            return;
        }
        AccessibilityEvent changed = new AccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
        changed.setContentChangeTypes(AccessibilityEvent.CONTENT_CHANGE_TYPE_STATE_DESCRIPTION);
        Send(changed, id);
    }

    /*
        Moves TalkBack to a node, for a focus change that came from Quorum
        (a new page, the program's own Focus call), from the game thread.
     */
    public void MoveTalkBackTo(final int id, final String reason) {
        view.post(new Runnable() {
            public void run() {
                if (id == accessibilityFocusId || !nodes.containsKey(id) || !IsTalkBackOn()) {
                    return;
                }
                SetAccessibilityFocus(id, "Quorum (" + reason + ")");
            }
        });
    }

    /*
        The focused item's active part changed (such as a combo box's
        highlighted row), from the game thread, after the snapshot that
        gives the part's node the input focus. TalkBack moves to the part and
        reads it, while Quorum's focus stays on the owner. With no part
        (HOST), the owner has the input focus again and TalkBack goes back
        to it.
     */
    public void MoveToActivePart(final int partId, final int ownerId) {
        view.post(new Runnable() {
            public void run() {
                activePartId = nodes.containsKey(partId) ? partId : AndroidNode.HOST;
                int id = activePartId != AndroidNode.HOST ? activePartId : ownerId;
                if (!nodes.containsKey(id)) {
                    return;
                }
                if (id != accessibilityFocusId && IsTalkBackOn()) {
                    SetAccessibilityFocus(id, "Quorum (active part)");
                }
                Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED), id);
            }
        });
    }

    @Override
    public AccessibilityNodeInfo createAccessibilityNodeInfo(int id) {
        if (id == AndroidNode.HOST) {
            AccessibilityNodeInfo info = new AccessibilityNodeInfo(view);
            view.onInitializeAccessibilityNodeInfo(info);
            for (int child : rootIds) {
                info.addChild(view, child);
            }
            return info;
        }

        AndroidNode node = nodes.get(id);
        if (node == null) {
            return null;
        }
        AccessibilityNodeInfo info = new AccessibilityNodeInfo(view, id);
        info.setPackageName(view.getContext().getPackageName());
        info.setClassName(node.className);
        info.setSource(view, id);
        if (node.parentId == AndroidNode.HOST) {
            info.setParent(view);
        } else {
            info.setParent(view, node.parentId);
        }
        for (int child : node.childIds) {
            info.addChild(view, child);
        }
        info.setBoundsInScreen(node.bounds);
        info.setVisibleToUser(true);
        info.setEnabled(node.enabled);
        info.setImportantForAccessibility(true);
        info.setText(node.text);
        info.setHintText(node.hint);
        info.setContentDescription(node.contentDescription);
        // Android 11 and later; before that, the state is in the text
        // (AndroidAccessibility.MakeNode).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            info.setStateDescription(node.stateDescription);
        }
        info.setCheckable(node.checkable);
        info.setChecked(node.checked);
        info.setSelected(node.selected);
        info.setEditable(node.editable);
        info.setHeading(node.heading);
        info.setClickable(node.clickable);
        info.setFocused(node.focused);
        info.setFocusable(node.clickable || node.editable || node.focused);
        info.setScreenReaderFocusable(node.readerFocusable);
        info.setContainerTitle(node.containerTitle);

        boolean focused = id == accessibilityFocusId;
        info.setAccessibilityFocused(focused);
        info.addAction(focused ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS
                               : AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS);
        if (node.clickable) {
            if (node.clickLabel != null) {
                info.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.ACTION_CLICK, node.clickLabel));
            } else {
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            }
        }
        if (node.editable) {
            info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_TEXT);
        }
        if (node.escapeLabel != null) {
            info.addAction(new AccessibilityNodeInfo.AccessibilityAction(ACTION_CHART_ESCAPE, node.escapeLabel));
        }
        // Not scroll actions: TalkBack sends those when a swipe reaches the
        // end of something scrollable, which pulled it back into the chart.
        if (node.adjustable) {
            info.addAction(new AccessibilityNodeInfo.AccessibilityAction(ACTION_CHART_UP, "Next by value"));
            info.addAction(new AccessibilityNodeInfo.AccessibilityAction(ACTION_CHART_DOWN, "Previous by value"));
        }
        if (node.editable && !node.focused) {
            info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_FOCUS);
        }
        // A slider: its range and value, adjusted by TalkBack's scroll
        // actions (swipe up or down, or the volume keys) or set outright.
        if (node.slider) {
            info.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(AccessibilityNodeInfo.RangeInfo.RANGE_TYPE_FLOAT,
                node.rangeMinimum, node.rangeMaximum, node.rangeValue));
            info.setFocusable(true);
            if (node.rangeValue < node.rangeMaximum) {
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            }
            if (node.rangeValue > node.rangeMinimum) {
                info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            }
            info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
        }
        // A control with a tooltip: its words as the tooltip text, and an
        // action to show or hide it, as a View with a tooltip has (Android 9
        // and later). The labels put the actions in TalkBack's Actions menu.
        if (node.hasTooltip && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info.setTooltipText(node.tooltipText);
            if (node.tooltipShowing) {
                info.addAction(new AccessibilityNodeInfo.AccessibilityAction(
                    android.R.id.accessibilityActionHideTooltip, "Hide tooltip"));
            } else {
                info.addAction(new AccessibilityNodeInfo.AccessibilityAction(
                    android.R.id.accessibilityActionShowTooltip, "Show tooltip"));
            }
        }
        // A combo box: TalkBack's Actions menu opens or closes its list.
        if (node.expandable) {
            info.addAction(node.expanded ? AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE
                                         : AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
        }
        // A grid (a calendar's days), and a cell in one: TalkBack says the
        // grid's size on entering it, and each cell's row and column.
        if (node.collectionRows > 0 && node.collectionColumns > 0) {
            info.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(
                node.collectionRows, node.collectionColumns, false));
        }
        if (node.collectionRow >= 0 && node.collectionColumn >= 0) {
            info.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(
                node.collectionRow, 1, node.collectionColumn, 1, false, node.selected));
        }
        // While an interactive tooltip shows: its buttons, by name.
        for (int i = 0; i < node.tooltipButtons.length && i < MAXIMUM_TOOLTIP_BUTTONS; i++) {
            info.addAction(new AccessibilityNodeInfo.AccessibilityAction(ACTION_TOOLTIP_BUTTON + i, node.tooltipButtons[i]));
        }
        return info;
    }

    @Override
    public boolean performAction(int id, int action, Bundle arguments) {
        if (id == AndroidNode.HOST) {
            return view.performAccessibilityAction(action, arguments);
        }
        AndroidNode node = nodes.get(id);
        if (node == null) {
            return false;
        }
        Log.d(TAG, "TalkBack action " + ActionName(action) + " on " + Describe(node, id));
        switch (action) {
            case AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS:
                SetAccessibilityFocus(id, "TalkBack");
                // The active part is already Quorum's: its owner keeps the
                // focus, so TalkBack following its input focus (or going
                // back to it) doesn't move Quorum's focus onto the part.
                if (id == activePartId) {
                    return true;
                }
                // Quorum's focus follows TalkBack, as it follows VoiceOver.
                // A calendar's day has no item: its calendar takes the focus.
                AndroidAccessibility.FocusFromTalkBack(node.calendarDay ? node.parentId : id);
                return true;
            case AccessibilityNodeInfo.ACTION_EXPAND:
                if (!node.expandable) {
                    return false;
                }
                AndroidAccessibility.ComboBoxFromTalkBack(id, true);
                return true;
            case AccessibilityNodeInfo.ACTION_COLLAPSE:
                if (!node.expandable) {
                    return false;
                }
                AndroidAccessibility.ComboBoxFromTalkBack(id, false);
                return true;
            case AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS:
                if (accessibilityFocusId == id) {
                    accessibilityFocusId = AndroidNode.HOST;
                    Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED), id);
                }
                return true;
            case ACTION_CHART_ESCAPE:
                AndroidAccessibility.ChartFromTalkBack(id, AndroidAccessibility.CHART_ESCAPE);
                return true;
            case ACTION_CHART_UP:
                AndroidAccessibility.ChartFromTalkBack(id, AndroidAccessibility.CHART_UP);
                return true;
            case ACTION_CHART_DOWN:
                AndroidAccessibility.ChartFromTalkBack(id, AndroidAccessibility.CHART_DOWN);
                return true;
            case AccessibilityNodeInfo.ACTION_FOCUS:
                // TalkBack's double tap on a field asks for input focus
                // first: the field takes it and the keyboard opens.
                if (!node.editable) {
                    return false;
                }
                AndroidAccessibility.ActivateFromTalkBack(id);
                return true;
            case AccessibilityNodeInfo.ACTION_SET_TEXT:
                if (!node.editable || arguments == null) {
                    return false;
                }
                CharSequence value = arguments.getCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE);
                AndroidAccessibility.SetTextFromTalkBack(id, value == null ? "" : value.toString());
                return true;
            case AccessibilityNodeInfo.ACTION_SCROLL_FORWARD:
                if (!node.slider) {
                    return false;
                }
                AndroidAccessibility.SliderFromTalkBack(id, AndroidAccessibility.SLIDER_STEP_UP);
                return true;
            case AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD:
                if (!node.slider) {
                    return false;
                }
                AndroidAccessibility.SliderFromTalkBack(id, AndroidAccessibility.SLIDER_STEP_DOWN);
                return true;
            case android.R.id.accessibilityActionSetProgress:
                if (!node.slider || arguments == null) {
                    return false;
                }
                AndroidAccessibility.SliderSetFromTalkBack(id,
                    arguments.getFloat(AccessibilityNodeInfo.ACTION_ARGUMENT_PROGRESS_VALUE, node.rangeValue));
                return true;
            case AccessibilityNodeInfo.ACTION_CLICK:
                if (!node.clickable || !node.enabled) {
                    return false;
                }
                if (node.calendarDay) {
                    AndroidAccessibility.CalendarDayFromTalkBack(node.parentId, node.collectionRow, node.collectionColumn);
                } else {
                    AndroidAccessibility.ActivateFromTalkBack(id);
                }
                Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED), id);
                return true;
            case android.R.id.accessibilityActionShowTooltip:
                if (!node.hasTooltip) {
                    return false;
                }
                AndroidAccessibility.TooltipFromTalkBack(id, true);
                return true;
            case android.R.id.accessibilityActionHideTooltip:
                if (!node.hasTooltip) {
                    return false;
                }
                AndroidAccessibility.TooltipFromTalkBack(id, false);
                return true;
        }
        int button = action - ACTION_TOOLTIP_BUTTON;
        if (button >= 0 && button < node.tooltipButtons.length && button < MAXIMUM_TOOLTIP_BUTTONS) {
            AndroidAccessibility.TooltipButtonFromTalkBack(id, button, node.tooltipButtons[button]);
            return true;
        }
        return false;
    }

    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        if (focus == AccessibilityNodeInfo.FOCUS_ACCESSIBILITY && accessibilityFocusId != AndroidNode.HOST) {
            return createAccessibilityNodeInfo(accessibilityFocusId);
        }
        // The active part has the input focus while its owner has Quorum's.
        if (focus == AccessibilityNodeInfo.FOCUS_INPUT && activePartId != AndroidNode.HOST) {
            return createAccessibilityNodeInfo(activePartId);
        }
        return null;
    }

    /*
        Explore by touch: TalkBack sends hovers, not touches. Says which node
        is under the finger; TalkBack then moves its focus there. Nothing is
        pressed. Returns false when TalkBack is off.
     */
    public boolean OnHover(MotionEvent event) {
        if (!IsTalkBackOn()) {
            return false;
        }
        int id = FindNodeAt((int) event.getRawX(), (int) event.getRawY());
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_HOVER_ENTER:
            case MotionEvent.ACTION_HOVER_MOVE:
                SetHover(id);
                return true;
            case MotionEvent.ACTION_HOVER_EXIT:
                SetHover(AndroidNode.HOST);
                return true;
        }
        return false;
    }

    private void SetHover(int id) {
        if (id == hoverId) {
            return;
        }
        int old = hoverId;
        hoverId = id;
        if (id != AndroidNode.HOST) {
            Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_HOVER_ENTER), id);
        }
        if (old != AndroidNode.HOST) {
            Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_HOVER_EXIT), old);
        }
    }

    // The deepest node whose bounds hold the point (screen pixels).
    private int FindNodeAt(int x, int y) {
        int found = AndroidNode.HOST;
        int[] level = rootIds;
        boolean deeper = true;
        while (deeper) {
            deeper = false;
            for (int i = level.length - 1; i >= 0; i--) {
                AndroidNode node = nodes.get(level[i]);
                if (node != null && node.bounds.contains(x, y)) {
                    found = node.id;
                    level = node.childIds;
                    deeper = true;
                    break;
                }
            }
        }
        return found;
    }

    private void SetAccessibilityFocus(int id, String who) {
        int old = accessibilityFocusId;
        accessibilityFocusId = id;
        if (old != AndroidNode.HOST && old != id) {
            Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED), old);
        }
        AndroidNode node = nodes.get(id);
        Log.d(TAG, who + " moved to " + Describe(node, id));
        Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED), id);
    }

    // A message TalkBack says now (Quorum's Notify), from the game thread.
    public void Announce(final String value) {
        view.post(new Runnable() {
            public void run() {
                AccessibilityEvent event = new AccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT);
                event.getText().add(value);
                Send(event, AndroidNode.HOST);
            }
        });
    }

    private boolean IsTalkBackOn() {
        AccessibilityManager manager = (AccessibilityManager) view.getContext().getSystemService(android.content.Context.ACCESSIBILITY_SERVICE);
        return manager != null && manager.isEnabled() && manager.isTouchExplorationEnabled();
    }

    // A node for the log: class, text (or the hint when there's none), and
    // where it is on the screen.
    private String Describe(AndroidNode node, int id) {
        if (node == null) {
            return "" + id;
        }
        String shown = node.text != null ? node.text : node.contentDescription;
        String words = "\"" + (shown == null ? "" : shown) + "\"";
        if (node.text == null && node.hint != null) {
            words = words + " (" + node.hint + ")";
        }
        return node.className + " " + words + " at " + node.bounds.toShortString();
    }

    private String ActionName(int action) {
        switch (action) {
            case AccessibilityNodeInfo.ACTION_CLICK: return "click";
            case AccessibilityNodeInfo.ACTION_FOCUS: return "focus";
            case AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS: return "accessibility focus";
            case AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS: return "clear accessibility focus";
            case AccessibilityNodeInfo.ACTION_SET_TEXT: return "set text";
            case AccessibilityNodeInfo.ACTION_EXPAND: return "expand";
            case AccessibilityNodeInfo.ACTION_COLLAPSE: return "collapse";
            case ACTION_CHART_ESCAPE: return "back to the group";
            case ACTION_CHART_UP: return "next by value";
            case ACTION_CHART_DOWN: return "previous by value";
            case AccessibilityNodeInfo.ACTION_SCROLL_FORWARD: return "scroll forward (slider up)";
            case AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD: return "scroll backward (slider down)";
            case android.R.id.accessibilityActionSetProgress: return "set progress";
            case android.R.id.accessibilityActionShowTooltip: return "show tooltip";
            case android.R.id.accessibilityActionHideTooltip: return "hide tooltip";
        }
        if (action >= ACTION_TOOLTIP_BUTTON && action < ACTION_TOOLTIP_BUTTON + MAXIMUM_TOOLTIP_BUTTONS) {
            return "tooltip button " + (action - ACTION_TOOLTIP_BUTTON + 1);
        }
        return "" + action;
    }

    private void Send(AccessibilityEvent event, int id) {
        // Android throws when no accessibility service is on.
        AccessibilityManager manager = (AccessibilityManager) view.getContext().getSystemService(android.content.Context.ACCESSIBILITY_SERVICE);
        if (manager == null || !manager.isEnabled()) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent == null) {
            return;
        }
        event.setPackageName(view.getContext().getPackageName());
        if (id == AndroidNode.HOST) {
            event.setSource(view);
        } else {
            event.setSource(view, id);
        }
        AndroidNode node = nodes.get(id);
        if (node != null) {
            event.setClassName(node.className);
            if (node.text != null) {
                event.getText().add(node.text);
            }
            if (node.contentDescription != null) {
                event.setContentDescription(node.contentDescription);
            }
        }
        parent.requestSendAccessibilityEvent(view, event);
    }
}
