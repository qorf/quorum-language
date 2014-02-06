/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import org.quorum.vm.interfaces.Dependency;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.ClassWriter;

import org.quorum.execution.Linker;
import org.quorum.steps.ClassExecution;
import org.quorum.steps.ContainerExecution;
import org.quorum.steps.IntermediateExecutionBuilder;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.vm.interfaces.CodeGenerator;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.ErrorType;

/**
 * This Code Generator creates java bytecode and appropriate executable jar
 * files from Quorum code.
 * 
 * @author Andreas Stefik
 */
public class QuorumBytecodeGenerator implements CodeGenerator {
    
    private Linker linker;
    private IntermediateExecutionBuilder builder;
    private HashMap<String, QuorumBytecode> classHash = new HashMap<String, QuorumBytecode>();
    private File buildFolder;
    private File distributionFolder;
    private String distributionName = "Default";
    private File mainFile;
    private String manifestMain = "";
    private List<Dependency> dependencies = new LinkedList<Dependency>();
    private File pluginFolder = null;
    
    /**
     * Stores all plugins passed into the compiler by the user, outside
     * the compiler plugin folder.
     */
    private HashMap<String, File> plugins = new HashMap<String, File>();
    private static final Logger logger = Logger.getLogger(QuorumBytecodeGenerator.class.getName());
    private String mainClassName = "";
    private boolean compileToDisk = true;
    private boolean hasInput = false;
    private boolean foundMain = false;
    
    /**
     * Set this flag to true if the generator should create .war files instead
     * of .jar files.
     */
    private boolean generateWar = false;
    
    /**
     * This method generates java bytecode for all classes on the system.
     * 
     */
    @Override
    public void generate() {
        classHash.clear();
        Iterator<ContainerExecution> containers = builder.getContainers();
        hasInput = builder.hasInput();
        while (containers.hasNext()) {
            ContainerExecution exe = containers.next();
            generate(exe);
        }
        
        if (!foundMain) {
            CompilerError error = new CompilerError();
            error.setColumn(1);
            error.setLineNumber(1);
            error.setError("Cannot start the program, as no main action is defined.");
            error.setErrorType(ErrorType.MISSING_MAIN);
            builder.getVirtualMachine().getCompilerErrors().setErrorKey(mainFile.getAbsolutePath());
            error.setFile(builder.getVirtualMachine().getMain());
            builder.getVirtualMachine().getCompilerErrors().addError(error);
        }
    }
    
    private void generate(ContainerExecution container) {
        Iterator<ClassExecution> classes = container.getClasses();
        ClassDescriptor currentClass = null;
        while (classes.hasNext()) {
            try {
                ClassExecution clazz = classes.next();
                clazz.setHasInput(hasInput);
                currentClass = clazz.getClassDescriptor();
                
                //grab the file and check to see if the path matches the main file
                String path = currentClass.getFile().getFile().getAbsolutePath();
                String mainPath = mainFile.getAbsolutePath();
                                
                QuorumBytecode code = generate(clazz);
                classHash.put(code.getStaticKey(), code);
                
                //if this is true, it is the main class
                //as such, mark it for the manifest.
                //This assumes only one class per file. If this assumption
                //changes, then this will no longer work.
                if(path.compareTo(mainPath) == 0) {
                    String staticKey = currentClass.getStaticKey();
                    mainClassName = QuorumConverter.convertStaticKeyToBytecodePath(staticKey);

                    staticKey = QuorumConverter.convertStaticKeyToManifestPath(staticKey);
                    manifestMain = staticKey;
                    
                    if(currentClass.getMethod("Main") != null || currentClass.getMethod("main") != null){
                        foundMain = true;
                    }
                }
            }
            catch(Exception e) {
                logger.log(Level.SEVERE, "The Quorum bytecode generator threw an error.", e);
                throw new RuntimeException("The Quorum bytecode generator threw an error.");
            }
        }
    }
    
