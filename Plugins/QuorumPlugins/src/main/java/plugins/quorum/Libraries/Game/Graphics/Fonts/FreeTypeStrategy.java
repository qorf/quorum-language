/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics.Fonts;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;
import plugins.quorum.Libraries.Game.Graphics.PixelMap;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;

import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.Graphics.Fonts.FontImageSheet_;
import quorum.Libraries.Game.Graphics.Glyph;
import quorum.Libraries.Game.Graphics.Glyph_;
import quorum.Libraries.Game.Graphics.Texture;
import quorum.Libraries.Game.Graphics.TextureFilter;
import quorum.Libraries.Game.Graphics.Texture_;

/**
 *
 * @author alleew
 */
public class FreeTypeStrategy 
{    
    public java.lang.Object me_ = null;
    
    static long libHandle = 0;
    
    // Identifies the face used by this font.
    long faceHandle; 
    
    Color_ color;
    
    /* bitmapData will contain the following information after a call to LoadBitmap:
        [0] : The distance from the cursor to the left side of the bitmap.
        [1] : The distance from the cursor to the top side of the bitmap.
        [2] : The number of rows in a bitmap.
        [3] : The number of pixels in each row of the bitmap.
        [4] : The distance to advance the cursor's X coordinate.
        [5] : The distance to advance the cursor's Y coordinate.
    */
    private final long[] bitmapData = new long[6];
    
    public void LoadFontNative(String fontFile)
    {   
        if (libHandle == 0)
            libHandle = InitFreeType();
        
        // We can now use our fontFile string to load the face.
        faceHandle = LoadFontC(libHandle, fontFile);
        
        if (faceHandle == 0)
            throw new RuntimeException("Could not load font!");
        
        // After loading our font, we should set its initial color and size.
        quorum.Libraries.Game.Graphics.Fonts.FontStrategy_ quorumFont = (quorum.Libraries.Game.Graphics.Fonts.FontStrategy_)me_;
        
        SetSizeNative(quorumFont.GetSize());
        
        if (quorumFont.GetAngle() != 0)
            SetAngleNative(quorumFont.GetAngle());
    }
    
    public void SetSizeNative(int size)
    {
        SetSizeC(faceHandle, size);
        
        // If using the cacheing subsystem, need to use "FTC_Lookup_Size"
    }
    
    public void SetAngleNative(double angle)
    {
        // Setting the angle using radians instead of degrees.
        SetAngleC(faceHandle, angle/180 * Math.PI);
        
        
        /*  To use the cacheing subsystem:
            Using info from: https://lists.gnu.org/archive/html/freetype-devel/2001-05/msg00044.html
        
            Rotation is acceptable if it is done before loading anything into the cache. Glyphs will
            be loaded into the cache in rotated form. The cache only cares about rotation at time of
            first loading. Thus, options are: multiple faces for multiple rotations, or reset cache
            each time rotation is changed.
        */
    }
    
    /* A one time call that initializes the FreeType library and passes a handle
       back to the libHandle. This allows Java to pass around the C pointer as
       necessary. */
    private native long InitFreeType();
    
    private native long LoadFontC(long library, String pathName);
    
    private native void SetSizeC(long face, int size);
    
    private native void SetAngleC(long face, double angle);
    
    /* The data parameter will contain the following information after a call to LoadBitmap:
        [0] : The distance from the cursor to the left side of the bitmap.
        [1] : The distance from the cursor to the top side of the bitmap.
        [2] : The number of rows in a bitmap.
        [3] : The number of pixels in each row of the bitmap.
        [4] : The distance to advance the cursor's X coordinate.
        [5] : The distance to advance the cursor's Y coordinate.
    
        LoadBitmap will also return a bitmap as a ByteBuffer so it can be drawn.
    */
    private native ByteBuffer LoadBitmap(long[] data, char glyph, long handle);

    /* The data parameter will contain the following information after a call to LoadBitmap:
        [0] : The distance from the cursor to the left side of the bitmap.
        [1] : The distance from the cursor to the top side of the bitmap.
        [2] : The number of rows in a bitmap.
        [3] : The number of pixels in each row of the bitmap.
        [4] : The distance to advance the cursor's X coordinate.
        [5] : The distance to advance the cursor's Y coordinate.

        LoadBitmap will also return a bitmap as a ByteBuffer so it can be drawn.

        This function gets a glyph in the format of signed distance fields. The "pixels"
        in the returned buffer indicate the distance to the edge of the glyph, and are
        positive if the pixel is outside the bounds of the glyph and negative if the
        pixel is inside the glyph.
    */
    private native ByteBuffer LoadSDFBitmap(long[] data, char glyph, long handle);

