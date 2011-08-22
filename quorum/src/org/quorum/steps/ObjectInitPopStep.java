/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.Stack;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.RuntimeObject;

/**
 * This opcode causes the VM to jump back to a position + 1, related to
 * where the object was initialized from.
 *
 * @author Andreas Stefik and Melissa Stefik
 */
public class ObjectInitPopStep extends IntermediateStep{

    /**
     * This stack stores which opcode this pop step matches up to (either create
     * object or init parent steps). If initParent is true it matches to an init parent
     * step. If initParent is false it matches to a create object step.
     */
    private Stack<Boolean> initParent = new Stack<Boolean>();
    private Stack<String> modes = new Stack<String>();
    private Stack<RuntimeObject> currentObject = new Stack<RuntimeObject>();

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        RuntimeObject thisObject = de.getThisObject();
        
        modes.push(thisObject.getMode());

        if(thisObject.isThisMode()){
            currentObject.push(thisObject);

            de.callStackPop();

            RuntimeObject callingClass = de.callingClassStackPop();
            if(callingClass != null){
                de.setThisPointer(callingClass.getHashKey());
            }
            initParent.push(false);
        } else{
            thisObject.setToThisMode();
            initParent.push(true);
        }
    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        Boolean parent = initParent.pop();
        String mode = modes.pop();

        if(parent){
            de.getThisObject().setMode(mode);
        } else{
            de.callStackUndo();
            de.callingClassStackUndo();
            RuntimeObject pop = currentObject.pop();
            de.setThisPointer(pop.getHashKey());
        }

        
    }

    /**
     * Jumps back to the position where the object was instantiated + 1.
     * @return
     */
    @Override
    public int nextStep() {//clazz should never be null. If it is, it's a
                           //serious bug in the compiler.
        return vm.getDataEnvironment().popCreateObjectOpcode() + 1;
    }
}
