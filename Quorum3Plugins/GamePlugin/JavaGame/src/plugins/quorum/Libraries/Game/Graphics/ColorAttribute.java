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
public class ColorAttribute extends Attribute
{
    public java.lang.Object me_ = null;
    
    public final static int DIFFUSE = Attribute.RegisterStatic("diffuseColor");
    public final static int SPECULAR = Attribute.RegisterStatic("specularColor");
    public final static int AMBIENT = Attribute.RegisterStatic("ambientColor");
    public final static int EMISSIVE = Attribute.RegisterStatic("emissiveColor");
    public final static int REFLECTION = Attribute.RegisterStatic("reflectionColor");
    public final static int AMBIENT_LIGHT = Attribute.RegisterStatic("ambientLightColor");
    public final static int FOG = Attribute.RegisterStatic("fogColor");
    
    public final static int MASK = DIFFUSE | SPECULAR | AMBIENT | EMISSIVE | REFLECTION | AMBIENT_LIGHT | FOG;
    
    public boolean SupportsAttribute(int type)
    {
        return (type & MASK) != 0;
    }
    
    public int GetDiffuseValue()
    {
        return DIFFUSE;
    }
    
    public int GetSpecularValue()
    {
        return SPECULAR;
    }
    
    public int GetAmbientValue()
    {
        return AMBIENT;
    }
    
    public int GetEmissiveValue()
    {
        return EMISSIVE;
    }
    
    public int GetReflectionValue()
    {
        return REFLECTION;
    }
    
    public int GetAmbientLightValue()
    {
        return AMBIENT_LIGHT;
    }
    
    public int GetFogValue()
    {
        return FOG;
    }
    
    public int GetColorAttributeMask()
    {
        return MASK;
    }
}
