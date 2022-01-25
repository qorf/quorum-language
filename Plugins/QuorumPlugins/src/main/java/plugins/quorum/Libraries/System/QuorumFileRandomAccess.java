/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author jeff
 */
public class QuorumFileRandomAccess {
    protected File file = null;
    protected RandomAccessFile randomAccess = null;
    protected boolean atEOF = false;
    protected long fileSize = 0;
    
    public void OpenForRandomAccessNative(String path) throws FileNotFoundException {
        file = new File(path);
        fileSize = file.length();
        randomAccess = new RandomAccessFile(file, "rw");
        atEOF = false;
    }

    public void Close() throws IOException {
        if (randomAccess != null)
            randomAccess.close();
    }

    public long GetPosition() throws IOException {
        return randomAccess.getFilePointer();
    }
    
    public void SetPositionNative(long position) throws IOException {
        randomAccess.seek(position);
        if (position < fileSize - 1)
            atEOF = false;
        else
            atEOF = true;
    }

    public String ReadNative() throws IOException {
        long currentPos = randomAccess.getFilePointer();

        // Gather all our remaining data.
        StringBuilder stringBuff = new StringBuilder();
        long remainingBytes = fileSize - currentPos;
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
            atEOF = true;
            return stringBuff.toString();
        }
        else {
            throw new IOException("Read() could not read any data from the file.");
        }
    }

    public String ReadNative(int numberOfBytes) throws EOFException, IOException {
        // Gather all our remaining data.
        StringBuilder stringBuff = new StringBuilder();
        long totalReadBytes = 0;
        int numRead = 0;
        byte[] byteBuff = new byte[numberOfBytes];
        numRead = randomAccess.read(byteBuff);

        if (numRead > 0) {
            totalReadBytes += numRead;
            stringBuff.append(new String(byteBuff));
        }
        else if (numRead == 0 || numRead == -1) {
            // We are at the end of the file. Let the uesr know this time.
            atEOF = true;
            throw new EOFException();
        }

        if (totalReadBytes > 0) {
            if (numRead >= fileSize) {
                // We are at the end of the file. Don't complain this time.
                atEOF = true;
            }
            return stringBuff.toString();
        }
        else {
            throw new IOException("Read() could not read any data from the file.");
        }
    }

    public String ReadLineNative() throws IOException {
       String line = randomAccess.readLine();

       if (line == null) {
            if (atEOF) {
                throw new EOFException();
            }
            atEOF = true;
            line = "";
        }

        return line;
    }
    
    public String ReadLinesNative() throws EOFException, IOException {
        String line = null;
        String lines = "";
        String newLine = System.getProperty("line.separator");
        do {
            line = randomAccess.readLine();

            if (line == null) {
                if (atEOF) {
                    throw new EOFException();
                }

                atEOF = true;
                line = "";
            } else {
                lines += line + newLine;
            }
        } while (line != null);

        return lines;
    }

    public void WriteNative(String textToWrite) throws IOException {
        byte[] bytes = textToWrite.getBytes();
        randomAccess.write(bytes);
    }

    public void WriteLineNative(String textToWrite) throws IOException {
        byte[] bytes = textToWrite.getBytes();
        byte[] newline = System.getProperty("line.separator").getBytes();
        randomAccess.write(bytes);
        randomAccess.write(newline);
    }
    
    public boolean IsAtEndOfFile() {
        return this.atEOF;
    }
    
    public String GetSystemNewline() {
        return System.getProperty("line.separator");
    }
}
