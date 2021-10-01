/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSString;
import quorum.Libraries.Containers.Number32BitArray;
import quorum.Libraries.Containers.Number32BitArray_;
import quorum.Libraries.Game.Graphics.Integer32BitBuffer_;
import quorum.Libraries.Game.Graphics.Number32BitBuffer_;
import quorum.Libraries.Language.Types.Integer_;

public class IOSGraphics implements GraphicsManager
{
    public java.lang.Object me_ = null;
    
    /* Instead of doing it via constructor, going to call init() manually,
    to avoid having a sensitive method called by creation of a plugin class.

    public IOSGLES20() {
            init();
    }
    */

    // The last viewport set, needed because GLKView resets the viewport on each call to render.
    public static int x, y, width, height;
    
    // MUST BE CALLED BEFORE USING ANY OTHER METHOD OF THIS CLASS.
    public static native void init( );	
    
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
    
    public void SetActiveTextureID(int id)
    {
        glActiveTexture(id);
    }
    
    public void SetBuffer(int purpose, Number32BitBuffer_ buffer, int option)
    {
        ByteBuffer data = ((quorum.Libraries.Game.Graphics.Number32BitBuffer)buffer).plugin_.byteBuffer;
        glBufferData(purpose, data.limit(), data, option);
    }

    public void SetBuffer(int purpose, Integer32BitBuffer_ buffer, int option)
    {
        ByteBuffer data = ((quorum.Libraries.Game.Graphics.Integer32BitBuffer)buffer).plugin_.byteBuffer;
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
        IntBuffer buffer = ((quorum.Libraries.Game.Graphics.Integer32BitBuffer)indices).plugin_.buffer;
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

    public native void glActiveTexture ( int texture );

    public native void glAttachShader ( int program, int shader );

    public native void glBindAttribLocation ( int program, int index, String name );

    public native void glBindBuffer ( int target, int buffer );

    public native void glBindFramebuffer ( int target, int framebuffer );

    public native void glBindRenderbuffer ( int target, int renderbuffer );

    public native void glBindTexture ( int target, int texture );

    public native void glBlendColor ( float red, float green, float blue, float alpha );

    public native void glBlendEquation (  int mode  );

    public native void glBlendEquationSeparate ( int modeRGB, int modeAlpha );

    public native void glBlendFunc ( int sfactor, int dfactor );

    public native void glBlendFuncSeparate ( int srcRGB, int dstRGB, int srcAlpha, int dstAlpha );

    public native void glBufferData ( int target, int size, Buffer data, int usage );

    public native void glBufferSubData ( int target, int offset, int size, Buffer data );

    public native int glCheckFramebufferStatus ( int target );

    public void ClearScreen(int mask)
    {
        glClear(mask);
    }
    
    public native void glClear ( int mask );

    public void ClearScreenColor(float red, float green, float blue, float alpha)
    {
        glClearColor(red, green, blue, alpha);
    }
    
    public void ClearScreenColor(double red, double green, double blue, double alpha)
    {
        glClearColor((float)red, (float)green, (float)blue, (float)alpha);
    }
    
    public native void glClearColor ( float red, float green, float blue, float alpha );

    public native void glClearDepthf ( float depth );

    public native void glClearStencil ( int s );

    public native void glColorMask ( boolean red, boolean green, boolean blue, boolean alpha );

    public native void glCompileShader ( int shader );

    public native void glCompressedTexImage2D ( int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data );

    public native void glCompressedTexSubImage2D ( int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data );

    public native void glCopyTexImage2D ( int target, int level, int internalformat, int x, int y, int width, int height, int border );

    public native void glCopyTexSubImage2D ( int target, int level, int xoffset, int yoffset, int x, int y, int width, int height );

    public native int glCreateProgram (  );

    public native int glCreateShader ( int type );

    public native void glCullFace ( int mode );

    public native void glDeleteBuffers ( int n, IntBuffer buffers );

    public native void glDeleteBuffer(int buffer);

    public native void glDeleteFramebuffers ( int n, IntBuffer framebuffers );

    public native void glDeleteFramebuffer(int framebuffer);

    public native void glDeleteProgram ( int program );

    public native void glDeleteRenderbuffers ( int n, IntBuffer renderbuffers );

    public native void glDeleteRenderbuffer(int renderbuffer);

    public native void glDeleteShader ( int shader );

    public native void glDeleteTextures ( int n, IntBuffer textures );

    public native void glDeleteTexture(int texture);

    public native void glDepthFunc ( int func );

    public native void glDepthMask ( boolean flag );

    public native void glDepthRangef ( float zNear, float zFar );

    public native void glDetachShader ( int program, int shader );

    public native void glDisable ( int cap );

    public native void glDisableVertexAttribArray ( int index );

    public native void glDrawArrays ( int mode, int first, int count );

    public native void glDrawElements ( int mode, int count, int type, Buffer indices );

    public native void glDrawElements ( int mode, int count, int type, int indices );

    public native void glEnable ( int cap );

    public native void glEnableVertexAttribArray ( int index );

    public native void glFinish (  );

    public native void glFlush (  );

    public native void glFramebufferRenderbuffer ( int target, int attachment, int renderbuffertarget, int renderbuffer );

    public native void glFramebufferTexture2D ( int target, int attachment, int textarget, int texture, int level );

    public native void glFrontFace ( int mode );

    public native void glGenBuffers ( int n, IntBuffer buffers );

    public native int glGenBuffer();

    public native void glGenerateMipmap ( int target );

    public native void glGenFramebuffers ( int n, IntBuffer framebuffers );

    public native int glGenFramebuffer();

    public native void glGenRenderbuffers ( int n, IntBuffer renderbuffers );

    public native int glGenRenderbuffer();

    public native void glGenTextures ( int n, IntBuffer textures );

    public native int glGenTexture();

    public native String glGetActiveAttrib ( int program, int index, IntBuffer size, Buffer type );

    public native String glGetActiveUniform ( int program, int index, IntBuffer size, Buffer type );

    public native void glGetAttachedShaders ( int program, int maxcount, Buffer count, IntBuffer shaders );

    public native int glGetAttribLocation ( int program, String name );

    public native void glGetBooleanv ( int pname, Buffer params );

    public native void glGetBufferParameteriv ( int target, int pname, IntBuffer params );

    public native int glGetError (  );

    public native void glGetFloatv ( int pname, FloatBuffer params );

    public native void glGetFramebufferAttachmentParameteriv ( int target, int attachment, int pname, IntBuffer params );

    public native void glGetIntegerv ( int pname, IntBuffer params );

    public native void glGetProgramiv ( int program, int pname, IntBuffer params );

    public native String glGetProgramInfoLog ( int program );

    public native void glGetRenderbufferParameteriv ( int target, int pname, IntBuffer params );

    public native void glGetShaderiv ( int shader, int pname, IntBuffer params );

    public native String glGetShaderInfoLog ( int shader );

    public native void glGetShaderPrecisionFormat ( int shadertype, int precisiontype, IntBuffer range, IntBuffer precision );

    public native void glGetShaderSource ( int shader, int bufsize, Buffer length, String source );

    public native String glGetString ( int name );

    public native void glGetTexParameterfv ( int target, int pname, FloatBuffer params );

    public native void glGetTexParameteriv ( int target, int pname, IntBuffer params );

    public native void glGetUniformfv ( int program, int location, FloatBuffer params );

    public native void glGetUniformiv ( int program, int location, IntBuffer params );

    public native int glGetUniformLocation ( int program, String name );

    public native void glGetVertexAttribfv ( int index, int pname, FloatBuffer params );

    public native void glGetVertexAttribiv ( int index, int pname, IntBuffer params );

    public native void glGetVertexAttribPointerv ( int index, int pname, Buffer pointer );

    public native void glHint ( int target, int mode );

    public native boolean glIsBuffer ( int buffer );

    public native boolean glIsEnabled ( int cap );

    public native boolean glIsFramebuffer ( int framebuffer );

    public native boolean glIsProgram ( int program );

    public native boolean glIsRenderbuffer ( int renderbuffer );

    public native boolean glIsShader ( int shader );

    public native boolean glIsTexture ( int texture );

    public native void glLineWidth ( float width );

    public native void glLinkProgram ( int program );

    public void PixelStorageMode(int type, int parameter) 
    {
        glPixelStorei(type, parameter);
    }
    
    public native void glPixelStorei ( int pname, int param );

    public native void glPolygonOffset ( float factor, float units );

    public native void glReadPixels ( int x, int y, int width, int height, int format, int type, Buffer pixels );

    public native void glReleaseShaderCompiler (  );

    public native void glRenderbufferStorage ( int target, int internalformat, int width, int height );

    public native void glSampleCoverage ( float value, boolean invert );

    public native void glScissor ( int x, int y, int width, int height );

    public native void glShaderBinary ( int n, IntBuffer shaders, int binaryformat, Buffer binary, int length );

    public native void glShaderSource ( int shader, String string );

    public native void glStencilFunc ( int func, int ref, int mask );

    public native void glStencilFuncSeparate ( int face, int func, int ref, int mask );

    public native void glStencilMask ( int mask );

    public native void glStencilMaskSeparate ( int face, int mask );

    public native void glStencilOp ( int fail, int zfail, int zpass );

    public native void glStencilOpSeparate ( int face, int fail, int zfail, int zpass );

    public native void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels);

