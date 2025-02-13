class plugins_quorum_Libraries_Containers_Array_ {
    constructor() {
        this.array_ = [];
        this.size = 0;
        this.resizable = true;
        this.DEFAULT_CAPACITY = 10;
        this.capacity = this.DEFAULT_CAPACITY;
        this.DEFAULT_RESIZE = 2;
    }
    
    SetNative$quorum_integer$quorum_Libraries_Language_Object(index, value) {
        this.array_[index] = value;
    };

    GetNative$quorum_integer(index) {
        return this.array_[index];
    };

    SetSizeNative$quorum_integer(size) {
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

    SetSizeNoFillNative$quorum_integer(size) {
        this.SetSizeNative$quorum_integer(size); //should this be the same in JS mode?
    };
    
    AddNative$quorum_integer$quorum_Libraries_Language_Object(index, value) {
        if(this.size + 1 >= this.capacity && this.resizable) {
            this.capacity = this.capacity * this.DEFAULT_RESIZE;
        } else if(this.size + 1 >= this.capacity && !this.resizable) {
            throw "Array Index Out of Bounds at " + index;
        }
        this.size = this.size + 1;
        this.array_.splice(index, 0, value);
    };

    AddNative$quorum_Libraries_Language_Object(value) {
        if(this.size >= this.capacity && this.resizable) {
            this.capacity = this.capacity * this.DEFAULT_RESIZE;
        }
        
        if (this.array_.length <= this.size)
            this.array_.push(value);
        else
            this.array_[this.size] = value;
        
        this.size = this.size + 1;
    };

    RemoveAtNative$quorum_integer(index) {
        var value = this.array_.splice(index, 1);
        value = value[0];
        this.size = this.size - 1;
        return value;
    };

    GetMaxSize() {
        return this.capacity;
    };

    SetMaxSize$quorum_integer(size) {
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

    GetSize() {
        return this.size;
    };

    GetAutoResize() {
        return this.resizable;
    };

    SetAutoResize$quorum_boolean(resize) {
        this.resizable = resize;
    };

    IsEmpty = function () {
        return this.size == 0;
    };

    ClearContents$quorum_integer$quorum_integer(start, stop)
    {
        for (var i = start; i < stop; i++) {
            this.array_[i] = null;
        }
    };

    Empty() {
        this.array_.length = 0;
        this.size = 0;
    };
    
    Empty$quorum_boolean(value) {
        if(value) {
            Empty();
        } else {
            this.size = 0;
        }
    };
};

class plugins_quorum_Libraries_Containers_Number32BitArray_ {
    constructor() {
        this.array_ = null;
    }

    Get$quorum_integer(index)
    {
        return this.array_[index];
    };

    Set$quorum_integer$quorum_number(index, value) 
    {
        this.array_[index] = value;
    };

    GetSize()
    {
        return this.array_.length;
    };
    
    SetSize$quorum_integer(size)
    {
        this.array_ = new Float32Array(size);
    };

};

class plugins_quorum_Libraries_Containers_Number64BitArray_ {
    constructor() {
        this.array_ = null;
    }

    Get$quorum_integer(index)
    {
        return this.array_[index];
    };

    Set$quorum_integer$quorum_number(index, value) 
    {
        this.array_[index] = value;
    };

    GetSize()
    {
        return this.array_.length;
    };
    
    SetSize$quorum_integer(size)
    {
        this.array_ = new Float64Array(size);
    };
};

class plugins_quorum_Libraries_Containers_Integer32BitArray_ {
    constructor() {
        this.array_ = null;
    }

    Get$quorum_integer(index)
    {
        return this.array_[index];
    };

    Set$quorum_integer$quorum_integer(index, value) 
    {
        this.array_[index] = value;
    };

    GetSize()
    {
        return this.array_.length;
    };
    
    SetSize$quorum_integer(size)
    {
        this.array_ = new Int32Array(size);
    };
};