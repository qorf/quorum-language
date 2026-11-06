package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.List_;
import quorum.Libraries.Interface.Item_;

public class ListItemKit extends ItemKit{
    // The name ListItem gives itself in its on create.
    private static final String DEFAULT_LIST_ITEM_NAME = "List Item";

    // A row of a list drawn as a tree (List:HasTreeAccessibility) is an
    // outline row, under the row it is drawn under (MacAccessibility.GetParentForKit).
    private final boolean tree;

    public ListItemKit() {
        this(false);
    }

    public ListItemKit(boolean tree) {
        this.tree = tree;
        SetRole(tree ? Role.TREE_ITEM : Role.LIST_ITEM);
    }

    public Node Build() {
        Node builder = new Node(GetRole());
        Item_ item = GetItem();
        if(item != null && item instanceof ListItem_) { //technically not compiler guaranteed. You can set the code to anything.
            ListItem_ listItem = (ListItem_) item;
            // A row scrolled out of view (ListRenderer hides it) has a node
            // too, so it is counted, but no place on screen.
            if (listItem.IsShowing()) {
                Rect rect = GetBoundingRectangle();
                builder.setBounds(rect);
            }
            //A name set by the program (anything but ListItem's default,
            //"List Item") is what the screen reader reads, as on Windows;
            //otherwise the row's text.
            String name = listItem.GetName();
            if (name != null && !name.isEmpty() && !name.equals(DEFAULT_LIST_ITEM_NAME)) {
                builder.setLabel(name);
            } else {
                builder.setLabel(listItem.GetText());
            }
            builder.setDescription(listItem.GetDescription());

            List_ list = listItem.GetList();
            if (list != null) {
                // VoiceOver reads it (AXSelected, the list's selected rows).
                builder.setSelected(list.GetSelection().GetListItem() == listItem);
            }
        } else {
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
        }
        BuildChildren(builder);
        return builder;
    }

    /*
        Its place in the list (index, for AXIndex), and its place among the
        rows at its depth under the same parent, with their number
        (position and count, for AXARIAPosInSet and AXARIASetSize). A list
        drawn as a tree says each row's depth with
        ListItem:GetAccessibilityLevel; in an ordinary list every row is at
        depth 0, so its place is its place in the whole list. Hidden rows
        count too.

        In a list drawn as a tree, the row is an outline row
        (MacAccessibilityExtras.OUTLINE_ROW), with its level and whether it
        opens and closes (ListItem:IsAccessibilityExpandable and
        IsAccessibilityExpanded), as TreeItemKit sends for a tree item.
    */
    @Override
    public void SendExtras() {
        int flags = tree ? MacAccessibilityExtras.OUTLINE_ROW : MacAccessibilityExtras.LIST_ROW;
        int index = -1;
        int level = 0;
        int position = 0;
        int count = -1;
        Item_ item = GetItem();
        if (item instanceof ListItem_) {
            ListItem_ listItem = (ListItem_) item;
            if (tree && listItem.IsAccessibilityExpandable()) {
                flags = flags | MacAccessibilityExtras.EXPANDABLE;
                if (listItem.IsAccessibilityExpanded()) {
                    flags = flags | MacAccessibilityExtras.EXPANDED;
                }
            }
            List_ list = listItem.GetList();
            if (list != null) {
                index = list.GetIndexOf(listItem);
                level = Math.max(0, listItem.GetAccessibilityLevel());
                int size = list.GetSize();
                if (index >= 0 && index < size) {
                    // Back to the first row at this depth under the same parent.
                    position = 1;
                    for (int i = index - 1; i >= 0; i--) {
                        int other = list.GetListItem(i).GetAccessibilityLevel();
                        if (other < level) {
                            break;
                        }
                        if (other == level) {
                            position = position + 1;
                        }
                    }
                    // On to the last one.
                    count = position;
                    for (int i = index + 1; i < size; i++) {
                        int other = list.GetListItem(i).GetAccessibilityLevel();
                        if (other < level) {
                            break;
                        }
                        if (other == level) {
                            count = count + 1;
                        }
                    }
                }
            }
        }
        MacAccessibilityExtras.setInfo(GetNodeID(), flags, level, tree ? -1 : index, count, position, null);
    }
}
