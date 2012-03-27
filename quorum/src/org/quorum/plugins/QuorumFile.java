/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.io.File;

/**
 * A plugin for the "File" class in Quorum, located under Libraries.System.File.
 * 
 * Many methods here are undocumented -- please see the File.quorum class for
 * specifics on each method.
 * 
 * @author Jeff Wilson
 */
public class QuorumFile {
    /*
     * The file (or directory) path this object represents. By default, this is
     * the application's working directory.
     */
    protected String path = null;

    /*
     * The "File" object we are representing here on the Java side.
     */
    protected File file = null;
    
    /**
     * Set our file to the current directory.
     */
    public QuorumFile() {
        file = new File(System.getProperty("user.dir"));
        path = file.getPath();
    }
    
    /*
     * This action gets the "last modified" date/time from the system in UNIX
     * timestamp format.
     */
    public long GetLastModifiedNative() {        
        return file.lastModified();
    }

    /*
     * This action gets the directory listing from the system delimited by
     * a newline.
     */
    public String GetDirectoryListingNative() {
        String listing = "";
        String newline = System.getProperty("line.separator");
        String[] list = file.list();
        
        // Add our current path onto the end of each file name.
        String prefix = file.getAbsolutePath();
        if (!path.endsWith(File.separator))
            prefix = prefix + File.separator;
        
        for (int x = 0; x < list.length; x++) {
            listing = listing + prefix + list[x] + newline;
        }
        
        return listing;
    }


    /*
     * Get the full-path name of the parent directory as a string.
     */
    public String GetParentDirectoryNative() {
        String parentPath = file.getParent();
        
        if (parentPath == null) {
            return "";
        }
        
        return parentPath;
    }
    

    /*
     * Get the path.
     */
    public String GetPathNative() {
        return this.path;
    }
    
    /*
     * Set the path on the native side.
     */
    public void SetPathNative(String path) {
        this.path = path;
        this.file = new File(path);
    }
    
    /*
     * Get the user's current working directory.
     */
    public String GetWorkingDirectoryNative() {
        return System.getProperty("user.dir");
    }
    
    /*
     * This action gets the system's newline character. This varies from
     * platform to platform and is difficult to detect manually. On some
     * systems, it may simply be "\n" (UNIX), and on others, it may be
     * "\r\n" (Windows).
     */
    public String GetSystemNewline() {
        return System.getProperty("line.separator");
    }

    public boolean Exists() {
        return file.exists();
    }
    
    public boolean IsFile() {
        return file.isFile();
    }
    
    public boolean IsDirectory() {
        return file.isDirectory();
    }

    public boolean IsHidden() {
        return file.isHidden();
    }

    public String GetFileName() {
        return file.getName();
    }

    public String GetFileExtension() {
        // The path may not contain an extension (or may not be a file)
        String name = file.getName();
        if (!name.contains("."))
            return "";
        
        int pos = name.lastIndexOf('.');
        return name.substring(pos+1);    
    }

    public double GetFreeDiskSpace() {
        return file.getFreeSpace();
    }

    public double GetTotalDiskSpace() {
        return file.getTotalSpace();
    }

    public double GetFileSize() {
        if (file.isFile()) {
            return file.length();
        }
        
        return 0;
    }

    public boolean Delete() {
        return file.delete();
    }

    public boolean CreateDirectory() {
        return file.mkdir();
    }

    public boolean Move(String newPath) {
        File newFile = new File(newPath);
        
        if (file.renameTo(newFile)) {
            this.path = newPath;
            this.file = new File(this.path);

            return true;
        }
        
        return false;
    }
}
