function plugins_quorum_Libraries_Game_Graphics_Attribute_() 
{
 
    if (!plugins_quorum_Libraries_Game_Graphics_Attribute_.initialized_plugins_quorum_Libraries_Game_Graphics_Attribute_)
    {
        plugins_quorum_Libraries_Game_Graphics_Attribute_.initialized_plugins_quorum_Libraries_Game_Graphics_Attribute_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_Attribute_.types = [];
        plugins_quorum_Libraries_Game_Graphics_Attribute_.typesSize = 0;
    }
    
    this.GetAttributeType$quorum_text = function(alias) 
    {
        var a = alias.toUpperCase();
        
        for (var i = 0; i < plugins_quorum_Libraries_Game_Graphics_Attribute_.typesSize; i++)
        {
            if (plugins_quorum_Libraries_Game_Graphics_Attribute_.types[i].toUpperCase() === a)
                return 1 << i;
        }
        
        return 0;
    };
    
    this.GetAttributeAlias$quorum_integer = function(type) 
    {
        var index = -1;
        while (type !== 0 && ++index < 31 && (((type >> index) & 1) === 0))
        {
            // Do nothing -- the loop counter affects the index as a side effect.
            // When the loop finishes, the index will be the value we need.
        }
        if (index >= 0 && index < typesSize)
            return plugins_quorum_Libraries_Game_Graphics_Attribute_.types[index];
        else
            return null;
    };
    
    this.Register = function(alias)
    {
        var result = this.GetAttributeType$quorum_text(alias);
        if (result > 0)
            return result;
        
        if (plugins_quorum_Libraries_Game_Graphics_Attribute_.typesSize >= 32)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Can't register more than 32 types of attributes.");
            throw exceptionInstance_;
        }
        
        plugins_quorum_Libraries_Game_Graphics_Attribute_.types[plugins_quorum_Libraries_Game_Graphics_Attribute_.typesSize] = alias;
        plugins_quorum_Libraries_Game_Graphics_Attribute_.typesSize++;
        return (1 << plugins_quorum_Libraries_Game_Graphics_Attribute_.typesSize - 1);
    };
}
