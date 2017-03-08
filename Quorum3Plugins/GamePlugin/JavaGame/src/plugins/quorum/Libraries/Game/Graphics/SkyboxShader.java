/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author William Allee
 */
public class SkyboxShader 
{
    static
    {
        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
    }
    
    public static final String vertexShader = 
        "attribute vec3 position;\n" +
        "varying vec3 textureCoordinates;\n" +
        "\n" +
        "uniform mat4 projection;\n" +
        "uniform mat4 view;\n" +
        "\n" +
        "void main()\n" +
        "{\n" +
        "    gl_Position =   projection * view * vec4(position, 1.0);  \n" +
        "    texureCoordinates = position;\n" +
        "}";
    
    public static final String fragmentShader = 
        "attribute vec3 textureCoordinates;\n" +
        "varying vec4 color;\n" +
        "\n" +
        "uniform samplerCube skybox;\n" +
        "\n" +
        "void main()\n" +
        "{    \n" +
        "    gl_FragColor = texture(skybox, textureCoordinates);\n" +
        "}";
    
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
}
