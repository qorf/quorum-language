/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Data.Compression;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import quorum.Libraries.System.File_;
import quorum.Libraries.Language.Errors.FileNotFoundError;
import quorum.Libraries.Language.Errors.InputOutputError;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author stefika
 */
public class Decompresser {
    public java.lang.Object me_ = null;
    boolean isGZip = true;

    public void DecompressGZip(File_ input, File_ output) {
        try {
            String path = input.GetAbsolutePath();
            String outPath = output.GetAbsolutePath();
            GZIPInputStream stream = new GZIPInputStream(new FileInputStream(path));
            write(outPath, stream);
            stream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DecompressZip(File_ input) {
        File myFile = new File(input.GetAbsolutePath());
        String parent = myFile.getParent() + "/";
        try {
            String path = myFile.getAbsolutePath();
            ZipFile zip = new ZipFile(path);
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while(entries.hasMoreElements()) {
                ZipEntry next = entries.nextElement();
                String name = next.getName();
                //ignore the junk on mac if it exists
                if(!name.startsWith("__MACOSX")) {
                    String entryPath = parent + name;
                    if(next.isDirectory()) {
                        File file = new File(entryPath);
                        if(!file.exists()) {
                            file.mkdirs();
                        }
                    } else {
                        File file = new File(entryPath);
                        InputStream stream = zip.getInputStream(next);
                        write(entryPath, stream);
                        stream.close();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean HasZipEntry(File_ input, String entryName) throws FileNotFoundError, InputOutputError{
        try (ZipFile zip = OpenZip(input)){
            ZipEntry entry = zip.getEntry(entryName);
            return entry != null && !entry.isDirectory();
        }catch(IOException ex){
            throw InputOutput("Could not read " + input.GetAbsolutePath() + ": " + ex.getMessage());
        }
    }

    public String ReadZipEntry(File_ input, String entryName) throws FileNotFoundError, InputOutputError{
        try(ZipFile zip = OpenZip(input)){
            ZipEntry entry = zip.getEntry(entryName);
            if(entry == null || entry.isDirectory()){
                throw InputOutput(input.GetAbsolutePath() + " has no entry named " + entryName + ".");
            }
            try(InputStream stream = zip.getInputStream(entry)){
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192];
                int count;
                while((count = stream.read(buffer)) > 0){
                    bytes.write(buffer, 0, count);
                }
                return new String(bytes.toByteArray(), StandardCharsets.UTF_8);
            }
        }catch(IOException ex){
            throw InputOutput("Could not read " + entryName + " from " + input.GetAbsolutePath() + ": " + ex.getMessage());
        }
    }

    private ZipFile OpenZip(File_ input) throws FileNotFoundError, InputOutputError{
        File file = new File(input.GetAbsolutePath());
        if(!file.isFile()){
            FileNotFoundError error = new FileNotFoundError();
            error.SetPath(file.getAbsolutePath());
            throw error;
        }
        try{
            return new ZipFile(file);
        }catch(IOException ex){
            throw InputOutput(file.getAbsolutePath() + " is not a ZIP archive (" + ex.getMessage() + ").");
        }
    }

    private static InputOutputError InputOutput(String message){
        InputOutputError error = new InputOutputError();
        error.SetErrorMessage(message);
        return error;
    }

    private void write(String outFilePath, InputStream inputStream) {
        try {
            File folder = new File(outFilePath).getParentFile();
            if (folder != null) {
                folder.mkdirs();
            }
            OutputStream out = new FileOutputStream(outFilePath);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String Unzip(String inFilePath) throws Exception {
        ZipInputStream gzipInputStream = new ZipInputStream(new FileInputStream(inFilePath));
        String outFilePath = inFilePath.replace(".gz", ".xml");
        OutputStream out = new FileOutputStream(outFilePath);

        byte[] buf = new byte[1024];
        int len;
        while ((len = gzipInputStream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        gzipInputStream.close();
        out.close();

        new File(inFilePath).delete();

        return outFilePath;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            quorum.Libraries.System.File input = new quorum.Libraries.System.File();
            input.SetWorkingDirectory("/Users/Nicole/Desktop/ziptest");
            //input.SetWorkingDirectory("/Users/stefika/Desktop/");
            input.SetPath("test.xml.gz");
            //input.SetPath("Final.pdf.gz");
            //input.SetPath("test.zip");

            quorum.Libraries.System.File output = new quorum.Libraries.System.File();
            output.SetWorkingDirectory("/Users/Nicole/Desktop");
            //file.SetPath("472 Fall.odt.zip");
            output.SetPath("Test.xml");
            // TODO code application logic here
            Decompresser decompress = new Decompresser();
            decompress.DecompressGZip(input, output);

        } catch (Exception ex) {
            Logger.getLogger(Decompresser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
