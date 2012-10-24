/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.Scopable;

/**
 * Opcode used to close a scope.
 * 
 * @author Melissa Stefik
 */
public class EndScopeStep extends IntermediateStep{
    protected String blockTag;
    private boolean lastIfScope = false;
    private Scopable scope;

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.callStackPop();
    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.callStackUndo();
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }

    public void setBlockTag(String tag){
        blockTag = tag;
    }

    public String getBlockTag() {
        return blockTag;
    }
    
    public void setLastIfScope(boolean value){
        lastIfScope = value;
    }
    
    public boolean isLastIfScope(){
        return lastIfScope;
    }

    public void setCurrentScope(Scopable currentScope) {
        scope = currentScope;
    }
    
    public Scopable getCurrentScope(){
        return scope;
    }
}
