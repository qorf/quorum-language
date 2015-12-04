function plugins_quorum_Libraries_Language_Types_Number_(optional) {
    this.number = 0.0;
    
    if(optional === undefined) {
    } else {
        number = optional;
    }
    this.SetValueNative$quorum_number = function (value) {
        number = value;
    };
    
    //system action IsInfinite() returns boolean
    this.IsInfinite = function () {
        return number.IsInfinite$NumberPrimitive();
    };
    
//    system action GetMinimumPositiveValue returns number
    this.GetMinimumPositiveValue = function () {
        return number.GetMinimumPositiveValue$NumberPrimitive();
    };
//    system action GetPositiveInfinityValue returns number

    this.GetPositiveInfinityValue = function () {
        return number.GetPositiveInfinityValue$NumberPrimitive();
    };
    //system action GetNumberOfBits returns integer
    this.GetNumberOfBits = function () {
        return number.GetNumberOfBits$NumberPrimitive();
    };
    //system action GetNegativeInfinityValue returns number
    this.GetNegativeInfinityValue = function () {
        return number.GetNegativeInfinityValue$NumberPrimitive();
    };
    //system action GetNotANumberValue returns number
    this.GetNotANumberValue = function () {
        return number.GetNotANumberValue$NumberPrimitive();
    };
    //system action IsNotANumber() returns boolean
    this.IsNotANumber = function () {
        return number.IsNotANumber$NumberPrimitive();
    };
    //system action GetHex() returns text
    this.GetHex = function () {
        return number.GetHex$NumberPrimitive();
    };
    //system action GetHashCode() returns integer
    this.GetHashCode = function () {
        return number.GetHashCode$NumberPrimitive();
    };
}

Number.prototype.IsInfinite$NumberPrimitive = function() {
    return !isFinite(this.valueOf());
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

Number.prototype.Compare$quorum_Libraries_Language_Object$NumberPrimitive = function(value) {
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

Number.prototype.GetMaximumValue$NumberPrimitive = function() {
  return Number.MAX_VALUE;
};

Number.prototype.GetMinimumValue$NumberPrimitive = function() {
  return Number.MIN_VALUE;
};

//system action GetMinimumPositiveValue returns number
Number.prototype.GetMinimumValue$NumberPrimitive = function() {
  return Number.MIN_VALUE;
};
//system action GetPositiveInfinityValue returns number
Number.prototype.GetPositiveInfinityValue$NumberPrimitive = function() {
  return Number.POSITIVE_INFINITY;
};
//system action GetNumberOfBits returns integer
Number.prototype.GetNumberOfBits$NumberPrimitive = function() {
  return 0;
};
//system action GetNegativeInfinityValue returns number
Number.prototype.GetNegativeInfinityValue$NumberPrimitive = function() {
  return Number.NEGATIVE_INFINITY;
};
//system action GetNotANumberValue returns number
Number.prototype.GetNotANumberValue$NumberPrimitive = function() {
  return Number.NaN;
};
//system action IsNotANumber() returns boolean
Number.prototype.IsNotANumber$NumberPrimitive = function() {
  return this.valueOf() == Number.NaN;
};
//ystem action GetHex() returns text
Number.prototype.GetHex$NumberPrimitive = function() {
  return "";
};
//system action GetHashCode() returns integer
Number.prototype.GetHashCode$NumberPrimitive = function() {
  return 0;
};