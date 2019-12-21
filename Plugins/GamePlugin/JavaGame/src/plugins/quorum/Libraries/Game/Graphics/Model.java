/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.util.Hashtable;
import quorum.Libraries.Compute.Vector3_;
import quorum.Libraries.System.File_;
import quorum.Libraries.Game.Graphics.Model_;
import quorum.Libraries.Game.Graphics.ModelLoaders.ModelReader;
import quorum.Libraries.Game.Graphics.ModelBuilder;
import quorum.Libraries.Game.Graphics.ModelBlueprint_;
import quorum.Libraries.Game.BoundingBox;
import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.Graphics.Texture_;

/**
 *
 * @author alleew
 */
public class Model 
{
    public java.lang.Object me_ = null;
    
    private final static ModelReader reader = new ModelReader();
    private final static ModelBuilder builder = new ModelBuilder();
    private final static Hashtable<String, ModelBlueprint_> hashTable = new Hashtable();
    private final static Hashtable<ModelBlueprint_, Vector3_> dimensionsTable = new Hashtable();
    private final static BoundingBox calcBox = new BoundingBox();
    
    public ModelBlueprint_ GetCachedBlueprint(File_ file)
    {
        ModelBlueprint_ blueprint = hashTable.get(file.GetPath());
        if (blueprint == null)
        {
            blueprint = reader.Read(file);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(file.GetPath());
            hashTable.put(file.GetPath(), blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedBox(double width, double height, double depth, Color_ color)
    {
        String blendKey = (color.GetAlpha() < 1.0 ? "BLENDED:" : "");
        String searchKey = ":BOX:DIFFUSE:" + blendKey + width + ":" + height + ":" + depth;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateBox(width, height, depth, color);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedBox(double width, double height, double depth, Texture_ texture)
    {
        String searchKey = ":BOX:TEXTURED:" + width + ":" + height + ":" + depth;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateBox(width, height, depth, texture);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedPlane(double width, double depth, Color_ color)
    {
        String blendKey = (color.GetAlpha() < 1.0 ? "BLENDED:" : "");
        String searchKey = ":PLANE:DIFFUSE:" + blendKey + width + ":" + depth;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreatePlane(width, depth, color);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedPlane(double width, double depth, Texture_ texture)
    {
        String searchKey = ":PLANE:TEXTURED:" + width + ":" + depth;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreatePlane(width, depth, texture);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedCylinder(double width, double height, double depth, int divisions, Color_ color)
    {
        String blendKey = (color.GetAlpha() < 1.0 ? "BLENDED:" : "");
        String searchKey = ":CYLINDER:DIFFUSE:" + blendKey + width + ":" + height + ":" + depth + ":" + divisions;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateCylinder(width, height, depth, divisions, color);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedCylinder(double width, double height, double depth, int divisions, Texture_ texture)
    {
        String searchKey = ":CYLINDER:TEXTURED:" + width + ":" + height + ":" + depth + ":" + divisions;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateCylinder(width, height, depth, divisions, texture);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedSphere(double width, double height, double depth, int hDivisions, int vDivisions, Color_ color)
    {
        String blendKey = (color.GetAlpha() < 1.0 ? "BLENDED:" : "");
        String searchKey = ":SPHERE:DIFFUSE:" + blendKey + width + ":" + height + ":" + depth + ":" + hDivisions + ":" + vDivisions;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateSphere(width, height, depth, hDivisions, vDivisions, color);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedSphere(double width, double height, double depth, int hDivisions, int vDivisions, Texture_ texture)
    {
        String searchKey = ":SPHERE:TEXTURED:" + width + ":" + height + ":" + depth + ":" + hDivisions + ":" + vDivisions;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateSphere(width, height, depth, hDivisions, vDivisions, texture);
            blueprint.Set_Libraries_Game_Graphics_ModelBlueprint__id_(searchKey);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public Vector3_ GetCachedDimensions(ModelBlueprint_ blueprint)
    {
        Vector3_ vector = dimensionsTable.get(blueprint);
        if (vector == null)
        {
            vector = blueprint.CalculateBoundingBox(calcBox).GetDimensions();
            dimensionsTable.put(blueprint, vector);
        }
        return vector;
    }
}
