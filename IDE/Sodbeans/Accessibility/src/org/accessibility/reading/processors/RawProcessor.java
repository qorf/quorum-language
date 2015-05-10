package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * A raw processor makes no modifications to the text passed to it.
 * @author jeff
 */
public class RawProcessor extends AbstractSpeechProcessor {
    public String process() {
        // Wow! Exciting!
        return text;
    }
}
