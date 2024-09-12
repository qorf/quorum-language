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
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Mesh;

import plugins.quorum.Libraries.Game.Graphics.OpenGL.OpenGLManager;
import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Compute.Vector2;
import quorum.Libraries.Compute.Vector3;
import quorum.Libraries.Compute.Matrix3_;
import quorum.Libraries.Compute.Matrix4_;

import plugins.quorum.Libraries.Game.GameFile;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.GameRuntimeError;

// libGDX dependencies to eliminate:
import plugins.quorum.Libraries.Game.libGDX.Array;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import plugins.quorum.Libraries.Game.libGDX.ObjectIntMap;

/** 
 * This is an adaptation of the libGDX ShaderProgram class. It is used to store
 * and indirectly access a vertex and fragment shader pair. The original class
 * was written by mzechner, and the adaptation was done by William Allee. The
 * original comments are left below.
 * 
 * <p>
 * A shader program encapsulates a vertex and fragment shader pair linked to form a shader program useable with OpenGL ES 2.0.
 * </p>
 * 
 * <p>
 * After construction a ShaderProgram can be used to draw {@link Mesh}. To make the GPU use a specific ShaderProgram the programs
 * {@link ShaderProgram#begin()} method must be used which effectively binds the program.
 * </p>
 * 
 * <p>
 * When a ShaderProgram is bound one can set uniforms, vertex attributes and attributes as needed via the respective methods.
 * </p>
 * 
 * <p>
 * A ShaderProgram can be unbound with a call to {@link ShaderProgram#end()}
 * </p>
 * 
 * <p>
 * A ShaderProgram must be disposed via a call to {@link ShaderProgram#dispose()} when it is no longer needed
 * </p>
 * 
 * <p>
 * ShaderPrograms are managed. In case the OpenGL context is lost all shaders get invalidated and have to be reloaded. This
 * happens on Android when a user switches to another application or receives an incoming call. Managed ShaderPrograms are
 * automatically reloaded when the OpenGL context is recreated so you don't have to do this manually.
 * </p>
 * 
 * @author mzechner */
public class ShaderProgram 
{
    /** default name for position attributes **/
    public static final String POSITION_ATTRIBUTE = "a_position";
    /** default name for normal attributes **/
    public static final String NORMAL_ATTRIBUTE = "a_normal";
    /** default name for color attributes **/
    public static final String COLOR_ATTRIBUTE = "a_color";
    /** default name for texcoords attributes, append texture unit number **/
    public static final String TEXCOORD_ATTRIBUTE = "a_texCoord";
    /** default name for tangent attribute **/
    public static final String TANGENT_ATTRIBUTE = "a_tangent";
    /** default name for binormal attribute **/
    public static final String BINORMAL_ATTRIBUTE = "a_binormal";

    /** flag indicating whether attributes & uniforms must be present at all times **/
    public static boolean pedantic = true;

    /** the list of currently available shaders **/
    private final static Array<ShaderProgram> RELOADABLE_SHADERS = new Array<ShaderProgram>();

    /** the log **/
    private String log = "";

    /** whether this program compiled successfully **/
    private boolean isCompiled;

    /** uniform lookup **/
    private final ObjectIntMap<String> uniforms = new ObjectIntMap<String>();

    /** uniform types **/
    private final ObjectIntMap<String> uniformTypes = new ObjectIntMap<String>();

    /** uniform sizes **/
    private final ObjectIntMap<String> uniformSizes = new ObjectIntMap<String>();

    /** uniform names **/
    private String[] uniformNames;

    /** attribute lookup **/
    private final ObjectIntMap<String> attributes = new ObjectIntMap<String>();

    /** attribute types **/
    private final ObjectIntMap<String> attributeTypes = new ObjectIntMap<String>();

    /** attribute sizes **/
    private final ObjectIntMap<String> attributeSizes = new ObjectIntMap<String>();

    /** attribute names **/
    private String[] attributeNames;

    /** program handle **/
    private int program;

    /** vertex shader handle **/
    private int vertexShaderHandle;

    /** fragment shader handle **/
    private int fragmentShaderHandle;

    /** matrix float buffer **/
    private final FloatBuffer matrix;

    /** vertex shader source **/
    private final String vertexShaderSource;

    /** fragment shader source **/
    private final String fragmentShaderSource;

