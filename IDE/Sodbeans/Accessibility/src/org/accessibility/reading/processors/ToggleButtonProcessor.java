package org.accessibility.reading.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sodbeans.phonemic.AbstractSpeechProcessor;

/**
 * Handles processing for JToggleButton objects.
 * 
 * @author jeff
 */
public class ToggleButtonProcessor extends AbstractSpeechProcessor {
    public String process() {
        Pattern pattern = Pattern.compile("<html><center>([a-zA-Z\\s]+)</center></html>");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find())
            text = matcher.replaceAll(matcher.group(1));

        return text;
    }
}