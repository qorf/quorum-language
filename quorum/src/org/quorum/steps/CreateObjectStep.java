/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.Stack;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.execution.ExpressionValue;

/**
 *  This step instantiates an object and places it in the heap.
 *
 * @author Andreas Stefik
 */
public class CreateObjectStep extends IntermediateStep {

    /** Keeps a stack of the hash values that this op-code has
     * has instantiated. The ClassDescriptor will not change for
     * these objects.
     */
    protected Stack<Integer> hashes = new Stack<Integer>();

    /** An object will be created of type clazz.
     *
     */
    protected ClassDescriptor clazz;

    private VariableParameterCommonDescriptor variable;
    private Stack<Integer> thisHash = new Stack<Integer>();

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        thisHash.push(de.getThisObject().getHashKey());

        //store the calling class and its mode
        de.callingClassStackPush(de.getThisObject());

        //create the new runtime object
        RuntimeObject ro = new RuntimeObject();
        ro.setLineInformation(lineInformation);
        ro.setClazz(clazz);
        ro.setAssociatedObjectVariable(variable);
        
        //instantiate an object by telling the data environment to do it
        int hash = de.addNewObject(ro);

        if(variable != null) {
            ExpressionValue value = new ExpressionValue();
            value.setName(variable.getName());
            TypeDescriptor desc = new TypeDescriptor();
            desc.setName(clazz.getStaticKey());
            value.setType(desc);
            value.setObjectHash(hash);
            de.setVariableValue(variable.getStaticKey(), value);
        }

        hashes.push(hash);

        de.setToObjectScope(hash);
        vm.getDataEnvironment().pushCreateObjectOpcode(vm.getExecution().getExecutionPosition());
    }

    @Override
    public void unexecute() {
        int hash = hashes.pop();
        DataEnvironment de = vm.getDataEnvironment();
        de.removeObject(hash);

        de.callStackUndo();

        //return to original scope
        int newScope = thisHash.pop();
        de.setThisPointer(newScope);

        de.callingClassStackUndo();
        if(variable != null) {
            de.popVariableValue(variable.getStaticKey());
        }
        vm.getDataEnvironment().undoCreateObjectOpcode();
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

    @Override
    public String getStaticKey() {
        return IntermediateConstants.CREATE_OBJECT_STEP.getName();
    }

    /**
     * The CreateObjectStep opcode sets the instruction pointer to the
     * beginning of the object initialization routine in question. For
     * example, if an object has type Z, then this opcode's next step
     * would be at the beginning of Z.
     * @return
     */
    @Override
    public int nextStep() {//clazz should never be null. If it is, it's a 
                           //serious bug in the compiler.
        return clazz.getLocation().getStart();
    }

    /**
     * @return the variable
     */
    public VariableParameterCommonDescriptor getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(VariableParameterCommonDescriptor variable) {
        this.variable = variable;
    }
}