    /** whether this shader was invalidated **/
    private boolean invalidated;

    /** reference count **/
    private int refCount = 0;
    
    final static IntBuffer intbuf = BufferUtils.newIntBuffer(1);
    
    IntBuffer params = BufferUtils.newIntBuffer(1);
    IntBuffer type = BufferUtils.newIntBuffer(1);
    private static float[] floatsFromMatrix3 = new float[9];
    private static float[] floatsFromMatrix4 = new float[16];

    /** Constructs a new ShaderProgram and immediately compiles it.
     * 
     * @param vertexShader the vertex shader
     * @param fragmentShader the fragment shader */
    public ShaderProgram(String vertexShader, String fragmentShader) 
    {
        if (vertexShader == null)
            throw new IllegalArgumentException("I can not create a ShaderProgram with an undefined vertex shader!");
        if (fragmentShader == null)
            throw new IllegalArgumentException("I can not create a ShaderProgram with an undefined fragment shader!");

        this.vertexShaderSource = vertexShader;
        this.fragmentShaderSource = fragmentShader;
        this.matrix = BufferUtils.newFloatBuffer(16);

        CompileShaders(vertexShader, fragmentShader);
        if (IsCompiled()) 
        {
            FetchAttributes();
            FetchUniforms();
            AddManagedShader(GameStateManager.application, this);
        }
        if (!IsCompiled())
            throw new GameRuntimeError("Failed to compile shader: " + GetLog());
    }

    public ShaderProgram(GameFile vertexShader, GameFile fragmentShader) 
    {
        this(vertexShader.ReadString(), fragmentShader.ReadString());
    }

    /** Loads and compiles the shaders, creates a new program and links the shaders.
     * 
     * @param vertexShader
     * @param fragmentShader */
    private void CompileShaders(String vertexShader, String fragmentShader) 
    {
        vertexShaderHandle = LoadShader(OpenGLManager.GL_VERTEX_SHADER, vertexShader);
        fragmentShaderHandle = LoadShader(OpenGLManager.GL_FRAGMENT_SHADER, fragmentShader);

        if (vertexShaderHandle == -1 || fragmentShaderHandle == -1) 
        {
            isCompiled = false;
            return;
        }

        program = LinkProgram();
        if (program == -1) 
        {
            isCompiled = false;
            return;
        }

        isCompiled = true;
    }

    private int LoadShader(int type, String source) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        IntBuffer intbuf = BufferUtils.newIntBuffer(1);

        int shader = gl.glCreateShader(type);
        if (shader == 0) return -1;

        gl.glShaderSource(shader, source);
        gl.glCompileShader(shader);
        gl.glGetShaderiv(shader, OpenGLManager.GL_COMPILE_STATUS, intbuf);

        int compiled = intbuf.get(0);
        if (compiled == 0) 
        {
            GameStateManager.nativeGraphics.glGetProgramiv(program, OpenGLManager.GL_INFO_LOG_LENGTH, intbuf);
            int infoLogLength = intbuf.get(0);
            if (infoLogLength > 1) 
            {
                String infoLog = gl.glGetShaderInfoLog(shader);
                log += infoLog;
                log += "Version is: " + GameStateManager.nativeGraphics.glGetString(OpenGLManager.GL_VERSION);
            }
            return -1;
        }

