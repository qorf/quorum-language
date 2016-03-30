/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language.structure;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.csl.api.HtmlFormatter;
import org.netbeans.modules.csl.api.Modifier;
import org.netbeans.modules.csl.api.StructureItem;
import org.openide.util.ImageUtilities;
import quorum.Libraries.Language.Compile.Symbol.Variable_;

/**
 *
 * @author stefika
 */
public class QuorumVariableStructureItem implements StructureItem {
    private Variable_ variable;
    
    @Override
    public String getName() {
        return getName(false);
    }

    @Override
    public String getSortText() {
        return getName(false);
    }

    @Override
    public String getHtml(HtmlFormatter hf) {
        return getName(true);
    }
    
    public String getName(boolean isHTML) {
        String name = variable.GetName() + " : ";
        quorum.Libraries.Language.Compile.Symbol.Type_ type = variable.GetType();
        if(type == null) {
            return "";
        }
        name = name + type.GetDisplayName();
        return name;
    }

    @Override
    public ElementHandle getElementHandle() {
        return null;
    }

    @Override
    public ElementKind getKind() {
        return ElementKind.FIELD;
    }

    @Override
    public Set<Modifier> getModifiers() {
        Set<Modifier> modifiers = new HashSet<>();
        if(variable.IsPrivate()) {
            modifiers.add(Modifier.PRIVATE);
        } else {
            modifiers.add(Modifier.PUBLIC);
        }
        return modifiers;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public List<? extends StructureItem> getNestedItems() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public long getPosition() {
        return variable.GetIndex();
    }

    @Override
    public long getEndPosition() {
        return variable.GetIndexEnd();
    }

    @Override
    public ImageIcon getCustomIcon() {
        if(variable.IsPrivate()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/fieldPrivate.png"));
        } else {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/fieldPublic.png"));
        }
    }

    /**
     * @return the variable
     */
    public Variable_ getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(Variable_ variable) {
        this.variable = variable;
    }
    
}
