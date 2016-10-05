/* global plugins_quorum_Libraries_Game_GameStateManager_, plugins_quorum_Libraries_Game_Graphics_ShaderProgram_ */

function plugins_quorum_Libraries_Game_Graphics_Painter2D_(quorumPainter) 
{
    var mesh;
    
    this.SPRITE_SIZE = 20;
    
    var index = 0;
    var inverseTexWidth = 0;
    var inverseTexHeight = 0;
    
    var transformMatrix = new quorum_Libraries_Compute_Matrix4_();
    var projectionMatrix = new quorum_Libraries_Compute_Matrix4_();
    var combinedMatrix = new quorum_Libraries_Compute_Matrix4_();
    var calcMatrix = new quorum_Libraries_Compute_Matrix4_();
    
    //var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
    
    var blendingDisabled = false;
    var blendSourceFunction = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics.gl.SRC_ALPHA;
    var blendDestFunction = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics.gl.ONE_MINUS_SRC_ALPHA;
    
    var shader;
    var customShader = null;
    var ownsShader;
    
    /*
     * Resources used for the font shader.
     */
    var fontShader;
    var useFontShader = false;
    
    var colorValue;
    
    this.me_ = quorumPainter;
    
    this.LoadDefaultPainter$quorum_Libraries_Game_Graphics_Mesh = function(quorumMesh) 
    {
        this.SetColor$quorum_Libraries_Game_Graphics_Color(this.me_.color);
        
        mesh = quorumMesh;
        
        var display = plugins_quorum_Libraries_Game_GameStateManager_.display;
        
        if (display === null || display === undefined)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("I couldn't create the Painter2D because the display hasn't been initialized!");
            throw exceptionInstance_;
        }
        
        projectionMatrix.SetToOrthographic2D(0, 0, display.GetWidth(), display.GetHeight());
        
        shader = this.CreateDefaultShader();
        fontShader = this.CreateFontShader();
        ownsShader = true;
    };
    
    this.CreateDefaultShader = function()
    {
        var vertexShader = "attribute vec4 " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.POSITION_ATTRIBUTE + ";\n" //
		+ "attribute vec4 " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.COLOR_ATTRIBUTE + ";\n" //
		+ "attribute vec2 " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "uniform mat4 u_projTrans;\n" //
		+ "varying vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "\n" //
		+ "void main()\n" //
		+ "{\n" //
		+ "   v_color = " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.COLOR_ATTRIBUTE + ";\n" //
		+ "   v_color.a = v_color.a * (255.0/254.0);\n" //
		+ "   v_texCoords = " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.TEXCOORD_ATTRIBUTE + "0;\n" //
		+ "   gl_Position =  u_projTrans * " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.POSITION_ATTRIBUTE + ";\n" //
		+ "}\n";
        
        var fragmentShader = "#ifdef GL_ES\n" //
		+ "#define LOWP lowp\n" //
		+ "precision mediump float;\n" //
		+ "#else\n" //
		+ "#define LOWP \n" //
		+ "#endif\n" //
		+ "varying LOWP vec4 v_color;\n" //
		+ "varying vec2 v_texCoords;\n" //
		+ "uniform sampler2D u_texture;\n" //
		+ "void main()\n"//
		+ "{\n" //
		+ "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" //
		+ "}";
        
        var shader = new plugins_quorum_Libraries_Game_Graphics_ShaderProgram_(vertexShader, fragmentShader);
        
        if (shader.IsCompiled() === false)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Error compiling default shader: " + shader.GetLog());
            throw exceptionInstance_;
        }
        
        return shader;
    };
    
    this.CreateFontShader = function()
    {
        var vertexShader = "attribute vec4 " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.POSITION_ATTRIBUTE + ";\n"
		+ "attribute vec4 " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.COLOR_ATTRIBUTE + ";\n"
		+ "attribute vec2 " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.TEXCOORD_ATTRIBUTE + "0;\n"
		+ "uniform mat4 u_projTrans;\n"
		+ "varying vec4 v_color;\n"
		+ "varying vec2 v_texCoords;\n"
		+ "\n"
		+ "void main()\n"
		+ "{\n"
		+ "   v_color = " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.COLOR_ATTRIBUTE + ";\n"
		+ "   v_color.a = v_color.a * (255.0/254.0);\n"
		+ "   v_texCoords = " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.TEXCOORD_ATTRIBUTE + "0;\n"
		+ "   gl_Position =  u_projTrans * " + plugins_quorum_Libraries_Game_Graphics_ShaderProgram_.POSITION_ATTRIBUTE + ";\n"
		+ "}\n";
        
        var fragmentShader = "#ifdef GL_ES\n"
		+ "#define LOWP lowp\n"
		+ "precision mediump float;\n"
		+ "#else\n"
		+ "#define LOWP \n"
		+ "#endif\n"
		+ "varying LOWP vec4 v_color;\n"
		+ "varying vec2 v_texCoords;\n"
                + "uniform vec4 u_fontColor;\n"
		+ "uniform sampler2D u_texture;\n"
		+ "void main()\n"
		+ "{\n"
                + "  vec4 compute = texture2D(u_texture, v_texCoords);\n"
                + "  compute.a = compute.a * u_fontColor.a;\n"
                + "  compute.rgb = u_fontColor.rgb;\n"
		+ "  gl_FragColor = v_color * compute;\n"
		+ "}";
        
        var shader = new plugins_quorum_Libraries_Game_Graphics_ShaderProgram_(vertexShader, fragmentShader);
        
        if (shader.IsCompiled() === false)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Error compiling font shader: " + shader.GetLog());
            throw exceptionInstance_;
        }
        
        return shader;
    };
    
    this.Begin = function() 
    {
        if (this.me_.IsDrawing())
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("This painter is already drawing! Call End() before calling Begin() again.");
            throw exceptionInstance_;
        }
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        
        graphics.glDepthMask(false);
        if (useFontShader)
            fontShader.Begin();
        else if (customShader !== null && customShader !== undefined)
            customShader.Begin();
        else
            shader.Begin();
        
        this.SetupMatrices();
        this.me_.drawing = true;
    };
    
    this.End = function() 
    {
        if (this.me_.IsDrawing())
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("This painter isn't drawing yet! Call Begin() before calling End().");
            throw exceptionInstance_;
        }
        
        if (index > 0)
            this.Flush();
        
        this.me_.lastTexture = null;
        this.me_.drawing = false;
        
        var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
        graphics.glDepthMask(true);
        if (this.IsBlendingEnabled())
            graphics.glDisable(graphics.gl.BLEND);
        
        if (useFontShader)
            fontShader.End();
        else if (customShader !== null && customShader !== undefined)
            customShader.End();
        else
            shader.End();
    };
    
    this.SetColor$quorum_Libraries_Game_Graphics_Color = function(newColor) 
    {
        this.me_.color = newColor;
        colorValue = this.me_.color.EncodeColorAsNumber();
    };
    
    this.SetColor$quorum_number$quorum_number$quorum_number$quorum_number = function(red, green, blue, alpha) 
    {
        this.me_.color.SetColor(red, green, blue, alpha);
        colorValue = this.me_.color.EncodeColorAsNumber();
    };
    
    this.Draw$quorum_Libraries_Game_Graphics_Drawable = function(drawable) 
    {
        if (!(this.me_.IsDrawing()))
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Painter2D:Begin() must be called before Draw.");
            throw exceptionInstance_;
        }
        
        var verticesLength = this.me_.GetVertices().GetSize();
        if (drawable.GetTexture() !== this.me_.lastTexture)
        {
            this.SwitchTexture(drawable.GetTexture());
        }
        else
        {
            if (verticesLength - index < drawable.DRAWABLE_SIZE)
            {
                this.Flush();
            }
        }
    };
    
    this.Flush = function() 
    {

    };
    
    this.IsBlendingEnabled = function() 
    {
        
    };
    
    this.Dispose = function() 
    {

    };
    
    this.ApplyCamera$quorum_Libraries_Game_Graphics_Camera = function(camera) 
    {

    };
    
    this.SetupMatrices = function()
    {
        
    };
}
