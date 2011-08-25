/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

/**
 *  This class very literally does nothing, as its purpose is to provide 
 * visitors Null executions to avoid writing != null tests everywhere.
 * @author Andreas Stefik
 */
public class NullExecutionStep extends ExecutionStep{

    public NullExecutionStep() {
        
    }
    
    @Override
    public void execute() {
    }

    @Override
    public void unexecute() {
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
