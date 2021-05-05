function plugins_quorum_Libraries_Game_Graphics_PixelMap_(quorumPixelMap) 
{
    
    var graphics = plugins_quorum_Libraries_Game_GameStateManager_.nativeGraphics;
    
    if (!plugins_quorum_Libraries_Game_Graphics_PixelMap_.initialized_plugins_quorum_Libraries_Game_Graphics_PixelMap_)
    {
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.initialized_plugins_quorum_Libraries_Game_Graphics_PixelMap_ = true;
        
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
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.blending.SetValue$quorum_integer(plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER);
//        this.SetBlend(this.blending);
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.scale = plugins_quorum_Libraries_Game_Graphics_PixelMap_.SCALE_LINEAR;
        
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
    var image;
    
    var disposed = false;
    
    this.me_ = quorumPixelMap;

    this.LoadPixelMap$quorum_Libraries_System_File = function(file) 
    {
        /*
         
         GameFile javaFile = GameStateManager.fileHandler.Convert(quorumFile);
        
            byte[] bytes = javaFile.ReadBytes();

            pixelPointer = Load(nativeData, bytes, 0, bytes.length);
            if (pixelPointer == null)
                throw new GameRuntimeError("Error loading PixelMap: " + GetFailureReason());

            basePointer = nativeData[0];
            width = (int)nativeData[1];
            height = (int)nativeData[2];
            format = (int)nativeData[3];

            final quorum.Libraries.Game.Graphics.PixelMap thisMap = (quorum.Libraries.Game.Graphics.PixelMap) me_;
            quorum.Libraries.Game.Graphics.Format_ newFormat = new quorum.Libraries.Game.Graphics.Format();
            newFormat.SetValue(format);
            thisMap.format = newFormat;
         
         */
        
        // Proper code to go here to be determined.
        
//        pixels = new Uint8Array([0, 0, 255, 255]);
//        width = 1;
//        height = 1;
//        format = 4;
//        
//        var image = new Image();
//        image.onload = function() 
//        {
//            FinishFileLoading(image);
//        };
//        image.src = "Hues.png";
    };
    
//    function FinishFileLoading(loadedImage)
//    {
//        image = loadedImage;
//        width = image.width;
//        height = image.height;
//        format = 4;
//    }

    this.LoadAsynchronously$quorum_Libraries_System_File$quorum_Libraries_Game_Graphics_Format$quorum_boolean$quorum_Libraries_Game_Graphics_Drawable$quorum_Libraries_Game_Graphics_Texture 
        = function(file, format, useMipMaps, drawable, texture)
    {
        var loadImage = new Image();
        var pixelMap = this.me_;
        loadImage.onload = function()
        {
            FinishLoadingAsynchronously(loadImage, file, format, useMipMaps, drawable, texture, pixelMap);
        };
        loadImage.src = file.GetAbsolutePath();
    };
    
    function FinishLoadingAsynchronously(loadImage, file, formatValue, useMipMaps, drawable, texture, pixelMap)
    {
        image = loadImage;
        width = loadImage.width;
        height = loadImage.height;
        format = 4;
        
        var newFormat = new quorum_Libraries_Game_Graphics_Format_();
        newFormat.SetValue$quorum_integer(4);
        pixelMap.format = newFormat;

        if (!(texture === null || texture === undefined))
        {
            texture.FinishLoadingAsynchronously$quorum_Libraries_System_File$quorum_Libraries_Game_Graphics_PixelMap$quorum_Libraries_Game_Graphics_Format$quorum_boolean$quorum_Libraries_Game_Graphics_Drawable(file, pixelMap, newFormat, useMipMaps, drawable);
            texture.plugin_.AlertTextureLoadListeners();
        }
    }
    
    this.CreatePixelMap$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Format = function(newWidth, newHeight, newFormat) 
    {
        width = newWidth;
        height = newHeight;
        format = newFormat.GetValue();
        image = undefined;
        
        this.me_.GetFormat().SetValue$quorum_integer(format);
        
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
        if (!(image === undefined || image === null))
            return;
        
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
        if (!(image === undefined || image === null))
            return;
        
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
        pixels = undefined;
        image = undefined;
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

    this.GetPixels = function()
    {
        if (image === undefined || image === null)
            return pixels;
        else
            return image;
    };

    this.Define2DImage$quorum_integer$quorum_integer$quorum_integer = function(target, mipLevel, border) 
    {
        graphics.glTexImage2D(target, mipLevel, this.GetGLInternalFormat(), this.GetWidth(), this.GetHeight(),
            border, this.GetGLInternalFormat(), this.GetGLType(), this.GetPixels());
    };
    
    this.Clear = function(clearColor)
    {
        if (!(image === undefined || image === null))
            return;
        
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
                
                for (var i = 0; i < width * height; i++)
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
                
                for (var i = 0; i < width * height; i++)
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
                
                for (var i = 0; i < width * height; i++)
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

                for (var i = 0; i < width * height; i++)
                {
                    pixels[i] = colorCode;
                }
                
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                colorCode = ((clearColor.GetRed() * 15) << 12)
                    | ((clearColor.GetGreen() * 15) << 8)
                    | ((clearColor.GetBlue() * 15) << 4)
                    | (clearColor.GetAlpha() * 15);
            
                for (var i = 0; i < width * height; i++)
                {
                    pixels[i] = colorCode;
                }
            
                break;
        }
    };

    this.DrawPixelMap$quorum_Libraries_Game_Graphics_PixelMap$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(pixmap, sourceX, sourceY, destinationX, destinationY, sourceWidth, sourceHeight) 
    {
        if (!(image === undefined || image === null))
            return;
        
        var destinationWidth = pixmap.GetWidth();
        var destinationHeight = pixmap.GetHeight();
        
        if (sourceWidth === destinationWidth && sourceHeight === destinationHeight)
        {
            for (; sourceY < sourceY + sourceHeight; sourceY++, destinationY++)
            {
                if (sourceY < 0 || destinationY < 0)
                    continue;
                if (sourceY >= sourceHeight || destinationY >= destinationHeight)
                    break;
                
                var sX, dX;
                
                for (sX = sourceX, dX = destinationX; sX < sourceX + width; sX++, dX++)
                {
                    if (sX < 0 || dX < 0)
                        continue;
                    if (sX >= sourceWidth || dX >= destinationWidth)
                        break;
                    
                    var colorCode = pixmap.GetPixel$quorum_integer$quorum_integer(sX, sourceY);
                    
                    if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
                    {
                        colorCode = this.Blend(colorCode, pixmap.GetPixel(dX, destinationY));
                    }
                    
                    pixmap.SetPixel(dX, destinationY, colorCode);
                }
            }
        }
        else
        {
            if (plugins_quorum_Libraries_Game_Graphics_PixelMap_.scale === plugins_quorum_Libraries_Game_Graphics_PixelMap_.SCALE_NEAREST)
            {
                var xRatio = (sourceWidth << 16) / destinationWidth + 1;
                var yRatio = (sourceHeight << 16) / destinationHeight + 1;
                
                var dX = destinationX;
                var dY = destinationY;
                var sX = sourceX;
                var sY = sourceY;
                
                for (var i = 0; i < destinationHeight; i++)
                {
                    sY = ((i * yRatio) >> 16) + sourceY;
                    dY = i + destinationY;
                    if (sY < 0 || dY < 0)
                        continue;
                    if (sY >= sourceHeight || dY >= destinationHeight)
                        break;
                    
                    for (var j = 0; j < destinationWidth; j++)
                    {
                        sX = ((j * xRatio) >> 16) + sourceX;
                        dX = j + destinationX;
                        if (sX < 0 || dX < 0)
                            continue;
                        if (sX >= sourceWidth || dX >= destinationWidth)
                            break;
                        
                        var colorCode = this.GetPixel$quorum_integer$quorum_integer(sX, sY);
                        
                        if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
                        {
                            colorCode = this.Blend(colorCode, pixmap.GetPixel(dX, dY));
                        }
                        
                        pixmap.SetPixel(dX, dY, colorCode);
                    }
                }
            }
            else if (plugins_quorum_Libraries_Game_Graphics_PixelMap_.scale === plugins_quorum_Libraries_Game_Graphics_PixelMap_.SCALE_LINEAR)
            {
                var xRatio = (sourceWidth - 1) / destinationWidth;
                var yRatio = (sourceHeight - 1) / destinationHeight;
                var xDifference = 0;
                var yDifference = 0;
                
                var dX = destinationX;
                var dY = destinationY;
                var sX = sourceX;
                var sY = sourceY;
                
                for (var i = 0; i < destinationHeight; i++)
                {
                    sY = ((i * yRatio) | 0) + sourceY;
                    dY = i + destinationY;
                    yDifference = (yRatio * i + sourceY) - sY;
                    
                    if (sY < 0 || dY < 0)
                        continue;
                    if (sY >= sourceHeight || dY >= destinationHeight)
                        break;
                    
                    for (var j = 0; j < destinationWidth; j++)
                    {
                        sX = ((j * xRatio) | 0) + sourceX;
                        dX = j + destinationX;
                        xDifference = (xRatio * j + sourceX) - sX;
                        
                        if (sX < 0 || dX < 0)
                            continue;
                        if (sX >= sourceWidth || dX >= destinationWidth)
                            break;
                        
                        var c1 = this.GetPixel$quorum_integer$quorum_integer(sX, sY);
                        var c2, c3, c4;
                        
                        if (sX + 1 < sourceWidth)
                            c2 = this.GetPixel$quorum_integer$quorum_integer(sX + 1, sY);
                        else
                            c2 = c1;
                        
                        if (sY + 1 < sourceHeight)
                            c3 = this.GetPixel$quorum_integer$quorum_integer(sX, sY + 1);
                        else
                            c3 = c1;
                        
                        if (sX + 1 < sourceWidth && sY + 1 < sourceHeight)
                            c4 = this.GetPixel$quorum_integer$quorum_integer(sX + 1, sY + 1);
                        else
                            c4 = c1;
                        
                        var ta = (1 - xDifference) * (1 - yDifference);
                        var tb = (xDifference) * (1 - yDifference);
                        var tc = (1 - xDifference) * (yDifference);
                        var td = (xDifference) * (yDifference);
                        
                        var red =   (((c1 & 0xff000000) >> 24) * ta +
                                    ((c2 & 0xff000000) >> 24) * tb +
                                    ((c3 & 0xff000000) >> 24) * tc +
                                    ((c4 & 0xff000000) >> 24) * td) & 0xff;
                            
                        var green = (((c1 & 0xff0000) >> 16) * ta +
                                    ((c2 & 0xff0000) >> 16) * tb +
                                    ((c3 & 0xff0000) >> 16) * tc +
                                    ((c4 & 0xff0000) >> 16) * td) & 0xff;
			var blue =  (((c1 & 0xff00) >> 8) * ta +
                                    ((c2 & 0xff00) >> 8) * tb +
                                    ((c3 & 0xff00) >> 8) * tc +
                                    ((c4 & 0xff00) >> 8) * td) & 0xff;
			var alpha = ((c1 & 0xff) * ta +
                                    (c2 & 0xff) * tb +
                                    (c3 & 0xff) * tc +
                                    (c4 & 0xff) * td) & 0xff;
                            
                        var colorCode = (red << 24) | (green << 16) | (blue << 8) | alpha;
                        
                        if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
                        {
                            colorCode = this.Blend(colorCode, pixmap.GetPixel(dX, dY));
                        }
                        
                        pixmap.SetPixel(dX, dY, colorCode);
                    }
                }
            }
        }
    };
    
    this.SetScale$quorum_integer = function(scale) 
    {
        plugins_quorum_Libraries_Game_Graphics_PixelMap_.scale = scale;
    };
    
    this.Fill$quorum_integer = function(color) 
    {
        if (!(image === undefined || image === null))
            return;
        
        var colorCode;
        var red, green, blue, alpha;
        
        switch (format)
        {
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_ALPHA:
                alpha = color & 0x000000ff;
                
                for (var i = 0; i < width * height; i++)
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
                red = (color & 0xff000000) >>> 24;
                green = (color & 0x00ff0000) >>> 16;
                blue = (color & 0x0000ff00) >>> 8;
                
                for (var i = 0; i < width * height; i++)
                {
                    var index = i * 3;
                    pixels[index] = red;
                    pixels[index + 1] = green;
                    pixels[index + 2] = blue;
                }
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA8888:
                red = (color & 0xff000000) >>> 24;
                green = (color & 0x00ff0000) >>> 16;
                blue = (color & 0x0000ff00) >>> 8;
                alpha = color & 0x000000ff;
                
                for (var i = 0; i < width * height; i++)
                {
                    var index = i * 4;
                    pixels[index] = red;
                    pixels[index + 1] = green;
                    pixels[index + 2] = blue;
                    pixels[index + 3] = alpha;
                }
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGB565:
                red = (((color & 0xff000000) >> 27) << 11) & 0xf800;
                green = (((color & 0xff0000) >> 18) << 5) & 0x7e0;
                blue = ((color & 0xff00) >> 11) & 0x1f;
                
                colorCode = red | green | blue;

                for (var i = 0; i < width * height; i++)
                {
                    pixels[i] = colorCode;
                }
                
                break;
            case plugins_quorum_Libraries_Game_Graphics_PixelMap_.FORMAT_RGBA4444:
                red = (((color & 0xff000000) >> 28) << 12) & 0xf000;
                green = (((color & 0xff0000) >> 20) << 8) & 0xf00;
                blue = (((color & 0xff00) >> 12) << 4) & 0xf0;
                alpha = ((color & 0xff) >> 4) & 0xf;
                
                colorCode = red | green | blue | alpha;
            
                for (var i = 0; i < width * height; i++)
                {
                    pixels[i] = colorCode;
                }
            
                break;
        }
    };
    
    this.DrawLine$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x1, y1, x2, y2, color) 
    {
        var distanceY = y2 - y1;
        var distanceX = x2 - x1;
        var fraction = 0;
        var stepX, stepY;
        var colorCode = color;
        
        if (distanceY < 0)
        {
            distanceY = -distanceY;
            stepY = -1;
        }
        else
        {
            stepY = 1;
        }
        
        if (distanceX < 0)
        {
            distanceX = -distanceX;
            stepX = -1;
        }
        else
        {
            stepX = 1;
        }
        
        distanceY = distanceY << 1;
        distanceX = distanceX << 1;
        
        if (this.InPixelMap(x1, y1))
        {
            if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
                colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(x1, y1)));
            
            this.SetPixel$quorum_integer$quorum_integer$quorum_integer(x1, y1, colorCode);
        }
        if (distanceX > distanceY)
        {
            fraction = distanceY - (distanceX >> 1);
            while (x1 != x2)
            {
                if (fraction >= 0)
                {
                    y1 = y1 + stepY;
                    fraction = fraction - distanceX;
                }
                
                x1 = x1 + stepX;
                fraction = fraction + distanceY;
                
                if (this.InPixelMap(x1, y1))
                {
                    if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
                        colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(x1, y1)));
                    
                    this.SetPixel$quorum_integer$quorum_integer$quorum_integer(x1, y1, colorCode);
                }
            }
        }
        else
        {
            fraction = distanceX - (distanceY >> 1);
            while (y1 != y2)
            {
                if (fraction >= 0)
                {
                    x1 = x1 + stepX;
                    fraction = fraction - distanceY;
                }
                
                y1 = y1 + stepY;
                fraction = fraction + distanceX;
                
                if (this.InPixelMap(x1, y1))
                {
                    if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
                        colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(x1, y1)));
                    
                    this.SetPixel$quorum_integer$quorum_integer$quorum_integer(x1, y1, colorCode);
                }
            }
        }
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
        for (var i = y; i < y2; i++)
        {
            this.HorizontalLine(x, x2, i, color);
        }
    };
    
    this.DrawCircle$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, radius, color) 
    {
        var px = 0;
        var py = radius;
        var p = (5 - ((radius * 4) >>> 0)) / 4;
        
        this.CirclePoints(x, y, px, py, color);
        
        while (px < py)
        {
            px++;
            if (p < 0)
            {
                p += 2 * px + 1;
            }
            else
            {
                py--;
                p += 2 * (px - py) + 1;
            }
            this.CirclePoints(x, y, px, py, color);
        }
    };
    
    this.FillCircle$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x, y, radius, color) 
    {
        var f = 1 - radius;
        var lineWidth = 1;
        var lineHeight = -2 * radius;
        var xOffset = 0;
        var yOffset = radius;
        
        this.HorizontalLine(x, x, y + radius, color);
        this.HorizontalLine(x, x, y - radius, color);
        this.HorizontalLine(x - radius, x + radius, y, color);
        
        while (xOffset < yOffset)
        {
            if (f >= 0)
            {
                yOffset--;
                lineHeight += 2;
                f += lineHeight;
            }
            
            xOffset++;
            lineWidth += 2;
            f += lineWidth;
            
            this.HorizontalLine(x - xOffset, x + xOffset, y + yOffset, color);
            this.HorizontalLine(x - xOffset, x + xOffset, y - yOffset, color);
            this.HorizontalLine(x - yOffset, x + yOffset, y + xOffset, color);
            this.HorizontalLine(x - yOffset, x + yOffset, y - xOffset, color);
        }
    };
    
    this.FillTriangle$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function(x1, y1, x2, y2, x3, y3, color) 
    {
        function Edge(x1, y1, x2, y2)
        {
            if (y2 > y1)
            {
                this.y1 = y1;
                this.y2 = y2;
                this.x1 = x1;
                this.x2 = x2;
            }
            else
            {
                this.y2 = y1;
                this.y1 = y2;
                this.x2 = x1;
                this.x1 = x2;
            }
        }
        
        function Maximum(value1, value2)
        {
            if (value1 > value2)
                return value1;
            
            return value2;
        }
        
        function Minimum(value1, value2)
        {
            if (value1 < value2)
                return value1;
            
            return value2;
        }
        
        var edges = [new Edge(x1, y1, x2, y2), new Edge(x1, y1, x3, y3), new Edge(x2, y2, x3, y3)];
        var tempEdge;
        var slope0, slope1, slope2;
        var edge0Length, edge1Length, edge2Length, tempLength;
        var y, boundY1, boundY2, calcX1, calcX2;
        
        // If the points are collinear, then we return immediately.
        if ((x2 - x1) * (y3 - y1) === (x3 - x1) * (y2 - y1))
            return;
        
        edge0Length = edges[0].y2 - edges[0].y1;
        edge1Length = edges[1].y2 - edges[1].y1;
        edge2Length = edges[2].y2 - edges[2].y1;
        
        // Edges are ordered according to descending length.
        if (edge1Length >= edge0Length && edge1Length >= edge2Length)
        {
            tempEdge = edges[0];
            edges[0] = edges[1];
            edges[1] = tempEdge;
            tempLength = edge0Length;
            edge0Length = edge1Length;
            edge1Length = tempLength;
        }
        else if (edge2Length >= edge0Length && edge2Length >= edge1Length)
        {
            tempEdge = edges[0];
            edges[0] = edges[2];
            edges[2] = tempEdge;
            tempLength = edge0Length;
            edge0Length = edge2Length;
            edge2Length = tempLength;
        }
        
        if (edge2Length > edge1Length)
        {
            tempEdge = edges[1];
            edges[1] = edges[2];
            edges[2] = tempEdge;
        }
        
        slope0 = ((edges[0].x1 - edges[0].x2)) / ((edges[0].y2 - edges[0].y1));
        slope1 = ((edges[1].x1 - edges[1].x2)) / ((edges[1].y2 - edges[1].y1));
        
        // We set our bounds to avoid iterating outside of the PixelMap.
        boundY1 = Maximum(edges[1].y1, 0);
        boundY2 = Minimum(edges[1].y2, height - 1);
        
        for (y = boundY1; y <= boundY2; y++)
        {
            // Using bitwise or with 0 will return the integer portion of the number.
            calcX1 = (edges[0].x2 + slope0 * (edges[0].y2 - y) + 0.5) | 0;
            calcX2 = (edges[1].x2 + slope1 * (edges[1].y2 - y) + 0.5) | 0;
            
            this.HorizontalLine(calcX1, calcX2, y, color);
        }
        
        // If there are still y-values which haven't been handled, keep calculating.
        if (edges[2].y2 - edges[2].y1 > 0)
        {
            slope2 = ((edges[2].x1 - edges[2].x2)) / ((edges[2].y2 - edges[2].y1));
            
            boundY1 = Maximum(edges[2].y1, 0);
            boundY2 = Minimum(edges[2].y2, height - 1);
            
            for (y = boundY1; y <= boundY2; y++)
            {
                calcX1 = (edges[0].x2 + slope0 * (edges[0].y2 - y) + 0.5) | 0;
                calcX2 = (edges[2].x2 + slope2 * (edges[2].y2 - y) + 0.5) | 0;
                
                this.HorizontalLine(calcX1, calcX2, y, color);
            }
        }
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
        
        for (var i = x1; i < x2; i++)
        {
            if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
            {
                colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(i, y)));
            }
            this.SetPixel$quorum_integer$quorum_integer$quorum_integer(i, y, colorCode);
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
        
        for (var i = y1; i < y2; i++)
        {
            if (this.GetBlending().GetValue() === plugins_quorum_Libraries_Game_Graphics_PixelMap_.BLEND_SOURCE_OVER)
            {
                colorCode = this.Blend(color, this.ConvertToRGBA8888(this.GetPixel$quorum_integer$quorum_integer(x, i)));
            }
            this.SetPixel$quorum_integer$quorum_integer$quorum_integer(x, i, colorCode);
        }
    };
    
    this.SafelySetPixel = function(x, y, code)
    {
        if (x < 0 || y < 0)
            return;
        if (x >= width || y >= height)
            return;
        this.SetPixel$quorum_integer$quorum_integer$quorum_integer(x, y, code);
    };
    
    this.CirclePoints = function(x, y, xOffset, yOffset, color)
    {
        if (x == 0)
        {
            this.SafelySetPixel(x, y + yOffset, color);
            this.SafelySetPixel(x, y - yOffset, color);
            this.SafelySetPixel(x + yOffset, y, color);
            this.SafelySetPixel(x - yOffset, y, color);
        }
        else if (x == y)
        {
            this.SafelySetPixel(x + xOffset, y + yOffset, color);
            this.SafelySetPixel(x - xOffset, y + yOffset, color);
            this.SafelySetPixel(x + xOffset, y - yOffset, color);
            this.SafelySetPixel(x - xOffset, y - yOffset, color);
        }
        else if (x < y)
        {
            this.SafelySetPixel(x + xOffset, y + yOffset, color);
            this.SafelySetPixel(x - xOffset, y + yOffset, color);
            this.SafelySetPixel(x + xOffset, y - yOffset, color);
            this.SafelySetPixel(x - xOffset, y - yOffset, color);
            this.SafelySetPixel(x + yOffset, y + xOffset, color);
            this.SafelySetPixel(x - yOffset, y + xOffset, color);
            this.SafelySetPixel(x + yOffset, y - xOffset, color);
            this.SafelySetPixel(x - yOffset, y - xOffset, color);
        }
    };
    
    this.InPixelMap = function(x, y)
    {
        if (x < 0 || y < 0)
            return false;
        if (x >= width || y >= height)
            return false;
        
        return true;
    };
	
	this.LoadFromFontBitmap = function(pixelArray, newWidth, newHeight, newFormat)
	{
		width = newWidth;
        height = newHeight;
        format = newFormat.GetValue();
        image = undefined;
		pixels = pixelArray;
	};
}
