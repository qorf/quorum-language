/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

/**
 *
 * @author Melissa Stefik
 */
public class StackTraceElement {
    private int lineNumber = 0;
    private String className = "";
    private String methodName = "";
    private String fileName = "";

    /**
     * TODO: Handle more error conditions.
     *
     * @param i
     * @return
     */
    public void init(String clazz, String method, String file, int line) {

        className = clazz;
        methodName = method;
        fileName = file;
        lineNumber = line;
    }

    public String getClassName(){
        return className;
    }

    public String getMethodName(){
        return methodName;
    }

    public String getFileName(){
        return fileName;
    }

    public int getLineNumber(){
        return lineNumber;
    }
}
