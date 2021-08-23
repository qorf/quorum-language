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

//import plugins.quorum.Libraries.Game.libGDX.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;

import quorum.Libraries.Game.Graphics.VertexAttributes_;
import quorum.Libraries.Game.Graphics.VertexAttribute_;

// Dependencies to remove:
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Game.Graphics.Shaders.ShaderProgram_;


/** <p>
 * Convenience class for working with OpenGL vertex arrays. It interleaves all data in the order you specified in the constructor
 * via {@link VertexAttribute}.
 * </p>
 * 
 * <p>
 * This class does not support shaders and for that matter OpenGL ES 2.0. For this {@link VertexBufferObject}s are needed.
 * </p>
 * 
 * @author mzechner, Dave Clayton <contact@redskyforge.com> */
public class VertexArray extends VertexData 
{
    private VertexAttributes_ attributes;
    private FloatBuffer buffer;
    private ByteBuffer byteBuffer;
    boolean isBound = false;
    
    float[] bridgeArray = null;

    /** Constructs a new interleaved VertexArray
     * 
     * @param numVertices the maximum number of vertices
     * @param attributes the {@link VertexAttributes} */
    public void Load(int numVertices, VertexAttributes_ attributes) 
    {
        this.attributes = attributes;
        byteBuffer = BufferUtils.newUnsafeByteBuffer(this.attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_() * numVertices);
        buffer = byteBuffer.asFloatBuffer();
        buffer.flip();
        byteBuffer.flip();
    }

    @Override
    public void Dispose () 
    {
        BufferUtils.disposeUnsafeByteBuffer(byteBuffer);
    }

    @Override
    public FloatBuffer GetBuffer() 
    {
        return buffer;
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

    public void SetVertices(float[] vertices, int offset, int count) 
    {
        BufferUtils.copy(vertices, byteBuffer, count, offset);
        buffer.position(0);
        buffer.limit(count);
    }
    
    public void SetVerticesNative(int offset, int count)
    {
        SetVertices(bridgeArray, offset, count);
    }

    public void UpdateVertices (int targetOffset, float[] vertices, int sourceOffset, int count) 
    {
        final int pos = byteBuffer.position();
        byteBuffer.position(targetOffset * 4);
        BufferUtils.copy(vertices, sourceOffset, count, byteBuffer);
        byteBuffer.position(pos);
    }
    
    public void UpdateVerticesNative(int targetOffset, int sourceOffset, int count)
    {
        UpdateVertices(targetOffset, bridgeArray, sourceOffset, count);
    }

    @Override
    public void Bind(final ShaderProgram shader) 
    {
        Bind(shader, null);
    }

    @Override
    public void Bind(final ShaderProgram shader, final int[] locations) 
    {
        final GraphicsManager gl = GameStateManager.nativeGraphics;
        final int numAttributes = attributes.GetSize();
        byteBuffer.limit(buffer.limit() * 4);
        if (locations == null) 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                final VertexAttribute_ attribute = attributes.GetAttribute(i);
                final int location = shader.GetAttributeLocation(attribute.Get_Libraries_Game_Graphics_VertexAttribute__alias_());
                if (location < 0)
                    continue;

                shader.EnableVertexAttribute(location);

                if (attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_() == GraphicsManager.GL_FLOAT) 
                {
                    buffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_() / 4);
                    shader.SetVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(),
                        buffer);
                }
                else
                {
                    byteBuffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
                    shader.SetVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(),
                        byteBuffer);
                }
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

                if (attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_() == GraphicsManager.GL_FLOAT) 
                {
                    buffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_() / 4);
                    shader.SetVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(), buffer);
                }
                else 
                {
                    byteBuffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
                    shader.SetVertexAttribute(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(), 
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(),
                        byteBuffer);
                }
            }
        }
        isBound = true;
    }

    /** Unbinds this VertexBufferObject.
     * 
     * @param shader the shader */
    @Override
    public void Unbind(ShaderProgram shader) 
    {
        Unbind(shader, null);
    }

    @Override
    public void Unbind(ShaderProgram shader, int[] locations) 
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
        isBound = false;
    }

    /** Binds this VertexData for rendering via glDrawArrays or glDrawElements. */
    public void Bind(ShaderProgram_ shader)
    {
        Bind(shader, null);
    }

    /** Binds this VertexData for rendering via glDrawArrays or glDrawElements.
     * @param locations array containing the attribute locations. */
    public void Bind(ShaderProgram_ shader, int[] locations)
    {
        final GraphicsManager gl = GameStateManager.nativeGraphics;
        final int numAttributes = attributes.GetSize();
        byteBuffer.limit(buffer.limit() * 4);
        if (locations == null) 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                final VertexAttribute_ attribute = attributes.GetAttribute(i);
                final int location = shader.GetInputLocation(attribute.Get_Libraries_Game_Graphics_VertexAttribute__alias_());
                if (location < 0)
                    continue;

                gl.glEnableVertexAttribArray(location);

                if (attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_() == GraphicsManager.GL_FLOAT) 
                {
                    buffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_() / 4);
                    gl.glVertexAttribPointer(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(),
                        buffer);
                }
                else
                {
                    byteBuffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
                    gl.glVertexAttribPointer(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(),
                        byteBuffer);
                }
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

                gl.glEnableVertexAttribArray(location);

                if (attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_() == GraphicsManager.GL_FLOAT) 
                {
                    buffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_() / 4);
                    gl.glVertexAttribPointer(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(), buffer);
                }
                else 
                {
                    byteBuffer.position(attribute.Get_Libraries_Game_Graphics_VertexAttribute__offset_());
                    gl.glVertexAttribPointer(location, attribute.Get_Libraries_Game_Graphics_VertexAttribute__componentCount_(),
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__type_(), 
                        attribute.Get_Libraries_Game_Graphics_VertexAttribute__normalized_(),
                        attributes.Get_Libraries_Game_Graphics_VertexAttributes__vertexSize_(),
                        byteBuffer);
                }
            }
        }
        isBound = true;
    }

    /** Unbinds this VertexData. */
    public void Unbind(ShaderProgram_ shader)
    {
        Unbind(shader, null);
    }

    /** Unbinds this VertexData.
     * @param locations array containing the attribute locations. */
    public void Unbind(ShaderProgram_ shader, int[] locations)
    {
        final GraphicsManager gl = GameStateManager.nativeGraphics;
        final int numAttributes = attributes.GetSize();
        if (locations == null) 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                gl.glDisableVertexAttribArray(shader.GetInputLocation(attributes.GetAttribute(i).Get_Libraries_Game_Graphics_VertexAttribute__alias_()));
            }
        }
        else 
        {
            for (int i = 0; i < numAttributes; i++) 
            {
                final int location = locations[i];
                if (location >= 0)
                    gl.glDisableVertexAttribArray(location);
            }
        }
        isBound = false;
    }
    
    @Override
    public VertexAttributes_ GetAttributes() 
    {
        return attributes;
    }

    @Override
    public void Reload() 
    {
        // Do nothing. The VertexArray doesn't require manual reloading.
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
        BufferUtils.copy(bridgeArray, buffer, bridgeArray.length, 0);
    }

}
