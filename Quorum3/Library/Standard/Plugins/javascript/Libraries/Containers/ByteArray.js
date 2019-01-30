/*
    The ByteArray class is a specialized kind of array specifically designed
    for low level byte manipulation from files. The purpose is to provide a 
    way to manipulate bytes efficiently in Quorum, which is necessary for 
    some specialized applications. 

    The values in each position in the array are raw bytes, which means 
    any integer returned is 8 bits in length. As such, they are individually 
    between the numbers -128 and 127.
*/

function plugins_quorum_Libraries_Containers_ByteArray_() {
    this.maxSize = 8;
    this.byteArray = new Int8Array(this.maxSize);
    this.isBigEndian = true;
    
    this.Get$quorum_integer = function (location){
        if(location >= 0 && location < this.byteArray.length){
            return this.byteArray[location];
        }
        else{
            throw "That is not a valid location";  
        }
    };
    
    this.Set$quorum_integer$quorum_integer = function(location, value){
        if(this.byteArray === null || location > this.byteArray.length || location < 0) {
            throw "Out of Range";
        }
        this.byteArray[location] = value;
    };
           
    this.GetSize = function(){
        return this.byteArray.length;
    };

    this.GetSubArray$quorum_integer$quorum_integer = function(begin, finish){    
        var array = new quorum_Libraries_Containers_ByteArray_();
        var size = finish - begin;
        var newBytes = new Int8Array(size);
        var j = 0;
        if(size > 0 && begin >= 0 && finish < this.byteArray.length) {
            for(var i = begin; i < finish; i++) {
                newBytes[j] = this.byteArray[i];
                j = j + 1;
            }
        } else {
            newBytes = [];
        }
        array.plugin_.byteArray = newBytes;
        return array;
    };
   
    this.IsBigEndian = function(){
       return this.isBigEndian;    
    };
   
    this.SetBigEndian$quorum_boolean = function(yes){
       this.isBigEndian = yes;     
    };
   

    this.UnsignedIntegerFromByte$quorum_integer = function(byte){
       var unsignedInt = byte & 0x00FF;  
       return unsignedInt;
    };
   
    this.CharacterFromByte$quorum_integer = function(byte){
        var character = String.fromCharCode(byte);
        return character;
    };

    this.CharacterFromTwoBytes$quorum_integer$quorum_integer = function(byte1, byte2){
        var array = new Array(byte1, byte2);
        return String.fromCharCode(this.GetSignedBuffer$quorum_array(array));
    };
    
    this.IntegerFromByte$quorum_integer = function(byte){
        return byte;
    };

    this.NumberFromFourBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        return this.GetFloatBuffer$quorum_array(array);
    };

    this.NumberFromEightBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8){
        var array = new Array(byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8);
        return this.GetFloatBuffer$quorum_array(array);
    };

    this.IntegerFromTwoBytes$quorum_integer$quorum_integer = function(byte1, byte2){
        var array = new Array(byte1, byte2);
        return this.GetSignedBuffer$quorum_array(array);       
    };
    
    this.UnsignedIntegerFromTwoBytes$quorum_integer$quorum_integer = function(byte1, byte2){
       var array = new Array(byte1, byte2);
       return this.GetUnsignedBuffer$quorum_array(array);
    };
   
    this.IntegerFromFourBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        return this.GetSignedBuffer$quorum_array(array);
    };
    
    this.UnsignedIntegerFromFourBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function (byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        return this.GetUnsignedBuffer$quorum_array(array);
    };

    this.SignedIntegerFromFourUnsignedBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        var buffer = new ArrayBuffer(8);
        var view = new DataView(buffer);
        for (var i = 0; i < array.length; i++){
            view.setUint8(i, array[i]);
        }

        return view.getInt32(0, !(this.IsBigEndian()));   
    };
   
    this.TextFromEightBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8){
        var array = new Array(byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8);
        var buffer = new ArrayBuffer(8);
        var view = new DataView(buffer);
        for (var i = 0; i < array.length; i++){
            view.setInt8(i, array[i]);
        } 
        
        if(this.IsBigEndian()){
            var high = view.getInt32(0, !(this.IsBigEndian()));
            var low = view.getUint32(4, !(this.IsBigEndian()));
        } else {
            var low = view.getUint32(0, !(this.IsBigEndian()));
            var high = view.getInt32(4, !(this.IsBigEndian()));
        }

        high = high.toString();
        low = low.toString();
        var bigIntHigh = new quorum_Libraries_Compute_BigInteger_();
        var bigIntLow = new quorum_Libraries_Compute_BigInteger_();
        bigIntHigh.SetValue$quorum_text(high);
        bigIntLow.SetValue$quorum_text(low);
        bigIntHigh = bigIntHigh.ShiftLeft$quorum_integer(32);
        bigIntHigh = bigIntHigh.Add$quorum_Libraries_Compute_BigInteger(bigIntLow);
        return bigIntHigh.GetText$quorum_integer(10);
        
        /* Only supported in Chrome. 
        
        if(this.IsBigEndian()){
            var high = BigInt(view.getInt32(0, !(this.IsBigEndian())));
            var low = BigInt(view.getUint32(4, !(this.IsBigEndian())));
        } else {
            var low = BigInt(view.getUint32(0, !(this.IsBigEndian())));
            var high = BigInt(view.getInt32(4, !(this.IsBigEndian())));
        }
        high = high << BigInt(32);
        var combined = high + low;
        return combined;
        
        
        */
    };
    
    this.GetSignedBuffer$quorum_array = function(arr){
        var buffer = new ArrayBuffer(8);
        var view = new DataView(buffer);
        for (var i = 0; i < arr.length; i++){
            view.setInt8(i, arr[i]);
        }
        if(arr.length === 2){
            return view.getInt16(0, !(this.IsBigEndian()));
        }else if(arr.length === 4){
            return view.getInt32(0, !(this.IsBigEndian()));
        }       
    };
    
    this.GetUnsignedBuffer$quorum_array = function(arr){
        var buffer = new ArrayBuffer(8);
        var view = new DataView(buffer);
        for (var i = 0; i < arr.length; i++){
            view.setInt8(i, arr[i]);
        }
        if(arr.length === 2){
            return view.getUint16(0, !(this.IsBigEndian()));
        }else if(arr.length === 4){
            return view.getUint32(0, !(this.IsBigEndian()));
        }
    }; 
    
    this.GetFloatBuffer$quorum_array = function(arr){
        var buffer = new ArrayBuffer(8);
        var view = new DataView(buffer);
        for (var i = 0; i < arr.length; i++){
            view.setInt8(i, arr[i]);
        }
        if (arr.length === 4){
            return view.getFloat32(0, !(this.IsBigEndian()));
        } else if (arr.length === 8){
            return view.getFloat64(0, !(this.IsBigEndian())); 
        }
    };
}

