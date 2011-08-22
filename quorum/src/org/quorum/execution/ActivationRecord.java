/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.util.HashMap;
import java.util.Iterator;
import org.quorum.symbols.BlockDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.Parameters;

/**
 * This class stores the unique data required for each method call.
 *
 * @author Andreas Stefik
 * @author Melissa Stefik
 */
public class ActivationRecord extends RuntimeScope {
    private Parameters parameters;
    private int returnValueAbsolute;
    private ExpressionValue returnValue;
    private RuntimeScope returnScope;
    private int returnRegister = -1;
    private int thisPointer = -1;
    private String thisMode;
    private HashMap<Integer, ExpressionValue> registers;

    /**
     * Constructor
     */
    public ActivationRecord() {
        registers = new HashMap<Integer, ExpressionValue>();
    }

    /**
     * Copy Constructor for activation record.
     *
     * @param record
     */
    public ActivationRecord(ActivationRecord record) {
        parameters = record.parameters;
        returnValueAbsolute = record.returnValueAbsolute;
        returnValue = new ExpressionValue(record.returnValue);
        //don't copy over registers
        registers = new HashMap<Integer, ExpressionValue>();
        setLineInformation(record.getLineInformation());
    }

    /**
     * Push a register/value onto the activation record.
     *
     * @param register is an integer representing the register location.
     * @param value is the value to placed in the register.
     */
    public void pushRegister(int register, ExpressionValue value) {
        registers.put(register, value);
    }

    /**
     * Removes the register and its value from the activation record and returns
     * that value.
     *
     * @param register is the register to remove from the activation record.
     * @return ExpressionValue popped off the activation record.
     */
    public ExpressionValue popRegister(int register) {
        return registers.remove(register);
    }

    /**
     * Gets the expression value from a register on the activation record. The
     * register and value remain in the activation record.
     *
     * @param register the int location of the register on the activation record.
     * @return Expression value at a particular register on the activation record.
     */
    public ExpressionValue getRegister(int register) {
        return registers.get(register);
    }

    /**
     * Returns an iterator over all the registers on the activation record.
     * @return Iterator over Integer values (registers).
     */
    public Iterator<Integer> getRegisters() {
        return registers.keySet().iterator();
    }

    /**
     * Removes and deletes all registers on the activation record.
     */
    public void clearRegisters() {
        registers.clear();
    }

    /**
     * This method stores the method's parametersOld so they can be added to this ActivationRecord at runtime
     * It also creates the scoping hierarchy for this method so that can be retained for the omniscient debugger
     * @param method the MethodDescriptor to build
     */
    public void build(MethodDescriptor method) {        
        //build the scoping hierarchy
        Iterator<BlockDescriptor> blocks = method.getChildren();        
        while(blocks.hasNext()) {
            BlockDescriptor block = blocks.next();
            RuntimeScope child = new RuntimeScope();
            child.build(block);
            this.addChild(child);            
        }

        //add the  method's parametersOld so we can fill them in when the method is called
        parameters = method.getParameters();
    }

    /**
     * @return the returnOffset
     */
    public int getReturnValueAbsolute() {
        return returnValueAbsolute;
    }    

    /**
     * @param returnOffset the returnOffset to set
     */
    public void setReturnValueAbsolute(int returnOffset) {
        this.returnValueAbsolute = returnOffset;
    }

    /**
     * @return the returnValue
     */
    public ExpressionValue getReturnValue() {
        return returnValue;
    }

    /**
     * @param returnValue the returnValue to set
     */
    public void setReturnValue(ExpressionValue returnValue) {
        this.returnValue = returnValue;
    }

    /**
     * @return the returnScope
     */
    public RuntimeScope getReturnScope() {
        return returnScope;
    }

    /**
     * @param returnScope the returnScope to set
     */
    public void setReturnScope(RuntimeScope returnScope) {
        this.returnScope = returnScope;
    }

    /**
     * Gets the register in which the return value will be placed.
     *
     * @return the returnRegister
     */
    public int getReturnRegister() {
        return returnRegister;
    }

    /**
     * Sets the register in which the return value will be placed.
     *
     * @param returnRegister the returnRegister to set
     */
    public void setReturnRegister(int returnRegister) {
        this.returnRegister = returnRegister;
    }

    /**
     * @return the parameters
     */
    public Parameters getParameters() {
        return parameters;
    }
    /**
     * @return the thisPointer
     */
    public int getThisPointer() {
        return thisPointer;
    }

    /**
     * @param thisPointer the thisPointer to set
     */
    public void setThisPointer(int thisPointer) {
        this.thisPointer = thisPointer;
    }

    /**
     * @return the mode
     */
    public String getThisMode() {
        return thisMode;
    }

    /**
     * @param mode the mode to set
     */
    public void setThisMode(RuntimeObject ro) {
        this.thisMode = ro.getMode();
    }
}
