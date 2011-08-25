/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import java.util.Stack;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.Result;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 *  Parent class for an auto boxing step
 *
 * @author Melissa Stefik
 */
public class AutoBoxCreateStep extends UnaryOperationStep{

    /** Keeps a stack of the hash values that this op-code has
     * has instantiated. The ClassDescriptor will not change for
     * these objects.
     */
    protected Stack<Integer> hashes = new Stack<Integer>();

    /** An object will be created of type clazz.
     *
     */
    protected ClassDescriptor clazz = null;

    private TypeDescriptor primitiveType = new TypeDescriptor();
    private Stack<Integer> thisHash = new Stack<Integer>();

    @Override
    public void execute() {

        if(primitiveType.isInteger()){
            clazz = vm.getSymbolTable().getClassDescriptor("Libraries.Language.Types.Integer");
        } else if(primitiveType.isNumber()){
            clazz = vm.getSymbolTable().getClassDescriptor("Libraries.Language.Types.Number");
        } else if(primitiveType.isText()){
            clazz = vm.getSymbolTable().getClassDescriptor("Libraries.Language.Types.Text");
        } else if(primitiveType.isBoolean()){
            clazz = vm.getSymbolTable().getClassDescriptor("Libraries.Language.Types.Boolean");
        }

        DataEnvironment de = vm.getDataEnvironment();
        thisHash.push(de.getThisObject().getHashKey());

        //store the calling class and it's mode
        de.callingClassStackPush(de.getThisObject());

        //create the new runtime object
        RuntimeObject ro = new RuntimeObject();

        ro.setClazz(clazz);

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

        //return to original scope
        int newScope = thisHash.pop();
        de.setThisPointer(newScope);

        de.callingClassStackUndo();
        vm.getDataEnvironment().undoCreateObjectOpcode();
    }

    /**
     * The CreateObjectStep opcode sets the instruction pointer to the
     * beginning of the object initialization routine in question. For
     * example, if an object has type Z, then this opcode's next step
     * would be at the beginning of Z.
     * @return
     */
    @Override
    public int nextStep() {//clazz should never be null. If it is, it's a
                           //serious bug in the compiler.
        return clazz.getLocation().getStart();
    }

    @Override
    protected Result calculateOpcode(ExpressionValue value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the primitiveType
     */
    public TypeDescriptor getPrimitiveType() {
        return primitiveType;
    }

    /**
     * @param primitiveType the primitiveType to set
     */
    public void setPrimitiveType(TypeDescriptor primitiveType) {
        this.primitiveType = primitiveType;
    }

    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
