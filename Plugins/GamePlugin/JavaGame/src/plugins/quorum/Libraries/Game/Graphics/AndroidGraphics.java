/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import android.opengl.GLES20;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.ShortBuffer;
import plugins.quorum.Libraries.Game.GameRuntimeError;

/**
 *
 * @author alleew
 */
public class AndroidGraphics implements GraphicsManager
{
    public java.lang.Object me_ = null;

    private ByteBuffer buffer = null;
    private FloatBuffer floatBuffer = null;
    private IntBuffer intBuffer = null;

    //Sets the OpenGL Clear Screen color when wiping the screen
    public void ClearScreenColor(float red, float green, float blue, float alpha){
        GLES20.glClearColor(red, green, blue, alpha);
    }

    public void glBindTexture (int target, int texture) {
        GLES20.glBindTexture(target, texture);
    }

    public void PixelStorageMode(int type, int parameter) {
        GLES20.glPixelStorei(type, parameter);
    }
    
    public String glGetString (int name) 
    {
        return GLES20.glGetString(name);
    }
    
    // This version of ClearScreenColor accepts the 64 bit values that the Quorum
    // "number" data type uses (i.e. doubles) and casts them down to 32 bit for
    // usage by GL11.
    public void ClearScreenColor(double red, double green, double blue, double alpha)
    {
        GLES20.glClearColor((float)red, (float)green, (float)blue, (float)alpha);
    }

    //Actually performs the screen clearing
    public void ClearScreen(int mask){
        GLES20.glClear(mask);
    }

    //Actually glViewport
    public void SetDrawingRegion(int x, int y, int width, int height){
        GLES20.glViewport(x, y, width, height);
    }
    
    public void glActiveTexture(int texture)
    {
        GLES20.glActiveTexture(texture);
    }

    public int glGenTexture()
    {
        int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        return texture[0];
    }

    public void glGenTextures (int n, IntBuffer textures) {
        GLES20.glGenTextures(n, textures);
    }

    public int glGenBuffer () 
    {
        int[] buffer = new int[1];
        GLES20.glGenBuffers(1, buffer, 0);
        return buffer[0];
    }

    public void glBindBuffer (int target, int buffer) 
    {
        GLES20.glBindBuffer(target, buffer);
    }
    
    public void glBufferData (int target, int size, Buffer data, int usage) 
    {
        GLES20.glBufferData(target, size, data, usage);
    }
    
    public void glBufferSubData (int target, int offset, int size, Buffer data) 
    {
        GLES20.glBufferSubData(target, offset, size, data);
    }
    
    public void glDeleteBuffer (int buffer) 
    {
        int[] array = new int[1];
        array[0] = buffer;
        GLES20.glDeleteBuffers(1, array, 0);
    }
    
    public void glDeleteTexture (int texture) 
    {
        int[] array = new int[1];
        array[0] = texture;
        GLES20.glDeleteTextures(1, array, 0);
    }
    
    public void glDepthMask (boolean flag) 
    {
        GLES20.glDepthMask(flag);
    }
    
    public void glDisable (int cap) 
    {
        GLES20.glDisable(cap);
    }
    
    public void glEnable (int cap) 
    {
	GLES20.glEnable(cap);
    }
    
    public int glCreateShader (int type) 
    {
	return GLES20.glCreateShader(type);
    }
    
    public void glShaderSource (int shader, String string) 
    {
	GLES20.glShaderSource(shader, string);
    }
    
    public void glCompileShader (int shader) 
    {
	GLES20.glCompileShader(shader);
    }
    
    public void glGetShaderiv (int shader, int pname, IntBuffer params) 
    {
        GLES20.glGetShaderiv(shader, pname, params);
    }
    
    public String glGetShaderInfoLog (int shader) 
    {
        return GLES20.glGetShaderInfoLog(shader);
        
        /*
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
        */
    }
    
