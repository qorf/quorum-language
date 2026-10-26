package plugins.quorum.Libraries.Interface.Accessibility;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.IOSApplication;
import plugins.quorum.Libraries.Game.IOSDisplay;
import plugins.quorum.Libraries.Game.IOSInput;
import plugins.quorum.Libraries.Interface.Accessibility.IOS.*;
import quorum.Libraries.Interface.Controls.*;
import quorum.Libraries.Interface.Controls.Charts.Chart_;
import quorum.Libraries.Interface.Controls.Charts.ChartItem_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;

import java.util.HashMap;

public class IOSAccessibility {
    public Object me_ = null;
    public HashMap mapAccessibilityElements = new HashMap<Integer, ItemIOS>();

    // The array each element is in: the top container's, or a list's.
    // Tree items aren't here: they live in their groups (treeGroups).
    private HashMap<ItemIOS, NSMutableArray<UIAccessibilityElement>> elementArrays = new HashMap<>();

    // Each tree item's group: its element, then its open children's groups.
    private HashMap<Integer, TreeItemGroupIOS> treeGroups = new HashMap<>();

    /*
        Set to true (and rebuild the plugins) to print, on the console, the
        containers' structure after every change and every notification sent
        to VoiceOver, with its target. For finding where VoiceOver is sent.
     */
    public static boolean debug = false;

    // IOSConfiguration's debugAccessibility, set when the game starts.
    public void SetDebug(boolean enabled) {
        debug = enabled;
    }

    private ItemIOS focus = null;

    // Elements were added or removed since VoiceOver was last told.
    private boolean screenChanged = false;

    // The focused element was removed, as when a Form changes page.
    private boolean focusRemoved = false;

    // The accessibility in use, for elements that look up others (the rotor).
    private static IOSAccessibility instance = null;

    public IOSAccessibility() {
        instance = this;
    }

    public void  NameChanged(Item_ item) {
        IOSDisplay.RequestFrame();
        RefreshText(item);
    }

    public void  DescriptionChanged(Item_ item) {
        IOSDisplay.RequestFrame();
        RefreshText(item);
    }

    private void RefreshText(Item_ item) {
        if (item == null) {
            return;
        }
        ItemIOS element = (ItemIOS) mapAccessibilityElements.get(item.GetHashCode());
        if (element != null) {
            element.RefreshText();
        }
    }

    public void  BoundsChanged(Item_ item) {}

    /*
        A text field became, or stopped being, a password field. VoiceOver's
        value follows by itself (TextAdjust.getAccessibilityValue), and if the
        field has the keyboard, the keyboard switches to or from secure entry.
     */
    public void  TextFieldUpdatePassword(TextField_ field) {
        if (field == null) {
            return;
        }

        Object element = mapAccessibilityElements.get(field.GetHashCode());
        if (element instanceof TextFieldIOS) {
            IOSInput input = ((quorum.Libraries.Game.IOSInput) GameStateManager.input).plugin_;
            if (input.getFocusedTextAdjust() == element) {
                input.UpdateSecureEntry();
            }
        }
    }

    public void  Update() {
    }

    /*
        Called once a frame, after Quorum's focus change is handled
        (IOSAccessibility.Update). If elements were added or removed, tells
        VoiceOver once that the screen changed, pointing it at the focus.
     */
    public void PostPendingChanges() {
        if (!screenChanged) {
            return;
        }
        screenChanged = false;
        if (debug) {
            PrintStructure();
        }

        // A new page: VoiceOver says so, and goes to the new focus.
        if (focusRemoved) {
            focusRemoved = false;
            Post(UIAccessibilityNotification.ScreenChangedNotification, GetVoiceOverTarget(focus));
            return;
        }

        // Elements came or went on the same page (a tree item opened):
        // VoiceOver stays where it is.
        ItemIOS current = ItemIOS.GetVoiceOverFocus();
        if (current != null && mapAccessibilityElements.containsValue(current)) {
            Post(UIAccessibilityNotification.LayoutChangedNotification, current);
        } else {
            Post(UIAccessibilityNotification.LayoutChangedNotification, GetVoiceOverTarget(focus));
        }
    }

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {
        IOSDisplay.RequestFrame();
    }

    public void SelectionChanged(SelectionEvent_ event) {
        IOSDisplay.RequestFrame();
    }

    public void ButtonActivated(Button_ button) {
        IOSDisplay.RequestFrame();
    }

