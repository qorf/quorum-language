/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.text;

/**
 * This interface processes single characters and returns string names
 * that are related to that character.
 * 
 * @author Andreas Stefik
 */
public interface CharacterProcessor {

    /**
     * Converts one character into a corresponding word or phrase.
     * 
     * @param c
     * @return
     */
    public String processCharacter(char c);
}