    public String glGetProgramInfoLog (int program) 
    {
        return GLES20.glGetProgramInfoLog(program);
        
        /*
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
        */
    }
    
    public void glGetProgramiv (int program, int pname, IntBuffer params) 
    {
        GLES20.glGetProgramiv(program, pname, params);
    }
    
    public int glGetAttribLocation (int program, String name) 
    {
        return GLES20.glGetAttribLocation(program, name);
    }
    
    public int glCreateProgram () 
    {
        return GLES20.glCreateProgram();
    }
    
    public void glAttachShader (int program, int shader) 
    {
        GLES20.glAttachShader(program, shader);
    }
    
    public void glLinkProgram (int program) 
    {
        GLES20.glLinkProgram(program);
    }
    
    public int glGetUniformLocation (int program, String name) 
    {
        return GLES20.glGetUniformLocation(program, name);
    }
    
    public void glUniform1i (int location, int x) 
    {
	GLES20.glUniform1i(location, x);
    }

    public void glUniform1iv (int location, int count, IntBuffer v) 
    {
        GLES20.glUniform1iv(location, count, v);
    }

    public void glUniform1iv (int location, int count, int[] v, int offset) 
    {
        GLES20.glUniform1iv(location, count, v, offset);
    }
    
    public void glUniform1f (int location, float x) 
    {
        GLES20.glUniform1f(location, x);
    }
    
    public void glUniform1fv (int location, int count, FloatBuffer v) 
    {
        GLES20.glUniform1fv(location, count, v);
    }

    public void glUniform1fv (int location, int count, float[] v, int offset) 
    {
        GLES20.glUniform1fv(location, count, v, offset);
    }
    
    public void glUniform2i (int location, int x, int y) 
    {
	GLES20.glUniform2i(location, x, y);
    }

    public void glUniform2iv (int location, int count, IntBuffer v) 
    {
        GLES20.glUniform2iv(location, count, v);
    }

    public void glUniform2iv (int location, int count, int[] v, int offset) 
    {
        GLES20.glUniform2iv(location, count, v, offset);
    }
    
    public void glUniform2f (int location, float x, float y) 
    {
	GLES20.glUniform2f(location, x, y);
    }
    
    public void glUniform2fv (int location, int count, FloatBuffer v) 
    {
        GLES20.glUniform2fv(location, count, v);
    }

    public void glUniform2fv (int location, int count, float[] v, int offset) 
    {
        GLES20.glUniform2fv(location, count, v, offset);
    }
    
    public void glUniform3i (int location, int x, int y, int z) 
    {
	GLES20.glUniform3i(location, x, y, z);
    }

    public void glUniform3iv (int location, int count, IntBuffer v) 
    {
        GLES20.glUniform3iv(location, count, v);
    }

    public void glUniform3iv (int location, int count, int[] v, int offset) 
    {
        GLES20.glUniform3iv(location, count, v, offset);
    }
    
    public void glUniform3f (int location, float x, float y, float z) 
    {
	GLES20.glUniform3f(location, x, y, z);
    }
    
    public void glUniform3fv (int location, int count, FloatBuffer v) 
    {
        GLES20.glUniform3fv(location, count, v);
    }

    public void glUniform3fv (int location, int count, float[] v, int offset) 
    {
        GLES20.glUniform3fv(location, count, v, offset);
    }
    
    public void glUniform4i (int location, int x, int y, int z, int w) 
    {
        GLES20.glUniform4i(location, x, y, z, w);
    }

    public void glUniform4iv (int location, int count, IntBuffer v) 
    {
        GLES20.glUniform4iv(location, count, v);
    }

    public void glUniform4iv (int location, int count, int[] v, int offset) 
    {
        GLES20.glUniform4iv(location, count, v, offset);
    }
    
    public void glUniform4f (int location, float x, float y, float z, float w) 
    {
	GLES20.glUniform4f(location, x, y, z, w);
    }
    
