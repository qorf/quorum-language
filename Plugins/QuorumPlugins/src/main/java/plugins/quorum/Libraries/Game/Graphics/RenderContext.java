/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.GameStateManager;

/**
 *
 * @author alleew
 */
public class RenderContext 
{
    public final TextureBinder textureBinder = new TextureBinder();
    private boolean blending;
    private int blendSFactor;
    private int blendDFactor;
    private int depthFunc;
    private float depthRangeNear;
    private float depthRangeFar;
    private boolean depthMask;
    private int cullFace;
    
    public void Begin()
    {
        GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_DEPTH_TEST);
        depthFunc = 0;
        GameStateManager.nativeGraphics.glDepthMask(true);
        depthMask = true;
        GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        blending = false;
        GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        cullFace = blendSFactor = blendDFactor = 0;
        textureBinder.Begin();
    }
    
    public void End()
    {
        if (depthFunc != 0)
            GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_DEPTH_TEST);
        if (!depthMask)
            GameStateManager.nativeGraphics.glDepthMask(true);
        if (blending)
            GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        if (cullFace > 0)
            GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        textureBinder.End();
    }
    
    public void SetDepthMask(final boolean mask)
    {
        if (depthMask != mask)
        {
            GameStateManager.nativeGraphics.glDepthMask(mask);
            depthMask = mask;
        }
    }
    
    public void SetDepthTest(final int depthFunction)
    {
        SetDepthTest(depthFunction, 0f, 1f);
    }
    
    public void SetDepthTest(final int depthFunction, final float depthRangeNear, final float depthRangeFar)
    {
        final boolean wasEnabled = depthFunc != 0;
        final boolean enabled = depthFunction != 0;
        if (depthFunc != depthFunction)
        {
            depthFunc = depthFunction;
            if (enabled)
            {
                GameStateManager.nativeGraphics.glEnable(GraphicsManager.GL_DEPTH_TEST);
                GameStateManager.nativeGraphics.glDepthFunc(depthFunction);
            }
            else
                GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_DEPTH_TEST);
        }
        if (enabled)
        {
            if (!wasEnabled || depthFunc != depthFunction)
                GameStateManager.nativeGraphics.glDepthFunc(depthFunc = depthFunction);
            if (!wasEnabled || this.depthRangeNear != depthRangeNear || this.depthRangeFar != depthRangeFar)
            {
                GameStateManager.nativeGraphics.glDepthRangef(depthRangeNear, depthRangeFar);
                this.depthRangeNear = depthRangeNear;
                this.depthRangeFar = depthRangeFar;
            }
        }
    }
    
    public void SetBlending(final boolean enabled, final int sFactor, final int dFactor)
    {
        if (enabled != blending)
        {
            blending = enabled;
            if (enabled)
                GameStateManager.nativeGraphics.glEnable(GraphicsManager.GL_BLEND);
            else
                GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        }
        
        if (enabled && (blendSFactor != sFactor || blendDFactor != dFactor))
        {
            GameStateManager.nativeGraphics.glBlendFunc(sFactor, dFactor);
            blendSFactor = sFactor;
            blendDFactor = dFactor;
        }
    }
    
    public void SetCullFace(final int face)
    {
        if (face != cullFace)
        {
            cullFace = face;
            if ((face == GraphicsManager.GL_FRONT) || (face == GraphicsManager.GL_BACK) || (face == GraphicsManager.GL_FRONT_AND_BACK))
            {
                GameStateManager.nativeGraphics.glEnable(GraphicsManager.GL_CULL_FACE);
                GameStateManager.nativeGraphics.glCullFace(face);
            }
            else
                GameStateManager.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        }
    }
    
}
