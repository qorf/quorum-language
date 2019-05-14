/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Containers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import quorum.Libraries.Containers.ByteArray_;
import quorum.Libraries.Language.Errors.InvalidLocationError;
import java.util.zip.Inflater;
/**
 *
 * @author stefika
 */
public class ByteArray {
    public java.lang.Object me_ = null;
    private byte[] bytes;
    boolean isBigEndian = true;
    
    public boolean IsBigEndian() {
        return isBigEndian;
    }
    
    public void SetBigEndian(boolean yes) {
        isBigEndian = yes;
    }
    
    public int Get(int index) throws InvalidLocationError {
        if(bytes == null || index >= bytes.length || index < 0) {
            throw new InvalidLocationError();
        }
        return bytes[index];
    }
    
    public void Set(int index, int value) throws InvalidLocationError {
        if(bytes == null || index > bytes.length || index < 0) {
            throw new InvalidLocationError();
        }
        bytes[index] = (byte) value;
    }
    
    public ByteArray_ GetSubArray(int begin, int finish) {
        quorum.Libraries.Containers.ByteArray array = new quorum.Libraries.Containers.ByteArray();
        int size = finish - begin;
        byte[] newBytes = null;
        int j = 0;
        if(size > 0 && begin >= 0 && finish < bytes.length) {
            newBytes = new byte[size];
            for(int i = begin; i < finish; i++) {
                newBytes[j] = bytes[i];
                j = j + 1;
            }
        } else {
            newBytes = new byte[0]; //an empty array
        }
        array.plugin_.bytes = newBytes;
        return array;
    }
    
    public ByteArray_ Decompress() {
        byte[] compressedBytes = bytes;
        //System.out.println(compressedBytes.length);
        //start out with a stream at least as large as the original and allow it to grow
        ByteArrayOutputStream stream = new ByteArrayOutputStream(compressedBytes.length);
        try {
            Inflater inflator = new Inflater();
            inflator.setInput(compressedBytes, 0, compressedBytes.length);
            //System.out.println("Needs Input:" + inflator.needsInput());
            //System.out.println("Needs Dictionary:" + inflator.needsDictionary());
            byte[] buffer = new byte[1024];   
            while (!inflator.needsInput()) {
                int count = inflator.inflate(buffer);
                stream.write(buffer, 0, count);
            }  
            inflator.end();
        } catch (DataFormatException ex) {
            Logger.getLogger(ByteArray.class.getName()).log(Level.SEVERE, null, ex);
            //if an error was thrown, return nothing
            return null;
        }
        quorum.Libraries.Containers.ByteArray newBytes = new quorum.Libraries.Containers.ByteArray();
        newBytes.plugin_.bytes = stream.toByteArray();
        return newBytes;
    }
    
    public int GetSize() {
        return bytes.length;
    }
    
    public void SetSize(int size) {
        bytes = new byte[size];
    }
    
    public String CharacterFromByte(int a) {
        char value = (char) a;
        return "" + value;
    }
    
    public String CharacterFromTwoBytes(int a, int b) {
        byte[] values = new byte[2];
        values[0] = (byte) a;
        values[1] = (byte) b;
        ByteBuffer buffer = GetWrappedBuffer(values);
        char car = buffer.getChar();
        return "" + car;
    }
    
    public int IntegerFromTwoBytes(int a, int b) {
        byte[] values = new byte[2];
        values[0] = (byte) a;
        values[1] = (byte) b;
        ByteBuffer buffer = GetWrappedBuffer(values);
        short value = buffer.getShort();
        return value;
    }
    
    public int IntegerFromByte(int a) {
        int value = a;
        return value;
    }
    
    public int UnsignedIntegerFromByte(int a) {
        int value = a & 0x00FF;
        return value;
    }
    
    public int UnsignedIntegerFromTwoBytes(int a, int b) {
        byte[] values = new byte[2];
        values[0] = (byte) a;
        values[1] = (byte) b;
        ByteBuffer buffer = GetWrappedBuffer(values);
        int value = buffer.getShort() & 0xFFFF;
        
        return value;
    }
    
    
    public int SignedIntegerFromFourUnsignedBytes(int a, int b, int c, int d) {
        byte[] values = new byte[4];
        values[0] = (byte) a;
        values[1] = (byte) b;
        values[2] = (byte) c;
        values[3] = (byte) d;
        ByteBuffer buffer = GetWrappedBuffer(values);
        int value = buffer.getInt();       
        return value;
    }
    
    public String TextFromFourBytes(int a, int b, int c, int d) {
        byte[] values = new byte[4];
        values[0] = (byte) a;
        values[1] = (byte) b;
        values[2] = (byte) c;
        values[3] = (byte) d;
        ByteBuffer buffer = GetWrappedBuffer(values);
        long value = buffer.getInt() & 0xFFFFFFFF;
        return ""+value;
    }
    
    public String TextFromEightBytes(int a, int b, int c, int d, int e, int f, int g, int h) {
        byte[] values = new byte[8];
        values[0] = (byte) a;
        values[1] = (byte) b;
        values[2] = (byte) c;
        values[3] = (byte) d;
        values[4] = (byte) e;
        values[5] = (byte) f;
        values[6] = (byte) g;
        values[7] = (byte) h;
        ByteBuffer buffer = GetWrappedBuffer(values);
        long value = buffer.getLong();
        return ""+value;
    }
    
    
    private ByteBuffer GetWrappedBuffer(byte[] values) {
        ByteBuffer buffer = ByteBuffer.wrap(values);
        if(isBigEndian) {
            buffer.order(ByteOrder.BIG_ENDIAN);
        } else {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer;
    }
    public int IntegerFromFourBytes(int a, int b, int c, int d) {
        byte[] values = new byte[4];
        values[0] = (byte) a;
        values[1] = (byte) b;
        values[2] = (byte) c;
        values[3] = (byte) d;
        ByteBuffer buffer = GetWrappedBuffer(values);
        int value = buffer.getInt();
        return value;
    }
    
    public double NumberFromFourBytes(int a, int b, int c, int d) {
        byte[] values = new byte[4];
        values[0] = (byte) a;
        values[1] = (byte) b;
        values[2] = (byte) c;
        values[3] = (byte) d;
        ByteBuffer buffer = GetWrappedBuffer(values);
        float value = buffer.getFloat();
        return value;
    }
    
    public double NumberFromEightBytes(int a, int b, int c, int d,
                                       int e, int f, int g, int h) {
        byte[] values = new byte[8];
        values[0] = (byte) a;
        values[1] = (byte) b;
        values[2] = (byte) c;
        values[3] = (byte) d;
        values[4] = (byte) e;
        values[5] = (byte) f;
        values[6] = (byte) g;
        values[7] = (byte) h;
        ByteBuffer buffer = GetWrappedBuffer(values);
        double value = buffer.getDouble();
        return value;
    }
    
    /**
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @param bytes the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
