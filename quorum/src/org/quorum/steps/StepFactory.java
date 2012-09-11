/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.steps;

import java.util.Iterator;
import org.quorum.plugins.RuntimeError;
import org.quorum.vm.interfaces.LineInformation;
import org.quorum.execution.ActivationRecord;
import org.quorum.execution.ExecutionStep;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.GenericDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.Result;
import org.quorum.symbols.SymbolTable;
import org.quorum.symbols.TypeChecker;
import org.quorum.symbols.TypeCheckerResult;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.*;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.implementation.OpcodeType;
import org.quorum.vm.interfaces.ErrorType;

/**
 * This class constructs the opcode that will be used in the compiler. It
 * connects to the type checking system and creates type specific opcodes,
 * relevant to a particular object or primitive.
 *
 * @author Andreas Stefik, Melissa Stefik, and Aaron Willows
 */
public class StepFactory {

    private QuorumVirtualMachine machine;
    private TypeChecker typeChecker;

    /**
     * Takes an expression object, determines if it is a boolean,
     * and if it is not, issues a compiler error.
     *
     * @return
     */
    public boolean assertBooleanExpression(TypeDescriptor type,
            ExecutionStep step, String fileName) {
        if (type == null || step == null) {
            return false;
        }

        if (!(type.getName().equals(TypeDescriptor.BOOLEAN))) {
            CompilerError error = new CompilerError();
            error.setLineNumber(step.getBeginLine());
            error.setError("If statements require an expression of type boolean.");
            error.setErrorType(ErrorType.IF_INVALID_EXPRESSION);
            error.setColumn(step.getBeginColumn());
            error.setFile(fileName);
            machine.getCompilerErrors().addError(error);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Create an Expression value with a register, result, type, and name of a
     * given value.
     *
     * @param register
     * @param result
     * @param type
     * @param name
     * @return generated ExpressionValue
     */
    public static ExpressionValue createExpressionValue(int register, Result result, TypeDescriptor type, String name) {
        ExpressionValue value = new ExpressionValue();
        value.setRegister(register);
        value.setResult(result);
        value.setName(name);
        value.setType(type);

        return value;
    }

    /**
     * Create an ExpressionValue with a register, result, and type of a given
     * value.
     *
     * @param register
     * @param result
     * @param type
     * @return generated ExpressionValue
     */
    public static ExpressionValue createExpressionValue(int register, Result result, TypeDescriptor type) {
        return createExpressionValue(register, result, type, null);
    }

    /**
     * Create an ExpressionValue with a register and result of a given value. The
     * ExpressionValue's type is set to integer. Only use this method if you are generating
     * an expression value with a primitive type of integer.
     *
     * @param register
     * @param result
     * @return generated ExpressionValue
     */
    public static ExpressionValue createExpressionValue(int register, int result) {
        Result r = new Result();
        r.integer = result;
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TypeDescriptor.INTEGER);
        return createExpressionValue(register, r, t);
    }

    /**
     * Create an ExpressionValue with a register and result of a given value. The
     * ExpressionValue's type is set to boolean. Only use this method if you are generating
     * an expression value with a primitive type of boolean.
     *
     * @param register
     * @param result
     * @return generated ExpressionValue
     */
    public static ExpressionValue createExpressionValue(int register, boolean result) {
        Result r = new Result();
        r.boolean_value = result;
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TypeDescriptor.BOOLEAN);
        return createExpressionValue(register, r, t);

    }

