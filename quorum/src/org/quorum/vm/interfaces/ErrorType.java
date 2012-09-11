/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.interfaces;

/**
 * The error type attached to a compiler error. 
 * 
 * @author Melissa Stefik
 */
public enum ErrorType {
    MISSING_VARIABLE("Cannot find symbol - variable"),
    EXPECTED_CLOSURE("Expected closure"),
    MISSING_RETURN("Missing - return"),
    MISSING_METHOD("Cannot find symbol - method"), 
    MISSING_MAIN("Missing main method"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    IDENTIFIER_EXPECTED("Identifier expected"),
    MISSING_CLASS("Cannot find symbol - class"),
    MISSING_IF("Missing - if"),
    MISSING_THEN("Missing - then"),
    MISSING_PARENT("Cannot find symbol - parent"),
    MISSING_USE("Cannot find symbol - package"), 
    INVALID_OPERATOR("Invalid operator"), 
    UNREACHABLE("Unreachable statements"), 
    DUPLICATE("Already defined"),  
    EOF("End of file error"),
    INHERITANCE_CIRCULAR("Circular inheritance"), 
    INHERITANCE_NULL("Cannot inherit a from null"),
    INHERITANCE_MODIFIER_DOWNGRADE("Cannot override a private method"), 
    INHERITANCE_AMBIGUOUS("Ambiguous inheritance"), 
    INHERITANCE_MISSMATCHED_RETURN("Invalid overriding return type"), 
    METHOD_CALL_AMBIGUOUS("Ambigous method call"), 
    USE_AMBIGUOUS("Ambiguous used package"), 
    CLASS_DUPLICATE("Class already defined"), 
    IF_INVALID_EXPRESSION("Invalid if expression"),  
    MISMATCHED_TEMPLATES("Mismatched class template"), 
    INSTANTIATE_ABSTRACT("Cannot instantiate abstract class"),
    INSTANTIATE_THIS("Cannot instantiate 'me'"), 
    INSTANTIATE_GENERIC("Cannot instantiate generic object"), 
    INVALID_ERROR("Invalid error type"), 
    INVALID_RETURN_NOW("Invalid return now"), 
    METHOD_DUPLICATE("Method already defined"),
    REPEAT_TIMES_NON_INTEGER("Repeat times non-integer"),
    REPEAT_NON_BOOLEAN("Repeat non-boolean"),
    CONSTANT_REASSIGNMENT("invalid constant assignment"),
    OTHER("other"), 
    CONSTANT_INITIALIZED("constant not initialized"); 

    String errorType = "";
    ErrorType(String st) {
        errorType = st;
    }

    @Override
    public String toString() {
        return errorType;
    }
}
