function plugins_quorum_Libraries_Interface_Accessibility_WebAccessibility_() {
    /*
     * These variables are old and may not be relevant. I'm leaving them in for now, 
     * but presume they will be modified or go away as we develop the library.
     */
    var type = "";      //specifies the input type of the element
    var elementType = "DIV";   //specifies the type of element DEFAULT is DIV right now for testing
    var elementList = [];   // array using the item's hashCode value as an index and the item as the value 
    var currentFocus = null;
    
//    system action NameChanged(Item item)

    this.NameChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            element.setAttribute("aria-label", item.GetName());
        }
        console.log("Name Changed");
    };

//    system action DescriptionChanged(Item item)

    this.DescriptionChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            element.setAttribute("aria-roledescription", item.GetDescription());
        }
        console.log("Description Changed");
    };
    
//    system action TextFieldUpdatePassword(TextField field)

    this.TextFieldUpdatePassword$quorum_Libraries_Interface_Controls_TextField = function(field) {
        console.log("Text field updated Changed");
    };
    
//    system action Update
//this is handled in Quorum for now might be added back if need be
    this.Update = function() {
        //removed
        //console.log("Update called");
    };
    
//    system action ProgressBarValueChanged(ProgressBarValueChangedEvent progress)

    this.ProgressBarValueChanged$quorum_Libraries_Interface_Events_ProgressBarValueChangedEvent = function(event) {
        console.log("Progress bar updated");
    };

//    system action Select(Item item)

    this.Select$quorum_Libraries_Interface_Item = function(item) {
        console.log(item.GetName() + " selected");
        var id = item.GetHashCode();
        var element = document.getElementById(currentIDECanvas_$Global_);
        element.setAttribute("aria-activedescendant", id);
    };

//    system action SelectionChanged(SelectionEvent event)
    // REMOVED for now as it was easier to handle Quorum side
    this.SelectionChanged$quorum_Libraries_Interface_Events_SelectionEvent = function(event) {
        console.log("Selection Changed");
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
    console.log("Button Activated");
};

//    system action ToggleButtonToggled(ToggleButton button)    
this.ToggleButtonToggled$quorum_Libraries_Interface_Controls_ToggleButton = function(button) {
    var id = button.GetHashCode();
    if( elementList[id] != null ) {
        var element = document.getElementById(id);
        element.setAttribute("aria-checked", button.GetToggleState())
    }
    console.log("Toggled Buttoned");
};
    
