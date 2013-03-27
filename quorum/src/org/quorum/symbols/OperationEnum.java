/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 * An enumerator for the allowable operations.
 *
 * @author Andreas Stefik
 */
public enum OperationEnum {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/"),
    MODULUS("mod"),
    AND("and"),
    OR("or"),
    EQUALS("="),
    NOT_EQUALS("not="),
    GREATER_EQUALS(">="),
    GREATER(">"),
    LESS_EQUALS("<="),
    LESS("<"),
    NOT("not"),
    RANGE("to"),
    RETURN("return"),
    CAST("cast"), 
    IMPLICIT_CAST("implicitCast"),
    AUTOBOX_CAST("autoboxCast"),
    IS_A("is");

    String operation;
    OperationEnum(String st) {
        operation = st;
    }

    @Override
    public String toString() {
        return operation;
    }
}
