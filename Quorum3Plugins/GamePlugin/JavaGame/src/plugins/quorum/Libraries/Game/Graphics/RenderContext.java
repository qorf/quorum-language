/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.GameState;

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
        GameState.nativeGraphics.glDisable(GraphicsManager.GL_DEPTH_TEST);
        depthFunc = 0;
        GameState.nativeGraphics.glDepthMask(true);
        depthMask = true;
        GameState.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        blending = false;
        GameState.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        cullFace = blendSFactor = blendDFactor = 0;
        textureBinder.Begin();
    }
    
    public void End()
    {
        if (depthFunc != 0)
            GameState.nativeGraphics.glDisable(GraphicsManager.GL_DEPTH_TEST);
        if (!depthMask)
            GameState.nativeGraphics.glDepthMask(true);
        if (blending)
            GameState.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        if (cullFace > 0)
            GameState.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        textureBinder.End();
    }
    
    public void SetDepthMask(final boolean mask)
    {
        if (depthMask != mask)
        {
            GameState.nativeGraphics.glDepthMask(mask);
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
                GameState.nativeGraphics.glEnable(GraphicsManager.GL_DEPTH_TEST);
                GameState.nativeGraphics.glDepthFunc(depthFunction);
            }
            else
                GameState.nativeGraphics.glDisable(GraphicsManager.GL_DEPTH_TEST);
        }
        if (enabled)
        {
            if (!wasEnabled || depthFunc != depthFunction)
                GameState.nativeGraphics.glDepthFunc(depthFunc = depthFunction);
            if (!wasEnabled || this.depthRangeNear != depthRangeNear || this.depthRangeFar != depthRangeFar)
            {
                GameState.nativeGraphics.glDepthRangef(depthRangeNear, depthRangeFar);
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
                GameState.nativeGraphics.glEnable(GraphicsManager.GL_BLEND);
            else
                GameState.nativeGraphics.glDisable(GraphicsManager.GL_BLEND);
        }
        
        if (enabled && (blendSFactor != sFactor || blendDFactor != dFactor))
        {
            GameState.nativeGraphics.glBlendFunc(sFactor, dFactor);
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
                GameState.nativeGraphics.glEnable(GraphicsManager.GL_CULL_FACE);
                GameState.nativeGraphics.glCullFace(face);
            }
            else
                GameState.nativeGraphics.glDisable(GraphicsManager.GL_CULL_FACE);
        }
    }
    
}
