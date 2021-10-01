/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.ByteOrder;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import plugins.quorum.Libraries.Game.GameRuntimeError;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Game.Graphics.Number32BitBuffer;
import quorum.Libraries.Game.Graphics.Number32BitBuffer_;
import quorum.Libraries.Game.Graphics.Integer32BitBuffer;
import quorum.Libraries.Game.Graphics.Integer32BitBuffer_;
import quorum.Libraries.Language.Types.Integer_;

/**
 *
 * @author Taylor Bockman, William Allee
 * 
 * This is a utility class utilized exclusively by the Java plugin side.
 */
public class DesktopGraphics implements GraphicsManager {
    public java.lang.Object me_ = null;

    // All GL20 constants are kept at the bottom of this class (as to make it
    // easier to find the functions while work is still undergoing).
    
    private ByteBuffer buffer = null;
    private FloatBuffer floatBuffer = null;
    private IntBuffer intBuffer = null;

    //Sets the OpenGL Clear Screen color when wiping the screen
    public void ClearScreenColor(float red, float green, float blue, float alpha)
    {
        GL11.glClearColor(red, green, blue, alpha);
    }
    
    public int CreateShaderID(int shaderType)
    {
        return glCreateShader(shaderType);
    }

    public void SetShaderCode(int shaderID, String source)
    {
        glShaderSource(shaderID, source);
    }

    public void CompileShader(int shaderID)
    {
        glCompileShader(shaderID);
    }

    public boolean IsShaderCompiled(int shaderID)
    {
        IntBuffer intbuf = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        glGetShaderiv(shaderID, GraphicsManager.GL_COMPILE_STATUS, intbuf);

        int compiled = intbuf.get(0);
        return compiled != 0;
    }

    public String GetShaderLog(int shaderID)
    {
        return glGetShaderInfoLog(shaderID);
    }

    public int CreateShaderProgramID()
    {
        return glCreateProgram();
    }

    public void AttachShader(int programID, int shaderID)
    {
        glAttachShader(programID, shaderID);
    }

    public void LinkShaderProgram(int programID)
    {
        glLinkProgram(programID);
    }
    
    public boolean IsShaderProgramLinked(int programID)
    {
        IntBuffer intbuf = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        glGetProgramiv(programID, GraphicsManager.GL_LINK_STATUS, intbuf);

        int linked = intbuf.get(0);
        return linked != 0;
    }

    public String GetShaderProgramLog(int programID)
    {
        return glGetProgramInfoLog(programID);
    }
    
    public void UseShaderProgram(int programID)
    {
        glUseProgram(programID);
    }
    
    public void DeleteShader(int shaderID)
    {
        glDeleteShader(shaderID);
    }
    
    public void DeleteShaderProgram(int programID)
    {
        glDeleteProgram(programID);
    }
    
    public void EnableProperty(int property)
    {
        glEnable(property);
    }

    public void DisableProperty(int property)
    {
        glDisable(property);
    }

    public void SetDepthMask(boolean mask)
    {
        glDepthMask(mask);
    }
    
    public void SetBlendFunction(int sfactor, int dfactor)
    {
        glBlendFunc(sfactor, dfactor);
    }
    
    public void SetCullFace(int face)
    {
        glCullFace(face);
    }

    public void SetDepthRange(double near, double far)
    {
        glDepthRangef((float)near, (float)far);
    }
    
    public int GetMaxTextureUnits()
    {
        IntBuffer buffer = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(16);
        glGetIntegerv(GraphicsManager.GL_MAX_TEXTURE_IMAGE_UNITS, buffer);
        return buffer.get(0);
    }
    
    public void SetActiveTextureID(int id)
    {
        glActiveTexture(id);
    }
    
    public int CreateBuffer()
    {
        return glGenBuffer();
    }

    public void BindBuffer(int purpose, int bufferID)
    {
        glBindBuffer(purpose, bufferID);
    }

