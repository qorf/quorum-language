/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import android.app.Activity;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import plugins.quorum.Libraries.Game.AndroidApplication;
import quorum.Libraries.Language.Errors.*;

/*
This class is adapted from the FileReader and QuorumFileReader classes. It is
used to draw information from an Android asset via a BufferedReader.
*/
public class AndroidAssetReader 
{
    public java.lang.Object me_ = null;
    
    protected BufferedReader bufferedReader = null;
    private boolean atEOF = false;
    
    public void Open(String assetName)
    {
        try {
            Activity activity = AndroidApplication.GetActivity();
            AssetManager assets = activity.getAssets();
            InputStream inputStream = assets.open(assetName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        catch (IOException e)
        {
            AndroidApplication.LogStatic("AndroidAssetReader - Open", "Exception " + e.getMessage());
        }
        
    }
    
    public void Close() throws InputOutputError 
    {
        try
        {
            if (bufferedReader != null)
                bufferedReader.close();
            
            bufferedReader = null;
        }
        catch (IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }

    public String ReadNative() throws InputOutputError 
    {
        if (atEOF)
            return "";
        
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int readCount;
        
        try
        {
            do
            {
                readCount = bufferedReader.read(buffer, 0, 1024);
                if (readCount > 0)
                    stringBuilder.append(buffer, 0, readCount);
                
            } while (readCount > 0);
            
            atEOF = true;
        }
        catch (IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
        
        return stringBuilder.toString();
    }

    public String ReadNative(int numberOfBytes) throws EndOfFileError, InputOutputError 
    {
        if (atEOF)
            throw new EndOfFileError();
        
        char[] buffer = new char[numberOfBytes];
        
        try
        {
            int readCount = bufferedReader.read(buffer, 0, numberOfBytes);
        
            if (readCount <= 0)
            {
                atEOF = true;
                throw new EndOfFileError();
            }
            
            // If we couldn't read the full requested amount, it's because we've
            // reached end of file. Don't raise an exception in this case.
            if (readCount < numberOfBytes)
                atEOF = true;
        }
        catch(IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
        
        return String.valueOf(buffer);
    }
    
    public String ReadLineNative() throws EndOfFileError, InputOutputError 
    {
        String line;
        
        try
        {
            line = bufferedReader.readLine();
            if (line == null)
            {
                if (atEOF)
                    throw new EndOfFileError();
                
                atEOF = true;
                line = "";
            }
            
            return line;
        }
        catch(IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    public String ReadLinesNative() throws EndOfFileError, InputOutputError 
    {
        String line;
        String lines = "";
        String newLine = System.getProperty("line.separator");
        
        try
        {
            do
            {
                line = bufferedReader.readLine();
                
                if (line == null)
                {
                    if (atEOF)
                        throw new EndOfFileError();
                    
                    atEOF = true;
                }
                else
                {
                    lines += line + newLine;
                }
            } while (line != null);
        }
        catch(IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
        
        return lines;
    }
    
    public boolean IsAtEndOfFile() 
    {
        return atEOF;
    }
    
    public String GetSystemNewline() 
    {
        return System.getProperty("line.separator");
    }
}
