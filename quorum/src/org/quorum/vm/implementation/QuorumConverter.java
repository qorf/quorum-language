/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;


import org.objectweb.asm.Opcodes;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.Parameters;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;

/**
 *
 * @author Andreas Stefik
 */
public class QuorumConverter {
    /**
     * This method converts an expression value object to an Object that
     * can be interpreted correctly by the MethodVisitor.LdcInsn(Object cst)
     * method in the org.objectweb.asm.MethodVisitor class.
     * 
     * @param value
     * @return From the ASM documentation, a non null Integer, a Float, a Long, 
     * a Double a String (or a Type for .class constants, for classes whose 
     * version is 49.0 or more).
     */
    public static Object convert(ExpressionValue value) {
        if(value.getType().isInteger()) {
            return new Integer(value.getResult().integer);
        }
        
        
        else if(value.getType().isText()) {
            return value.getResult().text;
        }
        
        
        //ultimately, this should account for all possible configurations
        //see MethodVisitor.LdcInsn for more information
        return null;
    }
    
    /**
     * Converts the name of a raw class name to its corresponding interface name.
     * This does NOT handle conversion of static keys to interface names.
     * @param name
     * @return 
     */
    public static String convertClassNameToInterfaceName(String name) {
        return name + "$Interface";
    }
    
    
    /**
     * This method takes a class descriptor and a variable, then converts that
     * information into a hidden getter method name for a field variable. 
     * @param clazz
     * @param variable
     * @return 
     */
    public static String generateGetterNameFromField(ClassDescriptor clazz, VariableDescriptor variable) {
        String name = "Get$" + javaSafeKey(clazz.getStaticKey()) + "$" + variable.getName();
        return name;
    }
    
    /**
     * This method generates the signature of a getter method from a variable
     * descriptor. By default, this would have no parameters, (), and have
     * a return type appropriate to the type of the field variable.
     * 
     * @param variable
     * @return 
     */
    public static String generateGetterSignatureFromField(VariableDescriptor variable) {
        return "()" + QuorumConverter.convertTypeToBytecodeString(variable.getType());
    }
    
        /**
     * This method takes a class descriptor and a variable, then converts that
     * information into a hidden getter method name for a field variable. 
     * @param clazz
     * @param variable
     * @return 
     */
    public static String generateGetterNameFromSubField(TypeDescriptor variableType, String variable) {
        String name = "Get$" + javaSafeKey(variableType.getStaticKey()) + "$" + variable;
        return name;
    }
    
    /**
     * This method generates the signature of a getter method from a variable
     * descriptor. By default, this would have no parameters, (), and have
     * a return type appropriate to the type of the field variable.
     * 
     * @param variable
     * @return 
     */
    public static String generateGetterSignatureFromSubField(TypeDescriptor type) {
        return "()" + QuorumConverter.convertTypeToBytecodeString(type);
    }
    
    /**
     * This method takes a class descriptor and a variable, then converts that
     * information into a hidden setter method name for a field variable. 
     * @param clazz
     * @param variable
     * @return 
     */
    public static String generateSetterNameFromField(ClassDescriptor clazz, VariableDescriptor variable) {
        String name = "Set$" + javaSafeKey(clazz.getStaticKey()) + "$" + variable.getName();
        return name;
    }
    
    /**
     * This method takes a class descriptor and a variable name in that class,
     * then converts that information into a method name for an existing field.
     * @param clazz
     * @param variable
     * @return 
     */
    public static String generateSetterNameFromSubField(TypeDescriptor variableType, String variable){
        String name = "Set$" + javaSafeKey(variableType.getStaticKey()) + "$" + variable;
        return name;
    }
    
    /**
     * This generates a method signature for a setter method. By default,
     * the signature for this method will be the variable's type between 
     * parentheses ( type ), followed by V, for a void return type.
     * 
     * @param variable
     * @return 
     */
    public static String generateSetterSignatureFromField(VariableDescriptor variable) {
        return "("+ QuorumConverter.convertTypeToBytecodeString(variable.getType()) +")" + "V";
    }
    
