/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ExpressionValue;

/**
 * Opcode to end a always statement. If there is an active runtime error there
 * should be either another try catch statement to jump to or it should fall
 * through.
 * 
 * @author Melissa Stefik
 */
public class AlwaysEndStep extends IntermediateStep {

    private boolean threwException = false;
    private boolean alertException = false;
    @Override
    public void execute() {
        RuntimeError error = vm.getExceptions().exceptionStackPop();
        if(error != null){
            threwException = true;
            vm.throwException(error);
        }

        ExpressionValue alertError = vm.getExceptions().alertStackPop();
        if(alertError != null){
            alertException = true;
            vm.alertException(alertError);
        }
    }

    @Override
    public void unexecute() {
        if(callStateBeforeException != null){
            if(threwException){
                vm.unThrowException(callStateBeforeException);
            }else if (alertException){
                vm.unAlertException(callStateBeforeException);
            }
        }
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
