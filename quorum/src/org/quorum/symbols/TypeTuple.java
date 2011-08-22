/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Aaron
 */
public class TypeTuple {
    private TypeDescriptor left;
    private TypeDescriptor right;
    private boolean assignment;

    /**
     * @return the left
     */
    public TypeDescriptor getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(TypeDescriptor left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public TypeDescriptor getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(TypeDescriptor right) {
        this.right = right;
    }

    /**
     * @return the assignment
     */
    public boolean isAssignment() {
        return assignment;
    }

    /**
     * @param assignment the assignment to set
     */
    public void setAssignment(boolean assignment) {
        this.assignment = assignment;
    }

   


}
