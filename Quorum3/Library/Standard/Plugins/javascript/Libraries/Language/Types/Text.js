function plugins_quorum_Libraries_Language_Types_Text_(optional) {
    this.text = "";
    
    if(optional === undefined) {
    } else {
        this.text = optional;
    }
    
    //private system action SetValueNative(text value)
    this.SetValueNative$quorum_text = function (value) {
        this.text = value;
    };
    //private system action GetCharacterNative(integer index) returns text
    this.GetCharacterNative$quorum_integer = function (index) {
        return this.text.GetCharacterNative$quorum_integer(index);
    };
    //private system action GetSubstringNative(integer startIndex, integer endIndex) returns text
    this.GetSubstringNative$quorum_integer$quorum_integer = function (startIndex, endIndex) {
        return this.text.GetSubstringNative(startIndex, endIndex);
    };
    //system action ParseInteger returns integer 
    this.ParseInteger = function () {
        return this.text.ParseInteger();
    };
    //system action ParseNumber returns number
    this.ParseNumber = function () {
        return this.text.ParseNumber();
    };
    //system action ParseBoolean returns boolean
    this.ParseBoolean = function () {
        return this.text.ParseBoolean();
    };
    //system action GetLineFeed() returns text
    this.GetLineFeed = function () {
        return this.text.GetLineFeed();
    };
    //system action GetCarriageReturn() returns text
    this.GetCarriageReturn = function () {
        return this.text.GetCarriageReturn();
    };
    //system action GetTab() returns text
    this.GetTab = function () {
        return this.text.GetTab();
    };
    //system action GetDoubleQuote() returns text
    this.GetDoubleQuote = function () {
        return this.text.GetDoubleQuote();
    };
    //system action GetUnicodeValue(integer twosCompliment) returns text
    this.GetUnicodeValue$quorum_integer = function (value) {
        return this.text.GetUnicodeValue$quorum_integer(value);
    };
    this.GetUnicodeInteger$quorum_integer = function (value) {
        return this.text.GetUnicodeInteger$quorum_integer(value);
    };
    //private system action ContainsNative(text lhv, text rhv) returns boolean
    this.ContainsNative$quorum_text$quorum_text = function(left, right) {
        return this.text.ContainsNative$quorum_text$quorum_text(left, right);
    };
    //private system action `Native(text left, text suffix) returns boolean
    this.EndsWithNative$quorum_text$quorum_text = function(left, right) {
        return this.text.EndsWithNative$quorum_text$quorum_text(left, right);
    };
    //private system action StartsWithNative(text left, text prefix) returns boolean
    this.StartsWithNative$quorum_text$quorum_text = function(left, right) {
        return this.text.StartsWithNative$quorum_text$quorum_text(left, right);
    };
    //private system action IndexOfNative(text left, text prefix) returns integer
    this.IndexOfNative$quorum_text$quorum_text = function(left, right) {
        return this.text.IndexOfNative$quorum_text$quorum_text(left, right);
    };
    //private system action IndexOfNative(text left, text prefix, integer index) returns integer
    this.IndexOfNative$quorum_text$quorum_text$quorum_integer = function(left, right, index) {
        return this.text.IndexOfNative$quorum_text$quorum_text$quorum_integer(left, right, index);
    };
    //private system action IsEmptyNative(text left) returns boolean
    this.IsEmptyNative$quorum_text = function(left) {
        return this.text.IsEmptyNative$quorum_text(left);
    };
    //private system action ReplaceNative(text left, text old, text replacement) returns text
    this.ReplaceNative$quorum_text$quorum_text$quorum_text = function(left, old, replacement) {
        return this.text.ReplaceNative$quorum_text$quorum_text$quorum_text(left, old, replacement);
    };
    //private system action GetSubtextNative(text left, integer start) returns text
    this.GetSubtextNative$quorum_text$quorum_integer = function(left, index) {
        return this.text.GetSubtextNative$quorum_text$quorum_integer(left, index);
    };
    //system action ToLowerCase() returns text
    this.ToLowerCase = function () {
        return this.text.ToLowerCase();
    };
    //system action ToUpperCase() returns text
    this.ToUpperCase = function () {
        return this.text.ToUpperCase();
    };
    //private system action TrimNative(text left) returns text
    this.TrimNative$quorum_text = function(left) {
        return this.text.TrimNative$quorum_text(left);
    };
    //private system action EqualsIgnoringCaseNative(text left, text right) returns boolean
    this.EqualsIgnoringCaseNative$quorum_text$quorum_text = function (left, right) {
        return this.text.EqualsIgnoringCaseNative$quorum_text$quorum_text(left, right);
    };
    //private system action CompareInt(text left, text right, boolean isIgnoringCase) returns integer
    this.CompareInt$quorum_text$quorum_text$quorum_boolean = function (left, right, isIgnoringCase) {
        return this.text.CompareInt$quorum_text$quorum_text$quorum_boolean(left, right, isIgnoringCase);
    };
    //system action GetSize() returns integer
    this.GetSize = function () {
        return this.text.GetSize();
    };
    //system action GetHashCode() returns integer
    this.GetHashCode = function() {
        return this.text.GetHashCode();
    };
    //system action GetSubtext(integer startIndex, integer endIndex) returns text
    this.GetSubtext$quorum_integer$quorum_integer = function(startIndex, endIndex) {
        return this.text.GetSubtext$quorum_integer$quorum_integer(startIndex, endIndex);
    };
}

