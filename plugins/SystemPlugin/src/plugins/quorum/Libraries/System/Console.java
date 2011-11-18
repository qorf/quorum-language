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
    public java.lang.Object ___$$$Calling___$$$___Object$$$___ = null;
    public void Print(String message) {
        System.out.println(message);
    }

    public String Input() {
        return grabInput();
    }

    public String Input(String message) {
        System.out.println(message);
        return grabInput();
    }
    
    private String grabInput() {
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
}
