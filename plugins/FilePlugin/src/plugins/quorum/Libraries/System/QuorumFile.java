/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The underlying implementation of the quorum File class.
 * 
 * @author jeff
 */
public class QuorumFile implements QuorumFileInterface {
    // Mode parameter values--must be consistent with those in File.quorum.
    public static final int INVALID_MODE = 0;
    public static final int READ_MODE = 1;
    public static final int WRITE_MODE = 2;
    public static final int RANDOM_ACCESS_MODE = 3;
    
    /* -- instance variables -- */
    protected int openMode = INVALID_MODE;
    protected boolean randomAccessWritable = false;
    protected File openFile;
    protected File currentFile;
    protected long openFileLength = 0;
    protected boolean eof = false;
    protected BufferedReader reader;
    protected BufferedWriter writer;
    protected RandomAccessFile randomAccess; // for random access mode only
    
    /* -- class methods -- */
    public void SetPath(String path){
        currentFile = new File(path);
    }
    
    public String GetPath(){
        return currentFile.getPath();
    }
    
    public boolean IsDirectory() {
        return currentFile.isDirectory();
    }
    
    public boolean IsFile() {
        return currentFile.isFile();
    }
    
    public boolean IsHidden() {
        return currentFile.isHidden();
    }
    
    public boolean Exists() {
        return currentFile.exists();
    }
    
    public String GetName() {
        return currentFile.getName();
    }
    
    public String GetExtension() {
        
        // The path may not contain an extension (or may not be a file)
        if (!currentFile.getPath().contains(".") || !currentFile.isFile())
            return "";
        
        String name = currentFile.getName();
        int pos = name.lastIndexOf('.');
        return name.substring(pos+1);
    }
    
    public String GetParent() {
        return currentFile.getParent();
    }
    
    public String GetURI() {
        return currentFile.toURI().toString();
    }

    public long GetFileSize() {
        return currentFile.length();
    }
    
    /**
     * Returns the unix timestamp of the time the file was last modified.
     * Converted into a DateTime object on the quorum side.
     * @param path
     * @return 
     */
    public long GetLastModified() throws FileNotFoundException {
        if (!currentFile.exists())
            throw new FileNotFoundException();
        
        return currentFile.lastModified();
    }
    
    public long GetFreeSpace() {
        return currentFile.getFreeSpace();
    }
    
    public long GetTotalSpace() {
        return currentFile.getTotalSpace();
    }
    
    public String GetDirectoryListing() {
        String listing = "";
        String newline = System.getProperty("line.separator");
        String[] list = currentFile.list();
        
        for (int x = 0; x < list.length; x++) {
            listing = listing + list[x] + newline;
        }
        
        return listing;
    }
    
    public boolean CreateDirectory() {
        return currentFile.mkdir();
    }
    
    public boolean Delete() {
        return currentFile.delete();
    }
    
    public boolean Move(String newPath) {
        return currentFile.renameTo(new File(newPath));
    }
    
    /* -- instance methods -- */
    
    /**
     * Opens a file.
     * 
     * @param path
     * @param mode - the mode to use. 1 - sequential read, 2 - sequential write, 3 - random access (read/write)
     * @param append - if mode is 2 (write), if true file is not erased if it already exists--it is appended to.
     * @return error code. 
     */
    public void Open(int mode, boolean append, boolean write) throws FileNotFoundException, IOException, IllegalArgumentException {
        if (currentFile.getPath() == null)
            throw new IllegalArgumentException("The path specified is not valid.");
        else if (currentFile.getPath().trim().isEmpty())
            throw new IllegalArgumentException("The path specified is not valid.");
        
        // Do we have a file open already? If so, bail out.
        if (openMode != INVALID_MODE)
            throw new IOException("A file is already open for this instance of File.");
        
        // Which mode is it?
        if (mode == READ_MODE) {
            // Open file for reading.
            openFile = currentFile;
            reader = new BufferedReader(new FileReader(openFile));
            openMode = READ_MODE;
            openFileLength = openFile.length();
        }
        else if (mode == WRITE_MODE) {
            // Open file for writing.
            openFile = currentFile;
            writer = new BufferedWriter(new FileWriter(openFile, append));
            openMode = WRITE_MODE;
            
            if (append)
                openFileLength = openFile.length();
            else
                openFileLength = 0;
        }
        else if (mode == RANDOM_ACCESS_MODE) {
            // Open file for random access
            openFile = currentFile;
            randomAccessWritable = write;
            
            if (write)
                randomAccess = new RandomAccessFile(openFile, "rw");
            else
                randomAccess = new RandomAccessFile(openFile, "r");
            openMode = RANDOM_ACCESS_MODE;
            openFileLength = openFile.length();
        }
    }
    
