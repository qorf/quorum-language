function plugins_quorum_Libraries_Game_Graphics_TextureAttribute_(quorumObject) 
{
    var attributePlugin = quorumObject.Libraries_Game_Graphics_Attribute__.plugin_;
    
    this.DIFFUSE_TEXTURE = attributePlugin.Register("diffuseTexture");
    this.SPECULAR_TEXTURE = attributePlugin.Register("specularTexture");
    this.BUMP_TEXTURE = attributePlugin.Register("bumpTexture");
    this.NORMAL_TEXTURE = attributePlugin.Register("normalTexture");
    this.AMBIENT_TEXTURE = attributePlugin.Register("ambientTexture");
    this.EMISSIVE_TEXTURE = attributePlugin.Register("emissiveTexture");
    this.REFLECTION_TEXTURE = attributePlugin.Register("reflectionTexture");
    this.MASK = this.DIFFUSE_TEXTURE | this.SPECULAR_TEXTURE | this.BUMP_TEXTURE
        | this.NORMAL_TEXTURE | this.AMBIENT_TEXTURE | this.EMISSIVE_TEXTURE | this.REFLECTION_TEXTURE;
    
    this.SupportsAttribute$quorum_integer = function(type) 
    {
        return (type & this.MASK) !== 0;
    };
    
    this.GetDiffuseValue = function() 
    {
        return this.DIFFUSE_TEXTURE;
    };
    
    this.GetSpecularValue = function() 
    {
        return this.SPECULAR_TEXTURE;
    };
    
    this.GetBumpValue = function() 
    {
        return this.BUMP_TEXTURE;
    };
    
    this.GetNormalValue = function() 
    {
        return this.NORMAL_TEXTURE;
    };
    
    this.GetAmbientValue = function() 
    {
        return this.AMBIENT_TEXTURE;
    };
    
    this.GetEmissiveValue = function() 
    {
        return this.EMISSIVE_TEXTURE;
    };
    
    this.GetReflectionValue = function() 
    {
        return this.REFLECTION_TEXTURE;
    };
    
    this.GetAttributeMask = function() 
    {
        return this.MASK;
    };
    
    this.GetAttributeType$quorum_text = function(alias)
    {
        attributePlugin.GetAttributeType$quorum_text(alias);
    };
    
    this.GetAttributeAlias$quorum_integer = function(type)
    {
        attributePlugin.GetAttributeAlias$quorum_integer(type);
    };
    
    this.Register = function(alias)
    {
        attributePlugin.Register(alias);
    };
}