    public void ToggleButtonToggled(ToggleButton_ button) {
        IOSDisplay.RequestFrame();
    }

    public void  FocusChanged(FocusEvent_ event) throws Exception {
        IOSDisplay.RequestFrame();
        Item_ item = event.GetNewFocus();
        if (item == null) {
            return;
        }
        ItemIOS element = (ItemIOS) mapAccessibilityElements.get(item.GetHashCode());
        if(element != null) {
            if(focus != null) { //tell the old focus it lost the focus
                focus.FocusLost();
            }
            focus = element;
            focus.Focus();
            if (debug) {
                System.out.println("Quorum focus -> " + Describe(element));
            }

            // A chart shows one level at a time; the selection that moved
            // focus may have entered or left one (RebuildChart).
            ItemIOS chartContainer = element instanceof ChartIOS ? element : GetParentContainer(item);
            if (chartContainer instanceof ChartIOS) {
                RebuildChart((ChartIOS) chartContainer);
            }

            // When VoiceOver moved the focus itself, it is already there, or
            // on an item inside the focused control (a tree item inside its
            // tree, which Quorum focuses instead), or on the element VoiceOver
            // reads for it (a chart's summary, which focuses the chart).
            ItemIOS current = ItemIOS.GetVoiceOverFocus();
            UIAccessibilityElement target = GetVoiceOverTarget(element);
            boolean alreadyThere = current != null && (current == element || current == target
                    || (item instanceof Item2D_ && IsInside(current.GetItem(), (Item2D_) item)));
            if (!alreadyThere) {
                Post(UIAccessibilityNotification.LayoutChangedNotification, target);
            }
        }
    }

    // Sends VoiceOver a notification, printing it when debugging.
    private void Post(UIAccessibilityNotification notification, UIAccessibilityElement target) {
        if (debug) {
            System.out.println("VoiceOver: " + NotificationName(notification) + " -> " + Describe(target));
        }
        UIAccessibilityGlobals.postNotification(notification, target);
    }

    // "screen changed" and "layout changed" for the debug log, instead of
    // their numbers (1000 and 1001).
    private String NotificationName(UIAccessibilityNotification notification) {
        if (notification == UIAccessibilityNotification.ScreenChangedNotification)
            return "screen changed";
        if (notification == UIAccessibilityNotification.LayoutChangedNotification)
            return "layout changed";
        return "notification " + notification;
    }

    // Prints the containers VoiceOver walks, in order, with each frame.
    private void PrintStructure() {
        System.out.println("VoiceOver structure:");
        PrintElements(IOSApplication.accessibilityContainer.getAccessibilityElements(), "  ");
    }

    private void PrintElements(NSArray<UIAccessibilityElement> elements, String indent) {
        for (UIAccessibilityElement element : elements) {
            System.out.println(indent + Describe(element) + " " + element.getAccessibilityFrame());
            if (element instanceof UIAccessibilityContainer) {
                PrintElements(((UIAccessibilityContainer) element).getAccessibilityElements(), indent + "  ");
            }
        }
    }

    private String Describe(UIAccessibilityElement element) {
        if (element == null) {
            return "(none)";
        }
        String name = element.getAccessibilityLabel();
        if (name == null && element instanceof ItemIOS && ((ItemIOS) element).GetItem() != null) {
            name = ((ItemIOS) element).GetItem().GetName();
        }
        return element.getClass().getSimpleName() + " \"" + name + "\"";
    }

    /*
        The element VoiceOver should go to for an item, or null.
     */
    public static UIAccessibilityElement GetVoiceOverTarget(Item_ item) {
        if (instance == null || item == null) {
            return null;
        }
        return instance.GetVoiceOverTarget((ItemIOS) instance.mapAccessibilityElements.get(item.GetHashCode()));
    }

    /*
        After a double tap or scrub moves a chart's selection to another
        level (into the bars, or back out to the chart area): asks for a
        frame, shows VoiceOver the new level's parts to swipe through, and
        moves VoiceOver to the selected part. FocusChanged can't be relied on
        here: it waits for a frame, and it counts VoiceOver on a bar as
        already on the chart area that holds it.
     */
    public static void ChartLevelChanged(Chart_ chart) {
        IOSDisplay.RequestFrame();
        if (instance == null || chart == null) {
            return;
        }
        Object container = instance.mapAccessibilityElements.get(chart.GetHashCode());
        if (container instanceof ChartIOS) {
            instance.RebuildChart((ChartIOS) container);
        }
        ChartItem_ selected = chart.GetSelection() == null ? null : chart.GetSelection().GetChartItem();
        UIAccessibilityElement target = GetVoiceOverTarget(selected);
        if (target != null) {
            instance.Post(UIAccessibilityNotification.LayoutChangedNotification, target);
        }
    }

