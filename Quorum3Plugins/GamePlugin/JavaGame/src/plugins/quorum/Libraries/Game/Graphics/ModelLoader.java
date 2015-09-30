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
import quorum.Libraries.Game.Graphics.VertexAttribute;
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
        ParseMeshes(modelData, json);
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
                ParseAttributes(attributes, jsonMesh.attributes);
                
                float[] vertexArray = mesh.Require("vertices").AsFloatArray();
                for (int i = 0; i < vertexArray.length; i++)
                    jsonMesh.SetVertex(i, vertexArray[i]);
                
                JsonValue meshParts = mesh.Require("parts");
                Array_ parts = jsonMesh.parts;
                for (JsonValue meshPart = meshParts.child; meshPart != null; meshPart = meshPart.next)
                {
                    ModelMeshPart jsonPart = new ModelMeshPart();
                    String partID = meshPart.GetString("id", null);
                    if (partID == null)
                        throw new GameRuntimeError("No ID given for mesh part.");
                    
                    for (int i = 0; i < parts.GetSize(); i++)
                    {
                        ModelMeshPart tempPart = (ModelMeshPart)parts.Get(i);
                        if (tempPart.id.equals(partID))
                            throw new GameRuntimeError("Mesh part with ID '" + partID + "'was already defined in this mesh.");
                    }
                    jsonPart.id = partID;
                    
                    String type = meshPart.GetString("type", null);
                    if (type == null)
                        throw new GameRuntimeError("No primitive type given for mesh part '" + partID + "'.");
                    
                    jsonPart.primitiveType = ParseType(type);
                    
                    short[] indices = meshPart.Require("indices").AsShortArray();
                    for (int i = 0; i < indices.length; i++)
                        jsonPart.SetIndex(i, indices[i]);
                        
                    parts.Add(jsonPart);
                }
                modelData.meshes.Add(jsonMesh);
            }
        }
    }
    
    private int ParseType (String type) 
    {
        if (type.equals("TRIANGLES")) 
        {
            return GraphicsManager.GL_TRIANGLES;
        } 
        else if (type.equals("LINES")) 
        {
            return GraphicsManager.GL_LINES;
        }
        else if (type.equals("POINTS")) 
        {
            return GraphicsManager.GL_POINTS;
        }
        else if (type.equals("TRIANGLE_STRIP")) 
        {
            return GraphicsManager.GL_TRIANGLE_STRIP;
        } 
        else if (type.equals("LINE_STRIP")) 
        {
            return GraphicsManager.GL_LINE_STRIP;
        }
        else 
        {
            throw new GameRuntimeError("Unknown primitive type '" + type
                + "', should be one of triangle, trianglestrip, line, linestrip, lineloop or point");
        }
    }
    
    private Array_ ParseAttributes (JsonValue attributes, Array_ vertexAttributes) 
    {
        vertexAttributes.Empty();
        int unit = 0;
        int blendWeightCount = 0;
        VertexAttribute vertexAttribute = new VertexAttribute();
        for (JsonValue value = attributes.child; value != null; value = value.next) {
                String attribute = value.AsString();
                String attr = (String)attribute;
                if (attr.equals("POSITION")) {
                        vertexAttributes.Add(vertexAttribute.Position());
                } else if (attr.equals("NORMAL")) {
                        vertexAttributes.Add(vertexAttribute.Normal());
                } else if (attr.equals("COLOR")) {
                        vertexAttributes.Add(vertexAttribute.ColorUnpacked());
                } else if (attr.equals("COLORPACKED")) {
                        vertexAttributes.Add(vertexAttribute.ColorPacked());
                } else if (attr.equals("TANGENT")) {
                        vertexAttributes.Add(vertexAttribute.Tangent());
                } else if (attr.equals("BINORMAL")) {
                        vertexAttributes.Add(vertexAttribute.Binormal());
                } else if (attr.startsWith("TEXCOORD")) {
                        vertexAttributes.Add(vertexAttribute.TextureCoordinates(unit++));
                } else if (attr.startsWith("BLENDWEIGHT")) {
                        vertexAttributes.Add(vertexAttribute.BoneWeight(blendWeightCount++));
                } else {
                        throw new GameRuntimeError("Unknown vertex attribute '" + attr
                                + "', should be one of position, normal, uv, tangent or binormal");
                }
        }
        return vertexAttributes;
    }
    
}
