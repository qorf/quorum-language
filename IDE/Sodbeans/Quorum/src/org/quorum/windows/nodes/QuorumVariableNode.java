/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import java.awt.Image;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumVariableNode extends AbstractNode implements Comparable {
    private quorum.Libraries.Language.Compile.Symbol.Variable_ variable;
    public QuorumVariableNode(quorum.Libraries.Language.Compile.Symbol.Variable_ variable) {
        super(Children.LEAF);
        this.variable = variable;
        computeName();
    }
    
    public void computeName() {
        String name = variable.GetName() + " : ";
        quorum.Libraries.Language.Compile.Symbol.Type_ type = variable.GetType();
        name = name + type.GetDisplayName();
        setDisplayName(name);
    }
    
    @Override
    public Image getIcon(int type) {
        if(variable.IsPrivate()) {
            return ImageUtilities.loadImage("org/quorum/resources/fieldPrivate.png");
        } else {
            return ImageUtilities.loadImage("org/quorum/resources/fieldPublic.png");
        }
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }
    
    @Override
    public Action getPreferredAction() {
        JumpToEditorAction jump = new JumpToEditorAction();
        int line = variable.GetLineNumber();
        File_ file = variable.GetFile();
        FileObject fo = org.quorum.support.Utility.toFileObject(file);
        jump.line = line;
        jump.file = fo;
        return jump;
    }
    public quorum.Libraries.Language.Compile.Symbol.Variable_ getVariable() {
        return variable;
    }
    
    @Override
    public int compareTo(Object o) {
        QuorumVariableNode rightNode = (QuorumVariableNode) o;
        
        quorum.Libraries.Language.Compile.Symbol.Variable_ left = getVariable();
        quorum.Libraries.Language.Compile.Symbol.Variable_ right = rightNode.getVariable();
        return left.GetStaticKey().compareTo(right.GetStaticKey());
    }
}
