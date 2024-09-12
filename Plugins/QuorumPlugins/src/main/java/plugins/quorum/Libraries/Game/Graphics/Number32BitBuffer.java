package plugins.quorum.Libraries.Game.Graphics;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;

public class Number32BitBuffer {
    public java.lang.Object me_ = null;
    
    public ByteBuffer byteBuffer;
    public FloatBuffer buffer;
    
    public void SetSize(int size)
    {
        byteBuffer = BufferUtils.newUnsafeByteBuffer(size * 4);
        
        final int limit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.capacity());
        buffer = byteBuffer.asFloatBuffer();
        byteBuffer.limit(limit);
        buffer.limit(limit/4);
    }

    public int GetSize()
    {
        return buffer.limit();
    }

    public int GetMaxSize()
    {
        return byteBuffer.capacity() / 4;
    }

    public void Set(Number32BitArray_ array)
    {
        float[] floats = ((Number32BitArray)array).plugin_.floats;
        BufferUtils.copy(floats, buffer, floats.length, 0);
        buffer.position(0);
        buffer.limit(floats.length);
    }
    
    public void Set(int targetOffset, Number32BitArray_ array, int sourceOffset, int count)
    {
        float[] floats = ((Number32BitArray)array).plugin_.floats;
        int lastPosition = byteBuffer.position();
        byteBuffer.position(targetOffset * 4);
        BufferUtils.copy(floats, sourceOffset, count, byteBuffer);
        byteBuffer.position(lastPosition);
        buffer.position(0);
    }

    public void Dispose()
    {
        BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
    }
    
    public void ResetWritePosition()
    {
        buffer.clear();
    }

    public int GetWritePosition()
    {
        return buffer.position();
    }

    public void SetWritePosition(int newPosition)
    {
        buffer.position(newPosition);
    }
    
    public void SetWritePosition(int start, int end)
    {
        buffer.position(start);
        buffer.limit(end);
    }

    public void Write(Number32BitArray_ array)
    {
        float[] floats = ((Number32BitArray)array).plugin_.floats;
        buffer.put(floats);
    }
    
    public double Get(int index)
    {
        return buffer.get(index);
    }
}
