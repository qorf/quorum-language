/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Melissa Stefik
 */
public class GenericDescriptor extends Descriptor{
    private ArrayList<TypeDescriptor> inheritedTypes = new ArrayList<TypeDescriptor>();

    public GenericDescriptor(GenericDescriptor generic){
        Iterator<TypeDescriptor> allTypes = generic.getAllTypes();
        while(allTypes.hasNext()){
            inheritedTypes.add(new TypeDescriptor(allTypes.next()));
        }
    }

    public GenericDescriptor() {
        inheritedTypes = new ArrayList<TypeDescriptor>();
    }

    /**
     * Add a bounded type of a generic. The leftmost value is the type which is
     * maintained(standard type erasure).
     *
     * @param td
     */
    public void addBoundType(TypeDescriptor td){
        inheritedTypes.add(td);
    }

    /**
     * Get the type of the generic descriptor.
     *
     * @return
     */
    public TypeDescriptor getType(){
        if(!inheritedTypes.isEmpty()){
            return inheritedTypes.get(0);
        }
        return null;
    }

    /**
     * reset the leftmost type
     * @param type
     */
    public void setType(TypeDescriptor type){
        if(!inheritedTypes.isEmpty()){
            inheritedTypes.remove(0);
        }
        inheritedTypes.add(0,type);
    }

    /**
     * get a list of all bounded types. This is used to see the history.
     * @return
     */
    public Iterator<TypeDescriptor> getAllTypes(){
        return inheritedTypes.iterator();
    }
}