    public void DeleteBuffer(int bufferID)
    {
        glDeleteBuffer(bufferID);
    }
    
    public void SetBuffer(int purpose, Number32BitBuffer_ buffer, int option)
    {
        ByteBuffer data = ((Number32BitBuffer)buffer).plugin_.byteBuffer;
        glBufferData(purpose, data.limit(), data, option);
    }

    public void SetBuffer(int purpose, Integer32BitBuffer_ buffer, int option)
    {
        ByteBuffer data = ((Integer32BitBuffer)buffer).plugin_.byteBuffer;
        glBufferData(purpose, data.limit(), data, option);
    }
    
    public void EnableVertexInput(int id)
    {
        glEnableVertexAttribArray(id);
    }
    
    public void DisableVertexInput(int id)
    {
        glDisableVertexAttribArray(id);
    }
    
    public void DrawBuffer(int primitiveType, int offset, int count)
    {
        glDrawArrays(primitiveType, offset, count);
    }

    public void DrawIndexedBuffer(int primitiveType, int count, int indexType, Integer32BitBuffer_ indices)
    {
        IntBuffer buffer = ((Integer32BitBuffer)indices).plugin_.buffer;
        glDrawElements(primitiveType, count, indexType, buffer);
    }

    public void DrawIndexedBuffer(int primitiveType, int count, int indexType, int indicesOffset)
    {
        glDrawElements(primitiveType, count, indexType, indicesOffset);
    }
    
    public void SetDepthFunction(int function)
    {
        glDepthFunc(function);
    }
    
    public int GetShaderInputCount(int programID)
    {
        IntBuffer params = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        glGetProgramiv(programID, GraphicsManager.GL_ACTIVE_UNIFORMS, params);
        return params.get(0);
    }

    public String GetShaderInputInformation(int programID, int index, Integer_ location, Integer_ size, Integer_ type)
    {
        IntBuffer params = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        params.put(0, 1);
        IntBuffer typeBuffer = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        typeBuffer.clear();
        
        String name = glGetActiveUniform(programID, index, params, typeBuffer);
        int value = glGetUniformLocation(programID, name);
        location.SetValue(value);
        type.SetValue(typeBuffer.get(0));
        size.SetValue(params.get(0));
        return name;
    }
    
    public int GetVertexInputCount(int programID)
    {
        IntBuffer params = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        glGetProgramiv(programID, GraphicsManager.GL_ACTIVE_ATTRIBUTES, params);
        return params.get(0);
    }

    public String GetVertexInputInformation(int programID, int index, Integer_ location, Integer_ size, Integer_ type)
    {
        IntBuffer params = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        params.put(0, 1);
        IntBuffer typeBuffer = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newIntBuffer(1);
        typeBuffer.clear();
        
        String name = glGetActiveAttrib(programID, index, params, typeBuffer);
        int value = glGetAttribLocation(programID, name);
        location.SetValue(value);
        type.SetValue(typeBuffer.get(0));
        size.SetValue(params.get(0));
        return name;
    }
    
    public void SetShaderInput(int uniformID, int value)
    {
        glUniform1i(uniformID, value);
    }
    
    public void SetShaderInput(int uniformID, int value1, int value2)
    {
        glUniform2i(uniformID, value1, value2);
    }

    public void SetShaderInput(int uniformID, int value1, int value2, int value3)
    {
        glUniform3i(uniformID, value1, value2, value3);
    }

    public void SetShaderInput(int uniformID, int value1, int value2, int value3, int value4)
    {
        glUniform4i(uniformID, value1, value2, value3, value4);
    }

    public void SetShaderInput(int uniformID, double value)
    {
        glUniform1f(uniformID, (float)value);
    }

    public void SetShaderInput(int uniformID, double value1, double value2)
    {
        glUniform2f(uniformID, (float)value1, (float)value2);
    }

    public void SetShaderInput(int uniformID, double value1, double value2, double value3)
    {
        glUniform3f(uniformID, (float)value1, (float)value2, (float)value3);
    }

