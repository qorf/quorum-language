/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.DataEnvironment;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.Result;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;

/**
 * Opcode for the binary Is A step. Result is true if the types are the same
 * and false if they are not.
 *
 * @author melissa stefik
 */
public class BinaryIsACustomCustomStep extends BinaryOperationStep{

    private TypeDescriptor rightType;

    @Override
    public void execute() {
        DataEnvironment data = vm.getDataEnvironment();
        ExpressionValue left = data.getRegister(leftRegister);
        ExpressionValue right = new ExpressionValue();
        right.setType(rightType);
        ExpressionValue result = new ExpressionValue();
        result.setType(left.getType());

        result.setRegister(resultRegister);
        Result res = calculateOpcode(left, right);
        result.setType(res.getType());
        result.setResult(res);
        data.setRegister(resultRegister, result);
    }

    @Override
    public void unexecute() {
        vm.getDataEnvironment().popRegister(resultRegister);
    }

    @Override
    protected Result calculateOpcode(ExpressionValue left, ExpressionValue right) {
        Result result = new Result();
        if(!left.isNull() && right != null ) {
            if(left.getType() != null && right.getType() != null){
                if(left.getType().getStaticKey().compareTo(right.getType().getStaticKey()) == 0){
                    result.boolean_value = true;
                }else{
                    RuntimeObject object = vm.getDataEnvironment().getObject(left.getObjectHash());
                    RuntimeObject parent = object.getParent(right.getType().getStaticKey());
                    if(parent != null){
                        result.boolean_value = true;
                    }else{
                        result.boolean_value = false;
                    }
                }
            }else{
                result.boolean_value = false;
            }
        }
        result.type = Result.BOOLEAN;
        return result;
    }

    /**
     * @return the rightType
     */
    public TypeDescriptor getRightType() {
        return rightType;
    }

    /**
     * @param rightType the rightType to set
     */
    public void setRightType(TypeDescriptor rightType) {
        this.rightType = rightType;
    }
}
