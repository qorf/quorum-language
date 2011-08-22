/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import org.quorum.vm.interfaces.CompilerError;

/**
 *  Gives static analysis related to a given file. For example, one can
 * retrieve a list of the statements that can be jumped to when doing
 * a step over from this class.
 * 
 * @author Andreas Stefik
 */
public class FileDescriptor extends Descriptor{
    private File file;
    private HashMap<String, ClassDescriptor> classes;
    private HashMap<Integer, Object> statementList;

    public FileDescriptor() {
        classes = new HashMap<String, ClassDescriptor>();
        statementList = new HashMap<Integer, Object>();
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
        this.setName(file.getAbsolutePath());
    }

    /**
     * Adds a new class to this file.
     * 
     * @param descriptor
     * @return
     */
    public CompilerError add(ClassDescriptor descriptor) {
        CompilerError error = super.isDefined(descriptor, classes);
        if(error == null) {
            classes.put(descriptor.getName(), descriptor);
        }
        return error;
    }

    /**
     * Inserts a flag at a given line for this file, indicating that
     * a step over command should stop here, as it is the beginning of a new
     * statement.
     * @param line
     */
    public void addStatementLine(int line) {
        statementList.put(line, null);
    }

    /**
     * Removes a given line from the list of valid statements.
     * 
     * @param line
     * @return
     */
    public boolean removeStatementLine(int line) {
        if(statementList.containsKey(line)) {
            statementList.remove(line);
            return true;
        }
        return false;
    }

    /**
     * Determines whether a given line has a statement associated with it. If
     * it does, a step over call will stop at this location.
     * @param line
     * @return
     */
    public boolean hasStatementLine(int line) {
        return statementList.containsKey(line);
    }

    /**
     * Returns a complete list of all keys stored for statements in this
     * file. Do not use this method unless you actually need the entire set.
     * Otherwise, inspect on a line by line basis using
     * hasStatementLine(int).
     * @return
     */
    public Iterator<Integer> getStatementLines() {
        return statementList.keySet().iterator();
    }

    /**
     * Returns a description of a given class that is located in this file.
     * @param name
     * @return
     */
    public ClassDescriptor getClassDescriptor(String name) {
        return classes.get(name);
    }
    
    public Iterator<ClassDescriptor> getClassIterator() {
        return classes.values().iterator();
    }

    /**
     * @return the name
     */
    public String getName() {
        return file.getAbsolutePath();
    }
}
