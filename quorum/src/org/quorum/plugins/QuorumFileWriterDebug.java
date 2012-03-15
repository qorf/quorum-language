/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
public class QuorumFileWriterDebug extends QuorumFileWriter {
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
        public Stack<Long> pointers = new Stack<Long>();
        public Stack<String> buffers = new Stack<String>();
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
        fd.randomAccess = new RandomAccessFile(fd.file, "w");
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
    
    /**
     * The same as Read(), but it does not store this read on the stack. This is
     * required for re-reading the entire file for saving Write()s.
     * @return
     * @throws EOFException
     * @throws IOException 
     */
    private String readAndDontStore() throws EOFException, IOException {
        if (descriptor == null)
            return "";
        
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
        
        if (totalReadBytes > 0) {
            // We will have reached the end of the file here. Don't throw an exception this time.
            return stringBuff.toString();
        }
        
        return "";
    }
    
    /*
     * Stores the current buffer in the buffer stack.
     */
    private void storeBuffer() throws IOException {
        if (descriptor == null)
            return;
        
        // Rewind the file and read all contents.
        descriptor.randomAccess.seek(0);
        
        // Read and put on the stack for later use.
        String buf = readAndDontStore();
        descriptor.buffers.add(buf);
        // Go back to our last position.
        descriptor.randomAccess.seek(descriptor.pointer);
    }
    
    @Override
    public void OpenForWriteNative(String path) throws FileNotFoundException {
        FileDescriptor fd = new FileDescriptor();
        fd.file = new File(path);
        fd.path = path;
        
        try {
            openDescriptor(fd);
        } catch (IOException ex) {
            Logger.getLogger(QuorumFileReaderDebug.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void OpenForWriteAppendNative(String path) throws FileNotFoundException {
        FileDescriptor fd = new FileDescriptor();
        fd.file = new File(path);
        fd.path = path;
        
        try {
            openDescriptor(fd);
        } catch (IOException ex) {
            Logger.getLogger(QuorumFileReaderDebug.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Undo an open operation.
     */
    public void unOpenForWriteNative() throws IOException {
        if (descriptor == null)
            return; // nothing is open
        
        descriptor.randomAccess.close();
        descriptor = null; // no file is open now.
    }
    
    /**
     * Undo an open operation.
     * 
     */
    public void unOpenForWriteAppendNative() throws IOException {
        unOpenForWriteNative();
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
    public void WriteNative(String textToWrite) throws IOException {
        // Keep a copy of the old buffer in case we need to unWrite later.
        storeBuffer();
        
        byte[] bytes = textToWrite.getBytes();
        descriptor.randomAccess.write(bytes);
        updatePosition(descriptor.randomAccess.getFilePointer());
        return;    
    }
    
    public void unWriteNative() throws IOException {
        // Rewrite the entire file. We will have to close it, open it to rewrite the new buffer
        // and restore our previous position. In other wors, it's very ugly.
        
        // Can we unwrite?
        if (descriptor == null || descriptor.buffers.isEmpty())
            return;
        
        // Close old file, open new one, write data, and re-open.
        descriptor.randomAccess.close();
        // Open file for writing.
        File f = new File(descriptor.path);
        BufferedWriter w = new BufferedWriter(new FileWriter(f, false));
        
        // Put all of the previous buffer into the file and close it.
        w.write(descriptor.buffers.pop()); 
        w.close();
        
        // Re-open file... and done.
        openDescriptor(descriptor);
    }
    
    @Override
    public void WriteLineNative(String textToWrite) throws EOFException, IOException {
        // Keep a copy of the old buffer in case we need to unWrite later.
        storeBuffer();
        
        byte[] bytes = textToWrite.getBytes();
        byte[] newline = System.getProperty("line.separator").getBytes();
        descriptor.randomAccess.write(bytes);
        descriptor.randomAccess.write(newline);
        updatePosition(descriptor.randomAccess.getFilePointer());
        return;  
    }
    
    /**
     * We can just call unWriteNative() here.
     * @throws IOException 
     */
    public void unWriteLineNative() throws IOException {
        this.unWriteNative();
    }
}
