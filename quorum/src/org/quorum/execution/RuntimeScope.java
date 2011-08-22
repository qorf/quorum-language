/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;
import java.util.Vector;
import org.quorum.vm.interfaces.LineInformation;
import org.quorum.symbols.BlockDescriptor;

/**
 * Represents information related to the scope of a variable or object.
 * 
 * @author Andreas Stefik, Aaron Willows, and Andrew Hauck
 */
public class RuntimeScope {

    private HashMap<String, DataObject> variables;
    private RuntimeScope parent;
    private Vector<RuntimeScope> children;
    private LineInformation lineInformation;
    private int previousThisPointer = -1;

    private Stack<String> previousModes = new Stack<String>();
    private HashMap<Integer, DataObject> registers;

    public RuntimeScope() {
        children = new Vector<RuntimeScope>();
        variables = new HashMap<String, DataObject>();  
        registers = new HashMap<Integer, DataObject>();
    }

    /**
     * Adds a variable to this scope. If the variable exists, it will be updated with the new value.
     * If the variable does not exist, it will search through appropriate parent
     * scopes to determine if the parent houses the variable. If the parent
     * houses the variable, it will update its value there. Otherwise, if the
     * value does not exist anywhere, it will update it in the current scope.
     * 
     * @param key the name of the variable to update
     * @param value the value to update the variable with
     */
    public void addVariable(String key, ExpressionValue value) {
        RuntimeScope scope = getScopeOfVariable(key);
        if(scope != null) { //the variable exists in some local scope (or a parent).
            scope.addVariableLocalScopeOnly(key, value);
        }
        else {
            this.addVariableLocalScopeOnly(key, value);
        }
    }

    /**
     * Adds a variable into the local scope. This function does not check
     * parent scopes to determine if a value is there. It only checks the local
     * scope. This is useful for statements where a variable is both defined
     * locally and in a parent scope (e.g., inside the "This" object).
     * 
     * @param key
     * @param value
     */
    public void addVariableLocalScopeOnly(String key, ExpressionValue value) {
        if(variables.containsKey(key)) {
            variables.get(key).pushValue(value);
        }
        else {
            DataObject o = new DataObject();
            o.setName(key);
            o.pushValue(value);
            variables.put(key, o);
        }
    }

    /**
     * Restores a variable to its previous value. This method searches through
     * both the local and the parent scope to find variables as appropriate.
     * 
     * @param key the name of the variable to restore
     * @return the value of the variable before it was restored. If the variable does not exist null.
     */
    public ExpressionValue undoAddVariable(String key) {
        RuntimeScope scope = getScopeOfVariable(key);
        if(scope != null) { //the variable exists in some local scope (or a parent).
            return scope.undoAddVariableLocalScopeOnly(key);
        }
        else {
            return null;
        }
    }

    /**
     * Removes a variable from the local scope only, with no consideration
     * of the same variable possibly being in a parent scope.
     * 
     * @param key
     * @return
     */
    public ExpressionValue undoAddVariableLocalScopeOnly(String key) {
        DataObject o = variables.get(key);
        if(o != null) {
            ExpressionValue returnMe = o.popValue();
            if(o.isEmpty()) {
                variables.remove(key);
            }

            return returnMe;
        }
        else {
            return null;
        }
    }

    /**
     * This function returns the appropriate scope, or parent scope, in which
     * a particular variable is housed.
     * @param key
     * @return
     */
    protected RuntimeScope getScopeOfVariable(String key) {
        DataObject o = this.variables.get(key);
        if(o != null) {
            return this;
        }
        else {
            if(parent != null) {
                return parent.getScopeOfVariable(key);
            }
            else {
                return null;
            }
        }
    }

    /**
     * Gets the value of the specified variable from either the local
     * scope or from a parent scope.
     * 
     * @param key the name of the variable to get
     * @return the current value of the variable
     */
    public ExpressionValue getVariable(String key) {
        DataObject o = variables.get(key);
        if(o != null) {
            return o.getCurrentValue();
        }
        else {
            if(parent != null) {
                return parent.getVariable(key);
            }
            else {
                return null;
            }
        }
    }

