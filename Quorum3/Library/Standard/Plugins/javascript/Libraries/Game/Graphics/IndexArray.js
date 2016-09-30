function plugins_quorum_Libraries_Game_Graphics_IndexArray_() 
{
    var bridgeArray = [];
    var integerArray;
    var length = 0;
    var maxLength = 0;
    var writingPosition = 0;
    
    this.supports32bits;
    
    this.Load$quorum_integer = function(maximumSize) 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        this.supports32bits = graphics.gl.getExtension("OES_element_index_uint");
        
        maxLength = maximumSize;
    };
    
    this.GetSize = function() 
    {
        return length;
    };
    
    this.GetMaxSize = function() 
    {
        return maxLength;
    };
    
    this.SetIndices$quorum_integer$quorum_integer = function(offset, count) 
    {
        this.SetIndices(bridgeArray, offset, count);
    };
    
    this.SetIndices = function(array, offset, count)
    {
        if (length !== count)
        {
            this.SetLength(count);
            integerArray = this.NewIntegerArray(count);
        }
        
        for (i = 0; i < count; i++)
            integerArray[i] = array[offset + i];
        
        writingPosition = 0;
    };
    
    this.GetBuffer = function()
    {
        return integerArray;
    };
    
    this.Bind = function() 
    {
        // Do nothing.
    };
    
    this.Unbind = function() 
    {
        // Do nothing.
    };
    
    this.Invalidate = function() 
    {
        // Do nothing.
    };
    
    this.Clear = function() 
    {
        length = 0;
        writingPosition = 0;
        integerArray = null;
    };
    
    this.Dispose = function() 
    {
        integerArray = null;
        bridgeArray = [];
        length = 0;
        writingPosition = 0;
    };
    
    this.PrepareBridgeArray$quorum_integer = function(newLength) 
    {
        if (length !== newLength)
        {
            this.SetLength(newLength);
            integerArray = this.NewIntegerArray(newLength);
        }
    };
    
    this.SendToBridgeArray$quorum_integer$quorum_integer = function(index, value) 
    {
        bridgeArray[index] = value;
    };
    
    this.PutBridgeArray = function() 
    {
        if (length < writingPosition + bridgeArray.length)
        {
            this.SetLength(writingPosition + bridgeArray.length);
            integerArray = this.NewIntegerArray(writingPosition + bridgeArray.length);
        }
        
        for (i = 0; i < bridgeArray.length; i++)
            integerArray[writingPosition + i] = bridgeArray[i];
        
        writingPosition = writingPosition + bridgeArray.length;
    };
    
    this.SetPosition$quorum_integer = function(position) 
    {
        writingPosition = position;
    };
    
    this.NewIntegerArray = function(size)
    {
        if (this.supports32bits)
            return new Uint32Array(size);
        else
            return new Uint16Array(size);
    };
    
    this.SetLength = function(newLength)
    {
        if (newLength > maxLength)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("The IndexArray was asked to set its length to " + newLength + ", but the maximum length is " + maxLength);
            throw exceptionInstance_;  
        }
        
        length = newLength;
    };
}
