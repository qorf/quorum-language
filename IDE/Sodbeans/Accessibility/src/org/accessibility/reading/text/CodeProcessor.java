/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.text;

/**
 * Processes characters for strings that are related to a code block.
 * @author astefik
 */
public class CodeProcessor implements TextProcessor{
    CharacterProcessor processor = new CodeCharacterLineProcessor();
    public String process(String str) {

        if(str.matches("(\\s)*")) {
            return "blank";
        }
        return processCharacters(str);
    }

    public String processCharacters(String str) {
        String s = "";
        for(int i = 0; i < str.length(); i++) {
            s += processor.processCharacter(str.charAt(i));
        }
        return s;
    }

}