//SetValue and GetValue are compiler checked, do not implement
//private system action SetValueNative(text value) //DO NOT IMPLEMENT
//action SetValue(text i) //DO NOT IMPLEMENT
//action GetValue returns text //DO NOT IMPLEMENT


//private system action GetCharacterNative(integer index) returns text
String.prototype.GetCharacterNative$quorum_integer = function(index) {
    return this.valueOf().charAt(index);
};
//private system action GetSubstringNative(integer startIndex, integer endIndex) returns text
String.prototype.GetCharacterNative$quorum_integer$quorum_integer = function(startIndex, endIndex) {
    return this.valueOf().charAt(startIndex, endIndex);
};
//system action ParseInteger returns integer    
String.prototype.ParseInteger = function() {
    return parseInt(this.valueOf());
};
//system action ParseNumber returns number
String.prototype.ParseNumber = function() {
    return parseFloat(this.valueOf());
};
//system action ParseBoolean returns boolean
String.prototype.ParseBoolean = function() {
    if (this.valueOf() == "true")
        return true;
    else if (this.valueOf() == "false")
        return false;
    else
        return false;
};
//system action GetLineFeed() returns text
String.prototype.GetLineFeed = function() {
    return String.fromCharCode(10);
};
//system action GetCarriageReturn() returns text
String.prototype.GetCarriageReturn = function() {
    return String.fromCharCode(13);
};
//system action GetTab() returns text
String.prototype.GetTab = function() {
    return String.fromCharCode(9);
};
//system action GetDoubleQuote() returns text
String.prototype.GetDoubleQuote = function() {
    return String.fromCharCode(34);
};
//system action GetUnicodeValue(integer twosCompliment) returns text
String.prototype.GetUnicodeValue$quorum_integer = function(value) {
    return String.fromCharCode(value);
};
String.prototype.GetUnicodeInteger$quorum_integer = function(value) {
    return this.valueOf().charCodeAt(value);
};
//private system action ContainsNative(text lhv, text rhv) returns boolean
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
//private system action EndsWithNative(text left, text suffix) returns boolean
String.prototype.EndsWithNative$quorum_text$quorum_text = function(left, suffix) {
    return left.valueOf().endsWith(suffix);
};
//private system action StartsWithNative(text left, text prefix) returns boolean
String.prototype.StartsWithNative$quorum_text$quorum_text = function(left, prefix) {
    return left.valueOf().startsWith(prefix);
};
//private system action IndexOfNative(text left, text prefix) returns integer
String.prototype.IndexOfNative$quorum_text$quorum_text = function(left, prefix) {
    return left.valueOf().indexOf(prefix);
};
//private system action IndexOfNative(text left, text prefix, integer index) returns integer
String.prototype.IndexOfNative$quorum_text$quorum_text$quorum_integer = function(left, prefix, index) {
    return left.valueOf().indexOf(prefix, index);
};
//private system action IsEmptyNative(text left) returns boolean
String.prototype.IsEmptyNative$quorum_text = function(left) {
    return (left.length === 0 || !left.trim());
};
//private system action ReplaceNative(text left, text old, text replacement) returns text
String.prototype.ReplaceNative$quorum_text$quorum_text$quorum_text = function(left, old, replacement) {
    return left.replace(old, replacement);
};
//private system action GetSubtextNative(text left, integer start) returns text
String.prototype.GetSubtextNative$quorum_text$quorum_integer = function(left, start) {
    return left.substring(start);
};
//system action ToLowerCase() returns text
String.prototype.ToLowerCase = function() {
    return this.valueOf().toLowerCase();
};
//system action ToUpperCase() returns text
String.prototype.ToUpperCase = function() {
    return this.valueOf().toUpperCase();
};
//private system action TrimNative(text left) returns text
String.prototype.TrimNative$quorum_text = function(left) {
    return left.trim();
};
//private system action EqualsIgnoringCaseNative(text left, text right) returns boolean
String.prototype.EqualsIgnoringCaseNative$quorum_text$quorum_text = function(left, right) {
    return left.toLowerCase() == right.toLowerCase();
};
//private system action CompareInt(text left, text right, boolean isIgnoringCase) returns integer
String.prototype.CompareInt$quorum_text$quorum_text$quorum_boolean = function(left, right, isIgnoringCase) {
    if (isIgnoringCase == false) {
        result = left.localeCompare(right);
        if (result != 0 && left.toLowerCase() == right.toLowerCase()) result *= -1;
        return result;
    } else
        return left.toLowerCase().localeCompare(right.toLowerCase());
};
//system action GetSize() returns integer
String.prototype.GetSize = function() {
    return this.valueOf().length;
};
//system action GetHashCode() returns integer
String.prototype.GetHashCode = function() {
    var theString = this.valueOf();
    var size = this.valueOf().length;
    var h = 0;
    for (var i = 0; i < size; i++) {
        h = 31*h + theString.charCodeAt(i);
    }
    return h;
};
//system action GetSubtext(integer startIndex, integer endIndex) returns text
String.prototype.GetSubtext$quorum_integer$quorum_integer = function(startIndex, endIndex) {
    return this.valueOf().substring(startIndex, endIndex);
};

