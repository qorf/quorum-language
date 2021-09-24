/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.Graphics.VertexAttribute_;
import quorum.Libraries.Game.Graphics.VertexAttributes_;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;

/**
 *
 * @author alleew
 */
public class VertexBufferObject extends VertexData
{
    private VertexAttributes_ attributes;
    private FloatBuffer buffer;
    private ByteBuffer byteBuffer;
    private int bufferHandle;
    private int usage;
    private boolean ownsBuffer;
    boolean isDirty = false;
    boolean isBound = false;
    
    // Acts as a "bridge" for communicating Arrays between Quorum and the plugin.
    private float[] bridgeArray = null;
    
    public void Load(boolean isStatic, int numVertices, VertexAttributes_ attributes)
    {
        bufferHandle = GameStateManager.nativeGraphics.glGenBuffer();
        
        ByteBuffer data = BufferUtils.newUnsafeByteBuffer(attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_() * numVertices);
        data.limit(0);
        SetBuffer(data, true, attributes);
        SetUsage(isStatic ? GraphicsManager.GL_STATIC_DRAW : GraphicsManager.GL_DYNAMIC_DRAW);
    }
    
    protected void SetBuffer(Buffer data, boolean ownsBuffer, VertexAttributes_ value)
    {
        if (isBound)
            throw new GameRuntimeError("Cannot change attributes of a VertexBufferObject while it is bound.");
        
        if (this.ownsBuffer && byteBuffer != null)
            BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
        
        attributes = value;
        
        if (data instanceof ByteBuffer)
            byteBuffer = (ByteBuffer)data;
        else
            throw new GameRuntimeError("Only ByteBuffer is currently supported.");
        this.ownsBuffer = ownsBuffer;
        
        final int limit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.capacity());
        buffer = byteBuffer.asFloatBuffer();
        byteBuffer.limit(limit);
        buffer.limit(limit/4);
    }
    
    protected void SetUsage(int value)
    {
        if (isBound)
            throw new GameRuntimeError("Cannot change the usage of a VertexBufferObject while it is bound.");
        usage = value;
    }
    
    public FloatBuffer GetBuffer()
    {
        isDirty = true;
        return buffer;
    }
    
    @Override
    public VertexAttributes_ GetAttributes()
    {
        return attributes;
    }
    
    @Override
    public int GetSize()
    {
        return buffer.limit() * 4 / attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_();
    }
    
    @Override
    public int GetMaxSize()
    {
        return byteBuffer.capacity() / attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_();
    }
    
    public void SetVerticesNative(int offset, int count)
    {
        isDirty = true;
        BufferUtils.copy(bridgeArray, byteBuffer, count, offset);
        buffer.position(0);
        buffer.limit(count);
        BufferChanged();
    }
    
    public void UpdateVerticesNative(int targetOffset, int sourceOffset, int count)
    {
        isDirty = true;
        final int pos = byteBuffer.position();
        byteBuffer.position(targetOffset * 4);
        BufferUtils.copy(bridgeArray, sourceOffset, count, byteBuffer);
        byteBuffer.position(pos);
        buffer.position(0);
        BufferChanged();
    }
    
    @Override
    public void Bind(ShaderProgram shader)
    {
        Bind(shader, null);
    }
    
    @Override
    public void Bind(ShaderProgram shader, int[] locations) 
    {
        final GraphicsManager gl = GameStateManager.nativeGraphics;

        gl.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, bufferHandle);
        
        if (isDirty) 
        {   
            byteBuffer.limit(buffer.limit() * 4);
            gl.glBufferData(GraphicsManager.GL_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }

        final int numAttributes = attributes.GetSize();
		
        if (locations == null) 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                final VertexAttribute_ attribute = attributes.GetAttribute(i);
                final int location = shader.GetAttributeLocation(attribute.Get_Libraries_Game_Graphics_VertexAttribute__alias_());
		
                if (location < 0)
                    continue;
		
                shader.EnableVertexAttribute(location);

                shader.SetVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                    attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(), attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                    attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(), attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
            }

        }
        else
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                final VertexAttribute_ attribute = attributes.GetAttribute(i);
                final int location = locations[i];
                
                if (location < 0) 
                    continue;
	
                shader.EnableVertexAttribute(location);

                shader.SetVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                    attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(), attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                    attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(), attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
            }
        }
		
        isBound = true;
    }
    
    @Override
    public void Unbind(final ShaderProgram shader) 
    {
        Unbind(shader, null);
    }

    @Override
    public void Unbind(final ShaderProgram shader, final int[] locations) 
    {
        final GraphicsManager gl = GameStateManager.nativeGraphics;
        final int numAttributes = attributes.GetSize();
        if (locations == null) 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                shader.DisableVertexAttribute(attributes.GetAttribute(i).Get_Libraries_Game_Graphics_VertexAttribute__alias_());
            }
        }
        else 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                final int location = locations[i];
                if (location >= 0)
                    shader.DisableVertexAttribute(location);
            }
        }
        
        gl.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, 0);
        isBound = false;
    }
    
    @Override
    public void Reload()
    {
        bufferHandle = GameStateManager.nativeGraphics.glGenBuffer();
        isDirty = true;
    }
    
    @Override
    public void Dispose()
    {
        GraphicsManager gl = GameStateManager.nativeGraphics;
        gl.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, 0);
        gl.glDeleteBuffer(bufferHandle);
        bufferHandle = 0;
        if (ownsBuffer)
            BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
    }

    public void PrepareBridgeArray(int length)
    {
        bridgeArray = new float[length];
    }
    
    public void SendToBridgeArray(Number32BitArray_ vertices)
    {
        Number32BitArray array = (Number32BitArray) vertices;
        plugins.quorum.Libraries.Containers.Number32BitArray plugin = array.plugin_;
        bridgeArray = plugin.floats;
    }
    
    public void SendToBridgeArray(int index, double value)
    {
        bridgeArray[index] = (float)value;
    }
    
    public void PopulateVertexBuffer()
    {
        isDirty = true;
        BufferUtils.copy(bridgeArray, buffer, bridgeArray.length, 0);
    }

    private void BufferChanged() 
    {
        if (isBound) 
        {
            GameStateManager.nativeGraphics.glBufferData(GraphicsManager.GL_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
    }
}