    public void glUniform4fv (int location, int count, FloatBuffer v) 
    {
        GLES20.glUniform4fv(location, count, v);
    }

    public void glUniform4fv (int location, int count, float[] v, int offset) 
    {
        GLES20.glUniform4fv(location, count, v, offset);
    }

    public void glUniformMatrix2fv (int location, int count, boolean transpose, FloatBuffer value) 
    {
        GLES20.glUniformMatrix2fv(location, count, transpose, value);
    }

    public void glUniformMatrix2fv (int location, int count, boolean transpose, float[] value, int offset) 
    {
        GLES20.glUniformMatrix2fv(location, count, transpose, value, offset);
    }

    public void glUniformMatrix3fv (int location, int count, boolean transpose, FloatBuffer value) 
    {
        GLES20.glUniformMatrix3fv(location, count, transpose, value);
    }

    public void glUniformMatrix3fv (int location, int count, boolean transpose, float[] value, int offset) 
    {
        GLES20.glUniformMatrix3fv(location, count, transpose, value, offset);
    }

    public void glUniformMatrix4fv (int location, int count, boolean transpose, FloatBuffer value) 
    {
        GLES20.glUniformMatrix4fv(location, count, transpose, value);
    }

    public void glUniformMatrix4fv (int location, int count, boolean transpose, float[] value, int offset) 
    {
        GLES20.glUniformMatrix4fv(location, count, transpose, value, offset);
    }
    
    public void glVertexAttribPointer (int indx, int size, int type, boolean normalized, int stride, Buffer buffer) 
    {
        GLES20.glVertexAttribPointer(indx, size, type, normalized, stride, buffer);
        
        /*
	if (buffer instanceof ByteBuffer) 
        {
            if (type == GL_BYTE)
		GL20.glVertexAttribPointer(indx, size, false, normalized, stride, (ByteBuffer)buffer);
            else if (type == GL_UNSIGNED_BYTE)
		GL20.glVertexAttribPointer(indx, size, true, normalized, stride, (ByteBuffer)buffer);
            else if (type == GL_SHORT)
		GL20.glVertexAttribPointer(indx, size, false, normalized, stride, ((ByteBuffer)buffer).asShortBuffer());
            else if (type == GL_UNSIGNED_SHORT)
		GL20.glVertexAttribPointer(indx, size, true, normalized, stride, ((ByteBuffer)buffer).asShortBuffer());
            else if (type == GL_FLOAT)
		GL20.glVertexAttribPointer(indx, size, normalized, stride, ((ByteBuffer)buffer).asFloatBuffer());
            else
		throw new GameRuntimeError("Can't use " + buffer.getClass().getName() + " with type " + type
						+ " with this method. Use ByteBuffer and one of GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT or GL_FLOAT for type. Blame LWJGL");
	}
        else if (buffer instanceof FloatBuffer) 
        {
            if (type == GL_FLOAT)
		GL20.glVertexAttribPointer(indx, size, normalized, stride, (FloatBuffer)buffer);
            else
		throw new GameRuntimeError("Can't use " + buffer.getClass().getName() + " with type " + type + " with this method.");
	} 
        else
            throw new GameRuntimeError("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer instead. Blame LWJGL");
        */
    }
    
    public void glVertexAttribPointer (int indx, int size, int type, boolean normalized, int stride, int ptr) 
    {
	GLES20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }
    
    public void glUseProgram (int program) 
    {
	GLES20.glUseProgram(program);
    }
    
    public void glDeleteShader (int shader) 
    {
	GLES20.glDeleteShader(shader);
    }
    
    public void glDeleteProgram (int program) 
    {
	GLES20.glDeleteProgram(program);
    }
    
    public void glDisableVertexAttribArray (int index) 
    {
        GLES20.glDisableVertexAttribArray(index);
    }
    
    public void glEnableVertexAttribArray (int index) 
    {
	GLES20.glEnableVertexAttribArray(index);
    }
    
