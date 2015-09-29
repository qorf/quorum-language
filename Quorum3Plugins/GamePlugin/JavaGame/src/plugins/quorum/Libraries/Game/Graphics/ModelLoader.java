/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import quorum.Libraries.Game.Graphics.ModelData.ModelData_;
import quorum.Libraries.Game.Graphics.ModelData.ModelData;
import quorum.Libraries.Game.Graphics.ModelData.ModelMesh;
import quorum.Libraries.Game.Graphics.ModelData.ModelMeshPart;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.System.File_;

import plugins.quorum.Libraries.Game.GameRuntimeError;
import java.io.File;

/**
 *
 * @author alleew
 */
public class ModelLoader 
{
    public java.lang.Object me_ = null;
    
    public static final short VERSION_HIGH = 0;
    public static final short VERSION_LOW = 1;
    protected final JsonReader reader = new JsonReader();
    
    public ModelData_ LoadModelData(File_ quorumFile)
    {
        File file = new File(quorumFile.GetAbsolutePath());
        
        JsonValue json = reader.parse(file);
        ModelData modelData = new ModelData();
        JsonValue version = json.Require("version");
        modelData.SetVersion(0, (int)version.GetShort(0));
        modelData.SetVersion(1, (int)version.GetShort(1));
        
        if (modelData.GetVersion(0) != VERSION_HIGH || modelData.GetVersion(1) != VERSION_LOW)
            throw new GameRuntimeError("Model version not supported.");
        
        modelData.id = json.GetString("id", "");
        //parseMeshes(modelData, json);
        //parseMaterials(model, json, handle.parent().path());
        //parseNodes(model, json);
        //parseAnimations(model, json);
        
        return modelData;
    }
    
    private void ParseMeshes(ModelData modelData, JsonValue json)
    {
        JsonValue meshes = json.Get("meshes");
        if (meshes != null)
        {
            modelData.meshes.SetMaxSize(meshes.GetSize());
            for (JsonValue mesh = meshes.child; mesh != null; mesh = mesh.next)
            {
                ModelMesh jsonMesh = new ModelMesh();
                
                String id = mesh.GetString("id", "");
                jsonMesh.id = id;
                
                JsonValue attributes = mesh.Require("attributes");
                //jsonMesh.attributes = parseAttributes(attributes);
                //jsonMesh.vertices = mesh.require("vertices").asFloatArray();
                
                JsonValue meshParts = mesh.Require("parts");
                Array_ parts = jsonMesh.parts;
                for (JsonValue meshPart = meshParts.child; meshPart != null; meshPart = meshPart.next)
                {
                    
                }
            }
        }
    }
}
