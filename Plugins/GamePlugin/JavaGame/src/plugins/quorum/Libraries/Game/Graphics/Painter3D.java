/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.Graphics.Shaders.SkyboxShader;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.Graphics.Painter3D_;
import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Game.Graphics.Camera;
import quorum.Libraries.Game.Graphics.Renderable_;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Game.Graphics.Environment_;
import quorum.Libraries.Game.Graphics.Skybox_;

/**
 *
 * @author alleew
 */
public class Painter3D 
{
    public java.lang.Object me_ = null;
    
    protected RenderContext context = new RenderContext();
    protected ShaderProvider shaderProvider = new ShaderProvider();
    protected Environment_ environment = null;
    protected Skybox_ skybox = null;
    protected SkyboxShader skyboxShader = new SkyboxShader();
    
    private boolean isRendering = false;
    
    // A convenient way to access the quorum side.
    private quorum.Libraries.Game.Graphics.Painter3D quorumBatch;
    
    private Array_ renderables;
    
    /*
    Used to detect if we've rendered the Skybox yet.
    We render the skybox after all opaque objects and before the first
    blended (i.e. transparent) objects.
    */
    private boolean shouldRenderSkybox = false;
    
    public void Initialize(Painter3D_ batch, Array_ array)
    {
//        quorumBatch = (quorum.Libraries.Game.Graphics.Painter3D)batch;
        renderables = array;
    }
    
    public boolean IsRendering()
    {
        return isRendering;
    }
    
    public void SetCamera(Camera_ cam)
    {
        if (isRendering && renderables.GetSize() > 0)
            Flush();
        
//        camera = cam;
    }
    
    public Camera_ GetCamera()
    {
//        return camera;
        return null;
    }
    
    public void SetEnvironment(Environment_ environ)
    {
        environment = environ;
    }
    
    public Environment_ GetEnvironment()
    {
        return environment;
    }

    public void SetSkybox(Skybox_ box)
    {
        skybox = box;
    }
    
    public Skybox_ GetSkybox()
    {
        return skybox;
    }
    
    public void Begin()
    {
        if (isRendering)
            throw new GameRuntimeError("The Painter3D is already rendering! Call End() before calling Begin() again.");
//        if (camera == null)
//            throw new GameRuntimeError("The Painter3D must have a camera set before calling Begin().");
        
        context.Begin();
        isRendering = true;
        
        shouldRenderSkybox = (skybox != null);
    }
    
    public void End()
    {
        Flush();
        
        // Render the Skybox if we haven't already.
        if (shouldRenderSkybox)
        {   
            if (skybox.IsLoaded() == false)
                throw new GameRuntimeError("I can't render the skybox because it wasn't fully loaded. Make sure all six sides are loaded before trying to use it.");
//            skyboxShader.Render(skybox, camera);
        }
        context.End();
        isRendering = false;
    }
    
    public void Flush()
    {
        renderables.Sort();
        Shader currentShader = null;
        
        if (shouldRenderSkybox && skybox.IsLoaded() == false)
            throw new GameRuntimeError("I can't render the skybox because it wasn't fully loaded. Make sure all six sides are loaded before trying to use it.");
        
        for (int i = 0; i < renderables.GetSize(); i++)
        {
            Renderable_ renderable = (Renderable_)renderables.Get(i);
//            Renderable renderPlugin = ((quorum.Libraries.Game.Graphics.Renderable)renderable).plugin_;
            
            /*
            When we find our first blended object, render the skybox first.
            The Skybox should be in the background before any transparent
            objects are drawn, so they can blend the skybox into the rendered
            pixel fragments.
            */
            if (shouldRenderSkybox && renderable.UsesBlending())
            {
                shouldRenderSkybox = false;
                if (currentShader != null)
                    currentShader.End();
                currentShader = null;
//                skyboxShader.Render(skybox, camera);
            }
            
//            if (currentShader != renderPlugin.shader) 
            {
                if (currentShader != null)
                    currentShader.End();
//                currentShader = renderPlugin.shader;
//                currentShader.Begin(camera, context);
            }
            currentShader.Render(renderable);
        }
        if (currentShader != null) {
            currentShader.End();
        }
        
        if(!renderables.IsEmpty()) {
            renderables.Empty(false);
        }
    }
    
//    public void RenderNative(Renderable_ renderable)
//    {
//        renderable.Set_Libraries_Game_Graphics_Renderable__environment_(environment);
//        Renderable renderPlugin = ((quorum.Libraries.Game.Graphics.Renderable)renderable).plugin_;
//        renderPlugin.camera = camera;
//        renderPlugin.shader = shaderProvider.GetShader(renderable);
//    }
}
