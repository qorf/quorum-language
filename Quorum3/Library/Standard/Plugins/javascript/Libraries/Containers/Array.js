function plugins_quorum_Libraries_Containers_Array_() {
    this.array_ = [];
    this.SetNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if (index >= 0 && index < this.length) {
            array_[index] = value;
        } else {
            //out of range
        }
    };
    
    this.GetNative$quorum_integer = function (index) {
        if (index >= 0 && index < this.length) {
            return array_[index].valueOf();
        } else {
            //out of range
        }
    };
    
    this.AddNative$quorum_Libraries_Language_Object = function (value) {
        array_.push(value);
    };
    
    this.AddNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if (index >= 0 && index <= this.length) {
            array_.splice(index, 0, value);
        } else {
            //out of range
        }
    };
    
    this.SetSizeNativee$quorum_integer = function (size) {};
    
    this.GetMaxSize = function () {
        return 0;
    };
    
    this.GetSize = function () {
        return 0;
    };
    
    this.GetAutoResize = function () {
        return false;
    };
    
    this.SetAutoResize$quorum_boolean = function (size) {};
    
    this.SetMaxSize$quorum_integer = function (size) {};
    
    this.Empty = function () {
        array_.length = 0;
    };
    
    this.IsEmpty = function () {
        return array_.length == 0;
    };
    
    this.RemoveAtNative$quorum_integer = function (index) {
        if (index >= 0 && index <= this.length) {
            return left.splice(index, 1).toString();
        } else {
            //out of range
        }
    };
}