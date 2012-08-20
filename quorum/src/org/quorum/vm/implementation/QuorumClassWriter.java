/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * This is an extension of ASM's default ClassWriter, which modifies the
 * getCommonSuperClass() method for quorum-specific classes.
 * 
 * @author jeff
 */
public class QuorumClassWriter extends ClassWriter {
    public QuorumClassWriter(final int flags) {
        super(flags);
    }

    public QuorumClassWriter(final ClassReader classReader, final int flags) {
        super(classReader, flags);
    }

    /**
     * If type1 and type2 correspond to quorum objects (e.g. start with quorum/),
     * the common type returned will be "java/lang/Object", as Quorum's inheritance
     * system extends only "java/lang/Object", besides exception classes, which
     * inherit from "java/lang/Throwable". Returning "java/lang/Object"
     * *SHOULD* be O.K., but this method may require more testing.
     * 
     * If the parameters do not correspond to quorum classes, the default
     * ClassWriter implementation is used.
     * 
     * @param type1
     * @param type2
     * @return 
     */
    @Override
    protected String getCommonSuperClass(String type1, String type2) {
        if (type1.startsWith("quorum/") && type2.startsWith("quorum/")) {
            return "java/lang/Object";
        } else {
            return super.getCommonSuperClass(type1, type2);
        }
    }
}