    public void SetShaderInput(int uniformID, double value1, double value2, double value3, double value4)
    {
        glUniform4f(uniformID, (float)value1, (float)value2, (float)value3, (float)value4);
    }
    
    public void SetShaderInputArray(int uniformID, Number32BitArray_ array, int startIndex, int length)
    {
        glUniform1fv(uniformID, length, ((Number32BitArray)array).plugin_.floats, startIndex);
    }

    public void SetShaderInputVector2Array(int uniformID, Number32BitArray_ array, int startIndex, int length)
    {
        glUniform2fv(uniformID, length, ((Number32BitArray)array).plugin_.floats, startIndex);
    }

    public void SetShaderInputVector3Array(int uniformID, Number32BitArray_ array, int startIndex, int length)
    {
        glUniform3fv(uniformID, length, ((Number32BitArray)array).plugin_.floats, startIndex);
    }

    public void SetShaderInputVector4Array(int uniformID, Number32BitArray_ array, int startIndex, int length)
    {
        glUniform4fv(uniformID, length, ((Number32BitArray)array).plugin_.floats, startIndex);
    }

    public void SetShaderInputMatrix4Array(int uniformID, int matrixCount, Number32BitArray_ array, int startIndex, boolean transpose)
    {
        glUniformMatrix4fv(uniformID, matrixCount, transpose, ((Number32BitArray)array).plugin_.floats, startIndex);
    }

    public void SetShaderInputMatrix3Array(int uniformID, int matrixCount, Number32BitArray_ array, int startIndex, boolean transpose)
    {
        glUniformMatrix3fv(uniformID, matrixCount, transpose, ((Number32BitArray)array).plugin_.floats, startIndex);
    }
    
    public void SetVertexInputInformation(int inputID, int size, int type, boolean normalize, int stride, int offset)
    {
        glVertexAttribPointer(inputID, size, type, normalize, stride, offset);
    }
    
    public void SetPixelClipping(int x, int y, int width, int height)
    {
        glScissor(x, y, width, height);
    }
    
    public void glBindTexture (int target, int texture) 
    {
        GL11.glBindTexture(target, texture);
    }

    public void PixelStorageMode(int type, int parameter) {
        GL11.glPixelStorei(type, parameter);
    }
    
    public String glGetString (int name) 
    {
	return GL11.glGetString(name);
    }
    
    // This version of ClearScreenColor accepts the 64 bit values that the Quorum
    // "number" data type uses (i.e. doubles) and casts them down to 32 bit for
    // usage by GL11.
    public void ClearScreenColor(double red, double green, double blue, double alpha)
    {
        GL11.glClearColor((float)red, (float)green, (float)blue, (float)alpha);
    }

    //Actually performs the screen clearing
    public void ClearScreen(int mask){
        GL11.glClear(mask);
    }

    //Actually glViewport
    public void SetDrawingRegion(int x, int y, int width, int height){
        GL11.glViewport(x, y, width, height);
    }
    
    public void glActiveTexture(int texture)
    {
        GL13.glActiveTexture(texture);
    }

    public int glGenTexture()
    {
        return GL11.glGenTextures();
    }

    public void glGenTextures (int n, IntBuffer textures) {
        GL11.glGenTextures(textures);
    }

    public int glGenBuffer () 
    {
        return GL15.glGenBuffers();
    }

    public void glBindBuffer (int target, int buffer) 
    {
        GL15.glBindBuffer(target, buffer);
    }
    
    public void glBufferData (int target, int size, Buffer data, int usage) 
    {
	if (data == null)
            GL15.glBufferData(target, size, usage);
	else if (data instanceof ByteBuffer)
            GL15.glBufferData(target, (ByteBuffer)data, usage);
	else if (data instanceof IntBuffer)
            GL15.glBufferData(target, (IntBuffer)data, usage);
	else if (data instanceof FloatBuffer)
            GL15.glBufferData(target, (FloatBuffer)data, usage);
	else if (data instanceof DoubleBuffer)
            GL15.glBufferData(target, (DoubleBuffer)data, usage);
	else if (data instanceof ShortBuffer) //
            GL15.glBufferData(target, (ShortBuffer)data, usage);
    }
    
