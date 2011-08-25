/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.BlockScope;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.RuntimeScope;

/**
 * Enter a block opcode that adds a custom scope, used for if statements, etc.
 *
 * @author Melissa Stefik
 */
public class BeginScopeStep extends IntermediateStep{

    protected String blockName;

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        BlockScope cs = new BlockScope();
        RuntimeScope localScope = de.getLocalScope();

        cs.setBlockName(blockName);
        cs.setParent(localScope);
        de.callStackPush(cs);
    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.callStackUndo();
    }

    /**
     * set the static key for the check statement.
     *
     * @param name
     */
    public void setBlockName(String name){
        blockName = name;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
