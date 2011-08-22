/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.BlockScope;
import org.quorum.execution.DataEnvironment;

/**
 * Check block opcode that adds a custom scope, used to determine how to
 * handle an exception.
 *
 * @author Melissa Stefik
 */
public class BeginCheckScopeStep extends IntermediateStep{

    private String blockName;

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        BlockScope cs = new BlockScope();
        
        cs.setPreviousThisPointer(de.getThisPointer());
        cs.setParent(de.getLocalScope());
        cs.setBlockName(blockName);
        de.callStackPush(cs);
        de.checkScopeStackPush(cs);
    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.callStackUndo();
        de.checkScopeStackUndo();
    }

    /**
     * set the static key for the check statement.
     *
     * @param name
     */
    public void setBlockName(String name){
        blockName = name;
    }

}
