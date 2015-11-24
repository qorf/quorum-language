/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Compile.Translate;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.System.File_;

/**
 *
 * @author Andreas Stefik
 */
public class JarGenerator {

    public java.lang.Object me_ = null;
    private final String ENCODING = "UTF-8";
    //private final String WAR_CLASS_DIRECTORY = "WEB-INF/classes";
    FileOutputStream stream = null;
    JarOutputStream target = null;

    
    public void Open(quorum.Libraries.System.File_ location) {
        try {
            quorum.Libraries.Language.Compile.Translate.JarGenerator generator
                    = (quorum.Libraries.Language.Compile.Translate.JarGenerator) me_;
            String manifestValue = generator.GetManifest();
            Manifest manifest = CreateManifest(manifestValue);
            File writeLocation = new File(location.GetAbsolutePath());
            if (!writeLocation.exists()) {
                writeLocation.getParentFile().mkdirs();
            }
            
            stream = new FileOutputStream(writeLocation);
            target = new JarOutputStream(stream, manifest);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Close() {
        try {
            target.close();
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stream.close();
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Add(File_ source) {
        BufferedInputStream in = null;
        try {
            String name = source.GetPath().replace("\\", "/");
            
            if(source.IsDirectory() && !name.endsWith("/")) {
                name = name + "/";
            }
            JarEntry entry = new JarEntry(name);
            entry.setTime((long) source.GetLastModifiedNative());
            target.putNextEntry(entry);
            
            if(!source.IsDirectory()) {
                File file = new File(source.GetAbsolutePath());
                in = new BufferedInputStream(new FileInputStream(file));

                byte[] buffer = new byte[1024];
                while (true) {
                    int count = in.read(buffer);
                    if (count == -1) {
                        break;
                    }
                    target.write(buffer, 0, count);
                }
            }
            target.closeEntry();
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void Add(File_ source, String path) {
        BufferedInputStream in = null;
        try {
            String name = path.replace("\\", "/");
            
            if(source.IsDirectory() && !name.endsWith("/")) {
                name = name + "/";
            }
            JarEntry entry = new JarEntry(name);
            entry.setTime((long) source.GetLastModifiedNative());
            target.putNextEntry(entry);
            
            if(!source.IsDirectory()) {
                File file = new File(source.GetAbsolutePath());
                in = new BufferedInputStream(new FileInputStream(file));

                byte[] buffer = new byte[1024];
                while (true) {
                    int count = in.read(buffer);
                    if (count == -1) {
                        break;
                    }
                    target.write(buffer, 0, count);
                }
            }
            target.closeEntry();
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Manifest CreateManifest(String total) {
        try {
            InputStream stream = new ByteArrayInputStream(total.getBytes(ENCODING));
            Manifest manifest = new Manifest();
            manifest.read(stream);
            return manifest;
        } catch (IOException ex) {
            Logger.getLogger(JarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String GetManifestVersion() {
        return Attributes.Name.MANIFEST_VERSION.toString();
    }

    public String GetManifestMainClass() {
        return Attributes.Name.MAIN_CLASS.toString();
    }

    public String GetManifestClassPath() {
        return Attributes.Name.CLASS_PATH.toString();
    }
}
