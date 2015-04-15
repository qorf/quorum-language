/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author stefika
 */
public class QuorumClassNode extends AbstractNode{
    private quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz = null;

    public QuorumClassNode(final quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz) {
        super(Children.createLazy(new Callable<Children>() {
            @Override
            public Children call() throws Exception {
                return getClassActions(clazz);
            }
        }), Lookups.fixed( new Object[] {clazz}));
        this.clazz = clazz;
        computeName();
    }
    
    public static Children getClassActions(quorum.Libraries.Language.Compile.Symbol.Class$Interface c) {
        Children children = new Children.SortedArray();
        
        ArrayList<Node> n = new ArrayList<>();
        
        quorum.Libraries.Containers.Blueprints.Iterator$Interface actions = c.GetActions();
        while(actions.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Action$Interface next = (quorum.Libraries.Language.Compile.Symbol.Action$Interface) actions.Next();
            QuorumActionNode node = new QuorumActionNode(next);
            n.add(node);
        }
        Node[] nodes = new Node[n.size()];
        for(int i = 0; i < nodes.length; i++) {
            nodes[i] = n.get(i);
        }
        children.add(nodes);
        return children;
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

    /**
     * @return the clazz
     */
    public quorum.Libraries.Language.Compile.Symbol.Class$Interface getClazz() {
        return clazz;
    }
}
