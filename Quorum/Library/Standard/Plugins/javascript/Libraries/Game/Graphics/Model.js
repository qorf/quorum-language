function plugins_quorum_Libraries_Game_Graphics_Model_() 
{
    var activeGame = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo();
    var modelInfo = activeGame.plugins_quorum_Libraries_Game_Graphics_Model_;
    if (!activeGame.plugins_quorum_Libraries_Game_Graphics_Model_)
    {
        modelInfo = {};
        activeGame.plugins_quorum_Libraries_Game_Graphics_Model_ = modelInfo;
        
        modelInfo.reader = new quorum_Libraries_Game_Graphics_ModelLoaders_ModelReader_();
        modelInfo.builder = new quorum_Libraries_Game_Graphics_ModelBuilder_();
        modelInfo.hashTable = {};
        modelInfo.dimensionsTable = {};
        modelInfo.calcBox = new quorum_Libraries_Game_BoundingBox_();
    }
    

    this.GetCachedBlueprint$quorum_Libraries_System_File = function(file) 
    {
        var blueprint = modelInfo.hashTable[file.GetPath()];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.reader.Read$quorum_Libraries_System_File(file);
            blueprint.id = file.GetPath();
            modelInfo.hashTable[file.GetPath()] = blueprint;
        }
        
        return blueprint;
    };
    
    this.GetCachedDimensions$quorum_Libraries_Game_Graphics_ModelBlueprint = function(schematic) 
    {
        if (schematic.id === undefined || schematic.id === null)
            return null;
        
        var vector = modelInfo.dimensionsTable[schematic.id];
        if (vector === undefined || vector === null)
        {
            vector = schematic.CalculateBoundingBox$quorum_Libraries_Game_BoundingBox(modelInfo.calcBox).GetDimensions();
            modelInfo.dimensionsTable[schematic.id] = vector;
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
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreateBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Color(width, height, depth, color);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
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
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreateCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Color(width, height, depth, divisions, color);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
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
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreateSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Color(width, height, depth, hDivisions, vDivisions, color);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };

    this.GetCachedBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Texture = function(width, height, depth, texture) 
    {
        var searchKey = ":BOX:TEXTURED:" + width + ":" + height + ":" + depth;
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreateBox$quorum_number$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Texture(width, height, depth, texture);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
        
            var material = blueprint.nodes.Get$quorum_integer(0).parts.Get$quorum_integer(0).material;
            var textureAttribute = new quorum_Libraries_Game_Graphics_TextureAttribute_();
            var descriptor = material.GetAttribute$quorum_integer(textureAttribute.GetDiffuseValue()).descriptor;
            descriptor.uWrap.value = descriptor.uWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
            descriptor.vWrap.value = descriptor.vWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
        }
        return blueprint;
    };

    this.GetCachedCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Texture = function(width, height, depth, divisions, texture) 
    {
        var searchKey = ":CYLINDER:TEXTURED:" + width + ":" + height + ":" + depth + ":" + divisions;
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreateCylinder$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_Libraries_Game_Graphics_Texture(width, height, depth, divisions, texture);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
        
            var material = blueprint.nodes.Get$quorum_integer(0).parts.Get$quorum_integer(0).material;
            var textureAttribute = new quorum_Libraries_Game_Graphics_TextureAttribute_();
            var descriptor = material.GetAttribute$quorum_integer(textureAttribute.GetDiffuseValue()).descriptor;
            descriptor.uWrap.value = descriptor.uWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
            descriptor.vWrap.value = descriptor.vWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
        }
        return blueprint;
    };

    this.GetCachedSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Texture = function(width, height, depth, hDivisions, vDivisions, texture) 
    {
        var searchKey = ":SPHERE:TEXTURED:" + width + ":" + height + ":" + depth + ":" + hDivisions + ":" + vDivisions;
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreateSphere$quorum_number$quorum_number$quorum_number$quorum_integer$quorum_integer$quorum_Libraries_Game_Graphics_Texture(width, height, depth, hDivisions, vDivisions, texture);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
        
            var material = blueprint.nodes.Get$quorum_integer(0).parts.Get$quorum_integer(0).material;
            var textureAttribute = new quorum_Libraries_Game_Graphics_TextureAttribute_();
            var descriptor = material.GetAttribute$quorum_integer(textureAttribute.GetDiffuseValue()).descriptor;
            descriptor.uWrap.value = descriptor.uWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
            descriptor.vWrap.value = descriptor.vWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
        }
        return blueprint;
    };
    
    this.GetCachedPlane$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Color$quorum_boolean = function(width, depth, color, doubleSided) 
    {
        var blendKey;
        if (color.GetAlpha() < 1.0)
            blendKey = "BLENDED:";
        else
            blendKey = "";
        
        var searchKey = ":PLANE:DIFFUSE:" + blendKey + width + ":" + depth + ":" + doubleSided;
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreatePlane$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Color$quorum_boolean(width, depth, color, doubleSided);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
        }
        return blueprint;
    };
    
    this.GetCachedPlane$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Texture$quorum_boolean = function(width, depth, texture, doubleSided) 
    {
        var searchKey = ":PLANE:TEXTURED:" + width + ":" + depth + ":" + doubleSided;
        
        var blueprint = modelInfo.hashTable[searchKey];
        
        if (blueprint === undefined || blueprint === null)
        {
            blueprint = modelInfo.builder.CreatePlane$quorum_number$quorum_number$quorum_Libraries_Game_Graphics_Texture$quorum_boolean(width, depth, texture, doubleSided);
            blueprint.id = searchKey;
            modelInfo.hashTable[searchKey] = blueprint;
            
            var material = blueprint.nodes.Get$quorum_integer(0).parts.Get$quorum_integer(0).material;
            var textureAttribute = new quorum_Libraries_Game_Graphics_TextureAttribute_();
            var descriptor = material.GetAttribute$quorum_integer(textureAttribute.GetDiffuseValue()).descriptor;
            descriptor.uWrap.value = descriptor.uWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
            descriptor.vWrap.value = descriptor.vWrap.Get_Libraries_Game_Graphics_TextureWrap__CLAMP_TO_EDGE_();
        }
        return blueprint;
    };
}
