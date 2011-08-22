/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 *
 * @author Andreas Stefik and Melissa Stefik
 */
public class VariableDescriptor extends VariableParameterCommonDescriptor{
    private AccessModifierEnum accessModifier;
    private boolean isInitializedClassVariable = false;

    /**
     * @return the accessModifier enumerator for public and private modifiers.
     */
    public AccessModifierEnum getAccessModifier() {
        return accessModifier;
    }

    /**
     * Set the access modifier (public, private, etc.)
     * @param accessModifier the accessModifier to set
     */
    public void setAccessModifier(AccessModifierEnum accessModifier) {
        this.accessModifier = accessModifier;
    }

    /**
     * @return the isInitializedClassVariable
     */
    public boolean isInitializedClassVariable() {
        return isInitializedClassVariable;
    }

    /**
     * @param isInitializedClassVariable the isInitializedClassVariable to set
     */
    public void setIsInitializedClassVariable(boolean isInitializedClassVariable) {
        this.isInitializedClassVariable = isInitializedClassVariable;
    }

}
