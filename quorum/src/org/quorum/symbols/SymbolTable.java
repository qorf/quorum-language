/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.symbols;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.ErrorType;
import org.quorum.vm.interfaces.LibraryIndexEntry;
import org.quorum.vm.interfaces.StandardLibrary;

/**
 * This class is a symbol table implementation. It stores static information
 * regarding files, classes, methods, variables, and parameters, for use
 * by the virtual machine in computing or executing code. This implementation
 * only stores static analysis. For dynamic analysis, see the sister class,
 * org.sonify,vm.execution.DataEnvironment.
 * 
 * @author Andreas Stefik
 */
public class SymbolTable {

    private HashMap<String, FileDescriptor> fileMap;
    private HashMap<String, MethodDescriptor> mainMethods;
    private HashMap<String, ClassDescriptor> classes;
    /**
     * A tool for analyzing control flow issues in the program.
     */
    private ControlFlowAnalyzer controlFlow;
    private HashMap<String, Boolean> scopes;
    private HashMap<String, String> indexer;
    /**
     * This hash stores classes according to their name and returns a
     * hash of class descriptors. This second hash uses a "container" string
     * as its value. For example, I could search for the class "Main" and this
     * would return to me a hash of all containers named Main. In this second
     * hash, I could then iterate over all members or search if there was
     * a "Main" class in a particular container. 
     */
    private HashMap<String, HashMap<String, ClassDescriptor>> classNames;
    /**
     * This hash stores a hash of all containers that are stored on the system
     * and returns a hash that contains all classes in that container.
     */
    private HashMap<String, HashMap<String, ClassDescriptor>> classContainers;
    private FileDescriptor currentFile;
    private String MAIN = "Main";
    private AbstractVirtualMachine virtualMachine;
    private Scopable currentScope;
    private ClassDescriptor currentClass;
    private MethodDescriptor currentMethod;
    private BlueprintDescriptor currentBlueprint;
    private SystemActionDescriptor currentSystemAction;

    public SymbolTable() {
        fileMap = new HashMap<String, FileDescriptor>();
        mainMethods = new HashMap<String, MethodDescriptor>();
        classes = new HashMap<String, ClassDescriptor>();
        classNames = new HashMap<String, HashMap<String, ClassDescriptor>>();
        classContainers = new HashMap<String, HashMap<String, ClassDescriptor>>();
        scopes = new HashMap<String, Boolean>();
        controlFlow = new ControlFlowAnalyzer();
        indexer = new HashMap<String, String>();
    }

    /**
     * Adds a new class into the symbol table. This method stores the class
     * in a number of places so that it can be retrieved and searched by
     * an IDE.
     * @param descriptor
     */
    public void add(ClassDescriptor descriptor) {
        //Later on we'll need to stick the class into a package or namespace etc.
        //for now just stick it in a global HashMap of classes
        currentScope = descriptor;
        currentClass = descriptor;
        currentFile.add(descriptor);
        descriptor.setFile(currentFile);
        classes.put(descriptor.getStaticKey(), descriptor);
        descriptor.setVirtualMachine(virtualMachine);

        CompilerError error;
        CompilerError error2;
        error = addClassToCustomHash(descriptor, classContainers, descriptor.getContainer().getContainer(), descriptor.getName());
        //report an error if necessary
        if (error != null) {
            virtualMachine.getCompilerErrors().addError(error);
        }

        error2 = addClassToCustomHash(descriptor, classNames, descriptor.getName(), descriptor.getContainer().getContainer());
        //only put in the one error if necessary.
        if (error2 != null && error == null) {
            virtualMachine.getCompilerErrors().addError(error);
        }
    }

    /**
     * Adds a method to the current scope, then switches the current scope to be the method
     * @param descriptor the method to add
     */
    public CompilerError add(MethodDescriptor descriptor) {
        CompilerError error = currentScope.add(descriptor);

        //Bug Fix #52 -- Andrew Hauck
        //Check to make sure multiple main instances don't exist
        if (descriptor.getName().equalsIgnoreCase(MAIN)) {
            String mainKey = getMainKey(currentFile.getName(), currentScope.toString(), descriptor.getName());
            String scopeKey = getScopeKey(currentFile.getName(), currentScope.toString(), descriptor.getName());

            if (mainMethods.get(mainKey) == null && scopes.get(scopeKey) != null) {
                CompilerError mainError = new CompilerError();
                mainError.setLineNumber(descriptor.getLineBegin());
                mainError.setColumn(descriptor.getColumnBegin());
                mainError.setError(descriptor.getName() + " has already been defined.");
                mainError.setErrorType(ErrorType.METHOD_DUPLICATE);
                return mainError;
            } else {
                scopes.put(scopeKey, Boolean.TRUE);
                mainMethods.put(mainKey, descriptor);
            }
        }

        currentScope = descriptor;
        currentMethod = descriptor;
        return error;
    }

    /**
     * Add a blueprint to the current scope.
     *
     * @param descriptor
     * @return
     */
    public CompilerError add(BlueprintDescriptor descriptor){
        CompilerError error = currentScope.add(descriptor);

        currentBlueprint = descriptor;
        return error;
    }

