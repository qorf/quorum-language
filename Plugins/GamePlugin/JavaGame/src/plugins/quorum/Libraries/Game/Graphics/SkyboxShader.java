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
        "uniform mat4 rotation;\n" +
        "\n" +
        "void main()\n" +
        "{\n" +
        "    vec4 pos = projection * view * rotation * vec4(position, 1.0);\n" +
        "    gl_Position = pos.xyww;\n" +
        "    textureCoordinates = position;\n" +
        "}";
    
    public static final String fragmentShader = 
        "#ifdef GL_ES\n" +
        "precision mediump float;\n" +
        "#endif\n" +
        "varying vec3 textureCoordinates;\n" +
        "uniform samplerCube skybox;\n" +
        "uniform float inverter;\n" +
        "\n" +
        "void main()\n" +
        "{\n" +
        "    vec3 texCoords = textureCoordinates;\n" +
        "    texCoords.x = inverter * textureCoordinates.x;\n" +
        "    gl_FragColor = textureCube(skybox, texCoords);\n" +
        "}";
    
    private FloatBuffer skyboxBuffer;
    private ByteBuffer byteBuffer;
    private int bufferHandle;
    
    private ShaderProgram program;
    private final int positionIndex;
    private final int projectionIndex;
    private final int viewIndex;
    private final int rotationIndex;
    private final int skyboxIndex;
    private final int inverterIndex;
    
    public SkyboxShader()
    {
        program = new ShaderProgram(vertexShader, fragmentShader);
        positionIndex = program.GetAttributeLocation("position");
        projectionIndex = program.FetchUniformLocation("projection", true);
        rotationIndex = program.FetchUniformLocation("rotation", true);
        viewIndex = program.FetchUniformLocation("view", true);
        skyboxIndex = program.FetchUniformLocation("skybox", true);
        inverterIndex = program.FetchUniformLocation("inverter", true);
        
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
        float[] temp = {-(float)m.row0column0,  (float)m.row1column0,  (float)m.row2column0, 0,
                        -(float)m.row0column1,  (float)m.row1column1,  (float)m.row2column1, 0,
                         (float)m.row0column2, -(float)m.row1column2, -(float)m.row2column2, 0,
                        0, 0, 0, 1};
        
        Matrix4 proj = (Matrix4)camera.GetProjectionMatrix();
        float[] projTemp = {(float)proj.row0column0, (float)proj.row1column0, (float)proj.row2column0, (float)proj.row3column0,
                            (float)proj.row0column1, (float)proj.row1column1, (float)proj.row2column1, (float)proj.row3column1,
                            (float)proj.row0column2, (float)proj.row1column2,                       0, (float)proj.row3column2,
                            (float)proj.row0column3, (float)proj.row1column3,                       0, (float)proj.row3column3};
        
        program.SetUniformMatrix4(viewIndex, temp);
        program.SetUniformMatrix4(projectionIndex, projTemp);
        program.SetUniformMatrix(rotationIndex, skybox.Get_Libraries_Game_Graphics_Skybox__transform_());
        
        program.EnableVertexAttribute(positionIndex);
        program.SetVertexAttribute(positionIndex, 3, GraphicsManager.GL_FLOAT, false, 12, 0);
        
        graphics.glActiveTexture(GraphicsManager.GL_TEXTURE0);
        program.SetUniform(skyboxIndex, 0);
        skybox.Get_Libraries_Game_Graphics_Skybox__cubeMap_().Bind();
        
        program.SetUniform(inverterIndex, (float)skybox.Get_Libraries_Game_Graphics_Skybox__inverter_());
        
        graphics.glDrawArrays(GraphicsManager.GL_TRIANGLES, 0, 36);
        
        graphics.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, 0);
        program.DisableVertexAttribute(positionIndex);
        skybox.Get_Libraries_Game_Graphics_Skybox__cubeMap_().BindToDefault();
        
        program.End();
    }
}