    /*
        VoiceOver can only go to elements. A list is a container and a tree a
        group: for them, the selected item, or the first.
     */
    private UIAccessibilityElement GetVoiceOverTarget(ItemIOS element) {
        if (element instanceof TreeIOS) {
            Tree_ tree = (Tree_) element.GetItem();
            TreeItem_ target = null;
            if (tree.GetSelection() != null) {
                target = tree.GetSelection().GetTreeItem();
            }
            if (target == null && tree.GetSize() > 0) {
                target = (TreeItem_) tree.GetTreeItems().Next();
            }
            return target == null ? null : (ItemIOS) mapAccessibilityElements.get(target.GetHashCode());
        }
        if (element instanceof ChartIOS) {
            return ((ChartIOS) element).GetSummary();
        }
        if (element instanceof GroupIOS) {
            GroupIOS group = (GroupIOS) element;
            if (group.getAccessibilityElementCount() == 0) {
                return null;
            }
            UIAccessibilityElement first = group.getAccessibilityElement(0);
            return first instanceof ItemIOS ? GetVoiceOverTarget((ItemIOS) first) : first;
        }
        if (element instanceof ListIOS) {
            ListIOS list = (ListIOS) element;
            for (UIAccessibilityElement child : list.getAccessibilityElements()) {
                if (child instanceof ListItemIOS && ((ListItemIOS) child).IsSelected()) {
                    return child;
                }
            }
            if (list.getAccessibilityElementCount() > 0) {
                return list.getAccessibilityElement(0);
            }
            return null;
        }
        return element;
    }

    public boolean NativeAdd(Item_ item) {
        IOSDisplay.RequestFrame();
        if(item == null) {
            return false;
        }

        int code = item.GetAccessibilityCode();
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_() || !item.IsShowing()) {
            return false;
        }

        // A tree item goes in its own group (which goes in its parent's group,
        // or the tree). Anything else goes in its nearest container: its list
        // or group (a Forms Grouping or Banner), or else the top container.
        TreeItemGroupIOS group = item instanceof TreeItem_ ? MakeTreeGroup((TreeItem_) item) : null;
        ItemIOS parentContainer = group == null ? GetParentContainer(item) : null;
        UIAccessibilityContainer container = IOSApplication.accessibilityContainer;
        if (group != null) {
            container = group;
        } else if (parentContainer != null) {
            container = (UIAccessibilityContainer) parentContainer;
        }
        ItemIOS element = CreateElement(item, code, container);

        // The identifier is how UI automation tools (XCUITest, Appium) find an
        // element, so it is the item's name, which stays the same from run to
        // run. Unnamed items fall back to their hash code.
        if (item.GetName() != null) {
            element.setAccessibilityIdentifier(item.GetName());
        }
        else {
            element.setAccessibilityIdentifier("" + item.GetHashCode());
        }
        element.RefreshText();

        mapAccessibilityElements.put(item.GetHashCode(), element);
        if (group != null) {
            RebuildGroup((TreeItem_) item);
            RebuildParentOf((TreeItem_) item);
        } else {
            NSMutableArray<UIAccessibilityElement> elements = IOSApplication.accessibilityContainer.getAccessibilityElements();
            if (parentContainer != null) {
                elements = (NSMutableArray<UIAccessibilityElement>) ((UIAccessibilityContainer) parentContainer).getAccessibilityElements();
            }
            if (parentContainer instanceof ChartIOS) {
                // A chart's parts are put in reading order by rebuilding.
                elements.add(element);
                elementArrays.put(element, elements);
                RebuildChart((ChartIOS) parentContainer);
            } else {
                elements.add(GetInsertionIndex(item, elements), element);
                elementArrays.put(element, elements);
            }
        }
        if (element instanceof ChartIOS) {
            RebuildChart((ChartIOS) element);
        }

