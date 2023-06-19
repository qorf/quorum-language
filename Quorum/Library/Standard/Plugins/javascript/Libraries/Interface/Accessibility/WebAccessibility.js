function plugins_quorum_Libraries_Interface_Accessibility_WebAccessibility_() {
    /*
     * These variables are old and may not be relevant. I'm leaving them in for now, 
     * but presume they will be modified or go away as we develop the library.
     */
    var type = "";      //specifies the input type of the element
    var elementType = "DIV";   //specifies the type of element DEFAULT is DIV right now for testing
    var elementList = [];   // array using the item's hashCode value as an index and the item as the value 
    var currentFocus = null;
    let root = null;
    let focusButton = null;
    let blurDelayedCall = null;
    let removedFocusedElement = false;

    const addBlurListener = function(element) {
        element.addEventListener("blur", () => {
            // Delay processing of this event until the next event cycle,
            // in case focus is being moved to another accessibility element.
            if (blurDelayedCall === null) {
                blurDelayedCall = setTimeout(() => {
                    if (plugins_quorum_Libraries_Game_WebInput_.IsFocused()) {
                        return;
                    }
                    blurDelayedCall = null;
                    focusButton.hidden = false;
                    root.hidden = true;
                });
            }
        });
    };

    this.Setup = function() {
        let container = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetContainer();
        let canvas = plugins_quorum_Libraries_Game_GameStateManager_.display.plugin_.GetCanvas();
        let config = plugins_quorum_Libraries_Game_GameStateManager_.application.plugin_.GetConfiguration();

        let title = config.Get_Libraries_Game_WebConfiguration__title_();
        if (title == null) {
            title = container.dataset.title;
            if (title == null) {
                title = "Game";
            }
        }

        let focusButtonName = config.Get_Libraries_Game_WebConfiguration__focusButtonName_();
        if (focusButtonName == null) {
            focusButtonName = container.dataset.focusButtonName;
            if (focusButtonName == null) {
                // Revisit this if Quorum gets localization support.
                focusButtonName = `Enter ${title}`;
            }
        }

        root = document.createElement("div");
        root.setAttribute("aria-label", title);
        root.setAttribute("tabindex", "-1");
        root.setAttribute("role", "dialog");
        root.setAttribute("aria-modal", true);
        root.hidden = true;

        root.style.position = "absolute";
        root.style.left = 0;
        root.style.bottom = 0;
        root.style.width = "100%";
        root.style.height = "100%";
        // Ensure that bugs in the positioning of shadow elements
        // don't affect the visible layout.
        root.style.overflow = "hidden";

        // The following style settings come from Flutter Web.
        // Make all semantics transparent. We use `filter` instead of `opacity`
        // attribute because `filter` is stronger. `opacity` does not apply to
        // some elements, particularly on iOS, such as the slider thumb and track.
        // We use transparency instead of "visibility:hidden" or "display:none"
        // so that a screen reader does not ignore these elements.
        root.style.filter = "opacity(0%)";
        // Make text explicitly transparent to signal to the browser that no
        // rasterization needs to be done.
        root.style.color = "rgba(0,0,0,0)";

        addBlurListener(root);

        // Accessibility elements must be inserted before the canvas, to ensure
        // that real mouse events get routed to the canvas while simulated
        // mouse events get routed to the accessibility elements.
        container.insertBefore(root, canvas);

        focusButton = document.createElement("button");
        focusButton.setAttribute("aria-label", focusButtonName);

        focusButton.style.position = "absolute";
        focusButton.style.left = 0;
        focusButton.style.bottom = 0;
        focusButton.style.width = "100%";
        focusButton.style.height = "100%";

        // The rationales for the following styles are the same as for the root.
        focusButton.style.filter = "opacity(0%)";
        focusButton.style.color = "rgba(0,0,0,0)";

        focusButton.addEventListener("click", (event) => {
            plugins_quorum_Libraries_Game_WebInput_.TakeFocus();
        });

        // Like the accessibility root, this element must be inserted before
        // the canvas.
        container.insertBefore(focusButton, canvas);
    };

    this.GetRoot = function() {
        return root;
    };

    this.InternalTakeFocus = function() {
        let element;
        if (currentFocus) {
            let id = currentFocus.GetHashCode();
            element = document.getElementById(id);
        } else {
            element = root;
        }
        if (!element)
            return;

        root.hidden = false;
        element.focus();
        focusButton.hidden = true;
    };

    this.InternalReleaseFocus = function() {
        focusButton.hidden = false;
        focusButton.focus();
        root.hidden = true;
    };

    const setBounds = (element, item) => {
        if (global_InstanceOf(item,"Libraries.Interface.Item2D")) {
            let item2D = global_CheckCast(item, "Libraries.Interface.Item2D");
            let x = item2D.GetScreenX();
            let y = item2D.GetScreenY();

            if (!(isNaN(x) || isNaN(y))) {
                // Find the nearest ancestor element that corresponds to an item.
                // If there is one, we need to adjust this element's position
                // to be relative to that ancestor.
                let ancestor = element.parentElement;
                while (ancestor && (ancestor !== root)) {
                    let ancestorId = ancestor.id;
                    if (ancestorId && elementList[ancestorId]) {
                        let ancestorItem = elementList[ancestorId];
                        if (global_InstanceOf(ancestorItem,"Libraries.Interface.Item2D")) {
                            let ancestorItem2D = global_CheckCast(ancestorItem, "Libraries.Interface.Item2D");
                            let ancestorX = ancestorItem2D.GetScreenX();
                            let ancestorY = ancestorItem2D.GetScreenY();
                            if (!(isNaN(ancestorX) || isNaN(ancestorY))) {
                                x -= ancestorX;
                                y -= ancestorY;
                                break;
                            }
                        }
                    }
                    ancestor = ancestor.parentElement;
                }

                element.style.position = "absolute";
                element.style.left = `${x}px`;
                element.style.bottom = `${y}px`;
                element.style.width = `${item2D.GetWidth()}px`;
                element.style.height = `${item2D.GetHeight()}px`;
            }
        }
    };
    
//    system action NameChanged(Item item)

    this.NameChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            if (item.GetAccessibilityCode() == 29) { //labels
                element.setAttribute("aria-label", item.GetText());
            } else {
                element.setAttribute("aria-label", item.GetName());
            }
            
        }
        //console.log("Name Changed");
    };

