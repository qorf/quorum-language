function plugins_quorum_Libraries_Game_Graphics_Texture_(quorumTexture) 
{
    var me_ = quorumTexture;
    var glTarget;
    var glHandle = 0;
    
    var listeners = [];
    
    var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
    
    /*
    The color that should be used to render this texture by the Painter2D when
    using the font shader. If the color is null, then the font shader will not be
    used.
    */
    this.fontColor = null;

    this.AddManagedTexture = function() 
    {
        // Not yet implemented.
    };
    
    this.Bind = function(unit) 
    {
        if (unit !== undefined && unit !== null)
            graphics.glActiveTexture(graphics.gl.TEXTURE0 + unit);
        
        graphics.glBindTexture(glTarget, glHandle);
    };
    
    this.BindToDefault = function() 
    {
        graphics.glBindTexture(glTarget, null);
    };
    
    this.CreateGLHandle = function() 
    {
        return graphics.glGenTexture();
    };
    
    this.SetGraphicsInfo$quorum_integer$quorum_integer = function(target, handle) 
    {
        glTarget = target;
        glHandle = handle;
    };
    
    this.GetGLTarget = function() 
    {
        return glTarget;
    };
    
    this.GetGLHandle = function() 
    {
        return glHandle;
    };
    
    this.Dispose = function() 
    {
        if (glHandle !== 0)
        {
            graphics.glDeleteTexture(glHandle);
            glHandle = 0;
        }
    };
    
    /*
    Used to set the color to apply to this texture if it is being rendered by
    the Painter2D's font shader.
    */
    this.SetFontColor = function(color)
    {
        this.fontColor = color;
    };
    
    /*
    Used to retrieve the color that should be applied to this texture if it is
    being rendered by the Painter2D's font shader.
    */
    this.GetFontColor = function()
    {
        return this.fontColor;
    };
    
    this.AddTextureLoadListener = function(listener)
    {
        listeners.push(listener);
    };
    
    this.AlertTextureLoadListeners = function()
    {
        for (var i = 0; i < listeners.length; i++)
        {
            listeners[i].TextureLoaded(me_);
        }
    };
    
    this.AddReloadableTexture = function()
    {
        // Do nothing, this isn't necessary on the web.
    };
    
    this.RemoveReloadableTexture = function()
    {
        // Do nothing, this isn't necessary on the web.
    };
}
