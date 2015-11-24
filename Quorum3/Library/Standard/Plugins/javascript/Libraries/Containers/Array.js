function plugins_quorum_Libraries_Containers_Array_() {
        
    this.SetNative$quorum_Object$quorum_integer$quorum_Object = function (left, index, value) {
        if (index >= 0 && index < this.length) {
            left[index] = value;
        } else {
            //out of range
        }
    };
    
    this.GetNative$quorum_Object$quorum_integer = function (left, index) {
        if (index >= 0 && index < this.length) {
            return left[index].valueOf();
        } else {
            //out of range
        }
    };
    
    this.AddNative$quorum_Object$quorum_Object = function (left, value) {
        left.push(value);
    };
    
    this.AddNative$quorum_Object$quorum_integer$quorum_Object = function (left, index, value) {
        if (index >= 0 && index <= this.length) {
            left.splice(index, 0, value);
        } else {
            //out of range
        }
    };
    
    this.RemoveAtNative$quorum_Object$quorum_integer = function (left, index) {
        if (index >= 0 && index <= this.length) {
            return left.splice(index, 1).toString();
        } else {
            //out of range
        }
    };

}

Array.prototype.GetSize = function() {
    return this.length;
};

Array.prototype.Set = function(index, value) {
    if (index >= 0 && index < this.length) {
        this[index] = value;
    } else {
        //out of range error
    }
};

Array.prototype.Get = function(index) {
    if (index >= 0 && index < this.length) {
        return this[index].valueOf();
    } else {
        //out of range
    }
};

Array.prototype.Add = function (value) {
    this.push(value);
};

Array.prototype.AddAt = function (index, value) {
    if (index >= 0 && index <= this.length) {
        this.splice(index, 0, value);
    } else {
        //out of range
    }
};

Array.prototype.RemoveAt = function (index) {
    if (index >= 0 && index <= this.length) {
        return this.splice(index, 1).toString();
    } else {
        //out of range
    }
};

Array.prototype.GetFirstLocation = function (value) {
    return this.indexOf(value).valueOf();
};

Array.prototype.GetLastLocation = function (value) {
    var i = this.length;
    while (i >= 0) {
        if (this[i] === value) {
            return i;
        }
        i--;
    }
    return -1;
};

Array.prototype.AddToEnd = function (value) {
    this.push(value);
};

Array.prototype.AddToFront = function (value) {
    this.push(value);
};

Array.prototype.Copy = function () {
    //returns object
};

Array.prototype.CopyToArray = function () {
    return this.slice(0);
};

Array.prototype.GetFromFront = function () {
    return this[0].valueOf();
};

Array.prototype.GetFromEnd = function () {
    return this[this.length-1].valueOf();
};

Array.prototype.GetIterator = function () {
    return this.values();
};

Array.prototype.Has = function (value) {
    //return this.includes(value);
    var i = 0;
    while (i < this.length) {
        if (this[i] === value) 
            return true;
        i++;
    }
    return false;
};

Array.prototype.Remove = function (value) {
    var index = this.GetFirstLocation(value);
    if (index >= 0) {
        this.RemoveAt(index);
        return true;
    }
    return false;
};

Array.prototype.RemoveAll = function (value) {
    var removed = false;
    var i = 0;
    while (i < this.length) {
        if (this[i] === value) {
            this.RemoveAt(i);
            removed = true;
        }
        i++;
    }
    return removed;
};

Array.prototype.RemoveFromEnd = function () {
    return this.pop();
};

Array.prototype.RemoveFromFront = function () {
    return this.shift();
};

Array.prototype.Sort = function () {
    this.sort();
};

Array.prototype.Empty = function () {
    this.length = 0;
};

Array.prototype.IsEmpty = function () {
    return (this.length === 0);
};