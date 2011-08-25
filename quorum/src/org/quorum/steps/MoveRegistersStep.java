/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Andreas Stefik
 */
public class MoveRegistersStep extends IntermediateStep{

    private int leftRegister = -1;
    private int rightRegister = -1;

    @Override
    public void execute() {
        ExpressionValue value = vm.getDataEnvironment().getRegister(getRightRegister());
        ExpressionValue copy = new ExpressionValue(value);
        vm.getDataEnvironment().setRegister(getLeftRegister(),copy);
    }

    @Override
    public void unexecute() {
        vm.getDataEnvironment().popRegister(getLeftRegister());
    }

    /**
     * @return the leftRegister
     */
    public int getLeftRegister() {
        return leftRegister;
    }

    /**
     * @param leftRegister the leftRegister to set
     */
    public void setLeftRegister(int leftRegister) {
        this.leftRegister = leftRegister;
    }

    /**
     * @return the rightRegister
     */
    public int getRightRegister() {
        return rightRegister;
    }

    /**
     * @param rightRegister the rightRegister to set
     */
    public void setRightRegister(int rightRegister) {
        this.rightRegister = rightRegister;
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
