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
public class FileRandomAccess {
    public java.lang.Object me_ = null;
    private QuorumFileRandomAccess inst = new QuorumFileRandomAccess();
    
    public void OpenForRandomAccessNative(String path) throws FileNotFoundError {
        try {
            inst.OpenForRandomAccessNative(path);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundError();
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

    public double GetPosition() throws InputOutputError {
        try {
            return inst.GetPosition();
        } catch (IOException ex) {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    public void SetPositionNative(double position) throws InputOutputError {
        try {
            inst.SetPositionNative((long)position);
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

    public String ReadLineNative() throws InputOutputError {
        try {
            return inst.ReadLineNative();
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
    
    public boolean IsAtEndOfFile() {
        return inst.IsAtEndOfFile();
    }
    
    public String GetSystemNewline() {
        return System.getProperty("line.separator");
    }
}
