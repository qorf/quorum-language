/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.IOException;

/**
 *
 * @author jeff
 */
public class FileWriter {
    public java.lang.Object $me = null;
    private QuorumFileWriter inst = new QuorumFileWriter();
    
    public void OpenForWriteNative(String path) throws IOException {
        inst.OpenForWriteNative(path);
    }

    public void OpenForWriteAppendNative(String path) throws IOException {
        inst.OpenForWriteAppendNative(path);
    }

    public void WriteNative(String textToWrite) throws IOException {
        inst.WriteNative(textToWrite);
    }

    public void WriteLineNative(String textToWrite) throws IOException {
        inst.WriteLineNative(textToWrite);
    }

    public void Close() throws IOException {
        inst.Close();
    }

    public void PushToDisk() throws IOException {
        inst.PushToDisk();
    }
}
