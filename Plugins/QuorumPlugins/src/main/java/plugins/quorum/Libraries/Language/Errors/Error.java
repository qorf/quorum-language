/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Errors;

import quorum.Libraries.Language.Errors.*;
import quorum.Libraries.System.StackTraceItem;

/**
 *
 * @author Andreas Stefik
 */
public class Error {
    public java.lang.Object me_ = null;
    
    public Error() {
    }
    
    public static quorum.Libraries.Language.Errors.Error ConvertToQuorumError(Throwable throwable) {
        if(throwable instanceof NullPointerException) {
            UndefinedObjectError error = new UndefinedObjectError();
            CaptureThrowableTrace(throwable.getStackTrace(), error);
            return error;
        } else if(throwable instanceof ClassCastException) {
            CastError error = new CastError();
            CaptureThrowableTrace(throwable.getStackTrace(), error);
            return error;
        } else if(throwable instanceof quorum.Libraries.Language.Errors.Error) {
            quorum.Libraries.Language.Errors.Error error = (quorum.Libraries.Language.Errors.Error) throwable;
            if(error.stackTrace == null) {
                CaptureThrowableTrace(throwable.getStackTrace(), error);
            }
            return error;
        } else {
            quorum.Libraries.Language.Errors.Error error = new quorum.Libraries.Language.Errors.Error();
            CaptureThrowableTrace(throwable.getStackTrace(), error);
            error.SetErrorMessage(throwable.getClass().toString() + ", " + throwable.getMessage());
            return error;
        }
    }
    
    public static void CaptureThrowableTrace(StackTraceElement[] trace, quorum.Libraries.Language.Errors.Error error) {
        quorum.Libraries.Containers.Array array = new quorum.Libraries.Containers.Array();
        for(int i = 0; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String fileName = e.getFileName();
            String className = e.getClassName();
            String quorumDot = "quorum.";
            boolean isQuorumClass = false;
            if(className.startsWith(quorumDot)) {
                className = className.substring(quorumDot.length());
                isQuorumClass = true;
            }
            int lineNumber = e.getLineNumber();
            String methodName = e.getMethodName();
            //only put in the quorum errors
            if((isQuorumClass || className.startsWith("plugins.quorum"))
               && !(methodName.startsWith("main") && lineNumber == -1)
               && !(className.startsWith("plugins.quorum.Libraries.Language.Errors.Error"))) {
                StackTraceItem item = new StackTraceItem();
                item.Set_Libraries_System_StackTraceItem__className_(className);
                item.Set_Libraries_System_StackTraceItem__fileName_(fileName);
                item.Set_Libraries_System_StackTraceItem__lineNumber_(lineNumber);
                item.Set_Libraries_System_StackTraceItem__methodName_(methodName);
                array.Add(item);
            }
        }
        
        //There was no useful stack trace, because we never even made it into the program
        //as such, craft a reasonable one.
        if(array.IsEmpty() && trace.length > 0) {
            StackTraceElement e = trace[trace.length - 1];
            StackTraceItem item = new StackTraceItem();
            item.Set_Libraries_System_StackTraceItem__className_(e.getClassName());
            item.Set_Libraries_System_StackTraceItem__fileName_(e.getFileName());
            item.Set_Libraries_System_StackTraceItem__lineNumber_(e.getLineNumber());
            item.Set_Libraries_System_StackTraceItem__methodName_("Main");
            array.Add(item);
        }
        error.SetStackTrace(array);
    }
}
