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
import quorum.Libraries.Game.Graphics.ModelLoader;
import quorum.Libraries.Game.Graphics.ModelBuilder;
import quorum.Libraries.Game.Graphics.ModelBlueprint_;
import quorum.Libraries.Game.BoundingBox;
import quorum.Libraries.Game.Graphics.Color_;

/**
 *
 * @author alleew
 */
public class Model 
{
    public java.lang.Object me_ = null;
    
    private final static ModelLoader loader = new ModelLoader();
    private final static ModelBuilder builder = new ModelBuilder();
    private final static Hashtable<String, ModelBlueprint_> hashTable = new Hashtable();
    private final static Hashtable<ModelBlueprint_, Vector3_> dimensionsTable = new Hashtable();
    private final static BoundingBox calcBox = new BoundingBox();
    
    public ModelBlueprint_ GetCachedBlueprint(File_ file)
    {
        ModelBlueprint_ blueprint = hashTable.get(file.GetAbsolutePath());
        if (blueprint == null)
        {
            blueprint = loader.LoadModel(file);
            hashTable.put(file.GetAbsolutePath(), blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedBox(double width, double height, double depth, Color_ color)
    {
        String searchKey = ":BOX:" + width + ":" + height + ":" + depth;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateBox(width, height, depth, color);
            hashTable.put(searchKey, blueprint);
        }
        return blueprint;
    }
    
    public ModelBlueprint_ GetCachedCylinder(double width, double height, double depth, int divisions, Color_ color)
    {
        String searchKey = ":CYLINDER:" + width + ":" + height + ":" + depth + ":" + divisions;
        
        ModelBlueprint_ blueprint = hashTable.get(searchKey);
        if (blueprint == null)
        {
            blueprint = builder.CreateCylinder(width, height, depth, divisions, color);
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
