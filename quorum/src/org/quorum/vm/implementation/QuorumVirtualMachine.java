/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.quorum.documentation.DocumentationGenerator;
import org.quorum.documentation.DocumentationStyle;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.Execution;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.Linker;
import org.quorum.execution.MainMethod;
import org.quorum.execution.RuntimeObject;
import org.quorum.execution.TimeStamp;
import org.quorum.parser.QuorumLexer;
import org.quorum.parser.QuorumParser;
import org.quorum.parser.QuorumParser.start_return;
import org.quorum.parser.QuorumTreeWalker;
import org.quorum.steps.ClassExecution;
import org.quorum.steps.ContainerExecution;
import org.quorum.steps.IntermediateExecutionBuilder;
import org.quorum.steps.NullIntermediateStep;
import org.quorum.steps.ObjectInitPopStep;
import org.quorum.symbols.AccessModifierEnum;
import org.quorum.symbols.BlockDescriptor;
import org.quorum.symbols.BlueprintDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.Documentation;
import org.quorum.symbols.FileDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.ParameterDescriptor;
import org.quorum.symbols.SymbolTable;
import org.quorum.symbols.SystemActionDescriptor;
import org.quorum.symbols.TypeChecker;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.CodeCompletionItem;
import org.quorum.vm.interfaces.CodeCompletionRequest;
import org.quorum.vm.interfaces.CodeCompletionResult;
import org.quorum.vm.interfaces.CodeCompletionType;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.ErrorType;
import org.quorum.vm.interfaces.LibraryIndexEntry;
import org.quorum.vm.interfaces.VariableWatch;
import org.quorum.vm.interfaces.VirtualMachineEvent;

/**
 * This virtual machine implements the Quorum Programming language.
 *
 * @author Andreas Stefik
 */
public class QuorumVirtualMachine extends AbstractVirtualMachine {
    private boolean parsed = false;
    private boolean buildAllEvent = false;
    private File currentFile;
    private IntermediateExecutionBuilder builder;
    private HashMap<String, QuorumFile> parseHash;
    private CompilerErrorManager compilerErrors;
    private DocumentationGenerator documentor = DocumentationStyle.getDocumentationGenerator(DocumentationStyle.PHP);
    private static final String USE = "use";
    private static final String PACKAGE = "package";
    private static final Logger logger = Logger.getLogger(QuorumVirtualMachine.class.getName());
    private boolean auditoryDebugging = true;
    /**
     * A flag determining if the last build was successful.
     */
    private boolean lastBuildSuccessful = false;
    /**
     * This file descriptor is a cached copy that can be used by the code
     * completion system if the last build was not successful.
     */
    private HashMap<String, FileDescriptor> cache = null;
    /**
     * This instance of QuorumVirtualMachine is used only if verification of
     * documentation example code is enabled.
     */
    private QuorumVirtualMachine verifierVM = null;
    /**
     * Used in the build(String) method to guarantee unique tokens.
     */
    private static SecureRandom random = new SecureRandom();
    private static int numVerifiedExamples = 0;
    private static int numNonCompilingExamples = 0;
    private static int numCrashingExamples = 0;
    private int numMissingExamples = 0;
    
    public QuorumVirtualMachine() {
        compilerErrors = new CompilerErrorManager();
        builder = new IntermediateExecutionBuilder();
        builder.setVirtualMachine(this);
        parseHash = new HashMap<String, QuorumFile>();
        typeChecker = new TypeChecker();
        standardLibrary = new QuorumStandardLibrary();
        generator = new QuorumBytecodeGenerator();
        cache = new HashMap<String, FileDescriptor>();
    }

    private void resetBuild() {
        table = new SymbolTable();
        table.setVirtualMachine(this);
        getTypeChecker().clear();
        parseHash.clear();
        currentFile = null;
        this.getCompilerErrors().clear();
        builder.clear();
        getExecution().clear();
        getExecution().restartExecution();
        this.getDataEnvironment().clear();
        getPluginManager().clear();
        cache.clear();
    }

    @Override
    public boolean generateDocumentation() {
        if (!(new File(documentationPath)).isDirectory()) {
            return false;
        }
        Iterator<LibraryIndexEntry> allClasses = this.standardLibrary.findAllClasses();
        HashMap<String, File> files = new HashMap<String, File>();
        File rootFolder = this.standardLibrary.getRootFolder();
        String absolutePath = rootFolder.getAbsolutePath();
        while (allClasses.hasNext()) {
            LibraryIndexEntry next = allClasses.next();
            String path = next.getPath();
            path = absolutePath + "/" + path;
            files.put(path, new File(path));
        }

        Iterator<ContainerExecution> containers = this.builder.getContainers();
        while (containers.hasNext()) {
            ContainerExecution containerExec = containers.next();
            String containerKey = containerExec.getStaticKey();
            Iterator<ClassExecution> classes = containerExec.getClasses();
            while (classes.hasNext()) {
                ClassExecution classExec = classes.next();

                ClassDescriptor clazz = classExec.getClassDescriptor();
                String fileKey = clazz.getFile().getStaticKey();
                if (!files.containsKey(fileKey)) {
                    files.put(fileKey, new File(fileKey));
                }
            }
        }
        File[] toArray = files.values().toArray(new File[files.size()]);

        generateAllDocumentation(toArray, false);

        return true;
    }

    public boolean generateAllDocumentation(File[] files, boolean verify) {
        documentor.clearIndex();
        Iterator<ContainerExecution> containers = this.builder.getContainers();

        if (verify) {
            // Spawn a new VM for documentation verification.
            
            //reset the count of compiling examples
            numVerifiedExamples = 0;
            numNonCompilingExamples = 0;
            numCrashingExamples = 0;
            verifierVM = new QuorumVirtualMachine();
        }
        while (containers.hasNext()) {
            ContainerExecution containerExec = containers.next();
            Iterator<ClassExecution> classes = containerExec.getClasses();
            while (classes.hasNext()) {
                ClassExecution classExec = classes.next();

                if (verify) {
                    // Verify that all documentaiton compiles.
                    verifyDocumentaitonCompilation(classExec.getClassDescriptor());
                }
                ClassDescriptor clazz = classExec.getClassDescriptor();
                String result = documentor.generate(clazz);
                documentationToFile(clazz, result);
            }
        }
        documentor.finishIndex();
        String index = documentor.getIndex();
        generateIndexFile(index);

        //copy over the CSS file
        File documentation = new File(documentationPath);
        File standardLibraryRoot = this.getStandardLibrary().getRootFolder();
        documentor.finish(standardLibraryRoot, documentation);
        return true;
    }

    private void generateIndexFile(String string) {
        String root = documentationPath;
        File file = new File(root);
        try {
            File result = new File(root + "/" + documentor.getIndexName() + "." + documentor.getFileExtension());
            Writer out = null;

            if (result.isFile()) {
                result.delete();
            }
            //create the file again
            result.createNewFile();
            result.setWritable(true);
            result.setReadable(true);
            out = new OutputStreamWriter(new FileOutputStream(result));

            out.write(string);
            out.close();
        } catch (IOException exception) {
            logger.log(Level.INFO, "Could not output index to file.", exception);
        }
    }

    private void documentationToFile(ClassDescriptor clazz, String string) {
        String root = documentationPath;
        String container = clazz.getContainer().getContainer();
        container = container.replace('.', '/');
        String[] split = container.split("/");

        File file = new File(root);
        if (file.isDirectory()) {
            //make sure all subfolders are in there
            for (int i = 0; i < split.length; i++) {
                String current = "";
                for (int j = 0; j <= i; j++) {
                    current += split[j] + "/";

                }
                File folder = new File(root + "/" + current);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            }

            //write the string to a newly created file, if it does not exist
            try {
                File result = new File(root + "/" + container + "/" + clazz.getName() + "." + documentor.getFileExtension());
                Writer out = null;

                if (result.isFile()) {
                    result.delete();
                }
                //create the file again
                result.createNewFile();
                result.setWritable(true);
                result.setReadable(true);
                out = new OutputStreamWriter(new FileOutputStream(result));

                out.write(string);
                out.close();
            } catch (IOException exception) {
                logger.log(Level.INFO, "Could not output documentation to file.", exception);
            }
        }
    }

