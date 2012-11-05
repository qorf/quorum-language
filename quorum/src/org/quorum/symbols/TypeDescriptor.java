/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *  This class describes either a built-in type or a user created type.
 * @author Andreas Stefik
 */
public class TypeDescriptor extends Descriptor {
    public static final String INTEGER = "integer";
    public static final String NUMBER = "number";
    public static final String BOOLEAN = "boolean";
    public static final String TEXT = "text";
    public static final String VOID = "void"; // for functions with no return type
    public static final String ARRAY = "array";
    public static final String ARRAY_CLASS = "Libraries.Containers.Array";
    public static final String INTEGER_OBJECT = "Libraries.Language.Types.Integer";
    public static final String NUMBER_OBJECT = "Libraries.Language.Types.Number";
    public static final String TEXT_OBJECT = "Libraries.Language.Types.Text";
    public static final String BOOLEAN_OBJECT = "Libraries.Language.Types.Boolean";
    public static final String OBJECT = "Libraries.Language.Object";
    public static final String CAST_ERROR_OBJECT = "Libraries.Language.Errors.CastError";
    public static final String ERROR_OBJECT = "Libraries.Language.Errors.Error";
    public static final String NULL = "NULL_TYPE";

    private boolean bytecodeInterface = false;
    private boolean inferable = true;
    
    private String templateName = null;
    private ArrayList<GenericDescriptor> subTypes = new ArrayList<GenericDescriptor>();
    private int expressionLevel = 0;
    
