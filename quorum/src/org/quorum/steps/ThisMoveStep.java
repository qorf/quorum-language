/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author Melissa Stefik
 */
public class ThisMoveStep extends IntermediateStep{

    private int temp;
    private TypeDescriptor type;
    
    
    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        RuntimeObject ro = de.getThisObject();

        ExpressionValue value = new ExpressionValue();
        value.setObjectHash(de.getThisPointer());
        type = ro.getClazz().getType();
        value.setType(type);
        value.setRegister(temp);
        vm.getDataEnvironment().setRegister(temp, value);

    }

    @Override
    public void unexecute() {
        if (!this.isInErrorState) {
            vm.getDataEnvironment().popRegister(temp);
        }
    }

    public void setTemp(int resultRegister) {
        temp = resultRegister;
    }

    /**
     * @return the temp
     */
    public int getTemp() {
        return temp;
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return the type
     */
    public TypeDescriptor getType() {
        return type;
    }
}