    /**
     * This test function allows you to run the virtual machine raw, in block
     * mode. It is useful for running tests, but should not be called unless the
     * desired behavior is to block the current thread.
     */
    public void blockRun() {
        Execution exec = getExecution();
        try {
            while (!exec.isExecutionFinished()) {
                exec.step();
            }
        } catch (Exception exception) {
            logger.log(Level.INFO, "The Quorum Compiler threw an exception in a test case.", exception);
        }
    }

    public void blockRunToCursor(String fileKey, long line) {
        getPluginManager().setDebugMode(false);
        //step until we hit the line. If we never do, run forever
        int currentLine;
        String currentFileKey;
        Execution execution = this.getExecution();
        if (execution.getCurrentStep() != null) {
            currentLine = execution.getLineNumber();
            currentFileKey = execution.getCurrentStep().getFileKey();
        } else {
            currentLine = -1;
            currentFileKey = "";
        }

        while (!getExecution().isExecutionFinished()
                && (currentFileKey.compareTo(fileKey) != 0
                || currentLine != (int) line)) {
            execution.step();
            if (execution.getCurrentStep() != null) {
                currentLine = execution.getLineNumber();
                currentFileKey = execution.getCurrentStep().getFileKey();
            }
        }
    }

    @Override
    public void buildSingle(File file) {
        if (building) {
            return;
        }
        try {
            //phase 1, 2 lexing and parsing
            removeFromBuild(file);
            this.compilerErrors.setErrorKey(file.getAbsolutePath());
            parse(file);

            //if the build was successful, the cache should be updated.
            updateCache(file);
            
            //phase 2.5 - semantic analysis pre-processing
            computeStandardLibraryFiles();
            getSymbolTable().calculatePackageUseForFile(file.getAbsolutePath());
            getSymbolTable().resolveAllMethods();
            getSymbolTable().resolveInheritance();
            getSymbolTable().compileTypeCheckingTables(typeChecker);

            //phase 3 - semantic analysis
            QuorumFile hf = getQuorumFileFromCache(file);
            semanticAnalysis(hf);

            
            this.compilerErrors.resetToDefaultKey();
            throwBuildEvent();
        } catch (Exception exception) {
            logger.log(Level.INFO, "The Quorum Compiler threw an exception in buildSingle(File).", exception);
        }
    }

    /**
     * This method uses the file as a key for where in the parse the system is,
     * but actually computes the parse from the String text. This allows us to
     * fix the bug
     *
     * @param file
     * @param text
     */
    public void buildSingle(File file, String text) {
        if (building) {
            return;
        }
        try {
            //phase 1, 2 lexing and parsing
            removeFromBuild(file);
            this.compilerErrors.setErrorKey(file.getAbsolutePath());
            parseSingle(file, text);
            //if the build was successful, the cache should be updated.
            updateCache(file);
            
            //phase 2.5 - semantic analysis pre-processing
            computeStandardLibraryFiles();
            getSymbolTable().calculatePackageUseForFile(file.getAbsolutePath());
            getSymbolTable().resolveAllMethods();
            getSymbolTable().resolveInheritance();
            getSymbolTable().compileTypeCheckingTables(typeChecker);

            //phase 3 - semantic analysis
            QuorumFile hf = getQuorumFileFromCache(file);
            semanticAnalysis(hf);
            
            this.compilerErrors.resetToDefaultKey();
            throwBuildEvent();
        } catch (Exception exception) {
            logger.log(Level.INFO, "The Quorum Compiler threw an exception in buildSingle(File).", exception);
        }
    }

    /**
     * This method updates the cache when a specific file is being parsed.
     *
     * @param file
     */
    private void updateCache(File file) {
        if (this.compilerErrors.isFileErrorFree(file.getAbsolutePath())) {
            lastBuildSuccessful = true;
            //update the cache by copying it
            FileDescriptor cacheMe = this.getSymbolTable().getFileDescriptor(file.getAbsolutePath());
            cache.put(file.getAbsolutePath(), cacheMe);
            //this.cache = cacheMe;
        } else {
            lastBuildSuccessful = false;
        }
    }

    private boolean parseSingle(File file, String text) {
        currentFile = file;
        String name = stripFileName(file.getName());
        table.resetCurrentObjects();
        try {
            ANTLRStringStream ss = new ANTLRStringStream(text);
            //ANTLRInputStream input = new ANTLRInputStream(fis);
            QuorumLexer lexer = new QuorumLexer(ss);
            lexer.setGrammarFileNameNoExtension(name);
            lexer.setQuorumVirtualMachine(this);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            //add a new FileDescriptorObject to the symbol table so that
            //we can tell which classes are defined in which files.
            currentFile = file;
            FileDescriptor fd = new FileDescriptor();
            fd.setFile(currentFile);
            table.add(fd);

            //create a parser and set its symbol table
            QuorumParser parser = new QuorumParser(tokens);
            parser.setGrammarFileNameNoExtension(name);
            parser.setQuorumVirtualMachine(this);

            //start parsing and create a structure for housing any
            //information found during the parse.
            start_return start = parser.start();
            updateCache(file);
            CommonTree syntaxTree = (CommonTree) start.getTree();
            QuorumFile hf = new QuorumFile();
            hf.setFile(file);
            hf.setFileInputStream(null);
            hf.setInput(null);
            hf.setLexer(lexer);
            hf.setTokens(tokens);
            hf.setParser(parser);
            hf.setStartNode(start);
            hf.setSyntaxTree(syntaxTree);
            cacheQuorumFile(hf);

            if (!this.getCompilerErrors().isCompilationErrorFree()) {
                parsed = false;
                updateCache(file);
            } else {
                parsed = true;
            }
        } catch (Exception exception) {
            logger.log(Level.INFO, "Exception thrown while trying to parse file "
                    + file.getAbsolutePath() + " with source <quorum>"
                    + text + "</quorum>", exception);
            parsed = false;
        }
        return true;
    }

    @Override
    public void parseSingle(File file) {
        if (!this.parseHash.containsKey(file.getAbsolutePath())) {
            parse(file);
            updateCache(file);
            getSymbolTable().calculatePackageUseForFile(file.getAbsolutePath());
        }
    }

    private void throwBuildEvent() {
        VirtualMachineEvent event = new VirtualMachineEvent(null, this, false);
        event.setBuildEvent(true);
        event.setBuildSuccessful(this.compilerErrors.isCompilationErrorFree());

        if (buildAllEvent) {
            event.setBuildAllEvent(true);
            buildAllEvent = false;
        }

        this.throwEventToListeners(event);
    }

    @Override
    public void removeFromBuild(File file) {
        //remove file from symbol table
        FileDescriptor d = new FileDescriptor();
        d.setFile(file);
        getSymbolTable().remove(d);

        //remove file from compiler errors
        this.getCompilerErrors().removeErrorsAtKey(file.getAbsolutePath());

        //remove from the parse hash
        parseHash.remove(file.getAbsolutePath());
    }

    private void computeStandardLibraryFiles() {
        Iterator<File> files = getSymbolTable().getStandardLibraryFiles();
        while (files.hasNext()) {
            //TODO: Fix this to account for recursive File additions.
            //parse all the standard library files
            while (files.hasNext()) {
                File file = files.next();
                if (!this.isFileCached(file.getAbsolutePath())) {
                    this.compilerErrors.setErrorKey(file.getAbsolutePath());
                    parse(file);
                }
            }
            files = getSymbolTable().getStandardLibraryFiles();//slow, but functional
        }
    }

    @Override
    public void build(File[] source) {
        Build build = new Build();
        build.source = source;
        executionManager.add(build);
    }

    @Override
    public void setAuditoryDebugging(boolean isSpeaking) {
        this.auditoryDebugging = isSpeaking;
    }

