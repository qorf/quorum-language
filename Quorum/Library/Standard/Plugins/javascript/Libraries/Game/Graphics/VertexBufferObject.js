/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Graphics_VertexBufferObject_() 
{
    var buffer;
    var floatArray;
    var bridgeArray = [];
    var length = 0;
    var maxLength = 0;
    var isDirty = false;
    var usage;
    var isBound = false;
    
    this.Load$quorum_boolean$quorum_integer$quorum_Libraries_Game_Graphics_VertexAttributes = function(isStatic, verticesCount, attributes) 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        
        buffer = graphics.glGenBuffer();
        maxLength = (attributes.vertexSize * verticesCount) / 4;
        this.attributes = attributes;
        
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
    
    this.GetAttributes = function() 
    {
        return this.attributes;
    };
    
    this.Reload = function()
    {
        // Do nothing. There's (currently) nothing to be done here.
    };
    
    this.SetVerticesNative$quorum_integer$quorum_integer = function(offset, count) 
    {
        isDirty = true;
        
        if (count !== length)
        {
            this.SetLength(count);
            floatArray = new Float32Array(count);
        }
        
        for (var i = 0; i < count; i++)
            floatArray[i] = bridgeArray[offset + i];
        
        this.BufferChanged();
    };
    
    this.UpdateVerticesNative$quorum_integer$quorum_integer$quorum_integer = function(targetOffset, sourceOffset, count) 
    {
        isDirty = true;
        
        var limit = targetOffset + count;
        if (length < limit)
        {
            this.SetLength(limit);
            
            var tempArray = new Float32Array(limit);
            
            for (var i = 0; i < targetOffset; i++)
                tempArray[i] = floatArray[i];
            
            floatArray = tempArray;
        }
        
        for (var i = 0; i < count; i++)
            floatArray[targetOffset + i] = bridgeArray[sourceOffset + i];
        
        this.BufferChanged();
    };
    
    this.Bind = function(shader, locations)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        
        graphics.glBindBuffer(graphics.gl.ARRAY_BUFFER, buffer);
        
        if (isDirty)
        {
            graphics.glBufferData(graphics.gl.ARRAY_BUFFER, floatArray, usage);
            isDirty = false;
        }
        
        var numAttributes = this.attributes.GetSize();
        
        if (locations === undefined || locations === null)
        {
            for (var i = 0; i < numAttributes; i++)
            {
                var attribute = this.attributes.GetAttribute(i);
                var location = shader.GetAttributeLocation(attribute.alias);
                
                if (location < 0)
                    continue;
                
                shader.EnableVertexAttributeAtLocation(location);
                
                shader.SetVertexAttributeAtLocation(location, attribute.componentCount, attribute.type, 
                    attribute.normalized, this.attributes.vertexSize, attribute.offset);
            }
        }
        else
        {
            for (var i = 0; i < numAttributes; i++)
            {
                var attribute = this.attributes.GetAttribute$quorum_integer(i);
                var location = locations[i];
                
                if (location < 0)
                    continue;
                
                shader.EnableVertexAttributeAtLocation(location);
                
                shader.SetVertexAttributeAtLocation(location, attribute.componentCount, attribute.type, 
                    attribute.normalized, this.attributes.vertexSize, attribute.offset);
            }
        }
        
        isBound = true;
    };
    
    this.Unbind = function(shader, locations)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        var numAttributes = this.attributes.GetSize();
        
        if (locations === undefined || locations === null)
        {
            for (var i = 0; i < numAttributes; i++)
            {
                shader.DisableVertexAttributeFromName(this.attributes.GetAttribute(i).alias);
            }
        }
        else
        {
            for (var i = 0; i < numAttributes; i++)
            {
                var location = locations[i];
                if (location >= 0)
                    shader.DisableVertexAttributeAtLocation(location);
            }
        }
        
        graphics.glBindBuffer(graphics.ARRAY_BUFFER, null);
        isBound = false;
    };
    
    this.Invalidate = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        buffer = graphics.glGenBuffer();
    };
    
    this.Dispose = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        graphics.glBindBuffer(graphics.gl.GL_ARRAY_BUFFER, null);
        graphics.glDeleteBuffer(buffer);
        buffer = null;
        length = 0;
        bridgeArray = [];
    };
    
    this.PrepareBridgeArray$quorum_integer = function(arrayLength) 
    {
        if (length !== arrayLength)
        {
            this.SetLength(arrayLength);
            floatArray = new Float32Array(arrayLength);
        }
    };
    
    this.SendToBridgeArray$quorum_integer$quorum_number = function(index, value) 
    {
        bridgeArray[index] = value;
    };
    
    this.SendToBridgeArray$quorum_Libraries_Containers_Number32BitArray = function(number32array)
    {
        bridgeArray = number32array.plugin_.array_;
        
        this.PrepareBridgeArray$quorum_integer(bridgeArray.length);
    };
    
    this.PopulateVertexBuffer = function() 
    {
        isDirty = true;
        for (var i = 0; i < length; i++)
            floatArray[i] = bridgeArray[i];
    };
    
    this.BufferChanged = function()
    {
        if (isBound)
        {
            var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
            graphics.glBufferData(graphics.gl.ARRAY_BUFFER, floatArray, usage);
            isDirty = false;
        }
    };
    
    this.SetLength = function(newLength)
    {
        if (newLength > maxLength)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("The VertexBufferObject was asked to set its length to " + newLength + ", but the maximum length is " + maxLength);
            throw exceptionInstance_;  
        }
        
        length = newLength;
    };
    
    this.GetBuffer = function()
    {
        isDirty = true;
        return floatArray;
    };
}

/*
    private system action SetVerticesNative(integer offset, integer count)

    private system action UpdateVerticesNative(integer targetOffset, integer sourceOffset, integer count)

    private system action PrepareBridgeArray(integer length)

    private system action SendToBridgeArray(Number32BitArray array)

    private system action SendToBridgeArray(integer index, number value)

    private system action PopulateVertexBuffer
 */
