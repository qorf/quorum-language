/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author stefika
 */
public class QuorumClassNode extends AbstractNode{
    private quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz = null;

    public QuorumClassNode(quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz) {
        super(new ClassChildren(clazz), Lookups.fixed( new Object[] {clazz}));
        this.clazz = clazz;
        computeName();
    }
    
    public void computeName() {
        String name = clazz.GetName() + " :: " ;
        
        quorum.Libraries.Containers.Blueprints.Iterator$Interface parents = clazz.GetParentClasses();
        boolean first = true;
        while(parents.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Class$Interface parent = (quorum.Libraries.Language.Compile.Symbol.Class$Interface) parents.Next();
            if(first) {
                name = name + parent.GetName();
            } else {
                name = name + ", " + parent.GetName();
            }
            first = false;
        }
        setDisplayName(name);
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("org/quorum/resources/class.png");
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    
    /**
     * @return the clazz
     */
    public quorum.Libraries.Language.Compile.Symbol.Class$Interface getClazz() {
        return clazz;
    }
}
