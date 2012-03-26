/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Java implementation for the "FileReader" Quorum class.
 * 
 * @author Jeff Wilson
 */
public class QuorumFileReader {
    protected File file = null;
    protected BufferedReader bufferedReader = null;
    protected long fileSize = 0; // used for EOF detection.
    protected boolean atEOF = false; // are we at the end of the file?
    protected long readSoFar = 0; // how much have we read so far?
    
    public void OpenForReadNative(String path) throws FileNotFoundException {
        file = new File(path);
        bufferedReader = new BufferedReader(new FileReader(file));
        fileSize = file.length();
        readSoFar = 0;
        
        // Is the file empty? if so, we're at eof.
        if (fileSize == 0)
            atEOF = true;
        else
            atEOF = false;
    }
    
    public void Close() throws IOException {
        if (bufferedReader != null)
            bufferedReader.close();
        file = null;
        bufferedReader = null;
        fileSize = 0;
        atEOF = false;
        readSoFar = 0;
    }

    public String ReadNative() throws IOException {
        if (atEOF)
            throw new EOFException();
        
        // Read all remaining file contents.
        StringBuilder stringBuff = new StringBuilder();
        char[] buffer = new char[1024];
        int numRead = 0;
        long totalRead = 0;

        do {
            numRead = bufferedReader.read(buffer, 0, 1024);

            if (numRead > 0) {
                stringBuff.append(buffer, 0, numRead);
                totalRead += numRead;
            }
        } while (totalRead < fileSize && numRead > 0);

        if (totalRead > 0)
        {
            // We will have reached the end of the file here. Don't throw an exception this time.
            atEOF = true;
            readSoFar = fileSize;
            return stringBuff.toString();
        }
        else
        {
            throw new IOException("Read() could not read any data from the file.");
        }
    }

    // TODO: Document
    public String ReadNative(int numberOfBytes) throws EOFException, IOException {
        if (atEOF)
            throw new EOFException();
        
        // Read appropriate number of bytes from the file.
        char[] buffer = new char[numberOfBytes];
        int numRead = 0;

        numRead = bufferedReader.read(buffer, 0, numberOfBytes);

        if (numRead == 0 || numRead == -1) {
            atEOF = true;
            throw new EOFException();
        }

        if (numRead > 0)
        {
            readSoFar += numRead;
            if (readSoFar >= fileSize) {
                // Reached end of file. Don't raise an exception this time.
                atEOF = true;
            }
            return String.valueOf(buffer);
        }
        else
        {
            throw new IOException("Read() could not read any data from the file.");
        }
    }
    
    public String ReadLineNative() throws EOFException, IOException {
        String line = bufferedReader.readLine();

        if (line == null) {
            if (atEOF) {
                throw new EOFException();
            }
            
            atEOF = true;
            line = "";
            readSoFar = fileSize;
        } else {
            readSoFar += line.length() + System.getProperty("line.separator").length(); // newline is consumed.
            
            if (readSoFar >= fileSize)
                atEOF = true; // don't throw exception this time.
        }

        return line;
    }
    
    public boolean IsAtEndOfFile() {
        return this.atEOF;
    }
}
