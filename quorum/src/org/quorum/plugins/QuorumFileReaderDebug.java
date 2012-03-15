/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Java implementation for the "FileReader" Quorum class. This implementation
 * supports going backwards. To accomplish this, the file *must* be opened in
 * random access mode; buffered reading can't be supported, as we must be
 * able to "back up."
 * 
 * @author jeff
 */
public class QuorumFileReaderDebug extends QuorumFileReader {
    /**
     * Represents the current file we have open (if any).
     */
    private FileDescriptor descriptor = null;
    
    /**
     * Our previous file descriptors that we had open.
     */
    private Stack<FileDescriptor> descriptors = new Stack<FileDescriptor>();
    /**
     * A FileDescriptor represents the current state of a file,
     * such as the last position read from, its path, etc.
     */
    private class FileDescriptor {
        public File file = null;
        public String path = "";
        public boolean atEOF = false;
        public Stack<Long> pointers = new Stack<Long>();
        public long pointer = 0;
        public long size = 0;
        public RandomAccessFile randomAccess = null;
    }
    
    /**
     * Open the file given by the FileDescriptor.
     * 
     * @param fd 
     */
    private void openDescriptor(FileDescriptor fd) throws FileNotFoundException, IOException {
        fd.randomAccess = new RandomAccessFile(fd.file, "r"); // read-only mode.
        descriptor = fd; // this is now the open file.
    }
    
    /**
     * Change the current pointer and store the previous one on the stack.
     * @param ptr 
     */
    private void updatePosition(long ptr) {
        if (descriptor == null)
            return;
        
        descriptor.pointers.add(descriptor.pointer);
        descriptor.pointer = ptr;
    }
    
    /**
     * Get the last position from the pointer stack. If the stack is empty,
     * return 0.
     * @return 
     */
    private long getLastPosition() {
        if (descriptor == null || descriptor.pointers.isEmpty())
            return 0;
        
        long pos = descriptor.pointers.pop();
        return pos;
    }
    
    @Override
    public void OpenForReadNative(String path) throws FileNotFoundException {
        FileDescriptor fd = new FileDescriptor();
        fd.file = new File(path);
        fd.path = path;
        fd.size = fd.file.length();
        
        try {
            openDescriptor(fd);
        } catch (IOException ex) {
            Logger.getLogger(QuorumFileReaderDebug.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Undo an open operation.
     */
    public void unOpenForReadNative() throws IOException {
        if (descriptor == null)
            return; // nothing is open
        
        descriptor.randomAccess.close();
        descriptor = null; // no file is open now.
    }
    
    @Override
    public void Close() throws IOException {
        if (descriptor == null)
            return; // no file is open.
        
        descriptor.randomAccess.close();
        descriptors.add(descriptor); // remember it.
        descriptor = null; // no file is open now.
    }

    public void unClose() throws FileNotFoundException, IOException {
        // Re-open the descriptor on the top of the stack.
        if (descriptors.empty())
            return;
        
        FileDescriptor fd = descriptors.pop();
        openDescriptor(fd);
    }
    
    @Override
    public String ReadNative() throws IOException {
        if (descriptor.atEOF)
            throw new EOFException();
        
        long currentPos = descriptor.randomAccess.getFilePointer();

        // Gather all our remaining data.
        StringBuilder stringBuff = new StringBuilder();
        long remainingBytes = descriptor.size - currentPos;
        long totalReadBytes = 0;
        int numRead = 0;
        byte[] byteBuff = new byte[1024];

        do {
            numRead = descriptor.randomAccess.read(byteBuff, 0, 1024);

            if (numRead > 0) {
                totalReadBytes += numRead;
                stringBuff.append(new String(byteBuff), 0, numRead);
            }
        } while (totalReadBytes < remainingBytes && numRead > 0);
        
        // Make this our new pointer.
        updatePosition(descriptor.randomAccess.getFilePointer());
        
        if (totalReadBytes > 0) {
            // We will have reached the end of the file here. Don't throw an exception this time.
            descriptor.atEOF = true;
            return stringBuff.toString();
        }
        else {
            throw new IOException("Read() could not read any data from the file.");
        }   
    }
    
    public void unReadNative() throws IOException {
        if (descriptor == null)
            return;
        
        // Go back to our last position.
        long pos = getLastPosition();
        descriptor.randomAccess.seek(pos);
        descriptor.pointer = pos;
    }
    
    @Override
    public String ReadNative(int numberOfBytes) throws EOFException, IOException {
        if (descriptor.atEOF)
            throw new EOFException();
                
        // Gather all our remaining data.
        StringBuilder stringBuff = new StringBuilder();
        long totalReadBytes = 0;
        int numRead = 0;
        byte[] byteBuff = new byte[numberOfBytes];

        numRead = descriptor.randomAccess.read(byteBuff, 0, numberOfBytes);

        if (numRead > 0) {
            totalReadBytes += numRead;
            stringBuff.append(new String(byteBuff));
        }
        else if (numRead == 0 || numRead == -1) {
            // We are at the end of the file. Let the uesr know this time.
            descriptor.atEOF = true;
            throw new EOFException();
        }
        
        // Make this our new pointer.
        updatePosition(descriptor.randomAccess.getFilePointer());
        
        if (totalReadBytes > 0) {
            if (numRead >= descriptor.size) {
                // We are at the end of the file. Don't complain this time.
                descriptor.atEOF = true;
            }
            return stringBuff.toString();
        }
        else {
            throw new IOException("Read() could not read any data from the file.");
        }
    }
    
    @Override
    public String ReadLineNative() throws EOFException, IOException {
       String line = descriptor.randomAccess.readLine();

       if (line == null) {
            if (descriptor.atEOF) {
                throw new EOFException();
            }
            descriptor.atEOF = true;
            line = "";
        }
       
        // Make this our new pointer.
        updatePosition(descriptor.randomAccess.getFilePointer());
        
        return line;
    }
    
    /**
     * We can simply call "unReadNative" to undo this operation.
     * 
     * @throws IOException 
     */
    public void unReadLineNative() throws IOException {
        this.unReadNative();
    }
}
