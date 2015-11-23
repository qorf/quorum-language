function plugins_quorum_Libraries_Language_Types_Integer_(optional) {
    this.integer = false;
    
    if(optional === undefined) {
    } else {
        integer = optional;
    }
    this.SetValueNative$quorum_integer = function (value) {
        integer = value;
    };
}