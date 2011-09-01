/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.execution.ExpressionValue;
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
import org.quorum.steps.MainCallStep;
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
import org.quorum.steps.StepFactory;
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
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author astefik
 */
public class QuorumJavaBytecodeStepVisitor implements ExecutionStepVisitor, Opcodes{

    private ClassWriter classWriter;
    private FieldVisitor fieldVisitor;
    private MethodVisitor methodVisitor;
    private AnnotationVisitor annotationVisitor;
    
    private Stack<BytecodeStackValue> bytecodeValues = new Stack<BytecodeStackValue>();
    private Stack<ExpressionValue> constants = new Stack<ExpressionValue>();
    private Stack<Integer> variables = new Stack<Integer>();
    private HashMap<Integer, BytecodeStackValue> variable = new HashMap<Integer, BytecodeStackValue>();
    
    private int methodStackSize = 1;
    
    /**
     * This variable stores the current class being processed on the system.
     */
    private ClassExecution clazz = null;
    
    private String processedClazzName = "";
    
    /**
     * This variable stores the current method being processed on the system.
     */
    private MethodExecution method = null;
    
    private final int THIS = 0;
    
    public QuorumJavaBytecodeStepVisitor() {
        
    }
    
    public void visit(ClassExecution clazz) {
        classWriter = new ClassWriter(0);
        this.clazz = clazz;
        
        String staticKey = clazz.getClassDescriptor().getStaticKey();
        //garbage code to ease debugging, remove for reality.
        if(!".Stefik".equals(staticKey) && !".Matt".equals(staticKey) && !".Main".equals(staticKey)) {
            return;
        }
        
        String name = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);
        processedClazzName = name;
        classWriter = new ClassWriter(0);
        
        //this will have to be modified for inheritance conversion
        classWriter.visit(V1_6, ACC_PUBLIC + ACC_SUPER, name, null, "java/lang/Object", null);
        
