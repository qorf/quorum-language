/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Andreas Stefik
 */
public class ExpressionValueConverter {
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
        if(value.getType().isText()) {
            return value.getResult().text;
        }
        
        //ultimately, this should account for all possible configurations
        //see MethodVisitor.LdcInsn for more information
        return null;
    }
}
