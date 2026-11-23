// The errors Text raises, as its Quorum code and Java plugin do.
function global_Text_Location_Error_(index) {
    var error = new quorum_Libraries_Language_Errors_InvalidLocationError_();
    error.SetErrorMessage$quorum_text("Text index out of range, " + index);
    return error;
}
function global_Text_Parse_Error_(kind) {
    var error = new quorum_Libraries_Language_Errors_ParseError_();
    error.SetErrorMessage$quorum_text("Parse Error: The text value could not be converted to " + kind + ".");
    return error;
}

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
// As Java's Integer.parseInt: an optional sign and digits only, within
// the integer range; anything else (spaces, 12abc, 1_000, 0x10) fails.
String.prototype.ParseInteger = function() {
    var text = this.valueOf();
    if (!/^[+-]?[0-9]+$/.test(text)) throw global_Text_Parse_Error_("an integer");
    var value = Number(text);
    if (value < -2147483648 || value > 2147483647) throw global_Text_Parse_Error_("an integer");
    return value;
};
// As Java's Double.parseDouble: surrounding spaces are ignored; a sign,
// digits with an optional point and exponent, NaN or Infinity, and an
// optional f/F/d/D suffix. Anything else (abc, 12abc, 12.5.3, 1_000) fails.
String.prototype.ParseNumber = function() {
    var text = this.valueOf().replace(/^[\x00-\x20]+|[\x00-\x20]+$/g, "");
    if (!/^[+-]?(NaN|Infinity|(([0-9]+\.?[0-9]*|\.[0-9]+)([eE][+-]?[0-9]+)?)[fFdD]?)$/.test(text)) {
        throw global_Text_Parse_Error_("a number");
    }
    return Number(text.replace(/[fFdD]$/, ""));
};
String.prototype.ParseBoolean = function() {
    // As Java's Boolean.parseBoolean: "true" in any letter case.
    return this.valueOf().toLowerCase() === "true";
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
// Fails outside the text, as Java's charAt does.
String.prototype.GetUnicodeInteger$quorum_integer = function(value) {
    var text = this.valueOf();
    if (value < 0 || value >= text.length) throw global_Text_Location_Error_(value);
    return text.charCodeAt(value);
};
// As Java's String.contains.
String.prototype.ContainsNative$quorum_text$quorum_text = function(left, searchString) {
    return left.valueOf().includes(searchString);
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
// As Java's String.isEmpty: spaces are not empty.
String.prototype.IsEmptyNative$quorum_text = function(left) {
    return left.length === 0;
};
// Split and SplitIntoLines are written once, in Text.quorum; a primitive
// text hands them a Text, as Java's PrimitiveSplit does.
function global_Text_Of_(value) {
    var result = new quorum_Libraries_Language_Types_Text_();
    result.SetValue$quorum_text(value);
    return result;
}
String.prototype.SplitIntoLines = function() {
    return global_Text_Of_(this.valueOf()).SplitIntoLines();
};
// Every match, as Java's String.replace. The replacement is passed through
// a function so $ characters in it aren't treated as patterns.
String.prototype.ReplaceNative$quorum_text$quorum_text$quorum_text = function(left, old, replacement) {
    return left.replaceAll(old, function () { return replacement; });
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
// Character-code order, as Java's compareTo and compareToIgnoreCase
// (JavaScript's < compares text by UTF-16 code units, as Java does).
String.prototype.CompareInt$quorum_text$quorum_text$quorum_boolean = function(left, right, isIgnoringCase) {
    if (isIgnoringCase) {
        left = left.toLowerCase();
        right = right.toLowerCase();
    }
    return left < right ? -1 : (left > right ? 1 : 0);
};
String.prototype.GetSize = function() {
    return this.valueOf().length;
};
// Java's String.hashCode, kept to 32 bits at every step.
String.prototype.GetHashCode = function() {
    var theString = this.valueOf();
    var h = 0;
    for (var i = 0; i < theString.length; i++) {
        h = (Math.imul(31, h) + theString.charCodeAt(i)) | 0;
    }
    return h;
};
// Java's substring rules: a start equal to the size is allowed and gives "".
String.prototype.GetSubtext$quorum_integer$quorum_integer = function(startIndex, endIndex) {
    var text = this.valueOf();
    if (startIndex < 0) throw global_Text_Location_Error_(startIndex);
    if (endIndex > text.length || startIndex > endIndex) throw global_Text_Location_Error_(endIndex);
    return text.substring(startIndex, endIndex);
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
// Fails when the start isn't inside the text, as Java's PrimitiveIndexOf.
String.prototype.IndexOf$quorum_text$quorum_integer = function(subText, index) {
    var text = this.valueOf();
    if (index < 0 || index >= text.length) throw global_Text_Location_Error_(index);
    return text.indexOf(subText, index);
};
String.prototype.IsEmpty = function() {
    return this.valueOf().length == 0;
};
// Every match, as Java's String.replace (see ReplaceNative).
String.prototype.Replace$quorum_text$quorum_text = function(old, replacement) {
    return this.valueOf().replaceAll(old, function () { return replacement; });
};
// Java's substring rules: a start equal to the size is allowed and gives "".
String.prototype.GetSubtext$quorum_integer = function(startIndex) {
    var text = this.valueOf();
    if (startIndex < 0 || startIndex > text.length) throw global_Text_Location_Error_(startIndex);
    return text.substring(startIndex);
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
// Fails outside the text, as Java's PrimitiveGetCharacter.
String.prototype.GetCharacter$quorum_integer = function(value) {
    var text = this.valueOf();
    if (value < 0 || value >= text.length) throw global_Text_Location_Error_(value);
    return text.charAt(value);
};
String.prototype.Split$quorum_text$quorum_boolean = function(value, keep) {
    return global_Text_Of_(this.valueOf()).Split$quorum_text$quorum_boolean(value, keep);
};
String.prototype.Split$quorum_text = function(value) {
    return global_Text_Of_(this.valueOf()).Split$quorum_text(value);
};