    /**
     * Add a system action (call down) to the current scope.
     *
     * @param descriptor
     * @return
     */
    public CompilerError add(SystemActionDescriptor descriptor){
        CompilerError error = currentScope.add(descriptor);

        currentSystemAction = descriptor;
        return error;
    }

    /**
     * Stores information regarding a new variable into the current scope.
     *
     * @param descriptor
     * @return
     */
    public CompilerError add(VariableDescriptor descriptor) {
        return currentScope.add(descriptor);
    }

    /**
     * Stores information regarding a new parameter into the current method.
     *
     * @param descriptor
     * @return
     */
    public CompilerError add(ParameterDescriptor descriptor) {
        return currentScope.add(descriptor);
    }

    /**
     * Adds a new block into the vm, with its own scope.
     *
     * @param descriptor
     */
    public void add(BlockDescriptor descriptor) {
        currentScope.add(descriptor);
        descriptor.setParent(currentScope);
        currentScope = descriptor;
    }

    /**
     * Adds a new file into the VM, which may contain classes, methods,
     * objects, or other code.
     * @param descriptor
     */
    public void add(FileDescriptor descriptor) {
        String path = descriptor.getFile().getAbsolutePath();
        if (!fileMap.containsKey(path)) {
            fileMap.put(path, descriptor);
            currentFile = descriptor;
        } else {
            //the file has already been read, throw an exception.
            throw new RuntimeException("File " + path + " has already been read");
        }
    }

    /**
     * Adds a key to the current file descriptor indicating that a statement
     * is located at that particular line.
     * @param line
     */
    public void addStatementFlagToCurrentFile(int line) {
        currentFile.addStatementLine(line);
        if (currentMethod != null) {
            currentMethod.addStatementLine(line);
        }
    }

    /**
     * This function adds classes to various kinds of search hashes in order
     * to make it easier for the IDE to search for information about the build.
     * @param descriptor
     * @param hash
     * @param key
     * @return
     */
    private CompilerError addClassToCustomHash(ClassDescriptor descriptor, HashMap<String, HashMap<String, ClassDescriptor>> hash, String key1, String key2) {
        HashMap<String, ClassDescriptor> map = hash.get(key1);

        if (map == null) { //make a new map
            map = new HashMap<String, ClassDescriptor>();
            hash.put(key1, map);
        }

        //now determine whether there is a class by the current name in the map
        ClassDescriptor alreadyExists = map.get(key2);
        if (alreadyExists == null) { //add the class to the map
            map.put(key2, descriptor);
            return null;
        } else { //throw a compiler error, this class already exists.
            CompilerError error = new CompilerError(descriptor.getLineBegin(),
                    "Class " + descriptor.getName() + " is already defined.", ErrorType.CLASS_DUPLICATE);
            return error;
        }

    }

    /**
     * This method analyzes one file to determine which classes
     * it can access from its use statements.
     * @param descriptor
     */
    public void compilerPackageUseTables(FileDescriptor descriptor) {
        Iterator<ClassDescriptor> it = descriptor.getClassIterator();
        while (it.hasNext()) {
            ClassDescriptor cd = it.next();

            Iterator<UseDescriptor> uses = cd.getUses();
            while (uses.hasNext()) {
                UseDescriptor use = uses.next();
                calculateUseForClass(cd, use);
            }
        }
    }

    /**
     * Allows you to recompute the use and package references for just
     * one file on the system.
     * @param file
     */
    public void calculatePackageUseForFile(String absolutePathToFile) {
        FileDescriptor fd = fileMap.get(absolutePathToFile);
        if (fd == null) { //no file by that name. Exception?
            throw new RuntimeException("File not found.");
        }

        compilerPackageUseTables(fd);
    }

