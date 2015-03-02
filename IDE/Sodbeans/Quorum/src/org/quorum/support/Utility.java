/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.support;

/**
 *
 * @author stefika
 */
public class Utility {
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
}
