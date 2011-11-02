/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.NullExecutionStep;
import org.quorum.steps.*;
import org.quorum.symbols.AccessModifierEnum;
import org.quorum.symbols.BlueprintDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.Parameters;
import org.quorum.symbols.Result;
import org.quorum.symbols.SystemActionDescriptor;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 * This class takes a set of opcodes
 * @author Andreas Stefik,Melissa Stefik, and Matt Lawson
 */
public class QuorumJavaBytecodeStepVisitor implements ExecutionStepVisitor, Opcodes {

    private ClassWriter classWriter;
    private FieldVisitor fieldVisitor;
    private MethodVisitor methodVisitor;
    private AnnotationVisitor annotationVisitor;
    private BytecodeStack stack = new BytecodeStack();
    private String processedClazzName = "";
    private final int THIS = 0;
    private ClassDescriptor currentClass = null;
    private ClassExecution currentClassExecution = null;
    private MethodExecution currentMethodExecution = null;
    private final String PLUGIN_NAME = "<plugin>";
    private int fieldSize = 1;
    private boolean first = true;
    
    public QuorumJavaBytecodeStepVisitor() {
    }

    /**
     * This method computes the bytecode for a class's constructor. This method
     * will be called twice, the first time for the current class
     * and the second time for when the current class is initialized
     * by a child class. This second constructor may never be used, but
     * is important to weave in anyway, otherwise it would waste memory, 
     * as children would have to initialize their parents (and their parents
     * would initialize their parents) and so on, leading to unnecessary
     * garbage collection.
     * 
     * @param clazz
     * @param isParent True if this constructor should initialize its parents 
     */
    private void computeConstructor(boolean isParent) {
        int numSystem = currentClass.getNumberSystemActions();
        String staticKey = currentClass.getStaticKey();
        String name = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);

        String signature = "";
        if (isParent) {
            signature = "()V";
        } else {
            signature = "(Z)V";
        }


