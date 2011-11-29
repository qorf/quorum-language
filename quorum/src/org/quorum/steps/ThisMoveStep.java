/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;

/**
 *
 * @author Melissa Stefik
 */
public class ThisMoveStep extends IntermediateStep{

    private int temp;
    
    
    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        RuntimeObject ro = de.getThisObject();

        ExpressionValue value = new ExpressionValue();
        value.setObjectHash(de.getThisPointer());
        value.setType(ro.getClazz().getType());
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
}
