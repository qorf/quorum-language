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
    private JumpType type;
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
    
    /**
     * @return the type
     */
    public JumpType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(JumpType type) {
        this.type = type;
    }
}
