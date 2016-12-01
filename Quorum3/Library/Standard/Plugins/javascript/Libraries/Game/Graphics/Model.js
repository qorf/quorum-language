function plugins_quorum_Libraries_Game_Graphics_Model_() 
{
    if (!plugins_quorum_Libraries_Game_Graphics_Model_.initialized_plugins_quorum_Libraries_Game_Graphics_Model_)
    {
        plugins_quorum_Libraries_Game_Graphics_Model_.initialized_plugins_quorum_Libraries_Game_Graphics_Model_ = true;
        
        plugins_quorum_Libraries_Game_Graphics_Model_.reader = new quorum_Libraries_Game_Graphics_ModelLoaders_ModelReader_();
        plugins_quorum_Libraries_Game_Graphics_Model_.builder = new quorum_Libraries_Game_Graphics_ModelBuilder_();
        plugins_quorum_Libraries_Game_Graphics_Model_.hashTable = {};
        plugins_quorum_Libraries_Game_Graphics_Model_.dimensionsTable = {};
        plugins_quorum_Libraries_Game_Graphics_Model_.calcBox = new quorum_Libraries_Game_BoundingBox_();
    }
    

    this.GetCachedBlueprint$quorum_Libraries_System_File = function(file) 
    {
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[file.GetPath()];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.reader.Read$quorum_Libraries_System_File(file);
            blueprint.id = file.GetPath();
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[file.GetPath()] = blueprint;
        }
        
        return blueprint;
    };
    
    this.GetCachedDimensions$quorum_Libraries_Game_Graphics_ModelBlueprint = function(schematic) 
    {
        if (schematic.id === undefined || schematic.id === null)
            return null;
        
        var vector = plugins_quorum_Libraries_Game_Graphics_Model_.dimensionsTable[schematic.id];
        if (vector === undefined || vector === null)
        {
            vector = schematic.CalculateBoundingBox$quorum_Libraries_Game_BoundingBox(plugins_quorum_Libraries_Game_Graphics_Model_.calcBox).GetDimensions();
            plugins_quorum_Libraries_Game_Graphics_Model_.dimensionsTable[schematic.id] = vector;
        }
        return vector;
    };
    
    this.GetCachedBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Color = function(width, height, depth, color) 
    {
        var blendKey;
        if (color.GetAlpha() < 1.0)
            blendKey = "BLENDED:";
        else
            blendKey = "";
        
        var searchKey = ":BOX:DIFFUSE:" + blendKey + width + ":" + height + ":" + depth;
        
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.builder.CreateBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Color(width, height, depth, color);
            blueprint.id = searchKey;
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };

    this.GetCachedCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Color = function(width, height, depth, divisions, color) 
    {
        var blendKey;
        if (color.GetAlpha() < 1.0)
            blendKey = "BLENDED:";
        else
            blendKey = "";
        
        var searchKey = ":CYLINDER:DIFFUSE:" + blendKey + width + ":" + height + ":" + depth + ":" + divisions;
        
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.builder.CreateCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Color(width, height, depth, divisions, color);
            blueprint.id = searchKey;
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };

    this.GetCachedSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Color = function(width, height, depth, hDivisions, vDivisions, color) 
    {
        var blendKey;
        if (color.GetAlpha() < 1.0)
            blendKey = "BLENDED:";
        else
            blendKey = "";
        
        var searchKey = ":SPHERE:DIFFUSE:" + blendKey + width + ":" + height + ":" + depth + ":" + hDivisions + ":" + vDivisions;
        
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.builder.CreateSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Color(width, height, depth, hDivisions, vDivisions, color);
            blueprint.id = searchKey;
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };

    this.GetCachedBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Texture = function(width, height, depth, texture) 
    {
        var searchKey = ":BOX:TEXTURED:" + width + ":" + height + ":" + depth;
        
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.builder.CreateBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Texture(width, height, depth, texture);
            blueprint.id = searchKey;
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };

    this.GetCachedCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Texture = function(width, height, depth, divisions, texture) 
    {
        var searchKey = ":CYLINDER:TEXTURED:" + width + ":" + height + ":" + depth + ":" + divisions;
        
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.builder.CreateCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Texture(width, height, depth, divisions, texture);
            blueprint.id = searchKey;
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };

    this.GetCachedSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Texture = function(width, height, depth, hDivisions, vDivisions, texture) 
    {
        var searchKey = ":SPHERE:TEXTURED:" + width + ":" + height + ":" + depth + ":" + hDivisions + ":" + vDivisions;
        
        var blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = plugins_quorum_Libraries_Game_Graphics_Model_.builder.CreateSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Texture(width, height, depth, hDivisions, vDivisions, texture);
            blueprint.id = searchKey;
            plugins_quorum_Libraries_Game_Graphics_Model_.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };
}
