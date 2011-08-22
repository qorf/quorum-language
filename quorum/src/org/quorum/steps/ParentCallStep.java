/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.ArrayList;
import java.util.Vector;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ActivationRecord;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.Scopable;
import org.quorum.symbols.SystemActionDescriptor;
import org.quorum.symbols.VirtualMethodDescriptor;

/**
 * Opcode to call a parent method.
 * @author Melissa Stefik
 */
public class ParentCallStep extends CallStep{

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        int previousThisPointer = de.getThisPointer();
        
        int returnRegister = this.activationRecord.getReturnRegister();

        objectHashParent = de.getThisPointer();

        RuntimeObject objectCallingTheMethod = de.getObject(objectHashParent);
        RuntimeObject methodCallObject = new RuntimeObject();

        methodCallObject.setClazz((ClassDescriptor) super.getMethodCallee().getParent());
        
        //next we need to get the class from the runtime object
        ClassDescriptor clazz = objectCallingTheMethod.getClazz();

        //tell the calling object it's mode (scope) nees to change to a parent
        objectCallingTheMethod.setMode(methodCallObject);

        //Rebuild the activation record using this method.
        //don't forget to set record.setPreviousThisPointer(previousThisPointer);
        ActivationRecord record = activationRecord;
        
        
        MethodDescriptor methodCallee = super.getMethodCallee();
        
        //next we need to figure out which method we are going to call
        //from the v-table
        VirtualMethodDescriptor vmd = vm.getVirtualMethodDescriptor(clazz, methodCallee);
        if (vmd != null) {
            record = new ActivationRecord(vmd.getActivationRecord());
            methodCallee = vmd.getMethodDescriptor();
            //keep track of what methods we have called.
            de.pushMethodCall(methodCallee);
            if(methodCallee instanceof SystemActionDescriptor) {
                //get the class the method is in
                Scopable parent = methodCallee.getParent();
                if(parent instanceof ClassDescriptor) {
                    ClassDescriptor parentClass = (ClassDescriptor) parent;
                    executeSystemAction((SystemActionDescriptor) methodCallee, parentClass,
                        objectCallingTheMethod, record, de);
                    de.setThisPointer(this.previousThisPointer); //set it back
                }
                else { //this should never happen and may be a compiler bug if it does.
                    this.runtimeError = new RuntimeError("The Object being referenced is undefined.", ErrorTypeDescriptor.getUndefinedObjectError(), vm);
                    setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
                    this.isInErrorState = vm.throwException(runtimeError);
                    this.wasInErrorState = true;
                }
                
                return;
            }
        }
        else {

            //is the user requesting a native function
            if(methodCallee instanceof SystemActionDescriptor) {
                executeSystemAction((SystemActionDescriptor) methodCallee, clazz,
                        objectCallingTheMethod, record, de);
                de.setThisPointer(this.previousThisPointer); //set it back
                //keep track of what methods we have called.
                de.pushMethodCall(methodCallee);
                return;
            } else {
                this.runtimeError = new RuntimeError("The Object being referenced is undefined.", ErrorTypeDescriptor.getUndefinedObjectError(), vm);
                setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
                this.isInErrorState = vm.throwException(runtimeError);
                this.wasInErrorState = true;
            }
        }
        
        record.setPreviousThisPointer(previousThisPointer);
        record.setReturnRegister(returnRegister);

        record.setThisPointer(objectHashParent);

        //if this is a polymorphic function in a parent class, set
        //the object to its correct mode.
        //Store the correct mode for the object in the activation record (no matter what)
        String scopeString = de.getThisObject().getClazz().getScopeString();
        objectCallingTheMethod.setPreviousMode(objectCallingTheMethod.getMode());
        if (scopeString.compareTo(objectCallingTheMethod.getMode()) == 0 && scopeString.compareTo(objectCallingTheMethod.getClazz().getStaticKey())==0) {
            objectCallingTheMethod.setToThisMode();
        } else if (scopeString.compareTo(objectCallingTheMethod.getClazz().getStaticKey()) != 0) {
            objectCallingTheMethod.setMode(scopeString);
        } else {
            objectCallingTheMethod.setToThisMode();
        }
        

        record.setThisMode(objectCallingTheMethod);
        record.setParent(objectCallingTheMethod);

        int returnValue = getReturn();
        record.setReturnValueAbsolute(returnValue);
        record.setReturnScope(de.getLocalScope());
        
        ArrayList<ExpressionValue> values = new ArrayList<ExpressionValue>();
        Vector<Integer> arguments = super.getArguments();
        if (arguments != null) {
            for(int j = 0; j < arguments.size(); j++) {
                ExpressionValue value = de.getRegister(arguments.get(j));
                ExpressionValue new_value = new ExpressionValue(value);
                values.add(new_value);
            }
        }
        
        de.callStackPush(record);
        if (super.getArguments() != null) {
            for (int j = 0; j < super.getArguments().size(); j++) {
                ParameterDescriptor parameter = record.getParameters().get(j);
                ExpressionValue new_value = values.get(j);
                new_value.setName(parameter.getName());
                record.addVariable(parameter.getName(), new_value);
                de.setVariableValue(parameter.getName(), new_value);
            }
        }

    }

    @Override
    public void unexecute() {
        super.unexecute();

    }
}
