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

/**
 *
 * @author stefika
 */
public class QuorumClassStructureItem implements StructureItem{

    private quorum.Libraries.Language.Compile.Symbol.Class_ clazz = null;
    @Override
    public String getName() {
        return clazz.GetName();
    }

    @Override
    public String getSortText() {
        return clazz.GetName();
    }

    @Override
    public String getHtml(HtmlFormatter hf) {
        return clazz.GetName();
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
        return true;
    }

    @Override
    public List<? extends StructureItem> getNestedItems() {
        return Collections.EMPTY_LIST;
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
        return new ImageIcon("org/quorum/resources/class.png", "class");
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
    
}
