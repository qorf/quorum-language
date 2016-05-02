/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.util.Hashtable;
import quorum.Libraries.System.File_;
import quorum.Libraries.Game.Graphics.Model_;
import quorum.Libraries.Game.Graphics.ModelLoader;
import quorum.Libraries.Game.Graphics.ModelBlueprint_;

/**
 *
 * @author alleew
 */
public class Model 
{
    public java.lang.Object me_ = null;
    
    private final static ModelLoader loader = new ModelLoader();
    private final static Hashtable<String, ModelBlueprint_> hashTable = new Hashtable();
    
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
}
