/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import quorum.Libraries.Language.Compile.Symbol.Type$Interface;
import quorum.Libraries.System.File$Interface;

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

    @Override
    public Action getPreferredAction() {
        JumpToEditorAction jump = new JumpToEditorAction();
        int line = action.GetLineNumber();
        File$Interface file = action.GetFile();
        FileObject fo = org.quorum.support.Utility.toFileObject(file);
        jump.line = line;
        jump.file = fo;
        return jump;
    }

    public quorum.Libraries.Language.Compile.Symbol.Action$Interface getAction() {
        return action;
    }
    
    @Override
    public Image getIcon(int type) {
        if(action.IsPrivate()) {
            return ImageUtilities.loadImage("org/quorum/resources/methodPrivate.png");
        } else {
            return ImageUtilities.loadImage("org/quorum/resources/methodPublic.png");
        }
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    
    @Override
    public int compareTo(Object o) {
        QuorumActionNode rightNode = (QuorumActionNode) o;
        
        quorum.Libraries.Language.Compile.Symbol.Action$Interface left = getAction();
        quorum.Libraries.Language.Compile.Symbol.Action$Interface right = rightNode.getAction();
        return left.GetStaticKey().compareTo(right.GetStaticKey());
    }
}