//    system action DescriptionChanged(Item item)

    this.DescriptionChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            if (item.GetAccessibilityCode() == 29) { //labels
                element.setAttribute("aria-description", item.GetName() + ", " + item.GetDescription());
            } else {
                element.setAttribute("aria-description", item.GetDescription());
            }
        }
        //console.log("Description Changed");
    };
    
//    system action BoundsChanged(Item item)

    this.BoundsChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            setBounds(element, item);
        }
        //console.log("Bounds Changed");
    };
    
//    system action TextFieldUpdatePassword(TextField field)

    this.TextFieldUpdatePassword$quorum_Libraries_Interface_Controls_TextField = function(field) {
        //console.log("Text field updated Changed");
    };

//  private system action TextSelectionChanged(TextBoxSelection selection)
this.TextSelectionChanged$quorum_Libraries_Interface_Selections_TextBoxSelection = function(selection) {
    var textBox = selection.GetTextBox();
    if (textBox == null){
        return;
    }else{
        var id = textBox.GetHashCode();
        var element = document.getElementById(id);
        element.selectionStart = selection.GetStartIndex();
        element.selectionEnd = selection.GetEndIndex();
    }
    
}

//  private system action TextSelectionChanged(TextBoxSelection selection)
this.TextSelectionChanged$quorum_Libraries_Interface_Selections_TextFieldSelection = function(selection) {
    var textField = selection.GetTextField();
    var id = textField.GetHashCode();
    var element = document.getElementById(id);
    
    element.setSelectionRange(selection.GetStartIndex(),selection.GetEndIndex());

    //console.log("TextField Selection Changed");
}
    
//    system action Update
//this is handled in Quorum for now might be added back if need be
    this.Update = function() {
        //removed
        ////console.log("Update called");
    };
    
//    system action ProgressBarValueChanged(ProgressBarValueChangedEvent progress)

    this.ProgressBarValueChanged$quorum_Libraries_Interface_Events_ProgressBarValueChangedEvent = function(event) {
        let progressbarID = event.GetProgressBar().GetHashCode();
        if(elementList[progressbarID] != null) {
            let element = document.getElementById(progressbarID);
            element.setAttribute("aria-valuenow", event.GetNewValue());
        }
        //console.log("Progress bar updated");
    };

