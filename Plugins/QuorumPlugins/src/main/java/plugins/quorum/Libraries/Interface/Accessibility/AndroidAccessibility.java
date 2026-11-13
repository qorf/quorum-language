package plugins.quorum.Libraries.Interface.Accessibility;

import android.graphics.Rect;
import android.os.Build;
import plugins.quorum.Libraries.Game.AndroidDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.Calendar_;
import quorum.Libraries.Interface.Controls.ColorWell_;
import quorum.Libraries.Interface.Controls.ComboBox_;
import quorum.Libraries.Interface.Controls.Control_;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.RadioButton_;
import quorum.Libraries.Interface.Controls.Slider_;
import quorum.Libraries.Interface.Controls.Tooltip_;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Interface.Controls.Tree_;
import quorum.Libraries.Interface.Controls.TreeItem_;
import quorum.Libraries.Interface.Forms.Banner_;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Interface.Controls.List_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.Charts.Chart_;
import quorum.Libraries.Interface.Controls.Charts.ChartItem_;
import plugins.quorum.Libraries.Interface.Mobile.AndroidKeyboard;
import plugins.quorum.Libraries.Interface.Mobile.AndroidTextInput;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.System.CalendarDate_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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

    // What TalkBack asked a chart part to do (ChartFromTalkBack).
    static final int CHART_ESCAPE = 0;
    static final int CHART_UP = 1;
    static final int CHART_DOWN = 2;

    // What TalkBack asks of a slider (SliderFromTalkBack).
    static final int SLIDER_STEP_UP = 0;
    static final int SLIDER_STEP_DOWN = 1;

    private final Map<Integer, Item_> items = new LinkedHashMap<>();
    private boolean changed = true;
    // Quorum's focus moved, and TalkBack should follow (not when TalkBack
    // moved it).
    private Item_ focusToFollow = null;
    // Why TalkBack is moved there, for the log.
    private String followReason = "";
    private int focusFromTalkBack = AndroidNode.HOST;
    // Where Quorum's focus ended up after TalkBack moved it: a control may
    // pass it on (a radio button to its group, a tree item to its tree).
    private int focusAfterTalkBack = AndroidNode.HOST;
    // The tooltip that shows, between TooltipShown and TooltipHidden.
    private Tooltip_ openTooltip = null;

    // A calendar's grid, as Calendar.quorum lays it out (its WIDTH, HEIGHT,
    // PADDING and CELL_SIZE): the days fill 7 by 6 cells in the bottom left,
    // a padding in from the edges.
    static final int CALENDAR_ROWS = 6;
    static final int CALENDAR_COLUMNS = 7;
    private static final double CALENDAR_WIDTH = 268;
    private static final double CALENDAR_HEIGHT = 292;
    private static final double CALENDAR_PADDING = 8;
    private static final double CALENDAR_CELL_SIZE = 36;

    // A calendar's days are virtual nodes with no item. Each calendar gets a
    // block of ids, kept while it is accessible, so TalkBack stays on the
    // same place in the grid when the snapshot is remade: the block's first
    // id plus row * 7 + column. Blocks start far above item ids (hash codes,
    // which Android keeps below 2 to the 28th).
    private static final int FIRST_CALENDAR_DAY_ID = 0x70000000;
    private static final int CALENDAR_DAY_BLOCK = 64;
    private final Map<Integer, Integer> calendarDayIds = new HashMap<>();
    private int nextCalendarDayId = FIRST_CALENDAR_DAY_ID;
    // A calendar whose current day moved, so TalkBack may follow it.
    private Calendar_ calendarToFollow = null;

    // The focused item's active part (Item:GetActiveDescendant, such as a
    // combo box's highlighted row) and that item, its owner. While there is
    // one, the part's node has the input focus in place of its owner's.
    // HOST when there is none.
    private int activePartId = AndroidNode.HOST;
    private int activeOwnerId = AndroidNode.HOST;
    // The active part changed: TalkBack moves to it, or back to its owner.
    private boolean activePartToFollow = false;

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
        calendarDayIds.remove(item.GetHashCode());
        if (calendarToFollow == item) {
            calendarToFollow = null;
        }
        if (activePartId == item.GetHashCode()) {
            activePartId = AndroidNode.HOST;
        }
        return items.remove(item.GetHashCode()) != null;
    }

    /*
        The focused item's active part changed (Accessibility's
        UpdateActiveDescendant). A part with no node yet is added first, as
        on the Mac. The part's node gets the input focus on the next
        snapshot, and then TalkBack moves to it, so it reads the part as the
        keys move it, while Quorum's focus stays on the owner. With no part
        (or one that can't be added), both go back to the owner.
     */
    public void NativeActiveDescendantChanged(Item_ focus, Item_ active) {
        if (focus == null) {
            return;
        }
        activeOwnerId = focus.GetHashCode();
        activePartId = AndroidNode.HOST;
        if (active != null && active != focus) {
            boolean known = items.containsKey(active.GetHashCode());
            if (!known) {
                known = NativeAdd(active);
            }
            if (known) {
                activePartId = active.GetHashCode();
            }
        }
        activePartToFollow = true;
        changed = true;
    }

    /*
        A calendar changed: its current day moved, its month changed, or a
        day was chosen. The days are made again, and if the calendar has the
        focus and TalkBack is on one of its days (or on nothing), TalkBack
        follows the current day, as Quorum's focus moves TalkBack.
     */
    public void NativeCalendarChanged(Calendar_ calendar) {
        changed = true;
        if (calendar != null) {
            calendarToFollow = calendar;
        }
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

    /*
        A tooltip opened (after its dialog showed): its control's node gets
        the tooltip's buttons as actions on the next snapshot.
     */
    public void NativeTooltipShown(Tooltip_ tooltip) {
        openTooltip = tooltip;
        changed = true;
    }

    /*
        A tooltip is closing (before its dialog hides): the next snapshot,
        made after it closed, drops its buttons from its control's node.
     */
    public void NativeTooltipHidden(Tooltip_ tooltip) {
        if (openTooltip == tooltip) {
            openTooltip = null;
        }
        changed = true;
    }
    public void Shutdown() { }

    public void FocusChanged(FocusEvent_ event) {
        Item_ item = event.Get_Libraries_Interface_Events_FocusEvent__gainedFocus_();
        // The keyboard goes when the focus leaves a text control.
        if (AndroidTextInput.IsActive() && !(item instanceof TextField_) && !(item instanceof TextBox_)) {
            AndroidTextInput.Hide();
        }
        // The old owner's active part loses the input focus. A new active
        // part is found in this frame's Update (NativeActiveDescendantChanged).
        if (activeOwnerId != AndroidNode.HOST && (item == null || item.GetHashCode() != activeOwnerId)) {
            activePartId = AndroidNode.HOST;
            activeOwnerId = AndroidNode.HOST;
            changed = true;
        }
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
        followReason = "focus changed";
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
        // TalkBack goes straight to a newly focused item's active part, not
        // to the item first.
        if (activePartToFollow && focusToFollow != null && focusToFollow.GetHashCode() == activeOwnerId) {
            focusToFollow = null;
        }
        if (focusToFollow != null) {
            // A calendar's node is only its days' grid: TalkBack goes to
            // the current day.
            if (focusToFollow instanceof Calendar_) {
                int day = CurrentDayId((Calendar_) focusToFollow);
                if (day != AndroidNode.HOST) {
                    provider.MoveTalkBackTo(day, followReason);
                }
            } else {
                provider.MoveTalkBackTo(focusToFollow.GetHashCode(), followReason);
            }
            focusToFollow = null;
        }
        if (activePartToFollow) {
            activePartToFollow = false;
            if (activeOwnerId != AndroidNode.HOST) {
                provider.MoveToActivePart(activePartId, activeOwnerId);
            }
        }
        if (calendarToFollow != null) {
            FollowCalendar(provider, calendarToFollow);
            calendarToFollow = null;
        }
    }

    private void FollowCalendar(AndroidAccessibilityProvider provider, Calendar_ calendar) {
        if (!HasFocus(calendar)) {
            return;
        }
        int day = CurrentDayId(calendar);
        if (day == AndroidNode.HOST) {
            return;
        }
        int on = provider.GetAccessibilityFocusId();
        Integer first = calendarDayIds.get(calendar.GetHashCode());
        boolean onDays = first != null && on >= first && on < first + CALENDAR_ROWS * CALENDAR_COLUMNS;
        if (on == AndroidNode.HOST || onDays) {
            provider.MoveTalkBackTo(day, "calendar day");
        }
    }

    // The node id of a calendar's current day, or HOST when it isn't shown.
    private int CurrentDayId(Calendar_ calendar) {
        Integer first = calendarDayIds.get(calendar.GetHashCode());
        CalendarDate_ topLeft = calendar.GetShownDay(0, 0);
        CalendarDate_ current = calendar.GetCurrentDay();
        if (first == null || topLeft == null || current == null) {
            return AndroidNode.HOST;
        }
        int index = topLeft.DaysUntil(current);
        if (index < 0 || index >= CALENDAR_ROWS * CALENDAR_COLUMNS) {
            return AndroidNode.HOST;
        }
        return first + index;
    }

    // The first id of a calendar's block of day ids, given out the first time.
    private int CalendarDayBase(Calendar_ calendar) {
        Integer first = calendarDayIds.get(calendar.GetHashCode());
        if (first == null) {
            first = nextCalendarDayId;
            nextCalendarDayId = nextCalendarDayId + CALENDAR_DAY_BLOCK;
            calendarDayIds.put(calendar.GetHashCode(), first);
        }
        return first;
    }

    /*
        A calendar's 42 days, as nodes in its grid node: each with its row and
        column, the date in words (and "today"), selected when it is the
        chosen day, and
        not enabled outside the allowed range. Their bounds are the calendar's
        cells, from its layout. Returns their ids in reading order.
     */
    private int[] AddCalendarDays(Calendar_ calendar, AndroidNode grid, Map<Integer, AndroidNode> nodes) {
        int first = CalendarDayBase(calendar);
        CalendarDate_ chosen = calendar.GetDate();
        // Today, read once for all the days.
        quorum.Libraries.System.CalendarDate today = new quorum.Libraries.System.CalendarDate();
        today.SetToToday();
        Rect bounds = grid.bounds;
        double width = bounds.width();
        double height = bounds.height();
        double gridLeft = bounds.left + width * CALENDAR_PADDING / CALENDAR_WIDTH;
        double gridBottom = bounds.bottom - height * CALENDAR_PADDING / CALENDAR_HEIGHT;
        double cellWidth = width * CALENDAR_CELL_SIZE / CALENDAR_WIDTH;
        double cellHeight = height * CALENDAR_CELL_SIZE / CALENDAR_HEIGHT;
        double gridTop = gridBottom - cellHeight * CALENDAR_ROWS;

        int[] ids = new int[CALENDAR_ROWS * CALENDAR_COLUMNS];
        for (int row = 0; row < CALENDAR_ROWS; row++) {
            for (int column = 0; column < CALENDAR_COLUMNS; column++) {
                int index = row * CALENDAR_COLUMNS + column;
                CalendarDate_ date = calendar.GetShownDay(row, column);
                AndroidNode day = new AndroidNode();
                day.id = first + index;
                day.parentId = grid.id;
                day.calendarDay = true;
                day.className = "android.view.View";
                day.collectionRow = row;
                day.collectionColumn = column;
                // The date in words, and "today": not the calendar's own words
                // for selected and unavailable, which TalkBack already says
                // from the node's selected and enabled states.
                String words = date == null ? null : date.ToSpokenText();
                if (words != null && date.Equals(today)) {
                    words = words + ", today";
                }
                day.contentDescription = Clean(words);
                day.selected = chosen != null && date != null && date.Equals(chosen);
                day.enabled = calendar.IsAvailable(date);
                day.clickable = true;
                day.clickLabel = "choose";
                day.bounds = new Rect(
                    (int) Math.round(gridLeft + column * cellWidth),
                    (int) Math.round(gridTop + row * cellHeight),
                    (int) Math.round(gridLeft + (column + 1) * cellWidth),
                    (int) Math.round(gridTop + (row + 1) * cellHeight));
                nodes.put(day.id, day);
                ids[index] = day.id;
            }
        }
        return ids;
    }

    private void BuildSnapshot(AndroidAccessibilityProvider provider) {
        Map<Integer, AndroidNode> nodes = new HashMap<>();
        Map<Integer, List<Integer>> children = new HashMap<>();
        List<Integer> roots = new ArrayList<>();

        List<Item_> all = new ArrayList<>(items.values());

        // A chart shows one level at a time: the ring of its selected part,
        // as on iOS. Parts outside it aren't nodes.
        Set<Item_> chartParts = new HashSet<>();
        for (Item_ item : all) {
            if (item instanceof Chart_) {
                ChartItem_ selected = SelectedPart((Chart_) item);
                if (selected != null && selected != item) {
                    chartParts.addAll(GetRing(selected));
                }
            }
        }

        for (Item_ item : all) {
            if (!item.IsShowing()) {
                continue;
            }
            if (item instanceof ChartItem_ && !(item instanceof Chart_) && !chartParts.contains(item)) {
                continue;
            }
            // A plain tooltip isn't a node, as an Android tooltip isn't: its
            // words are on its control's node.
            if (item instanceof Tooltip_ && ((Tooltip_) item).GetContent() == null) {
                continue;
            }
            AndroidNode node = MakeNode(item);
            nodes.put(node.id, node);
        }
        // While the focused item has an active part, the part's node has the
        // input focus in place of its owner's.
        AndroidNode activeNode = activePartId == AndroidNode.HOST ? null : nodes.get(activePartId);
        if (activeNode != null) {
            activeNode.focused = true;
            AndroidNode owner = nodes.get(activeOwnerId);
            if (owner != null) {
                owner.focused = false;
            }
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
            } else if (item instanceof Chart_) {
                // A chart's parts in ring order.
                ChartItem_ selected = SelectedPart((Chart_) item);
                List<Integer> ids = new ArrayList<>();
                if (selected != null && selected != item) {
                    for (ChartItem_ part : GetRing(selected)) {
                        if (nodes.containsKey(part.GetHashCode())) {
                            ids.add(part.GetHashCode());
                        }
                    }
                }
                node.childIds = ToArray(ids);
            } else if (item instanceof Calendar_) {
                // A calendar's days, which have no items.
                node.childIds = AddCalendarDays((Calendar_) item, node, nodes);
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
        // A chart part is in its chart.
        if (item instanceof ChartItem_ && !(item instanceof Chart_)) {
            Chart_ chart = ((ChartItem_) item).GetContainer();
            if (chart != null && nodes.containsKey(chart.GetHashCode())) {
                return chart.GetHashCode();
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
            || code == item.Get_Libraries_Interface_Item__TAB_PANE_()
            || code == item.Get_Libraries_Interface_Item__TOOLTIP_();
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
            node.focused = HasFocus(item);
        } else if (code == item.Get_Libraries_Interface_Item__COMBO_BOX_() && item instanceof ComboBox_) {
            // A text field with a list of choices below it, as Android's
            // AutoCompleteTextView and Material's editable exposed drop-down
            // are: an edit box to TalkBack. Its Actions menu opens or closes
            // the list, and its state says which.
            ComboBox_ box = (ComboBox_) item;
            node.className = "android.widget.EditText";
            node.clickable = true;
            node.editable = true;
            node.hint = node.text;
            node.text = Clean(box.GetText());
            node.focused = HasFocus(item);
            node.expandable = true;
            node.expanded = box.IsOpen();
            node.stateDescription = node.expanded ? "expanded" : "collapsed";
        } else if (code == item.Get_Libraries_Interface_Item__COLOR_WELL_() && item instanceof ColorWell_) {
            // A button showing a color: its name, then the color's words
            // ("#4A5A6E, dim gray") as its state (Android 11 and later), or
            // after its name before that. Its description is the same words.
            ColorWell_ well = (ColorWell_) item;
            node.className = "android.widget.Button";
            node.clickable = true;
            String color = Clean(well.GetColorText());
            node.hint = node.hint != null && node.hint.equals(color) ? null : node.hint;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                node.stateDescription = color;
            } else if (color != null) {
                node.text = node.text == null ? color : node.text + ", " + color;
            }
        } else if (code == item.Get_Libraries_Interface_Item__CALENDAR_() && item instanceof Calendar_) {
            // The days' grid, titled with the month: TalkBack says the title
            // and the grid's size when it enters, then reads the days
            // (BuildSnapshot adds them). The grid itself isn't a stop.
            node.className = "android.widget.GridView";
            node.containerTitle = Clean(item.GetName());
            node.text = null;
            node.hint = null;
            node.readerFocusable = false;
            node.collectionRows = CALENDAR_ROWS;
            node.collectionColumns = CALENDAR_COLUMNS;
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()) {
            node.className = "android.widget.EditText";
            node.clickable = true;
            node.editable = true;
            node.focused = HasFocus(item);
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
        } else if (code == item.Get_Libraries_Interface_Item__SLIDER_() && item instanceof Slider_) {
            // TalkBack adjusts a SeekBar with a swipe up or down (or the
            // volume keys) and says its state description as the value.
            Slider_ slider = (Slider_) item;
            node.className = "android.widget.SeekBar";
            node.slider = true;
            node.rangeMinimum = (float) slider.GetMinimum();
            node.rangeMaximum = (float) slider.GetMaximum();
            node.rangeValue = (float) slider.GetValue();
            node.stateDescription = Clean(slider.GetValueText());
        } else if (code == item.Get_Libraries_Interface_Item__CHART_()) {
            // The chart's summary, as iOS's ChartSummaryIOS: its title, then
            // what it shows. A double tap goes to the top level.
            Chart_ chart = (Chart_) item;
            node.className = "android.view.View";
            String title = Clean(chart.GetTitle());
            node.text = title != null ? title : node.text;
            node.hint = Clean(chart.GetDisplayName());
            node.clickable = chart.GetChartArea() != null;
            node.clickLabel = "explore";
        } else if (code == item.Get_Libraries_Interface_Item__CHART_SECTION_()
                || code == item.Get_Libraries_Interface_Item__CHART_ITEM_()) {
            // As iOS's ChartItemIOS: the part's name ("Monday bar"), then its
            // value or summary.
            ChartItem_ part = (ChartItem_) item;
            node.className = "android.view.View";
            node.text = TrimChartName(item.GetName());
            String value = Clean(part.GetDisplayName());
            node.hint = value != null ? value : Clean(item.GetDescription());
            node.clickable = part.GetEnterItem() != null;
            node.clickLabel = "go in";
            node.escapeLabel = part.GetEscapeItem() != null ? "Back to the group" : null;
            node.adjustable = part.GetUpItem() != null || part.GetDownItem() != null;
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
        } else if (code == item.Get_Libraries_Interface_Item__TOOLTIP_() && item instanceof Tooltip_) {
            // An interactive tooltip (a plain one has no node): a group
            // holding its buttons, titled with its words, as a group is.
            node.className = "android.view.ViewGroup";
            node.containerTitle = Clean(((Tooltip_) item).GetText());
            node.text = null;
            node.hint = null;
            node.readerFocusable = false;
        }
        if (item instanceof Control_ && !(item instanceof Tooltip_)) {
            AddTooltip((Control_) item, node);
        }
        return node;
    }

    /*
        A control whose tooltip is on and has words: TalkBack can show or
        hide it, and reads its words as the tooltip text, as for a View with
        a tooltip. The words are left off when they are the node's text or
        hint, so they aren't said twice. When they hold the hint (a tooltip
        title before the description), the hint is left off instead, since
        the words say more; Android before 9 has no tooltip text, so there
        the hint stays. While an interactive tooltip shows, its buttons are
        actions on this node.
     */
    private void AddTooltip(Control_ control, AndroidNode node) {
        if (!control.IsTooltipEnabled() || !control.HasTooltipContent()) {
            return;
        }
        String words = Clean(control.GetTooltipWords());
        if (words == null) {
            return;
        }
        node.hasTooltip = true;
        node.tooltipShowing = control.IsTooltipShowing();
        if (!words.equals(node.text) && !words.equals(node.hint)) {
            node.tooltipText = words;
            if (node.hint != null && words.contains(node.hint)
                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                node.hint = null;
            }
        }

        Tooltip_ tooltip = openTooltip;
        if (tooltip == null || !tooltip.IsOpen() || tooltip.GetContent() == null) {
            return;
        }
        Control_ owner = tooltip.GetOwner();
        if (owner == null || owner.GetHashCode() != control.GetHashCode()) {
            return;
        }
        Array_ buttons = tooltip.GetFocusableItems();
        if (buttons == null) {
            return;
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < buttons.GetSize(); i++) {
            Object button = buttons.Get(i);
            String name = button instanceof Item_ ? Clean(((Item_) button).GetName()) : null;
            names.add(name != null ? name : "Button " + (i + 1));
        }
        node.tooltipButtons = names.toArray(new String[0]);
    }

    /*
        An item's place on the screen, in pixels from the top left. Quorum
        counts y from the bottom, in the same pixels (AndroidInput flips
        touches the same way).
     */
    private Rect BoundsOf(Item_ item) {
        if (item instanceof Item3D_) {
            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();
            if (rectangle == null) {
                return new Rect();
            }
            int height = GameStateManager.display.GetHeight();
            return new Rect((int) rectangle.GetX(),
                    height - (int) (rectangle.GetY() + rectangle.GetHeight()),
                    (int) (rectangle.GetX() + rectangle.GetWidth()),
                    height - (int) rectangle.GetY());
        }
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

    private boolean HasFocus(Item_ item) {
        return GameStateManager.game != null && GameStateManager.game.GetFocus() == item;
    }

    private static ChartItem_ SelectedPart(Chart_ chart) {
        return chart.GetSelection() == null ? null : chart.GetSelection().GetChartItem();
    }

    /*
        The parts at a part's level, in reading order: its ring of left and
        right items (copied from iOS's ChartDescriptors.GetRing, which can't
        be used on Android: it imports MobiVM's Audio Graph classes).
     */
    private static List<ChartItem_> GetRing(ChartItem_ part) {
        ChartItem_ start = part;
        Set<ChartItem_> seen = new HashSet<>();
        seen.add(start);
        ChartItem_ left = start.GetLeftItem();
        while (left != null && !seen.contains(left)) {
            seen.add(left);
            start = left;
            left = left.GetLeftItem();
        }
        // In a ring the walk stops back at the part, so start there.
        if (left == part) {
            start = part;
        }

        List<ChartItem_> ring = new ArrayList<>();
        Set<ChartItem_> visited = new HashSet<>();
        ChartItem_ next = start;
        while (next != null && !visited.contains(next)) {
            visited.add(next);
            ring.add(next);
            next = next.GetRightItem();
        }
        return ring;
    }

    // Chart names are built to be spoken before a description ("Monday bar,").
    private static String TrimChartName(String name) {
        if (name == null) {
            return null;
        }
        String trimmed = name.trim();
        while (trimmed.endsWith(",")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1).trim();
        }
        return trimmed.isEmpty() ? null : trimmed;
    }

    /*
        After the chart's selection moved to another level or part: the
        snapshot is remade with that level, and TalkBack goes to the selected
        part, even though it is "inside" what TalkBack was on.
     */
    private void FollowChartSelection(Chart_ chart) {
        changed = true;
        ChartItem_ selected = SelectedPart(chart);
        if (selected != null) {
            focusToFollow = selected;
            followReason = "chart level";
            focusFromTalkBack = AndroidNode.HOST;
            focusAfterTalkBack = AndroidNode.HOST;
        }
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
                    // A radio button takes the focus without being selected:
                    // a double tap selects it (ActivateFromTalkBack).
                    if (item instanceof RadioButton_) {
                        ((RadioButton_) item).FocusWithoutSelecting();
                    } else {
                        item.Focus();
                    }
                    // The chart's selection box follows TalkBack, as on iOS.
                    if (item instanceof ChartItem_ && !(item instanceof Chart_)) {
                        Chart_ chart = ((ChartItem_) item).GetContainer();
                        if (chart != null) {
                            chart.Select((ChartItem_) item);
                        }
                    }
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
                if (item instanceof Chart_) {
                    // The summary: to the top level (the chart area's ring).
                    Chart_ chart = (Chart_) item;
                    if (chart.GetChartArea() != null) {
                        chart.Select(chart.GetChartArea());
                    }
                    self.FollowChartSelection(chart);
                } else if (item instanceof ChartItem_) {
                    ChartItem_ part = (ChartItem_) item;
                    Chart_ chart = part.GetContainer();
                    if (chart != null) {
                        chart.Select(part);
                        chart.SelectionEnter();
                        self.FollowChartSelection(chart);
                    }
                } else if (item instanceof TextField_ || item instanceof TextBox_) {
                    // As a touch does (TextBoxOpenKeyboardBehavior): the
                    // field takes the focus and the keyboard opens.
                    item.Focus();
                    AndroidKeyboard keyboard = new AndroidKeyboard();
                    keyboard.DisplayKeyboard(AndroidKeyboard.TEXT_NO_CORRECTIONS,
                        item instanceof TextBox_ ? AndroidKeyboard.LINEFEED : AndroidKeyboard.DONE);
                } else if (item instanceof TreeItem_) {
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

    // TalkBack's Actions menu or adjust gesture on a chart part.
    static void ChartFromTalkBack(final int id, final int what) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (!(item instanceof ChartItem_)) return;
                ChartItem_ part = (ChartItem_) item;
                Chart_ chart = part.GetContainer();
                if (chart == null) return;
                chart.Select(part);
                if (what == CHART_ESCAPE) {
                    chart.SelectionEscape();
                } else if (what == CHART_UP) {
                    chart.SelectionUp();
                } else {
                    chart.SelectionDown();
                }
                self.FollowChartSelection(chart);
            }
        });
    }

    // TalkBack's adjust gesture on a slider: a step up or down, as the
    // arrow keys do.
    static void SliderFromTalkBack(final int id, final int what) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (!(item instanceof Slider_)) return;
                Slider_ slider = (Slider_) item;
                if (what == SLIDER_STEP_UP) {
                    slider.StepUp();
                } else {
                    slider.StepDown();
                }
                self.changed = true;
            }
        });
    }

    // A value set through TalkBack (ACTION_SET_PROGRESS), as if typed.
    static void SliderSetFromTalkBack(final int id, final double value) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (!(item instanceof Slider_)) return;
                ((Slider_) item).ChangeValue(value, true);
                self.changed = true;
            }
        });
    }

    // TalkBack's show or hide tooltip action on a control.
    static void TooltipFromTalkBack(final int id, final boolean show) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (!(item instanceof Control_)) return;
                Control_ control = (Control_) item;
                if (show) {
                    control.ShowTooltip();
                } else {
                    control.HideTooltip();
                }
                self.changed = true;
            }
        });
    }

    /*
        One of an interactive tooltip's buttons, chosen from its control's
        actions: the button is activated, as a double tap on it would. The
        button must still be at that place with that name, in the tooltip
        that shows for the control.
     */
    static void TooltipButtonFromTalkBack(final int id, final int index, final String name) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Tooltip_ tooltip = self.openTooltip;
                if (tooltip == null || !tooltip.IsOpen()) return;
                Control_ owner = tooltip.GetOwner();
                if (owner == null || owner.GetHashCode() != id) return;
                Array_ buttons = tooltip.GetFocusableItems();
                if (buttons == null || index < 0 || index >= buttons.GetSize()) return;
                Object button = buttons.Get(index);
                if (!(button instanceof Control_)) return;
                String current = self.Clean(((Control_) button).GetName());
                if (current != null && !current.equals(name)) return;
                ((Control_) button).Activate();
                self.changed = true;
            }
        });
    }

    // TalkBack's expand or collapse action on a combo box: its list opens
    // or closes.
    static void ComboBoxFromTalkBack(final int id, final boolean open) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (!(item instanceof ComboBox_)) return;
                ComboBox_ box = (ComboBox_) item;
                if (open) {
                    box.Open();
                } else {
                    box.Close();
                }
                self.changed = true;
            }
        });
    }

    /*
        A double tap on a calendar's day: the current day moves there and is
        chosen, as Enter does. The calendar takes the focus first, as a touch
        gives it. A day outside the allowed range is left alone.
     */
    static void CalendarDayFromTalkBack(final int calendarId, final int row, final int column) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(calendarId);
                if (!(item instanceof Calendar_)) return;
                Calendar_ calendar = (Calendar_) item;
                CalendarDate_ date = calendar.GetShownDay(row, column);
                if (date == null || !calendar.IsAvailable(date)) return;
                if (!self.HasFocus(calendar)) {
                    self.focusFromTalkBack = calendarId;
                    calendar.Focus();
                }
                CalendarDate_ current = calendar.GetCurrentDay();
                int days = current == null ? 0 : current.DaysUntil(date);
                if (days != 0) {
                    calendar.MoveCurrentDay(days);
                }
                calendar.ChooseCurrentDay();
                self.changed = true;
            }
        });
    }

    // Voice typing or paste through TalkBack: the field's whole text.
    static void SetTextFromTalkBack(final int id, final String value) {
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                AndroidAccessibility self = instance;
                if (self == null) return;
                Item_ item = self.items.get(id);
                if (item instanceof TextField_) {
                    ((TextField_) item).SetText(value);
                } else if (item instanceof TextBox_) {
                    ((TextBox_) item).SetText(value);
                }
                self.changed = true;
            }
        });
    }
}
