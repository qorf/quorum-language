class plugins_quorum_Libraries_Language_Types_Boolean_ {
    constructor(optional) {
        this.myBoolean = false;
    
        if(optional === undefined) {
        } else {
            this.myBoolean = optional;
        }
    }
    
    SetValueNative$quorum_boolean(bool) {
        myBoolean = bool;
    };
    
    GetHashCode() {
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