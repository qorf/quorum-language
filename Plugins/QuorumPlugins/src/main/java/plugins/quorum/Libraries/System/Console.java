/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A compiler plugin for basic console input/output.
 * 
 * @author Andreas Stefik
 */
public class Console {
    public java.lang.Object me_ = null;
    public static String[] commandLineArguments = new String[0];

    /*
        The program's starting thread, for multitasking. The generated
        main(String[]) stores the command line arguments here before any
        Quorum code runs, and storing a static field sets this class up on
        that thread, so this runs on the starting thread (desktop, and iOS,
        which starts from the program's jar). Multitasking never looks for
        it; the platform tells it (Lines.SetStartingThread, where the first
        notice wins). Android skips main(String[]) and tells Lines from
        AndroidApplication.SetActivity instead.
    */
    static {
        plugins.quorum.Libraries.Multitasking.Lines.SetStartingThread(Thread.currentThread());
    }

    public Console() {

    }
    public void Print(String message) {
        System.out.println(message);
    }

    public String Input() {
        return StaticInput();
    }

    public String Input(String message) {
        return StaticInput(message);
    }
    
    public static String StaticInput() {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            while ((str = in.readLine()) != null) {
                
                builder.append(str);
                if(!str.isEmpty()) {
                    break;
                }
            }
        } catch (IOException e) {
        }
        return builder.toString();
    }
    
    /**
     * Gathers input from the user, showing a popup box that takes a string
     * as input and returns a string as output.
     * 
     * @param text
     * @return 
     */
    public static String StaticInput(String text) {
        if(text != null) {
            System.out.println(text);
        }
        return StaticInput();
    }

    public static void Load() {
    }
    
    public static void Unload() {
    }
    
    public static void main(String args[]) {
        Load();
        StaticInput("Hello, World!");
        Unload();
    }
    
    public String GetConsoleArgument(int index) {
        if(commandLineArguments != null && index <= commandLineArguments.length) {
            return commandLineArguments[index];
        }
        return null;
    }

    /*
        Returns the number of console arguments that have been passed.

        Attribute: Return the number of console arguments.
    */
    public int GetNumberConsoleArguments() {
        return commandLineArguments.length;
    }
}
