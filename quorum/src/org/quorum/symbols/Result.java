/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.symbols;

/**
 * A result class that represents the actual value of a particular
 * operation.
 * 
 * @author Andreas Stefik
 */
public class Result {

    public static final int INTEGER = 0;
    public static final int NUMBER = 1;
    public static final int BOOLEAN = 2;
    public static final int STRUCTURE = 3;
    public static final int TEXT = 4;
    public static final int NULL = 5;
    public int type = 0;
    public int integer = 0;
    public double number = 0;
    public boolean boolean_value = false;
    public String text = "";
    public Structure structure;
    /**
     * This is a temporary hack that will be removed once ExpressionValue
     * has been properly refactored.
     */
    public boolean noConvert = false;

    @Override
    public String toString() {
        switch (type) {
            case 0:
                return "" + integer;
            case 1:
                return "" + number;
            case 2:
                return "" + boolean_value;
            case 3:
                if (structure == null) {
                    return "";
                } else {
                    return "" + structure;
                }
            case 4:
                return text;
            case 5:
                return "undefined";
            default:
                return "";
        }
    }

    public Result() {
    }

    /**
     * Copies a result into a new result
     * @param result
     */
    public Result(Result result) {
        integer = result.integer;
        number = result.number;
        boolean_value = result.boolean_value;
        text = result.text;
        structure = result.structure;
        type = result.type;
    }

    /**
     * gets the results type
     * @return
     */
    public TypeDescriptor getType() {
        if (type == INTEGER) {
            return TypeDescriptor.getIntegerType();
        } else if (type == NUMBER) {
            return TypeDescriptor.getNumberType();
        } else if (type == TEXT) {
            return TypeDescriptor.getTextType();
        } else if (type == BOOLEAN) {
            return TypeDescriptor.getBooleanType();
        } else if (type == NULL) {
            return TypeDescriptor.getNullType();
        } else {
            return TypeDescriptor.getVoidType();
        }
    }

    /**
     * Returns a default Result value for a particular type. If no primitive
     * type is specified, an integer type is returned.
     * @param type
     * @return
     */
    public static Result getDefaultResult(TypeDescriptor type) {
        Result result = new Result();
        if (type == null || type.isInteger()) {
            result.type = Result.INTEGER;
        } else if (type.isNumber()) {
            result.type = Result.NUMBER;
        } else if (type.isText()) {
            result.type = Result.TEXT;
        } else if (type.isNull()) {
            result.type = Result.NULL;
        } else if (type.isBoolean()) {
            result.type = Result.BOOLEAN;
        }
        return result;
    }
}
