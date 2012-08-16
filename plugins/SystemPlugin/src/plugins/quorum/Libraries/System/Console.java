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
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * A compiler plugin for basic console input/output.
 * 
 * @author Andreas Stefik
 */
public class Console {
    public java.lang.Object $me = null;
    private static JOptionPane optionPane = null;
    private static JDialog dialog = null;
    private static boolean useDialog = false;
    private static TextToSpeech speech = null;
    

    static {
        try {
            if (System.getProperty(Quorum.QUORUM_TEST_JVM_FLAG) == null) {
                optionPane = new JOptionPane();
                optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
                optionPane.setWantsInput(true);
                dialog = optionPane.createDialog("Input Dialog");
                dialog.setModalityType(ModalityType.APPLICATION_MODAL);
                dialog.pack();

                // Allow dialog to be used.
                useDialog = true;

                // Set up speech if we get here.
                speech = TextToSpeechFactory.getDefaultTextToSpeech();
            }
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
            // Read the user the prompt message if we are in sodbeans.
            // Sodbeans calls Quorum programs using the -D flag, like so:
            // java -Dsodbeans=1 -jar <Default.jar> ...
            if (System.getProperty("sodbeans") != null)
                speech.speak(text, SpeechPriority.MEDIUM_HIGH);
            
            // Now, show the input dialog.
            String answer = "";
            optionPane = new JOptionPane();
            optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
            optionPane.setWantsInput(true);
            dialog = optionPane.createDialog("Input Dialog");
            dialog.setModalityType(ModalityType.APPLICATION_MODAL);
            dialog.pack();
            optionPane.setMessage(text);
            optionPane.setInitialSelectionValue("");
            dialog.setVisible(true); 
            dialog.requestFocus();

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
        if (useDialog && dialog != null)
            dialog.dispose();
    }
    
    public static void main(String args[]) {
        Load();
        StaticInput("Hello, World!");
        Unload();
    }
}
