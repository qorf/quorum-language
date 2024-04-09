/* global plugins_quorum_Libraries_Game_GameStateManager_ */

function plugins_quorum_Libraries_Game_Graphics_Mesh_() 
{
    this.quorumMesh = null;
    this.calcVector = new quorum_Libraries_Compute_Vector3_();
    
    this.SetQuorumReference$quorum_Libraries_Game_Graphics_Mesh = function(mesh) 
    {
        this.quorumMesh = mesh;
    };
    
    /* The shader parameter must be a valid ShaderProgram, and the primitiveType
     * parameter must be one of the following constant values accessible from
     * the gl property in WebGraphics:
     *      POINTS: Draws a single dot at each vertex.
     *      LINE_STRIP: Draws a straight line to the next vertex.
     *      LINE_LOOP: Draws a straight line to the next vertex, and connects the last vertex back to the first.
     *      LINES: Draws a line between a pair of vertices.
     *      TRIANGLE_STRIP: Draws a triangle between the first three vertices. Each successive vertex forms a triangle with the last two vertices used.
     *      TRIANGLE_FAN: Draws a triangle between the first three vertices. Each successive vertex forms a triangle with the first vertex and the last vertex used.
     *      TRIANGLES: Draws a triangle for a group of three vertices.
     *      
     * If the offset, count, or autoBind values are undefined, default values
     * will be provided using the contents of the Quorum Mesh object.
     * 
     * @param {ShaderProgram} shader
     * @param {integer} primitiveType
     * @param {integer} offset
     * @param {integer} count
     * @param {boolean} autoBind
     * @returns {undefined}
     */
    this.Render = function(shader, primitiveType, offset, count, autoBind)
    {
        if (offset === undefined)
            offset = 0;
        
        if (count === undefined)
        {
            if (this.quorumMesh.indices.GetSize() > 0)
                count = this.quorumMesh.indices.GetSize();
            else
                count = this.quorumMesh.vertices.GetSize();
        }
        
        if (count === 0)
            return;
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        
        if (autoBind === undefined)
            autoBind = this.quorumMesh.autoBind;
        
        if (autoBind)
            this.Bind(shader);
        
        if (this.quorumMesh.indices.GetSize() > 0)
        {
            if (this.quorumMesh.indices.plugin_.supports32bits)
                graphics.glDrawElements(primitiveType, count, graphics.gl.UNSIGNED_INT, offset * 4);
            else
                graphics.glDrawElements(primitiveType, count, graphics.gl.UNSIGNED_SHORT, offset * 2);
        }
        else
        {
            graphics.glDrawArrays(primitiveType, offset, count);
        }
      
        if (autoBind)
            this.Unbind(shader);
    };
    
    this.Bind = function(shader, locations)
    {
        this.quorumMesh.vertices.plugin_.Bind(shader, locations);
        if (this.quorumMesh.indices.GetSize() > 0)
            this.quorumMesh.indices.plugin_.Bind();
    };
    
    this.Unbind = function(shader, locations)
    {
        this.quorumMesh.vertices.plugin_.Unbind(shader, locations);
        if (this.quorumMesh.indices.GetSize() > 0)
            this.quorumMesh.indices.plugin_.Unbind();
    };
    
    this.CalculateBoundingBox$quorum_Libraries_Game_BoundingBox = function(box) 
    {
        box.Invalidate();
        
        var numVertices = this.quorumMesh.GetVerticesCount();
        if (numVertices === 0)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("There were no vertices defined for this Mesh!");
            throw exceptionInstance_;
        }
        
        var verts = this.quorumMesh.vertices.plugin_.GetBuffer();
        
        var posAttrib = this.quorumMesh.GetVertexAttributes().FindByUsage$quorum_integer(this.quorumMesh.GetVertexAttributes().POSITION);
        var offset = posAttrib.offset / 4;
        var vertexSize = this.quorumMesh.vertices.GetAttributes().vertexSize;
        var index = offset;
        
        switch (posAttrib.componentCount)
        {
            case 1:
                for (var i = 0; i < numVertices; i++)
                {
                    box.Extend$quorum_number$quorum_number$quorum_number(verts[index], 0, 0);
                    index = index + vertexSize;
                }
                break;
                
            case 2:
                for (var i = 0; i < numVertices; i++)
                {
                    box.Extend$quorum_number$quorum_number$quorum_number(verts[index], verts[index + 1], 0);
                    index = index + vertexSize;
                }
                break;
                
            case 3:
                for (var i = 0; i < numVertices; i++)
                {
                    box.Extend$quorum_number$quorum_number$quorum_number(verts[index], verts[index + 1], verts[index + 2]);
                    index = index + vertexSize;
                }
                break;
        }
        return box;
    };
    
    this.ExtendBoundingBox$quorum_Libraries_Game_BoundingBox$quorum_integer$quorum_integer$quorum_Libraries_Compute_Matrix4 = function(box, offset, count, transform) 
    {
        var numIndices = this.quorumMesh.GetIndicesCount();
        
        if (offset < 0 || count < 1 || offset + count > numIndices)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Invalid parameter(s) to ExtendBoundingBox - offset = " + offset + ", count = " + count + ", max = " + numIndices);
            throw exceptionInstance_;
        }
        
        var vertices = this.quorumMesh.vertices.plugin_.GetBuffer();
        var indices = this.quorumMesh.indices.plugin_.GetBuffer();
        
        var posAttrib = this.quorumMesh.GetVertexAttributes().FindByUsage$quorum_integer(this.quorumMesh.GetVertexAttributes().POSITION);
        
        var posOffset = posAttrib.offset / 4;
        var vertexSize = this.quorumMesh.vertices.GetAttributes().vertexSize / 4;
        var end = offset + count;
        
        switch (posAttrib.componentCount)
        {
            case 1:
                for (var i = offset; i < end; i++)
                {
                    var idx = indices[i] * vertexSize + posOffset;
                    this.calcVector.Set$quorum_number$quorum_number$quorum_number(vertices[idx], 0, 0);
                    if (transform !== null && transform !== undefined)
                        this.calcVector.Multiply$quorum_Libraries_Compute_Matrix4(transform);
                    
                    box.Extend$quorum_Libraries_Compute_Vector3(this.calcVector);
                }
                break;
                
            case 2:
                for (var i = offset; i < end; i++)
                {
                    var idx = indices[i] * vertexSize + posOffset;
                    this.calcVector.Set$quorum_number$quorum_number$quorum_number(vertices[idx], vertices[idx + 1], 0);
                    if (transform !== null && transform !== undefined)
                        this.calcVector.Multiply$quorum_Libraries_Compute_Matrix4(transform);
                    
                    box.Extend$quorum_Libraries_Compute_Vector3(this.calcVector);
                }
                break;
                
            case 3:
                for (var i = offset; i < end; i++)
                {
                    var idx = indices[i] * vertexSize + posOffset;
                    this.calcVector.Set$quorum_number$quorum_number$quorum_number(vertices[idx], vertices[idx + 1], vertices[idx + 2]);
                    if (transform !== null && transform !== undefined)
                        this.calcVector.Multiply$quorum_Libraries_Compute_Matrix4(transform);
                    
                    box.Extend$quorum_Libraries_Compute_Vector3(this.calcVector);
                }
                break;
        }
    };
    
    this.AddReloadableMesh = function()
    {
        // Do nothing, this isn't necessary on the web.
    };
    
    this.RemoveReloadableMesh = function()
    {
        // Do nothing, this isn't necessary on the web.
    };
}
