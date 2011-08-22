/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 * Enumerated type for Errors of exceptions available in the quorum language.
 *
 * @author Melissa Stefik
 */
public class ErrorTypeDescriptor extends Descriptor{

    public static final String ERROR = "Libraries.Language.Errors.Error";
    public static final String CAST_ERROR = "Libraries.Language.Errors.CastError";
    public static final String INDEX_OUT_OF_BOUNDS_ERROR = "Libraries.Language.Errors.InvalidLocationError";
    public static final String DIVIDE_BY_ZERO_ERROR = "Libraries.Language.Errors.DivideByZeroError";
    public static final String OUT_OF_BOUNDS_ERROR = "Libraries.Language.Errors.OutOfBoundsError";
    public static final String UNDEFINED_OBJECT_ERROR = "Libraries.Language.Errors.UndefinedObjectError";
    public static final String INPUT_OUTPUT_ERROR = "Libraries.Language.Errors.InputOutputError";
    public static final String FILE_NOT_FOUND_ERROR = "Libraries.Language.Errors.FileNotFoundError";
    public static final String END_OF_FILE_ERROR = "Libraries.Language.Errors.EndOfFileError";
    public static final String INVALID_ARGUMENT_ERROR = "Libraries.Language.Errors.InvalidArgumentError";
    public static final String INVALID = "Invalid";

    public static final String ALWAYS = "Always";
    private TypeDescriptor type;

    /**
     * Constructor
     */
    public ErrorTypeDescriptor() {
        setName(INVALID);
        type = new TypeDescriptor();
    }

    /**
     * Copy constructor for TypeDescriptor
     * @param type
     */
    public ErrorTypeDescriptor(ErrorTypeDescriptor type) {
        if(type.getName() != null) {
            super.setName(type.getName());
        }

        super.setColumnBegin(type.getColumnBegin());
        super.setColumnEnd(type.getColumnEnd());
        super.setLineBegin(type.getLineBegin());
        super.setLineEnd(type.getLineEnd());
    }

    /**
     * Gets a ErrorTypeDescriptor representing a general error type
     * @return
     */
    public static ErrorTypeDescriptor getErrorType() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(ERROR);
        return t;
    }

    /**
     * Gets a ErrorTypeDescriptor representing a cast error type
     * @return
     */
    public static ErrorTypeDescriptor getCastErrorType() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(CAST_ERROR);
        return t;
    }

    /**
     * Gets a ErrorTypeDescriptor representing a index out of bounds error type
     * @return
     */
    public static ErrorTypeDescriptor getIndexOutOfBoundsErrorType() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(INDEX_OUT_OF_BOUNDS_ERROR);
        return t;
    }

    /**
     * Gets a ErrorTypeDescriptor representing a divide by zero error.
     * @return
     */
    public static ErrorTypeDescriptor getDivideByZeroErrorType() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(DIVIDE_BY_ZERO_ERROR);
        return t;
    }
    
    /**
     * Gets a ErrorTypeDescriptor representing a divide by zero error.
     * @return
     */
    public static ErrorTypeDescriptor getOutOfBoundsError() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(OUT_OF_BOUNDS_ERROR);
        return t;
    }
    
    /**
     * Gets a ErrorTypeDescriptor representing a divide by zero error.
     * @return
     */
    public static ErrorTypeDescriptor getUndefinedObjectError() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(UNDEFINED_OBJECT_ERROR);
        return t;
    }
    
    /**
     * Gets a ErrorTypeDescriptor representing an IOError.
     * @return
     */
    public static ErrorTypeDescriptor getInputOutputError() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(INPUT_OUTPUT_ERROR);
        return t;
    }
    
    /**
     * Gets a ErrorTypeDescriptor representing an FileNotFoundError.
     * @return
     */
    public static ErrorTypeDescriptor getFileNotFoundError() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(FILE_NOT_FOUND_ERROR);
        return t;
    }

    /**
     * Gets a ErrorTypeDescriptor representing an EOFError.
     * @return
     */
    public static ErrorTypeDescriptor getEndOfFileError() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(END_OF_FILE_ERROR);
        return t;
    }


    /**
     * Gets a ErrorTypeDescriptor representing an InvalidArgumentError.
     * @return
     */
    public static ErrorTypeDescriptor getInvalidArgumentError() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(INVALID_ARGUMENT_ERROR);
        return t;
    }
    
    /**
     * Gets a ErrorTypeDescriptor representing a invalid error type
     * @return
     */
    public static ErrorTypeDescriptor getInvalidType() {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(INVALID);
        return t;
    }

    /**
     * Determines if the type is invalid.
     * @return
     */
    public boolean isInvalidType(){
        return getName().compareToIgnoreCase(INVALID) == 0;
    }

    /**
     * Returns a new type descriptor whose type is the static key of
     * the class in question.
     *
     * @param clazz
     * @return
     */
    public static ErrorTypeDescriptor getCustomErrorType(ClassDescriptor clazz) {
        ErrorTypeDescriptor t = new ErrorTypeDescriptor();
        t.setName(clazz.getStaticKey());
        return t;
    }

    @Override
    public String toString() {
        if(getName() != null ) {
            return getName();
        }
        else {
            return INVALID;
        }

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

    

}
