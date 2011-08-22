/*
 * CompilerError.java
 *
 * Created on January 26, 2007, 5:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 * A compiler error message. This messages says where the error occurred, line
 * number, and what the message was.
 * @author Andreas Stefik
 */
public class CompilerError {
    
    private int lineNumber = 0;
    private int column = 0;
    private String file;
    private String error;
    private String absolutePath;
    
    /** Creates a new instance of CompilerError */
    public CompilerError() {
        lineNumber = 0;
        error = "";
    }
    
    public CompilerError(int line, String error) {
        lineNumber = line;
        this.error = error;
    }

    /**
     * line number
     * @return line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * line number
     * @param lineNumber line number
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * error
     * @return error
     */
    public String getError() {
        return error;
    }

    /**
     * error
     * @param error error
     */
    public void setError(String error) {
        this.error = error;
    }
    
    /**
     * A formatted string of the compiler error message.
     * @return A formatted string of the compiler error message.
     */
    @Override
    public String toString() {
        if(getLineNumber() != 0) {
            return getFile() + ": " + getLineNumber() + ": " + getColumn() + getError();
        }
        else {
            return getError();
        }
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Returns the absolute path to the file in which this error
     * occurred.
     *
     * @return the absolutePath
     */
    public String getAbsolutePath() {
        return absolutePath;
    }

    /**
     * Sets the absolute path to the file in which this error occurred.
     * 
     * @param absolutePath the absolutePath to set
     */
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
