/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.GameRuntimeError;
import quorum.Libraries.Game.Graphics.ModelBatch_;
import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Game.Graphics.Camera;
import quorum.Libraries.Game.Graphics.Renderable_;
import quorum.Libraries.Containers.Array_;

/**
 *
 * @author alleew
 */
public class ModelBatch 
{
    java.lang.Object me_ = null;
    
    protected RenderContext context = new RenderContext();
    protected ShaderProvider shaderProvider = new ShaderProvider();
    
    private boolean isRendering = false;
    
    // A convenient way to access the quorum side.
    private quorum.Libraries.Game.Graphics.ModelBatch quorumBatch;
    
    private Array_ renderables;
    
    private Camera_ camera;
    
    public void Initialize(ModelBatch_ batch, Array_ array)
    {
        quorumBatch = (quorum.Libraries.Game.Graphics.ModelBatch)batch;
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
        
        camera = cam;
    }
    
    public Camera_ GetCamera()
    {
        return camera;
    }
    
    public void Begin()
    {
        if (isRendering)
            throw new GameRuntimeError("The ModelBatch is already rendering! Call End() before calling Begin() again.");
        if (camera == null)
            throw new GameRuntimeError("The ModelBatch must have a camera set before calling Begin().");
        
        context.Begin();
        isRendering = true;
    }
    
    public void End()
    {
        Flush();
        context.End();
        isRendering = false;
    }
    
    public void Flush()
    {
        renderables.Sort();
        Shader currentShader = null;
        for (int i = 0; i < renderables.GetSize(); i++)
        {
            Renderable_ renderable = (Renderable_)renderables.Get(i);
            Renderable renderPlugin = ((quorum.Libraries.Game.Graphics.Renderable)renderable).plugin_;
            if (currentShader != renderPlugin.shader) 
            {
                if (currentShader != null)
                    currentShader.End();
                currentShader = renderPlugin.shader;
                currentShader.Begin(camera, context);
            }
            currentShader.Render(renderable);
        }
        if (currentShader != null)
            currentShader.End();
        
        renderables.Empty();
    }
    
    public void RenderNative(Renderable_ renderable)
    {
        Renderable renderPlugin = ((quorum.Libraries.Game.Graphics.Renderable)renderable).plugin_;
        renderPlugin.camera = camera;
        renderPlugin.shader = shaderProvider.GetShader(renderable);
    }
}
