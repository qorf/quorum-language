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
    private quorum.Libraries.Language.Compile.Symbol.Class_ clazz;
    
    public ClassChildren(quorum.Libraries.Language.Compile.Symbol.Class_ clazz) {
        this.clazz = clazz;
    }
    
    @Override
    protected java.util.List<Node> initCollection() {
        List fields = new ArrayList();
        quorum.Libraries.Containers.Blueprints.Iterator_ variables = clazz.GetVariables();
        while(variables.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Variable_ next = (quorum.Libraries.Language.Compile.Symbol.Variable_) variables.Next();
            QuorumVariableNode node = new QuorumVariableNode(next);
            fields.add(node);
        }
        Collections.sort(fields);
        
        List methods = new ArrayList();
        quorum.Libraries.Containers.Blueprints.Iterator_ actions = clazz.GetActions();
        while(actions.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Action_ next = (quorum.Libraries.Language.Compile.Symbol.Action_) actions.Next();
            QuorumActionNode node = new QuorumActionNode(next);
            methods.add(node);
        }
        
        Collections.sort(methods);
        fields.addAll(methods);
        return fields;
    }
}
