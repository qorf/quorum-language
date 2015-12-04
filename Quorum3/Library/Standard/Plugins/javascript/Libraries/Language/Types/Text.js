function plugins_quorum_Libraries_Language_Types_Text_(optional) {
    this.text = "";
    
    if(optional === undefined) {
    } else {
        text = optional;
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
        return this.text.GetSubstringNative$quorum_integer$quorum_integer(startIndex, endIndex);
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
    this.GetUnicodeValue = function (value) {
        return this.text.GetUnicodeValue(value);
    };
    //private system action ContainsNative(text lhv, text rhv) returns boolean
    this.ContainsNative$quorum_text$quorum_text = function(left, right) {
        return this.text.ContainsNative$quorum_text$quorum_text(left, right);
    };
    //private system action EndsWithNative(text left, text suffix) returns boolean
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
    this.GetSubtext = function(startIndex, endIndex) {
        return this.text.GetSubtext(startIndex, endIndex);
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
    return this.valueOf().value;
};
//system action ParseBoolean returns boolean
String.prototype.ParseBoolean = function() {
    return this.valueOf();
};
//system action GetLineFeed() returns text
String.prototype.GetLineFeed = function() {
    return String.valueOf().fromCharCode(10);
};
//system action GetCarriageReturn() returns text
String.prototype.GetCarriageReturn = function() {
    return String.valueOf().fromCharCode(13);
};

//system action GetTab() returns text
String.prototype.GetTab = function() {
    return String.valueOf().fromCharCode(9);
};
//system action GetDoubleQuote() returns text
String.prototype.GetDoubleQuote = function() {
    return String.valueOf().fromCharCode(34);
};

//system action GetUnicodeValue(integer twosCompliment) returns text
String.prototype.GetUnicodeValue$quorum_integer = function(value) {
    return String.valueOf().fromCharCode(value);
};
//private system action ContainsNative(text lhv, text rhv) returns boolean
String.prototype.ContainsNative$quorum_text$quorum_text = function(left, right) {
    return this.valueOf().contains(left, right);
};
//private system action EndsWithNative(text left, text suffix) returns boolean
String.prototype.EndsWith$quorum_text$quorum_text = function(left, suffix) {
    return this.valueOf().endsWith(left, suffix);
};
//private system action StartsWithNative(text left, text prefix) returns boolean
String.prototype.StartsWith$quorum_text$quorum_text = function(left, prefix) {
    return this.valueOf().startsWith(left, prefix);
};
//private system action IndexOfNative(text left, text prefix) returns integer
String.prototype.IndexOfNative$quorum_text$quorum_text = function(left, prefix) {
    return this.valueOf().indexOf(left, prefix);
};
//private system action IndexOfNative(text left, text prefix, integer index) returns integer
String.prototype.IndexOfNative$quorum_text$quorum_text$quorum_integer = function(left, prefix, index) {
    return this.valueOf().indexOf(left, prefix, index);
};
//private system action IsEmptyNative(text left) returns boolean
String.prototype.IsEmptyNative$quorum_text = function(left) {
    return (left.length === 0 || !left.trim());
};
//private system action ReplaceNative(text left, text old, text replacement) returns text
String.prototype.ReplaceNative$quorum_text$quorum_text$quorum_text = function(left, old, replacement) {
    return this.valueOf().replace(left, old, replacement);
};
//private system action GetSubtextNative(text left, integer start) returns text
String.prototype.GetSubtextNative$quorum_text$quorum_integer = function(left, start) {
    return this.valueOf().substr(left, start);
};
//system action ToLowerCase() returns text
String.prototype.ToLowerCase = function() {
    return this.valueOf().toLowerCase;
};
//system action ToUpperCase() returns text
String.prototype.ToUpperCase = function() {
    return this.valueOf().toLowerUpper;
};
//private system action TrimNative(text left) returns text
String.prototype.TrimNative$quorum_text = function(left) {
    return left.trim();
};
//private system action EqualsIgnoringCaseNative(text left, text right) returns boolean
String.prototype.EqualsIgnoringCaseNative$quorum_text$quorum_text = function(left, right) {
    return left === right;
};
//private system action CompareInt(text left, text right, boolean isIgnoringCase) returns integer
String.prototype.CompareInt$quorum_text$quorum_text$quorum_boolean = function(left, right, isIgnoringCase) {
    return 0;
};
//system action GetSize() returns integer
String.prototype.GetSize = function() {
    return this.valueOf().length;
};
//system action GetHashCode() returns integer
String.prototype.GetHashCode = function() {
    return 0;
};
//system action GetSubtext(integer startIndex, integer endIndex) returns text
String.prototype.GetSubtext$quorum_integer$quorum_integer = function(startIndex, endIndex) {
    return 0;
};

//action Contains(text val) returns boolean
String.prototype.Contains$quorum_text = function(val) {
    return false;
};
//action EndsWith(text suffix) returns boolean
String.prototype.EndsWith$quorum_text = function(suffix) {
    return false;
};
//action StartsWith(text prefix) returns boolean
String.prototype.StartsWith$quorum_text = function(prefix) {
    return false;
};
//action IndexOf(text subText) returns integer
String.prototype.IndexOf$quorum_text = function(subText) {
    return "invalid_fix_me";
};
//action IndexOf(text subText, integer index) returns integer
String.prototype.IndexOf$quorum_text$quorum_integer = function(subText, index) {
    return "invalid_fix_me";
};
//action IsEmpty() returns boolean
String.prototype.IsEmpty = function() {
    return this.valueOf().length == 0;
};

//action Replace(text old, text replacement) returns text
String.prototype.Replace$quorum_text$quorum_text = function(old, replacement) {
    return "invalid_fix_me";
};

//action GetSubtext(integer startIndex) returns text
String.prototype.Replace$quorum_integer = function(startIndex) {
    return "invalid_fix_me";
};

//action Trim() returns text
String.prototype.Trim = function() {
    return this.valueOf().trim();
};

//action Equals(Object object) returns boolean
String.prototype.Equals$quorum_Libraries_Language_Object = function(value) {
    return this.text === value;
};
//action EqualsIgnoringCase(Object object) returns boolean
String.prototype.EqualsIgnoringCase$quorum_Libraries_Language_Object = function(value) {
    return this.text === value;
};
//action Compare(Object object) returns CompareResult
String.prototype.Compare$quorum_Libraries_Language_Object = function (value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();

    var object = new quorum_Libraries_Language_Types_Text_(false, this.valueOf());

    var b = global_CheckCast(value, "Libraries.Language.Types.Text");

    var other = b.GetValue();
    var nativeResult = this.valueOf().localeCompare(other);
    if ((nativeResult == 0)) {
        result.result = result.EQUAL;
    }
    else if ((nativeResult < -1)) {
        result.result = result.SMALLER;
    }
    else {
        result.result = result.LARGER;
    }
    return result;
};
//action CompareIgnoringCase(Object object) returns CompareResult
String.prototype.Compare$quorum_Libraries_Language_Object = function (value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();

    var b = global_CheckCast(value, "Libraries.Language.Types.Text");

    var other = b.GetValue();
    var lowerL = other.toLowerCase()
    var lowerR = this.valueOf().toLowerCase()
    var nativeResult = lowerL.localeCompare(lowerR);
    if ((nativeResult == 0)) {
        result.result = result.EQUAL;
    }
    else if ((nativeResult < -1)) {
        result.result = result.SMALLER;
    }
    else {
        result.result = result.LARGER;
    }
    return result;
};

//action GetCharacter(integer index) returns text
String.prototype.GetCharacter$quorum_integer = function(value) {
    return "a";
};
//action Split(text delimiter) returns Array<text>
String.prototype.Split$quorum_text = function(value) {
    return "a";
};
