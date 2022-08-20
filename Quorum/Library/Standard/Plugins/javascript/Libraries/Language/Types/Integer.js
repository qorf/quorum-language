function plugins_quorum_Libraries_Language_Types_Integer_(optional) {
    this.integer = 0;
    
    if(optional === undefined) {
    } else {
        this.integer = optional;
    }
    
    //private system action SetValueNative(integer i)
    this.SetValueNative$quorum_integer = function (value) {
        this.integer = value;
    };
    //system action GetHashCode() returns integer
    this.GetHashCode = function() {
        return this.integer.GetHashCode$IntegerPrimitive();
    };
    //system action BitCount returns integer
    this.BitCount = function() {
        return this.integer.BitCount$IntegerPrimitive();
    };
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
    this.RotateLeft$quorum_integer = function(value) {
        return this.integer.RotateLeft$quorum_integer$IntegerPrimitive(value);
    };
    //system action RotateRight(integer value) returns integer
    this.RotateRight$quorum_integer = function(value) {
        return this.integer.RotateRight$quorum_integer$IntegerPrimitive(value);
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

//system action GetHashCode() returns integer
Number.prototype.GetHashCode$IntegerPrimitive = function () {
    return this.valueOf();
};
//system action BitCount returns integer
Number.prototype.BitCount$IntegerPrimitive = function() {
    var text = Number(this.valueOf()).toString(2);
    var count = 0;
    for(i = 0; i < text.length; i++) {
        var char2 = text.charAt(i);
        if(char2 == "1") {
            count++;
        }
    }
    return count;
};
//system action HighestOneBit returns integer
Number.prototype.HighestOneBit$IntegerPrimitive = function () {
    var num = this.valueOf();
    var position = 0;
    while (num != 0) {
        position++;
        num = num >> 1;
    }
    return Math.pow(2,position-1);
};
//system action LowestOneBit returns integer
Number.prototype.LowestOneBit$IntegerPrimitive = function () {
    var num = this.valueOf();
    var position = 0;
    while (num != 0) {
        if (num & 1 == 1) break;
        position++;
        num = num >> 1;
    }
    return Math.pow(2,position);
};
//system action LeadingZeros returns integer
Number.prototype.LeadingZeros$IntegerPrimitive = function () {
    return 32 - this.valueOf().toString(2).length;
};
//system action TrailingZeros returns integer
Number.prototype.TrailingZeros$IntegerPrimitive = function () {
    var num = this.valueOf();
    var position = 0;
    while (num != 0) {
        if (num & 1 == 1) break;
        position++;
        num = num >> 1;
    }
    return position;
};
//system action Reverse returns integer
Number.prototype.Reverse$IntegerPrimitive = function () {
    var str = this.valueOf().toString(2);
    while (str.length < 32) str = "0" + str;
    str = str.split('').reverse().join('');
    return parseInt(str,2) >> 0;
};
//system action RotateLeft(integer value) returns integer
Number.prototype.RotateLeft$quorum_integer$IntegerPrimitive = function (value) {
    return (this.valueOf() >> (32-value) | this.valueOf() << value);
};
//system action RotateRight(integer value) returns integer
Number.prototype.RotateRight$quorum_integer$IntegerPrimitive = function (value) {
    return (this.valueOf() << (32-value) | this.valueOf() >> value);
};
//system action GetBinary returns text
Number.prototype.GetBinary$IntegerPrimitive = function() {
    return this.valueOf().toString(2);
};
//system action GetHex returns text
Number.prototype.GetHex$IntegerPrimitive = function () {
    return this.valueof().toString(16);
};
//system action GetOctal returns text
Number.prototype.GetOctal$IntegerPrimitive = function () {
    return this.valueOf().toString(8);
};

Number.prototype.GetNumber$IntegerPrimitive = function() {
  return this.valueOf() * 1.0;
};
Number.prototype.GetText$IntegerPrimitive = function() {
    return this.valueOf() + "";
};
Number.prototype.GetMaximumValue$IntegerPrimitive = function() {
  return  2147483647;
};
Number.prototype.GetMinimumValue$IntegerPrimitive = function() {
  return  -2147483648;
};
Number.prototype.Compare$quorum_Libraries_Language_Object$IntegerPrimitive = function(value) {
    var b = global_CheckCast(value, "Libraries.Language.Types.Integer");
    
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
    return result;
};
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
