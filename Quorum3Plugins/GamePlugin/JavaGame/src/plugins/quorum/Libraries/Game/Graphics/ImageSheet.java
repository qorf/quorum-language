/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game.Graphics;


//import static com.badlogic.gdx.graphics.Texture.TextureWrap.*;
//
//import com.badlogic.gdx.Files.FileType;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.Pixmap.Format;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.Texture.TextureFilter;
//import com.badlogic.gdx.graphics.Texture.TextureWrap;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Page;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.Disposable;
//import com.badlogic.gdx.utils.GdxRuntimeException;
//import com.badlogic.gdx.utils.ObjectMap;
//import com.badlogic.gdx.utils.ObjectSet;
//import com.badlogic.gdx.utils.Sort;
//import com.badlogic.gdx.utils.StreamUtils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.Set;

import plugins.quorum.Libraries.Game.libGDX.Disposable;
import plugins.quorum.Libraries.Game.libGDX.Array;
import plugins.quorum.Libraries.Game.GameRuntimeError;

import plugins.quorum.Libraries.Game.Graphics.ImageSheet.ImageSheetData.Sheet;
import plugins.quorum.Libraries.Game.Graphics.ImageSheet.ImageSheetData.Region;

//import quorum.Libraries.Game.Graphics.Texture;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.Format_;
import quorum.Libraries.Game.Graphics.TextureFilter_;
import quorum.Libraries.Game.Graphics.TextureWrap_;
import quorum.Libraries.Game.Graphics.Drawable_;
import quorum.Libraries.System.File_;

