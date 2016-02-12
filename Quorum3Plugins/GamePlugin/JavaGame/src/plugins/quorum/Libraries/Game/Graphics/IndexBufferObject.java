/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.nio.ByteBuffer;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.GameState;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;

/**
 *
 * @author alleew
 */
public class IndexBufferObject extends IndexData
{
    ShortBuffer buffer;
    ByteBuffer byteBuffer;
    int bufferHandle;
    boolean isDirect;
    boolean isDirty = true;
    boolean isBound = false;
    int usage;
    
    private boolean empty;
    private short[] bridgeArray = null;
    
    public void Load(boolean isStatic, int maxIndices)
    {
        empty = maxIndices == 0;
        if (empty)
            maxIndices = 1; // Avoid allocating a zero-sized buffer due to a bug in Android's ART < Android 5.0
        
        byteBuffer = BufferUtils.newUnsafeByteBuffer(maxIndices * 2);
        isDirect = true;
        
        buffer = byteBuffer.asShortBuffer();
        buffer.flip();
        byteBuffer.flip();
        bufferHandle = GameState.nativeGraphics.glGenBuffer();
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
            GameState.nativeGraphics.glBufferData(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
    }

    public void SetIndices (ShortBuffer indices) 
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
            GameState.nativeGraphics.glBufferData(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
    }
    
    public ShortBuffer GetBuffer()
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

        GraphicsManager gl = GameState.nativeGraphics;
        
        gl.glBindBuffer(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, bufferHandle);
        
        if (isDirty) 
        {
            byteBuffer.limit(buffer.limit() * 2);
            gl.glBufferData(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, usage);
            isDirty = false;
        }
        isBound = true;
    }

    /** Unbinds this IndexBufferObject. */
    public void Unbind() 
    {
        GameState.nativeGraphics.glBindBuffer(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, 0);
        isBound = false;
    }

    /** Invalidates the IndexBufferObject so a new OpenGL buffer handle is created. Use this in case of a context loss. */
    public void Invalidate() 
    {
        bufferHandle = GameState.nativeGraphics.glGenBuffer();
        isDirty = true;
    }

    /** Disposes this IndexBufferObject and all its associated OpenGL resources. */
    public void Dispose()
    {
        GameState.nativeGraphics.glBindBuffer(GraphicsManager.GL_ELEMENT_ARRAY_BUFFER, 0);
        GameState.nativeGraphics.glDeleteBuffer(bufferHandle);
        bufferHandle = 0;

        BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
    }
    
    public void PrepareBridgeArray(int length)
    {
        bridgeArray = new short[length];
    }
    
    public void SendToBridgeArray(int index, int value)
    {
        bridgeArray[index] = (short)value;
    }
    
    public void PutBridgeArray()
    {
        buffer.put(bridgeArray);
    }
    
    public void SetPosition(int position)
    {
        buffer.position(position);
    }
    
}
