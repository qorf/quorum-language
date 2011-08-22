/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.HashMap;
import java.util.Iterator;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 * This class holds scoping information for an instantiation of a class
 * @author Andreas Stefik and Melissa Stefik
 */
public class RuntimeObject extends RuntimeScope{
    private int hashKey = 0;
    private ClassDescriptor clazz;
    private VariableParameterCommonDescriptor associatedObjectVariable;
    private final String THIS_MODE = "";
    private HashMap<String, RuntimeObject> parents = new HashMap<String, RuntimeObject>();
    private String mode = THIS_MODE;

    /**
     * @return the hashKey
     */
    public int getHashKey() {
        return hashKey;
    }

    /**
     * @param hashKey the hashKey to set
     */
    public void setHashKey(int hashKey) {
        this.hashKey = hashKey;
    }

    /**
     * Returns if this object is a null pointer.
     * 
     * @return
     */
    public boolean isNull() {
        return this.getHashKey() <= -1;
    }
    /**
     * @return the clazz
     */
    public ClassDescriptor getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(ClassDescriptor clazz) {
        this.clazz = clazz;
    }

    /**
     * @return the associatedObjectVariable
     */
    public VariableParameterCommonDescriptor getAssociatedObjectVariable() {
        return associatedObjectVariable;
    }

    /**
     * @param associatedObjectVariable the associatedObjectVariable to set
     */
    public void setAssociatedObjectVariable(VariableParameterCommonDescriptor associatedObjectVariable) {
        this.associatedObjectVariable = associatedObjectVariable;
    }

    @Override
    public ExpressionValue getVariable(String varName) {
        if(this.mode.compareTo(this.THIS_MODE) == 0) {
            return super.getVariable(varName);
        }
        else { //it must be a parent's name.
            RuntimeObject parent = this.parents.get(this.mode);
            if(parent != null)
                return parent.getVariable(varName);
            return null;
        }
    }

    /**
     * This function returns the appropriate scope, or parent scope, in which
     * a particular variable is housed.
     * @param key
     * @return
     */
    @Override
    protected RuntimeScope getScopeOfVariable(String key) {
        ExpressionValue o = this.getVariable(key);
        if(o != null) {
            return this;
        }
        else {
            if(!parents.isEmpty()) {
                Iterator<RuntimeObject> parentsIt = this.getParents();
                RuntimeScope scope = null;
                while(parentsIt.hasNext() && scope == null){
                    RuntimeObject next = parentsIt.next();
                    scope = next.getScopeOfVariable(key);
                }
                return scope;
            }
            else {
                return null;
            }
        }
    }
    
    @Override
    public void addVariable(String key, ExpressionValue value) {
        if(this.mode.compareTo(this.THIS_MODE) == 0) {
            super.addVariable(key, value);
        }
        else { //it must be a parent's name.
            RuntimeObject parent = this.parents.get(this.mode);
            parent.addVariable(key, value);
        }
    }

    @Override
    public void addVariableLocalScopeOnly(String key, ExpressionValue value) {
        if(this.mode.compareTo(this.THIS_MODE) == 0) {
            super.addVariableLocalScopeOnly(key, value);
        }
        else { //it must be a parent's name.
            RuntimeObject parent = this.parents.get(this.mode);
            parent.addVariableLocalScopeOnly(key, value);
        }
    }

    @Override
    public ExpressionValue undoAddVariable(String key) {
        if(this.mode.compareTo(this.THIS_MODE) == 0) {
            return super.undoAddVariable(key);
        }
        else { //it must be a parent's name.
            RuntimeObject parent = this.parents.get(this.mode);
            return parent.undoAddVariable(key);
        }
    }

    @Override
    public ExpressionValue undoAddVariableLocalScopeOnly(String key) {
        if(this.mode.compareTo(this.THIS_MODE) == 0) {
            return super.undoAddVariableLocalScopeOnly(key);
        }
        else { //it must be a parent's name.
            RuntimeObject parent = this.parents.get(this.mode);
            return parent.undoAddVariableLocalScopeOnly(key);
        }
    }

    /**
     * Add a runtime object to the hash of parents. This runtime object
     * should be a parent of the current runtime object.
     *
     * @param ro
     */
    public void addParentClass(RuntimeObject ro) {
        this.parents.put(ro.getClazz().getStaticKey(), ro);
    }

    /**
     * Returns the mode that this object is currently in.
     * 
     * @return
     */
    public String getMode() {
        return this.mode;
    }

    /**
     * Returns the number of parents this object has.
     *
     * @return
     */
    public int getNumberParents() {
        return parents.size();
    }

    /**
     * A convenience function that returns whether or not this object has
     * parents.
     * 
     * @return
     */
    public boolean hasParents() {
        return !parents.isEmpty();
    }

    /**
     * Returns a parent of this class if key matches the static key
     * of one of its parents. In general, ClassDescriptor.getStaticKey()
     * should be used, if possible, to prevent errors.
     * 
     * @param key
     * @return
     */
    public RuntimeObject getParent(String key) {
        return parents.get(key);
    }

    /**
     * Returns and removes a parent of this class if key matches the static key
     * of one of its parents. In general, ClassDescriptor.getStaticKey()
     * should be used for the key.
     * @param key
     * @return
     */
    public RuntimeObject removeParent(String key){
        return parents.remove(key);
    }
    
    /**
     * Returns an iterator of all parents listed for this class.
     *
     * @return
     */
    public Iterator<RuntimeObject> getParents() {
        return parents.values().iterator();
    }
    
    /**
     * Set the current objects mode
     * @param ro
     */
    public void setMode(RuntimeObject ro) {
        setMode(ro.getClazz().getStaticKey());
        
    }

    /**
     * Sets the mode for this object. Note: These modes must be checked
     * at compile time for validity.
     * 
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return Query the mode if it is in "this mode" return true. Otherwise,
     * return false.
     */
    public boolean isThisMode(){
        if(this.mode.compareTo(this.THIS_MODE) == 0){
            return true;
        }
        return false;
    }

    /**
     * Set the mode to this mode
     */
    public void setToThisMode() {
        setMode(this.THIS_MODE);
    }

    /**
     * Get the requested variable from the specified parent object.
     * 
     * @param locatedInClass
     * @param queryName
     * @return
     */
    public ExpressionValue getVariableFromParent(String locatedInClass, String queryName) {
            RuntimeObject parent = this.parents.get(locatedInClass);
            if(parent != null)
                return parent.getVariable(queryName);
            return null;
    }

    public void addVariableToParent(String locatedInClass, String queryName, ExpressionValue newVal){
        RuntimeObject parent = this.parents.get(locatedInClass);
        if(parent != null){
            parent.addVariable(queryName, newVal);
        }

    }

    public void undoAddVariableToParent(String locatedInClass, String queryName){
        RuntimeObject parent = this.parents.get(locatedInClass);
        if(parent != null){
            parent.undoAddVariable(queryName);
        }
    }

    @Override
    public String toString() {
        return this.clazz.getStaticKey();
    }
}
