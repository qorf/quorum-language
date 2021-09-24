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
public class TextureAttribute extends Attribute
{
    public java.lang.Object me_ = null;
    
    public static final int DIFFUSE_TEXTURE = RegisterStatic("diffuseTexture");
    public static final int SPECULAR_TEXTURE = RegisterStatic("specularTexture");
    public static final int BUMP_TEXTURE = RegisterStatic("bumpTexture");
    public static final int NORMAL_TEXTURE = RegisterStatic("normalTexture");
    public static final int AMBIENT_TEXTURE = RegisterStatic("ambientTexture");
    public static final int EMISSIVE_TEXTURE = RegisterStatic("emissiveTexture");
    public static final int REFLECTION_TEXTURE = RegisterStatic("reflectionTexture");
    public static final int MASK = DIFFUSE_TEXTURE | SPECULAR_TEXTURE | BUMP_TEXTURE | NORMAL_TEXTURE | AMBIENT_TEXTURE | EMISSIVE_TEXTURE | REFLECTION_TEXTURE;
    
    public int GetDiffuseValue()
    {
        return DIFFUSE_TEXTURE;
    }
    
    public int GetSpecularValue()
    {
        return SPECULAR_TEXTURE;
    }
    
    public int GetBumpValue()
    {
        return BUMP_TEXTURE;
    }
    
    public int GetNormalValue()
    {
        return NORMAL_TEXTURE;
    }
    
    public int GetAmbientValue()
    {
        return AMBIENT_TEXTURE;
    }
    
    public int GetEmissiveValue()
    {
        return EMISSIVE_TEXTURE;
    }
    
    public int GetReflectionValue()
    {
        return REFLECTION_TEXTURE;
    }
    
    public int GetAttributeMask()
    {
        return MASK;
    }
    
    public boolean SupportsAttribute(int type)
    {
        return (type & MASK) != 0;
    }
}
