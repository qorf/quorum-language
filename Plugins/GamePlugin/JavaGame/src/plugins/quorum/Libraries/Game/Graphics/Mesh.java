/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.libGDX.Array;
//import plugins.quorum.Libraries.Game.libGDX.ShaderProgram;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Compute.Vector3;

import quorum.Libraries.Game.Graphics.Mesh_;
import quorum.Libraries.Game.BoundingBox_;
import quorum.Libraries.Game.Graphics.VertexAttribute_;
import quorum.Libraries.Game.Graphics.Shaders.ShaderProgram_;

/**
 *
 * @author alleew
 */
public class Mesh 
{
    public java.lang.Object me_ = null;
    private quorum.Libraries.Game.Graphics.Mesh quorumMesh = null;
    
    // Static vector used during calculations.
    private static Vector3 calcVector = new Vector3();
    
    final static Array<Mesh_> RELOADABLE_MESHES = new Array<>();
    
    /*
    An action used by the Quorum mesh class during creation. This will ensure
    the plugin has a reference to its corresponding class.
    */
    public void SetQuorumReference(Mesh_ mesh)
    {
        quorumMesh = (quorum.Libraries.Game.Graphics.Mesh)mesh;
    }
    
    public void Render(ShaderProgram shader, int primitiveType) 
    {
        Render(shader, primitiveType, 0, quorumMesh.indices.GetMaxSize() > 0 ? quorumMesh.indices.GetSize() : quorumMesh.vertices.GetSize(), 
            quorumMesh.autoBind);
    }
    
    public void Render(ShaderProgram shader, int primitiveType, int offset, int count)
    {
        Render(shader, primitiveType, offset, count, quorumMesh.autoBind);
    }
    
    public void Render(ShaderProgram shader, int primitiveType, int offset, int count, boolean autoBind) 
    {
        if (count == 0) 
            return;

        if (autoBind) 
            Bind(shader);

        if (quorumMesh.isVertexArray) 
        {
            if (quorumMesh.indices.GetSize() > 0) 
            {
                IntBuffer buffer = ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.GetBuffer();
                int oldPosition = buffer.position();
                int oldLimit = buffer.limit();
                buffer.position(offset);
                buffer.limit(offset + count);
                GameStateManager.nativeGraphics.glDrawElements(primitiveType, count, GraphicsManager.GL_UNSIGNED_INT, buffer);
                buffer.position(oldPosition);
                buffer.limit(oldLimit);
            } 
            else
            {
                GameStateManager.nativeGraphics.glDrawArrays(primitiveType, offset, count);
            }
        }
        else
        {
            if (quorumMesh.indices.GetSize() > 0)
            {
                GameStateManager.nativeGraphics.glDrawElements(primitiveType, count, GraphicsManager.GL_UNSIGNED_INT, offset * 4);
            }
            else
            {
                GameStateManager.nativeGraphics.glDrawArrays(primitiveType, offset, count);
            }
        }

        if (autoBind)
            Unbind(shader);
    }
    
    public void Bind(final ShaderProgram shader)
    {
        Bind(shader, null);
    }
    
