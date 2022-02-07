/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.ICONST_4;
import static org.objectweb.asm.Opcodes.ICONST_5;
import static org.objectweb.asm.Opcodes.ICONST_M1;
import static org.objectweb.asm.Opcodes.SIPUSH;

/**
 *
 * @author stefika
 */
public class JavaBytecodeMethodWriter {
    public java.lang.Object me_ = null;
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
    
    public void VisitJump(int opcode, quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ label) {
        Label l = getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel)label);
        methodVisitor.visitJumpInsn(opcode, l);
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
    
    public void VisitConstant(String value) {
        Object v = value;
        methodVisitor.visitLdcInsn(v);
    }
    
    public void VisitConstant(int value) {
        if ((value >= -1 && value <= 5)) {
            switch (value) {
                case -1:
                    methodVisitor.visitInsn(ICONST_M1);
                    break;
                case 0:
                    methodVisitor.visitInsn(ICONST_0);
                    break;
                case 1:
                    methodVisitor.visitInsn(ICONST_1);
                    break;
                case 2:
                    methodVisitor.visitInsn(ICONST_2);
                    break;
                case 3:
                    methodVisitor.visitInsn(ICONST_3);
                    break;
                case 4:
                    methodVisitor.visitInsn(ICONST_4);
                    break;
                case 5:
                    methodVisitor.visitInsn(ICONST_5);
                    break;
            }
        } else if ((value >= -128 && value <= 127)) {
            methodVisitor.visitIntInsn(BIPUSH, value);
        } else if ((value >= -32768 && value <= 32767)) {
            methodVisitor.visitIntInsn(SIPUSH, value);
        } else {
            methodVisitor.visitLdcInsn(value);
        }
    }
    
    public void VisitConstant(boolean value) {
        if (value) {
            methodVisitor.visitInsn(ICONST_1);
        } else {
            methodVisitor.visitInsn(ICONST_0);
        }
    }
    
    public void VisitConstant(double value) {
        Object v = value;
        methodVisitor.visitLdcInsn(v);
    }
    
    public void VisitLocalVariable(String name, String description, String signature, 
            quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ start,
            quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ finish,
            int index) {
        Label startLabel = getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel) start);
        Label finishLabel = getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel) finish);
        methodVisitor.visitLocalVariable(name, description, signature, startLabel, finishLabel, index);
    }
    
    public void VisitUndefinedConstant() {
        methodVisitor.visitInsn(ACONST_NULL);
    }
    
    public void VisitLabel(quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ label) {
        Label l = getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel)label);
        methodVisitor.visitLabel(l);
    }
    
    public void VisitTryCatchBlock(
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ tryStart, 
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ tryEnd, 
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ catchStart, 
        String type) {
        methodVisitor.visitTryCatchBlock(
            getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel) tryStart), 
            getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel)tryEnd), 
            getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel)catchStart), 
            type);
    }
    
    public void VisitLine(int line, quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel_ label) {
        Label start = getLabel((quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel)label);
        methodVisitor.visitLineNumber(line, start);
    }
    
    public Label getLabel(quorum.Libraries.Language.Compile.Translate.JavaBytecodeLabel label) {
        JavaBytecodeLabel get = (JavaBytecodeLabel) label.plugin_;
        return get.getLabel();
    }
}
