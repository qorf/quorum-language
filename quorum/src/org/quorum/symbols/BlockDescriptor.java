/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.CompilerError;

/**
 *
 * @author Morgoth and Melissa Stefik
 */
public class BlockDescriptor extends Descriptor implements Scopable{
    private Scopable parent;
    private Vector<BlockDescriptor> children;
    private HashMap<String, VariableParameterCommonDescriptor> variables;
    int currentBlock;
    

    public BlockDescriptor() {
        children = new Vector<BlockDescriptor>();
        variables = new HashMap<String, VariableParameterCommonDescriptor>();
    }

    /**
     * returns an iterator that iterates over this BlockDescriptors children
     * @return
     */
    public Iterator<BlockDescriptor> getChildren() {
        return children.iterator();
    }

    @Override
    public VariableParameterCommonDescriptor getVariable(String key) {
        VariableParameterCommonDescriptor var = variables.get(key);
        if(var == null)
            var = parent.getVariable(key);
        return var;
    }
    
    /**
     * Returns all variables defined in this block. Note that this does not
     * return variables defined in a child's scope.
     * 
     * @return 
     */
    public Iterator<VariableParameterCommonDescriptor> getVariables() {
        return variables.values().iterator();
    }
    
    /**
     * This method grabs all variables defined in this scope and all parent
     * scopes, except for those in a parent class descriptor. These variables
     * are filtered based on line number, whereby variables that are not
     * defined before the current line are not included. 
     * 
     * @param line
     * @return 
     */
    public Iterator<VariableParameterCommonDescriptor> getAllVariablesExceptInClass(int line) {
        //first create a hash of all variables in this scope
        HashMap<String, VariableParameterCommonDescriptor> map = new HashMap<String, VariableParameterCommonDescriptor>();
        Iterator<VariableParameterCommonDescriptor> val = this.getVariables();
        while(val.hasNext()) {
            VariableParameterCommonDescriptor next = val.next();
            if(!map.containsKey(next.getStaticKey()) && next.getLineBegin() <= line) {
                map.put(next.getStaticKey(), next);
            }
        }
        
        //now grab all parents except for the class descriptor and see if they mask
        //any variables. If they do, do not put in the masked variable
        Scopable currentParent = parent;
        while(currentParent != null) {
            if(currentParent instanceof BlockDescriptor) {
                BlockDescriptor block = (BlockDescriptor) currentParent;
                val = block.variables.values().iterator();                
                currentParent = currentParent.getParent();
            }
            else if (currentParent instanceof MethodDescriptor){
                MethodDescriptor block = (MethodDescriptor) currentParent;
                val = block.getVariables();                
                currentParent = null;
            }
            
            while(val.hasNext()) {
                VariableParameterCommonDescriptor next = val.next();
                if(!map.containsKey(next.getStaticKey()) && next.getLineBegin() <= line) {
                    map.put(next.getStaticKey(), next);
                }
            }
        }
        
        return map.values().iterator();
    }

    @Override
    public MethodDescriptor getMethod(String key) {
        if(parent != null) {
            return parent.getMethod(key);
        }
        else {
            return null;
        }
    }

    @Override
    public ClassDescriptor getClass(String key) {
        return null;

    }

    @Override
    public CompilerError add(VariableDescriptor descriptor) {        
        CompilerError error = super.isDefined(descriptor, variables);
        if (error == null) {
            variables.put(descriptor.getStaticKey(), descriptor);
            parent.getNumberOfVariables();
            descriptor.setVariableNumber(getNumberOfVariables());
        }

        return error;
    }

    @Override
    public CompilerError add(ParameterDescriptor descriptor) {        
        CompilerError error = super.isDefined(descriptor, variables);
        if (error == null) {
            variables.put(descriptor.getStaticKey(), descriptor);
        }
        return error;
    }

    @Override
    public CompilerError add(BlueprintDescriptor descriptor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompilerError add(MethodDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a action to a block.");
    }

    @Override
    public CompilerError add(SystemActionDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a system function to a block.");
    }

    @Override
    public CompilerError add(ClassDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a class to a block.");
    }

    @Override
    public void setParent(Scopable parent) {
        this.parent = parent;
    }

    @Override
    public Scopable getParent() {
        return parent;
    }

    @Override
    public void add(BlockDescriptor descriptor) {
       descriptor.setParent(this);
       Integer k = (Integer)children.size();
       descriptor.setName(k.toString());
       children.add(descriptor);
    }

    /**
     * Gets the next block in this BlockDescriptor's children.
     * @deprecated use getChildren() instead
     * @return
     */
    @Deprecated
    @Override
    public BlockDescriptor getNextBlock() {
        return children.get(currentBlock++);

    }

    @Override
    public String getScopeString() {
        if(parent == null) {
            return getName();
        }
        else {
            return parent.getScopeString() + ":" + getName();
        }
    }

    @Override
    public BlueprintDescriptor getBlueprint(String key) {
        return null;
    }

    @Override
    public int getNumberOfVariables() {
        return parent.getNumberOfVariables() + variables.size();
    }
}