//    system action Select(Item item)

    this.Select$quorum_Libraries_Interface_Item = function(item) {
        //console.log(item.GetName() + " selected");
        var id = item.GetHashCode();
        // Look for the nearest focusable element.
        let element = document.getElementById(id);
        while (element) {
            if (element.hasAttribute("tabindex")) {
                break;
            }
            element = element.parentElement;
        }
        element.setAttribute("aria-activedescendant", id);
    };

//    system action SelectionChanged(SelectionEvent event)
    // REMOVED for now as it was easier to handle Quorum side
    this.SelectionChanged$quorum_Libraries_Interface_Events_SelectionEvent = function(event) {
        //console.log("Selection Changed");
    };
    
//    system action ButtonActivated(Button button)
this.ButtonActivated$quorum_Libraries_Interface_Controls_Button = function(button) {
    var id = button.GetHashCode();
    if( elementList[id] != null ) {
        var element = document.getElementById(id);
        // removed for now because this makes it a toggle button
        // not sure what we should be doing instead
        //element.setAttribute('aria-pressed', "true");
    }
    //console.log("Button Activated");
};

//    system action ToggleButtonToggled(ToggleButton button)    
this.ToggleButtonToggled$quorum_Libraries_Interface_Controls_ToggleButton = function(button) {
    var id = button.GetHashCode();
    if( elementList[id] != null ) {
        var element = document.getElementById(id);
        element.setAttribute("aria-checked", button.GetToggleState())
    }
    //console.log("Toggled Buttoned");
};
    
//    system action FocusChanged(FocusEvent event)
    this.FocusChanged$quorum_Libraries_Interface_Events_FocusEvent = function(event) {
        //console.log("Focus Changed");
        var item = event.GetNewFocus();
        if(item == null) {
            //console.log("Tried to focus nothing");
            return;
        }

        // if not accessible focus the parent
        // if no accessible parent then ignore event
        if (item.GetAccessibilityCode() == -1) {
            var accessibleFocus = item.GetAccessibleParent();
            if (accessibleFocus != undefined) {
                item = accessibleFocus;
            } else {
                return;
            }
        }
        var id = item.GetHashCode();
        //accessible item got focus that was never added
        if (elementList[id] == null) {
            return;
        }
        currentFocus = item;
        if (plugins_quorum_Libraries_Game_WebInput_.IsFocused() || removedFocusedElement) {
            removedFocusedElement = false;
            let element = document.getElementById(id);
            element.focus();
        }
        
    };
