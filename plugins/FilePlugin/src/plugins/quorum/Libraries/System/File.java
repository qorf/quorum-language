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
    public java.lang.Object $me = null;
    private QuorumFile inst = new QuorumFile();
    
    /* -- class methods -- */
    public double GetLastModifiedNative() {
        try {
            return inst.GetLastModified();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public String GetDirectoryListingNative() {
        return inst.GetDirectoryListing();
    }
    
    public int GetMode() {
        return inst.GetMode();
    }
    
    public boolean IsDirectory() {
        return inst.IsDirectory();
    }
    
    public boolean IsHidden() {
        return inst.IsHidden();
    }
    
    public boolean Exists() {
        return inst.Exists();
    }
    
    public String GetName() {
        return inst.GetName();
    }
    
    public String GetExtension() {
        return inst.GetExtension();
    }
    
    public String GetParent() {
        return inst.GetParent();
    }
    
    public String GetURI() {
        return inst.GetURI();
    }
    
    public double GetFileSize() {
        return inst.GetFileSize();
    }
    
    public double GetFreeSpace() {
        return inst.GetFreeSpace();
    }
    
    public double GetTotalSpace() {
        return inst.GetTotalSpace();
    }
    
    public boolean CreateDirectory() {
        return inst.CreateDirectory();
    }
    
    public boolean Delete() {
        return inst.Delete();
    }
    
    public boolean Move(String newPath) {
        return inst.Move(newPath);
    }
    
    public String GetSystemNewline() {
        return System.getProperty("line.separator");
    }
    
    public void SetPath(String path) {
        inst.SetPath(path);
    }
    
    public String GetPath() {
        return inst.GetPath();
    }
    
    /* -- instance methods -- */
    public void OpenNative(int mode, boolean append, boolean write) {
        try {
            inst.Open(mode, append, write);
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
