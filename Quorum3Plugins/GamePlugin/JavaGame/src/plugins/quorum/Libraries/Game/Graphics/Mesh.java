/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.ShortBuffer;
import plugins.quorum.Libraries.Game.GameState;
import plugins.quorum.Libraries.Game.libGDX.ShaderProgram;

import quorum.Libraries.Game.Graphics.Mesh_;

/**
 *
 * @author alleew
 */
public class Mesh 
{
    java.lang.Object me_ = null;
    quorum.Libraries.Game.Graphics.Mesh quorumMesh = null;
    
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
                ShortBuffer buffer = ((quorum.Libraries.Game.Graphics.IndexData)quorumMesh.indices).plugin_.GetBuffer();
                int oldPosition = buffer.position();
                int oldLimit = buffer.limit();
                buffer.position(offset);
                buffer.limit(offset + count);
                GameState.nativeGraphics.glDrawElements(primitiveType, count, GraphicsManager.GL_UNSIGNED_SHORT, buffer);
                buffer.position(oldPosition);
                buffer.limit(oldLimit);
            } 
            else
            {
                GameState.nativeGraphics.glDrawArrays(primitiveType, offset, count);
            }
        }
        else
        {
            if (quorumMesh.indices.GetSize() > 0)
                GameState.nativeGraphics.glDrawElements(primitiveType, count, GraphicsManager.GL_UNSIGNED_SHORT, offset * 2);
            else
                GameState.nativeGraphics.glDrawArrays(primitiveType, offset, count);
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
        ((quorum.Libraries.Game.Graphics.VertexData)quorumMesh.vertices).plugin_.Bind(shader, locations);
        if (quorumMesh.indices.GetSize() > 0)
            ((quorum.Libraries.Game.Graphics.IndexData)quorumMesh.indices).plugin_.Bind();
    }
    
    public void Unbind(final ShaderProgram shader)
    {
        Unbind(shader, null);
    }
    
    public void Unbind(final ShaderProgram shader, final int[] locations)
    {
        ((quorum.Libraries.Game.Graphics.VertexData)quorumMesh.vertices).plugin_.Unbind(shader, locations);
        if (quorumMesh.indices.GetSize() > 0)
            ((quorum.Libraries.Game.Graphics.IndexData)quorumMesh.indices).plugin_.Unbind();
    }
}
