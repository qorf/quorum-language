function plugins_quorum_Libraries_Game_Graphics_PixelMap_(quorumPixelMap) 
{
    
    var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
    
    if (!this.initialized_plugins_quorum_Libraries_Game_Graphics_PixelMap_)
    {
        this.initialized_plugins_quorum_Libraries_Game_Graphics_PixelMap_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA = 1;
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA = 2;
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
        
        /*
         * These lookup tables are used to quickly transform values between the
         * possible range in a 4, 5, or 6 bit value to the range present in an 8
         * bit value.
         */
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable4bit = [0, 17, 34, 51, 68, 85, 102, 119, 136,
            153, 170, 187, 204, 221, 238, 255];
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable5bit = [0, 8, 16, 24, 32, 41, 49, 57, 65, 74,
            82, 90, 98, 106, 115, 123, 131, 139, 148, 156, 164, 172, 180, 189, 197, 205, 213, 222, 230, 238, 246, 255];
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable6bit = [0, 4, 8, 12, 16, 20, 24, 28, 32, 36,
            40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 85, 89, 93, 97, 101, 105, 109, 113, 117, 121, 125, 129, 133,
            137, 141, 145, 149, 153, 157, 161, 165, 170, 174, 178, 182, 186, 190, 194, 198, 202, 206, 210, 214, 218,
            222, 226, 230, 234, 238, 242, 246, 250, 255];
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
        
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
                pixels = new Uint8Array(width * height);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
                pixels = new Uint8Array(width * height * 2);
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
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                code = this.ConvertToFormat(code);
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
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                colorCode = pixels[x + width * y];
                colorCode = this.ConvertToRGBA8888(colorCode);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                index = (x + width * y) * 3;
                colorCode = (pixels[index] << 24) | (pixels[index + 1] << 16) | (pixels[index + 2] << 8);
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                index = (x + width * y) * 4;
                colorCode = (pixels[index] << 24) | (pixels[index + 1] << 16) | (pixels[index + 2] << 8) | (pixels[index + 3]);
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
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
                return graphics.gl.LUMINANCE_ALPHA;
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
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
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
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("FORMAT_LUMINANCE_ALPHA isn't currently supported on this platform.");
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
        this.scale = scale;
    };
    
    this.Fill$quorum_integer = function(color) 
    {

    };
    
    this.DrawLine$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x1, y1, x2, y2, color) 
    {

    };
    this.DrawRectangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, width, height, color) 
    {
        this.HorizontalLine(x, x + width - 1, y, color);
        this.HorizontalLine(x, x + width - 1, y + height - 1, color);
        this.VerticalLine(y, y + height - 1, x, color);
        this.VerticalLine(y, y + height - 1, x + width - 1, color);
    };
    
    this.FillRectangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, width, height, color) 
    {
        var x2 = x + width - 1;
        var y2 = y + height - 1;
        
        if (x >= width)
            return;
        if (y >= height)
            return;
        if (x2 < 0)
            return;
        if (y2 < 0)
            return;
        
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        if (x2 >= width)
            x2 = width - 1;
        if (y2 >= height)
            y2 = height - 1;
        
        y2++;
        for (i = y; i < y2; i++)
        {
            this.HorizontalLine(x, x2, i, color);
        }
    };
    
    this.DrawCircle$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, radius, color) 
    {
        var px = 0;
        var py = radius;
        var p = (5 - ((radius * 4) >>> 0)) / 4;
        
        
    };
    
    this.FillCircle$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, radius, color) 
    {

    };
    
    this.FillTriangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x1, y1, x2, y2, x3, y3, color) 
    {

    };
    
    /*
     *  Internal actions used for various purposes, such as blending pixels,
     *  converting between different color formats, and to draw horizontal
     *  and vertical lines.
     */
    
    this.Blend = function(source, destination)
    {
        var sourceRed   = (source & 0xff000000) >>> 24;
        var sourceGreen = (source & 0x00ff0000) >>> 16;
        var sourceBlue  = (source & 0x0000ff00) >>> 8;
        var sourceAlpha = (source & 0x000000ff);
        
        var destinationRed   = (destination & 0xff000000) >>> 24;
        var destinationGreen = (destination & 0x00ff0000) >>> 16;
        var destinationBlue  = (destination & 0x0000ff00) >>> 8;
        var destinationAlpha = (destination & 0x000000ff);
        
        destinationRed = destinationRed + sourceAlpha * (sourceRed - destinationRed) / 255;
        destinationGreen = destinationGreen + sourceAlpha * (sourceGreen - destinationGreen) / 255;
        destinationBlue = destinationBlue + sourceAlpha * (sourceBlue - destinationBlue) / 255;
        // Bit shift by 0 forces the result to truncate the value after the decimal, resulting in an integer.
        destinationAlpha = ((1.0 - (1.0 - sourceAlpha / 255.0) * (1.0 - destinationAlpha / 255.0)) * 255) >>> 0;
        
        return ((destinationRed << 24) | (destinationGreen << 16) | (destinationBlue << 8) | destinationAlpha);
    };
    
    // Converts a color code in RGBA8888 format to the format used by this PixelMap.
    this.ConvertToFormat = function(code)
    {
        var r, g, b, a, l;
        
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
                return code & 0xff;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
                var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
                exceptionInstance_.SetErrorMessage$quorum_text("FORMAT_LUMINANCE_ALPHA isn't currently supported on this platform.");
                throw exceptionInstance_;
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                return code;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                return code;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
                r = (((code & 0xff000000) >> 27) << 11) & 0xf800;
                g = (((code & 0xff0000) >> 18) << 5) & 0x7e0;
                b = ((code & 0xff00) >> 11) & 0x1f;
                return r | g | b;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                r = (((code & 0xff000000) >> 28) << 12) & 0xf000;
                g = (((code & 0xff0000) >> 20) << 8) & 0xf00;
                b = (((code & 0xff00) >> 12) << 4) & 0xf0;
                a = ((code & 0xff) >> 4) & 0xf;
                return r | g | b | a;
        }
    };
    
    // Converts a color code from the PixelMap's format to the RGBA8888 format.
    this.ConvertToRGBA8888 = function(code)
    {
        var r, g, b, a;
        
        switch(format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
                return (code & 0xff) | 0xffffff00;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_LUMINANCE_ALPHA:
                return ((code & 0xff00) << 16) | ((code & 0xff00) << 8) | (code & 0xffff);
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB888:
                return (code << 8) | 0x000000ff;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                return code;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
                r = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable5bit[(code & 0xf800) >> 11] << 24;
                g = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable6bit[(code & 0x7e0) >> 5] << 16;
                b = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable5bit[(code & 0x1f)] << 8;
                return r | g | b | 0xff;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                r = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable4bit[(code & 0xf000) >> 12] << 24;
                g = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable4bit[(code & 0xf00) >> 8] << 16;
                b = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable4bit[(code & 0xf0) >> 4] << 8;
                a = plugins_quorum_Libraries_Game_Graphics_PixelMap_.lookupTable4bit[(code & 0xf)];
                return r | g | b | a;
        }
        
    };
    
    // Expects the color code provided to be in the RGBA8888 format.
    this.HorizontalLine = function(x1, x2, y, color)
    {
        var colorCode = color;
        
        if (y < 0 || y >= height)
            return;
        
        if (x1 > x2)
        {
            var temp = x1;
            x1 = x2;
            x2 = temp;
        }
        
        if (x1 >= width)
            return;
        if (x2 < 0)
            return;
        
        if (x1 < 0)
            x1 = 0;
        if (x2 >= width)
            x2 = width - 1;
        
        x2 = x2 + 1;
        
        for (i = x1; i < x2; i++)
        {
            if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
            {
                colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(i, y)));
            }
            this.SetPixel(i, y, colorCode);
        }
    };
    
    // Expects the color code provided to be in the RGBA8888 format.
    this.VerticalLine = function(y1, y2, x, color)
    {
        var colorCode = color;
        
        if (x < 0 || x >= width)
            return;
        
        if (y1 > y2)
        {
            var temp = y1;
            y1 = y2;
            y2 = temp;
        }
        
        if (y1 >= height)
            return;
        
        if (y2 < 0)
            return;
        
        if (y1 < 0)
            y1 = 0;
        
        if (y2 >= height)
            y2 = height - 1;
        
        y2 = y2 + 1;
        
        for (i = y1; i < y2; i++)
        {
            if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
            {
                colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(x, i)));
            }
            this.SetPixel(x, i, colorCode);
        }
    };
}
