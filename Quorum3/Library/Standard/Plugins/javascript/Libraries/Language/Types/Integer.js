function plugins_quorum_Libraries_Language_Types_Integer_(optional) {
    this.integer = false;
    
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
  return  Number.MAX_VALUE;
};

Number.prototype.GetMaximumValue = function() {
  return  Number.MIN_VALUE;
};

Number.prototype.Equals$quorum_integer = function(value) {
    return this.valueOf().integer === value;
};

Number.prototype.Compare$quorum_integer = function(value) {
    if (this.valueOf().integer === value) {
        /*return EQUAL*/
    } else if (this.valueOf().integer < value) {
        /*return SMALLER*/
    } else {
        /*return LARGER*/
    }
};
Number.prototype.GetValue = function() {
    return this.valueOf().integer;
};