//action Contains(text val) returns boolean
String.prototype.Contains$quorum_text = function(val) {
    return this.ContainsNative$quorum_text$quorum_text(this.valueOf(), val);
};
//action EndsWith(text suffix) returns boolean
String.prototype.EndsWith$quorum_text = function(suffix) {
    return this.valueOf().endsWith(suffix);
};
//action StartsWith(text prefix) returns boolean
String.prototype.StartsWith$quorum_text = function(prefix) {
    return this.valueOf().startsWith(prefix);
};
//action IndexOf(text subText) returns integer
String.prototype.IndexOf$quorum_text = function(subText) {
    return this.valueOf().indexOf(subText);
};
//action IndexOf(text subText, integer index) returns integer
String.prototype.IndexOf$quorum_text$quorum_integer = function(subText, index) {
    return this.valueOf().indexOf(subText, index);
};
//action IsEmpty() returns boolean
String.prototype.IsEmpty = function() {
    return this.valueOf().length == 0;
};
//action Replace(text old, text replacement) returns text
String.prototype.Replace$quorum_text$quorum_text = function(old, replacement) {
    return this.valueOf().replace(old, replacement);
};
//action GetSubtext(integer startIndex) returns text
String.prototype.GetSubtext$quorum_integer = function(startIndex) {
    return this.valueOf().substring(startIndex);
};
//action Trim() returns text
String.prototype.Trim = function() {
    return this.valueOf().trim();
};
//action Equals(Object object) returns boolean
String.prototype.Equals$quorum_Libraries_Language_Object = function(value) {
    return this.valueOf() == value.GetValue();
};
//action EqualsIgnoringCase(Object object) returns boolean
String.prototype.EqualsIgnoringCase$quorum_Libraries_Language_Object = function(value) {
    return this.valueOf().toLowerCase() == value.GetValue().toLowerCase();
};
//action Compare(Object object) returns CompareResult
String.prototype.Compare$quorum_Libraries_Language_Object = function (value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();
    var valueText = global_CheckCast(value, "Libraries.Language.Types.Text").GetValue();
    var nativeResult = this.CompareInt$quorum_text$quorum_text$quorum_boolean(this.valueOf(), valueText, false);
    if ((nativeResult == 0)) {
        result.result = result.EQUAL;
    }
    else if ((nativeResult < 0)) {
        result.result = result.SMALLER;
    }
    else {
        result.result = result.LARGER;
    }
    return result;
};
//action CompareIgnoringCase(Object object) returns CompareResult
String.prototype.CompareIgnoringCase$quorum_Libraries_Language_Object = function (value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();
    var valueText = global_CheckCast(value, "Libraries.Language.Types.Text").GetValue();
    var nativeResult = this.CompareInt$quorum_text$quorum_text$quorum_boolean(this.valueOf(), valueText, true);
    if ((nativeResult == 0)) {
        result.result = result.EQUAL;
    }
    else if ((nativeResult < 0)) {
        result.result = result.SMALLER;
    }
    else {
        result.result = result.LARGER;
    }
    return result;
};
//action GetCharacter(integer index) returns text
String.prototype.GetCharacter$quorum_integer = function(value) {
    return this.GetCharacterNative$quorum_integer(value);
};
//action Split(text delimiter) returns Array<text>
String.prototype.Split$quorum_text = function(value) {
    var result = this.split(value);
    var resultArray = new quorum_Libraries_Containers_Array_();
    for (i = 0; i < result.length; i++) {
        resultArray.Add$quorum_Libraries_Language_Object(new quorum_Libraries_Language_Types_Text_(false, result[i]));
    }
    return resultArray;
};
