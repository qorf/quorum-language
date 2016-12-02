function plugins_quorum_Libraries_Game_Graphics_NumberAttribute_(quorumObject) 
{

    var attributePlugin = quorumObject.Libraries_Game_Graphics_Attribute__.plugin_;
    
    this.SHININESS = attributePlugin.Register("shininess");
    this.ALPHA_TEST = attributePlugin.Register("alphaTest");

    this.GetShininessValue = function() 
    {
        return this.SHININESS;
    };
    
    this.GetAlphaTestValue = function() 
    {
        return this.ALPHA_TEST;
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
