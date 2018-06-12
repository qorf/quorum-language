/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;

/**
 *
 * @author stefika
 */
public class BinaryFileReader {
    public java.lang.Object me_ = null;
    private java.io.File file;
    private BufferedReader bufferedReader;
    
    public static void main(String[] args) {
        byte a = 127;
        a = (byte) (a + 1); //compiler error without cast
        System.out.println(a);
        
        short b = 32767;
        b = (short) (b + 1); //compiler error without cast
        System.out.println(b);
    }
    
    public ByteArray_ ReadBytes() {
        ByteArray array = new ByteArray();
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
            array.plugin_.setBytes(bytes);
        } catch (IOException ex) {
            Logger.getLogger(BinaryFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return array;
    }
    
    public void OpenForReadNative(String path) throws FileNotFoundException {
        file = new java.io.File(path);
    }
    
    public void Close() throws IOException {
        System.out.println("Close");
    }
    
    public String ReadCharacter() {
        System.out.println("ReadCharacter");
        return "";
    }
    
    public int ReadByteAsInteger() {
        System.out.println("ReadByteAsInteger");
        return 0;
    }
    
    public int ReadTwoBytesAsInteger() {
        System.out.println("ReadTwoBytesAsInteger");
        return 0;
    }
    
    public int ReadInteger() {
        System.out.println("ReadInteger");
        return 0;
    }
    
    public double ReadFourBytesAsNumber() {
        System.out.println("ReadFourBytesAsNumber");
        return 0;
    }

    public double ReadNumber() {
        System.out.println("ReadNumber");
        return 0;
    }
}
