/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Graphics_VertexArray_() 
{
    var buffer;
    var bridgeArray = [];
    var length = 0;
    
    this.Load$quorum_integer$quorum_Libraries_Game_Graphics_VertexAttributes = function(numVertices, attributes) 
    {
        this.attributes = attributes;
        buffer = new Float32Array((attributes.vertexSize * numVertices)/4);
    };
    
    this.Dispose = function() 
    {
        buffer = null;
        bridgeArray = [];
        length = 0;
    };
    
    this.GetSize = function() 
    {
        return length;
    };
    
    this.GetMaxSize = function() 
    {
        return buffer.length;
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
        for (i = 0; i < count; i++)
        {
            buffer[i] = array[offset + i];
        }
        
        this.length = count;
    };
    
    this.UpdateVerticesNative$quorum_integer$quorum_integer$quorum_integer = function(targetOffset, sourceOffset, count) 
    {
        this.UpdateVertices(targetOffset, this.bridgeArray, sourceOffset, count);
    };
    
    this.UpdateVertices = function(targetOffset, vertices, sourceOffset, count)
    {
        for (i = targetOffset; i < count + targetOffset; i++)
        {
            buffer[i] = vertices[sourceOffset + i];
        }
        
        var lastIndex = targetOffset + count;
        if (lastIndex > this.length)
            this.length = lastIndex;
    };
    
    this.Bind = function(shader, locations)
    {
        var numAttributes = this.attributes.GetSize();
        
        if (locations === undefined || locations === null)
        {
            var GL_FLOAT = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics.gl.FLOAT;
            
            for (i = 0; i < numAttributes; i++)
            {
                var attribute = attributes.GetAttribute(i);
                var location = shader.GetAttributeLocationFromName(attribute.alias);
                if (location < 0)
                    continue;
                
                shader.EnableVertexAttributeAtLocation(location);
                
                if (attribute.type == GL_FLOAT)
                {
                    
                }
            }
        }
    };
    
    this.Unbind = function(shader, locations)
    {
        var numAttributes = attributes.GetSize();
        if (locations === undefined)
        {
            for (i = 0; i < numAttributes; i++)
            {
                shader.DisableVertexAttributeFromName(attributes.GetAttribute(i).alias);
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
        for (i = 0; i < this.length; i++)
            buffer[i] = bridgeArray[i];
    };
    
    this.PrepareBridgeArray$quorum_integer = function(length) 
    {
        this.length = length;
    };
    
    this.SendToBridgeArray$quorum_integer$quorum_number = function(index, value) 
    {
        bridgeArray[index] = value;
    };
}
