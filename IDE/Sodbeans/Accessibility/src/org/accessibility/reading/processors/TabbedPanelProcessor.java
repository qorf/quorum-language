package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;
import org.sodbeans.phonemic.SpeechPriority;

/**
 * A raw processor makes no modifications to the text passed to it.
 * @author jeff
 */
public class TabbedPanelProcessor extends AbstractSpeechProcessor {
    public String process() {
        this.priority = SpeechPriority.MEDIUM_HIGH;
        return text;
    }
}