    @Override
    public boolean getAuditoryDebugging() {
        return this.auditoryDebugging;
    }

    
    private void verifyDocumentaitonCompilation(ClassDescriptor clazz) {
        Iterator<MethodDescriptor> methods = clazz.getMethods();

        while (methods.hasNext()) {
            //verifierVM = new QuorumVirtualMachine();
            verifierVM.resetBuild();
            MethodDescriptor method = methods.next();
            String example = method.getDocumentation().getExample();

            if (example != null && !example.isEmpty()) {
                try {
                    verifierVM.build(example);

                    if (!verifierVM.getCompilerErrors().isCompilationErrorFree()) {
                        System.err.println("Warning: Method " + clazz.getStaticKey() + ":" 
                                + method.getStaticKey() +  
                                " has example documentation that will not compile.");
                        numNonCompilingExamples++;
                    } 
//                    else {
//                        System.err.println("OK: " + clazz.getStaticKey() + ":" 
//                                + method.getStaticKey());
//                    }
                } catch(Exception e) {
                    System.err.println("Crash: " + clazz.getStaticKey() + ":" 
                                + method.getStaticKey());
                    numCrashingExamples++;
                }
                
                numVerifiedExamples++;
            } else {
                //if the method is private, it's fine for the example to be 
                //missing, as you couldn't call it anyway from an example.
                if(method.getAccessModifier() == AccessModifierEnum.PUBLIC) {
                    System.err.println("Warning: " + clazz.getStaticKey() + ":" 
                        + method.getStaticKey() +
                        " has a missing example.");
                    numMissingExamples++;
                    numVerifiedExamples++;
                }
            }
        }
    }

    private class Build implements Runnable {
        public File[] source;
        @Override
        public void run() {
            try {
                buildActual(source);
                if(source == null) {
                    return;
                }
                for(int i = 0; i < source.length; i++) {
                    updateCache(source[i]);
                }
                //updateCache();
            } catch (Exception exception) {
                logger.log(Level.INFO, "The Quorum Compiler threw an exception in build(File[]).", exception);
            }
        }
    }

    @Override
    public void build(File[] source, boolean block) {
        Build build = new Build();
        build.source = source;
        if (block) {
            build.run();
        } else {
            executionManager.add(build);
        }
    }

    static String token = "bob" + new BigInteger(130, random).toString(32) + ".quorum";
    
    /**
     * This function builds source code directly from a string that is not
     * necessarily saved to disk. Since quorum requires files be associated with
     * the source code, Quorum internally creates a fake file with a unique key.
     *
     * @param source
     */
    public void build(String source) {
        File fakeMain = new File(token);
        this.setMain(fakeMain.getAbsolutePath());
        this.compilerErrors.setErrorKey(fakeMain.getAbsolutePath());
        this.parseSingle(fakeMain, source);
        
        updateCache(fakeMain);
        
        computeStandardLibraryFiles();
        getSymbolTable().compilePackageUseTables();
        getSymbolTable().resolveAllMethods();
        getSymbolTable().resolveInheritance();
        getSymbolTable().compileTypeCheckingTables(typeChecker);


        //now do semantic analysis on all of the files
        //if there were no compiler errors
        if (this.getCompilerErrors().isCompilationErrorFree()) {
            Iterator<QuorumFile> it = parseHash.values().iterator();
            while (it.hasNext()) {
                QuorumFile file = it.next();
                this.compilerErrors.setErrorKey(file.getFile().getAbsolutePath());
                semanticAnalysis(file);
            }
        }
        //if it worked, link it
        link();
        
    }

    private void link() {
        if (this.getCompilerErrors().isCompilationErrorFree()) {
//            Linker linker = new Linker();
//            linker.setMachine(this);
//            linker.link(builder);
//            Vector<ExecutionStep> steps = linker.getLinkedSteps();
//            vTable = linker.getVTable();
            //this.getExecution().addStep(steps);
            
             //For now just set the first main method as the entry point
            if (this.isGenerateCode()) {
                QuorumBytecodeGenerator gen = (QuorumBytecodeGenerator) this.generator;
                //gen.setLinker(linker);
                gen.setBuilder(builder);
                gen.generate();
                try {
                    gen.writeToDisk();
                } catch (IOException ex) {
                    Logger.getLogger(QuorumVirtualMachine.class.getName()).log(Level.INFO,
                            "Could not write bytecode to disk", ex);
                }
            }
        }
    }

    /**
     * This function builds the source, but does so in an unsafe way to allow
     * automated testers to check for any potential problems with exceptions or
     * other issues.
     *
     * @param source
     */
    public void testBuild(File[] source) {
        buildActual(source);
    }
    private boolean building = false;

    private void buildActual(File[] source) {
        building = true;
        resetBuild();
        //first parse the files and fill up the symbol table
        for (int i = 0; i < source.length; i++) {
            File file = source[i];
            this.compilerErrors.setErrorKey(file.getAbsolutePath());
            parse(file);
            updateCache(file);
        }
        //now compute all of the package and use references
        //to make sure that each file is referencing something known
        //to the system

        computeStandardLibraryFiles();
        getSymbolTable().compilePackageUseTables();
        getSymbolTable().resolveAllMethods();
        getSymbolTable().resolveInheritance();
        getSymbolTable().compileTypeCheckingTables(typeChecker);


        //now do semantic analysis on all of the files
        //if there were no compiler errors
        if (this.getCompilerErrors().isCompilationErrorFree()) {
            Iterator<QuorumFile> it = parseHash.values().iterator();
            while (it.hasNext()) {
                QuorumFile file = it.next();
                this.compilerErrors.setErrorKey(file.getFile().getAbsolutePath());
                semanticAnalysis(file);
            }
            this.compilerErrors.resetToDefaultKey();
            //now take the execution that has been built and put it into
            //its execution model

            //Link phase
            link();

            it = parseHash.values().iterator();
            while (it.hasNext()) {
                QuorumFile file = it.next();
                this.compilerErrors.setErrorKey(file.getFile().getAbsolutePath());
            }
        }

        buildAllEvent = true;
        throwBuildEvent();
        building = false;
    }

    /**
     * Determines whether the current file has already been parsed.
     *
     * @param fileKey
     * @return
     */
    private boolean isFileCached(String fileKey) {
        return parseHash.containsKey(fileKey);
    }

    private void cacheQuorumFile(QuorumFile file) {
        parseHash.put(file.getFile().getAbsolutePath(), file);
    }

    private QuorumFile getQuorumFileFromCache(File file) {
        QuorumFile hf = parseHash.get(file.getAbsolutePath());
        return hf;
    }

    private String stripFileName(String name) {
        int i = name.lastIndexOf('.');

        if (i > 0 && i < name.length() - 1) {
            return name.substring(0, i);
        }

        return null;
    }

    private boolean parse(File file) {
        currentFile = file;
        String name = stripFileName(file.getName());
        table.resetCurrentObjects();
        try {
            FileInputStream fis = new FileInputStream(file);
            ANTLRInputStream input = new ANTLRInputStream(fis);
            QuorumLexer lexer = new QuorumLexer(input);
            lexer.setGrammarFileNameNoExtension(name);
            lexer.setQuorumVirtualMachine(this);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            //add a new FileDescriptorObject to the symbol table so that
            //we can tell which classes are defined in which files.
            currentFile = file;
            FileDescriptor fd = new FileDescriptor();
            fd.setFile(currentFile);
            table.add(fd);

            //create a parser and set its symbol table
            QuorumParser parser = new QuorumParser(tokens);
            parser.setGrammarFileNameNoExtension(name);
            parser.setQuorumVirtualMachine(this);

            //start parsing and create a structure for housing any
            //information found during the parse.
            start_return start = parser.start();
            //update the cache if it parsed
            updateCache(file);
            CommonTree syntaxTree = (CommonTree) start.getTree();
            QuorumFile hf = new QuorumFile();
            hf.setFile(file);
            hf.setFileInputStream(fis);
            hf.setInput(input);
            hf.setLexer(lexer);
            hf.setTokens(tokens);
            hf.setParser(parser);
            hf.setStartNode(start);
            hf.setSyntaxTree(syntaxTree);
            cacheQuorumFile(hf);

            if (!this.getCompilerErrors().isCompilationErrorFree()) {
                parsed = false;
            } else {
                parsed = true;
            }
        } catch (RecognitionException exception) {
            logger.log(Level.INFO, "ANTLR recognition exception.", exception);
            parsed = false;
        } catch (FileNotFoundException exception) {
            logger.log(Level.INFO, "Could not parse file: " + file.getAbsolutePath(), exception);
            parsed = false;
        } catch (IOException exception) {
            logger.log(Level.INFO, "Could not do IO operation in file: " + file.getAbsolutePath(), exception);
            parsed = false;
        }
        return true;
    }