    /**
     * Looks at a class descriptor and a use descriptor inside of that
     * table to analyze and track what words are usable via that
     * class.
     * @param cd
     * @param use
     */
    private void calculateUseForClass(ClassDescriptor cd, UseDescriptor use) {
        if (use.isLastNamePackageReference()) {
            String name = use.getNameWithoutLast();
            //check to see if this is a valid package
            HashMap<String, ClassDescriptor> map = classContainers.get(name);

            if (map != null) { //cache all references in the current class
                Iterator<ClassDescriptor> itClasses = map.values().iterator();
                while (itClasses.hasNext()) {
                    ClassDescriptor cacheMe = itClasses.next();
                    CompilerError error = cd.addValidatedUse(cacheMe, use);
                    if (error != null) {
                        this.getVirtualMachine().getCompilerErrors().addError(error);
                    }
                }
            } else { //this is a compiler error, it does not reference a proper package
                CompilerError error = new CompilerError(use.getLineBegin(),
                        "Package " + use.getNameWithoutLast() + " does not"
                        + " exist. Did you spell it correctly?", ErrorType.MISSING_USE);
                        CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                        errorManager.setErrorKey(currentFile.getFile().getAbsolutePath());
                        errorManager.addError(error);
            }
        } else { //this use refers to a particular class, not a package
            String name = use.getNameWithoutLast();
            HashMap<String, ClassDescriptor> map = classContainers.get(name);

            if (map != null) { //this is a valid package
                ClassDescriptor cacheMe = map.get(use.getLastName());
                if (cacheMe != null) {
                    CompilerError error = cd.addValidatedUse(cacheMe, use);
                    if (error != null) {
                        this.getVirtualMachine().getCompilerErrors().addError(error);
                    }
                } else { //the package exists, but the class doesn't
                    CompilerError error = new CompilerError(use.getLineBegin(),
                            "Package " + use.getNameWithoutLast() + " exists, but there is"
                            + " no class " + use.getLastName()
                            + " inside of it. Did you spell its name correctly?", ErrorType.MISSING_USE);
                        CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                        errorManager.setErrorKey(currentFile.getFile().getAbsolutePath());
                        errorManager.addError(error);
                }
            } else { //this package does not exist, throw a compiler error

                //is this package in the standard library?
                StandardLibrary library = getVirtualMachine().getStandardLibrary();
                if (!library.doesPackageExist(name)) {
                    CompilerError error = new CompilerError(use.getLineBegin(),
                            "Package " + use.getNameWithoutLast() + " does not"
                            + " exist. Did you spell it correctly?", ErrorType.MISSING_USE);
                        CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                        errorManager.setErrorKey(currentFile.getFile().getAbsolutePath());
                        errorManager.addError(error);
                } else {//see if the file exists, and if it does, parse it
                    File entry = library.findClass(name, use.getLastName());
                    if (entry == null) {
                        CompilerError error = new CompilerError(use.getLineBegin(),
                                "Package " + use.getNameWithoutLast() + " exists, but there is"
                                + " no class " + use.getLastName()
                                + " inside of it. Did you spell its name correctly?", ErrorType.MISSING_USE);
                        CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                        errorManager.setErrorKey(currentFile.getFile().getAbsolutePath());
                        errorManager.addError(error);
                    } else {
                        //retrieve the file that is required and send it
                        //to the VM for parsing, but NOT semantic analysis.
                        //       getVirtualMachine().parseSingle(entry);

                        //validate the class's usage
                        ClassDescriptor desc = this.findClass(use.getLastName(),
                                use.getNameWithoutLast());
                        if (desc != null) { //this is a valid class
                            cd.addValidatedUse(desc, use);
                        } else { //something is seriously wrong
                            throw new RuntimeException("Compiler Bug: Class is both"
                                    + " in and not in the symbol table, which "
                                    + "cannot be true");
                        }

                        //do we need to validate usage?
                    }
                }
            }
        }
    }

    /**
     * Get an iterator of the standard library files.
     * @return
     */
    public Iterator<File> getStandardLibraryFiles() {
        HashMap<String, File> stl = new HashMap<String, File>();
        Iterator<FileDescriptor> it = fileMap.values().iterator();
        while (it.hasNext()) {
            FileDescriptor fd = it.next();
            Collection<File> collection = getStandardLibraryFiles(fd);
            Iterator<File> fit = collection.iterator();
            while (fit.hasNext()) {
                File file = fit.next();
                stl.put(file.getAbsolutePath(), file);
            }
        }

        return stl.values().iterator();
    }

    /**
     * Get a collection of the standard library files.
     *
     * @param fd
     * @return
     */
    public Collection<File> getStandardLibraryFiles(FileDescriptor fd) {
        Vector<File> stl = new Vector<File>();

        Iterator<ClassDescriptor> ci = fd.getClassIterator();
        while (ci.hasNext()) {
            ClassDescriptor cd = ci.next();

            Iterator<UseDescriptor> ui = cd.getUses();
            while (ui.hasNext()) {
                UseDescriptor use = ui.next();

                StandardLibrary library = getVirtualMachine().getStandardLibrary();
                if (use.isLastNamePackageReference()) {//ignore this case for now
                    Iterator<LibraryIndexEntry> entries = library.findAllClassesInPackage(use.getNameWithoutLast());
                    while (entries.hasNext()) {
                        LibraryIndexEntry entry = entries.next();
                        //does this entry match any of the types
                        //that the class has?
                        String name = entry.getFullClassName();
                        ClassDescriptor valid = cd.getValidatedClassUse(name);
                        ClassDescriptor alreadyExists = this.getClassDescriptor(name);
                        if (valid == null && alreadyExists == null) {//no naming conflict
                            File file = library.findClass(entry);
                            stl.add(file);
                        } else if (valid != null && alreadyExists == null) {
                            CompilerError error = new CompilerError(cd.getLineBegin(),
                                    "Cannot define two separate classes in "
                                    + "different packages of type  " + cd.getName(), ErrorType.CLASS_DUPLICATE);
                            this.getVirtualMachine().getCompilerErrors().addError(error);
                        }
                    }




                } else { //this use refers to a particular class, not a package
                    String name = use.getNameWithoutLast();
                    HashMap<String, ClassDescriptor> map = classContainers.get(name);
                    if(map != null) {
                        if(map.get(use.getLastName()) == null) {
                            if (library.doesPackageExist(name)) {//do something if it is in the library
                                File entry = library.findClass(name, use.getLastName());
                                if (entry != null) {
                                    stl.add(entry);
                                }
                            }
                        }
                    }
                    else {
                        if (library.doesPackageExist(name)) {//do something if it is in the library
                            File entry = library.findClass(name, use.getLastName());
                            if (entry != null) {
                                stl.add(entry);
                            }
                        }
                    }
                }
            }
        }




        return stl;
    }

