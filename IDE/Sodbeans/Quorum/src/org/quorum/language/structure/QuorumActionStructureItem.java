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
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Language.Compile.Symbol.Action_;
import quorum.Libraries.Language.Compile.Symbol.Block_;
import quorum.Libraries.Language.Compile.Symbol.Type_;

/**
 *
 * @author stefika
 */
public class QuorumActionStructureItem implements StructureItem.CollapsedDefault {
    private Action_ action = null;
    private boolean isConstructor = false;
    
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
        return getName(false);
    }

    @Override
    public ElementHandle getElementHandle() {
        return null;
    }
    
    public String getName(boolean isHTML) {
        String name = "";
        
        if(isConstructor) {
            name = "on create" + "(";
        } else {
            name = action.GetName() + "(";
        }
        boolean first = true;
        quorum.Libraries.Containers.Iterator_ params = action.GetParameterIterator();
        while(params.HasNext()) {
            quorum.Libraries.Language.Compile.Symbol.Variable_ param = (quorum.Libraries.Language.Compile.Symbol.Variable_) params.Next();
            Type_ type = param.GetType();
            if(first == true) {
                name = name + type.GetStaticKey() + " " + param.GetName();
            } else {
                name = name + ", " + type.GetStaticKey() + " " + param.GetName();
            }
            
            first = false;
        }
        name = name + ")";
        
        Type_ ret = action.GetReturnType();
        if(ret != null && !ret.IsVoid()) {
            if(isHTML) {
                name = name + " : " + ret.GetStaticKey();
            } else {
                name = name + " : " + ret.GetStaticKey();
            }
        }
        return name;
    }

    @Override
    public ElementKind getKind() {
        return ElementKind.METHOD;
    }

    @Override
    public Set<Modifier> getModifiers() {
        Set<Modifier> modifiers = new HashSet<>();
        if(action.IsPrivate()) {
            modifiers.add(Modifier.PRIVATE);
        } else {
            modifiers.add(Modifier.PUBLIC);
        }
        if(action.IsBlueprint()) {
            modifiers.add(Modifier.ABSTRACT);
        }
        return modifiers;
    }

    @Override
    public boolean isLeaf() {
        Block_ block = action.GetBlock();
        if(block != null) {
            int size = block.GetSubBlockSize();
            if(size == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<? extends StructureItem> getNestedItems() {
        Block_ block = action.GetBlock();
        if(block != null) {
            int size = block.GetSubBlockSize();
            if(size == 0) {
                return Collections.EMPTY_LIST;
            }

            List<StructureItem> items = new LinkedList<>();
            Iterator_ it = block.GetBlocks();
            while(it.HasNext()) {
                Block_ next = (Block_) it.Next();
                QuorumBlockStructureItem item = new QuorumBlockStructureItem();
                item.setBlock(next);
                items.add(item);
            }
            return items;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public long getPosition() {
        return action.GetIndex();
    }

    @Override
    public long getEndPosition() {
        return action.GetIndexEnd();
    }

    @Override
    public ImageIcon getCustomIcon() {
        if(action.IsPrivate()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/methodPrivate.png"));
        } else {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/methodPublic.png"));
        }
    }
    
    @Override
    public boolean isCollapsedByDefault() {
        return true;
    }

    /**
     * @return the action
     */
    public Action_ getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action_ action) {
        this.action = action;
    }

    /**
     * @return the isConstructor
     */
    public boolean isIsConstructor() {
        return isConstructor;
    }

    /**
     * @param isConstructor the isConstructor to set
     */
    public void setIsConstructor(boolean isConstructor) {
        this.isConstructor = isConstructor;
    }
}
