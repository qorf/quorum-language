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
import org.quorum.steps.AlertStep;
import org.quorum.steps.AlwaysEndStep;
import org.quorum.steps.AssignIntegerObjectToNumberAutoBoxLocalStep;
import org.quorum.steps.AssignIntegerObjectToNumberAutoBoxStep;
import org.quorum.steps.AssignObjectAutoBoxLocalStep;
import org.quorum.steps.AssignObjectAutoBoxStep;
import org.quorum.steps.AssignmentBooleanLocalStep;
import org.quorum.steps.AssignmentBooleanStep;
import org.quorum.steps.AssignmentCustomLocalStep;
import org.quorum.steps.AssignmentCustomStep;
import org.quorum.steps.AssignmentIntegerLocalStep;
import org.quorum.steps.AssignmentIntegerStep;
import org.quorum.steps.AssignmentNumberIntegerLocalStep;
import org.quorum.steps.AssignmentNumberIntegerStep;
import org.quorum.steps.AssignmentNumberLocalStep;
import org.quorum.steps.AssignmentNumberStep;
import org.quorum.steps.AssignmentStep;
import org.quorum.steps.AssignmentTextLocalStep;
import org.quorum.steps.AssignmentTextStep;
import org.quorum.steps.AutoBoxCreateStep;
import org.quorum.steps.BeginCheckScopeStep;
import org.quorum.steps.BeginScopeStep;
import org.quorum.steps.BinaryAddBooleanTextStep;
import org.quorum.steps.BinaryAddIntegerNumberStep;
import org.quorum.steps.BinaryAddIntegerTextStep;
import org.quorum.steps.BinaryAddNumberIntegerStep;
import org.quorum.steps.BinaryAddNumberStep;
import org.quorum.steps.BinaryAddNumberTextStep;
import org.quorum.steps.BinaryAddStep;
import org.quorum.steps.BinaryAddTextBooleanStep;
import org.quorum.steps.BinaryAddTextIntegerStep;
import org.quorum.steps.BinaryAddTextNumberStep;
import org.quorum.steps.BinaryAndStep;
import org.quorum.steps.BinaryConcatenateStep;
import org.quorum.steps.BinaryDivideIntegerNumberStep;
import org.quorum.steps.BinaryDivideNumberIntegerStep;
import org.quorum.steps.BinaryDivideNumberStep;
import org.quorum.steps.BinaryDivideStep;
import org.quorum.steps.BinaryEqualsBooleanStep;
import org.quorum.steps.BinaryEqualsCustomCustomStep;
import org.quorum.steps.BinaryEqualsCustomNullStep;
import org.quorum.steps.BinaryEqualsIntegerNumberStep;
import org.quorum.steps.BinaryEqualsNullCustomStep;
import org.quorum.steps.BinaryEqualsNumberIntegerStep;
import org.quorum.steps.BinaryEqualsNumberStep;
import org.quorum.steps.BinaryEqualsStep;
import org.quorum.steps.BinaryEqualsStringStep;
import org.quorum.steps.BinaryGreaterEqualsIntegerNumberStep;
import org.quorum.steps.BinaryGreaterEqualsNumberIntegerStep;
import org.quorum.steps.BinaryGreaterEqualsNumberStep;
import org.quorum.steps.BinaryGreaterEqualsStep;
import org.quorum.steps.BinaryGreaterEqualsStringStep;
import org.quorum.steps.BinaryGreaterThanIntegerNumberStep;
import org.quorum.steps.BinaryGreaterThanNumberIntegerStep;
import org.quorum.steps.BinaryGreaterThanNumberStep;
import org.quorum.steps.BinaryGreaterThanStep;
import org.quorum.steps.BinaryGreaterThanStringStep;
import org.quorum.steps.BinaryIsACustomCustomStep;
import org.quorum.steps.BinaryLessEqualsIntegerNumberStep;
import org.quorum.steps.BinaryLessEqualsNumberIntegerStep;
import org.quorum.steps.BinaryLessEqualsNumberStep;
import org.quorum.steps.BinaryLessEqualsStep;
import org.quorum.steps.BinaryLessEqualsStringStep;
import org.quorum.steps.BinaryLessThanIntegerNumberStep;
import org.quorum.steps.BinaryLessThanNumberIntegerStep;
import org.quorum.steps.BinaryLessThanNumberStep;
import org.quorum.steps.BinaryLessThanStep;
import org.quorum.steps.BinaryLessThanStringStep;
import org.quorum.steps.BinaryModIntegerNumberStep;
import org.quorum.steps.BinaryModNumberIntegerStep;
import org.quorum.steps.BinaryModNumberStep;
import org.quorum.steps.BinaryModStep;
import org.quorum.steps.BinaryMultiplyIntegerNumberStep;
import org.quorum.steps.BinaryMultiplyNumberIntegerStep;
import org.quorum.steps.BinaryMultiplyNumberStep;
import org.quorum.steps.BinaryMultiplyStep;
import org.quorum.steps.BinaryNotEqualsBooleanStep;
import org.quorum.steps.BinaryNotEqualsCustomCustomStep;
import org.quorum.steps.BinaryNotEqualsCustomNullStep;
import org.quorum.steps.BinaryNotEqualsIntegerNumberStep;
import org.quorum.steps.BinaryNotEqualsNullCustomStep;
import org.quorum.steps.BinaryNotEqualsNumberIntegerStep;
import org.quorum.steps.BinaryNotEqualsNumberStep;
import org.quorum.steps.BinaryNotEqualsStep;
import org.quorum.steps.BinaryNotEqualsStringStep;
import org.quorum.steps.BinaryOrStep;
import org.quorum.steps.BinarySubtractIntegerNumberStep;
import org.quorum.steps.BinarySubtractNumberIntegerStep;
import org.quorum.steps.BinarySubtractNumberStep;
import org.quorum.steps.BinarySubtractStep;
import org.quorum.steps.BooleanAutoBoxStep;
import org.quorum.steps.CallStep;
import org.quorum.steps.ClassExecution;
import org.quorum.steps.ConditionalJumpCheckStep;
import org.quorum.steps.ConditionalJumpIfStep;
import org.quorum.steps.ConditionalJumpLoopStep;
import org.quorum.steps.CreateObjectStep;
import org.quorum.steps.DataStackPopStep;
import org.quorum.steps.EndScopeStep;
import org.quorum.steps.InputStep;
import org.quorum.steps.IntegerAutoBoxStep;
import org.quorum.steps.JumpStep;
import org.quorum.steps.LinearExecution;
import org.quorum.steps.LoopType;
import org.quorum.steps.MeVariableMoveStep;
import org.quorum.steps.MethodExecution;
import org.quorum.steps.MoveRegistersStep;
import org.quorum.steps.MoveStep;
import org.quorum.steps.NullIntermediateStep;
import org.quorum.steps.NumberAutoBoxStep;
import org.quorum.steps.ObjectCastStep;
import org.quorum.steps.ObjectInitParentStep;
import org.quorum.steps.ObjectInitPopStep;
import org.quorum.steps.ParentVariableMoveStep;
import org.quorum.steps.PrintStep;
import org.quorum.steps.PushStep;
import org.quorum.steps.ReturnStep;
import org.quorum.steps.SimpleAlertStep;
import org.quorum.steps.SpeakStep;
import org.quorum.steps.TextAutoBoxStep;
import org.quorum.steps.ThisMoveStep;
import org.quorum.steps.UnaryBooleanBooleanCastStep;
import org.quorum.steps.UnaryBooleanIntegerCastStep;
import org.quorum.steps.UnaryBooleanNumberCastStep;
import org.quorum.steps.UnaryBooleanTextCastStep;
import org.quorum.steps.UnaryIntegerBooleanCastStep;
import org.quorum.steps.UnaryIntegerIntegerCastStep;
import org.quorum.steps.UnaryIntegerNumberCastStep;
import org.quorum.steps.UnaryIntegerTextCastStep;
import org.quorum.steps.UnaryNotStep;
import org.quorum.steps.UnaryNumberBooleanCastStep;
import org.quorum.steps.UnaryNumberIntegerCastStep;
import org.quorum.steps.UnaryNumberNumberCastStep;
import org.quorum.steps.UnaryNumberTextCastStep;
import org.quorum.steps.UnaryTextBooleanCastStep;
import org.quorum.steps.UnaryTextIntegerCastStep;
import org.quorum.steps.UnaryTextNumberCastStep;
import org.quorum.steps.UnaryTextTextCastStep;
import org.quorum.steps.VariableInObjectMoveStep;
import org.quorum.steps.VariableMoveStep;
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
    private BytecodeStackValue returnValue = null;
    private int fieldSize = 1;

    public QuorumJavaBytecodeStepVisitor() {
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
//                !".Matt".equals(staticKey) && !".Melissa".equals(staticKey) && !".Main".equals(staticKey)
//                && !"Libraries.Language.Object".equals(staticKey)) {
//            return;
//        }
//
        if (!".Stefik".equals(staticKey) //&& !"Libraries.Sound.Speech".equals(staticKey)
                //&& !"Libraries.Language.Object".equals(staticKey) && !"Libraries.Language.Support.CompareResult".equals(staticKey)
                && !".Matt".equals(staticKey) && !".Melissa".equals(staticKey) && !".Main".equals(staticKey)
                && !".Print".equals(staticKey)) {
            return;
        }
        String name = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);
        processedClazzName = name;


        //this will have to be modified for inheritance conversion
        classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null);
        //first weave in the parents of the class and initialize them
        /*
         * 
        //Add parents as extra behind the scenes fields.
        Iterator<ClassDescriptor> parents = currentClass.getFlattenedListOfParents();
        while (parents.hasNext()) {
        ClassDescriptor parent = parents.next();
        String parentKey = parent.getStaticKey();
        String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
        String converted = QuorumConverter.convertStaticKeyToBytecodePathTypeName(parentKey);
        //            fieldVisitor = classWriter.visitField(ACC_PUBLIC, parentName, converted, null, null);
        //            fieldVisitor.visitEnd();
        //            fieldSize += 2;
        }*/


        //Do field visiting for the class.
        Iterator<VariableDescriptor> classVariables = clazz.getClassDescriptor().getClassVariables();
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
        //computeConstructor(false);

        /*
        //now dump all of the parent methods that 
        //are not in the base class out as wrapper functions.
        parents = currentClass.getFlattenedListOfParents();
        while (parents.hasNext()) {
        ClassDescriptor parent = parents.next();
        //computeParentMethods(parent);
        }
         */

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
            Iterator<ClassDescriptor> parents = currentClass.getFlattenedListOfParents();
            while (parents.hasNext()) {
                ClassDescriptor parent = parents.next();
                String parentKey = parent.getStaticKey();
                String parentName = QuorumConverter.convertParentStaticKeyToValidName(parent.getStaticKey());
                String converted = QuorumConverter.convertStaticKeyToBytecodePath(parentKey);
//                methodVisitor.visitVarInsn(ALOAD, THIS);
//                methodVisitor.visitTypeInsn(NEW, converted);
//                methodVisitor.visitInsn(DUP);
//                //push a boolean onto the stack
//                methodVisitor.visitInsn(ICONST_0);
//                fieldSize++;
//                methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "(Z)V");
//                methodVisitor.visitFieldInsn(PUTFIELD, name, parentName, QuorumConverter.convertStaticKeyToBytecodePathTypeName(parentKey));
            }
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
            int size = BytecodeStackValue.getSize(param.getType());
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
            int size = BytecodeStackValue.getSize(param.getType());
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
            } else {

                //queue up the ending opcodes but still visit them (assignment, print, etc.)
                if (opcodeType != null) {
                    tracker.addToQueue(i);
                }

                //otherwise process each step and let each step decide if it has
                //queued up steps to visit and in which order they are processed.
                ExecutionStep step = steps.get(i);
                step.visit(this);
            }
        }
        //this should be filled out with the number of local variables
        int numberVariables = stack.getMaxVariablesSize();
        int stackSize = stack.getMaxSize();
        //the stack size should also change depending on the 
        //expressions that need to be processed.
        methodVisitor.visitMaxs(stackSize + 1, numberVariables + currentClass.getNumberFlatParents() + 1);
        methodVisitor.visitEnd();

    }

    public void end() {
        int a = 5;
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

    private void addParametersAsVariables(MethodDescriptor method) {
        for (int i = 1; i <= method.getParameters().size(); i++) {
            BytecodeStackValue parameterVariable = new BytecodeStackValue();
            TypeDescriptor constantType = new TypeDescriptor();
            ParameterDescriptor parameter = method.getParameters().get(i - 1);

            constantType.setName(parameter.getType().getName());
            parameterVariable.setType(constantType);
            parameterVariable.setName(parameter.getName());

            parameterVariable.setAsVariable(i);
            stack.setVariable(i, parameterVariable);
        }
    }

    /**
     * This method takes a stack value, computes the appropriate
     * op-code to be placed in the code block of the method, and then
     * visits this method.
     * 
     * @param value 
     */
    public void addToMethodVisit(BytecodeStackValue value) {
        if (value.isConstant()) {
            if (value.getType().isInteger()) {
                if ((value.getResult().integer >= -1 && value.getResult().integer <= 5)) {
                    switch (value.getResult().integer) {
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
                } else if ((value.getResult().integer >= -128 && value.getResult().integer <= 127)) {
                    methodVisitor.visitIntInsn(BIPUSH, value.getResult().integer);
                } else if ((value.getResult().integer >= -32768 && value.getResult().integer <= 32767)) {
                    methodVisitor.visitIntInsn(SIPUSH, value.getResult().integer);
                } else {
                    methodVisitor.visitLdcInsn(value.getResult().integer);
                }
            } else if (value.getType().isBoolean()) {
                if (value.getResult().boolean_value) {
                    methodVisitor.visitInsn(ICONST_1);
                } else {
                    methodVisitor.visitInsn(ICONST_0);
                }
            } else {
                methodVisitor.visitLdcInsn(value.getValue());
            }
        } else {
            methodVisitor.visitVarInsn(value.getLoadOpcode(), stack.getMappedVariableNumber(value.getVarNumber()));
        }
    }

    /**
     * Performs the necessary bytecode operations for assigning a field variable (non local).
     * 
     * @param variable
     * @param subVariableName
     * @param subVariableType 
     */
    private void performFieldAssignment(VariableParameterCommonDescriptor variable, String subVariableName, TypeDescriptor subVariableType) {
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
        processExpressions();
        methodVisitor.visitFieldInsn(PUTFIELD, fieldParent, variableName, QuorumConverter.convertTypeToBytecodeString(variableType));
    }

    /**
     * Performs the necessary bytecode operations for assigning a value to a local variable.
     * @param value
     * @param step 
     */
    private void performLocalAssignment(BytecodeStackValue value, AssignmentStep step) {
        int variableNumber = step.getVariable().getVariableNumber() - currentClass.getNumberOfVariables();
        int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber);
        value.setAsVariable(variableNumber);
        value.setAsReturnValue(false);
        //processExpressions();
        methodVisitor.visitVarInsn(value.getStoreOpcode(), mappedVariableNumber);
        stack.setVariable(variableNumber, value);
    }

    /**
     * This helper method performs an assignment into either a local variable or a field variable.
     * 
     * @param value The bytecode stack value needed by the assignment.
     * @param step The Assignment step being performed.
     * @param isDefined True if the variable being assigned a value was already defined.
     */
    private void performAssignment(BytecodeStackValue value, AssignmentStep step, boolean isDefined) {
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
                performFieldAssignment(step.getVariable(), step.getSubVariableName(), value.getType());
            } else {
                performLocalAssignment(value, step);
            }
        } else {
            if (isField) {
                performFieldAssignment(step.getVariable(), step.getSubVariableName(), value.getType());
            } else {
                performLocalAssignment(value, step);

                // It's not a field, so we need to tell the system that we are
                // adding a new value, which increases the "field size."
                if (value.getType().isNumber()) {
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
        BytecodeStackValue operand = stack.popConstant();

        operand.getType().setName(TypeDescriptor.NUMBER);
        stack.pushConstant(operand);
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
        BytecodeStackValue numberVal = stack.popConstant();
        BytecodeStackValue integerVal = stack.popConstant();

        swapOperandStackValues(numberVal.getType(), integerVal.getType());

        methodVisitor.visitInsn(I2D);

        if (orderMatters) {
            swapOperandStackValues(numberVal.getType(), numberVal.getType());
        }

        integerVal.getType().setName(TypeDescriptor.NUMBER);
        stack.pushConstant(numberVal);
        stack.pushConstant(integerVal);
    }

    /**
     * Helper method: prepares a text value to be concatenated.
     */
    private void prepareTextValueConcatenation() {
        //get the operand and it's type
        BytecodeStackValue operand = stack.popConstant();
        TypeDescriptor operandType = operand.getType();

        if (operandType.isBoolean()) {//if the operand is a boolean
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "toString", "(Z)Ljava/lang/String;");
        } else if (operandType.isInteger()) {//if the operand is an integer
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;");
        } else if (operandType.isNumber()) {//if the operand is a number
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "toString", "(D)Ljava/lang/String;");
        }

        //set the new type to text and push the constant
        operandType.setName(TypeDescriptor.TEXT);
        stack.pushConstant(operand);
    }

    /**
     * helper method: swap values and prepare for concatenation.
     */
    private void prepareValueTextConcatenation() {
        BytecodeStackValue text = stack.popConstant();
        BytecodeStackValue value = stack.getConstantFromTop(0);

        swapOperandStackValues(text.getType(), value.getType());

        prepareTextValueConcatenation();

        swapOperandStackValues(text.getType(), value.getType());
        stack.pushConstant(text);
    }

    /**
     * Perform concatenation.
     */
    private void performTextConcatenation() {
        stack.popConstant();
        stack.popConstant();
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;");

        // Push a text constant onto the stack.
        BytecodeStackValue bytecodeValue = new BytecodeStackValue();
        bytecodeValue.setType(TypeDescriptor.getTextType());
        bytecodeValue.setResult(Result.getDefaultResult(TypeDescriptor.getIntegerType()));
        stack.pushConstant(bytecodeValue);
    }

    private void performEqualityComparison(boolean testEqual) {
        //get two values off the stack
        BytecodeStackValue operand = stack.popConstant();
        stack.popConstant();

        if (operand.getType().isNumber()) {
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

        } else if (operand.getType().isText()) {
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
        BytecodeStackValue result = new BytecodeStackValue();
        result.setType(TypeDescriptor.getBooleanType());
        stack.pushConstant(result);
    }

    private void performInequalityComparison(boolean testEqual, boolean lessThan) {
        //get two values off the stack
        BytecodeStackValue operand = stack.popConstant();
        stack.popConstant();

        if (operand.getType().isNumber()) {
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

        //push a boolean result onto the stack
        BytecodeStackValue result = new BytecodeStackValue();
        result.setType(TypeDescriptor.getBooleanType());
        stack.pushConstant(result);
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
//        stack.popConstant();
//        stack.popConstant();

        // Insert the appropriate opcode.
        methodVisitor.visitInsn(bytecodeOpcode);

        // Push a constant of type returnType onto the stack.
        BytecodeStackValue bytecodeValue = new BytecodeStackValue();
        bytecodeValue.setType(returnType);
        bytecodeValue.setResult(Result.getDefaultResult(returnType));
        stack.pushConstant(bytecodeValue);
    }

    private void performComparison(int oppositeBytecodeOpcode) {
//        Label c0 = new Label();
//        methodVisitor.visitJumpInsn(oppositeBytecodeOpcode, c0);
//        methodVisitor.visitInsn(ICONST_1);
//        Label c1 = new Label();
//        methodVisitor.visitJumpInsn(GOTO, c1);
//        methodVisitor.visitLabel(c0);
//        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//        methodVisitor.visitInsn(ICONST_0);
//        methodVisitor.visitLabel(c1);
//        methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{Opcodes.INTEGER});
    }

    private void swapOperandStackValues(TypeDescriptor topType, TypeDescriptor secondType) {
        if (!topType.isNumber() && !secondType.isNumber()) {
            methodVisitor.visitInsn(SWAP);
        } else if (topType.isNumber() && secondType.isNumber()) {
            stack.implicitStackIncrease(topType);
            methodVisitor.visitInsn(DUP2_X2);
            methodVisitor.visitInsn(POP2);
        } else if (topType.isNumber()) {
            stack.implicitStackIncrease(topType);
            methodVisitor.visitInsn(DUP2_X1);
            methodVisitor.visitInsn(POP2);
        } else {
            stack.implicitStackIncrease(topType);
            methodVisitor.visitInsn(DUP_X2);
            methodVisitor.visitInsn(POP);
        }
    }

    private void castValueToText() {
        BytecodeStackValue value = stack.popConstant();

        methodVisitor.visitMethodInsn(INVOKESTATIC, QuorumConverter.convertTypeToJavaClassTypeEquivalent(value.getType()),
                "toString", "(" + value.getByteCodeTypeDescriptor() + ")Ljava/lang/String;");
        value.getType().setName(TypeDescriptor.TEXT);
        stack.pushConstant(value);
    }

    private void castTextToValue(TypeDescriptor returnValueType) {
        BytecodeStackValue value = stack.popConstant();

        String type = QuorumConverter.convertTypeToJavaTypeEquivalent(returnValueType);
        type = type.substring(0, 1).toUpperCase() + type.substring(1);


        methodVisitor.visitMethodInsn(INVOKESTATIC, QuorumConverter.convertTypeToJavaClassTypeEquivalent(returnValueType),
                "parse" + type, "(Ljava/lang/String;)" + QuorumConverter.convertTypeToBytecodeString(returnValueType));
        value.getType().setName(returnValueType.getName());
        stack.pushConstant(value);
    }

    private void castValueToValue(TypeDescriptor returnValueType) {
        BytecodeStackValue value = stack.popConstant();

        String leftBytecodeString = QuorumConverter.convertTypeToBytecodeString(value.getType());
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

        value.getType().setName(returnValueType.getName());
        stack.pushConstant(value);
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
                    int callStepNumber = tracker.getFunctionParameterMapping(i);
                    ExecutionStep callStep = steps.get(callStepNumber);
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
                } //if no, do nothing
                
                ExecutionStep step = steps.get(i);
                step.visit(this);
            }

            //clear out the queue at the end of visiting
//            if(tracker.getOpcodeType(end).equals(OpcodeType.ROOT_EXPRESSION)){
//                tracker.clearProcessedFromQueue();
//            }else{
                tracker.clearQueue();
//            }
        }
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
        processExpressions();
        //prepare the integer as a number.
        prepareNumberIntegerOperation();
        BytecodeStackValue pop = stack.popConstant();
        //Assigns an integer to a local variable or field of type number.
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentNumberIntegerStep step) {
        //process the expressions
        processExpressions();
        //prepare the integer as a number.
        prepareNumberIntegerOperation();
        BytecodeStackValue pop = stack.popConstant();
        //Assigns an integer to a field of type number.
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentBooleanLocalStep step) {
        //Assigns a boolean to a local variable or field of type boolean

        //1. Loop through all expressions and dequeue them
        //processExpressions(getTopOfQueue())
        processExpressions();
        //2. Now that everything is out of the queue,
        //do the actual assignment
        BytecodeStackValue pop = stack.popConstant();
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentBooleanStep step) {
        //Assigns a boolean to a field of type boolean
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentIntegerLocalStep step) {
        //process the expressions in the queue
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
        //Assigns an integer to a local variable or field of type integer
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentIntegerStep step) {
        //Assigns an integer to a field of type integer
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentNumberLocalStep step) {
        //Assigns a number to a local variable or field of type number.
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentNumberStep step) {
        //Assigns a number to a field of type number.
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
        performAssignment(pop, step, true);
    }

    @Override
    public void visit(AssignmentTextLocalStep step) {
        //Assigns a number to a local variable or field of type text.
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
        performAssignment(pop, step, false);
    }

    @Override
    public void visit(AssignmentTextStep step) {
        //remove the constant from the bytecode stack and assign a number to 
        //a field of type text.
        processExpressions();
        BytecodeStackValue pop = stack.popConstant();
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
        stack.popConstant();
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
        stack.popConstant();
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
        performEqualityComparison(true);
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
        performEqualityComparison(true);
    }

    @Override
    public void visit(BinaryEqualsNumberStep step) {
        //Determines if two numbers are equal.
        performEqualityComparison(true);
    }

    @Override
    public void visit(BinaryEqualsStep step) {
        //Determines if two integers are equal.
        performEqualityComparison(true);
    }

    @Override
    public void visit(BinaryEqualsStringStep step) {
        //Determines if two text values are equal.
        performEqualityComparison(true);
    }

    @Override
    public void visit(BinaryGreaterEqualsIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(true);
        //determines if the converted number is greater than or equal to another number.
        performInequalityComparison(true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //determines if another number is greater than or equal to the converted number.
        performInequalityComparison(true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsNumberStep step) {
        //Determines if one number is greater than or equal to another number.
        performInequalityComparison(true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsStep step) {
        //Determines if one integer is greater than or equal to another integer.
        performInequalityComparison(true, false);
    }

    @Override
    public void visit(BinaryGreaterEqualsStringStep step) {
        //Determines if one text value is greater than or equal to another text value.
        performInequalityComparison(true, false);
    }

    @Override
    public void visit(BinaryGreaterThanIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(true);
        //then determines if the converted number is greater than another number.
        performInequalityComparison(false, false);
    }

    @Override
    public void visit(BinaryGreaterThanNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //determines if another number is greater than the converted number
        performInequalityComparison(false, false);
    }

    @Override
    public void visit(BinaryGreaterThanNumberStep step) {
        //Determines if one number is greater than another number.
        performInequalityComparison(false, false);
    }

    @Override
    public void visit(BinaryGreaterThanStep step) {
        //Determines if one integer is greater than another integer.
        performInequalityComparison(false, false);
    }

    @Override
    public void visit(BinaryGreaterThanStringStep step) {
        //Determines if one text value is greater than another text value
        performInequalityComparison(false, false);
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
        performInequalityComparison(true, true);
    }

    @Override
    public void visit(BinaryLessEqualsNumberIntegerStep step) {
        //Converts an integer to a number.
        prepareNumberIntegerOperation();
        //determines if another number is less than or equal to the converted number.
        performInequalityComparison(true, true);
    }

    @Override
    public void visit(BinaryLessEqualsNumberStep step) {
        //Determines if one number is less than or equal to another number.
        performInequalityComparison(true, true);
    }

    @Override
    public void visit(BinaryLessEqualsStep step) {
        //Determines if one integer is less than or equal to another integer.
        performInequalityComparison(true, true);
    }

    @Override
    public void visit(BinaryLessEqualsStringStep step) {
        //Determines if one text value is less than or equal to another text value.
        performInequalityComparison(true, true);
    }

    @Override
    public void visit(BinaryLessThanIntegerNumberStep step) {
        //Converts an integer to a number.
        prepareIntegerNumberOperation(true);
        //then determines if the converted number is less than another number.
        performInequalityComparison(false, true);
    }

    @Override
    public void visit(BinaryLessThanNumberIntegerStep step) {
        //Converts an integer to a number
        prepareNumberIntegerOperation();
        //determines if another number is less than the converted number.
        performInequalityComparison(false, true);
    }

    @Override
    public void visit(BinaryLessThanNumberStep step) {
        //Determines if one number is less than another number.
        performInequalityComparison(false, true);
    }

    @Override
    public void visit(BinaryLessThanStep step) {
        //Determines if one integer is less than another integer.
        performInequalityComparison(false, true);
    }

    @Override
    public void visit(BinaryLessThanStringStep step) {
        //Determines if one text value is less than another text value.
        performInequalityComparison(false, true);
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
        BytecodeStackValue operand = stack.popConstant();
        stack.popConstant();
        methodVisitor.visitInsn(IXOR);
        stack.pushConstant(operand);
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
        performEqualityComparison(false);
    }

    @Override
    public void visit(BinaryNotEqualsNullCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsNumberIntegerStep step) {
        prepareNumberIntegerOperation();
        performEqualityComparison(false);
    }

    @Override
    public void visit(BinaryNotEqualsNumberStep step) {
        performEqualityComparison(false);
    }

    @Override
    public void visit(BinaryNotEqualsStep step) {
        performEqualityComparison(false);
    }

    @Override
    public void visit(BinaryNotEqualsStringStep step) {
        performEqualityComparison(false);
    }

    @Override
    public void visit(BinaryOrStep step) {
        BytecodeStackValue operand = stack.popConstant();
        stack.popConstant();
        methodVisitor.visitInsn(IOR);
        stack.pushConstant(operand);
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
                   //I don't recall exactly how to do that
                
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
        }
        else {
            VariableParameterCommonDescriptor var = step.getParentObject();
            converted = QuorumConverter.convertStaticKeyToBytecodePath(var.getType().getStaticKey());
        }   
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, converted,
                    callee.getName(),
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        
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
        //if this is a from step we need to process the extra root expression.
        if(step.getLoopType().equals(LoopType.FROM)){
            //process all the queued steps
            processExpressions();
        }
        
        //build the top label
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);

        //get thebytecode
        int currentLoopBytecode = 0;
        if(step.getLoopType().equals(LoopType.UNTIL)){
           currentLoopBytecode = IFNE;
        }else{
           currentLoopBytecode = IFEQ;  
        }
        LabelStackValue temp = new LabelStackValue(LabelTypeEnum.LOOP, currentLoopBytecode, label0);
        stack.pushLabel(temp);

        //process all the queued steps
        processExpressions();

        //generate the lables and visit as necessary
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(currentLoopBytecode , label1);

        //push the second label onto the stack so we can visit it later
        LabelStackValue temp2 = new LabelStackValue(LabelTypeEnum.LOOP, currentLoopBytecode, label1);
        stack.pushLabel(temp2);

    }

    @Override
    public void visit(CreateObjectStep step) {
        ClassDescriptor clazz = step.getClazz();
        //methodVisitor.visitVarInsn(ALOAD, THIS);
        String converted = QuorumConverter.convertStaticKeyToBytecodePath(clazz.getStaticKey());
        methodVisitor.visitTypeInsn(NEW, converted);
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, converted, "<init>", "()V");
        VariableParameterCommonDescriptor variable = step.getVariable();

        int variableNumber = variable.getVariableNumber() - currentClass.getNumberOfVariables();
        if (variableNumber == -1) {
            return; //it's a field and we don't handle these yet.
        }

        int mappedVariableNumber = stack.getMappedVariableNumber(variableNumber);

        BytecodeStackValue value = new BytecodeStackValue();
        TypeDescriptor type = new TypeDescriptor();
        type.setName(variable.getType().getName());
        value.setAsVariable(variableNumber);
        value.setType(type);
        value.setName(variable.getName());
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

        stack.setVariable(variableNumber, value);


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
                Label label0 = stack.popLabel().getLabel();

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
        int a = 5;
    }

    @Override
    public void visit(MeVariableMoveStep step) {
        int a = 5;
    }

    @Override
    public void visit(MoveRegistersStep step) {
        int a = 5;
    }

    @Override
    public void visit(MoveStep step) {
        BytecodeStackValue bytecodeValue = new BytecodeStackValue();
        bytecodeValue.setType(step.getValue().getType());
        bytecodeValue.setResult(step.getValue().getResult());
        addToMethodVisit(bytecodeValue);
        stack.pushConstant(bytecodeValue);
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
        BytecodeStackValue value = stack.popConstant(); // get expression result off stack.

        // Insert the appropriate print opcode for the type.
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(" + value.getByteCodeTypeDescriptor() + ")V");
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
            stack.popConstant();
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
        BytecodeStackValue operand = stack.popConstant();
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitInsn(IXOR);
        stack.pushConstant(operand);
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
        VariableParameterCommonDescriptor variable = step.getObj();
        BytecodeStackValue variableValue = stack.getVariable(variable.getVariableNumber());
        addToMethodVisit(variableValue);

        String name = step.getVariableName();
        TypeDescriptor type = step.getVariableType();
        methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(variable.getType().getName()),
                name, QuorumConverter.convertTypeToBytecodeString(type));

        BytecodeStackValue constant = new BytecodeStackValue();
        TypeDescriptor constantType = new TypeDescriptor();
        constantType.setName(type.getName());
        constant.setType(constantType);
        constant.setName(variable.getType().getName() + ":" + name);
        constant.setAsConstant();
        addToMethodVisit(constant);
        stack.pushConstant(constant);

    }

    @Override
    public void visit(VariableMoveStep step) {
        BytecodeStackValue variable;
        String variableType = "";
        BytecodeStackValue constant = new BytecodeStackValue();
        TypeDescriptor constantType = new TypeDescriptor();

        if (step.getValue() instanceof VariableDescriptor) {
            VariableDescriptor varDescriptor = (VariableDescriptor) step.getValue();
            if (varDescriptor != null && varDescriptor.isInitializedClassVariable()) {
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, QuorumConverter.convertStaticKeyToBytecodePath(currentClass.getStaticKey()),
                        varDescriptor.getName(), QuorumConverter.convertTypeToBytecodeString(varDescriptor.getType()));
                variableType = varDescriptor.getType().getName();
            } else {
                int variableNumber = step.getValue().getVariableNumber() - currentClass.getNumberOfVariables();
                variable = stack.getVariable(variableNumber);
                variableType = variable.getType().getName();
                addToMethodVisit(variable);
            }
        }
        constantType.setName(variableType);
        constant.setType(constantType);

        constant.setAsConstant();
        stack.pushConstant(constant);
    }
}
