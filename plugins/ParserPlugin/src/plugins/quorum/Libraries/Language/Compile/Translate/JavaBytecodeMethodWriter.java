/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import org.objectweb.asm.MethodVisitor;

/**
 *
 * @author stefika
 */
public class JavaBytecodeMethodWriter {
    public java.lang.Object $me = null;
    private MethodVisitor methodVisitor;

    /**
     * @return the methodVisitor
     */
    public MethodVisitor getMethodVisitor() {
        return methodVisitor;
    }

    /**
     * @param methodVisitor the methodVisitor to set
     */
    public void setMethodVisitor(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }
    
}
