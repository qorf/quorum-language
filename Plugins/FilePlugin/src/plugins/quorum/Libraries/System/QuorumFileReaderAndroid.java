/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Language.Errors.EndOfFileError;
import quorum.Libraries.Language.Errors.InputOutputError;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class QuorumFileReaderAndroid extends QuorumFileReader {
    File_ quorumFile = null;
    AndroidAssetReader assetReader = null;
    public QuorumFileReaderAndroid() {
    }
    
    @Override
    public void OpenForReadNative(File_ quorumFile) throws FileNotFoundException {
        this.quorumFile = quorumFile;
        if(!QuorumFileAndroid.IsAssetRequest(this.quorumFile.GetWorkingDirectory())) {
            super.OpenForReadNative(quorumFile);
            return;
        }
        
        assetReader = new AndroidAssetReader();
        assetReader.Open(quorumFile.GetPath());
    }
    
    @Override
    public void Close() throws IOException {
        if(!QuorumFileAndroid.IsAssetRequest(this.quorumFile.GetWorkingDirectory())) {
            super.Close();
            return;
        }
        try {
            assetReader.Close();
        } catch (InputOutputError ex) {
            Logger.getLogger(QuorumFileReaderAndroid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String ReadNative() throws IOException {
        if(!QuorumFileAndroid.IsAssetRequest(this.quorumFile.GetWorkingDirectory())) {
            super.ReadNative();
        }
        
        try {
            return assetReader.ReadNative();
        } catch (InputOutputError ex) {
            return null;
        }
    }

    @Override
    public String ReadNative(int numberOfBytes) throws EOFException, IOException {
        if(!QuorumFileAndroid.IsAssetRequest(this.quorumFile.GetWorkingDirectory())) {
            super.ReadNative(numberOfBytes);
        }
        
        try {
            return assetReader.ReadNative(numberOfBytes);
        } catch (InputOutputError ex) {
            return null;
        } catch (EndOfFileError ex) {
            return null;
        }
    }
    
    @Override
    public String ReadLineNative() throws EOFException, IOException {
        if(!QuorumFileAndroid.IsAssetRequest(this.quorumFile.GetWorkingDirectory())) {
            super.ReadLineNative();
        }
        
        try {
            return assetReader.ReadLineNative();
        } catch (InputOutputError ex) {
            return null;
        } catch (EndOfFileError ex) {
            return null;
        }
    }
    
    @Override
    public String ReadLinesNative() throws EOFException, IOException {
        if(!QuorumFileAndroid.IsAssetRequest(this.quorumFile.GetWorkingDirectory())) {
            super.ReadLinesNative();
        }
        
        try {
            return assetReader.ReadLinesNative();
        } catch (InputOutputError ex) {
            return null;
        } catch (EndOfFileError ex) {
            return null;
        }
    }
}
