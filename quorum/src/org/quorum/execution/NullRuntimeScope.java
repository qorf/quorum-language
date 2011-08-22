/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

/**
 * Used in place of a null pointer in the initial DataEnvironment setup. This
 * object returns false for its isValidScope call, indicating that this
 * object should not be used as a real scope.
 * 
 * @author Andreas Stefik
 */
public class NullRuntimeScope extends RuntimeScope{
    @Override
    public boolean isValidScope() {
        return false;
    }
}
