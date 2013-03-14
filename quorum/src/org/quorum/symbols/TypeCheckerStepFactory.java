/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import org.quorum.execution.ExecutionStep;
import org.quorum.steps.*;

/**
 * This enumerated type stores a list of all legal operations and generates
 * appropriate opcodes if the operation is legal (null otherwise).
 * 
 * @author Andreas Stefik
 */
public enum TypeCheckerStepFactory {
    INTEGER_INTEGER_PLUS,
    INTEGER_INTEGER_MINUS,
    INTEGER_INTEGER_TIMES,
    INTEGER_INTEGER_DIVIDE,
    INTEGER_INTEGER_MODULUS,
    INTEGER_INTEGER_GREATER_EQUALS,
    INTEGER_INTEGER_GREATER,
    INTEGER_INTEGER_EQUALS,
    INTEGER_INTEGER_NOT_EQUALS,
    INTEGER_INTEGER_LESS_EQUALS,
    INTEGER_INTEGER_LESS,
    INTEGER_INTEGER_RANGE,

    //numbers
    NUMBER_NUMBER_PLUS,
    NUMBER_NUMBER_MINUS,
    NUMBER_NUMBER_TIMES,
    NUMBER_NUMBER_DIVIDE,
    NUMBER_NUMBER_MODULUS,
    NUMBER_NUMBER_GREATER_EQUALS,
    NUMBER_NUMBER_GREATER,
    NUMBER_NUMBER_EQUALS,
    NUMBER_NUMBER_NOT_EQUALS,
    NUMBER_NUMBER_LESS_EQUALS,
    NUMBER_NUMBER_LESS,

    //boolean values
    BOOLEAN_BOOLEAN_AND,
    BOOLEAN_BOOLEAN_OR,
    BOOLEAN_BOOLEAN_EQUALS,
    BOOLEAN_BOOLEAN_NOT_EQUALS,

    //text values
    TEXT_TEXT_PLUS,
    TEXT_TEXT_GREATER_EQUALS,
    TEXT_TEXT_GREATER,
    TEXT_TEXT_EQUALS,
    TEXT_TEXT_NOT_EQUALS,
    TEXT_TEXT_LESS_EQUALS,
    TEXT_TEXT_LESS,

    //unary operations
    UNARY_BOOLEAN_NOT,

    //Assignment steps
    ASSIGNMENT_INTEGER_INTEGER,
    ASSIGNMENT_INTEGER_INTEGER_LOCAL,
    ASSIGNMENT_NUMBER_NUMBER,
    ASSIGNMENT_NUMBER_NUMBER_LOCAL,
    ASSIGNMENT_NUMBER_INTEGER,
    ASSIGNMENT_NUMBER_INTEGER_LOCAL,
    ASSIGNMENT_BOOLEAN_BOOLEAN,
    ASSIGNMENT_BOOLEAN_BOOLEAN_LOCAL,
    ASSIGNMENT_TEXT_TEXT,
    ASSIGNMENT_TEXT_TEXT_LOCAL,

    //custom types
    ASSIGNMENT_CREATE_CUSTOM_TYPE,
    ASSIGNMENT_CREATE_CUSTOM_TYPE_LOCAL,
    ASSIGNMENT_CUSTOM_TYPE,
    ASSIGNMENT_CUSTOM_TYPE_LOCAL,
    ASSIGNMENT_CUSTOM_TYPE_NULL,
    ASSIGNMENT_CUSTOM_TYPE_NULL_LOCAL,
    CUSTOM_CUSTOM_EQUALS,
    CUSTOM_NULL_EQUALS,
    NULL_CUSTOM_EQUALS,
    CUSTOM_CUSTOM_NOT_EQUALS,
    CUSTOM_NULL_NOT_EQUALS,
    NULL_CUSTOM_NOT_EQUALS,
    CUSTOM_CUSTOM_IS_A,
    CUSTOM_NULL_IMPLICIT_CAST,
    CUSTOM_CUSTOM_IMPLICIT_CAST,
    ASSIGNMENT_CUSTOM_IMPLICIT_CAST,
    PARAMETER_CUSTOM_IMPLICIT_CAST,

