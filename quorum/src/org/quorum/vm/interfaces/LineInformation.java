/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 *
 * @author Aaron Willows
 */
public class LineInformation {

    private int startLine = 0;
    private int endLine = 0;

    private int startColumn  = 0;
    private int endColumn = 0;

    private String className;
    private String methodName;
    private String file;

    public LineInformation() {
        

    }

    public LineInformation(int startLine, int endLine, int startColumn, int endColumn) {
        this.endColumn = endColumn;
        this.startColumn =  startColumn;
        this.startLine = startLine;
        this.endLine = endLine;

    }
    public LineInformation(int startLine, int endLine) {
        this.startLine = startLine;
        this.endLine = endLine;
    }

    /**
     * @return the startLine
     */
    public int getStartLine() {
        return startLine;
    }

    /**
     * @param startLine the startLine to set
     */
    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    /**
     * @return the endLine
     */
    public int getEndLine() {
        return endLine;
    }

    /**
     * @param endLine the endLine to set
     */
    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    /**
     * @return the startColumn
     */
    public int getStartColumn() {
        return startColumn;
    }

    /**
     * @param startColumn the startColumn to set
     */
    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    /**
     * @return the endColumn
     */
    public int getEndColumn() {
        return endColumn;
    }

    /**
     * @param endColumn the endColumn to set
     */
    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
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
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
