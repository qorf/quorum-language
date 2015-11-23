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