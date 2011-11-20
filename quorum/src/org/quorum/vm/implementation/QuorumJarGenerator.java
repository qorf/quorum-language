/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefika
 */
public class QuorumJarGenerator {
    
    private Manifest manifest;
    private File writeLocation;
    private JarOutputStream jar;
    private String main;
    private final String CREATED_BY = "Quorum 1.0";
    public static int BUFFER_SIZE = 10240;
    public ArrayList<File> files;
    
    public QuorumJarGenerator() {
        files = new ArrayList<File>();
    }
    
    public void writeJarFile() {
        FileOutputStream stream = null;
        {
            JarOutputStream out = null;
            try {
                byte buffer[] = new byte[BUFFER_SIZE];
                stream = new FileOutputStream(writeLocation);
                createNewManifest();
                
                out = new JarOutputStream(stream, manifest);
                
                for(int i = 0; i < this.files.size(); i++) {
                    File file = files.get(i);
                    JarEntry entry = new JarEntry(file.getName());
                    
                    entry.setTime(file.lastModified());
                    out.putNextEntry(entry);

                    // Write file to archive
                    FileInputStream input = new FileInputStream(file);
                    while (true) {
                      int nRead = input.read(buffer, 0, buffer.length);
                      if (nRead <= 0)
                        break;
                      out.write(buffer, 0, nRead);
                    }
                    input.close();
                }
                
                
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    stream.close();
                } catch (IOException ex) {
                    Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }
    
    public void add(File file) {
        files.add(file);
    }

    public void createNewManifest() {
        final String ENCODING = "UTF-8";
        String version = Attributes.Name.MANIFEST_VERSION + ": 1.0\n";
        String created = "Created-By: " + CREATED_BY + "\n";
        String mainClass = Attributes.Name.MAIN_CLASS + ": " + getMain() + "\n";
        String total = version + created + mainClass + "\n";
        try {
            InputStream stream = new ByteArrayInputStream(total.getBytes(ENCODING));
            manifest = new Manifest();
            manifest.read(stream);
        } catch (IOException ex) {
            Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    /**
     * Returns where on disk a jar file will be written.
     * 
     * @return the writeLocation
     */
    public File getWriteLocation() {
        return writeLocation;
    }

    /**
     * Sets where on disk the jar file will be written.
     * 
     * @param writeLocation the writeLocation to set
     */
    public void setWriteLocation(File writeLocation) {
        this.writeLocation = writeLocation;
    }

    /**
     * @return the main
     */
    public String getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(String main) {
        this.main = main;
    }
}
