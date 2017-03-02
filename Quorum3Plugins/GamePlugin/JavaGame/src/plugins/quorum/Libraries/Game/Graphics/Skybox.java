/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

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

    }
    
    public void LoadSide(File_ file, Texture_ texture, int side)
    {
        
    }
    
    public boolean IsLoaded()
    {
        return ((quorum.Libraries.Game.Graphics.Skybox)me_).cubeMap != null;
    }
}
