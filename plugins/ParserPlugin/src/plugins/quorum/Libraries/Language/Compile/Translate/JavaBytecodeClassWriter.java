/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

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
    
}
