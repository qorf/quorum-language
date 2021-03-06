function plugins_quorum_Libraries_Containers_Array_() {
    this.array_ = [];
    this.size = 0;
    this.resizable = true;
    this.DEFAULT_CAPACITY = 10;
    this.capacity = this.DEFAULT_CAPACITY;
    this.DEFAULT_RESIZE = 2;

    this.SetNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        this.array_[index] = value;
    };

    this.GetNative$quorum_integer = function (index) {
        return this.array_[index];
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
        this.size = size;
        this.capacity = size;
    };

    this.SetSizeNoFillNative$quorum_integer = function (size) {
        this.SetSizeNative$quorum_integer(size); //should this be the same in JS mode?
    };
    
    this.AddNative$quorum_integer$quorum_Libraries_Language_Object = function (index, value) {
        if(this.size + 1 >= this.capacity && this.resizable) {
            this.capacity = this.capacity * this.DEFAULT_RESIZE;
        } else if(this.size + 1 >= this.capacity && !this.resizable) {
            throw "Array Index Out of Bounds at " + index;
        }
        this.size = this.size + 1;
        this.array_.splice(index, 0, value);
    };

    this.AddNative$quorum_Libraries_Language_Object = function (value) {
        if(this.size >= this.capacity && this.resizable) {
            this.capacity = this.capacity * this.DEFAULT_RESIZE;
        }
        
        if (this.array_.length <= this.size)
            this.array_.push(value);
        else
            this.array_[this.size] = value;
        
        this.size = this.size + 1;
    };

    this.RemoveAtNative$quorum_integer = function (index) {
        var value = this.array_.splice(index, 1);
        value = value[0];
        this.size = this.size - 1;
        return value;
    };

    this.GetMaxSize = function () {
        return this.capacity;
    };

    this.SetMaxSize$quorum_integer = function (size) {
        if(this.capacity > size) {
            var array2_ = [];
            if(this.size > size) {
                this.size = size;
            }
            for(i = 0; i < this.size; i++) {
                array2_.push(this.array_[i]);
            }
            this.array_ = array2_;
            this.capacity = size;
        } else if(this.capacity < size) {
            this.capacity = size;
        }
    };

    this.GetSize = function () {
        return this.size;
    };

    this.GetAutoResize = function () {
        return this.resizable;
    };

    this.SetAutoResize$quorum_boolean = function (resize) {
        this.resizable = resize;
    };

    this.IsEmpty = function () {
        return this.size == 0;
    };

    this.ClearContents$quorum_integer$quorum_integer = function (start, stop)
    {
        for (var i = start; i < stop; i++) {
            this.array_[i] = null;
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
};

function plugins_quorum_Libraries_Containers_Number32BitArray_() {
    this.array_ = null;

    this.Get$quorum_integer = function(index)
    {
        return this.array_[index];
    };

    this.Set$quorum_integer$quorum_number = function(index, value) 
    {
        this.array_[index] = value;
    };

    this.GetSize = function()
    {
        return this.array_.length;
    };
    
    this.SetSize$quorum_integer = function(size)
    {
        this.array_ = new Float32Array(size);
    };

};

function plugins_quorum_Libraries_Containers_Number64BitArray_() {
    this.array_ = null;

    this.Get$quorum_integer = function(index)
    {
        return this.array_[index];
    };

    this.Set$quorum_integer$quorum_number = function(index, value) 
    {
        this.array_[index] = value;
    };

    this.GetSize = function()
    {
        return this.array_.length;
    };
    
    this.SetSize$quorum_integer = function(size)
    {
        this.array_ = new Float64Array(size);
    };
};

function plugins_quorum_Libraries_Containers_Integer32BitArray_() {
    this.array_ = null;

    this.Get$quorum_integer = function(index)
    {
        return this.array_[index];
    };

    this.Set$quorum_integer$quorum_integer = function(index, value) 
    {
        this.array_[index] = value;
    };

    this.GetSize = function()
    {
        return this.array_.length;
    };
    
    this.SetSize$quorum_integer = function(size)
    {
        this.array_ = new Int32Array(size);
    };
};