import java.util.HashSet;
//import java.util.HashMap;
import java.util.Comparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ImageSheet {
    
        public java.lang.Object me_ = null;
    
        static final String[] tuple = new String[4];
        
        private final HashSet<Texture_> textures = new HashSet(4, 0.8f);
        private Array<Region> regions = new Array();
        
        public static class ImageSheetData 
        {
         
            public static class Sheet
            {
                public final File textureFile;
                public Texture_ texture;
                public final float width, height;
                public final boolean useMipMaps;
                public final int format;
                public final int minFilter;
                public final int magFilter;
                public final int uWrap;
                public final int vWrap;
                
                public Sheet(File file, float width, float height, boolean useMipMaps, int format, 
                        int minFilter, int magFilter, int uWrap, int vWrap)
                {
                    this.textureFile = file;
                    this.width = width;
                    this.height = height;
                    this.useMipMaps = useMipMaps;
                    this.format = format;
                    this.minFilter = minFilter;
                    this.magFilter = magFilter;
                    this.uWrap = uWrap;
                    this.vWrap = vWrap;
                }
            }
            
            public static class Region
            {
                public Sheet sheet;
                public int index;
                public String name;
                public float offsetX;
                public float offsetY;
                public int originalWidth;
                public int originalHeight;
                public boolean rotate;
                public int left;
                public int top;
                public int width;
                public int height;
                public boolean flip;
                // public int[] splits;
                // public int[] pads;
            }
            
            final Array<Sheet> sheets = new Array();
            final Array<Region> regions = new Array();
            
            public ImageSheetData(File packFile, File imagesDirectory, boolean flip)
            {
                /*  These values correspond to constant values used in
                    TextureWrap.quorum, which in turn correspond to values in
                    GraphicsManager, which correspond to values in LWJGL. Needless
                    to say, don't change these values.
                */
                final int CLAMP_TO_EDGE = 33071;
                final int REPEAT = 10497;
                
                BufferedReader reader;
                try 
                {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(packFile)), 64);
                }
                catch (Exception ex) 
                {
                    throw new GameRuntimeError("Could not read file " + packFile.getAbsolutePath());
                }
                
                try
                {
                    Sheet sheet = null;
                    while (true)
                    {
                        String line = reader.readLine();
                        if (line == null)
                            break;
                        if (line.trim().length() == 0)
                            sheet = null;
                        else if (sheet == null)
                        {
                            File newSheet = new File(imagesDirectory, line);
                            
                            readTuple(reader);
                            
                            float width = Integer.parseInt(tuple[0]);
                            float height = Integer.parseInt(tuple[1]);
                            
                            readTuple(reader);
                            
                            String formatString = tuple[0].toLowerCase();
                            int format;
                            
                            switch(formatString)
                            {
                                case("intensity"):
                                    format = 1;
                                    break;
                                case("luminancealpha"):
                                    format = 2;
                                    break;
                                case("rgb888"):
                                    format = 3;
                                    break;
                                case("rgba8888"):
                                    format = 4;
                                    break;
                                case("rgb565"):
                                    format = 5;
                                    break;
                                case("rgba4444"):
                                    format = 6;
                                default:
                                    throw new GameRuntimeError("Did not recognize format in ImageSheet: " + tuple[0]);
                            }
                            
                            readTuple(reader);
                            
                            String minString = tuple[0].toLowerCase();
                            String maxString = tuple[1].toLowerCase();
                            int minFilter;
                            int maxFilter;

                            // The values used here are from TextureFilter.quorum.
                            
                            switch(minString)
                            {
                                case("nearest"):
                                    minFilter = 9728;
                                    break;
                                case("linear"):
                                    minFilter = 9729;
                                    break;
                                case("mipmap"):
                                    minFilter = 9987;
                                    break;
                                case("mipmapnearestnearest"):
                                    minFilter = 9984;
                                    break;
                                case("mipmaplinearnearest"):
                                    minFilter = 9985;
                                    break;
                                case("mipmapnearestlinear"):
                                    minFilter = 9986;
                                    break;
                                case("mipmaplinearlinear"):
                                    minFilter = 9987;
                                    break;
                                default:
                                    throw new GameRuntimeError("Did not recognize filter in ImageSheet: " + tuple[0]);
                            }
                            
                            switch(maxString)
                            {
                                case("nearest"):
                                    maxFilter = 9728;
                                    break;
                                case("linear"):
                                    maxFilter = 9729;
                                    break;
                                case("mipmap"):
                                    maxFilter = 9987;
                                    break;
                                case("mipmapnearestnearest"):
                                    maxFilter = 9984;
                                    break;
                                case("mipmaplinearnearest"):
                                    maxFilter = 9985;
                                    break;
                                case("mipmapnearestlinear"):
                                    maxFilter = 9986;
                                    break;
                                case("mipmaplinearlinear"):
                                    maxFilter = 9987;
                                    break;
                                default:
                                    throw new GameRuntimeError("Did not recognize filter in ImageSheet: " + tuple[1]);
                            }
                            
                            boolean isMipMap = false;
                            
                            if (!(minFilter == 9728 || minFilter == 9729))
                                isMipMap = true;
                            
                            String direction = readValue(reader);
                            
                            // Using constants defined before the loop.
                            
                            int repeatX = CLAMP_TO_EDGE;
                            int repeatY = CLAMP_TO_EDGE;
                            if (direction.equals("x"))
                                repeatX = REPEAT;
                            else if (direction.equals("y"))
                                repeatY = REPEAT;
                            else if (direction.equals("xy"))
                            {
                                repeatX = REPEAT;
                                repeatY = REPEAT;
                            }
                            
                            sheet = new Sheet(newSheet, width, height, isMipMap, format, minFilter, maxFilter, repeatX, repeatY);
                            sheets.add(sheet);
                        }
                        else
                        {
                            boolean rotate = Boolean.valueOf(readValue(reader));
                            
                            readTuple(reader);
                            int left = Integer.parseInt(tuple[0]);
                            int top = Integer.parseInt(tuple[1]);
                            
                            readTuple(reader);
                            int width = Integer.parseInt(tuple[0]);
                            int height = Integer.parseInt(tuple[1]);
                            
                            Region region = new Region();
                            region.sheet = sheet;
                            region.left = left;
                            region.top = top;
                            region.width = width;
                            region.height = height;
                            region.name = line;
                            region.rotate = rotate;
                            
                            readTuple(reader);
                            
                            /* Code needed for nine-patches, which we are not currently supporting.
                            if (readTuple(reader) == 4) { // split is optional
                                    region.splits = new int[] {Integer.parseInt(tuple[0]), Integer.parseInt(tuple[1]),
                                            Integer.parseInt(tuple[2]), Integer.parseInt(tuple[3])};

                                    if (readTuple(reader) == 4) { // pad is optional, but only present with splits
                                            region.pads = new int[] {Integer.parseInt(tuple[0]), Integer.parseInt(tuple[1]),
                                                    Integer.parseInt(tuple[2]), Integer.parseInt(tuple[3])};

                                            readTuple(reader);
                                    }
                            }*/

                            region.originalWidth = Integer.parseInt(tuple[0]);
                            region.originalHeight = Integer.parseInt(tuple[1]);
                            
                            readTuple(reader);
                            region.offsetX = Integer.parseInt(tuple[0]);
                            region.offsetY = Integer.parseInt(tuple[1]);

                            region.index = Integer.parseInt(readValue(reader));
                            

                            if (flip)
                                region.flip = true;

                            regions.add(region);
                        }
                    }
                }
                catch (Exception ex)
                {
                    throw new GameRuntimeError("Error reading pack file: " + packFile);
                }
                finally
                {
                    try
                    {
                        reader.close();
                    }
                    catch (Exception e)
                    {
                        // Ignore any thrown exceptions.
                    }
                }
                
                regions.sort(indexComparator);
            }
        }
        
        public void LoadNative(File_ quorumFile)
        {
            File file = new File(quorumFile.GetAbsolutePath());
            File parent = file.getParentFile();
            LoadNative(file, parent);
        }
        
        public void LoadNative(File packFile, File imagesDirectory)
        {
            LoadNative(packFile, imagesDirectory, false);
        }
        
        public void LoadNative(File packFile, File imagesDirectory, boolean flip)
        {
            LoadData(new ImageSheetData(packFile, imagesDirectory, flip));
        }
        
        public void LoadData(ImageSheetData data)
        {
            //HashMap<Sheet, Texture_> sheetToTexture = new HashMap<Sheet, Texture_>();
            for (Sheet sheet : data.sheets) 
            {
                Texture_ texture = null;
                
                TextureFilter_ minFilter = new quorum.Libraries.Game.Graphics.TextureFilter();
                TextureFilter_ magFilter = new quorum.Libraries.Game.Graphics.TextureFilter();
                minFilter.ConstructTextureFilter(sheet.minFilter);
                magFilter.ConstructTextureFilter(sheet.magFilter);
                
                TextureWrap_ uWrap = new quorum.Libraries.Game.Graphics.TextureWrap();
                TextureWrap_ vWrap = new quorum.Libraries.Game.Graphics.TextureWrap();
                uWrap.ConstructTextureWrap(sheet.uWrap);
                vWrap.ConstructTextureWrap(sheet.vWrap);

                if (sheet.texture == null) {
                        texture = new quorum.Libraries.Game.Graphics.Texture();
                        File_ quorumFile = new quorum.Libraries.System.File();
                        quorumFile.SetWorkingDirectory(sheet.textureFile.getParent());
                        quorumFile.SetPath(sheet.textureFile.getName());
                        Format_ format = new quorum.Libraries.Game.Graphics.Format();
                        format.SetValue(sheet.format);
                        texture.LoadFromFile(quorumFile, format, sheet.useMipMaps);
                        sheet.texture = texture;
                } 
                else 
                {
                        texture = sheet.texture;
                }
                
                texture.SetFilter(minFilter, magFilter);
                texture.SetWrap(uWrap, vWrap);
                
                textures.add(texture);
            }

            regions = data.regions;
        }
        
        /** Releases all resources associated with this ImageSheet instance. This releases all the textures backing all TextureRegions
	 * and Drawables, which should no longer be used after calling dispose. */
	public void Dispose () {
		for (Texture_ texture : textures)
			texture.Dispose();
		textures.clear();
	}
        
        /** Returns the first region found with the specified name as a Drawable. If whitespace was stripped from the region when it was
	 * packed, the sprite is automatically positioned as if whitespace had not been stripped. This method uses string comparison to
	 * find the region and constructs a new sprite, so the result should be cached rather than calling this method multiple times.
	 * @return The sprite, or null. */
	public Drawable_ GetDrawableNative (String name) {
		for (int i = 0, n = regions.size; i < n; i++)
                {
			if (regions.get(i).name.equals(name))
                            return NewDrawable(regions.get(i));
                }
		return null;
	}
        
        /** Returns the first region found with the specified name and index as a sprite. This method uses string comparison to find the
	 * region and constructs a new sprite, so the result should be cached rather than calling this method multiple times.
	 * @return The sprite, or null.
	 * @see #createSprite(String) */
	public Drawable_ GetDrawableNative (String name, int index) {
		for (int i = 0, n = regions.size; i < n; i++) {
			Region region = regions.get(i);
			if (!region.name.equals(name)) continue;
			if (region.index != index) continue;
			return NewDrawable(regions.get(i));
		}
		return null;
	}
        
        private Drawable_ NewDrawable (Region region) 
        {
            Drawable_ drawable = new quorum.Libraries.Game.Graphics.Drawable();
            drawable.Load(region.sheet.texture, region.left, region.top, region.width, region.height);
            return drawable;
	}
        
        static final Comparator<Region> indexComparator = new Comparator<Region>() {
		public int compare (Region region1, Region region2) {
			int i1 = region1.index;
			if (i1 == -1) i1 = Integer.MAX_VALUE;
			int i2 = region2.index;
			if (i2 == -1) i2 = Integer.MAX_VALUE;
			return i1 - i2;
		}
	};
        
        static String readValue (BufferedReader reader) throws IOException {
		String line = reader.readLine();
		int colon = line.indexOf(':');
		if (colon == -1) throw new GameRuntimeError("Invalid line: " + line);
		return line.substring(colon + 1).trim();
	}
        
        /** Returns the number of tuple values read (1, 2 or 4). */
	static int readTuple (BufferedReader reader) throws IOException {
		String line = reader.readLine();
		int colon = line.indexOf(':');
		if (colon == -1) throw new GameRuntimeError("Invalid line: " + line);
		int i = 0, lastMatch = colon + 1;
		for (i = 0; i < 3; i++) {
			int comma = line.indexOf(',', lastMatch);
			if (comma == -1) break;
			tuple[i] = line.substring(lastMatch, comma).trim();
			lastMatch = comma + 1;
		}
		tuple[i] = line.substring(lastMatch).trim();
		return i + 1;
	}
}
 