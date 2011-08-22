/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.AbstractVirtualMachine;

/**
 *  This class contains DataObjects, it is similar to the symbol table, basicly the programs heap
 * @author Aaron Willows
 */
public class DataScope {
    private HashMap<String, DataObject> variables;
    private DataScope parent;    
    private AbstractVirtualMachine vm;
    private Vector<DataScope> children;
    
    private int currentChild;

    public DataScope() {
        variables = new HashMap<String, DataObject>();
        children = new Vector<DataScope>();

    }
    
    public DataScope getNextChild() {
        return children.get(currentChild++);
    }
    public DataScope getPreviousChild() {
        return children.get(currentChild--);
    }

    public void addChild(DataScope child) {
        children.add(child);
    }

    /**
     * Adds a variable to this DataScope
     * @param key the name of the variable to set
     * @param value the initial value to give the variable
     */
    public void setVariable(String key, ExpressionValue value) {
        TimeStamp stamp = new TimeStamp(getVm().getExecution().getTimeStamp());
        if(variables.containsKey(key)) {
            variables.get(key).pushValue(value, stamp);
        }
        else {
            DataObject o = new DataObject();
            o.pushValue(value, stamp);
            variables.put(key, o);
        }
    }

    /**
     * Pops the current value of the variable off the stack if the variable is not in the hash table it does nothing
     * @param key the key of the variable to unset
     */
    public ExpressionValue unsetVariable(String key) {
        if(variables.containsKey(key)) {
            return variables.get(key).popValue();
        }
        return null;
    }

    /**
     * Gets the current value of the specified variable
     * @param key the key of the variale to get
     * @return the ExpressionValue of the variable
     */
    public ExpressionValue getVariable(String key) {
        DataObject var = variables.get(key);
        if(var != null) {
            return var.getCurrentValue();
        }
        else if(getParent() != null) {
            return getParent().getVariable(key);
        }
        else {
            return null;
        }
    }

    /**
     * @return the parent
     */
    public DataScope getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(DataScope parent) {
        this.parent = parent;
    }

    /**
     * @return the vm
     */
    public AbstractVirtualMachine getVm() {
        return vm;
    }

    /**
     * @param vm the vm to set
     */
    public void setVm(AbstractVirtualMachine vm) {
        this.vm = vm;
    }

    public int getVariableCount() {
        int count = 0;
        if(parent != null) {
            count += parent.getVariableCount();
        }
        count += variables.size();
        return count;

    }

    protected Vector<DataObject> getAccessibleVariables() {
        Vector<DataObject> objects = new Vector<DataObject>();
        
        if(parent != null) {
            Vector<DataObject> parents = parent.getAccessibleVariables();
            objects.addAll(parents);
        }
        objects.addAll(variables.values());
        return objects;
    }
    public Iterator<DataObject> getVariables() {
        return getAccessibleVariables().iterator();
    }
}