    /**
     * This method analyzes the complete set of files and classes
     * to determine which classes can access which other classes
     * by explicitly declaring it through its use statements.
     */
    public void compilePackageUseTables() {
        Iterator<FileDescriptor> it = fileMap.values().iterator();
        while (it.hasNext()) {
            FileDescriptor fd = it.next();
            compilerPackageUseTables(fd);
        }
    }

    /**
     * This method informs the type checker of all classes currently loaded
     * into the symbol table.
     * 
     * @param checker
     */
    public void compileTypeCheckingTables(TypeChecker checker) {
        Iterator<ClassDescriptor> it = this.getClassDescriptors();
        while (it.hasNext()) {
            checker.addType(it.next());
        }
    }

    /**
     * Resets the symbol table so that it is not acting as if it is
     * "inside" of a particular construct. For example, as we are parsing
     * this method would tell the symbol table it is no longer inside of
     * a given file.
     */
    public void resetCurrentObjects() {
        currentFile = null;
    }

    /**
     * This function returns all classes that happen to go buy the name
     * "clazz." This function is useful when you want to use a class of
     * a given name but you don't have a reference set to the relative location
     * of the class. For example, this method can find all classes that have
     * a given name, which can then be used to pop up a yellow "lightbulb"
     * in NetBeans, with a list of which reference the user is referring to.
     *
     * @param clazz
     * @return
     */
    public Iterator<ClassDescriptor> findClassesByName(String clazz) {
        HashMap<String, ClassDescriptor> map = classNames.get(clazz);
        if (map == null) { //no classes exist with that name
            return null;
        } else { //a hash exists with that name
            return map.values().iterator();
        }
    }

    /**
     * This method returns a class that exists in a particular package. Invalid
     * package names or class names will result in a return value of null.
     * @param clazz
     * @param container
     * @return
     */
    public ClassDescriptor findClass(String clazz, String container) {
        HashMap<String, ClassDescriptor> map = classContainers.get(container);
        if (map == null) {
            return null;
        }

        ClassDescriptor cd = map.get(clazz);
        return cd;
    }

    /**
     * This method returns a class from its fully qualified name. For example,
     * a class called Dog in the default container would be .Dog. Null is
     * returned if no class can be found.
     * 
     * @param clazz
     * @return
     */
    public ClassDescriptor findFullyQualifiedClass(String clazz) {
        String[] split = clazz.split("\\.");
        if (split.length == 1) {
            return null;
        }
        String name = split[split.length - 1];
        String pack = "";
        for (int i = 0; i < split.length - 1; i++) {
            pack += split[i] + ".";
        }
        pack = pack.substring(0, pack.length() - 1);

        ClassDescriptor cd = findClass(name, pack);
        return cd;
    }
    
    /**
     * This method returns a class that maps to a primitive type. For example,
     * text will return the Text class and test will return null.
     * 
     * @param primitive
     * @return 
     */
    public ClassDescriptor findAPrimitivesClass(String primitive){
        ClassDescriptor cd = null;
        if(primitive.equals("text") || primitive.equals("boolean") || primitive.equals("integer") || primitive.equals("number")){
            String name =  primitive;
            name = name.replaceFirst("" + name.charAt(0), ("" + name.charAt(0)).toUpperCase());
            
            cd = findClass(name, "Libraries.Language.Types");
        }
        return cd;
    }

    /**
     * When a class instantiates an object, it needs to determine what
     * class the user is likely trying to reference and where that class
     * is. This method conducts a search for a particular class to instantiate
     * from within another class. If that class has references (imports in java speak)
     * defined, it will use these references to determine whether a particular
     * name fits with a particular class.
     *
     * @param fromClass
     * @param classBeingSearchedFor
     * @return
     */
    public ClassDescriptor findClassDescriptorFromClass(String fromClass,
            String container, String classBeingSearchedFor) {
        //first test if the class we are searching for is in the same container
        HashMap<String, ClassDescriptor> map = classContainers.get(container);

        if (map == null) { //there are no classes in this container
            //the search has failed
            return null;
        }
        ClassDescriptor looking = map.get(fromClass);
        if (looking == null) { //the source class is incorrect. Thus, by definition
            //cannot find another class
            return null;
        }

        ClassDescriptor found = map.get(classBeingSearchedFor);
        if (found != null) { //the descriptor exists in the current container
            //and can be referenced from this class
            return found;
        } else { //TODO: Check to see if the class has a reference to the class
        }

        //if the class is not in the same container, check to see if there
        //is an explicit reference to it.
        return null;
    }

    /**
     * This method takes a class name(unqualified class name) and, given the current class in scope,
     * determines whether it is allowed to instantiate or reference an object
     * of type clazz. This returns null if no class is found.
     * 
     * @param classBeingSearchedFor
     * @return
     */
    public ClassDescriptor findClassDescriptorFromCurrentClass(String classBeingSearchedFor) {
        return findClassDescriptorFromClass(getCurrentClass().getStaticKey(), classBeingSearchedFor);
    }

