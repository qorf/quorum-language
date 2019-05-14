/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile.Translate;

import org.objectweb.asm.FieldVisitor;

/**
 *
 * @author stefika
 */
public class JavaBytecodeFieldWriter {
    public java.lang.Object me_ = null;
    private FieldVisitor visitor;

    /**
     * @return the visitor
     */
    public FieldVisitor getFieldVisitor() {
        return visitor;
    }

    /**
     * @param visitor the visitor to set
     */
    public void setFieldVisitor(FieldVisitor visitor) {
        this.visitor = visitor;
    }
    
    public void VisitEnd() {
        visitor.visitEnd();
    }
}
