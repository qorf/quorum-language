class plugins_quorum_Libraries_Language_Types_Number_ {
    constructor(optional) {
      this.number = 0.0;

      if(optional === undefined) {
      } else {
          number = optional;
      }
    };
    
    
    SetValueNative$quorum_number(value) {
        number = value;
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
       Number.MIN_VALUE;
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
//system action IsInfinite() returns boolean
Number.prototype.IsInfinite$NumberPrimitive = function() {
    return !isFinite(this.valueOf());
};
//system action IsNotANumber() returns boolean
Number.prototype.IsNotANumber$NumberPrimitive = function() {
  return isNaN(this.valueOf());
};
//system action GetHex() returns text
Number.prototype.GetHex$NumberPrimitive = function() {
  return this.valueOf().toString(16);
};
//system action GetHashCode() returns integer
Number.prototype.GetHashCode$NumberPrimitive = function() {
  return Math.floor(this.valueOf());
};

Number.prototype.GetInteger$NumberPrimitive = function() {
  return Math.floor(this.valueOf());
};
Number.prototype.GetText$NumberPrimitive = function() {
  var illegal_ = this.valueOf();
  var res = (illegal_ % 1 == 0 ? illegal_.toFixed(1) : illegal_);
  return res;
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


