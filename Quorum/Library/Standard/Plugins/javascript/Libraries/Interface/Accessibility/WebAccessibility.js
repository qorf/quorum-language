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
    
//    system action SelectionChanged(SelectionEvent event)

    this.SelectionChanged$quorum_Libraries_Interface_Events_SelectionEvent = function(event) {
        console.log("Selection Changed");
    };
    
//    system action ButtonActivated(Button button)
    this.ButtonActivated$quorum_Libraries_Interface_Controls_Button = function(button) {
        console.log("Button Activated");
    };
    
//    system action ToggleButtonToggled(ToggleButton button)    
    this.ToggleButtonToggled$quorum_Libraries_Interface_Controls_ToggleButton = function(button) {
        console.log("Toggled Buttoned");
    };
    
//    system action FocusChanged(FocusEvent event)
    this.FocusChanged$quorum_Libraries_Interface_Events_FocusEvent = function(event) {
        console.log("Focus Changed");
        var item = event.GetNewFocus();
        var id = item.GetHashCode();
        currentFocus = item;
        var element = document.getElementById(currentIDECanvas_$Global_);
        element.setAttribute("aria-activedescendant", id)
    };
//    system action Add(Item item)
    this.Add$quorum_Libraries_Interface_Item = function(item) {
        //dont add to DOM if not accessible
        if (item.GetAccessibilityCode() == -1) {
            return;
        }
       //replace this code with item appropriate material
        var id = item.GetHashCode();
        var itemName = item.GetName();   //used for testing purposes
        elementList[id] = item;      //adds the item to the elementList array using the item's HashCode value as an index
        elementType = "DIV";
        //default role
        let role = "region";
        switch(item.GetAccessibilityCode()){
            //CUSTOM
            case 1:
               break;
            //CHECKBOX
            case 2:
                break;
            //RADIO_BUTTON
            case 3:
                break;
            //BUTTON
            case 4:
                break;
            //TOGGLE_BUTTON
            case 5:
                break;
            //TEXTBOX
            case 6:
                break;
            //MENU_BAR
            case 7:
                break;
            //MENU_ITEM
            case 8:
                break;
            //PANE
            case 9:
                break;
            //TREE
            case 10:
                break;
            //TREE_ITEM
            case 11:
                break;
            //TOOLBAR
            case 12:
                break;
            //TAB
            case 13:
                break;
            //TAB_PANE
            case 14:
                break;
            //TABLE
            case 15:
                break;
            //CELL
            case 16:
                break;
            //TEXT_FIELD
            case 17:
                break;
            //LIST
            case 18:
                break;
            //LIST_ITEM
            case 19:
                break;
            //TREE_TABLE
            case 20:
                break;
            //DIALOG
            case 21:
                break;
            //POPUP_MENU
            case 22:
                break;
            //PROGRESS_BAR
            case 23:
                break;
            //TREE_TABLE_CELL
            case 24:
                break;
            //GROUP
            case 25:
                break;
            default:
                // do nothing?
        }
      
        /* Creating Item Element Tag with Attributes */
        var para = document.createElement(elementType);
        para.id = id;       //sets the item's id to the item's HashCode value
        //para.type = type;
        para.setAttribute("role",role);
        para.setAttribute("aria-label", itemName);
        para.setAttribute("aria-description", item.GetDescription())
        para.tabindex = -1;
       //para.setAttribute("type", type);
       //para.setAttribute("value", item.GetDescription());
       //para.setAttribute("tabindex", -1);
       if (item.GetAccessibilityCode() == 4){
          para.onclick = this.InvokeButton$quorum_Libraries_Interface_Item;
       }
       else if (item.GetAccessibilityCode() == 2){
           para.onclick = this.UpdateToggleState$quorum_Libraries_Interface_Item$boolean;
       }
       else if (item.GetAccessibilityCode() == 3){
           para.setAttribute("name", item.GetName());  //item.GetButtonGroup() for value
           para.onclick = this.UpdateToggleState$quorum_Libraries_Interface_Item$boolean;
       }
       
       /*
       //Drawable using an img tag 
       else if (item.GetAccessibilityCode() == 1){
           para.setAttribute("src", description);      //Need Path for src attribute
           para.setAttribute("alt", item.GetDescription());
       }
       */
      
       //var node = document.createTextNode(description);
       //para.appendChild(node);

       var canvas = document.getElementById(currentIDECanvas_$Global_);
       canvas.appendChild(para);
        console.log(item.GetName(), " has been added.");
    };
//    system action Remove(Item item)
    this.Remove$quorum_Libraries_Interface_Item = function(item) {
        let id = item.GetHashCode();
        //if it wasn't accessible it was never in the DOM
        if( elementList[id] != null ) {
            document.getElementById(item.GetHashCode()).remove();
        }
        console.log(elementList[item.GetHashCode()], " has been removed.");
    };
    
//    system action MenuChanged(MenuChangeEvent event)
    this.MenuChanged$quorum_Libraries_Interface_Events_MenuChangeEvent = function(event) {
        console.log("Menu Changed");
    };
    
//    system action TreeChanged(TreeChangeEvent event)
    this.TreeChanged$quorum_Libraries_Interface_Events_TreeChangeEvent = function(event) {
        console.log("Tree Changed");
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