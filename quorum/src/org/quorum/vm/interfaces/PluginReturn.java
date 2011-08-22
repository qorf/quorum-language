/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import org.quorum.execution.ExpressionValue;

/**
 * This class represents a return value that is passed from a plugin on the system.
 * All return values must be passed as ExpressionValue objects.
 * 
 * @author Andreas Stefik
 */
public class PluginReturn {
    private ExpressionValue value;

    /**
     * @return the value
     */
    public ExpressionValue getReturnValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setReturnValue(ExpressionValue value) {
        this.value = value;
    }
}