    public void glVertexAttrib4f (int indx, float x, float y, float z, float w) 
    {
	GLES20.glVertexAttrib4f(indx, x, y, z, w);
    }

    public void glVertexAttrib4fv (int indx, FloatBuffer values) 
    {
        GLES20.glVertexAttrib4fv(indx, values);
    }
    
    public String glGetActiveUniform (int program, int index, IntBuffer size, Buffer type) 
    {
        /*
	// FIXME this is less than ideal of course...
	IntBuffer typeTmp = BufferUtils.createIntBuffer(2);
	String name = GL20.glGetActiveUniform(program, index, 256, typeTmp);
	size.put(typeTmp.get(0));
	if (type instanceof IntBuffer) ((IntBuffer)type).put(typeTmp.get(1));
	return name;
        */
        return GLES20.glGetActiveUniform(program, index, size, (IntBuffer)type);
    }
    
    public String glGetActiveAttrib (int program, int index, IntBuffer size, Buffer type) 
    {
        /*
	// FIXME this is less than ideal of course...
	IntBuffer typeTmp = BufferUtils.createIntBuffer(2);
	String name = GL20.glGetActiveAttrib(program, index, 256, typeTmp);
	size.put(typeTmp.get(0));
	if (type instanceof IntBuffer) ((IntBuffer)type).put(typeTmp.get(1));
	return name;
        */
        return GLES20.glGetActiveAttrib(program, index, size, (IntBuffer)type);
    }
    
    public void glDrawElements (int mode, int count, int type, int indices) 
    {
	GLES20.glDrawElements(mode, count, type, indices);
    }
    
    public void glDrawElements (int mode, int count, int type, Buffer indices) 
    {
        GLES20.glDrawElements(mode, count, type, indices);
        /*
	if (indices instanceof ShortBuffer && type == GL_UNSIGNED_SHORT)
            GL11.glDrawElements(mode, (ShortBuffer)indices);
	else if (indices instanceof ByteBuffer && type == GL_UNSIGNED_SHORT)
            GL11.glDrawElements(mode, ((ByteBuffer)indices).asShortBuffer()); // FIXME yay...
        else if (indices instanceof ByteBuffer && type == GL_UNSIGNED_BYTE)
            GL11.glDrawElements(mode, (ByteBuffer)indices);
	else
            throw new GameRuntimeError("Can't use " + indices.getClass().getName()
			+ " with this method. Use ShortBuffer or ByteBuffer instead. Blame LWJGL");
        */
    }
    
    public void glDrawArrays (int mode, int first, int count) 
    {
	GLES20.glDrawArrays(mode, first, count);
    }
    
    public void glBlendFunc (int sfactor, int dfactor) 
    {
	GLES20.glBlendFunc(sfactor, dfactor);
    }
  
    public void SetTextureParameter(int targetTexture, int parameterType, int parameterValue)
    {
        GLES20.glTexParameterf(targetTexture, parameterType, parameterValue);
    }

    //This method was pulled directly out of libgdx because it just works as a drop-in.
    public void glTexImage2D (int target, int level, int internalformat, int width, int height, int border, int format, int type,
                  Buffer pixels) 
    {
        GLES20.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
        
        /*
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
        */
    }
    
    public void glGetIntegerv(int pname, IntBuffer params)
    {
        GLES20.glGetIntegerv(pname, params);
    }
    
    public void glDepthFunc(int func)
    {
        GLES20.glDepthFunc(func);
    }
    
    public void glDepthRangef (float zNear, float zFar) 
    {
        GLES20.glDepthRangef(zNear, zFar);
    }
    
    public void glCullFace (int mode) 
    {
        GLES20.glCullFace(mode);
    }
    
    public void glScissor(int x, int y, int width, int height)
    {
        GLES20.glScissor(x, y, width, height);
    }
  
    /*
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
        */
}
