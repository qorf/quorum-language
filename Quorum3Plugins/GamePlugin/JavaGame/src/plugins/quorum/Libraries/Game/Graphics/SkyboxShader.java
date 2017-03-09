/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Compute.Matrix4;
import quorum.Libraries.Compute.Matrix4_;
import quorum.Libraries.Game.Graphics.Camera_;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import quorum.Libraries.Game.Graphics.Skybox_;

/**
 *
 * @author William Allee
 */
public class SkyboxShader 
{
    public static final String vertexShader = 
        "attribute vec3 position;\n" +
        "varying vec3 textureCoordinates;\n" +
        "\n" +
        "uniform mat4 projection;\n" +
        "uniform mat4 view;\n" +
        "\n" +
        "void main()\n" +
        "{\n" +
//        "    gl_Position =   projection * view * vec4(position, 1.0);  \n" +
//        "    texureCoordinates = position;\n" +
        "}";
    
    public static final String fragmentShader = 
//        "attribute vec3 textureCoordinates;\n" +
        "uniform samplerCube skybox;\n" +
        "\n" +
        "void main()\n" +
        "{\n" +
//        "    gl_FragColor = textureCube(skybox, textureCoordinates);\n" +
        "}";
    
    private FloatBuffer skyboxBuffer;
    private ByteBuffer byteBuffer;
    private int bufferHandle;
    
    private ShaderProgram program;
    private int positionIndex;
    private int projectionIndex;
    private int viewIndex;
    private int skyboxIndex;
    
    private boolean Test()
    {
        return true;
    }
    
    public SkyboxShader()
    {
        program = new ShaderProgram(vertexShader, fragmentShader);
        if (Test())
            return;
        positionIndex = program.GetAttributeLocation("position");
        projectionIndex = program.FetchUniformLocation("projection", true);
        viewIndex = program.FetchUniformLocation("view", true);
        skyboxIndex = program.FetchUniformLocation("skybox", true);
        
        float[] skyboxVertices = {
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
             1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,

            -1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,

             1.0f, -1.0f, -1.0f,
             1.0f, -1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f,  1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,

            -1.0f, -1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,

            -1.0f,  1.0f, -1.0f,
             1.0f,  1.0f, -1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f, -1.0f,

            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
             1.0f, -1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
             1.0f, -1.0f,  1.0f
        };

        byteBuffer = BufferUtils.newByteBuffer(skyboxVertices.length * 4);        
        skyboxBuffer = byteBuffer.asFloatBuffer();
        skyboxBuffer.put(skyboxVertices);
        skyboxBuffer.position(0);
        byteBuffer.position(0);
        byteBuffer.limit(skyboxBuffer.limit() * 4);

        bufferHandle = GameStateManager.nativeGraphics.glGenBuffer();
        
        GameStateManager.nativeGraphics.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, bufferHandle);
        GameStateManager.nativeGraphics.glBufferData(GraphicsManager.GL_ARRAY_BUFFER, byteBuffer.limit(), byteBuffer, GraphicsManager.GL_STATIC_DRAW);
        GameStateManager.nativeGraphics.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, 0);
    }
    
    public void Render(Skybox_ skybox, Camera_ camera)
    {
        program.Begin();
        
        GraphicsManager graphics = GameStateManager.nativeGraphics;
        graphics.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, bufferHandle);
        
        Matrix4 m = (Matrix4)camera.GetViewMatrix();
        float[] temp = {(float)m.row0column0, (float)m.row1column0, (float)m.row2column0, 0,
                        (float)m.row0column1, (float)m.row1column1, (float)m.row2column1, 0,
                        (float)m.row0column2, (float)m.row1column2, (float)m.row2column2, 0,
                        0, 0, 0, 1};
        
        program.SetUniformMatrix4(viewIndex, temp);
        program.SetUniformMatrix(projectionIndex, camera.GetProjectionMatrix());

        program.EnableVertexAttribute(positionIndex);
        program.SetVertexAttribute(positionIndex, 3, GraphicsManager.GL_FLOAT, false, 12, 0);
        
        graphics.glActiveTexture(GraphicsManager.GL_TEXTURE0);
        program.SetUniform(skyboxIndex, 0);
        skybox.Get_Libraries_Game_Graphics_Skybox__cubeMap_().Bind();
        
        graphics.glDrawArrays(GraphicsManager.GL_TRIANGLES, 0, 36);
        
        graphics.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, 0);
        program.DisableVertexAttribute(positionIndex);
        skybox.Get_Libraries_Game_Graphics_Skybox__cubeMap_().BindToDefault();
        
        program.End();
    }
    
    private float[] ConvertMatrix4ToArray(Matrix4_ matrix)
    {
        Matrix4 m = (Matrix4)matrix;
        // OpenGL expects the matrix to be stored in column-major format.
        float[] temp = {(float)m.row0column0, (float)m.row1column0, (float)m.row2column0, (float)m.row3column0,
                        (float)m.row0column1, (float)m.row1column1, (float)m.row2column1, (float)m.row3column1,
                        (float)m.row0column2, (float)m.row1column2, (float)m.row2column2, (float)m.row3column2,
                        (float)m.row0column3, (float)m.row1column3, (float)m.row2column3, (float)m.row3column3};
        return temp;
    }
}