    /**
     * Close the file.
     * @return
     */
    public void Close() throws IOException {
        boolean success = false;
        if (openMode == READ_MODE) {
            reader.close();
            success = true;
        }
        else if (openMode == WRITE_MODE) {
            writer.close();
            success = true;
        }
        else if (openMode == RANDOM_ACCESS_MODE) {
            randomAccess.close();
            success = true;
        }
        
        if (!success) {
            throw new IOException("An error occurred while attempting to close the file.");
        }
        else
        {
            openMode = INVALID_MODE;
            openFileLength = 0;
            randomAccessWritable = false;
            eof = false;
            reader = null;
            writer = null;
            randomAccess = null;
        }
    }
    
    /**
     * Get position of read/write pointer. Only works for random access files.
     * 
     * @return 
     */
    public long GetPosition() throws IOException {
        if (openMode != RANDOM_ACCESS_MODE)
            throw new IOException("GetPosition() requires a file open in random access mode.");

        return randomAccess.getFilePointer();
    }
    
    /**
     * Set position of read/write pointer. Only works in random access mode.
     * @return 
     */
    public void SetPosition(long position) throws IOException, IllegalArgumentException {
        if (openMode != RANDOM_ACCESS_MODE)
            throw new IOException("SetPosition() requires a file open in random access mode.");
        else if (position < 0)
            throw new IllegalArgumentException("The given position must be greater than or equal to zero.");
        
        randomAccess.seek(position);
        if (position < openFileLength)
            eof = false;
    }
    
    /**
     * Read entire file from current position and return contents.
     * @return 
     */
    public String Read() throws EOFException, IOException {
        if (eof)
            throw new EOFException();
        
        if (openMode == READ_MODE) {
            // Read entire file contents.
            StringBuilder stringBuff = new StringBuilder();
            char[] buffer = new char[1024];
            int numRead = 0;
            long totalRead = 0;

            do {
                numRead = reader.read(buffer, 0, 1024);

                if (numRead > 0) {
                    stringBuff.append(buffer, 0, numRead);
                    totalRead += numRead;
                }
            } while (totalRead < openFileLength && numRead > 0);

            if (totalRead > 0)
            {
                // We will have reached the end of the file here. Don't throw an exception this time.
                eof = true;
                return stringBuff.toString();
            }
            else
            {
                throw new IOException("Read() could not read any data from the file.");
            }
        }
        else if (openMode == RANDOM_ACCESS_MODE) {
            long currentPos = randomAccess.getFilePointer();
            
            // Gather all our remaining data.
            StringBuilder stringBuff = new StringBuilder();
            long remainingBytes = openFileLength - currentPos;
            long totalReadBytes = 0;
            int numRead = 0;
            byte[] byteBuff = new byte[1024];
            
            do {
                numRead = randomAccess.read(byteBuff, 0, 1024);

                if (numRead > 0) {
                    totalReadBytes += numRead;
                    stringBuff.append(new String(byteBuff), 0, numRead);
                }
            } while (totalReadBytes < remainingBytes && numRead > 0);
            
            if (totalReadBytes > 0) {
                // We will have reached the end of the file here. Don't throw an exception this time.
                eof = true;
                return stringBuff.toString();
            }
            else {
                throw new IOException("Read() could not read any data from the file.");
            }
        }
        
        // Not in a valid mode, or no open file.
        throw new IOException("Read() requires a file open in read or random access mode.");
    }
    
