function plugins_quorum_Libraries_Game_Graphics_DepthTestAttribute_(quorumObject) 
{
    var attributePlugin = quorumObject.Libraries_Game_Graphics_Attribute__.plugin_;
    
    this.DEPTH_TEST = attributePlugin.Register("depthTest");
    
    this.GetDepthTestValue = function() 
    {
        return this.DEPTH_TEST;
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
