function plugins_quorum_Libraries_Interface_Accessibility_WebAccessibility_() {
    /*
     * These variables are old and may not be relevant. I'm leaving them in for now, 
     * but presume they will be modified or go away as we develop the library.
     */
    var type = "";      //specifies the input type of the element
    var elementType = "";   //specifies the type of element DEFAULT is INPUT right now for testing
    var elementList = [];   // array using the item's hashCode value as an index and the item as the value 
    var currentFocus = null;
    
//    system action NameChanged(Item item)

    this.NameChanged$quorum_Libraries_Interface_Item = function(item) {
    };

//    system action DescriptionChanged(Item item)

    this.DescriptionChanged$quorum_Libraries_Interface_Item = function(item) {
    };
    
//    system action TextFieldUpdatePassword(TextField field)

    this.TextFieldUpdatePassword$quorum_Libraries_Interface_Controls_TextField = function(field) {
    };
    
//    system action Update

    this.Update = function() {
    };
    
//    system action ProgressBarValueChanged(ProgressBarValueChangedEvent progress)

    this.ProgressBarValueChanged$quorum_Libraries_Interface_Events_ProgressBarValueChangedEvent = function(event) {
    };
    
//    system action SelectionChanged(SelectionEvent event)

    this.SelectionChanged$quorum_Libraries_Interface_Events_SelectionEvent = function(event) {
    };
    
//    system action ButtonActivated(Button button)
    this.ButtonActivated$quorum_Libraries_Interface_Controls_Button = function(button) {
    };
    
//    system action ToggleButtonToggled(ToggleButton button)    
    this.ToggleButtonToggled$quorum_Libraries_Interface_Controls_ToggleButton = function(button) {
    };
    
//    system action FocusChanged(FocusEvent event)
    this.FocusChanged$quorum_Libraries_Interface_Events_FocusEvent = function(event) {
        
    };
//    system action Add(Item item)
    this.Add$quorum_Libraries_Interface_Item = function(item) {      
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
//    system action Remove(Item item)
    this.Remove$quorum_Libraries_Interface_Item = function(item) {
        var parent = document.getElementById("QuorumGraphicsCanvas");
        var child = document.getElementById(item.GetHashCode());
        parent.removeChild(child);
        console.log(elementList[item.GetHashCode()], " has been removed.");
    };
    
//    system action MenuChanged(MenuChangeEvent event)
    this.MenuChanged$quorum_Libraries_Interface_Events_MenuChangeEvent = function(event) {
    };
    
//    system action TreeChanged(TreeChangeEvent event)
    this.TreeChanged$quorum_Libraries_Interface_Events_TreeChangeEvent = function(event) {
    };
    
//    system action TreeTableChanged(TreeTableChangeEvent event)
    this.TreeTableChanged$quorum_Libraries_Interface_Events_TreeTableChangeEvent = function(event) {
    };
    
//    system action ControlActivated(ControlActivationEvent event)
    this.ControlActivated$quorum_Libraries_Interface_Events_ControlActivationEvent = function(event) {
    };
    
//    system action TextChanged(TextChangeEvent event)
    this.TextChanged$quorum_Libraries_Interface_Events_TextChangeEvent = function(event) {
    };
    
//    system action WindowFocusChanged(WindowFocusEvent event)
    this.WindowFocusChanged$quorum_Libraries_Interface_Events_WindowFocusEvent = function(event) {
    };
    
//    system action Notify(Item item, text value)
    this.Notify$quorum_Libraries_Interface_Item$text = function(item, value) {
    };
    
//    system action Notify(Item item, text value, integer notificationType)
    this.Notify$quorum_Libraries_Interface_Events_Item$text$integer = function(item, value, notificationType) {
    };
    
//    system action Shutdown
    this.Shutdown = function() {
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