    public quorum.Libraries.Game.Graphics.Glyph_ GetGlyphNative(String character)
    {
        char target = character.charAt(0);
        quorum.Libraries.Game.Graphics.Glyph glyph = new quorum.Libraries.Game.Graphics.Glyph();
        
        ByteBuffer rawBitmap = LoadBitmap(bitmapData, target, faceHandle);

        // Since the space is just open space, there's no need to load it in a drawable.
        if (target != ' ')
        {
            int size = rawBitmap.capacity();
            ByteBuffer bitmap = BufferUtils.newByteBuffer(size * 4);

            for (int i = 0; i < size; i++)
            {
                bitmap.put((byte)255);
                bitmap.put((byte)255);
                bitmap.put((byte)255);
                bitmap.put(rawBitmap.get(i));
            }
            
            bitmap.flip();
            
            quorum.Libraries.Game.Graphics.PixelMap pixmap = new quorum.Libraries.Game.Graphics.PixelMap();
            plugins.quorum.Libraries.Game.Graphics.PixelMap map = pixmap.plugin_;

            map.LoadFromByteBuffer(bitmap, (int)bitmapData[3], (int)bitmapData[2], PixelMap.FORMAT_RGBA8888);

            quorum.Libraries.Game.Graphics.FileTextureData texData = new quorum.Libraries.Game.Graphics.FileTextureData();
            texData.InitializeFileTextureData(null, pixmap, null, false);
            texData.SetDisposalState(false);

            quorum.Libraries.Game.Graphics.Texture texture = new quorum.Libraries.Game.Graphics.Texture();
            texture.LoadFromTextureData(texData);

            quorum.Libraries.Game.Graphics.TextureRegion region = new quorum.Libraries.Game.Graphics.TextureRegion();
            region.LoadTextureRegion(texture);
        
            glyph.texture = region;
        }
        else
        {
            glyph.texture = null;
        }
        
        glyph.horizontalAdvance = (int)(bitmapData[4] >> 6);
        glyph.verticalAdvance = (int)(bitmapData[5] >> 6);
        glyph.lengthToGlyph = (int)bitmapData[0];
        glyph.heightFromBaseLine = (int)(bitmapData[1]);

        return glyph;
    }

    public quorum.Libraries.Game.Graphics.Glyph_ GetBorderedGlyphNative(String character)
    {
        char target = character.charAt(0);
        quorum.Libraries.Game.Graphics.Glyph glyph = new quorum.Libraries.Game.Graphics.Glyph();

        ByteBuffer rawBitmap = LoadSDFBitmap(bitmapData, target, faceHandle);

        // Since the space is just open space, there's no need to load it in a drawable.
        if (target != ' ')
        {
            int size = rawBitmap.capacity();
            ByteBuffer bitmap = BufferUtils.newByteBuffer(size * 4);

            for (int i = 0; i < size; i++)
            {
                bitmap.put((byte)255);
                bitmap.put((byte)255);
                bitmap.put((byte)255);
                bitmap.put(rawBitmap.get(i));
            }

            bitmap.flip();

            quorum.Libraries.Game.Graphics.PixelMap pixmap = new quorum.Libraries.Game.Graphics.PixelMap();
            plugins.quorum.Libraries.Game.Graphics.PixelMap map = pixmap.plugin_;

            map.LoadFromFontBitmap(bitmap, (int)bitmapData[3], (int)bitmapData[2], PixelMap.FORMAT_RGBA8888);

            quorum.Libraries.Game.Graphics.FileTextureData texData = new quorum.Libraries.Game.Graphics.FileTextureData();
            texData.InitializeFileTextureData(null, pixmap, null, false);
            texData.SetDisposalState(false);

            quorum.Libraries.Game.Graphics.Texture texture = new quorum.Libraries.Game.Graphics.Texture();
            texture.LoadFromTextureData(texData);

            quorum.Libraries.Game.Graphics.TextureRegion region = new quorum.Libraries.Game.Graphics.TextureRegion();
            region.LoadTextureRegion(texture);

            glyph.texture = region;
        }
        else
        {
            glyph.texture = null;
        }

        glyph.horizontalAdvance = (int)(bitmapData[4] >> 6);
        glyph.verticalAdvance = (int)(bitmapData[5] >> 6);
        glyph.lengthToGlyph = (int)bitmapData[0];
        glyph.heightFromBaseLine = (int)(bitmapData[1]);

        return glyph;
    }
    
    private class ImageSheetRow
    {
        public ImageSheetRow(int height, int endOfRow)
        {
            this.height = height;
            this.endOfRow = endOfRow;
        }
        
        // The end of the row is the last image that appears on this row, or
        // 256 if this is the last row.
        int endOfRow;
        
        // The height is the height of the tallest image in this row.
        int height;
    }
    
