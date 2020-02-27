function plugins_quorum_Libraries_Interface_AccessibilityManager_() {
    var type = "";      //specifies the input type of the element
    var elementType = "";   //specifies the type of element DEFAULT is INPUT right now for testing
    var elementList = [];   // array using the item's hashCode value as an index and the item as the value 
    var currentFocus = null;

    //private system action SetFocus(Item item) returns boolean
//

    this.SetFocus$quorum_Libraries_Interface_Item = function(item) {
        currentFocus = item;
        
        //item.Focus();
        //plugins_quorum_Libraries_Interface_AccessibilityManager_.focus = item;
    };
    
    //    private system action Select(Item item) returns boolean
//

    this.Select$quorum_Libraries_Interface_Item = function(item) {
        
    };
    
    //    private system action Deselect(Item item) returns boolean
//

    this.Deselect$quorum_Libraries_Interface_Item = function(item) {

    };
    
    //    // NativeAdd: Adds an Item to the accessibility hierarchy so the screen 
//    //            readers can access information about it.
//    private system action NativeAdd(Item item) returns boolean
//    
//  


   this.NativeAdd$quorum_Libraries_Interface_Item = function(item) {      
       //replace this code with item appropriate material 

        var id = item.GetHashCode();        
        var description = item.GetName();   //used for testing purposes
        elementList[id] = item;      //adds the item to the elementList array using the item's HashCode value as an index
        
        if (item.GetAccessibilityCode() == 1)
        {
            type = "image";
            elementType = "IMG";
        }
        if (item.GetAccessibilityCode() == 2)
        {
            type = "checkbox";
            elementType = "INPUT";
        }
        else if (item.GetAccessibilityCode() == 3)
        {
            type = "radio";
            elementType = "INPUT";
        }
        else if (item.GetAccessibilityCode() == 4 || item.GetAccessibilityCode() == 13)
        {
            type = "button";
            elementType = "INPUT";
        }
        else if (item.GetAccessibilityCode() == 5)
        {
            type = "range";
            elementType = "INPUT";
        }
        else if (item.GetAccessibilityCode() == 6)
        {
            type = "textarea";
            elementType = "INPUT";
        }
       
        /* Creating Item Element Tag with Attributes */
        var para = document.createElement(elementType);
        para.id = id;       //sets the item's id to the item's HashCode value
        para.setAttribute("type", type);
        para.setAttribute("value", item.GetDescription());
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
       
        var node = document.createTextNode(description);
        para.appendChild(node);

        //we very likely need to not hard code this, but for testing it is ok.
        var element = document.getElementById("QuorumGraphicsCanvas");
        element.appendChild(para);
        console.log(description, " has been added.");
    };
    

    //    
//    // NativeRemove: Removes an Item from the native accessibility tree so that it is no longer available to screen readers.
//    private system action NativeRemove(Item item) returns boolean
//
    this.NativeRemove$quorum_Libraries_Interface_Item = function(item) {
        var parent = document.getElementById("QuorumGraphicsCanvas");
        var child = document.getElementById(item.GetHashCode());
        parent.removeChild(child);
        console.log(elementList[item.GetHashCode()], " has been removed.");
    };
    
    //    // ===== actions for Buttons
//    
//    // == Pushbuttons
//    private system action InvokeButton(Item button) returns boolean
    this.InvokeButton$quorum_Libraries_Interface_Item = function(item) {
        var buttonId = this.id;
        var button = elementList[buttonId];
        console.log(button.GetName(), "has been clicked.");
        
        var mouseEvent = new quorum_Libraries_Interface_Events_MouseEvent_();     //Creates MouseEvent variable
        mouseEvent.SetSource$quorum_Libraries_Interface_Item(button);                 //sets the Source for the mouseEvent to the quorum_FakeButton_ item
        mouseEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(1);      //sets the MouseEvent to MOUSE_CLICKED
        button.ActivateFakeButton$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
    };
    
    //
//    // == Togglebuttons
//    private system action UpdateToggleState(Item button, boolean selected) returns boolean
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
    
    //
//    // ===== actions for Textbox
//
//    // TextSelectionChanged: This action will be called whenever the text selection has changed.
//    //                       For example, a word or character has been added to the selection.
//    private system action TextSelectionChanged(TextBoxSelection selection)
    this.TextSelectionChanged$quorum_Libraries_Interface_Selections_TextBoxSelection_ = function(selection) {

    };
    
    //
//    // CaretPositionChanged: This action will be called whenever the caret moves or when text has
//    //                       been added or removed.
//    private system action CaretPositionChanged(Item TextBox, Text adjacentCharacter)
    this.CaretPositionChanged$quorum_Libraries_Interface_Item$text = function(item, adjacentCharacter) {

    };
    
    //
//    // =====
//
//    private system action NativeMenuChanged(MenuChangeEvent event) returns boolean
    this.NativeMenuChanged$quorum_Libraries_Interface_Events_MenuChangeEvent = function(event) {

    };

//    private system action NativeTreeChanged(TreeChangeEvent event) returns boolean
    this.NativeTreeChanged$quorum_Libraries_Interface_Events_TreeChangeEvent = function(event) {

    };


//    //    system action Update
//
    this.Update = function() {

    };
}