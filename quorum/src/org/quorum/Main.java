/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.plugins.DefaultPluginLoader;
import org.quorum.vm.implementation.QuorumStandardLibrary;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.CompilerErrorManager;

/**
 * This is the Main class for running Quorum programs. This class will
 * ultimately allow you to pass command line arguments to Quorum, a series
 * of flags, or other attributes to control how you build source.
 * 
 * @author Andreas Stefik
 */
public class Main {

    private static QuorumVirtualMachine vm;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File root = null;
        try {
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            File file = new File(decodedPath);
            //if we are running this outside of the debugger, go up a level
            //so that we ignore the jar file itself, relative to 
            //the files we are looking for.
            if(decodedPath.endsWith("jar")) {
                file = file.getParentFile();
                decodedPath = file.getAbsolutePath();
            }
            root = file;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(root == null) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                "Could not load Virtual Machine, "
                + "as Quorum's location is invalid.");
        }
        
        File index = new File(root.getAbsolutePath() +
                "/libraries/indexes/quorum.index");
        
        File library = new File(root.getAbsolutePath() +
                "/libraries/quorum");
        File build = new File(root.getAbsolutePath() +
                "/build");
        
        File distribution = new File(root.getAbsolutePath() +
                "/distribute");
        
        File dependencies = new File(root.getAbsolutePath() +
                "/lib");
        
        File phonemic = new File(dependencies.getAbsolutePath() +
                "/phonemic.jar");
        
        File phonemicJNI = new File(dependencies.getAbsolutePath() +
                "/jni");
        
        if(!build.isDirectory()) {
            build.mkdir();
        }
        
        QuorumStandardLibrary.overrideStandardLibraryPath(library, index);
        vm = new QuorumVirtualMachine();
        
        //add the default plugins
        DefaultPluginLoader loader = new DefaultPluginLoader();
        loader.loadIntoVirtualMachine(vm);
        loader.checkConsistency(vm);

        //now parse the command line arguments
        //TODO: this will need to be tweaked for command line arguments
        //and for correct file processing.
        if(args.length >= 1 ) {
            String value = args[0];
            File[] files = new File[args.length];
            
            for(int i = 0; i < args.length; i++) {
                String path = args[i];
                path = path.replaceAll("\\%20", " ");
                File next = new File(root + "/" + args[i]);
                files[i] = next;
            }
            //setup the VM
            vm.setGenerateCode(true);
            vm.getCodeGenerator().setBuildFolder(build);
            vm.getCodeGenerator().setDistributionFolder(distribution);
            vm.getCodeGenerator().addDependency(phonemic);
            vm.getCodeGenerator().addDependency(phonemicJNI);
            vm.setMain(files[0].getAbsolutePath());
            //build
            vm.build(files);
            
            if (!vm.getCompilerErrors().isCompilationErrorFree()) {
                CompilerErrorManager compilerErrors = vm.getCompilerErrors();
                Iterator<CompilerError> errors = compilerErrors.iterator();
                
                while (errors.hasNext()) {
                    System.err.println(errors.next().toString());
                }
            }
            else {
                //execute
                vm.run();
            }
        }
        else {
            vm.build("say \"Hello, world!\"");
            vm.run();
        }
    }
}