    /**
     * Allows one to search with an unqualified class name for a class from
     * within a particular class.
     *
     * @param fromClass The fully qualified name to a class we are searching within.
     * @param classBeingSearchedFor The unresolved name. For example, this might
     *  be Stack instead of .Stack or System.Util.Stack.
     * @return
     */
    public ClassDescriptor findClassDescriptorFromClass(String fromClass, String classBeingSearchedFor) {
        //first get the class's container and the class itself
        ClassDescriptor classFrom = this.getClassDescriptor(fromClass);
        if (classFrom != null && fromClass.compareTo(classBeingSearchedFor) == 0) {
            return classFrom;
        }
        String container = classFrom.getContainer().getContainer();
        HashMap<String, ClassDescriptor> map = classContainers.get(container);
        if (map == null) { //if the current class is not in a proper container, it's
            //a bug in the compiler, throw an exception
            throw new RuntimeException("Compiler bug: Current class indicates it"
                    + "has a container, but it is not in a proper container. Check whether"
                    + "the current class was added correctly into the symbol table.");
        }

        //look for the class being searched for
        ClassDescriptor cd = map.get(classBeingSearchedFor);
        if (cd != null) {
            return cd; //found it, this is legal
        }

        //there's no class of that name in the current container
        //look to see if it's available as a reference
        if(classFrom != null){
            cd = classFrom.getValidatedClassUse(classBeingSearchedFor);
        }else{
            cd = getCurrentClass().getValidatedClassUse(classBeingSearchedFor);
        }
        if (cd != null) {
            return cd; //found it, this is legal
        }

        //we've looked, but alas, the class does not appear to be able to
        //reference the class being asked for
        return null;
    }
    
    /**
     * This method takes a class name(fully qualified class name) and, given the current class in scope,
     * determines whether it is allowed to instantiate or reference an object
     * of type clazz. This returns null if no class is found.
     * 
     * @param classBeingSearchedFor
     * @return
     */
    public ClassDescriptor findFullyQualifiedClassDescriptorFromCurrentClass(String classBeingSearchedFor) {
        return findFullyQualifiedClassDescriptorFromClass(getCurrentClass().getStaticKey(), classBeingSearchedFor);
    }
    
    /**
     * Allows one to search with a fully qualified class name for a class from
     * within a particular class.
     *
     * @param fromClass The fully qualified name to a class we are searching within.
     * @param classBeingSearchedFor The fully qualified name. For example, this might
     *  be .Stack or Libraries.Containers.Stack.
     * @return
     */
    public ClassDescriptor findFullyQualifiedClassDescriptorFromClass(String fromClass, String classBeingSearchedFor) {
        //first get the class's container and the class itself
        ClassDescriptor classFrom = this.getClassDescriptor(fromClass);
        if (classFrom != null && fromClass.compareTo(classBeingSearchedFor) == 0) {
            return classFrom;
        }
        String container = classFrom.getContainer().getContainer();
        HashMap<String, ClassDescriptor> map = classContainers.get(container);
        if (map == null) { //if the current class is not in a proper container, it's
            //a bug in the compiler, throw an exception
            throw new RuntimeException("Compiler bug: Current class indicates it"
                    + "has a container, but it is not in a proper container. Check whether"
                    + "the current class was added correctly into the symbol table.");
        }

        //look for the class being searched for
        ClassDescriptor cd = map.get(classBeingSearchedFor);
        if (cd != null) {
            return cd; //found it, this is legal
        }

        //there's no class of that name in the current container
        //look to see if it's available as a reference
        if(classFrom != null){
            cd = classFrom.getValidatedClassUseFromQualifiedName(classBeingSearchedFor);
        }else{
            cd = getCurrentClass().getValidatedClassUseFromQualifiedName(classBeingSearchedFor);
        }
        if (cd != null) {
            return cd; //found it, this is legal
        }

        //we've looked, but alas, the class does not appear to be able to
        //reference the class being asked for
        return null;
    }

    /**
     * Returns a description of what is in a particular file on the system.
     * @param absolutePath
     * @return a parsed and valid FileDescriptor IF one exists.
     */
    public FileDescriptor getFileDescriptor(String absolutePath) {
        return fileMap.get(absolutePath);
    }

    /**
     * Returns a complete set of all files currently listed in the symbol
     * table.
     * 
     * @return
     */
    public Iterator<FileDescriptor> getFileDescriptors() {
        return fileMap.values().iterator();
    }

    /**
     * Returns a complete set of all classes currently listed in the
     * symbol table.
     *
     * @return
     */
    public Iterator<ClassDescriptor> getClassDescriptors() {
        return classes.values().iterator();
    }

    /**
     * This method searches for a given class inside of a given file. If it finds
     * the class, it returns it, else it returns null.
     * @param file
     * @param className
     * @return
     */
    public ClassDescriptor getClassDescriptor(String file, String className) {
        FileDescriptor f = fileMap.get(file);
        if (f == null) {
            return null;
        }
        ClassDescriptor cl = f.getClassDescriptor(className);
        return cl; //could be null
    }

