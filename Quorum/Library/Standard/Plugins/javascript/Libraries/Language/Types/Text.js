class plugins_quorum_Libraries_Language_Types_Text_ {
    constructor(optional) {
        this.text = "";
        if(optional === undefined) {
        } else {
            this.text = optional;
        }
    }

    SetValueNative$quorum_text(value) {
        this.text = value;
    };
    GetCharacterNative$quorum_integer(index) {
        return this.text.GetCharacterNative$quorum_integer(index);
    };
    GetSubstringNative$quorum_integer$quorum_integer(startIndex, endIndex) {
        return this.text.GetSubstringNative(startIndex, endIndex);
    };
    ParseInteger() {
        return this.text.ParseInteger();
    };
    ParseNumber() {
        return this.text.ParseNumber();
    };
    ParseBoolean() {
        return this.text.ParseBoolean();
    };
    GetLineFeed() {
        return this.text.GetLineFeed();
    };
    GetCarriageReturn() {
        return this.text.GetCarriageReturn();
    };
    GetTab() {
        return this.text.GetTab();
    };
    GetDoubleQuote() {
        return this.text.GetDoubleQuote();
    };
    GetUnicodeValue$quorum_integer(value) {
        return this.text.GetUnicodeValue$quorum_integer(value);
    };
    GetUnicodeInteger$quorum_integer(value) {
        return this.text.GetUnicodeInteger$quorum_integer(value);
    };
    ContainsNative$quorum_text$quorum_text(left, right) {
        return this.text.ContainsNative$quorum_text$quorum_text(left, right);
    };
    EndsWithNative$quorum_text$quorum_text(left, right) {
        return this.text.EndsWithNative$quorum_text$quorum_text(left, right);
    };
    StartsWithNative$quorum_text$quorum_text(left, right) {
        return this.text.StartsWithNative$quorum_text$quorum_text(left, right);
    };
    SplitIntoLines() {
        return this.text.SplitIntoLines();
    };
    IndexOfNative$quorum_text$quorum_text(left, right) {
        return this.text.IndexOfNative$quorum_text$quorum_text(left, right);
    };
    IndexOfNative$quorum_text$quorum_text$quorum_integer(left, right, index) {
        return this.text.IndexOfNative$quorum_text$quorum_text$quorum_integer(left, right, index);
    };
    IsEmptyNative$quorum_text(left) {
        return this.text.IsEmptyNative$quorum_text(left);
    };
    ReplaceNative$quorum_text$quorum_text$quorum_text(left, old, replacement) {
        return this.text.ReplaceNative$quorum_text$quorum_text$quorum_text(left, old, replacement);
    };
    GetSubtextNative$quorum_text$quorum_integer(left, index) {
        return this.text.GetSubtextNative$quorum_text$quorum_integer(left, index);
    };
    ToLowerCase() {
        return this.text.ToLowerCase();
    };
    ToUpperCase() {
        return this.text.ToUpperCase();
    };
    TrimNative$quorum_text(left) {
        return this.text.TrimNative$quorum_text(left);
    };
    EqualsIgnoringCaseNative$quorum_text$quorum_text(left, right) {
        return this.text.EqualsIgnoringCaseNative$quorum_text$quorum_text(left, right);
    };
    CompareInt$quorum_text$quorum_text$quorum_boolean(left, right, isIgnoringCase) {
        return this.text.CompareInt$quorum_text$quorum_text$quorum_boolean(left, right, isIgnoringCase);
    };
    GetSize() {
        return this.text.GetSize();
    };
    GetHashCode() {
        return this.text.GetHashCode();
    };
    GetSubtext$quorum_integer$quorum_integer(startIndex, endIndex) {
        return this.text.GetSubtext$quorum_integer$quorum_integer(startIndex, endIndex);
    };
}
String.prototype.GetCharacterNative$quorum_integer = function(index) {
    return this.valueOf().charAt(index);
};
String.prototype.GetCharacterNative$quorum_integer$quorum_integer = function(startIndex, endIndex) {
    return this.valueOf().charAt(startIndex, endIndex);
};  
String.prototype.ParseInteger = function() {
    return parseInt(this.valueOf());
};
String.prototype.ParseNumber = function() {
    return parseFloat(this.valueOf());
};
String.prototype.ParseBoolean = function() {
    if (this.valueOf() == "true")
        return true;
    else if (this.valueOf() == "false")
        return false;
    else
        return false;
};
String.prototype.GetLineFeed = function() {
    return String.fromCharCode(10);
};
String.prototype.GetCarriageReturn = function() {
    return String.fromCharCode(13);
};
String.prototype.GetTab = function() {
    return String.fromCharCode(9);
};
String.prototype.GetDoubleQuote = function() {
    return String.fromCharCode(34);
};
String.prototype.GetUnicodeValue$quorum_integer = function(value) {
    return String.fromCharCode(value);
};
String.prototype.GetUnicodeInteger$quorum_integer = function(value) {
    return this.valueOf().charCodeAt(value);
};
String.prototype.ContainsNative$quorum_text$quorum_text = function(left, searchString) {
    var l = left.valueOf();
    for(var i = 0; i < l.length; i++) {
        var j = 0;
        if((searchString.length - j) > l.length - i) {
            return false;
        }
        if(searchString[j] == l[i]) {
            j++;
            while(j < searchString.length && i < l.length && searchString[j] == l[i + j]) {
                if(j == searchString.length - 1) {
                    return true;
                }
                j++;
            }
        }
    }
    return false;
};
String.prototype.EndsWithNative$quorum_text$quorum_text = function(left, suffix) {
    return left.valueOf().endsWith(suffix);
};
String.prototype.StartsWithNative$quorum_text$quorum_text = function(left, prefix) {
    return left.valueOf().startsWith(prefix);
};
String.prototype.IndexOfNative$quorum_text$quorum_text = function(left, prefix) {
    return left.valueOf().indexOf(prefix);
};
String.prototype.IndexOfNative$quorum_text$quorum_text$quorum_integer = function(left, prefix, index) {
    return left.valueOf().indexOf(prefix, index);
};
String.prototype.IsEmptyNative$quorum_text = function(left) {
    return (left.length === 0 || !left.trim());
};
this.SplitIntoLines = function() {
    var result = this.split("\r");
    var resultArray = new quorum_Libraries_Containers_Array_();
    for (i = 0; i < result.length; i++) {
        resultArray.Add$quorum_Libraries_Language_Object(new quorum_Libraries_Language_Types_Text_(false, result[i]));
    }
    return resultArray;
};
String.prototype.ReplaceNative$quorum_text$quorum_text$quorum_text = function(left, old, replacement) {
    return left.replace(old, replacement);
};
String.prototype.GetSubtextNative$quorum_text$quorum_integer = function(left, start) {
    return left.substring(start);
};
String.prototype.ToLowerCase = function() {
    return this.valueOf().toLowerCase();
};
String.prototype.ToUpperCase = function() {
    return this.valueOf().toUpperCase();
};
String.prototype.TrimNative$quorum_text = function(left) {
    return left.trim();
};
String.prototype.EqualsIgnoringCaseNative$quorum_text$quorum_text = function(left, right) {
    return left.toLowerCase() == right.toLowerCase();
};
String.prototype.CompareInt$quorum_text$quorum_text$quorum_boolean = function(left, right, isIgnoringCase) {
    if (isIgnoringCase == false) {
        result = left.localeCompare(right);
        if (result != 0 && left.toLowerCase() == right.toLowerCase()) result *= -1;
        return result;
    } else
        return left.toLowerCase().localeCompare(right.toLowerCase());
};
String.prototype.GetSize = function() {
    return this.valueOf().length;
};
String.prototype.GetHashCode = function() {
    var theString = this.valueOf();
    var size = this.valueOf().length;
    var h = 0;
    for (var i = 0; i < size; i++) {
        h = 31*h + theString.charCodeAt(i);
    }
    return h;
};
String.prototype.GetSubtext$quorum_integer$quorum_integer = function(startIndex, endIndex) {
    return this.valueOf().substring(startIndex, endIndex);
};
String.prototype.Contains$quorum_text = function(val) {
    return this.ContainsNative$quorum_text$quorum_text(this.valueOf(), val);
};
String.prototype.EndsWith$quorum_text = function(suffix) {
    return this.valueOf().endsWith(suffix);
};
String.prototype.StartsWith$quorum_text = function(prefix) {
    return this.valueOf().startsWith(prefix);
};
String.prototype.IndexOf$quorum_text = function(subText) {
    return this.valueOf().indexOf(subText);
};
String.prototype.IndexOf$quorum_text$quorum_integer = function(subText, index) {
    return this.valueOf().indexOf(subText, index);
};
String.prototype.IsEmpty = function() {
    return this.valueOf().length == 0;
};
String.prototype.Replace$quorum_text$quorum_text = function(old, replacement) {
    return this.valueOf().replace(old, replacement);
};
String.prototype.GetSubtext$quorum_integer = function(startIndex) {
    return this.valueOf().substring(startIndex);
};
String.prototype.Trim = function() {
    return this.valueOf().trim();
};
String.prototype.Equals$quorum_Libraries_Language_Object = function(value) {
    return this.valueOf() == value.GetValue();
};
String.prototype.EqualsIgnoringCase$quorum_Libraries_Language_Object = function(value) {
    return this.valueOf().toLowerCase() == value.GetValue().toLowerCase();
};
String.prototype.Compare$quorum_Libraries_Language_Object = function (value) {
    var valueText = global_CheckCast(value, "Libraries.Language.Types.Text").GetValue();
    var nativeResult = this.CompareInt$quorum_text$quorum_text$quorum_boolean(this.valueOf(), valueText, false);
    return nativeResult;
};
String.prototype.CompareIgnoringCase$quorum_Libraries_Language_Object = function (value) {
    var valueText = global_CheckCast(value, "Libraries.Language.Types.Text").GetValue();
    var nativeResult = this.CompareInt$quorum_text$quorum_text$quorum_boolean(this.valueOf(), valueText, true);
    return nativeResult;
};
String.prototype.GetCharacter$quorum_integer = function(value) {
    return this.GetCharacterNative$quorum_integer(value);
};
String.prototype.Split$quorum_text$quorum_boolean = function(value, keep) {
    var result = this.split(value);
    var resultArray = new quorum_Libraries_Containers_Array_();
    for (var i = 0; i < result.length; i++) {
        if ((i != result.length -1) || (result[i] != "") || (keep))
            resultArray.Add$quorum_Libraries_Language_Object(new quorum_Libraries_Language_Types_Text_(false, result[i]));
    }
    return resultArray;
};
String.prototype.Split$quorum_text = function(value) {
    return this.Split$quorum_text$quorum_boolean(value, false);
};
