function plugins_quorum_Libraries_Language_Types_Integer_(optional) {
    this.integer = 0;
    
    if(optional === undefined) {
    } else {
        integer = optional;
    }
    this.SetValueNative$quorum_integer = function (value) {
        integer = value;
    };
    
    this.GetHashCode = function() {
        return 0;
    };
    
    this.GetBinary = function() {
        return integer.toString(2);
    };
}

Number.prototype.GetBinary = function() {
    return this.valueOf().toString(2);
};

Number.prototype.GetNumber = function() {
  return  this.integer;
};

Number.prototype.GetText = function() {
  return  this.integer;
};

Number.prototype.GetMaximumValue = function() {
  return  2147483647;
};

Number.prototype.GetMinimumValue = function() {
  return  -2147483648;
};

Number.prototype.Equals$quorum_integer = function(value) {
    return this.valueOf().integer === value;
};

Number.prototype.Compare$quorum_Libraries_Language_Object = function(value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();
    var b = global_CheckCast(value, "Libraries.Language.Types.Number");
    
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

Number.prototype.GetValue = function() {
    return this.valueOf().integer;
};
