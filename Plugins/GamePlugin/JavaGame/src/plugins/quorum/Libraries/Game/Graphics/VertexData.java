/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.FloatBuffer;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import quorum.Libraries.Game.Graphics.Shaders.ShaderProgram_;
//import plugins.quorum.Libraries.Game.libGDX.ShaderProgram;

import quorum.Libraries.Game.Graphics.VertexAttributes_;

/**
 * This class acts as a pseudo-interface. Each method here is meant to be 
 * overridden by the extending class. This class only exists such that the Java
 * plugin implementations of VertexData will respect the inheritance hierarchy
 * that is present in the Quorum implementations of the VertexData classes.
 * 
 * @author alleew
 */
public class VertexData 
{
    public java.lang.Object me_ = null;
    
    /** @return the number of vertices this VertexData stores */
    public int GetSize()
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** @return the number of vertices this VertedData can store */
    public int GetMaxSize()
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** @return the {@link VertexAttributes} as specified during construction. */
    public VertexAttributes_ GetAttributes()
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Binds this VertexData for rendering via glDrawArrays or glDrawElements. */
    public void Bind(ShaderProgram shader)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Binds this VertexData for rendering via glDrawArrays or glDrawElements.
     * @param locations array containing the attribute locations. */
    public void Bind(ShaderProgram shader, int[] locations)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Unbinds this VertexData. */
    public void Unbind(ShaderProgram shader)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Unbinds this VertexData.
     * @param locations array containing the attribute locations. */
    public void Unbind(ShaderProgram shader, int[] locations)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }
    
    /** Binds this VertexData for rendering via glDrawArrays or glDrawElements. */
    public void Bind(ShaderProgram_ shader)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Binds this VertexData for rendering via glDrawArrays or glDrawElements.
     * @param locations array containing the attribute locations. */
    public void Bind(ShaderProgram_ shader, int[] locations)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Unbinds this VertexData. */
    public void Unbind(ShaderProgram_ shader)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Unbinds this VertexData.
     * @param locations array containing the attribute locations. */
    public void Unbind(ShaderProgram_ shader, int[] locations)
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Manually reloads the VertexData. Use this in case of a context loss. */
    public void Reload()
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }

    /** Disposes this VertexData and all its associated OpenGL resources. */
    public void Dispose()
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }
    
    /** Returns the underlying FloatBuffer used by this VertexData. */
    public FloatBuffer GetBuffer()
    {
        throw new GameRuntimeError("This action hasn't been implemented for this class!");
    }
}
