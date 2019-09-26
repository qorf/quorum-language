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
    public java.lang.Object me_ = null;
    private QuorumFileReader inst = new QuorumFileReader();
    
    public void OpenForReadNative(String path) throws FileNotFoundError {
        try {
            inst.OpenForReadNative(path);
        } catch (FileNotFoundException ex) {
            FileNotFoundError error = new FileNotFoundError();
            error.SetPath(path);
            throw error;
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

    public String ReadNative() throws InputOutputError {
        try {
            return inst.ReadNative();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    // TODO: Document
    public String ReadNative(int numberOfBytes) throws EndOfFileError, InputOutputError {
        try {
            return inst.ReadNative(numberOfBytes);
        } catch (EOFException ex) {
            throw new EndOfFileError();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    public String ReadLineNative() throws EndOfFileError, InputOutputError {
        try {
            return inst.ReadLineNative();
        } catch (EOFException ex) {
            throw new EndOfFileError();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    public String ReadLinesNative() throws EndOfFileError, InputOutputError {
        try {
            return inst.ReadLinesNative();
        } catch (EOFException ex) {
            throw new EndOfFileError();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    public boolean IsAtEndOfFile() {
        return inst.IsAtEndOfFile();
    }
    
    public String GetSystemNewline() {
        return inst.GetSystemNewline();
    }
}