        // The change is announced once, by PostPendingChanges, not per element.
        screenChanged = true;
        return true;
    }

    /*
        Makes the element for an item, by its accessibility code. Codes
        without their own class get a plain ItemIOS.
     */
    private ItemIOS CreateElement(Item_ item, int code, UIAccessibilityContainer container) {
        if (item instanceof Icon_) {
            ImageIOS image = new ImageIOS(container);
            image.Initialize(item);
            return image;
        } else if (code == item.Get_Libraries_Interface_Item__CHECKBOX_()) {
            CheckboxIOS checkbox = new CheckboxIOS(container);
            checkbox.Initialize((Checkbox_) item);
            return checkbox;
        } else if (code == item.Get_Libraries_Interface_Item__RADIO_BUTTON_()) {
            RadioButtonIOS radioButton = new RadioButtonIOS(container);
            radioButton.Initialize((RadioButton_) item);
            return radioButton;
        } else if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            ButtonIOS button = new ButtonIOS(container);
            button.Initialize((Button_) item);
            return button;
        } else if (code == item.Get_Libraries_Interface_Item__TOGGLE_BUTTON_()) {
            ToggleButtonIOS toggleButton = new ToggleButtonIOS(container);
            toggleButton.Initialize((ToggleButton_) item);
            return toggleButton;
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()) {
            TextBoxIOS textBox = new TextBoxIOS(container);
            textBox.Initialize((TextBox_) item);
            return textBox;
        } else if (code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()) {
            TextFieldIOS textField = new TextFieldIOS(container);
            textField.Initialize((TextField_) item);
            return textField;
        } else if (code == item.Get_Libraries_Interface_Item__LABEL_()) {
            LabelIOS label = new LabelIOS(container);
            label.Initialize(item);
            return label;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_()) {
            ListIOS list = new ListIOS(container);
            list.Initialize(item);
            return list;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_ITEM_()) {
            ListItemIOS listItem = new ListItemIOS(container);
            listItem.Initialize(item);
            return listItem;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_() && item instanceof Chart_) {
            ChartIOS chart = new ChartIOS(container);
            chart.Initialize(item);
            // A touch (without VoiceOver) selects the part under the finger,
            // and dragging moves the selection. With VoiceOver on, VoiceOver
            // takes the touches, so this changes nothing there.
            ((Chart_) item).SetTouchExplorationEnabled(true);
            ChartSummaryIOS summary = new ChartSummaryIOS(chart);
            summary.Initialize(item);
            summary.setAccessibilityIdentifier(item.GetName() + " summary");
            chart.SetSummary(summary);
            return chart;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_()
                || code == item.Get_Libraries_Interface_Item__CHART_SECTION_()
                || code == item.Get_Libraries_Interface_Item__CHART_ITEM_()) {
            // Parts of a chart, and chart graphics inside one (Plot) that
            // aren't charts themselves.
            ChartItemIOS part = new ChartItemIOS(container);
            part.Initialize(item);
            return part;
        } else if (code == item.Get_Libraries_Interface_Item__GROUP_()) {
            GroupIOS group = new GroupIOS(container);
            group.Initialize(item);
            return group;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_()) {
            TreeIOS tree = new TreeIOS(container);
            tree.Initialize(item);
            return tree;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_ITEM_()) {
            TreeItemIOS treeItem = new TreeItemIOS(container);
            treeItem.Initialize(item);
            return treeItem;
        } else if (code == item.Get_Libraries_Interface_Item__PROGRESS_BAR_()) {
            ProgressBarIOS progressBar = new ProgressBarIOS(container);
            progressBar.Initialize(item);
            return progressBar;
        }

        // Menus, tabs, tables, cells, trees, dialogs, charts, groups, panes,
        // custom items and any other code: a plain element for now.
        ItemIOS itemIOS = new ItemIOS(container);
        itemIOS.Initialize(item);
        return itemIOS;
    }

    /*
        The group for a tree item, inside its parent item's group, or its
        tree's container for a top-level item. Undefined if neither has one
        (the item then goes in the top container like any other).
     */
    private TreeItemGroupIOS MakeTreeGroup(TreeItem_ treeItem) {
        UIAccessibilityContainer parent = null;
        TreeItem_ parentItem = treeItem.GetParentTreeItem();
        if (parentItem != null) {
            parent = treeGroups.get(parentItem.GetHashCode());
        } else if (treeItem.GetTree() != null) {
            Object tree = mapAccessibilityElements.get(treeItem.GetTree().GetHashCode());
            if (tree instanceof TreeIOS) {
                parent = (TreeIOS) tree;
            }
        }
        if (parent == null) {
            return null;
        }

        TreeItemGroupIOS group = new TreeItemGroupIOS(parent);
        group.Initialize(treeItem);
        treeGroups.put(treeItem.GetHashCode(), group);
        return group;
    }

    /*
        Rebuilds a tree item's group from Quorum's tree: the item's own
        element, then the groups of its children that have them (those
        showing, so while it is open), in the tree's order.
     */
    private void RebuildGroup(TreeItem_ treeItem) {
        TreeItemGroupIOS group = treeGroups.get(treeItem.GetHashCode());
        if (group == null) {
            return;
        }

        NSMutableArray<UIAccessibilityElement> elements = group.getAccessibilityElements();
        elements.clear();
        Object element = mapAccessibilityElements.get(treeItem.GetHashCode());
        if (element != null) {
            elements.add((ItemIOS) element);
        }
        Iterator_ children = treeItem.GetTreeItems();
        while (children.HasNext()) {
            TreeItemGroupIOS childGroup = treeGroups.get(((TreeItem_) children.Next()).GetHashCode());
            if (childGroup != null) {
                elements.add(childGroup);
            }
        }
    }

    /*
        Rebuilds a chart's elements for the level VoiceOver is at, as the
        desktop's keyboard navigation works: the summary, then the parts at
        the selected part's level (ChartDescriptors.GetRing), so swiping moves
        along that level, a double tap enters a part (ChartItemIOS.activate)
        and the two-finger scrub escapes (performEscape). With nothing
        selected, or the chart itself, only the summary. The chart builds its
        navigation graph in its first update, after its parts are added, so
        this runs again whenever the selection moves (FocusChanged).
     */
    private void RebuildChart(ChartIOS chart) {
        NSMutableArray<UIAccessibilityElement> elements = chart.getAccessibilityElements();
        elements.clear();
        if (chart.GetSummary() != null) {
            elements.add(chart.GetSummary());
        }

        Chart_ quorumChart = (Chart_) chart.GetItem();
        ChartItem_ selected = quorumChart.GetSelection() == null ? null : quorumChart.GetSelection().GetChartItem();
        if (selected == null || selected == quorumChart) {
            return;
        }
        for (ChartItem_ part : ChartDescriptors.GetRing(selected)) {
            Object element = mapAccessibilityElements.get(part.GetHashCode());
            if (element != null) {
                elements.add((UIAccessibilityElement) element);
            }
        }
    }

    // Rebuilds the array a tree item's group is in: its parent's group, or
    // its tree's top-level groups.
    private void RebuildParentOf(TreeItem_ treeItem) {
        TreeItem_ parentItem = treeItem.GetParentTreeItem();
        if (parentItem != null) {
            RebuildGroup(parentItem);
            return;
        }

        Tree_ tree = treeItem.GetTree();
        if (tree == null) {
            return;
        }
        Object treeElement = mapAccessibilityElements.get(tree.GetHashCode());
        if (!(treeElement instanceof TreeIOS)) {
            return;
        }

        NSMutableArray<UIAccessibilityElement> groups = ((TreeIOS) treeElement).getAccessibilityElements();
        groups.clear();
        Iterator_ items = tree.GetTreeItems();
        while (items.HasNext()) {
            TreeItemGroupIOS group = treeGroups.get(((TreeItem_) items.Next()).GetHashCode());
            if (group != null) {
                groups.add(group);
            }
        }
    }

    /*
        The container an element goes in: a list item's list, or the nearest
        ancestor that is a list or a group. Undefined means the top container.
     */
    private ItemIOS GetParentContainer(Item_ item) {
        if (item instanceof ListItem_) {
            List_ list = ((ListItem_) item).GetList();
            if (list != null) {
                Object element = mapAccessibilityElements.get(list.GetHashCode());
                if (element instanceof ListIOS) {
                    return (ListIOS) element;
                }
            }
        }

        Item2D_ ancestor = item instanceof Item2D_ ? ((Item2D_) item).GetParent() : null;
        while (ancestor != null) {
            Object element = mapAccessibilityElements.get(ancestor.GetHashCode());
            if (element instanceof ListIOS || element instanceof GroupIOS || element instanceof ChartIOS) {
                return (ItemIOS) element;
            }
            ancestor = ancestor.GetParent();
        }
        return null;
    }

    /*
        Where a new element goes in an array (the top container's, a list's or
        a group's): after its nearest ancestor that has an element, and after
        that ancestor's descendants already listed. When that ancestor isn't
        in the array (it is the list or group itself), or there is none (a
        page being added), at the end, which is Quorum's order anyway.
     */
    private int GetInsertionIndex(Item_ item, NSMutableArray<UIAccessibilityElement> elements) {
        if (!(item instanceof Item2D_)) {
            return elements.size();
        }

        Item2D_ ancestor = ((Item2D_) item).GetParent();
        while (ancestor != null && !mapAccessibilityElements.containsKey(ancestor.GetHashCode())) {
            ancestor = ancestor.GetParent();
        }
        if (ancestor == null) {
            return elements.size();
        }

        int index = elements.indexOf(mapAccessibilityElements.get(ancestor.GetHashCode()));
        if (index < 0) {
            return elements.size();
        }
        index = index + 1;
        while (index < elements.size() && IsInside(((ItemIOS) elements.get(index)).GetItem(), ancestor)) {
            index = index + 1;
        }
        return index;
    }

    private boolean IsInside(Item_ item, Item2D_ ancestor) {
        Item2D_ current = item instanceof Item2D_ ? ((Item2D_) item).GetParent() : null;
        while (current != null) {
            if (current == ancestor) {
                return true;
            }
            current = current.GetParent();
        }
        return false;
    }

    public void TextSelectionChanged(TextBoxSelection_ selection)
    {
        IOSDisplay.RequestFrame();
    }

    public void TextSelectionChanged(TextFieldSelection_ selection)
    {
        IOSDisplay.RequestFrame();
    }

    public boolean Select(Item_ item)
    {
        return false;
    }

    private class HiddenView extends UIView {
        public HiddenView() {

        }
    }

    private class HiddenButton extends UIButton {
        public HiddenButton() {

        }
    }
    private class HiddenTextField extends UITextField {
        public HiddenTextField () {

            setKeyboardType(UIKeyboardType.Default);
            setReturnKeyType(UIReturnKeyType.Done);
            setAutocapitalizationType(UITextAutocapitalizationType.None);
            setAutocorrectionType(UITextAutocorrectionType.No);
            setSpellCheckingType(UITextSpellCheckingType.No);
            setHidden(true);
        }

        @Override
        public void deleteBackward () {
            //app.input.inputProcessor.keyTyped((char)8);
            //super.deleteBackward();
            //Gdx.graphics.requestRendering();
        }
    }

    /*
        Removes an item's element, for example when the page it is on is
        replaced, or a tree item closes. Quorum removes children first.
     */
    public boolean NativeRemove(Item_ item) {
        IOSDisplay.RequestFrame();
        if (item == null) {
            return false;
        }

        ItemIOS element = (ItemIOS) mapAccessibilityElements.remove(item.GetHashCode());
        if (element == null) {
            return false;
        }

        NSMutableArray<UIAccessibilityElement> elements = elementArrays.remove(element);
        if (elements != null) {
            elements.remove(element);
        }
        if (treeGroups.remove(item.GetHashCode()) != null) {
            RebuildParentOf((TreeItem_) item);
        }
        if (focus == element) {
            focus = null;
            focusRemoved = true;
        }
        ItemIOS.ClearVoiceOverFocus(element);
        screenChanged = true;
        return true;
    }

    public void  MenuChanged(MenuChangeEvent_ event) {
        IOSDisplay.RequestFrame();
    }

    public void  TreeChanged(TreeChangeEvent_ event) {
        IOSDisplay.RequestFrame();
    }

    public void  TreeTableChanged(TreeTableChangeEvent_ event) {
        IOSDisplay.RequestFrame();
    }

    public void  ControlActivated(ControlActivationEvent_ event) {
        IOSDisplay.RequestFrame();
    }

    public void  TextChanged(TextChangeEvent_ event) {
        IOSDisplay.RequestFrame();
    }

    public void  WindowFocusChanged(WindowFocusEvent_ event) {}

    // VoiceOver speaks the message.
    public void  Notify(Item_ item, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        if (debug) {
            System.out.println("VoiceOver: announcement \"" + value + "\"");
        }
        UIAccessibilityGlobals.postNotification(UIAccessibilityNotification.AnnouncementNotification, new NSString(value));
    }

    public void  Notify(Item_ item, String value, int notificationType) {
        Notify(item, value);
    }

    public void  Shutdown() {}
}
