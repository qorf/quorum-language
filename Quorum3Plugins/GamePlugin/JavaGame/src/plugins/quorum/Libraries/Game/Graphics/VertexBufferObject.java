/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.GameState;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;
import plugins.quorum.Libraries.Game.libGDX.ShaderProgram;

import quorum.Libraries.Game.Graphics.VertexAttribute_;
import quorum.Libraries.Game.Graphics.VertexAttributes;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 *
 * @author alleew
 */
public class VertexBufferObject 
{
    private VertexAttributes attributes;
    private FloatBuffer buffer;
    private ByteBuffer byteBuffer;
    private int bufferHandle;
    private int usage;
    private boolean ownsBuffer;
    boolean isDirty = false;
    boolean isBound = false;
    
    // Acts as a "bridge" for communicating Arrays between Quorum and the plugin.
    private float[] bridgeArray = null;
    
    public void Load(boolean isStatic, int numVertices, VertexAttributes attributes)
    {
        bufferHandle = GameState.nativeGraphics.glGenBuffer();
        
        ByteBuffer data = BufferUtils.newUnsafeByteBuffer(attributes.vertexSize * numVertices);
        data.limit(0);
        SetBuffer(data, true, attributes);
        SetUsage(isStatic ? GraphicsManager.GL_STATIC_DRAW : GraphicsManager.GL_DYNAMIC_DRAW);
    }
    
    protected void SetBuffer(Buffer data, boolean ownsBuffer, VertexAttributes value)
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
    
    public VertexAttributes GetAttributes()
    {
        return attributes;
    }
    
    public int GetSize()
    {
        return buffer.limit() * 4 / attributes.vertexSize;
    }
    
    public int GetMaxSize()
    {
        return byteBuffer.capacity() / attributes.vertexSize;
    }
    
    public void Bind(ShaderProgram shader, int[] locations) 
    {
        final GraphicsManager gl = GameState.nativeGraphics;

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
                final int location = shader.getAttributeLocation(attribute.Get_Libraries_Game_Graphics_VertexAttribute__alias_());
		
                if (location < 0)
                    continue;
		
                shader.enableVertexAttribute(location);

                shader.setVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                    attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(), attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                    attributes.vertexSize, attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
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
		
                shader.enableVertexAttribute(location);

                shader.setVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                    attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(), attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                    attributes.vertexSize, attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
            }
        }
		
        isBound = true;
    }
    
    public void PrepareBridgeArray(int length)
    {
        bridgeArray = new float[length];
    }
    
    public void SendToBridgeArray(int index, double value)
    {
        bridgeArray[index] = (float)value;
    }
    
    public void PopulateVertexBuffer()
    {
        BufferUtils.copy(bridgeArray, buffer, bridgeArray.length, 0);
    }
}
