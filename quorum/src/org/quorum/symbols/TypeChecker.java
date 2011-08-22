/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.HashMap;
import java.util.Iterator;
import org.quorum.steps.AutoBoxCreateStep;
import org.quorum.steps.UnaryOperationStep;

/** 
 * This class checks expressions returned from the abstract syntax tree
 * to determine if they are semantically legal in a programming language.
 *
 * @author Andreas Stefik
 */
public class TypeChecker {

    /**
     * Stores all possible type check operations in a format retrievable
     * for quick answers to whether an operation is legal and
     * what opcode should be used if it is.
     */
    HashMap<String, TypeCheckerResult> results;

    /**
     * Generates a key for a particular type check operation.
     *
     * @param left The type of the left operand.
     * @param right The type of the right operand.
     * @param op The operation to be performed (e.g., +, -, *, /).
     * @param assignment
     * @return
     */
    private String generateTypeCheckKey(String left, String right, OperationEnum op, boolean assignment) {
        return generateTypeCheckKey(left, right, op, assignment, false);
    }

    /**
     * Generates a key for a particular type check operation. In this case,
     * the user may pass a flag suggesting to generate an assignment statement
     * that forces variables into the local scope.
     * 
     * @param left
     * @param right
     * @param op
     * @param assignment
     * @param local
     * @return
     */
    private String generateTypeCheckKey(String left, String right,
            OperationEnum op, boolean assignment, boolean local) {
        String a = "f";
        if(assignment) a = "t";
        if(op != null) {
            return left  + "$" + right + "$" + op + "$" + a + "$" + local;
        }
        else {
            return left  + "$" + right + "$" + a + "$" + local;
        }
    }
    
    /**
     * Fills the type check table with appropriate rules for a particular
     * programming language.
     */
    private void generateTypeCheckingTable() {
        ////////////////////////////////////////////////////////////////////////
        //INTEGER INTEGER
        ////////////////////////////////////////////////////////////////////////
        //+
        TypeCheckerResult result = new TypeCheckerResult();
        String key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.PLUS, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_PLUS);
        results.put(key, result);

        //-
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER, 
                TypeDescriptor.INTEGER, OperationEnum.MINUS, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_MINUS);
        results.put(key, result);

        //*
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.TIMES, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_TIMES);
        results.put(key, result);

        // / (divide)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.DIVIDE, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_DIVIDE);
        results.put(key, result);

        // Mod
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.MODULUS, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_MODULUS);
        results.put(key, result);

        // = (equals)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_EQUALS);
        results.put(key, result);

        // != (equals)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_NOT_EQUALS);
        results.put(key, result);

        // >=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.GREATER_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_GREATER_EQUALS);
        results.put(key, result);

        // >
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.GREATER, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_GREATER);
        results.put(key, result);

        // <=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.LESS_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_LESS_EQUALS);
        results.put(key, result);

        // <
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.LESS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_LESS);
        results.put(key, result);

        // <
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.LESS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_LESS);
        results.put(key, result);

        // Range
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.RANGE, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_RANGE);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //NUMBER NUMBER
        ////////////////////////////////////////////////////////////////////////
        //+
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.PLUS, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_PLUS);
        results.put(key, result);

        //-
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.MINUS, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_MINUS);
        results.put(key, result);

        //*
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.TIMES, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_TIMES);
        results.put(key, result);

        // / (divide)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.DIVIDE, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_DIVIDE);
        results.put(key, result);

        // Mod
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.MODULUS, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_MODULUS);
        results.put(key, result);

        // = (equals)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_EQUALS);
        results.put(key, result);

        // != (equals)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_NOT_EQUALS);
        results.put(key, result);

        // >=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.GREATER_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_GREATER_EQUALS);
        results.put(key, result);

        // >
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.GREATER, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_GREATER);
        results.put(key, result);

        // <=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.LESS_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_LESS_EQUALS);
        results.put(key, result);

        // <
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.LESS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_LESS);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //BOOLEAN BOOLEAN
        ////////////////////////////////////////////////////////////////////////
        //and
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.AND, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_AND);
        results.put(key, result);

        //or
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.OR, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_OR);
        results.put(key, result);

        //=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_EQUALS);
        results.put(key, result);

        //!=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_NOT_EQUALS);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //TEXT TEXT
        ////////////////////////////////////////////////////////////////////////
        //+ (concatenate)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.PLUS, false);
        setNoConvertResult(result, TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_PLUS);
        results.put(key, result);

        //=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_EQUALS);
        results.put(key, result);

        //!=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_NOT_EQUALS);
        results.put(key, result);

        //>=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.GREATER_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_GREATER_EQUALS);
        results.put(key, result);

        //>
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.GREATER, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_GREATER);
        results.put(key, result);

        //<=
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.LESS_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_LESS_EQUALS);
        results.put(key, result);

        //<
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.LESS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_LESS);
        results.put(key, result);


        ////////////////////////////////////////////////////////////////////////
        //Assignment Operations
        ////////////////////////////////////////////////////////////////////////
        //Integer integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, null, true, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_INTEGER_INTEGER);
        results.put(key, result);

        //Integer Integer Local
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, null, true, true);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_INTEGER_INTEGER_LOCAL);
        results.put(key, result);

        //Text Text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, null, true, false);
        setNoConvertResult(result, TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_TEXT_TEXT);
        results.put(key, result);

        //Text Text local
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, null, true, true);
        setNoConvertResult(result, TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_TEXT_TEXT_LOCAL);
        results.put(key, result);

        //Number Number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, null, true, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_NUMBER_NUMBER);
        results.put(key, result);

        //Number Number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, null, true, true);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_NUMBER_NUMBER_LOCAL);
        results.put(key, result);

        //Number Integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER, null, true, false);
        setWideningConversionResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_NUMBER_INTEGER);
        results.put(key, result);

        //Number Integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER, null, true, true);
        setWideningConversionResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_NUMBER_INTEGER_LOCAL);
        results.put(key, result);

        //Booolean Boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, null, true, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_BOOLEAN_BOOLEAN);
        results.put(key, result);

        //Booolean Boolean Local
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, null, true, true);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_BOOLEAN_BOOLEAN_LOCAL);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //Unary Operations
        ////////////////////////////////////////////////////////////////////////
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                null, OperationEnum.NOT, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.UNARY_BOOLEAN_NOT);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //Return types
        ////////////////////////////////////////////////////////////////////////
        //integer integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.RETURN, false);
        setNoConvertResult(result, TypeDescriptor.getIntegerType());
        results.put(key, result);

        //text text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.RETURN, false);
        setNoConvertResult(result, TypeDescriptor.getTextType());
        results.put(key, result);

        //number number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.RETURN, false);
        setNoConvertResult(result, TypeDescriptor.getNumberType());
        results.put(key, result);

        //boolean boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.RETURN, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        results.put(key, result);

        //number integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER, OperationEnum.RETURN, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_INTEGER_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        //integer to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.INTEGER, OperationEnum.RETURN, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_INTEGER_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        //number to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.NUMBER, OperationEnum.RETURN, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_NUMBER_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);


        //boolean to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.RETURN, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_CAST);
        results.put(key, result);

        //boolean to number