    /**
     * This method allows you to retrieve a class using its full package
     * name. For example, a class in the default package "", named Recursive,
     * would be named ".Recursive" (Notice the period). These strings
     * are always generated correctly from ClassDescriptor.getStaticKey() calls.
     * @param className
     * @return
     */
    public ClassDescriptor getClassDescriptor(String className) {
        ClassDescriptor cl = classes.get(className);
        return cl; //could be null
    }

    /**
     * This method returns a class descriptor from a package. The first parameter
     * 
     * @param className the name of the class. This is not a full static key.
     * @param location This is the package name.
     * @return 
     */
    public ClassDescriptor getClassDescriptorFromPackage(String className, String location) {
        HashMap<String, ClassDescriptor> get = classContainers.get(location);
        if(get != null) {
            return get.get(className);
        }
        return null;
    }
    
    /**
     * This method helps the tree walker set what item is currently in scope
     * so that variables and methods can be easily queried.
     * @param scope
     */
    public void setCurrentScope(Scopable scope) {
        currentScope = scope;
    }

    /**
     * Returns a representation of the current scope that is being built
     * in the symbol table. These scopes are static and are not related to the
     * runtime behavior of a program directory. In essence, they are templates
     * for the runtime behavior of a program.
     * @return
     */
    public Scopable getCurrentScope() {
        return currentScope;
    }

    public ClassDescriptor getMainClass(){
        String main = this.getVirtualMachine().getMain();
        FileDescriptor mainFile = this.getFileDescriptor(main);
        ClassDescriptor firstClass = mainFile.getFirstClass();
        
        return firstClass;
    }
    /**
     * Generates a key indicating whether a particular file, class, or method
     * is a "Main" key, meaning whether the Virtual machine can enter into
     * the program from this point.
     * 
     * @param fileName
     * @param className
     * @param methodName
     * @return
     */
    private String getMainKey(String fileName, String className, String methodName) {
        String main = "";
        main += fileName + ":" + className + ":" + methodName;
        return main;
    }

    /**
     * Generates a key based on the current scope in the symbol table.
     *
     * @param fileName
     * @param className
     * @param methodName
     * @return
     */
    private String getScopeKey(String fileName, String className, String methodName) {
        String main = "";
        main += fileName + ":" + className + ":" + methodName.toLowerCase();
        return main;
    }

    /**
     * Removes a file descriptor, which is useful for recompiling one,
     * and only one, file.
     *
     * @param descriptor
     */
    public void remove(FileDescriptor descriptor) {
        String path = descriptor.getFile().getAbsolutePath();
        FileDescriptor real = fileMap.get(path);
        if (real != null) {
            Iterator<ClassDescriptor> it = real.getClassIterator();
            //get all the classes defined in the file and remove
            //them from the global hash.
            while (it.hasNext()) {
                ClassDescriptor cd = it.next();
                classes.remove(cd.getStaticKey());
                removeClassFromCustomHash(classNames, cd.getName(), cd.getContainer().getContainer());
                removeClassFromCustomHash(classContainers, cd.getContainer().getContainer(), cd.getName());
            }

            fileMap.remove(path);
        } //else there is nothing to do, there is no file of that name
    }

    /**
     * A helper method for removing a particular class from a hash table.
     * 
     * @param hash
     * @param key1
     * @param key2
     */
    private void removeClassFromCustomHash(HashMap<String, HashMap<String, ClassDescriptor>> hash, String key1, String key2) {
        HashMap<String, ClassDescriptor> map = hash.get(key1);

        if (map == null) { //there is no map of this name
            return;
        }
        //now determine whether there is a class by the current name in the map
        ClassDescriptor exists = map.get(key2);
        if (exists != null) { //remove the class from the map
            map.remove(key2);
            //if the map is now empty, remove it
            if (map.size() == 0) {
                hash.remove(key1);
            }
        }
    }

    /**
     * @return the virtualMachine
     */
    public AbstractVirtualMachine getVirtualMachine() {
        return virtualMachine;
    }

    public void resolveAllMethods() {
        Iterator<ClassDescriptor> cit = this.getClassDescriptors();
        while (cit.hasNext()) {
            ClassDescriptor c = cit.next();
            c.resolveMethods();
            c.resolveBlueprints();
            c.resolveSystemActions();
        }
    }

