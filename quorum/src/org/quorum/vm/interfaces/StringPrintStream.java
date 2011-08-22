/*
 * StringPrintStream.java
 *
 * Created on February 20, 2007, 11:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.io.PrintStream;

/**
 *  Used for redirecting standard intput/output/etc to a string representation.
 *
 * @author Andreas Stefik
 */
public class StringPrintStream extends PrintStream {
    
    private static PrintStream oldStdout;
    private static PrintStream oldStderr;
    private static String message = "";
    
    /** Creates a new instance of StringPrintStream */
    public StringPrintStream(PrintStream ps) {
	super(ps);
    }
    
    
    // Starts copying stdout and stderr to the file f.
    public static void start() {
	// Save old settings.
	oldStdout = System.out;
	oldStderr = System.err;
        setDefaultCompilerMessage();

	// Start redirecting the output.
	System.setOut(new StringPrintStream(System.out));
	System.setErr(new StringPrintStream(System.err));
    }

    private static void setDefaultCompilerMessage() {
        message = "";
    }
    
    // Restores the original settings.
    public static void stop() {
	System.setOut(oldStdout);
	System.setErr(oldStderr);
    }
    
    // PrintStream override.
    public void write(int b) {
        try {
	    char test = (char)b;
            //message.concat((String) b);
            message += test;
        } catch (Exception e) {
            e.printStackTrace();
            setError();
        }
	super.write(b); //remove redundant output after debugging
    }
    // PrintStream override.
    public void write(byte buf[], int off, int len) {
        try {
            for(int i = 0; i < len; i++) {
                char a;
                a = (char) buf[off + i];
                message += a;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            setError();
        }
	super.write(buf, off, len); //remove redundant output after debugging
    }

    public static String getMessage() {
        return message;
    }
}
