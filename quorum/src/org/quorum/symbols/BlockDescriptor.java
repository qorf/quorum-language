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

    public VariableParameterCommonDescriptor getVariable(String key) {
        VariableParameterCommonDescriptor var = variables.get(key);
        if(var == null)
            var = parent.getVariable(key);
        return var;
    }

    public MethodDescriptor getMethod(String key) {
        if(parent != null) {
            return parent.getMethod(key);
        }
        else {
            return null;
        }
    }

    public ClassDescriptor getClass(String key) {
        return null;

    }

    public CompilerError add(VariableDescriptor descriptor) {        
        CompilerError error = super.isDefined(descriptor, variables);
        if (error == null) {
            variables.put(descriptor.getStaticKey(), descriptor);
            parent.getNumberOfVariables();
            descriptor.setVariableNumber(getNumberOfVariables());
        }

        return error;
    }

    public CompilerError add(ParameterDescriptor descriptor) {        
        CompilerError error = super.isDefined(descriptor, variables);
        if (error == null) {
            variables.put(descriptor.getStaticKey(), descriptor);
        }
        return error;
    }

    public CompilerError add(BlueprintDescriptor descriptor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CompilerError add(MethodDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a action to a block.");
    }

    public CompilerError add(SystemActionDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a system function to a block.");
    }

    public CompilerError add(ClassDescriptor descriptor) {
        throw new UnsupportedOperationException("Cannot add a class to a block.");
    }

    public void setParent(Scopable parent) {
        this.parent = parent;
    }

    public Scopable getParent() {
        return parent;
    }

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
    public BlockDescriptor getNextBlock() {
        return children.get(currentBlock++);

    }

    public String getScopeString() {
        if(parent == null) {
            return getName();
        }
        else {
            return parent.getScopeString() + ":" + getName();
        }
    }

    public BlueprintDescriptor getBlueprint(String key) {
        return null;
    }

    @Override
    public int getNumberOfVariables() {
        return parent.getNumberOfVariables() + variables.size();
    }
}