//    system action NativeAdd(Item item)
    this.NativeAdd$quorum_Libraries_Interface_Item = function(item) {
        //dont add to DOM if not accessible
        if (item.GetAccessibilityCode() == -1) {
            return;
        }

       //replace this code with item appropriate material
        var id = item.GetHashCode();
        //dont want to add something to the DOM twice
        if( elementList[id] != null ) {
            return;
        }
        var itemName = item.GetName();
        elementList[id] = item;      //adds the item to the elementList array using the item's HashCode value as an index
        elementType = "DIV";
        //default role
        let role = null;
        let roleDescription = item.GetAccessibilityRoleDescription();

        /* Creating Item Element Tag with Attributes */
        var parent = undefined; // used if item needs to be added to group
        var para = document.createElement(elementType);
        para.id = id;       //sets the item's id to the item's HashCode value

        switch(item.GetAccessibilityCode()){
            //ITEM or CUSTOM
            case 0:
            case 1:
                if (item.IsFocusable() ) {
                    role = "application";
                } else {
                    role = "img";
                }
                // If a custom role description isn't provided, an empty string
                // will indicate that while assistive technologies should treat
                // this item like an application, e.g. by switching into
                // focus mode, it's not really an application, but we don't know
                // what it is.
                if (roleDescription == null) {
                    roleDescription = "";
                }
                break;
            //CHECKBOX
            case 2:
                role = "checkbox";
                if (item.GetName() == undefined)
                    itemName = "Check Box"
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Checkbox")) {
                    let checkbox = global_CheckCast(item, "Libraries.Interface.Controls.Checkbox");
                    para.setAttribute('aria-checked', checkbox.GetToggleState());
                }   
                //check for checked status
                break;
            //RADIO_BUTTON
            case 3:
                role = "radio";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.RadioButton")) {
                    let radioButton = global_CheckCast(item, "Libraries.Interface.Controls.RadioButton");
                    // attach to proper parent
                    // IDK WHAT WILL HAPPEN IF RADIO BUTTON IS ADDED TO GAME BEFORE GROUP
                    let parentGroup = radioButton.GetButtonGroup();
                    if (parentGroup != undefined) {
                        // attach to radiogroup
                        parent = parentGroup.GetHashCode();
                    }
                    para.setAttribute("aria-checked", radioButton.GetToggleState());
                }
                break;
            //BUTTON
            case 4:
                role = "button";
                break;
            //TOGGLE_BUTTON
            case 5:
                role = "button";
                para.setAttribute("aria-roledescription","toggle button");
                if (item.GetName() == undefined)
                    itemName = "Toggle Button"
                para.setAttribute('aria-pressed', "false");
                //check for pressed
                break;
            //TEXTBOX
            case 6:
                elementType = "textarea"
                para = document.createElement(elementType);
                role="textbox";
                para.setAttribute('aria-multiline','true');
                para.id = id;
                break;
            //MENU_BAR
            case 7:
                role = "menubar";
                break;
            //MENU_ITEM
            case 8:
                role = "menuitem";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.MenuItem")) {
                    let menuItem = global_CheckCast(item, "Libraries.Interface.Controls.MenuItem");
                    if (menuItem.IsMenu()) {
                        let menuGroup = document.createElement(elementType);
                        menuGroup.id = id + "-submenu";
                        menuGroup.setAttribute("role", "menu");
                        para.appendChild(menuGroup);
                        para.setAttribute("aria-haspopup", true);
                        para.setAttribute("aria-expanded", menuItem.IsOpen());
                    }
                    //attach to proper parent
                    let parentMenu = menuItem.GetParentMenu();
                    if (parentMenu != undefined) {
                        parent = parentMenu.GetHashCode() + "-submenu";
                    } else {
                        parentMenu = menuItem.GetMenuRoot();
                        if (parentMenu != undefined) {
                            parent = parentMenu.GetHashCode();
                        } else {
                            // lonely menu item
                        }
                    }
                }
                break;
            //PANE
            case 9:
                break;
            //TREE
            case 10:
                role = "tree";
                break;
            //TREE_ITEM
            case 11:
                role = "treeitem";
                // tree items can have subtrees so they need a group too
                if (global_InstanceOf(item,"Libraries.Interface.Controls.TreeItem")) {
                    let treeItem = global_CheckCast(item, "Libraries.Interface.Controls.TreeItem");
                    if (treeItem.IsSubtree()) {
                        let treegroup = document.createElement(elementType);
                        treegroup.id = id+"-group";
                        treegroup.setAttribute("role","group");
                        para.appendChild(treegroup);
                        para.setAttribute("aria-expanded", treeItem.IsOpen());
                    }
                    // attach to proper parent
                    let parentTree = treeItem.GetParentTreeItem();
                    if (parentTree != undefined) {
                        // if attached to a treeitem they need to be in a group
                        parent = parentTree.GetHashCode() + "-group";
                    } else {
                        parentTree = treeItem.GetTree();
                        if (parentTree != undefined) {
                            // if attached to tree directly they can be a child of that element
                            parent = parentTree.GetHashCode();
                        } else {
                            // the tree item is not on any tree
                        }
                    }
                }
                break;
            //TOOLBAR
            case 12:
                role = "toolbar";
                break;
            //TAB
            case 13:
                role = "tab";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Tab")) {
                    let tab = global_CheckCast(item, "Libraries.Interface.Controls.Tab");
                    let tabpane = tab.GetTabPane();
                    if (tabpane != undefined) {
                        parent = tabpane.GetHashCode()+"-tablist";
                    }
                }
                break;
            //TAB_PANE
            case 14:
                role = "tabpanel";
                let tablist = document.createElement(elementType);
                tablist.id = id + "-tablist";
                tablist.role = "tablist";
                //probably shouldn't be attached to the tab panel so adding directly to the root
                root.appendChild(tablist);
                break;
            //TABLE
            case 15:
                role = "table";
                // if cells get added after the spreadsheet is added this could cause issues
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Spreadsheet")) {
                    let spreadsheet = global_CheckCast(item, "Libraries.Interface.Controls.Spreadsheet");
                    for (let i = 0; i < spreadsheet.GetColumnsSize(); i++) {
                        let row = document.createElement(elementType);
                        row.id =id+"-row-"+i;
                        row.setAttribute("role","row");
                        para.appendChild(row);
                    }
                }
                break;
            //CELL
            case 16:
                role = "cell";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Cell")) {
                    let cell = global_CheckCast(item, "Libraries.Interface.Controls.Cell");
                    let spreadsheet = global_CheckCast(cell.GetSpreadsheet(), "Libraries.Interface.Controls.Spreadsheet");
                    para.innerHTML = cell.GetText();
                    if(spreadsheet != undefined) {
                        let position = spreadsheet.GetCellCoordinates$quorum_Libraries_Interface_Controls_Cell(cell);
                        let rowNum = position.GetFirstValue();
                        parent = spreadsheet.GetHashCode()+"-row-"+rowNum.GetValue();
                    }
                }
                break;
            //TEXT_FIELD
            case 17:
                elementType = "INPUT"
                para = document.createElement(elementType);
                para.id = id;
                para.type = "text"
                //role = "textbox";
                //para.setAttribute("contenteditable",true);
                break;
            //LIST
            case 18:
                role = "list";
                break;
            //LIST_ITEM
            case 19:
                role = "listitem";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.ListItem")) {
                    let listItem = global_CheckCast(item, "Libraries.Interface.Controls.ListItem");
                    para.textContent = listItem.GetText();
                    itemName = listItem.GetText();
                    //attach to proper parent
                    let parentList = listItem.GetList();
                    if (parentList != undefined) {
                        parent = parentList.GetHashCode();
                    } else {
                        // the list item is not in a list
                    }
                }
                break;
            //TREE_TABLE
            case 20:
                role = "treegrid";
                let rowgroup = document.createElement(elementType);
                rowgroup.id = id+"-rowgroup";
                rowgroup.setAttribute("role", "rowgroup");
                para.appendChild(rowgroup);
                break;
            //DIALOG
            case 21:
                role = "dialog";
                // dialogs are accessible parents so as long as their children are added properly dialogs will be announced
                break;
            //POPUP_MENU
            case 22:
                break;
            //PROGRESS_BAR
            case 23:
                role = "progressbar";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.ProgressBar")) {
                    let progressbar = global_CheckCast(item, "Libraries.Interface.Controls.ProgressBar");
                    para.setAttribute("aria-valuemin", progressbar.GetMinimum());
                    para.setAttribute("aria-valuemax", progressbar.GetMaximum());
                    para.setAttribute("aria-valuenow", progressbar.GetValue());
                }
                break;
            //TREE_TABLE_CELL
            case 24:
                role = "gridcell";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.TreeTableCell")) {
                    let treecell = global_CheckCast(item, "Libraries.Interface.Controls.TreeTableCell");
                    let treeTableID = undefined;
                    if (treecell.GetTreeTable() != undefined) {
                        treeTableID = treecell.GetTreeTable().GetHashCode() + "-rowgroup"
                    }
                    let treeTableRow = treecell.GetRow();
                    if (treeTableRow != undefined && treeTableID != undefined) {
                        let rowID = treeTableRow.GetHashCode();
                        let row = document.getElementById(rowID);
                        // if the row is not in the DOM make a new row
                        if (row == undefined) {
                            row = document.createElement(elementType);
                            row.id = treeTableRow.GetHashCode();
                            row.setAttribute("role", "row");
                            let tableElement = document.getElementById(treeTableID);
                            if (tableElement != undefined) {
                                tableElement.appendChild(row);
                            }
                        }
                        parent = rowID;
                        //check if this is an expandable cell
                        if (!treeTableRow.IsEmpty()) {
                            let column = treecell.GetColumn();
                            if (column != undefined && column.IsFirstColumn()) {
                                para.setAttribute("aria-expanded", treeTableRow.IsExpanded());
                            }
                        }
                    }
                    para.innerHTML = treecell.GetText();
                }
                break;
            //GROUP
            case 25:
                role = "radiogroup";
                if (item.GetName() == undefined)
                    itemName = "Radio Group"
                break;
            case 26:
                role="application";
                break;
            case 27:
                role="graphics-object group";
                break;
            case 28:
                role="graphics-symbol img";
                break;
            case 29: //label
                if (item.IsFocusable() ) {
                    role = "img";
                    itemName = item.GetText();
                    para.setAttribute("aria-description", item.GetName() + ", " + item.GetDescription());
                }
                break;
            default:
                // do nothing?
        }

        if (parent == undefined) {
            let accessibleParent = item.GetAccessibleParent();
            if (accessibleParent != undefined) {
                let parentID = accessibleParent.GetHashCode();
                if (elementList[parentID] != null) {
                    parent = parentID;
                }
            }
        }

        if (role != null) {
            para.setAttribute("role",role);
        }
        if (roleDescription != null) {
            para.setAttribute("aria-roledescription", roleDescription);
        }
        
        //process labels differently
        if (itemName != null) {
            para.setAttribute("aria-label", itemName);
        }
        // para.setAttribute("aria-label", para.getAttribute("aria-label") + " " + item.GetDescription())

        if (item.IsFocusable()) {
            para.setAttribute("tabindex", "-1");
            para.addEventListener("focus", (event) => {
                if (blurDelayedCall !== null) {
                    clearTimeout(blurDelayedCall);
                    blurDelayedCall = null;
                }
                if (currentFocus !== para) {
                    item.Focus();
                }
            });
            addBlurListener(para);
        }

        if (global_InstanceOf(item,"Libraries.Interface.Controls.ListItem")) {
            let listItem = global_CheckCast(item, "Libraries.Interface.Controls.ListItem");
            para.addEventListener("click", (event) => {
                if (event.target !== para) {
                    return; // ignore bubbled events
                }
                listItem.Select();
            });
        } else if (global_InstanceOf(item,"Libraries.Interface.Controls.Control")) {
            let control = global_CheckCast(item, "Libraries.Interface.Controls.Control");
            para.addEventListener("click", (event) => {
                if (event.target !== para) {
                    return; // ignore bubbled events
                }
                control.Activate();
            });
        }

        //add element to a parent if need be or directly to the root
        if (parent != undefined) {
            var parentElement = document.getElementById(parent);
            parentElement.appendChild(para);
            //console.log(item.GetName(), " has been added to a parent.");
        } else {
            root.appendChild(para);
            //console.log(item.GetName(), " has been added.");
        }

        // Set the element's bounds after we've added it, so setBounds can assume
        // the element's parent is already set.
        setBounds(para, item);
};
//    system action NativeRemove(Item item)
    this.NativeRemove$quorum_Libraries_Interface_Item = function(item) {
        let id = item.GetHashCode();
        //cant remove what's not there
        if( elementList[id] == null ) {
            return;
        }
        let element = document.getElementById(id);
        if (element != null) { //if the parent was removed then this would come up null
            if (element === document.activeElement)
            {
                removedFocusedElement = true;
            }

            element.remove();
        }
        //console.log(elementList[id], " has been removed.");
        elementList[id] = null;
    };
    
