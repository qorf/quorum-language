/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.execution.ExpressionValue;

/**
 * When executed this stepInto copies an ExpressionValue from a variable into a register
 * @author Aaron Willows
 */
public class VariableMoveStep extends IntermediateStep {

    /**
     * This number represents the value of a temporary variable in the
     * symbol table. This value is conceptually similar to a "register."
     */
    private int temp;
    private int index = -1;
    private VariableParameterCommonDescriptor value;

    @Override
    public void execute() {
        String queryName = value.getStaticKey();
        ExpressionValue eVal;
        eVal = vm.getDataEnvironment().getVariableValue(queryName);

        if (eVal != null) {
            ExpressionValue newValue = new ExpressionValue(eVal);
            vm.getDataEnvironment().setRegister(temp, newValue);
        } else {
            this.isInErrorState = true;
        }
    }

    @Override
    public void unexecute() {
        if (!this.isInErrorState) {
            vm.getDataEnvironment().popRegister(temp);
        }
    }

    /**
     * @return the temp
     */
    public int getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(int temp) {
        this.temp = temp;
    }

    /**
     * @return the value
     */
    public VariableParameterCommonDescriptor getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(VariableParameterCommonDescriptor value) {
        this.value = value;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
