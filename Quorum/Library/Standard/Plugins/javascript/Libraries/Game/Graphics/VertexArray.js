/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Graphics_VertexArray_() 
{
    var buffer;
    var floatArray;
    var bridgeArray = [];
    var length = 0;
    var maxLength = 0;
    var isDirty = false;
    
    this.Load$quorum_integer$quorum_Libraries_Game_Graphics_VertexAttributes = function(numVertices, attributes) 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        this.attributes = attributes;
        buffer = graphics.glGenBuffer();
        maxLength = (attributes.vertexSize * numVertices) / 4;
    };
    
    this.Dispose = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glBindBuffer(graphics.gl.ARRAY_BUFFER, null);
        graphics.glDeleteBuffer(buffer);
        buffer = null;
        
        floatArray = null;
        bridgeArray = [];
        length = 0;
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
        this.attributes;
    };
    
    this.SetVerticesNative$quorum_integer$quorum_integer = function(offset, count) 
    {
        this.SetVertices(bridgeArray, offset, count);
    };
    
    this.SetVertices = function(array, offset, count)
    {
        isDirty = true;
        
        if (length !== count)
        {
            this.SetLength(count);
            floatArray = new Float32Array(count);
        }
        
        for (var i = 0; i < count; i++)
        {
            floatArray[i] = array[offset + i];
        }
    };
    
    this.UpdateVerticesNative$quorum_integer$quorum_integer$quorum_integer = function(targetOffset, sourceOffset, count) 
    {
        this.UpdateVertices(targetOffset, bridgeArray, sourceOffset, count);
    };
    
    this.UpdateVertices = function(targetOffset, vertices, sourceOffset, count)
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
        
        for (var i = targetOffset; i < count + targetOffset; i++)
        {
            floatArray[i] = vertices[sourceOffset + i];
        }
    };
    
    this.Bind = function(shader, locations)
    {
        var numAttributes = this.attributes.GetSize();
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        graphics.glBindBuffer(graphics.gl.ARRAY_BUFFER, buffer);
        
        if (isDirty)
        {
            graphics.glBufferData(graphics.gl.ARRAY_BUFFER, floatArray, graphics.gl.STATIC_DRAW);
            isDirty = false;
        }
        
        if (locations === undefined || locations === null)
        {
            for (var i = 0; i < numAttributes; i++)
            {
                var attribute = this.attributes.GetAttribute$quorum_integer(i);
                var location = shader.GetAttributeLocation(attribute.alias);
                if (location < 0)
                    continue;
                
                shader.EnableVertexAttributeAtLocation(location);
                
                graphics.glBindBuffer(graphics.gl.ARRAY_BUFFER, buffer);
                shader.SetVertexAttributeAtLocation(location, attribute.componentCount, 
                    attribute.type, attribute.normalized, this.attributes.vertexSize, attribute.offset);
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
                
                shader.SetVertexAttributeAtLocation(location, attribute.componentCount, 
                    attribute.type, attribute.normalized, this.attributes.vertexSize, attribute.offset);
            }
        }
    };
    
    this.Unbind = function(shader, locations)
    {
        var numAttributes = this.attributes.GetSize();
        if (locations === undefined)
        {
            for (var i = 0; i < numAttributes; i++)
            {
                shader.DisableVertexAttributeFromName(this.attributes.GetAttribute$quorum_integer(i).alias);
            }
        }
        else
        {
            for (var i = 0; i < numAttributes; i++)
            {
                if (i >= 0)
                    shader.DisableVertexAttributeAtLocation(i);
            }
        }
    };
        
    this.PopulateVertexBuffer = function() 
    {
        isDirty = true;
        for (var i = 0; i < length; i++)
            floatArray[i] = bridgeArray[i];
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
    
    this.SetLength = function(newLength)
    {
        if (newLength > maxLength)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("The VertexArray was asked to set its length to " + newLength + ", but the maximum length is " + maxLength);
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