//    system action MenuChanged(MenuChangeEvent event)
    this.MenuChanged$quorum_Libraries_Interface_Events_MenuChangeEvent = function(event) {
        //console.log("Menu Changed");
        var menuItemID = event.GetMenuItem().GetHashCode();
        if (elementList[menuItemID] != null) {
            var element = document.getElementById(menuItemID);
            if (event.GetEventType() == 1) {  //OPENED
                element.setAttribute("aria-expanded", true);
            } else if (event.GetEventType() == 2) {   //CLOSED
                element.setAttribute("aria-expanded", false);
            }
        }
    };
    
//    system action TreeChanged(TreeChangeEvent event)
    this.TreeChanged$quorum_Libraries_Interface_Events_TreeChangeEvent = function(event) {
        //console.log("Tree Changed");
        var treeItemID = event.GetTreeItem().GetHashCode();
        if (elementList[treeItemID] != null) {
            var element = document.getElementById(treeItemID);
            if (event.GetEventType() == 1) {  //OPENED
                element.setAttribute("aria-expanded", true);
            } else if (event.GetEventType() == 2) {   //CLOSED
                element.setAttribute("aria-expanded", false);
            }
        }
    };
    
//    system action TreeTableChanged(TreeTableChangeEvent event)
    this.TreeTableChanged$quorum_Libraries_Interface_Events_TreeTableChangeEvent = function(event) {
        //console.log("TreeTable Changed");
        let cells = event.GetTreeTableCells();
        for(let i = 0; i< cells.GetSize(); i++) {
            let cell = cells.Get$quorum_integer(i).GetHashCode();
            let element = document.getElementById(cell);
            if (element != undefined && element.hasAttribute("aria-expanded")) {
                if (event.GetEventType() == 1) {  //OPENED
                    element.setAttribute("aria-expanded", true);
                } else if (event.GetEventType() == 2) {   //CLOSED
                    element.setAttribute("aria-expanded", false);
                }
                break;
            }
        }
    };
    
