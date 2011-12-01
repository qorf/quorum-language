/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.BlockScope;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;

/**
 * Check block opcode that adds a custom scope, used to determine how to
 * handle an exception.
 *
 * @author Melissa Stefik
 */
public class BeginCheckScopeStep extends IntermediateStep{

    private String blockName;
    private CheckLandingPads landingPads;

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

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return the landingPads
     */
    public CheckLandingPads getLandingPads() {
        return landingPads;
    }

    /**
     * @param landingPads the landingPads to set
     */
    public void setLandingPads(CheckLandingPads landingPads) {
        this.landingPads = landingPads;
    }
}
