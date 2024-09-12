/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics.ModelLoaders;

import quorum.Libraries.Game.Graphics.ModelData.ModelData_;
import quorum.Libraries.Game.Graphics.ModelData.ModelData;
import quorum.Libraries.Game.Graphics.ModelData.ModelMesh;
import quorum.Libraries.Game.Graphics.ModelData.ModelMeshPart;
import quorum.Libraries.Game.Graphics.ModelData.ModelMaterial;
import quorum.Libraries.Game.Graphics.ModelData.ModelTextureData;
import quorum.Libraries.Game.Graphics.ModelData.ModelNode;
import quorum.Libraries.Game.Graphics.ModelData.ModelNodePart;
import quorum.Libraries.Game.Graphics.ModelData.ModelAnimation;
import quorum.Libraries.Game.Graphics.ModelData.ModelNodeAnimation;
import quorum.Libraries.Game.Graphics.ModelData.ModelNodeKeyframe_;
import quorum.Libraries.Game.Graphics.VertexAttribute;
import quorum.Libraries.Game.Graphics.Color;
import quorum.Libraries.Compute.Vector2;
import quorum.Libraries.Compute.Vector3;
//import quorum.Libraries.Compute.Matrix4;
import quorum.Libraries.Compute.Quaternion;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.System.File_;

import plugins.quorum.Libraries.Game.Graphics.OpenGL.OpenGLManager;
import plugins.quorum.Libraries.Game.GameRuntimeError;
import java.io.File;

/**
 *
 * @author alleew
 */
public class Graphics3DLoader 
{
    public java.lang.Object me_ = null;
    
    public static final short VERSION_HIGH = 0;
    public static final short VERSION_LOW = 1;
    protected final JsonReader jsonReader = new JsonReader();
    protected final G3dbReader g3dbReader = new G3dbReader();
    
    private final Quaternion tempQ = new Quaternion();
    