    public void glBufferSubData (int target, int offset, int size, Buffer data) 
    {
	if (data == null)
            throw new GameRuntimeError("The data value is undefined, which is not allowed for this action.");
	else if (data instanceof ByteBuffer)
            GL15.glBufferSubData(target, offset, (ByteBuffer)data);
	else if (data instanceof IntBuffer)
            GL15.glBufferSubData(target, offset, (IntBuffer)data);
	else if (data instanceof FloatBuffer)
            GL15.glBufferSubData(target, offset, (FloatBuffer)data);
	else if (data instanceof DoubleBuffer)
            GL15.glBufferSubData(target, offset, (DoubleBuffer)data);
	else if (data instanceof ShortBuffer) //
            GL15.glBufferSubData(target, offset, (ShortBuffer)data);
    }
    
    public void glDeleteBuffer (int buffer) 
    {
	GL15.glDeleteBuffers(buffer);
    }
    
    public void glDeleteTexture (int texture) 
    {
	GL11.glDeleteTextures(texture);
    }
    
    public void glDepthMask (boolean flag) 
    {
	GL11.glDepthMask(flag);
    }
    
    public void glDisable (int cap) 
    {
	GL11.glDisable(cap);
    }
    
    public void glEnable (int cap) 
    {
	GL11.glEnable(cap);
    }
    
    public int glCreateShader (int type) 
    {
	return GL20.glCreateShader(type);
    }
    
    public void glShaderSource (int shader, String string) 
    {
	GL20.glShaderSource(shader, string);
    }
    
    public void glCompileShader (int shader) 
    {
	GL20.glCompileShader(shader);
    }
    
    public void glGetShaderiv (int shader, int pname, IntBuffer params) 
    {
        GL20.glGetShaderiv(shader, pname, params);
    }
    
    public String glGetShaderInfoLog (int shader) 
    {
	ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
	buffer.order(ByteOrder.nativeOrder());
	ByteBuffer tmp = ByteBuffer.allocateDirect(4);
	tmp.order(ByteOrder.nativeOrder());
	IntBuffer intBuffer = tmp.asIntBuffer();

	GL20.glGetShaderInfoLog(shader, intBuffer, buffer);
	int numBytes = intBuffer.get(0);
	byte[] bytes = new byte[numBytes];
	buffer.get(bytes);
	return new String(bytes);
    }
    
    public String glGetProgramInfoLog (int program) 
    {
	ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
	buffer.order(ByteOrder.nativeOrder());
	ByteBuffer tmp = ByteBuffer.allocateDirect(4);
	tmp.order(ByteOrder.nativeOrder());
	IntBuffer intBuffer = tmp.asIntBuffer();

	GL20.glGetProgramInfoLog(program, intBuffer, buffer);
	int numBytes = intBuffer.get(0);
	byte[] bytes = new byte[numBytes];
	buffer.get(bytes);
	return new String(bytes);
    }
    
    public void glGetProgramiv (int program, int pname, IntBuffer params) 
    {
	GL20.glGetProgramiv(program, pname, params);
    }
    
    public int glGetAttribLocation (int program, String name) 
    {
	return GL20.glGetAttribLocation(program, name);
    }
    
    public int glCreateProgram () 
    {
	return GL20.glCreateProgram();
    }
    
    public void glAttachShader (int program, int shader) 
    {
	GL20.glAttachShader(program, shader);
    }
    
    public void glLinkProgram (int program) 
    {
	GL20.glLinkProgram(program);
    }
    
    public int glGetUniformLocation (int program, String name) 
    {
	return GL20.glGetUniformLocation(program, name);
    }
    
