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

/**
 * Java bytecode plugin for the "File" class.
 * 
 * TODO: How do we re-throw exceptions to be the Quorum type? For example,
 * an IllegalArgumentException has a different name in Quorum...
 * 
 * @author jeff
 */
public class File {
    public java.lang.Object ___$$$Calling___$$$___Object$$$___ = null;
    private QuorumFile inst = new QuorumFile();
    
    /* -- class methods -- */
    public double GetLastModifiedNative(String path) {
        try {
            return inst.GetLastModified(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public String GetDirectoryListingNative(String path) {
        return inst.GetDirectoryListing(path);
    }
    
    public int GetMode() {
        return inst.GetMode();
    }
    
    public boolean IsDirectory(String path) {
        return inst.IsDirectory(path);
    }
    
    public boolean IsHidden(String path) {
        return inst.IsHidden(path);
    }
    
    public boolean Exists(String path) {
        return inst.Exists(path);
    }
    
    public String GetName(String path) {
        return inst.GetName(path);
    }
    
    public String GetExtension(String path) {
        return inst.GetExtension(path);
    }
    
    public String GetParent(String path) {
        return inst.GetParent(path);
    }
    
    public String GetURI(String path) {
        return inst.GetURI(path);
    }
    
    public double GetFileSize(String path) {
        return inst.GetFileSize(path);
    }
    
    public double GetFreeSpace(String path) {
        return inst.GetFreeSpace(path);
    }
    
    public double GetTotalDiskSpace(String path) {
        return inst.GetTotalDiskSpace(path);
    }
    
    public boolean CreateDirectory(String path) {
        return inst.CreateDirectory(path);
    }
    
    public boolean Delete(String path) {
        return inst.Delete(path);
    }
    
    public boolean Move(String oldPath, String newPath) {
        return inst.Move(oldPath, newPath);
    }
    
    public String GetSystemNewline() {
        return System.getProperty("line.separator");
    }
    
    
    /* -- instance methods -- */
    public void OpenNative(String path, int mode, boolean append, boolean write) {
        try {
            inst.Open(path, mode, append, write);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean IsEndOfFile() {
        return inst.IsEOF();
    }
    
    public double GetPosition() {
        try {
            return inst.GetPosition();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public void SetPosition(double position) {
        try {
            inst.SetPosition((long)position);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String Read() {
        try {
            return inst.Read();
        } catch (EOFException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public String Read(int amount) {
        try {
            return inst.Read(amount);
        } catch (EOFException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public String ReadLine() {
        try {
            return inst.ReadLine();
        } catch (EOFException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void Write(String StringToWrite) {
        try {
            inst.Write(StringToWrite);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void WriteLine(String StringToWrite) {
        try {
            inst.WriteLine(StringToWrite);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Rewind() {
        try {
            inst.Rewind();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ForceWriteContents() {
        try {
            inst.ForceWriteContents();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean Close() {
        try {
            inst.Close();
        } catch (IOException ex) {
            return false;
        }
        
        return true;
    }
}