    public void Bind(final ShaderProgram shader, final int[] locations)
    {
        if (quorumMesh.isVertexArray)
        {
            ((quorum.Libraries.Game.Graphics.VertexArray)quorumMesh.vertices).plugin_.Bind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.Bind();
        }
        else
        {
            ((quorum.Libraries.Game.Graphics.VertexBufferObject)quorumMesh.vertices).plugin_.Bind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexBufferObject)quorumMesh.indices).plugin_.Bind();
        }
    }
    
    public void Unbind(final ShaderProgram shader)
    {
        Unbind(shader, null);
    }
    
    public void Unbind(final ShaderProgram shader, final int[] locations)
    {
        if (quorumMesh.isVertexArray)
        {
            ((quorum.Libraries.Game.Graphics.VertexArray)quorumMesh.vertices).plugin_.Unbind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.Unbind();
        }
        else
        {
            ((quorum.Libraries.Game.Graphics.VertexBufferObject)quorumMesh.vertices).plugin_.Unbind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexBufferObject)quorumMesh.indices).plugin_.Unbind();
        }
    }
    
    public void Render(ShaderProgram_ shader, int primitiveType, int offset, int count)
    {
        Render(shader, primitiveType, offset, count, quorumMesh.autoBind);
    }
    
    public void Render(ShaderProgram_ shader, int primitiveType, int offset, int count, boolean autoBind) 
    {
        if (count == 0) 
            return;

        if (autoBind) 
            Bind(shader);

        if (quorumMesh.isVertexArray) 
        {
            if (quorumMesh.indices.GetSize() > 0) 
            {
                IntBuffer buffer = ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.GetBuffer();
                int oldPosition = buffer.position();
                int oldLimit = buffer.limit();
                buffer.position(offset);
                buffer.limit(offset + count);
                GameStateManager.nativeGraphics.glDrawElements(primitiveType, count, GraphicsManager.GL_UNSIGNED_INT, buffer);
                buffer.position(oldPosition);
                buffer.limit(oldLimit);
            } 
            else
            {
                GameStateManager.nativeGraphics.glDrawArrays(primitiveType, offset, count);
            }
        }
        else
        {
            if (quorumMesh.indices.GetSize() > 0)
            {
                GameStateManager.nativeGraphics.glDrawElements(primitiveType, count, GraphicsManager.GL_UNSIGNED_INT, offset * 4);
            }
            else
            {
                GameStateManager.nativeGraphics.glDrawArrays(primitiveType, offset, count);
            }
        }

        if (autoBind)
            Unbind(shader);
    }
    
    public void Bind(final ShaderProgram_ shader)
    {
        Bind(shader, null);
    }
    
    public void Bind(final ShaderProgram_ shader, final int[] locations)
    {
        if (quorumMesh.isVertexArray)
        {
            ((quorum.Libraries.Game.Graphics.VertexArray)quorumMesh.vertices).plugin_.Bind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.Bind();
        }
        else
        {
            ((quorum.Libraries.Game.Graphics.VertexBufferObject)quorumMesh.vertices).plugin_.Bind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexBufferObject)quorumMesh.indices).plugin_.Bind();
        }
    }
    
    public void Unbind(final ShaderProgram_ shader)
    {
        Unbind(shader, null);
    }
    
    public void Unbind(final ShaderProgram_ shader, final int[] locations)
    {
        if (quorumMesh.isVertexArray)
        {
            ((quorum.Libraries.Game.Graphics.VertexArray)quorumMesh.vertices).plugin_.Unbind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.Unbind();
        }
        else
        {
            ((quorum.Libraries.Game.Graphics.VertexBufferObject)quorumMesh.vertices).plugin_.Unbind(shader, locations);
            if (quorumMesh.indices.GetSize() > 0)
                ((quorum.Libraries.Game.Graphics.IndexBufferObject)quorumMesh.indices).plugin_.Unbind();
        }
    }
    
    public BoundingBox_ CalculateBoundingBox(BoundingBox_ box)
    {
        box.Invalidate();
        
        int numVertices = quorumMesh.GetVerticesCount();
        if (numVertices == 0)
            throw new GameRuntimeError("There were no vertices defined for this Mesh!");
        
        final FloatBuffer verts;
        
        if (quorumMesh.isVertexArray)
            verts = ((quorum.Libraries.Game.Graphics.VertexArray)quorumMesh.GetVertexData()).plugin_.GetBuffer();
        else
            verts = ((quorum.Libraries.Game.Graphics.VertexBufferObject)quorumMesh.GetVertexData()).plugin_.GetBuffer();
        
        VertexAttribute_ posAttrib = quorumMesh.GetVertexAttributes().FindByUsage(quorumMesh.GetVertexAttributes().Get_Libraries_Game_Graphics_VertexAttributes__POSITION_());
        final int offset = posAttrib.Get_Libraries_Game_Graphics_VertexAttribute__offset_() / 4;
        final int vertexSize = quorumMesh.vertices.GetAttributes().Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_();
        int index = offset;
        
        switch (posAttrib.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_())
        {
            case 1:
                for (int i = 0; i < numVertices; i++)
                {
                    box.Extend(verts.get(index), 0, 0);
                    index += vertexSize;
                }
                break;
                
            case 2:
                for (int i = 0; i < numVertices; i++)
                {
                    box.Extend(verts.get(index), verts.get(index + 1), 0);
                    index += vertexSize;
                }
                break;
                
            case 3:
                for (int i = 0; i < numVertices; i++)
                {
                    box.Extend(verts.get(index), verts.get(index + 1), verts.get(index + 2));
                    index += vertexSize;
                }
                break;
        }
        
        return box;
    }
    
    public BoundingBox_ ExtendBoundingBox(BoundingBox_ box, int offset, int count, Matrix4_ transform)
    {
        int numIndices = quorumMesh.GetIndicesCount();
        
        if (offset < 0 || count < 1 || offset + count > numIndices)
            throw new GameRuntimeError("Invalid parameter(s) to ExtendBoundingBox - offset = " + offset + ", count = " + count + ", max = " + numIndices);

        final FloatBuffer verts;
        final IntBuffer index;
        if (quorumMesh.isVertexArray)
        {
            verts = ((quorum.Libraries.Game.Graphics.VertexArray)quorumMesh.vertices).plugin_.GetBuffer();
            index = ((quorum.Libraries.Game.Graphics.IndexArray)quorumMesh.indices).plugin_.GetBuffer();
        }
        else
        {
            verts = ((quorum.Libraries.Game.Graphics.VertexBufferObject)quorumMesh.vertices).plugin_.GetBuffer();
            index = ((quorum.Libraries.Game.Graphics.IndexBufferObject)quorumMesh.indices).plugin_.GetBuffer();
        }
        
        final VertexAttribute_ posAttrib = quorumMesh.GetVertexAttributes().FindByUsage(quorumMesh.GetVertexAttributes().Get_Libraries_Game_Graphics_VertexAttributes__POSITION_());
        
        final int posOffset = posAttrib.Get_Libraries_Game_Graphics_VertexAttribute__offset_() / 4;
        final int vertexSize = quorumMesh.vertices.GetAttributes().Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_() / 4;
        final int end = offset + count;
        
        switch (posAttrib.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_())
        {
            case 1:
                for (int i = offset; i < end; i++)
                {
                    final int idx = index.get(i) * vertexSize + posOffset;
                    calcVector.Set(verts.get(idx), 0, 0);
                    if (transform != null)
                        calcVector.Multiply(transform);
                    
                    box.Extend(calcVector);
                }
                break;
                
            case 2:
                for (int i = offset; i < end; i++)
                {
                    final int idx = index.get(i) * vertexSize + posOffset;
                    calcVector.Set(verts.get(idx), verts.get(idx + 1), 0);
                    if (transform != null)
                        calcVector.Multiply(transform);
                    box.Extend(calcVector);
                }
                break;
                
            case 3: 
                for (int i = offset; i < end; i++)
                {
                    final int idx = index.get(i) * vertexSize + posOffset;
                    try
                    {
                        calcVector.Set(verts.get(idx), verts.get(idx + 1), verts.get(idx + 2));
                        if (transform != null)
                            calcVector.Multiply(transform);
                        box.Extend(calcVector);
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Attempted to access up to " + (idx + 2) + ", verts size was " + verts.capacity());
                        System.out.println("i = " + i + ", index.get(" + i + ") = " + index.get(i));
                        throw ex;
                    }
                }
            
                break;
        }
        
        return box;
    }
    
    public void AddReloadableMesh()
    {
        RELOADABLE_MESHES.add(quorumMesh);
    }
    
    public void RemoveReloadableMesh()
    {
        RELOADABLE_MESHES.removeValue(quorumMesh, true);
    }
    
    public static void ReloadMeshes()
    {
        for (Mesh_ mesh : RELOADABLE_MESHES)
        {
            mesh.Reload();
        }
    }
}