    public void glUniform1i (int location, int x) 
    {
	GL20.glUniform1i(location, x);
    }

    public void glUniform1iv (int location, int count, IntBuffer v) 
    {
	GL20.glUniform1iv(location, v);
    }

    public void glUniform1iv (int location, int count, int[] v, int offset) 
    {
	GL20.glUniform1iv(location, toIntBuffer(v, offset, count));
    }
    
    public void glUniform1f (int location, float x) 
    {
	GL20.glUniform1f(location, x);
    }
    
    public void glUniform1fv (int location, int count, FloatBuffer v) 
    {
	GL20.glUniform1fv(location, v);
    }

    public void glUniform1fv (int location, int count, float[] v, int offset) 
    {
	GL20.glUniform1fv(location, toFloatBuffer(v, offset, count));
    }
    
    public void glUniform2i (int location, int x, int y) 
    {
	GL20.glUniform2i(location, x, y);
    }

    public void glUniform2iv (int location, int count, IntBuffer v) 
    {
	GL20.glUniform2iv(location, v);
    }

    public void glUniform2iv (int location, int count, int[] v, int offset) 
    {
	GL20.glUniform2iv(location, toIntBuffer(v, offset, count << 1));
    }
    
    public void glUniform2f (int location, float x, float y) 
    {
	GL20.glUniform2f(location, x, y);
    }
    
    public void glUniform2fv (int location, int count, FloatBuffer v) 
    {
	GL20.glUniform2fv(location, v);
    }

    public void glUniform2fv (int location, int count, float[] v, int offset) 
    {
	GL20.glUniform2fv(location, toFloatBuffer(v, offset, count << 1));
    }
    
    public void glUniform3i (int location, int x, int y, int z) 
    {
	GL20.glUniform3i(location, x, y, z);
    }

    public void glUniform3iv (int location, int count, IntBuffer v) 
    {
	GL20.glUniform3iv(location, v);
    }

    public void glUniform3iv (int location, int count, int[] v, int offset) 
    {
	GL20.glUniform3iv(location, toIntBuffer(v, offset, count * 3));
    }
    
    public void glUniform3f (int location, float x, float y, float z) 
    {
	GL20.glUniform3f(location, x, y, z);
    }
    
    public void glUniform3fv (int location, int count, FloatBuffer v) 
    {
	GL20.glUniform3fv(location, v);
    }

    public void glUniform3fv (int location, int count, float[] v, int offset) 
    {
	GL20.glUniform3fv(location, toFloatBuffer(v, offset, count * 3));
    }
    
    public void glUniform4i (int location, int x, int y, int z, int w) 
    {
	GL20.glUniform4i(location, x, y, z, w);
    }

    public void glUniform4iv (int location, int count, IntBuffer v) 
    {
	GL20.glUniform4iv(location, v);
    }

    public void glUniform4iv (int location, int count, int[] v, int offset) 
    {
	GL20.glUniform4iv(location, toIntBuffer(v, offset, count << 2));
    }
    
    public void glUniform4f (int location, float x, float y, float z, float w) 
    {
	GL20.glUniform4f(location, x, y, z, w);
    }
    
    public void glUniform4fv (int location, int count, FloatBuffer v) 
    {
	GL20.glUniform4fv(location, v);
    }

    public void glUniform4fv (int location, int count, float[] v, int offset) 
    {
	GL20.glUniform4fv(location, toFloatBuffer(v, offset, count << 2));
    }

    public void glUniformMatrix2fv (int location, int count, boolean transpose, FloatBuffer value) 
    {
	GL20.glUniformMatrix2fv(location, transpose, value);
    }

    public void glUniformMatrix2fv (int location, int count, boolean transpose, float[] value, int offset) 
    {
        GL20.glUniformMatrix2fv(location, transpose, toFloatBuffer(value, offset, count << 2));
    }

    public void glUniformMatrix3fv (int location, int count, boolean transpose, FloatBuffer value) 
    {
	GL20.glUniformMatrix3fv(location, transpose, value);
    }