    /**
     * This method digs through the inheritance hierarchy of various
     * classes to ensure that it is valid.
     * 
     */
    public void resolveInheritance() {
        Iterator<ClassDescriptor> iterator = this.classes.values().iterator();
        //resolve each class
        while (iterator.hasNext()) {
            ClassDescriptor next = iterator.next();

            //get unresolved parents and resolve them
            Iterator<String> unresolved = next.getUnresolvedParentKeys();
            while (unresolved.hasNext()) {//resolve them
                String unPar = unresolved.next();
                ClassDescriptor cd = findClassDescriptorFromClass(next.getStaticKey(), unPar);
                
                if (cd != null) {
                    next.addParent(cd);
                } else { //throw a compiler error
                    //unresolved parent names
                    Iterator<ClassDescriptor> findClassesByName = this.findClassesByName(unPar);
                    while (findClassesByName != null && findClassesByName.hasNext()) {
                        if (cd != null) {
                            CompilerError error = new CompilerError(next.getLineBegin(),
                                    "More than one classes has the name " + unPar + ". Please specify the full class name and path.", ErrorType.INHERITANCE_AMBIGUOUS);
                            CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                            errorManager.setErrorKey(next.getFile().getFile().getAbsolutePath());
                            errorManager.addError(error);
                        }
                        cd = findClassesByName.next();
                    }

                    //if we still don't have a class descriptor throw a compiler error.
                    if(cd == null){
                        CompilerError error = new CompilerError(next.getLineBegin(),
                                "Class " + unPar + " does not"
                                + " exist. Did you forget the \"use\" statement?", ErrorType.MISSING_CLASS);
                            CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                            errorManager.setErrorKey(next.getFile().getFile().getAbsolutePath());
                            errorManager.addError(error);
                    }
                }
            }
            
                            
            ClassDescriptor classDescriptor = getMainClass();
            if(classDescriptor.equals(next)){
                Iterator<BlueprintDescriptor> blueprints = classDescriptor.getUnImplementedInheritedBlueprints();
                while(blueprints.hasNext()){
                    BlueprintDescriptor blue = blueprints.next();
                    CompilerError error = new CompilerError(
                                            1,
                                            "You cannot instantiate the abstract class "
                                            + classDescriptor.getName() + ". This class does not have an implemention for the " + blue.getMethodSignature(false) + " blueprint in the " + ((ClassDescriptor)blue.getParent()).getName() +" class.", ErrorType.INSTANTIATE_ABSTRACT);

                    CompilerErrorManager errorManager = getVirtualMachine().getCompilerErrors();
                    errorManager.setErrorKey(next.getFile().getFile().getAbsolutePath());
                    errorManager.addError(error);

                }
            }
            //check to see if this class is already an object
            final String objectName = TypeDescriptor.OBJECT;
            ClassDescriptor parent = next.getParent(objectName);
            if(parent == null && next.getStaticKey().compareTo(objectName) != 0) {
                ClassDescriptor object = this.getClassDescriptor(objectName);
                next.addParent(object);
            }
        }

        //iterate through each class that has been loaded and store any loops
        ArrayList<String> loopsFound = new ArrayList<String>();
        iterator = this.classes.values().iterator();
        ArrayList<ClassDescriptor> processed = new ArrayList<ClassDescriptor>();

        while (iterator.hasNext()) {

            //store the visited nodes and iterators
            ArrayList<ClassDescriptor> visitedNodes = new ArrayList<ClassDescriptor>();
            ArrayList<Iterator<ClassDescriptor>> storedIterators = new ArrayList<Iterator<ClassDescriptor>>();
            ArrayList<ClassDescriptor> prevNodes = new ArrayList<ClassDescriptor>();
            Iterator<ClassDescriptor> parents = new ClassDescriptor().getImmediateParents();

            ClassDescriptor root = iterator.next();
            ClassDescriptor node = root;


            if (node.hasParents()) {
                parents = node.getImmediateParents();
                storedIterators.add(parents);
                prevNodes.add(node);
            }

            //loop until visited
            while (!visitedNodes.contains(node)) {

                //visit parents
                while (parents.hasNext()) {
                    ClassDescriptor prevNode = node;
                    node = parents.next();
                    root.addAncestorToInit(node);

                    if (loopsFound.contains(node.getStaticKey())) {
                        
                        if (!storedIterators.isEmpty() && !prevNodes.isEmpty()) {
                            node = prevNodes.remove(prevNodes.size() - 1);
                            parents = storedIterators.remove(storedIterators.size() - 1);
                        }
                        visitedNodes.add(node);

                    } else if (node.getStaticKey().equals(root.getStaticKey()) || prevNodes.contains(node)) {//detect circular inheritance
                        loopsFound.add(node.getStaticKey());
                        CompilerError error = new CompilerError(node.getLineBegin(),
                                "Class " + node.getStaticKey() + " inherits from itself. Circular inheritance is not allowed.", ErrorType.INHERITANCE_CIRCULAR);
                        CompilerErrorManager eManager = getVirtualMachine().getCompilerErrors();
                        eManager.setErrorKey(node.getFile().getFile().getAbsolutePath());
                        eManager.addError(error);

                        //remove stored item we have found a loop and need to terminate it.
                        if (!storedIterators.isEmpty() && !prevNodes.isEmpty()) {
                            node = prevNodes.remove(prevNodes.size() - 1);
                            parents = storedIterators.remove(storedIterators.size() - 1);
                        }
                        visitedNodes.add(node);

                    } else if (node.hasParents()) {//store items and continue up the chain of parents
                        parents = node.getImmediateParents();
                        storedIterators.add(parents);
                        prevNodes.add(node);
                    } else {

                        //visit the node continue backwards
                        visitedNodes.add(node);
                        if (!storedIterators.isEmpty() && !prevNodes.isEmpty()) {
                            node = prevNodes.remove(prevNodes.size() - 1);
                            parents = storedIterators.remove(storedIterators.size() - 1);

                            //add node back into the queue if they have another branch
                            if(parents.hasNext()){
                                prevNodes.add(node);
                                storedIterators.add(parents);
                            }

                        }
                    }

                }

                if (!storedIterators.isEmpty() && !prevNodes.isEmpty()) {
                    node.addParentsVirtualMethods();
                    visitedNodes.add(node);
                    parents = storedIterators.remove(storedIterators.size() - 1);
                    node = prevNodes.remove(prevNodes.size() - 1);
                    
                    //add node back into the queue if they have another branch
                    if(parents.hasNext()){
                        prevNodes.add(node);
                        storedIterators.add(parents);
                    }
                } else {
                    root.addParentsVirtualMethods();
                    visitedNodes.add(root);
                }

            }
            root.reverseFlattenedListOfParentes();
            
            Iterator<ClassDescriptor> newClassesIterator = visitedNodes.iterator();
            while(newClassesIterator.hasNext()){
                ClassDescriptor newClass = newClassesIterator.next();
                this.classes.put(newClass.getStaticKey(), newClass);

            }
        }
    }

