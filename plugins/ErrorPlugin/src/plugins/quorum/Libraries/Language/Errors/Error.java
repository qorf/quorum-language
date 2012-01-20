/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Errors;

import java.util.HashMap;

/**
 *
 * @author Andreas Stefik
 */
public class Error {
    public java.lang.Object $me = null;
    private StackTraceElement[] stackTrace;
    
    public Error() {
    }
    
    /**
     * This may not need to actually be implemented, since this is used for
     * setting data inside the Quorum virtual machine.
     * 
     * @param message 
     */
    public void SystemSetMessage(String message) {
    }
    
    public String GetClassName(int index) {
        String className;
        
        if (index > stackTrace.length) {
            className = null;
        }
        else {
            className = stackTrace[index].getClassName();
        }
        
        return className;
    }
    
    public String GetMethodName(int index) {
        String methodName;
        
        if (index > stackTrace.length) {
            methodName = null;
        }
        else {
            methodName = stackTrace[index].getMethodName();
        }
        
        return methodName;
    }
    
    public String GetFileName(int index) {
        String fileName;
        
        if (index > stackTrace.length) {
            fileName = null;
        }
        else {
            fileName = stackTrace[index].getFileName();
        }
        
        return fileName;
    }
    
    public int GetLineNumber(int index) {
        int number;
        
        if (index > stackTrace.length - 1) {
            number = -1;
        }
        else {
            number = stackTrace[index].getLineNumber();
        }
        
        return number;
    }
    
    /**
     * Get the stack trace.
     * 
     * Note: This may be including the "Error" class itself, which we don't want,
     * so this may need to be modified later to have a certain offset.
     */
    public void InitStackTraceFromError() {
        stackTrace = Thread.currentThread().getStackTrace();
    }
    
    /**
     * TODO: This should return the last rnuntime error, if possible...
     * @return 
     */
    public String InitMessage() {
        return "An Error has occurred.";
    }
    
    public int GetStackSize() {
        return stackTrace.length;
    }
}
