/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;


import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.Parameters;
import org.quorum.symbols.TypeDescriptor;

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
    
    public static String convertMethodDescriptorToBytecodeSignature(MethodDescriptor descriptor) {
        String result = "";
        TypeDescriptor ret = descriptor.getReturnType();
        String returnString = convertTypeToBytecodeString(ret);
        String parametersString = "(";
        Parameters parameters = descriptor.getParameters();
        for(int i = 0; i < parameters.size(); i++) {
            ParameterDescriptor param = parameters.get(i);
            TypeDescriptor type = param.getType();
            parametersString += convertTypeToBytecodeString(type);
        }
        
        parametersString += ")";
        
        result = parametersString + returnString;
        return result;
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
     * This method takes a type descriptor and converts it into a valid 
     * type descriptor value in Java bytecode. This conversion copies
     * largely page 11 of the asm-guide.pdf documentation for ASM.
     * 
     * TODO: Finish conversions.
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
            return null;
        }
    }
}