    public void SetTextureParameter(int targetTexture, int parameterType, int parameterValue)
    {
        glTexParameterf(targetTexture, parameterType, parameterValue);
    }
    
    public native void glTexParameterf ( int target, int pname, float param );

    public native void glTexParameterfv ( int target, int pname, FloatBuffer params );

    public native void glTexParameteri ( int target, int pname, int param );

    public native void glTexParameteriv ( int target, int pname, IntBuffer params );

    public native void glTexSubImage2D ( int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels );

    public native void glUniform1f ( int location, float x );

    public native void glUniform1fv ( int location, int count, FloatBuffer v );

    public native void glUniform1fv(int location, int count, float[] v, int offset);

    public native void glUniform1i ( int location, int x );

    public native void glUniform1iv ( int location, int count, IntBuffer v );

    public native void glUniform1iv(int location, int count, int[] v, int offset);

    public native void glUniform2f ( int location, float x, float y );

    public native void glUniform2fv ( int location, int count, FloatBuffer v );

    public native void glUniform2fv(int location, int count, float[] v, int offset);

    public native void glUniform2i ( int location, int x, int y );

    public native void glUniform2iv ( int location, int count, IntBuffer v );

    public native void glUniform2iv(int location, int count, int[] v, int offset);

    public native void glUniform3f ( int location, float x, float y, float z );

