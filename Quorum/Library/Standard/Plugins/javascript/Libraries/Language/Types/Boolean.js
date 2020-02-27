function plugins_quorum_Libraries_Language_Types_Boolean_(optional) {
    this.myBoolean = false;
    
    if(optional === undefined) {
    } else {
        this.myBoolean = optional;
    }
    this.SetValueNative$quorum_boolean = function (bool) {
        myBoolean = bool;
    };
    
    this.GetHashCode = function() {
        return this.myBoolean ? 1231 : 1237;;
    };
}

Boolean.prototype.GetText = function() {
    return this.valueOf() + "";
};

Boolean.prototype.GetValue = function() {
    return this.valueOf().myBoolean;
};

Boolean.prototype.Equals$quorum_Libraries_Language_Object = function (value) {
    return this.valueOf().myBoolean === value;
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