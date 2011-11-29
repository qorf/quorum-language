/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.objectweb.asm.ClassWriter;

import org.quorum.execution.Linker;
import org.quorum.steps.ClassExecution;
import org.quorum.steps.ContainerExecution;
import org.quorum.steps.IntermediateExecutionBuilder;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.vm.interfaces.CodeGenerator;

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
    private List<File> dependencies = new LinkedList<File>();
    
    /**
     * This method generates java bytecode for all classes on the system.
     * 
     */
    @Override
    public void generate() {
        Iterator<ContainerExecution> containers = builder.getContainers();
        while (containers.hasNext()) {
            ContainerExecution exe = containers.next();
            generate(exe);
        }
    }
    
    private void generate(ContainerExecution container) {
        Iterator<ClassExecution> classes = container.getClasses();
        ClassDescriptor currentClass = null;
        while (classes.hasNext()) {
            try {
                ClassExecution clazz = classes.next();
                currentClass = clazz.getClassDescriptor();
                
                //grab the file and check to see if the path matches the main file
                String path = currentClass.getFile().getFile().getAbsolutePath();
                String mainPath = mainFile.getAbsolutePath();
                
                //if this is true, it is the main class
                //as such, mark it for the manifest.
                //This assumes only one class per file. If this assumption
                //changes, then this will no longer work.
                if(path.compareTo(mainPath) == 0) {
                    String staticKey = currentClass.getStaticKey();
                    staticKey = QuorumConverter.convertStaticKeyToManifestPath(staticKey);
                    manifestMain = staticKey;
                }
                
                QuorumBytecode code = generate(clazz);
                classHash.put(code.getStaticKey(), code);
            }
            catch(Exception e) {
                System.out.println("Exception in class: " + currentClass.getStaticKey());
                e.printStackTrace();
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
     * bytecode to disk.
     */
    @Override
    public void writeToDisk() throws IOException {
        if(classHash == null) {
            return;
        }
        
        if(buildFolder == null || !buildFolder.isDirectory()) {
            throw new FileNotFoundException("Cannot write to a build folder that does not exist.");
        }
        
        //prepare jar file for output
        QuorumJarGenerator jar = new QuorumJarGenerator();
        
        Iterator<QuorumBytecode> iterator = classHash.values().iterator();
        while(iterator.hasNext()) {
            QuorumBytecode code = iterator.next();
            prepareFolder(code);
            writeBytes(code.getClassFile(), code.getOutput());
            writeBytes(code.getInterfaceFile(), code.getInterfaceOutput());
        }
        
        //if there's a place to write the jar, write it
        if(this.distributionFolder != null) {
            //say where to write the jar
            this.distributionFolder.mkdirs();
            String jarName = this.distributionFolder.getAbsolutePath();
            jarName = jarName + "/" + this.distributionName + ".jar";
            File loc = new File(jarName);
            jar.setWriteLocation(loc);
            
            //setup the manifest
            //set the main file
            jar.setMain(this.manifestMain);
            
            //TODO: setup any dependencies
                        
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
        dependencies.add(file);
    }

    @Override
    public Iterator<File> getDependencies() {
        return dependencies.iterator();
    }
    
    @Override
    public void clearDependencies() {
        dependencies.clear();
    }
    
    @Override
    public int getNumberOfDependencies() {
        return dependencies.size();
    }
}
