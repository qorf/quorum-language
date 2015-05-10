/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * This speech processor handles the tutorial remote's text area,
 * identified in the editor reader by its special accessible name:
 * __sodbeans_tutorial_instructionArea__
 * @author jeff
 */
public class TutorialTextProcessor extends AbstractSpeechProcessor {
    public String process() {
        // Right now, there is no pre-processing.
        return text;
    }
}
