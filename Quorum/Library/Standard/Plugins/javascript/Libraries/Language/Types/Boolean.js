class plugins_quorum_Libraries_Language_Types_Boolean_ {
    constructor(optional) {
        this.myBoolean = false;
    
        if(optional === undefined) {
        } else {
            this.myBoolean = optional;
        }
    }
    
    SetValueNative$quorum_boolean(bool) {
        this.myBoolean = bool;
    };
    
    GetHashCode() {
        return this.myBoolean ? 1231 : 1237;;
    };
}

Boolean.prototype.GetText = function() {
    return this.valueOf() + "";
};

// 1231 for true and 1237 for false, as Java's Boolean.hashCode.
Boolean.prototype.GetHashCode = function() {
    return this.valueOf() ? 1231 : 1237;
};

Boolean.prototype.GetValue = function() {
    return this.valueOf().myBoolean;
};

// Compares with the other Boolean's value, as Compare does.
Boolean.prototype.Equals$quorum_Libraries_Language_Object = function (value) {
    var b = global_CheckCast(value, "Libraries.Language.Types.Boolean");
    return this.valueOf() === b.GetValue();
};

Boolean.prototype.Compare$quorum_Libraries_Language_Object = function(value) {
    var b = global_CheckCast(value, "Libraries.Language.Types.Boolean");
    
    var me = this.valueOf();
    var other = b.GetValue();
    if ((me == other)) {
        return 0;
    }
    else if ((me < other)) {
        return -1;
    }
    else {
        return 1;
    }
};