//    system action ControlActivated(ControlActivationEvent event)
    this.ControlActivated$quorum_Libraries_Interface_Events_ControlActivationEvent = function(event) {
        //console.log("Control Activated");
    };

//    system action TextChanged(TextChangeEvent event)
    this.TextChanged$quorum_Libraries_Interface_Events_TextChangeEvent = function(event) {
        var control = event.GetControl();
        if ( global_InstanceOf(control,"Libraries.Interface.Controls.TextBox") )
        {
            var textbox = global_CheckCast(control, "Libraries.Interface.Controls.TextBox");
            var text = textbox.GetText();
            var id = textbox.GetHashCode();
            var position = textbox.GetCaretPosition();
            var element = document.getElementById(id);
            if(element != null) {
              element.selectionStart = position;
              element.selectionEnd = position;
              element.innerHTML = text;
            }
            // console.log("Caret Position: ", position);
            // console.log("Current Line", linePosition);
            // console.log("TextBox text Changed");
        }
        else if ( global_InstanceOf(control,"Libraries.Interface.Controls.TextField") )
        {
            var textfield = global_CheckCast(control, "Libraries.Interface.Controls.TextField");
            var text = textfield.GetText();
            var id = textfield.GetHashCode();
            var element = document.getElementById(id);
            if(element != null) {
              element.value = text;
            }
            //console.log("TextField Text Changed");
        }
        else {
            //console.log("Text Changed");
        }
        
    };
    