    /**
     * Create an ExpressionValue with a register and result of a given value. The
     * ExpressionValue's type is set to number. Only use this method if you are generating
     * an expression value with a primitive type of number.
     *
     * @param register
     * @param result
     * @return generated ExpressionValue
     */
    public static ExpressionValue createExpressionValue(int register, float result) {
        Result r = new Result();
        r.number = result;
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TypeDescriptor.NUMBER);
        return createExpressionValue(register, r, t);
    }

    /**
     * Create an ExpressionValue with a register and result of a given value. The
     * ExpressionValue's type is set to text. Only use this method if you are generating
     * an expression value with a primitive type of text.
     *
     * @param register
     * @param result
     * @return generated ExpressionValue
     */
    public static ExpressionValue createExpressionValue(int register, String result) {
        Result r = new Result();
        r.text = result;
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TypeDescriptor.TEXT);
        return createExpressionValue(register, r, t);
    }

    /**
     * Creates and adds a UnaryNotStep to the execution builder. Effectively,
     * this opcode negates a boolean value.
     *
     * @param location
     * @param register
     * @param value
     * @param step
     * @returns a class containing the results
     */
    public ResultTuple addUnaryNotStep(int register, ExpressionValue value, ExecutionStep step) {
        UnaryOperationInformation info = generateUnaryOperationInfoformation(
                register, value, step);
        return addUnaryOperation(info, OperationEnum.NOT);
    }

    /**
     * Generates a support class to pass information along for unary operations.
     *
     * @param register
     * @param leftValue
     * @param step
     * @return
     */
    private UnaryOperationInformation generateUnaryOperationInfoformation(
            int register, ExpressionValue leftValue, ExecutionStep step) {
        UnaryOperationInformation info = new UnaryOperationInformation();
        info.register = register;
        info.value = leftValue;
        info.step = step;
        return info;
    }

    /**
     * Adds a generic unary operation to the system, with opcode op.
     *
     * @param info
     * @param op
     * @return
     */
    private ResultTuple addUnaryOperation(UnaryOperationInformation info, OperationEnum op) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(info.register + 1);
        LineInformation location = new LineInformation(
                info.step.getBeginLine(),
                info.step.getEndLine(),
                info.step.getBeginColumn(),
                info.step.getEndColumn());

        boolean passed = true;
        TypeCheckerResult typeCheckResult = getTypeChecker().check(info.value.getType(), op);
        if (typeCheckResult.getResult() == null) {
            CompilerError error = new CompilerError(location.getStartLine(), typeCheckResult.getErrorMessage(), typeCheckResult.getErrorType());
            error.setFile(location.getFile());
            machine.getCompilerErrors().addError(error);
            passed = false;
        }
        info.typeCheckResult = typeCheckResult;
        ExpressionValue resultValue = new ExpressionValue();
        resultValue.setRegister(info.register);


        if (passed) {
            resultValue.setType(typeCheckResult.getResult());

            UnaryOperationStep resultStep = (UnaryOperationStep) info.typeCheckResult.generateOpcode();
            resultStep.setLineInformation(location);
            resultStep.setRegister(info.value.getRegister());
            resultStep.setResultRegister(info.register);

            addUsedRegisterToCurrentMethod(info.register);
            
            machine.getBuilder().add(resultStep);

            result.setStep(resultStep);
            result.setValue(resultValue);
        } else {
            result.setStep(new NullIntermediateStep());
            result.setValue(resultValue);
        }
        return result;
    }

    /**
     * Creates and adds a BinaryEqualsStep to the execution builder
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return an object containing the results
     */
    public ResultTuple addBinaryEqualsStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.EQUALS);
    }

    /**
     * Creates an opcode for a not equals comparison.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryNotEqualsStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.NOT_EQUALS);
    }

    /**
     * Creates an opcode for a "is a" type comparison.
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryIsAStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, TypeDescriptor type) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, type);
        return addBinaryOperation(info, OperationEnum.IS_A);
    }

    /**
     * Creates an opcode for a greater than comparison.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryGreaterThanStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.GREATER);
    }

    /**
     * Creates an opcode for a greater than or equal to comparison.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryGreaterEqualsStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.GREATER_EQUALS);
    }

    /**
     * Creates an opcode for a less than comparison.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryLessThanStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.LESS);
    }

    /**
     * Creates an opcode for a less than or equals comparison.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryLessEqualsStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.LESS_EQUALS);
    }

    /**
     * Creates an opcode for an or operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryOrStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.OR);
    }

    /**
     * Creates an opcode for an and operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryAndStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.AND);
    }

    /**
     * Generates a type appropriate opcode for an addition operator, if
     * one exists. If an appropriate opcode does not exist, a compiler error
     * is thrown.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryAddStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.PLUS);
    }

    /**
     * Creates a data structure for housing basic information about this binary
     * operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    private BinaryOperationInformation generateBinaryInfoformation(
            int register, ExpressionValue leftValue, ExecutionStep leftStep,
            ExpressionValue rightValue, ExecutionStep rightStep) {

        BinaryOperationInformation info = new BinaryOperationInformation();
        info.register = register;
        info.leftValue = leftValue;
        info.leftStep = leftStep;
        info.rightValue = rightValue;
        info.rightStep = rightStep;
        return info;
    }

    /**
     * Instance of generated binary step
     * 
     * @param register
     * @param leftValue
     * @param leftStep
     * @param type
     * @return
     */
    private BinaryOperationInformation generateBinaryInfoformation(
            int register, ExpressionValue leftValue, ExecutionStep leftStep,
            TypeDescriptor type) {

        BinaryOperationInformation info = new BinaryOperationInformation();
        info.register = register;
        info.leftValue = leftValue;
        info.leftStep = leftStep;
        info.rightType = type;
        return info;
    }

    /**
     * Does a generic binary operation of type op.
     * @param info
     * @param op
     * @return
     */
    private ResultTuple addBinaryOperation(BinaryOperationInformation info, OperationEnum op) {
        preBinaryOperationStep(info);
        TypeCheckerResult typeCheckResult = null;
        if(info.rightValue != null){
            typeCheckResult = typeChecker.check(
                    info.leftValue.getType(), info.rightValue.getType(), op, false);
        }else{
            typeCheckResult = typeChecker.check(info.leftValue.getType(), info.rightType, op);
        }
        info.typeCheckResult = typeCheckResult;
        return postBinaryOperationStep(info);
    }

    /**
     * Does some initialization on this binary operation.
     *
     * @param info
     */
    private void preBinaryOperationStep(BinaryOperationInformation info) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(info.register + 1);
        LineInformation location = null;
        if(info.rightStep != null){
            location = new LineInformation(
                    info.leftStep.getBeginLine(),
                    info.rightStep.getEndLine(),
                    info.leftStep.getBeginColumn(),
                    info.rightStep.getEndColumn());
        }else{
            location = new LineInformation(
                    info.leftStep.getBeginLine(),
                    info.leftStep.getEndLine(),
                    info.leftStep.getBeginColumn(),
                    info.leftStep.getBeginColumn());
        }
        location.setFile(info.leftStep.getFileKey());

        ExpressionValue resultValue = new ExpressionValue();
        resultValue.setType(info.leftValue.getType());
        resultValue.setRegister(info.register);
        result.setValue(resultValue);

        info.location = location;
        info.result = result;
        info.resultValue = resultValue;
    }

    /**
     * Does some post processing on this operation.
     *
     * @param info
     * @return
     */
    private ResultTuple postBinaryOperationStep(BinaryOperationInformation info) {
        boolean passed = true;
        if (info.typeCheckResult.getResult() == null) {
            CompilerError error = new CompilerError(info.location.getStartLine(), info.typeCheckResult.getErrorMessage(), info.typeCheckResult.getErrorType());
            error.setFile(info.location.getFile());
            machine.getCompilerErrors().addError(error);
            passed = false;
        }
        info.resultValue.setType(info.typeCheckResult.getResult());

        if (passed) {
            BinaryOperationStep resultStep;
            if(info.rightValue != null){
                resultStep = (BinaryOperationStep) info.typeCheckResult.generateOpcode();
            }else{
                resultStep = new BinaryIsACustomCustomStep();
            }
            resultStep.setLineInformation(info.location);
            resultStep.setLeftRegister(info.leftValue.getRegister());
            
            if(info.rightValue != null){
                resultStep.setRightRegister(info.rightValue.getRegister());
            }else{
                ((BinaryIsACustomCustomStep)resultStep).setRightType(info.rightType);
            }

            resultStep.setResultRegister(info.register);
            
            addUsedRegisterToCurrentMethod(info.register);

            machine.getBuilder().add(resultStep);
            info.result.setStep(resultStep);
        } else {
            info.result.setStep(new NullIntermediateStep());
        }
        return info.result;
    }

    /**
     * Creates an opcode for a subtraction operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinarySubtractStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.MINUS);
    }

    /**
     * Creates an opcode for a multiple operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryMultiplyStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.TIMES);
    }

    /**
     * Creates an opcode for a divide operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryDivideStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.DIVIDE);
    }

    /**
     * Creates an opcode for a modulus operation.
     *
     * @param register
     * @param leftValue
     * @param leftStep
     * @param rightValue
     * @param rightStep
     * @return
     */
    public ResultTuple addBinaryModStep(int register, ExpressionValue leftValue, ExecutionStep leftStep, ExpressionValue rightValue, ExecutionStep rightStep) {
        BinaryOperationInformation info = generateBinaryInfoformation(register, leftValue, leftStep, rightValue, rightStep);
        return addBinaryOperation(info, OperationEnum.MODULUS);
    }

    /**
     * Creates a step that assigns a value to a variable in the virtual machine.
     *
     * @param location
     * @param variableName
     * @param rightValue
     * @param rightStep
     * @param localOnly
     */
    public void addAssignmentStep(LineInformation location, String variableName,
            ExpressionValue rightValue, ExecutionStep rightStep, boolean localOnly, boolean hasRHS) {
        AssignmentStepInformation info = generateAssignmentStepInformation(
                location, variableName, rightValue, rightStep, localOnly, "", null, false, hasRHS);
        addAssignmentGeneralStep(info);
    }

    /**
     * Generates an instance of AssignmentStepInformation for use in the
     * various methods that generate assignment steps.
     *
     * @param location
     * @param variableName
     * @param rightValue
     * @param rightStep
     * @param localOnly
     * @return
     */
    private AssignmentStepInformation generateAssignmentStepInformation(
            LineInformation location, String variableName,
            ExpressionValue rightValue, ExecutionStep rightStep,
            boolean localOnly, String variableInObjectName,
            ClassDescriptor parentClazz, boolean isMe, boolean isValidConstantAssignment) {
        AssignmentStepInformation info = new AssignmentStepInformation();
        info.location = location;
        info.variableInObjectName = variableInObjectName;
        info.variableName = variableName;
        info.rightStep = rightStep;
        info.rightValue = rightValue;
        info.localOnly = localOnly;
        info.parent = parentClazz;
        info.isAssignedToMe = isMe;
        info.hasRightHandSide = isValidConstantAssignment;
        return info;
    }

    /**
     * A general method for creating any and all types of assignment statements.
     *
     * @param info
     */
    private void addAssignmentGeneralStep(AssignmentStepInformation info) {
        TypeDescriptor leftType = new TypeDescriptor();
        VariableParameterCommonDescriptor vd  = null;
        if(info.isAssignedToMe){
            vd = machine.getSymbolTable().getCurrentClass().getVariable(info.variableName);
        }else{
            vd = machine.getSymbolTable().getVariable(info.variableName);
        }

        if(vd == null && info.parent != null){
            ClassDescriptor curClass = machine.getSymbolTable().getCurrentClass();
            ClassDescriptor parent = curClass.getParent(info.parent.getStaticKey());

            if(parent != null){
                VariableDescriptor variable = parent.getVariable(info.variableName);
                if(variable != null){
                    vd = (VariableParameterCommonDescriptor)variable;
                }
            }else{
                CompilerError error = new CompilerError(info.location.getStartLine(),
                    info.parent.getStaticKey() + " is not a parent of the class "
                    + curClass.getStaticKey(), ErrorType.MISSING_PARENT);
                error.setFile(info.location.getFile());
                machine.getCompilerErrors().addError(error);
            }
        }

        if (vd != null) {

            leftType = vd.getType();

            if(vd.isConstant() && vd.isAssignedAValue()){
                CompilerError error = new CompilerError(info.location.getStartLine(),
                    "Cannot assign a new value to the constant variable " + vd.getName(), ErrorType.CONSTANT_REASSIGNMENT);
                error.setFile(info.location.getFile());
                machine.getCompilerErrors().addError(error);
            }else if(vd.isConstant() && !info.hasRightHandSide && vd.getType().isPrimitiveType()){
                CompilerError error = new CompilerError(info.location.getStartLine(),
                    "The constant variable " + vd.getName() + " must be assigned a value when it is defined.", ErrorType.CONSTANT_INITIALIZED);
                error.setFile(info.location.getFile());
                machine.getCompilerErrors().addError(error);
            }else if(vd.isConstant()){
                vd.setIsAssignedAValue(true);
            }
            
            CompilerError e = this.machine.getSymbolTable().getControlFlow().addStatement(info.location);
            if (e != null) {
                machine.getCompilerErrors().addError(e);
            }


            if (!info.variableInObjectName.equals("")) {
                ClassDescriptor classDescriptor = machine.getSymbolTable().getClassDescriptor(vd.getType().toString());
                if(info.parent != null){
                    ClassDescriptor parent = classDescriptor.getParent(info.parent.getStaticKey());
                    if(parent != null && parent.equals(info.parent)){
                        classDescriptor = parent;
                    }
                }
                if (classDescriptor != null) {
                    VariableDescriptor variable = classDescriptor.getVariable(info.variableInObjectName);
                    if (variable == null ) {
                        this.addCompilerError("Variable " + info.variableInObjectName
                                + " has not been defined in the class " + vd.getName() + ".", info.location, ErrorType.MISSING_VARIABLE);
                    }else if(variable.getAccessModifier().equals(variable.getAccessModifier().PRIVATE) && !info.location.getClassName().equals(vd.getType().getName())){
                        this.addCompilerError("Variable " + info.variableInObjectName
                                + " cannot be accessed from " + vd.getName() + " because it is private. ", info.location, ErrorType.MISSING_VARIABLE);
                    }else {
                        leftType = variable.getType();
                        if(leftType.isTemplated()){ //if the type is templated resolve the type for the assignment
                            ClassDescriptor variablesClass = machine.getSymbolTable().getClassDescriptor(vd.getType().getName());
                            VariableDescriptor variableInObject = variablesClass.getVariable(info.variableInObjectName);
                            TypeDescriptor type = variableInObject.getType();
                            
                            if(type.isTemplated()){//if the variable in the object is templated also
                                Iterator<GenericDescriptor> subTypes = vd.getType().getSubTypes();
                                Iterator<GenericDescriptor> templateVariables = variablesClass.getTemplateVariables();
                                
                                while(templateVariables.hasNext()&& subTypes.hasNext()){//search through the templated types to match
                                    GenericDescriptor nextTemplatedVariable = templateVariables.next();
                                    GenericDescriptor nextSubType = subTypes.next();
                                    if(nextTemplatedVariable.getName().equals(type.getTemplateName())){
                                        leftType = nextSubType.getType();
                                    }
                                }
                            }else{
                                leftType = type;
                            }
                        }
                    }
                }
            }

            TypeDescriptor rightType = null;
            if (info.rightValue != null) {
                rightType = info.rightValue.getType();
            } else if (leftType.isPrimitiveType()) {
                //get a default expression value, put it into
                //a register, and assign it.
                ExpressionValue value = ExpressionValue.getPrimitiveDefault(leftType);
                rightType = leftType;
                info.rightValue = value;
                value.setRegister(0); //it doesn't matter what register it goes in
                this.addMoveStep(0, info.location, rightType, value.getResult());
            }

            //next, if this vd needs to have its type updated to a full package
            //name, do so now.
            TypeDescriptor vdType = leftType;
            if (!vdType.isPrimitiveType()) {
                ClassDescriptor c =
                        this.machine.getSymbolTable().findClassDescriptorFromCurrentClass(
                        vdType.getStaticKey());
                if (c != null) {
                    if(vd.getType()!= null && vd.getType().hasSubTypes()){
                        vd.getType().setName(c.getType().getStaticKey());
                    }else{
                        vd.setType(c.getType()); //now the full package name
                    }
                    leftType = vd.getType();
                }
            }

            //the right type also needs to be updated
            if (rightType != null && !rightType.isPrimitiveType()) {
                ClassDescriptor c =
                        this.machine.getSymbolTable().findClassDescriptorFromCurrentClass(
                        rightType.getStaticKey());
                if (c != null) {
                    rightType.setName(c.getType().getName()); //now the full package name
                }
            }

            if(leftType != null){
                machine.getSymbolTable().getCurrentClass().resolveSubTypes(leftType.getSubTypes());
            }

            if(rightType != null){
                machine.getSymbolTable().getCurrentClass().resolveSubTypes(rightType.getSubTypes());
            }

            TypeCheckerResult typeCheckResult = null;
            
            typeCheckResult = typeChecker.check(leftType, rightType, null, true, info.localOnly);

            if (typeCheckResult.getResult() != null) {
                ExecutionStep stepResult = typeCheckResult.generateOpcode();
                if (stepResult instanceof CreateObjectStep) {//create an object

                    CreateObjectStep objStep = (CreateObjectStep) stepResult;
                    objStep.setLineInformation(info.location);
                    ClassDescriptor cd = machine.getSymbolTable().getClassDescriptor(vd.getType().getStaticKey());
                    objStep.setClazz(cd);
                    objStep.setVariable(vd);

                    //determine if the template values match.
                    //a compiler error is fired when the template types
                    //are not equivalent.
                    if(cd.getNumberOfTemplateVariables() > vd.getNumberOfTemplateTypes()
                            || (cd.getNumberOfTemplateVariables() < vd.getNumberOfTemplateTypes() && cd.getNumberOfTemplateVariables() != 0)){
                        //throw a compiler error, this is not allowed
                        CompilerError error = new CompilerError(
                                info.location.getStartLine(),
                                "The class " + cd.getName() +
                                " is a templated class of the form " + cd.getName() + "<", ErrorType.MISMATCHED_TEMPLATES);
                        String errorString = error.getError();
                        Iterator<GenericDescriptor> templateVariables = cd.getTemplateVariables();
                        int count = 0;
                        while(templateVariables.hasNext()){
                            if(count != 0){
                                errorString = errorString + ", " + templateVariables.next().getName();
                            }else{
                                errorString = errorString + templateVariables.next().getName();
                            }
                            count++;
                        }
                        errorString = errorString + ">";
                        error.setError(errorString);
                        error.setFile(info.location.getFile());
                        machine.getCompilerErrors().addError(error);
                    }else if(cd.getNumberOfTemplateVariables() < vd.getNumberOfTemplateTypes()){
                        //throw a compiler error, this is not allowed
                        CompilerError error = new CompilerError(
                                info.location.getStartLine(),
                                "The class " + cd.getName() +
                                " is not a templated class.", ErrorType.MISMATCHED_TEMPLATES);
                        error.setFile(info.location.getFile());
                        machine.getCompilerErrors().addError(error);
                    }

                    //determine if this object is an abstract class (a class
                    //containing blueprints) and add a compiler error if it is.
                    if (cd.hasBlueprints() || !cd.isInstantiatable()) {
                        //throw a compiler error, this is not allowed
                        CompilerError error = new CompilerError(
                                info.location.getStartLine(),
                                "You cannot instantiate the abstract class "
                                + cd.getName() + ". This class has not implemented all necessary blueprints.", ErrorType.INSTANTIATE_ABSTRACT);
                        error.setFile(info.location.getFile());
                        machine.getCompilerErrors().addError(error);
                    }

                    //determine whether this object is of the same type,
                    //as the enclosing class, is being constructed
                    //and
                    Scopable scope = this.machine.getSymbolTable().getCurrentScope();
                    if (scope instanceof ClassDescriptor) {
                        ClassDescriptor parent = (ClassDescriptor) scope;
                        if (parent.getStaticKey().compareTo(cd.getStaticKey()) == 0) {
                            //throw a compiler error, this is not allowed
                            CompilerError error = new CompilerError(
                                    info.location.getStartLine(),
                                    "You cannot instantiate an object of type "
                                    + cd.getStaticKey() + " inside a class of the same type. Instead, say \""
                                    + cd.getName() + " " + cd.getName().toLowerCase()
                                    + " = undefined\".", ErrorType.INSTANTIATE_THIS);
                            error.setFile(info.location.getFile());
                            machine.getCompilerErrors().addError(error);
                        }

                        //if the variable is a template type it can not be instantiated.
                        if(vd.getType().getTemplateName() != null){
                            //throw a compiler error, this is not allowed
                            CompilerError error = new CompilerError(
                                    info.location.getStartLine(),
                                    "You cannot instantiate a generic object "
                                    + vd.getName() + " of type "
                                    + vd.getType().getTemplateName() + ". Instead, say \""
                                    + vd.getType().getTemplateName() + " " + vd.getName()
                                    + " = undefined\".", ErrorType.INSTANTIATE_GENERIC);
                            error.setFile(info.location.getFile());
                            machine.getCompilerErrors().addError(error);
                        }
                    }else if(scope instanceof MethodDescriptor){
                        //if the variable is a template type it can not be instantiated.
                        if(vd.getType().getTemplateName() != null){
                            //throw a compiler error, this is not allowed
                            CompilerError error = new CompilerError(
                                    info.location.getStartLine(),
                                    "You cannot instantiate a generic object "
                                    + vd.getName() + " of type "
                                    + vd.getType().getTemplateName() + ". Instead, say \""
                                    + vd.getType().getTemplateName() + " " + vd.getName()
                                    + " = undefined\".", ErrorType.INSTANTIATE_GENERIC);
                            error.setFile(info.location.getFile());
                            machine.getCompilerErrors().addError(error);
                        }
                    }
                    vd.setInitialized(true);
                    machine.getBuilder().add(objStep);

                }else { //else it is an assignment step
                    AssignmentStep asStep = (AssignmentStep) stepResult;
                    asStep.setLineInformation(info.location);
                    asStep.setVariable(vd);
                    asStep.setParent(info.parent);
                    asStep.setSubVariable(info.variableInObjectName, leftType);
                    asStep.setRegister(info.rightValue.getRegister());
                    machine.getBuilder().add(asStep);
                    if(info.rightStep != null) {
                        vd.setInitialized(true);
                    }
                }
                
            } else { //the assignment is invalid
                CompilerError error = new CompilerError(
                        info.location.getStartLine(),
                        typeCheckResult.getErrorMessage(), typeCheckResult.getErrorType());
                error.setFile(info.location.getFile());
                machine.getCompilerErrors().addError(error);
            }
        } else { //throw a compiler error
            CompilerError error = new CompilerError(info.location.getStartLine(),
                    "The variable " + info.variableName + " cannot be assigned"
                    + " a value in this context, as it does not exist.", ErrorType.MISSING_VARIABLE);
            error.setFile(info.location.getFile());
            machine.getCompilerErrors().addError(error);
        }
    }

    /**
     * This version of assignment step is for assigning objects.
     *
     * @param location
     * @param variableName
     * @param localOnly
     */
    public void addAssignmentStep(LineInformation location, String variableName, boolean localOnly, boolean hasRHS) {
        AssignmentStepInformation info = generateAssignmentStepInformation(
                location, variableName, null, null, localOnly, "", null, false, hasRHS);
        addAssignmentGeneralStep(info);
    }

    /**
     * This version of assignment step is for assigning an object's inner variables.
     *
     * @param location
     * @param variableName
     * @param localOnly
     * @param id
     */
    public void addAssignmentStep(LineInformation location, String variableName,
            ExpressionValue rightValue, ExecutionStep rightStep, boolean localOnly, String id, ClassDescriptor parent, boolean isMe, boolean hasRHS) {
        AssignmentStepInformation info = generateAssignmentStepInformation(
                location, variableName, rightValue, rightStep, localOnly, id,
                parent, isMe, hasRHS);
        addAssignmentGeneralStep(info);
    }

    /**
     * Adds a counter variable to a loop.
     *
     * @param register
     * @param location
     * @return
     */
    public ResultTuple addLoopCounter(int register, LineInformation location) {
        return addLoopCounterGeneral(register, location, null);
    }

    /**
     * Adds a counter variable to a loop.
     *
     * @param register
     * @param inValue
     * @param location
     * @return
     */
    public ResultTuple addLoopCounter(int register, ExpressionValue inValue, LineInformation location) {
        return addLoopCounterGeneral(register, location, inValue);
    }

    /**
     * A generic version of loop counter to reduce code duplication.
     *
     * @param register
     * @param location
     * @param inValue
     * @return
     */
    private ResultTuple addLoopCounterGeneral(int register,
            LineInformation location, ExpressionValue inValue) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(register + 1);
        TypeDescriptor type = new TypeDescriptor();
        type.setName(TypeDescriptor.INTEGER);

        Result valueResult = new Result();
        if (inValue != null) {
            valueResult = inValue.getResult();
        } else {
            valueResult.integer = 0;
        }

        ExpressionValue value = new ExpressionValue();
        value.setType(type);
        value.setResult(valueResult);
        if (inValue != null) {
            value.setRegister(inValue.getRegister());
        } else {
            value.setRegister(register);
        }
        result.setValue(value);

        MoveStep step = new MoveStep();
        step.setLineInformation(location);
        step.setTemp(register);
        step.setValue(value);
        result.setStep(step);

        machine.getBuilder().add(step);

        return result;
    }

    private ResultTuple addStackMoveStep(int register, LineInformation location, CallStep call) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(register + 1);

        DataStackPopStep step = new DataStackPopStep();
        step.setMatchingCall(call);
        step.setLineInformation(location);
        step.setRegister(register);
        result.setStep(step);

        machine.getBuilder().add(step);

        return result;
    }

    /**
     * Use this to add a call step to the builder. Call steps call functions,
     * either on objects or inside of the current object.
     *
     * @param info A set of information regarding the call.
     * @return
     */
    public ResultTuple addCallStep(CallInfo info) {
        ResultTuple tuple = new ResultTuple();
        String methodKey = MethodDescriptor.autoGenerateKey(info.methodName, info.argumentTypes);

        ClassDescriptor clazz = null;
        MethodDescriptor method = null;
        VariableParameterCommonDescriptor vd = null;

        String className = "";
        
        if(info.isSoloMethod){
            CompilerError e = this.machine.getSymbolTable().getControlFlow().addStatement(info.location);
            if (e != null) {
                machine.getCompilerErrors().addError(e);
            }
        }

        if (info.isObjectCall) { //we're calling on an object
             vd = machine.getSymbolTable().getCurrentScope().getVariable(info.variable.getName());
            if (vd == null) { //Compiler error, object was not found
                CompilerError error = new CompilerError();
                error.setLineNumber(info.location.getStartLine());
                error.setError("The variable " + info.variable.getName() + " must be defined before it is used.");
                error.setColumn(info.location.getStartColumn());
                error.setErrorType(ErrorType.MISSING_VARIABLE);
                error.setFile(info.location.getFile());
                machine.getCompilerErrors().addError(error);
                tuple.setNextRegister(info.register);
                tuple.setStep(new CallStep());
                tuple.setValue(new ExpressionValue());
                return tuple;
            } else {
                TypeDescriptor type = vd.getType();
                machine.getSymbolTable().getCurrentClass().resolveSubTypes(type.getSubTypes());
                clazz = machine.getSymbolTable().findFullyQualifiedClass(type.getName());
                className = type.getName();
            }
        } else {
            clazz = machine.getSymbolTable().getCurrentClass();
            className = "Compiler Error: This should never happen";
        }

        if (clazz == null) {
            CompilerError error = new CompilerError(info.location.getStartLine(), "Class " + className + " has not been defined.", ErrorType.MISSING_CLASS);
            error.setFile(info.location.getFile());
            error.setColumn(info.location.getStartColumn());
            machine.getCompilerErrors().addError(error);
            tuple.setNextRegister(info.register);
            tuple.setStep(new CallStep());
            tuple.setValue(new ExpressionValue());
            return tuple;
        }

        //clazz.resolveVirtualMethodTemplates();
        method = clazz.getResolvedMethod(info.methodName, info.argumentTypes, vd, info.location);
        BlueprintDescriptor blueprint = clazz.getBlueprint(methodKey);
        SystemActionDescriptor systemAction = clazz.getResolvedSystemMethod(info.methodName, info.argumentTypes, vd, info.location);

        boolean isMethodErrorChecked = checkActionAccessModifier(method, vd, info.location);
        boolean isSystemActionErrorChecked = checkActionAccessModifier(systemAction, vd, info.location);

        
        if(method != null) {
            addMethodCasts(info, method, vd, clazz);
        }
        else if(systemAction != null) {
            addMethodCasts(info, systemAction, vd, clazz);
        }
        String containerKey = clazz.getContainer().getContainer();
        ContainerExecution cExec = machine.getBuilder().getContainer(containerKey);
        if (cExec == null) {
            cExec = machine.getBuilder().addContainer(clazz);

        }
        ClassExecution ce = cExec.getClassExecution(clazz.getStaticKey());
        if (ce == null) { //if it's null, add it to the builder
            ce = new ClassExecution();
            ce.setClassDescriptor(clazz);
            ce.setVm(machine);
            cExec.addClass(ce);
        }

        if (!isMethodErrorChecked && !isSystemActionErrorChecked && method == null && blueprint == null && systemAction == null) {
            CompilerError error = new CompilerError();
            error.setLineNumber(info.location.getStartLine());
            if(vd == null){
                error.setError("The action '" + methodKey + "' in class '" + machine.getSymbolTable().getCurrentClass() + "' has not been defined.");
            }else{
                error.setError("The action '" + methodKey + "' in class '" + vd.getType().getName() + "' has not been defined.");
            }
            error.setColumn(info.location.getStartColumn());
            error.setFile(info.location.getFile());
            error.setErrorType(ErrorType.MISSING_METHOD);
            CompilerErrorManager errorManager = machine.getCompilerErrors();
            errorManager.setErrorKey(machine.getSymbolTable().getCurrentClass().getFile().getFile().getAbsolutePath());
            errorManager.addError(error);
            tuple.setNextRegister(info.register);
            tuple.setStep(new CallStep());
            tuple.setValue(new ExpressionValue());

        } else if(!isMethodErrorChecked && !isSystemActionErrorChecked) {

            if(method == null && blueprint != null){
                method = blueprint;
            } else if(method == null && blueprint == null) {
                method = systemAction;
            } else{

                MethodExecution me = ce.getMethod(method.getStaticKey());
                //Bug Fix(Melissa): prevents inherited methods from being directly added
                //this may alter the way the program counter functions and could be brittle.
                MethodDescriptor virtualMethod = clazz.getVirtualMethod(method.getStaticKey());
                if (me == null && virtualMethod == null) { //The method to call has not been added to the builder so add it here
                    me = new MethodExecution();
                    me.setMethodDescriptor(method);
                    me.setVm(machine);
                    
                    ce.add(me);
                }
            }

            //Assign Arguments and return values
            ExpressionValue ret = new ExpressionValue();
            ret.setType(TypeDescriptor.getVoidType());
            ret.setRegister(info.register);
            ret.setResult(new Result());

            //setup the activation records
            ActivationRecord record = new ActivationRecord();
            int ret_offset = machine.getBuilder().getCurrentClass().getStepCount() + 2;
            record.setLineInformation(info.location);
            record.setReturnValueAbsolute(ret_offset);
            record.setReturnValue(ret);
            record.build(method);
            record.setReturnRegister(info.register);

            //setup the call step for execution
            CallStep step = new CallStep();
            step.setIsObjectCall(info.isObjectCall);
            step.setIsThisCall(info.isThisCall);
            step.setIsSoloMethodCall(info.isSoloMethod);
            step.setIsNestedMethodCall(info.isNested);
            if (info.isObjectCall) {
                step.setParentObject(machine.getSymbolTable().getCurrentScope().getVariable(info.variable.getName()));
            }

            step.setActivationRecord(record);
            step.setMethodCallee(method);

            step.setArguments(info.argumentRegisters);
            step.setLineInformation(info.location);
            ExpressionValue value = new ExpressionValue();
            
            TypeDescriptor returnType = method.getReturnType();
            //set type depending on whether we are dealing with a templated type
            if(vd != null){
                if(returnType.getTemplateName() != null){
                    MethodDescriptor resolvedTypeVirtualMethod = clazz.getResolvedTypeVirtualMethod(method.getStaticKey());
                    if(resolvedTypeVirtualMethod != null && resolvedTypeVirtualMethod.getReturnType() != null){
                        value = setReturnTemplateType(resolvedTypeVirtualMethod.getReturnType().getTemplateName(), resolvedTypeVirtualMethod.getReturnType(), clazz, vd);
                        method.addMappedTemplateType(value.getType().getTemplateName(), returnType);
                    }else{ //there were no virtual methods of resolved type
                        value = setReturnTemplateType(returnType.getTemplateName(), returnType, clazz, vd);
                        method.addMappedTemplateType(value.getType().getTemplateName(), returnType);
                    }
                }else if(returnType.hasSubTypes()){
                    TypeDescriptor newType = new TypeDescriptor(returnType);
                    value.setType(setReturnTemplateType(newType,clazz,vd));
                    method.addMappedTemplateType(value.getType().getTemplateName(), returnType);
                }else{
                    value.setType(returnType);
                }
            } else {
              value.setType(returnType);
            }
            
            if(value != null && value.getType() != null && value.getType().getTemplateName() != null)
                method.addMappedTemplateType(value.getType().getTemplateName(), returnType);
            
            value.setRegister(info.register);
            tuple.setNextRegister(info.register);
            
            //hack for the bytecode so we can identify the location of the call step
            if(machine.getBuilder().getCurrentMethod() == null){
                tuple.setStepCount(machine.getBuilder().getCurrentClass().getStepCount());
            }else{
                tuple.setStepCount(machine.getBuilder().getCurrentMethod().getStepCount());
            }
            
            machine.getBuilder().add(step);


            if(vd != null){
                Iterator<GenericDescriptor> templateTypes = vd.getTemplateTypes();
                while(templateTypes.hasNext()){//loop through variable descriptor template types
                    GenericDescriptor next = templateTypes.next();
                    Iterator<TypeDescriptor> allTypes = next.getAllTypes();
                    String typeName = next.getName();
                    while(allTypes.hasNext()){
                        TypeDescriptor type = allTypes.next();
                        if(type.getName().compareTo(typeName)==0
                                && type.isPrimitiveType()
                                && method.getReturnType().getTemplateName() != null
                                && method.getReturnType().isObjectClass()){
                            TypeDescriptor actualType = new TypeDescriptor(type);
                            actualType.convertToClass();
                            TypeCheckerResult extraResult = typeChecker.check(type, actualType, OperationEnum.IMPLICIT_CAST, false);
                            if (extraResult.getResult() != null) {
                                if (extraResult.getConversionResult() != TypeConversionResults.None) {
                                    UnaryOperationStep resultStep = (UnaryOperationStep) extraResult.generateOpcode();
                                    resultStep.setRegister(info.register);
                                    resultStep.setLineInformation(info.location);
                                    machine.getBuilder().add(resultStep);
                                }
                            }
                        }
                    }
                }
            }

            //finally, setup the result for the rest of the expression parsing
            ResultTuple sms = addStackMoveStep(info.register, info.location, step);
            tuple.setValue(value);
            tuple.setStep(sms.getStep());
            tuple.setNextRegister(sms.getNextRegister());
            
        }
        return tuple;
    }

    private boolean checkActionAccessModifier(MethodDescriptor method, VariableParameterCommonDescriptor vd, LineInformation callInformation){
        boolean isErrorChecked = false;
        if (method != null ) {
            if (method.getAccessModifier() != null) {//temporary fix: this should never be null
                if (vd != null && method.getAccessModifier().equals(AccessModifierEnum.PRIVATE)
                        && (method.getParent().getScopeString().compareTo(vd.getType().getStaticKey()) == 0 
                        && method.getParent().getScopeString().compareTo(callInformation.getClassName())!=0)) {
                    
                    //add the compiler error
                    String message = "Action '" + method.getStaticKey() + "' is private to the class '" + ((ClassDescriptor)method.getParent()).getName() + "'";
                    CompilerError error = new CompilerError(callInformation.getStartLine(),message, ErrorType.MISSING_METHOD);
                    error.setFile(callInformation.getFile());
                    CompilerErrorManager errorManager = machine.getCompilerErrors();
                    errorManager.setErrorKey(machine.getSymbolTable().getCurrentClass().getFile().getFile().getAbsolutePath());
                    errorManager.addError(error);
                    isErrorChecked = true;
                    
                    method = null;
                }
            }
        }
        return isErrorChecked;
    }
    /**
     * Sets the return type of a templated method (this include subtypes)
     * @param type
     * @param clazz
     * @param vd
     * @return
     */
    private TypeDescriptor setReturnTemplateType(TypeDescriptor type, ClassDescriptor clazz, VariableParameterCommonDescriptor vd){

        if(type.hasSubTypes()){
            Iterator<GenericDescriptor> subTypes = type.getSubTypes();
            while(subTypes.hasNext()){
                GenericDescriptor next = subTypes.next();
                next.setType(setReturnTemplateType(next.getType(), clazz, vd));
                next.setName(next.getType().getName());
            }
        }else if(type.getTemplateName() != null){
            ExpressionValue val = setReturnTemplateType(type.getTemplateName(),type,clazz,vd);
            return val.getType();
        }
        return type;
    }

    /**
     * Changes the templated return type over to it's actual type.
     *
     * @param templateName
     * @param defaultType
     * @param clazz
     * @param vd
     * @return
     */
    private ExpressionValue setReturnTemplateType(String templateName,TypeDescriptor defaultType, ClassDescriptor clazz, VariableParameterCommonDescriptor vd){
            boolean typeNotSet = true;
            ExpressionValue value = new ExpressionValue();
            Iterator<GenericDescriptor> templateTypes = vd.getTemplateTypes();
            Iterator<GenericDescriptor> templateVariables = clazz.getTemplateVariables();
            while(templateTypes.hasNext() && templateVariables.hasNext()){
                GenericDescriptor callerType = templateTypes.next();
                GenericDescriptor templateType = templateVariables.next();
                if(templateName != null && templateType.getStaticKey().compareTo(templateName) == 0){
                    TypeDescriptor type = callerType.getType();
                    type.setTemplateName(templateName);
                    //if(type.isPrimitiveType())
                    //    type.convertToClass();
                    value.setType(type);
                    typeNotSet = false;
                }
            }

            if (typeNotSet) {//values type was not set(set to the return type)
                value.setType(defaultType);
            }
            return value;
    }

    /**
     * Use this to add a call step to the builder for a call to a parent method.
     * Call steps call functions,  either on objects or inside of the current object.
     *
     * @param info
     * @return
     */
    public ResultTuple addParentCallStep(CallInfo info) {
        ResultTuple tuple = new ResultTuple();
        String methodKey = MethodDescriptor.autoGenerateKey(info.methodName, info.argumentTypes);

        ClassDescriptor clazz = null;
        MethodDescriptor method = null;
        String className = info.locatedIn;


        clazz = machine.getSymbolTable().findClassDescriptorFromCurrentClass(className);
        
        if(clazz == null){
            clazz = machine.getSymbolTable().findFullyQualifiedClassDescriptorFromCurrentClass(className);
        }

        if (clazz == null) {//compiler error parent class was not found
            CompilerError error = new CompilerError();
            error.setLineNumber(info.location.getStartLine());
            error.setError("The parent class '" + className + " has not been defined.");
            error.setColumn(info.location.getStartColumn());
            error.setFile(info.location.getFile());
            error.setErrorType(ErrorType.MISSING_PARENT);
            machine.getCompilerErrors().addError(error);
            tuple.setNextRegister(info.register);
            tuple.setStep(new CallStep());
            tuple.setValue(new ExpressionValue());
        } else {
            //clazz.resolveVirtualMethodTemplates();
            method = clazz.getResolvedMethod(info.methodName, info.argumentTypes, null, info.location);
            SystemActionDescriptor systemAction = clazz.getResolvedSystemMethod(info.methodName, info.argumentTypes, null, info.location);

            if (method != null) {
                addMethodCasts(info, method, null, null);
            } else if (systemAction != null) {
                addMethodCasts(info, systemAction, null, null);
            }
            String containerKey = clazz.getContainer().getContainer();
            ContainerExecution cExec = machine.getBuilder().getContainer(containerKey);
            if (cExec == null) {
                cExec = machine.getBuilder().addContainer(clazz);

            }
            ClassExecution ce = cExec.getClassExecution(clazz.getStaticKey());
            if (ce == null) { //if it's null, add it to the builder
                ce = new ClassExecution();
                ce.setClassDescriptor(clazz);
                ce.setVm(machine);
                cExec.addClass(ce);
            }

            if (method == null && systemAction == null) {
                CompilerError error = new CompilerError();
                error.setLineNumber(info.location.getStartLine());
                error.setError("The action '" + methodKey + "' in class '" + clazz.getName() + "' has not been defined.");
                error.setColumn(info.location.getStartColumn());
                error.setFile(info.location.getFile());
                error.setErrorType(ErrorType.MISSING_METHOD);
                CompilerErrorManager errorManager = machine.getCompilerErrors();
                errorManager.setErrorKey(machine.getSymbolTable().getCurrentClass().getFile().getFile().getAbsolutePath());
                errorManager.addError(error);
                tuple.setNextRegister(info.register);
                tuple.setStep(new CallStep());
                tuple.setValue(new ExpressionValue());

            } else {
                
                if (method == null && systemAction != null) {
                    method = systemAction;
                } else {
                    MethodExecution me = ce.getMethod(method.getStaticKey());
                    //Bug Fix(Melissa): prevents inherited methods from being directly added
                    //this may alter the way the program counter functions and could be brittle.
                    MethodDescriptor virtualMethod = clazz.getVirtualMethod(method.getStaticKey());
                    if (me == null && virtualMethod == null) { //The method to call has not been added to the builder so add it here
                        me = new MethodExecution();
                        me.setMethodDescriptor(method);
                        me.setVm(machine);
                        ce.add(me);
                    }

                }
                //Assign Arguments and return values
                ExpressionValue ret = new ExpressionValue();
                ret.setType(TypeDescriptor.getVoidType());
                ret.setRegister(info.register);
                ret.setResult(new Result());

                //setup the activation records
                ActivationRecord record = new ActivationRecord();
                int ret_offset = machine.getBuilder().getCurrentClass().getStepCount() + 2;
                record.setReturnValueAbsolute(ret_offset);
                record.setReturnValue(ret);
                record.build(method);
                record.setReturnRegister(info.register);

                //setup the call step for execution
                ParentCallStep step = new ParentCallStep();
                step.setIsObjectCall(info.isObjectCall);
                if (info.isObjectCall) {
                    step.setParentObject(machine.getSymbolTable().getCurrentScope().getVariable(info.variable.getName()));
                }

                step.setActivationRecord(record);
                step.setMethodCallee(method);

                step.setArguments(info.argumentRegisters);
                step.setLineInformation(info.location);
                ExpressionValue value = new ExpressionValue();
                value.setType(method.getReturnType());
                value.setRegister(info.register);
                tuple.setNextRegister(info.register);
                machine.getBuilder().add(step);

                //finally, setup the result for the rest of the expression parsing
                ResultTuple sms = addStackMoveStep(info.register, info.location, step);
                tuple.setValue(value);
                tuple.setStep(sms.getStep());
                tuple.setNextRegister(sms.getNextRegister());
            }
        }

        return tuple;
    }

    /**
     * This method adds implicit casts for any parameter that requires a type
     * conversion for Quorum to recognize it.
     *
     * @param info
     * @param method
     * @return whether or not the operation was successful.
     */
    private boolean addMethodCasts(CallInfo info, MethodDescriptor method, VariableParameterCommonDescriptor vd, ClassDescriptor clazz) {
        if (method != null && info.argumentTypes != null) {
            Parameters params = method.getParameters();
            if (params.size() != info.argumentTypes.size()) {
                return false;
            }

            for (int i = 0; i < params.size(); i++) {
                ExecutionStep arg = info.argumentSteps.get(i);
                TypeDescriptor specified = params.get(i).getType();
                TypeDescriptor actual = info.argumentTypes.get(i);

                if(specified.getTemplateName() != null && vd != null && actual.isPrimitiveType()){
                    if(vd.getType().getStaticKey().compareTo(clazz.getStaticKey()) == 0){

                        Iterator<GenericDescriptor> templateTypes = vd.getTemplateTypes();
                        Iterator<GenericDescriptor> templateVariables = clazz.getTemplateVariables();

                        while(templateTypes.hasNext() && templateVariables.hasNext()){

                            GenericDescriptor nextTemplateType = templateTypes.next();
                            GenericDescriptor nextClazzType = templateVariables.next();

                            if(nextClazzType.getType()!=null && nextClazzType.getType().getTemplateName() != null
                                    && nextClazzType.getType().getTemplateName().compareTo(specified.getTemplateName()) == 0){
                                TypeDescriptor templateSpecified = new TypeDescriptor(nextTemplateType.getType());
                                TypeCheckerResult result = typeChecker.check(templateSpecified, actual, OperationEnum.IMPLICIT_CAST, false);
                                if (result.getResult() != null) {
                                    UnaryOperationStep rezultStep = (UnaryOperationStep) result.generateOpcode();
                                    rezultStep.setLineInformation(info.location);
                                    rezultStep.setRegister(info.argumentRegisters.get(i));
                                    rezultStep.setResultRegister(info.argumentRegisters.get(i));
                                    machine.getBuilder().add(rezultStep);
                                    actual = templateSpecified;
                                }
                            }
                        }
                    }
                }

                TypeCheckerResult result = typeChecker.check(specified, actual, OperationEnum.IMPLICIT_CAST, false);
                if (result.getResult() != null) {
                    if (result.getConversionResult() != TypeConversionResults.None) {
                        UnaryOperationStep resultStep = (UnaryOperationStep) result.generateOpcode();
                        if(resultStep instanceof AutoBoxCreateStep){
                            AutoBoxCreateStep abStep = (AutoBoxCreateStep)resultStep;
                            abStep.setPrimitiveType(actual);
                        }
                        resultStep.setLineInformation(info.location);
                        resultStep.setRegister(info.argumentRegisters.get(i));
                        resultStep.setResultRegister(info.argumentRegisters.get(i));
                        int position = machine.getBuilder().add(resultStep);
                        

                        if(resultStep instanceof AutoBoxCreateStep){
                            arg.setCastStepLocation(position - 1);
                            result = typeChecker.checkAutobox(actual);
                            if(result.getResult() != null){
                                resultStep = (UnaryOperationStep) result.generateOpcode();
                                resultStep.setLineInformation(info.location);
                                resultStep.setRegister(info.argumentRegisters.get(i));
                                resultStep.setResultRegister(info.argumentRegisters.get(i));
                                machine.getBuilder().add(resultStep);
                            }
                        }else{
                            arg.setCastStepLocation(position);
                        }
                    }
                } else {
                        return false;
                }
            }

            return true;
        }
        return false;
    }

    /**
     * Use this to add a cast step to the builder.
     *
     * @param location
     * @param type
     * @param value
     * @param step
     * @param register
     * @param explicitCast
     * @return
     */
    public ResultTuple addCastStep(LineInformation location, TypeDescriptor type,
            ExpressionValue value, ExecutionStep step, int register, boolean explicitCast) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(register + 1);

        OperationEnum cast;
        if (explicitCast) {
            cast = OperationEnum.CAST;
            step.setIsCastStep(true);
        } else {
            cast = OperationEnum.IMPLICIT_CAST;
        }
        boolean passed = true;
        TypeCheckerResult typeCheckResult = getTypeChecker().check(type,
                value.getType(), cast, false, false);
        if (typeCheckResult.getResult() == null) {
            CompilerError error = new CompilerError(location.getStartLine(), typeCheckResult.getErrorMessage(), typeCheckResult.getErrorType());
            error.setFile(location.getFile());
            machine.getCompilerErrors().addError(error);
            passed = false;
        }

        ExpressionValue resultValue = new ExpressionValue();
        resultValue.setRegister(register);
        if (passed) {
            resultValue.setType(type);

            UnaryOperationStep resultStep = (UnaryOperationStep) typeCheckResult.generateOpcode();
            
            if (resultStep == null) {
                result.setStep(step);
                result.setValue(value);
            } else {
                if(resultStep instanceof ObjectCastStep){
                    ((ObjectCastStep)resultStep).setConvertedType(type);
                }
                resultStep.setLineInformation(location);
                resultStep.setRegister(value.getRegister());
                resultStep.setResultRegister(register);

                machine.getBuilder().add(resultStep);
                result.setStep(resultStep);
                result.setValue(resultValue);
            }
        } else {
            result.setStep(step);
            result.setValue(resultValue);
        }
        return result;
    }

    /**
     * Use this to add a cast step to the builder.
     *
     * @param location
     * @param type
     * @param value
     * @param step
     * @param register
     * @return
     */
    public ResultTuple addCastStep(LineInformation location, TypeDescriptor type,
            ExpressionValue value, ExecutionStep step, int register) {
        return addCastStep(location, type, value, step, register, true);
    }

    /**
     * Adds a return to a function, restoring its state to
     * the point at which it was called.
     *
     * @param location
     * @param value
     * @param step
     */
    public void addReturnStep(LineInformation location, ExpressionValue value, ExecutionStep step) {
        ReturnStep returnStep = new ReturnStep();
        MethodDescriptor currentMethod = machine.getBuilder().getCurrentMethod().getMethodDescriptor();

        if (currentMethod == null || currentMethod instanceof SystemActionDescriptor) {
            return;
        }

        returnStep.setLineInformation(location);
        if (value != null) {
            returnStep.setReturnRegister(value.getRegister());
            returnStep.setReturnsAValue(true);
        } else {
            TypeDescriptor returnType = currentMethod.getReturnType();
            if (returnType.getName().compareTo(TypeDescriptor.VOID) == 0) {
                returnStep.setReturnsAValue(false);
            } else {
                CompilerError error = new CompilerError();
                error.setLineNumber(location.getStartLine());
                error.setFile(machine.getSymbolTable().getCurrentClass().getFile().getStaticKey());
                error.setError("Cannot \"return now\" from an action that"
                        + " requires a return of type " + returnType.getName() + ".");
                error.setErrorType(ErrorType.INVALID_RETURN_NOW);
                machine.getCompilerErrors().addError(error);
            }
        }

        //add a return value to the control flow checker.
        CompilerError error = this.machine.getSymbolTable().getControlFlow().addReturnStatement(location);
        if (error != null) {
            machine.getCompilerErrors().addError(error);
        }

        //check the return type of the method with the return type
        //actually specified by the user.
        if (value != null) { //temporarily wrap this for testing as the architecture is built.
            TypeDescriptor returnRequired =
                    machine.getBuilder().getCurrentMethod().getMethodDescriptor().getReturnType();
            TypeDescriptor returnActual = value.getType();

            TypeCheckerResult result = typeChecker.check(returnRequired, returnActual);
            if (result.getResult() == null) {
                error = new CompilerError(location.getStartLine(), result.getErrorMessage(), result.getErrorType());
                machine.getCompilerErrors().addError(error);
            }else if(result.getConversionResult().compareTo(TypeConversionResults.None) != 0){
                UnaryOperationStep resultStep = (UnaryOperationStep) result.generateOpcode();
                resultStep.setLineInformation(location);
                resultStep.setRegister(value.getRegister());
                resultStep.setResultRegister(value.getRegister());
                machine.getBuilder().add(resultStep);
            }
        }
        returnStep.setMethod(machine.getBuilder().getCurrentMethod());
        machine.getBuilder().add(returnStep);
    }

    /**
     * Takes a parameter of a generic type (e.g., integer, text), and places
     * it into a register.
     *
     * @param register
     * @param location
     * @param type
     * @param res
     * @return
     */
    private ResultTuple addMoveStep(int register, LineInformation location,
            TypeDescriptor type, Result res) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(register + 1);

        ExpressionValue resultValue = new ExpressionValue();
        resultValue.setType(type);
        resultValue.setResult(res);
        resultValue.setRegister(register);

        result.setValue(resultValue);


        MoveStep resultStep = new MoveStep();
        resultStep.setLineInformation(location);
        resultStep.setTemp(register);
        resultStep.setValue(resultValue);

        result.setStep(resultStep);

        machine.getBuilder().add(resultStep);
        return result;
    }

    /**
     * Puts a value of type boolean into a register.
     *
     * @param register
     * @param location
     * @param value
     * @return
     */
    public ResultTuple addMoveStep(int register, LineInformation location, boolean value) {
        TypeDescriptor type = new TypeDescriptor();
        type.setName(TypeDescriptor.BOOLEAN);
        Result res = new Result();
        res.boolean_value = value;
        res.type = Result.BOOLEAN;
        return addMoveStep(register, location, type, res);
    }

    /**
     * Puts the value of null into a register.
     *
     * @param register
     * @param location
     * @return
     */
    public ResultTuple addMoveNullStep(int register, LineInformation location) {
        TypeDescriptor type = TypeDescriptor.getNullType();
        Result res = new Result();
        res.type = Result.NULL;
        return addMoveStep(register, location, type, res);
    }

    /**
     * Puts a value of type number into a register.
     *
     * @param register
     * @param location
     * @param value
     * @return
     */
    public ResultTuple addMoveStep(int register, LineInformation location, double value) {
        TypeDescriptor type = new TypeDescriptor();
        type.setName(TypeDescriptor.NUMBER);
        Result res = new Result();
        res.number = value;
        res.type = Result.NUMBER;
        return addMoveStep(register, location, type, res);
    }

    /**
     * puts a value of type integer into a register.
     *
     * @param register
     * @param location
     * @param value
     * @return
     */
    public ResultTuple addMoveStep(int register, LineInformation location, int value) {
        TypeDescriptor type = new TypeDescriptor();
        type.setName(TypeDescriptor.INTEGER);
        Result res = new Result();
        res.integer = value;
        res.type = Result.INTEGER;
        return addMoveStep(register, location, type, res);
    }

    /**
     * Puts a value of type text into a register.
     *
     * @param register
     * @param location
     * @param value
     * @return
     */
    public ResultTuple addMoveStep(int register, LineInformation location, String value) {
        TypeDescriptor type = new TypeDescriptor();
        type.setName(TypeDescriptor.TEXT);
        Result res = new Result();
        res.text = value.substring(1, value.length() - 1);
        res.type = Result.TEXT;
        return addMoveStep(register, location, type, res);
    }

    /** This method creates a step that moves a value from one register to another.
     *
     * @param leftRegister The register we are moving to
     * @param rightRegister The register we are moving from
     * @param location The line and column information for the step
     */
    public void addMoveRegistersStep(int leftRegister, int rightRegister, LineInformation location) {
        MoveRegistersStep step = new MoveRegistersStep();
        step.setLeftRegister(leftRegister);
        step.setRightRegister(rightRegister);
        step.setLineInformation(location);
        machine.getBuilder().add(step);
    }

    /**
     * Helper method for adding compiler errors to the virtual machine.
     *
     * @param err
     * @param location
     */
    private void addCompilerError(String err, LineInformation location, ErrorType errorType) {
        CompilerError error = new CompilerError();
        error.setFile(location.getFile());
        error.setLineNumber(location.getStartLine());
        error.setColumn(location.getStartColumn());
        error.setError(err);
        error.setErrorType(errorType);
        machine.getCompilerErrors().addError(error);
    }

    /**
     * Start an if statement block, including a conditional jump step
     * if the statement resolves to false and an opening scope if the condition
     * is true.
     *
     * @param info
     */
    public void startIf(IfStatementInfo info){
        SymbolTable symbolTable = this.machine.getSymbolTable();
        addConditionalJumpIfStep(info.ifFalseLabel, info.ifConditionalStep);

        CompilerError e = symbolTable.getControlFlow().addStatement(info.ifLocation);
        if (e != null) {
                machine.getCompilerErrors().addError(e);
        }
        
        addBeginScopeStep(info.ifStartLabel, "if");
        symbolTable.getControlFlow().ifStart();
        symbolTable.enterNextBlock();
    }

    /**
     * Add a conditional jump step to the builder for if statements. IF the
     * condition is false jump and if it is true continue.
     *
     * @param label the string representing where the jump will go to.
     * @param conditionalStep the jump step for a condition in an if(else if)
     * statement
     */
    public void addConditionalJumpIfStep(String label, ConditionalJumpIfStep conditionalStep){

        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        String al = label;
        builder.add(conditionalStep);
        builder.addUnresolvedJumpStep(al, conditionalStep);
        
        machine.getBuilder().addStepLabel(OpcodeType.IF, -1);
    }

    /**
     * Add a jump step for ending an if or else if statement.
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     */
    public void addIfEndJumpStep(IfStatementInfo info){
        //end the block
        addEndScopeStep("if");

        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        String al = info.endLabel;
        builder.add(info.ifJumpStep);
        builder.addUnresolvedJumpStep(al, info.ifJumpStep);
    }

    /**
     * End the if statement and close the compiler time scopes.
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     */
    public void endIf(IfStatementInfo info){
        SymbolTable symbolTable = this.machine.getSymbolTable();
        symbolTable.getControlFlow().ifEnd();
        symbolTable.popScope();

        String al = info.ifFalseLabel;
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        builder.addJumpLabelAndResolveSteps(al);
    }

    /**
     * Start the else if block updating runtime/compile time scopes and
     * adding a conditional jump step.
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     * @param counter the number representing the else if block. The first
     * else if block is 0 and the fifth else if block is 4.
     */
    public void startElseIf(IfStatementInfo info, int counter){
        SymbolTable symbolTable = this.machine.getSymbolTable();

        addConditionalJumpIfStep(info.elseIfFalseLabels.get(counter), info.elseIfConditionalSteps.get(counter));

        addBeginScopeStep(info.elseIfStartLabels.get(counter), "elseif");
        symbolTable.getControlFlow().ifElseStart();
        symbolTable.enterNextBlock();
    }

    /**
     * Add the else if end jump step. This will end the runtime scope of the
     * current else if block and allow a runtime jump to the end of the
     * else if statement.
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     * @param counter the number representing the else if block. The first
     * else if block is 0 and the fifth else if block is 4.
     */
    public void addElseIfEndJumpStep(IfStatementInfo info, int counter) {
        //end the block
        addEndScopeStep("elseif");

        JumpBaseStep jump = info.elseIfJumpSteps.get(counter);
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        String al = info.endLabel;
        builder.add(jump);
        //resolve a jump to the end of the if statement
        builder.addUnresolvedJumpStep(al, jump);
    }

    /**
     * End an else if block closing the compile time scope and resolving
     * any jumps.
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     * @param counter the number representing the else if block. The first
     * else if block is 0 and the fifth else if block is 4.
     */
    public void endElseIf(IfStatementInfo info, int counter){
        SymbolTable symbolTable = this.machine.getSymbolTable();
        symbolTable.getControlFlow().ifElseEnd();
        symbolTable.popScope();

        String al = info.elseIfFalseLabels.get(counter);
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        builder.addJumpLabelAndResolveSteps(al);
    }

    /**
     * Start the else statement, creating a new scope.
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     */
    public void startElse(IfStatementInfo info){
        SymbolTable symbolTable = this.machine.getSymbolTable();

        addBeginScopeStep(info.elseStartLabel, "else");
        symbolTable.getControlFlow().elseStart();
        symbolTable.enterNextBlock();
    }
    /**
     * End the else block and it's scopes.
     */
    public void endElse(){
        //end the block
        addEndScopeStep("else");
        
        SymbolTable symbolTable = this.machine.getSymbolTable();
        symbolTable.getControlFlow().elseEnd();
        symbolTable.popScope();
    }

    /**
     *
     * @param info stored information on the if statement (generated in
     * the symbol table walker).
     */
    public void endIfBlock(IfStatementInfo info){
        //resolve end jump (completed block)
        String al = info.endLabel;
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        builder.addJumpLabelAndResolveSteps(al);
        
        
        if(builder.getCurrentMethod() == null) {
            ExecutionStep get = builder.getCurrentClass().getSteps().get(builder.getCurrentClass().getStepCount() - 1);
            EndScopeStep st = (EndScopeStep)get;
            st.setLastIfScope(true);
        }
        else {
            ExecutionStep get = builder.getCurrentMethod().getSteps().get(builder.getCurrentMethod().getStepCount() - 1);
            if(get instanceof EndScopeStep){
                EndScopeStep st = (EndScopeStep)get;
                st.setLastIfScope(true);
            }else{
                get = builder.getCurrentMethod().getSteps().get(builder.getCurrentMethod().getStepCount() - 2);
                if(get instanceof EndScopeStep){
                    EndScopeStep st = (EndScopeStep)get;
                    st.setLastIfScope(true);
                }
            }
        }
    }

    /**
     * The start check method must be called when a new check statement
     * is beginning.
     *
     * @param info
     */
    public void startCheck(ExceptionInfo info) {
        //build and add a checkLandingPad
        CheckLandingPads checkLandingPads = new CheckLandingPads();

        SymbolTable symbolTable = this.machine.getSymbolTable();
        checkLandingPads.setClazz(symbolTable.getCurrentClass());
        checkLandingPads.setMethod(symbolTable.getCurrentMethod());
        this.machine.addCheckLandingPads(info.checkStartLabel, checkLandingPads);

        CompilerError e = symbolTable.getControlFlow().addStatement(info.location);
        if (e != null) {
                machine.getCompilerErrors().addError(e);
        }
            
        //start check and enter the check block
        addBeginCheckScopeStep(info.checkStartLabel, checkLandingPads);
        
        symbolTable.getControlFlow().checkStart();
        symbolTable.enterNextBlock();
    }

    /**
     * Helper method for adding a begin check step to the builder whenever
     * a block is entered.
     *
     * @param info
     */
    public void addBeginCheckScopeStep(String name, CheckLandingPads landingPads){
        BeginCheckScopeStep step = new BeginCheckScopeStep();
        step.setBlockName(name);
        step.setLandingPads(landingPads);

        machine.getBuilder().add(step);
    }

    public void addBeginDetectScopeStep(String name, boolean isFirstDetect, DetectInfo detect){
        BeginDetectScopeStep step = new BeginDetectScopeStep();
        step.setBlockName(name);
        step.setFirstDetect(isFirstDetect);
        step.setLandingPads(machine.getCheckLandingPads(name));
        step.setDetectInfo(detect);

        machine.getBuilder().add(step);
    }

    /**
     * Helper method for adding a begin scope step to the builder whenever
     * a block is entered.
     *
     * @param info
     */
    public void addBeginScopeStep(String name, String tag){
        BeginScopeStep step = new BeginScopeStep();
        step.setBlockName(name);
        step.setBlockTag(tag);

        machine.getBuilder().add(step);
    }

    /**
     * Helper method for adding a end scope step to the builder whenever a
     * block is exited. Use for scope changes caused by language constructs
     * such as if statements or loops.
     *
     */
    public void addEndScopeStep(String tag){
        EndScopeStep step = new EndScopeStep();
        step.setBlockTag(tag);
        machine.getBuilder().add(step);
    }

    /**
     * This method must be called when the parser reaches the end
     * of a check block in the parser. It will add a jump step.
     * 
     * @param info
     */
    public void addCheckEndJumpStep(ExceptionInfo info) {
        //end the block
        addEndScopeStep("check");

        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        String al = info.alwaysStartLabel;
        builder.add(info.checkJump);
        builder.addUnresolvedJumpStep(al, info.checkJump);
    }

    /**
     * Ends a check statement.
     * 
     * @param info
     */
    public void endCheck(ExceptionInfo info) {
        SymbolTable symbolTable = this.machine.getSymbolTable();
        symbolTable.getControlFlow().checkEnd();
        symbolTable.popScope();
    }

    /**
     * This method starts a new detect statement, accounting for any processing
     * and table updates in the runtime environment that must be accounted
     * for for this particular type of detect.
     * 
     * @param info
     * @param detectNum A zero-indexed number indicating which detect
     * this is in comparison to a particular check statement.
     */
    public void startDetect(ExceptionInfo info, int detectNum) {
        boolean firstDetect = false;
        //figure out which exception types are caught here
        DetectInfo detectInfo = new DetectInfo();
        Iterator<DetectParameter> iterator = info.detectParameters.iterator();
        while(iterator.hasNext()){
            DetectParameter next = iterator.next();
            ErrorTypeDescriptor nextParam = next.errorType;

            if(nextParam.isInvalidType()){//check that the alert type is of a Quorum Error Type.
                CompilerError error = new CompilerError(nextParam.getLineBegin(),
                    "The detect was given an invalid error type.", ErrorType.INVALID_ERROR);
                error.setFile(nextParam.getLineInformation().getFile());
                machine.getCompilerErrors().addError(error);
            }

            String paramName = nextParam.getStaticKey();

            //build landing pad
            detectInfo.setDetectParameter(next);

            IntermediateExecutionBuilder builder = this.machine.getBuilder();
            if(builder.getCurrentMethod() != null){
                detectInfo.setLocalLocation(builder.getCurrentMethod().getStepCount());
                builder.getCurrentMethod().getMethodDescriptor().addDetectType(info.checkStartLabel, paramName);
            }

            //add a marker for EACH type into the system. This is important, because
            //the final build will need to know how to catch for each and every type
            CheckLandingPads landingPads = this.machine.getCheckLandingPads(info.checkStartLabel);
            if(landingPads != null){
                if(landingPads.getAllDetects().isEmpty())
                    firstDetect = true;
                    
                landingPads.addLandingPad(paramName, detectInfo);
            }
        }

        this.addBeginDetectScopeStep(info.detectStartLabels.get(0), firstDetect, detectInfo);
    }

    /**
     * This method must be called when the parser reaches the end
     * of a detect block in the parser. It will add a jump step.
     *
     * @param info
     */
    public void addDetectEndJumpStep(ExceptionInfo info, JumpBaseStep jump) {
        //end the scope
        this.addEndScopeStep("detect");
        
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        String al = info.alwaysStartLabel;
        builder.add(jump);
        builder.addUnresolvedJumpStep(al, jump);
    }

    /**
     * Ends a detect statement, putting a jump step into the op-code stream.
     *
     * @param info
     * @param detectNum
     */
    public void endDetect(ExceptionInfo info, int detectNum) {
        //add a jump to the always, just like before.
        SymbolTable symbolTable = this.machine.getSymbolTable();
        symbolTable.getControlFlow().detectEnd();
        symbolTable.popScope();
    }

    /**
     * Starts an always block.
     *
     * @param info
     */
    public void startAlways(ExceptionInfo info, boolean isAlways) {
        String al = info.alwaysStartLabel;
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        builder.addJumpLabelAndResolveSteps(al);
        //build landing pad
        DetectInfo detectInfo = new DetectInfo();
        detectInfo.setAlawysBlock(isAlways);
        if (builder.getCurrentMethod() != null) {
            detectInfo.setLocalLocation(builder.getCurrentMethod().getStepCount() - 1);
            builder.getCurrentMethod().getMethodDescriptor().addDetectType(info.checkStartLabel, ErrorTypeDescriptor.ALWAYS.toString());
        }

        //add a marker for EACH type into the system. This is important, because
        //the final build will need to know how to catch for each and every type
        CheckLandingPads landingPads = this.machine.getCheckLandingPads(info.checkStartLabel);
        if (landingPads != null) {
            landingPads.addLandingPad(ErrorTypeDescriptor.ALWAYS.toString(), detectInfo);
        }

        if(info.hasAlways){
            //start the block
            this.addBeginScopeStep(info.alwaysStartLabel, "always");
            SymbolTable symbolTable = this.machine.getSymbolTable();
            symbolTable.enterNextBlock();
        }
    }

    /**
     * Ends an always block.
     * 
     * @param info
     */
    public void endAlways(ExceptionInfo info) {
        IntermediateExecutionBuilder builder = this.machine.getBuilder();
        if(info.hasAlways){
            //end the block
            this.addEndScopeStep("always");
            SymbolTable symbolTable = this.machine.getSymbolTable();
            symbolTable.popScope();
        }
        
        AlwaysEndStep step = new AlwaysEndStep();
        step.addCheckDetectLabel(info.checkStartLabel);
        builder.add(step);
    }
    
    /**
     * This method takes a variable and moves it into a register.
     *
     * @param location
     * @param expressionValue
     * @param expressionStep
     * @param resultRegister
     * @param type
     * @return
     */
    public ResultTuple addVariableMoveStep(LineInformation location,
            ExpressionValue expressionValue, ExecutionStep expressionStep,
            int resultRegister, QualifiedNameDescriptor type) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(resultRegister + 1);

        VariableMoveStep step = new VariableMoveStep();
        step.setLineInformation(location);

        VariableParameterCommonDescriptor vd = machine.getSymbolTable().getVariable(type.getStaticKey());

        ExpressionValue resultValue;
        if (vd == null) {
            resultValue = new ExpressionValue();
            resultValue.setType(new TypeDescriptor());
            setVariableNotDefinedError(location, type.getStaticKey());
        } else {
            if (!vd.isInitialized()) {
                this.addCompilerError("Variable " + vd.getName()
                        + " must be initialized before it is used.", location, ErrorType.MISSING_VARIABLE);
            }
            resultValue = StepFactory.createExpressionValue(resultRegister, new Result(), vd.getType());
            step.setTemp(resultRegister);
            step.setValue(vd);
            addUsedRegisterToCurrentMethod(resultRegister);
            
            machine.getBuilder().add(step);

        }
        result.setValue(resultValue);
        result.setStep(step);

        return result;
    }

    private void addUsedRegisterToCurrentMethod(int register) {
        MethodDescriptor currentMethod = this.machine.getSymbolTable().getCurrentMethod();
        if(currentMethod != null) {
            currentMethod.addUsedRegister(register);
        }
    }
    /**
     * Puts the value of "this" into a register.
     *
     * @param location
     * @param resultRegister
     * @return
     */
    public ResultTuple addMoveThisStep(LineInformation location,
            int resultRegister) {
        ClassDescriptor clazz = machine.getSymbolTable().getCurrentClass();
        TypeDescriptor type = TypeDescriptor.getClassType(clazz);

        ResultTuple result = new ResultTuple();
        result.setNextRegister(resultRegister + 1);

        ThisMoveStep step = new ThisMoveStep();
        step.setLineInformation(location);

        ExpressionValue resultValue;
        resultValue = StepFactory.createExpressionValue(resultRegister, new Result(), type);

        step.setTemp(resultRegister);
        machine.getBuilder().add(step);

        result.setValue(resultValue);
        result.setStep(step);

        return result;
    }

    /**
     * This method takes a variable in an object and moves it into a register.
     *
     * @param location
     * @param expressionValue
     * @param expressionStep
     * @param resultRegister
     * @param varName
     * @param idVariable
     * @return
     */
    public ResultTuple addVariableInObjectMoveStep(LineInformation location,
            ExpressionValue expressionValue, ExecutionStep expressionStep,
            int resultRegister, QualifiedNameDescriptor varName, String idVariable, ClassDescriptor parent) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(resultRegister + 1);

        VariableInObjectMoveStep step = new VariableInObjectMoveStep();
        step.setLineInformation(location);

        VariableParameterCommonDescriptor vd = machine.getSymbolTable().getVariable(varName.getStaticKey());

        //check the object
        ExpressionValue resultValue;
        if (vd == null) {
            resultValue = new ExpressionValue();
            resultValue.setType(new TypeDescriptor());
            setVariableNotDefinedError(location, varName.getStaticKey());
        } else {
            if (!vd.isInitialized()) {
                this.addCompilerError("Variable " + vd.getName()
                        + " must be initialized before it is used.", location, ErrorType.MISSING_VARIABLE);
            }
            //check the variable
            TypeDescriptor type = new TypeDescriptor();
            ClassDescriptor classDescriptor = machine.getSymbolTable().getClassDescriptor(vd.getType().toString());
            if(parent != null){
                    classDescriptor = parent;
            }
            
            if (classDescriptor != null) {
                VariableDescriptor variable = classDescriptor.getVariable(idVariable);
                if (variable == null || (variable.getAccessModifier().equals(variable.getAccessModifier().PRIVATE) &&
                        !classDescriptor.getStaticKey().equals(machine.getSymbolTable().getCurrentClass().getStaticKey()))) {
                    this.addCompilerError("Variable " + idVariable
                            + " has not been defined in the class " + vd.getName() + ".", location, ErrorType.MISSING_VARIABLE);
                } else {
                    type = variable.getType();
                }
            }

            resultValue = StepFactory.createExpressionValue(resultRegister, new Result(), type);
            step.setTemp(resultRegister);
            step.setObj(vd);
            step.setParent(parent);
            step.setVariableName(idVariable);
            step.setVariableType(type);
            machine.getBuilder().add(step);

        }
        result.setValue(resultValue);
        result.setStep(step);

        return result;
    }

    /**
     *
     * @param location
     * @param expressionValue
     * @param expressionStep
     * @param resultRegister
     * @param variableName
     * @param parent
     * @return
     */
    public ResultTuple addParentVariableMoveStep(LineInformation location,
            ExpressionValue expressionValue, ExecutionStep expressionStep,
            int resultRegister, QualifiedNameDescriptor variableName, ClassDescriptor parent) {

        ResultTuple result = new ResultTuple();
        result.setNextRegister(resultRegister + 1);

        boolean foundParent = false;

        ParentVariableMoveStep step = new ParentVariableMoveStep();
        step.setLineInformation(location);

        Iterator<ClassDescriptor> ancestors = machine.getSymbolTable().getCurrentClass().getFlattenedListOfParents();
        while (!foundParent && ancestors.hasNext()) {
            ClassDescriptor anc = ancestors.next();
            if (anc.getStaticKey().equals(parent.getStaticKey())) {
                foundParent = true;
            }
        }

        if (!foundParent) {
            CompilerError error = new CompilerError();

            error.setLineNumber(location.getStartLine());
            error.setError("The class '" + parent.getStaticKey() + "' is not a parent of the class "
                    + machine.getSymbolTable().getCurrentClass().getStaticKey() + ".");
            error.setColumn(location.getStartColumn());
            error.setErrorType(ErrorType.MISSING_PARENT);
            error.setFile(location.getFile());
            machine.getCompilerErrors().addError(error);
        }

        VariableDescriptor vd = parent.getVariable(variableName.getStaticKey());

        ExpressionValue resultValue;
        if (vd == null) {
            resultValue = new ExpressionValue();
            resultValue.setType(new TypeDescriptor());
            setVariableNotDefinedError(location, variableName.getStaticKey());
        } else {
            if (!vd.isInitializedClassVariable() && !vd.isInitialized()) {
                this.addCompilerError("Variable " + vd.getName()
                        + " is not initialized in the parent class's initialization block.", location, ErrorType.MISSING_VARIABLE);
            }
            resultValue = StepFactory.createExpressionValue(resultRegister, new Result(), vd.getType());
            step.setTemp(resultRegister);
            step.setValue(vd);
            step.setLocatedInClass(parent);
            machine.getBuilder().add(step);

        }

        result.setValue(resultValue);
        result.setStep(step);

        return result;
    }
    
    public ResultTuple addMeVariableMoveStep(LineInformation location,
            ExpressionValue expressionValue, ExecutionStep expressionStep,
            int resultRegister, QualifiedNameDescriptor variableName) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(resultRegister + 1);

        MeVariableMoveStep step = new MeVariableMoveStep();
        step.setLineInformation(location);

        VariableParameterCommonDescriptor vd = machine.getSymbolTable().getCurrentClass().getVariable(variableName.getStaticKey());

        ExpressionValue resultValue;
        if (vd == null) {
            resultValue = new ExpressionValue();
            resultValue.setType(new TypeDescriptor());
            setVariableNotDefinedError(location, variableName.getStaticKey());
        } else {
            if (!vd.isInitialized()) {
                this.addCompilerError("Variable " + vd.getName()
                        + " must be initialized before it is used.", location, ErrorType.MISSING_VARIABLE);
            }
            resultValue = StepFactory.createExpressionValue(resultRegister, new Result(), vd.getType());
            step.setTemp(resultRegister);
            step.setValue(vd);
            addUsedRegisterToCurrentMethod(resultRegister);
            
            machine.getBuilder().add(step);

        }
        result.setValue(resultValue);
        result.setStep(step);

        return result;
    }

    private void setVariableNotDefinedError(LineInformation location, String variableName) {
        CompilerError error = new CompilerError();

        error.setLineNumber(location.getStartLine());
        error.setError("The variable '" + variableName + "' has not been defined. Did you spell the variable's name correctly?");
        error.setColumn(location.getStartColumn());
        error.setFile(location.getFile());
        error.setErrorType(ErrorType.MISSING_VARIABLE);
        machine.getCompilerErrors().addError(error);
    }

    /**
     * Creates a step for printing to the console and its it to the builder.
     *
     * @param location
     * @param value
     * @param step
     */
    public void addPrintStep(LineInformation location, ExpressionValue value, ExecutionStep step) {
        //typecheck the values and step information here.
        if (value.getType() == null || !value.getType().isText()) {//try casting to a text value
            this.addCastStep(location, TypeDescriptor.getTextType(),
                    value, step, value.getRegister(), false);
        }
        
        CompilerError e = this.machine.getSymbolTable().getControlFlow().addStatement(location);
        if (e != null) {
            machine.getCompilerErrors().addError(e);
        }

        PrintStep printStep = new PrintStep();
        printStep.setLineInformation(location);

        printStep.setParameterRegister(value.getRegister());
        machine.getBuilder().add(printStep);
    }

    /**
     * Adds a step for speaking a value and adds it to the builder.
     *
     * @param location
     * @param value
     * @param step
     */
    public void addSpeakStep(LineInformation location, ExpressionValue value, ExecutionStep step) {
        //typecheck the values and step information here.
        if (value.getType() == null || !value.getType().isText()) {//try casting to a text value
            this.addCastStep(location, TypeDescriptor.getTextType(), value,
                    step, value.getRegister(), false);
        }
        
        CompilerError e = this.machine.getSymbolTable().getControlFlow().addStatement(location);
        if (e != null) {
            machine.getCompilerErrors().addError(e);
        }
        
        SpeakStep speakStep = new SpeakStep();
        speakStep.setLineInformation(location);

        speakStep.setParameterRegister(value.getRegister());
        machine.getBuilder().add(speakStep);
    }

    /**
     * Adds a step for throwing an exception and adds it to the builder.
     *
     * @param location
     * location of the alert statement.
     *
     * @param value
     * The expression value of the error parameter in the
     * alert statement. 
     *
     * @param step
     * The step of the error parameter in the alert statement.
     */
    public void addAlertStep(LineInformation location, ErrorTypeDescriptor errorType, ExpressionValue value, ExecutionStep step){
        //add a return value to the control flow checker.
        CompilerError returnError = this.machine.getSymbolTable().getControlFlow().addReturnStatement(location);
        if (returnError != null) {
            machine.getCompilerErrors().addError(returnError);
        }
        
         if(errorType != null){
            AlertStep alertStep = new AlertStep();

            //check that the alerts error type is a valid ErrorTypeDescriptor
            //then add the value to the alert step.
            if(errorType.isInvalidType()){//check that the alert type is of a Quorum Error Type.
                CompilerError error = new CompilerError(location.getStartLine(), "The alert was given an invalid error type.", ErrorType.INVALID_ERROR );
                error.setFile(location.getFile());
                machine.getCompilerErrors().addError(error);
            }else{
                alertStep.setErrorType(errorType);
            }

            alertStep.setLineInformation(location);
            alertStep.setParameterRegister(value.getRegister());

            machine.getBuilder().add(alertStep);
        }else{
            SimpleAlertStep alertStep = new SimpleAlertStep();

            alertStep.setMessageRegister(value.getRegister());
            alertStep.setLineInformation(location);

            //typecheck the values and step information here.
            if (value.getType() == null || !value.getType().isText()) {//try casting to a text value
                this.addCastStep(location, TypeDescriptor.getTextType(), value,
                        step, value.getRegister(), false);
            }

            machine.getBuilder().add(alertStep);

        }
    }

    /**
     * Use this to add a input step to the builder.
     *
     * @param location
     * @param value
     * @param step
     * @param resultRegister
     * @return
     */
    public ResultTuple addInputStep(LineInformation location, ExpressionValue value, ExecutionStep step, int resultRegister) {
        ResultTuple result = new ResultTuple();
        result.setNextRegister(resultRegister + 1);

        //typecheck the values and step information here.
        if (value.getType() == null || !value.getType().isText()) {//try casting to a text value
            this.addCastStep(location, TypeDescriptor.getTextType(), value,
                    step, value.getRegister(), false);
        }
        InputStep inputStep = new InputStep();
        inputStep.setLineInformation(location);
        inputStep.setResultRegister(resultRegister);
        inputStep.setParameterRegister(value.getRegister());



        Result res = new Result();
        res.text = "";
        res.type = Result.TEXT;

        ExpressionValue resultValue = new ExpressionValue();
        resultValue.setType(TypeDescriptor.getTextType());
        resultValue.setResult(res);
        resultValue.setRegister(resultRegister);

        result.setValue(resultValue);
        result.setStep(inputStep);

        machine.getBuilder().add(inputStep);
        return result;
    }

    /**
     * @return the machine
     */
    public QuorumVirtualMachine getMachine() {
        return machine;
    }

    /**
     * @param machine the machine to set
     */
    public void setMachine(QuorumVirtualMachine machine) {
        this.machine = machine;
    }

    /**
     * @return the typeChecker
     */
    public TypeChecker getTypeChecker() {
        return typeChecker;
    }

    /**
     * @param typeChecker the typeChecker to set
     */
    public void setTypeChecker(TypeChecker typeChecker) {
        this.typeChecker = typeChecker;
    }

    /**
     * This class holds information regarding binary operation steps. It is
     * effectively a helper class to avoid code duplication.
     *
     */
    protected class BinaryOperationInformation {

        int register;
        ExpressionValue leftValue;
        ExecutionStep leftStep;
        ExpressionValue rightValue;
        ExecutionStep rightStep;
        TypeDescriptor leftType;
        TypeDescriptor rightType;
        LineInformation location;
        TypeCheckerResult typeCheckResult;
        ResultTuple result;
        ExpressionValue resultValue;
    }

    /**
     * This class holds information regarding unary operation steps. It is
     * effectively a helper class to avoid code duplication.
     */
    protected class UnaryOperationInformation {

        int register;
        ExpressionValue value;
        ExecutionStep step;
        LineInformation location;
        TypeCheckerResult typeCheckResult;
        ResultTuple result;
        ExpressionValue resultValue;
    }

    /**
     * This class holds information regarding assignment steps. It is
     * effectively a helper class to avoid code duplication.
     */
    protected class AssignmentStepInformation {

        LineInformation location;
        String variableInObjectName;
        String variableName;
        ExpressionValue rightValue;
        ExecutionStep rightStep;
        boolean localOnly;
        ClassDescriptor parent;
        boolean isAssignedToMe;
        boolean hasRightHandSide;
    }
}