        //call the class's initialization function
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, THIS);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
        
        //loop through each step. Have a separate visitor method for each subclass
        Vector<ExecutionStep> steps = clazz.getSteps();
        for(int i = 0; i < steps.size(); i++) {
            ExecutionStep step = steps.get(i);
            step.visit(this);
        }
        
        //now do all methods
        Iterator<MethodExecution> methods = clazz.getMethods();
        while(methods.hasNext()) {
            MethodExecution method = methods.next();
            visit(method);
        }
        
        classWriter.visitEnd();
    }
    
    public void visit(MethodExecution method) {
        boolean main = method.isMainMethod();
        
        //add the bytecode for the main method.
        


        if(main) {
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
        methodStackSize = 1;

        methodVisitor.visitCode();

        Vector<ExecutionStep> steps = method.getSteps();
        for(int i = 0; i < steps.size(); i++) {
            ExecutionStep step = steps.get(i);
            step.visit(this);
        }

        methodVisitor.visitInsn(RETURN);
        //this should be filled out with the number of local variables
        int numberVariables = method.getMethodDescriptor().getNumberVariables();
        //the stack size should also change depending on the 
        //expressions that need to be processed.
        methodVisitor.visitMaxs(2, numberVariables + 1);
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
    public void visit(AssignmentBooleanLocalStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentBooleanStep step) {
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
    public void visit(AssignmentIntegerLocalStep step) {
        BytecodeStackValue pop = bytecodeValues.pop();
        if (pop.isConstant()) {
            int variableNumber = step.getVariable().getVariableNumber();
            pop.setAsVariable(variableNumber);
            methodVisitor.visitLdcInsn(pop.getResult().integer);
            methodVisitor.visitVarInsn(ISTORE, variableNumber);
        }
        
//        if(!constants.isEmpty()) { //we're just storing a constant
//            ExpressionValue pop = constants.pop();
//
//            methodVisitor.visitLdcInsn(ExpressionValueConverter.convert(pop));
//            methodVisitor.visitVarInsn(ISTORE, variableNumber);
//            variables.push(variableNumber);
//        }
//        else { //otherwise we're storing something more complicated
//            
//        }
    }

    @Override
    public void visit(AssignmentIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentNumberIntegerLocalStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentNumberLocalStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(AssignmentTextLocalStep step) {
        
        BytecodeStackValue pop = bytecodeValues.pop();
        int variableNumber = step.getVariable().getVariableNumber();
        if (pop.isConstant()) {
            pop.setAsVariable(variableNumber);
            methodVisitor.visitVarInsn(ASTORE, variableNumber);
            variable.put(variableNumber, pop);
        }
        
        
//        if(!constants.isEmpty()) { //we're just storing a constant
//            ExpressionValue pop = constants.pop();
//            int variableNumber = step.getVariable().getVariableNumber();
//            methodVisitor.visitLdcInsn(ExpressionValueConverter.convert(pop));
//            methodVisitor.visitVarInsn(ASTORE, variableNumber);
//            variables.push(variableNumber);
//        }
//        else { //otherwise we're storing something more complicated
//            
//        }
    }

    @Override
    public void visit(AssignmentTextStep step) {
        int a = 5;
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
        int a = 5;
    }

    @Override
    public void visit(BinaryAddBooleanTextStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddIntegerTextStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddNumberTextStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddTextBooleanStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddTextIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAddTextNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryAndStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryConcatenateStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryDivideIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryDivideNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryDivideNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryDivideStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsBooleanStep step) {
        int a = 5;
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
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsNullCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryEqualsStringStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterEqualsIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterEqualsNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterEqualsNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterEqualsStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterEqualsStringStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterThanIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterThanNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterThanNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterThanStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryGreaterThanStringStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryIsACustomCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessEqualsIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessEqualsNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessEqualsNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessEqualsStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessEqualsStringStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessThanIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessThanNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessThanNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessThanStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryLessThanStringStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryModIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryModNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryModNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryModStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryMultiplyIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryMultiplyNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryMultiplyNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryMultiplyStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsBooleanStep step) {
        int a = 5;
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
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsNullCustomStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryNotEqualsStringStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinaryOrStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinarySubtractIntegerNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinarySubtractNumberIntegerStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinarySubtractNumberStep step) {
        int a = 5;
    }

    @Override
    public void visit(BinarySubtractStep step) {
        int a = 5;
    }

    @Override
    public void visit(BooleanAutoBoxStep step) {
        int a = 5;
    }

    @Override
    public void visit(CallStep step) {
        MethodDescriptor callee = step.getMethodCallee();
        String name = callee.getName();
        
        //NOTE: step.isThisCall is not what you want here, that's different
        if(!step.IsObjectCall()) { //it's a this call, so load it
            methodVisitor.visitVarInsn(ALOAD, THIS);
            //TODO: Change to accomodate parameters
            
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, processedClazzName, 
                    callee.getName(), 
                    QuorumConverter.convertMethodDescriptorToBytecodeSignature(callee));
        }
    }

    @Override
    public void visit(ConditionalJumpCheckStep step) {
        int a = 5;
    }

    @Override
    public void visit(ConditionalJumpIfStep step) {
        int a = 5;
    }

    @Override
    public void visit(ConditionalJumpLoopStep step) {
        int a = 5;
    }

    @Override
    public void visit(CreateObjectStep step) {
        int a = 5;
    }

    @Override
    public void visit(DataStackPopStep step) {
        int a = 5;
    }

    @Override
    public void visit(EndScopeStep step) {
        int a = 5;
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
        ExpressionValue value = step.getValue();
        BytecodeStackValue bytecodeValue = new BytecodeStackValue(value, true, 0);
        methodVisitor.visitLdcInsn(bytecodeValue.getValue());
        bytecodeValues.push(bytecodeValue);
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
        
        BytecodeStackValue pop = bytecodeValues.pop();
        if (pop.isConstant())
            methodVisitor.visitLdcInsn(pop.getValue());
        else
            methodVisitor.visitVarInsn(pop.getLoadOpCode(), pop.getVarNumber());
        //fill this out with more legal constants
//        if(!constants.isEmpty()) {
//            ExpressionValue value = constants.pop();
//            if(value.getType().isText()) {   
//                methodVisitor.visitLdcInsn(value.getResult().text);
//            }
//        }
//        else if(!variables.isEmpty()) {
//            Integer pop = variables.pop();
//            int val = pop;
//            methodVisitor.visitVarInsn(ALOAD, val);
//        }

        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(" + pop.getByteCodeTypeDescriptor() + ")V");
    }

    @Override
    public void visit(PushStep step) {
        int a = 5;
    }

    @Override
    public void visit(ReturnStep step) {
        int a = 5;
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
        int a = 5;
    }

    @Override
    public void visit(UnaryBooleanNumberCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryBooleanTextCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryIntegerBooleanCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryIntegerIntegerCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryIntegerNumberCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryIntegerTextCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryNotStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryNumberBooleanCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryNumberIntegerCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryNumberNumberCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryNumberTextCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryTextBooleanCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryTextIntegerCastStep step) {
//        if(!constants.isEmpty()) {
//            ExpressionValue value = constants.pop();
//            if(value.getType().isInteger()) {
//                value.setType(TypeDescriptor.getTextType());
//                constants.push(value);
//                methodVisitor.visitLdcInsn(value.getResult().text);
//            }
//        }
//        else if(!variables.isEmpty()) {
//            Integer pop = variables.pop();
//            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "Java/lang/Integer", "toString", "()Ljava/lang/String;");
////            methodVisitor.visitVarInsn(NOP, V1_1);
//            constants.push(StepFactory.createExpressionValue(step.getRegister() + 1, pop.toString()));
////            methodVisitor.visitLdcInsn(pop.toString());
//        }
    }

    @Override
    public void visit(UnaryTextNumberCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(UnaryTextTextCastStep step) {
        int a = 5;
    }

    @Override
    public void visit(VariableInObjectMoveStep step) {
        int a = 5;
    }

    @Override
    public void visit(VariableMoveStep step) {
        BytecodeStackValue value = variable.get(step.getValue().getVariableNumber());
        bytecodeValues.push(value);
//        methodVisitor.visitVarInsn(value.getLoadOpCode(), value.getVarNumber());
    }
}
