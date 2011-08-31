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
    
    public void setAsVariable(int varNumber) {
        isConstant = false;
        this.varNumber = varNumber;
    }
    
    // temporary
    public Object getValue(){
        if (type.isText())
            return result.text;
        return result.integer;
    }
    
    public int getLoadOpCode() {
        if (type.isInteger())
            return Opcodes.ILOAD;
        return Opcodes.ALOAD;
    }
//    
    public String getByteCodeTypeDescriptor() {
        if (type.isInteger())
            return "I";
        return "Ljava/lang/String;";
    }
//    public int get
}
