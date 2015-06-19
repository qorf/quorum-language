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
import quorum.Libraries.System.File$Interface;
        
/**
 *
 * @author stefika
 */
public class JavaBytecodeClassWriter implements Opcodes{
    public java.lang.Object $me = null;
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
    public void Visit(int javaVersion, int access, String className, String signature, String superClassName, quorum.Libraries.Containers.Array$Interface array) {
        String[] values = new String[array.GetSize()];
        for(int i = 0; i < array.GetSize(); i++) {
            quorum.Libraries.Language.Types.Text$Interface text = (quorum.Libraries.Language.Types.Text$Interface) array.Get(i);
            values[i] = text.Get$Libraries$Language$Types$Text$value();
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
    public quorum.Libraries.Language.Compile.Translate.JavaBytecodeMethodWriter$Interface 
        VisitMethod(int access, String actionName, String description, 
                String generics, quorum.Libraries.Containers.Array$Interface errors) {
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeMethodWriter methodWriter = new quorum.Libraries.Language.Compile.Translate.JavaBytecodeMethodWriter();
        String[] values = null;
        if(errors != null) {
            values = new String[errors.GetSize()];
            for(int i = 0; i < errors.GetSize(); i++) {
                quorum.Libraries.Language.Types.Text$Interface text = (quorum.Libraries.Language.Types.Text$Interface) errors.Get(i);
                values[i] = text.Get$Libraries$Language$Types$Text$value();
            }
        }
        MethodVisitor visitor = classWriter.visitMethod(access, actionName, description, generics, values);
        try {
            //get the plugin from the passed class
            Field field = methodWriter.getClass().getField("<plugin>");
            JavaBytecodeMethodWriter get = (JavaBytecodeMethodWriter) field.get(methodWriter);
            get.setMethodVisitor(visitor);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(JavaBytecodeClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return methodWriter;
    }
        
    public quorum.Libraries.Language.Compile.Translate.JavaBytecodeFieldWriter$Interface VisitField(
            int opcode, String name, String description, String signature, quorum.Libraries.Language.Object$Interface object) {
        quorum.Libraries.Language.Compile.Translate.JavaBytecodeFieldWriter fieldWriter = 
                new quorum.Libraries.Language.Compile.Translate.JavaBytecodeFieldWriter();
        
        FieldVisitor visitor = classWriter.visitField(opcode, name, description, signature, object);
        try {
            //get the plugin from the passed class
            Field field = fieldWriter.getClass().getField("<plugin>");
            JavaBytecodeFieldWriter get = (JavaBytecodeFieldWriter) field.get(fieldWriter);
            get.setFieldVisitor(visitor);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(JavaBytecodeClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fieldWriter;
    }
    
    public void VisitEnd() {
        classWriter.visitEnd();
    }
    
    public void Write(File$Interface file) throws quorum.Libraries.Language.Errors.InputOutputError{
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
