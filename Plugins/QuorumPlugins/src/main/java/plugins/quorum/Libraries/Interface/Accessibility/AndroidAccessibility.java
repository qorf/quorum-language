package plugins.quorum.Libraries.Interface.Accessibility;

import android.graphics.Rect;
import plugins.quorum.Libraries.Game.AndroidDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.Control_;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.Tree_;
import quorum.Libraries.Interface.Controls.TreeItem_;
import quorum.Libraries.Interface.Forms.Banner_;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Interface.Controls.List_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
    Quorum's accessibility on Android, for TalkBack. Accessible items are
    kept here, on the game's thread, in the order they were added (reading
    order, as on iOS). After a change, PublishChanges copies them into
    AndroidNodes for AndroidAccessibilityProvider, which TalkBack reads on
    Android's main thread.
 */
public class AndroidAccessibility {
    public Object me_ = null;

    private static AndroidAccessibility instance = null;

    private final Map<Integer, Item_> items = new LinkedHashMap<>();
    private boolean changed = true;
    // Quorum's focus moved, and TalkBack should follow (not when TalkBack
    // moved it).
    private Item_ focusToFollow = null;
    private int focusFromTalkBack = AndroidNode.HOST;
    // Where Quorum's focus ended up after TalkBack moved it: a control may
    // pass it on (a radio button to its group, a tree item to its tree).
    private int focusAfterTalkBack = AndroidNode.HOST;

    public AndroidAccessibility() {
        instance = this;
    }

