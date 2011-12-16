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
 * 
 * Command line arguments are as follows:
 * 
 * java -jar Quorum.jar [-flag]* [file]*
 * 
 * In other words, any number of flags beginning with a dash and then any number
 * of files. Legal flags are
 * 
 * -interpret This causes the VM to run in interpreted mode
 * -compiled This causes the VM to compile Quorum code to java bytecode. This is the default setting.
 * -document This causes the VM to compile Quorum code normally, but instead of outputting
 *          bytecode or interpreting the code, it outputs documentation for the code.
 * -name [String] This sets the name which is output for the corresponding distribution files.
 *                  An example might be -name Music. This would cause Quorum to output a 
 *                  jar file by the name of Music.jar into the folder distribute.
 * -help This causes help to be output to the command line.
 * 
 * After entering any flags desired, a list of files indicates to the compiler
 * which files to interpret or compile. 
 * 
 * Examples:
 * 
 * The following would run one file, Main.quorum, in interpreted mode.
 * java -jar Quorum.jar -interpret Main.quorum
 * 
 * The following would compile one file, Main.quorum, down to java bytecode. This
 * file would put class files in the build folder and the file Main.jar into the 
 * distribute folder.
 * java -jar Quorum.jar -name Main Main.quorum
 * 
 * As -compiled is the default setting, this does exactly the same thing as the 
 * previous example.
 * java -jar Quorum.jar -compiled -name Main Main.quorum
 * 
 * This also does the same thing as the previous example, except since no 
 * -name flag is passed, the distribute folder will contain a file called 
 * 
 * In this example, Quorum will compile Main.quorum and Test.quorum to Java bytecode,
 * giving the distribution the name Default.jar.
 * java -jar Quorum.jar Main.quorum Test.quorum
 * 
 * Instead of compiling, this command causes Quorum to output documentation
 * in wiki-style format.
 * java -jar Quorum.jar -document Main.quorum
 * 
 * @author Andreas Stefik
 */
public class Main {
    /**
     * This is the virtual machine that compiles Quorum programs.
     */
    private static QuorumVirtualMachine vm;
    
    /**
     * If this flag is true, any files passed should be compiled internally
     * and executed in interpreted mode.
     */
    private static boolean interpret = false;
    
    /**
     * This is the name given to the distribution that Quorum will output.
     */
    private static String name = "Default.jar";
    
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
            vm.build("say \"Please pass the files you would to compile on the command line. For example, you might type java -jar Quorum.jar Main.quorum.\"");
            vm.run();
        }
    }
}
