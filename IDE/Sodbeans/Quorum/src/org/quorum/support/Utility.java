/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.support;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
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
        
        f.SetWorkingDirectory(parent + "/");
        f.SetPath(name);
        return f;
    }
    
    /**
     * Returns a NetBeans FileObject from a quorum file.
     * 
     * @param file
     * @return 
     */
    public static FileObject toFileObject(quorum.Libraries.System.File$Interface file) {
        java.io.File f = new java.io.File(file.GetAbsolutePath());
        return FileUtil.toFileObject(f);
    }
    
    /**
     * Computes a relative path, val, compared to an absolute file, file.
     * @param file
     * @param val
     * @return 
     */
    public static java.io.File computeRelativePath(java.io.File file, String val) {
        java.io.File directory = new java.io.File(file.getAbsolutePath());
        Path targetFile = Paths.get(val);
        Iterator<Path> iterator = targetFile.iterator();
        while(iterator.hasNext()) {
            Path next = iterator.next();
            String s = next.toString();
            if(s.compareTo("..")==0) {
                directory = directory.getParentFile();
            } else {
                directory = new java.io.File(directory.getAbsolutePath() + "/" + s);
            }
        }
        return directory;
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
