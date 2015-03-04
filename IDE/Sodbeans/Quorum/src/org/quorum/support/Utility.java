/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.support;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 * This is a helper class for converting between Java and Quorum specific 
 * file IO.
 * 
 * @author Andreas Stefik
 */
public class Utility {
    
    /**
     * Converts a java file io type to a Quorum specific file.
     * @param file
     * @return 
     */
    public static quorum.Libraries.System.File toQuorumFile(java.io.File file) {
        if(file == null) {
            return null;
        }
        quorum.Libraries.System.File f = new quorum.Libraries.System.File();
        String parent = file.getParent();
        String name = file.getName();
        
        f.SetWorkingDirectory(parent);
        f.SetPath(name);
        return f;
    }
    
    /**
     * Converts a NetBeans specific file type to a Quorum type.
     * 
     * @param file
     * @return 
     */
    public static quorum.Libraries.System.File toQuorumFile(FileObject file) {
        return toQuorumFile(FileUtil.toFile(file));
    }
    
    /**
     * Converts a Quorum specific file type written in Quorum to Java file io.
     * @param file
     * @return 
     */
    public static java.io.File toQuorumFile(quorum.Libraries.System.File$Interface file) {
        if(file == null) {
            return null;
        }
        String path = file.GetAbsolutePath();
        java.io.File io = new java.io.File(path);
        return io;
    }
}
