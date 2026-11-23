class plugins_quorum_Libraries_Language_Types_Integer_ {
    constructor(optional) {
        this.integer = 0;
        
        if(optional === undefined) {
        } else {
            this.integer = optional;
        }
    }

    //private system action SetValueNative(integer i)
    SetValueNative$quorum_integer(value) {
        this.integer = value;
    };
    //system action GetHashCode() returns integer
    GetHashCode() {
        return this.integer.GetHashCode$IntegerPrimitive();
    };
    //system action BitCount returns integer
    BitCount() {
        return this.integer.BitCount$IntegerPrimitive();
    };
    //system action HighestOneBit returns integer
    HighestOneBit() {
        return this.integer.HighestOneBit$IntegerPrimitive();
    };
    //system action LowestOneBit returns integer
    LowestOneBit() {
        return this.integer.LowestOneBit$IntegerPrimitive();
    };
    //system action LeadingZeros returns integer
    LeadingZeros() {
        return this.integer.LeadingZeros$IntegerPrimitive();
    };
    //system action TrailingZeros returns integer
    TrailingZeros() {
        return this.integer.TrailingZeros$IntegerPrimitive();
    };
    //system action Reverse returns integer
    Reverse() {
        return this.integer.Reverse$IntegerPrimitive();
    };
    //system action RotateLeft(integer value) returns integer
    RotateLeft$quorum_integer(value) {
        return this.integer.RotateLeft$quorum_integer$IntegerPrimitive(value);
    };
    //system action RotateRight(integer value) returns integer
    RotateRight$quorum_integer(value) {
        return this.integer.RotateRight$quorum_integer$IntegerPrimitive(value);
    };
    //system action GetBinary returns text
    GetBinary() {
        return this.integer.GetBinary$IntegerPrimitive();
    };
    //system action GetHex returns text
    GetHex() {
        return this.integer.GetHex$IntegerPrimitive();
    };
    //system action GetOctal returns text
    GetOctal() {
        return this.integer.GetOctal$IntegerPrimitive();
    };
}

//system action GetHashCode() returns integer
Number.prototype.GetHashCode$IntegerPrimitive = function () {
    return this.valueOf();
};
//system action BitCount returns integer
// The one bits of the 32-bit value, as Java's Integer.bitCount.
Number.prototype.BitCount$IntegerPrimitive = function() {
    var n = this.valueOf() | 0;
    n = n - ((n >>> 1) & 0x55555555);
    n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
    return Math.imul((n + (n >>> 4)) & 0x0F0F0F0F, 0x01010101) >>> 24;
};
//system action HighestOneBit returns integer
// The integer with only the highest one bit set, as Java's
// Integer.highestOneBit: 0 for 0, and the minimum integer for any
// negative value (its highest one bit is the sign bit).
Number.prototype.HighestOneBit$IntegerPrimitive = function () {
    var num = this.valueOf() | 0;
    return num & (0x80000000 >>> Math.clz32(num));
};
//system action LowestOneBit returns integer
// As Java's Integer.lowestOneBit (i & -i): 0 for 0, a signed result.
Number.prototype.LowestOneBit$IntegerPrimitive = function () {
    var num = this.valueOf() | 0;
    return num & -num;
};
//system action LeadingZeros returns integer
// Math.clz32 counts leading zeros of the 32-bit value (32 for 0), as Java.
Number.prototype.LeadingZeros$IntegerPrimitive = function () {
    return Math.clz32(this.valueOf());
};
//system action TrailingZeros returns integer
// As Java's Integer.numberOfTrailingZeros (32 for 0).
Number.prototype.TrailingZeros$IntegerPrimitive = function () {
    var num = this.valueOf() | 0;
    if (num === 0) return 32;
    return 31 - Math.clz32(num & -num);
};
//system action Reverse returns integer
// The 32 bits in reverse order, as Java's Integer.reverse.
Number.prototype.Reverse$IntegerPrimitive = function () {
    var num = this.valueOf() | 0, result = 0;
    for (var i = 0; i < 32; i++) {
        result = (result << 1) | (num & 1);
        num >>>= 1;
    }
    return result | 0;
};
//system action RotateLeft(integer value) returns integer
// >>> brings in zeros, as Java's Integer.rotateLeft. Shift counts are taken
// mod 32, so 0, 32 and negative distances match Java.
Number.prototype.RotateLeft$quorum_integer$IntegerPrimitive = function (value) {
    var num = this.valueOf() | 0;
    return (num << value) | (num >>> (32 - value));
};
//system action RotateRight(integer value) returns integer
// As Java's Integer.rotateRight (see RotateLeft).
Number.prototype.RotateRight$quorum_integer$IntegerPrimitive = function (value) {
    var num = this.valueOf() | 0;
    return (num >>> value) | (num << (32 - value));
};
//system action GetBinary returns text
// The unsigned 32-bit value (>>> 0), as Java's toBinaryString, toHexString
// and toOctalString: -8 gives 11111111111111111111111111111000.
Number.prototype.GetBinary$IntegerPrimitive = function() {
    return (this.valueOf() >>> 0).toString(2);
};
//system action GetHex returns text
Number.prototype.GetHex$IntegerPrimitive = function () {
    return (this.valueOf() >>> 0).toString(16);
};
//system action GetOctal returns text
Number.prototype.GetOctal$IntegerPrimitive = function () {
    return (this.valueOf() >>> 0).toString(8);
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
