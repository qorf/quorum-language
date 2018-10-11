function plugins_quorum_Libraries_Interface_AccessibilityManager_() {
    
    //private system action SetFocus(Item item) returns boolean
//

    this.SetFocus$quorum_Libraries_Interface_Item = function(item) {

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
        var para = document.createElement("button");
        var node = document.createTextNode("This is new.");
        para.appendChild(node);

        //we very likely need to not hard code this, but for testing it is ok.
        var element = document.getElementById("QuorumGraphicsCanvas");
        element.appendChild(para);
    };
    //    
//    // NativeRemove: Removes an Item from the native accessibility tree so that it is no longer available to screen readers.
//    private system action NativeRemove(Item item) returns boolean
//
    this.NativeRemove$quorum_Libraries_Interface_Item = function(item) {

    };
    
    //    // ===== actions for Buttons
//    
//    // == Pushbuttons
//    private system action InvokeButton(Item button) returns boolean
    this.InvokeButton$quorum_Libraries_Interface_Item = function(item) {

    };
    
    //
//    // == Togglebuttons
//    private system action UpdateToggleState(Item button, boolean selected) returns boolean
    this.UpdateToggleState$quorum_Libraries_Interface_Item$boolean = function(item, selected) {

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