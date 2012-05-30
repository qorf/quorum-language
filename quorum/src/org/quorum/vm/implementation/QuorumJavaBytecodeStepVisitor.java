/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.NullExecutionStep;
import org.quorum.plugins.RuntimeError;
import org.quorum.steps.*;
import org.quorum.symbols.AccessModifierEnum;
import org.quorum.symbols.BlueprintDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.GenericDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.Parameters;
import org.quorum.symbols.Result;
import org.quorum.symbols.Scopable;
import org.quorum.symbols.SystemActionDescriptor;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 * This class takes a set of opcodes
 * @author Andreas Stefik,Melissa Stefik, and Matt Lawson
 */
public class QuorumJavaBytecodeStepVisitor implements ExecutionStepVisitor, Opcodes {

    private ClassWriter interfaceWriter;
    private MethodVisitor interfaceMethodVisitor;
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
        
        // If this is a normal class, we can just initialize by calling java/Lang/Object.<init>().
        // If it is an exception class, we must initialize by calling java/lang/Throwable.<init>().
        if (currentClass.getStaticKey().equals("Libraries.Language.Errors.Error") || currentClass.getParent("Libraries.Language.Errors.Error") != null) {
            // invoke <init> on java/lang/Throwable.
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Throwable", "<init>", "()V");
        }
        else {
            // invoke <init> on java/lang/Object.
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        }
        
        //now do field initialization
        stack.startMethod(1);
        Vector<ExecutionStep> steps = this.currentClassExecution.getSteps();
        OpcodeTracker tracker = currentClassExecution.getTracker();

        //loop through and queue up the field variables in the constructor instead
        //of the class visit.
        for (int i = 0; i < steps.size(); i++) {
            ExecutionStep step = steps.get(i);
            OpcodeType opcodeType = tracker.getOpcodeType(i + 1);

            if (opcodeType == OpcodeType.ROOT_EXPRESSION) {
                tracker.addToQueue(i + 1);
                int finalPosition = tracker.getFinalPosition(i + 1) - 1;

                //calculate the final position and jump to it.
                if (finalPosition >= 0) {
                    i = finalPosition;
                }
            } else {
                tracker.addToQueue(i + 1);
                step.visit(this);
            }
        }


        if (isParent) {
            //initialize all of the parent objects as fields
            Iterator<ClassDescriptor> parents = currentClass.getFlattenedListOfParents();
            while (parents.hasNext()) {
                ClassDescriptor parent = parents.next();
                String parentKey = parent.getStaticKey();
                String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
                String converted = QuorumConverter.convertStaticKeyToBytecodePath(parentKey);
                methodVisitor.visitVarInsn(ALOAD, THIS);
                methodVisitor.visitTypeInsn(NEW, converted);
                methodVisitor.visitInsn(DUP);
                //methodVisitor.visitInsn(DUP);
                //push a boolean onto the stack
                methodVisitor.visitInsn(ICONST_0);
                methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "(Z)V");
                methodVisitor.visitFieldInsn(PUTFIELD, name, parentName, QuorumConverter.convertStaticKeyToBytecodePathTypeName(parentKey));
            }