    public native void glUniform3fv ( int location, int count, FloatBuffer v );

    public native void glUniform3fv(int location, int count, float[] v, int offset);

    public native void glUniform3i ( int location, int x, int y, int z );

    public native void glUniform3iv ( int location, int count, IntBuffer v );

    public native void glUniform3iv(int location, int count, int[] v, int offset);

    public native void glUniform4f ( int location, float x, float y, float z, float w );

    public native void glUniform4fv ( int location, int count, FloatBuffer v );

    public native void glUniform4fv(int location, int count, float[] v, int offset);

    public native void glUniform4i ( int location, int x, int y, int z, int w );

    public native void glUniform4iv ( int location, int count, IntBuffer v );

    public native void glUniform4iv(int location, int count, int[] v, int offset);

    public native void glUniformMatrix2fv ( int location, int count, boolean transpose, FloatBuffer value );

    public native void glUniformMatrix2fv(int location, int count, boolean transpose, float[] value, int offset);

    public native void glUniformMatrix3fv ( int location, int count, boolean transpose, FloatBuffer value );

    public native void glUniformMatrix3fv(int location, int count, boolean transpose, float[] value, int offset);

    public native void glUniformMatrix4fv ( int location, int count, boolean transpose, FloatBuffer value );

    public native void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset);

    public native void glUseProgram ( int program );

    public native void glValidateProgram ( int program );

    public native void glVertexAttrib1f ( int indx, float x );

    public native void glVertexAttrib1fv ( int indx, FloatBuffer values );

    public native void glVertexAttrib2f ( int indx, float x, float y );

    public native void glVertexAttrib2fv ( int indx, FloatBuffer values );

    public native void glVertexAttrib3f ( int indx, float x, float y, float z );

    public native void glVertexAttrib3fv ( int indx, FloatBuffer values );

    public native void glVertexAttrib4f ( int indx, float x, float y, float z, float w );

    public native void glVertexAttrib4fv ( int indx, FloatBuffer values );

    public native void glVertexAttribPointer ( int indx, int size, int type, boolean normalized, int stride, Buffer ptr );

    public native void glVertexAttribPointer ( int indx, int size, int type, boolean normalized, int stride, int ptr );

    public void SetDrawingRegion(int x, int y, int width, int height)
    {
        glViewport(x, y, width, height);
    }
    
    public void glViewport(int x, int y, int width, int height) 
    {
        IOSGraphics.x = x;
        IOSGraphics.y = y;
        IOSGraphics.width = width;
        IOSGraphics.height = height;
        glViewportJni(x, y, width, height);
    }

    public native void glViewportJni ( int x, int y, int width, int height );
}