    /**
     * Read `amount' number of bytes from file
     * @param amount
     * @return 
     */
    public String Read(int amount) throws EOFException, IOException, IllegalArgumentException {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount to read must be greater than zero.");
                
        if (eof)
            throw new EOFException();
                
        if (openMode == READ_MODE) {
            // Read entire file contents.
            char[] buffer = new char[amount];
            int numRead = 0;
        
            numRead = reader.read(buffer, 0, amount);

            if (numRead == 0 || numRead == -1) {
                eof = true;
                throw new EOFException();
            }

            if (numRead > 0)
            {
                if (numRead >= openFileLength) {
                    // Reached end of file. Don't raise an exception this time.
                    eof = true;
                }
                return String.valueOf(buffer);
            }
            else
            {
                throw new IOException("Read() could not read any data from the file.");
            }
        }
        else if (openMode == RANDOM_ACCESS_MODE) {            
            // Gather all our remaining data.
            StringBuilder stringBuff = new StringBuilder();
            long totalReadBytes = 0;
            int numRead = 0;
            byte[] byteBuff = new byte[amount];
            
            numRead = randomAccess.read(byteBuff, 0, amount);

            if (numRead > 0) {
                totalReadBytes += numRead;
                stringBuff.append(new String(byteBuff));
            }
            else if (numRead == 0 || numRead == -1) {
                // We are at the end of the file. Let the uesr know this time.
                eof = true;
                throw new EOFException();
            }
            
            if (totalReadBytes > 0) {
                if (numRead >= openFileLength) {
                    // We are at the end of the file. Don't complain this time.
                    eof = true;
                }
                return stringBuff.toString();
            }
            else {
                throw new IOException("Read() could not read any data from the file.");
            }
        }
        
        // Not in a valid mode, or no open file.
        throw new IOException("Read() requires a file open in read or random access mode.");
    }
    
    /**
     * Reads the next line in the file.
     * @return the line
     */
    public String ReadLine() throws IOException {
        if (openMode == READ_MODE) {
            String line = reader.readLine();

            if (line == null) {
                if (eof) {
                    throw new EOFException();
                }
                eof = true;
                line = "";
            }

            return line;
        }
        else if (openMode == RANDOM_ACCESS_MODE) {
            String line = randomAccess.readLine();

           if (line == null) {
                if (eof) {
                    throw new EOFException();
                }
                eof = true;
                line = "";
            }

            return line;
        }
        
        // Not in a valid mode, or no open file.
        throw new IOException("ReadLine() requires a file open in read or random access mode.");
    }
    
    /**
     * Write text to the file.
     * @param text
     * @return
     */
    public void Write(String text) throws IOException {
        // Don't write empty strings or null strings.
        if (text == null)
            throw new NullPointerException("Text value must not be Nothing.");
        else if (text.isEmpty())
            throw new NullPointerException("Text value must be non-empty.");
            
        if (openMode == WRITE_MODE) {
            writer.write(text);
            return;
        }
        else if (openMode == RANDOM_ACCESS_MODE) {
            if (!randomAccessWritable)
                throw new IOException("Write() called when random access writing is disabled.");
            
            byte[] bytes = text.getBytes();
            randomAccess.write(bytes);
            return;
        }

        throw new IOException("Write() requires a file open in write or wrtiable random access mode.");
    }
    
    /**
     * Write a line of text to the file, ended with system-dependent newline.
     * @param text
     * @return
     */
    public void WriteLine(String text) throws IOException, IllegalArgumentException {
        // Don't write null strings.
        if (text == null)
            throw new IllegalArgumentException("Text value must not be Nothing.");
        
        else if (openMode == WRITE_MODE) {
            writer.write(text);
            writer.newLine();
            return;
        }
        else if (openMode == RANDOM_ACCESS_MODE) {
            if (!randomAccessWritable) {
                throw new IOException("WriteLine() called when random access writing is disabled.");
            }
            
            byte[] bytes = text.getBytes();
            byte[] newline = System.getProperty("line.separator").getBytes();
            randomAccess.write(bytes);
            randomAccess.write(newline);
            return;
        }
        
        throw new IOException("WriteLine() requires a file open in write or wrtiable random access mode.");
    }
    
    /**
     * Rewind the read/write pointer. Only works for random access files.
     * @return 
     */
    public void Rewind() throws IOException {
        if (openMode != RANDOM_ACCESS_MODE)
            throw new IOException("Rewind() requires a file open in random access mode.");
        
        randomAccess.seek(0);
        eof = false; // we aren't at the end of the file.
    }
    
    /**
     * Same as `flush' in standard input/output library. 
     */
    public void ForceWriteContents() throws IOException {
        if (openMode != WRITE_MODE)
            throw new IOException("ForceWriteContents() requires a file open in write mode.");
        
        writer.flush();
    }
    
    /**
     * Returns whether or not we reached end of file. Only valid for read/random mode.
     * @return 
     */
    public boolean IsEOF() {
        return eof;
    }
    
    /**
     * Returns the mode the file is open for.
     * @return 
     */
    public int GetMode() {
        return openMode;
    }
}
