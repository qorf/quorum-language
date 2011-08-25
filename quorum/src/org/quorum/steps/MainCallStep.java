/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.execution.ExpressionValue;

/**
 * This step represents a call from a method that started a program
 * running in the first place, a "main" method.
 * 
 * @author Andreas Stefik
 */
public class MainCallStep extends CallStep{
    private ClassDescriptor classDescriptor;

    @Override
    public void execute() {

        //create an object of this class's type and setup the program
        //for execution
        DataEnvironment de = vm.getDataEnvironment();
        int hash = de.getThisPointer();

        ExpressionValue value = new ExpressionValue();
        TypeDescriptor desc = new TypeDescriptor();
        desc.setName(classDescriptor.getStaticKey());
        value.setType(desc);
        value.setObjectHash(hash);

        objectHashParent = hash;
        //now do what a normal function would do
        super.execute();

    }

    @Override
    public void unexecute() {
        super.unexecute();

        //now destroy the object we created.
    }

    @Override
    protected int getReturn() {
        return -2;
    }

    /**
     * @return the classDescriptor
     */
    public ClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }

    /**
     * @param classDescriptor the classDescriptor to set
     */
    public void setClassDescriptor(ClassDescriptor classDescriptor) {
        this.classDescriptor = classDescriptor;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
