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

/**
 * The underlying implementation of the quorum File class, with debugging features,
 * such as unreading and unwriting from a file.
 * 
 * @author jeff
 */
public class QuorumDebugFile extends QuorumFile {
    /**
     * A FileDescriptor represents everything abou the current state of a file,
     * including its path, mode, append/writable status, its previous (and current)
     * pointer, as well as its previous (and current) buffer.
     */
    private class FileDescriptor {
        public File file = null;
        public String filePath = "";
        public int fileMode = INVALID_MODE;
        public boolean append = false;
        public boolean writable = false;
        public boolean eof = false;
        public Stack<Long> ptrStack = new Stack<Long>();
        public Stack<String> bufStack = new Stack<String>();
        public long currentPtr = 0;
        public long length = 0;
        public boolean existed = true; // if this is false, delete the file upon unOpen.
    }
    
    /**
     * Represents a Move() operation. These can be used later to undo moves and deletes.
     */
    private class MoveRecord {
        public String from = "";
        public String to = "";
    }
    
    private Stack<FileDescriptor> descriptors = new Stack<FileDescriptor>();
    private FileDescriptor curDescriptor = null;
    
    // Keep track of created directories.
    private Stack<String> directories = new Stack<String>();
    private Stack<MoveRecord> moves = new Stack<MoveRecord>();
    private Stack<MoveRecord> deletes = new Stack<MoveRecord>();
    
    /* -- class methods -- */
    @Override
    public boolean CreateDirectory(String path) {
        File f = new File(path);
        boolean result = f.mkdir();
        
        if (result) {
            // Keep track of this directory, since we created it.
            directories.add(path);
        }
        
        return result;
    }
    
    public void unCreateDirectory() {
        // Delete the last created directory.
        
        if (directories.isEmpty())
            return;
        
        // Delete the directory.
        String path = directories.pop();
        File dir = new File(path);
        deleteDir(dir);
    }
    
    /**
     * Recursively delete everything in a directory. Is called of unCreateDirectory()
     * can confirm that we created the specified directory during runtime.
     * 
     * Copied verbatim from http://www.exampledepot.com/egs/java.io/DeleteDir.html .
     * @param path
     * @return 
     */
    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
    
    @Override
    /**
     * When debugging, we won't delete files, but instead, rename them to
     * <name>.quorumDeleted<timestamp> where <timestamp> is the current system time in
     * milliseconds. These will be stored so that unDelete() is possible, which
     * will just rename it.
     * 
     * Disadvantage: If the program terminates improperly, "deleted" files
     * will clutter the disk.
     */
    public boolean Delete(String path) {
        String newPath = path + ".quorumdeleted" + System.currentTimeMillis();
        
        File f = new File(path);
        boolean result = f.renameTo(new File(newPath));
        
        // If this succeeded, store it.
        if (result) {
            MoveRecord rec = new MoveRecord();
            rec.from = path;
            rec.to = newPath;
            deletes.add(rec);
        }
        
        return result;
    }
    
    public void unDelete() {
        if (deletes.isEmpty())
            return;
        
        // Undo the last delete.
        MoveRecord rec = deletes.pop();
        File f = new File(rec.to);
        f.renameTo(new File(rec.from)); // give it the old name
        
    }
    @Override
    public boolean Move(String oldPath, String newPath) {
        File f = new File(oldPath);
        boolean result = f.renameTo(new File(newPath));
        
        // If this succeeded, store it.
        if (result) {
            MoveRecord rec = new MoveRecord();
            rec.from = oldPath;
            rec.to = newPath;
            moves.add(rec);
        }
        
        return result;
    }
    
