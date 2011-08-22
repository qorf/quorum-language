/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.symbols;

import java.util.Comparator;

/**
 * Allows for a default comparison operation between static keys. 
 * 
 * @author Andreas Stefik
 */
public class DescriptorComparator implements Comparator<Descriptor>{

    public int compare(Descriptor left, Descriptor right) {
        return left.getStaticKey().compareTo(right.getStaticKey());
    }
    
}