    /**
     * Gets a TypeDescriptor representing a void type
     * @return
     */
    public static TypeDescriptor getVoidType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(VOID);
        return t;
    }
    
    public static TypeDescriptor getIntegerType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(INTEGER);
        return t;
    }
    
    public static TypeDescriptor getNumberType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(NUMBER);
        return t;
    }
    
    public static TypeDescriptor getBooleanType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(BOOLEAN);
        return t;
    }
    
    public static TypeDescriptor getTextType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TEXT);
        return t;
    }

    public static TypeDescriptor getArrayType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(ARRAY);
        return t;
    }

    public static TypeDescriptor getIntegerObjectType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(INTEGER_OBJECT);
        return t;
    }

    public static TypeDescriptor getNumberObjectType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(NUMBER_OBJECT);
        return t;
    }

    public static TypeDescriptor getTextObjectType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TEXT_OBJECT);
        return t;
    }

    public static TypeDescriptor getBooleanObjectType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(BOOLEAN_OBJECT);
        return t;
    }
    
    public static TypeDescriptor getErrorObjectType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(ERROR_OBJECT);
        return t;
    }
    
    public static TypeDescriptor getCastErrorObjectType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(CAST_ERROR_OBJECT);
        return t;
    }

    public static TypeDescriptor getNullType() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(NULL);
        return t;
    }

    /**
     * Returns a type representing Libraries.Language.Object, the base class
     * for all objects on the system.
     * 
     * @return
     */
    public static TypeDescriptor getSystemObject() {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(TypeDescriptor.OBJECT);
        return t;
    }

    /**
     * Determines whether this object is of type void.
     *
     * @return
     */
    public boolean isVoid() {
        return getName().compareToIgnoreCase(VOID) == 0;
    }

    /**
     * Determines whether this object is of type integer.
     * 
     * @return
     */
    public boolean isInteger() {
        return getName().compareToIgnoreCase(INTEGER) == 0;
    }

    /**
     * Determines whether this object is of type number.
     *
     * @return
     */
    public boolean isNumber() {
        return getName().compareToIgnoreCase(NUMBER) == 0;
    }

    /**
     * Determines whether this object is of type text.
     *
     * @return
     */
    public boolean isText() {
        return getName().compareToIgnoreCase(TEXT) == 0;
    }

    /**
     * Determines whether this object is of type text.
     *
     * @return
     */
    public boolean isBoolean() {
        return getName().compareToIgnoreCase(BOOLEAN) == 0;
    }

    /**
     * This method determines whether this object is of type null. It does
     * not determine whether this object itself is null.
     * 
     * @return
     */
    public boolean isNull() {
        return getName().compareToIgnoreCase(NULL) == 0;
    }

    /**
     * Determines if this is of type Libraries.Containers.Array.
     * 
     * @return
     */
    public boolean isArrayClass() {
        return getName().compareToIgnoreCase(ARRAY_CLASS) == 0;
    }

    /**
     * Determines if this is of type Libraries.Language.Types.Integer.
     *
     * @return
     */
    public boolean isIntegerClass() {
        return getName().compareToIgnoreCase(INTEGER_OBJECT) == 0;
    }

    /**
     * Determines if this is of type Libraries.Language.Types.Number.
     *
     * @return
     */
    public boolean isNumberClass() {
        return getName().compareToIgnoreCase(NUMBER_OBJECT) == 0;
    }

    /**
     * Determines if this is of type Libraries.Language.Types.Text.
     *
     * @return
     */
    public boolean isTextClass() {
        return getName().compareToIgnoreCase(TEXT_OBJECT) == 0;
    }

    /**
     * Determines if this is of type Libraries.Language.Types.Boolean.
     *
     * @return
     */
    public boolean isBooleanClass() {
        return getName().compareToIgnoreCase(BOOLEAN_OBJECT) == 0;
    }
    
    /**
     * Determines if this is of type Libraries.Language.Errors.CastError.
     *
     * @return
     */
    public boolean isCastErrorClass() {
        return getName().compareToIgnoreCase(BOOLEAN_OBJECT) == 0;
    }

    /**
     * Determines if this is a type Libraries.Language.Object
     * 
     * @return
     */
    public boolean isObjectClass() {
        return getName().compareToIgnoreCase(OBJECT) == 0;
    }

    /**
     * Returns a new type descriptor whose type is the static key of
     * the class in question.
     * 
     * @param clazz
     * @return
     */
    public static TypeDescriptor getClassType(ClassDescriptor clazz) {
        TypeDescriptor t = new TypeDescriptor();
        t.setName(clazz.getStaticKey());
        Iterator<GenericDescriptor> templateVariables = clazz.getTemplateVariables();
        while(templateVariables.hasNext()){
            GenericDescriptor next = templateVariables.next();
            t.addSubType(next);
        }
        return t;
    }


    public TypeDescriptor() {
        setName(VOID);
    }

    /**
     * Copy constructor for TypeDescriptor
     * @param type
     */
    public TypeDescriptor(TypeDescriptor type) {
        if(type.getName() != null) {
            super.setName(type.getName());
        }

        if(type.getTemplateName() != null) {
            setTemplateName(type.getTemplateName());
        }
        
        Iterator<GenericDescriptor> st = type.getSubTypes();
        while(st.hasNext()){
            GenericDescriptor next = new GenericDescriptor(st.next());
            addSubType(next);
        }

        super.setColumnBegin(type.getColumnBegin());
        super.setColumnEnd(type.getColumnEnd());
        super.setLineBegin(type.getLineBegin());
        super.setLineEnd(type.getLineEnd());
        
        setNumBytes(type.getNumBytes());
    }

    /**
     * Determines whether this particular type is considered a "primitive"
     * type for a particular language. This method should be overriden
     * if types are different for a particular language implementation.
     * @return
     */
    public boolean isPrimitiveType() {
        if(getName().compareTo(TEXT)==0) {
            return true;
        }
        else if(getName().compareTo(NUMBER)==0) {
            return true;
        }
        else if(getName().compareTo(INTEGER)==0) {
            return true;
        }
        else if(getName().compareTo(BOOLEAN)==0) {
            return true;
        }
        //it does not appear to be a primitive
        return false;
    }

    /**
     * This function returns the proper name of a primitive type if
     * the string is a representative of a primitive.
     * 
     * @param string
     * @return
     */
    public static String isPrimitiveType(String string) {
        if(string.compareTo(TEXT)==0) {
            return TEXT;
        }
        else if(string.compareTo(NUMBER)==0) {
            return NUMBER;
        }
        else if(string.compareTo(INTEGER)==0) {
            return INTEGER;
        }
        else if(string.compareTo(BOOLEAN)==0) {
            return BOOLEAN;
        }
        //it does not appear to be a primitive
        return null;
    }

    /** The number of bytes on the system this type takes up.
     * 
     */
    private int numBytes = 0;

   

    /**
     * @return the numBytes
     */
    public int getNumBytes() {
        return numBytes;
    }

    /**
     * @param numBytes the numBytes to set
     */
    public void setNumBytes(int numBytes) {
        this.numBytes = numBytes;
    }

    
    @Override
    public String toString() {
        if(getName() != null && !isNull()) {
            return getName();
        }
        else if(isNull()){
            return "undefined";
        }
        else {
            return "<undefined>";
        }

    }

    /**
     * This name represents the placeholder value in a templated class. For example,
     * if you declared a templated class "class Test<Type>" the template name would
     * be "Type".
     *
     * This method returns a string that represents the template name.
     *
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * This name represents the placeholder value in a templated class. For example,
     * if you declared a templated class "class Test<Type>" the template name would
     * be "Type".
     *
     * This method sets the value of the template name to some string value.
     *
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * Add a Type descriptor for a templated type. eg. Array<Type> would
     * generate a Type descriptor for Array then populate the templated type
     * with a type descriptor for Type.
     *
     * @param type
     * @return
     */
    public boolean addSubType(GenericDescriptor type){
        return subTypes.add(type);
    }

    /**
     *
     * @param types
     * @return
     */
    public boolean setSubTypes(ArrayList<GenericDescriptor> types){
        subTypes = types;
        return true;
    }

    /**
     * e.g. returning a type descriptor for Array<Type> would return the
     * type descriptor for Type
     *
     * @return iterator of TypeDescriptors that represent a types templated values.
     */
    public Iterator<GenericDescriptor> getSubTypes(){
        if(subTypes != null) {
            return subTypes.iterator();
        }
        else {
            ArrayList<GenericDescriptor> list = new ArrayList<GenericDescriptor>();
            return list.iterator();
        }
    }
    
    /**
     * Returns true if the object has a template name (this indicates you
     * are dealing with the type descriptor prior to type erasure). After type
     * erasure has occurred this template name will go away.
     * @return 
     */
    public boolean isTemplated(){
        if(templateName == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * returns true if a TypeDescriptor is sub typed.
     * @return
     */
    public boolean hasSubTypes(){
        if(subTypes.isEmpty()){
            return false;
        }else{
            return true;
        }


    }

    public String generateTypeKey(){
        String name = this.getName();
        if(this.hasSubTypes()){
            name = name + "<";
            Iterator<GenericDescriptor> subTypes1 = getSubTypes();
            while(subTypes1.hasNext()){
                GenericDescriptor next = subTypes1.next();
                name = name + next.getType().generateTypeKey();
                if(subTypes1.hasNext()){
                    name = name + ", ";
                }
            }
            name = name + ">";
        }
        this.setName(name);
        return name;
    }

    /**
     * This method converts any primitive type to its appropriate class
     * variable. If this TypeDescriptor does not represent a primitive type,
     * this call does nothing.
     */
    public void convertToClass() {
        if(this.isInteger()) {
            super.setName(INTEGER_OBJECT);
        }
        if(this.isBoolean()) {
            super.setName(BOOLEAN_OBJECT);
        }
        if(this.isText()) {
            super.setName(TEXT_OBJECT);
        }
        if(this.isNumber()) {
            super.setName(NUMBER_OBJECT);
        }
    }

    /**
     * This method converts any object type to its appropriate primitive
     * variable. If this TypeDescriptor does not represent a primitive type,
     * this call does nothing.
     */
    public void convertToPrimitive() {
        if(this.isIntegerClass()) {
            super.setName(INTEGER);
        }
        if(this.isBooleanClass()) {
            super.setName(BOOLEAN);
        }
        if(this.isTextClass()) {
            super.setName(TEXT);
        }
        if(this.isNumberClass()) {
            super.setName(NUMBER);
        }
    }

    /**
     * NOTE: NEVER USE THIS. This method is an artifact of the conversion from 
     * the bytecode stack using TypeDescriptors instead of a custom class.
     * This method is meaningless in the interpreter and should be ignored. For
     * bytecode generation, this method lets us know whether the type is 
     * an "interface" automatically behind the scenes.
     * 
     * @return the bytecodeInterface
     */
    public boolean isBytecodeInterface() {
        return bytecodeInterface;
    }

    /**
     * NOTE: NEVER USE THIS. This method is an artifact of the conversion from 
     * the bytecode stack using TypeDescriptors instead of a custom class.
     * This method is meaningless in the interpreter and should be ignored. For
     * bytecode generation, this method sets whether the type is 
     * an "interface" automatically behind the scenes.
     * 
     * @param bytecodeInterface the bytecodeInterface to set
     */
    public void setBytecodeInterface(boolean bytecodeInterface) {
        this.bytecodeInterface = bytecodeInterface;
    }

    /**
     * set the expression level of this type.
     * 
     * @param expressionLevel 
     */
    public void setExpressionLevel(int expressionLevel){
        this.expressionLevel = expressionLevel;
    }
    
    /**
     * get the expression level of this type.
     * 
     * @return expression level of the current type descriptor
     */
    public int getExpressionLevel(){
        return expressionLevel;
    }

    public void setInferable(boolean b) {
        inferable = b;
    }
    
    public boolean isInferable(){
        return inferable;
    }

    public boolean compare(TypeDescriptor returnType) {
        if(this.getStaticKey().compareTo(returnType.getStaticKey()) == 0){
            if(this.hasSubTypes() && returnType.hasSubTypes()){
                Iterator<GenericDescriptor> thisSubTypes = this.getSubTypes();
                Iterator<GenericDescriptor> returnSubTypes = returnType.getSubTypes();
                if(thisSubTypes.hasNext() && returnSubTypes.hasNext()){
                    TypeDescriptor thisTypes = thisSubTypes.next().getType();
                    TypeDescriptor returnTypes = returnSubTypes.next().getType();
                    if(thisTypes.compare(returnTypes)){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
