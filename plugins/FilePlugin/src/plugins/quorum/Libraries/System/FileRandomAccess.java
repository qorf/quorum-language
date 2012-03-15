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
public class FileRandomAccess {
    public java.lang.Object $me = null;
    private QuorumFileRandomAccess inst = new QuorumFileRandomAccess();
    
    public void OpenForRandomAccessNative(String path) throws FileNotFoundException {
        inst.OpenForRandomAccessNative(path);
    }

    public void Close() throws IOException {
        inst.Close();
    }

    public long GetPosition() throws IOException {
        return inst.GetPosition();
    }
    
    public void SetPosition(long position) throws IOException {
        inst.SetPosition(position);
    }

    public String Read() throws IOException {
        return inst.Read();
    }

    public String Read(int numberOfBytes) throws EOFException, IOException {
        return inst.Read(numberOfBytes);
    }

    public String ReadLine() throws IOException {
        return inst.ReadLine();
    }

    public void Write(String textToWrite) throws IOException {
        inst.Write(textToWrite);
    }

    public void WriteLine(String textToWrite) throws IOException {
        inst.WriteLine(textToWrite);
    }
    
    public boolean IsAtEndOfFile() {
        return inst.IsAtEndOfFile();
    }
}
