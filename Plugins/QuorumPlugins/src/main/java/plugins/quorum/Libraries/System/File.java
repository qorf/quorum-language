/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;

/**
 * Java bytecode plugin for the "File" class. This is merely a wrapper for the
 * QuorumFilePlugin class.
 * 
 * @author Jeff Wilson
 */
public class File {
    public java.lang.Object me_ = null;
    private QuorumFile inst;
    static boolean isAndroid = false;
    
    static
    {
        isAndroid = IsAndroid();
    }
    
    public static boolean IsAndroid() {
        String os = System.getProperty("os.name");
        return os.contains("Linux") && System.getProperty("java.runtime.name").contains("Android Runtime");
    }
    
    public File() {
        if(!isAndroid) {
            inst = new QuorumFile();
        } else {
            inst = new QuorumFileAndroid();
        }
    }
    
    
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
    
    public boolean CreateDirectories() {
        return inst.CreateDirectories();
    }
    
    public boolean Move(String newPath) {
        return inst.Move(newPath);
    }
    
    public void SetExecutable(boolean exec) {
        inst.SetExecutable(exec);
    }
    
    public boolean Copy(quorum.Libraries.System.File_ file) {
        String pathOriginal = inst.getAbsolutePathNative();
        java.io.File original = new java.io.File(pathOriginal);
        
        String pathCopy = file.GetAbsolutePath();
        java.io.File copy = new java.io.File(pathCopy);
        try {
            Files.copy(original.toPath(), copy.toPath(), REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public String GetWorkingDirectoryFromPath(String path) {
        return inst.GetWorkingDirectoryFromPath(path);
    }
    
    public String GetPathFromPath(String path) {
        return inst.GetPathFromPath(path);
    }
}
