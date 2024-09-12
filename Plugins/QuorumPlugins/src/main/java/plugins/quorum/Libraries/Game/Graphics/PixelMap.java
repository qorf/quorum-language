/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game.Graphics;

import java.nio.ByteBuffer;

import plugins.quorum.Libraries.Game.AndroidApplication;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.GameFile;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import plugins.quorum.Libraries.Game.Graphics.OpenGL.OpenGLManager;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;

/**
 *
 * @author stefika
 */
public class PixelMap {
    public java.lang.Object me_ = null;
    
    public static final int FORMAT_ALPHA = 1;
    public static final int FORMAT_LUMINANCE_ALPHA = 2;
    public static final int FORMAT_RGB888 = 3;
    public static final int FORMAT_RGBA8888 = 4;
    public static final int FORMAT_RGB565 = 5;
    public static final int FORMAT_RGBA4444 = 6;

    public static final int SCALE_NEAREST = 0;
    public static final int SCALE_LINEAR = 1;

    public static final int BLEND_NONE = 0;
    public static final int BLEND_SOURCE_OVER = 1;
    
    private OpenGLManager gl20 = GameStateManager.nativeGraphics;
    
    private static quorum.Libraries.Game.Graphics.Blending_ blending;

    // These variables originate in libGDX's Gdx2dPixmap class. They've moved
    // here because that class is being consolidated with Pixmap for our needs.
    private boolean disposed = false;
    
    long basePointer = -1;
    int format;
    ByteBuffer pixelPointer;
    long[] nativeData = new long[4];
    int width;
    int height;

    static 
    {
        //System.load(GameState.GetNativePath());
        blending = new quorum.Libraries.Game.Graphics.Blending();
        //blending.Set_Libraries_Game_Graphics_Blending_value(BLEND_SOURCE_OVER);
        blending.SetValue(BLEND_SOURCE_OVER);
        SetBlend(blending);
        SetScale(SCALE_LINEAR);
    }
    
    public void LoadPixelMap(quorum.Libraries.System.File_ quorumFile)
    {   
        byte[] bytes;
        
        if (GameStateManager.operatingSystem.contains("Android"))
        {
            bytes = AndroidApplication.QuorumFileToBytes(quorumFile);
        }
        else
        {
            GameFile javaFile = GameStateManager.fileHandler.Convert(quorumFile);
            bytes = javaFile.ReadBytes();
        }
        
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
    }
    
    public void LoadAsynchronously(quorum.Libraries.System.File_ file, quorum.Libraries.Game.Graphics.Format_ format,
            boolean useMipMaps, quorum.Libraries.Game.Graphics.Drawable_ drawable, quorum.Libraries.Game.Graphics.Texture_ texture)
    {
        LoadPixelMap(file);
        texture.FinishLoadingAsynchronously(file, (quorum.Libraries.Game.Graphics.PixelMap) me_, format, useMipMaps, drawable);
    }
    
    public void CreatePixelMap(int newWidth, int newHeight, quorum.Libraries.Game.Graphics.Format_ newFormat) 
    {
        final quorum.Libraries.Game.Graphics.PixelMap thisMap = (quorum.Libraries.Game.Graphics.PixelMap) me_;
        thisMap.GetFormat().SetValue(newFormat.GetValue());
        
        pixelPointer = NewPixelMap(nativeData, newWidth, newHeight, newFormat.GetValue());
        if (pixelPointer == null)
            throw new GameRuntimeError("Error creating new PixelMap.");
        
        basePointer = nativeData[0];
        width = (int)nativeData[1];
        height = (int)nativeData[2];
        format = (int)nativeData[3];
        
        final quorum.Libraries.Game.Graphics.Color clearColor = new quorum.Libraries.Game.Graphics.Color();
        clearColor.SetColor(0,0,0,0);
        Clear(clearColor);
    }
    
    public void SetBlending(quorum.Libraries.Game.Graphics.Blending_ newBlending)
    {
        SetBlend(newBlending);
    }
    
    private static void SetBlend (quorum.Libraries.Game.Graphics.Blending_ newBlending)
    {
        blending = newBlending;
        SetBlendNative(blending.Get_Libraries_Game_Graphics_Blending__value_());
    }
    
