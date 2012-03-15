/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import quorum.Libraries.Language.Errors.*;

/**
 *
 * @author jeff
 */
public class FileReader {
    public java.lang.Object $me = null;
    private QuorumFileReader inst = new QuorumFileReader();
    
    public void OpenForReadNative(String path) throws FileNotFoundException {
        inst.OpenForReadNative(path);
    }
    
    public void Close() throws IOException {
        inst.Close();
    }

    public String ReadNative() throws IOException {
        return inst.ReadNative();
    }

    // TODO: Document
    public String ReadNative(int numberOfBytes) throws EOFException, IOException {
        return inst.ReadNative(numberOfBytes);
    }
    
    public String ReadLineNative() throws EOFException, IOException {
        return inst.ReadLineNative();
    }
    
    public boolean IsAtEndOfFile() {
        return inst.IsAtEndOfFile();
    }
}
