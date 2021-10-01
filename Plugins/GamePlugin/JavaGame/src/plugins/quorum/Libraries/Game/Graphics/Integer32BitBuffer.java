package plugins.quorum.Libraries.Game.Graphics;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import quorum.Libraries.Containers.Integer32BitArray;
import quorum.Libraries.Containers.Integer32BitArray_;

public class Integer32BitBuffer {
    public java.lang.Object me = null;
    
    ByteBuffer byteBuffer;
    IntBuffer buffer;
    
    public void SetSize(int size)
    {
        byteBuffer = BufferUtils.newUnsafeByteBuffer(size * 4);
        
        final int limit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.capacity());
        buffer = byteBuffer.asIntBuffer();
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

    public void Set(Integer32BitArray_ array)
    {
        int[] ints = ((Integer32BitArray)array).plugin_.ints;
        BufferUtils.copy(ints, 0, buffer, ints.length);
        buffer.position(0);
        buffer.limit(ints.length);
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

    public void Write(Integer32BitArray_ array)
    {
        int[] ints = ((Integer32BitArray)array).plugin_.ints;
        buffer.put(ints);
    }
}