    private QuorumBytecode generate(ClassExecution clazz) {
        QuorumBytecode code = new QuorumBytecode();
        code.setStaticKey(clazz.getStaticKey());
        QuorumJavaBytecodeStepVisitor visitor = new QuorumJavaBytecodeStepVisitor();
        visitor.visit(clazz);
        ClassWriter classWriter = visitor.getClassWriter();
        ClassWriter interfaceWriter = visitor.getInterfaceWriter();
        
        //get the final bytecode for the name mangled interface
        byte[] interfaceBytes = interfaceWriter.toByteArray();
        code.setInterfaceOutput(interfaceBytes);
        
        //get the final bytecode and hash it away.
        byte[] b = classWriter.toByteArray();
        code.setOutput(b);
        return code;
    }

    /**
     * This method flushes all of the bytecode arrays for the generated
     * bytecode to disk. If disk compilation is turned off, this method
     * does nothing.
     */
    @Override
    public void writeToDisk() throws IOException {
        if (!compileToDisk)
            return;
        
        if(classHash == null) {
            return;
        }
        
        if(buildFolder == null) {
            logger.log(Level.INFO, "The build directory is invalid or not properly set.");
            return;
        }
        else if(!buildFolder.exists()) {
            buildFolder.mkdirs();
        }
        
        //prepare jar file for output
        QuorumJarGenerator jar = new QuorumJarGenerator();
        jar.setGenerateWar(this.isGenerateWar());
        
        Iterator<QuorumBytecode> iterator = classHash.values().iterator();
        while(iterator.hasNext()) {
            QuorumBytecode code = iterator.next();
            prepareFolder(code);
            writeBytes(code.getClassFile(), code.getOutput());
            writeBytes(code.getInterfaceFile(), code.getInterfaceOutput());
        }
        //once the class files are written, copy the plugin folder to the
        //build location
        //File buildParent = this.buildFolder.getParentFile();
        File pluginWrite = new File(buildFolder.getPath() + "/plugins");
        jar.copyFile(this.pluginFolder, pluginWrite);
        
        //if there's a place to write the jar, write it
        if(this.distributionFolder != null) {
            //say where to write the jar
            this.distributionFolder.mkdirs();
            String jarName = this.distributionFolder.getAbsolutePath();
            jarName = jarName + "/" + this.distributionName + jar.getFileExtension();
            File loc = new File(jarName);
            jar.setWriteLocation(loc);
            
            //setup the manifest
            //set the main file
            jar.setMain(this.manifestMain);
            
            //TODO: setup any dependencies
            jar.setDependencies(dependencies);            
            
            //write the jar to disk
            jar.writeJarFile(this.buildFolder);
        }
    }
    
    private void prepareFolder(QuorumBytecode code) throws IOException {
        File file;
        File interfaceFile;
        String path = QuorumConverter.convertStaticKeyToBytecodePath(code.getStaticKey());
        String interfacePath = QuorumConverter.convertClassNameToInterfaceName(path);
        
        
        String fullPath = buildFolder + "/" + path + ".class";
        String interfaceFullPath = buildFolder + "/" + interfacePath + ".class";
        String[] split = fullPath.split("/");
        
        String valueWithoutName = "";        
        for(int i = 0; i < split.length - 1; i++) {
            valueWithoutName += split[i] + "/";
        }
        valueWithoutName = valueWithoutName.substring(0, valueWithoutName.length() - 1);
        
        File dirs = new File(valueWithoutName);
        boolean mkdirs = dirs.mkdirs();
        
        file = new File(fullPath);
        interfaceFile = new File(interfaceFullPath);
        if(file.isFile()) {
            //delete it and remake it
            file.delete();
            file.createNewFile();
        }
        
        if(interfaceFile.isFile()) {
            interfaceFile.delete();
            interfaceFile.createNewFile();
        }
        
        code.setClassFile(file);
        code.setInterfaceFile(interfaceFile);       
    }
    
    
    
    private void writeBytes(File file, byte[] bites) throws FileNotFoundException, IOException {
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(bites);
        stream.flush();
        stream.close();        
    }
    
    
    /**
     * This method cleans out the hash of generated bytecode.
     */
    public void clean() {
        linker = null;
        builder = null;
        classHash = new HashMap<String, QuorumBytecode>();
    }
    
