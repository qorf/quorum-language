/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.plugins.DefaultPluginLoader;
import org.quorum.vm.implementation.QuorumClassLoader;
import org.quorum.vm.implementation.QuorumJarGenerator;
import org.quorum.vm.implementation.QuorumStandardLibrary;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CodeGenerator;
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
 * quorum [-flag]* [file]*
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
 * -plugins [Path] This sets an absolute path for the location of plugins to 
 *                  override the location used by Quorum. By default, if this flag is not
 *                  set, it is set to a path relative to Quorum.jar/libraries/plugins.
 * -help This causes help to be output to the command line.
 * 
 * After entering any flags desired, a list of files indicates to the compiler
 * which files to interpret or compile. 
 * 
 * Examples:
 * 
 * The following would run one file, Main.quorum, in interpreted mode.
 * quorum -interpret Main.quorum
 * 
 * The following would compile one file, Main.quorum, down to java bytecode. This
 * file would put class files in the build folder and the file Main.jar into the 
 * distribute folder.
 * quorum -name Main Main.quorum
 * 
 * The following would compile one file, Main.quorum, down to java bytecode. This
 * file would put class files in the build folder and the file Main.jar into the 
 * distribute folder. It would also change the plugin folder on windows.
 * quorum -name Main -plugins C:\plugins Main.quorum
 * 
 * As -compiled is the default setting, this does exactly the same thing as the 
 * previous example.
 * quorum -compile -name Main Main.quorum
 * 
 * In this example, Quorum will compile Main.quorum and Test.quorum to Java bytecode,
 * giving the distribution the name Default.jar.
 * quorum Main.quorum Test.quorum
 * 
 * Instead of compiling, this command causes Quorum to output documentation
 * in wiki-style format.
 * quorum -document Main.quorum
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
    private static String name = "Default";
    
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
     * This flag allows the user to override the folder containing quorum
     * plugins.
     */
    private static final String PLUGINS = "-plugins";
    
    /**
     * This is the default relative path for the plugins directory relative
     * to Quorum.jar.
     */
    public static final String DEFAULT_PLUGIN_PATH = "libraries/plugins";
    
    /**
     * This is an absolute path indicating the location of the plugin directory.
     */
    private static File pluginFolder = null;
    
    /**
     * This flags tracks where the default plugin folder has been overriden.
     */
    private static boolean pluginOverride = false;
    
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
     * A folder for the distributions to be placed.
     */
    private static final String DISTRIBUTE_DIRECTORY = "Run";
    
    /**
     * A folder for build information, like classes and plugins.
     */
    private static final String BUILD_DIRECTORY = "Build";
    
    /**
     * A folder for documentation to be generated into.
     */
    private static final String DOCUMENTATION_DIRECTORY = "Documentation";
    
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
        
        pluginFolder = new File(root.getAbsolutePath() + "/" + 
                DEFAULT_PLUGIN_PATH);
        
        File library = new File(root.getAbsolutePath() +
                "/libraries/quorum");
        
        // The "build" directory should be in the current directory.
        File build = new File("./" + BUILD_DIRECTORY);
        
        File distribution = new File("./" + DISTRIBUTE_DIRECTORY);
        
        File dependencies = new File(root.getAbsolutePath() +
                "/lib");
        
        File phonemic = new File(dependencies.getAbsolutePath() +
                "/phonemic.jar");
        
        File phonemicJNI = new File(dependencies.getAbsolutePath() +
                "/jni");
        
        File documentation = new File(DOCUMENTATION_DIRECTORY + "/");
        
        if(!build.isDirectory()) {
            build.mkdir();
        }
        
        if(!documentation.isDirectory()) {
            documentation.mkdirs();
        }
        
        QuorumStandardLibrary.overrideStandardLibraryPath(library, index);
        vm = new QuorumVirtualMachine();
        
        //add the default plugins into the interpreter
        //Note: This plugin loader is legacy code, with a misleading name
        //While it is called plugins here, it has nothing to do with the 
        //-plugins flag and is only used for the interpreted omniscient 
        //debugger.
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
                
                // If compiling, show the user some information.
                if (!isInterpret)
                    System.out.println("Preparing to build " + files[fileIndex]);
            }
            
            //setup the VM
            vm.setGenerateCode(true);
            vm.setBuildFolder(build);
            vm.setDistributionFolder(distribution);
            vm.addDependency(phonemic);
            vm.addDependency(phonemicJNI);
            vm.setDistributionName(name);
            vm.setPluginFolder(pluginFolder);
            vm.setMain(files[0].getAbsolutePath());
            //build
            
            // If compling or documenting, let the user know we're building.
            if (!isInterpret) {
                System.out.print("\nBuilding files...");
                vm.build(files, true);
                System.out.println(" done.");
            } //but if we're interpreting, don't bother the user
            else {
                vm.build(files, true);
            }
            
            if (!vm.getCompilerErrors().isCompilationErrorFree()) {
                System.out.println("The code did not build correctly. Here is a list of errors:");
                CompilerErrorManager compilerErrors = vm.getCompilerErrors();
                Iterator<CompilerError> errors = compilerErrors.iterator();
                
                int i = 1;
                while (errors.hasNext()) {
                    System.err.println(i + ": " + errors.next().toString());
                }
            }
            else {
                // Should we execute, build the documentation, or clean up?
                if (isDocumentation) {
                    // Set the documentation directory to the distribution folder.
                    vm.setDocumentationPath(documentation.getAbsolutePath());
                    
                    System.out.print("Generating documentation...");
                    vm.generateAllDocumentation(files);
                    System.out.println(" done.");
                    
                    //copy over the CSS file
                    File css = new File(root.getAbsolutePath() + "/libraries/style.css");
                    File destination = new File(documentation.getAbsolutePath() + "/style.css");
                    try {
                        QuorumJarGenerator gen = new QuorumJarGenerator();
                        gen.copyFile(css, destination);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.out.println("Documentation placed in the folder: " + documentation.getAbsolutePath());
                }
                else if (isInterpret) {
                    // Generate code without dumping to disk.
                    CodeGenerator g = vm.getCodeGenerator();
                    g.setCompileToDisk(false);
                    vm.build(files, true);
                    
                    // Load the main quorum class and invoke the static main(String[] args) method with no arguments.
                    String mainClassName = g.getMainClassName();
                    QuorumClassLoader classLoader = new QuorumClassLoader();
                    classLoader.setCodeGenerator(g);
                    classLoader.setPluginFolder(pluginFolder);
                    Class<?> quorumClass = null;
                    try {
                        quorumClass = classLoader.loadClass(mainClassName.replaceAll("/", "."));
                        Method declaredMethod = quorumClass.getDeclaredMethod("main", new Class[]{String[].class});
                        String[] programArguments = {}; // TODO: In the future, this can be used to pass arguments to a program running in interpreted mode.
                        declaredMethod.invoke(null, (Object)programArguments);
                    } catch (Exception e) {
                        // TODO: Handle this in a more friendly manner.
                        System.err.println("Unable to run interpreter: " + e.getMessage());
                    }
                }
                else {
                    // Not generating documentation or running, so we're compiling.
                    System.out.println("Build completed successfully.\n");

                    // Tell the user how to run their program.
                    System.out.println("To run your program, type:\n");
                    
                    // Show appropriate pathing.
                    String pathSep = "/";
                    if (System.getProperty("os.name").contains("Windows"))
                        pathSep = "\\";
                    String fullPath = "" + DISTRIBUTE_DIRECTORY + pathSep + name + ".jar";
                    
                    System.out.println("java -jar " + fullPath + "\n");
                    System.out.println("into this command prompt.");
                    
                    // If they used the default name, let them know that they
                    // can change it.
                    if (name.equals("Default")) {
                        System.out.println("(Note that if you want to name your file something other than `Default.jar', " +
                                "you can do so using the `-name' flag. Type `quorum -help' for details.)");
                    }
                }
            }
        }
        else {
            // Tell the user that they need to pass a file to process!
            System.err.println("Please pass the files you would like to compile on the command line. For example, you might type quorum Main.quorum.");
            vm.build("say \"Please pass the files you would like to compile on the command line. For example, you might type quorum Main.quorum.\"");
            vm.run();
        }
        System.exit(0);
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
                
  "quorum [-flag]* [file]*\n"+
  
  "In other words, any number of flags beginning with a dash and then any number"+
  "of files. Legal flags are as follows:\n\n"+
  
  "-interpret This causes the VM to run in interpreted mode.\n\n"+
  "-compile This causes the VM to compile Quorum code to java bytecode. This is the default setting.\n"+
  "\tCode will not be run in this mode, only compiled.\n\n"+
  "-document This causes the VM to compile Quorum code normally, but instead of outputting"+
  " bytecode or interpreting the code, it outputs documentation for the code. This feature is currently"+
  " in the experimental stage and only outputs `wiki' formatted documentation. In the future, HTML output is" +
  " planned.\n\n"+
                
  "-name [String] This sets the name which is output for the corresponding distribution files."+
  "An example might be -name Music. This would cause Quorum to output a "+
  "jar file by the name of Music.jar into the folder distribute.\n\n"+
                
  "-plugins [Path] This sets an absolute path for the location of plugins to "
  + "override the location used by Quorum. By default, if this flag is not"
  + "set, it is set to a path relative to Quorum.jar/libraries/plugins.\n\n"+
                
  "-help This causes help to be output to the command line.\n\n"+
  
  "After entering any flags desired, a list of files indicates to the compiler "+
  "which files to interpret or compile.\n\n "+
  
  "Examples:\n"+
  
  "The following would run one file, Main.quorum, in interpreted mode.\n"+
  "quorum -interpret Main.quorum\n\n"+
  
  "The following would compile one file, Main.quorum, down to java bytecode. This "+
  "file would put class files in the build folder and the file Main.jar into the "+
  "distribute folder.\n"+
  "quorum -name Main Main.quorum\n\n"+
  
  "The following would compile one file, Main.quorum, down to java bytecode. This "+
  "file would put class files in the build folder and the file Main.jar into the "+ 
  "distribute folder. It would also change the plugin folder on windows.\n "+
  "quorum -name Main -plugins C:\\plugins Main.quorum.\n\n"+
        
  "As -compiled is the default setting, this does exactly the same thing as the "+
  "previous example.\n"+
  "quorum -compile -name Main Main.quorum\n\n"+
  
  "In this example, Quorum will compile Main.quorum and Test.quorum to Java bytecode, "+
  "giving the distribution the name Default.jar.\n"+
  "quorum Main.quorum Test.quorum\n"+
  
  "Instead of compiling, this command causes Quorum to output documentation "+
  "in wiki-style format.\n"+
  "quorum -document Main.quorum"
       );
 
        System.exit(0); // exit the system
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
            else if(arg.compareTo(PLUGINS) == 0){
                String abs;
                if((index + 1) < args.length) {
                    abs = args[(index + 1)];
                    
                    //is this a valid folder?
                    File path = new File(abs);
                    if(path.exists() && path.isDirectory()) {
                        //it's valid, keep track of it
                        pluginOverride = true;
                        pluginFolder = path;
                    }
                    else {
                        System.err.println("The path passed to -plugins is either "
                                + "an invalid directory or does not exist.");
                    }
                    index++;
                }
                else {
                    // The user didn't provide a parameter for the `-name' flag...
                    System.err.println("The `-plugins' flag requires an argument. For example, -plugins C:\\plugins");
                    //outputHelp();
                }
            }
            else if(arg.compareTo(NAME) == 0){
                if((index + 1) < args.length) {
                    name = args[(index + 1)];
                    index++;
                }
                else {
                    // The user didn't provide a parameter for the `-name' flag...
                    System.err.println("The `-name' flag requires an argument. For example, -name Hello");
                    //outputHelp();
                }
            }
            else {
                // Unrecognized command line argument.
                System.err.println("Unrecognized command line argument: " + arg);
                //outputHelp();
            }
            index++;
        }
    }
}