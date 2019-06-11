function plugins_quorum_Libraries_Containers_Array_() {
    this.array_ = [];
    this.maxSize = 10; /*this value is ignored, but it makes the behavior similar to other targets*/
    this.autoResize = true;

    this.SetNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if (index >= 0 && index < this.array_.length) {
            this.array_[index] = value;
        } else {
        }
    };

    this.GetNative$quorum_integer = function (index) {
        if (index >= 0 && index < this.array_.length) {
            return this.array_[index];
        } else {
        }
    };

    this.AddNative$quorum_Libraries_Language_Object = function (value) {
        this.array_.push(value);
        if (this.array_.length >= this.maxSize) {
            this.maxSize = this.maxSize * 2;
        }
    };

    this.AddNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if (index >= 0 && index <= this.array_.length) {
            this.array_.splice(index, 0, value);
            if (this.array_.length >= this.maxSize) {
                this.maxSize = this.maxSize * 2;
            }
        } else {
        }
    };

    this.SetSizeNative$quorum_integer = function (size) {
        var value = this.GetSize();
        for (i = value; i < size; i++) {
            this.array_.push(null);
        }
    };
    
    this.SetSizeNoFillNative$quorum_integer = function (size) {
        this.array_ = [];
        var value = this.GetSize();
        for (i = value; i < size; i++) {
            this.array_.push(null);
        } //In JavaScript mode, you have to fill it, I think
    };

    this.GetMaxSize = function () {
        return this.maxSize;
    };

    this.GetSize = function () {
        return this.array_.length;
    };

    this.GetAutoResize = function () {
        return this.autoResize;
    };

    this.SetAutoResize$quorum_boolean = function (resize) {
        this.autoResize = resize;
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
            var value = this.array_.splice(index, 1);
            value = value[0];
            return value;
        } else {
        }
    };
}