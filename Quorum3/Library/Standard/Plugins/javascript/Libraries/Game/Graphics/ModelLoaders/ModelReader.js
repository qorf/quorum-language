/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function plugins_quorum_Libraries_Game_Graphics_ModelLoaders_ModelReader_(quorumReader)
{
    var me_ = quorumReader;
    
    this.ReadAsynchronously$quorum_Libraries_System_File = function(file)
    {
        var listener = new quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_WavefrontConverterListener_();
        var objRequest = new XMLHttpRequest();
        objRequest.onreadystatechange = function(event)
        {
            if (objRequest.readyState === 4)
            {
                if (objRequest.status === 200)
                {
                    var lines = objRequest.responseText.split("\n");
                    for (var i = 0; i < lines.length; i++)
                    {
                        var value = lines[i];
                        var lexer = new quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_WavefrontObjectLexer_();
                        lexer.SetListener$quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_WavefrontObjectListener(listener);
                        lexer.Read$quorum_text(value);
                        
                        var parser = new quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_WavefrontObjectParser_();
                        parser.SetListener$quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_WavefrontObjectListener(listener);
                        parser.Parse$quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_WavefrontObjectLexer(lexer);
                    }
                    
                    var materials = new quorum_Libraries_Containers_Array_();
                    var materialsList;
                    
                    for (var i = 0; i < materials.GetSize(); i++)
                    {
                        var source = materials.Get$quorum_integer(i);
                        var materialListener = new quorum_Libraries_Game_Graphics_ModelLoaders_WavefrontObject_MaterialConverterListener_();
                        
                    }
                }
                else
                    console.log("There was an error attempting to load model file " + file.GetAbsolutePath());
            }
        };
        
        objRequest.open("GET", file.GetAbsolutePath());
        objRequest.send();
    };
}

/*
 
        Array<text> materials = listener:GetMaterialLibraries()
        Array<Material> materialsList
        i = 0
        repeat while i < materials:GetSize()
            text source = materials:Get(i)
            MaterialConverterListener materialListener

            //setup the file
            FileReader materialReader
            File materialFile
            File parentDirectory = file:GetParentDirectory()
            materialFile:SetWorkingDirectory(parentDirectory:GetWorkingDirectory())
            materialFile:SetPath(source)

            materialReader:OpenForRead(materialFile)
            repeat while not materialReader:IsAtEndOfFile()
                text value = materialReader:ReadLine()
                //now parse it
                MaterialLexer lexer
                lexer:SetListener(materialListener)
                lexer:Read(value)

                MaterialParser parser
                parser:SetListener(materialListener)
                parser:Parse(lexer)
            end
            i = i + 1

            Array<Material> value = materialListener:GetMaterials()
            k = 0
            repeat while k < value:GetSize()
                materialsList:Add(value:Get(k))
                k = k + 1
            end
        end

        ModelBlueprint schematic = ConstructWavefrontObject(listener:GetPositions(), listener:GetTextures(), listener:GetNormals(),
            listener:GetFaces(), materialsList, listener:GetUseMaterialIndices(), listener:GetUseMaterialNames(), file)

        return schematic
    end
 
 */

