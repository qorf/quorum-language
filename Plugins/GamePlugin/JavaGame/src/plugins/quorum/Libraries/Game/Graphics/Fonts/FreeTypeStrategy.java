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
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;

import quorum.Libraries.Game.Graphics.Color_;
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
        // FOR TESTING PURPOSES ONLY
        if (libHandle == 0)
            libHandle = InitFreeType();
        
        
        
        /*  We create the shader here. This will set the following fields:
                - shaderHandle
                - vertexShaderHandle
                - fragShaderHandle
                - colorHandle
                - texHandle
                - coordHandle
            shaderHandle is needed anytime we change color. All values are
            needed for use in Dispose to clean up and deallocate resources. */
        //CreateShader();
        
        // We also need to initialize the  image cache used by this Font.
        //cacheHandle = InitImageCache(cacheManagerHandle);
        
        // We will also want a handle to an OpenGL texture for symbol rendering,
        // and we can initialize it.
        // GraphicsManager gl = GameState.nativeGraphics;
        
        //glHandle = gl.glGenTexture();
        //gl.glBindTexture(GraphicsManager.GL_TEXTURE_2D, glHandle);
        //gl.glUniform1i(texHandle, 0);
        
        //gl.PixelStorageMode(GraphicsManager.GL_UNPACK_ALIGNMENT, 1);
        
        //gl.SetTextureParameter(GraphicsManager.GL_TEXTURE_2D, GraphicsManager.GL_TEXTURE_WRAP_S, GraphicsManager.GL_CLAMP_TO_EDGE);
        //gl.SetTextureParameter(GraphicsManager.GL_TEXTURE_2D, GraphicsManager.GL_TEXTURE_WRAP_T, GraphicsManager.GL_CLAMP_TO_EDGE);
        
        //gl.SetTextureParameter(GraphicsManager.GL_TEXTURE_2D, GraphicsManager.GL_TEXTURE_MIN_FILTER, GraphicsManager.GL_LINEAR);
        //gl.SetTextureParameter(GraphicsManager.GL_TEXTURE_2D, GraphicsManager.GL_TEXTURE_MAG_FILTER, GraphicsManager.GL_LINEAR);
        
        //bufferHandle = gl.glGenBuffer();
        
        // We can now use our fontFile string to load the face.
        faceHandle = LoadFontC(libHandle, fontFile);
        
        // if (faceHandle == 0) throw new StuffIsBrokenException();
        // Temporary, overly simplistic exception while testing.
        if (faceHandle == 0)
            throw new RuntimeException("Could not load font!");
        
        // After loading our font, we should set its initial color and size.
        quorum.Libraries.Game.Graphics.Fonts.FontStrategy_ quorumFont = (quorum.Libraries.Game.Graphics.Fonts.FontStrategy_)me_;
        //quorum.Libraries.Game.Graphics.Color_ color = quorumFont.GetColor();
        //SetColorNative(color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
        
        SetSizeNative(quorumFont.GetSize());
        
        if (quorumFont.GetAngle() != 0)
            SetAngleNative(quorumFont.GetAngle());
    }
    
    /* Code for drawing if using a separate shader. The current code instead
       uses a SpriteBatch in Quorum code.

    public void DrawTextNative(String target)
    {
        // Activate this shader.
        GameState.nativeGraphics.glUseProgram(shaderHandle);
        
        quorum.Libraries.Game.Graphics.Font quorumFont = (quorum.Libraries.Game.Graphics.Font)me_;
        
        float x = (float)quorumFont.GetCursorX();
        float y = (float)quorumFont.GetCursorY();
        
        GraphicsManager gl = GameState.nativeGraphics;
        
        // We need blending to handle the alpha for our glyph.
        gl.glEnable(GraphicsManager.GL_BLEND);
	gl.glBlendFunc(GraphicsManager.GL_SRC_ALPHA, GraphicsManager.GL_ONE_MINUS_SRC_ALPHA);
        
        gl.glEnableVertexAttribArray(coordHandle);
        
        gl.glBindBuffer(GraphicsManager.GL_ARRAY_BUFFER, bufferHandle);
        gl.glVertexAttribPointer(coordHandle, 4, GraphicsManager.GL_FLOAT, false, 0, 0);
        
        ByteBuffer glyphBitmap = null;
        FloatBuffer pointBuffer = BufferUtils.createFloatBuffer(16);
        
        for (int i = 0; i < target.length(); i++)
        {
            glyphBitmap = LoadBitmap(bitmapData, target.charAt(i));
            
            // If a character failed to load, ignore and continue.
            if (glyphBitmap == null)
                continue;
            
            gl.glTexImage2D(GraphicsManager.GL_TEXTURE_2D, 0, GraphicsManager.GL_RED, (int)bitmapData[3],
                    (int)bitmapData[2], 0, GraphicsManager.GL_RED, GraphicsManager.GL_UNSIGNED_BYTE, glyphBitmap);
            
            float x1 = x + bitmapData[0];
            float y1 = y + bitmapData[1];
            float x2 = x1 + bitmapData[3];
            float y2 = y1 + bitmapData[2];
            
            float[] points = {  x1, y1, 0, 0,
                                x2, y1, 1, 0,
                                x1, y2, 0, 1,
                                x2, y2, 1, 1};
            
            pointBuffer.put(points);
            pointBuffer.flip();
            
            // Note that the size value of 16 is actually unused by the method in our case, but was included for clarity.
            gl.glBufferData(GraphicsManager.GL_ARRAY_BUFFER, 16, pointBuffer, GraphicsManager.GL_DYNAMIC_DRAW);
            gl.glDrawArrays(GraphicsManager.GL_TRIANGLE_STRIP, 0, 4);
            
            x = x + bitmapData[4];
            y = y + bitmapData[5];
            
            // Clean out our buffer so it can be reused after we're done with this character.
            pointBuffer.clear();
        }
        /*
            The above approach may be a performance bottleneck due to the need
            to load a new texture into OpenGL every time a glyph is encountered.
            The fastest approach (at time of this function call) would be to
            load every single glyph into a single texture atlas at time of the
            font being loaded. This isn't practical for large fonts, though, as
            it requires iteration through the entire font and would use a lot of
            memory.
        **-
        
        // After we finish drawing, we can disable the vertex attribute coords.
        gl.glDisableVertexAttribArray(coordHandle);
        
        // Disable blending after we've finished rendering text.
        gl.glDisable(GraphicsManager.GL_BLEND);
        
        // Deactivate this shader (so it isn't accidentally used elsewhere).
        GameState.nativeGraphics.glUseProgram(0);
    }*/
    
    public void SetSizeNative(int size)
    {
        SetSizeC(faceHandle, size);
        
        // If using the cacheing subsystem, need to use "FTC_Lookup_Size"
    }
    
    /* Not used in the current code. Should this use its own font in the future,
    this will be used.
    
    public void SetColorNative(double red, double green, double blue, double alpha)
    {   
        // Activate this shader.
        GameState.nativeGraphics.glUseProgram(shaderHandle);
        
        // Set the uniform color of the active shader to the appropriate color.
        GameState.nativeGraphics.glUniform4f(colorHandle, (float)red, (float)green, (float)blue, (float)alpha);
        
        // Deactivate this shader (so it isn't accidentally used elsewhere).
        GameState.nativeGraphics.glUseProgram(0);
    }*/
    
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
    
    
    /*  A one time call that initializes the cache manager for all Fonts. Each
        font will have its own image cache, but all image caches have to be
        managed by the same manager (to ensure that it properly limits the use
        of resources across all fonts). Must be called after InitFreeType(). */
    //static private native long InitCacheManager();
    
    /*  Initializes the image cache used by this Font object. */
    //private native long InitImageCache(long cacheManager);
    
    
    /* Each Font will require a shader. The shader for 2D text is rather simple,
    with its most complicated feature being a vector 4 representing RGBA color.
    When this function is completed, the following fields will be set:
        - shaderHandle
        - vertexShaderHandle
        - fragShaderHandle      
    
    Current code does not provide its own shader, but instead uses a SpriteBatch
    to render the text. This code has been kept in a comment should it be needed
    for the future.
    
    private void CreateShader()
    {
        String vertexShader = "attribute vec4 coord;\n"
                + "varying vec2 texpos;\n"
                + "\n"
                + "void main(void) {\n"
                + "gl_Position = vec4(coord.xy, 0, 1);\n"
                + "texpos = coord.zw;\n"
                + "}\n";
        
        /* The resulting vertex shader source code:
        
            attribute vec4 coord;
            varying vec2 texpos;

            void main(void) {
              gl_Position = vec4(coord.xy, 0, 1);
              texpos = coord.zw;
            }
        **-
        
        String fragShader = "varying vec2 texpos;\n"
                + "uniform sampler2D tex;\n"
                + "uniform vec4 color;\n"
                + "\n"
                + "void main (void) {\n"
                + "gl_FragColor = vec4(1, 1, 1, texture2D(tex, texpos).a) * color;\n"
                + "}\n";
        
        /* The resulting fragment shader source code:
        
            varying vec2 texpos;
            uniform sampler2D tex;
            uniform vec4 color;

            void main(void) {
              gl_FragColor = vec4(1, 1, 1, texture2D(tex, texpos).a) * color;
            }
        **-
        
        GraphicsManager graphics = GameState.nativeGraphics;
        
        /* When we compile a shader, to test if the compilation was successful
        we need an IntBuffer to retrieve the result from OpenGL. **-
        ByteBuffer tempBuffer = ByteBuffer.allocateDirect(4);
	tempBuffer.order(ByteOrder.nativeOrder());
	IntBuffer buffer = tempBuffer.asIntBuffer();
        
        /* Requesting OpenGL to prepare memory for a shader. **-
        vertexShaderHandle = graphics.glCreateShader(GraphicsManager.GL_VERTEX_SHADER);
        // if (vertexShaderHandle == 0) throw new StuffIsBrokenException();
        
        /* Providing the source and compiling the vertex shader. **-
        graphics.glShaderSource(vertexShaderHandle, vertexShader);
        graphics.glCompileShader(vertexShaderHandle);
        graphics.glGetShaderiv(vertexShaderHandle, GraphicsManager.GL_COMPILE_STATUS, buffer);
        
        // if (buffer.get(0) == 0) throw new StuffIsBrokenException();
        
        /* Resetting the IntBuffer for more error testing. **-
        tempBuffer = ByteBuffer.allocateDirect(4);
	tempBuffer.order(ByteOrder.nativeOrder());
	buffer = tempBuffer.asIntBuffer();
        
        fragShaderHandle = graphics.glCreateShader(GraphicsManager.GL_FRAGMENT_SHADER);
        // if (fragShaderHandle == 0) throw new StuffIsBrokenException();
        
        graphics.glShaderSource(fragShaderHandle, fragShader);
        graphics.glCompileShader(fragShaderHandle);
        graphics.glGetShaderiv(fragShaderHandle, GraphicsManager.GL_COMPILE_STATUS, buffer);
        
        // if (buffer.get(0) == 0) throw new StuffIsBrokenException();
        
        /* Requesting OpenGL to prepare memory for a program that will contain
        the shaders we just created. **-
        shaderHandle = graphics.glCreateProgram();
        // if (shaderHandle == 0) throw new StuffIsBrokenException();
        
        /* Loading the shaders into the program and linking it together so it
        can be used by our Font. **-
        graphics.glAttachShader(shaderHandle, vertexShaderHandle);
        graphics.glAttachShader(shaderHandle, fragShaderHandle);
        graphics.glLinkProgram(shaderHandle);
        
        tempBuffer = ByteBuffer.allocateDirect(4);
	tempBuffer.order(ByteOrder.nativeOrder());
	buffer = tempBuffer.asIntBuffer();
        
        graphics.glGetProgramiv(shaderHandle, GraphicsManager.GL_LINK_STATUS, buffer);
        // if (buffer.get(0) == 0) throw new StuffIsBrokenException();
        
        // Finally, load the handles we need from our shader.
        colorHandle = GameState.nativeGraphics.glGetUniformLocation(shaderHandle, "color");
        texHandle = GameState.nativeGraphics.glGetUniformLocation(shaderHandle, "tex");
        coordHandle = GameState.nativeGraphics.glGetAttribLocation(shaderHandle, "coord");
    }*/
    
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
    
    public quorum.Libraries.Game.Graphics.Glyph_ GetGlyphNative(String character)
    {        
        char target = character.charAt(0);
        quorum.Libraries.Game.Graphics.Glyph glyph = new quorum.Libraries.Game.Graphics.Glyph();
        
        ByteBuffer bitmap = LoadBitmap(bitmapData, target, faceHandle);
        
        // Since the space is just open space, there's no need to load it in a drawable.
        if (target != ' ')
        {
            quorum.Libraries.Game.Graphics.PixelMap pixmap = new quorum.Libraries.Game.Graphics.PixelMap();
            plugins.quorum.Libraries.Game.Graphics.PixelMap map = pixmap.plugin_;

            map.LoadFromFontBitmap(bitmap, (int)bitmapData[3], (int)bitmapData[2], 1);

            quorum.Libraries.Game.Graphics.FileTextureData texData = new quorum.Libraries.Game.Graphics.FileTextureData();
            texData.InitializeFileTextureData(null, pixmap, null, false);
            texData.SetDisposalState(false);

            quorum.Libraries.Game.Graphics.Texture texture = new quorum.Libraries.Game.Graphics.Texture();
            texture.LoadFromTextureData(texData);
            
            /*
            Setting the texture filter to linear makes text look much better
            when it isn't lying cleanly along pixel boundaries, but makes it
            look muddier in general.
            */
//            TextureFilter minFilter = new TextureFilter();
//            TextureFilter magFilter = new TextureFilter();
//            minFilter.ConstructTextureFilter(minFilter.LINEAR);
//            magFilter.ConstructTextureFilter(magFilter.LINEAR);
//            texture.SetFilter(minFilter, magFilter);
            
            quorum.Libraries.Game.Graphics.Color c = new quorum.Libraries.Game.Graphics.Color();
            c.SetColor(color.GetRed(), color.GetGreen(), color.GetBlue(), color.GetAlpha());
            texture.plugin_.fontColor = c;

            quorum.Libraries.Game.Graphics.Drawable sprite = new quorum.Libraries.Game.Graphics.Drawable();
            sprite.Load(texture);
        
            glyph.drawable = sprite;
        }
        else
        {
            glyph.drawable = null;
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
    
    public boolean LoadImageSheet(Texture_ texture, quorum.Libraries.Containers.HashTable_ table)
    {
        long[][] nativeData = new long[256][6];
        ByteBuffer[] pixels = new ByteBuffer[256];
        Queue<ImageSheetRow> rows = new LinkedList<>();
        
        IntBuffer buffer = BufferUtils.newIntBuffer(1);
        GameStateManager.nativeGraphics.glGetIntegerv(GraphicsManager.GL_MAX_TEXTURE_SIZE, buffer);
        int maxSize = buffer.get(0);
        int rowHeight = 0;
        int rowWidth = 0;
        int totalHeight = 0;
        
        // Load the ASCII characters.
        for (char current = 0; current < 256; current++)
        {
            long[] currentData = nativeData[current];
            
            /* The data parameter will contain the following information after a call to LoadBitmap:
                [0] : The distance from the cursor to the left side of the bitmap.
                [1] : The distance from the cursor to the top side of the bitmap.
                [2] : The number of rows in a bitmap.
                [3] : The number of pixels in each row of the bitmap.
                [4] : The distance to advance the cursor's X coordinate.
                [5] : The distance to advance the cursor's Y coordinate.

                LoadBitmap will also return a bitmap as a ByteBuffer so it can be drawn.
            */
            pixels[current] = LoadBitmap(currentData, current, faceHandle);
            
            int currentWidth = (int)currentData[3];
            rowWidth += currentWidth;
            
            if (rowWidth > maxSize)
            {
                ImageSheetRow newRow = new ImageSheetRow(rowHeight, current - 1);
                rows.add(newRow);
                
                totalHeight += rowHeight;
                rowWidth = currentWidth;
                rowHeight = 0;
            }
            
            int currentHeight = (int)currentData[2];
            if (currentHeight > rowHeight)
            {
                rowHeight = currentHeight;
                if (totalHeight + rowHeight > maxSize)
                {
                    // We've exceeded the maximum size we can place in a Texture.
                    // Return false to indicate failure.
                    return false;
                }
            }
        }
        
        // Assemble the ByteBuffers into a single ByteBuffer for use by PixelMap.
        // NYI
        
        // True indicates successful loading of the ImageSheet and caching of Glyph data.
        return true;
    }
    
    public int GetHeight()
    {
        return (int)(GetLineHeightNative(faceHandle) >> 6);
    }
    
    native public long GetLineHeightNative(long handle);
    
    public void DisposeNative()
    {
        if (faceHandle != 0)
        {
            DisposeC(faceHandle);
            faceHandle = 0;
        }
    }
    
    private native void DisposeC(long handle);
    
    public void SetColorNative(Color_ c)
    {
        color = c;
    }
    
    public Color_ GetColor()
    {
        return color;
    }
}
