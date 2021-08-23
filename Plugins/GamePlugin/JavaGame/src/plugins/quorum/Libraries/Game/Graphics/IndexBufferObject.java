/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import quorum.Libraries.Containers.Integer32BitArray;
import quorum.Libraries.Containers.Integer32BitArray_;

/**
 *
 * @author alleew
 */
public class IndexBufferObject extends IndexData
{
    IntBuffer buffer;
    ByteBuffer byteBuffer;
    int bufferHandle;
    boolean isDirty = true;
    boolean isBound = false;
    int usage;
    
    private boolean empty;
    private int[] bridgeArray = null;
    
    public void Load(boolean isStatic, int maxIndices)
    {
        empty = maxIndices == 0;
        if (empty)
            maxIndices = 1; // Avoid allocating a zero-sized buffer due to a bug in Android's ART < Android 5.0
        
        byteBuffer = BufferUtils.newUnsafeByteBuffer(maxIndices * 4);
        
        buffer = byteBuffer.asIntBuffer();
        buffer.flip();
        byteBuffer.flip();
        bufferHandle = GameStateManager.nativeGraphics.glGenBuffer();
        usage = isStatic ? GraphicsManager.GL_STATIC_DRAW : GraphicsManager.GL_DYNAMIC_DRAW;
    }
    
    /** @return the number of indices currently stored in this buffer */
    public int GetSize()
    {
        return empty ? 0 : buffer.limit();
    }

    /** @return the maximum number of indices this IndexBufferObject can store. */
    public int GetMaxSize() 
    {
        return empty ? 0 : buffer.capacity();
    }

    @Override
    public void SetIndexRange(int start, int end)
    {
        buffer.position(start);
        buffer.limit(end);
    }
	
    public void SetIndices(int offset, int count) 
    {
        isDirty = true;
        buffer.clear();
        buffer.put(bridgeArray, offset, count);
        buffer.flip();
        byteBuffer.position(0);
        byteBuffer.limit(count << 1);

        if (isBound) 
        {
            GameStateManager.nativeGraphics.glBufferData(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
    }

    public void SetIndices (IntBuffer indices) 
    {
        isDirty = true;
        int pos = indices.position();
        buffer.clear();
        buffer.put(indices);
        buffer.flip();
        indices.position(pos);
        byteBuffer.position(0);
        byteBuffer.limit(buffer.limit() << 1);

        if (isBound) {
            GameStateManager.nativeGraphics.glBufferData(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
    }
    
    public IntBuffer GetBuffer()
    {
        isDirty = true;
        return buffer;
    }
    
    public void Clear()
    {
        buffer.clear();
    }
    
    /** Binds this IndexBufferObject for rendering with glDrawElements. */
    public void Bind()
    {
        if (bufferHandle == 0)
            throw new GameRuntimeError("Attempted to bind before a buffer was allocated!");

        GraphicsManager gl = GameStateManager.nativeGraphics;
        
        gl.glBindBuffer(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, bufferHandle);
        
        if (isDirty) 
        {
            byteBuffer.limit(buffer.limit() * 4);
            gl.glBufferData(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
        isBound = true;
    }

    /** Unbinds this IndexBufferObject. */
    public void Unbind() 
    {
        GameStateManager.nativeGraphics.glBindBuffer(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, 0);
        isBound = false;
    }

    /** Invalidates the IndexBufferObject so a new OpenGL buffer handle is created. Use this in case of a context loss. */
    public void Invalidate() 
    {
        bufferHandle = GameStateManager.nativeGraphics.glGenBuffer();
        isDirty = true;
    }

    /** Disposes this IndexBufferObject and all its associated OpenGL resources. */
    public void Dispose()
    {
        GameStateManager.nativeGraphics.glBindBuffer(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, 0);
        GameStateManager.nativeGraphics.glDeleteBuffer(bufferHandle);
        bufferHandle = 0;

        BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
    }
    
    public void PrepareBridgeArray(int length)
    {
        bridgeArray = new int[length];
    }
    
    public void SendToBridgeArray(int index, int value)
    {
        bridgeArray[index] = value;
    }
    
    public void SendToBridgeArray(Integer32BitArray_ vertices)
    {
        Integer32BitArray array = (Integer32BitArray) vertices;
        plugins.quorum.Libraries.Containers.Integer32BitArray plugin = array.plugin_;
        bridgeArray = plugin.ints;
    }
    
    public void PutBridgeArray()
    {
        buffer.put(bridgeArray);
    }
    
    public void SetPosition(int position)
    {
        buffer.position(position);
    }
    
    @Override
    public void Reload()
    {
        bufferHandle = GameStateManager.nativeGraphics.glGenBuffer();
        isDirty = true;
    }
}
