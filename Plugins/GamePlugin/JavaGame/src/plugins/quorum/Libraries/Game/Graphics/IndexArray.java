/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

// Dependencies to remove:
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import quorum.Libraries.Containers.Integer32BitArray;
import quorum.Libraries.Containers.Integer32BitArray_;

public class IndexArray extends IndexData 
{
    private int[] bridgeArray = null;
    
    IntBuffer buffer;
    ByteBuffer byteBuffer;

    /** Creates a new IndexArray to be used with vertex arrays.
     * 
     * @param maxIndices the maximum number of indices this buffer can hold */
    public void Load(int maxIndices) 
    {
        byteBuffer = BufferUtils.newUnsafeByteBuffer(maxIndices * 4);
        buffer = byteBuffer.asIntBuffer();
        buffer.flip();
        byteBuffer.flip();
    }
    
    @Override
    public void SetIndexRange(int start, int end)
    {
        buffer.position(start);
        buffer.limit(end);
    }

    /** @return the number of indices currently stored in this buffer */
    @Override
    public int GetSize() 
    {
        return buffer.limit();
    }

    /** @return the maximum number of indices this IndexArray can store. */
    @Override
    public int GetMaxSize() 
    {
        return buffer.capacity();
    }

    /** <p>
     * Sets the indices of this IndexArray, discarding the old indices. The count must equal the number of indices to be copied to
     * this IndexArray.
     * </p>
     * 
     * <p>
     * This can be called in between calls to {@link #bind()} and {@link #unbind()}. The index data will be updated instantly.
     * </p>
     * 
     * @param indices the vertex data
     * @param offset the offset to start copying the data from
     * @param count the number of shorts to copy */
    public void SetIndices(int[] indices, int offset, int count) 
    {
        buffer.clear();
        buffer.put(indices, offset, count);
        buffer.flip();
        byteBuffer.position(0);
        byteBuffer.limit(count << 2);
    }
    
    public void SetIndices(int offset, int count)
    {
        SetIndices(bridgeArray, offset, count);
    }
	
    public void SetIndices(IntBuffer indices) 
    {
        int pos = indices.position();
        buffer.clear();
        buffer.limit(indices.remaining());
        buffer.put(indices);
        buffer.flip();
        indices.position(pos);
        byteBuffer.position(0);
        byteBuffer.limit(buffer.limit() << 2);
    }

    /** <p>
     * Returns the underlying ShortBuffer. If you modify the buffer contents they wil be uploaded on the call to {@link #bind()}.
     * If you need immediate uploading use {@link #setIndices(short[], int, int)}.
     * </p>
     * 
     * @return the underlying short buffer. */
    public IntBuffer GetBuffer() 
    {
        return buffer;
    }

    /** Binds this IndexArray for rendering with glDrawElements. */
    @Override
    public void Bind () 
    {
        // Do nothing.
    }

    /** Unbinds this IndexArray. */
    @Override
    public void Unbind() 
    {
        // Do nothing.
    }

    /** Reloads the IndexData. Used in case of a context loss, IndexArray 
        doesn't have any state that needs to be manually recovered after context
        loss (unlike buffer objects), so this does nothing. */
    @Override
    public void Reload() 
    {
        // Do nothing. The IndexArray doesn't require manual reloading.
    }

    public void Clear()
    {
        buffer.clear();
    }
    
    /** Disposes this IndexArray and all its associated OpenGL resources. */
    @Override
    public void Dispose () 
    {
        BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
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
}
