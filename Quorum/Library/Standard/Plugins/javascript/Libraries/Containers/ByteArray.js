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
    
    /*
    This action returns the byte, in integer form, in the array at the given location.

    Attribute: Parameter location The index of where you want to read the array from.

    Attribute: Returns Returns the byte, as an integer, at the specified location.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    */
    this.Get$quorum_integer = function (location){
        if(location >= 0 && location < this.byteArray.length){
            return this.byteArray[location];
        }
        else{
            throw "That is not a valid location";  
        }
    };
    
    /*
    This action sets the byte at the given location in the array to the value given.

    Attribute: Parameter location The index of where you want to read the array from.

    Attribute: Parameter value The value to set the byte at the location to.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Set(0, 10)
    */
    this.Set$quorum_integer$quorum_integer = function(location, value){
        if(this.byteArray === null || location > this.byteArray.length || location < 0) {
            throw "Out of Range";
        }
        this.byteArray[location] = value;
    };
    
    /*
        This action gets the size of the array.

        Attribute: Return The size of the array. 

        Attribute: Example
        use Libraries.Containers.ByteArray
        ByteArray myArray
        integer size = myArray:GetSize()
        output size
    */          
    this.GetSize = function(){
        return this.byteArray.length;
    };
    
    this.SetSize$quorum_integer = function(size) {
        this.byteArray = new Int8Array(size);   
    };
    
    /*
    This action creates a new ByteArray that is a sub-array of an existing ByteArray,
    starting at begin and ending at finish.

    Attribute: Parameter begin The index location to begin creating the new ByteArray.

    Attribute: Parameter finish The index location to finish creating the new ByteArray.

    Attribute: Returns Returns a ByteArray containing all elements from the original ByteArray
    between begin and finish, inclusive.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    ByteArray newValue = value:GetSubArray(0, 2)
    */   
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
    
    /*
    This action checks if the data file is in Big Endian format or not.

    Attribute: Returns Returns true if the file is in Big Endian and false if not.
    
    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    boolean endian = value:IsBigEndian()
    */   
    this.IsBigEndian = function(){
       return this.isBigEndian;    
    };

    /*
    This action sets the file to be in Big Endian format or not.

    Attribute: Parameter yes Sets the format to Big Endian if true, Little Endian if false.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    value:SetBigEndian(true)
    */
    this.SetBigEndian$quorum_boolean = function(yes){
       this.isBigEndian = yes;     
    };
   
    /*
    This action converts a byte into an unsigned integer.

    Attribute: Parameter byte The byte value to convert to an unsigned integer.

    Attribute: Returns Returns the byte value as an unsigned integer.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    byte = value:UnsignedIntegerFromByte(byte)
    */
    this.UnsignedIntegerFromByte$quorum_integer = function(byte1){
       var unsignedInt = byte1 & 0x00FF;  
       return unsignedInt;
    };

    /*
    This action converts a byte into a character.

    Attribute: Parameter byte The byte value to convert to a character.

    Attribute: Returns Returns the character as text.
    
    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    text letter = value:CharacterFromByte(byte)
    */
    this.CharacterFromByte$quorum_integer = function(byte2){
        var character = String.fromCharCode(byte2);
        return character;
    };
    
    /*
    This action converts two bytes into a character.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Returns Returns the character as text.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    text letter = value:CharacterFromTwoBytes(byte, byte2)
    */
    this.CharacterFromTwoBytes$quorum_integer$quorum_integer = function(byte1, byte2){
        var array = new Array(byte1, byte2);
        return String.fromCharCode(this.GetSignedBuffer$quorum_array(array));
    };

    /*
    This action converts a byte into an integer.

    Attribute: Parameter byte The byte value to convert to an integer.

    Attribute: Returns Returns the byte value as an integer.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    byte = value:IntegerFromByte(byte)
    */
    this.IntegerFromByte$quorum_integer = function(byte2){
        return byte2;
    };

    /*
    This action converts four bytes into a number.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Parameter byte3 The third byte value to use for conversion.

    Attribute: Parameter byte4 The fourth byte value to use for conversion.

    Attribute: Returns Returns the byte values as a number.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    integer byte3 = value:Get(2)
    integer byte4 = value:Get(3)
    number result = value:NumberFromFourBytes(byte, byte2, byte3, byte4)
    */
    this.NumberFromFourBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        return this.GetFloatBuffer$quorum_array(array);
    };

    /*
    This action converts eight bytes into a number.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Parameter byte3 The third byte value to use for conversion.

    Attribute: Parameter byte4 The fourth byte value to use for conversion.

    Attribute: Parameter byte5 The fifth byte value to use for conversion.

    Attribute: Parameter byte6 The sixth byte value to use for conversion.

    Attribute: Parameter byte7 The seventh byte value to use for conversion.

    Attribute: Parameter byte8 The eighth byte value to use for conversion.

    Attribute: Returns Returns the byte values as a number.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    integer byte3 = value:Get(2)
    integer byte4 = value:Get(3)
    integer byte5 = value:Get(4)
    integer byte6 = value:Get(5)
    integer byte7 = value:Get(6)
    integer byte8 = value:Get(7)
    number result = value:NumberFromEightBytes(byte, byte2, byte3, byte4, byte5, byte6, byte7, byte8)
    */
    this.NumberFromEightBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8){
        var array = new Array(byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8);
        return this.GetFloatBuffer$quorum_array(array);
    };

    /*
    This action converts two bytes into an integer. 

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Returns Returns the byte values as an integer.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    byte = value:IntegerFromTwoBytes(byte, byte2)
    */
    this.IntegerFromTwoBytes$quorum_integer$quorum_integer = function(byte1, byte2){
        var array = new Array(byte1, byte2);
        return this.GetSignedBuffer$quorum_array(array);       
    };
    
    /*
    This action converts two bytes into an unsigned integer.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Returns Returns the byte values as an unsigned integer.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    byte = value:UnsignedIntegerFromTwoBytes(byte, byte2)
    */    
    this.UnsignedIntegerFromTwoBytes$quorum_integer$quorum_integer = function(byte1, byte2){
       var array = new Array(byte1, byte2);
       return this.GetUnsignedBuffer$quorum_array(array);
    };
   
    /*
    This action converts four bytes into an integer.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Parameter byte3 The third byte value to use for conversion.

    Attribute: Parameter byte4 The fourth byte value to use for conversion.

    Attribute: Returns Returns the byte values as an integer.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    integer byte3 = value:Get(2)
    integer byte4 = value:Get(3)
    byte = value:IntegerFromFourBytes(byte, byte2, byte3, byte4)
    */   
    this.IntegerFromFourBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        return this.GetSignedBuffer$quorum_array(array);
    };
    

    /*
    This action converts four unsigned bytes into a signed integer.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Parameter byte3 The third byte value to use for conversion.

    Attribute: Parameter byte4 The fourth byte value to use for conversion.

    Attribute: Returns Returns the byte values as a signed integer.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    integer byte3 = value:Get(2)
    integer byte4 = value:Get(3)
    byte = value:SignedIntegerFromFourUnsignedBytes(byte, byte2, byte3, byte4)
    */
    this.SignedIntegerFromFourUnsignedBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(byte1, byte2, byte3, byte4){
        var array = new Array(byte1, byte2, byte3, byte4);
        var buffer = new ArrayBuffer(8);
        var view = new DataView(buffer);
        for (var i = 0; i < array.length; i++){
            view.setUint8(i, array[i]);
        }

        return view.getInt32(0, !(this.IsBigEndian()));   
    };
    
    /*
    This action converts eight bytes into text as a sequence of numbers.

    Attribute: Parameter byte1 The first byte value to use for conversion.

    Attribute: Parameter byte2 The second byte value to use for conversion.

    Attribute: Parameter byte3 The third byte value to use for conversion.

    Attribute: Parameter byte4 The fourth byte value to use for conversion.

    Attribute: Parameter byte5 The fifth byte value to use for conversion.

    Attribute: Parameter byte6 The sixth byte value to use for conversion.

    Attribute: Parameter byte7 The seventh byte value to use for conversion.

    Attribute: Parameter byte8 The eighth byte value to use for conversion.

    Attribute: Returns Returns the byte values as text.

    Attribute: Example
    use Libraries.System.File
    use Libraries.Containers.ByteArray

    File file
    file:SetPath("files/Quorum.png")
    ByteArray value = file:ReadBytes()
    integer byte = value:Get(0)
    integer byte2 = value:Get(1)
    integer byte3 = value:Get(2)
    integer byte4 = value:Get(3)
    integer byte5 = value:Get(4)
    integer byte6 = value:Get(5)
    integer byte7 = value:Get(6)
    integer byte8 = value:Get(7)
    text result = value:TextFromEightBytes(byte, byte2, byte3, byte4, byte5, byte6, byte7, byte8)
    */
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
    
    this.TextFromFourBytes$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function (byte1, byte2, byte3, byte4) {
        var array = new Array(byte1, byte2, byte3, byte4);
        return this.GetUnsignedBuffer$quorum_array(array).toString();
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

