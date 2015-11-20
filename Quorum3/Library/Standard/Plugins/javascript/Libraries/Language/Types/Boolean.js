function plugins_quorum_Libraries_Language_Types_Boolean_(optional) {
    this.boolean = false;
    
    if(optional === undefined) {
    } else {
        boolean = optional;
    }
    this.SetValueNative$quorum_boolean = function (bool) {
        boolean = bool;
    };
}

Boolean.prototype.GetText = function() {
    return this.valueOf() + "";
}