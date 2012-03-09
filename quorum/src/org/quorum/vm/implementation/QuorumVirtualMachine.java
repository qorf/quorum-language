/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.vm.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.stringtemplate.*;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.CodeCompletionItem;
import org.quorum.vm.interfaces.CodeCompletionRequest;
import org.quorum.vm.interfaces.CodeCompletionResult;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.LibraryIndexEntry;
import org.quorum.vm.interfaces.VariableWatch;
import org.quorum.vm.interfaces.VirtualMachineEvent;
import org.quorum.documentation.DocumentationGenerator;
import org.quorum.documentation.DocumentationStyle;
import org.quorum.documentation.TracWikiDocumentationGenerator;
import org.quorum.execution.DataEnvironment;
import org.quorum.execution.Execution;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.Linker;
import org.quorum.parser.QuorumLexer;
import org.quorum.parser.QuorumParser;
import org.quorum.parser.QuorumParser.start_return;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.execution.TimeStamp;
import org.quorum.parser.QuorumTreeWalker;
import org.quorum.steps.ClassExecution;
import org.quorum.steps.ContainerExecution;
import org.quorum.steps.IntermediateExecutionBuilder;
import org.quorum.steps.NullIntermediateStep;
import org.quorum.steps.ObjectInitPopStep;
import org.quorum.symbols.AccessModifierEnum;
import org.quorum.symbols.BlockDescriptor;
import org.quorum.symbols.ClassDescriptor;
import org.quorum.symbols.Documentation;
import org.quorum.symbols.FileDescriptor;
import org.quorum.symbols.MethodDescriptor;
import org.quorum.symbols.SymbolTable;
import org.quorum.symbols.TypeChecker;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.symbols.VariableDescriptor;
import org.quorum.symbols.VariableParameterCommonDescriptor;

/**
 * This virtual machine implements the Quorum Programming language.
 * 
 * @author Andreas Stefik
 */
public class QuorumVirtualMachine extends AbstractVirtualMachine {

    private boolean parsed = false;
    private boolean buildAllEvent = false;
    private int errors = 0;
    private File currentFile;
    private IntermediateExecutionBuilder builder;
    private HashMap<String, QuorumFile> parseHash;
    private CompilerErrorManager compilerErrors;
    private DocumentationGenerator documentor = DocumentationStyle.getDocumentationGenerator(DocumentationStyle.TRAC_WIKI);
    private static final String USE = "use";
    private static final Logger logger = Logger.getLogger(QuorumVirtualMachine.class.getName());
    //private BuildManager buildManager;
    
    public QuorumVirtualMachine() {
        compilerErrors = new CompilerErrorManager();
        builder = new IntermediateExecutionBuilder();
        builder.setVirtualMachine(this);
        parseHash = new HashMap<String, QuorumFile>();
        typeChecker = new TypeChecker();
        standardLibrary = new QuorumStandardLibrary();
        generator = new QuorumBytecodeGenerator();
        //buildManager = new BuildManager();
    }

    private void resetBuild() {
        //TODO: Change this to be more efficient
        table = new SymbolTable();
        table.setVirtualMachine(this);
        getTypeChecker().clear();
        parseHash.clear();
        currentFile = null;
        errors = 0;
        this.getCompilerErrors().clear();
        builder.clear();
        //matcher.clear();
        getExecution().clear();
        getExecution().restartExecution();
        this.getDataEnvironment().clear();
        getPluginManager().clear();
    }

