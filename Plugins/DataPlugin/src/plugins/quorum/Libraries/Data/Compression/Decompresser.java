/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Data.Compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;
import quorum.Libraries.System.File_;
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
    
    private void write(String outFilePath, InputStream inputStream) {
        try {
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
