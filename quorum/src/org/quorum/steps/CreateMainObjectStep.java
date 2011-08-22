/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.RuntimeObject;

/**
 * This op-code creates an object initially in the virtual machine
 * and sets the "This" pointer to that object. This object
 * should not be extended or altered, as it is intended for use
 * only at the beginning of an execution.
 * 
 * @author stefika
 */
public final class CreateMainObjectStep extends CreateObjectStep{

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        RuntimeObject ro = new RuntimeObject();
        ro.setClazz(clazz);
        de.callingClassStackPush(de.getThisObject());

        //instantiate an object by telling the data environment to do it
        int hash = de.addNewObject(ro);
        hashes.push(hash);
        
        de.setToObjectScope(hash);
        vm.getDataEnvironment().pushCreateObjectOpcode(vm.getExecution().getExecutionPosition());
    }

    @Override
    public void unexecute() {
        int hash = hashes.pop();
        DataEnvironment de = vm.getDataEnvironment();
        de.removeObject(hash);
        de.callStackUndo();
        vm.getDataEnvironment().undoCreateObjectOpcode();
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.CREATE_MAIN_OBJECT_STEP.getName();
    }

}