//        result = new TypeCheckerResult();
//        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
//                TypeDescriptor.BOOLEAN, OperationEnum.RETURN, false);
//        result.setConversionResult(TypeConversionResults.Widening);
//        result.setResult(TypeDescriptor.getNumberType());
//        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_BOOLEAN_CAST);
//        results.put(key, result);

        //boolean to integer
//        result = new TypeCheckerResult();
//        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
//                TypeDescriptor.BOOLEAN, OperationEnum.RETURN, false);
//        result.setConversionResult(TypeConversionResults.Widening);
//        result.setResult(TypeDescriptor.getIntegerType());
//        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_BOOLEAN_CAST);
//        results.put(key, result);

        //boolean to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.BOOLEAN, OperationEnum.RETURN, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_BOOLEAN_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //INTEGER NUMBER
        ////////////////////////////////////////////////////////////////////////
        //+
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.PLUS, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_PLUS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.PLUS, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_PLUS);


        //-
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.MINUS, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_MINUS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.MINUS, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_MINUS);

        //*
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.TIMES, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_TIMES);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.TIMES, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_TIMES);

        // divide
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.DIVIDE, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_DIVIDE);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.DIVIDE, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_DIVIDE);

        // mod
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.MODULUS, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_MODULUS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.MODULUS, false, TypeDescriptor.getNumberType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_MODULUS);

        // >=
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.GREATER_EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_GREATER_EQUALS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.GREATER_EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_GREATER_EQUALS);

        // >
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.GREATER, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_GREATER);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.GREATER, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_GREATER);

        // ==
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_EQUALS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_EQUALS);

        // !=
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.NOT_EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_NOT_EQUALS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.NOT_EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_NOT_EQUALS);

        // <=
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.LESS_EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_LESS_EQUALS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.LESS_EQUALS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_LESS_EQUALS);

        // <
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.NUMBER,
                OperationEnum.LESS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.INTEGER_NUMBER_LESS);
        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.INTEGER,
                OperationEnum.LESS, false, TypeDescriptor.getBooleanType(),
                TypeCheckerStepFactory.NUMBER_INTEGER_LESS);


        ////////////////////////////////////////////////////////////////////////
        //TEXT conversion
        ////////////////////////////////////////////////////////////////////////
        addImplicitTypeCast(TypeDescriptor.INTEGER, TypeDescriptor.TEXT,
                OperationEnum.PLUS, false, TypeDescriptor.getTextType(),
                TypeCheckerStepFactory.INTEGER_TEXT_PLUS);
        addImplicitTypeCast(TypeDescriptor.TEXT, TypeDescriptor.INTEGER,
                OperationEnum.PLUS, false, TypeDescriptor.getTextType(),
                TypeCheckerStepFactory.TEXT_INTEGER_PLUS);

        addImplicitTypeCast(TypeDescriptor.NUMBER, TypeDescriptor.TEXT,
                OperationEnum.PLUS, false, TypeDescriptor.getTextType(),
                TypeCheckerStepFactory.NUMBER_TEXT_PLUS);
        addImplicitTypeCast(TypeDescriptor.TEXT, TypeDescriptor.NUMBER,
                OperationEnum.PLUS, false, TypeDescriptor.getTextType(),
                TypeCheckerStepFactory.TEXT_NUMBER_PLUS);

        addImplicitTypeCast(TypeDescriptor.BOOLEAN, TypeDescriptor.TEXT,
                OperationEnum.PLUS, false, TypeDescriptor.getTextType(),
                TypeCheckerStepFactory.BOOLEAN_TEXT_PLUS);
        addImplicitTypeCast(TypeDescriptor.TEXT, TypeDescriptor.BOOLEAN,
                OperationEnum.PLUS, false, TypeDescriptor.getTextType(),
                TypeCheckerStepFactory.TEXT_BOOLEAN_PLUS);


        ////////////////////////////////////////////////////////////////////////
        //TYPE CASTING - EXPLICIT
        ////////////////////////////////////////////////////////////////////////
        //text to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_CAST);
        results.put(key, result);

        //integer to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_CAST);
        results.put(key, result);

        //number to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_CAST);
        results.put(key, result);

        //text to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.TEXT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Narrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_TEXT_CAST);
        results.put(key, result);

        //integer to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.INTEGER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_INTEGER_CAST);
        results.put(key, result);

        //text to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.TEXT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Narrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_TEXT_CAST);
        results.put(key, result);

        //number to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.NUMBER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_NUMBER_CAST);
        results.put(key, result);

        //number to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.NUMBER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Narrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_NUMBER_CAST);
        results.put(key, result);

        //integer to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_INTEGER_CAST);
        results.put(key, result);

        //boolean to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_CAST);
        results.put(key, result);

        //boolean to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.BOOLEAN, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_BOOLEAN_CAST);
        results.put(key, result);

        //number to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.NUMBER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Narrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_NUMBER_CAST);
        results.put(key, result);

        //boolean to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.BOOLEAN, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_BOOLEAN_CAST);
        results.put(key, result);

        //integer to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.INTEGER, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Narrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_INTEGER_CAST);
        results.put(key, result);

        //boolean to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.BOOLEAN, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_BOOLEAN_CAST);
        results.put(key, result);

        //text to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.TEXT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.Narrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_TEXT_CAST);
        results.put(key, result);

        ////////////////////////////////////////////////////////////////////////
        //TYPE CASTING - IMPLICIT
        ////////////////////////////////////////////////////////////////////////
        //text to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_CAST);
        results.put(key, result);

        //integer to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_CAST);
        results.put(key, result);

        //number to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_CAST);
        results.put(key, result);

        //integer to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.INTEGER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_INTEGER_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        //number to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.NUMBER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_NUMBER_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        //integer to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_INTEGER_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        //boolean to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_CAST);
        results.put(key, result);

        //boolean to number
