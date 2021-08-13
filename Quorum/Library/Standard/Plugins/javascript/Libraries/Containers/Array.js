function plugins_quorum_Libraries_Containers_Array_() {
    this.array_ = [];
    this.size = 0;
    this.capacity = 10;
    this.autoResize = true;
       
    this.QuickSetSize = function (value) {
        this.size = value;
        if(value > this.capacity) {
            this.capacity = this.capacity * 2;
        }
    };

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
        this.QuickSetSize(this.size + 1);
    };

    

    this.AddNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        this.array_.splice(index, 0, value);
        this.QuickSetSize(this.size + 1);
    };

    this.SetSizeNative$quorum_integer = function (size) {
        if(this.array_.length > size) {
            var array2_ = [];
            for(i = 0; i < size; i++) {
                array2_.push(this.array_[i]);
            }
            this.array_ = array2_;
        } else if(this.array_.length < size) {
            var array2_ = [];
            for(i = 0; i < size; i++) {
                if(i < this.array_.length) {
                    array2_.push(this.array_[i]);
                } else {
                    array2_.push(null);
                }
            }
            this.array_ = array2_;
        }
        this.QuickSetSize(size);
        this.capacity = size;
    };
    
    this.SetSizeNoFillNative$quorum_integer = function (size) {
        this.SetSizeNative$quorum_integer(size); //should this be the same in JS mode?
    };

    this.GetMaxSize = function () {
        return this.capacity;
    };

    this.GetSize = function () {
        return this.size;
    };

    this.GetAutoResize = function () {
        return this.autoResize;
    };

    this.SetAutoResize$quorum_boolean = function (resize) {
        this.autoResize = resize;
    };

    this.SetMaxSize$quorum_integer = function (size) {
        if(this.array_.length > size) {
            var array2_ = [];
            for(i = 0; i < size; i++) {
                array2_.push(this.array_[i]);
            }
            this.array_ = array2_;
            this.capacity = size;
        } else if(this.array_.length < size) {
            this.capacity = size;
        }
    };

    this.Empty = function () {
        this.array_.length = 0;
        this.size = 0;
    };
    
    this.Empty$quorum_boolean = function (value) {
        if(value) {
            Empty();
        } else {
            this.size = 0;
        }
    };

    this.IsEmpty = function () {
        return this.size == 0;
    };

    this.RemoveAtNative$quorum_integer = function (index) {
        if (index >= 0 && index <= this.array_.length) {
            var value = this.array_.splice(index, 1);
            value = value[0];
            this.QuickSetSize(this.size - 1);
            return value;
        } else {
        }
    };
    
    this.ClearContents$quorum_integer$quorum_integer = function (start, stop)
    {
        for (var i = start; i < stop; i++) {
            if (i >= this.array_.length)
                return;
            this.array_[i] = null;
        }
    };
}