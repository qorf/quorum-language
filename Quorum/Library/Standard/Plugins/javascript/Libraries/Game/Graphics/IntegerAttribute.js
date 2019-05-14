function plugins_quorum_Libraries_Game_Graphics_IntegerAttribute_(quorumObject) 
{
    var attributePlugin = quorumObject.Libraries_Game_Graphics_Attribute__.plugin_;
    
    this.CULL_FACE = attributePlugin.Register("cullFace");
    
    this.GetCullFaceValue = function() 
    {
        return this.CULL_FACE;
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
