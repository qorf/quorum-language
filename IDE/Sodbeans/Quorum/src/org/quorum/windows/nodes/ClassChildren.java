/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author stefika
 */
public class ClassChildren extends Index.ArrayChildren{
    private quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz;
    
    public ClassChildren(quorum.Libraries.Language.Compile.Symbol.Class$Interface clazz) {
        this.clazz = clazz;
    }
    
    @Override
    protected java.util.List<Node> initCollection() {
        List fields = new ArrayList();
        quorum.Libraries.Containers.Blueprints.Iterator$Interface variables = clazz.GetVariables();
        while(variables.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Variable$Interface next = (quorum.Libraries.Language.Compile.Symbol.Variable$Interface) variables.Next();
            QuorumVariableNode node = new QuorumVariableNode(next);
            fields.add(node);
        }
        Collections.sort(fields);
        
        List methods = new ArrayList();
        quorum.Libraries.Containers.Blueprints.Iterator$Interface actions = clazz.GetActions();
        while(actions.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Action$Interface next = (quorum.Libraries.Language.Compile.Symbol.Action$Interface) actions.Next();
            QuorumActionNode node = new QuorumActionNode(next);
            methods.add(node);
        }
        
        Collections.sort(methods);
        fields.addAll(methods);
        return fields;
    }
}