        return shader;
    }

    private int LinkProgram() 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        int program = gl.glCreateProgram();
        if (program == 0) return -1;

        gl.glAttachShader(program, vertexShaderHandle);
        gl.glAttachShader(program, fragmentShaderHandle);
        gl.glLinkProgram(program);

        ByteBuffer tmp = ByteBuffer.allocateDirect(4);
        tmp.order(ByteOrder.nativeOrder());
        IntBuffer intbuf = tmp.asIntBuffer();

        gl.glGetProgramiv(program, OpenGLManager.GL_LINK_STATUS, intbuf);
        int linked = intbuf.get(0);
        if (linked == 0) 
        {
            GameStateManager.nativeGraphics.glGetProgramiv(program, OpenGLManager.GL_INFO_LOG_LENGTH, intbuf);
            int infoLogLength = intbuf.get(0);
            if (infoLogLength > 1) 
            {
                log = GameStateManager.nativeGraphics.glGetProgramInfoLog(program);
            }
            return -1;
        }

        return program;
    }

    /** @return the log info for the shader compilation and program linking stage. The shader needs to be bound for this method to
     *         have an effect. */
    public String GetLog() 
    {
        if (isCompiled) 
        {
            GameStateManager.nativeGraphics.glGetProgramiv(program, OpenGLManager.GL_INFO_LOG_LENGTH, intbuf);
            int infoLogLength = intbuf.get(0);
            if (infoLogLength > 1) 
            {
                log = GameStateManager.nativeGraphics.glGetProgramInfoLog(program);
            }
            return log;
        }
        else 
        {
            return log;
        }
    }

    /** @return whether this ShaderProgram compiled successfully. */
    public boolean IsCompiled() 
    {
        return isCompiled;
    }

    private int FetchAttributeLocation(String name) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        // -2 == not yet cached
        // -1 == cached but not found
        int location;
        if ((location = attributes.get(name, -2)) == -2) 
        {
            location = gl.glGetAttribLocation(program, name);
            attributes.put(name, location);
        }
        return location;
    }

    private int FetchUniformLocation(String name) 
    {
        return FetchUniformLocation(name, pedantic);
    }

    public int FetchUniformLocation(String name, boolean pedantic) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        // -2 == not yet cached
        // -1 == cached but not found
        int location;
        if ((location = uniforms.get(name, -2)) == -2) 
        {
            location = gl.glGetUniformLocation(program, name);
            if (location == -1 && pedantic)
                throw new IllegalArgumentException("I couldn't find a uniform with the name '" + name + "' in the shader!");
            
            uniforms.put(name, location);
        }
        return location;
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value the value */
    public void SetUniform(String name, int value) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform1i(location, value);
    }

    public void SetUniform(int location, int value) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform1i(location, value);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value */
    public void SetUniform(String name, int value1, int value2) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform2i(location, value1, value2);
    }

    public void SetUniform(int location, int value1, int value2) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform2i(location, value1, value2);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value */
    public void SetUniform(String name, int value1, int value2, int value3) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform3i(location, value1, value2, value3);
    }

    public void SetUniform(int location, int value1, int value2, int value3) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform3i(location, value1, value2, value3);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value
     * @param value4 the fourth value */
    public void SetUniform(String name, int value1, int value2, int value3, int value4) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform4i(location, value1, value2, value3, value4);
    }

    public void SetUniform(int location, int value1, int value2, int value3, int value4) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform4i(location, value1, value2, value3, value4);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value the value */
    public void SetUniform(String name, float value) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform1f(location, value);
    }

    public void SetUniform(int location, float value) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform1f(location, value);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value */
    public void SetUniform(String name, float value1, float value2) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform2f(location, value1, value2);
    }

    public void SetUniform(int location, float value1, float value2) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform2f(location, value1, value2);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value */
    public void SetUniform(String name, float value1, float value2, float value3) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform3f(location, value1, value2, value3);
    }

    public void SetUniform(int location, float value1, float value2, float value3) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform3f(location, value1, value2, value3);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value
     * @param value4 the fourth value */
    public void SetUniform(String name, float value1, float value2, float value3, float value4)
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform4f(location, value1, value2, value3, value4);
    }

    public void SetUniform(int location, float value1, float value2, float value3, float value4) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform4f(location, value1, value2, value3, value4);
    }

    public void SetUniformVector1(String name, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform1fv(location, length, values, offset);
    }

    public void SetUniformVector1(int location, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform1fv(location, length, values, offset);
    }

    public void SetUniformVector2(String name, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform2fv(location, length / 2, values, offset);
    }

    public void SetUniformVector2(int location, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform2fv(location, length / 2, values, offset);
    }

    public void SetUniformVector3(String name, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform3fv(location, length / 3, values, offset);
    }

    public void SetUniformVector3(int location, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform3fv(location, length / 3, values, offset);
    }

    public void SetUniformVector4(String name, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchUniformLocation(name);
        gl.glUniform4fv(location, length / 4, values, offset);
    }

    public void SetUniformVector4(int location, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniform4fv(location, length / 4, values, offset);
    }

    /** Sets the uniform matrix with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param matrix the matrix */
    public void SetUniformMatrix(String name, Matrix4_ matrix) 
    {
        SetUniformMatrix(name, matrix, false);
    }

    /** Sets the uniform matrix with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param matrix the matrix
     * @param transpose whether the matrix should be transposed */
    public void SetUniformMatrix(String name, Matrix4_ matrix, boolean transpose) 
    {
        SetUniformMatrix(FetchUniformLocation(name), matrix, transpose); 
    }

    public void SetUniformMatrix(int location, Matrix4_ matrix) 
    {
        SetUniformMatrix(location, matrix, false);
    }

    public void SetUniformMatrix(int location, Matrix4_ matrix, boolean transpose) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        MatrixToArray(matrix);
        gl.glUniformMatrix4fv(location, 1, transpose, floatsFromMatrix4, 0);
    }

    /** Sets the uniform matrix with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param matrix the matrix */
    public void SetUniformMatrix(String name, Matrix3_ matrix) 
    {
        SetUniformMatrix(name, matrix, false);
    }

    /** Sets the uniform matrix with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param matrix the matrix
     * @param transpose whether the uniform matrix should be transposed */
    public void SetUniformMatrix (String name, Matrix3_ matrix, boolean transpose) 
    {
        SetUniformMatrix(FetchUniformLocation(name), matrix, transpose);
    }

    public void SetUniformMatrix(int location, Matrix3_ matrix) 
    {
        SetUniformMatrix(location, matrix, false);
    }

    public void SetUniformMatrix(int location, Matrix3_ matrix, boolean transpose) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        MatrixToArray(matrix);
        gl.glUniformMatrix3fv(location, 1, transpose, floatsFromMatrix3, 0);
    }
        
    /*
    A custom implementation of this method that uses the matrix's value
    array directly, rather than requiring the Matrix object.
    */
    public void SetUniformMatrix4(int location, float[] matrix) 
    {
        SetUniformMatrix4(location, matrix, false);
    }

    /*
    A custom implementation of this method that uses the matrix's value
    array directly, rather than requiring the Matrix object.
    */
    public void SetUniformMatrix4(int location, float[] matrix, boolean transpose) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniformMatrix4fv(location, 1, transpose, matrix, 0);
    }
        
    /*
    A custom implementation of this method that uses the matrix's value
    array directly, rather than requiring the Matrix object.
    */
    public void SetUniformMatrix3(int location, float[] matrix) 
    {
        SetUniformMatrix3(location, matrix, false);
    }

    /*
    A custom implementation of this method that uses the matrix's value
    array directly, rather than requiring the Matrix object.
    */
    public void SetUniformMatrix3(int location, float[] matrix, boolean transpose) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniformMatrix3fv(location, 1, transpose, matrix, 0);
    }

    /** Sets an array of uniform matrices with the given name. Throws an IllegalArgumentException in case it is not called in
     * between a {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param buffer buffer containing the matrix data
     * @param transpose whether the uniform matrix should be transposed */
    public void SetUniformMatrix3(String name, FloatBuffer buffer, int count, boolean transpose) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        buffer.position(0);
        int location = FetchUniformLocation(name);
        gl.glUniformMatrix3fv(location, count, transpose, buffer);
    }

    /** Sets an array of uniform matrices with the given name. Throws an IllegalArgumentException in case it is not called in
     * between a {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param buffer buffer containing the matrix data
     * @param transpose whether the uniform matrix should be transposed */
    public void SetUniformMatrix4(String name, FloatBuffer buffer, int count, boolean transpose) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        buffer.position(0);
        int location = FetchUniformLocation(name);
        gl.glUniformMatrix4fv(location, count, transpose, buffer);
    }

    public void SetUniformMatrix4(int location, float[] values, int offset, int length) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUniformMatrix4fv(location, length / 16, false, values, offset);
    }

    public void SetUniformMatrix4(String name, float[] values, int offset, int length) 
    {
        SetUniformMatrix4(FetchUniformLocation(name), values, offset, length);
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param values x and y as the first and second values respectively */
    public void SetUniformVector2(String name, Vector2 values) 
    {
        SetUniform(name, (float)values.GetX(), (float)values.GetY());
    }

    public void SetUniformVector2(int location, Vector2 values) 
    {
        SetUniform(location, (float)values.GetX(), (float)values.GetY());
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param values x, y and z as the first, second and third values respectively */
    public void SetUniformVector3(String name, Vector3 values) 
    {
        SetUniform(name, (float)values.GetX(), (float)values.GetY(), (float)values.GetZ());
    }

    public void SetUniform(int location, Vector3 values) 
    {
        SetUniform(location, (float)values.GetX(), (float)values.GetY(), (float)values.GetZ());
    }

    /** Sets the uniform with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the name of the uniform
     * @param values r, g, b and a as the first through fourth values respectively */
    public void SetUniform(String name, Color_ values) 
    {
        SetUniform(name, (float)values.GetRed(), (float)values.GetGreen(), (float)values.GetBlue(), (float)values.GetAlpha());
    }

    public void SetUniform(int location, Color_ values) 
    {
        SetUniform(location, (float)values.GetRed(), (float)values.GetGreen(), (float)values.GetBlue(), (float)values.GetAlpha());
    }

    public void SetAttribute(String name, Color_ values) 
    {
        SetAttribute(name, (float)values.GetRed(), (float)values.GetGreen(), (float)values.GetBlue(), (float)values.GetAlpha());
    }

    /** Sets the vertex attribute with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the attribute name
     * @param size the number of components, must be >= 1 and <= 4
     * @param type the type, must be one of GL20.GL_BYTE, GL20.GL_UNSIGNED_BYTE, GL20.GL_SHORT,
     *           GL20.GL_UNSIGNED_SHORT,GL20.GL_FIXED, or GL20.GL_FLOAT. GL_FIXED will not work on the desktop
     * @param normalize whether fixed point data should be normalized. Will not work on the desktop
     * @param stride the stride in bytes between successive attributes
     * @param buffer the buffer containing the vertex attributes. */
    public void SetVertexAttribute(String name, int size, int type, boolean normalize, int stride, Buffer buffer) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchAttributeLocation(name);
        if (location == -1) return;
        gl.glVertexAttribPointer(location, size, type, normalize, stride, buffer);
    }

    public void SetVertexAttribute(int location, int size, int type, boolean normalize, int stride, Buffer buffer) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();

        gl.glVertexAttribPointer(location, size, type, normalize, stride, buffer);
    }

    /** Sets the vertex attribute with the given name. Throws an IllegalArgumentException in case it is not called in between a
     * {@link #begin()}/{@link #end()} block.
     * 
     * @param name the attribute name
     * @param size the number of components, must be >= 1 and <= 4
     * @param type the type, must be one of GL20.GL_BYTE, GL20.GL_UNSIGNED_BYTE, GL20.GL_SHORT,
     *           GL20.GL_UNSIGNED_SHORT,GL20.GL_FIXED, or GL20.GL_FLOAT. GL_FIXED will not work on the desktop
     * @param normalize whether fixed point data should be normalized. Will not work on the desktop
     * @param stride the stride in bytes between successive attributes
     * @param offset byte offset into the vertex buffer object bound to GL20.GL_ARRAY_BUFFER. */
    public void SetVertexAttribute(String name, int size, int type, boolean normalize, int stride, int offset) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchAttributeLocation(name);
        if (location == -1) return;
        gl.glVertexAttribPointer(location, size, type, normalize, stride, offset);
    }

    public void SetVertexAttribute(int location, int size, int type, boolean normalize, int stride, int offset) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glVertexAttribPointer(location, size, type, normalize, stride, offset);
    }

    /** Makes OpenGL ES 2.0 use this vertex and fragment shader pair. When you are done with this shader you have to call
     * {@link ShaderProgram#end()}. */
    public void Begin() 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glUseProgram(program);
    }

    /** Disables this shader. Must be called when one is done with the shader. Don't mix it with dispose, that will release the
     * shader resources. */
    public void End() 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        gl.glUseProgram(0);
    }

    /** Disposes all resources associated with this shader. Must be called when the shader is no longer used. */
    public void Dispose() 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        gl.glUseProgram(0);
        gl.glDeleteShader(vertexShaderHandle);
        gl.glDeleteShader(fragmentShaderHandle);
        gl.glDeleteProgram(program);
        RELOADABLE_SHADERS.removeValue(this, true);
    }

    /** Disables the vertex attribute with the given name
     * 
     * @param name the vertex attribute name */
    public void DisableVertexAttribute (String name) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchAttributeLocation(name);
        if (location == -1) return;
        gl.glDisableVertexAttribArray(location);
    }

    public void DisableVertexAttribute(int location) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glDisableVertexAttribArray(location);
    }

    /** Enables the vertex attribute with the given name
     * 
     * @param name the vertex attribute name */
    public void EnableVertexAttribute(String name) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        int location = FetchAttributeLocation(name);
        if (location == -1) return;
        gl.glEnableVertexAttribArray(location);
    }

    public void EnableVertexAttribute (int location) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        CheckManaged();
        gl.glEnableVertexAttribArray(location);
    }

    private void CheckManaged() 
    {
        if (invalidated) 
        {
            CompileShaders(vertexShaderSource, fragmentShaderSource);
            invalidated = false;
        }
    }

    private void AddManagedShader(quorum.Libraries.Game.Application_ app, ShaderProgram shaderProgram) 
    {
        if (app == null)
            throw new GameRuntimeError("The ShaderProgram can't associate a shader with an undefined application.");
        RELOADABLE_SHADERS.add(shaderProgram);
    }

    /* 
    Reloads all shaders that are in use. Used after OpenGL context loss to 
    regenerate information that's been invalidated.  
    */
    public static void ReloadShaders() 
    {
        if (GameStateManager.nativeGraphics == null) 
            return;

        for (int i = 0; i < RELOADABLE_SHADERS.size; i++) 
        {
            RELOADABLE_SHADERS.get(i).invalidated = true;
            RELOADABLE_SHADERS.get(i).CheckManaged();
        }
    }

    public static void ClearAllShaderPrograms(quorum.Libraries.Game.Application_ app) 
    {
        RELOADABLE_SHADERS.clear();
    }

    /** Sets the given attribute
     * 
     * @param name the name of the attribute
     * @param value1 the first value
     * @param value2 the second value
     * @param value3 the third value
     * @param value4 the fourth value */
    public void SetAttribute(String name, float value1, float value2, float value3, float value4) 
    {
        OpenGLManager gl = GameStateManager.nativeGraphics;
        int location = FetchAttributeLocation(name);
        gl.glVertexAttrib4f(location, value1, value2, value3, value4);
    }

    private void FetchUniforms() 
    {
        params.clear();
        GameStateManager.nativeGraphics.glGetProgramiv(program, OpenGLManager.GL_ACTIVE_UNIFORMS, params);
        int numUniforms = params.get(0);

        uniformNames = new String[numUniforms];

        for (int i = 0; i < numUniforms; i++) 
        {
            params.clear();
            params.put(0, 1);
            type.clear();
            String name = GameStateManager.nativeGraphics.glGetActiveUniform(program, i, params, type);
            int location = GameStateManager.nativeGraphics.glGetUniformLocation(program, name);
            uniforms.put(name, location);
            uniformTypes.put(name, type.get(0));
            uniformSizes.put(name, params.get(0));
            uniformNames[i] = name;
        }
    }

    private void FetchAttributes() 
    {
        params.clear();
        GameStateManager.nativeGraphics.glGetProgramiv(program, OpenGLManager.GL_ACTIVE_ATTRIBUTES, params);
        int numAttributes = params.get(0);

        attributeNames = new String[numAttributes];

        for (int i = 0; i < numAttributes; i++) 
        {
            params.clear();
            params.put(0, 1);
            type.clear();
            String name = GameStateManager.nativeGraphics.glGetActiveAttrib(program, i, params, type);
            int location = GameStateManager.nativeGraphics.glGetAttribLocation(program, name);
            attributes.put(name, location);
            attributeTypes.put(name, type.get(0));
            attributeSizes.put(name, params.get(0));
            attributeNames[i] = name;
        }
    }

    /** @param name the name of the attribute
     * @return whether the attribute is available in the shader */
    public boolean HasAttribute (String name) 
    {
        return attributes.containsKey(name);
    }

    /** @param name the name of the attribute
     * @return the type of the attribute, one of {@link GL20#GL_FLOAT}, {@link GL20#GL_FLOAT_VEC2} etc. */
    public int GetAttributeType(String name) 
    {
        return attributeTypes.get(name, 0);
    }

    /** @param name the name of the attribute
     * @return the location of the attribute or -1. */
    public int GetAttributeLocation(String name) 
    {
        return attributes.get(name, -1);
    }

    /** @param name the name of the attribute
     * @return the size of the attribute or 0. */
    public int GetAttributeSize(String name) 
    {
        return attributeSizes.get(name, 0);
    }

    /** @param name the name of the uniform
     * @return whether the uniform is available in the shader */
    public boolean HasUniform(String name) 
    {
        return uniforms.containsKey(name);
    }

    /** @param name the name of the uniform
     * @return the type of the uniform, one of {@link GL20#GL_FLOAT}, {@link GL20#GL_FLOAT_VEC2} etc. */
    public int GetUniformType(String name) 
    {
        return uniformTypes.get(name, 0);
    }

    /** @param name the name of the uniform
     * @return the location of the uniform or -1. */
    public int GetUniformLocation(String name) 
    {
        return uniforms.get(name, -1);
    }

    /** @param name the name of the uniform
     * @return the size of the uniform or 0. */
    public int GetUniformSize(String name) 
    {
        return uniformSizes.get(name, 0);
    }

    /** @return the attributes */
    public String[] GetAttributes() 
    {
        return attributeNames;
    }

    /** @return the uniforms */
    public String[] GetUniforms() 
    {
        return uniformNames;
    }

    /** @return the source of the vertex shader */
    public String GetVertexShaderSource() 
    {
        return vertexShaderSource;
    }

    /** @return the source of the fragment shader */
    public String GetFragmentShaderSource() 
    {
        return fragmentShaderSource;
    }
        
    private static void MatrixToArray(Matrix3_ mat)
    {
        floatsFromMatrix3[0] = (float)mat.Get_Libraries_Compute_Matrix3__row0column0_();
        floatsFromMatrix3[1] = (float)mat.Get_Libraries_Compute_Matrix3__row1column0_();
        floatsFromMatrix3[2] = (float)mat.Get_Libraries_Compute_Matrix3__row2column0_();
        floatsFromMatrix3[3] = (float)mat.Get_Libraries_Compute_Matrix3__row0column1_();
        floatsFromMatrix3[4] = (float)mat.Get_Libraries_Compute_Matrix3__row1column1_();
        floatsFromMatrix3[5] = (float)mat.Get_Libraries_Compute_Matrix3__row2column1_();
        floatsFromMatrix3[6] = (float)mat.Get_Libraries_Compute_Matrix3__row0column2_();
        floatsFromMatrix3[7] = (float)mat.Get_Libraries_Compute_Matrix3__row1column2_();
        floatsFromMatrix3[8] = (float)mat.Get_Libraries_Compute_Matrix3__row2column2_();
    }
        
    private static void MatrixToArray(Matrix4_ mat)
    {
        floatsFromMatrix4[0] = (float)mat.Get_Libraries_Compute_Matrix4__row0column0_();
        floatsFromMatrix4[1] = (float)mat.Get_Libraries_Compute_Matrix4__row1column0_();
        floatsFromMatrix4[2] = (float)mat.Get_Libraries_Compute_Matrix4__row2column0_();
        floatsFromMatrix4[3] = (float)mat.Get_Libraries_Compute_Matrix4__row3column0_();
        floatsFromMatrix4[4] = (float)mat.Get_Libraries_Compute_Matrix4__row0column1_();
        floatsFromMatrix4[5] = (float)mat.Get_Libraries_Compute_Matrix4__row1column1_();
        floatsFromMatrix4[6] = (float)mat.Get_Libraries_Compute_Matrix4__row2column1_();
        floatsFromMatrix4[7] = (float)mat.Get_Libraries_Compute_Matrix4__row3column1_();
        floatsFromMatrix4[8] = (float)mat.Get_Libraries_Compute_Matrix4__row0column2_();
        floatsFromMatrix4[9] = (float)mat.Get_Libraries_Compute_Matrix4__row1column2_();
        floatsFromMatrix4[10] = (float)mat.Get_Libraries_Compute_Matrix4__row2column2_();
        floatsFromMatrix4[11] = (float)mat.Get_Libraries_Compute_Matrix4__row3column2_();
        floatsFromMatrix4[12] = (float)mat.Get_Libraries_Compute_Matrix4__row0column3_();
        floatsFromMatrix4[13] = (float)mat.Get_Libraries_Compute_Matrix4__row1column3_();
        floatsFromMatrix4[14] = (float)mat.Get_Libraries_Compute_Matrix4__row2column3_();
        floatsFromMatrix4[15] = (float)mat.Get_Libraries_Compute_Matrix4__row3column3_();
    }
}
