function plugins_quorum_Libraries_Game_Graphics_PixelMap_(quorumPixelMap) 
{
    
    var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
    
    if (!this.initialized_plugins_quorum_Libraries_Game_Graphics_PixelMap_)
    {
        this.initialized_plugins_quorum_Libraries_Game_Graphics_PixelMap_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA = 1;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE = 2;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888 = 3;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888 = 4;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565 = 5;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444 = 6;

        plugins_quorum_Libraries_Game_Graphics_PixelMap_.SCALE_NEAREST = 0;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.SCALE_LINEAR = 1;

        plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_NONE = 0;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER = 1;
        
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.blending = new quorum_Libraries_Game_Graphics_Blending_();
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.blending.SetValue(plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER);
//        this.SetBlend(this.blending);
        this.SetScale(this.SCALE_LINEAR);
    }
    
    var format;
    var width;
    var height;
    var pixels;
    
    var disposed = false;
    
    this.me_ = quorumPixelMap;

    this.LoadPixelMap$quorum_Libraries_System_File = function(file) 
    {
        // To be implemented - requires file loading.
    };
    
    this.CreatePixelMap$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Format = function(newWidth, newHeight, newFormat) 
    {
        width = newWidth;
        height = newHeight;
        format = newFormat.GetValue();
        
        if (this.GetGLType(format) === graphics.gl.UNSIGNED_BYTE)
            pixels = new Uint8Array(width * height);
        
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE:
                pixels = new Uint8Array(width * height);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                pixels = new Uint8Array(width * height * 3);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                pixels = new Uint8Array(width * height * 4);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                pixels = new Uint16Array(width * height);
                break;
                
            default:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("I couldn't recognize the provided format with integer value " + format);
                throw exceptionInstance_;
        }
        
        var clearColor = new quorum_Libraries_Game_Graphics_Color_();
        clearColor.SetColor$quorum_number$quorum_number$quorum_number$quorum_number(0.0, 0.0, 0.0, 0.0);
        this.Clear(clearColor);
    };
    
    this.SetBlending$quorum_Libraries_Game_Graphics_Blending = function(newBlend) 
    {
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.blending = newBlend;
    };
    
    this.GetBlending = function() 
    {
        return plugins_quorum_Libraries_Game_Graphics_PixelMap_.blending;
    };
    
    this.SetPixel$quorum_integer$quorum_integer$quorum_integer = function(x, y, code) 
    {
        var index;
        
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE:
                pixels[x + width * y] = code;
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                index = (x + width * y) * 3;
                pixels[index] = (code & 0xff000000) >>> 24;
                pixels[index + 1] = (code & 0x00ff0000) >>> 16;
                pixels[index + 2] = (code & 0x0000ff00) >>> 8;
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                index = (x + width * y) * 4;
                pixels[index] = (code & 0xff000000) >>> 24;
                pixels[index + 1] = (code & 0x00ff0000) >>> 16;
                pixels[index + 2] = (code & 0x0000ff00) >>> 8;
                pixels[index + 3] = (code & 0x000000ff);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                pixels[x + width * y] = code;
                break;
                
            default:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("I couldn't recognize the provided format with integer value " + format);
                throw exceptionInstance_;
        }
    };
    
    this.GetPixel$quorum_integer$quorum_integer = function(x, y) 
    {
        var colorCode = 0;
        var index;
        
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE:
                colorCode = pixels[x + width * y];
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                index = (x + width * y) * 3;
                colorCode = (pixels[index] << 24) | (pixels[index + 1] << 16) | (pixels[index + 2] << 8);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                index = (x + width * y) * 4;
                colorCode = (pixels[index] << 24) | (pixels[index + 1] << 16) | (pixels[index + 2] << 8) | (pixels[index + 3]);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                colorCode = pixels[x + width * y];
                break;
                
            default:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("I couldn't recognize the provided format with integer value " + format);
                throw exceptionInstance_;
        }
        
        return colorCode;
    };
    
    this.GetWidth = function() 
    {
        return width;
    };
    
    this.GetHeight = function() 
    {
        return height;
    };
    
    this.Dispose = function() 
    {
        if (disposed === true)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("I can't dispose this PixelMap because it was already disposed!");
            throw exceptionInstance_;
        }
        
        disposed = true;
        //Free(basePointer);
    };
    
    this.GetGLInternalFormat = function() 
    {
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
                return graphics.gl.ALPHA;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE:
                return graphics.gl.LUMINANCE;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
                return graphics.gl.RGB;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                return graphics.gl.RGBA;
                
            default:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("I couldn't recognize the currently set format with integer value " + format);
                throw exceptionInstance_;
        }
    };
    
    this.GetGLType = function()
    {
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                return graphics.gl.UNSIGNED_BYTE;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
                return graphics.gl.UNSIGNED_SHORT_5_6_5;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                return graphics.gl.UNSIGNED_SHORT_4_4_4_4;
                
            default:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("I couldn't recognize the currently set format with integer value " + format);
                throw exceptionInstance_;
        }
    };

    this.Define2DImage$quorum_integer$quorum_integer$quorum_integer = function(target, mipLevel, border) 
    {
        graphics.glTexImage2D(target, mipLevel, this.GetGLInternalFormat(), this.GetWidth(), this.GetHeight(),
            border, this.GetGLInternalFormat(), this.GetGLType(), this.GetPixels());
    };
    
    this.Clear = function(clearColor)
    {
        if (clearColor === null || clearColor === undefined)
            clearColor = this.me_.color;
        
        var colorCode;
        var red;
        var green;
        var blue;
        var alpha;
        
        switch (format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
                colorCode = clearColor.GetColorCode();
                alpha = colorCode & 0x000000ff;
                
                for (i = 0; i < width * height; i++)
                {
                    pixels[i] = alpha;
                }
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("FORMAT_LUMINANCE isn't currently supported on this platform.");
                throw exceptionInstance_;
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                colorCode = clearColor.GetColorCode();
                
                red = (colorCode & 0xff000000) >>> 24;
                green = (colorCode & 0x00ff0000) >>> 16;
                blue = (colorCode & 0x0000ff00) >>> 8;
                
                for (i = 0; i < width * height; i++)
                {
                    var index = i * 3;
                    pixels[index] = red;
                    pixels[index + 1] = green;
                    pixels[index + 2] = blue;
                }
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                colorCode = clearColor.GetColorCode();
                
                red = (colorCode & 0xff000000) >>> 24;
                green = (colorCode & 0x00ff0000) >>> 16;
                blue = (colorCode & 0x0000ff00) >>> 8;
                alpha = colorCode & 0x000000ff;
                
                for (i = 0; i < width * height; i++)
                {
                    var index = i * 4;
                    pixels[index] = red;
                    pixels[index + 1] = green;
                    pixels[index + 2] = blue;
                    pixels[index + 3] = alpha;
                }
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
                colorCode = ((clearColor.GetRed() * 31) << 11) 
                    | ((clearColor.GetGreen() * 63) << 5) 
                    | (clearColor.GetBlue() * 31);

                for (i = 0; i < width * height; i++)
                {
                    pixels[i] = colorCode;
                }
                
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                colorCode = ((clearColor.GetRed() * 15) << 12)
                    | ((clearColor.GetGreen() * 15) << 8)
                    | ((clearColor.GetBlue() * 15) << 4)
                    | (clearColor.GetAlpha() * 15);
            
                for (i = 0; i < width * height; i++)
                {
                    pixels[i] = colorCode;
                }
            
                break;
        }
    };

    this.DrawPixelMap$quorum_Libraries_Game_Graphics_PixelMap$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(pixmap, sourceX, sourceY, destX, destY, sourceWidth, sourceHeight) 
    {

    };
    
    this.SetScale$quorum_integer = function(scale) 
    {

    };
    
    this.Fill$quorum_integer = function(color) 
    {

    };
    
    this.DrawLine$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x1, y1, x2, y2, color) 
    {

    };
    this.DrawRectangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, width, height, color) 
    {

    };
    
    this.FillRectangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, width, height, color) 
    {

    };
    
    this.DrawCircle$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, radius, color) 
    {

    };
    
    this.FillCircle$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, radius, color) 
    {

    };
    
    this.FillTriangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x1, y1, x2, y2, x3, y3, color) 
    {

    };
    
}