    /**
     * @param virtualMachine the virtualMachine to set
     */
    public void setVirtualMachine(AbstractVirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
    }

    /**
     * Sets the currentScope to its parent
     */
    public void popScope() {

        if (currentMethod == currentScope) {
            currentMethod = null;
        }

        if (currentClass == currentScope) {
            currentClass = null;
        }


        if (currentScope.getParent() != null) {
            currentScope = currentScope.getParent();
        }
    }

    /**
     * This method first finds the a class in a particular package, sets
     * it to the current scope in this symbol table, then returns it.
     * @param clazz
     * @param container
     * @return
     */
    public ClassDescriptor enterClass(String clazz, String container) {
        ClassDescriptor cd = findClass(clazz, container);
        currentClass = cd;
        currentScope = cd;
        return cd;
    }

    /**
     * Informs the symbol table to begin processing assuming that it is
     * within a particular method identified by key.
     * 
     * @param key
     * @return
     */
    public MethodDescriptor enterMethod(String key) {
        MethodDescriptor d = currentScope.getMethod(key);
        //for control flow analysis
        getControlFlow().setMethod(d);
        getControlFlow().setParentFile(currentFile);
        //set the current scoping values for generic processing
        currentScope = d;
        currentMethod = d;
        return d;
    }

    /**
     * Informs the symbol table to begin processing assuming that it is
     * within a particular constructor (only one per class).
     *
     * @return the MethodDescriptor of the constructor.
     */
    public MethodDescriptor enterConstructor(){
        MethodDescriptor constructor = getCurrentClass().getConstructor();

        getControlFlow().setMethod(constructor);
        getControlFlow().setParentFile(currentFile);

        currentScope = constructor;
        currentMethod = constructor;
        return constructor;
    }

    /**
     * Informs the symbol table to begin processing in the scope of a
     * particular file.
     * @param key
     * @return
     */
    public FileDescriptor enterFile(String key) {
        FileDescriptor fd = this.getFileDescriptor(key);

        this.currentFile = fd;
        this.currentScope = null;
        this.currentClass = null;
        this.currentMethod = null;

        return fd;
    }

    /**
     * Enters the next block in the sequence of scopes in a particular
     * class or method.
     * 
     * @return
     */
    public BlockDescriptor enterNextBlock() {
        BlockDescriptor d = currentScope.getNextBlock();
        currentScope = d;
        return d;
    }

    /**
     * Returns a variable with the given static key.
     * 
     * @param key
     * @return
     */
    public VariableParameterCommonDescriptor getVariable(String key) {
        return currentScope.getVariable(key);
    }

    /**
     * Returns a method with the given static key.
     *
     * @param key
     * @return
     */
    public MethodDescriptor getMethod(String key) {
        return currentScope.getMethod(key);
    }

    /**
     * Returns a blueprint with the given static key.
     * @param key
     * @return
     */
    public BlueprintDescriptor getBlueprint(String key){
        return currentScope.getBlueprint(key);
    }

    /**
     * Returns the current class being processed on the system.
     * 
     * @return the currentClass
     */
    public ClassDescriptor getCurrentClass() {
        return currentClass;
    }

    /**
     * Returns the current method being processed on the system.
     * 
     * @return
     */
    public MethodDescriptor getCurrentMethod() {
        return currentMethod;
    }

    /**
     * Returns the current method being processed on the system.
     *
     * @return
     */
    public BlueprintDescriptor getCurrentBlueprint() {
        return currentBlueprint;
    }

    /**
     * @return the controlFlow
     */
    public ControlFlowAnalyzer getControlFlow() {
        return controlFlow;
    }
    
    /**
     * Add the xml representation for the parsed code for a file containing quorum code.
     * This xml logs the line numbers of assignment statements, if statements,
     * check/detect statements, and loops. This allows structures to then be spoken in
     * the TOD debugger.
     * 
     * @param className the complete class name.
     * @param xml The xml structure.
     */
    public void addClassIndexer(String className, String xml){
        indexer.put(className, xml);
    }
            
    
    /**
     * Get the xml representation for a class.
     * 
     * @param className
     * @return 
     */
    public String getClassIndexer(String className){
        if(indexer.containsKey(className)){
            return indexer.get(className);
        }else{
            return null;
        }
    }
}
