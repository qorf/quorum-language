
function plugins_quorum_Libraries_Containers_Array_() {
    this.array_ = [];
    this.maxSize = 10; //this value is ignored, but it makes the behavior similar to other targets
    
    this.SetNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if (index >= 0 && index < this.array_.length) {
            this.array_[index] = value;
        } else {
            //out of range
        }
    };
    
    this.GetNative$quorum_integer = function (index) {
        if (index >= 0 && index < this.array_.length) {
            return this.array_[index];
        } else {
            //out of range
        }
    };
    
    this.AddNative$quorum_Libraries_Language_Object = function (value) {
        this.array_.push(value);
    };
    
    this.AddNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if (index >= 0 && index <= this.array_.length) {
            this.array_.splice(index, 0, value);
        } else {
            //out of range
        }
    };
    
    this.SetSizeNative$quorum_integer = function (size) {
        var value = this.GetSize();
        for(i = value; i < size; i++) {
            this.array_.push(null);
        }
    };
    
    this.GetMaxSize = function () {
        return this.maxSize;
    };
    
    this.GetSize = function () {
        return this.array_.length;
    };
    
    this.GetAutoResize = function () {
        return true;
    };
    
    this.SetAutoResize$quorum_boolean = function (resize) {
    };
    
    this.SetMaxSize$quorum_integer = function (size) {
        this.maxSize = size;
    };
    
    this.Empty = function () {
        this.array_.length = 0;
    };
    
    this.IsEmpty = function () {
        return this.array_.length == 0;
    };
    
    this.RemoveAtNative$quorum_integer = function (index) {
        if (index >= 0 && index <= this.array_.length) {
            return this.array_.splice(index, 1).toString();
        } else {
            //out of range
        }
    };
}