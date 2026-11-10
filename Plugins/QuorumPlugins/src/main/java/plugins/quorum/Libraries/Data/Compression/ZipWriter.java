package plugins.quorum.Libraries.Data.Compression;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import javax.imageio.ImageIO;
import quorum.Libraries.Language.Errors.InputOutputError;

/*
* Java side of Libraries.Data.Compression.ZipWriter. Uses only java.util.zip,
* javax.imageio and java.security.MessageDigest, should be standard with every
* available java runtime.
*/
public class ZipWriter{
    public java.lang.Object me_ = null;

    private ZipOutputStream zip;
    private String archivePath = "";
    private BufferedImage image;
    private final Map<String, byte[]> written = new HashMap<>();

    public void Open(String path) throws InputOutputError{
        Close();
        File file = new File(path).getAbsoluteFile();
        File folder = file.getParentFile();
        if(folder != null && !folder.isDirectory() && !folder.mkdirs()){
            throw Failure("ZipWriter: could not create the folder " + folder.getPath());
        }
        try{
            zip = new ZipOutputStream(new FileOutputStream(file));
            zip.setLevel(9);
            archivePath = file.getPath();
        }catch(IOException e){
            throw Failure("ZipWriter: could not create " + file.getPath() + ": " + e.getMessage());
        }
    }

    public void AddText(String entryName, String content) throws InputOutputError{
        AddBytes(entryName, content.getBytes(StandardCharsets.UTF_8));
    }

    public void BeginImage(int width, int height) throws InputOutputError{
        image = new BufferedImage(Math.max(width, 1), Math.max(height, 1), BufferedImage.TYPE_INT_ARGB);
    }

    public void SetPixel(int x, int y, int red, int green, int blue, int alpha) throws InputOutputError{
        if(image == null || x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()){
            return;
        }
        int argb = clamp(alpha) << 24 | clamp(red) << 16 | clamp(green) << 8 | clamp(blue);
        image.setRGB(x, y, argb);
    }

    public void AddImage(String entryName) throws InputOutputError{
        if(image == null){
            throw Failure("ZipWriter: AddImage(" + entryName + ") was called before BeginImage");
        }
        try{
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            ImageIO.write(image, "png", png);
            AddBytes(entryName, png.toByteArray());
        }catch(IOException e){
            throw Failure("ZipWriter: could not encode " + entryName + " as PNG: " + e.getMessage());
        }
    }

    public void AddCopy(String entryName, String existingEntryName) throws InputOutputError{
        byte[] bytes = written.get(existingEntryName);
        if(bytes == null){
            throw Failure("ZipWriter: AddCopy(" + entryName + ") needs " + existingEntryName
            + ", which has not been added to " + archivePath + " yet");
        }
        AddBytes(entryName, bytes);
    }

    public void AddMd5(String entryName, String content) throws InputOutputError{
        try{
            byte[] digest = MessageDigest.getInstance("MD5").digest(content.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(32);
            for(byte b : digest){
                hex.append(String.format("%02X", b & 0xFF));
            }
            AddBytes(entryName, hex.toString().getBytes(StandardCharsets.US_ASCII));
        }catch(NoSuchAlgorithmException e){
            throw Failure("ZipWriter: this Java runtime has no MD5 support");
        }
    }

    public void Close() throws InputOutputError{
        written.clear();
        if(zip == null){
            return;
        }
        try{
            zip.close();
        }catch(IOException e){
            throw Failure("ZipWriter: could not finish " + archivePath + ": " + e.getMessage());
        }finally{
            zip = null;
        }
    }

    private void AddBytes(String entryName, byte[] bytes) throws InputOutputError{
        if(zip == null){
            throw Failure("ZipWriter: " + entryName + " was added before Open was called");
        }
        if(written.containsKey(entryName)){
            throw Failure("ZipWriter: " + archivePath + " already has an entry named " + entryName);
        }
        try{
            zip.putNextEntry(new ZipEntry(entryName));
            zip.write(bytes);
            zip.closeEntry();
            written.put(entryName, bytes);
        }catch(IOException e){
            throw Failure("ZipWriter: could not add " + entryName + " to " + archivePath + ": " + e.getMessage());
        }
    }

    private static InputOutputError Failure(String message){
        InputOutputError error = new InputOutputError();
        error.SetErrorMessage(message);
        return error;
    }

    private static int clamp(int value){
        return value < 0 ? 0 : (value > 255 ? 255 : value);
    }
}
