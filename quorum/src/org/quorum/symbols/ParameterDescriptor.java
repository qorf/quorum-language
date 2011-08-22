/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 *  This class represents the description for a parameter in a function.
 * 
 * @author Andreas Stefik
 */
public class ParameterDescriptor extends VariableParameterCommonDescriptor{

    /**
     * Variable parameters are always initialized, because if the user
     * attempts to pass an uninitialized variable to a parameter, that
     * will be an error anyway.
     * 
     * @return
     */
    @Override
    public boolean isInitialized() {
        return true;
    }
}
