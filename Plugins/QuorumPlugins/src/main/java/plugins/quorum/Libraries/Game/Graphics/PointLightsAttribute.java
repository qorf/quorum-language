/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

/**
 *
 * @author alleew
 */
public class PointLightsAttribute extends Attribute
{
    public java.lang.Object me_ = null;
    
    public final static int POINT_LIGHTS = RegisterStatic("pointLights");
    
    public int GetPointLightsValue()
    {
        return POINT_LIGHTS;
    }
}
