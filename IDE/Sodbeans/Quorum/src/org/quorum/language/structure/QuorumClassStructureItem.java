/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language.structure;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.csl.api.HtmlFormatter;
import org.netbeans.modules.csl.api.Modifier;
import org.netbeans.modules.csl.api.StructureItem;
import org.openide.util.ImageUtilities;
import quorum.Libraries.Containers.Blueprints.Iterator_;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Variable_;

/**
 *
 * @author stefika
 */
public class QuorumClassStructureItem implements StructureItem.CollapsedDefault {

    private quorum.Libraries.Language.Compile.Symbol.Class_ clazz = null;
    @Override
    public String getName() {
        return getName(false);
    }

    @Override
    public String getSortText() {
        return getName(false);
    }
    
    private String getName(boolean isHTML) {
        String name = "";
        
        if(isHTML) { 
            name = clazz.GetName() + " :: ";
        } else {
            name = clazz.GetName() + "<i> :: ";
        }
        quorum.Libraries.Containers.Blueprints.Iterator_ parents = clazz.GetParentClasses();
        boolean first = true;
        while(parents.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Class_ parent = (quorum.Libraries.Language.Compile.Symbol.Class_) parents.Next();
            if(first) {
                name = name + parent.GetName();
            } else {
                name = name + ", " + parent.GetName();
            }
            first = false;
        }
        if(isHTML) {
            name = name + "</i>";
        }
        return name;
    }

    @Override
    public String getHtml(HtmlFormatter hf) {
        return getName(true);
    }

    @Override
    public ElementHandle getElementHandle() {
        return null;
    }

    @Override
    public ElementKind getKind() {
        return ElementKind.CLASS;
    }

    @Override
    public Set<Modifier> getModifiers() {
        Set<Modifier> modifiers = new HashSet<>();
        return modifiers;
    }

    @Override
    public boolean isLeaf() {
        Iterator_ actions = clazz.GetActions();
        Iterator_ variables = clazz.GetVariables();
        if(actions != null && actions.HasNext()) {
            return false;
        }
        
        if(variables != null && variables.HasNext()) {
            return false;
        }
        
        if(clazz.HasConstructor()) {
            return false;
        }
        return true;
    }

    @Override
    public List<? extends StructureItem> getNestedItems() {
        Iterator_ variables = clazz.GetVariables();
        if(variables == null) {
            return Collections.EMPTY_LIST;
        }
        List<StructureItem> items = new LinkedList<>();
        while(variables.HasNext()) {
            Variable_ next = (Variable_) variables.Next();
            QuorumVariableStructureItem item = new QuorumVariableStructureItem();
            item.setVariable(next);
            items.add(item);
        }
        
        Iterator_ actions = clazz.GetActions();
        if(actions == null) {
            return Collections.EMPTY_LIST;
        }
        
        while(actions.HasNext()) {
            Action_ next = (Action_) actions.Next();
            QuorumActionStructureItem item = new QuorumActionStructureItem();
            item.setAction(next);
            items.add(item);
        }
        
        if(clazz.HasConstructor()) {
            Action_ constructor = clazz.GetConstructor();
            QuorumActionStructureItem item = new QuorumActionStructureItem();
            item.setAction(constructor);
            item.setIsConstructor(true);
            items.add(item);
        }
        return items;
    }
    
    @Override
    public long getPosition() {
        return clazz.GetIndex();
    }

    @Override
    public long getEndPosition() {
        return clazz.GetIndexEnd();
    }

    @Override
    public ImageIcon getCustomIcon() {
        return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/class.png"));
    }

    /**
     * @return the clazz
     */
    public quorum.Libraries.Language.Compile.Symbol.Class_ getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(quorum.Libraries.Language.Compile.Symbol.Class_ clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isCollapsedByDefault() {
        return false;
    }
    
}
