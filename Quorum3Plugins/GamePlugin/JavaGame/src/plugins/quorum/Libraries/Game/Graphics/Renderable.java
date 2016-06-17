/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import quorum.Libraries.Game.Graphics.Camera_;
import quorum.Libraries.Game.Graphics.Renderable_;

/**
 *
 * @author alleew
 */
public class Renderable 
{
    public java.lang.Object me_ = null;
    
    public Camera_ camera;
    
    public Shader shader;
    
    public Camera_ GetCamera()
    {
        return camera;
    }
    
    public void SetNative(Renderable_ other)
    {
        shader = ((quorum.Libraries.Game.Graphics.Renderable)other).plugin_.shader;
    }
    
}