    private class TextureRegionData
    {
        public TextureRegionData(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        
        public int x;
        public int y;
        public int width;
        public int height;
    }
    
    public boolean LoadImageSheet(FontImageSheet_ sheet, boolean bordered)
    {
        Texture_ texture = sheet.Get_Libraries_Game_Graphics_Fonts_FontImageSheet__imageSheet_();
        quorum.Libraries.Containers.Array_ table = sheet.Get_Libraries_Game_Graphics_Fonts_FontImageSheet__glyphTable_();
        
        long[] currentData = new long[6];
        ByteBuffer[] pixels = new ByteBuffer[256];
        Glyph[] glyphs = new Glyph[256];
        TextureRegionData[] regionData = new TextureRegionData[256];
        Queue<ImageSheetRow> rows = new LinkedList<>();
        
        // How much padding there should be between symbols on the ImageSheet.
        int padding = 1;
        
        IntBuffer buffer = BufferUtils.newIntBuffer(1);
        GameStateManager.nativeGraphics.glGetIntegerv(GraphicsManager.GL_MAX_TEXTURE_SIZE, buffer);
        int maxSize = buffer.get(0);
        int rowHeight = padding;
        int rowWidth = padding;
        int totalHeight = rowHeight;
        int totalWidth = rowWidth;
        
        // Load the ASCII characters.
        for (char current = 0; current < 256; current++)
        {
            /* The data parameter will contain the following information after a call to LoadBitmap:
                [0] : The distance from the cursor to the left side of the bitmap.
                [1] : The distance from the cursor to the top side of the bitmap.
                [2] : The number of rows in a bitmap.
                [3] : The number of pixels in each row of the bitmap.
                [4] : The distance to advance the cursor's X coordinate.
                [5] : The distance to advance the cursor's Y coordinate.

                LoadBitmap will also return a bitmap as a ByteBuffer so it can be drawn.
            */
            ByteBuffer value;
            if (bordered)
                value = LoadSDFBitmap(currentData, current, faceHandle);
            else
                value = LoadBitmap(currentData, current, faceHandle);
            
            int currentWidth = (int)currentData[3];
            int currentHeight = (int)currentData[2];
            
            if (currentWidth == 0 || currentHeight == 0 || value == null)
            {
                // We use a single empty pixel to represent symbols that don't
                // have a visual representation, e.g. space or new line.
                currentWidth = 1;
                currentHeight = 1;
                ByteBuffer emptyPixel = BufferUtils.newByteBuffer(1);
                emptyPixel.put(0, (byte)0);
                pixels[current] = emptyPixel;
            }
            else
            {
                ByteBuffer valueCopy = BufferUtils.newByteBuffer(currentWidth * currentHeight);
                BufferUtils.copy(value, valueCopy, (currentWidth * currentHeight));
                pixels[current] = valueCopy;
            }
            
            Glyph glyph = new Glyph();
            glyph.horizontalAdvance = (int)(currentData[4] >> 6);
            glyph.verticalAdvance = (int)(currentData[5] >> 6);
            glyph.lengthToGlyph = (int)currentData[0];
            glyph.heightFromBaseLine = (int)(currentData[1]);
            glyphs[current] = glyph;
            
            table.Add(glyph);
            
            int x = rowWidth;
            rowWidth += currentWidth + padding;
            
            if (rowWidth > totalWidth)
                totalWidth = rowWidth;
            
            if (rowWidth > maxSize)
            {
                ImageSheetRow newRow = new ImageSheetRow(rowHeight, current - 1);
                rows.add(newRow);
                
                totalHeight += rowHeight;
                rowWidth = currentWidth;
                x = padding;
                rowHeight = padding;
            }
            
            if (currentHeight + padding > rowHeight)
            {
                rowHeight = currentHeight + padding;
                if (totalHeight + rowHeight > maxSize)
                {
                    // We've exceeded the maximum size we can place in a Texture.
                    // Return false to indicate failure.
                    return false;
                }
            }
            
            regionData[current] = new TextureRegionData(x, totalHeight, currentWidth, currentHeight);
        }
        
        totalHeight += rowHeight + padding;
        
        // Add the current and final row to the queue.
        rows.add(new ImageSheetRow(rowHeight, 255));
        
        // Assemble the ByteBuffers into a single ByteBuffer for use by PixelMap.
        ByteBuffer destination = BufferUtils.newByteBuffer(totalWidth * totalHeight * 4);
        ImageSheetRow currentRow = rows.remove();
        ByteBuffer currentSource = null;
        TextureRegionData currentRegion = null;
        int startOfRow = 0;
        int currentImage = 0;
        int destinationIndex = 0;
        
        for (int y = 0, subY = -padding; y < totalHeight; y++, subY++)
        {
            // The subY is the current row of pixels being rendered for the
            // current row of images. When it matches the height of the current
            // row of images, it's time to begin the next row.
            if (subY == currentRow.height)
            {
                startOfRow = currentRow.endOfRow + 1;
                
                if (rows.isEmpty() == false)
                    currentRow = rows.remove();
                else
                    currentRow = null;
                
                subY = -padding;
            }
            
            if (currentRow != null)
            {
                currentImage = startOfRow;
                currentSource = pixels[startOfRow];
                currentRegion = regionData[startOfRow];
            }
            
            for (int x = 0, subX = -padding; x < totalWidth; x++, subX++, destinationIndex+=4)
            {
                destination.put(destinationIndex, (byte)255);
                destination.put(destinationIndex + 1, (byte)255);
                destination.put(destinationIndex + 2, (byte)255);
                
                if (currentRow == null || currentImage > currentRow.endOfRow || subX < 0)
                {
                    destination.put(destinationIndex + 3, (byte)0);
                    continue;
                }
                
                // The subX is the current pixel x position being rendered in
                // the current image. When it matches the width of the current
                // image, it's time to begin the next one.
                if (subX >= currentRegion.width)
                {
                    currentImage++;
                    if (currentImage <= currentRow.endOfRow)
                    {
                        currentSource = pixels[currentImage];
                        currentRegion = regionData[currentImage];
                        subX = -padding;
                    }
                    else
                    {
                        continue;
                    }
                }
                
                if (subY >= currentRegion.height || subY < 0 || subX < 0)
                {
                    destination.put(destinationIndex + 3, (byte)0);
                }
                else
                {
                    destination.put(destinationIndex + 3, currentSource.get(currentRegion.width * subY + subX));
                }
            }
        }
        
        quorum.Libraries.Game.Graphics.PixelMap pixmap = new quorum.Libraries.Game.Graphics.PixelMap();
        plugins.quorum.Libraries.Game.Graphics.PixelMap map = pixmap.plugin_;

        map.LoadFromByteBuffer(destination, totalWidth, totalHeight, PixelMap.FORMAT_RGBA8888);

        quorum.Libraries.Game.Graphics.FileTextureData texData = new quorum.Libraries.Game.Graphics.FileTextureData();
        texData.InitializeFileTextureData(null, pixmap, null, false);
        texData.SetDisposalState(false);

        texture.LoadFromTextureData(texData);
            
        quorum.Libraries.Game.Graphics.Color c = new quorum.Libraries.Game.Graphics.Color();
        c.SetColor(color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
        ((Texture)texture).plugin_.fontColor = c;

        for (int i = 0; i < 256; i++)
        {
            TextureRegionData data = regionData[i];
            Glyph glyph = glyphs[i];
            
            quorum.Libraries.Game.Graphics.TextureRegion region = new quorum.Libraries.Game.Graphics.TextureRegion();
            region.LoadTextureRegion(texture, data.x, data.y, data.width, data.height);
        
            glyph.texture = region;
        }
        
        // True indicates successful loading of the ImageSheet and caching of Glyph data.
        return true;
    }
    
    public int SystemGetHeight()
    {
        return (int)(GetLineHeightNative(faceHandle) >> 6);
    }
    
    native public long GetLineHeightNative(long handle);
    
    public int SystemGetMaximumAscent()
    {
        return (int)(GetMaximumAscentNative(faceHandle) >> 6);
    }
    
    native public long GetMaximumAscentNative(long handle);
    
    public int SystemGetMaximumDescent()
    {
        return (int)(GetMaximumDescentNative(faceHandle) >> 6);
    }
    
    native public long GetMaximumDescentNative(long handle);
    
    public int SystemGetUnderlinePosition()
    {
        return (int)(GetUnderlinePositionNative(faceHandle) >> 6);
    }
    
    native public long GetUnderlinePositionNative(long handle);
    
    public int SystemGetUnderlineThickness()
    {
        return (int)(GetUnderlineThicknessNative(faceHandle) >> 6);
    }
    
    native public long GetUnderlineThicknessNative(long handle);
    
    public void DisposeNative()
    {
        if (faceHandle != 0)
        {
            DisposeC(faceHandle);
            faceHandle = 0;
        }
    }
    
    private native void DisposeC(long handle);

    /*
    Get the error code for the last Freetype error that was recorded. Used for debugging.
    DOUBLE CHECK THE NATIVE IMPLEMENTATION FOR WHERE ERRORS ARE RECORDED!
    The initial implementation was sloppy about recording errors, so legitimate errors might
    not be stored. Using this naively might give you the wrong idea!
    */
    private native int GetLastError();

    public void SetColorNative(Color_ c)
    {
        color = c;
    }
    
    public Color_ GetColor()
    {
        return color;
    }
}