    /**
     * @return the linker
     */
    public Linker getLinker() {
        return linker;
    }

    /**
     * @param linker the linker to set
     */
    public void setLinker(Linker linker) {
        this.linker = linker;
    }

    /**
     * @return the builder
     */
    public IntermediateExecutionBuilder getBuilder() {
        return builder;
    }

    /**
     * @param builder the builder to set
     */
    public void setBuilder(IntermediateExecutionBuilder builder) {
        this.builder = builder;
    }

    /**
     * @return the buildFolder
     */
    @Override
    public File getBuildFolder() {
        return buildFolder;
    }

    /**
     * @param buildFolder the buildFolder to set
     */
    @Override
    public void setBuildFolder(File buildFolder) {
        this.buildFolder = buildFolder;
        if(!buildFolder.exists()) {
            buildFolder.mkdirs();
        }
    }

    @Override
    public File getDistributionFolder() {
        return this.distributionFolder;
    }

    @Override
    public void setDistributionFolder(File distributionFolder) {
        this.distributionFolder = distributionFolder;
    }

    @Override
    public String getDistributionName() {
        return this.distributionName;
    }

    @Override
    public void setDistributionName(String name) {
        this.distributionName = name;
    }

    @Override
    public File getMainFile() {
        return mainFile;
    }

    @Override
    public void setMainFile(File file) {
        mainFile = file;
    }

    @Override
    public void addDependency(File file) {
        Dependency dep = new Dependency();
        dep.setFile(file);
        dependencies.add(dep);
    }
    
    @Override
    public void addPlugin(File file) {
        plugins.put(file.getAbsolutePath(), file);
    }

    @Override
    public void clearPlugins() {
        plugins.clear();
    }
    
    @Override
    public Iterator<Dependency> getDependencies() {
        return dependencies.iterator();
    }
    
    @Override
    public void clearDependencies() {
        dependencies.clear();
        plugins.clear();
    }
    
    @Override
    public int getNumberOfDependencies() {
        return dependencies.size();
    }

    @Override
    public void addDependency(File file, String relativePath) {
        Dependency dep = new Dependency();
        dep.setFile(file);
        dep.setRelativePath(relativePath);
        dep.setExecutionDependency(false);
        dependencies.add(dep);
    }
    
    @Override
    public void setPluginFolder(File file) {
        pluginFolder = file;
    }

    @Override
    public File getPluginFolder() {
        return pluginFolder;
    }
    
    @Override
    public String getMainClassName() {
            return mainClassName;
    }

    @Override
    public void setCompileToDisk(boolean compileToDisk) {
        this.compileToDisk = compileToDisk;
    }

    @Override
    public boolean getCompileToDisk() {
        return compileToDisk;
    }

    @Override
    public byte[] load(String name) {
        // Strip out 'quorum.'.
        String quorumName = name.substring(name.indexOf('.') + 1);

        // Does the quorumName contain a '.'? If not, prepend one.
        if (!quorumName.contains(".")) {
            quorumName = "." + quorumName;
        }
            
        // Strip out "$Interface", if present.
        if (quorumName.contains("$Interface"))
            quorumName = quorumName.substring(0, quorumName.indexOf("$Interface"));
        
        QuorumBytecode bytecode = classHash.get(quorumName);
        if (bytecode != null)
            if (name.contains("$Interface"))
                return bytecode.getInterfaceOutput();
            else
                return bytecode.getOutput();
        else
            return null;
    }
    
    
    /**
     * 
     * Returns true if a .war file will be generated instead of a .jar file.
     * 
     * @return the generateWar
     */
    public boolean isGenerateWar() {
        return generateWar;
    }

    /**
     * 
     * Set this to true if the Jar generator should generate .war files instead.
     * This is useful if you want Quorum to output files that can be uploaded
     * to a Tomcat server.
     * 
     * @param generateWar the generateWar to set
     */
    public void setGenerateWar(boolean generateWar) {
        this.generateWar = generateWar;
    }
}