    @Override
    public boolean generateDocumentation() {
        if(!(new File(documentationPath)).isDirectory()) {
            return false;
        }
        Iterator<LibraryIndexEntry> allClasses = this.standardLibrary.findAllClasses();
        HashMap<String, File> files = new HashMap<String, File>();
        File rootFolder = this.standardLibrary.getRootFolder();
        String absolutePath = rootFolder.getAbsolutePath();
        while(allClasses.hasNext()) {
            LibraryIndexEntry next = allClasses.next();
            String path = next.getPath();
            path = absolutePath + "/" + path;
            files.put(path, new File(path));
        }
        
        Iterator<ContainerExecution> containers = this.builder.getContainers();
        while(containers.hasNext()) {
            ContainerExecution containerExec = containers.next();
            String containerKey = containerExec.getStaticKey();
            Iterator<ClassExecution> classes = containerExec.getClasses();
            while(classes.hasNext()) {
                ClassExecution classExec = classes.next();
                
                ClassDescriptor clazz = classExec.getClassDescriptor();
                String fileKey = clazz.getFile().getStaticKey();
                if(!files.containsKey(fileKey)) {
                    files.put(fileKey, new File(fileKey));
                }
            }
        }
        File[] toArray = files.values().toArray(new File[files.size()]);
        
        generateAllDocumentation(toArray);
        
        return true;
    }
    