            //now that all parents have been instantiated, get 
            //their parents and set them appropriately
            parents = currentClass.getFlattenedListOfParents();
            while (parents.hasNext()) {
                ClassDescriptor parent = parents.next();
                String parentKey = parent.getStaticKey();
                String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
                String convertedParentName = QuorumConverter.convertStaticKeyToBytecodePath(parentKey);
                String convertedParentNameType = QuorumConverter.convertStaticKeyToBytecodePathTypeName(parentKey);
                Iterator<ClassDescriptor> grandParents = parent.getFlattenedListOfParents();
                while (grandParents.hasNext()) {
                    ClassDescriptor grandParent = grandParents.next();
                    String grandParentKey = grandParent.getStaticKey();
                    String grandParentName = QuorumConverter.convertParentStaticKeyToValidName(grandParent.getStaticKey());
                    String convertedGrandParent = QuorumConverter.convertStaticKeyToBytecodePathTypeName(grandParentKey);

                    //load the this pointer
                    methodVisitor.visitVarInsn(ALOAD, 0);

                    methodVisitor.visitFieldInsn(GETFIELD, this.processedClazzName, parentName, convertedParentNameType);
                    methodVisitor.visitVarInsn(ALOAD, 0);
                    //get the current object's parent field
                    methodVisitor.visitFieldInsn(GETFIELD, this.processedClazzName, grandParentName, convertedGrandParent);
                    //put it into the appropriate parent
                    methodVisitor.visitFieldInsn(PUTFIELD, convertedParentName, grandParentName, convertedGrandParent);
                }
            }
        }


        final String objectName = "$me";
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
            methodVisitor.visitFieldInsn(PUTFIELD, converted, objectName, javaObjectPath);
            fieldSize += 2;
        }

        int maxLocals = 3;

        if (isParent) {
            maxLocals++;
        }

        //put in the data from the constructor
        if (currentClassExecution.hasConstructor()) {
            MethodExecution constructor = currentClassExecution.getConstructor();
            currentMethodExecution = constructor;
            visitBlock(constructor, currentMethodExecution.getTracker());
            currentMethodExecution = null;
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
    private void computeMethod(MethodDescriptor action) {
        ClassDescriptor parent = (ClassDescriptor) action.getParent();
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
        methodVisitor.visitFieldInsn(GETFIELD, className, parentName, "L" + convertedParent + ";");
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

    /**
     * Generate a stub method for a blueprint.
     * 
     * @param blueprint 
     */
    public void computeBlueprint(BlueprintDescriptor blueprint) {
        String name = blueprint.getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(blueprint);
        TypeDescriptor returnType = blueprint.getReturnType();
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, name, params, null, null);
        methodVisitor.visitCode();

        int position = 1;

        stack.pushExpressionType(returnType);

        if (!returnType.isPrimitiveType() || returnType.isText()) {
            methodVisitor.visitInsn(ACONST_NULL);
        } else if (returnType.isNumber()) {
            methodVisitor.visitInsn(DCONST_0);
        } else {
            methodVisitor.visitInsn(ICONST_0);
        }

        int returnOpcode = QuorumConverter.convertTypeToReturnOpcode(returnType);
        methodVisitor.visitInsn(returnOpcode);

        int stackSize = position;
        int varSize = position;

        if (blueprint.getReturnType().isNumber()) {
            stackSize += 1;
        }

        methodVisitor.visitMaxs(stackSize, varSize);
        methodVisitor.visitEnd();
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
     * This returns a classwriter object that represents an interface for a
     * Quorum object.
     * 
     * @return 
     */
    public ClassWriter getInterfaceWriter() {
        return interfaceWriter;
    }

    /**
     * This returns a classwriter object with the corresponding bits for a
     * built Quorum class. This class does NOT contain the bits for
     * its corresponding interface.
     * 
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
            if (value.getType().isNumber()) {
                v = value.number;
            } else if (value.getType().isText()) {
                v = value.text;
            }

            if (value.getType().isNull()) {
                methodVisitor.visitInsn(ACONST_NULL);
            } else {
                methodVisitor.visitLdcInsn(v);
            }
        }
    }

    /**
     * Pushes the value of a variable onto the stack.
     * 
     * @param type - the type of variable
     * @param interpreterVarNumber - the variable's number in the interpreter. mapped to a JVM variable pool number.
     */
    public void pushVariable(TypeDescriptor type, int interpreterVarNumber) {
        methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(type), stack.getMappedVariableNumber(interpreterVarNumber, type));
    }

    /**
     * Performs the necessary bytecode operations for assigning a field variable (non local).
     * 
     * @param variable
     * @param subVariableName
     * @param subVariableType 
     */
    private void performFieldAssignment(AssignmentStep step, TypeDescriptor subVariableType, boolean isDefined, String conversion) {
        VariableParameterCommonDescriptor variable = step.getVariable();
        String subVariableName = step.getSubVariableName();
        String methodParent;
        String variableName;
        TypeDescriptor variableType;
        boolean assigningToObjectVariable = false;

        //if the sub-variable name does not match
        if (!subVariableName.equals("")) {
            //process and visit the variable instruction based on variable type.
            methodParent = QuorumConverter.convertStaticKeyToBytecodePath(variable.getType().getName());
            if (variable.isFieldVariable()) {
                assigningToObjectVariable = true;
                stack.pushExpressionType(variable.getType());
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                        variable.getName(), QuorumConverter.convertTypeToBytecodeString(variable.getType()));
            } else {
                methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(variable.getType()), stack.getMappedVariableNumber(variable.getVariableNumber(), variable.getType()));
            }
            variableName = subVariableName;
            variableType = stack.popExpressionType();
        } else {//if the sub-variable name does match
            //process and visit the aload variable instruction
            methodParent = QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey());
            methodVisitor.visitVarInsn(ALOAD, 0);

            if (step.getParent() != null && !step.getParent().equals(currentClass)) {
                methodVisitor.visitFieldInsn(GETFIELD, methodParent, QuorumConverter.convertParentStaticKeyToValidName(step.getParent().getStaticKey()), QuorumConverter.convertStaticKeyToBytecodePathTypeName(step.getParent().getStaticKey()));
            }

            variableName = variable.getName();
            variableType = variable.getType();
        }

        //process the expression and put the field
        if (!isDefined) {
            processFieldExpressions();
            variableType.setBytecodeInterface(true);
        } else {
            processExpressions();
        }

        //since this is a field we need to process any conversions later        
        if (conversion != null && conversion.equals("i2d")) {
            prepareNumberIntegerOperation();
        }

        //make the assignment
        if (step.getParent() != null && !step.getParent().equals(currentClass)) {
            methodVisitor.visitFieldInsn(PUTFIELD, QuorumConverter.convertStaticKeyToBytecodePath(step.getParent().getStaticKey()), variableName, QuorumConverter.convertTypeToBytecodeString(variableType));
        } else if (assigningToObjectVariable) {
            
            if(step.getParent() != null){
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, methodParent + "$Interface",
                    QuorumConverter.generateSetterNameFromSubField(step.getParent().getType(), variableName), QuorumConverter.generateSetterSignatureFromSubField(variableType));
            }else{
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, methodParent + "$Interface",
                    QuorumConverter.generateSetterNameFromSubField(variable.getType(), variableName), QuorumConverter.generateSetterSignatureFromSubField(variableType));
            }
        } else {
            methodVisitor.visitFieldInsn(PUTFIELD, methodParent, variableName, QuorumConverter.convertTypeToBytecodeString(variableType));
        }
    }

    /**
     * Performs the necessary bytecode operations for assigning a value to a local variable.
     * @param value
     * @param step 
     */
    private void performLocalAssignment(TypeDescriptor valueType, AssignmentStep step) {
        int variableNumber = step.getVariable().getVariableNumber() - currentClass.getNumberOfVariables();
        String subVariableName = step.getSubVariableName();

        //if this is an object and we are assigning to a field in that object load it
        if (!subVariableName.equals("")) {
            TypeDescriptor type = step.getVariable().getType();

            if (step.getVariable() instanceof ParameterDescriptor) {
                //we are now assigning to a parameter variable
                ParameterDescriptor varDescriptor = (ParameterDescriptor) step.getVariable();
                variableNumber = stack.getParameterNumber(varDescriptor.getName());
            }

            methodVisitor.visitVarInsn(ALOAD, variableNumber);
            processExpressions();
            valueType = stack.popExpressionType();

            if (!valueType.isPrimitiveType()) {
                if (valueType.isNull()) {
                    valueType = step.getSubVariableType();
                }
                valueType.setBytecodeInterface(true);
            }

            if(step.getParent() != null){
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, QuorumConverter.convertStaticKeyToBytecodePath(type.getStaticKey() + "$Interface"),
                        QuorumConverter.generateSetterNameFromSubField(step.getParent().getType(), subVariableName), QuorumConverter.generateSetterSignatureFromSubField(valueType));
            }else{
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, QuorumConverter.convertStaticKeyToBytecodePath(type.getStaticKey() + "$Interface"),
                        QuorumConverter.generateSetterNameFromSubField(type, subVariableName), QuorumConverter.generateSetterSignatureFromSubField(valueType));
            }
            } else {
            int mappedVariableNumber = -1;
            if (step.getVariable() instanceof ParameterDescriptor) {
                //we are now assigning to a parameter variable
                ParameterDescriptor varDescriptor = (ParameterDescriptor) step.getVariable();
                mappedVariableNumber = stack.getParameterNumber(varDescriptor.getName());
            } else {
                //if we are not dealing with an object variable then store it in a local varaiable
                mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, valueType);

                // Is it defined yet?
                if (mappedVariableNumber == -1) {
                    stack.setVariable(variableNumber, valueType);
                    mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, valueType);
                } else {
                    if (valueType.isBytecodeInterface()) {
                        //we need to set the stack variables type to something new or something?
                        stack.setVariableType(mappedVariableNumber, valueType);
                    }
                }
            }

            methodVisitor.visitVarInsn(QuorumConverter.getStoreOpcode(valueType), mappedVariableNumber);
        }


    }

    /**
     * This helper method performs an assignment into either a local variable or a field variable.
     * 
     * @param value The bytecode stack value needed by the assignment.
     * @param step The Assignment step being performed.
     * @param isDefined True if the variable being assigned a value was already defined.
     */
    private void performAssignment(TypeDescriptor valueType, AssignmentStep step, boolean isDefined, String conversion) {
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
                performFieldAssignment(step, valueType, isDefined, conversion);
            } else {
                performLocalAssignment(valueType, step);
            }
        } else {
            if (isField) {
                performFieldAssignment(step, valueType, isDefined, conversion);
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
        stack.clearExpressionTypeStack();
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
     * Performs an equality comparison (custom types only).
     * 
     * testEqual    | comparison
     * -------------|------------
     * true         | =
     * false        | not=
     * 
     * @param testEqual 
     */
    private void performCustomEqualityComparison(boolean testEqual) {
        //get two values off the stack
        stack.popExpressionType();
        stack.popExpressionType();

        //if an integer or boolean is on the stack evaluate it.
        // Start two labels: One we will jump to if we're not equal, and the
        // other will jump past that case.
        Label jumpIfNotEqualLabel = new Label();
        Label jumpPastLabel = new Label();


        // Do the comparison. Are they equal?
        methodVisitor.visitJumpInsn(IF_ACMPNE, jumpIfNotEqualLabel);

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
                if (!testEqual) {
                    methodVisitor.visitJumpInsn(IFGE, jumpIfFalseLabel);
                } else {
                    methodVisitor.visitJumpInsn(IFGT, jumpIfFalseLabel);
                }
            } else {
                if (!testEqual) {
                    methodVisitor.visitJumpInsn(IFLE, jumpIfFalseLabel);
                } else {
                    methodVisitor.visitJumpInsn(IFLT, jumpIfFalseLabel);
                }
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
                {
                    methodVisitor.visitJumpInsn(IF_ICMPGE, jumpIfFalseLabel);
                } else {
                    methodVisitor.visitJumpInsn(IF_ICMPGT, jumpIfFalseLabel);
                }
            } else {
                if (!testEqual) // if testing equality, use IF_ICMPGT.
                {
                    methodVisitor.visitJumpInsn(IF_ICMPLE, jumpIfFalseLabel);
                } else {
                    methodVisitor.visitJumpInsn(IF_ICMPLT, jumpIfFalseLabel);
                }
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
     * Helper method to (in bytecode) get the text value from an text object.
     */
    private void getTextFromTextObject() {
        stack.popExpressionType();
        String autoBoxClassName = "quorum/Libraries/Language/Types/Text$Interface";
        String autoBoxMethodSignature = "()Ljava/lang/String;";
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                "GetValue", autoBoxMethodSignature);
        stack.pushExpressionType(TypeDescriptor.getTextType());
    }

    /**
     * Helper method: get a boolean value from a Boolean object.
     */
    private void getBooleanFromBooleanObject() {
        stack.popExpressionType();
        String autoBoxClassName = "quorum/Libraries/Language/Types/Boolean$Interface";
        String autoBoxMethodSignature = "()Z";
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                "GetValue", autoBoxMethodSignature);
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
    }

    /**
     * Helper method: get a integer value from a Integer object.
     */
    private void getIntegerFromIntegerObject() {
        stack.popExpressionType();
        String autoBoxClassName = "quorum/Libraries/Language/Types/Integer$Interface";
        String autoBoxMethodSignature = "()I";
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                "GetValue", autoBoxMethodSignature);
        stack.pushExpressionType(TypeDescriptor.getIntegerType());
    }

    /**
     * Helper method: get a boolean value from a Boolean object.
     */
    private void getNumberFromNumberObject() {
        stack.popExpressionType();
        String autoBoxClassName = "quorum/Libraries/Language/Types/Number$Interface";
        String autoBoxMethodSignature = "()D";
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                "GetValue", autoBoxMethodSignature);
        stack.pushExpressionType(TypeDescriptor.getNumberType());
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
        stack.pushExpressionType(returnValueType);
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

        stack.pushExpressionType(returnValueType);
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

    private void processCastStep(Vector<ExecutionStep> steps, int i, ArrayList<Integer> visitedCasts) {
        //get the next step to be processed and get a cast step location if there is one.
        ExecutionStep step = steps.get(i);
        int castStepLocation = step.getCastStepLocation();
        //if there is a cast step associated with the step then process as 
        //one of two types of casts, autobox or standard cast.
        if (castStepLocation != -1) {
            //if we are dealing with an autobox we are dealing with 3 steps.
            //with a standard cast we will need move the cast step directly after
            //the visit of the parameter.
            ExecutionStep createStep = steps.get(castStepLocation + 1);
            ExecutionStep castStep = steps.get(castStepLocation);
            if (createStep instanceof AutoBoxCreateStep && !visitedCasts.contains(castStepLocation + 1)) {//autobox
                castStep = steps.get(i + 1);
                if (!(castStep instanceof UnaryOperationStep)) {
                    castStep = null;
                }
                visitWithAutoBoxStep(step, (AutoBoxCreateStep) createStep, castStep);
                if(castStep != null){
                    visitedCasts.add(castStepLocation);
                }
                visitedCasts.add(castStepLocation + 1);
                visitedCasts.add(castStepLocation + 2);
            } else if (!(castStep instanceof IntegerReverseAutoBoxStep)
                    && !(castStep instanceof NumberReverseAutoBoxStep)
                    && !(castStep instanceof TextReverseAutoBoxStep)
                    && !(castStep instanceof BooleanReverseAutoBoxStep) 
                    && !(createStep instanceof AutoBoxCreateStep)
                    && !visitedCasts.contains(castStepLocation)) {//standard cast
                step.visit(this);

                step = steps.get(castStepLocation);
                step.visit(this);
                visitedCasts.add(castStepLocation);
            } else if(!(createStep instanceof AutoBoxCreateStep) && !visitedCasts.contains(castStepLocation)) {//visit reverse autoboxes on returns
                castStep.setModifiedReturn(true);
                step.visit(this);
                castStep.visit(this);
                visitedCasts.add(castStepLocation);
            }else{
                step.visit(this);
            }
        } else {//standard visit of a non casting series of steps.
            step.visit(this);
        }
    }

    private int processAutoBox(Vector<ExecutionStep> steps, int i, int castStepLocation, int callStepLocation, ArrayList<Integer> visitedCasts, OpcodeTracker tracker) {
        //if there is a cast step associated with the step then process as 
        //one of two types of casts, autobox or standard cast.
        if (castStepLocation != -1) {
            //if we are dealing with an autobox we are dealing with 3 steps.
            //with a standard cast we will need move the cast step directly after
            //the visit of the parameter.
            ExecutionStep createStep = steps.get(castStepLocation + 1);
            if (createStep instanceof AutoBoxCreateStep && !visitedCasts.contains(castStepLocation + 1)) {//autobox
                ExecutionStep castStep = steps.get(i + 1);
                if (!(castStep instanceof UnaryOperationStep)) {
                    castStep = null;
                }

                if(castStep != null)
                    visitedCasts.add(castStepLocation);
                
                visitedCasts.add(castStepLocation + 1);
                visitedCasts.add(castStepLocation + 2);

                visitWithAutoBoxStep(steps, (AutoBoxCreateStep) createStep, castStep, tracker, i, callStepLocation + 1, visitedCasts);

                i = callStepLocation + 1;
            }
        }
        return i;
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

            ArrayList<Integer> visitedCasts = new ArrayList<Integer>();
            //check queue for its current value
            int begin = tracker.removeFromQueue();
            int end = tracker.peekQueue();

            //loop through all op-codes
            Vector<ExecutionStep> steps = execution.getSteps();
            processExpression(tracker, begin, end, steps, visitedCasts);

            //clear out the queue at the end of visiting
            if (!tracker.getOpcodeType(end).equals(OpcodeType.ROOT_EXPRESSION)) {
                tracker.clearQueue();
            }
        }

        if (tracker.getQueueSize() == 1) {
            tracker.clearQueue();
        }
    }

    private void processExpression(OpcodeTracker tracker, int begin, int end, Vector<ExecutionStep> steps, ArrayList<Integer> visitedCasts) {

        //loop through all op-codes
        for (int i = begin; i < end; i++) { //visit the expressions
            // If this is a cast step that we have already visited, ignore
            // it, so we don't accidentally visit it twice.
            if (visitedCasts.contains(i)) {
                continue;
            }

            //if we are in a call step find that call step and check to see if it
            //is an autobox. If it is insert the autobox.
            int castStepLocation = -1;
            ArrayList<Integer> functionParameterMapping = tracker.getFunctionParameterMapping(i);
            if (functionParameterMapping != null) {
                for(int count = functionParameterMapping.size() - 1; count >= 0; count--){
                    int callStepNumber = functionParameterMapping.get(count);
                    ExecutionStep callStep = steps.get(callStepNumber);

                    if(callStep instanceof CallStep){
                        castStepLocation = callStep.getCastStepLocation();

                        i = processAutoBox(steps, i, castStepLocation, callStepNumber, visitedCasts, tracker);
                    }
                }

            }

            //is this step the first parameter to a function call?
            //if yes, call visitCallSpecial, with the call step 
            //for that function call
            boolean funcParam = tracker.containsFunctionParameterMapping(i);
            if (funcParam) {
                ArrayList<Integer> callLocations = tracker.getFunctionParameterMapping(i);
                for (int k = callLocations.size() - 1; k >= 0; k--) {
                    int opcodeLocation = callLocations.get(k);
                    if(!visitedCasts.contains(k)){
                        ExecutionStep callStep = steps.get(opcodeLocation);

                        boolean newStep = false;

                        //opcodeLocation = i;
                        // Make sure this is actually a CallStep.
                        while (!(callStep instanceof CallStep)) {
                            opcodeLocation++;
                            callStep = steps.get(opcodeLocation);
                            newStep = true;
                        }

                        if (callStep instanceof CallStep) {
                            CallStep call = (CallStep) callStep;

                            //check the new step for casts
                            if (newStep) {
                                castStepLocation = call.getCastStepLocation();
                                
                                
                                i = processAutoBox(steps, i, castStepLocation, opcodeLocation, visitedCasts, tracker);

                            }

                            //insert the pointer for the object being called upon
                            if (!call.isCalleeLoaded()) {
                                visitCallSpecial(call);
                            }
                        } else {
                            Logger.getLogger(QuorumJavaBytecodeStepVisitor.class.getName()).log(
                                    Level.SEVERE, "Function mapping between opcode parameters "
                                    + "and callsteps results in incorrect values. This is a compiler bug.");
                        }
                    }
                }
            }//if no, do nothing

            processCastStep(steps, i, visitedCasts);
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

            ArrayList<Integer> visitedCasts = new ArrayList<Integer>();

            //check queue for its current value
            int begin = tracker.removeFromQueue();
            int end = tracker.peekQueue();

            //loop through all op-codes
            Vector<ExecutionStep> steps = execution.getSteps();
            boolean countersReady = false;
            for (int i = begin; i < end; i++) { //visit the expressions

                // If this is a cast step that we have already visited, ignore
                // it, so we don't accidentally visit it twice.
                if (visitedCasts.contains(i)) {
                    continue;
                }

                //if we are in a call step find that call step and check to see if it
                //is an autobox. If it is insert the autobox.
                int castStepLocation = -1;
                ArrayList<Integer> functionParameterMapping = tracker.getFunctionParameterMapping(i);
                if (functionParameterMapping != null) {
                    int callStepNumber = functionParameterMapping.get(functionParameterMapping.size() - 1);
                    ExecutionStep callStep = steps.get(callStepNumber);
                    castStepLocation = callStep.getCastStepLocation();

                    i = processAutoBox(steps, i, castStepLocation, callStepNumber, visitedCasts, tracker);

                }

                //is this step the first parameter to a function call?
                //if yes, call visitCallSpecial, with the call step 
                //for that function call
                boolean funcParam = tracker.containsFunctionParameterMapping(i);
                if (funcParam) {
                    ArrayList<Integer> callLocations = tracker.getFunctionParameterMapping(i);
                    for (int k = callLocations.size() - 1; k >= 0; k--) {
                        int opcodeLocation = callLocations.get(k);
                        ExecutionStep callStep = steps.get(opcodeLocation);

                        boolean newStep = false;

                        // Make sure this is actually a CallStep.
                        while (!(callStep instanceof CallStep)) {
                            opcodeLocation++;
                            callStep = steps.get(opcodeLocation);
                            newStep = true;
                        }

                        if (callStep instanceof CallStep) {
                            CallStep call = (CallStep) callStep;

                            //check the new step for casts
                            if (newStep) {
                                castStepLocation = call.getCastStepLocation();

                                i = processAutoBox(steps, i, castStepLocation, opcodeLocation, visitedCasts, tracker);
                            }

                            //insert the pointer for the object being called upon
                            if (!call.isCalleeLoaded()) {
                                visitCallSpecial(call);
                            }
                        } else {
                            Logger.getLogger(QuorumJavaBytecodeStepVisitor.class.getName()).log(
                                    Level.SEVERE, "Function mapping between opcode parameters "
                                    + "and callsteps results in incorrect values. This is a compiler bug.");
                        }
                    }
                }//if no, do nothing



                if ((loopType.equals(LabelTypeEnum.FROM) || loopType.equals(LabelTypeEnum.TIMES)) && countersReady) {
                    methodVisitor.visitLabel(stack.peekLabel().getLabel());
                    methodVisitor.visitVarInsn(ILOAD, stack.peekCounterVariable());
                    methodVisitor.visitVarInsn(ILOAD, stack.peekMaximumVariable());
                }

                processCastStep(steps, i, visitedCasts);

                if (loopType.equals(LabelTypeEnum.FROM) && i == begin) {
                    methodVisitor.visitVarInsn(ISTORE, stack.pushMaximumVariable());
                    i = i + 1;
                    countersReady = true;
                } else if (loopType.equals(LabelTypeEnum.TIMES) && i == end - 3) {
                    methodVisitor.visitVarInsn(ISTORE, stack.pushMaximumVariable());
                } else if (loopType.equals(LabelTypeEnum.TIMES) && i == end - 2) {
                    countersReady = true;
                    methodVisitor.visitVarInsn(ISTORE, stack.pushCounterVariable());
                }
            }

            //clear out the queue at the end of visiting
            if (!tracker.getOpcodeType(end).equals(OpcodeType.ROOT_EXPRESSION)) {
                tracker.clearQueue();
            }
        }

        if (tracker.getQueueSize() == 1) {
            tracker.clearQueue();
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
        * Visit the given expression, performing the appropriate autoboxing.
        * 
        * @param step
        * @param castStep - the autobox step
        */
    private void visitWithAutoBoxStep(Vector<ExecutionStep> steps, AutoBoxCreateStep createStep, ExecutionStep castStep, OpcodeTracker tracker, int begin, int end, ArrayList<Integer> visitedCasts) {

        String autoBoxClassName = null;

        String autoBoxMethodSignature = null;

        if (createStep.getPrimitiveType().isInteger()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Integer";
            autoBoxMethodSignature = "(I)V";
        } else if (createStep.getPrimitiveType().isNumber()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Number";
            autoBoxMethodSignature = "(D)V";
        } else if (createStep.getPrimitiveType().isText()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Text";
            autoBoxMethodSignature = "(Ljava/lang/String;)V";
        } else if (createStep.getPrimitiveType().isBoolean()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Boolean";
            autoBoxMethodSignature = "(Z)V";
        }
        // Create a new autoboxed object.
        methodVisitor.visitTypeInsn(NEW, autoBoxClassName);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, autoBoxClassName, "<init>", "()V");
        // Add the expression from the step.
        processExpression(tracker, begin, end, steps, visitedCasts);

        // Do we need to do a cast?
        if (castStep != null) {
            castStep.visit(this);
        }

        // Call SetValue.
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, autoBoxClassName,
                "SetValue", autoBoxMethodSignature);
    }

    private void visitWithAutoBoxStep(ExecutionStep step, AutoBoxCreateStep createStep, ExecutionStep castStep) {
        String autoBoxClassName = null;

        String autoBoxMethodSignature = null;

        if (createStep.getPrimitiveType().isInteger()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Integer";
            autoBoxMethodSignature = "(I)V";
        } else if (createStep.getPrimitiveType().isNumber()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Number";
            autoBoxMethodSignature = "(D)V";
        } else if (createStep.getPrimitiveType().isText()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Text";
            autoBoxMethodSignature = "(Ljava/lang/String;)V";
        } else if (createStep.getPrimitiveType().isBoolean()) {
            autoBoxClassName = "quorum/Libraries/Language/Types/Boolean";
            autoBoxMethodSignature = "(Z)V";
        }
        // Create a new autoboxed object.
        methodVisitor.visitTypeInsn(NEW, autoBoxClassName);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, autoBoxClassName, "<init>", "()V");

        // Add the expression from the step.
        step.visit(this);

        // Do we need to do a cast?
        if (castStep != null) {
            castStep.visit(this);
        }

        // Call SetValue.
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, autoBoxClassName,
                "SetValue", autoBoxMethodSignature);
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
        //classWriter = new ClassWriter(0);
        interfaceWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);


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
//        if (!".Main".equals(staticKey) && !".Melissa".equals(staticKey) && !".Stefik".equals(staticKey) && !"Libraries.Language.Object".equals(staticKey)
//                && !"Libraries.Language.Support.CompareResult".equals(staticKey)
////                && !"Libraries.Containers.List".equals(staticKey)
////                && !"Libraries.Containers.Blueprints.ListBlueprint".equals(staticKey)
//                && !"Libraries.Containers.Array".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.Copyable".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.Indexed".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.Container".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.Addable".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.Iterative".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.ListBlueprint".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.ArrayBlueprint".equals(staticKey)
//                && !"Libraries.Containers.Blueprints.Iterator".equals(staticKey)
//                && !"Libraries.Containers.Support.ArrayIterator".equals(staticKey)
//                && !"Libraries.Containers.Support.ListNode".equals(staticKey)
//                && !"Libraries.Language.Types.Integer".equals(staticKey)
//                && !"Libraries.Language.Types.Number".equals(staticKey)
//                && !"Libraries.Language.Types.Text".equals(staticKey)
//                && !"Libraries.Language.Types.Boolean".equals(staticKey)
//                && !"Libraries.Language.Errors.Error".equals(staticKey)
//                && !"Libraries.System.StackTraceItem".equals(staticKey)
//                && !".StefikGrand".equals(staticKey)) {
//            return;
//        }



        String name = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);
        processedClazzName = name;

        String interfaceName = QuorumConverter.convertClassNameToInterfaceName(name);

        if (currentClass.getStaticKey().equals("Libraries.Language.Errors.Error")) {
            classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Throwable", new String[]{interfaceName});
        } else if (currentClass.getParent("Libraries.Language.Errors.Error") != null) {
            classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "quorum/Libraries/Language/Errors/Error", new String[]{interfaceName});
        } else {
            //this will have to be modified for inheritance conversion
            classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", new String[]{interfaceName});
        }
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
        while (classVariables.hasNext()) {
            VariableDescriptor next = classVariables.next();
            String varName = next.getName();
            TypeDescriptor varType = next.getType();
            String converted = "";

            if (varType.isPrimitiveType()) {
                converted = QuorumConverter.convertTypeToBytecodeString(varType);
            } else {
                converted = QuorumConverter.convertStaticKeyToBytecodePathTypeName(QuorumConverter.convertClassNameToInterfaceName(varType.getStaticKey()));
            }

            int accessModifier;
            if (next.getAccessModifier().toString().compareTo(AccessModifierEnum.PUBLIC.toString()) == 0) {
                accessModifier = ACC_PUBLIC;
            } else {
                accessModifier = ACC_PUBLIC;
            }

            fieldVisitor = classWriter.visitField(accessModifier, varName, converted, null, null);
            fieldVisitor.visitEnd();
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
        computeConstructor(false);

        //now dump all of the parent methods that 
        //are not in the base class out as wrapper functions.
        Iterator<MethodDescriptor> methodIterator = currentClass.getVirtualMethods();
        while (methodIterator.hasNext()) {
            MethodDescriptor method = methodIterator.next();

            if (!(method instanceof BlueprintDescriptor)) {
                computeMethod(method);
            }
        }

        this.visitInterface(clazz);

        //now do all methods
        Iterator<MethodExecution> methods = clazz.getMethods();
        while (methods.hasNext()) {
            MethodExecution method = methods.next();
            MethodDescriptor methodDescriptor = method.getMethodDescriptor();
            if (!methodDescriptor.isConstructor()) {
                visit(method);
            }
        }

        //insert stub methods for all the blueprints
        Iterator<BlueprintDescriptor> blueprints = clazz.getClassDescriptor().getBlueprints();
        while (blueprints.hasNext()) {
            BlueprintDescriptor blueprint = blueprints.next();
            computeBlueprint(blueprint);
        }

        Iterator<SystemActionDescriptor> systems = clazz.getClassDescriptor().getSystemActions();
        while (systems.hasNext()) {
            SystemActionDescriptor sys = systems.next();
            computeSystemAction(sys);
        }
        classWriter.visitEnd();
    }

    /**
     * Computes the bytecode for a interface representing the inheritance
     * hierarchy of the Quorum objects.
     * 
     * @param clazz 
     */
    public void visitInterface(ClassExecution clazz) {
        String staticKey = clazz.getClassDescriptor().getStaticKey();
        String name = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);

        String interfaceName = QuorumConverter.convertClassNameToInterfaceName(name);
        //generate a list of parents here
        int numberFlatParents = currentClass.getNumberFlatParents();
        String[] parentStrings = new String[numberFlatParents];

        int i = 0;
        Iterator<ClassDescriptor> parents = currentClass.getFlattenedListOfParents();
        while (parents.hasNext()) {
            ClassDescriptor parent = parents.next();
            String parentKey = parent.getStaticKey();
            String parentFullPath = QuorumConverter.convertStaticKeyToBytecodePath(parentKey);
            parentFullPath = QuorumConverter.convertClassNameToInterfaceName(parentFullPath);

            visitFieldVariablesInterface(parent, clazz.getClassDescriptor());
            parentStrings[i] = parentFullPath;
            i++;
        }
        //pass these parents into the visit function
        interfaceWriter.visit(V1_6, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, interfaceName, null, "java/lang/Object", parentStrings);

        Iterator<BlueprintDescriptor> blueprints = clazz.getClassDescriptor().getBlueprints();
        while (blueprints.hasNext()) {
            BlueprintDescriptor bPrints = blueprints.next();
            visitInterface(bPrints);
        }

        Iterator<MethodExecution> methods = clazz.getMethods();
        while (methods.hasNext()) {
            MethodExecution method = methods.next();
            visitInterface(method);
        }

        Iterator<SystemActionDescriptor> systems = clazz.getClassDescriptor().getSystemActions();
        while (systems.hasNext()) {
            SystemActionDescriptor sys = systems.next();
            visitInterface(sys);
        }
        //Do field visiting for the class.
        visitFieldVariablesInterface(null, clazz.getClassDescriptor());

        interfaceWriter.visitEnd();
    }

    /**
     * This method computes helper getter and setter methods for a field variable
     * either in a parent class (parent != null) or in a base class only (parent == null).
     * 
     * @param parent
     * @param clazz 
     */
    private void visitFieldVariablesInterface(ClassDescriptor parent, ClassDescriptor clazz) {
        Iterator<VariableDescriptor> classVariables;

        if (parent != null) {
            classVariables = parent.getClassVariables();
        } else {
            classVariables = clazz.getClassVariables();
        }
        int j = 0;
        while (classVariables.hasNext()) {
            VariableDescriptor var = classVariables.next();

            //generate the getter in the interface
            String hiddenGetterName;
            String hiddenGetterSignature;

            if (parent != null) {
                hiddenGetterName = QuorumConverter.generateGetterNameFromField(parent, var);
            } else {
                hiddenGetterName = QuorumConverter.generateGetterNameFromField(clazz, var);
            }

            hiddenGetterSignature = QuorumConverter.generateGetterSignatureFromField(var);

            //we only need to weave this into the parent interface, not the child
            if (parent == null) {
                interfaceMethodVisitor = interfaceWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, hiddenGetterName, hiddenGetterSignature, null, null);
                interfaceMethodVisitor.visitEnd();
            }


            //now generate the actual getter in the implementation class
            MethodVisitor hiddenGetter = classWriter.visitMethod(ACC_PUBLIC, hiddenGetterName, hiddenGetterSignature, null, null);
            hiddenGetter.visitVarInsn(ALOAD, 0);

            if (parent != null) {
                hiddenGetter.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()), QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey()), QuorumConverter.convertStaticKeyToBytecodePathTypeName(parent.getStaticKey()));
                hiddenGetter.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(parent.getStaticKey()),
                        var.getName(), QuorumConverter.convertTypeToBytecodeString(var.getType()));
            } else {
                hiddenGetter.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(clazz.getStaticKey()),
                        var.getName(), QuorumConverter.convertTypeToBytecodeString(var.getType()));
            }

            hiddenGetter.visitInsn(QuorumConverter.convertTypeToReturnOpcode(var.getType()));
            hiddenGetter.visitMaxs(1, 1);
            hiddenGetter.visitEnd();

            //generate the setter in the interface
            String hiddenSetterName;
            if (parent != null) {
                hiddenSetterName = QuorumConverter.generateSetterNameFromField(parent, var);
            } else {
                hiddenSetterName = QuorumConverter.generateSetterNameFromField(clazz, var);
            }

            String hiddenSetterSignature = QuorumConverter.generateSetterSignatureFromField(var);
            if (parent == null) {
                interfaceMethodVisitor = interfaceWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, hiddenSetterName, hiddenSetterSignature, null, null);
                interfaceMethodVisitor.visitEnd();
            }

            //generate the setter into the bytecode
            MethodVisitor hiddenSetter = classWriter.visitMethod(ACC_PUBLIC, hiddenSetterName, hiddenSetterSignature, null, null);
            hiddenSetter.visitVarInsn(ALOAD, 0);


            if (parent != null) {
                hiddenSetter.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(clazz.getStaticKey()), QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey()), QuorumConverter.convertStaticKeyToBytecodePathTypeName(parent.getStaticKey()));
                hiddenSetter.visitVarInsn(QuorumConverter.getLoadOpcode(var.getType()), 1);
                hiddenSetter.visitFieldInsn(PUTFIELD,
                        QuorumConverter.convertStaticKeyToBytecodePath(parent.getStaticKey()),
                        var.getName(), QuorumConverter.convertTypeToBytecodeString(var.getType()));
            } else {
                hiddenSetter.visitVarInsn(QuorumConverter.getLoadOpcode(var.getType()), 1);
                hiddenSetter.visitFieldInsn(PUTFIELD,
                        QuorumConverter.convertStaticKeyToBytecodePath(clazz.getStaticKey()),
                        var.getName(), QuorumConverter.convertTypeToBytecodeString(var.getType()));
            }
            hiddenSetter.visitInsn(RETURN);
            hiddenSetter.visitMaxs(2, 2);
            hiddenSetter.visitEnd();

            j++;
        }
    }

    /**
     * Computes the methods for an interface.
     * 
     * @param method 
     */
    public void visitInterface(MethodExecution method) {
        //still have to handle parameters here.
        String name = method.getMethodDescriptor().getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(method.getMethodDescriptor());
        interfaceMethodVisitor = interfaceWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, name, params, null, null);
        interfaceMethodVisitor.visitEnd();
    }

    /**
     * Computes the methods for an interface.
     * 
     * @param method 
     */
    public void visitInterface(BlueprintDescriptor method) {
        //still have to handle parameters here.
        String name = method.getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(method);
        interfaceMethodVisitor = interfaceWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, name, params, null, null);
        interfaceMethodVisitor.visitEnd();
    }

    /**
     * Computes the methods for an interface.
     * 
     * @param method 
     */
    public void visitInterface(SystemActionDescriptor method) {
        //still have to handle parameters here.
        String name = method.getName();
        String params = QuorumConverter.convertMethodDescriptorToBytecodeSignature(method);
        interfaceMethodVisitor = interfaceWriter.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, name, params, null, null);
        interfaceMethodVisitor.visitEnd();
    }

    /**
     * Computes the contents of a method block.
     * 
     * @param method 
     */
    public void visitBlock(MethodExecution method, OpcodeTracker tracker) {
        visitAllSteps(method, 0, method.getSteps().size(), tracker);
    }

    /**
     * Executes the steps between start and end.
     * 
     * @param method
     * @param start
     * @param end
     * @param tracker 
     */
    public void visitAllSteps(MethodExecution method, int start, int end, OpcodeTracker tracker) {
        Vector<ExecutionStep> steps = method.getSteps();
        for (int i = start; i < end; i++) {//visit each of the steps in the method execution
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
            methodVisitor.visitMethodInsn(INVOKESTATIC, "plugins/quorum/Libraries/System/Quorum", "Load",
                "()V");
            methodVisitor.visitTypeInsn(NEW, processedClazzName);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, processedClazzName, "<init>", "()V");
            methodVisitor.visitVarInsn(ASTORE, 1);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, processedClazzName,
                    method.getMethodDescriptor().getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(method.getMethodDescriptor()));
            methodVisitor.visitMethodInsn(INVOKESTATIC, "plugins/quorum/Libraries/System/Quorum", "Unload",
                "()V");
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

        visitBlock(method, currentMethodExecution.getTracker());

        //the stack size should also change depending on the 
        //expressions that need to be processed.
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();

    }

    @Override
    public void visit(AlertStep step) {
        //throw the exception
        methodVisitor.visitInsn(ATHROW);
    }

    @Override
    public void visit(AlwaysEndStep step) {
        CheckDetectDescriptor desc = stack.popCheckDetect();

        methodVisitor.visitVarInsn(ALOAD, desc.getLastDetectVariableNumber());
        methodVisitor.visitInsn(ATHROW);

        //check detect construct is now ending
        methodVisitor.visitLabel(desc.getConstructEnd());
    }

    @Override
    public void visit(AssignIntegerObjectToNumberAutoBoxLocalStep step) {
        //TODO:make test cases for these
        //process the expressions
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();

            //convert object to primitive
            String autoBoxClassName = QuorumConverter.convertTypeToBytecodeString(pop);
            pop.convertToPrimitive();
            String autoBoxMethodSignature = QuorumConverter.generateGetterSignatureFromSubField(pop);

            // Call SetValue.
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName, "GetValue", autoBoxMethodSignature);

            //push the int onto the expression stack and calculate the cast to number
            stack.pushExpressionType(pop);
            TypeDescriptor valueType = new TypeDescriptor();
            valueType.setName(TypeDescriptor.NUMBER);
            castValueToValue(valueType);
            stack.popExpressionType();

            pop = valueType;
        }

        //Assigns an integer to a local variable or field of type number.
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignIntegerObjectToNumberAutoBoxStep step) {
        //TODO:make test cases for these
        //process the expressions
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();

            //convert object to primitive
            String autoBoxClassName = QuorumConverter.convertTypeToBytecodeString(pop);
            pop.convertToPrimitive();
            String autoBoxMethodSignature = QuorumConverter.generateGetterSignatureFromSubField(pop);

            // Call SetValue.
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName, "GetValue", autoBoxMethodSignature);

            //push the int onto the expression stack and calculate the cast to number
            stack.pushExpressionType(pop);
            TypeDescriptor valueType = new TypeDescriptor();
            valueType.setName(TypeDescriptor.NUMBER);
            castValueToValue(valueType);
            stack.popExpressionType();

            pop = valueType;
        }

        //Assigns an integer to a field of type number.
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AssignObjectAutoBoxLocalStep step) {
        //TODO:Check to make sure this only triggers on a primitive/Object pair
        //if it fires on a Object/Parent pair we need to modify this solution.
        //process the expressions
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();

            //convert object to primitive
            String autoBoxClassName = QuorumConverter.convertTypeToBytecodeString(pop);
            pop.convertToPrimitive();
            String autoBoxMethodSignature = QuorumConverter.generateGetterSignatureFromSubField(pop);

            // Call SetValue.
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName, "GetValue", autoBoxMethodSignature);
        }

        //Assigns an integer to a local variable or field of type number.
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignObjectAutoBoxStep step) {
        //TODO:Check to make sure this only triggers on a primitive/Object pair
        //if it fires on a Object/Parent pair we need to modify this solution.
        //process the expressions
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();

            //convert object to primitive
            String autoBoxClassName = QuorumConverter.convertTypeToBytecodeString(pop);
            pop.convertToPrimitive();
            String autoBoxMethodSignature = QuorumConverter.generateGetterSignatureFromSubField(pop);

            // Call SetValue.
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName, "GetValue", autoBoxMethodSignature);
        }

        //Assigns an integer to a field of type number.
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AssignmentCustomLocalStep step) {
        //process the expressions
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }

        //Assigns an integer to a local variable or field of type number.
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignmentCustomStep step) {
        //process the expressions
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }

        //Assigns an integer to a field of type number.
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AssignmentNumberIntegerLocalStep step) {
        //process the expressions
        TypeDescriptor pop = null;
        String conversion = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            //prepare the integer as a number.
            prepareNumberIntegerOperation();
            pop = stack.popExpressionType();
        } else {
            conversion = "i2d";
        }

        //Assigns an integer to a local variable or field of type number.
        performAssignment(pop, step, false, conversion);
    }

    @Override
    public void visit(AssignmentNumberIntegerStep step) {
        //process the expressions
        TypeDescriptor pop = null;
        String conversion = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            prepareNumberIntegerOperation();
            pop = stack.popExpressionType();
        } else {
            conversion = "i2d";
        }

        //Assigns an integer to a field of type number.
        performAssignment(pop, step, true, conversion);
    }

    @Override
    public void visit(AssignmentBooleanLocalStep step) {
        //Assigns a boolean to a local variable or field of type boolean
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignmentBooleanStep step) {
        //Assigns a boolean to a field of type boolean
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AssignmentIntegerLocalStep step) {
        //process the expressions in the queue
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        //Assigns an integer to a local variable or field of type integer
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignmentIntegerStep step) {
        //Assigns an integer to a field of type integer
        TypeDescriptor pop = null;

        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AssignmentNumberLocalStep step) {
        //Assigns a number to a local variable or field of type number.
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignmentNumberStep step) {
        //Assigns a number to a field of type number.
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AssignmentTextLocalStep step) {
        //Assigns a number to a local variable or field of type text.
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, false, null);
    }

    @Override
    public void visit(AssignmentTextStep step) {
        //remove the constant from the bytecode stack and assign a number to 
        //a field of type text.
        TypeDescriptor pop = null;
        if (step.getSubVariableName().equals("") && !step.getVariable().isFieldVariable()) {
            processExpressions();
            pop = stack.popExpressionType();
        }
        performAssignment(pop, step, true, null);
    }

    @Override
    public void visit(AutoBoxCreateStep step) {
        int a = 5;
    }

    @Override
    public void visit(BeginCheckScopeStep step) {
        CheckDetectDescriptor desc = new CheckDetectDescriptor();
        stack.pushCheckDetect(desc);

        Stack<LabelStackValue> tempStack = new Stack<LabelStackValue>();
        ArrayList<DetectInfo> allDetects = step.getLandingPads().getAllDetects();
        //get all of the blocks associated with the try (all detects and always blocks).
        for (int i = 0; i < allDetects.size(); i++) {
            DetectInfo next = allDetects.get(i);

            //if this is a detect parameter build the visit try catch block calls
            if (next.getDetectParameter().errorType != null) {
                methodVisitor.visitTryCatchBlock(desc.getCheckStart(), desc.getCheckEnd(), desc.pushDetectStartLabel(), "quorum/Libraries/Language/Errors/Error");
                //if there is an always and this is the first detect or
                //if it's not the first detect generate the try catch block calls
                if (step.getLandingPads().hasAlwaysBlock() && i == 0) {
                    desc.setHasAlways(true);
                    methodVisitor.visitTryCatchBlock(desc.getCheckStart(), desc.getCheckEnd(), desc.getAlwaysStart(), null); // null means "catch any type"
                    methodVisitor.visitTryCatchBlock(desc.peekDetectStartLabel(), desc.pushDetectEndLabel(), desc.getAlwaysStart(), null);
                    tempStack.push(new LabelStackValue(LabelTypeEnum.DETECT, GOTO, desc.peekDetectEndLabel()));
                } else if (step.getLandingPads().hasAlwaysBlock()) {
                    desc.setHasAlways(true);
                    methodVisitor.visitTryCatchBlock(desc.peekDetectStartLabel(), desc.pushDetectEndLabel(), desc.getAlwaysStart(), null);
                }
            } else if (next.isAlawysBlock()) {
                methodVisitor.visitTryCatchBlock(desc.getAlwaysStart(), desc.getAlwaysEnd(), desc.getAlwaysStart(), null); // null means "catch any type"
                desc.setAlwaysStartPosition(next.getLocalLocation());
            }
        }

        //reverse and put into label stack
        while (!tempStack.isEmpty()) {
            stack.pushLabel(tempStack.pop());
        }

        stack.pushLabel(new LabelStackValue(LabelTypeEnum.CHECK, GOTO, desc.getCheckEnd()));
        //visit the try label
        methodVisitor.visitLabel(desc.getCheckStart());
    }

    @Override
    public void visit(BeginDetectScopeStep step) {
        CheckDetectDescriptor desc = stack.peekCheckDetect();

        //Insert 'always' code here.
        int alwaysStartPosition = desc.getAlwaysStartPosition() + 2;
        int alwaysEndPosition = alwaysStartPosition;
        Vector<ExecutionStep> steps = this.currentMethodExecution.getSteps();
        while (desc.getAlwaysStartPosition() != -1 && !(steps.get(alwaysEndPosition) instanceof AlwaysEndStep)) {
            alwaysEndPosition++;
        }

        visitAllSteps(this.currentMethodExecution, alwaysStartPosition, alwaysEndPosition, this.getCurrentTracker());

        //LabelStackValue popLabel0 = stack.popLabel();
        methodVisitor.visitJumpInsn(GOTO, desc.getConstructEnd());


        methodVisitor.visitLabel(desc.getNextDetectStartLabel());

        // Store the error. This is essentially a "hidden" variable.
        int variableNumber = step.getDetectParameter().getVariableNumber();
        int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, step.getDetectParameter().getType());
        if (mappedVariableNumber == -1) {
            stack.setVariable(variableNumber, step.getDetectParameter().getType());
            mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, step.getDetectParameter().getType());
        }

        desc.setLastDetectVariableNumber(mappedVariableNumber);
        methodVisitor.visitVarInsn(ASTORE, mappedVariableNumber);

        stack.pushLabel(new LabelStackValue(LabelTypeEnum.DETECT, GOTO, desc.peekDetectEndLabel()));
        desc.pushDetectStartLabel();


    }

    @Override
    public void visit(BeginScopeStep step) {
        if (step.getBlockTag().equals("always")) {
            CheckDetectDescriptor desc = stack.peekCheckDetect();

            //Insert 'always' code here *if it's labeled as always*
            int alwaysStartPosition = desc.getAlwaysStartPosition() + 2;
            int alwaysEndPosition = alwaysStartPosition;
            Vector<ExecutionStep> steps = this.currentMethodExecution.getSteps();
            while (desc.getAlwaysStartPosition() != -1 && !(steps.get(alwaysEndPosition) instanceof AlwaysEndStep)) {
                alwaysEndPosition++;
            }

            visitAllSteps(this.currentMethodExecution, alwaysStartPosition, alwaysEndPosition, this.getCurrentTracker());

            //LabelStackValue popLabel0 = stack.popLabel();
            methodVisitor.visitJumpInsn(GOTO, desc.getConstructEnd());


            methodVisitor.visitLabel(desc.getAlwaysStart());

            //TODO: this astore needs to be fixed and not hardcoded.
            methodVisitor.visitVarInsn(ASTORE, desc.getLastDetectVariableNumber());
            methodVisitor.visitLabel(desc.getAlwaysEnd());
        }

    }

    @Override
    public void visit(BinaryAddBooleanTextStep step) {
        //Converts a boolean to text.
        TypeDescriptor popExpressionType = stack.popExpressionType();
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
        stack.pushExpressionType(popExpressionType);
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
        TypeDescriptor popExpressionType = stack.popExpressionType();
        stack.pushExpressionType(TypeDescriptor.getIntegerType());
        stack.pushExpressionType(popExpressionType);
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
        TypeDescriptor popExpressionType = stack.popExpressionType();
        stack.pushExpressionType(TypeDescriptor.getNumberType());
        stack.pushExpressionType(popExpressionType);
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
        if(!stack.peekExpressionType().equals(TypeDescriptor.getBooleanType())){
            stack.popExpressionType();
            stack.pushExpressionType(TypeDescriptor.getBooleanType());
        }
        prepareTextValueConcatenation();
        //concatenates a text value and the converted boolean text.
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddTextIntegerStep step) {
        //Converts an integer to text, then concatenates a text value and the converted
        //integer text.
        if(!stack.peekExpressionType().equals(TypeDescriptor.getIntegerType())){
            stack.popExpressionType();
            stack.pushExpressionType(TypeDescriptor.getIntegerType());
        }
        prepareTextValueConcatenation();
        performTextConcatenation();
    }

    @Override
    public void visit(BinaryAddTextNumberStep step) {
        //Converts a number to text, then concatenates a text value and the converted
        //number text.
        if(!stack.peekExpressionType().equals(TypeDescriptor.getNumberType())){
            stack.popExpressionType();
            stack.pushExpressionType(TypeDescriptor.getNumberType());
        }
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
        performCustomEqualityComparison(true);
    }

    @Override
    public void visit(BinaryEqualsCustomNullStep step) {
        //determines if the reference is equal to another reference.
        performCustomEqualityComparison(true);
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
        //determines if the reference is equal to another reference.
        performCustomEqualityComparison(true);
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
        TypeDescriptor rhs = step.getRightType();
        String bytecodePath = QuorumConverter.convertStaticKeyToBytecodePath(rhs.getStaticKey());
        methodVisitor.visitTypeInsn(INSTANCEOF, QuorumConverter.convertClassNameToInterfaceName(bytecodePath));
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
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
        performCustomEqualityComparison(false);
    }

    @Override
    public void visit(BinaryNotEqualsCustomNullStep step) {
        performCustomEqualityComparison(false);
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
        performCustomEqualityComparison(false);
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
        if (step instanceof BooleanReverseAutoBoxStep && !stack.isExpressionTypeEmpty()) {
            TypeDescriptor currentType = stack.popExpressionType();

            if (currentType.getName().equals("Libraries.Language.Object")) {
                String autoBoxClassName = "quorum/Libraries/Language/Types/Boolean$Interface";
                String autoBoxMethodSignature = "()Z";

                // Create a new autoboxed object.
                methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(TypeDescriptor.getBooleanObjectType().getStaticKey())));

                // Call SetValue.
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                        "GetValue", autoBoxMethodSignature);
                stack.pushExpressionType(TypeDescriptor.getBooleanType());
            } else {
                if (step instanceof BooleanReverseAutoBoxToIntegerStep) {
                    getBooleanFromBooleanObject();

                    //pop the boolean off and replace the type with integer
                    stack.popExpressionType();
                    stack.pushExpressionType(TypeDescriptor.getIntegerType());

                } else if (step instanceof BooleanReverseAutoBoxToNumberStep) {
                    getBooleanFromBooleanObject();

                    //pop the boolean off and replace the type with integer
                    stack.popExpressionType();
                    stack.pushExpressionType(TypeDescriptor.getIntegerType());

                    castValueToValue(TypeDescriptor.getNumberType());

                } else if (step instanceof BooleanReverseAutoBoxToTextStep) {
                    getBooleanFromBooleanObject();
                    castValueToText();
                } else if (step instanceof BooleanReverseAutoBoxStep && step.hasModifiedReturn()) {
                    getBooleanFromBooleanObject();
                } else {
                    stack.pushExpressionType(currentType);
                }
            }
        }
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
            if (!stack.isExpressionTypeEmpty()) {
                secondType = stack.peekExpressionType();
            }
            methodVisitor.visitVarInsn(ALOAD, THIS);
        } else { //handle the case where we are pushing the reference on 
            //to the stack from 
            VariableParameterCommonDescriptor var = step.getParentObject();
            boolean field = var.isFieldVariable();
            if (field) {
                String key = currentClass.getStaticKey();
                String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
                String classNameSupplement = QuorumConverter.convertStaticKeyToBytecodePathTypeName(QuorumConverter.convertClassNameToInterfaceName(var.getType().getName()));
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, className, var.getName(), classNameSupplement);
            } else { //determine the local variable number and load it
                if (var instanceof ParameterDescriptor) {
                    //we are now calling a method on a variable (aka object). If it 
                    //is a parameter that we are calling on then load that parameter.
                    ParameterDescriptor varDescriptor = (ParameterDescriptor) var;
                    int number = stack.getParameterNumber(varDescriptor.getName());
                    methodVisitor.visitVarInsn(ALOAD, number);
                } else {
                    //Otherwise, load the variable from the mapped variable on the
                    //stack.
                    int number = var.getVariableNumber() - currentClass.getNumberOfVariables();
                    int mapped = stack.getMappedVariableNumber(number, var.getType());
                    methodVisitor.visitVarInsn(ALOAD, mapped);
                }
            }
        }
        step.setIsCalleeLoaded(true);
    }

    @Override
    public void visit(CallStep step) {
        boolean isParameter = false;
        boolean isCalledOnField = false;
        boolean isCalledOnInterface = false;
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
            if (callee.getParameters().isEmpty() && !step.isSoloMethodCall() && !step.isCalleeLoaded()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
            } else if (step.isSoloMethodCall() && !step.isCalleeLoaded()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                processExpressions();
            } else if (!step.isCalleeLoaded()) {
                processExpressions();
            }
        } else {
            VariableParameterCommonDescriptor var = step.getParentObject();

            //grab the variable from storage and check if it is an interface type
            TypeDescriptor varType = null;
            if (var != null) {
                varType = stack.getVariable(stack.getMappedVariableNumber(var.getVariableNumber() - currentClass.getNumberOfVariables(), var.getType()));
                if (varType != null) {
                    isCalledOnInterface = varType.isBytecodeInterface();
                }
            }

            //if this is a parent call step then treat it as such and load the parent
            //before calling ivokevirtual.
            if (step instanceof ParentCallStep) {
                ParentCallStep parentStep = (ParentCallStep) step;
                ClassDescriptor parent = (ClassDescriptor) parentStep.getMethodCallee().getParent();

                if (!step.isCalleeLoaded()) {
                    String key = currentClass.getStaticKey();
                    String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
                    methodVisitor.visitVarInsn(ALOAD, 0);
                    methodVisitor.visitFieldInsn(GETFIELD, className, QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey()), QuorumConverter.convertStaticKeyToBytecodePathTypeName(parent.getStaticKey()));
                }

                converted = QuorumConverter.convertTypeToJavaClassTypeEquivalent(parent.getType());
            } else {
                //if this is a standard call step then treat it as such.
                boolean field = var.isFieldVariable();
                if (isCalledOnInterface) {
                    converted = QuorumConverter.convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(var.getType().getStaticKey()));
                } else {
                    converted = QuorumConverter.convertTypeToJavaClassTypeEquivalent(var.getType());
                }

                if (field) {//if this is a call on a field object
                    isCalledOnField = true;

                    if (!step.isCalleeLoaded()) {
                        String key = currentClass.getStaticKey();
                        String className = QuorumConverter.convertStaticKeyToBytecodePath(key);
                        //String classNameSupplement = QuorumConverter.convertStaticKeyToBytecodePathTypeName(key);
                        String varTypeName = QuorumConverter.convertTypeToBytecodeString(var.getType());
                        methodVisitor.visitVarInsn(ALOAD, 0);
                        methodVisitor.visitFieldInsn(GETFIELD, className, var.getName(), varTypeName);
                    }

                    converted = QuorumConverter.convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(var.getType().getStaticKey()));
                } else { //determine the local variable number and load it

                    //we are now calling a method on a variable (aka object). If it 
                    //is a parameter that we are calling on then load that parameter.
                    if (var instanceof ParameterDescriptor) {
                        ParameterDescriptor varDescriptor = (ParameterDescriptor) var;

                        if (!step.isCalleeLoaded()) {
                            int number = stack.getParameterNumber(varDescriptor.getName());
                            methodVisitor.visitVarInsn(ALOAD, number);
                        }

                        isParameter = true;
                        converted = QuorumConverter.convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(var.getType().getStaticKey()));
                    } else if (!step.isCalleeLoaded()) {
                        //Otherwise, load the variable from the mapped variable on the
                        //stack.
                        int number = var.getVariableNumber() - currentClass.getNumberOfVariables();
                        int mapped = stack.getMappedVariableNumber(number, var.getType());
                        methodVisitor.visitVarInsn(ALOAD, mapped);
                    }
                }
            }
            if (!step.isCalleeLoaded()) {
                processExpressions();
            }
        }

        if (!isParameter && !isCalledOnField && !isCalledOnInterface) {
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, converted,
                    callee.getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        } else {
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, converted,
                    callee.getName(), QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        }

        // Is the method return type void? If not, push its return type onto the stack.
        if (!step.getMethodCallee().getReturnType().isVoid()) {
            if (step.isSoloMethodCall()) {
                methodVisitor.visitInsn(POP);
            } else {
                TypeDescriptor returnType = step.getMethodCallee().getReturnType();

                //if we have a return type that is a templated value we will need
                //to cast from object to the templated type that is defined by the 
                //variable the method is being called on.
                if (returnType.isTemplated()) {
                    boolean notFound = true;
                    String templateName = returnType.getTemplateName();
                    if (step.getParentObject() != null) {
                        Iterator<GenericDescriptor> templateTypes = step.getParentObject().getTemplateTypes();
                        while (templateTypes.hasNext() && notFound) {
                            TypeDescriptor next = templateTypes.next().getType();
                            //if the type is primitive we need to cast and get the value
                            //otherwise we will just cast
                            TypeDescriptor mappedTemplateType = step.getMethodCallee().getMappedTemplateType(next.getTemplateName());
                            if (mappedTemplateType != null && next.isPrimitiveType()) {
                                next.convertToClass();
                                String autoBoxClassName = QuorumConverter.convertStaticKeyToBytecodePathTypeName(QuorumConverter.convertClassNameToInterfaceName(next.getStaticKey()));

                                // Create a new autoboxed object.
                                methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(next.getStaticKey())));

                                // Call SetValue.
                                next.convertToPrimitive();
                                String autoBoxMethodSignature = "()" + QuorumConverter.convertTypeToBytecodeString(next);
                                methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                                        "GetValue", autoBoxMethodSignature);
                                stack.pushExpressionType(next);
                                notFound = false;
                            } else if (mappedTemplateType != null) {
                                // Create a new autoboxed object.
                                methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(next.getStaticKey())));
                                next.setBytecodeInterface(true);
                                stack.pushExpressionType(next);
                                notFound = false;
                            }
                        }
                    } else {
                        stack.pushExpressionType(returnType);
                    }

                } else {
                    stack.pushExpressionType(returnType);
                }
            }
        }
    }

    @Override
    public void visit(ConditionalJumpCheckStep step) {
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
        if (!step.isElseIf()) {
            stack.addIfBlock();
            label.setIfValue(stack.getCurrentNumberOfIfStatements());
        }
        stack.pushLabel(label);

    }

    @Override
    public void visit(ConditionalJumpLoopStep step) {
        LabelTypeEnum loopType = LabelTypeEnum.LOOP;
        //if this is a from step we need to process the extra root expression.
        if (step.getLoopType().equals(LoopType.FROM)) {
            loopType = LabelTypeEnum.FROM;
            //process the first move step of three in a from loop.
            //only repeat from has this extra move step (cuased by the hidden variable).
            processExpressions();
            methodVisitor.visitVarInsn(ISTORE, stack.pushCounterVariable());
        } else if (step.getLoopType().equals(LoopType.TIMES)) {
            loopType = LabelTypeEnum.TIMES;
        }

        //build the top label
        Label label0 = new Label();
        if (step.getLoopType().equals(LoopType.UNTIL) || step.getLoopType().equals(LoopType.WHILE)) {
            methodVisitor.visitLabel(label0);
        }

        //if we are dealing with an until loop we will need to negate our result using IFNE.
        int currentLoopBytecode = 0;
        if (step.getLoopType().equals(LoopType.UNTIL)) {
            currentLoopBytecode = IFNE;
        } else {
            currentLoopBytecode = IFEQ;
        }

        //push the label before the loop expression is processed.
        LabelStackValue temp = new LabelStackValue(loopType, currentLoopBytecode, label0);
        stack.pushLabel(temp);

        //process all the queued steps
        processSpecialLoopExpression(loopType);

        //generate the lables and visit as necessary
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(currentLoopBytecode, label1);

        //push the second label onto the stack so we can visit it later
        LabelStackValue temp2 = new LabelStackValue(loopType, currentLoopBytecode, label1);
        stack.pushLabel(temp2);

    }

    @Override
    public void visit(CreateObjectStep step) {
        boolean isField = false;
        ClassDescriptor clazz = step.getClazz();

        if (currentMethodExecution == null) {
            this.currentClassExecution.getTracker().clearQueue();
            isField = true;
            methodVisitor.visitVarInsn(ALOAD, 0);

        } else {
            this.currentMethodExecution.getTracker().clearQueue();
        }

        //methodVisitor.visitVarInsn(ALOAD, THIS);
        String converted = QuorumConverter.convertStaticKeyToBytecodePath(clazz.getStaticKey());
        methodVisitor.visitTypeInsn(NEW, converted);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "()V");
        VariableParameterCommonDescriptor variable = step.getVariable();
        variable.getType().setBytecodeInterface(isField);

        int variableNumber = variable.getVariableNumber() - currentClass.getNumberOfVariables();

        TypeDescriptor type = new TypeDescriptor();
        type.setName(variable.getType().getName());
        type.setBytecodeInterface(isField);

        stack.setVariable(variableNumber, type);
        int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, type);
        if (!isField && mappedVariableNumber != -1) {
            methodVisitor.visitVarInsn(ASTORE, mappedVariableNumber);
        } else if (isField && mappedVariableNumber != -1) {
            methodVisitor.visitFieldInsn(PUTFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClassExecution.getStaticKey()), variable.getName(), QuorumConverter.convertTypeToBytecodeString(type));
        }
        //stack.setVariable(variableNumber, value);
    }

    @Override
    public void visit(DataStackPopStep step) {
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
                    LabelStackValue newLabel = new LabelStackValue(LabelTypeEnum.IF, GOTO, label1);
                    newLabel.setIfValue(stack.getCurrentNumberOfIfStatements());
                    stack.pushLabel(newLabel);
                    methodVisitor.visitLabel(top);
                } else if (step.isLastIfScope()) {//jumping out of the if
                    Label first = label.getLabel();
                    methodVisitor.visitLabel(first);
                    stack.popLabel();

                    while (!stack.isEmptyLabel() && stack.peekLabel().getIfValue() == stack.getCurrentNumberOfIfStatements()) {
                        first = stack.peekLabel().getLabel();
                        methodVisitor.visitLabel(first);
                        stack.popLabel();
                    }
                    stack.endIfBlock();
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
            } else if (!step.getBlockTag().equals("always") && (label.getLabelType().equals(LabelTypeEnum.CHECK) || label.getLabelType().equals(LabelTypeEnum.DETECT))) {//end of the loop will mark the goto and visit the end label.
                stack.popLabel();

                Label label1 = label.getLabel();
                if (label1 != null) {
                    methodVisitor.visitLabel(label1);
                }
            }
        }

    }

    @Override
    public void visit(InputStep step) {
        // Insert the appropriate steps for this statement.
        processExpressions();
        TypeDescriptor typeToPrint = stack.popExpressionType();

        // Insert the appropriate say opcode for the type.
        methodVisitor.visitMethodInsn(INVOKESTATIC, "plugins/quorum/Libraries/System/Console", "StaticInput",
                "(" + QuorumConverter.convertTypeToBytecodeString(typeToPrint) + ")Ljava/lang/String;");
        stack.pushExpressionType(TypeDescriptor.getTextType());
    }

    @Override
    public void visit(IntegerAutoBoxStep step) {
        if (step instanceof IntegerReverseAutoBoxStep && !stack.isExpressionTypeEmpty()) {
            TypeDescriptor currentType = stack.popExpressionType();

            if (currentType.getName().equals("Libraries.Language.Object")) {
                String autoBoxClassName = "quorum/Libraries/Language/Types/Integer$Interface";
                String autoBoxMethodSignature = "()I";

                // Create a new autoboxed object.
                methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(TypeDescriptor.getIntegerObjectType().getStaticKey())));

                // Call SetValue.
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                        "GetValue", autoBoxMethodSignature);
                stack.pushExpressionType(TypeDescriptor.getIntegerType());
            } else {//reverse autobox subclasses
                if (step instanceof IntegerReverseAutoBoxToTextStep) {
                    getIntegerFromIntegerObject();
                    castValueToText();
                } else if (step instanceof IntegerReverseAutoBoxToNumberStep) {
                    getIntegerFromIntegerObject();
                    castValueToValue(TypeDescriptor.getNumberType());
                } else if (step instanceof IntegerReverseAutoBoxToBooleanStep) {
                    getIntegerFromIntegerObject();
                    //pop integer off stack and change to boolean
                    stack.popExpressionType();
                    stack.pushExpressionType(TypeDescriptor.getBooleanType());
                } else if (step instanceof IntegerReverseAutoBoxStep && step.hasModifiedReturn()) {
                    getIntegerFromIntegerObject();
                } else {
                    stack.pushExpressionType(currentType);
                }
            }
        }
    }

    @Override
    public void visit(JumpStep step) {
        //if the stack has a label
        if (step.getType() != null && !stack.isEmptyLabel() && step.getType().equals(JumpType.LOOP)) {
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
            } else if (label.getLabelType().equals(LabelTypeEnum.LOOP)) {//if the label is for the general loop just pop off the remaining label.
                //methodVisitor.visitLabel(label.getLabel());
                stack.popLabel();
            }
        }
    }

    @Override
    public void visit(MeVariableMoveStep step) {
        TypeDescriptor variable = null;

        //if we have a variable descriptor
        if (step.getValue() instanceof VariableDescriptor) {
            //if the variable is initialized load the variable and visit the field
            VariableDescriptor varDescriptor = (VariableDescriptor) step.getValue();
            if (varDescriptor != null && varDescriptor.isInitializedClassVariable()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                //methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()), QuorumConverter.convertParentStaticKeyToValidName(step.getLocatedInClass().getStaticKey()), QuorumConverter.convertStaticKeyToBytecodePathTypeName(step.getLocatedInClass().getStaticKey()));
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                        varDescriptor.getName(), QuorumConverter.convertTypeToBytecodeString(varDescriptor.getType()));
                variable = varDescriptor.getType();
            }

        }

        //push the type of the variable on the top of the stack
        stack.pushExpressionType(variable);
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
    }

    @Override
    public void visit(NullExecutionStep step) {
    }

    @Override
    public void visit(NumberAutoBoxStep step) {
        if (step instanceof NumberReverseAutoBoxStep && !stack.isExpressionTypeEmpty()) {
            TypeDescriptor currentType = stack.popExpressionType();

            if (currentType.getName().equals("Libraries.Language.Object")) {
                String autoBoxClassName = "quorum/Libraries/Language/Types/Number$Interface";
                String autoBoxMethodSignature = "()D";

                // Create a new autoboxed object.
                methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(TypeDescriptor.getNumberObjectType().getStaticKey())));

                // Call SetValue.
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                        "GetValue", autoBoxMethodSignature);
                stack.pushExpressionType(TypeDescriptor.getNumberType());
            } else {//reverse autobox subclasses
                if (step instanceof NumberReverseAutoBoxToTextStep) {
                    getNumberFromNumberObject();
                    castValueToText();
                } else if (step instanceof NumberReverseAutoBoxToIntegerStep) {
                    getNumberFromNumberObject();
                    castValueToValue(TypeDescriptor.getIntegerType());
                } else if (step instanceof NumberReverseAutoBoxToBooleanStep) {
                    getNumberFromNumberObject();
                    castValueToValue(TypeDescriptor.getIntegerType());
                    //pop integer off stack and change to boolean
                    stack.popExpressionType();
                    stack.pushExpressionType(TypeDescriptor.getBooleanType());
                } else if (step instanceof NumberReverseAutoBoxStep && step.hasModifiedReturn()) {
                    getNumberFromNumberObject();
                } else {
                    stack.pushExpressionType(currentType);
                }
            }
        }
    }

    @Override
    public void visit(ObjectCastStep step) {
        stack.popExpressionType();
        //make a copy of the type descriptor and set its bytecodeInterface
        //flag to true. The point here is that the type must be converted to
        //an interface, and as such, we have to let the bytecode stack know
        //about that
        //We are also making a copy, so this never gets sent through to the 
        //the interpreter accidentally.
        TypeDescriptor convertedType = new TypeDescriptor(step.getConvertedType());
        convertedType.setBytecodeInterface(true);
        methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(convertedType.getStaticKey())));
        stack.pushExpressionType(convertedType);
    }

    @Override
    public void visit(ObjectInitParentStep step) {
    }

    @Override
    public void visit(ObjectInitPopStep step) {
    }

    @Override
    public void visit(ParentVariableMoveStep step) {
        TypeDescriptor variable = null;

        //if we have a variable descriptor
        if (step.getValue() instanceof VariableDescriptor) {
            //if the variable is initialized load the variable and visit the field
            VariableDescriptor varDescriptor = (VariableDescriptor) step.getValue();
            if (varDescriptor != null && varDescriptor.isInitializedClassVariable()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()), 
                        QuorumConverter.convertParentStaticKeyToValidName(step.getLocatedInClass().getStaticKey()), QuorumConverter.convertTypeToBytecodeString(step.getLocatedInClass().getType()));
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(step.getLocatedInClass().getStaticKey()),
                        varDescriptor.getName(), QuorumConverter.convertTypeToBytecodeString(varDescriptor.getType()));
                variable = varDescriptor.getType();
            } else {//otherwise get the variable number and push the new variable onto the bytecode stack
                int variableNumber = step.getValue().getVariableNumber() - currentClass.getNumberOfVariables();
                int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, step.getValue().getType());
                variable = stack.getVariable(mappedVariableNumber);
                pushVariable(step.getValue().getType(), variableNumber);
            }
        }

        //push the type of the variable on the top of the stack
        stack.pushExpressionType(variable);
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
    }

    @Override
    public void visit(ReturnStep step) {
        // partial - handles only primitive types and text
        TypeDescriptor returnType = step.getMethodDescriptor().getReturnType();
        processExpressions();

        if (returnType.isVoid()) {
            methodVisitor.visitInsn(RETURN);
        } else {
            //processExpressions();
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
        RuntimeError runtimeError = step.getRuntimeError();


        //create the java exception 
        methodVisitor.visitTypeInsn(NEW, "java/lang/Throwable");
        methodVisitor.visitInsn(DUP);
        if (runtimeError != null) {
            methodVisitor.visitLdcInsn(runtimeError.getErrorMessage());
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Throwable", "<init>", "(Ljava/lang/String;)V");
        } else {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Throwable", "<init>", "()V");
        }


        //throw the exception
        methodVisitor.visitInsn(ATHROW);
    }

    @Override
    public void visit(SpeakStep step) {
        // Insert the appropriate steps for this statement.
        processExpressions();
        TypeDescriptor typeToPrint = stack.popExpressionType();

        // Insert the appropriate say opcode for the type.
        methodVisitor.visitMethodInsn(INVOKESTATIC, "plugins/quorum/Libraries/Sound/Speech", "StaticSay",
                "(" + QuorumConverter.convertTypeToBytecodeString(typeToPrint) + ")V");
    }

    @Override
    public void visit(TextAutoBoxStep step) {
        if (step instanceof TextReverseAutoBoxStep  && !stack.isExpressionTypeEmpty()) {
            TypeDescriptor currentType = stack.popExpressionType();

            if (currentType.getName().equals("Libraries.Language.Object")) {
                String autoBoxClassName = "quorum/Libraries/Language/Types/Text$Interface";
                String autoBoxMethodSignature = "()Ljava/lang/String;";

                // Create a new autoboxed object.
                methodVisitor.visitTypeInsn(CHECKCAST, QuorumConverter.convertStaticKeyToBytecodePath(QuorumConverter.convertClassNameToInterfaceName(TypeDescriptor.getTextObjectType().getStaticKey())));

                // Call SetValue.
                methodVisitor.visitMethodInsn(INVOKEINTERFACE, autoBoxClassName,
                        "GetValue", autoBoxMethodSignature);
                stack.pushExpressionType(TypeDescriptor.getTextType());
            } else {
                if (step instanceof TextReverseAutoBoxToIntegerStep) {
                    getTextFromTextObject();
                    castTextToValue(TypeDescriptor.getIntegerType());
                } else if (step instanceof TextReverseAutoBoxToNumberStep) {
                    getTextFromTextObject();
                    castTextToValue(TypeDescriptor.getNumberType());
                } else if (step instanceof TextReverseAutoBoxToBooleanStep) {
                    getTextFromTextObject();
                    castTextToValue(TypeDescriptor.getBooleanType());
                } else if (step instanceof TextReverseAutoBoxStep && step.hasModifiedReturn()) {
                    getTextFromTextObject();
                } else {
                    stack.pushExpressionType(currentType);
                }
            }
        }
    }

    @Override
    public void visit(ThisMoveStep step) {
        methodVisitor.visitVarInsn(ALOAD, 0);

        //push the type of the variable on the top of the stack
        stack.pushExpressionType(currentClassExecution.getClassDescriptor().getType());
    }

    @Override
    public void visit(UnaryBooleanBooleanCastStep step) {
    }

    @Override
    public void visit(UnaryBooleanIntegerCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getIntegerType());
        castValueToValue(TypeDescriptor.getBooleanType());
    }

    @Override
    public void visit(UnaryBooleanNumberCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getNumberType());
        castValueToValue(TypeDescriptor.getBooleanType());
    }

    @Override
    public void visit(UnaryBooleanTextCastStep step) {
        castTextToValue(TypeDescriptor.getBooleanType());
    }

    @Override
    public void visit(UnaryIntegerBooleanCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
        castValueToValue(TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(UnaryIntegerIntegerCastStep step) {
    }

    @Override
    public void visit(UnaryIntegerNumberCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getNumberType());
        castValueToValue(TypeDescriptor.getIntegerType());
    }

    @Override
    public void visit(UnaryIntegerTextCastStep step) {
        castTextToValue(TypeDescriptor.getIntegerType());
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
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
        castValueToValue(TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(UnaryNumberIntegerCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getIntegerType());        
        castValueToValue(TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(UnaryNumberNumberCastStep step) {
    }

    @Override
    public void visit(UnaryNumberTextCastStep step) {
        castTextToValue(TypeDescriptor.getNumberType());
    }

    @Override
    public void visit(UnaryTextBooleanCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getBooleanType());
        castValueToText();
    }

    @Override
    public void visit(UnaryTextIntegerCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getIntegerType());
        castValueToText();
    }

    @Override
    public void visit(UnaryTextNumberCastStep step) {
        stack.pushExpressionType(TypeDescriptor.getNumberType());
        castValueToText();
    }

    @Override
    public void visit(UnaryTextTextCastStep step) {
    }

    @Override
    public void visit(VariableInObjectMoveStep step) {
        //get the variable from the step and store it
        VariableParameterCommonDescriptor variable = step.getObj();
        boolean isFieldInObject = false;
        if (variable.isFieldVariable()) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                    variable.getName(), QuorumConverter.convertTypeToBytecodeString(variable.getType()));
            isFieldInObject = true;
        } else if (step.getObj() instanceof ParameterDescriptor) {
            ParameterDescriptor param = (ParameterDescriptor) step.getObj();
            int variableNumber = stack.getParameterNumber(param.getName());
            param.getType().setBytecodeInterface(true);
            methodVisitor.visitVarInsn(QuorumConverter.getLoadOpcode(param.getType()), variableNumber);
            isFieldInObject = true;
        } else {
            pushVariable(variable.getType(), variable.getVariableNumber() - currentClass.getNumberOfVariables());
        }
        
        

        //visit the field instruction
        String name = step.getVariableName();
        TypeDescriptor type = step.getVariableType();

        if (!type.isPrimitiveType()) {
            type.setBytecodeInterface(true);
        }
        
        if(step.getParent() != null){
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, QuorumConverter.convertStaticKeyToBytecodePath(variable.getType().getStaticKey() + "$Interface"),
                    QuorumConverter.generateGetterNameFromSubField(step.getParent().getType(), name), QuorumConverter.generateGetterSignatureFromSubField(type));
        }else{
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, QuorumConverter.convertStaticKeyToBytecodePath(variable.getType().getStaticKey() + "$Interface"),
                    QuorumConverter.generateGetterNameFromSubField(variable.getType(), name), QuorumConverter.generateGetterSignatureFromSubField(type));
        }

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
            if (varDescriptor != null && varDescriptor.isFieldVariable()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                        varDescriptor.getName(), QuorumConverter.convertTypeToBytecodeString(varDescriptor.getType()));
                variable = varDescriptor.getType();
            } else {//otherwise get the variable number and push the new variable onto the bytecode stack
                int variableNumber = step.getValue().getVariableNumber() - currentClass.getNumberOfVariables();
                int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber, step.getValue().getType());
                variable = stack.getVariable(mappedVariableNumber);
                pushVariable(step.getValue().getType(), variableNumber);
            }
        } else if (step.getValue() instanceof ParameterDescriptor) {
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
