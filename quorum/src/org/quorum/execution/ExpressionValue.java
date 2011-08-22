/*
 * ExpressionValue.java
 *
 * Created on February 20, 2007, 1:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.execution;

import org.quorum.symbols.Result;
import org.quorum.symbols.TypeDescriptor;

/**
 * This class represents the value of an expression after being evaluated
 * at runtime. It includes a TypeDescriptor that gives information on the
 * object's type and its current value in the field named result.
 *
 * @author Andreas Stefik
 */
public class ExpressionValue {

    /** This represents the type of the system. getDataType() is deprecated.
     * 
     */
    private TypeDescriptor type;

    /**
     * An integer representing when the result was set into this
     * expression value object.
     */
    private TimeStamp timeStamp;
    
    /**
     * This class represents the result of the expression value.
     */
    private Result result;

    /**
     * This is the location where this particular expression will be stored.
     */
    private int register;
    
    private int tempArrayPosition = -1;

    
    private boolean isArray = false;

    /**
     * Not all ExpressionValue objects have a name. If the name is null, a fair
     * assumption is that the object is a temporary variable stored for 
     * an intermediate part of an expression. If the name is specified, then the
     * opposite is true, the expression is actually a named variable in the program.
     */
    private String name;

    /** If the expressionvalue is an object, this is its hash value in the heap.
     * 
     */
    private int objectHash = -1;

    
    /** Creates a new instance of ExpressionValue */
    public ExpressionValue() {
        result = new Result();
    }

    /**
     * Copy constructor for ExpressionValue.
     * @param value the ExpressionValue to copy
     */
    public ExpressionValue(ExpressionValue value) {
        register = value.getRegister();
        type = new TypeDescriptor(value.getType());
        tempArrayPosition = value.tempArrayPosition;
        timeStamp = new TimeStamp(value.getTimeStamp());
        result = new Result(value.getResult());
        if(value.getName() != null) {
            name = value.getName();
        }
        this.objectHash = value.objectHash;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int temp) {
        this.register = temp;
    }

    @Override
    public String toString() {
        if(type != null){
            if(!type.isPrimitiveType()) {
                if(objectHash == -1) {
                    return "undefined";
                }
                else {
                    return "#" + objectHash;
                }
            }
            //it's not an object
            if(this.getResult() != null) {
                return this.getResult() + "";
            }
            else {
                return super.toString();
            }
        }else{
            return super.toString();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

    public int getTempArrayPosition() {
        return tempArrayPosition;
    }

    public void setTempArrayPosition(int tempArrayPosition) {
        this.tempArrayPosition = tempArrayPosition;
    }

    /**
     * This returns true if this expression value is an object and its
     * hash value is equal to -1, the symbolic indicator of null.
     * 
     * @return
     */
    public boolean isNull() {
        return this.getObjectHash() == -1 && !this.getType().isPrimitiveType();
    }

    /**
     * @return the type
     */
    public TypeDescriptor getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeDescriptor type) {
        this.type = type;
    }

    /**
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * @return the timeStamp
     */
    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the objectHash
     */
    public int getObjectHash() {
        return objectHash;
    }

    /**
     * @param objectHash the objectHash to set
     */
    public void setObjectHash(int objectHash) {
        this.objectHash = objectHash;
    }

    /**
     * Returns a default ExpressionValue for a particular primitive type.
     * If a non-primitive type is returned, this method returns null.
     * 
     * @param type
     * @return
     */
    public static ExpressionValue getPrimitiveDefault(TypeDescriptor type) {
        if(!type.isPrimitiveType()) {
            return null;
        }
        ExpressionValue value = new ExpressionValue();
        value.setType(type);
        Result result = Result.getDefaultResult(type);
        value.setResult(result);

        return value;
    }

    /**
     * Returns an expression value of an object type. While this result returns
     * a valid expression value, it does not actually put this object onto
     * the heap, nor does it initialize the object. In other words, this
     * creates a null pointer to the appropriate type.
     * 
     * @param type
     * @return
     */
    public static ExpressionValue getObjectDefault(TypeDescriptor type) {
        if(type.isPrimitiveType()) {
            return null;
        }
        ExpressionValue value = new ExpressionValue();
        value.setType(type);
        value.setObjectHash(-1); //a null pointer, on purpose, as this is not on the heap.
        return value;
    }
}
