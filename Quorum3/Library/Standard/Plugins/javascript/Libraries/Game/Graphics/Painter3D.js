function plugins_quorum_Libraries_Game_Graphics_Painter3D_() 
{
    var context = new plugins_quorum_Libraries_Game_Graphics_RenderContext_();
    var shaderProvider = new plugins_quorum_Libraries_Game_Graphics_ShaderProvider_();
    var environment;
    var isRendering = false;
    
    var quorumBatch;
    var renderables;
    var camera;
    
    this.Initialize$quorum_Libraries_Game_Graphics_Painter3D$quorum_Libraries_Containers_Array = function(batch, render) 
    {
        quorumBatch = batch;
        renderables = render;
    };
    
    this.IsRendering = function() 
    {
        return isRendering;
    };
    
    this.SetCamera$quorum_Libraries_Game_Graphics_Camera = function(cam) 
    {
        if (isRendering && renderables.GetSize() > 0)
            this.Flush();
        
        camera = cam;
    };
    
    this.GetCamera = function() 
    {
        return camera;
    };
    
    this.SetEnvironment$quorum_Libraries_Game_Graphics_Environment = function(environ) 
    {
        environment = environ;
    };
    
    this.GetEnvironment = function() 
    {
        return environment;
    };

    this.Begin = function() 
    {
        if (isRendering)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("The Painter3D is already rendering! Call End() before calling Begin() again.");
            throw exceptionInstance_;
        }
        if (camera === null || camera === undefined)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("The Painter3D must have a camera set before calling Begin().");
            throw exceptionInstance_;
        }
        
        context.Begin();
        isRendering = true;
    };
    
    this.End = function() 
    {
        this.Flush();
        context.End();
        isRendering = false;
    };
    
    this.Flush = function()
    {
        renderables.Sort();
        var currentShader = null;
        var renderable;
        var renderPlugin;
        
        for (var i = 0; i < renderables.GetSize(); i++)
        {
            renderable = renderables.Get(i);
            renderPlugin = renderable.plugin_;
            if (currentShader !== renderPlugin.shader)
            {
                if (currentShader !== null && currentShader !== undefined)
                    currentShader.End();
                currentShader = renderPlugin.shader;
                currentShader.Begin(camera, context);
            }
            currentShader.Render(renderable);
        }
        if (currentShader !== null && currentShader !== undefined)
            currentShader.End();
        
        renderables.Empty();
    };
    
    this.RenderNative$quorum_Libraries_Game_Graphics_Renderable = function(renderable) 
    {
        renderable.Set_Libraries_Game_Graphics_Renderable__environment_(environment);
        var renderPlugin = renderable.plugin_;
        renderPlugin.camera = camera;
        renderPlugin.shader = shaderProvider.GetShader(renderable);
    };
}
