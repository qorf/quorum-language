class plugins_quorum_Libraries_Language_Types_Number_ {
    constructor(optional) {
      this.number = 0.0;

      if(optional === undefined) {
      } else {
          this.number = optional;
      }
    };
    
    
    SetValueNative$quorum_number(value) {
        this.number = value;
    };
    //system action GetMaximumValue returns number
    GetMaximumValue() {
        return this.number.GetMaximumValue$NumberPrimitive();
    };
    //system action GetMinimumValue returns number
    GetMinimumValue() {
        return this.number.GetMinimumValue$NumberPrimitive();
    };
    //system action GetMinimumPositiveValue returns number
    GetMinimumPositiveValue() {
        return this.number.GetMinimumPositiveValue$NumberPrimitive();
    };
    //system action GetPositiveInfinityValue returns number
    GetPositiveInfinityValue() {
        return this.number.GetPositiveInfinityValue$NumberPrimitive();
    };
    //system action GetNumberOfBits returns integer
    GetNumberOfBits() {
        return this.number.GetNumberOfBits$NumberPrimitive();
    };
    //system action GetNegativeInfinityValue returns number
    GetNegativeInfinityValue() {
        return this.number.GetNegativeInfinityValue$NumberPrimitive();
    };
    //system action GetNotANumberValue returns number
    GetNotANumberValue() {
        return this.number.GetNotANumberValue$NumberPrimitive();
    };
    //system action IsInfinite() returns boolean
    IsInfinite() {
        return this.number.IsInfinite$NumberPrimitive();
    };
    //system action IsNotANumber() returns boolean
    IsNotANumber() {
        return this.number.IsNotANumber$NumberPrimitive();
    };
    //system action GetHex() returns text
    GetHex() {
        return this.number.GetHex$NumberPrimitive();
    };
    //system action GetHashCode() returns integer
    GetHashCode() {
        return this.number.GetHashCode$NumberPrimitive();
    };
}

//system action GetMaximumValue returns number
Number.prototype.GetMaximumValue$NumberPrimitive = function() {
  return Number.MAX_VALUE;
};
//system action GetMinimumValue returns number
Number.prototype.GetMinimumValue$NumberPrimitive = function() {
  return -Number.MAX_VALUE;
};
//system action GetMinimumPositiveValue returns number
Number.prototype.GetMinimumPositiveValue$NumberPrimitive = function() {
    return Number.MIN_VALUE;
};
//system action GetPositiveInfinityValue returns number
Number.prototype.GetPositiveInfinityValue$NumberPrimitive = function() {
  return Number.POSITIVE_INFINITY;
};
//system action GetNumberOfBits returns integer
// A number is a 64-bit floating point value, as in Java (Double.SIZE).
Number.prototype.GetNumberOfBits$NumberPrimitive = function() {
  return 64;
};
//system action GetNegativeInfinityValue returns number
Number.prototype.GetNegativeInfinityValue$NumberPrimitive = function() {
  return Number.NEGATIVE_INFINITY;
};
//system action GetNotANumberValue returns number
Number.prototype.GetNotANumberValue$NumberPrimitive = function() {
  return Number.NaN;
};
//system action IsInfinite() returns boolean
// True only for positive and negative infinity, as Java's Double.isInfinite.
// isFinite is also false for NaN, which is not infinite.
Number.prototype.IsInfinite$NumberPrimitive = function() {
    var value = this.valueOf();
    return value === Infinity || value === -Infinity;
};
//system action IsNotANumber() returns boolean
Number.prototype.IsNotANumber$NumberPrimitive = function() {
  return isNaN(this.valueOf());
};
//system action GetHex() returns text
// The format of Java's Double.toHexString: 1.0 gives 0x1.0p0, -2.5 gives
// -0x1.4p1, and subnormal values keep the 0x0. form (0x0.0000000000001p-1022).
Number.prototype.GetHex$NumberPrimitive = function() {
    var value = this.valueOf();
    if (isNaN(value)) return "NaN";
    if (value === Infinity) return "Infinity";
    if (value === -Infinity) return "-Infinity";
    var view = new DataView(new ArrayBuffer(8));
    view.setFloat64(0, value);
    var high = view.getUint32(0), low = view.getUint32(4);
    var sign = (high >>> 31) ? "-" : "";
    var exponent = (high >>> 20) & 0x7ff;
    var fraction = ((high & 0xfffff).toString(16).padStart(5, "0") + low.toString(16).padStart(8, "0")).replace(/0+$/, "");
    if (fraction === "") fraction = "0";
    if (exponent === 0) {
        if (fraction === "0") return sign + "0x0.0p0";
        return sign + "0x0." + fraction + "p-1022";
    }
    return sign + "0x1." + fraction + "p" + (exponent - 1023);
};
//system action GetHashCode() returns integer
// Java's Double.hashCode: the high 32 bits XORed with the low 32 bits, with
// every NaN treated as the canonical NaN (0x7ff8000000000000), as Java does.
Number.prototype.GetHashCode$NumberPrimitive = function() {
    var value = this.valueOf();
    if (isNaN(value)) return 0x7ff80000 | 0;
    var view = new DataView(new ArrayBuffer(8));
    view.setFloat64(0, value);
    return view.getInt32(0) ^ view.getInt32(4);
};

// The same as cast(integer, value): drops the fraction toward zero, as
// Java's (int) cast does (-2.5 gives -2). Math.floor rounded down instead.
Number.prototype.GetInteger$NumberPrimitive = function() {
  return global_truncate_(this.valueOf());
};
// The same text as output and concatenation give, as Java's Double.toString.
Number.prototype.GetText$NumberPrimitive = function() {
  return global_number_converter_(this.valueOf());
};
Number.prototype.Compare$quorum_Libraries_Language_Object$NumberPrimitive = function(value) {
    var b = global_CheckCast(value, "Libraries.Language.Types.Number");
    
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
Number.prototype.Equals$quorum_Libraries_Language_Object$NumberPrimitive = function (value) {
    var b = global_CheckCast(value, "Libraries.Language.Types.Number");

    var me = this.valueOf();
    var other = b.GetValue();
    if ((me == other)) {
        return true;
    } else {
        return false;
    }
};


