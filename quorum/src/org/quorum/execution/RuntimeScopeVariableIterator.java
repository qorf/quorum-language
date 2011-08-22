/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.Iterator;

/**
 * This object iterates over all variables inside of a RuntimeScope object,
 * regardless of what scope they are in, including parent scopes or object
 * scopes.
 * 
 * @author Andreas Stefik
 */
public class RuntimeScopeVariableIterator implements Iterator<DataObject>{

    /**
     * The scope in which to grab variables.
     */
    private RuntimeScope scope;

    /**
     * The iterator on the current local scope in the RuntimeScope object.
     */
    private Iterator<DataObject> iterator;

    public boolean hasNext() {
        if(iterator.hasNext()) {
            return true;
        }
        else {
            if(scope.getParent() != null) {
                setScope(scope.getParent());
                return hasNext();
            }
            else {
                return false;
            }
        }
    }

    public DataObject next() {
        return iterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * @return the scope
     */
    public RuntimeScope getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(RuntimeScope scope) {
        this.scope = scope;
        iterator = scope.getLocalVariables();
    }

}
