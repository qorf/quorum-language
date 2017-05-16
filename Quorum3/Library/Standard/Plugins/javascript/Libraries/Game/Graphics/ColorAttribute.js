function plugins_quorum_Libraries_Game_Graphics_ColorAttribute_(quorumObject) 
{
    var attributePlugin = quorumObject.Libraries_Game_Graphics_Attribute__.plugin_;
    
    this.DIFFUSE = attributePlugin.Register("diffuseColor");
    this.SPECULAR = attributePlugin.Register("specularColor");
    this.AMBIENT = attributePlugin.Register("ambientColor");
    this.EMISSIVE = attributePlugin.Register("emissiveColor");
    this.REFLECTION = attributePlugin.Register("reflectionColor");
    this.AMBIENT_LIGHT = attributePlugin.Register("ambientLightColor");
    this.FOG = attributePlugin.Register("fogColor");
    
    this.MASK = this.DIFFUSE | this.SPECULAR | this.AMBIENT | this.EMISSIVE 
        | this.REFLECTION | this.AMBIENT_LIGHT | this.FOG;
    
    this.SupportsAttribute$quorum_integer = function(type) 
    {
        return (type & this.MASK) !== 0;
    };
    
    this.GetDiffuseValue = function() 
    {
        return this.DIFFUSE;
    };
    
    this.GetSpecularValue = function() 
    {
        return this.SPECULAR;
    };
    
    this.GetAmbientValue = function() 
    {
        return this.AMBIENT;
    };
    
    this.GetEmissiveValue = function() 
    {
        return this.EMISSIVE;
    };
    
    this.GetReflectionValue = function() 
    {
        return this.REFLECTION;
    };
    
    this.GetAmbientLightValue = function() 
    {
        return this.AMBIENT_LIGHT;
    };
    
    this.GetFogValue = function() 
    {
        return this.FOG;
    };
    
    this.GetColorAttributeMask = function() 
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
