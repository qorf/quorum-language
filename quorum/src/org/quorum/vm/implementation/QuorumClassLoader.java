/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.vm.interfaces.CodeGenerator;

/**
 * Load a class into the Quorum compiler's JVM memory space.
 * 
 * @author jeff
 */
public class QuorumClassLoader extends ClassLoader {
    private CodeGenerator codeGenerator = null;
    private File pluginFolder = null;
    
    private QuorumSecurityMode securityMode = QuorumSecurityMode.NORMAL;
    private static HashMap<String, String> classesNotAllowed;
    
    static {
        classesNotAllowed = new HashMap<String, String>();
        classesNotAllowed.put("quorum.Libraries.System.File", null);
        classesNotAllowed.put("quorum.Libraries.System.FileRandomAccess", null);
        classesNotAllowed.put("quorum.Libraries.System.FileReader", null);
        classesNotAllowed.put("quorum.Libraries.System.FileWriter", null);
        classesNotAllowed.put("quorum.Libraries.System.Properties", null);
        
        classesNotAllowed.put("quorum.Libraries.Sound.Audio", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Chord", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Instrument", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Music", null);
        classesNotAllowed.put("quorum.Libraries.Sound.MusicEvent", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Note", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Playable", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Speech", null);
        classesNotAllowed.put("quorum.Libraries.Sound.Track", null);
        
        
        //turn off plugins
        classesNotAllowed.put("plugins.quorum.Libraries.System.File", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.FileRandomAccess", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.FileReader", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.FileWriter", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.Properties", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.QuorumFile", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.QuorumFileInterface", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.QuorumFilePlugin", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.QuorumFileRandomAccess", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.QuorumFileReader", null);
        classesNotAllowed.put("plugins.quorum.Libraries.System.QuorumFileWriter", null);
        
        classesNotAllowed.put("plugins.quorum.Libraries.Sound.Audio", null);
        classesNotAllowed.put("plugins.quorum.Libraries.Sound.Music", null);
        classesNotAllowed.put("plugins.quorum.Libraries.Sound.QuorumMusic", null);
        classesNotAllowed.put("plugins.quorum.Libraries.Sound.Speech", null);
    }
    
    
    
    
    
    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        if(this.securityMode == QuorumSecurityMode.WEB) {
            if(classesNotAllowed.containsKey(name)) {
                QuorumSecurityException ex = new QuorumSecurityException("Class " + name + " cannot be used from "
                        + "the web version of Quorum.");
                ex.setClassName(name);
                throw ex;
            }
        }
        byte[] bytes = null;
        if (name.startsWith("quorum.")) {
            bytes = codeGenerator.load(name);

        } else if (name.startsWith("plugins.quorum.")) {
            try {
                // Strip out the first '.', to remove the plugins prefix.
                String quorumName = name.substring(name.indexOf(".") + 1);
                bytes = getBytesFromClassFile(new File(pluginFolder, quorumName.replace('.', File.separatorChar) + ".class"));
            } catch (IOException ex) {
                // TODO: Report this error in a friendly way.
                Logger.getLogger(QuorumClassLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Did we load the bytes successfully?
        if (bytes != null) {
            return classFromBytecode(name, bytes);
        } else {
            // No... ask the parent classloader (couldn't be resolved as a quorum class).
            return super.loadClass(name);
        }
    }
    
    /**
     * Load the given class given a name and a list of bytecodes.
     * 
     * @param name
     * @param bytecode
     * @return 
     */
    public Class classFromBytecode(String name, byte[] bytecode) {
        return defineClass(name, bytecode, 0, bytecode.length);
    }
    
    // Returns the contents of a file in a byte array.
    private byte[] getBytesFromClassFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    /**
     * @return the codeGenerator
     */
    public CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }

    /**
     * @param codeGenerator the codeGenerator to set
     */
    public void setCodeGenerator(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    /**
     * @return the pluginFolder
     */
    public File getPluginFolder() {
        return pluginFolder;
    }

    /**
     * @param pluginFolder the pluginFolder to set
     */
    public void setPluginFolder(File pluginFolder) {
        this.pluginFolder = pluginFolder;
    }

    /**
     * @return the securityMode
     */
    public QuorumSecurityMode getSecurityMode() {
        return securityMode;
    }

    /**
     * @param securityMode the securityMode to set
     */
    public void setSecurityMode(QuorumSecurityMode securityMode) {
        this.securityMode = securityMode;
    }
}
