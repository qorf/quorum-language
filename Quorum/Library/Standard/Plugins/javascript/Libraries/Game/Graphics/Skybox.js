function plugins_quorum_Libraries_Game_Graphics_Skybox_(quorumBox)
{
    if (!plugins_quorum_Libraries_Game_Graphics_Skybox_.initialized_plugins_quorum_Libraries_Game_Graphics_Skybox_)
    {
        plugins_quorum_Libraries_Game_Graphics_Skybox_.RIGHT = 0;
        plugins_quorum_Libraries_Game_Graphics_Skybox_.LEFT = 1;
        plugins_quorum_Libraries_Game_Graphics_Skybox_.UP = 2;
        plugins_quorum_Libraries_Game_Graphics_Skybox_.DOWN = 3;
        plugins_quorum_Libraries_Game_Graphics_Skybox_.FRONT = 4;
        plugins_quorum_Libraries_Game_Graphics_Skybox_.BACK = 5;
    }
    
    this.me_ = quorumBox;
    
    var sidesRequested = [false, false, false, false, false, false];
    
    this.InitializeCubeMap$quorum_Libraries_Game_Graphics_Texture = function(texture)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        var texturePlugin = texture.plugin_;
        texturePlugin.SetGraphicsInfo$quorum_integer$quorum_integer(graphics.gl.TEXTURE_CUBE_MAP, texturePlugin.CreateGLHandle());
        texturePlugin.Bind();
        
        graphics.SetTextureParameter$quorum_integer$quorum_integer$quorum_integer(graphics.gl.TEXTURE_CUBE_MAP, graphics.gl.TEXTURE_MAG_FILTER, graphics.gl.LINEAR);
        graphics.SetTextureParameter$quorum_integer$quorum_integer$quorum_integer(graphics.gl.TEXTURE_CUBE_MAP, graphics.gl.TEXTURE_MIN_FILTER, graphics.gl.LINEAR);
        graphics.SetTextureParameter$quorum_integer$quorum_integer$quorum_integer(graphics.gl.TEXTURE_CUBE_MAP, graphics.gl.TEXTURE_WRAP_S, graphics.gl.CLAMP_TO_EDGE);
        graphics.SetTextureParameter$quorum_integer$quorum_integer$quorum_integer(graphics.gl.TEXTURE_CUBE_MAP, graphics.gl.TEXTURE_WRAP_T, graphics.gl.CLAMP_TO_EDGE);
        graphics.SetTextureParameter$quorum_integer$quorum_integer$quorum_integer(graphics.gl.TEXTURE_CUBE_MAP, graphics.gl.TEXTURE_WRAP_R, graphics.gl.CLAMP_TO_EDGE);
    };
    
    this.LoadSide$quorum_Libraries_System_File$quorum_Libraries_Game_Graphics_Texture$quorum_integer = function(file, texture, side)
    {
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().nativeGraphics;
        switch(side)
        {
            case graphics.gl.TEXTURE_CUBE_MAP_POSITIVE_X:
                sidesRequested[plugins_quorum_Libraries_Game_Graphics_Skybox_.RIGHT] = true;
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_NEGATIVE_X:
                sidesRequested[plugins_quorum_Libraries_Game_Graphics_Skybox_.LEFT] = true;
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_POSITIVE_Y:
                sidesRequested[plugins_quorum_Libraries_Game_Graphics_Skybox_.UP] = true;
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_NEGATIVE_Y:
                sidesRequested[plugins_quorum_Libraries_Game_Graphics_Skybox_.DOWN] = true;
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_POSITIVE_Z:
                sidesRequested[plugins_quorum_Libraries_Game_Graphics_Skybox_.BACK] = true;
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_NEGATIVE_Z:
                sidesRequested[plugins_quorum_Libraries_Game_Graphics_Skybox_.FRONT] = true;
                break;
        }
        
        var me_ = this.me_;
        switch(side)
        {
            case graphics.gl.TEXTURE_CUBE_MAP_POSITIVE_X:
                me_.Set_Libraries_Game_Graphics_Skybox__rightSource_(file);
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_NEGATIVE_X:
                me_.Set_Libraries_Game_Graphics_Skybox__leftSource_(file);
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_POSITIVE_Y:
                me_.Set_Libraries_Game_Graphics_Skybox__upSource_(file);
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_NEGATIVE_Y:
                me_.Set_Libraries_Game_Graphics_Skybox__downSource_(file);
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_POSITIVE_Z:
                me_.Set_Libraries_Game_Graphics_Skybox__backSource_(file);
                break;
            case graphics.gl.TEXTURE_CUBE_MAP_NEGATIVE_Z:
                me_.Set_Libraries_Game_Graphics_Skybox__frontSource_(file);
                break;
        }
        
        var loadImage = new Image();
        loadImage.onload = function()
        {   
            texture.Bind();
            graphics.glTexImage2D(side, 0, graphics.gl.RGBA, loadImage.width, loadImage.height,
            0, graphics.gl.RGBA, graphics.gl.UNSIGNED_BYTE, loadImage);
        };
        loadImage.src = file.GetAbsolutePath();
    };
    
    this.SidesRequested = function()
    {
        for (var i = 0; i < 6; i++)
            if (sidesRequested[i] === false)
                return false;
        return true;
    };
    
    this.DisposeNative = function()
    {
        for (var i = 0; i < 6; i++)
            sidesRequested[i] = false;
    };
}