    public void glUniformMatrix3fv (int location, int count, boolean transpose, float[] value, int offset) 
    {
	GL20.glUniformMatrix3fv(location, transpose, toFloatBuffer(value, offset, count * 9));
    }

    public void glUniformMatrix4fv (int location, int count, boolean transpose, FloatBuffer value) 
    {
	GL20.glUniformMatrix4fv(location, transpose, value);
    }

    public void glUniformMatrix4fv (int location, int count, boolean transpose, float[] value, int offset) 
    {
	GL20.glUniformMatrix4fv(location, transpose, toFloatBuffer(value, offset, count << 4));
    }
    
    public void glVertexAttribPointer (int indx, int size, int type, boolean normalized, int stride, Buffer buffer) 
    {
        
	if (buffer instanceof ByteBuffer) 
        {
            if (type == GL_BYTE || type == GL_UNSIGNED_BYTE)
		GL20.glVertexAttribPointer(indx, size, type, normalized, stride, (ByteBuffer)buffer);
            else if (type == GL_SHORT || type == GL_UNSIGNED_SHORT)
		GL20.glVertexAttribPointer(indx, size, type, normalized, stride, ((ByteBuffer)buffer).asShortBuffer());
            else if (type == GL_FLOAT)
		GL20.glVertexAttribPointer(indx, size, type, normalized, stride, ((ByteBuffer)buffer).asFloatBuffer());
            else
		throw new GameRuntimeError("Can't use " + buffer.getClass().getName() + " with type " + type
						+ " with this method. Use ByteBuffer and one of GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT or GL_FLOAT for type.");
	}
        else if (buffer instanceof FloatBuffer) 
        {
            if (type == GL_FLOAT)
		GL20.glVertexAttribPointer(indx, size, type, normalized, stride, (FloatBuffer)buffer);
            else
		throw new GameRuntimeError("Can't use " + buffer.getClass().getName() + " with type " + type + " with this method.");
	} 
        else
            throw new GameRuntimeError("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer instead.");
    }
    
    public void glVertexAttribPointer (int indx, int size, int type, boolean normalized, int stride, int ptr) 
    {
	GL20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }
    
    public void glUseProgram (int program) 
    {
	GL20.glUseProgram(program);
    }
    
    public void glDeleteShader (int shader) 
    {
	GL20.glDeleteShader(shader);
    }
    
    public void glDeleteProgram (int program) 
    {
	GL20.glDeleteProgram(program);
    }
    
    public void glDisableVertexAttribArray (int index) 
    {
        GL20.glDisableVertexAttribArray(index);
    }
    
    public void glEnableVertexAttribArray (int index) 
    {
	GL20.glEnableVertexAttribArray(index);
    }
    
    public void glVertexAttrib4f (int indx, float x, float y, float z, float w) 
    {
	GL20.glVertexAttrib4f(indx, x, y, z, w);
    }

    public void glVertexAttrib4fv (int indx, FloatBuffer values) 
    {
	GL20.glVertexAttrib4f(indx, values.get(), values.get(), values.get(), values.get());
    }
    
    public String glGetActiveUniform (int program, int index, IntBuffer size, Buffer type) 
    {
        if (type instanceof IntBuffer)
        {
            return GL20.glGetActiveUniform(program, index, size, (IntBuffer)type);
        }
        else
        {
            IntBuffer typeTmp = BufferUtils.createIntBuffer(1);
            return GL20.glGetActiveUniform(program, index, size, typeTmp);
        }
    }
    
    public String glGetActiveAttrib (int program, int index, IntBuffer size, Buffer type) 
    {
        if (type instanceof IntBuffer)
        {
            return GL20.glGetActiveAttrib(program, index, size, (IntBuffer)type);
        }
        else
        {
            IntBuffer typeTmp = BufferUtils.createIntBuffer(1);
            return GL20.glGetActiveAttrib(program, index, size, typeTmp);
        }
    }
    
