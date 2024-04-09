function plugins_quorum_Libraries_Game_Graphics_IndexBufferObject_() 
{
    var buffer;
    var integerArray;
    var bridgeArray = [];
    var length = 0;
    var maxLength;
    var writingPosition = 0;
    
    var isDirty = true;
    var isBound = false;
    var usage;
    
    this.supports32bits;

    this.Load$quorum_boolean$quorum_integer = function(isStatic, maxIndices) 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        
        this.supports32bits = graphics.gl.getExtension("OES_element_index_uint");
        
        maxLength = maxIndices;
        writingPosition = 0;
        
        buffer = graphics.glGenBuffer();
        
        if (isStatic)
            usage = graphics.gl.STATIC_DRAW;
        else
            usage = graphics.gl.DYNAMIC_DRAW;
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
        isDirty = true;
        
        if (length !== count)
        {
            this.SetLength(count);
            integerArray = this.NewIntegerArray(count);
        }
        
        for (var i = 0; i < count; i++)
            integerArray[i] = bridgeArray[offset + i];
        
        writingPosition = 0;
        
        if (isBound)
        {
            var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
            graphics.glBufferData(graphics.gl.ELEMENT_ARRAY_BUFFER, integerArray, usage);
            isDirty = false;
        }
    };
    
    this.Bind = function() 
    {
        if (buffer === undefined || buffer === null)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Attempted to bind before a buffer was allocated!");
            throw exceptionInstance_;  
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        
        graphics.glBindBuffer(graphics.gl.ELEMENT_ARRAY_BUFFER, buffer);
        
        if (isDirty)
        {
            graphics.glBufferData(graphics.gl.ELEMENT_ARRAY_BUFFER, integerArray, usage);
            isDirty = false;
        }
        isBound = true;
    };
    
    this.Unbind = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        graphics.glBindBuffer(graphics.gl.ELEMENT_ARRAY_BUFFER, null);
        isBound = false;
    };
    
    this.Invalidate = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        buffer = graphics.glGenBuffer();
        isDirty = true;
    };
    
    this.Clear = function() 
    {
        integerArray = null;
        length = 0;
        writingPosition = 0;
    };
    
    this.Reload = function()
    {
        // Do nothing. There's (currently) nothing to be done here.
    };
    
    this.Dispose = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        graphics.glBindBuffer(graphics.gl.ELEMENT_ARRAY_BUFFER, null);
        graphics.glDeleteBuffer(buffer);
        buffer = null;
        
        integerArray = null;
        bridgeArray = [];
        length = 0;
        maxLength = 0;
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
    
    this.SendToBridgeArray$quorum_Libraries_Containers_Integer32BitArray = function(int32array)
    {
        bridgeArray = int32array.plugin_.array_;
    };
    
    this.PutBridgeArray = function() 
    {
        isDirty = true;
        
        if (length < writingPosition + bridgeArray.length)
        {
            this.SetLength(writingPosition + bridgeArray.length);
            integerArray = this.NewIntegerArray(writingPosition + bridgeArray.length);
        }
        
        for (var i = 0; i < bridgeArray.length; i++)
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
    
    this.GetBuffer = function()
    {
        isDirty = true;
        return integerArray;
    };
}

/*
    private system action SendToBridgeArray(Integer32BitArray array)
 */