    /**
     * @return the parent
     */
    public RuntimeScope getParent() {
        return parent;
    }

    public void pushRegisterValue(int i, ExpressionValue value, TimeStamp stamp) {
        //then check if there is a register stack at this location
        if (!registers.containsKey(i)) {
            //add a new register stack
            DataObject o = new DataObject();
            registers.put(i, o);
        }
        registers.get(i).pushValue(value, stamp);
    }
    
    public ExpressionValue popRegisterValue(int i) {
        
        DataObject o = registers.get(i);
        return o.popValue();
    }
    
    public ExpressionValue getRegisterValue(int i) {
        HashMap<Integer, DataObject> hash = findRegisterParent(i);
        DataObject o = hash.get(i);
        return o.getCurrentValue();
    }
    
    private HashMap<Integer, DataObject> findRegisterParent(int i) {
        if(this.registers.containsKey(i)) {
            return registers;
        }
        else if (this.getParent() != null) {
            return this.getParent().findRegisterParent(i);
        }
        else {
            return null;
        }
    }
    /**
     * @param parent the parent to set
     */
    public void setParent(RuntimeScope parent) {
        this.parent = parent;
    }

    /**
     * @return the children
     */
    public ListIterator<RuntimeScope> getChildren() {
        return children.listIterator();
    }

    /**
     * Adds a child to this object's scope.
     * 
     * @param child
     */
    public void addChild(RuntimeScope child) {
        child.setParent(this);
        children.add(child);
    }

    /**
     * Returns the number of variables in the current object's local scope.
     * 
     * @return
     */
    public int getNumberLocalVariables() {
        return variables.size();
    }

    /**
     * Returns whether or not the current scope has any local variables.
     * 
     * @return
     */
    public boolean hasLocalVariables() {
        return !variables.isEmpty();
    }

    /**
     * This function returns all variables that exist in the local scope
     * of the current object. No variables are returned that are in
     * the parent scope, like that of this variable's parent object.
     * 
     * @return
     */
    public Iterator<DataObject> getLocalVariables() {
        return variables.values().iterator();// -- Bug Fix: Andrew Hauck
    }

    /**
     * This function returns a list of all variables currently accessible
     * from within this scope, including any variables in the parent scope.
     * 
     * @return
     */
    public Iterator<DataObject> getAllVariables() {
        RuntimeScopeVariableIterator iterator = new RuntimeScopeVariableIterator();
        iterator.setScope(this);
        return iterator;
    }
    
    /**
     * This function determines whether this scope is valid in the context
     * of the compiler. It is only useful in determining if this object is
     * a null scope.
     * 
     * @return
     */
    public boolean isValidScope() {
        return true;
    }

    /**
     * Clears the variable's scope.
     */
    public void clear(){
        variables.clear();
        registers.clear();
    }

    public void build(BlockDescriptor block) {
        Iterator<BlockDescriptor> blocks = block.getChildren();

        while(blocks.hasNext()) {
            BlockDescriptor child_block = blocks.next();
            RuntimeScope child = new RuntimeScope();
            child.build(child_block);
            this.addChild(child);
            
        }
    }
    
    /**
     * @return the previousThisPointer
     */
    public int getPreviousThisPointer() {
        return previousThisPointer;
    }

    /**
     * @param previousThisPointer the previousThisPointer to set
     */
    public void setPreviousThisPointer(int previousThisPointer) {
        this.previousThisPointer = previousThisPointer;
    }

    public void setPreviousMode(String m){
        previousModes.push(m);
    }

    public String getPreviousMode(){
        if(!previousModes.isEmpty())
            return previousModes.pop();
        return "";
    }

    /**
     * @return the lineInfo
     */
    public LineInformation getLineInformation() {
        return lineInformation;
    }

    /**
     * @param lineInfo the lineInfo to set
     */
    public void setLineInformation(LineInformation lineInfo) {
        this.lineInformation = lineInfo;
    }
}
