/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.execution;

import java.util.ArrayList;

/**
 * This class contains the result of running a .class file. This class is useful
 * for testing purposes as it is possible to determine if the run actually
 * succeeded (e.g. the 'java' process returned a "0"), if the run failed for
 * some reason ('the 'java' process returned something other than "0"), and it
 * is also possible to determine what each line of output is.
 * 
 * @author jeff
 */
public class RunResult {
    private boolean successful = false;
    private int returnCode = -1;
    private ArrayList<String> lines = new ArrayList<String>();
    
    /**
     * Get the number of lines of output.
     * 
     * @return 
     */
    public int getNumberOfLines() {
        return lines.size();
    }
    
    /**
     * Get the value of the given line.
     * @param line
     * @return 
     */
    public String getLine(int line) {
        return lines.get(line);
    }
    
    /**
     * Add a line of output.
     * 
     * @param line 
     */
    public void addLine(String line) {
        lines.add(line);
    }

    /**
     * @return the successful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * @param successful the successful to set
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
    
    public int getReturnCode() {
        return returnCode;
    }
    
    public void setReturnCode(int code) {
        this.returnCode = code;
    }
}
