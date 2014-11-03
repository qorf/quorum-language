/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Errors;

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
        String className = "";
        
        if (index > stackTrace.length) {
            className = "Unknown class name";
        }
        else {
            className = stackTrace[index].getClassName();
            if(className == null){
                className = "Unknown class.";
            }
        }
        
        return className;
    }
    
    public String GetMethodName(int index) {
        String methodName = "";
        
        if (index > stackTrace.length) {
            methodName = "Unknown action.";
        }
        else {
            methodName = stackTrace[index].getMethodName();
            if(methodName == null){
                methodName = "Unknown action.";
            }
        }
        
        return methodName;
    }
    
    public String GetFileName(int index) {
        String fileName;
        
        if (index > stackTrace.length) {
            fileName = "Unknown file.";
        }
        else {
            fileName = stackTrace[index].getFileName();
            if(fileName == null){
                fileName = "Unknown file.";
            }
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
        int a = 0;
    }
    
    /**
     * TODO: This should return the last runtime error, if possible...
     * @return 
     */
    public String InitMessage() {
        return "An Error has occurred.";
    }
    
    public int GetStackSize() {
        return stackTrace.length;
    }
}
