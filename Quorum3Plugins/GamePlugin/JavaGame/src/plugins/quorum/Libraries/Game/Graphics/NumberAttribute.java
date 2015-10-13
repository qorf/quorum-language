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
public class NumberAttribute extends Attribute
{
    public java.lang.Object me_ = null;
    
    public static final int SHININESS = RegisterStatic("shininess");
    public static final int ALPHA_TEST = RegisterStatic("alphaTest");
    
    public int GetShininessValue()
    {
        return SHININESS;
    }
    
    public int GetAlphaTestValue()
    {
        return ALPHA_TEST;
    }
}