//    system action WindowFocusChanged(WindowFocusEvent event)
    this.WindowFocusChanged$quorum_Libraries_Interface_Events_WindowFocusEvent = function(event) {
        //console.log("Window Focused");
    };
    
//    system action Notify(Item item, text value)
    this.Notify$quorum_Libraries_Interface_Item$text = function(item, value) {
        //console.log("Notify call");
    };
    
//    system action Notify(Item item, text value, integer notificationType)
    this.Notify$quorum_Libraries_Interface_Events_Item$text$integer = function(item, value, notificationType) {
        //console.log("Notify call 2");
    };
    
//    system action Shutdown
    this.Shutdown = function() {
        //console.log("Shutdown");
        //dispose of the shadow DOM tree
        if (root) {
            root.remove();
            root = null;
        }
        if (focusButton) {
            focusButton.remove();
            focusButton = null;
        }
        elementList.length = 0;
        currentFocus = null;
    };
    


/*
 * This implementation is old, but I am leaving it in as an exemplar. 
 * 
 * 
 *
    this.InvokeButton$quorum_Libraries_Interface_Item = function(item) {
        var buttonId = this.id;
        var button = elementList[buttonId];
        //console.log(button.GetName(), "has been clicked.");
        
        var mouseEvent = new quorum_Libraries_Interface_Events_MouseEvent_();     //Creates MouseEvent variable
        mouseEvent.SetSource$quorum_Libraries_Interface_Item(button);                 //sets the Source for the mouseEvent to the quorum_FakeButton_ item
        mouseEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(1);      //sets the MouseEvent to MOUSE_CLICKED
        button.ActivateFakeButton$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
    };
    

    this.UpdateToggleState$quorum_Libraries_Interface_Item$boolean = function(item, selected) {
        var toggleId = this.id;
        var toggle = elementList[toggleId];
        
        var mouseEvent = new quorum_Libraries_Interface_Events_MouseEvent_();
        mouseEvent.SetSource$quorum_Libraries_Interface_Item(toggle);
        mouseEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(1);
        if(toggle.GetAccessibilityCode() == 2){     //checkboxes
            if (this.checked == true){
                toggle.ActivateFakeCheckbox$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
                //console.log(toggle.GetName(), "has been checked.");
            }
            else if (this.checked == false){
                toggle.DeactivateFakeCheckbox$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
                //console.log(toggle.GetName(), "has been unchecked.");
            }
        }
        else if (toggle.GetAccessibilityCode() == 3){   //radio buttons work on traversing radio button options within the group
            //console.log("radio buttons");
        }

    };
    */
}
