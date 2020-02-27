/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Graphics_IndexArray_() 
{
    var buffer;
    var integerArray = null;
    var bridgeArray = null;
    var length = 0;
    var maxLength;
    var position = 0;
    
    var isDirty = true;
    var isBound = false;
    
    this.supports32bits;
    
    this.Load$quorum_integer = function(maximumSize) 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        this.supports32bits = graphics.gl.getExtension("OES_element_index_uint");
        
        maxLength = maximumSize;
        position = 0;
        
        buffer = graphics.glGenBuffer();
        
        if (this.supports32bits)
            integerArray = new Int32Array(maximumSize);
        else
            integerArray = new Int16Array(maximumSize);
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
        isDirty = true;
        
        if (length !== count)
        {
            this.SetLength(count);
        }
        
        for (var i = 0; i < count; i++)
            integerArray[i] = array[offset + i];
        
        position = 0;
        
        if (isBound)
        {
            var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
            graphics.glBufferData(graphics.gl.ELEMENT_ARRAY_BUFFER, this.GetBuffer(), graphics.gl.STATIC_DRAW);
            isDirty = false;
        }
    };
    
    this.GetBuffer = function()
    {
        return integerArray.subarray(position, length - position);
    };
    
    this.Bind = function() 
    {
        if (buffer === undefined || buffer === null)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Attempted to bind before a buffer was allocated!");
            throw exceptionInstance_;
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        graphics.glBindBuffer(graphics.gl.ELEMENT_ARRAY_BUFFER, buffer);
        
        if (isDirty)
        {
            graphics.glBufferData(graphics.gl.ELEMENT_ARRAY_BUFFER, this.GetBuffer(), graphics.gl.STATIC_DRAW);
            isDirty = false;
        }
        isBound = true;
    };
    
    this.Unbind = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glBindBuffer(graphics.gl.ELEMENT_ARRAY_BUFFER, null);
        isBound = false;
    };
    
    this.Invalidate = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        buffer = graphics.glGenBuffer();
        isDirty = true;
    };
    
    this.Clear = function() 
    {
        if (this.supports32bits)
            integerArray = new Int32Array(maxLength);
        else
            integerArray = new Int16Array(maxLength);
        
        length = 0;
        position = 0;
    };
    
    this.Dispose = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glBindBuffer(graphics.gl.ELEMENT_ARRAY_BUFFER, null);
        graphics.glDeleteBuffer(buffer);
        buffer = null;
        
        integerArray = null;
        bridgeArray = null;
        length = 0;
        maxLength = 0;
        position = 0;
    };
    
    this.SendToBridgeArray$quorum_Libraries_Containers_Integer32BitArray = function(int32array)
    {
        bridgeArray = int32array.plugin_.array_;
    };
    
    this.PutBridgeArray = function() 
    {
        isDirty = true;
        
        if (length < position + bridgeArray.length)
        {
            this.SetLength(position + bridgeArray.length);
        }
        
        for (var i = 0; i < bridgeArray.length; i++)
            integerArray[position + i] = bridgeArray[i];
        
        position = position + bridgeArray.length;
    };
    
    this.SetPosition$quorum_integer = function(newPosition) 
    {
        position = newPosition;
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
        isDirty = true;
    };
}