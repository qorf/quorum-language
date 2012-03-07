/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author jeff
 */
public interface QuorumFileInterface {
    /* -- class methods -- */
    public boolean IsDirectory();
    
    public boolean IsFile();
    
    public boolean IsHidden();
    
    public boolean Exists();
    
    public String GetName();

    public String GetExtension();
    
    public String GetParent();
    
    public String GetURI();

    public long GetFileSize();
    
    /**
     * Returns the unix timestamp of the time the file was last modified.
     * Converted into a DateTime object on the quorum side.
     * @param path
     * @return 
     */
    public long GetLastModified() throws FileNotFoundException;
    
    public long GetFreeSpace();
    
    public long GetTotalSpace();
    
    public String GetDirectoryListing();
    
    public boolean CreateDirectory();
    
    public boolean Delete();
    
    public boolean Move(String newPath);
    
    public void SetPath(String path);
    
    public String GetPath();
    
    /* -- instance methods -- */
    
    /**
     * Opens a file.
     * 
     * @param path
     * @param mode - the mode to use. 1 - sequential read, 2 - sequential write, 3 - random access (read/write)
     * @param append - if mode is 2 (write), if true file is not erased if it already exists--it is appended to.
     * @return error code. 
     */
    public void Open(int mode, boolean append, boolean write) throws FileNotFoundException, IOException, IllegalArgumentException;
    
    /**
     * Close the file.
     * @return
     */
    public void Close() throws IOException;
    
    /**
     * Get position of read/write pointer. Only works for random access files.
     * 
     * @return 
     */
    public long GetPosition() throws IOException;
    
    /**
     * Set position of read/write pointer. Only works in random access mode.
     * @return 
     */
    public void SetPosition(long position) throws IOException, IllegalArgumentException;
    
    /**
     * Read entire file from current position and return contents.
     * @return 
     */
    public String Read() throws EOFException, IOException;
    
    /**
     * Read `amount' number of bytes from file
     * @param amount
     * @return 
     */
    public String Read(int amount) throws EOFException, IOException, IllegalArgumentException;
    
    /**
     * Reads the next line in the file.
     * @return the line
     */
    public String ReadLine() throws IOException;
    
    /**
     * Write text to the file.
     * @param text
     * @return
     */
    public void Write(String text) throws IOException;
    
    /**
     * Write a line of text to the file, ended with system-dependent newline.
     * @param text
     * @return
     */
    public void WriteLine(String text) throws IOException, IllegalArgumentException;
    
    /**
     * Rewind the read/write pointer. Only works for random access files.
     * @return 
     */
    public void Rewind() throws IOException;
    
    /**
     * Same as `flush' in standard input/output library. 
     */
    public void ForceWriteContents() throws IOException;
    
    /**
     * Returns whether or not we reached end of file. Only valid for read/random mode.
     * @return 
     */
    public boolean IsEOF();
    /**
     * Returns the mode the file is open for.
     * @return 
     */
    public int GetMode();
}
