/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginManager;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.plugins.RuntimeError;
import org.quorum.execution.ActivationRecord;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.DataObject;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.BlueprintDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.Scopable;
import org.quorum.symbols.SystemActionDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.symbols.VirtualMethodDescriptor;

/**
 * This step represents calling a function in Sodbeans, including activation
 * records, return values, and appropriate scoping operations (e.g., objects).
 * 
 * @author Andreas Stefik,Melissa Stefik, and Aaron Willows
 */
public class CallStep extends IntermediateStep {

    protected ActivationRecord activationRecord;
    /**
     * The method descriptor for the method from a virtual table, that will
     * actually be called.
     */
    private MethodDescriptor methodCallee;
    private Vector<Integer> arguments;
    private boolean isObjectCall = false;
    protected int previousThisPointer;
    private VariableParameterCommonDescriptor parentObject;
    private boolean isThisCall = false;
    private boolean isSoloMethodCall = false;
    private boolean isCalleeLoaded = false;
    /**
     * This value represents the object hash for the parent of this
     * call. In other words, it represents which object the call step
     * was called on. It does NOT represent which object called this particular
     * activation record.
     */
    protected int objectHashParent = -1;
    protected Stack<Integer> objectParentStack = new Stack<Integer>();
    private boolean isNested = false;
    

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        previousThisPointer = de.getThisPointer();
        int returnRegister = this.activationRecord.getReturnRegister();

        //this activation record is set to use a compile time determined
        //method descriptor. This will need to be redone using the v-table.
        if (isObjectCall) {
            ExpressionValue parent = de.getVariableValue(parentObject.getStaticKey());
            if (parent.isNull()) {
                this.runtimeError = new RuntimeError("The Object being referenced is undefined.", ErrorTypeDescriptor.getUndefinedObjectError(), vm);
                setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
                this.isInErrorState = vm.throwException(runtimeError);
                this.wasInErrorState = true;

            } else {
                objectHashParent = parent.getObjectHash();
                de.setThisPointer(objectHashParent);
            }
            //now set the This pointer

        } else {
            objectHashParent = de.getThisPointer();
        }

        //until you know the object in question (objectHashParent), you
        //cannot determine which method to call. So, you
        //must first get the object from the data environment
        RuntimeObject objectCallingTheMethod = de.getObject(objectHashParent);
        objectParentStack.push(objectHashParent);
        ActivationRecord record = activationRecord;

