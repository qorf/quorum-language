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
    
    public void VisitCode() {
        methodVisitor.visitCode();
    }
    public void VisitEnd() {
        methodVisitor.visitEnd();
    }
    public void VisitField(int opcode, String owner, String name, String description) {
        methodVisitor.visitFieldInsn(opcode, owner, name, description);
    }
    public void VisitInstruction(int opcode) {
        methodVisitor.visitInsn(opcode);
    }
    public void VisitMaxSize(int stack, int locals) {
        methodVisitor.visitMaxs(stack, locals);
    }
    public void VisitMethodInvoke(int opcode, String owner, String name, String description, boolean isInterface) {
        methodVisitor.visitMethodInsn(opcode, owner, name, description, isInterface);
    }
    public void VisitType(int opcode, String className) {
        methodVisitor.visitTypeInsn(opcode, className);
    }
    public void VisitVariable(int opcode, int variable) {
        methodVisitor.visitVarInsn(opcode, variable);
    }
}