    public ModelData_ LoadModelData(File_ quorumFile)
    {
        File file = new File(quorumFile.GetAbsolutePath());
        
        JsonValue json;
        
        if (file.getAbsolutePath().endsWith(".g3dj"))
            json = jsonReader.parse(file);
        else if (file.getAbsolutePath().endsWith(".g3db"))
            json = g3dbReader.parse(file);
        else
            throw new GameRuntimeError("I couldn't recognize the extension of this model!");
        
        ModelData modelData = new ModelData();
        JsonValue version = json.Require("version");
        modelData.SetVersion(0, (int)version.GetShort(0));
        modelData.SetVersion(1, (int)version.GetShort(1));
        
        if (modelData.GetVersion(0) != VERSION_HIGH || modelData.GetVersion(1) != VERSION_LOW)
            throw new GameRuntimeError("Model version not supported.");
        
        modelData.id = json.GetString("id", "");
        ParseMeshes(modelData, json);
        ParseMaterials(modelData, json, file.getParent());
        ParseNodes(modelData, json);
        ParseAnimations(modelData, json);
        
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
                jsonMesh.vertices.SetSize(vertexArray.length);
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
                    jsonPart.indices.SetSize(indices.length);
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
            return OpenGLManager.GL_TRIANGLES;
        } 
        else if (type.equals("LINES")) 
        {
            return OpenGLManager.GL_LINES;
        }
        else if (type.equals("POINTS")) 
        {
            return OpenGLManager.GL_POINTS;
        }
        else if (type.equals("TRIANGLE_STRIP")) 
        {
            return OpenGLManager.GL_TRIANGLE_STRIP;
        } 
        else if (type.equals("LINE_STRIP")) 
        {
            return OpenGLManager.GL_LINE_STRIP;
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
    
    private Array_ ParseNodes(ModelData modelData, JsonValue json)
    {
        JsonValue nodes = json.Get("nodes");
        if (nodes != null)
        {
            for (JsonValue node = nodes.child; node != null; node = node.next)
                modelData.nodes.Add(ParseNodesRecursively(node));
        }
        
        return modelData.nodes;
    }
    
    private ModelNode ParseNodesRecursively(JsonValue json)
    {
        ModelNode jsonNode = new ModelNode();
        
        String id = json.GetString("id", null);
        if (id == null)
            throw new GameRuntimeError("Node is missing an ID.");
        jsonNode.id = id;
        
        JsonValue translation = json.Get("translation");
        if (translation != null && translation.size != 3)
            throw new GameRuntimeError("Node translation is incomplete.");
        if (translation != null)
        {
            Vector3 vec3 = new Vector3();
            vec3.Set(translation.GetFloat(0), translation.GetFloat(1), translation.GetFloat(2));
            jsonNode.translation = vec3;
        }
        else
            jsonNode.translation = null;
        
        JsonValue rotation = json.Get("rotation");
        if (rotation != null && rotation.size != 4)
            throw new GameRuntimeError("Node rotation is incomplete.");
        if (rotation != null)
        {
            Quaternion q = new Quaternion();
            q.Set(rotation.GetFloat(0), rotation.GetFloat(1), rotation.GetFloat(2), rotation.GetFloat(3));
            jsonNode.rotation = q;
        }
        else
            jsonNode.rotation = null;
        
        JsonValue scale = json.Get("scale");
        if (scale!= null && scale.size != 3)
            throw new GameRuntimeError("Node scale is incomplete.");
        if (scale != null)
        {
            Vector3 vec3 = new Vector3();
            vec3.Set(scale.GetFloat(0), scale.GetFloat(1), scale.GetFloat(2));
            jsonNode.scale = vec3;
        }
        
        String meshID = json.GetString("mesh", null);
        if (meshID != null)
            jsonNode.meshID = meshID;
        
        JsonValue materials = json.Get("parts");
        if (materials != null)
        {
            jsonNode.parts.SetSize(materials.size);
            int i = 0;
            for (JsonValue material = materials.child; material != null; material = material.next, i++)
            {
                ModelNodePart nodePart = new ModelNodePart();
                
                String meshPartID = material.GetString("meshpartid", null);
                if (meshPartID == null)
                    throw new GameRuntimeError("Node " + id + "part is missing meshPartID.");
                String materialID = material.GetString("materialid", null);
                if (materialID == null)
                    throw new GameRuntimeError("Node " + id + "part is missing materialID.");
                
                nodePart.materialID = materialID;
                nodePart.meshPartID = meshPartID;
                
                JsonValue bones = material.Get("bones");
                if (bones != null)
                {
                    int j = 0;
                    for (JsonValue bone = bones.child; bone != null; bone = bone.next, j++)
                    {
                        String nodeID = bone.GetString("node", null);
                        if (nodeID == null)
                            throw new GameRuntimeError("Bone node ID is missing.");
                        
                        //Matrix4 transform = new Matrix4();
                        
                        JsonValue val = bone.Get("translation");
                        if (val != null && val.size >= 3)
                            ;//transform.Rotate(tempQ.Set(val.GetFloat(0), val.GetFloat(1), val.GetFloat(2), val.GetFloat(3)));
                        
                        val = bone.Get("rotation");
                        if (val!= null && val.size >= 4)
                            ;//transform.Rotate(tempQ.Set(val.GetFloat(0), val.GetFloat(1), val.GetFloat(2), val.GetFloat(3)));
                        
                        val = bone.Get("scale");
                        if (val != null && val.size >= 3)
                            ;//transform.Scale(val.GetFloat(0), val.GetFloat(1), val.GetFloat(2));
                        
                        //nodePart.bones.Add(nodeID, transform);
                    }
                }
                
                jsonNode.parts.Set(i, nodePart);
            }
        }
        
        JsonValue children = json.Get("children");
        if (children != null)
        {
            jsonNode.children.SetSize(children.size);
            
            int i = 0;
            for (JsonValue child = children.child; child != null; child = child.next, i++)
            {
                jsonNode.children.Set(i, ParseNodesRecursively(child));
            }
        }
        
        return jsonNode;
    }
    
    private void ParseAnimations(ModelData modelData, JsonValue json)
    {
        JsonValue animations = json.Get("animations");
        if (animations == null)
            return;
        
        modelData.animations.SetSize(animations.size);
        
        for (JsonValue anim = animations.child; anim != null; anim = anim.next)
        {
            JsonValue nodes = anim.Get("bones");
            if (nodes == null)
                continue;
            ModelAnimation animation = new ModelAnimation();
            modelData.animations.Add(animation);
            animation.nodeAnimations.SetSize(nodes.size);
            animation.id = anim.GetString("id");
            
            for (JsonValue node = nodes.child; node != null; node = node.next)
            {
                ModelNodeAnimation nodeAnim = new ModelNodeAnimation();
                animation.nodeAnimations.Add(nodeAnim);
                nodeAnim.nodeID = node.GetString("boneId");
                
                // For backwards compatibility with version 0.1 of libGDX's system
                JsonValue keyframes = node.Get("keyframes");
                if (keyframes != null && keyframes.IsArray())
                {
                    for (JsonValue keyframe = keyframes.child; keyframe != null; keyframe = keyframe.next)
                    {
                        final float keytime = keyframe.GetFloat("keytime", 0f) / 1000.f;
                        JsonValue translation = keyframe.Get("translation");
                        if (translation != null && translation.size == 3)
                        {
                            ModelNodeKeyframe_ tkf = nodeAnim.CreateVector3Keyframe();
                            tkf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__keyTime_(keytime);
                            Vector3 tempV3 = new Vector3();
                            tempV3.Set(translation.GetFloat(0), translation.GetFloat(1), translation.GetFloat(2));
                            tkf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__value_(tempV3);
                            nodeAnim.translation.Add(tkf);
                        }
                        
                        JsonValue rotation = keyframe.Get("rotation");
                        if (rotation != null && rotation.size == 4)
                        {
                            ModelNodeKeyframe_ rkf = nodeAnim.CreateQuaternionKeyframe();
                            rkf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__keyTime_(keytime);
                            Quaternion tempQ = new Quaternion();
                            tempQ.Set(rotation.GetFloat(0), rotation.GetFloat(1), rotation.GetFloat(2), rotation.GetFloat(3));
                            rkf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__value_(tempQ);
                        }
                        
                        JsonValue scale = keyframe.Get("scale");
                        if (scale != null && scale.size == 3)
                        {
                            ModelNodeKeyframe_ skf = nodeAnim.CreateVector3Keyframe();
                            skf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__keyTime_(keytime);
                            Vector3 tempV3 = new Vector3();
                            tempV3.Set(scale.GetFloat(0), scale.GetFloat(1), scale.GetFloat(2));
                            skf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__value_(tempV3);
                            nodeAnim.scaling.Add(skf);
                        }
                    }
                }
                else // Version 0.2
                {
                    JsonValue translationKF = node.Get("translation");
                    if (translationKF != null && translationKF.IsArray())
                    {
                        nodeAnim.translation.SetMaxSize(translationKF.size);
                        for (JsonValue keyframe = translationKF.child; keyframe != null; keyframe = keyframe.next)
                        {
                            ModelNodeKeyframe_ kf = nodeAnim.CreateVector3Keyframe();
                            nodeAnim.translation.Add(kf);
                            kf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__keyTime_(keyframe.GetFloat("keytime", 0) / 1000.f);
                            JsonValue translation = keyframe.Get("value");
                            if (translation != null && translation.size >= 3)
                            {
                                Vector3 tempV3 = new Vector3();
                                tempV3.Set(translation.GetFloat(0), translation.GetFloat(1), translation.GetFloat(2));
                                kf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__value_(tempV3);
                            }
                        }
                    }
                    
                    JsonValue rotationKF = node.Get("rotation");
                    if (rotationKF != null && rotationKF.IsArray())
                    {
                        nodeAnim.rotation.SetMaxSize(rotationKF.size);
                        for (JsonValue keyframe = rotationKF.child; keyframe != null; keyframe = keyframe.next)
                        {
                            ModelNodeKeyframe_ kf = nodeAnim.CreateQuaternionKeyframe();
                            nodeAnim.rotation.Add(kf);
                            kf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__keyTime_(keyframe.GetFloat("keytime", 0f) / 1000.f);
                            JsonValue rotation = keyframe.Get("value");
                            if (rotation != null && rotation.size >= 4)
                            {
                                Quaternion tempQ = new Quaternion();
                                tempQ.Set(rotation.GetFloat(0), rotation.GetFloat(1), rotation.GetFloat(2), rotation.GetFloat(3));
                                kf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__value_(tempQ);
                            }
                        }
                    }
                    
                    JsonValue scalingKF = node.Get("scaling");
                    if (scalingKF != null && scalingKF.IsArray())
                    {
                        nodeAnim.scaling.SetMaxSize(scalingKF.size);
                        for (JsonValue keyframe = scalingKF.child; keyframe != null; keyframe = keyframe.next)
                        {
                            ModelNodeKeyframe_ kf = nodeAnim.CreateVector3Keyframe();
                            nodeAnim.scaling.Add(kf);
                            kf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__keyTime_(keyframe.GetFloat("keytime", 0f) / 1000.f);
                            JsonValue scaling = keyframe.Get("value");
                            if (scaling != null && scaling.size >= 3)
                            {
                                Vector3 tempV3 = new Vector3();
                                tempV3.Set(scaling.GetFloat(0), scaling.GetFloat(1), scaling.GetFloat(2));
                                kf.Set_Libraries_Game_Graphics_ModelData_ModelNodeKeyframe__value_(tempV3);
                            }
                        }
                    }
                }
            }
        }
    }
    
}
