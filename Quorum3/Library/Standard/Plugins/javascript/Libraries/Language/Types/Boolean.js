function plugins_quorum_Libraries_Language_Types_Boolean_(optional) {
    this.boolean = false;
    
    if(optional === undefined) {
    } else {
        boolean = optional;
    }
    this.SetValueNative$quorum_boolean = function (bool) {
        boolean = bool;
    };
    
    this.GetHashCode = function() {
        return 0;
    };
}

Boolean.prototype.GetText = function() {
    return this.valueOf() + "";
};

Boolean.prototype.GetValue = function() {
    return this.valueOf().boolean;
};

Boolean.prototype.Equals$quorum_boolean = function(value) {
    return this.valueOf().boolean === value;
};

Boolean.prototype.Compare$quorum_boolean = function(value) {
    if (this.valueOf().boolean === value) {
        //return EQUAL
    } else if (this.valueOf().boolean === false && this.value === true) {
        //return SMALLER
    } else {
        //return LARGER
    }
};