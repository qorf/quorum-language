/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.Stack;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 * This opcode is responsible for jumping to the appropriate
 * position follow initialization of parents.
 * @author Melissa Stefik
 */
public class ObjectInitParentStep extends IntermediateStep {

    private ClassDescriptor parent = new ClassDescriptor();
    private Stack<String> previousMode = new Stack<String>();
    private VariableParameterCommonDescriptor variable;

    @Override
    public void execute() {
        //create a new runtime object and get current this pointer and add it as a parent to this class
        DataEnvironment de = vm.getDataEnvironment();
        RuntimeObject ro = new RuntimeObject();
        RuntimeObject currentThis = de.getThisObject();

        ro.setClazz(parent);

        //set the current "this" object to a new mode and add the parent class
        previousMode.push(currentThis.getMode());
        currentThis.addParentClass(ro);
        currentThis.setMode(ro);

        vm.getDataEnvironment().pushCreateObjectOpcode(vm.getExecution().getExecutionPosition());
    }

    @Override
    public void unexecute() {
        RuntimeObject currentThis = vm.getDataEnvironment().getThisObject();
        String pop = previousMode.pop();
        currentThis.setMode(pop);
        currentThis.removeParent(pop);
        vm.getDataEnvironment().undoCreateObjectOpcode();
    }

    @Override
    public int nextStep() {
            return parent.getLocation().getStart() + parent.getNumberFlatParents(); //parent is a classdescriptor of a parent class
    }

    /**
     * Set the parent that is being initialized in this step.
     * @param classDescriptor
     */
    public void setParent(ClassDescriptor classDescriptor) {
        parent = classDescriptor;
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

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