    /**
     * This generates a method signature for a setter method. By default,
     * the signature for this method will be the variable's type between 
     * parentheses ( type ), followed by V, for a void return type.
     * 
     * @param variable
     * @return 
     */
    public static String generateSetterSignatureFromSubField(TypeDescriptor type) {
        return "("+ QuorumConverter.convertTypeToBytecodeString(type) +")" + "V";
    }
    
    /**
     * This method takes a method descriptor and converts into a signature,
     * with appropriate parameters in their String bytecode formats.
     * 
     * @param descriptor
     * @return 
     */
    public static String convertMethodDescriptorToBytecodeSignature(MethodDescriptor descriptor) {
        String result = "";
        TypeDescriptor ret = descriptor.getReturnType();
        String parametersString = "(";
        Parameters parameters = descriptor.getParameters();
        for(int i = 0; i < parameters.size(); i++) {
            ParameterDescriptor param = parameters.get(i);
            TypeDescriptor type = param.getType();
            if (param.getType().isPrimitiveType())
                parametersString += convertTypeToBytecodeString(type);
            else {
                parametersString += "L" + convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(type.getStaticKey())) + ";";
                type.setBytecodeInterface(true);
            }
        }
        
        parametersString += ")";
        
        String returnString = null;
        if (ret.isPrimitiveType() || ret.isVoid()){
            returnString = convertTypeToBytecodeString(ret);
        }else{
            returnString = "L" + convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(ret.getStaticKey())) + ";";
            ret.setBytecodeInterface(true);
        }
        
        result = parametersString + returnString;
        return result;
    }
    
    /**
     * This method takes a method descriptor and converts into a signature,
     * with appropriate parameters in their String bytecode formats.
     * 
     * @param descriptor
     * @return 
     */
    public static String convertPrimitiveMethodDescriptorToBytecodeSignature(MethodDescriptor descriptor, TypeDescriptor primitiveType) {
        String result = "";
        TypeDescriptor ret = descriptor.getReturnType();
        String parametersString = "(";
        
        if(primitiveType.isInteger()){
            parametersString += "I";
        }else if(primitiveType.isNumber()){
            parametersString += "D";
        }else if(primitiveType.isBoolean()){
            parametersString += "Z";
        }else{
            parametersString += "Ljava/lang/String;";
        }
        
        Parameters parameters = descriptor.getParameters();
        for(int i = 0; i < parameters.size(); i++) {
            ParameterDescriptor param = parameters.get(i);
            TypeDescriptor type = param.getType();
            if (param.getType().isPrimitiveType())
                parametersString += convertTypeToBytecodeString(type);
            else {
                parametersString += "L" + convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(type.getStaticKey())) + ";";
                type.setBytecodeInterface(true);
            }
        }
        
        parametersString += ")";
        
        String returnString = null;
        if (ret.isPrimitiveType() || ret.isVoid()){
            returnString = convertTypeToBytecodeString(ret);
        }else{
            returnString = "L" + convertClassNameToInterfaceName(QuorumConverter.convertStaticKeyToBytecodePath(ret.getStaticKey())) + ";";
            ret.setBytecodeInterface(true);
        }
        
        result = parametersString + returnString;
        return result;
    }
    
    /**
     * This method takes a static key from a parent object of a class
     * and converts it to a valid name to be used by a class.
     * 
     * @param key
     * @return 
     */
    public static String convertParentStaticKeyToValidName(String key) {
        return "$" + javaSafeKey(key);
    }
    
    /**
     * This method takes a static key from a parent object of a class
     * and converts it to a valid name to be used by a class.
     * 
     * @param key
     * @return 
     */
    public static String getChildID() {
        return "$hidden$";
    }
    
    /**
     * Converts a static key for a class into a full class name.
     * @param key
     * @return 
     */
    public static String convertStaticKeyToBytecodePath(String key) {
        if(key.charAt(0) == '.') { //a quorum class in the default package
            key = key.substring(1);
        }
        String result = "quorum/" + key.replaceAll("\\.", "/");
        return result;
    }
    
    /**
     * Converts a static key for a class into a standard java path name.
     * 
     * @param key
     * @return 
     */
    public static String convertStaticKeyToManifestPath(String key) {
        if(key.charAt(0) == '.') { //a quorum class in the default package
            key = key.substring(1);
        }
        String result = "quorum." + key;
        return result;
    }
    
    /**
     * Converts a static key for a class into a full class name. In this case,
     * the type name is converted with additional information, so 
     * quorum/test would be Lquorum/test;
     * 
     * @param key
     * @return 
     */
    public static String convertStaticKeyToBytecodePathTypeName(String key) {
        return "L" + convertStaticKeyToBytecodePath(key) + ";";
    }
    
    /**
     * Converts a static key for a class into a full class name.
     * @param key
     * @return 
     */
    public static String convertStaticKeyToPluginPath(String key) {
        return "plugins/" + convertStaticKeyToBytecodePath(key);
    }
    
    /**
     * Converts a static key for a class into a full class name. In this case,
     * the type name is converted with additional information, so 
     * plugin/quorum/test would be Lplugin/quorum/test;
     * 
     * @param key
     * @return 
     */
    public static String convertStaticKeyToPluginPathTypeName(String key) {
        return "Lplugins/" + convertStaticKeyToBytecodePath(key) + ";";
    }
    
    /**
     * This method takes a type descriptor and converts it into a valid 
     * type descriptor value in Java bytecode. This conversion copies
     * largely page 11 of the asm-guide.pdf documentation for ASM. For example
     * the type integer would convert to I, while text would convert to
     * Ljava/lang/String;.
     * 
     * @param type
     * @return 
     */
    public static String convertTypeToBytecodeString(TypeDescriptor type) {
        if(type.isBoolean()) {
            return "Z";
        }
        else if(type.isText()) {
            return "Ljava/lang/String;";
        }
        else if(type.isNumber()) {
            return "D";
        }
        else if(type.isInteger()) {
            return "I";
        }
        else if(type.isVoid()) {
            return "V";
        }
        else {
            String key = type.getStaticKey();
            String convert = convertStaticKeyToBytecodePath(key);
            
            if(type.isBytecodeInterface()){
                convert = convertClassNameToInterfaceName(convert);
            }
            
            return "L" + convert + ";";
        }
    }
    
        /**
     * This method takes a type descriptor and converts it into a valid 
     * type descriptor value in Java bytecode.
     * 
     * @param type
     * @return 
     */
    public static String convertObjectTypeToPath(TypeDescriptor type) {
        
            String key = type.getStaticKey();
            String convert = convertStaticKeyToBytecodePath(key);
            
            if(type.isBytecodeInterface()){
                convert = convertClassNameToInterfaceName(convert);
            }
            
            return convert;
    }
    /**
     * This method takes a Quorum type descriptor and converts it into
     * the name of the type.
     * 
     * @param type
     * @return 
     */
    public static String convertTypeToJavaTypeEquivalent(TypeDescriptor type) {
        if(type.isBoolean()) {
            return "boolean";
        }
        else if(type.isText()) {
            return "java/lang/String";
        }
        else if(type.isNumber()) {
            return "double";
        }
        else if(type.isInteger()) {
            return "int";
        }
        else if(type.isVoid()) {
            return "void";
        }
        else {
            String key = type.getStaticKey();
            return convertStaticKeyToBytecodePath(key);
        }    
    }
    
    /**
     * This method takes a Quorum type descriptor and converts it into the 
     * class name of an auto-boxed type.
     * 
     * @param type
     * @return 
     */
    public static String convertTypeToJavaClassTypeEquivalent(TypeDescriptor type) {
        if(type.isBoolean()) {
            return "java/lang/Boolean";
        }
        else if(type.isText()) {
            return "java/lang/String";
        }
        else if(type.isNumber()) {
            return "java/lang/Double";
        }
        else if(type.isInteger()) {
            return "java/lang/Integer";
        }
        else {
            String key = type.getStaticKey();
            return convertStaticKeyToBytecodePath(key);
        }    
    }
    
    
    public static Object convertTypeToBytecodeType(TypeDescriptor type){
        if(type.isBoolean()){
            return Opcodes.INTEGER;
        }else if (type.isText()){
            return "java/lang/String";
        }else if(type.isInteger()){
            return Opcodes.INTEGER;
        }else if(type.isNumber()){
            return Opcodes.DOUBLE;
        }else if(type.isIntegerClass() || type.isNumberClass()){
            return Opcodes.TOP;
        }else if(type.isObjectClass()){
            return "java/lang/Object";
        }else if(type.isVoid()){//not sure about this one?
            return Opcodes.NULL;
        }else{
            String s = type.getName();
            return convertStaticKeyToBytecodePath(s);
        }
    }
    
    public static String convertPrimitiveToObjectPath(TypeDescriptor type){
        if(type.isBoolean()){
            return "plugins/quorum/Libraries/Language/Types/Boolean";
        }else if (type.isText()){
            return "plugins/quorum/Libraries/Language/Types/Text";
        }else if(type.isInteger()){
            return "plugins/quorum/Libraries/Language/Types/Integer";
        }else if(type.isNumber()){
            return "plugins/quorum/Libraries/Language/Types/Number";
        }else{
            return null;
        }
    }
    
    /**
     * Takes a type descriptor object and converts it into the appropriate
     * opcode for a return statement.
     * 
     * @param type The most common cases are IRETURN for ints, DRETURN for doubles
     *        and RETURN for void.
     * @return 
     */
    public static int convertTypeToReturnOpcode(TypeDescriptor type) {
        if(type.isVoid()) {
            return Opcodes.RETURN;
            
        }
        else if(type.isNumber()) {
            return Opcodes.DRETURN;
        }
        else if(type.isInteger() || type.isBoolean()) {
            return Opcodes.IRETURN;
        }
        else {
            return Opcodes.ARETURN;
        }
    }
    
    /**
     * Generates the appropriate opcode for loading an item onto the stack
     * from a given Quorum type descriptor.
     * 
     * @param type
     * @return 
     */
    public static int getLoadOpcode(TypeDescriptor type) {
        if (type.isInteger() || type.isBoolean())
            return Opcodes.ILOAD;
        else if(type.isNumber())
            return Opcodes.DLOAD;
        return Opcodes.ALOAD;
    }
    
    /**
     * Returns the appropriate opcode for storing a particular 
     * value from the stack, given a Quorum type descriptor.
     * 
     * @param type
     * @return 
     */
    public static int getStoreOpcode(TypeDescriptor type) {
        if (type.isInteger() || type.isBoolean())
            return Opcodes.ISTORE;
        else if(type.isNumber())
            return Opcodes.DSTORE;
        return Opcodes.ASTORE;
    }

    /**
     * Helper method: determines the size of a type for a variable position number.
     * 
     * @param valueType returns 2 if a number and 1 in any other case.
     * @return 
     */
    public static int getSizeOfType(TypeDescriptor valueType){
        if(valueType.isNumber()){
            return 2;
        }else{
            return 1;
        }
    }
    
    /**
     * Convert a static key to a form that is safe to use in a .class file.
     * This is used when generating method names or variable names in Java
     * .class files, as a "." cannot be present in a method or variable name.
     * 
     * @param key
     * @return The static key with '.' replaced with '$' (for example, Libraries.Language.Object becomes Libraries$Language$Object). 
     */
    private static String javaSafeKey(String key) {
        return key.replaceAll("\\.", "\\$");
    }
}
