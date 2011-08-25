/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ActivationRecord;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.MethodDescriptor;

/**
 * Constructor opcode for a quorum class is able to hook into the instantiation
 * of an object.
 *
 * @author Melissa Stefik
 */
public class OnCreateStep extends CallStep{


    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        previousThisPointer = de.getThisPointer();

        objectHashParent = de.getThisPointer();

        //get the object being generated
        RuntimeObject objectCallingTheConstructor = de.getObject(objectHashParent);

        //next we need to get the class from the runtime object
        ClassDescriptor clazz = objectCallingTheConstructor.getClazz();
        if(!objectCallingTheConstructor.isThisMode()){//if it's not in this mode get the parent being initialized.
            clazz = clazz.getParent(objectCallingTheConstructor.getMode());
        }

        //make the activation record and get the constructor
        ActivationRecord record = new ActivationRecord(activationRecord);
        MethodDescriptor constructor = clazz.getConstructor();
        setMethodCallee(constructor);

        //finally, rebuild the activation record using this method.
        //don't forget to set record.setPreviousThisPointer(previousThisPointer);
        record.setLineInformation(activationRecord.getLineInformation());
        record.setPreviousThisPointer(previousThisPointer);
        record.setThisPointer(objectHashParent);

        //Store the correct mode for the object in the activation record (no matter what)
        objectCallingTheConstructor.setPreviousMode(objectCallingTheConstructor.getMode());
        record.setThisMode(objectCallingTheConstructor);
        record.setParent(objectCallingTheConstructor);

        de.callStackPush(record);

    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        ActivationRecord rec = (ActivationRecord) de.callStackPeek();

        de.callStackUndo();
        de.setThisPointer(previousThisPointer);
        RuntimeObject objectCallingTheMethod = de.getObject(rec.getThisPointer());
        objectCallingTheMethod.setMode(objectCallingTheMethod.getPreviousMode());

        activationRecord.clearRegisters();
        isInErrorState = false;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
