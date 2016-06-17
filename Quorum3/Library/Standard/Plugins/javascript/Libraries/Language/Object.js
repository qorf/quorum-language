function plugins_quorum_Libraries_Language_Object_() {
    this.GetHashCode = function (value) {
        return 0;
    };
}

function global_number_converter_(value) {
    return value % 1 == 0 ? value.toFixed(1) : value;
}

function global_InstanceOf(variable, name) {
    var value = variable.parentNames_;
    for	(index = 0; index < value.length; index++) {
        if(value[index] == name) {
            return true;
        }
    }
    return false;
}

function global_parseInteger(text) {
    text = text.trim();
    if(text == null) {
        var error = new quorum_Libraries_Language_Errors_CastError_();
        throw error;
    }
    if(text.length == 0) {
        var error = new quorum_Libraries_Language_Errors_CastError_();
        throw error;
    }
    var index = 0;
    var result = 0;
    var isMinus = false;
    if(text.charAt(index) == '+') {
        index = 1;
    } else if(text.charAt(index) == '-') {
        isMinus = true;
        index = 1;
    }
    for (; index < text.length; index++) {
        var char = text.charCodeAt(index) - 48;
        if(char >= 0 && char < 10) {
            result = result * 10;
            result = result + char;
        } else { /* unlike typical javascript, throw an exception from Quorum */
            var error = new quorum_Libraries_Language_Errors_CastError_();
            throw error;
        }
    }
    if(isMinus) {
        result = result * -1;
    }
    return result;
}

function global_parseNumber(text) {
    text = text.trim();
    if (text == null) {
        var error = new quorum_Libraries_Language_Errors_CastError_();
        throw error;
    }
    if (text.length == 0) {
        var error = new quorum_Libraries_Language_Errors_CastError_();
        throw error;
    }
    if(/^(\-|\+)?([0-9]+(\.[0-9]+)?|Infinity)$/.test(text)) {
      return Number(text);
    } else {
        var error = new quorum_Libraries_Language_Errors_CastError_();
        throw error;
    }
}

function global_CheckCast(from, to) {
    var names = from.parentNames_;
    for(i = 0; i < names.length; i++) {
        if(names[i] == to) {
            return from;
        }
    }
    var error = new quorum_Libraries_Language_Errors_CastError_();
    throw error;
}

function global_ErrorCheck(error) {
    names = error.parentNames_;
    if(names == null) {
        return new quorum_Libraries_Language_Errors_Error_();
    } else {
        return error; /*it's already a quorum error*/
    }
}

function global_GetValue_(value, type) {
    if (value == null) {
        if(type == "number" || type == "boolean" || type == "integer") {
            throw "party foul";
        } else {
            return null;
        }
    } else {
        return value.GetValue();
    }
}