    //Auto-boxed conversions
    AUTOBOX_CREATE_OBJECT_CAST,
    INTEGER_INTEGER_OBJECT_AUTOBOX,
    INTEGER_OBJECT_INTEGER_AUTOBOX,
    INTEGER_OBJECT_NUMBER_AUTOBOX,
    INTEGER_OBJECT_TEXT_AUTOBOX,
    INTEGER_OBJECT_BOOLEAN_AUTOBOX,
    ASSIGNMENT_INTEGER_OBJECT_INTEGER,
    ASSIGNMENT_INTEGER_OBJECT_INTEGER_LOCAL,
    ASSIGNMENT_INTEGER_OBJECT_NUMBER,
    ASSIGNMENT_INTEGER_OBJECT_NUMBER_LOCAL,
    NUMBER_NUMBER_OBJECT_AUTOBOX,
    NUMBER_OBJECT_NUMBER_AUTOBOX,
    NUMBER_OBJECT_INTEGER_AUTOBOX,
    NUMBER_OBJECT_TEXT_AUTOBOX,
    NUMBER_OBJECT_BOOLEAN_AUTOBOX,
    ASSIGNMENT_NUMBER_OBJECT_NUMBER,
    ASSIGNMENT_NUMBER_OBJECT_NUMBER_LOCAL,
    TEXT_TEXT_OBJECT_AUTOBOX,
    TEXT_OBJECT_TEXT_AUTOBOX,
    TEXT_OBJECT_INTEGER_AUTOBOX,
    TEXT_OBJECT_NUMBER_AUTOBOX,
    TEXT_OBJECT_BOOLEAN_AUTOBOX,
    ASSIGNMENT_TEXT_OBJECT_TEXT,
    ASSIGNMENT_TEXT_OBJECT_TEXT_LOCAL,
    BOOLEAN_BOOLEAN_OBJECT_AUTOBOX,
    BOOLEAN_OBJECT_BOOLEAN_AUTOBOX,
    BOOLEAN_OBJECT_TEXT_AUTOBOX,
    BOOLEAN_OBJECT_NUMBER_AUTOBOX,
    BOOLEAN_OBJECT_INTEGER_AUTOBOX,
    ASSIGNMENT_BOOLEAN_OBJECT_BOOLEAN,
    ASSIGNMENT_BOOLEAN_OBJECT_BOOLEAN_LOCAL,
    
    //widening conversions
    INTEGER_NUMBER_PLUS,
    INTEGER_NUMBER_MINUS,
    INTEGER_NUMBER_TIMES,
    INTEGER_NUMBER_DIVIDE,
    INTEGER_NUMBER_MODULUS,
    INTEGER_NUMBER_GREATER_EQUALS,
    INTEGER_NUMBER_GREATER,
    INTEGER_NUMBER_EQUALS,
    INTEGER_NUMBER_NOT_EQUALS,
    INTEGER_NUMBER_LESS_EQUALS,
    INTEGER_NUMBER_LESS,
    
    NUMBER_INTEGER_PLUS,
    NUMBER_INTEGER_MINUS,
    NUMBER_INTEGER_TIMES,
    NUMBER_INTEGER_DIVIDE,
    NUMBER_INTEGER_MODULUS,
    NUMBER_INTEGER_GREATER_EQUALS,
    NUMBER_INTEGER_GREATER,
    NUMBER_INTEGER_EQUALS,
    NUMBER_INTEGER_NOT_EQUALS,
    NUMBER_INTEGER_LESS_EQUALS,
    NUMBER_INTEGER_LESS,

    TEXT_INTEGER_PLUS,
    TEXT_NUMBER_PLUS,
    TEXT_BOOLEAN_PLUS,

    INTEGER_TEXT_PLUS,
    NUMBER_TEXT_PLUS,
    BOOLEAN_TEXT_PLUS,