    public void glDrawElements (int mode, int count, int type, int indices) 
    {
	GL11.glDrawElements(mode, count, type, indices);
    }
    
    public void glDrawElements (int mode, int count, int type, Buffer indices) 
    {
	if (indices instanceof ShortBuffer && type == GL_UNSIGNED_SHORT)
            GL11.glDrawElements(mode, (ShortBuffer)indices);
	else if (indices instanceof ByteBuffer && type == GL_UNSIGNED_SHORT)
            GL11.glDrawElements(mode, ((ByteBuffer)indices).asShortBuffer()); // FIXME yay...
        else if (indices instanceof ByteBuffer && type == GL_UNSIGNED_BYTE)
            GL11.glDrawElements(mode, (ByteBuffer)indices);
        else if (indices instanceof IntBuffer && type == GL_UNSIGNED_INT)
            GL11.glDrawElements(mode, (IntBuffer)indices);
	else
            throw new GameRuntimeError("Can't use " + indices.getClass().getName()
			+ " with this method. Use ShortBuffer or ByteBuffer instead.");
    }
    
    public void glDrawArrays (int mode, int first, int count) 
    {
	GL11.glDrawArrays(mode, first, count);
    }
    
    public void glBlendFunc (int sfactor, int dfactor) 
    {
	GL11.glBlendFunc(sfactor, dfactor);
    }
  
    public void SetTextureParameter(int targetTexture, int parameterType, int parameterValue)
    {
        GL11.glTexParameterf(targetTexture, parameterType, parameterValue);
    }

    //This method was pulled directly out of libgdx because it just works as a drop-in.
    public void glTexImage2D (int target, int level, int internalformat, int width, int height, int border, int format, int type,
                  Buffer pixels) 
    {
        if (pixels == null)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (ByteBuffer)null);
        else if (pixels instanceof ByteBuffer)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (ByteBuffer)pixels);
        else if (pixels instanceof ShortBuffer)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (ShortBuffer)pixels);
        else if (pixels instanceof IntBuffer)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (IntBuffer)pixels);
        else if (pixels instanceof FloatBuffer)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (FloatBuffer)pixels);
        else if (pixels instanceof DoubleBuffer)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (DoubleBuffer)pixels);
        else
            throw new GameRuntimeError("An error occurred while defining 2D image.");

    }
    
    public void glGetIntegerv(int pname, IntBuffer params)
    {
        GL11.glGetIntegerv(pname, params);
    }
    
    public void glDepthFunc (int func) 
    {
        GL11.glDepthFunc(func);
    }
    
    public void glDepthRangef (float zNear, float zFar) 
    {
        GL11.glDepthRange(zNear, zFar);
    }
    
    public void glCullFace (int mode) 
    {
        GL11.glCullFace(mode);
    }
    
    public void glScissor(int x, int y, int width, int height)
    {
        GL11.glScissor(x, y, width, height);
    }
  
    private IntBuffer toIntBuffer (int v[], int offset, int count) 
    {
	ensureBufferCapacity(count << 2);
	floatBuffer.clear();
	plugins.quorum.Libraries.Game.libGDX.BufferUtils.copy(v, count, offset, intBuffer);
	return intBuffer;
    }

    private void ensureBufferCapacity (int numBytes) 
    {
        if (buffer == null || buffer.capacity() < numBytes) {
            buffer = plugins.quorum.Libraries.Game.libGDX.BufferUtils.newByteBuffer(numBytes);
            floatBuffer = buffer.asFloatBuffer();
            intBuffer = buffer.asIntBuffer();
        }
    }

    private FloatBuffer toFloatBuffer (float v[], int offset, int count) {
            ensureBufferCapacity(count << 2);
            floatBuffer.clear();
            plugins.quorum.Libraries.Game.libGDX.BufferUtils.copy(v, floatBuffer, count, offset);
            return floatBuffer;
    }
}

