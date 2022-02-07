/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Containers.ByteArray;
import quorum.Libraries.Containers.ByteArray_;
import quorum.Libraries.Interface.Events.FileLoadEvent;
import quorum.Libraries.Interface.Events.FileLoadListener_;
import quorum.Libraries.System.BinaryFileReader_;
import quorum.Libraries.System.File_;

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
        byte[] bytes = FileToByteArray(file);
        array.plugin_.setBytes(bytes);
        
        return array;
    }
    
    public static byte[] FileToByteArray(java.io.File file) 
    {
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024*8];
            int bytesRead = 0;
            
            while((bytesRead = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, bytesRead);
            }    
            
            byteArray = outputStream.toByteArray();
            //bytes = Files.readAllBytes(file.toPath());
            //array.plugin_.setBytes(bytes);
        } catch (IOException ex) {
            Logger.getLogger(BinaryFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return byteArray;
    }
    
    public void OpenForReadNative(String path) throws FileNotFoundException {
        file = new java.io.File(path);
    }
    
    public void OpenForRead(File_ file, FileLoadListener_ listener) throws FileNotFoundException {
        this.file = new java.io.File(file.GetAbsolutePath());
        FileLoadEvent event = new FileLoadEvent();
        event.Set(file, (BinaryFileReader_)me_);
        listener.OnLoad(event);
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
