/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 *
 * @author jeff
 */
class ExceptionHandler implements UncaughtExceptionHandler {

    public ExceptionHandler() {
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        if (ex instanceof quorum.Libraries.Language.Errors.Error) {
//            // Return our special "quorum exception" error code.
//            System.exit(2);
//        } else {
//            // We don't know what this exception is--return the normal error code.
//            System.exit(1);
//        }
    }
    
}
