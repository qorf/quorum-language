package plugins.quorum.Libraries.Interface.Accessibility;

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

    private final View view;
    // The snapshot, by id, and the top-level ids in reading order.
    private volatile Map<Integer, AndroidNode> nodes = new HashMap<>();
    private volatile int[] rootIds = new int[0];
    // The node TalkBack's focus is on, and the one under the finger.
    private volatile int accessibilityFocusId = AndroidNode.HOST;
    private int hoverId = AndroidNode.HOST;

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
                nodes = newNodes;
                rootIds = newRootIds;
                if (accessibilityFocusId != AndroidNode.HOST && !nodes.containsKey(accessibilityFocusId)) {
                    accessibilityFocusId = AndroidNode.HOST;
                }
                AccessibilityEvent event = new AccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
                event.setContentChangeTypes(AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE);
                Send(event, AndroidNode.HOST);
            }
        });
    }

    /*
        Moves TalkBack to a node, for a focus change that came from Quorum
        (a new page, the program's own Focus call), from the game thread.
     */
    public void MoveTalkBackTo(final int id) {
        view.post(new Runnable() {
            public void run() {
                if (id == accessibilityFocusId || !nodes.containsKey(id) || !IsTalkBackOn()) {
                    return;
                }
                SetAccessibilityFocus(id);
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
        info.setEnabled(true);
        info.setImportantForAccessibility(true);
        info.setText(node.text);
        info.setHintText(node.hint);
        info.setStateDescription(node.stateDescription);
        info.setCheckable(node.checkable);
        info.setChecked(node.checked);
        info.setSelected(node.selected);
        info.setEditable(node.editable);
        info.setHeading(node.heading);
        info.setClickable(node.clickable);
        info.setFocusable(node.clickable || node.editable);
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
        switch (action) {
            case AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS:
                SetAccessibilityFocus(id);
                // Quorum's focus follows TalkBack, as it follows VoiceOver.
                AndroidAccessibility.FocusFromTalkBack(id);
                return true;
            case AccessibilityNodeInfo.ACTION_CLEAR_ACCESSIBILITY_FOCUS:
                if (accessibilityFocusId == id) {
                    accessibilityFocusId = AndroidNode.HOST;
                    Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED), id);
                }
                return true;
            case AccessibilityNodeInfo.ACTION_CLICK:
                if (!node.clickable) {
                    return false;
                }
                Log.d(TAG, "TalkBack double tap on \"" + node.text + "\"");
                AndroidAccessibility.ActivateFromTalkBack(id);
                Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED), id);
                return true;
        }
        return false;
    }

    @Override
    public AccessibilityNodeInfo findFocus(int focus) {
        if (focus == AccessibilityNodeInfo.FOCUS_ACCESSIBILITY && accessibilityFocusId != AndroidNode.HOST) {
            return createAccessibilityNodeInfo(accessibilityFocusId);
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

    private void SetAccessibilityFocus(int id) {
        int old = accessibilityFocusId;
        accessibilityFocusId = id;
        if (old != AndroidNode.HOST && old != id) {
            Send(new AccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED), old);
        }
        AndroidNode node = nodes.get(id);
        Log.d(TAG, "TalkBack moved to " + (node == null ? id : node.className + " \"" + node.text + "\""));
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

    private void Send(AccessibilityEvent event, int id) {
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
        }
        parent.requestSendAccessibilityEvent(view, event);
    }
}
