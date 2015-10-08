/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import java.nio.ByteBuffer;
import plugins.quorum.Libraries.Game.GameState;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;

/**
 *
 * @author alleew
 */
public class IndexBufferObject 
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
    
    public ShortBuffer GetBuffer()
    {
        isDirty = true;
        return buffer;
    }
    
    public void Clear()
    {
        buffer.clear();
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
