/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Language.Errors.*;

/**
 * Java bytecode plugin for the "File" class. This is merely a wrapper for the
 * QuorumFilePlugin class.
 * 
 * @author Jeff Wilson
 */
public class File {
    public java.lang.Object $me = null;
    private QuorumFile inst = new QuorumFile();
    
    public double GetLastModifiedNative() {
        return inst.GetLastModifiedNative();
    }
    
    public java.lang.String GetDirectoryListingNative() {
        return inst.GetDirectoryListingNative();
    }
    public java.lang.String GetParentDirectoryNative() {
        return inst.GetParentDirectoryNative();
    }
    
    public String GetPathNative() {
        return inst.GetPathNative();
    }
    
    /**
     * Get the absolute path of this File object.
     * @return 
     */
    public String GetAbsolutePathNative() {
        return inst.getAbsolutePathNative();
    }
    
    public void SetPathNative(String newPath) {
        inst.SetPathNative(newPath);
    }
    
    public String GetWorkingDirectoryNative() {
        return inst.GetWorkingDirectoryNative();
    }
    
    /**
     * Set the user's current working directory.
     * @param path
     * @return false if the path does not exist or does not refer to a directory.
     */
    public boolean SetWorkingDirectoryNative(String path) {
        return inst.SetWorkingDirectoryNative(path);
    }
    
    public String GetSystemNewline() {
        return inst.GetSystemNewline();
    }
    
    public boolean Exists() {
        return inst.Exists();
    }
    
    public boolean IsFile() {
        return inst.IsFile();
    }
    
    public boolean IsDirectory() {
        return inst.IsDirectory();
    }
    
    public boolean IsHidden() {
        return inst.IsHidden();
    }
    
    public String GetFileName() {
        return inst.GetFileName();
    }
    
    public String GetFileExtension() {
        return inst.GetFileExtension();
    }
    
    public double GetFreeDiskSpace() {
        return inst.GetFreeDiskSpace();
    }
    
    public double GetTotalDiskSpace() {
        return inst.GetTotalDiskSpace();
    }
    
    public double GetFileSize() {
        return inst.GetFileSize();
    }
    
    public boolean Delete() {
        return inst.Delete();
    }
    
    public boolean CreateDirectory() {
        return inst.CreateDirectory();
    }
    
    public boolean Move(String newPath) {
        return inst.Move(newPath);
    }
}
