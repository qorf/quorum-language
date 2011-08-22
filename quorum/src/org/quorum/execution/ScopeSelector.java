/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import org.quorum.symbols.ClassDescriptor;

/**
 * Handles selecting scope of "this" or "parent".
 * @author Melissa Stefik
 */
public class ScopeSelector {
    private boolean isParent;
    private ClassDescriptor currentClass = new ClassDescriptor();

    /**
     * @return the currentClass
     */
    public ClassDescriptor getCurrentClass() {
        return currentClass;
    }

    /**
     * @param currentClass the currentClass to set
     */
    public void setCurrentClass(ClassDescriptor currentClass) {
        this.currentClass = currentClass;
    }

    /**
     * @return the isParent
     */
    public boolean isParent() {
        return isParent;
    }

    /**
     * @param isParent the isParent to set
     */
    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }



}
