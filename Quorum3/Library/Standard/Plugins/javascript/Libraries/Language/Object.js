function plugins_quorum_Libraries_Language_Object_() {
    this.GetHashCode = function (value) {
        return 0;
    };
}

function global_number_converter_(value) {
    return value % 1 == 0 ? value.toFixed(1) : value;
}