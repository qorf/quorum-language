/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.IOException;
import quorum.Libraries.Language.Errors.*;

/**
 *
 * @author jeff
 */
public class FileWriter {
    public java.lang.Object me_ = null;
    private QuorumFileWriter inst = new QuorumFileWriter();
    
    public void OpenForWriteNative(String path) throws InputOutputError {
        try {
            inst.OpenForWriteNative(path);
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    public void OpenForWriteAppendNative(String path) throws InputOutputError {
        try {
            inst.OpenForWriteAppendNative(path);
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    public void WriteNative(String textToWrite) throws InputOutputError {
        try {
            inst.WriteNative(textToWrite);
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    public void WriteLineNative(String textToWrite) throws InputOutputError {
        try {
            inst.WriteLineNative(textToWrite);
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    public void Close() throws InputOutputError {
        try {
            inst.Close();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    public void PushToDisk() throws InputOutputError {
        try {
            inst.PushToDisk();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
}