        if (objectCallingTheMethod != null) {
            //next we need to get the class from the runtime object
            ClassDescriptor clazz = objectCallingTheMethod.getClazz();
            if (!objectCallingTheMethod.getMode().equals("") && !(methodCallee instanceof BlueprintDescriptor)) {//check if it needs to be in a different mode
                clazz = clazz.getParent(objectCallingTheMethod.getMode());
            }

            //next we need to figure out which method we are going to call
            //from the v-table
            VirtualMethodDescriptor vmd = vm.getVirtualMethodDescriptor(clazz, methodCallee);
            if (vmd != null) {
                record = new ActivationRecord(vmd.getActivationRecord());
                methodCallee = vmd.getMethodDescriptor();
                //keep track of what methods we have called.
                de.pushMethodCall(methodCallee);
                if (methodCallee instanceof SystemActionDescriptor) {
                    //get the class the method is in
                    Scopable parent = methodCallee.getParent();
                    if (parent instanceof ClassDescriptor) {
                        ClassDescriptor parentClass = (ClassDescriptor) parent;
                        executeSystemAction((SystemActionDescriptor) methodCallee, parentClass,
                                objectCallingTheMethod, record, de);
                        de.setThisPointer(this.previousThisPointer); //set it back
                    } else { //this should never happen and may be a compiler bug if it does.
                        this.runtimeError = new RuntimeError("The Object being referenced is undefined.", ErrorTypeDescriptor.getUndefinedObjectError(), vm);
                        setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
                        this.isInErrorState = vm.throwException(runtimeError);
                        this.wasInErrorState = true;
                    }

                    return;
                }
            } else {

                //is the user requesting a native function
                if (methodCallee instanceof SystemActionDescriptor) {
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

            //finally, rebuild the activation record using this method.
            //don't forget to set record.setPreviousThisPointer(previousThisPointer);
            record.setLineInformation(activationRecord.getLineInformation());
            record.setPreviousThisPointer(previousThisPointer);
            record.setReturnRegister(returnRegister);
            record.setThisPointer(objectHashParent);

            //if this is a polymorphic function in a parent class, set
            //the object to its correct mode.
            //Store the correct mode for the object in the activation record (no matter what)
            if (vmd != null) {
                String scopeString = vmd.getMethodDescriptor().getParent().getScopeString();
                objectCallingTheMethod.setPreviousMode(objectCallingTheMethod.getMode());
                if (scopeString.compareTo(objectCallingTheMethod.getMode()) == 0 && scopeString.compareTo(objectCallingTheMethod.getClazz().getStaticKey()) == 0) {
                    objectCallingTheMethod.setToThisMode();
                } else if (scopeString.compareTo(objectCallingTheMethod.getClazz().getStaticKey()) != 0) {
                    objectCallingTheMethod.setMode(scopeString);
                } else {
                    objectCallingTheMethod.setToThisMode();
                }
            }

            record.setThisMode(objectCallingTheMethod);
            record.setParent(objectCallingTheMethod);
        }
        int returnValue = getReturn();
        record.setReturnValueAbsolute(returnValue);
        record.setReturnScope(de.getLocalScope());
        //record.setReturnRegister(activationRecord.getReturnRegister());

        //now push on the values of the various registers
        if (this.methodCallee != null) {
            Iterator<Integer> usedRegisters = this.methodCallee.getUsedRegisters();
            while (usedRegisters.hasNext()) {
                int i = usedRegisters.next();
                if (de.hasRegisterValue(i)) {
                    ExpressionValue register = de.getRegister(i);
                    if (register != null) {
                        record.pushRegister(i, register);
                    }
                }
            }
        }


        //This list grabs the values of registers from the local scope
        //before a new activation record is pushed on the stack. This must
        //happen before the activation record is pushed on because the registers
        //that have the parameter values are only in this scope.
        ArrayList<ExpressionValue> values = new ArrayList<ExpressionValue>();
        if (arguments != null) {
            for (int j = 0; j < arguments.size(); j++) {
                //ParameterDescriptor parameter = record.getParameters().get(j);
                ExpressionValue value = de.getRegister(arguments.get(j));
                ExpressionValue new_value = new ExpressionValue(value);
                values.add(new_value);
            }
        }

        //This pushes the new activation record onto the stack.
        de.callStackPush(record);

        //This pushes the actual register values onto the new activation 
        //record's local scope.
        if (arguments != null) {
            for (int j = 0; j < arguments.size(); j++) {
                ParameterDescriptor parameter = record.getParameters().get(j);
                // ExpressionValue value = de.getRegister(arguments.get(j));
                //ExpressionValue new_value = new ExpressionValue(value);
                ExpressionValue new_value = values.get(j);
                new_value.setName(parameter.getName());
                record.addVariableLocalScopeOnly(parameter.getName(), new_value);
                de.setVariableValue(parameter.getName(), new_value);
            }
        }
    }

    protected void executeSystemAction(SystemActionDescriptor system,
            ClassDescriptor clazz, RuntimeObject objectCallingTheMethod,
            ActivationRecord record, DataEnvironment de) {
        String classKey = clazz.getStaticKey();
        String methodKey = methodCallee.getStaticKey();
        PluginManager pluginManager = vm.getPluginManager();
        //call down to a plugin
        Plugin plugin = pluginManager.get(classKey);
        if (plugin != null) {
            PluginCall call = new PluginCall();
            //setup the call
            call.setActionName(methodKey);
            call.setCallingObject(objectCallingTheMethod);
            call.setVirtualMachine(vm);

            if (arguments != null) {
                for (int j = 0; j < arguments.size(); j++) {
                    ParameterDescriptor parameter = record.getParameters().get(j);
                    ExpressionValue value = de.getRegister(arguments.get(j));
                    ExpressionValue new_value = new ExpressionValue(value);
                    new_value.setName(parameter.getName());
                    //record.addVariable(parameter.getName(), new_value);
                    //de.setVariableValue(parameter.getName(), new_value);

                    call.addArgument(parameter.getName(), new_value);
                }
            }

            //make the call and get the return value
            PluginReturn ret = plugin.execute(call);

            //set the return value to the appropriate register

            if (ret.getReturnValue() != null && !ret.getReturnValue().getType().isVoid()) {
                ret.getReturnValue().setRegister(this.activationRecord.getReturnRegister());
                ExpressionValue returnMe = new ExpressionValue(ret.getReturnValue());

                DataObject o = new DataObject();
                o.pushValue(returnMe);
                de.dataStackPush(o);

                de.setRegister(returnMe.getRegister(), returnMe);

            }
            //exit out, we don't need to setup activation records for this native call.
            //check to ensure the return was successful, otherwise
            //throw an exception
            return;

        } //throw an exception?
    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        if (de.getNumberOfMethodsCalled() == 0) {
            return; //should we flag this as a bug?
        }
        MethodDescriptor popMethodCall = de.popMethodCall();
        if (popMethodCall == null) {
            return; //again, a bug?
        }

        if (popMethodCall instanceof SystemActionDescriptor) {
            PluginCall call = new PluginCall();
            Scopable parent = methodCallee.getParent();
            ClassDescriptor clazz = null;
            if (parent instanceof ClassDescriptor) {
                ClassDescriptor parentClass = (ClassDescriptor) parent;
                clazz = parentClass;
            } else {
                return; // a bug?
            }
            String classKey = clazz.getStaticKey();
            String methodKey = methodCallee.getStaticKey();
            PluginManager pluginManager = vm.getPluginManager();

            //setup the call
            call.setActionName(methodKey);
            RuntimeObject objectCallingTheMethod = de.getObject(objectParentStack.pop());
            call.setCallingObject(objectCallingTheMethod);
            call.setVirtualMachine(vm);
            Plugin plug = pluginManager.get(classKey);
            PluginReturn ret = plug.unexecute(call);
            if (!methodCallee.getReturnType().isVoid()) {
                int returnRegister = this.activationRecord.getReturnRegister();
                de.popRegister(returnRegister);
            }
        } else {

            ActivationRecord nearest = de.peekNearestActivationRecord();
            de.callStackUndo();
            de.setThisPointer(previousThisPointer);
            RuntimeObject objectCallingTheMethod = de.getObject(objectParentStack.pop());
            objectCallingTheMethod.setMode(objectCallingTheMethod.getPreviousMode());
            if (arguments != null) {
                for (int j = 0; j < arguments.size(); j++) {
                    nearest.undoAddVariable(nearest.getParameters().get(j).getName());
                }
            }
            activationRecord.clearRegisters();
            isInErrorState = false;
        }
    }

    /**
     * Overriden to change the return value in, for example, a main method.
     * @return
     */
    protected int getReturn() {
        return vm.getExecution().getExecutionPosition() + 1;
    }

    /**
     * @return the activationRecord
     */
    public ActivationRecord getActivationRecord() {
        return activationRecord;
    }

    /**
     * @param activationRecord the activationRecord to set
     */
    public void setActivationRecord(ActivationRecord activationRecord) {
        this.activationRecord = activationRecord;
    }

    @Override
    public String toString() {
        return "call function at " + getReturn();
    }

    @Override
    public int nextStep() {
        if (this.isInErrorState) {//don't actually jump to the new position
            return vm.getExecution().getExecutionPosition();
        }
        int loc = 0;

        if (methodCallee instanceof SystemActionDescriptor) {
            //Skip the data pop step, since nothing was pushed on
            //for a native function.
            if(this.wasInErrorState){
                loc = vm.getExecution().getExecutionPosition() + 1;
            }else{
                loc = vm.getExecution().getExecutionPosition() + 2;
            }
        } else {
            loc = this.methodCallee.getLocation().getStart();
        }
        return loc;
    }

    /**
     * @return the arguments
     */
    public Vector<Integer> getArguments() {
        return arguments;
    }

    /**
     * @param arguments the arguments to set
     */
    public void setArguments(Vector<Integer> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.CALL_STEP.getName();
    }

    /**
     * @return the objectHashParent
     */
    public int getObjectHashParent() {
        return objectHashParent;
    }

    /**
     * @param objectHashParent the objectHashParent to set
     */
    public void setObjectHashParent(int objectHashParent) {
        this.objectHashParent = objectHashParent;
    }

    /**
     * @return the methodCallee
     */
    public MethodDescriptor getMethodCallee() {
        return methodCallee;
    }

    /**
     * set the MethodDescriptor for the method callee.
     * @param method
     */
    public void setMethodCallee(MethodDescriptor method) {
        this.methodCallee = method;
    }

    /**
     * @return the isObjectCall
     */
    public boolean IsObjectCall() {
        return isObjectCall;
    }

    /**
     * @param isObjectCall the isObjectCall to set
     */
    public void setIsObjectCall(boolean isObjectCall) {
        this.isObjectCall = isObjectCall;
    }

    /**
     * @return the parentObject
     */
    public VariableParameterCommonDescriptor getParentObject() {
        return parentObject;
    }

    /**
     * @param parentObject the parentObject to set
     */
    public void setParentObject(VariableParameterCommonDescriptor parentObject) {
        this.parentObject = parentObject;
    }

    /**
     * @return the isThisCall
     */
    public boolean isThisCall() {
        return isThisCall;
    }

    /**
     * @param isThisCall the isThisCall to set
     */
    public void setIsThisCall(boolean isThisCall) {
        this.isThisCall = isThisCall;
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return the isSoloMethodCall
     */
    public boolean isSoloMethodCall() {
        return isSoloMethodCall;
    }

    /**
     * @param isSoloMethodCall the isSoloMethodCall to set
     */
    public void setIsSoloMethodCall(boolean isSoloMethodCall) {
        this.isSoloMethodCall = isSoloMethodCall;
    }

    public void setIsNestedMethodCall(boolean nested) {
        this.isNested = nested;
    }

    /**
     * @return the isNested
     */
    public boolean isNested() {
        return isNested;
    }

    /**
     * @return the isCalleeLoaded
     */
    public boolean isCalleeLoaded() {
        return isCalleeLoaded;
    }

    /**
     * @param isCalleeLoaded the isCalleeLoaded to set
     */
    public void setIsCalleeLoaded(boolean isCalleeLoaded) {
        this.isCalleeLoaded = isCalleeLoaded;
    }
}
