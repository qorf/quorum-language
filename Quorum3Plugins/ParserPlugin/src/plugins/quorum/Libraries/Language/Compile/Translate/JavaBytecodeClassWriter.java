/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import quorum.Libraries.Language.Errors.InputOutputError;
import quorum.Libraries.System.File_;
        
/**
 *
 * @author stefika
 */
public class JavaBytecodeClassWriter implements Opcodes{
    public java.lang.Object me_ = null;
    private ClassWriter classWriter;
    
    public JavaBytecodeClassWriter() {
        classWriter = new QuorumClassWriter(ClassWriter.COMPUTE_FRAMES);
    }
    
    public void VisitSource(String name) {
        classWriter.visitSource(name, null);
    }
    /**
     * In this method, the array value at the end contains all strings. This is
     * guaranteed by the Quorum compiler.
     * 
     * @param javaVersion
     * @param access
     * @param className
     * @param signature
     * @param superClassName
     * @param array 
     */
    public void Visit(int javaVersion, int access, String className, String signature, String superClassName, quorum.Libraries.Containers.Array_ array) {
        String[] values = new String[array.GetSize()];
        for(int i = 0; i < array.GetSize(); i++) {
            quorum.Libraries.Language.Types.Text_ text = (quorum.Libraries.Language.Types.Text_) array.Get(i);
            values[i] = text.Get_Libraries_Language_Types_Text__value_();
        }
        
        classWriter.visit(javaVersion, access, className, signature, superClassName, values);
    }
    
    /**
     *
     * @param access
     * @param actionName
     * @param description
     * @param generics
     * @param errors
     * @return
     */
    public quorum.Libraries.Language.Compile.Translate.JavaBytecodeMethodWriter_ 
        VisitMethod(int access, String actionName, String description, 
                String generics, quorum.Libraries.Containers.Array_ errors) {
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeMethodWriter methodWriter = new quorum.Libraries.Language.Compile.Translate.JavaBytecodeMethodWriter();
        String[] values = null;
        if(errors != null) {
            values = new String[errors.GetSize()];
            for(int i = 0; i < errors.GetSize(); i++) {
                quorum.Libraries.Language.Types.Text_ text = (quorum.Libraries.Language.Types.Text_) errors.Get(i);
                values[i] = text.Get_Libraries_Language_Types_Text__value_();
            }
        }
        MethodVisitor visitor = classWriter.visitMethod(access, actionName, description, generics, values);
        JavaBytecodeMethodWriter get = (JavaBytecodeMethodWriter) methodWriter.plugin_;
        get.setMethodVisitor(visitor);
        return methodWriter;
    }
        
    public quorum.Libraries.Language.Compile.Translate.JavaBytecodeFieldWriter_ VisitField(
            int opcode, String name, String description, String signature, quorum.Libraries.Language.Object_ object) {
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeFieldWriter fieldWriter = 
                new quorum.Libraries.Language.Compile.Translate.JavaBytecodeFieldWriter();
        
        FieldVisitor visitor = classWriter.visitField(opcode, name, description, signature, object);
        JavaBytecodeFieldWriter get = fieldWriter.plugin_;
        get.setFieldVisitor(visitor);
        return fieldWriter;
    }
    
    public void VisitEnd() {
        classWriter.visitEnd();
    }
    
    public void Write(File_ file) throws quorum.Libraries.Language.Errors.InputOutputError{
        String path = file.GetAbsolutePath();
        java.io.File javaFile = new java.io.File(path);
        byte[] bites = classWriter.toByteArray();
        try {
            FileOutputStream stream = new FileOutputStream(javaFile);
            stream.write(bites);
            stream.flush();
        } catch(IOException e) {
            InputOutputError io = new quorum.Libraries.Language.Errors.InputOutputError();
            io.SetErrorMessage("Could not write class to disk");
            throw io;
            
        }
    }
    
}
