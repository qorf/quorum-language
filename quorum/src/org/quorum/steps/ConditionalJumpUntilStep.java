/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Melissa Stefik
 */
public class ConditionalJumpUntilStep extends ConditionalJumpLoopStep {

    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(leftRegister);
        jump = !value.getResult().boolean_value;
        iterationNum++;
    }

    @Override
    public void unexecute() {
        iterationNum--;
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue value = data.getRegister(leftRegister);
        jump = !value.getResult().boolean_value;
    }

}