    public void unMove() {
        if (moves.isEmpty())
            return;
        
        // Undo the last move.
        MoveRecord rec = moves.pop();
        File f = new File(rec.to);
        f.renameTo(new File(rec.from)); // give it the old name
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
    @Override
    public void Open(String path, int mode, boolean append, boolean write) throws FileNotFoundException, IOException, IllegalArgumentException {        
        if (path == null)
            throw new IllegalArgumentException("The path specified is not valid.");
        else if (path.trim().isEmpty())
            throw new IllegalArgumentException("The path specified is not valid.");
        
        // Do we have a file open already?
        if (curDescriptor != null)
            throw new IOException("A file is already open for this instance of File.");

        // Build the file descriptor and make it our current one.
        FileDescriptor fd = new FileDescriptor();
        fd.filePath = path;
        fd.fileMode = mode;
        fd.append = append;
        fd.writable = write;
        
        // Open file for random access
        fd.file = new File(path);
       
        fd.length = fd.file.length();
        
        // Does it exist? If this is random access with write or just plain write mode,
        // keep track of whether or not it existed. If it didn't, we will delete it when
        // unOpen is called.
        if ((mode == RANDOM_ACCESS_MODE && write) || mode == WRITE_MODE)
            fd.existed = new File(path).exists();
        
        openDescriptor(fd);
    }
    
    /**
     * Opens the given descriptor and restores state information for the currently open file.
     * @param fd
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void openDescriptor(FileDescriptor fd) throws FileNotFoundException, IOException {
        if (fd.writable)
            randomAccess = new RandomAccessFile(fd.file, "rw");
        else
            randomAccess = new RandomAccessFile(fd.file, "r");

        
        // Make this our current descriptor now.
        curDescriptor = fd;
        
        // Seek to position given by fd.
        randomAccess.seek(fd.currentPtr);
    }
    
    public void unOpen() throws IOException {
        if (curDescriptor == null)
            return;
        
        // We will actually close the file--we have enough information to
        // resume where we left off.
        randomAccess.close();
        
        // Did this file exist before we created it? If not, delete it.
        if (((curDescriptor.fileMode == RANDOM_ACCESS_MODE && curDescriptor.writable) ||
                curDescriptor.fileMode == WRITE_MODE) && !curDescriptor.existed) {
            Delete(curDescriptor.filePath);
        }
        
        // No file is open now.
        curDescriptor = null;
    }
    
    /**
     * Close the file.
     * @return
     */
    @Override
    public void Close() throws IOException {
        if (curDescriptor == null)
            return;
        
        // We will actually close the file--we have enough information to
        // resume where we left off.
        randomAccess.close();

        
        // For later use by unClose().
        descriptors.add(curDescriptor);
        
        curDescriptor = null;
    }
    
    public void unClose() throws IOException {
        // Make our current descriptor the previous one.
        if (!descriptors.isEmpty()) {
            FileDescriptor fd = descriptors.pop();
            openDescriptor(fd);
        }
    }
    
    /**
     * Set position of read/write pointer. Only works in random access mode.
     * @return 
     */
    @Override
    public void SetPosition(long position) throws IOException, IllegalArgumentException {
        if (curDescriptor == null || curDescriptor.fileMode != RANDOM_ACCESS_MODE)
            throw new IOException("SetPosition() requires a file open in random access mode.");
        else if (position < 0)
            throw new IllegalArgumentException("The given position must be greater than or equal to zero.");
        
        // Store the previous position.
        randomAccess.seek(position);
        updatePosition(position);
        
        if (position < curDescriptor.length)
            eof = false;
    }
    
    public void unSetPosition() throws IOException {
        // Get the previous position from the stack.
        if (!curDescriptor.ptrStack.isEmpty()) {
            long pos = getLastPosition();
            randomAccess.seek(pos);
            curDescriptor.currentPtr = pos;
        }
    }
    
    /**
     * Read entire file from current position and return contents.
     * @return 
     */
    @Override
    public String Read() throws EOFException, IOException {
        if (curDescriptor == null || (curDescriptor.fileMode != READ_MODE && curDescriptor.fileMode != RANDOM_ACCESS_MODE))
        {
            // Not in a valid mode, or no open file.
            throw new IOException("Read() requires a file open in read or random access mode.");
        }
        
        if (curDescriptor.eof)
            throw new EOFException();
        

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
        
        // Make this our new pointer.
        updatePosition(randomAccess.getFilePointer());
        
        if (totalReadBytes > 0) {
            // We will have reached the end of the file here. Don't throw an exception this time.
            eof = true;
            return stringBuff.toString();
        }
        else {
            throw new IOException("Read() could not read any data from the file.");
        }        
    }
    
    /**
     * The same as Read(), but it does not store this read on the stack. This is
     * required for re-reading the entire file for saving Write()s.
     * @return
     * @throws EOFException
     * @throws IOException 
     */
    private String ReadAndDontStore() throws EOFException, IOException {
        if (curDescriptor == null || (curDescriptor.fileMode != READ_MODE && curDescriptor.fileMode != RANDOM_ACCESS_MODE))
            return "";
        
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
            return stringBuff.toString();
        }
        
        return "";
    }
    /**
     * Change the current pointer and store the previous one on the stack.
     * @param ptr 
     */
    private void updatePosition(long ptr) {
        if (curDescriptor == null)
            return;
        
        curDescriptor.ptrStack.add(curDescriptor.currentPtr);
        curDescriptor.currentPtr = ptr;
    }
    
    /**
     * Get the last position from the pointer stack. If the stack is empty,
     * return 0.
     * @return 
     */
    private long getLastPosition() {
        if (curDescriptor == null || curDescriptor.ptrStack.isEmpty())
            return 0;
        
        long pos = curDescriptor.ptrStack.pop();
        return pos;
    }
    
    public void unRead() throws EOFException, IOException {
        if (curDescriptor == null)
            return;
        
        // Go back to our last position.
        long pos = getLastPosition();
        randomAccess.seek(pos);
        curDescriptor.currentPtr = pos;
    }
    