    private boolean semanticAnalysis(QuorumFile file) {
        if (parsed) {
            try {
                builder.setCurrentFileKey(file.getFile().getAbsolutePath());
                table.enterFile(file.getFile().getAbsolutePath());
                CommonTreeNodeStream nodes = new CommonTreeNodeStream(file.getSyntaxTree());
                nodes.setTokenStream(file.getTokens());
                QuorumTreeWalker symbol = new QuorumTreeWalker(nodes);
                symbol.setGrammarFileNameNoExtension(stripFileName(file.getFile().getName()));
                symbol.setQuorumVirtualMachine(this);
                QuorumTreeWalker.start_return tree = symbol.start();
                file.setTree(tree);
            } catch (RecognitionException exception) {
                logger.log(Level.INFO, "Could not do IO operation in file: " + file.getFile().getAbsolutePath(), exception);
            }
        }
        return true;
    }

    @Override
    public VariableWatch getVariableValue(String variable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getType(ExpressionValue value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExecutionStep getNullExecutionStep() {
        return new NullIntermediateStep();
    }

    @Override
    public CompilerErrorManager getCompilerErrors() {
        return compilerErrors;
    }

    /**
     * @return the builder
     */
    public IntermediateExecutionBuilder getBuilder() {
        return builder;
    }

    @Override
    public void clean() {
        Clean clean = new Clean();
        executionManager.add(clean);
    }

    private class Clean implements Runnable {

        @Override
        public void run() {
            resetBuild();
            //check the build and distribution folders and delete them if they exist
            File build = getCodeGenerator().getBuildFolder();
            File distribute = getCodeGenerator().getDistributionFolder();
            delete(build);
            delete(distribute);
        }
    }

    @Override
    public void clean(boolean block) {
        Clean clean = new Clean();
        if (block) {
            clean.run();
        } else {
            executionManager.add(clean);
        }
    }

    /**
     * This method deletes a folder from the system.
     *
     * @param dir
     */
    private static void delete(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                delete(new File(dir, children[i]));
            }
        }
        dir.delete();
    }

    @Override
    public ExpressionValue createExpressionValue(TypeDescriptor type) {

        ExpressionValue value = null;
        if (type.isPrimitiveType()) {
            value = ExpressionValue.getPrimitiveDefault(type);
        } else {
            value = new ExpressionValue();

            //now put this value on the heap
            RuntimeObject ro = new RuntimeObject();
            String name = type.getName();
            ClassDescriptor clazz = this.getSymbolTable().getClassDescriptor(name);
            ro.setClazz(clazz);
            DataEnvironment de = this.getDataEnvironment();
            de.callingClassStackPush(de.getThisObject());

            ro.setLineInformation(this.getExecution().getCurrentStep().getLineInformation());
            int hash = de.addNewObject(ro);

            //now grab the current execution pointer and remember it
            int startPosition = this.getExecution().getExecutionPosition();
            int classStart = clazz.getLocation().getStart();

            de.setToObjectScope(hash);
            de.pushCreateObjectOpcode(startPosition);
            //reset the execution pointer and run through the object initialization

            //initPopSteps and all included opcodes should be executed
            this.getExecution().setExecutionPosition(classStart);
            Execution execution = this.getExecution();
            boolean finished = false;
            while (!finished) {
                ExecutionStep step = execution.step();
                ExecutionStep nextStep = execution.getNextStep();
                if (de.getObject(hash).equals(de.getThisObject()) && ro.isThisMode() && nextStep instanceof ObjectInitPopStep) {
                    //pop appropriately
                    execution.step();
                    finished = true;
                }
            }

            value.setObjectHash(hash);
            value.setType(type);
            TimeStamp stamp = new TimeStamp(startPosition);
            value.setTimeStamp(stamp);

            //now restore the execution to its previous point.
            execution.setExecutionPosition(startPosition);
        }
        return value;
    }

    @Override
    public void undoCreateExpressionValue(RuntimeObject variable) {
        DataEnvironment de = getDataEnvironment();
        de.removeObject(variable.getHashKey());

        de.callStackUndo();
        de.callingClassStackUndo();

        //return to original scope
        int newScope = de.callingClassStackPeek().getHashKey();
        de.setThisPointer(newScope);

        getDataEnvironment().undoCreateObjectOpcode();
    }

    @Override
    public CodeCompletionResult requestCodeCompletionResult(CodeCompletionRequest request) {
        //tell the parser not to parse while we are doing this update.
        //technically, there's a potential (and very rare) race condition here.
        //It can probably be removed by carefully synchronizing 
        //code completion and parser calls.
        building = true;

        CodeCompletionResult result = new CodeCompletionResult();
        String line = request.getLine();
        line = line.trim();
        String sub = request.getLine().substring(0, request.getStartOffset());
        boolean isDot = false;

        if (sub.length() > 0) {
            char c = sub.charAt(sub.length() - 1);
            if (c == '.') {
                isDot = true;
            }
        }

        boolean useStart = line.startsWith(USE);
        boolean packageStart = line.startsWith(PACKAGE);
        if (useStart) {
            addUseResults(result, request, true);
        } else if (!useStart && packageStart) {
            addUseResults(result, request, false);
        } else if (!useStart && !isDot) { //if it is a dot request, ignore it.
            addExpressionResults(result, request);
        }

        building = false;
        return result;
    }

    /**
     * Adds custom classes defined by the user into the code completion.
     *
     * @param result
     * @param request
     * @param pack
     */
    private void addCustomClasses(CodeCompletionResult result, CodeCompletionRequest request, String pack) {
        //this is slow, but check all classes that are compiled.
        //if they aren't in the standard library, make sure they make it into
        //the code completion, as they are user defined.
        Iterator<ClassDescriptor> classes = table.getClassDescriptors();
        while (classes.hasNext()) {
            ClassDescriptor clazz = classes.next();
            if (standardLibrary.findClass(clazz.getContainer().getContainer(), clazz.getName()) == null
                    && clazz.getContainer().getContainer().compareTo(pack) == 0) {
                CodeCompletionItem classCompletionItem = getClassCompletionItem(clazz);
                result.add(classCompletionItem);
            }
        }
    }

