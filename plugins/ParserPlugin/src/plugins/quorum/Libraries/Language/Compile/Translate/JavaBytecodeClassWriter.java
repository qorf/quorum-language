/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import java.io.FileOutputStream;
import java.io.IOException;
import org.objectweb.asm.ClassWriter;
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
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
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
    
    public void VisitEnd() {
        classWriter.visitEnd();
    }
    
    public void Write(File$Interface file) throws quorum.Libraries.Language.Errors.InputOutputError{
        String path = file.GetAbsolutePath();
        System.out.println("sdlfksldfjsdf");
        java.io.File javaFile = new java.io.File(path);
        byte[] bites = classWriter.toByteArray();
        try {
            FileOutputStream stream = new FileOutputStream(javaFile);
            System.out.println("sdlfksldfjsdf");
            stream.write(bites);
            stream.flush();
        } catch(IOException e) {
            System.out.println("sdlfksldfjsdf");
            InputOutputError io = new quorum.Libraries.Language.Errors.InputOutputError();
            io.SetErrorMessage("Could not write class to disk");
            throw io;
            
        }
    }
    
}
