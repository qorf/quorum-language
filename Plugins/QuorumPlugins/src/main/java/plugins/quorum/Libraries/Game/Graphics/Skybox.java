/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.Graphics.Texture_;
import quorum.Libraries.Game.Graphics.PixelMap;
import quorum.Libraries.System.File_;

/**
 *
 * @author alleew
 */
public class Skybox 
{
    public java.lang.Object me_ = null;
    
    public void InitializeCubeMap(Texture_ texture)
    {
        Texture texturePlugin = ((quorum.Libraries.Game.Graphics.Texture)texture).plugin_;
        texturePlugin.SetGraphicsInfo(GraphicsManager.GL_TEXTURE_CUBE_MAP, texturePlugin.CreateGLHandle());
        texturePlugin.Bind();
        
        GraphicsManager graphics = GameStateManager.nativeGraphics;
        graphics.SetTextureParameter(GraphicsManager.GL_TEXTURE_CUBE_MAP, GraphicsManager.GL_TEXTURE_MAG_FILTER, GraphicsManager.GL_LINEAR);
        graphics.SetTextureParameter(GraphicsManager.GL_TEXTURE_CUBE_MAP, GraphicsManager.GL_TEXTURE_MIN_FILTER, GraphicsManager.GL_LINEAR);
        graphics.SetTextureParameter(GraphicsManager.GL_TEXTURE_CUBE_MAP, GraphicsManager.GL_TEXTURE_WRAP_S, GraphicsManager.GL_CLAMP_TO_EDGE);
        graphics.SetTextureParameter(GraphicsManager.GL_TEXTURE_CUBE_MAP, GraphicsManager.GL_TEXTURE_WRAP_T, GraphicsManager.GL_CLAMP_TO_EDGE);
        graphics.SetTextureParameter(GraphicsManager.GL_TEXTURE_CUBE_MAP, GraphicsManager.GL_TEXTURE_WRAP_R, GraphicsManager.GL_CLAMP_TO_EDGE);

        // Flush the error queue -- on certain Android devices, spurious errors can be recorded by the last set texture parameter call.
        int errorCode = GameStateManager.nativeGraphics.GetGraphicsErrorCode();
        while (errorCode != 0) {
            errorCode = GameStateManager.nativeGraphics.GetGraphicsErrorCode();
        }

        // Make sure the texture will be reloaded after a context loss.
        texturePlugin.AddReloadableTexture();
    }
    
    public void LoadSide(File_ file, Texture_ texture, int side)
    {
        PixelMap map = new PixelMap();
        map.LoadPixelMap(file);
        
        texture.Bind();
        map.Define2DImage(side, 0, 0);
        map.Dispose();
        
        switch(side)
        {
            case GraphicsManager.GL_TEXTURE_CUBE_MAP_POSITIVE_X:
                ((quorum.Libraries.Game.Graphics.Skybox)me_).rightSource = file;
                break;
            case GraphicsManager.GL_TEXTURE_CUBE_MAP_NEGATIVE_X:
                ((quorum.Libraries.Game.Graphics.Skybox)me_).leftSource = file;
                break;
            case GraphicsManager.GL_TEXTURE_CUBE_MAP_POSITIVE_Y:
                ((quorum.Libraries.Game.Graphics.Skybox)me_).upSource = file;
                break;
            case GraphicsManager.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y:
                ((quorum.Libraries.Game.Graphics.Skybox)me_).downSource = file;
                break;
            case GraphicsManager.GL_TEXTURE_CUBE_MAP_POSITIVE_Z:
                ((quorum.Libraries.Game.Graphics.Skybox)me_).backSource = file;
                break;
            case GraphicsManager.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z:
                ((quorum.Libraries.Game.Graphics.Skybox)me_).frontSource = file;
                break;
        }
    }
}
