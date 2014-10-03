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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import quorum.Libraries.Containers.Blueprints.Iterator$Interface;
import quorum.Libraries.Language.Compile.Dependency;
import quorum.Libraries.Language.Object$Interface;
import quorum.Libraries.System.File$Interface;

/**
 *
 * @author Andreas Stefik
 */
public class QuorumJarGenerator {
    public java.lang.Object $me = null;
    private final String ENCODING = "UTF-8";
    private quorum.Libraries.Language.Compile.Compiler quorumCompiler;
    private final String WAR_CLASS_DIRECTORY = "WEB-INF/classes";
    private String buildDirectory = "";
    
    public void WriteNative() {
        quorum.Libraries.Language.Compile.Translate.QuorumJarGenerator generator = 
                (quorum.Libraries.Language.Compile.Translate.QuorumJarGenerator) $me;
        
        quorum.Libraries.Language.Compile.Compiler compiler = 
                (quorum.Libraries.Language.Compile.Compiler) generator.compiler;
        quorumCompiler = compiler;
        
        FileOutputStream stream = null;
        JarOutputStream target = null;
        try {
            String manifestValue = generator.GetManifest();
            Manifest manifest = CreateManifest(manifestValue);
            quorum.Libraries.System.File jarLocation = 
                    (quorum.Libraries.System.File) compiler.GetDistributionFile();
            File writeLocation = new File(jarLocation.GetAbsolutePath());
            if(!writeLocation.exists()) {
                writeLocation.getParentFile().mkdirs();
            }
            
            stream = new FileOutputStream(writeLocation);
            target = new JarOutputStream(stream, manifest);
            File$Interface build = compiler.GetRootFolder();
            buildDirectory = build.GetAbsolutePath();
            Iterator$Interface dependencies = compiler.GetDependencies();
            while(dependencies.HasNext()) {
                Dependency next = (Dependency) dependencies.Next();
                add(new File(buildDirectory + next.from), next, target);
            }
        } catch (Exception ex) {
            Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                target.close();
            } catch (IOException ex) {
                Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
//        if(!this.isGenerateWar()) {
//            writeDependenciesJar();
//        }
    }
    
    private void add(File source, Dependency dep, JarOutputStream target) throws IOException {
        //taken in part from http://stackoverflow.com/questions/1281229/how-to-use-jaroutputstream-to-create-a-jar-file
        BufferedInputStream in = null;
        try {
            if (buildDirectory.compareTo(source.getAbsolutePath()) == 0) {
                for (File nestedFile : source.listFiles()) {
                    add(nestedFile, dep, target);
                }
            } else {
                int pathLength = buildDirectory.length() + dep.from.length();
                String absolute = source.getAbsolutePath();
                
                String relative = absolute.substring(pathLength);
                String name = dep.to.substring(1) + relative.replace("\\", "/");
                if (source.isDirectory()) {
                    if (!name.isEmpty()) {
                        if (!name.endsWith("/")) {
                            name += "/";
                        }
                        
                        if(quorumCompiler.IsWebApplication()) {
                            name = WAR_CLASS_DIRECTORY + "/" + name;
                        }
                        JarEntry entry = new JarEntry(name);
                        entry.setTime(source.lastModified());
                        target.putNextEntry(entry);
                        target.closeEntry();
                    }
                    for (File nestedFile : source.listFiles()) {
                        add(nestedFile, dep, target);
                    }
                    return;
                }

                if(quorumCompiler.IsWebApplication()) {
                    name = WAR_CLASS_DIRECTORY + "/" + name;
                }
                JarEntry entry = new JarEntry(name);
                entry.setTime(source.lastModified());
                target.putNextEntry(entry);
                in = new BufferedInputStream(new FileInputStream(source));

                byte[] buffer = new byte[1024];
                while (true) {
                    int count = in.read(buffer);
                    if (count == -1) {
                        break;
                    }
                    target.write(buffer, 0, count);
                }
                target.closeEntry();
            }
        } finally {
            if (in != null) {
                in.close();
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
            Logger.getLogger(QuorumJarGenerator.class.getName()).log(Level.SEVERE, null, ex);
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
