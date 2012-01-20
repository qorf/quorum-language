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
 * of files. Legal flags are as follows:
 * 
 * -interpret This causes the VM to run in interpreted mode
 * -compile This causes the VM to compile Quorum code to java bytecode. This is the default setting.
 *          Code will not be executed in this mode, only compiled.
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
 * java -jar Quorum.jar -compile -name Main Main.quorum
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
     * This is the name given to the distribution that Quorum will output.
     */
    private static String name = "Default.jar";
    
    /**
     * A state variable for controlling which command line argument
     * is currently being processed.
     */
    private static int argumentIndex = 0;
    
    /**
     * A command line flag telling the computer to interpret the source.
     */
    private static final String INTERPRET = "-interpret";
    
    /**
     * A flag telling the computer to compile down to java bytecode.
     */
    private static final String COMPILE = "-compile";
    
    /**
     * A flag telling the computer to output documentation.
     */
    private static final String DOCUMENT = "-document";
    /**
     * A flag telling the computer the name of the file to distribute. This
     * flag must be followed a string name, with no file extension. 
     * 
     * Example: -name Hello
     * 
     * this would output Hello.jar.
     */
    private static final String NAME = "-name";
    
    /**
     * Adding in this flag causes all others to be ignored and help
     * information is output to the command line.
     */
    private static final String HELP = "-help";
    
    /**
     * If this flag is set, code will be interpreted rather than compiled to JVM bytecode.
     * This is off by default.
     */
    private static boolean isInterpret = false;
    
    /**
     * Whether or not to generate documentation instead of compiling/interpreting. This is off
     * by default.
     */
    private static boolean isDocumentation = false;
    
    
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

        processFlags(args);
        
        // Now we will process remaining flags.
        if(args.length >= 1 ) {
            File[] files = new File[args.length - argumentIndex];
            int fileIndex = 0; // file index is different from command line index.
            for(int i = argumentIndex; i < args.length; i++) {
                String path = args[i];
                path = path.replaceAll("\\%20", " ");
                File next = new File(args[i]);
                files[fileIndex] = next;
            }
            
            //setup the VM
            vm.setGenerateCode(!isInterpret);
            vm.getCodeGenerator().setBuildFolder(build);
            vm.getCodeGenerator().setDistributionFolder(distribution);
            vm.getCodeGenerator().addDependency(phonemic);
            vm.getCodeGenerator().addDependency(phonemicJNI);
            vm.getCodeGenerator().setDistributionName(name);
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
                // Should we execute or build documentation?
                
                if (isDocumentation) {
                    // Set the documentation directory to the distribution folder.
                    vm.setDocumentationPath(root.getAbsolutePath() + "/distribute");
                    vm.generateDocumentation();
                }
                else if (isInterpret) {
                    vm.run();
                }
            }
        }
        else {
            // Tell the user that they need to pass a file to process!
            System.err.println("Please pass the files you would like to compile on the command line. For example, you might type java -jar Quorum.jar Main.quorum.");
            vm.build("say \"Please pass the files you would like to compile on the command line. For example, you might type java -jar Quorum.jar Main.quorum.\"");
            vm.run();
        }
    }
    
    /**
     * Output help to the user. Note that this will cause Quorum to exit.
     */
    private static void outputHelp() {
        System.out.println("\n\n"+
  
                
" .d88888b.\n"+                                                   
"d88P\" \"Y88b\n"+                                                  
"888     888\n"+                                                  
"888     888 888  888  .d88b.  888d888 888  888 88888b.d88b.\n"+  
"888     888 888  888 d88\"\"88b 888P\"   888  888 888 \"888 \"88b\n"+ 
"888 Y8b 888 888  888 888  888 888     888  888 888  888  888\n"+ 
"Y88b.Y8b88P Y88b 888 Y88..88P 888     Y88b 888 888  888  888\n"+ 
" \"Y888888\"   \"Y88888  \"Y88P\"  888      \"Y88888 888  888  888\n"+ 
"       Y8b\n\n\n"+        
                                                                                         

"Below is the command line documentation for the general purpose programming language Quorum:\n\n"+
                
  "java -jar Quorum.jar [-flag]* [file]*\n"+
  
  "In other words, any number of flags beginning with a dash and then any number"+
  "of files. Legal flags are as follows:\n\n"+
  
  "-interpret This causes the VM to run in interpreted mode.\n\n"+
  "-compile This causes the VM to compile Quorum code to java bytecode. This is the default setting.\n"+
  "\tCode will not be run in this mode, only compiled.\n\n"+
  "-document This causes the VM to compile Quorum code normally, but instead of outputting"+
  " bytecode or interpreting the code, it outputs documentation for the code.\n\n"+
                
  "-name [String] This sets the name which is output for the corresponding distribution files."+
  "An example might be -name Music. This would cause Quorum to output a "+
  "jar file by the name of Music.jar into the folder distribute.\n\n"+
                
  "-help This causes help to be output to the command line.\n\n"+
  
  "After entering any flags desired, a list of files indicates to the compiler "+
  "which files to interpret or compile.\n\n "+
  
  "Examples:\n"+
  
  "The following would run one file, Main.quorum, in interpreted mode.\n"+
  "java -jar Quorum.jar -interpret Main.quorum\n\n"+
  
  "The following would compile one file, Main.quorum, down to java bytecode. This "+
  "file would put class files in the build folder and the file Main.jar into the "+
  "distribute folder.\n"+
  "java -jar Quorum.jar -name Main Main.quorum\n\n"+
  
  "As -compiled is the default setting, this does exactly the same thing as the "+
  "previous example.\n"+
  "java -jar Quorum.jar -compile -name Main Main.quorum\n\n"+
  
  "In this example, Quorum will compile Main.quorum and Test.quorum to Java bytecode, "+
  "giving the distribution the name Default.jar.\n"+
  "java -jar Quorum.jar Main.quorum Test.quorum\n"+
  
  "Instead of compiling, this command causes Quorum to output documentation "+
  "in wiki-style format.\n"+
  "java -jar Quorum.jar -document Main.quorum"
       );
 
        System.exit(1); // exit with error.
    }
    
    /**
     * Process command line flags such as -help and -name {name}.
     * 
     * @param args 
     */
    private static void processFlags(String[] args) {
        int index = 0;
        boolean flagsFinished = false;
        while(index < args.length && !flagsFinished) {
            String arg = args[index];
            if(!arg.startsWith("-")) {
                argumentIndex = index;
                return;
            }
            else if(arg.compareTo(HELP) == 0){
                outputHelp();
            }
            else if(arg.compareTo(DOCUMENT) == 0){
                isDocumentation = true;
            }
            else if(arg.compareTo(INTERPRET) == 0){
                isInterpret = true;
            }
            else if(arg.compareTo(COMPILE) == 0){
                isInterpret = false;
            }
            else if(arg.compareTo(NAME) == 0){
                if((index + 1) < args.length) {
                    name = args[(index + 1)];
                    index++;
                }
                else {
                    // The user didn't provide a parameter for the `-name' flag...
                    System.err.println("The `-name' flag requires an argument. For example, -name Hello");
                    outputHelp();
                }
            }
            else {
                // Unrecognized command line argument.
                System.err.println("Unrecognized command line argument: " + arg);
                outputHelp();
            }
            index++;
        }
    }
    
}