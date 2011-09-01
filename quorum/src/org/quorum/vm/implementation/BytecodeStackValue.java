/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.objectweb.asm.Opcodes;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Result;
import org.quorum.symbols.TypeDescriptor;
/**
 *
 * @author mattlawson
 */

public class BytecodeStackValue {
    private Result result;
    private TypeDescriptor type;
    private int varNumber;
    private boolean isConstant; 
    
    public BytecodeStackValue() {
        result = new Result();
        type = new TypeDescriptor();
        varNumber = 0;
        isConstant = true;
    }
    
    public BytecodeStackValue(boolean isConstant, int varNumber) {
        this.isConstant = isConstant;
        this.varNumber = varNumber;
        result = new Result();
    }
    
    public BytecodeStackValue(ExpressionValue value, boolean isConstant, int varNumber) {
        this.isConstant = isConstant;
        this.varNumber = varNumber;
        result = value.getResult();
        type = value.getType();
    }
    
    public BytecodeStackValue(Result result, TypeDescriptor type, boolean isConstant, int varNumber) {
        this.isConstant = isConstant;
        this.varNumber = varNumber;
        this.result = result;
        this.type = type;
    }
    
    public Result getResult() {
        return result;
    }
    
    public TypeDescriptor getTypeDescriptor() {
        return type;
    }
    
    public int getVarNumber() {
        return varNumber;
    }
    
    public boolean isConstant() {
        return isConstant;
    }
    
    public void setResult(Result result) {
        this.result = result;
    }
    
    public void setType (TypeDescriptor type) {
        this.type = type;
    }
    
    public void setVarNumber (int varNumber) {
        this.varNumber = varNumber;
    }
    
    public void setAsVariable(int varNumber) {
        isConstant = false;
        this.varNumber = varNumber;
    }
    
    // temporary
    public Object getValue(){
        if (type.isBoolean())
            return result.boolean_value;
        else if (type.isInteger())
            return result.integer;
        else if (type.isNumber())
            return result.number;
        else if (type.isText())
            return result.text;
        else
            return null;
    }
    
    public int getLoadOpCode() {
        if (type.isInteger() || type.isBoolean())
            return Opcodes.ILOAD;
        else if(type.isNumber())
            return Opcodes.DLOAD;
        return Opcodes.ALOAD;
    }
//    
    public String getByteCodeTypeDescriptor() {
        if(type.isBoolean()) {
            return "Z";
        }
        else if(type.isText()) {
            return "Ljava/lang/String;";
        }
        else if(type.isNumber()) {
            return "D";
        }
        else if(type.isInteger()) {
            return "I";
        }
        else {
            return null;
        }
    }

    /**
     * This value computes the size of the object being pushed on the stack
     * from its type, to help with computations related to max stack size
     * in methods.
     * 
     * TODO: Make this change size depending upon if it's a double, int, etc.
     * 
     * @return 
     */
    public int getSize() {
        return 1;
    }
}
