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
    
    EXPECTED_CLOSURE("Expected closure"),
    ILLEGAL_START_OF_EXPRESSION("Illegal start of expression"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    IDENTIFIER_EXPECTED("Identifier expected"),
    CLASS_EXPECTED("Missing 'class'"),
    MISSING_THEN("Missing 'then'"),
    MISSING_RETURN("Missing 'return'"),
    MISSING_IF("Missing 'if'"),
    INHERITANCE_CIRCULAR("Circular inheritance"), 
    INHERITANCE_NULL("Cannot inherit a from null"),
    INHERITANCE_MODIFIER_DOWNGRADE("Cannot override a private method"), 
    INHERITANCE_AMBIGUOUS("Ambiguous inheritance"), 
    INHERITANCE_MISSMATCHED_RETURN("Invalid overriding return type"), 
    METHOD_CALL_AMBIGUOUS("Ambigous method call"), 
    USE_AMBIGUOUS("Ambiguous used package"), 
    CLASS_DUPLICATE("Class already defined"), 
    MISSING_USE("Cannot find symbol - package"), 
    MISSING_CLASS("Cannot find symbol - class"),
    MISSING_VARIABLE("Cannot find symbol - variable"),
    IF_INVALID_EXPRESSION("Invalid if expression"), 
    MISSING_PARENT("Cannot find symbol - parent"), 
    MISMATCHED_TEMPLATES("Mismatched class template"), 
    INSTANTIATE_ABSTRACT("Cannot instantiate abstract class"),
    INSTANTIATE_THIS("Cannot instantiate 'me'"), 
    INSTANTIATE_GENERIC("Cannot instantiate generic object"), 
    INVALID_ERROR("Invalid error type"), 
    INVALID_RETURN_NOW("Invalid return now"), 
    MISSING_METHOD("Cannot find symbol - method"), 
    METHOD_DUPLICATE("Method already defined"),
    OTHER("other"), 
    INVALID_OPERATOR("Invalid operator"), 
    UNREACHABLE("Unreachable statements"), 
    DUPLICATE("Already defined"), 
    MISSING_MAIN("Missing main method"), 
    EOF("End of file error");

    String errorType;
    ErrorType(String st) {
        errorType = st;
    }

    @Override
    public String toString() {
        return errorType;
    }
}
