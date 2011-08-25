/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;

/**
 *
 * @author Andy
 */
public class JumpStep extends JumpBaseStep {
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
