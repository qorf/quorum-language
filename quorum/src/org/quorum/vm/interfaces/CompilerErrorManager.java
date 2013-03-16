/*
 * ErrorManager.java
 *
 * Created on January 26, 2007, 5:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.io.File;
import java.util.*;

/**
 * A class which supports a series of compiler errorsPerFile, useful for outputting
 * messages to an interface.
 * @author Andreas Stefik
 */
public class CompilerErrorManager {

    private int numberOfErrors;
    
    /**
     * Records the errorsPerFile using the full path of the file as
     * the key and the list for that file as the value.
     */
    private HashMap<String, LinkedList<CompilerError>> errorsPerFile;

    private String errorKey = "";
    private String fileNameKey = "";

    /** Creates a new instance of ErrorManager */
    public CompilerErrorManager() {
        numberOfErrors = 0;
        errorsPerFile = new HashMap<String, LinkedList<CompilerError>>();
    }
    
    /**
     * Adds a new compiler error to the list of compiler errorsPerFile.
     * @param error The new compiler error
     */
    public void addError(CompilerError error) {
        if(error != null) {
            error.setAbsolutePath(errorKey);
            error.setFile(fileNameKey);
            if(error.getLineNumber() <= 0){
                error.setLineNumber(1);
            }
        }

        numberOfErrors++;
        if(errorKey.compareTo("") != 0) { //if the string is not empty
            //check if there is already a list
            LinkedList<CompilerError> l = errorsPerFile.get(errorKey);
            if(l != null) {
                l.add(error);
            }
            else {
                l = new LinkedList<CompilerError>();
                l.add(error);
                errorsPerFile.put(errorKey, l);
            }
        }
    }

    public void removeErrorsAtKey(String key) {
        if(errorsPerFile.containsKey(key)) {
            LinkedList<CompilerError> list = errorsPerFile.remove(key);
            this.numberOfErrors = this.numberOfErrors - list.size();
        }
    }

    public boolean isCompilationErrorFree() {
        return numberOfErrors == 0;
    }
    
    /**
     * Clears the list of compiler errorsPerFile
     */
    public void clear() {
        //list.clear();
        numberOfErrors = 0;
        errorsPerFile.clear();
    }
    
    /**
     * Returns a formatted list of compiler error messages. The error
     * messages include are those from all files currently loaded into
     * the compiler, not just a single file.
     * @return Returns a formatted list of compiler error messages
     */
    @Override
    public String toString() {
        String errors = "";

        int num = this.getNumberSyntaxErrors();

        if(num == 1) {
            errors += num + " compiler error.";
        }
        else {
            errors += num + " compiler errors.";
        }
        errors += " The " +
                "first error is";


        Iterator<CompilerError> it = iterator();
        if(it.hasNext()) {
            CompilerError error = it.next();
            errors += " at line " + error.getLineNumber() 
                    + " in file " + error.getFile() +
                    ", " + error.getError();
        }
        return errors;
    }
    
    /**
     * Returns a formatted list of compiler error messages. This formatted list
     * does not include file names and is intended only for in cases where
     * we can guarantee there are errors only in one file.
     * 
     * @return Returns a formatted list of compiler error messages
     */
    public String getShortErrorList() {
        String errors = "";

        int num = this.getNumberSyntaxErrors();

        if(num == 1) {
            errors += num + " compiler error. ";
        }
        else {
            errors += num + " compiler errors:\n";
        }

        
        int i = 1;
        Iterator<CompilerError> it = iterator();
        if(num == 1) {
            CompilerError error = it.next();
            errors += "Line " + error.getLineNumber() +
                    ", " + error.getError();
        } else if(num > 1) {
            while(it.hasNext()) {
                CompilerError error = it.next();
                errors += i + ": " + "Line " + error.getLineNumber() +
                        ", " + error.getError() + "\n";
                i++;
            }
        }
        return errors;
    }
    
    /**
     * An iterator of all CompilerError messages
     * @return An iterator of CompilerError messages
     */
    public Iterator<CompilerError> iterator() {
        return new TotalErrorsIterator();
    }

    /**
     * Returns the compiler errors specific to a single file.
     * 
     * @param fileKey
     * @return
     */
    public Iterator<CompilerError> iterator(String fileKey) {
        LinkedList<CompilerError> errors = errorsPerFile.get(fileKey);
        if(errors != null) {
            return errors.iterator();
        }
        else {
            return new LinkedList<CompilerError>().iterator();
        }
    }
    
    /**
     * Determines whether a particular file is error free for a particular
     * compile.
     * 
     * @param fileKey
     * @return 
     */
    public boolean isFileErrorFree(String fileKey) {
        LinkedList<CompilerError> errors = errorsPerFile.get(fileKey);
        if(errors != null) {
            return errors.size() == 0;
        } else {
            return true;
        }
    }
    
    /**
     * @return the errorKey
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Returns the number of syntax errors currently logged on the system.
     * @return
     */
    public int getNumberSyntaxErrors() {
        return numberOfErrors;
    }
    /**
     * @param errorKey the errorKey to set
     */
    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
        File file = new File(errorKey);
        fileNameKey = file.getName();
    }

    public void resetToDefaultKey() {
        this.setErrorKey("");
    }
    
    public class TotalErrorsIterator implements Iterator<CompilerError> {

        private Iterator<LinkedList<CompilerError>> globalIterator;
        private Iterator<CompilerError> fileIterator;
        private CompilerError next;

        public boolean hasNext() {

            //if this is the first time through set up the global iterator
            //and the compiler error iterator
            if(globalIterator == null) {
                globalIterator = errorsPerFile.values().iterator();
                if(fileIterator == null && globalIterator.hasNext()) {
                        fileIterator = globalIterator.next().iterator();
                }
                else{
                    return false;
                }
            }

            if(fileIterator.hasNext()) {
                return true;
            }
            else {
                
                while(globalIterator.hasNext()) {
                    fileIterator = globalIterator.next().iterator();

                    if(fileIterator.hasNext()) {
                        return true;
                    }
                    else {
                        fileIterator = null;
                    }
                }

                return false;
            }
        }

        public CompilerError next() {
            return fileIterator.next();
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
