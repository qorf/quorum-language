/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.awt.Dialog.ModalityType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * A compiler plugin for basic console input/output.
 * 
 * @author Andreas Stefik
 */
public class Console {
    public java.lang.Object $me = null;
    private static JOptionPane optionPane;
    private static JDialog dialog;
    private static boolean useDialog = false;
    
    static {
        //initialize an input dialog that can be used application wide.
        try {
            optionPane = new JOptionPane();
            optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
            optionPane.setWantsInput(true);
            dialog = optionPane.createDialog("Input Dialog");
            dialog.setModalityType(ModalityType.APPLICATION_MODAL);
            dialog.pack();
            
            // Allow dialog to be used.
            useDialog = true;
        } catch (Throwable e) {
            // If SWING or AWT not available, we will use standard input
            // only.
            useDialog = false;
        }
    }
    
    public Console() {
        
    }
    public void Print(String message) {
        System.out.println(message);
    }

    public String Input() {
        return grabInput();
    }

    public String Input(String message) {
        System.out.print(message + " ");
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
    
    /**
     * Gathers input from the user, showing a popup box that takes a string
     * as input and returns a string as output.
     * 
     * @param text
     * @return 
     */
    public static String StaticInput(String text) {
        if (useDialog) {
            String answer = "";
            optionPane.setMessage(text);
            dialog.setVisible(true); 
            answer = (String) optionPane.getInputValue();
            return answer;
        } else {
            // TODO refactor
            System.out.print(text + " " );
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

    public static void Load() {
    }
    
    public static void Unload() {
        if (useDialog)
            dialog.dispose();
    }
    
    public static void main(String args[]) {
        Load();
        StaticInput("Hello, World!");
        Unload();
    }
}
