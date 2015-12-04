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

Boolean.prototype.Equals$quorum_Libraries_Language_Object = function (value) {
    return this.valueOf().boolean === value;
};

Boolean.prototype.Compare$quorum_Libraries_Language_Object = function(value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();
    var b = global_CheckCast(value, "Libraries.Language.Types.Boolean");
    
    var me = this.valueOf();
    var other = b.GetValue();
    if ((me == other)) {
        result.result = result.EQUAL;
    }
    else if ((me < other)) {
        result.result = result.SMALLER;
    }
    else {
        result.result = result.LARGER;
    }
    return result;
};