    public boolean generateAllDocumentation(File[] files) {
        TracWikiDocumentationGenerator doc = new TracWikiDocumentationGenerator();
        this.build(files);
        Iterator<ContainerExecution> containers = this.builder.getContainers();
        while(containers.hasNext()) {
            ContainerExecution containerExec = containers.next();
            Iterator<ClassExecution> classes = containerExec.getClasses();
            while(classes.hasNext()) {
                ClassExecution classExec = classes.next();
                ClassDescriptor clazz = classExec.getClassDescriptor();
                String result = doc.generate(clazz);
                documentationToFile(clazz, result);
            }
        }
        return true;
    }
    private void documentationToFile(ClassDescriptor clazz, String string){
        String root = documentationPath;
        
        String container = clazz.getContainer().getContainer();
        container = container.replace('.', '/');
        String[] split = container.split("/");
        
        File file = new File(root);
        if(file.isDirectory()) {
            //make sure all subfolders are in there
            for(int i = 0; i < split.length; i++) {
                String current = "";
                for(int j = 0; j <= i; j++) {
                    current += split[j] + "/";
                    
                }
                File folder = new File(root + "/" + current);
                if(!folder.exists()) {
                    folder.mkdirs();
                }
            }
            
            //write the string to a newly created file, if it does not exist
            try {
                File result = new File(root + "/" + container + "/" + clazz.getName() + ".wiki");
                Writer out = null;

                if(result.isFile()) {
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
     * This test function allows you to run the virtual machine raw,
     * in block mode. It is useful for running tests, but should not
     * be called unless the desired behavior is to block the current
     * thread.
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
        if(building) {
            return;
        }
        try {
            //phase 1, 2 lexing and parsing
            removeFromBuild(file);
            this.compilerErrors.setErrorKey(file.getAbsolutePath());
            parse(file);

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
        }
        catch(Exception exception) {
            logger.log(Level.INFO, "The Quorum Compiler threw an exception in buildSingle(File).", exception);
        }
    }

    /**
     * This method uses the file as a key for where in the parse the system
     * is, but actually computes the parse from the String text. This allows
     * us to fix the bug
     *
     * @param file
     * @param text
     */
    public void buildSingle(File file, String text) {
        if(building) {
            return;
        }
        try {
            //phase 1, 2 lexing and parsing
            removeFromBuild(file);
            this.compilerErrors.setErrorKey(file.getAbsolutePath());
            parseSingle(file, text);
            
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
        }
        catch(Exception exception) {
            logger.log(Level.INFO, "The Quorum Compiler threw an exception in buildSingle(File).", exception);
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
            } else {
                parsed = true;
            }
        } catch (RecognitionException exception) {
            logger.log(Level.INFO, "Exception thrown while trying to parse file "
                    + file.getAbsolutePath() + " with source <quorum>" + 
                    text + "</quorum>", exception);
            parsed = false;
        }
        return true;
    }

    @Override
    public void parseSingle(File file) {
        if(!this.parseHash.containsKey(file.getAbsolutePath())) {
            parse(file);
            getSymbolTable().calculatePackageUseForFile(file.getAbsolutePath());
        }
    }

    private void throwBuildEvent() {
        VirtualMachineEvent event = new VirtualMachineEvent(null, this, false);
        event.setBuildEvent(true);
        event.setBuildSuccessful(this.compilerErrors.isCompilationErrorFree());
        
        if(buildAllEvent){
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
        
        //matcher.clear();
    }

    private void computeStandardLibraryFiles() {
  
        Iterator<File> files = getSymbolTable().getStandardLibraryFiles();
        while(files.hasNext()) {
            //TODO: Fix this to account for recursive File additions.
            //parse all the standard library files
            while(files.hasNext()) {
                File file = files.next();
                if(!this.isFileCached(file.getAbsolutePath())) {
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
//        try {
//            buildActual(source);
//        }
//        catch(Exception exception) {
//            logger.log(Level.INFO, "The Quorum Compiler threw an exception in build(File[]).", exception);
//        }
    }
    
    private class Build implements Runnable{
        public File[] source;
        @Override
        public void run() {
            try {
                buildActual(source);
            }
            catch(Exception exception) {
                logger.log(Level.INFO, "The Quorum Compiler threw an exception in build(File[]).", exception);
            }
        }
    }
    
    @Override
    public void build(File[] source, boolean block) {
        Build build = new Build();
        build.source = source;        
        if(block) {
            build.run();
        }
        else {
            executionManager.add(build);
        }
    }
    
    /**
     * This function builds source code directly from a string that is
     * not necessarily saved to disk. Since quorum requires files be 
     * associated with the source code, Quorum internally creates a fake
     * file with a unique key.
     * 
     * @param source 
     */
    public void build(String source) {
        File main = new File("InvalidNotOnDiskUniqueKey2340238746293141920348293847");
        this.setMain(main.getAbsolutePath());
        
        this.parseSingle(main, source);
        
        computeStandardLibraryFiles();
        getSymbolTable().compilePackageUseTables();
        getSymbolTable().resolveAllMethods();
        getSymbolTable().resolveInheritance();
        getSymbolTable().compileTypeCheckingTables(typeChecker);


        //now do semantic analysis on all of the files
        //if there were no compiler errors
        if(this.getCompilerErrors().isCompilationErrorFree()) {
            Iterator<QuorumFile> it = parseHash.values().iterator();
            while(it.hasNext()) {
                QuorumFile file = it.next();
                this.compilerErrors.setErrorKey(file.getFile().getAbsolutePath());
                semanticAnalysis(file);
            }
        }
        //if it worked, link it
        link();
    }
    
    
    private void link() {
        if(this.getCompilerErrors().isCompilationErrorFree()) {
            Linker linker = new Linker();
            linker.setMachine(this);
            linker.link(builder);
            Vector<ExecutionStep> steps = linker.getLinkedSteps();
            vTable = linker.getVTable();
            this.getExecution().addStep(steps);
            if(this.isGenerateCode()) {
                QuorumBytecodeGenerator gen = (QuorumBytecodeGenerator) this.generator;
                gen.setLinker(linker);
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
     * This function builds the source, but does so in an unsafe way to 
     * allow automated testers to check for any potential problems with exceptions
     * or other issues.
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
        if(this.getCompilerErrors().isCompilationErrorFree()) {
            Iterator<QuorumFile> it = parseHash.values().iterator();
            while(it.hasNext()) {
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
            while(it.hasNext()) {
                QuorumFile file = it.next();
                this.compilerErrors.setErrorKey(file.getFile().getAbsolutePath());
                generateJavaCode(file);
            }
        }

        buildAllEvent = true;
        throwBuildEvent();
        building = false;
    }
    /**
     * Determines whether the current file has already been parsed.
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

    private QuorumFile removeQuorumFileFromCache(File file) {
        return parseHash.remove(file.getAbsolutePath());
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
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream inp = classLoader.getResourceAsStream("org/quorum/parser/Quorum.stg");
                InputStreamReader groupFileR = new InputStreamReader(inp);
                StringTemplateGroup templates = new StringTemplateGroup(groupFileR);
                groupFileR.close();
                builder.setCurrentFileKey(file.getFile().getAbsolutePath());
                table.enterFile(file.getFile().getAbsolutePath());
                CommonTreeNodeStream nodes = new CommonTreeNodeStream(file.getSyntaxTree());
                nodes.setTokenStream(file.getTokens());
                QuorumTreeWalker symbol = new QuorumTreeWalker(nodes);
                //symbol.setTemplateLib(templates);
                symbol.setGrammarFileNameNoExtension(stripFileName(file.getFile().getName()));
                symbol.setQuorumVirtualMachine(this);
                //file.setTree(symbol.start());
                QuorumTreeWalker.start_return tree = symbol.start();
                file.setTree(tree);
            } catch (RecognitionException exception) {
                logger.log(Level.INFO, "Could not do IO operation in file: " + file.getFile().getAbsolutePath(), exception);
            } catch (IOException exception) {
                logger.log(Level.INFO, "Could not do IO operation in file: " + file.getFile().getAbsolutePath(), exception);
            }
        }
        return true;
    }

    private boolean generateJavaCode(QuorumFile file) {
        if (parsed) {
            /*try{
                String filename = stripFileName(file.getFile().getName());
                StringTemplate output = (StringTemplate)file.getTree().getTemplate();
                FileWriter out = new FileWriter(filename + ".java");
                if (output != null){
                    out.write(output.toString());}
                out.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }*/
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
//        resetBuild();
//        //check the build and distribution folders and delete them if they exist
//        File build = this.getCodeGenerator().getBuildFolder();
//        File distribute = this.getCodeGenerator().getDistributionFolder();
//        delete(build);
//        delete(distribute);
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
        if(block) {
            clean.run();
        }
        else {
            executionManager.add(clean);
        }
    }
    
    /**
     * This method deletes a folder from the system.
     * 
     * @param dir 
     */
    private static void delete(File dir) {
        if(dir == null || !dir.exists()) {
            return;
        }
        
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                delete(new File(dir, children[i]));
            }
        }
        dir.delete();
    }

    @Override
    public ExpressionValue createExpressionValue(TypeDescriptor type) {
        
        ExpressionValue value = null;
        if(type.isPrimitiveType()) {
            value = ExpressionValue.getPrimitiveDefault(type);
        }
        else {
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
            while(!finished) {
                ExecutionStep step = execution.step();
                ExecutionStep nextStep = execution.getNextStep();
                if(de.getObject(hash).equals(de.getThisObject()) && ro.isThisMode() && nextStep instanceof ObjectInitPopStep) {
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
        //VariableParameterCommonDescriptor associatedObjectVariable = variable.getAssociatedObjectVariable();
        de.removeObject(variable.getHashKey());

        de.callStackUndo();
        de.callingClassStackUndo();

        //return to original scope
        int newScope = de.callingClassStackPeek().getHashKey();
        de.setThisPointer(newScope);

        //de.callingClassStackUndo();

        getDataEnvironment().undoCreateObjectOpcode();
    }

    
    @Override
    public CodeCompletionResult requestCodeCompletionResult(CodeCompletionRequest request) {
        CodeCompletionResult result = new CodeCompletionResult();
        String line = request.getLine();
        line = line.trim();
        String sub = request.getLine().substring(0, request.getStartOffset());
        boolean isDot = false;
        
        if(sub.length() > 0){
            char c = sub.charAt(sub.length() - 1);
            if(c == '.') {
                isDot = true;
            }
        }
        
        boolean useStart = line.startsWith(USE);
        if(useStart) {
            addUseResults(result, request);
        }
        else if(!useStart && !isDot) { //if it is a dot request, ignore it.
            addExpressionResults(result, request);
        }
        
        return result;
    }
    
    /**
     * This method calculates the possible options for the use statement.
     * 
     * @param result
     * @param line 
     */
    private void addUseResults(CodeCompletionResult result, CodeCompletionRequest request) {
        String line = request.getLine();
        line = line.substring(USE.length(), line.length());
        line = line.trim();
        String[] split = line.split("\\.");
        
        if(split.length > 0  && line.length() != 0) {
            if(line.charAt(line.length() - 1) == '.') {
                result.setFilter("");
            }
            else {
                result.setFilter(split[split.length - 1]);
            }
        }
        
        final String root = this.standardLibrary.getStandardLibraryRootName();
        if(split.length == 1) {
            String left = split[0];
            if(left.matches("\\s*") || result.getFilter().length() != 0) {
                CodeCompletionItem item = new CodeCompletionItem();
                item.setCompletion(root);
                item.setDisplayName(root);
                result.add(item);
            }
            else if(left.equals(root)) {
                addSubpackagesAndClasses(result, standardLibrary.getStandardLibraryRootName());
            }
        }
        else {
            String pack = "";
            int length = split.length;
            if(result.getFilter().length() != 0) {
                length--;
            }
            for(int i = 0; i < length; i++) {
                if(i == 0) {
                    pack += split[i];
                }
                else {
                    pack += "." + split[i];
                }
            }
            addSubpackagesAndClasses(result, pack);
        }
    }
    
    private void addSubpackagesAndClasses(CodeCompletionResult result, String name) {
        Iterator<String> subs = standardLibrary.findAllSubpackages(name);
        while(subs.hasNext()) {
            String next = subs.next();
            CodeCompletionItem item = new CodeCompletionItem();
            item.setCompletion(next);
            item.setDisplayName(next);
            result.add(item);
        }
        Iterator<LibraryIndexEntry> classes = standardLibrary.findAllClassesInPackage(name);
        while(classes.hasNext()) {
            LibraryIndexEntry next = classes.next();
            CodeCompletionItem item = new CodeCompletionItem();
            item.setCompletion(next.getName());
            item.setDisplayName(next.getName());
            result.add(item);
        }
    }
    
    private void addExpressionResults(CodeCompletionResult result, CodeCompletionRequest request) {
        String expression = request.getLine().substring(0, request.getStartOffset());
        
        int begin = 0;
        int end = 0;
        int index = request.getStartOffset() - (request.getLine().length() - expression.length());
        if(index >= expression.length()) {
            index = expression.length() - 1;
        }
        
        while(index > 0) {
            if(Character.isWhitespace(expression.charAt(index)) ||
               expression.charAt(index) == '=') {
                expression = expression.substring(index + 1, request.getStartOffset());
                expression = expression.trim();
                index = -1;
            }
            index = index - 1;
        }
        //now split the string into pieces
        String[] split = expression.split(":");
        
        if(split.length > 0 && expression.length() != 0) {
            if(expression.charAt(expression.length() - 1) == ':') {
                result.setFilter("");
            }
            else {
                result.setFilter(split[split.length - 1]);
            }
        }
        
        FileDescriptor file = this.getSymbolTable().getFileDescriptor(request.getFileKey());
        ClassDescriptor clazz = null;
        if(file != null) {
            Iterator<ClassDescriptor> classIterator = file.getClassIterator();
            while(classIterator.hasNext()) {
                ClassDescriptor next = classIterator.next();
                if( (request.getLineNumber() >= next.getLineBegin() && request.getLineNumber() <= next.getLineEnd())
                   || (next.getLineBegin() == 1 && next.getLineEnd() == 0) //must be a file with no explicit class definition
                        ) {
                    clazz = next;
                }
            }
        }
        final String me = "me";
        final String parent = "parent";
        
        if(clazz != null) {
            MethodDescriptor method = clazz.getMethodAtLine(request.getLineNumber());
            //if the method is null, it must be a class variable (or garbage)
            boolean isMe = false;
            if(method != null) {
                if(split.length == 1) {
                    String left = split[0];
                    if(left.equals(me)){
                        isMe = true;
                    }
                    if(left.equals(me) || result.getFilter().length() > 0) {
                        addClassToResult(result, clazz, isMe);
                    }
                    if(left.equals(parent)) {
                        addParentClasses(result, clazz);
                    }
                    else if(left.matches("\\s*")) {
                        isMe = true;
                        addClassToResult(result, clazz, isMe);
                    }
                    else {
                        VariableParameterCommonDescriptor variable = method.getVariable(left);
                        if(variable == null) {
                            variable = findVariableInBlocks(request, method, left);
                        }
                        if(variable != null) {
                            String staticKey = variable.getType().getStaticKey();
                            addToCodeCompletionResult(result, staticKey, clazz);
                        }
                    }
                }
                else if(split.length == 0) {
                    return;
                }
                else { //do fancier parsing
                    String left = split[0];
                    
                    if(left.equals(parent)) {
                        if(split.length > 2) {
                            String resolvedName = clazz.resolveParentName(split[1]);
                            ClassDescriptor par = clazz.getParent(resolvedName);
                            if(par != null) {
                                addClassToResult(result, par, isMe);
                            }
                        }
                        else if(split.length == 2) {
                            if(result.getFilter().length() == 0) {
                                String resolvedName = clazz.resolveParentName(split[1]);
                                ClassDescriptor par = clazz.getParent(resolvedName);
                                if(par != null) {
                                    addClassToResult(result, par, isMe);
                                }
                            }
                            else {
                                addParentClasses(result, clazz);
                            }
                        }
                        
                    }
                    else if(left.equals(me)) {
                        isMe = true;
                        addClassToResult(result, clazz, isMe);
                    }
                    else { //This should work until chaining is in place.
                        VariableParameterCommonDescriptor variable = method.getVariable(left);
                        if(variable == null) {
                            variable = findVariableInBlocks(request, method, left);
                        }
                        if(variable != null) {
                            String staticKey = variable.getType().getStaticKey();
                            addToCodeCompletionResult(result, staticKey, clazz);
                        }
                    }
                }
            }
        }
    }
    
    private void addParentClasses(CodeCompletionResult result, ClassDescriptor clazz) {
        Iterator<ClassDescriptor> parents = clazz.getFlattenedListOfParents();
        while(parents.hasNext()) {
            ClassDescriptor next = parents.next();
            CodeCompletionItem item = getClassCompletionItem(next);
            result.add(item);
        }
    }
    
    private CodeCompletionItem getClassCompletionItem(ClassDescriptor clazz) {
        CodeCompletionItem item = new CodeCompletionItem();
                
                
        String signature = clazz.getStaticKey();
        String name = clazz.getName();

        String description = "";
        description += "<h1>" + signature + "</h1>";
        String[] paragraphs = Documentation.breakStringIntoParagraphArray(clazz.getDocumentation().getDescription());
        for(int i = 0; i < paragraphs.length; i++) {
            description += "<p>" + paragraphs[i] + "</p>";
        }

        description += "<h2>" +"Code Example:"+ "</h2>";
        description += "<PRE><CODE>" + clazz.getDocumentation().getExample() +
                "</PRE></CODE>";

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
        while(children.hasNext() && !done) {
            BlockDescriptor block = children.next();
            if(request.getLineNumber() >= block.getLineBegin() 
                    && request.getLineNumber() <= block.getLineEnd()) {
                done = true;
                variable = block.getVariable(key);
                if(variable == null){
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
        while(children.hasNext() && !done) {
            BlockDescriptor next = children.next();
            if(request.getLineNumber() >= next.getLineBegin() 
                    && request.getLineNumber() <= next.getLineEnd()) {
                done = true;
                variable = next.getVariable(key);
                if(variable == null){
                    variable = findVariableInBlocks(request, next, key);
                }
            }
        }
        return variable;
    }
    
    private void addToCodeCompletionResult(CodeCompletionResult result, String classStaticKey, ClassDescriptor containingClass) {
        ClassDescriptor clazz = this.getSymbolTable().getClassDescriptor(classStaticKey);
        if(clazz == null) { //can the name be resolved from the parser?
            if(classStaticKey.length() > 0) {
                ClassDescriptor validatedClassUse = containingClass.getValidatedClassUse(classStaticKey);
                clazz = validatedClassUse;
            }
        }
        addClassToResult(result, clazz, false);
    }
    
    private void addClassToResult(CodeCompletionResult result, ClassDescriptor clazz, boolean isCurrentClass) {
        if(clazz != null) {
            //add all of its methods
            Collection<MethodDescriptor> allMethods = clazz.getAllMethods(AccessModifierEnum.PUBLIC);
            
            if(isCurrentClass){
                allMethods = clazz.getAllMethods();
            }
            
            Iterator<MethodDescriptor> iterator = allMethods.iterator();
            while(iterator.hasNext()) {
                MethodDescriptor method = iterator.next();
                CodeCompletionItem item = new CodeCompletionItem();
                
                
                String signature = method.getMethodSignature(true);
                TypeDescriptor returnType = method.getReturnType();
                if(!returnType.isVoid()) {
                    if(returnType.isTemplated()) {
                        signature += " returns " + returnType.getTemplateName();
                    }
                    else {
                        signature += " returns " + returnType.getStaticKey();
                    }
                }
                
                String description = "";
                String[] paragraphs = Documentation.breakStringIntoParagraphArray(method.getDocumentation().getDescription());
                for(int i = 0; i < paragraphs.length; i++) {
                    description += "<p>" + paragraphs[i] + "</p>";
                }
                
                description += "<h2>" +"Code Example:"+ "</h2>";
                description += "<PRE><CODE>" + method.getDocumentation().getExample() +
                        "</PRE></CODE>";
                
                item.setDisplayName(signature);
                item.setDocumentation(description);
                item.setCompletion(method.getName());
                result.add(item);
            }
            addVariablesToResults(result, clazz, isCurrentClass);
        }
    }
    
    public void addVariablesToResults(CodeCompletionResult result, ClassDescriptor clazz, boolean isCurrentClass){
            //add all of its methods
            Collection<VariableDescriptor> allMethods = clazz.getAllClassVariables();
            
            Iterator<VariableDescriptor> iterator = allMethods.iterator();
            while(iterator.hasNext()) {
                VariableDescriptor variable = iterator.next();
                CodeCompletionItem item = new CodeCompletionItem();
                
                
                String signature = variable.getName();
                TypeDescriptor type = variable.getType();
                String displayType = "";
                if(!type.isVoid()) {
                    if(type.isTemplated()) {
                        displayType = type.getTemplateName();
                    }
                    else {
                        displayType = type.getStaticKey();
                    }
                }
                
                String description = "";
                String[] paragraphs = Documentation.breakStringIntoParagraphArray(variable.getDocumentation().getDescription());
                for(int i = 0; i < paragraphs.length; i++) {
                    description += "<p>" + paragraphs[i] + "</p>";
                }
                
                description += "<h2>" +"Code Example:"+ "</h2>";
                description += "<PRE><CODE>" + variable.getDocumentation().getExample() +
                        "</PRE></CODE>";
                item.setDisplayName(signature);
                item.setDisplayType(displayType);
                item.setDocumentation(description);
                item.setCompletion(variable.getName());
                result.add(item);
            }
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
    
    private class AddDependency implements Runnable{
        private File file;
        private String path;

        @Override
        public void run() {
            if(path == null) {
                getCodeGenerator().addDependency(file);
            }
            else {
                getCodeGenerator().addDependency(file, path);
            }
        }
    }
    
    @Override
    public void clearDependencies() {
        ClearDependencies dep = new ClearDependencies();
        executionManager.add(dep);
    }
    
    private class ClearDependencies implements Runnable{
        @Override
        public void run() {
            getCodeGenerator().clearDependencies();
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
}
