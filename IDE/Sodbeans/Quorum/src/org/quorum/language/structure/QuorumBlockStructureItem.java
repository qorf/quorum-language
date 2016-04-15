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
import org.netbeans.modules.csl.api.StructureItem.CollapsedDefault;
import org.openide.util.ImageUtilities;
import quorum.Libraries.Containers.Blueprints.Iterator_;
import quorum.Libraries.Language.Compile.Symbol.Block_;

/**
 *
 * @author stefika
 */
public class QuorumBlockStructureItem implements CollapsedDefault {
    private Block_ block;
    
    @Override
    public String getName() {
        return getName(false);
    }

    public String getName(boolean isHTML) {
        String result = "";
        if(isHTML) {
            if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__REPEAT_TIMES_()) {
                return result + "repeat times";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__REPEAT_WHILE_()) {
                return result + "repeat while";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__REPEAT_UNTIL_()) {
                return result + "repeat until";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__IF_())  {
                return result + "if";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__ELSE_IF_())  {
                return result + "elseif";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__ELSE_()) {
                return result + "else";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__CHECK_()) {
                return result + "check";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__DETECT_()) {
                return result + "detect";
            } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__ALWAYS_()) {
                return result + "detect";
            } else {
                return "Unknown control structure";
            }
        } else {
            return getPaddedNumber();
        }
    }
    
    private String getPaddedNumber() {
        String pad = "0000000000000000000";     
        
        String result = "" + getPosition();
        int length = result.length();
        String subPad = pad.substring(0, pad.length() - length);
        subPad = subPad + result;
        return subPad;
    }
    @Override
    public String getSortText() {
        return getPaddedNumber();
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
        return ElementKind.OTHER;
    }

    @Override
    public Set<Modifier> getModifiers() {
        Set<Modifier> modifiers = new HashSet<>();
        return modifiers;
    }

    @Override
    public boolean isLeaf() {
        int size = block.GetSubBlockSize();
        if(size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<? extends StructureItem> getNestedItems() {
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
    }

    @Override
    public long getPosition() {
        return block.GetIndex();
    }

    @Override
    public long getEndPosition() {
        return block.GetIndexEnd();
    }

    @Override
    public ImageIcon getCustomIcon() {
        if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__REPEAT_TIMES_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/repeat.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__REPEAT_WHILE_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/repeat.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__REPEAT_UNTIL_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/repeat.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__IF_())  {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/if.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__ELSE_IF_())  {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/if.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__ELSE_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/if.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__CHECK_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/exception.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__DETECT_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/exception.png"));
        } else if(block.GetBlockType() == block.Get_Libraries_Language_Compile_Symbol_Block__ALWAYS_()) {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/exception.png"));
        } else {
            return new ImageIcon(ImageUtilities.loadImage("org/quorum/resources/methodPrivate.png"));
        }
    }

    /**
     * @return the block
     */
    public Block_ getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(Block_ block) {
        this.block = block;
    }

    @Override
    public boolean isCollapsedByDefault() {
        return true;
    }
}