    public void SetPixel(int x, int y, int code)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        SetPixel(basePointer, x, y, code);
    }
    
    public int GetPixel(int x, int y)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        return GetPixel(basePointer, x, y);
    }

    public int GetWidth()
    {
      return width;
    }
    
    public int GetHeight()
    {
      return height;
    }
    
    public quorum.Libraries.Game.Graphics.Blending_ GetBlending()
    {
        return blending;
    }
    
    public void Dispose()
    {
        if (disposed == true)
            throw new GameRuntimeError("I can't dispose this PixelMap because it was already disposed!");
        disposed = true;

        if (basePointer != -1)
            Free(basePointer);

        pixelPointer = null;
    }    
    
    public int GetGLInternalFormat () 
    {
        switch (format) 
        {
            case FORMAT_ALPHA:
		return OpenGLManager.GL_ALPHA8;
            case FORMAT_LUMINANCE_ALPHA:
		return OpenGLManager.GL_LUMINANCE_ALPHA;
            case FORMAT_RGB888:
            case FORMAT_RGB565:
		return OpenGLManager.GL_RGB;
            case FORMAT_RGBA8888:
            case FORMAT_RGBA4444:
		return OpenGLManager.GL_RGBA;
            default:
		throw new GameRuntimeError("I couldn't recognize the currently set format with integer value " + format);
	}
    }
    
    public int GetGLType () 
    {
	switch (format) 
        {
            case FORMAT_ALPHA:
            case FORMAT_LUMINANCE_ALPHA:
            case FORMAT_RGB888:
            case FORMAT_RGBA8888:
		return OpenGLManager.GL_UNSIGNED_BYTE;
            case FORMAT_RGB565:
		return OpenGLManager.GL_UNSIGNED_SHORT_5_6_5;
            case FORMAT_RGBA4444:
		return OpenGLManager.GL_UNSIGNED_SHORT_4_4_4_4;
            default:
		throw new GameRuntimeError("unknown format: " + format);
	}
    }
    
    public ByteBuffer GetPixels()
    {
	return pixelPointer;
    }
    
    public void Define2DImage(int target, int mipLevel, int border)
    {
        gl20.glTexImage2D(target, mipLevel, GetGLInternalFormat(), GetWidth(), GetHeight(), border, GetGLInternalFormat(), GetGLType(), GetPixels());
    }
    
    // Without a parameter given, this will use the PixelMap's current color.
    public void Clear()
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        final quorum.Libraries.Game.Graphics.PixelMap thisMap = (quorum.Libraries.Game.Graphics.PixelMap) me_;
        int colorCode = thisMap.color.GetColorCode();
        Clear(basePointer, colorCode);
    }
    
    public void Clear(quorum.Libraries.Game.Graphics.Color_ clearColor)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        int colorCode = clearColor.GetColorCode();
        Clear(basePointer, colorCode);
    }
    
    public void DrawPixelMap(quorum.Libraries.Game.Graphics.PixelMap pixmap, int sourceX, int sourceY, int destX, int destY, int sourceWidth, int sourceHeight)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        plugins.quorum.Libraries.Game.Graphics.PixelMap map = pixmap.plugin_;
        long sourceBasePointer = map.basePointer;

        DrawPixelMap(sourceBasePointer, basePointer, sourceX, sourceY, sourceWidth, sourceHeight, destX, destY, pixmap.GetWidth(), pixmap.GetHeight());
    }
    
    public void Fill(int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        Clear(basePointer, color);
    }

    public void DrawLine(int x1, int y1, int x2, int y2, int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        DrawLine(basePointer, x1, y1, x2, y2, color);
    }
    
    public void DrawRectangle(int x, int y, int width, int height, int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        DrawRectangle(basePointer, x, y, width, height, color);
    }
    
    public void FillRectangle(int x, int y, int width, int height, int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        FillRectangle(basePointer, x, y, width, height, color);
    }
    
    public void DrawCircle(int x, int y, int radius, int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        DrawCircle(basePointer, x, y, radius, color);
    }
    
    public void FillCircle(int x, int y, int radius, int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        FillCircle(basePointer, x, y, radius, color);
    }
    public void FillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color)
    {
        if (pixelPointer == null)
            throw new GameRuntimeError("The PixelMap wasn't initialized yet! Use LoadPixelMap() or CreatePixelMap() first.");
        
        FillTriangle(basePointer, x1, y1, x2, y2, x3, y3, color);
    }
    
    private static native ByteBuffer Load(long[] nativeData, byte[] buffer, int offset, int len);
    
    private static native ByteBuffer NewPixelMap (long[] nativeData, int width, int height, int format); /*MANUAL
		gdx2d_pixmap* pixmap = gdx2d_new(width, height, format);
		if(pixmap==0)
			return 0;
	
		jobject pixel_buffer = env->NewDirectByteBuffer((void*)pixmap->pixels, pixmap->width * pixmap->height * gdx2d_bytes_per_pixel(pixmap->format));
		jlong* p_native_data = (jlong*)env->GetPrimitiveArrayCritical(nativeData, 0);
		p_native_data[0] = (jlong)pixmap;
		p_native_data[1] = pixmap->width;
		p_native_data[2] = pixmap->height;
		p_native_data[3] = pixmap->format;
		env->ReleasePrimitiveArrayCritical(nativeData, p_native_data, 0);
	
		return pixel_buffer;
	*/

    private static native void Free (long pixmap); /*
	gdx2d_free((gdx2d_pixmap*)pixmap);
    */

    private static native void Clear (long pixmap, int color); /*
	gdx2d_clear((gdx2d_pixmap*)pixmap, color);
    */

    private static native void SetPixel (long pixmap, int x, int y, int color); /*
	gdx2d_set_pixel((gdx2d_pixmap*)pixmap, x, y, color);
    */

    private static native int GetPixel (long pixmap, int x, int y); /*
	return gdx2d_get_pixel((gdx2d_pixmap*)pixmap, x, y);
    */

    private static native void DrawLine (long pixmap, int x, int y, int x2, int y2, int color); /*
	gdx2d_draw_line((gdx2d_pixmap*)pixmap, x, y, x2, y2, color);
    */

    private static native void DrawRectangle (long pixmap, int x, int y, int width, int height, int color); /*
	gdx2d_draw_rect((gdx2d_pixmap*)pixmap, x, y, width, height, color);
    */

    private static native void DrawCircle (long pixmap, int x, int y, int radius, int color); /*
	gdx2d_draw_circle((gdx2d_pixmap*)pixmap, x, y, radius, color);	
    */

    private static native void FillRectangle (long pixmap, int x, int y, int width, int height, int color); /*
	gdx2d_fill_rect((gdx2d_pixmap*)pixmap, x, y, width, height, color);
    */

    private static native void FillCircle (long pixmap, int x, int y, int radius, int color); /*
	gdx2d_fill_circle((gdx2d_pixmap*)pixmap, x, y, radius, color);
    */

    private static native void FillTriangle (long pixmap, int x1, int y1, int x2, int y2, int x3, int y3, int color); /*
	gdx2d_fill_triangle((gdx2d_pixmap*)pixmap, x1, y1, x2, y2, x3, y3, color);
    */

    private static native void DrawPixelMap (long src, long dst, int srcX, int srcY, int srcWidth, int srcHeight, int dstX,
	int dstY, int dstWidth, int dstHeight); /*
	gdx2d_draw_pixmap((gdx2d_pixmap*)src, (gdx2d_pixmap*)dst, srcX, srcY, srcWidth, srcHeight, dstX, dstY, dstWidth, dstHeight);
    */

    public static native void SetBlendNative (int blend); /*
	gdx2d_set_blend(blend);
    */

    public static native void SetScale (int scale); /*
	gdx2d_set_scale(scale);
    */

    public static native String GetFailureReason (); /*
        return env->NewStringUTF(gdx2d_get_failure_reason());
    */
    
    public void LoadFromByteBuffer(ByteBuffer pixels, int width, int height, int format)
    {      
        pixelPointer = pixels;
        this.width = width;
        this.height = height;
        this.format = format;
        
        final quorum.Libraries.Game.Graphics.PixelMap thisMap = (quorum.Libraries.Game.Graphics.PixelMap) me_;
        quorum.Libraries.Game.Graphics.Format_ newFormat = new quorum.Libraries.Game.Graphics.Format();
        newFormat.SetValue(format);
        thisMap.format = newFormat;
    }

    public void Screenshot(int x, int y, int width, int height)
    {
        // We screenshot using RGBA format, so we need to multiply by 4 to store all 4 components.
        ByteBuffer buffer = BufferUtils.newByteBuffer(width * height * 4);

        GameStateManager.nativeGraphics.glReadPixels(x, y, width, height, OpenGLManager.GL_RGBA, OpenGLManager.GL_UNSIGNED_BYTE, buffer);

        // The result of read pixels is inverted, so we have to flip it vertically.
        ByteBuffer result = BufferUtils.newByteBuffer(width * height * 4);

        // We'll be iterating by row, so we have to iterate over all 4 bytes of each pixel in the row
        int expandedWidth = width * 4;
        for (int j = 0; j < height; j++)
            for (int i = 0; i < expandedWidth; i++)
                result.put(j * expandedWidth + i, buffer.get((height - 1 - j) * expandedWidth + i));

        LoadFromByteBuffer(result, width, height, FORMAT_RGBA8888);
    }
    
}
