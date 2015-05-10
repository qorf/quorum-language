/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.text;

/**
 * This class is a pre-processor that takes whatever text is currently on
 * the screen (e.g., a line, a book), and pre-processes it for
 * human consumption. The goal of most pre-processors is to make the
 * resulting text as screen reader and platform independent as possible,
 *
 * @author astefik
 */
public interface TextProcessor {

    /**
     * This function takes in a string and pre-processes it to be platform
     * neutral and to read characters as appropriate. For example, one custom
     * reader might read asterisks as "star," while another might use complex
     * parsing to determine what is read.
     * 
     * @param str
     * @return
     */
    public String process(String str);
}
