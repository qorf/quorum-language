/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import quorum.Libraries.Language.Compile.Symbol.Type$Interface;

/**
 *
 * @author stefika
 */
public class QuorumActionNode extends AbstractNode implements Comparable {
    private quorum.Libraries.Language.Compile.Symbol.Action$Interface action;
    public QuorumActionNode(quorum.Libraries.Language.Compile.Symbol.Action$Interface action) {
        super(Children.LEAF);
        this.action = action;
        computeName();
    }
    
    public void computeName() {
        String name = action.GetName() + "(";
        boolean first = true;
        quorum.Libraries.Containers.Blueprints.Iterator$Interface params = action.GetParameterIterator();
        while(params.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Variable$Interface param = (quorum.Libraries.Language.Compile.Symbol.Variable$Interface) params.Next();
            Type$Interface type = param.GetType();
            if(first = true) {
                name = name + type.GetStaticKey() + " " + param.GetName();
            } else {
                name = name + ", " + type.GetStaticKey() + " " + param.GetName();
            }
            
            first = false;
        }
        name = name + ")";
        setDisplayName(name);
    }

    public quorum.Libraries.Language.Compile.Symbol.Action$Interface getAction() {
        return action;
    }
    
    @Override
    public int compareTo(Object o) {
        QuorumActionNode rightNode = (QuorumActionNode) o;
        
        quorum.Libraries.Language.Compile.Symbol.Action$Interface left = getAction();
        quorum.Libraries.Language.Compile.Symbol.Action$Interface right = rightNode.getAction();
        return left.GetStaticKey().compareTo(right.GetStaticKey());
    }
}
