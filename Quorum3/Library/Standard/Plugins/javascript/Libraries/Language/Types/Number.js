function plugins_quorum_Libraries_Language_Types_Number_(optional) {
    this.number = false;
    
    if(optional === undefined) {
    } else {
        number = optional;
    }
    this.SetValueNative$quorum_number = function (value) {
        number = value;
    };
    
    this.IsInfinite = function (left) {
        return !isFinite(left);
    };
}

Number.prototype.IsInfinite = function() {
    return !isFinite(this.valueOf());
};