    public boolean NativeAdd(Item_ item) {
        if (item == null) {
            return false;
        }
        int code = item.GetAccessibilityCode();
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_()) {
            return false;
        }
        items.put(item.GetHashCode(), item);
        changed = true;
        return true;
    }

    public boolean NativeRemove(Item_ item) {
        if (item == null) {
            return false;
        }
        changed = true;
        return items.remove(item.GetHashCode()) != null;
    }

    public void NameChanged(Item_ item) { changed = true; }
    public void DescriptionChanged(Item_ item) { changed = true; }
    public void BoundsChanged(Item_ item) { changed = true; }
    public void ToggleButtonToggled(ToggleButton_ button) { changed = true; }
    public void SelectionChanged(SelectionEvent_ event) { changed = true; }
    public void TextChanged(TextChangeEvent_ event) { changed = true; }
    public void ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) { changed = true; }
    public void TreeChanged(TreeChangeEvent_ event) { changed = true; }
    public void MenuChanged(MenuChangeEvent_ event) { changed = true; }
    public void TreeTableChanged(TreeTableChangeEvent_ event) { changed = true; }
    public void ControlActivated(ControlActivationEvent_ event) { changed = true; }
    public void ButtonActivated(Button_ button) { }
    public void TextFieldUpdatePassword(TextField_ field) { changed = true; }
    public void TextSelectionChanged(TextBoxSelection_ selection) { }
    public void TextSelectionChanged(TextFieldSelection_ selection) { }
    public void WindowFocusChanged(WindowFocusEvent_ event) { }
    public boolean Select(Item_ item) { return false; }
    public void Shutdown() { }

    public void FocusChanged(FocusEvent_ event) {
        Item_ item = event.Get_Libraries_Interface_Events_FocusEvent__gainedFocus_();
        if (item == null || !items.containsKey(item.GetHashCode())) {
            return;
        }
        // TalkBack's own move, or where Quorum passed it on: TalkBack stays.
        int gained = item.GetHashCode();
        if (gained == focusFromTalkBack || gained == focusAfterTalkBack) {
            focusFromTalkBack = AndroidNode.HOST;
            focusAfterTalkBack = AndroidNode.HOST;
            return;
        }
        // TalkBack is on something inside the newly focused control (a tree
        // item in its tree), as on iOS: it's already there.
        AndroidAccessibilityProvider provider = AndroidAccessibilityProvider.GetCurrent();
        Item_ current = provider == null ? null : items.get(provider.GetAccessibilityFocusId());
        if (current != null && (current == item || IsInside(current, item))) {
            return;
        }
        focusToFollow = item;
    }

    public void Notify(Item_ item, String value) {
        Notify(item, value, 0);
    }

    // Android 16 has no replacement for announcements yet; TalkBack still
    // speaks them.
    public void Notify(Item_ item, String value, int notificationType) {
        AndroidAccessibilityProvider provider = AndroidAccessibilityProvider.GetCurrent();
        if (provider != null && value != null) {
            provider.Announce(value);
        }
    }

    public boolean IsScreenReaderListening() {
        android.view.accessibility.AccessibilityManager manager = plugins.quorum.Libraries.Game.AndroidApplication.accessibilityManager;
        return manager != null && manager.isEnabled() && manager.isTouchExplorationEnabled();
    }

    /*
        Once a frame, on the game thread (AndroidAccessibility.quorum's
        Update): after a change, the snapshot TalkBack reads is remade, and
        then TalkBack follows a focus change that came from Quorum.
     */
    public void PublishChanges() {
        AndroidAccessibilityProvider provider = AndroidAccessibilityProvider.GetCurrent();
        if (provider == null) {
            return;
        }
        if (changed) {
            changed = false;
            BuildSnapshot(provider);
        }
        if (focusToFollow != null) {
            provider.MoveTalkBackTo(focusToFollow.GetHashCode());
            focusToFollow = null;
        }
    }

    private void BuildSnapshot(AndroidAccessibilityProvider provider) {
        Map<Integer, AndroidNode> nodes = new HashMap<>();
        Map<Integer, List<Integer>> children = new HashMap<>();
        List<Integer> roots = new ArrayList<>();

        List<Item_> all = new ArrayList<>(items.values());
        for (Item_ item : all) {
            if (!item.IsShowing()) {
                continue;
            }
            AndroidNode node = MakeNode(item);
            nodes.put(node.id, node);
        }
        for (Item_ item : all) {
            AndroidNode node = nodes.get(item.GetHashCode());
            if (node == null) {
                continue;
            }
            node.parentId = FindParent(item, nodes);
            if (node.parentId == AndroidNode.HOST) {
                roots.add(node.id);
            } else {
                List<Integer> list = children.get(node.parentId);
                if (list == null) {
                    list = new ArrayList<>();
                    children.put(node.parentId, list);
                }
                list.add(node.id);
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : children.entrySet()) {
            nodes.get(entry.getKey()).childIds = ToArray(entry.getValue());
        }
        // A tree's and a tree item's children go in the tree's order (items
        // are added as days open, so add order is wrong).
        for (Item_ item : all) {
            AndroidNode node = nodes.get(item.GetHashCode());
            if (node == null) {
                continue;
            }
            if (item instanceof Tree_) {
                node.childIds = InOrder(((Tree_) item).GetTreeItems(), nodes);
            } else if (item instanceof TreeItem_) {
                node.childIds = InOrder(((TreeItem_) item).GetTreeItems(), nodes);
            }
        }
        provider.Publish(nodes, ToArray(roots));
    }

    /*
        The node an item goes in: a list item's list, or its nearest ancestor
        that is a container (a group, list, tree, chart, dialog or tab pane)
        and showing. HOST is the top level.
     */
    private int FindParent(Item_ item, Map<Integer, AndroidNode> nodes) {
        // A tree item is in its parent item, or its tree.
        if (item instanceof TreeItem_) {
            TreeItem_ treeItem = (TreeItem_) item;
            Item_ owner = treeItem.GetParentTreeItem() != null ? treeItem.GetParentTreeItem() : treeItem.GetTree();
            if (owner != null && nodes.containsKey(owner.GetHashCode())) {
                return owner.GetHashCode();
            }
        }
        if (item instanceof ListItem_) {
            List_ list = ((ListItem_) item).GetList();
            if (list != null && nodes.containsKey(list.GetHashCode())) {
                return list.GetHashCode();
            }
        }
        Item2D_ ancestor = item instanceof Item2D_ ? ((Item2D_) item).GetParent() : null;
        while (ancestor != null) {
            if (nodes.containsKey(ancestor.GetHashCode()) && IsContainer(ancestor)) {
                return ancestor.GetHashCode();
            }
            ancestor = ancestor.GetParent();
        }
        return AndroidNode.HOST;
    }

    private boolean IsContainer(Item_ item) {
        int code = item.GetAccessibilityCode();
        return code == item.Get_Libraries_Interface_Item__GROUP_()
            || code == item.Get_Libraries_Interface_Item__LIST_()
            || code == item.Get_Libraries_Interface_Item__TREE_()
            || code == item.Get_Libraries_Interface_Item__CHART_()
            || code == item.Get_Libraries_Interface_Item__DIALOG_()
            || code == item.Get_Libraries_Interface_Item__TAB_PANE_();
    }

    private AndroidNode MakeNode(Item_ item) {
        AndroidNode node = new AndroidNode();
        node.id = item.GetHashCode();
        node.text = Clean(item.GetName());
        String description = Clean(item.GetDescription());
        node.hint = description != null && !description.equals(node.text) ? description : null;
        node.bounds = BoundsOf(item);

        int code = item.GetAccessibilityCode();
        if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            node.className = "android.widget.Button";
            node.clickable = true;
        } else if (code == item.Get_Libraries_Interface_Item__CHECKBOX_()) {
            node.className = "android.widget.CheckBox";
            node.clickable = true;
            node.checkable = true;
            node.checked = ((ToggleButton_) item).GetToggleState();
        } else if (code == item.Get_Libraries_Interface_Item__RADIO_BUTTON_()) {
            node.className = "android.widget.RadioButton";
            node.clickable = true;
            node.checkable = true;
            node.checked = ((ToggleButton_) item).GetToggleState();
        } else if (code == item.Get_Libraries_Interface_Item__TOGGLE_BUTTON_()) {
            // A button that stays on or off, as on iOS, not a switch.
            node.className = "android.widget.Button";
            node.clickable = true;
            node.checkable = true;
            node.checked = ((ToggleButton_) item).GetToggleState();
            node.stateDescription = node.checked ? "on" : "off";
        } else if (code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()) {
            node.className = "android.widget.EditText";
            node.clickable = true;
            node.editable = true;
            node.hint = node.text;
            node.text = Clean(((TextField_) item).GetText());
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()) {
            node.className = "android.widget.EditText";
            node.clickable = true;
            node.editable = true;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_()) {
            node.className = "android.widget.ListView";
        } else if (code == item.Get_Libraries_Interface_Item__LIST_ITEM_()) {
            ListItem_ listItem = (ListItem_) item;
            node.className = "android.view.View";
            node.text = Clean(listItem.GetText());
            node.clickable = true;
            node.clickLabel = "select";
            // Selected as iOS decides it: the list's selection is this item.
            List_ list = listItem.GetList();
            node.selected = list != null && list.GetSelection() != null && list.GetSelection().GetListItem() == listItem;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_ITEM_()) {
            TreeItem_ treeItem = (TreeItem_) item;
            node.className = "android.view.View";
            node.clickable = true;
            if (treeItem.GetSize() > 0) {
                node.stateDescription = treeItem.IsOpen() ? "expanded" : "collapsed";
                node.clickLabel = treeItem.IsOpen() ? "collapse" : "expand";
            } else {
                node.clickLabel = "select";
            }
        } else if (code == item.Get_Libraries_Interface_Item__TAB_()
                || code == item.Get_Libraries_Interface_Item__MENU_ITEM_()) {
            node.className = "android.view.View";
            node.clickable = true;
        } else if (code == item.Get_Libraries_Interface_Item__PROGRESS_BAR_()) {
            node.className = "android.widget.ProgressBar";
        } else if (code == item.Get_Libraries_Interface_Item__LABEL_()) {
            // A label's text is its description (iOS's LabelIOS); its name
            // is for the program ("Explore Title Label").
            node.className = "android.widget.TextView";
            String words = Clean(item.GetDescription());
            node.text = words != null ? words : node.text;
            node.hint = null;
        } else if (code == item.Get_Libraries_Interface_Item__GROUP_()) {
            node.className = "android.view.ViewGroup";
            node.containerTitle = item instanceof Banner_ ? null : node.text;
            node.text = null;
            node.hint = null;
            node.readerFocusable = false;
        }
        return node;
    }

    /*
        An item's place on the screen, in pixels from the top left. Quorum
        counts y from the bottom, in the same pixels (AndroidInput flips
        touches the same way).
     */
    private Rect BoundsOf(Item_ item) {
        if (!(item instanceof Item2D_)) {
            return new Rect();
        }
        Item2D_ item2D = (Item2D_) item;
        double x = item2D.GetScreenX();
        double y = item2D.GetScreenY();
        if (Double.isNaN(x)) x = 0;
        if (Double.isNaN(y)) y = 0;
        int screenHeight = GameStateManager.display.GetHeight();
        int left = (int) x;
        int bottom = screenHeight - (int) y;
        int right = (int) (x + item2D.GetWidth());
        int top = screenHeight - (int) (y + item2D.GetHeight());
        return new Rect(left, top, right, bottom);
    }

    private String Clean(String value) {
        if (value == null) {
            return null;
        }
        value = value.trim();
        return value.isEmpty() ? null : value;
    }

    private int[] InOrder(Iterator_ iterator, Map<Integer, AndroidNode> nodes) {
        List<Integer> ids = new ArrayList<>();
        while (iterator.HasNext()) {
            Object next = iterator.Next();
            if (next instanceof Item_ && nodes.containsKey(((Item_) next).GetHashCode())) {
                ids.add(((Item_) next).GetHashCode());
            }
        }
        return ToArray(ids);
    }

    private boolean IsInside(Item_ item, Item_ ancestor) {
        Item2D_ parent = item instanceof Item2D_ ? ((Item2D_) item).GetParent() : null;
        while (parent != null) {
            if (parent == ancestor) {
                return true;
            }
            parent = parent.GetParent();
        }
        return false;
    }

    private int[] ToArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /*
        TalkBack put its focus on a node: Quorum's focus follows on the game
        thread (as it follows VoiceOver on iOS), without moving TalkBack back.
     */
    static void FocusFromTalkBack(final int id) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (item != null) {
                    self.focusFromTalkBack = id;
                    item.Focus();
                    Item_ result = GameStateManager.game == null ? null : GameStateManager.game.GetFocus();
                    self.focusAfterTalkBack = result == null ? AndroidNode.HOST : result.GetHashCode();
                }
            }
        });
    }

    // A double tap: the item's own activation, as on iOS.
    static void ActivateFromTalkBack(final int id) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                // As on iOS: a tree item with children opens or closes, one
                // without and a list item are selected, anything else is
                // activated.
                if (item instanceof TreeItem_) {
                    TreeItem_ treeItem = (TreeItem_) item;
                    if (treeItem.GetSize() > 0) {
                        treeItem.ToggleOpen();
                    } else {
                        treeItem.Select();
                    }
                } else if (item instanceof ListItem_) {
                    ((ListItem_) item).Select();
                } else if (item instanceof Control_) {
                    ((Control_) item).Activate();
                }
                self.changed = true;
            }
        });
    }
}
