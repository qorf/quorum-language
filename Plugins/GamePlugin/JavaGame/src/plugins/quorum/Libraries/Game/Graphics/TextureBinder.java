/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.nio.IntBuffer;
import plugins.quorum.Libraries.Game.libGDX.BufferUtils;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.Texture;
import quorum.Libraries.Game.Graphics.TextureDescriptor_;
import quorum.Libraries.Game.Graphics.TextureDescriptor;

/**
 * An implementation of the libGDX class "DefaultTextureBinder".
 * 
 * @author alleew
 */
public class TextureBinder 
{
    public final static int ROUND_ROBIN = 0;
    public final static int WEIGHTED = 1;
    
    // GLES only supports up to 32 textures.
    public final static int MAX_GLES_UNITS = 32;
    
    // The index of the first exclusive texture unit.
    private final int offset;
    
    // The amount of exclusive textures that may be used.
    private final int count;
    
    // The weight added to a texture when its reused.
    private final int reuseWeight;
    
    // The textures currently exclusive bound.
    private final Texture_[] textures;
    
    // The weight (reuseWeight * reused - discarded) of the textures.
    private final int[] weights;
    
    // The method of binding to use.
    private final int method;
    
    // Flag to indicate that the current texture is reused.
    private boolean reused;
    
    // A texture descriptor used to directly interact with textures.
    private final TextureDescriptor_ tempDescriptor = new TextureDescriptor();
    
    // The last texture index that was assigned when given a new texture.
    private int currentTexture = 0;
    
    /* This constructor is set to use default settings. Because this is only
    ever called by the engine in a single location, it doesn't seem necessary
    to allow for different options currently. */
    public TextureBinder()
    {
        int max = Math.min(GetMaxTextureUnits(), MAX_GLES_UNITS);
        
        method = WEIGHTED;
        offset = 1;
        count = max - 1;
        textures = new Texture_[count];
        reuseWeight = 10;
        weights = new int[count];
    }
    
    public void Begin()
    {
        for (int i = 0; i < count; i++)
        {
            textures[i] = null;
            weights[i] = 0;
        }
    }
    
    public void End()
    {
        /*
        We do not unbind any textures -- we simply make sure none of the
        textures are active to prevent accidental manipulation of them.
        */
        GameStateManager.nativeGraphics.glActiveTexture(GraphicsManager.GL_TEXTURE0);
    }
    
    public int Bind(TextureDescriptor_ textureDesc)
    {
        return BindTexture((TextureDescriptor)textureDesc, false);
    }
    
    public int Bind(Texture_ texture)
    {
        tempDescriptor.SetDescriptor(texture, null, null, null, null);
        return BindTexture((TextureDescriptor)tempDescriptor, false);
    }
    
    private final int BindTexture(final TextureDescriptor textureDesc, final boolean rebind)
    {
        final int index, result;
        Texture_ texture = textureDesc.texture;
        reused = false;
        
        switch (method)
        {
            case ROUND_ROBIN:
                index = BindTextureRoundRobin(texture);
                result = offset + index;
                break;
            case WEIGHTED:
                index = BindTextureWeighted(texture);
                result = offset + index;
                break;
            default:
                return -1;
        }
        
        if (reused)
        {
            if (rebind)
                ((Texture)texture).plugin_.Bind(result);
            else
                GameStateManager.nativeGraphics.glActiveTexture(GraphicsManager.GL_TEXTURE0 + result);
        }
        texture.UnsafeSetWrap(textureDesc.uWrap, textureDesc.vWrap);
        texture.UnsafeSetFilter(textureDesc.minFilter, textureDesc.magFilter);
        return result;
    }
    
    private static int GetMaxTextureUnits()
    {
        IntBuffer buffer = BufferUtils.newIntBuffer(16);
        GameStateManager.nativeGraphics.glGetIntegerv(GraphicsManager.GL_MAX_TEXTURE_IMAGE_UNITS, buffer);
        return buffer.get(0);
    }
    
    private final int BindTextureRoundRobin(Texture_ texture)
    {
        for (int i = 0; i < count; i++)
        {
            final int index = (currentTexture + i) % count;
            if (textures[index] == texture)
            {
                reused = true;
                return index;
            }
        }
        currentTexture = (currentTexture + 1) % count;
        textures[currentTexture] = texture;
        ((Texture)texture).plugin_.Bind(offset + currentTexture);
        return currentTexture;
    }
    
    private final int BindTextureWeighted(Texture_ texture)
    {
        int result = -1;
        int weight = weights[0];
        int windex = 0;
        for (int i = 0; i < count; i++)
        {
            if (textures[i] == texture)
            {
                result = i;
                weights[i] += reuseWeight;
            }
            else if (weights[i] < 0 || --weights[i] < weight)
            {
                weight = weights[i];
                windex = i;
            }
        }
        if (result < 0)
        {
            textures[windex] = texture;
            weights[windex] = 100;
            ((Texture)texture).plugin_.Bind(offset + (result = windex));
        }
        else
            reused = true;

        return result;
    }
    
}
