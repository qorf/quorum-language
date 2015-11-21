function plugins_quorum_Libraries_Language_Types_Text_(optional) {
    this.text = false;
    
    if(optional === undefined) {
    } else {
        text = optional;
    }
    this.SetValueNative$quorum_text = function (value) {
        text = value;
    };
    
    this.ContainsNative$quorum_text$quorum_text = function(left, right) {
        return left.contains(right);
    };
    
    this.EndsWithNative$quorum_text$quorum_text = function(left, right) {
        return left.endsWith(right);
    };
    
    this.StartsWithNative$quorum_text$quorum_text = function(left, right) {
        return left.startsWith(right);
    };
    
    this.IndexOfNative$quorum_text$quorum_text = function(left, right) {
        return left.indexOf(right);
    };
       
    this.IndexOfNative$quorum_text$quorum_text$quorum_integer = function(left, right, index) {
        if (index < left.length && index >= 0)
            return left.indexOf(right, index);
        else
            return -1; //out of range
    };

    this.IsEmptyNative$quorum_text = function(left) {
        return (left.length === 0 || !left.trim());
    };
       
    this.ReplaceNative$quorum_text$quorum_text$quorum_text = function(left, old, replacement) {
        return left.replace(old, replacement);
    };
       
    this.GetSubtextNative$quorum_text$quorum_integer = function(left, index) {
        if (index < left.length && index >= 0)
            return left.substr(index);
        else
            return -1; //out of range 
    };
       
    this.TrimNative$quorum_text = function(left) {
        return left.trim();
    };
       
    this.ToUpperCaseNative$quorum_text = function (left) {
        return left.ToLowerCase();
    };
       
    this.ToLowerCaseNative$quorum_text = function (left) {
        return left.ToLowerCase();
    };
       
    this.GetSize$quorum_text = function (left) {
        return left.length;
    };
    
    this.GetCharacterAt$quorum_text$quorum_integer = function (left, index) {
        return left.charAt(index);
    };
}

String.prototype.ParseInteger$quorum_integer = function(value) {
    return this.valueOf().value;
};

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
