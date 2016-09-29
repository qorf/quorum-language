/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Graphics_VertexArray_() 
{
    var buffer;
    var floatArray;
    var bridgeArray = [];
    var length = 0;
    var isDirty = false;
    
    this.Load$quorum_integer$quorum_Libraries_Game_Graphics_VertexAttributes = function(numVertices, attributes) 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        this.attributes = attributes;
        buffer = graphics.glGenBuffer();
        floatArray = new Float32Array((attributes.vertexSize * numVertices)/4);
    };
    
    this.Dispose = function() 
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glBindBuffer(graphics.gl.ARRAY_BUFFER, 0);
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
        return floatArray.length;
    };
    
    this.GetAttributes = function() 
    {
        this.attributes;
    };
    
    this.SetVerticesNative$quorum_integer$quorum_integer = function(offset, count) 
    {
        this.SetVertices(this.bridgeArray, offset, count);
    };
    
    this.SetVertices = function(array, offset, count)
    {
        isDirty = true;
        for (i = 0; i < count; i++)
        {
            floatArray[i] = array[offset + i];
        }
        
        length = count;
    };
    
    this.UpdateVerticesNative$quorum_integer$quorum_integer$quorum_integer = function(targetOffset, sourceOffset, count) 
    {
        this.UpdateVertices(targetOffset, this.bridgeArray, sourceOffset, count);
    };
    
    this.UpdateVertices = function(targetOffset, vertices, sourceOffset, count)
    {
        isDirty = true;
        for (i = targetOffset; i < count + targetOffset; i++)
        {
            floatArray[i] = vertices[sourceOffset + i];
        }
        
        var lastIndex = targetOffset + count;
        if (lastIndex > length)
            length = lastIndex;
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
            for (i = 0; i < numAttributes; i++)
            {
                var attribute = this.attributes.GetAttribute(i);
                var location = shader.GetAttributeLocationFromName(attribute.alias);
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
            for (i = 0; i < numAttributes; i++)
            {
                var attribute = this.attributes.GetAttribute(i);
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
            for (i = 0; i < numAttributes; i++)
            {
                shader.DisableVertexAttributeFromName(this.attributes.GetAttribute(i).alias);
            }
        }
        else
        {
            for (i = 0; i < numAttributes; i++)
            {
                if (i >= 0)
                    shader.DisableVertexAttributeAtLocation(i);
            }
        }
    };
        
    this.PopulateVertexBuffer = function() 
    {
        isDirty = true;
        for (i = 0; i < length; i++)
            floatArray[i] = bridgeArray[i];
    };
    
    this.PrepareBridgeArray$quorum_integer = function(arrayLength) 
    {
        length = arrayLength;
    };
    
    this.SendToBridgeArray$quorum_integer$quorum_number = function(index, value) 
    {
        bridgeArray[index] = value;
    };
}
