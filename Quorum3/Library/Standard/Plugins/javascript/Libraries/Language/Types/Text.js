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
    this.GetSubstringNative = function (startIndex, endIndex) {
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

//private system action SetValueNative(text value)
//private system action GetCharacterNative(integer index) returns text
//private system action GetSubstringNative(integer startIndex, integer endIndex) returns text

//system action ParseInteger returns integer    
String.prototype.ParseInteger = function() {
    return parseInt(this.valueOf());
};
//system action ParseNumber returns number
//system action ParseBoolean returns boolean
//system action GetLineFeed() returns text
//system action GetCarriageReturn() returns text
//system action GetTab() returns text
//system action GetDoubleQuote() returns text
//system action GetUnicodeValue(integer twosCompliment) returns text
//private system action ContainsNative(text lhv, text rhv) returns boolean
//private system action EndsWithNative(text left, text suffix) returns boolean
//private system action StartsWithNative(text left, text prefix) returns boolean
//private system action IndexOfNative(text left, text prefix) returns integer
//private system action IndexOfNative(text left, text prefix, integer index) returns integer
//private system action IsEmptyNative(text left) returns boolean
//private system action ReplaceNative(text left, text old, text replacement) returns text
//private system action GetSubtextNative(text left, integer start) returns text
//system action ToLowerCase() returns text
//system action ToUpperCase() returns text
//private system action TrimNative(text left) returns text
//private system action EqualsIgnoringCaseNative(text left, text right) returns boolean
//private system action CompareInt(text left, text right, boolean isIgnoringCase) returns integer
//system action GetSize() returns integer
//system action GetHashCode() returns integer
//system action GetSubtext(integer startIndex, integer endIndex) returns text
    
//action Contains(text val) returns boolean
//action EndsWith(text suffix) returns boolean
//action StartsWith(text prefix) returns boolean
//action IndexOf(text subText) returns integer
//action IndexOf(text subText, integer index) returns integer
//action IsEmpty() returns boolean
//action Replace(text old, text replacement) returns text
//action GetSubtext(integer startIndex) returns text
//action Trim() returns text
//action SetValue(text i)
//action GetValue returns text
//action Equals(Object object) returns boolean
//action EqualsIgnoringCase(Object object) returns boolean
//action Compare(Object object) returns CompareResult
//action CompareIgnoringCase(Object object) returns CompareResult
//action GetCharacter(integer index) returns text
//action Split(text delimiter) returns Array<text>


String.prototype.ParseNumber$quorum_number = function(value) {
    return this.valueOf().value;
};

String.prototype.ParseBoolean$quorum_boolean = function(value) {
    return this.valueOf().value;
};

String.prototype.GetLineFeed = function() {
    return String.valueOf().fromCharCode(10);
};

String.prototype.GetCarriageReturn = function() {
    return String.valueOf().fromCharCode(13);
};

String.prototype.GetTab = function() {
    return String.valueOf().fromCharCode(9);
};

String.prototype.GetDoubleQuote = function() {
    return String.valueOf().fromCharCode(34);
};

String.prototype.GetUnicodeValue$quorum_integer = function(value) {
    return String.valueOf().fromCharCode(value);
};

String.prototype.Contains$quorum_text = function(right) {
    return this.valueOf().contains(right);
};

String.prototype.EndsWith$quorum_text = function(right) {
    return this.valueOf().endsWith(right);
};

String.prototype.StartsWith$quorum_text = function(right) {
    return this.valueOf().startsWith(right);
};

String.prototype.IndexOf$quorum_text = function(right) {
    return this.valueOf().indexOf(right);
};

String.prototype.IndexOf$quorum_text$quorum_text = function(right, index) {
    return this.valueOf().indexOf(right, index);
};

String.prototype.IndexOf$quorum_text$quorum_integer = function (right, index) {
    return this.valueOf().indexOf(right, index);
};

String.prototype.IsEmpty = function() {
    return (this.valueOf().length === 0 || !this.valueOf().trim());
};

String.prototype.Replace$quorum_text$quorum_text = function(old, replacement) {
    return this.valueOf().replace(old, replacement);
};

String.prototype.GetSubtext$quorum_integer = function(index) {
    return this.valueOf().substr(index);
};

String.prototype.ToLowerCase = function() {
    return this.valueOf().toLowerCase;
};

String.prototype.ToUpperCase = function() {
    return this.valueOf().toLowerUpper;
};

String.prototype.Trim = function() {
    return this.valueOf().trim();
};

String.prototype.SetValue$quorum_text = function(value) {
    this.text = value;
};

String.prototype.GetValue = function() {
    return this.text;
};

String.prototype.Equals$quorum_text = function(value) {
    return this.text === value;
};

String.prototype.GetSize = function() {
    return this.valueOf().length;
};

String.prototype.GetCharacter$quorum_integer = function(index) {
    return this.valueOf().charAt(index);
};

String.prototype.Compare$quorum_Libraries_Language_Object = function(value) {
    var result = new quorum_Libraries_Language_Support_CompareResult_();
    var thisOne = this.value;
    
    var object = new quorum_Libraries_Language_Types_Text_(false, this.valueOf());
    
    var b = global_CheckCast(object, "Libraries.Language.Types.Text");

    var other = b.GetValue();
    var nativeResult = this.valueOf().localeCompare(other.GetValue());
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
