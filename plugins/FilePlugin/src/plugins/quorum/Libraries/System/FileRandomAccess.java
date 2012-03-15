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
    
    public void SetPositionNative(long position) throws IOException {
        inst.SetPositionNative(position);
    }

    public String ReadNative() throws IOException {
        return inst.ReadNative();
    }

    public String ReadNative(int numberOfBytes) throws EOFException, IOException {
        return inst.ReadNative(numberOfBytes);
    }

    public String ReadLineNative() throws IOException {
        return inst.ReadLineNative();
    }

    public void WriteNative(String textToWrite) throws IOException {
        inst.WriteNative(textToWrite);
    }

    public void WriteLineNative(String textToWrite) throws IOException {
        inst.WriteLineNative(textToWrite);
    }
    
    public boolean IsAtEndOfFile() {
        return inst.IsAtEndOfFile();
    }
}
