/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author jeff
 */
public class QuorumFileWriter {
    protected File file = null;
    protected BufferedWriter bufferedWriter = null;
    
    public void OpenForWriteNative(String path) throws IOException {
        file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file));
    }

    public void OpenForWriteAppendNative(String path) throws IOException {
        file = new File(path);
        bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    }

    public void WriteNative(String textToWrite) throws IOException {
        bufferedWriter.write(textToWrite);
    }

    public void WriteLineNative(String textToWrite) throws IOException {
        bufferedWriter.write(textToWrite);
        bufferedWriter.newLine();
    }

    public void Close() throws IOException {
        if (bufferedWriter != null)
            bufferedWriter.close();
    }

    public void PushToDisk() throws IOException {
        bufferedWriter.flush();
    }
}