    /**
     * Read `amount' number of bytes from file
     * @param amount
     * @return 
     */
    @Override
    public String Read(int amount) throws EOFException, IOException, IllegalArgumentException {
        if (curDescriptor == null || (curDescriptor.fileMode != READ_MODE && curDescriptor.fileMode != RANDOM_ACCESS_MODE))
        {
            // Not in a valid mode, or no open file.
            throw new IOException("Read() requires a file open in read or random access mode.");
        }
        
        if (amount <= 0)
            throw new IllegalArgumentException("Amount to read must be greater than zero.");
                
        if (curDescriptor.eof)
            throw new EOFException();
                
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
        
        // Make this our new pointer.
        updatePosition(randomAccess.getFilePointer());
        
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
    
    /**
     * Reads the next line in the file.
     * @return the line
     */
    @Override
    public String ReadLine() throws IOException {
       if (curDescriptor == null || (curDescriptor.fileMode != READ_MODE && curDescriptor.fileMode != RANDOM_ACCESS_MODE))
       {
           // Not in a valid mode, or no open file.
           throw new IOException("Read() requires a file open in read or random access mode.");
       }
        
       String line = randomAccess.readLine();

       if (line == null) {
            if (eof) {
                throw new EOFException();
            }
            eof = true;
            line = "";
        }
       
        // Make this our new pointer.
        updatePosition(randomAccess.getFilePointer());
        
        return line;
    }
    
    /**
     * Write text to the file.
     * @param text
     * @return
     */
    @Override
    public void Write(String text) throws IOException {
        // Don't write empty strings or null strings.
        if (text == null)
            throw new NullPointerException("Text value must not be Nothing.");
        else if (text.isEmpty())
            throw new NullPointerException("Text value must be non-empty.");
        
        if (curDescriptor == null)
            throw new IOException("Write() requires a file open in write or wrtiable random access mode.");

        if (!curDescriptor.writable)
            throw new IOException("Write() called when random access writing is disabled.");
        
        // Keep a copy of the old buffer in case we need to unWrite later.
        storeBuffer();
        
        byte[] bytes = text.getBytes();
        randomAccess.write(bytes);
        updatePosition(randomAccess.getFilePointer());
        return;
    }
    
    /**
     * Write a line of text to the file, ended with system-dependent newline.
     * @param text
     * @return
     */
    @Override
    public void WriteLine(String text) throws IOException, IllegalArgumentException {
        // Don't write empty strings or null strings.
        if (text == null)
            throw new NullPointerException("Text value must not be Nothing.");
        
        if (curDescriptor == null)
            throw new IOException("Write() requires a file open in write or wrtiable random access mode.");

        if (!curDescriptor.writable)
            throw new IOException("Write() called when random access writing is disabled.");
        
        // Keep a copy of the old buffer in case we need to unWrite later.
        storeBuffer();
        
        byte[] bytes = text.getBytes();
        byte[] newline = System.getProperty("line.separator").getBytes();
        randomAccess.write(bytes);
        randomAccess.write(newline);
        updatePosition(randomAccess.getFilePointer());
        return;        
    }
    
    /*
     * Stores the current buffer in the buffer stack.
     */
    private void storeBuffer() throws IOException {
        if (curDescriptor == null)
            return;
        
        // Rewind the file and read all contents.
        randomAccess.seek(0);
        
        // Read and put on the stack for later use.
        String buf = ReadAndDontStore();
        curDescriptor.bufStack.add(buf);
        
        // Go back to our last position.
        randomAccess.seek(curDescriptor.currentPtr);
    }
    
    public void unWrite() throws IOException {
        // Rewrite the entire file. We will have to close it, open it to rewrite the new buffer
        // and restore our previous position. In other wors, it's very ugly.
        
        // Can we unwrite?
        if (curDescriptor == null || curDescriptor.bufStack.isEmpty())
            return;
        
        // Close old file, open new one, write data, and re-open.
        randomAccess.close();
        // Open file for writing.
        File f = new File(curDescriptor.filePath);
        BufferedWriter w = new BufferedWriter(new FileWriter(f, false));
        
        // Put all of the previous buffer into the file and close it.
        w.write(curDescriptor.bufStack.pop()); 
        w.close();
        
        // Re-open file... and done.
        openDescriptor(curDescriptor);
    }


    /**
     * Rewind the read/write pointer. Only works for random access files.
     * @return 
     */
    @Override
    public void Rewind() throws IOException {
        if (openMode != RANDOM_ACCESS_MODE)
            throw new IOException("Rewind() requires a file open in random access mode.");
        
        randomAccess.seek(0);
        updatePosition(0);
        eof = false; // we aren't at the end of the file.
    }
}