//    system action FocusChanged(FocusEvent event)
    this.FocusChanged$quorum_Libraries_Interface_Events_FocusEvent = function(event) {
        console.log("Focus Changed");
        var item = event.GetNewFocus();
        var id = item.GetHashCode();
        currentFocus = item;
        var element = document.getElementById(currentIDECanvas_$Global_);
        element.setAttribute("aria-activedescendant", id);
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
        var itemName = item.GetName();   //used for testing purposes
        elementList[id] = item;      //adds the item to the elementList array using the item's HashCode value as an index
        elementType = "DIV";
        //default role
        let role = "region";

        /* Creating Item Element Tag with Attributes */
        var parent = undefined; // used if item needs to be added to group
        var para = document.createElement(elementType);
        para.id = id;       //sets the item's id to the item's HashCode value

        switch(item.GetAccessibilityCode()){
            //CUSTOM
            case 1:
                para.setAttribute("aria-roledescription","custom");
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
                }
                para.setAttribute("aria-checked", false);
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
                role = "textbox";
                break;
            //MENU_BAR
            case 7:
                role = "menubar";
                break;
            //MENU_ITEM
            case 8:
                role = "menuitem";
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
                break;
            //TAB_PANE
            case 14:
                role = "tabpanel";
                break;
            //TABLE
            case 15:
                role = "table";
                break;
            //CELL
            case 16:
                role = "cell";
                break;
            //TEXT_FIELD
            case 17:
                break;
            //LIST
            case 18:
                role = "list";
                break;
            //LIST_ITEM
            case 19:
                role = "listitem"
                break;
            //TREE_TABLE
            case 20:
                role = "treegrid";
                break;
            //DIALOG
            case 21:
                role = "dialog";
                break;
            //POPUP_MENU
            case 22:
                break;
            //PROGRESS_BAR
            case 23:
                role = "progressbar"
                break;
            //TREE_TABLE_CELL
            case 24:
                break;
            //GROUP
            case 25:
                role = "radiogroup";
                if (item.GetName() == undefined)
                    itemName = "Radio Group"
                break;
            default:
                // do nothing?
        }

        para.setAttribute("role",role);
        para.setAttribute("aria-label", itemName);
        para.setAttribute("aria-description", item.GetDescription())
        para.tabindex = -1;

        //add element to a parent if need be or directly to canvas
        if (parent != undefined) {
            var parentElement = document.getElementById(parent);
            parentElement.appendChild(para);
            console.log(item.GetName(), " has been added to a parent.");
        } else {
            var canvas = document.getElementById(currentIDECanvas_$Global_);
            canvas.appendChild(para);
            console.log(item.GetName(), " has been added.");
        }
};
//    system action NativeRemove(Item item)
    this.NativeRemove$quorum_Libraries_Interface_Item = function(item) {
        let id = item.GetHashCode();
        //cant remove what's not there
        if( elementList[id] == null ) {
            return;
        }
        //if it wasn't accessible it was never in the DOM
        if( elementList[id] != null ) {
            document.getElementById(item.GetHashCode()).remove();
        }
        console.log(elementList[item.GetHashCode()], " has been removed.");
        elementList[id] = null;
    };
    
//    system action MenuChanged(MenuChangeEvent event)
    this.MenuChanged$quorum_Libraries_Interface_Events_MenuChangeEvent = function(event) {
        console.log("Menu Changed");
    };
    
//    system action TreeChanged(TreeChangeEvent event)
    this.TreeChanged$quorum_Libraries_Interface_Events_TreeChangeEvent = function(event) {
        console.log("Tree Changed");
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
        console.log("TreeTable Changed");
    };
    
//    system action ControlActivated(ControlActivationEvent event)
    this.ControlActivated$quorum_Libraries_Interface_Events_ControlActivationEvent = function(event) {
        console.log("Control Activated");
    };
    
//    system action TextChanged(TextChangeEvent event)
    this.TextChanged$quorum_Libraries_Interface_Events_TextChangeEvent = function(event) {
        console.log("Text Changed");
    };
    
//    system action WindowFocusChanged(WindowFocusEvent event)
    this.WindowFocusChanged$quorum_Libraries_Interface_Events_WindowFocusEvent = function(event) {
        console.log("Window Focused");
    };
    
//    system action Notify(Item item, text value)
    this.Notify$quorum_Libraries_Interface_Item$text = function(item, value) {
        console.log("Notify call");
    };
    
//    system action Notify(Item item, text value, integer notificationType)
    this.Notify$quorum_Libraries_Interface_Events_Item$text$integer = function(item, value, notificationType) {
        console.log("Notify call 2");
    };
    
//    system action Shutdown
    this.Shutdown = function() {
        console.log("Shutdown");
        //dispose of the children
        var canvas = document.getElementById(currentIDECanvas_$Global_);
        while (canvas.firstChild) {
            canvas.firstChild.remove()
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
        console.log(button.GetName(), "has been clicked.");
        
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
                console.log(toggle.GetName(), "has been checked.");
            }
            else if (this.checked == false){
                toggle.DeactivateFakeCheckbox$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
                console.log(toggle.GetName(), "has been unchecked.");
            }
        }
        else if (toggle.GetAccessibilityCode() == 3){   //radio buttons work on traversing radio button options within the group
            console.log("radio buttons");
        }

    };
    */
}