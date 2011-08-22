/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;
import java.util.Iterator;
import org.quorum.execution.ActivationRecord;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.DataObject;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;

/**
 * Represents an op-code that returns from a function.
 * 
 * @author Andreas Stefik, Aaron Willows
 */
public class ReturnStep extends IntermediateStep {

    private int returnAddress;
    private int returnRegister;
    private boolean returnsAValue;
    private String previousMode;
    private MethodExecution methodToReturnFrom;
    private boolean poppedActivationRecord = false;

    @Override
    public void execute() {
        DataEnvironment de = vm.getDataEnvironment();
        ActivationRecord rec = de.peekNearestActivationRecord();
        de.setThisPointer(rec.getPreviousThisPointer());
        
        //set the This pointer back to the mode it was in
        //it should gather this information from th activation record
        //which would set this information in call step
        int hash = rec.getThisPointer();
        RuntimeObject ro = de.getObject(hash);
        if(ro != null) {
            previousMode = ro.getMode();
            ro.setMode(ro.getPreviousMode());
        }

        returnAddress = rec.getReturnValueAbsolute();
        

        ExpressionValue myReturn = null;
        int retrurnSpot = -1;
        if(isReturnsAValue()){
            ExpressionValue value = de.getRegister(returnRegister);
            ExpressionValue new_value = new ExpressionValue(value);
            DataObject o = new DataObject();
            o.pushValue(new_value);
            de.dataStackPush(o);
            int setToRegister = rec.getReturnRegister();
            retrurnSpot = setToRegister;
            myReturn = new_value;
        }
        de.popNearestActivationRecord();
        if(isReturnsAValue()) {
            de.setRegister(retrurnSpot, myReturn);
        }
    }

    @Override
    public void unexecute() {
        DataEnvironment de = vm.getDataEnvironment();
        de.undoPopNearestActivationRecord();

        ActivationRecord rec = (ActivationRecord) de.peekNearestActivationRecord();
        de.setThisPointer(rec.getThisPointer());

        if(previousMode != null){
            de.getThisObject().setMode(previousMode);
        }

        Iterator<Integer> registers = rec.getRegisters();
        while(registers.hasNext()) {
            int i = registers.next();
            ExpressionValue register = rec.getRegister(i);
            if(register != null) {
                de.popRegister(i);
            }
        }
    }

    @Override
   public boolean isReturnStep() {
        return true;
    }

    @Override
    public String toString() {
            return "return to " + returnAddress;
    }
    

    @Override
    public int nextStep() {
        return returnAddress + 1;
    }

    /**
     * @return the returnValue
     */
    public int getReturnRegister() {
        return returnRegister;
    }

    /**
     * @param returnValue the returnValue to set
     */
    public void setReturnRegister(int returnRegister) {
        this.returnRegister = returnRegister;
    }

    /**
     * @return the returnsAValue
     */
    public boolean isReturnsAValue() {
        return returnsAValue;
    }

    /**
     * @param returnsAValue the returnsAValue to set
     */
    public void setReturnsAValue(boolean returnsAValue) {
        this.returnsAValue = returnsAValue;
    }

    /**
     * @return the methodToReturnFrom
     */
    public MethodExecution getMethod() {
        return methodToReturnFrom;
    }

    /**
     * @param methodToReturnFrom the methodToReturnFrom to set
     */
    public void setMethod(MethodExecution methodToReturnFrom) {
        this.methodToReturnFrom = methodToReturnFrom;
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.RETURN_STEP.getName();
    }

}



