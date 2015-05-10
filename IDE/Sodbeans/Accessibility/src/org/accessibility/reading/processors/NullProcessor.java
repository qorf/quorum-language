/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * A null processor sends an empty string to the TTS engine, causing it to be
 * ignored.
 * @author jeff
 */
public class NullProcessor extends AbstractSpeechProcessor {
    public String process() {
        return "";
    }
}
