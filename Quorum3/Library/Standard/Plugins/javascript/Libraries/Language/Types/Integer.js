function plugins_quorum_Libraries_Language_Types_Integer_(optional) {
    this.integer = 0;
    
    if(optional === undefined) {
    } else {
        this.integer = optional;
    }
    this.SetValueNative$quorum_integer = function (value) {
        this.integer = value;
    };
    
    this.BitCount = function() {
        var text = Number(this.integer).toString(2)
        var count = 0;
        for(i = 0; i < text.length; i++) {
            var char = text.charAt(i);
            if(char == "1") {
                count++;
            }
        }
        return count;
    };
    
    //system action GetHashCode() returns integer
    this.GetHashCode = function() {
        return this.integer.GetHashCode$IntegerPrimitive();
    };
    
    this.GetBinary = function() {
        return this.integer.GetBinary$IntegerPrimitive();
    };
    
    //system action BitCount returns integer
    this.BitCount = function() {
        return this.integer.BitCount$IntegerPrimitive();
    };
    //
    //system action HighestOneBit returns integer
    this.HighestOneBit = function() {
        return this.integer.HighestOneBit$IntegerPrimitive();
    };
    //system action LowestOneBit returns integer
    this.LowestOneBit = function() {
        return this.integer.LowestOneBit$IntegerPrimitive();
    };
    //system action LeadingZeros returns integer
    this.LeadingZeros = function() {
        return this.integer.LeadingZeros$IntegerPrimitive();
    };
    //system action TrailingZeros returns integer
    this.TrailingZeros = function() {
        return this.integer.TrailingZeros$IntegerPrimitive();
    };
    //system action Reverse returns integer
    this.Reverse = function() {
        return this.integer.Reverse$IntegerPrimitive();
    };
    //system action RotateLeft(integer value) returns integer
    this.RotateLeft = function() {
        return this.integer.RotateLeft$IntegerPrimitive();
    };
    //system action RotateRight(integer value) returns integer
    this.RotateRight = function() {
        return this.integer.RotateRight$IntegerPrimitive();
    };
    //system action GetBinary returns text
    this.GetBinary = function() {
        return this.integer.GetBinary$IntegerPrimitive();
    };
    //system action GetHex returns text
    this.GetHex = function() {
        return this.integer.GetHex$IntegerPrimitive();
    };
    //system action GetOctal returns text
    this.GetOctal = function() {
        return this.integer.GetOctal$IntegerPrimitive();
    };
}

Number.prototype.Equals$quorum_Libraries_Language_Object$IntegerPrimitive = function (value) {
    var b = global_CheckCast(value, "Libraries.Language.Types.Integer");

    var me = this.valueOf();
    var other = b.GetValue();
    if ((me == other)) {
        return true;
    } else {
        return false;
    }
};

//system action GetHashCode() returns integer
Number.prototype.GetHashCode$IntegerPrimitive = function () {
    return 0;
};
//system action BitCount returns integer
Number.prototype.BitCount$IntegerPrimitive = function() {
    var text = Number(this.valueOf()).toString(2);
    var count = 0;
    for(i = 0; i < text.length; i++) {
        var char = text.charAt(i);
        if(char == "1") {
            count++;
        }
    }
    return count;
};

//system action HighestOneBit returns integer
Number.prototype.HighestOneBit$IntegerPrimitive = function () {
    return 0;
};
//system action LowestOneBit returns integer
Number.prototype.LowestOneBit$IntegerPrimitive = function () {
    return 0;
};
//system action LeadingZeros returns integer
Number.prototype.LeadingZeros$IntegerPrimitive = function () {
    return 0;
};
//system action TrailingZeros returns integer
Number.prototype.TrailingZeros$IntegerPrimitive = function () {
    return 0;
};
//system action Reverse returns integer
Number.prototype.Reverse$IntegerPrimitive = function () {
    return 0;
};
//system action RotateLeft(integer value) returns integer
Number.prototype.RotateLeft$IntegerPrimitive = function (value) {
    return 0;
};
//system action RotateRight(integer value) returns integer
Number.prototype.RotateRight$IntegerPrimitive = function (value) {
    return 0;
};
//system action GetBinary returns text
Number.prototype.GetBinary$IntegerPrimitive = function() {
    return this.valueOf().toString(2);
};

//system action GetHex returns text
Number.prototype.GetHex$IntegerPrimitive = function () {
    return 0;
};
//system action GetOctal returns text
Number.prototype.GetOctal$IntegerPrimitive = function () {
    return 0;
};

Number.prototype.GetNumber$IntegerPrimitive = function() {
  return this.valueOf() * 1.0;
};

Number.prototype.GetText$IntegerPrimitive = function() {
  return this.valueOf() * "";
};

Number.prototype.GetMaximumValue$IntegerPrimitive = function() {
  return  2147483647;
};

Number.prototype.GetMinimumValue$IntegerPrimitive = function() {
  return  -2147483648;
};

Number.prototype.Compare$quorum_Libraries_Language_Object$IntegerPrimitive = function(value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();
    var b = global_CheckCast(value, "Libraries.Language.Types.Integer");
    
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

Number.prototype.GetValue$IntegerPrimitive = function() {
    return this.valueOf();
};