    /**
     * This method calculates the possible options for the use statement.
     *
     * @param result
     * @param line
     * @param isUse if this is false, pull out the word package, otherwise, use
     */
    private void addUseResults(CodeCompletionResult result, CodeCompletionRequest request, boolean isUse) {
        String line = request.getLine().trim();
        if (isUse) {
            line = line.substring(USE.length(), line.length());
        } else {
            line = line.substring(PACKAGE.length(), line.length());
        }
        line = line.trim();
        String[] split = line.split("\\.");

        if (split.length > 0 && line.length() != 0) {
            if (line.charAt(line.length() - 1) == '.') {
                result.setFilter("");
            } else {
                result.setFilter(split[split.length - 1]);
            }
        }

        final String root = this.standardLibrary.getStandardLibraryRootName();
        if (split.length == 1) {
            String left = split[0];
            if (left.matches("\\s*") || result.getFilter().length() != 0) {
                CodeCompletionItem item = new CodeCompletionItem();
                item.setCodeCompletionType(CodeCompletionType.PACKAGE);
                item.setCompletion(root);
                item.setDisplayName(root);
                result.add(item);
                addCustomClasses(result, request, root);

            } else if (left.equals(root)) {
                addSubpackagesAndClasses(result, standardLibrary.getStandardLibraryRootName());
                addCustomClasses(result, request, standardLibrary.getStandardLibraryRootName());
            }
        } else {
            String pack = "";
            int length = split.length;
            if (result.getFilter().length() != 0) {
                length--;
            }
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    pack += split[i];
                } else {
                    pack += "." + split[i];
                }
            }
            addSubpackagesAndClasses(result, pack);
            addCustomClasses(result, request, pack);
        }

    }

    private void addSubpackagesAndClasses(CodeCompletionResult result, String name) {
        Iterator<String> subs = standardLibrary.findAllSubpackages(name);
        while (subs.hasNext()) {
            String next = subs.next();
            CodeCompletionItem item = new CodeCompletionItem();
            item.setCodeCompletionType(CodeCompletionType.PACKAGE);
            item.setCompletion(next);
            item.setDisplayName(next);
            result.add(item);
        }
        
        Iterator<LibraryIndexEntry> classes = standardLibrary.findAllClassesInPackage(name);
        boolean hasAny = classes.hasNext();
        while (classes.hasNext()) {
            LibraryIndexEntry next = classes.next();
            String key = next.getFullClassName();
            ClassDescriptor clazz = table.getClassDescriptor(key);
            if(clazz != null) {
                CodeCompletionItem classCompletionItem = getClassCompletionItem(clazz);
                result.add(classCompletionItem);
            } else {
                CodeCompletionItem item = new CodeCompletionItem();
                item.setCodeCompletionType(CodeCompletionType.CLASS);
                item.setCompletion(next.getName());
                item.setDisplayName(next.getName());
                result.add(item);
            }
        }
        
        
        //if there is at least one class in this package, prompt the user that
        //they can use the "all" option
        if(hasAny) {
            CodeCompletionItem item = new CodeCompletionItem();
            item.setCodeCompletionType(CodeCompletionType.PACKAGE);
            String signature = "Use all classes with the \"all\" keyword.";
            String itemName = "all";

            String description = "";
            description += "<h1>" + signature + "</h1>"
                    + "<p align=\"justify\">The word "
                    + "all indicates to quorum that we would like to"
                    + " have access to all classes in the package. For "
                    + "example, the phrase Libraries.Containers.all would indicate "
                    + "that we want to use any class in the Libraries.Containers "
                    + "package."
                    + "</p>";
            description += "<h2>" + "Code Example:" + "</h2>";
            description += "<PRE><CODE>"
                    + "use Libraries.Containers.all\n\n"
                    + "List&lt;integer&gt; list\n"
                    + "Array&lt;integer&gt; array"
                    + "</PRE></CODE>";

            item.setDisplayName(itemName);
            item.setDocumentation(description);
            item.setCompletion("all");
            result.add(item);
        }
    }

    private void addExpressionResults(CodeCompletionResult result, CodeCompletionRequest request) {
        String expression = request.getLine().substring(0, request.getStartOffset()).trim();
        //now split the string into pieces
        String[] split = expression.split(":");

        if (split.length > 0 && expression.length() != 0) {
            if (expression.charAt(expression.length() - 1) == ':') {
                result.setFilter("");
            } else {
                result.setFilter(split[split.length - 1]);
            }
        }

        FileDescriptor file = null;
        if (!this.lastBuildSuccessful && cache != null && cache.containsKey(request.getFileKey())) {
            file = cache.get(request.getFileKey());
        } else {
            file = this.getSymbolTable().getFileDescriptor(request.getFileKey());
        }

        ClassDescriptor clazz = null;
        if (file != null) {
            Iterator<ClassDescriptor> classIterator = file.getClassIterator();
            while (classIterator.hasNext()) {
                ClassDescriptor next = classIterator.next();
                if ((request.getLineNumber() >= next.getLineBegin() && request.getLineNumber() <= next.getLineEnd())
                        || (next.getLineBegin() == 1 && next.getLineEnd() == 0) //must be a file with no explicit class definition
                        ) {
                    clazz = next;
                }
            }
        }
        final String me = "me";
        final String parent = "parent";

        if (clazz != null) {
            MethodDescriptor method = clazz.getMethodAtLine(request.getLineNumber());
            //if the method is null, it must be a class variable (or garbage)
            boolean isMe = false;
            //if (method != null) {
            String left = split[0];
            String original = request.getLine().substring(0, request.getStartOffset());
            String partialLine = original;
            //parse left, starting from the start offset, working
            for (int i = request.getStartOffset() - 1; i >= 0; i--) {
                if (partialLine.charAt(i) == '('
                        || partialLine.charAt(i) == ','
                        || partialLine.charAt(i) == '*'
                        || partialLine.charAt(i) == '/'
                        || partialLine.charAt(i) == '+'
                        || partialLine.charAt(i) == '-'
                        || partialLine.charAt(i) == ' '
                        || isMod(partialLine, i) //make this work for mod --- if it's a d, check backwards and ensure that it's not part of another word
                        ) { //this is the end of this expression
                    partialLine = partialLine.substring(i + 1, request.getStartOffset());
                    //resplit it
                    partialLine = partialLine.trim();
                    split = partialLine.split(":");
                    left = split[0];
                    if (split.length > 1 || !partialLine.contains(":")) {
                        result.setFilter(split[split.length - 1]);
                    } else {
                        result.setFilter("");
                    }
                    i = -1; //finish early.
                }
            }

            partialLine = partialLine.trim();
            if (left.equals(parent)) {
                if (split.length > 2) {
                    String resolvedName = clazz.resolveParentName(split[1]);
                    ClassDescriptor par = clazz.getParent(resolvedName);
                    if (par != null) {
                        addClassToResult(null, request, result, par, isMe, null);
                    }
                } else if (split.length == 2) {
                    String resolvedName = clazz.resolveParentName(split[1]);
                    ClassDescriptor par = clazz.getParent(resolvedName);
                    if (par != null) {
                        result.setFilter("");
                        addClassToResult(null, null, result, par, isMe, null);
                    } else {
                        addParentClasses(result, clazz);
                    }
                } else if (split.length == 1) { //they are requesting a list of parents
                    Iterator<ClassDescriptor> parents = clazz.getFlattenedListOfParents();
                    while (parents.hasNext()) {
                        ClassDescriptor par = parents.next();
                        CodeCompletionItem classCompletionItem = getClassCompletionItem(par);
                        result.add(classCompletionItem);
                    }
                }

            } else if (left.equals(me) || left.isEmpty() || left.matches("\\s")) {
                isMe = true;
                addClassToResult(null, request, result, clazz, isMe, method);
            } else { //This should work until chaining is in place.
                if (method != null) {
                    VariableParameterCommonDescriptor variable = method.getVariable(left);
                    if (variable == null) {
                        variable = findVariableInBlocks(request, method, left);
                    }
                    if (variable != null) {
                        TypeDescriptor type = variable.getType();
                        String staticKey = "";
                        if(type != null){
                            staticKey = type.getStaticKey();
                        }

                        if(type.isPrimitiveType()){
                            TypeDescriptor tempType = new TypeDescriptor(type);
                            tempType.convertToClass();
                            staticKey = tempType.getStaticKey();
                        }
                        
                        ClassDescriptor validKey = table.getClassDescriptor(staticKey);
                        if (validKey == null) { //check if the class is in the same package
                            ClassDescriptor checker = table.getClassDescriptorFromPackage(
                                    staticKey, clazz.getContainer().getContainer());
                            if (checker != null) {
                                staticKey = checker.getStaticKey();
                            }
                        }

                        if (split[split.length - 1].equals(parent)) {
                            clazz = this.getSymbolTable().findClassDescriptorFromCurrentClass(staticKey);
                            if (clazz != null) {
                                addParentClasses(result, clazz);
                            }
                        } else {
                            addToCodeCompletionResult(variable, result, staticKey, clazz);
                        }
                    } else if (variable == null) {
                        if(split.length == 0) {
                            addDefaultValues(partialLine, result, request, clazz, method);
                        } else if(split.length == 1){
                            CodeCompletionRequest newRequest = new CodeCompletionRequest();
                            newRequest.setEndOffset(request.getEndOffset());
                            newRequest.setFileKey(request.getFileKey());
                            newRequest.setLineNumber(request.getLineNumber());
                            newRequest.setStartOffset(request.getStartOffset());
                            
                            String newLine = "";
                            int oldLineLength = request.getLine().length();
                            for(int i = 0; i < oldLineLength; i++) {
                                newLine += " "; //make the lengths match exactly, but make them empty.
                            }
                            
                            
                            addDefaultValues(partialLine, result, newRequest, clazz, method);
                            result.setFilter(partialLine);
                        }
                    }
                }
            }
            //    }
        }
    }

    /**
     * This method adds a set of variables and methods to the result. This
     * includes any information that is potentially useful to the user, like
     * auto-completing variables, primitives, classes that can be instantiated,
     * or other information.
     *
     * @param result
     * @param method
     */
    private void addDefaultValues(String partial, CodeCompletionResult result,
            CodeCompletionRequest request, ClassDescriptor clazz,
            MethodDescriptor method) {
        addClassToResult(null, null, result, clazz, true, null);

        addVariablesForMethod(partial, result, request, clazz, method);
        
        //add filtered classes you can instantiate
        addValidClassUses(partial, result, request, clazz);

        //add common control structures
        addControlStructures(result);

        //add filtered primitive values you can use.
        addPrimitiveValues(result);

    }
    
    /**
     * Adds all variables for a particular class. This method looks at the current
     * scope and tries to determine exactly which variables should be placed
     * in code completion. Its decision is based on scoping information, 
     * what the user has typed, and the line number the request is on.
     * 
     * @param partial
     * @param result
     * @param request
     * @param clazz
     * @param method 
     */
    private void addVariablesForMethod(String partial, CodeCompletionResult result,
            CodeCompletionRequest request, ClassDescriptor clazz,
            MethodDescriptor method) {
        BlockDescriptor scope = method.getBlockAtLine(request.getLineNumber());
        Iterator<VariableParameterCommonDescriptor> variables = null;
        if (scope != null) { //grab all of the variables and all those from its parents
            variables = scope.getAllVariablesExceptInClass(request.getLineNumber()).values().iterator();
        } else {      //just grab the variables from the method and call it good.
            variables = method.getVariables();
        }

        if (variables != null) {
            while (variables.hasNext()) {
                VariableParameterCommonDescriptor var = variables.next();
                if (var.getName().startsWith(partial) && var.getLineBegin() <= request.getLineNumber()) {
                    CodeCompletionItem item = new CodeCompletionItem();
                    if(var instanceof VariableDescriptor) {
                        item.setCodeCompletionType(getVariableCompletionType((VariableDescriptor) var));
                    }
                    else if(var instanceof ParameterDescriptor){
                        item.setCodeCompletionType(CodeCompletionType.PARAMETER);
                    }
                    else {
                        item.setCodeCompletionType(CodeCompletionType.LOCAL_VARIABLE);
                    }
                    addVariableCompletionItem(var, var.getName(), result, item);
                }
            }
        }
    }

    /**
     * Adds code completion items for several control structures.
     *
     * @param result
     */
    private void addControlStructures(CodeCompletionResult result) {
        //repeat times
        CodeCompletionItem item = new CodeCompletionItem();
        item.setCodeCompletionType(CodeCompletionType.CONTROL_STRUCTURE);
        String signature = "repeat times";
        String name = "repeat times";

        String description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">The word "
                + "repeat starts a control structure that tells the computer to "
                + "do something 0 or more times. In the case of the repeat times "
                + "control structure, the computer will conduct the repetition "
                + "a predetermined number of times. The number of times the loop "
                + "repeats is computed before the first iteration."
                + "</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "integer i = 0\n"
                + "repeat 10 times"
                + "\n\ti = i + 1"
                + "\n\tprint i"
                + "\nend"
                + "</PRE></CODE>";

        item.setDisplayName(name);
        item.setDocumentation(description);
        item.setCompletion("repeat 10 times\nend\n");
        result.add(item);

        //repeat while
        item = new CodeCompletionItem();
        item.setCodeCompletionType(CodeCompletionType.CONTROL_STRUCTURE);
        signature = "repeat while";
        name = "repeat while";

        description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">The word "
                + "repeat starts a control structure that tells the computer to "
                + "do something 0 or more times. In the case of the repeat while "
                + "control structure, the computer will conduct the repetition "
                + "until the expression after the word while is false."
                + "</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "integer i = 0\n"
                + "repeat while i < 10"
                + "\n\ti = i + 1"
                + "\n\tprint i"
                + "\nend"
                + "</PRE></CODE>";

        item.setDisplayName(name);
        item.setDocumentation(description);
        item.setCompletion("repeat while false\nend\n");
        result.add(item);

        //repeat while
        item = new CodeCompletionItem();
        item.setCodeCompletionType(CodeCompletionType.CONTROL_STRUCTURE);
        signature = "repeat until";
        name = "repeat until";

        description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">The word "
                + "repeat starts a control structure that tells the computer to "
                + "do something 0 or more times. In the case of the repeat while "
                + "control structure, the computer will conduct the repetition "
                + "until the expression after the word while is false."
                + "</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "integer i = 0\n"
                + "repeat until i >= 10"
                + "\n\ti = i + 1"
                + "\n\tprint i"
                + "\nend"
                + "</PRE></CODE>";

        item.setDisplayName(name);
        item.setDocumentation(description);
        item.setCompletion("repeat until true\nend\n");
        result.add(item);

        //conditional statements
        item = new CodeCompletionItem();
        item.setCodeCompletionType(CodeCompletionType.CONTROL_STRUCTURE);
        signature = "if";
        name = "if";

        description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">The word "
                + "if begins a control structure that allows the computer to make "
                + "a decision based on its input. For example, a conditional "
                + "statement might execute only on a particular operating system "
                + "or only if a particular variable is greater than 10."
                + "</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "integer i = 0\n"
                + "if i = 0\n"
                + "\tprint i\n"
                + "end"
                + "</PRE></CODE>";

        item.setDisplayName(name);
        item.setDocumentation(description);
        item.setCompletion("if true\nend\n");
        result.add(item);
    }

    /**
     * This method adds completion for the classes that are accessible from a
     * particular class
     *
     * @param partial
     * @param result
     * @param request
     */
    private void addValidClassUses(String partial, CodeCompletionResult result,
            CodeCompletionRequest request, ClassDescriptor clazz) {
        Iterator<ClassDescriptor> uses = clazz.getValidUses();
        while (uses.hasNext()) {
            ClassDescriptor next = uses.next();
            CodeCompletionItem classCompletionItem = getClassCompletionItem(next);
            result.add(classCompletionItem);
        }
    }

    private void addPrimitiveValues(CodeCompletionResult result) {

        //do integers
        CodeCompletionItem integer = new CodeCompletionItem();
        integer.setCodeCompletionType(CodeCompletionType.PRIMITIVE);
        String signature = "integer";
        String name = "integer";

        String description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">An integer is a primitive type that contains values from "
                + "-2,147,483,648 to 2,147,483,647. The integer, with a lower "
                + "case letter i at the beginning is called a primitive. There is "
                + "another version of integer that can be used, the Libraries.Language.Types.Integer class. "
                + "The advantage of using the primitive version is that computations "
                + "are faster, while the advantage of using the Integer class is that "
                + "we can call actions on the value it contains.</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "integer a = 5\n"
                + "intger b = 10\n"
                + "integer c = a + b\n"
                + "print c"
                + "</PRE></CODE>";

        integer.setDisplayName(name);
        integer.setDocumentation(description);
        integer.setCompletion(name);

        //do integers
        CodeCompletionItem number = new CodeCompletionItem();
        number.setCodeCompletionType(CodeCompletionType.PRIMITIVE);
        signature = "number";
        name = "number";

        description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">A number is a primitive type that contains values that potentially "
                + "have a decimal place. These numbers can be very small or very "
                + "large. Using scientific notation, this range is from "
                + "4.94065645841246544e-324d to 1.79769313486231570e+308d. "
                + "Numbers, therefore, are 64-bit values. "
                + "In addition to the number primitive type, with a lower "
                + "case letter n at the beginningn, there is "
                + "another version of number that can be used, the Libraries.Language.Types.Number class. "
                + "The advantage of using the primitive version is that computations "
                + "are faster, while the advantage of using the Number class is that "
                + "we can call actions on the value it contains.</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "number a = 5.0\n"
                + "number b = 10.0\n"
                + "number c = a + b\n"
                + "print c"
                + "</PRE></CODE>";

        number.setDisplayName(name);
        number.setDocumentation(description);
        number.setCompletion(name);

        CodeCompletionItem bool = new CodeCompletionItem();
        bool.setCodeCompletionType(CodeCompletionType.PRIMITIVE);
        signature = "boolean";
        name = "boolean";

        description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">A boolean is a primitive type that contains values that are "
                + "either true or false. Boolean values take up very little space "
                + "in primitive form, only 1-bit. "
                + "In addition to the boolean primitive type, with a lower "
                + "case letter b at the beginningn, there is "
                + "another version of boolean that can be used, the Libraries.Language.Types.Boolean class. "
                + "The advantage of using the primitive version is that computations "
                + "are faster, while the advantage of using the boolean class is that "
                + "we can call actions on the value it contains.</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "boolean a = true\n"
                + "boolean b = false\n"
                + "boolean c = a or b\n"
                + "print c"
                + "</PRE></CODE>";

        bool.setDisplayName(name);
        bool.setDocumentation(description);
        bool.setCompletion(name);

        CodeCompletionItem text = new CodeCompletionItem();
        text.setCodeCompletionType(CodeCompletionType.PRIMITIVE);
        signature = "text";
        name = "text";

        description = "";
        description += "<h1>" + signature + "</h1>"
                + "<p align=\"justify\">A text value is a list of sequential characters in the alphabet. "
                + "In Quorum, a text value is a literal set of characters, where "
                + "typing  a tab places a tab in the output, or pressing enter "
                + "places a new line in the output. The one exception is that a double "
                + "quote cannot be typed, as this terminates a string. As such, "
                + "to use a double quote, use the + operator, "
                + "and type quote, as shown in the example.</p>"
                + "<p align=\"justify\">In addition to the text primitive type, with a lower "
                + "case letter t at the beginningn, there is "
                + "another version of text that can be used, the Libraries.Language.Types.Text class. "
                + "This class contains extra helper actions related to using text values."
                + "</p>";
        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>"
                + "text a = \"Hello\"\n"
                + "text b = \", World!\"\n"
                + "//The next line will have the words Hello, World!, surrounded in double quotes"
                + "text c = quote + a + b + quote\n"
                + "print c"
                + "</PRE></CODE>";

        text.setDisplayName(name);
        text.setDocumentation(description);
        text.setCompletion(name);

        result.add(integer);
        result.add(number);
        result.add(bool);
        result.add(text);
    }

    /**
     * Determines, at a given point in a string, whether that string has a mod
     * operator immediately preceeding it.
     *
     * @param partial
     * @param i
     * @return
     */
    private boolean isMod(String partial, int i) {
        if (i - 3 < 0) {
            return false;
        }

        if (partial.charAt(i) == 'd'
                && partial.charAt(i - 1) == 'o'
                && partial.charAt(i - 2) == 'm'
                && partial.charAt(i - 3) == ' ') {
            return true;
        }

        return false;
    }

    private void addParentClasses(CodeCompletionResult result, ClassDescriptor clazz) {
        Iterator<ClassDescriptor> parents = clazz.getFlattenedListOfParents();
        while (parents.hasNext()) {
            ClassDescriptor next = parents.next();
            CodeCompletionItem item = getClassCompletionItem(next);
            result.add(item);
        }
    }

    private CodeCompletionItem getClassCompletionItem(ClassDescriptor clazz) {
        CodeCompletionItem item = new CodeCompletionItem();
        item.setCodeCompletionType(CodeCompletionType.CLASS);

        String signature = clazz.getStaticKey();
        String name = clazz.getName();

        String description = "";
        description += "<h1>" + signature + "</h1>";
        String[] paragraphs = Documentation.breakStringIntoParagraphArray(clazz.getDocumentation().getDescription());
        for (int i = 0; i < paragraphs.length; i++) {
            description += "<p>" + paragraphs[i] + "</p>";
        }

        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>" + clazz.getDocumentation().getExample()
                + "</PRE></CODE>";

        item.setDisplayName(name);
        item.setDocumentation(description);
        item.setCompletion(name);
        return item;
    }

    private VariableParameterCommonDescriptor findVariableInBlocks(CodeCompletionRequest request, MethodDescriptor method,
            String key) {
        VariableParameterCommonDescriptor variable = null;
        Iterator<BlockDescriptor> children = method.getChildren();
        boolean done = false;
        while (children.hasNext() && !done) {
            BlockDescriptor block = children.next();
            if (request.getLineNumber() >= block.getLineBegin()
                    && request.getLineNumber() <= block.getLineEnd()) {
                done = true;
                variable = block.getVariable(key);
                if (variable == null) {
                    variable = findVariableInBlocks(request, block, key);
                }
            }
        }
        return variable;
    }

    private VariableParameterCommonDescriptor findVariableInBlocks(CodeCompletionRequest request, BlockDescriptor block,
            String key) {
        VariableParameterCommonDescriptor variable = null;
        Iterator<BlockDescriptor> children = block.getChildren();
        boolean done = false;
        while (children.hasNext() && !done) {
            BlockDescriptor next = children.next();
            if (request.getLineNumber() >= next.getLineBegin()
                    && request.getLineNumber() <= next.getLineEnd()) {
                done = true;
                variable = next.getVariable(key);
                if (variable == null) {
                    variable = findVariableInBlocks(request, next, key);
                }
            }
        }
        return variable;
    }

    private void addToCodeCompletionResult(VariableParameterCommonDescriptor variable, CodeCompletionResult result, String classStaticKey, ClassDescriptor containingClass) {
        ClassDescriptor clazz = this.getSymbolTable().getClassDescriptor(classStaticKey);
        if (clazz == null) { //can the name be resolved from the parser?
            if (classStaticKey.length() > 0) {
                ClassDescriptor validatedClassUse = containingClass.getValidatedClassUse(classStaticKey);
                clazz = validatedClassUse;
            }
        }
        addClassToResult(variable, null, result, clazz, false, null);
    }

    /**
     * This method adds a class to the results. This class may or may not be
     * templated. If it is, a variable must be passed which contains correct
     * templated type information.
     *
     * @param variable if this call is templated, this is the variable by which
     * template information should be gathered
     * @param result
     * @param clazz
     * @param isCurrentClass
     */
    private void addClassToResult(VariableParameterCommonDescriptor variable, 
            CodeCompletionRequest request, 
            CodeCompletionResult result, ClassDescriptor clazz, 
            boolean isCurrentClass, MethodDescriptor insideMethod) {
        if (clazz != null) {
            if(variable == null || (variable != null && !variable.getType().isPrimitiveType())){
                addVariablesToResults(result, clazz, isCurrentClass);
            }

            //add all of its methods
            Collection<MethodDescriptor> allMethods = clazz.getAllMethods(AccessModifierEnum.PUBLIC);

            if (isCurrentClass) {
                allMethods = clazz.getAllMethods();
            }

            Iterator<MethodDescriptor> iterator = allMethods.iterator();
            while (iterator.hasNext()) {
                MethodDescriptor method = iterator.next();
                CodeCompletionItem item = new CodeCompletionItem();
                if(variable == null || !variable.getType().isPrimitiveType() || (variable.getType().isPrimitiveType() && (!method.getName().equals("GetValue") && !method.getName().equals("SetValue")))){
                    if(method.getAccessModifier() == AccessModifierEnum.PUBLIC && method instanceof MethodDescriptor) {
                        item.setCodeCompletionType(CodeCompletionType.PUBLIC_ACTION);
                    } else if(method.getAccessModifier() == AccessModifierEnum.PRIVATE && method instanceof MethodDescriptor) {
                        item.setCodeCompletionType(CodeCompletionType.PRIVATE_ACTION);
                    } else if(method.getAccessModifier() == AccessModifierEnum.PUBLIC && method instanceof BlueprintDescriptor) {
                        item.setCodeCompletionType(CodeCompletionType.PUBLIC_BLUEPRINT_ACTION);
                    } else if(method.getAccessModifier() == AccessModifierEnum.PRIVATE && method instanceof BlueprintDescriptor) {
                        item.setCodeCompletionType(CodeCompletionType.PRIVATE_BLUEPRINT_ACTION);
                    } else if(method.getAccessModifier() == AccessModifierEnum.PUBLIC && method instanceof SystemActionDescriptor) {
                        item.setCodeCompletionType(CodeCompletionType.PUBLIC_SYSTEM_ACTION);
                    } else if(method.getAccessModifier() == AccessModifierEnum.PRIVATE && method instanceof SystemActionDescriptor) {
                        item.setCodeCompletionType(CodeCompletionType.PRIVATE_SYSTEM_ACTION);
                    }

                    //if the method exists in the base class, mark it as such
                    if(clazz.getMethod(method.getStaticKey()) != null) { //it's in the base
                        item.setIsBaseClassMethod(true);
                    }


                    String signature = "";
                    if (variable == null) {
                        signature = method.getMethodSignature(true);
                    } else {
                        signature = method.getMethodSignature(true, variable, clazz);
                    }

                    String description = "";
                    String[] paragraphs = Documentation.breakStringIntoParagraphArray(method.getDocumentation().getDescription());
                    for (int i = 0; i < paragraphs.length; i++) {
                        description += "<p>" + paragraphs[i] + "</p>";
                    }

                    description += "<h2>" + "Code Example:" + "</h2>";
                    description += "<PRE><CODE>" + method.getDocumentation().getExample()
                            + "</PRE></CODE>";

                    //convert the signature to an PHP friendly format
                    signature = signature.replaceAll("<", "&lt;");
                    signature = signature.replaceAll(">", "&gt;");
                    item.setDisplayName(signature);
                    item.setDocumentation(description);
                    item.setCompletion(method.getName());
                    result.add(item);
                }
            }
        }
        
        if(insideMethod != null) {
            addVariablesForMethod("", result, request, clazz, insideMethod);
        }
        
        //add filtered classes you can instantiate
        addValidClassUses("", result, request, clazz);

        //add common control structures
        addControlStructures(result);

        //add primitives that you can use from here
        addPrimitiveValues(result);
    }
    
    /**
     * Returns the code completion type from the variable descriptor.
     * 
     * @param variable
     * @return 
     */
    public CodeCompletionType getVariableCompletionType(VariableDescriptor variable) {
        if(variable.isFieldVariable() && variable.getAccessModifier() == AccessModifierEnum.PUBLIC) {
            return CodeCompletionType.PUBLIC_FIELD_VARIABLE;
        } else if(variable.isFieldVariable() && variable.getAccessModifier() == AccessModifierEnum.PRIVATE) {
            return CodeCompletionType.PRIVATE_FIELD_VARIABLE;
        }
        else {
            return CodeCompletionType.LOCAL_VARIABLE;
        }
    }

    public void addVariablesToResults(CodeCompletionResult result, ClassDescriptor clazz, boolean isCurrentClass) {
        //add all of its methods
        Collection<VariableDescriptor> allVariables;


        if (isCurrentClass) {
            allVariables = clazz.getAllClassVariables();
        } else {
            allVariables = clazz.getAllClassVariables(AccessModifierEnum.PUBLIC);
        }

        Iterator<VariableDescriptor> iterator = allVariables.iterator();
        while (iterator.hasNext()) {
            VariableDescriptor variable = iterator.next();

            String signature = variable.getName();
            CodeCompletionItem item = new CodeCompletionItem();
            item.setCodeCompletionType(getVariableCompletionType(variable));
            
            //add any local variables or field variables not inherited.
            if(!variable.isFieldVariable() || clazz.getVariable(variable.getName()) != null){
                addVariableCompletionItem(variable, signature, result, item);
            }
            
            //add any inherited variables
            for (int i = 0; i < clazz.getNumFlatParents(); i++) {
                ClassDescriptor next = clazz.getFlatParent(i);
                if (next.getVariable(variable.getStaticKey()) != null) {
                    signature = "parent:" + next.getName() + ":" + variable.getName();
                    CodeCompletionItem item2 = new CodeCompletionItem();
                    item.setCodeCompletionType(getVariableCompletionType(variable));
                    addVariableCompletionItem(variable, signature, result, item2);
                }
            }
        }
    }

    private void addVariableCompletionItem(VariableParameterCommonDescriptor variable, String signature, CodeCompletionResult result, CodeCompletionItem item) {
        TypeDescriptor type = variable.getType();
        String displayType = "";
        if (!type.isVoid()) {
            if (type.isTemplated()) {
                displayType = type.getTemplateName();
            } else {
                displayType = type.getStaticKey();
            }
        }

        String description = "";
        if (variable instanceof VariableDescriptor) {
            VariableDescriptor var = (VariableDescriptor) variable;
            description = var.getAccessModifier().toString() + " " + displayType + " " + signature;
        } else {
            description = displayType + " " + signature;
        }

        String[] paragraphs = Documentation.breakStringIntoParagraphArray(variable.getDocumentation().getDescription());
        for (int i = 0; i < paragraphs.length; i++) {
            description += "<p>" + paragraphs[i] + "</p>";
        }

        description += "<h2>" + "Code Example:" + "</h2>";
        description += "<PRE><CODE>" + variable.getDocumentation().getExample()
                + "</PRE></CODE>";
        item.setDisplayName(signature);
        item.setDisplayType(displayType);
        item.setDocumentation(description);
        item.setCompletion(signature);
        result.add(item);
    }

    @Override
    public void setBuildFolder(File buildFolder) {
        SetBuildFolder folder = new SetBuildFolder();
        folder.build = buildFolder;
        this.executionManager.add(folder);
    }

    private class SetBuildFolder implements Runnable {

        public File build;

        @Override
        public void run() {
            getCodeGenerator().setBuildFolder(build);
        }
    }

    @Override
    public void setPluginFolder(File file) {
        SetPluginFolder folder = new SetPluginFolder();
        folder.plugin = file;
        executionManager.add(folder);
    }

    private class SetPluginFolder implements Runnable {

        public File plugin;

        @Override
        public void run() {
            getCodeGenerator().setPluginFolder(plugin);
        }
    }

    @Override
    public void setDistributionName(String name) {
        SetDistributionName myName = new SetDistributionName();
        myName.name = name;
        executionManager.add(myName);
    }

    private class SetDistributionName implements Runnable {

        public String name;

        @Override
        public void run() {
            getCodeGenerator().setDistributionName(name);
        }
    }
    
    /**
     * Adds an individual plugin to the virtual machine for later processing.
     * 
     * @param file 
     */
    public void addPlugin(File file) {
        AddPlugin add = new AddPlugin();
        add.file = file;
        executionManager.add(add);
    }
    
    private class AddPlugin implements Runnable {
        private File file;

        @Override
        public void run() {
            if(file != null) {
                getCodeGenerator().addPlugin(file);
            }
        }
    }

    @Override
    public void addDependency(File file) {
        AddDependency dep = new AddDependency();
        dep.file = file;
        executionManager.add(dep);
    }

    @Override
    public void addDependency(File file, String relativePath) {
        AddDependency dep = new AddDependency();
        dep.file = file;
        dep.path = relativePath;
        executionManager.add(dep);
    }

    private class AddDependency implements Runnable {

        private File file;
        private String path;

        @Override
        public void run() {
            if (path == null) {
                getCodeGenerator().addDependency(file);
            } else {
                getCodeGenerator().addDependency(file, path);
            }
        }
    }

    @Override
    public void clearDependencies() {
        ClearDependencies dep = new ClearDependencies();
        executionManager.add(dep);
    }

    private class ClearDependencies implements Runnable {

        @Override
        public void run() {
            getCodeGenerator().clearDependencies();
        }
    }
    
    @Override
    public void clearUserPlugins() {
        ClearDependencies dep = new ClearDependencies();
        executionManager.add(dep);
    }

    private class ClearUserPlugins implements Runnable {

        @Override
        public void run() {
            getCodeGenerator().clearPlugins();
        }
    }

    @Override
    public void setMain(String main) {
        SetMain m = new SetMain();
        m.path = main;
        executionManager.add(m);
    }

    private class SetMain implements Runnable {

        String path;

        @Override
        public void run() {
            main = path;
            File file = new File(main);
            getCodeGenerator().setMainFile(file);
        }
    }

    @Override
    public void setDistributionFolder(File file) {
        SetDistributionFolder folder = new SetDistributionFolder();
        folder.distribution = file;
        this.executionManager.add(folder);
    }

    private class SetDistributionFolder implements Runnable {

        public File distribution;

        @Override
        public void run() {
            getCodeGenerator().setDistributionFolder(distribution);
        }
    }
    
    @Override
    public int getNumVerifiedExamples() {
        return numVerifiedExamples;
    }

    @Override
    public int getNumNonCompilingExamples() {
        return numNonCompilingExamples;
    }

    @Override
    public int getNumCrashingExamples() {
        return numCrashingExamples;
    }
    
    @Override
    public int getNumMissingExamples() {
        return numMissingExamples;
    }
}
