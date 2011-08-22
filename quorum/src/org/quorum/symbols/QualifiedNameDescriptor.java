/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.Iterator;
import java.util.List;
import org.antlr.runtime.tree.CommonTree;

/**
 *
 * @author Andreas Stefik
 */
public class QualifiedNameDescriptor extends Descriptor{
    private String[] names;
    private String key = "";
    
    public QualifiedNameDescriptor() {

    }


    @Override
    public void setName(String name){
        names = name.split(UseDescriptor.DELIMITER);
        key = name;
        super.setName(name);
    }
    
    /** This method makes an array of string names corresponding to a namespace
     * from a list of string names.
     * @param names
     */
    public void setNameFromList(List names) {
        this.names = new String[names.size()];
        Iterator it = names.iterator();
        int i = 0;
        key = "";
        while(it.hasNext()) {
           
            this.names[i] = ((CommonTree) it.next()).getText();
            


            key += this.names[i];
            if(it.hasNext()) {
                key += UseDescriptor.DELIMITER;
            }
            i++;
        }
    }
   

    public String getNameAtIndex(int index) {
        return names[index];
    }

    /** Returns a list of the values corresponding to a namespace structure
     * for a given type. In Java, for example, this might be
     * Java.util.Vector
     * @return
     */
    public String[] getNames() {
        return names;
    }

    @Override
    public String getStaticKey() {
        return key;
    }

    @Override
    public String toString() {
        return key;
    }
}
