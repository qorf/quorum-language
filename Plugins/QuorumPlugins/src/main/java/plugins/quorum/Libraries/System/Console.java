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
