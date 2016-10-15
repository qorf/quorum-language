function plugins_quorum_Libraries_Game_Graphics_Texture_() 
{
    var glTarget;
    var glHandle = 0;
    
    var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
    
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
    
    this.SetGL20Info$quorum_integer$quorum_integer = function(target, handle) 
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
}
