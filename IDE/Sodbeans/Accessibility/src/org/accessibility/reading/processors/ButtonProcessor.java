/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * Handles button processing.
 * @author jeff
 */
public class ButtonProcessor extends AbstractSpeechProcessor {
    public String process() {
        // Did we get any text? If not, we can't do much.
        if (text == null)
            return "Unknown button. No accessibility values were set for this button.";

        // Otherwise, we have some processing to do.
        if (text.length() != 0) {
            // Kill any leading '<' and '>' characters
            text = text.replace("<", "");
            text = text.replace(">", "");

            return text + " button";
        }

        // blank string will be ignored by TTS engine.
        return "";
    }
}