    //casting operations
    INTEGER_TEXT_CAST,
    TEXT_INTEGER_CAST,
    NUMBER_TEXT_CAST,
    TEXT_NUMBER_CAST,
    INTEGER_NUMBER_CAST,
    NUMBER_INTEGER_CAST,
    NUMBER_BOOLEAN_CAST,
    BOOLEAN_NUMBER_CAST,
    INTEGER_BOOLEAN_CAST,
    BOOLEAN_INTEGER_CAST,
    TEXT_BOOLEAN_CAST,
    BOOLEAN_TEXT_CAST,
    BOOLEAN_BOOLEAN_CAST,
    TEXT_TEXT_CAST,
    INTEGER_INTEGER_CAST,
    NUMBER_NUMBER_CAST,
    CUSTOM_CUSTOM_CAST, 
    
    ASSIGNMENT_TEXT_TYPE_NULL,
    ASSIGNMENT_TEXT_TYPE_NULL_LOCAL, 
    TEXT_NULL_EQUALS;


    public ExecutionStep generateStep() {
        switch(this) {
            //integer operations
            case INTEGER_INTEGER_PLUS:
                return new BinaryAddStep();
            case INTEGER_INTEGER_MINUS:
                return new BinarySubtractStep();
            case INTEGER_INTEGER_TIMES:
                return new BinaryMultiplyStep();
            case INTEGER_INTEGER_DIVIDE:
                return new BinaryDivideStep();
            case INTEGER_INTEGER_MODULUS:
                return new BinaryModStep();
            case INTEGER_INTEGER_GREATER_EQUALS:
                return new BinaryGreaterEqualsStep();
            case INTEGER_INTEGER_GREATER:
                return new BinaryGreaterThanStep();
            case INTEGER_INTEGER_EQUALS:
                return new BinaryEqualsStep();
            case INTEGER_INTEGER_NOT_EQUALS:
                return new BinaryNotEqualsStep();
            case INTEGER_INTEGER_LESS_EQUALS:
                return new BinaryLessEqualsStep();
            case INTEGER_INTEGER_LESS:
                return new BinaryLessThanStep();

            //number operations
            case NUMBER_NUMBER_PLUS:
                return new BinaryAddNumberStep();
            case NUMBER_NUMBER_MINUS:
                return new BinarySubtractNumberStep();
            case NUMBER_NUMBER_TIMES:
                return new BinaryMultiplyNumberStep();
            case NUMBER_NUMBER_DIVIDE:
                return new BinaryDivideNumberStep();
            case NUMBER_NUMBER_MODULUS:
                return new BinaryModNumberStep();
            case NUMBER_NUMBER_GREATER_EQUALS:
                return new BinaryGreaterEqualsNumberStep();
            case NUMBER_NUMBER_GREATER:
                return new BinaryGreaterThanNumberStep();
            case NUMBER_NUMBER_EQUALS:
                return new BinaryEqualsNumberStep();
            case NUMBER_NUMBER_NOT_EQUALS:
                return new BinaryNotEqualsNumberStep();
            case NUMBER_NUMBER_LESS_EQUALS:
                return new BinaryLessEqualsNumberStep();
            case NUMBER_NUMBER_LESS:
                return new BinaryLessThanNumberStep();

            //boolean operations
            case BOOLEAN_BOOLEAN_AND:
                return new BinaryAndStep();
            case BOOLEAN_BOOLEAN_OR:
                return new BinaryOrStep();
            case BOOLEAN_BOOLEAN_EQUALS:
                return new BinaryEqualsBooleanStep();
            case BOOLEAN_BOOLEAN_NOT_EQUALS:
                return new BinaryNotEqualsBooleanStep();

            //text operations
            case TEXT_TEXT_PLUS:
                return new BinaryConcatenateStep();
            case TEXT_TEXT_EQUALS:
                return new BinaryEqualsStringStep();
            case TEXT_NULL_EQUALS:
                return new BinaryEqualsStringNullStep();
            case TEXT_TEXT_NOT_EQUALS:
                return new BinaryNotEqualsStringStep();
            case TEXT_TEXT_GREATER_EQUALS:
                return new BinaryGreaterEqualsStringStep();
            case TEXT_TEXT_GREATER:
                return new BinaryGreaterThanStringStep();
            case TEXT_TEXT_LESS_EQUALS:
                return new BinaryLessEqualsStringStep();
            case TEXT_TEXT_LESS:
                return new BinaryLessThanStringStep();

            //unary operations
            case UNARY_BOOLEAN_NOT:
                return new UnaryNotStep();

            //assignment statements
            case ASSIGNMENT_INTEGER_INTEGER:
                return new AssignmentIntegerStep();
            case ASSIGNMENT_INTEGER_INTEGER_LOCAL:
                return new AssignmentIntegerLocalStep();
            case ASSIGNMENT_NUMBER_NUMBER:
                return new AssignmentNumberStep();
            case ASSIGNMENT_NUMBER_NUMBER_LOCAL:
                return new AssignmentNumberLocalStep();
            case ASSIGNMENT_NUMBER_INTEGER:
                return new AssignmentNumberIntegerStep();
            case ASSIGNMENT_NUMBER_INTEGER_LOCAL:
                return new AssignmentNumberIntegerLocalStep();
            case ASSIGNMENT_BOOLEAN_BOOLEAN:
                return new AssignmentBooleanStep();
            case ASSIGNMENT_BOOLEAN_BOOLEAN_LOCAL:
                return new AssignmentBooleanLocalStep();
            case ASSIGNMENT_TEXT_TEXT:
                return new AssignmentTextStep();
            case ASSIGNMENT_TEXT_TEXT_LOCAL:
                return new AssignmentTextLocalStep();
            case ASSIGNMENT_TEXT_TYPE_NULL:
                return new AssignmentTextNullStep();
            case ASSIGNMENT_TEXT_TYPE_NULL_LOCAL:
                return new AssignmentTextNullLocalStep();

            //custom types
            case ASSIGNMENT_CREATE_CUSTOM_TYPE:
                return new CreateObjectStep();
            case ASSIGNMENT_CREATE_CUSTOM_TYPE_LOCAL:
                return new CreateObjectStep();
            case ASSIGNMENT_CUSTOM_TYPE:
                return new AssignmentCustomStep();
            case ASSIGNMENT_CUSTOM_TYPE_LOCAL:
                return new AssignmentCustomLocalStep();
            case ASSIGNMENT_CUSTOM_TYPE_NULL:
                return new AssignmentCustomStep();
            case ASSIGNMENT_CUSTOM_TYPE_NULL_LOCAL:
                return new AssignmentCustomLocalStep();
            case CUSTOM_CUSTOM_EQUALS:
                return new BinaryEqualsCustomCustomStep();
            case CUSTOM_NULL_EQUALS:
                return new BinaryEqualsCustomNullStep();
            case NULL_CUSTOM_EQUALS:
                return new BinaryEqualsNullCustomStep();
            case CUSTOM_CUSTOM_NOT_EQUALS:
                return new BinaryNotEqualsCustomCustomStep();
            case CUSTOM_NULL_NOT_EQUALS:
                return new BinaryNotEqualsCustomNullStep();
            case NULL_CUSTOM_NOT_EQUALS:
                return new BinaryNotEqualsNullCustomStep();
            case CUSTOM_CUSTOM_IS_A:
                return new BinaryIsACustomCustomStep();
            case CUSTOM_NULL_IMPLICIT_CAST:
                return null; //no conversion required
            case CUSTOM_CUSTOM_IMPLICIT_CAST:
                return null;//no conversion required
            case ASSIGNMENT_CUSTOM_IMPLICIT_CAST:
                return new AssignmentCustomStep();
            case CUSTOM_CUSTOM_CAST:
                return new ObjectCastStep();
            case PARAMETER_CUSTOM_IMPLICIT_CAST:
                return null;

            //autoboxed conversion results
            case AUTOBOX_CREATE_OBJECT_CAST:
                return new AutoBoxCreateStep();
            case INTEGER_INTEGER_OBJECT_AUTOBOX:
                return new IntegerAutoBoxStep();
            case INTEGER_OBJECT_INTEGER_AUTOBOX:
                return new IntegerReverseAutoBoxStep();
            case INTEGER_OBJECT_NUMBER_AUTOBOX:
                return new IntegerReverseAutoBoxToNumberStep();
            case INTEGER_OBJECT_TEXT_AUTOBOX:
                return new IntegerReverseAutoBoxToTextStep();
            case INTEGER_OBJECT_BOOLEAN_AUTOBOX:
                return new IntegerReverseAutoBoxToBooleanStep();
            case ASSIGNMENT_INTEGER_OBJECT_INTEGER:
                return new AssignObjectAutoBoxStep();
            case ASSIGNMENT_INTEGER_OBJECT_INTEGER_LOCAL:
                return new AssignObjectAutoBoxLocalStep();
            case ASSIGNMENT_INTEGER_OBJECT_NUMBER:
                return new AssignIntegerObjectToNumberAutoBoxStep();
            case ASSIGNMENT_INTEGER_OBJECT_NUMBER_LOCAL:
                return new AssignIntegerObjectToNumberAutoBoxLocalStep();
            case NUMBER_NUMBER_OBJECT_AUTOBOX:
                return new NumberAutoBoxStep();
            case NUMBER_OBJECT_NUMBER_AUTOBOX:
                return new NumberReverseAutoBoxStep();
            case NUMBER_OBJECT_INTEGER_AUTOBOX:
                return new NumberReverseAutoBoxToIntegerStep();
            case NUMBER_OBJECT_TEXT_AUTOBOX:
                return new NumberReverseAutoBoxToTextStep();
            case NUMBER_OBJECT_BOOLEAN_AUTOBOX:
                return new NumberReverseAutoBoxToBooleanStep();
            case ASSIGNMENT_NUMBER_OBJECT_NUMBER:
                return new AssignObjectAutoBoxStep();
            case ASSIGNMENT_NUMBER_OBJECT_NUMBER_LOCAL:
                return new AssignObjectAutoBoxLocalStep();
            case TEXT_TEXT_OBJECT_AUTOBOX:
                return new TextAutoBoxStep();
            case TEXT_OBJECT_TEXT_AUTOBOX:
                return new TextReverseAutoBoxStep();
            case TEXT_OBJECT_BOOLEAN_AUTOBOX:
                return new TextReverseAutoBoxToBooleanStep();
            case TEXT_OBJECT_NUMBER_AUTOBOX:
                return new TextReverseAutoBoxToNumberStep();
            case TEXT_OBJECT_INTEGER_AUTOBOX:
                return new TextReverseAutoBoxToIntegerStep();
            case ASSIGNMENT_TEXT_OBJECT_TEXT:
                return new AssignObjectAutoBoxStep();
            case ASSIGNMENT_TEXT_OBJECT_TEXT_LOCAL:
                return new AssignObjectAutoBoxLocalStep();
            case BOOLEAN_BOOLEAN_OBJECT_AUTOBOX:
                return new BooleanAutoBoxStep();
            case BOOLEAN_OBJECT_BOOLEAN_AUTOBOX:
                return new BooleanReverseAutoBoxStep();
            case BOOLEAN_OBJECT_NUMBER_AUTOBOX:
                return new BooleanReverseAutoBoxToNumberStep();
            case BOOLEAN_OBJECT_INTEGER_AUTOBOX:
                return new BooleanReverseAutoBoxToIntegerStep();
            case BOOLEAN_OBJECT_TEXT_AUTOBOX:
                return new BooleanReverseAutoBoxToTextStep();
            case ASSIGNMENT_BOOLEAN_OBJECT_BOOLEAN:
                return new AssignObjectAutoBoxStep();
            case ASSIGNMENT_BOOLEAN_OBJECT_BOOLEAN_LOCAL:
                return new AssignObjectAutoBoxLocalStep();


            //widening conversion results
            //integer number
            case INTEGER_NUMBER_PLUS:
                return new BinaryAddIntegerNumberStep();
            case INTEGER_NUMBER_MINUS:
                return new BinarySubtractIntegerNumberStep();
            case INTEGER_NUMBER_TIMES:
                return new BinaryMultiplyIntegerNumberStep();
            case INTEGER_NUMBER_DIVIDE:
                return new BinaryDivideIntegerNumberStep();
            case INTEGER_NUMBER_MODULUS:
                return new BinaryModIntegerNumberStep();
            case INTEGER_NUMBER_GREATER_EQUALS:
                return new BinaryGreaterEqualsIntegerNumberStep();
            case INTEGER_NUMBER_GREATER:
                return new BinaryGreaterThanIntegerNumberStep();
            case INTEGER_NUMBER_EQUALS:
                return new BinaryEqualsIntegerNumberStep();
            case INTEGER_NUMBER_NOT_EQUALS:
                return new BinaryNotEqualsIntegerNumberStep();
            case INTEGER_NUMBER_LESS_EQUALS:
                return new BinaryLessEqualsIntegerNumberStep();
            case INTEGER_NUMBER_LESS:
                return new BinaryLessThanIntegerNumberStep();

            //number integer
            case NUMBER_INTEGER_PLUS:
                return new BinaryAddNumberIntegerStep();
            case NUMBER_INTEGER_MINUS:
                return new BinarySubtractNumberIntegerStep();
            case NUMBER_INTEGER_TIMES:
                return new BinaryMultiplyNumberIntegerStep();
            case NUMBER_INTEGER_DIVIDE:
                return new BinaryDivideNumberIntegerStep();
            case NUMBER_INTEGER_MODULUS:
                return new BinaryModNumberIntegerStep();
            case NUMBER_INTEGER_GREATER_EQUALS:
                return new BinaryGreaterEqualsNumberIntegerStep();
            case NUMBER_INTEGER_GREATER:
                return new BinaryGreaterThanNumberIntegerStep();
            case NUMBER_INTEGER_EQUALS:
                return new BinaryEqualsNumberIntegerStep();
            case NUMBER_INTEGER_NOT_EQUALS:
                return new BinaryNotEqualsNumberIntegerStep();
            case NUMBER_INTEGER_LESS_EQUALS:
                return new BinaryLessEqualsNumberIntegerStep();
            case NUMBER_INTEGER_LESS:
                return new BinaryLessThanNumberIntegerStep();

            //text conversion
            //text type
            case TEXT_INTEGER_PLUS:
                return new BinaryAddTextIntegerStep();
            case TEXT_NUMBER_PLUS:
                return new BinaryAddTextNumberStep();
            case TEXT_BOOLEAN_PLUS:
                return new BinaryAddTextBooleanStep();

            //type text
            case INTEGER_TEXT_PLUS:
                return new BinaryAddIntegerTextStep();
            case NUMBER_TEXT_PLUS:
                return new BinaryAddNumberTextStep();
            case BOOLEAN_TEXT_PLUS:
                return new BinaryAddBooleanTextStep();

            case INTEGER_TEXT_CAST:
                return new UnaryIntegerTextCastStep();
            case TEXT_INTEGER_CAST:
                return new UnaryTextIntegerCastStep();
            case NUMBER_TEXT_CAST:
                return new UnaryNumberTextCastStep();
            case TEXT_NUMBER_CAST:
                return new UnaryTextNumberCastStep();
            case INTEGER_NUMBER_CAST:
                return new UnaryIntegerNumberCastStep();
            case NUMBER_INTEGER_CAST:
                return new UnaryNumberIntegerCastStep();
            case NUMBER_BOOLEAN_CAST:
                return new UnaryNumberBooleanCastStep();
            case BOOLEAN_NUMBER_CAST:
                return new UnaryBooleanNumberCastStep();
            case INTEGER_BOOLEAN_CAST:
                return new UnaryIntegerBooleanCastStep();
            case BOOLEAN_INTEGER_CAST:
                return new UnaryBooleanIntegerCastStep();
            case TEXT_BOOLEAN_CAST:
                return new UnaryTextBooleanCastStep();
            case BOOLEAN_TEXT_CAST:
                return new UnaryBooleanTextCastStep();
            case BOOLEAN_BOOLEAN_CAST:
                return new UnaryBooleanBooleanCastStep();
            case TEXT_TEXT_CAST:
                return new UnaryTextTextCastStep();
            case INTEGER_INTEGER_CAST:
                return new UnaryIntegerIntegerCastStep();
            case NUMBER_NUMBER_CAST:
                return new UnaryNumberNumberCastStep();
            default:
                return null;
        }
    }
}