//        result = new TypeCheckerResult();
//        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
//                TypeDescriptor.BOOLEAN, OperationEnum.IMPLICIT_CAST, false);
//        result.setConversionResult(TypeConversionResults.Widening);
//        result.setResult(TypeDescriptor.getNumberType());
//        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_BOOLEAN_CAST);
//        results.put(key, result);

        //boolean to integer
//        result = new TypeCheckerResult();
//        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
//                TypeDescriptor.BOOLEAN, OperationEnum.IMPLICIT_CAST, false);
//        result.setConversionResult(TypeConversionResults.Widening);
//        result.setResult(TypeDescriptor.getIntegerType());
//        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_BOOLEAN_CAST);
//        results.put(key, result);

        //boolean to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.BOOLEAN, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.Widening);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_BOOLEAN_CAST);
        result.setConversionScore(TypeConversionEnum.LOSSLESS_CONVERT);
        results.put(key, result);

        this.addAutoBoxedTypes();
    }

    private void addImplicitTypeCast(String left, String right,
            OperationEnum op, boolean assignment, TypeDescriptor typeResult,
            TypeCheckerStepFactory fact) {

        TypeCheckerResult result = new TypeCheckerResult();
        String key = this.generateTypeCheckKey(left, right, op, assignment);
        setWideningConversionResult(result, typeResult);
        result.setOpcodeGenerator(fact);
        results.put(key, result);
    }

    /**
     * Helper method for handling the case when one type is implicitly widened
     * to accommodate a larger data type (e.g., integer to number).
     *
     * @param result
     * @param resultType
     */
    private void setWideningConversionResult(TypeCheckerResult result, TypeDescriptor resultType) {
        result.setConversionResult(TypeConversionResults.Widening);
        result.setErrorMessage("");
        result.setResult(resultType);
    }

    /**
     * Helper method for handling the case when no type conversion has
     * occurred.
     * 
     * @param result
     * @param resultType
     */
    private void setNoConvertResult(TypeCheckerResult result, TypeDescriptor resultType) {
        result.setConversionResult(TypeConversionResults.None);
        result.setErrorMessage("");
        result.setResult(resultType);
    }

    /**
     * Creates a new TypeChecker
     */
    public TypeChecker() {
        results = new HashMap<String, TypeCheckerResult>();
        generateTypeCheckingTable();
    }

    /**
     * Checks the two type descriptor and generates an appropriate opcode, if
     * one exists, for the pairing. This operation should not be used for
     * unary operations like (Number) 15, or "not true."
     * 
     * @param left
     * @param right
     * @param op
     * @param assignment Determines whether this should generate an assignment
     * statement, if the operation is legal.
     * @return
     */
    public TypeCheckerResult check(TypeDescriptor left, TypeDescriptor right, 
            OperationEnum op, boolean assignment) {
        return check(left, right, op, assignment, false);
    }

    /**
     * Checks the two type descriptor and generates an appropriate opcode, if
     * one exists, for the pairing. This operation should not be used for
     * unary operations like (Number) 15, or "not true."
     * 
     * @param left
     * @param right
     * @param op
     * @param assignment Determines whether this should generate an assignment
     * statement, if the operation is legal.
     * @param local If this method's opcode generator generates an assignment
     * statement and this flag is true, the assignment statement will force
     * the variable into the local scope, as opposed to possibly placing it
     * into the parent's scope.
     * @return
     */
    public TypeCheckerResult check(TypeDescriptor left, TypeDescriptor right,
            OperationEnum op, boolean assignment, boolean local) {
        String leftKey = "";
        TypeCheckerResult result = null;

        if(left != null) {
            leftKey = left.getStaticKey();
        }
        
        String rightKey = null;

        if(right != null) {
            rightKey = right.getStaticKey();
        }

        if(left != null && right != null && !right.isNull()){
            TypeDescriptor rTemp = new TypeDescriptor(right);
            TypeDescriptor lTemp = new TypeDescriptor(left);
            if(left.hasSubTypes() && right.hasSubTypes()){
                result = new TypeCheckerResult();
                result = checkTemplateTypes(left, right, op, assignment, local);
                if(result == null){
                    result = new TypeCheckerResult();
                    result.setConversionResult(TypeConversionResults.None);
                    result.setErrorMessage("Cannot assign a value of type \"" +
                        right.generateTypeKey() + "\"" +
                        " to a variable of type \"" + left.generateTypeKey() + ".\"");
                    return result;
                }
                //return result;
            }else if(left.hasSubTypes() || right.hasSubTypes()){
                result = new TypeCheckerResult();
                result = checkTemplateTypes(left, right, op, assignment, local);
                if(result == null){
                    result = new TypeCheckerResult();
                    result.setConversionResult(TypeConversionResults.None);
                    result.setErrorMessage("Cannot assign a value of type \"" +
                        rTemp.generateTypeKey() + "\"" +
                        " to a variable of type \"" + lTemp.generateTypeKey() + ".\"");
                    return result;
                }
                
            }

            if(op != null && (op.compareTo(OperationEnum.EQUALS) == 0 || op.compareTo(OperationEnum.NOT_EQUALS) == 0)){
                if(!left.isPrimitiveType()){
                    leftKey = TypeDescriptor.getSystemObject().getStaticKey();
                }
                if(!right.isPrimitiveType()){
                    rightKey = TypeDescriptor.getSystemObject().getStaticKey();
                }
            }
        }



        String key = generateTypeCheckKey(leftKey, rightKey, op, assignment, local);
        result = results.get(key);

        //if everything is valid, generate a string related to the item's name.
        String lstr = "";
        String rstr = "";
        if(left != null){
            lstr = left.toString();
        }
        if(right != null) {
            rstr = right.toString();
        }
        
        if(result == null) {
            result = new TypeCheckerResult();
            result.setConversionResult(TypeConversionResults.Incompatible);
            if(!assignment) {
                result.setErrorMessage("The operation \"" +
                    lstr + " " + op + " "+ rstr +
                    ",\" is not allowed.");
            }
            else {
                result.setErrorMessage("Cannot assign a value of type \"" +
                    rightKey + "\"" +
                    " to a variable of type \"" + lstr + ".\"");
            }
        }
        return result;
    }

    /**
     * Checks to see if the secondary auto-box step is allowed. This should only
     * be used as the second step in auto-boxing for a unary operation.
     * @param primitiveType
     * @return
     */
    public TypeCheckerResult checkAutobox(TypeDescriptor primitiveType){
        String rightKey = null;
        TypeCheckerResult result = null;

        if(primitiveType != null) {
            rightKey = primitiveType.getStaticKey();
        }

        TypeDescriptor objectType = null;
        if(rightKey.equals(TypeDescriptor.INTEGER)){
            objectType = TypeDescriptor.getIntegerObjectType();
        }else if(rightKey.equals(TypeDescriptor.NUMBER)){
            objectType = TypeDescriptor.getNumberObjectType();
        }else if(rightKey.equals(TypeDescriptor.TEXT)){
            objectType = TypeDescriptor.getTextObjectType();
        }else if(rightKey.equals(TypeDescriptor.BOOLEAN)){
            objectType = TypeDescriptor.getBooleanObjectType();
        }else{//error something went wrong

        }

        String leftKey = "";
        if(objectType != null) {
            leftKey = objectType.getStaticKey();
        }

        String key = generateTypeCheckKey(leftKey, rightKey, OperationEnum.AUTOBOX_CAST, false, false);
        result = results.get(key);

        if(result == null) {
            result = new TypeCheckerResult();
            result.setConversionResult(TypeConversionResults.Incompatible);
                result.setErrorMessage("The unary operation \"" + OperationEnum.AUTOBOX_CAST + " " +
                rightKey + "\" is not allowed.");
        }

        return result;

    }

    /**
     *
     * @param specifiedType
     * @param actualType
     * @param op
     * @param assignment
     * @param local
     * @return
     */
    public TypeCheckerResult checkTemplateTypes(TypeDescriptor specifiedType, TypeDescriptor actualType,
            OperationEnum op, boolean assignment, boolean local){

        if(specifiedType == null){
            return null;
        }else if(actualType == null){
            return null;
        }
        //if both are subtyped (templated) compare the two
        if(specifiedType.hasSubTypes() && actualType.hasSubTypes()){

            Iterator<GenericDescriptor> specifiedSubTypes = specifiedType.getSubTypes();
            Iterator<GenericDescriptor> actualSubTypes = actualType.getSubTypes();
            while(specifiedSubTypes.hasNext() && actualSubTypes.hasNext()){
                TypeDescriptor specifiedNext = new TypeDescriptor(specifiedSubTypes.next().getType());
                TypeDescriptor actualNext = new TypeDescriptor(actualSubTypes.next().getType());
                return checkTemplateTypes(specifiedNext, actualNext, op, assignment, local);
            }

            //if they are not of the same length
            if((!specifiedSubTypes.hasNext() && actualSubTypes.hasNext()) ||
                    (specifiedSubTypes.hasNext() && !actualSubTypes.hasNext())){
                return null;
            }
        }else if(actualType.getTemplateName() != null && ((!specifiedType.hasSubTypes() && actualType.hasSubTypes()) ||
                (specifiedType.hasSubTypes() && !actualType.hasSubTypes()))){
            return null;
        }

//        if(actualType.isPrimitiveType() && !specifiedType.isPrimitiveType()){
//            actualType.convertToClass();
//        }else if (!actualType.isPrimitiveType() && specifiedType.isPrimitiveType()){
//            actualType.convertToPrimitive();
//        }

        if(op != null && !op.equals(OperationEnum.RETURN)){
            if(actualType.isPrimitiveType()){
                actualType.convertToClass();
            }

            if(specifiedType.isPrimitiveType()){
                specifiedType.convertToClass();
            }
        }else{
            if (actualType.isPrimitiveType() && !specifiedType.isPrimitiveType()) {
                actualType.convertToClass();
            } else if (!actualType.isPrimitiveType() && specifiedType.isPrimitiveType()) {
                specifiedType.convertToClass();
            }
        }
        
        String key = generateTypeCheckKey(specifiedType.getName(), actualType.getName(), op, assignment, local);
        return results.get(key);
    }

    /**
     * Checks to see if the unary operation op is allowed on type left. This
     * function should not be used for binary operations like a + b.
     * 
     * @param left
     * @param op
     * @return
     */
    public TypeCheckerResult check(TypeDescriptor left, OperationEnum op) {
        String leftKey = "";
        if(left != null) {
            leftKey = left.getStaticKey();
        }
        String key = generateTypeCheckKey(leftKey, null, op, false);
        TypeCheckerResult result = results.get(key);

        if(result == null) {
            result = new TypeCheckerResult();
            result.setConversionResult(TypeConversionResults.Incompatible);
                result.setErrorMessage("The unary operation \"" + op + " " +
                left + "\" is not allowed.");
        }
        return result;
    }

    /**
     * This version of check returns whether a return statement's actual
     * return value matches appropriately with its required return type.
     * 
     * @param returnRequired
     * @param returnActual
     * @return
     */
    public TypeCheckerResult check(TypeDescriptor returnRequired, TypeDescriptor returnActual) {
        String requiredString = null;
        String actualString = null;
        String actualName = null;
        TypeCheckerResult result = null;

        if(returnRequired != null) {
            requiredString = returnRequired.getStaticKey();
        }

        if(returnActual != null) {
            actualString = returnActual.getStaticKey();
            actualName = returnActual.toString();
        }

        if(returnRequired != null && returnRequired.hasSubTypes()){
            result = new TypeCheckerResult();
            result = checkTemplateTypes(returnRequired, returnActual,  OperationEnum.RETURN, false, false);
            if(result == null){
                result = new TypeCheckerResult();
                result.setConversionResult(TypeConversionResults.None);
                result.setErrorMessage("Cannot return type \"" +
                    returnActual.generateTypeKey() + "\"" +
                    ", as the action accepts a return of type \"" + returnRequired.generateTypeKey() + ".\"");
            }
            return result;
        }

        String key = generateTypeCheckKey(requiredString,
                actualString, OperationEnum.RETURN, false);
         result = results.get(key);

        if(result == null) {
            result = new TypeCheckerResult();
            result.setConversionResult(TypeConversionResults.Incompatible);
                result.setErrorMessage("Cannot return type \"" + actualName +
                "\", as the action accepts a return of type \"" +
                returnRequired.toString() + ".\"");
        }else{
            result = checkTemplateTypes(returnRequired, returnActual,OperationEnum.RETURN,false,false);
            if(result == null){
                result = new TypeCheckerResult();
                result.setConversionResult(TypeConversionResults.Incompatible);
                result.setErrorMessage("Cannot return type \"" + actualName +
                "\", as the action accepts a return of type \"" +
                returnRequired.toString() + ".\"");
            }
        }
        return result;
    }

    /**
     * Checks to see if the binary operation, instance of or "is a" op, is
     * allowed on type left. This function should not be used for binary
     * operations other than instance of.
     *
     * @param left
     * @param right
     * @param op
     * @return
     */
    public TypeCheckerResult check(TypeDescriptor left, TypeDescriptor right, OperationEnum op) {

        TypeCheckerResult result = null;

        if (op.compareTo(OperationEnum.IS_A) == 0) {

            String leftKey = "";

            if (left != null) {
                leftKey = left.getStaticKey();
            }

            String rightKey = null;

            if (right != null) {
                rightKey = right.getStaticKey();
            }

            String key = generateTypeCheckKey(leftKey, rightKey, op, false);
            result = results.get(key);

            if(result == null) {
                result = new TypeCheckerResult();
                result.setConversionResult(TypeConversionResults.Incompatible);
                    result.setErrorMessage("Inconvertable types\"" + " " +
                    left + " " + right);
            }

        }
        return result;

    }

    /**
     * This method allows the addition of a new type into the type checker
     * and ultimately allows you to reassign values to existing objects and
     * to ensure they are correct at compile time.
     * @param clazz
     */
    public void addType(ClassDescriptor clazz) {
        //put in the class for non-local scoped variables
        TypeDescriptor type = clazz.getType();
        TypeCheckerResult result = new TypeCheckerResult();
        String key = this.generateTypeCheckKey(type.getName(),
                null, null, true);
        setNoConvertResult(result, type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CREATE_CUSTOM_TYPE);
        results.put(key, result);

        //put in the case where we are forcing the object into the local scope.
        type = clazz.getType();
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                null, null, true, true);
        setNoConvertResult(result, type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CREATE_CUSTOM_TYPE_LOCAL);
        results.put(key, result);

        //Now for the case where you assign a previously created variable to the
        //object
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), null, true);
        setNoConvertResult(result, type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CUSTOM_TYPE);
        results.put(key, result);

        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), null, true, true);
        setNoConvertResult(result, type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CUSTOM_TYPE_LOCAL);
        results.put(key, result);

        //now add this class as a valid return type
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), OperationEnum.RETURN, false);
        setNoConvertResult(result, type);
        results.put(key, result);

        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                TypeDescriptor.NULL, OperationEnum.RETURN, false);
        setNoConvertResult(result, type);
        results.put(key, result);


        //now allow you to explicitly set the value of an object to null
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                TypeDescriptor.getNullType().getStaticKey(), null, true);
        setNoConvertResult(result, type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CUSTOM_TYPE_NULL);
        results.put(key, result);

        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                TypeDescriptor.getNullType().getStaticKey(), null, true, true);
        setNoConvertResult(result, type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CUSTOM_TYPE_NULL_LOCAL);
        results.put(key, result);

        // = (equals)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_EQUALS);
        results.put(key, result);

        // != (equals)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_NOT_EQUALS);
        results.put(key, result);

        // is a (instanceof)
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), OperationEnum.IS_A, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_IS_A);
        results.put(key, result);

        //=
        //CUSTOM null
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                TypeDescriptor.getNullType().getStaticKey(), OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_NULL_EQUALS);
        results.put(key, result);

        //null CUSTOM
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.getNullType().getStaticKey(),
                type.getName(), OperationEnum.EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NULL_CUSTOM_EQUALS);
        results.put(key, result);

        //not=
        //CUSTOM null
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                TypeDescriptor.getNullType().getStaticKey(), OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_NULL_NOT_EQUALS);
        results.put(key, result);

        //null CUSTOM
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.getNullType().getStaticKey(),
                type.getName(), OperationEnum.NOT_EQUALS, false);
        setNoConvertResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NULL_CUSTOM_NOT_EQUALS);
        results.put(key, result);

        //CUSTOM null Implicit Cast
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                TypeDescriptor.getNullType().getStaticKey(), OperationEnum.IMPLICIT_CAST, false);
        setNoConvertResult(result, TypeDescriptor.getClassType(clazz));
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_NULL_IMPLICIT_CAST);
        results.put(key, result);

        //CUSTOM CUSTOM Implicit Cast
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), OperationEnum.IMPLICIT_CAST, false);
        setNoConvertResult(result, TypeDescriptor.getClassType(clazz));
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_IMPLICIT_CAST);
        results.put(key, result);

        //explicit class cast
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(type.getName(),
                type.getName(), OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.None);
        result.setResult(type);
        result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_CAST);
        results.put(key, result);
        
        //parent casts
        Iterator<ClassDescriptor> parents = clazz.getImmediateParents();
        addParentTypes(clazz, parents);
    }

    private void addParentTypes(ClassDescriptor clazz, Iterator<ClassDescriptor> parents){
        TypeDescriptor type = clazz.getType();
        
        while(parents.hasNext()){
            ClassDescriptor next = parents.next();
            TypeDescriptor tempType = next.getType();

            //implicit class cast
            TypeCheckerResult result = new TypeCheckerResult();
            String key = this.generateTypeCheckKey(tempType.getName(),
                    type.getName(), null, true, false);
            setNoConvertResult(result, TypeDescriptor.getClassType(next));
            result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_CUSTOM_IMPLICIT_CAST);
            results.put(key, result);

            //implicit class cast
            result = new TypeCheckerResult();
            key = this.generateTypeCheckKey(tempType.getName(),
                    type.getName(), OperationEnum.IMPLICIT_CAST, false, false);
            setNoConvertResult(result, TypeDescriptor.getClassType(next));
            result.setOpcodeGenerator(TypeCheckerStepFactory.PARAMETER_CUSTOM_IMPLICIT_CAST);
            result.setConversionScore(TypeConversionEnum.PARENT);
            results.put(key, result);

            // is a (instanceof)
            result = new TypeCheckerResult();
            key = this.generateTypeCheckKey(type.getName(),
                    tempType.getName(), OperationEnum.IS_A, false);
            setNoConvertResult(result, TypeDescriptor.getBooleanType());
            result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_IS_A);
            results.put(key, result);

            // is a (instanceof)
            result = new TypeCheckerResult();
            key = this.generateTypeCheckKey(tempType.getName(),
                    type.getName(), OperationEnum.IS_A, false);
            setNoConvertResult(result, TypeDescriptor.getBooleanType());
            result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_IS_A);
            results.put(key, result);

            //explicit class cast
            result = new TypeCheckerResult();
            key = this.generateTypeCheckKey(tempType.getName(),
                    type.getName(), OperationEnum.CAST, false);
            result.setConversionResult(TypeConversionResults.SubclassNarrowing);
            result.setResult(tempType);
            result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_CAST);
            results.put(key, result);

            //explicit class cast
            result = new TypeCheckerResult();
            key = this.generateTypeCheckKey(type.getName(),
                    tempType.getName(), OperationEnum.CAST,false);
            result.setConversionResult(TypeConversionResults.SubclassWideing);
            result.setResult(type);
            result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_CAST);
            results.put(key, result);

            //return class cast
            result = new TypeCheckerResult();
            key = this.generateTypeCheckKey(tempType.getName(),
                    type.getName(), OperationEnum.RETURN, false);
            setNoConvertResult(result, TypeDescriptor.getClassType(next));
            result.setOpcodeGenerator(TypeCheckerStepFactory.CUSTOM_CUSTOM_IMPLICIT_CAST);
            result.setConversionScore(TypeConversionEnum.PARENT);
            results.put(key, result);

            if(next.hasParents()){
                Iterator<ClassDescriptor> newParents = next.getImmediateParents();
                addParentTypes(clazz, newParents);
            }
        }
    }

    /**
     * add Types to check for all autoboxing.
     */
    public void addAutoBoxedTypes() {

        //implicit auto-box to Integer
        TypeCheckerResult result = new TypeCheckerResult();
        String key = this.generateTypeCheckKey(TypeDescriptor.OBJECT,
                TypeDescriptor.INTEGER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getIntegerObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.AUTOBOX_CREATE_OBJECT_CAST);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast primitive to object
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER_OBJECT,
                TypeDescriptor.INTEGER, OperationEnum.AUTOBOX_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getIntegerObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_INTEGER_OBJECT_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast Object to primitive
        //Integer to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_INTEGER_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //Integer to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_NUMBER_AUTOBOX);
        results.put(key, result);

        //Integer to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_TEXT_AUTOBOX);
        results.put(key, result);

        //Integer to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //assign integer object to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER_OBJECT, null, true, false);
        setWideningConversionResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_INTEGER_OBJECT_INTEGER);
        results.put(key, result);

        //assign integer object to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER_OBJECT, null, true, true);
        setWideningConversionResult(result, TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_INTEGER_OBJECT_INTEGER_LOCAL);
        results.put(key, result);

        //assign integer object to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER_OBJECT, null, true, false);
        setWideningConversionResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_INTEGER_OBJECT_NUMBER);
        results.put(key, result);

        //assign integer object to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER_OBJECT, null, true, true);
        setWideningConversionResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_INTEGER_OBJECT_NUMBER_LOCAL);
        results.put(key, result);

        //explicit Integer to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_INTEGER_AUTOBOX);
        results.put(key, result);

        //explicit Integer to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_NUMBER_AUTOBOX);
        results.put(key, result);

        //explicit Integer to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_TEXT_AUTOBOX);
        results.put(key, result);

        //explicit Integer to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.INTEGER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.INTEGER_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //implicit auto-box to Number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.OBJECT,
                TypeDescriptor.NUMBER, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getNumberObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.AUTOBOX_CREATE_OBJECT_CAST);
        result.setConversionScore(TypeConversionEnum.PARENT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast primitive to object
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER_OBJECT,
                TypeDescriptor.NUMBER, OperationEnum.AUTOBOX_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getNumberObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_NUMBER_OBJECT_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast Object to primitive
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_NUMBER_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

//        //Number to integer
//        result = new TypeCheckerResult();
//        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
//                TypeDescriptor.NUMBER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
//        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
//        result.setResult(TypeDescriptor.getIntegerType());
//        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_INTEGER_AUTOBOX);
//        results.put(key, result);

        //Number to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_TEXT_AUTOBOX);
        results.put(key, result);

        //Number to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //assign number object to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER_OBJECT, null, true, false);
        setWideningConversionResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_NUMBER_OBJECT_NUMBER);
        results.put(key, result);

        //assign number object to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER_OBJECT, null, true, true);
        setWideningConversionResult(result, TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_NUMBER_OBJECT_NUMBER_LOCAL);
        results.put(key, result);

        //explicit Number to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_NUMBER_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //explicit Number to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_INTEGER_AUTOBOX);
        results.put(key, result);

        //explicit Number to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_TEXT_AUTOBOX);
        results.put(key, result);

        //explicit Number to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.NUMBER_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.NUMBER_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //implicit auto-box to Text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.OBJECT,
                TypeDescriptor.TEXT, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getTextObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.AUTOBOX_CREATE_OBJECT_CAST);
        result.setConversionScore(TypeConversionEnum.PARENT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast primitive to object
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT_OBJECT,
                TypeDescriptor.TEXT, OperationEnum.AUTOBOX_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getTextObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_TEXT_OBJECT_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast Object to primitive
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_TEXT_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //Text to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_INTEGER_AUTOBOX);
        results.put(key, result);

        //Text to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_NUMBER_AUTOBOX);
        results.put(key, result);

        //Text to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //assign text object to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT_OBJECT, null, true, false);
        setWideningConversionResult(result, TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_TEXT_OBJECT_TEXT);
        results.put(key, result);

        //assign text object to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT_OBJECT, null, true, true);
        setWideningConversionResult(result, TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_TEXT_OBJECT_TEXT_LOCAL);
        results.put(key, result);

        //explicit Text to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_TEXT_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //explicit Text to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_INTEGER_AUTOBOX);
        results.put(key, result);

        //explicit Text to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_NUMBER_AUTOBOX);
        results.put(key, result);

        //explicit Text to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.TEXT_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.TEXT_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //auto-box to Boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.OBJECT,
                TypeDescriptor.BOOLEAN, OperationEnum.IMPLICIT_CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getBooleanObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.AUTOBOX_CREATE_OBJECT_CAST);
        result.setConversionScore(TypeConversionEnum.PARENT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast primitive to object
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN_OBJECT,
                TypeDescriptor.BOOLEAN, OperationEnum.AUTOBOX_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassWideing);
        result.setResult(TypeDescriptor.getBooleanObjectType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_BOOLEAN_OBJECT_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //implicit unary cast Object to primitive
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_BOOLEAN_AUTOBOX);
        result.setConversionScore(TypeConversionEnum.EXACT_AUTO_BOX);
        results.put(key, result);

        //Boolean to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_INTEGER_AUTOBOX);
        results.put(key, result);

        //Boolean to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_NUMBER_AUTOBOX);
        results.put(key, result);

        //Boolean to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.IMPLICIT_CAST, false, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_TEXT_AUTOBOX);
        results.put(key, result);

        //assign boolean object to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN_OBJECT, null, true, false);
        setWideningConversionResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_BOOLEAN_OBJECT_BOOLEAN);
        results.put(key, result);

        //assign boolean object to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN_OBJECT, null, true, true);
        setWideningConversionResult(result, TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.ASSIGNMENT_BOOLEAN_OBJECT_BOOLEAN_LOCAL);
        results.put(key, result);

        //explicit Boolean to boolean
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.BOOLEAN,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getBooleanType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_BOOLEAN_AUTOBOX);
        results.put(key, result);

        //explicit Boolean to integer
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.INTEGER,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getIntegerType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_INTEGER_AUTOBOX);
        results.put(key, result);

        //explicit Boolean to number
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.NUMBER,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getNumberType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_NUMBER_AUTOBOX);
        results.put(key, result);

        //explicit Boolean to text
        result = new TypeCheckerResult();
        key = this.generateTypeCheckKey(TypeDescriptor.TEXT,
                TypeDescriptor.BOOLEAN_OBJECT, OperationEnum.CAST, false);
        result.setConversionResult(TypeConversionResults.SubclassNarrowing);
        result.setResult(TypeDescriptor.getTextType());
        result.setOpcodeGenerator(TypeCheckerStepFactory.BOOLEAN_OBJECT_TEXT_AUTOBOX);
        results.put(key, result);
    }
    /**
     * This method clears out the type checking table and regenerates it
     * with only primitive types.
     */
    public void clear() {
        results.clear();
        generateTypeCheckingTable();
    }
}
