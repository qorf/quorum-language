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
import quorum.Libraries.Game.Graphics.ModelData.ModelMaterial;
import quorum.Libraries.Game.Graphics.ModelData.ModelTextureData;
import quorum.Libraries.Game.Graphics.VertexAttribute;
import quorum.Libraries.Game.Graphics.Color;
import quorum.Libraries.Compute.Vector2;

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
    
    private void ParseMaterials(ModelData modelData, JsonValue json, String materialDir)
    {
        JsonValue materials = json.Get("materials");
        if (materials == null)
        {
            // Could possibly create a default material in this case -- libGDX does not handle this currently.
        }
        else
        {
            modelData.materials.SetSize(materials.size);
            for (JsonValue material = materials.child; material != null; material = material.next)
            {
                ModelMaterial jsonMaterial = new ModelMaterial();
                
                String id = material.GetString("id", null);
                if (id == null)
                    throw new GameRuntimeError("The material did not have an ID.");
                
                jsonMaterial.id = id;
                
                final JsonValue diffuse = material.Get("diffuse");
                if (diffuse != null)
                    jsonMaterial.diffuse = ParseColor(diffuse);
                
                final JsonValue ambient = material.Get("ambient");
                if (ambient != null)
                    jsonMaterial.ambient = ParseColor(ambient);
                
                final JsonValue emissive = material.Get("emissive");
                if (emissive != null)
                    jsonMaterial.emissive = ParseColor(emissive);
                
                final JsonValue specular = material.Get("specular");
                if (specular != null)
                    jsonMaterial.specular = ParseColor(specular);
                
                final JsonValue reflection = material.Get("reflection");
                if (reflection != null)
                    jsonMaterial.reflection = ParseColor(reflection);
                
                jsonMaterial.shininess = material.GetFloat("shininess", 0.0f);
                jsonMaterial.opacity = material.GetFloat("opacity", 1.0f);
                
                JsonValue textures = material.Get("textures");
                if (textures != null)
                {
                    for (JsonValue texture = textures.child; texture != null; texture = texture.next)
                    {
                        ModelTextureData jsonTexture = new ModelTextureData();
                        
                        String textureID = texture.GetString("id", null);
                        if (textureID == null)
                            throw new GameRuntimeError("The texture did not have an ID.");
                        jsonTexture.id = textureID;
                        
                        String fileName = texture.GetString("filename", null);
                        if (fileName == null)
                            throw new GameRuntimeError("The texture does not have an associated file name.");
                        jsonTexture.fileName = materialDir + (materialDir.length() == 0 || materialDir.endsWith("/") ? "" : "/") + fileName;
                        
                        jsonTexture.uvTranslation = ReadVector2(texture.Get("uvTranslation"), 0f, 0f);
                        jsonTexture.uvScaling = ReadVector2(texture.Get("uvScaling"), 1f, 1f);
                        
                        String textureType = texture.GetString("type", null);
                        if (textureType == null)
                            throw new GameRuntimeError("The texture did not have a provided type.");
                        
                        jsonTexture.usage = ParseTextureUsage(textureType);
                        
                        jsonMaterial.textures.Add(jsonTexture);
                    }
                }
                modelData.materials.Add(jsonMaterial);
            }
        }
    }
    
    private int ParseTextureUsage(final String value)
    {
        ModelTextureData modelTexture = new ModelTextureData();
        if (value.equalsIgnoreCase("AMBIENT"))
            return modelTexture.USAGE_AMBIENT;
        else if (value.equalsIgnoreCase("BUMP"))
            return modelTexture.USAGE_BUMP;
        else if (value.equalsIgnoreCase("DIFFUSE"))
            return modelTexture.USAGE_DIFFUSE;
        else if (value.equalsIgnoreCase("EMISSIVE"))
            return modelTexture.USAGE_EMISSIVE;
        else if (value.equalsIgnoreCase("NONE"))
            return modelTexture.USAGE_NONE;
        else if (value.equalsIgnoreCase("NORMAL"))
            return modelTexture.USAGE_NORMAL;
        else if (value.equalsIgnoreCase("REFLECTION"))
            return modelTexture.USAGE_REFLECTION;
        else if (value.equalsIgnoreCase("SHININESS"))
            return modelTexture.USAGE_SHININESS;
        else if (value.equalsIgnoreCase("SPECULAR"))
            return modelTexture.USAGE_SPECULAR;
        else if (value.equalsIgnoreCase("TRANSPARENCY")) 
            return modelTexture.USAGE_TRANSPARENCY;
        return modelTexture.USAGE_UNKNOWN;
    }
    
    private Color ParseColor(JsonValue colorArray)
    {
        if (colorArray.size >= 3)
        {
            Color color =  new Color();
            color.SetColor(colorArray.GetFloat(0), colorArray.GetFloat(1), colorArray.GetFloat(2), 1.0f);
            return color;
        }
        else
            throw new GameRuntimeError("Expected at least 3 Color values.");
    }
    
    private Vector2 ReadVector2(JsonValue vectorArray, float x, float y)
    {
        if (vectorArray == null)
        {
            Vector2 vector2 = new Vector2();
            vector2.Set(x, y);
            return vector2;
        }
        else if (vectorArray.size == 2)
        {
            Vector2 vector2 = new Vector2();
            vector2.Set(vectorArray.GetFloat(0), vectorArray.GetFloat(1));
            return vector2;
        }
        else
            throw new GameRuntimeError("Expected at least two values for Vector2.");
    }
    
}