        //call the class's initialization function
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", signature, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, THIS);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");

        //now do field initialization
        stack.startMethod(1);
        Vector<ExecutionStep> steps = this.currentClassExecution.getSteps();
        for (int i = 0; i < steps.size(); i++) {
            ExecutionStep step = steps.get(i);
            step.visit(this);
        }

        if (isParent) {
            //initialize all of the parent objects as fields
//            Iterator<ClassDescriptor> parents = currentClass.getFlattenedListOfParents();
//            while (parents.hasNext()) {
//                ClassDescriptor parent = parents.next();
//                String parentKey = parent.getStaticKey();
//                String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
//                String converted = QuorumConverter.convertStaticKeyToBytecodePath(parentKey);
//                methodVisitor.visitVarInsn(ALOAD, THIS);
//                methodVisitor.visitTypeInsn(NEW, converted);
//                methodVisitor.visitInsn(DUP);
//                //push a boolean onto the stack
//                methodVisitor.visitInsn(ICONST_0);
//                methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "(Z)V");
//                methodVisitor.visitFieldInsn(PUTFIELD, name, parentName, QuorumConverter.convertStaticKeyToBytecodePathTypeName(parentKey));
//            }
            
            //now that all parents have been instantiated, get 
            //their parents and set them appropriately
//            parents = currentClass.getFlattenedListOfParents();
//            while(parents.hasNext()) {
//                ClassDescriptor parent = parents.next();
//                String parentKey = parent.getStaticKey();
//                String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
//                String convertedParentName = QuorumConverter.convertStaticKeyToBytecodePath(parentKey);
//                
//                Iterator<ClassDescriptor> grandParents = parent.getFlattenedListOfParents();
//                while(grandParents.hasNext()) {
//                    ClassDescriptor grandParent = grandParents.next();
//                    String grandParentKey = grandParent.getStaticKey();
//                    String grandParentName = QuorumConverter.convertParentStaticKeyToValidName(grandParent.getStaticKey());
//                    String convertedGrandParent = QuorumConverter.convertStaticKeyToBytecodePath(grandParentKey);
//                    
//                    //load the this pointer
//                    methodVisitor.visitVarInsn(ALOAD, 0);
//                    //get the current object's parent field
//                    methodVisitor.visitFieldInsn(GETFIELD, this.processedClazzName, grandParentName, convertedGrandParent);
//                    //put it into the appropriate parent
//                    methodVisitor.visitFieldInsn(PUTFIELD, convertedParentName, grandParentName, convertedGrandParent);
//                }
//            }
        }

        final String objectName = "___$$$Calling___$$$___Object$$$___";
        final String javaObjectPath = "Ljava/lang/Object;";
        //initialize the plugin last
        if (numSystem > 0) {
            methodVisitor.visitVarInsn(ALOAD, THIS);
            String converted = QuorumConverter.convertStaticKeyToPluginPath(currentClass.getStaticKey());
            String convertedSupplement = QuorumConverter.convertStaticKeyToPluginPathTypeName(currentClass.getStaticKey());
            methodVisitor.visitTypeInsn(NEW, converted);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "()V");
            methodVisitor.visitFieldInsn(PUTFIELD, name, PLUGIN_NAME, convertedSupplement);

            //now load the current object into the plugin, in case it needs it
            methodVisitor.visitVarInsn(ALOAD, THIS);
            methodVisitor.visitFieldInsn(GETFIELD, name, PLUGIN_NAME, convertedSupplement);
            methodVisitor.visitVarInsn(ALOAD, THIS);
            methodVisitor.visitFieldInsn(PUTFIELD, convertedSupplement, objectName, javaObjectPath);
            fieldSize += 2;
        }

        int maxLocals = 3;

        if (isParent) {
            maxLocals++;
        }

        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(fieldSize, maxLocals);
        methodVisitor.visitEnd();
    }

    /**
     * Takes any method used by a parent and weaves them into the current
     * class via composition.
     * 
     * @param parent
     * @param action 
     */
    private void computeParentMethod(ClassDescriptor parent, MethodDescriptor action) {
        String name = action.getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(action);
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, name, params, null, null);
        methodVisitor.visitCode();

        //names
        String key = currentClass.getStaticKey();
        String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
        String convertedParent = QuorumConverter.convertStaticKeyToBytecodePath(parent.getStaticKey());
        String convertedSupplement = QuorumConverter.convertStaticKeyToBytecodePathTypeName(key);
        String signature = QuorumConverter.convertMethodDescriptorToBytecodeSignature(action);

        String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
        stack.startMethod(1);
        addParametersAsVariables(action);

        //get the appropriate parent field
        methodVisitor.visitVarInsn(ALOAD, 0);
        //the four parameters: 1) get a field, 2) the class the field is in
        //3) the name of the field, and 4) 
        methodVisitor.visitFieldInsn(GETFIELD, className, parentName, convertedSupplement);
        //load parameters
        Parameters parameters = action.getParameters();
        int position = 1;
        for (int i = 0; i < parameters.size(); i++) {

            ParameterDescriptor param = parameters.get(i);
            methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(param.getType()), position);
            int size = QuorumConverter.getSizeOfType(param.getType());
            position += size;
        }

        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, convertedParent, action.getName(), signature);

        int returnOpcode = QuorumConverter.convertTypeToReturnOpcode(action.getReturnType());
        methodVisitor.visitInsn(returnOpcode);

        int stackSize = position;
        int varSize = position;

        if (action.getReturnType().isNumber()) {
            stackSize += 1;
        }

        methodVisitor.visitMaxs(stackSize, varSize);
        methodVisitor.visitEnd();
    }

    private void computeParentMethods(ClassDescriptor parent) {
        Collection<MethodDescriptor> methods = parent.getAllMethods(AccessModifierEnum.PUBLIC);
        Iterator<MethodDescriptor> iterator = methods.iterator();
        while (iterator.hasNext()) {
            MethodDescriptor method = iterator.next();
            MethodDescriptor baseMethod = currentClass.getMethod(method.getStaticKey());
            //weave in the method into the base class, by composition
            if (baseMethod == null && !(method instanceof BlueprintDescriptor)) {
                computeParentMethod(parent, method);
            }
        }

    }
    
    /**
     * 
     * @param action 
     */
    public void computeSystemAction(SystemActionDescriptor action) {
        String name = action.getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(action);
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, name, params, null, null);
        methodVisitor.visitCode();

        //names
        String key = currentClass.getStaticKey();
        String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
        String converted = QuorumConverter.convertStaticKeyToPluginPath(key);
        String convertedSupplement = QuorumConverter.convertStaticKeyToPluginPathTypeName(key);
        String signature = QuorumConverter.convertMethodDescriptorToBytecodeSignature(action);

        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, className, PLUGIN_NAME, convertedSupplement);
        //load parameters
        Parameters parameters = action.getParameters();
        int position = 1;
        for (int i = 0; i < parameters.size(); i++) {
            ParameterDescriptor param = parameters.get(i);
            methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(param.getType()), position);
            int size = QuorumConverter.getSizeOfType(param.getType());
            position += size;
        }

        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, converted, action.getName(), signature);

        int returnOpcode = QuorumConverter.convertTypeToReturnOpcode(action.getReturnType());
        methodVisitor.visitInsn(returnOpcode);

        int stackSize = position;
        int varSize = position;

        if (action.getReturnType().isNumber()) {
            stackSize += 1;
        }

        methodVisitor.visitMaxs(stackSize, varSize);
        methodVisitor.visitEnd();
    }
    
    /**
     * @return the classWriter
     */
    public ClassWriter getClassWriter() {
        return classWriter;
    }

    /**
     * @param classWriter the classWriter to set
     */
    public void setClassWriter(ClassWriter classWriter) {
        this.classWriter = classWriter;
    }

    /**
     * Add in all parameters as local variables so they can be later referenced
     * by the JVM. For example, if we are in the method
     * 
     * action foo (integer i)
     * end
     * 
     * we can reference 'i' by using iload_1.
     * 
     * @param method The method we're in.
     */
    private void addParametersAsVariables(MethodDescriptor method) {
        for (int i = 1; i <= method.getParameters().size(); i++) {
            TypeDescriptor constantType = new TypeDescriptor();
            ParameterDescriptor parameter = method.getParameters().get(i - 1);
            constantType.setName(parameter.getType().getName());
            
            stack.addParameter(parameter.getName(), parameter.getType());
        }
    }

    /**
     * Push a constant value onto the stack, such as an integer, number, text value
     * or boolean value.
     * @param value 
     */
    public void pushConstant(Result value) {
        if (value.getType().isInteger()) {
            if ((value.integer >= -1 && value.integer <= 5)) {
                switch (value.integer) {
                    case -1:
                        methodVisitor.visitInsn(ICONST_M1);
                        break;
                    case 0:
                        methodVisitor.visitInsn(ICONST_0);
                        break;
                    case 1:
                        methodVisitor.visitInsn(ICONST_1);
                        break;
                    case 2:
                        methodVisitor.visitInsn(ICONST_2);
                        break;
                    case 3:
                        methodVisitor.visitInsn(ICONST_3);
                        break;
                    case 4:
                        methodVisitor.visitInsn(ICONST_4);
                        break;
                    case 5:
                        methodVisitor.visitInsn(ICONST_5);
                        break;
                }
            } else if ((value.integer >= -128 && value.integer <= 127)) {
                methodVisitor.visitIntInsn(BIPUSH, value.integer);
            } else if ((value.integer >= -32768 && value.integer <= 32767)) {
                methodVisitor.visitIntInsn(SIPUSH, value.integer);
            } else {
                methodVisitor.visitLdcInsn(value.integer);
            }
        } else if (value.getType().isBoolean()) {
            if (value.boolean_value) {
                methodVisitor.visitInsn(ICONST_1);
            } else {
                methodVisitor.visitInsn(ICONST_0);
            }
        } else {
            Object v = null;
            if (value.getType().isNumber())
                v = value.number;
            else if (value.getType().isText())
                v = value.text;
            methodVisitor.visitLdcInsn(v);
        }
    }
    
    /**
     * Pushes the value of a variable onto the stack.
     * 
     * @param type - the type of variable
     * @param interpreterVarNumber - the variable's number in the interpreter. mapped to a JVM variable pool number.
     */
    public void pushVariable(TypeDescriptor type, int interpreterVarNumber) {
        methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(type), stack.getMappedVariableNumber(interpreterVarNumber));
    }
    
    /**
     * Performs the necessary bytecode operations for assigning a field variable (non local).
     * 
     * @param variable
     * @param subVariableName
     * @param subVariableType 
     */
    private void performFieldAssignment(VariableParameterCommonDescriptor variable, String subVariableName, TypeDescriptor subVariableType, boolean isDefined) {
        String fieldParent;
        String variableName;
        TypeDescriptor variableType;

        //if the sub-variable name does not match
        if (!subVariableName.equals("")) {
            //process and visit the variable instruction based on variable type.
            fieldParent = QuorumConverter.convertStaticKeyToBytecodePath(variable.getType().getName());
            methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(variable.getType()), stack.getMappedVariableNumber(variable.getVariableNumber()));
            variableName = subVariableName;
            variableType = subVariableType;
        } else {//if the sub-variable name does match
            //process and visit the aload variable instruction
            fieldParent = QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey());
            methodVisitor.visitVarInsn(ALOAD, 0);
            variableName = variable.getName();
            variableType = variable.getType();
        }

        //process the expression and put the field
        if(!isDefined)
            processFieldExpressions();
        else
            processExpressions();
        methodVisitor.visitFieldInsn(PUTFIELD, fieldParent, variableName, QuorumConverter.convertTypeToBytecodeString(variableType));
    }

    /**
     * Performs the necessary bytecode operations for assigning a value to a local variable.
     * @param value
     * @param step 
     */
    private void performLocalAssignment(TypeDescriptor valueType, AssignmentStep step) {
        int variableNumber = step.getVariable().getVariableNumber();

        int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber);
        
        // Is it defined yet?
        if (mappedVariableNumber == -1) {
            stack.setVariable(variableNumber, valueType);
            mappedVariableNumber = stack.getMappedVariableNumber(variableNumber);
        }
        methodVisitor.visitVarInsn(QuorumConverter.getStoreOpcode(valueType), mappedVariableNumber);
        //stack.setVariable(variableNumber, valueType);
    }

    /**
     * This helper method performs an assignment into either a local variable or a field variable.
     * 
     * @param value The bytecode stack value needed by the assignment.
     * @param step The Assignment step being performed.
     * @param isDefined True if the variable being assigned a value was already defined.
     */
    private void performAssignment(TypeDescriptor valueType, AssignmentStep step, boolean isDefined) {
        VariableParameterCommonDescriptor variable = step.getVariable();
        if (variable == null) {
            Logger.getLogger(QuorumJavaBytecodeStepVisitor.class.getName()).log(
                    Level.SEVERE,
                    "The AssignmentStep object of type: "
                    + step.getClass().getCanonicalName()
                    + ", has a missing variable value (null). ");
        }

        boolean isField = step.getVariable().isFieldVariable();

        // Is the variable we are assigning to defined?
        if (isDefined) {
            // It is defined--is it a field?
            if (isField) {
                performFieldAssignment(step.getVariable(), step.getSubVariableName(), valueType, isDefined);
            } else {
                performLocalAssignment(valueType, step);
            }
        } else {
            if (isField) {
                performFieldAssignment(step.getVariable(), step.getSubVariableName(), valueType, isDefined);
            } else {
                performLocalAssignment(valueType, step);

                // It's not a field, so we need to tell the system that we are
                // adding a new value, which increases the "field size."
                if (valueType.isNumber()) {
                    fieldSize += 2;
                } else {
                    fieldSize += 1;
                }

            }

        }
    }

    /**
     * Converts an integer (top stack value) to a number
     * 
     * stack before step:   integer -> number;
     * stack after step:    number -> number
     */
    private void prepareNumberIntegerOperation() {
        methodVisitor.visitInsn(I2D);

        // Pop off the integer.
        stack.popExpressionType();
        
        // Push a number.
        stack.pushExpressionType(TypeDescriptor.getNumberType());
    }

    /**
     * Converts an integer (second stack value) to a number (swaps the stack 
     * values, performs the conversion, then swaps the new values if order matters)
     * 
     * stack before step:   number -> integer;
     * stack after step:    number -> number
     * 
     * @param orderMatters 
     */
    private void prepareIntegerNumberOperation(boolean orderMatters) {
        TypeDescriptor numberVal = stack.popExpressionType();
        TypeDescriptor integerVal = stack.popExpressionType();

        swapOperandStackValues(numberVal, integerVal);

        methodVisitor.visitInsn(I2D);

        if (orderMatters) {
            swapOperandStackValues(numberVal, numberVal);
        }

        stack.pushExpressionType(TypeDescriptor.getNumberType());
        stack.pushExpressionType(TypeDescriptor.getNumberType());
    }

    /**
     * Helper method: prepares a text value to be concatenated.
     */
    private void prepareTextValueConcatenation() {
        // TODO: Refactor for QuorumConverter.
        //get the operand and it's type
        TypeDescriptor operandType = stack.popExpressionType();

        if (operandType.isBoolean()) {//if the operand is a boolean
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "toString", "(Z)Ljava/lang/String;");
        } else if (operandType.isInteger()) {//if the operand is an integer
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;");
        } else if (operandType.isNumber()) {//if the operand is a number
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "toString", "(D)Ljava/lang/String;");
        }

        //set the new type to text and push the constant
        stack.pushExpressionType(TypeDescriptor.getTextType());
    }

    /**
     * helper method: swap values and prepare for concatenation.
     */
    private void prepareValueTextConcatenation() {
        //pop the text type off the stack and grab the first value in the concatination.
        TypeDescriptor textType = stack.popExpressionType();
        TypeDescriptor otherType = stack.peekExpressionType();

        //swap the values on the stack before preparing the non-text value
        swapOperandStackValues(textType, otherType);

        prepareTextValueConcatenation();

        //return to the orriginal state and push the text back on the stack
        swapOperandStackValues(textType, textType);
        stack.pushExpressionType(textType);
    }

    /**
     * Perform concatenation.
     */
    private void performTextConcatenation() {
        // Pop two text values off the stack.
        stack.popExpressionType();
        stack.popExpressionType();
        
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;");

        // Push a text constant onto the stack.
        stack.pushExpressionType(TypeDescriptor.getTextType());
    }

    /**
     * Performs an equality comparison.
     * 
     * testEqual    | comparison
     * -------------|------------
     * true         | =
     * false        | not=
     * 
     * @param operandType
     * @param testEqual 
     */
    private void performEqualityComparison(TypeDescriptor operandType, boolean testEqual) {
        //get two values off the stack
        stack.popExpressionType();
        stack.popExpressionType();

        if (operandType.isNumber()) {
            // Start two labels: One we will jump to if we're not equal, and the
            // other will jump past that case.

            Label jumpIfNotEqualLabel = new Label();
            Label jumpPastLabel = new Label();

            // Do the comparison.
            methodVisitor.visitInsn(Opcodes.DCMPL);

            // Are they equal?
            methodVisitor.visitJumpInsn(IFNE, jumpIfNotEqualLabel);

            // If they are, we will fall through to here. We will need to jump past
            // the next instructions below.
            if (testEqual) {
                methodVisitor.visitInsn(ICONST_1); // push a constant 'true'.
            } else {
                methodVisitor.visitInsn(ICONST_0); // push a constant 'false'.
            }
            methodVisitor.visitJumpInsn(GOTO, jumpPastLabel);
            // This is where our "jumpIfNotEqualLabel" label begins.
            methodVisitor.visitLabel(jumpIfNotEqualLabel);

            if (testEqual) {
                methodVisitor.visitInsn(ICONST_0); // push a constant 'false'.
            } else {
                methodVisitor.visitInsn(ICONST_1); // push a constant 'true'.
            }

            // This is where our "jumpPastLabel" label begins.
            methodVisitor.visitLabel(jumpPastLabel);

        } else if (operandType.isText()) {
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z");

            if (!testEqual) {//if testing not equal do an xor
                methodVisitor.visitInsn(ICONST_1);
                methodVisitor.visitInsn(IXOR);
            }
        } else {
            //if an integer or boolean is on the stack evaluate it.
            // Start two labels: One we will jump to if we're not equal, and the
            // other will jump past that case.
            Label jumpIfNotEqualLabel = new Label();
            Label jumpPastLabel = new Label();


            // Do the comparison. Are they equal?
            methodVisitor.visitJumpInsn(IF_ICMPNE, jumpIfNotEqualLabel);

            // If they are, we will fall through to here. We will need to jump past
            // the next instructions below.
            if (testEqual) {
                methodVisitor.visitInsn(ICONST_1); // push a constant 'true'.
            } else {
                methodVisitor.visitInsn(ICONST_0); // push a constant 'false'.
            }
            methodVisitor.visitJumpInsn(GOTO, jumpPastLabel);

            // This is where our "jumpIfNotEqualLabel" label begins.
            methodVisitor.visitLabel(jumpIfNotEqualLabel);

            if (testEqual) {
                methodVisitor.visitInsn(ICONST_0); // push a constant 'false'.
            } else {
                methodVisitor.visitInsn(ICONST_1); // push a constant 'true'.
            }
            // This is where our "jumpPastLabel" label begins.
            methodVisitor.visitLabel(jumpPastLabel);
        }

        //push a boolean result onto the stack
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
    }

    /**
     * Performs an inequality comparison. 
     * 
     * The function performs the comparison based on the parameters testEqual and lessThan.
     * 
     * testEqual    |   lessThan    | comparison
     * true         |   true        | <=
     * true         |   false       | <
     * false        |   true        | >=
     * false        |   false       | >
     * 
     * @param operandType - the final type on the stack. *MUST* be either INTEGER or NUMBER.
     * @param testEqual
     * @param lessThan 
     */
    private void performInequalityComparison(TypeDescriptor operandType, boolean testEqual, boolean lessThan) {
        // There will be two constants on the stack at this time. Pop them off.
        stack.popExpressionType();
        stack.popExpressionType();

        if (operandType.isNumber()) {
            // Start two labels: One we will jump to if we're not equal, and the
            // other will jump past that case.
            Label jumpIfFalseLabel = new Label();
            Label jumpPastLabel = new Label();
            
            // Do the comparison.
            methodVisitor.visitInsn(Opcodes.DCMPG);
            
            // And jump as appropriate.
            if (lessThan) {
                if (!testEqual) 
                    methodVisitor.visitJumpInsn(IFGE, jumpIfFalseLabel);
                else
                    methodVisitor.visitJumpInsn(IFGT, jumpIfFalseLabel);
            }
            else {
                if (!testEqual) 
                    methodVisitor.visitJumpInsn(IFLE, jumpIfFalseLabel);
                else
                    methodVisitor.visitJumpInsn(IFLT, jumpIfFalseLabel);
            }
            
            // If they are, we will fall through to here. We will need to jump past
            // the next instructions below.
            methodVisitor.visitInsn(ICONST_1); // push a constant 'true'.
            
            methodVisitor.visitJumpInsn(GOTO, jumpPastLabel);

            // This is where our "jumpIfNotEqualLabel" label begins.
            methodVisitor.visitLabel(jumpIfFalseLabel);
            methodVisitor.visitInsn(ICONST_0); // push a constant 'false'.
            
            // This is where our "jumpPastLabel" label begins.
            methodVisitor.visitLabel(jumpPastLabel); 

        } else {
            // Start two labels: One we will jump to if we're not equal, and the
            // other will jump past that case.
            Label jumpIfFalseLabel = new Label();
            Label jumpPastLabel = new Label();

            // Do the comparison.
            if (lessThan) {
                if (!testEqual) // if testing equality, use IF_ICMPGT.
                    methodVisitor.visitJumpInsn(IF_ICMPGE, jumpIfFalseLabel);
                else
                    methodVisitor.visitJumpInsn(IF_ICMPGT, jumpIfFalseLabel);
                }
            else {
                if (!testEqual) // if testing equality, use IF_ICMPGT.
                    methodVisitor.visitJumpInsn(IF_ICMPLE, jumpIfFalseLabel);
                else
                    methodVisitor.visitJumpInsn(IF_ICMPLT, jumpIfFalseLabel);
            }
            
            // If they are, we will fall through to here. We will need to jump past
            // the next instructions below.
            methodVisitor.visitInsn(ICONST_1); // push a constant 'true'.

            methodVisitor.visitJumpInsn(GOTO, jumpPastLabel);

            // This is where our "jumpIfNotEqualLabel" label begins.
            methodVisitor.visitLabel(jumpIfFalseLabel);

            methodVisitor.visitInsn(ICONST_0); // push a constant 'false'.

            // This is where our "jumpPastLabel" label begins.
            methodVisitor.visitLabel(jumpPastLabel);
        }

        // push a boolean onto the stack.
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
    }

    /**
     * Perform the appropriate steps for a binary arithmetic operation.
     * 
     * @param bytecodeOpcode
     * @param returnType 
     */
    private void performBinaryArithmeticOperation(int bytecodeOpcode, TypeDescriptor returnType) {
        // A binary addition requires two constants to be on the stack. Now,
        // we pop them off.
        stack.popExpressionType();
        stack.popExpressionType();

        // Insert the appropriate opcode.
        methodVisitor.visitInsn(bytecodeOpcode);

        // Push a constant of type returnType onto the stack.
        stack.pushExpressionType(returnType);
    }

    /**
     * Swap two types on the operand stack.
     * 
     * @param topType
     * @param secondType 
     */
    private void swapOperandStackValues(TypeDescriptor topType, TypeDescriptor secondType) {
        if (!topType.isNumber() && !secondType.isNumber()) {
            methodVisitor.visitInsn(SWAP);
        } else if (topType.isNumber() && secondType.isNumber()) {
            //stack.implicitStackIncrease(topType);
            methodVisitor.visitInsn(DUP2_X2);
            methodVisitor.visitInsn(POP2);
        } else if (topType.isNumber()) {
            //stack.implicitStackIncrease(topType);
            methodVisitor.visitInsn(DUP2_X1);
            methodVisitor.visitInsn(POP2);
        } else {
            //stack.implicitStackIncrease(topType);
            methodVisitor.visitInsn(DUP_X2);
            methodVisitor.visitInsn(POP);
        }
    }

    /**
     * Cast an arbitrary type to a text value. The type to cast from is popped
     * off the bytecode stack.
     */
    private void castValueToText() {
        TypeDescriptor type = stack.popExpressionType();

        methodVisitor.visitMethodInsn(INVOKESTATIC, QuorumConverter.convertTypeToJavaClassTypeEquivalent(type),
                "toString", "(" + QuorumConverter.convertTypeToBytecodeString(type) + ")Ljava/lang/String;");
        stack.pushExpressionType(TypeDescriptor.getTextType());
    }

    /**
     * Cast a text value to another type, for example, an integer.
     * 
     * If we wanted to cast the text value "3" to an integer, in the JVM, we must
     * call Integer.parseInt(value), which is exactly the code that this method
     * inserts.
     * 
     * @param returnValueType - the type to cast to.
     */
    private void castTextToValue(TypeDescriptor returnValueType) {
        TypeDescriptor valueType = stack.popExpressionType();

        String type = QuorumConverter.convertTypeToJavaTypeEquivalent(returnValueType);
        type = type.substring(0, 1).toUpperCase() + type.substring(1);


        methodVisitor.visitMethodInsn(INVOKESTATIC, QuorumConverter.convertTypeToJavaClassTypeEquivalent(returnValueType),
                "parse" + type, "(Ljava/lang/String;)" + QuorumConverter.convertTypeToBytecodeString(returnValueType));
        stack.pushExpressionType(valueType);
    }

    /**
     * Cast the current value on the stack to the specified type. The type to
     * cast from is popped off by the bytecode stack.
     * 
     * @param returnValueType the type to cast to.
     */
    private void castValueToValue(TypeDescriptor returnValueType) {
        TypeDescriptor valueType = stack.popExpressionType();

        String leftBytecodeString = QuorumConverter.convertTypeToBytecodeString(valueType);
        String rightBytecodeString = QuorumConverter.convertTypeToBytecodeString(returnValueType);

        if (leftBytecodeString.compareTo("Z") == 0) {
            leftBytecodeString = "I";
        }

        if (rightBytecodeString.compareTo("Z") == 0) {
            rightBytecodeString = "I";
        }

        if (leftBytecodeString.compareTo(rightBytecodeString) != 0) {

            String opcodeName = leftBytecodeString + "2" + rightBytecodeString;
            int opcode = 0;
            try {
                Field field = Opcodes.class.getField(opcodeName);
                opcode = field.getInt(field.getType());
            } catch (Exception ex) {
                Logger.getLogger(QuorumJavaBytecodeStepVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            methodVisitor.visitInsn(opcode);
        }

        stack.pushExpressionType(valueType);
    }

    /**
     * Returns the current opcode tracker from the system.
     * 
     * @return 
     */
    public OpcodeTracker getCurrentTracker() {
        OpcodeTracker tracker = null;

        if (currentMethodExecution == null) {//if no current method execution
            tracker = currentClassExecution.getTracker();
        } else {//otherwise look in the method execution
            tracker = currentMethodExecution.getTracker();
        }
        return tracker;
    }
    /**
     * Helper method: process the cached expressions whenever they need to be
     * processed. eg. while loops should be identified then the expressions are
     * inserted.
     */
    private void processExpressions() {
        OpcodeTracker tracker = null;
        LinearExecution execution = null;

        if (currentMethodExecution == null) {//if no current method execution
            tracker = currentClassExecution.getTracker();
            execution = currentClassExecution;
        } else {//otherwise look in the method execution
            tracker = currentMethodExecution.getTracker();
            execution = currentMethodExecution;
        }

        //for each item in the queue excluding the last 
        //item(which is the opcode processing the expressions)
        for (int j = 0; j < tracker.getQueueSize() - 1; j++) {
            //check queue for its current value
            int begin = tracker.removeFromQueue();
            int end = tracker.peekQueue();

            //loop through all op-codes
            Vector<ExecutionStep> steps = execution.getSteps();
            for (int i = begin; i < end; i++) { //visit the expressions
                //is this step the first parameter to a function call?
                //if yes, call visitCallSpecial, with the call step 
                //for that function call
                boolean funcParam = tracker.containsFunctionParameterMapping(i);
                if(funcParam) {
                    ArrayList<Integer> callLocations = tracker.getFunctionParameterMapping(i);
                    for(int k = 0; k < callLocations.size(); k++){
                        ExecutionStep callStep = steps.get(callLocations.get(k));
                        if(callStep instanceof CallStep) {
                            CallStep call = (CallStep) callStep;
                            //insert the pointer for the object being called upon
                            visitCallSpecial(call);
                        }
                        else {
                            Logger.getLogger(QuorumJavaBytecodeStepVisitor.class.getName()).log(
                                    Level.SEVERE, "Function mapping between opcode parameters "
                                    + "and callsteps results in incorrect values. This is a compiler bug.");
                        }
                    }
                }//if no, do nothing
                
                ExecutionStep step = steps.get(i);
                step.visit(this);
            }

            //clear out the queue at the end of visiting
            if(!tracker.getOpcodeType(end).equals(OpcodeType.ROOT_EXPRESSION)){
                tracker.clearQueue();
            }
        }
        
        
    }
    
    /**
     * Helper method used to process expression for loops with hidden variables,
     * this includes repeat times and repeat from.
     * 
     * @param loopType 
     */
    public void processSpecialLoopExpression(LabelTypeEnum loopType) {
        OpcodeTracker tracker = null;
        LinearExecution execution = null;

        if (currentMethodExecution == null) {//if no current method execution
            tracker = currentClassExecution.getTracker();
            execution = currentClassExecution;
        } else {//otherwise look in the method execution
            tracker = currentMethodExecution.getTracker();
            execution = currentMethodExecution;
        }

        //for each item in the queue excluding the last 
        //item(which is the opcode processing the expressions)
        for (int j = 0; j < tracker.getQueueSize() - 1; j++) {
            //check queue for its current value
            int begin = tracker.removeFromQueue();
            int end = tracker.peekQueue();

            //loop through all op-codes
            Vector<ExecutionStep> steps = execution.getSteps();
            for (int i = begin; i < end; i++) { //visit the expressions
                //is this step the first parameter to a function call?
                //if yes, call visitCallSpecial, with the call step 
                //for that function call
                boolean funcParam = tracker.containsFunctionParameterMapping(i);
                if (funcParam) {
                    ArrayList<Integer> callLocations = tracker.getFunctionParameterMapping(i);
                    for(int k = 0; k < callLocations.size(); k++){
                        ExecutionStep callStep = steps.get(callLocations.get(k));
                        if(callStep instanceof CallStep) {
                            CallStep call = (CallStep) callStep;
                            //insert the pointer for the object being called upon
                            visitCallSpecial(call);
                        }
                        else {
                            Logger.getLogger(QuorumJavaBytecodeStepVisitor.class.getName()).log(
                                    Level.SEVERE, "Function mapping between opcode parameters "
                                    + "and callsteps results in incorrect values. This is a compiler bug.");
                        }
                    }
                }//if no, do nothing

                if ((loopType.equals(LabelTypeEnum.FROM) || loopType.equals(LabelTypeEnum.TIMES)) && i > begin + 1) {
                    methodVisitor.visitLabel(stack.peekLabel().getLabel());
                    methodVisitor.visitVarInsn(ILOAD, stack.peekCounterVariable());
                    methodVisitor.visitVarInsn(ILOAD, stack.peekMaximumVariable());
                }

                ExecutionStep step = steps.get(i);
                step.visit(this);

                if (loopType.equals(LabelTypeEnum.FROM) && i == begin) {
                    methodVisitor.visitVarInsn(ISTORE, stack.pushMaximumVariable());
                    i = i + 1;
                } else if (loopType.equals(LabelTypeEnum.TIMES) && i == begin) {
                    methodVisitor.visitVarInsn(ISTORE, stack.pushMaximumVariable());
                } else if (loopType.equals(LabelTypeEnum.TIMES) && i == begin + 1) {
                    methodVisitor.visitVarInsn(ISTORE, stack.pushCounterVariable());
                }
            }

            //clear out the queue at the end of visiting
            if (!tracker.getOpcodeType(end).equals(OpcodeType.ROOT_EXPRESSION)) {
                tracker.clearQueue();
            }
        }
    }
    
    /**
     * Process the opcode tracker queue for field expressions only (must be
     * in the class execution).
     * 
     */
    private void processFieldExpressions() {
        OpcodeTracker tracker = null;
        LinearExecution execution = null;

        if (currentMethodExecution == null) {//if no current method execution
            tracker = currentClassExecution.getTracker();
            execution = currentClassExecution;
        } else {//otherwise look in the method execution
            tracker = currentMethodExecution.getTracker();
            execution = currentMethodExecution;
        }

        //for each item in the queue excluding the last 
        //item(which is the opcode processing the expressions)
        for (int j = 0; j < tracker.getQueueSize() - 1; j++) {
            //check queue for its current value
            int begin = tracker.removeFromQueue() - 1;
            int end = tracker.peekQueue() - 1;

            //loop through all op-codes
            Vector<ExecutionStep> steps = execution.getSteps();
            for (int i = begin; i < end; i++) { //visit the expressions
                
                ExecutionStep step = steps.get(i);
                step.visit(this);
            }

            //clear out the queue at the end of visiting
            tracker.clearQueue();
        }
    }

    /**
     * Visiting the class execution will handle bytecode generation necessary 
     * for generating class headers and visiting subcomponents of the class (by
     * visiting those items).
     * 
     * Some code contained in this method is temporarily used to make testing
     * of the bytecode generator easier.
     * 
     * 
     * @param clazz 
     */
    public void visit(ClassExecution clazz) {
        //classWriter = new ClassWriter(0);

        //if you need to cheat temporarily, this will compute the maxS
        //function automatically. This is useful for reverse engineering.
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        String staticKey = clazz.getClassDescriptor().getStaticKey();
        currentClass = clazz.getClassDescriptor();
        currentClassExecution = clazz;

        //garbage code to ease debugging, remove for reality.
//        if(!".Melissa".equals(staticKey) && !".Stefik".equals(staticKey) && !".Matt".equals(staticKey) && !".Main".equals(staticKey)) {
//            return;
//        }

//        if (!".Stefik".equals(staticKey) && !"Libraries.Sound.Speech".equals(staticKey) && 
//                !".Matt".equals(staticKey) && !".Main".equals(staticKey)
//                && !"Libraries.Language.Object".equals(staticKey)) {
//            return;
//        }
//
        if (!".Main".equals(staticKey) && !".Melissa".equals(staticKey) && !".Stefik".equals(staticKey) && !"Libraries.Language.Object".equals(staticKey)
                && !"Libraries.Language.Support.CompareResult".equals(staticKey)) {
            return;
        }
        
        String name = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);
        processedClazzName = name;


        //this will have to be modified for inheritance conversion
        classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null);
        //first weave in the parents of the class and initialize them          
        //Add parents as extra behind the scenes fields.        
        Iterator<ClassDescriptor> parents = currentClass.getFlattenedListOfParents();
        while (parents.hasNext()) {
            ClassDescriptor parent = parents.next();
            String parentKey = parent.getStaticKey();
            String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
            String converted = QuorumConverter.convertStaticKeyToBytecodePathTypeName(parentKey);
            fieldVisitor = classWriter.visitField(ACC_PUBLIC, parentName, converted, null, null);
            fieldVisitor.visitEnd();
            fieldSize += 2;
        }


        //Do field visiting for the class.
        Iterator<VariableDescriptor> classVariables = clazz.getClassDescriptor().getClassVariables();
        OpcodeTracker tracker = currentClassExecution.getTracker();
        int i = 1;
        while (classVariables.hasNext()) {
            VariableDescriptor next = classVariables.next();
            String varName = next.getName();
            TypeDescriptor varType = next.getType();
            String converted = QuorumConverter.convertTypeToBytecodeString(varType);
            int accessModifier;
            if (next.getAccessModifier().toString().compareTo(AccessModifierEnum.PUBLIC.toString()) == 0) {
                accessModifier = ACC_PUBLIC;
            } else {
                accessModifier = ACC_PRIVATE;
            }

            OpcodeType opcodeType = tracker.getOpcodeType(i);

            //if the opcode type is of root expression queue it up for later.
            //also queue up the next item (the end) this should always be the
            //next item because we are talking about class variables only.
            if (opcodeType == OpcodeType.ROOT_EXPRESSION) {
                tracker.addToQueue(i);
                i = tracker.getFinalPosition(i) + 1;
                tracker.addToQueue(i);
            }

            fieldVisitor = classWriter.visitField(accessModifier, varName, converted, null, null);
            fieldVisitor.visitEnd();
            

            i++;
        }

        //put in an extra plugin field if the class has system actions.
        int numSystem = currentClass.getNumberSystemActions();
        if (numSystem > 0) {
            String converted = QuorumConverter.convertStaticKeyToPluginPathTypeName(currentClass.getStaticKey());
            fieldVisitor = classWriter.visitField(ACC_PUBLIC, PLUGIN_NAME, converted, null, null);
            fieldVisitor.visitEnd();
        }

        //add a constructor that initializes its parents
        computeConstructor(true);
        //add a constructor that doesn't
//        computeConstructor(false);

        
        //now dump all of the parent methods that 
        //are not in the base class out as wrapper functions.
//        parents = currentClass.getFlattenedListOfParents();
//        while (parents.hasNext()) {
//            ClassDescriptor parent = parents.next();
//            computeParentMethods(parent);
//        }

        //now do all methods
        Iterator<MethodExecution> methods = clazz.getMethods();
        while (methods.hasNext()) {
            MethodExecution method = methods.next();
            visit(method);
        }

        Iterator<SystemActionDescriptor> systems = clazz.getClassDescriptor().getSystemActions();
        while (systems.hasNext()) {
            SystemActionDescriptor sys = systems.next();
            computeSystemAction(sys);
        }
        classWriter.visitEnd();
    }

    /**
     * Visit the method execution, i.e. each step that needs to be visited for 
     * each methods execution.
     * 
     * @param method 
     */
    public void visit(MethodExecution method) {
        currentMethodExecution = method;
        boolean main = method.isMainMethod();
        //add the bytecode for the main method.
        if (main) {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            methodVisitor.visitTypeInsn(NEW, processedClazzName);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, processedClazzName, "<init>", "()V");
            methodVisitor.visitVarInsn(ASTORE, 1);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, processedClazzName,
                    method.getMethodDescriptor().getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(method.getMethodDescriptor()));
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }

        //still have to handle parameters here.
        String name = method.getMethodDescriptor().getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(method.getMethodDescriptor());
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, name, params, null, null);
        stack.startMethod(1);
        addParametersAsVariables(method.getMethodDescriptor());
        methodVisitor.visitCode();

        Vector<ExecutionStep> steps = method.getSteps();
        OpcodeTracker tracker = currentMethodExecution.getTracker();
        for (int i = 0; i < steps.size(); i++) {//visit each of the steps in the method execution
            OpcodeType opcodeType = tracker.getOpcodeType(i);

            //if the opcode type is of root expression queue it up for later.
            if (opcodeType == OpcodeType.ROOT_EXPRESSION) {
                tracker.addToQueue(i);
                int finalPosition = tracker.getFinalPosition(i);

                //calculate the final position and jump to it.
                if (finalPosition >= 0) {
                    i = finalPosition;
                }
            } else if (opcodeType == OpcodeType.METHOD_CALL) {//if we have a method call in an expression(never a solo method call).
                int lookahead = 0;
                
                do {//find the final position indicated by any opcode type other than null or another method call.
                    lookahead++;
                    opcodeType = tracker.getOpcodeType(i + lookahead);
                } while (opcodeType == null || opcodeType == OpcodeType.METHOD_CALL);
                
                i = (lookahead) + i;
                
                //mark the end of the visits
                tracker.addToQueue(i);
                
                //visit the final step which should process all the queued up steps.
                ExecutionStep step = steps.get(i);
                step.visit(this);
            } else {

                //queue up the ending opcodes but still visit them (assignment, print, etc.)
                if (opcodeType != null) {
                    tracker.addToQueue(i);
                }

                //otherwise process each step and let each step decide if it has
                //queued up steps to visit and in which order they are processed.
                ExecutionStep step = steps.get(i);
                
                if (step instanceof EndScopeStep) {
                    // Is it a loop? If so, skip the incrementation op codes.
                    if (stack.peekLabel() != null && (stack.peekLabel().getLabelType().equals(LabelTypeEnum.FROM) || stack.peekLabel().getLabelType().equals(LabelTypeEnum.TIMES))) {          
                        methodVisitor.visitInsn(ICONST_1);
                        methodVisitor.visitVarInsn(ILOAD, stack.peekCounterVariable());
                        methodVisitor.visitInsn(IADD);
                        methodVisitor.visitVarInsn(ISTORE, stack.popCounterVariable());
                        i += 4;
                        step = steps.get(i);
                    }
                }
                step.visit(this);
            }
        }
        
        //the stack size should also change depending on the 
        //expressions that need to be processed.
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();

    }

    @Override
    public void visit(AlertStep step) {
        int a = 5;
    }

    @Override
    public void visit(AlwaysEndStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignIntegerObjectToNumberAutoBoxLocalStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignIntegerObjectToNumberAutoBoxStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignObjectAutoBoxLocalStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignObjectAutoBoxStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentCustomLocalStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentNumberIntegerLocalStep step) {
        //process the expressions
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        //prepare the integer as a number.
        prepareNumberIntegerOperation();
        TypeDescriptor pop = stack.popExpressionType();
        //Assigns an integer to a local variable or field of type number.
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentNumberIntegerStep step) {
        //process the expressions
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        //prepare the integer as a number.
        prepareNumberIntegerOperation();
        TypeDescriptor pop = stack.popExpressionType();
        //Assigns an integer to a field of type number.
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentBooleanLocalStep step) {
        //Assigns a boolean to a local variable or field of type boolean

        //1. Loop through all expressions and dequeue them
        //processExpressions(getTopOfQueue())
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        //2. Now that everything is out of the queue,
        //do the actual assignment
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentBooleanStep step) {
        //Assigns a boolean to a field of type boolean
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentIntegerLocalStep step) {
        //process the expressions in the queue
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        //Assigns an integer to a local variable or field of type integer
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentIntegerStep step) {
        //Assigns an integer to a field of type integer
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentNumberLocalStep step) {
        //Assigns a number to a local variable or field of type number.
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentNumberStep step) {
        //Assigns a number to a field of type number.
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentTextLocalStep step) {
        //Assigns a number to a local variable or field of type text.
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentTextStep step) {
        //remove the constant from the bytecode stack and assign a number to 
        //a field of type text.
        if(!step.getVariable().isFieldVariable())
            processExpressions();
        
        TypeDescriptor pop = stack.popExpressionType();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AutoBoxCreateStep step) {
        int a = 5;
    }

    @Override
    public void visit(BeginCheckScopeStep step) {
        int a = 5;
    }

    @Override
    public void visit(BeginScopeStep step) {
    }

    @Override
    public void visit(BinaryAddBooleanTextStep step) {
        //Converts a boolean to text.
        prepareValueTextConcatenation();
        //Concatenates the converted boolean and other text.
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(false);
        //Adds the two numbers together.
        performBinaryArithmeticOperation(DADD, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryAddIntegerTextStep step) {
        //Converts an integer to text.
        prepareValueTextConcatenation();
        //Concatenates the converted integer and text.
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //Adds the two numbers together.
        performBinaryArithmeticOperation(DADD, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryAddNumberStep step) {
        //Adds the two numbers together.
        performBinaryArithmeticOperation(DADD, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryAddNumberTextStep step) {
        //Converts a number to text.
        prepareValueTextConcatenation();
        //concatenates the converted number and text.
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddStep step) {
        //Binary addition of two integers.
        performBinaryArithmeticOperation(IADD, TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(BinaryAddTextBooleanStep step) {
        //Converts a boolean to text.
        prepareTextValueConcatenation();
        //concatenates a text value and the converted boolean text.
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddTextIntegerStep step) {
        //Converts an integer to text, then concatenates a text value and the converted
        //integer text.
        prepareTextValueConcatenation();
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddTextNumberStep step) {
        //Converts a number to text, then concatenates a text value and the converted
        //number text.
        prepareTextValueConcatenation();
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAndStep step) {
        //Perform a binary and operation on two booleans.
        stack.popExpressionType(); // we only need to pop one off, since both are booleans.
        methodVisitor.visitInsn(IAND);
    }

    @Override
    public void visit(BinaryConcatenateStep step) {
        //Concatenates two text values
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryDivideIntegerNumberStep step) {
        //Converts an integer to a number, then divides the integer by the number.
        prepareIntegerNumberOperation(true);
        performBinaryArithmeticOperation(DDIV, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryDivideNumberIntegerStep step) {
        //Converts an integer to a number, then divides the number by the integer.
        prepareNumberIntegerOperation();
        performBinaryArithmeticOperation(DDIV, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryDivideNumberStep step) {
        //Divides two numbers.
        performBinaryArithmeticOperation(DDIV, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryDivideStep step) {
        //Divides two integers.
        performBinaryArithmeticOperation(IDIV, TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(BinaryEqualsBooleanStep step) {
        //Determines if two boolean values are equal.
        stack.popExpressionType(); // only need to pop off one boolean - both the same.
        methodVisitor.visitInsn(IXOR);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(IXOR);
    }

    @Override
    public void visit(BinaryEqualsCustomCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsCustomNullStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(false);
        //determines if the converted number is equal to another number
        performEqualityComparison(TypeDescriptor.getNumberType(), true);
    }

    @Override
    public void visit(BinaryEqualsNullCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //then determines if a number is equal to the converted number
        performEqualityComparison(TypeDescriptor.getNumberType(), true);
    }

    @Override
    public void visit(BinaryEqualsNumberStep step) {
        //Determines if two numbers are equal.
        performEqualityComparison(TypeDescriptor.getNumberType(), true);
    }

    @Override
    public void visit(BinaryEqualsStep step) {
        //Determines if two integers are equal.
        performEqualityComparison(TypeDescriptor.getIntegerType(), true);
    }

    @Override
    public void visit(BinaryEqualsStringStep step) {
        //Determines if two text values are equal.
        performEqualityComparison(TypeDescriptor.getTextType(), true);
    }

    @Override
    public void visit(BinaryGreaterEqualsIntegerNumberStep step) {
        //Converts number to an integer
        prepareIntegerNumberOperation(true);
        //determines if the converted number is greater than or equal to another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //determines if another number is greater than or equal to the converted number.
        performInequalityComparison(TypeDescriptor.getNumberType(), true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsNumberStep step) {
        //Determines if one number is greater than or equal to another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsStep step) {
        //Determines if one integer is greater than or equal to another integer.
        performInequalityComparison(TypeDescriptor.getIntegerType(), true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsStringStep step) {
        //Determines if one text value is greater than or equal to another text value.
        performInequalityComparison(TypeDescriptor.getTextType(), true, false);
    }

    @Override
    public void visit(BinaryGreaterThanIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(true);
        //then determines if the converted number is greater than another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), false, false);
    }

    @Override
    public void visit(BinaryGreaterThanNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //determines if another number is greater than the converted number
        performInequalityComparison(TypeDescriptor.getNumberType(), false, false);
    }

    @Override
    public void visit(BinaryGreaterThanNumberStep step) {
        //Determines if one number is greater than another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), false, false);
    }

    @Override
    public void visit(BinaryGreaterThanStep step) {
        //Determines if one integer is greater than another integer.
        performInequalityComparison(TypeDescriptor.getIntegerType(), false, false);
    }

    @Override
    public void visit(BinaryGreaterThanStringStep step) {
        //Determines if one text value is greater than another text value
        performInequalityComparison(TypeDescriptor.getTextType(), false, false);
    }

    @Override
    public void visit(BinaryIsACustomCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessEqualsIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(true);
        //then determines if the converted number is less than or equal to another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), true, true);
    }

    @Override
    public void visit(BinaryLessEqualsNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //determines if another number is less than or equal to the converted number.
        performInequalityComparison(TypeDescriptor.getNumberType(), true, true);
    }

    @Override
    public void visit(BinaryLessEqualsNumberStep step) {
        //Determines if one number is less than or equal to another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), true, true);
    }

    @Override
    public void visit(BinaryLessEqualsStep step) {
        //Determines if one integer is less than or equal to another integer.
        performInequalityComparison(TypeDescriptor.getIntegerType(), true, true);
    }

    @Override
    public void visit(BinaryLessEqualsStringStep step) {
        //Determines if one text value is less than or equal to another text value.
        performInequalityComparison(TypeDescriptor.getTextType(), true, true);
    }

    @Override
    public void visit(BinaryLessThanIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(true);
        //then determines if the converted number is less than another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), false, true);
    }

    @Override
    public void visit(BinaryLessThanNumberIntegerStep step) {
        //Converts an integer to a number
        prepareNumberIntegerOperation();
        //determines if another number is less than the converted number.
        performInequalityComparison(TypeDescriptor.getNumberType(), false, true);
    }

    @Override
    public void visit(BinaryLessThanNumberStep step) {
        //Determines if one number is less than another number.
        performInequalityComparison(TypeDescriptor.getNumberType(), false, true);
    }

    @Override
    public void visit(BinaryLessThanStep step) {
        //Determines if one integer is less than another integer.
        performInequalityComparison(TypeDescriptor.getIntegerType(), false, true);
    }

    @Override
    public void visit(BinaryLessThanStringStep step) {
        //Determines if one text value is less than another text value.
        performInequalityComparison(TypeDescriptor.getTextType(), false, true);
    }

    @Override
    public void visit(BinaryModIntegerNumberStep step) {
        //Converts an integer to a number. 
        prepareIntegerNumberOperation(true);
        //mods the integer by the number.
        performBinaryArithmeticOperation(DREM, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryModNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //mods the number by the integer.
        performBinaryArithmeticOperation(DREM, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryModNumberStep step) {
        //Mods one number by another number.
        performBinaryArithmeticOperation(DREM, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryModStep step) {
        //Mods one integer by another integer
        performBinaryArithmeticOperation(IREM, TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(BinaryMultiplyIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(false);
        //multiplies the integer by the number.
        performBinaryArithmeticOperation(DMUL, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryMultiplyNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //mods the number by the integer.
        performBinaryArithmeticOperation(DMUL, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryMultiplyNumberStep step) {
        //Multiplies one number by another number.
        performBinaryArithmeticOperation(DMUL, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinaryMultiplyStep step) {
        //Multiplies one integer by another integer.
        performBinaryArithmeticOperation(IMUL, TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(BinaryNotEqualsBooleanStep step) {
        //Determines if two boolean values are not equal.
        stack.popExpressionType(); // only pop off one - both equal
        methodVisitor.visitInsn(IXOR);
    }

    @Override
    public void visit(BinaryNotEqualsCustomCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsCustomNullStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsIntegerNumberStep step) {
        //Converts an integer to a number 
        prepareIntegerNumberOperation(false);
        //determines if the converted number is not equal to another number
        performEqualityComparison(TypeDescriptor.getNumberType(), false);
    }

    @Override
    public void visit(BinaryNotEqualsNullCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsNumberIntegerStep step) {
        prepareNumberIntegerOperation();
        performEqualityComparison(TypeDescriptor.getNumberType(), false);
    }

    @Override
    public void visit(BinaryNotEqualsNumberStep step) {
        performEqualityComparison(TypeDescriptor.getNumberType(), false);
    }

    @Override
    public void visit(BinaryNotEqualsStep step) {
        performEqualityComparison(TypeDescriptor.getIntegerType(), false);
    }

    @Override
    public void visit(BinaryNotEqualsStringStep step) {
        performEqualityComparison(TypeDescriptor.getTextType(), false);
    }

    @Override
    public void visit(BinaryOrStep step) {
        stack.popExpressionType(); // only need to pop one - both are equal
        methodVisitor.visitInsn(IOR);
    }

    @Override
    public void visit(BinarySubtractIntegerNumberStep step) {
        prepareIntegerNumberOperation(true);
        performBinaryArithmeticOperation(DSUB, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinarySubtractNumberIntegerStep step) {
        prepareNumberIntegerOperation();
        performBinaryArithmeticOperation(DSUB, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinarySubtractNumberStep step) {
        performBinaryArithmeticOperation(DSUB, TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(BinarySubtractStep step) {
        performBinaryArithmeticOperation(ISUB, TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(BooleanAutoBoxStep step) {
        int a = 5;
    }

    /**
     * This method pushes on a reference to the object on which it
     * is going to call a function. This might either be this, ALOAD 0, 
     * or a different object.
     * 
     * @author: Andy Stefik
     * @param step 
     */
    public void visitCallSpecial(CallStep step) {
        MethodDescriptor callee = step.getMethodCallee();
        if (!step.IsObjectCall()) { //it's a this call, so load it
            TypeDescriptor secondType = TypeDescriptor.getVoidType();
            if(!stack.isExpressionTypeEmpty()){
                secondType = stack.peekExpressionType();
            }
            methodVisitor.visitVarInsn(ALOAD, THIS);
        }
        else { //handle the case where we are pushing the reference on 
               //to the stack from 
            VariableParameterCommonDescriptor var = step.getParentObject();
            boolean field = var.isFieldVariable();
            if(field) {
                String key = currentClass.getStaticKey();
                String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
                String classNameSupplement = QuorumConverter.convertStaticKeyToBytecodePathTypeName(key);
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, className, var.getName(), classNameSupplement);
            }
            else { //determine the local variable number and load it
                if(var instanceof ParameterDescriptor){
                    //we are now calling a method on a variable (aka object). If it 
                    //is a parameter that we are calling on then load that parameter.
                    ParameterDescriptor varDescriptor = (ParameterDescriptor) var;
                    int number = stack.getParameterNumber(varDescriptor.getName());
                    methodVisitor.visitVarInsn(ALOAD, number);
                }else{
                    //Otherwise, load the variable from the mapped variable on the
                    //stack.
                    int number = var.getVariableNumber();
                    int mapped = stack.getMappedVariableNumber(number);
                    methodVisitor.visitVarInsn(ALOAD, mapped);
                }
            }
        }
    }
    
    @Override
    public void visit(CallStep step) {
        /**
         * I haven't tested this, as we aren't quite there yet in the build,
         * but it might work or be easily modified to work correctly.
         * Remove this comment and the old dead code once finished.
         */
        MethodDescriptor callee = step.getMethodCallee();
        String converted = "";
        if (!step.IsObjectCall()) {
            converted = processedClazzName;
            
            // It's a call on the 'this' object.
            if (callee.getParameters().isEmpty() && !step.isSoloMethodCall()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
            }
            else if (step.isSoloMethodCall()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                processExpressions();
            }
        }
        else {
            VariableParameterCommonDescriptor var = step.getParentObject();
            boolean field = var.isFieldVariable();
            if(field) {
                String key = currentClass.getStaticKey();
                String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
                String classNameSupplement = QuorumConverter.convertStaticKeyToBytecodePathTypeName(key);
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, className, var.getName(), classNameSupplement);
            }
            else { //determine the local variable number and load it
                
                //we are now calling a method on a variable (aka object). If it 
                //is a parameter that we are calling on then load that parameter.
                if(var instanceof ParameterDescriptor){
                    ParameterDescriptor varDescriptor = (ParameterDescriptor) var;
                    int number = stack.getParameterNumber(varDescriptor.getName());
                    methodVisitor.visitVarInsn(ALOAD, number);
                }else{
                    //Otherwise, load the variable from the mapped variable on the
                    //stack.
                    int number = var.getVariableNumber();
                    int mapped = stack.getMappedVariableNumber(number);
                    methodVisitor.visitVarInsn(ALOAD, mapped);
                }
            }
        }
        
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, converted,
                    callee.getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        
        // Is the method return type void? If not, push its return type onto the stack.
        if (!step.getMethodCallee().getReturnType().isVoid()) {
            stack.pushExpressionType(step.getMethodCallee().getReturnType());
        }
        
       /* MethodDescriptor callee = step.getMethodCallee();
        //NOTE: step.isThisCall is not what you want here, that's different
        if (!step.IsObjectCall()) { //it's a this call, so load it
            methodVisitor.visitVarInsn(ALOAD, THIS);
            //push the this pointer on and top it off
            stack.implicitStackIncrease(currentClass.getType());
            Parameters parameters = callee.getParameters();
            for (int i = 0; i < parameters.size(); i++) {
                int location = parameters.size() - 1 - i;
                BytecodeStackValue value = stack.getConstantFromTop(location);
                addToMethodVisit(value);
            }
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, processedClazzName,
                    callee.getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        } else {
            VariableParameterCommonDescriptor var = step.getParentObject();
            String staticKey = var.getStaticKey();
            //int variableNumber = var.getVariableNumber();
            int variableNumber = 1;

            methodVisitor.visitVarInsn(ALOAD, stack.getMappedVariableNumber(variableNumber));

            //push the this pointer on and pop it off
            String converted = QuorumConverter.convertStaticKeyToBytecodePath(var.getType().getStaticKey());

            //this is technically the wrong class, but the size is the same, regardless of class type
            stack.implicitStackIncrease(currentClass.getType());
            Parameters parameters = callee.getParameters();
            for (int i = 0; i < parameters.size(); i++) {
                int location = parameters.size() - 1 - i;
                BytecodeStackValue value = stack.getConstantFromTop(location);
                addToMethodVisit(value);
            }
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, converted,
                    callee.getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        }
        if (!callee.getReturnType().isVoid()) {
            BytecodeStackValue returnVal = new BytecodeStackValue();
            returnVal.setAsConstant();
            returnVal.setAsReturnValue(true);
            TypeDescriptor returnType = new TypeDescriptor();
            returnType.setName(callee.getReturnType().getName());
            returnVal.setType(returnType);
            stack.pushConstant(returnVal);
        }*/
    }

    @Override
    public void visit(ConditionalJumpCheckStep step) {
        int a = 5;
    }

    @Override
    public void visit(ConditionalJumpIfStep step) {
        //process the expressions
        processExpressions();

        //generate a new label for the if and visit the jump instruction
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);

        //generate the if label add the label to the bytecode stack
        LabelStackValue label = new LabelStackValue(LabelTypeEnum.IF, IFEQ, label0);
        stack.pushLabel(label);

    }

    @Override
    public void visit(ConditionalJumpLoopStep step) {
        LabelTypeEnum loopType = LabelTypeEnum.LOOP;
        //if this is a from step we need to process the extra root expression.
        if(step.getLoopType().equals(LoopType.FROM)){
            loopType = LabelTypeEnum.FROM;
            //process the first move step of three in a from loop.
            //only repeat from has this extra move step (cuased by the hidden variable).
            processExpressions();
            methodVisitor.visitVarInsn(ISTORE, stack.pushCounterVariable());
        }else if(step.getLoopType().equals(LoopType.TIMES)){
            loopType = LabelTypeEnum.TIMES;  
        }
        
        //build the top label
        Label label0 = new Label();
        if (step.getLoopType().equals(LoopType.UNTIL) || step.getLoopType().equals(LoopType.WHILE))
            methodVisitor.visitLabel(label0);

        //if we are dealing with an until loop we will need to negate our result using IFNE.
        int currentLoopBytecode = 0;
        if(step.getLoopType().equals(LoopType.UNTIL)){
           currentLoopBytecode = IFNE;
        }else{
           currentLoopBytecode = IFEQ;  
        }
        
        //push the label before the loop expression is processed.
        LabelStackValue temp = new LabelStackValue(loopType, currentLoopBytecode, label0);
        stack.pushLabel(temp);
        
        //process all the queued steps
        processSpecialLoopExpression(loopType);
        
        //generate the lables and visit as necessary
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(currentLoopBytecode , label1);

        //push the second label onto the stack so we can visit it later
        LabelStackValue temp2 = new LabelStackValue(loopType, currentLoopBytecode, label1);
        stack.pushLabel(temp2);

    }

    @Override
    public void visit(CreateObjectStep step) {
        ClassDescriptor clazz = step.getClazz();
        this.currentMethodExecution.getTracker().clearQueue();
        //methodVisitor.visitVarInsn(ALOAD, THIS);
        String converted = QuorumConverter.convertStaticKeyToBytecodePath(clazz.getStaticKey());
        methodVisitor.visitTypeInsn(NEW, converted);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "()V");
        VariableParameterCommonDescriptor variable = step.getVariable();

        int variableNumber = variable.getVariableNumber();
//        if (variableNumber == -1) {
//            return; //it's a field and we don't handle these yet.
//        }
//        BytecodeStackValue value = new BytecodeStackValue();
        TypeDescriptor type = new TypeDescriptor();
        type.setName(variable.getType().getName());
//        value.setAsVariable(variableNumber);
//        value.setType(type);
//        value.setName(variable.getName());
        //methodVisitor.visitVarInsn(ASTORE, mappedVariableNumber);
//        if (value.isConstant()) {
//            value.setAsVariable(variableNumber);
//            methodVisitor.visitVarInsn(value.getStoreOpCode(), mappedVariableNumber);
//            stack.setVariable(variableNumber, value);
//        }
//        else {
//            addToMethodVisit(value);
//            value.setAsConstant();
//            methodVisitor.visitVarInsn(value.getStoreOpCode(), mappedVariableNumber);
//            stack.setVariable(variableNumber, value);
//        }

        stack.setVariable(variableNumber, type);
        int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber);
        if (mappedVariableNumber != -1) {
            methodVisitor.visitVarInsn(ASTORE, mappedVariableNumber);
        }
        //stack.setVariable(variableNumber, value);
        int b = 5;
    }

    @Override
    public void visit(DataStackPopStep step) {
        int a = 5;
    }

    @Override
    public void visit(EndScopeStep step) {
        if (!stack.isEmptyLabel()) {//if the stack has a label
            LabelStackValue label = stack.peekLabel();
            if (label.getLabelType().equals(LabelTypeEnum.IF)) {//if the label is for an if statement
                if (label.getJumpType() != GOTO && !step.isLastIfScope()) {//if the label is NOT a GOTO jump

                    stack.popLabel();
                    Label top = label.getLabel();

                    //check to see if an end label has already been made for the if,
                    //if it has get that label and if it has not create the label.
                    Label label1;
                    if (!stack.isEmptyLabel() && stack.peekLabel().getJumpType() == GOTO) {
                        label1 = stack.peekLabel().getLabel();
                    } else {
                        label1 = new Label();
                    }

                    methodVisitor.visitJumpInsn(GOTO, label1);
                    stack.pushLabel(new LabelStackValue(LabelTypeEnum.IF, GOTO, label1));
                    methodVisitor.visitLabel(top);
                } else if (step.isLastIfScope()) {//jumping out of the if
                    Label first = label.getLabel();
                    methodVisitor.visitLabel(first);
                    stack.popLabel();

                    while (!stack.isEmptyLabel()) {
                        first = stack.peekLabel().getLabel();
                        methodVisitor.visitLabel(first);
                        stack.popLabel();
                    }
                } else if (label.getJumpType() == GOTO) {
                    if (step.getBlockTag().equals("elseif")) {
                        stack.undoLabel();
                    }

                    stack.popLabel();
                    Label pop = label.getLabel();
                    methodVisitor.visitLabel(pop);
                }
            } else if (label.getLabelType().equals(LabelTypeEnum.LOOP)) {//end of the loop will mark the goto and visit the end label.

                stack.popLabel();

                //there are two labels that have been stored from the begging scope, get them.
                Label label1 = label.getLabel();
                Label label0 = stack.peekLabel().getLabel();

                //create a goto to the beginning of the while loop (label 0)
                methodVisitor.visitJumpInsn(GOTO, label0);
                //visit the end of the loop label.
                methodVisitor.visitLabel(label1);
            }
        }
        
    }

    @Override
    public void visit(InputStep step) {
        int a = 5;
    }

    @Override
    public void visit(IntegerAutoBoxStep step) {
        int a = 5;
    }

    @Override
    public void visit(JumpStep step) {
        //if the stack has a label
        if (!stack.isEmptyLabel()) {
            LabelStackValue label = stack.peekLabel();
            //if the label is for a repeat times or repeat from process all the labels here
            if (label.getLabelType().equals(LabelTypeEnum.FROM) || label.getLabelType().equals(LabelTypeEnum.TIMES)) {
                stack.popLabel();

                //there are two labels that have been stored from the begging scope, get them.
                Label label1 = label.getLabel();
                Label label0 = stack.popLabel().getLabel();
                
                //create a goto to the beginning of the while loop (label 0)
                //methodVisitor.visitIincInsn(stack.popCounterVariable(), 1);
                methodVisitor.visitJumpInsn(GOTO, label0);
                //visit the end of the loop label.
                methodVisitor.visitLabel(label1);
            }else if(label.getLabelType().equals(LabelTypeEnum.LOOP)){//if the label is for the general loop just pop off the remaining label.
                stack.popLabel();
            }
        }
    }

    @Override
    public void visit(MeVariableMoveStep step) {
        int a = 5;
    }

    @Override
    public void visit(MoveRegistersStep step) {
    }

    @Override
    public void visit(MoveStep step) {
        // Push the appropriate value onto the stack.
        stack.pushExpressionType(step.getValue().getType());
        
        pushConstant(step.getValue().getResult());
    }

    @Override
    public void visit(NullIntermediateStep step) {
        int a = 5;
    }

    @Override
    public void visit(NullExecutionStep step) {
        int a = 5;
    }

    @Override
    public void visit(NumberAutoBoxStep step) {
        int a = 5;
    }

    @Override
    public void visit(ObjectCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(ObjectInitParentStep step) {
        int a = 5;
    }

    @Override
    public void visit(ObjectInitPopStep step) {
        int a = 5;
    }

    @Override
    public void visit(ParentVariableMoveStep step) {
        int a = 5;
    }

    @Override
    public void visit(PrintStep step) {
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        // Insert the appropriate steps for this statement.
        processExpressions();
        
        TypeDescriptor typeToPrint = stack.popExpressionType();

        // Insert the appropriate print opcode for the type.
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(" + QuorumConverter.convertTypeToBytecodeString(typeToPrint) + ")V");
    }

    @Override
    public void visit(PushStep step) {
        int a = 5;
    }

    @Override
    public void visit(ReturnStep step) {
        // partial - handles only primitive types and text
        TypeDescriptor returnType = step.getMethodDescriptor().getReturnType();

        if (returnType.isVoid()) {
            methodVisitor.visitInsn(RETURN);
        } else {
            processExpressions();
            stack.popExpressionType();
            if (returnType.isBoolean() || returnType.isInteger()) {
                methodVisitor.visitInsn(IRETURN);
            } else if (returnType.isNumber()) {
                methodVisitor.visitInsn(DRETURN);
            } else {
                methodVisitor.visitInsn(ARETURN);
            }
        }
    }

    @Override
    public void visit(SimpleAlertStep step) {
        int a = 5;
    }

    @Override
    public void visit(SpeakStep step) {
        int a = 5;
    }

    @Override
    public void visit(TextAutoBoxStep step) {
        int a = 5;
    }

    @Override
    public void visit(ThisMoveStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryBooleanBooleanCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryBooleanIntegerCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.BOOLEAN);
        castValueToValue(valueType);
    }

    @Override
    public void visit(UnaryBooleanNumberCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.BOOLEAN);
        castValueToValue(valueType);
    }

    @Override
    public void visit(UnaryBooleanTextCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.BOOLEAN);
        castTextToValue(valueType);
    }

    @Override
    public void visit(UnaryIntegerBooleanCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.INTEGER);
        castValueToValue(valueType);
    }

    @Override
    public void visit(UnaryIntegerIntegerCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryIntegerNumberCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.INTEGER);
        castValueToValue(valueType);
    }

    @Override
    public void visit(UnaryIntegerTextCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.INTEGER);
        castTextToValue(valueType);
    }

    @Override
    public void visit(UnaryNotStep step) {
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(IXOR);
        
        // NOTE: No need to pop and push a boolean type here--a boolean type
        // is still on the stack.
    }

    @Override
    public void visit(UnaryNumberBooleanCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.NUMBER);
        castValueToValue(valueType);
    }

    @Override
    public void visit(UnaryNumberIntegerCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.NUMBER);
        castValueToValue(valueType);
    }

    @Override
    public void visit(UnaryNumberNumberCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryNumberTextCastStep step) {
        TypeDescriptor valueType = new TypeDescriptor();
        valueType.setName(TypeDescriptor.NUMBER);
        castTextToValue(valueType);
    }

    @Override
    public void visit(UnaryTextBooleanCastStep step) {
        castValueToText();
    }

    @Override
    public void visit(UnaryTextIntegerCastStep step) {
        castValueToText();
    }

    @Override
    public void visit(UnaryTextNumberCastStep step) {
        castValueToText();
    }

    @Override
    public void visit(UnaryTextTextCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(VariableInObjectMoveStep step) {
        //get the variable from the step and store it
        VariableParameterCommonDescriptor variable = step.getObj();
        pushVariable(variable.getType(), variable.getVariableNumber());

        //visit the field instruction
        String name = step.getVariableName();
        TypeDescriptor type = step.getVariableType();
        methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(variable.getType().getName()),
                name, QuorumConverter.convertTypeToBytecodeString(type));

        //push the type of the variable on the top of the stack
        stack.pushExpressionType(type);

    }

    @Override
    public void visit(VariableMoveStep step) {
        TypeDescriptor variable = null;

        //if we have a variable descriptor
        if (step.getValue() instanceof VariableDescriptor) {
            //if the variable is initialized load the variable and visit the field
            VariableDescriptor varDescriptor = (VariableDescriptor) step.getValue();
            if (varDescriptor != null && varDescriptor.isInitializedClassVariable()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                        varDescriptor.getName(), QuorumConverter.convertTypeToBytecodeString(varDescriptor.getType()));
                variable = varDescriptor.getType();
            } else {//otherwise get the variable number and push the new variable onto the bytecode stack
                int variableNumber = step.getValue().getVariableNumber();
                variable = stack.getVariable(variableNumber);
                pushVariable(step.getValue().getType(), variableNumber);
            }
        }else if(step.getValue() instanceof ParameterDescriptor){
                ParameterDescriptor varDescriptor = (ParameterDescriptor) step.getValue();
                int variableNumber = stack.getParameterNumber(varDescriptor.getName());
                variable = varDescriptor.getType();
                methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(step.getValue().getType()), variableNumber);
                //methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                //varDescriptor.getName(), QuorumConverter.convertTypeToBytecodeString(varDescriptor.getType()));            
        }
        
        //push the type of the variable on the top of the stack
        stack.pushExpressionType(variable);
    }
}
