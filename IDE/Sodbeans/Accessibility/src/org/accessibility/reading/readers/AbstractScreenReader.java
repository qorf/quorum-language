/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import java.awt.Point;
import org.magnify.MagnifierFactory;
import org.magnify.MagnifierInterface;
import org.accessibility.reading.UberEvent;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.accessibility.options.AccessibilityOptions;

/**
 * A base class for representing general screen reading processing for
 * any type of reader subclass. This class handles processing the events
 * and passing them down to the appropriate subclass, which will do the actual
 * reading. This class does not do any reading itself.
 * 
 * @author Andreas Stefik
 */
public abstract class AbstractScreenReader implements ComponentScreenReader{
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    protected MagnifierInterface magnifier = MagnifierFactory.getDefaultMagnifier();
    private UberEvent uber;

    protected TextToSpeech getTextToSpeech() {
        return speech;
    }
    
    public void read() {
        SpeechProcessor proc = getRead();
        if (proc != null)
            if(AccessibilityOptions.isSelfVoicing()) {
                speech.speak(proc);
            }
    }
    
    public void read(String s, SpeechPriority p){
        if(AccessibilityOptions.isSelfVoicing()) {
            speech.speak(s, p);
        }
    }

    public SpeechProcessor getRead() {
        switch(uber.readType) {
            case FOCUS:
                return getFocusEventProcessor();
            case KEYBOARD:
                return getKeyEventProcessor();
            case MOUSE:
                return getMouseEventProcessor();
            case MENU:
                return getMenuEventProcessor();
        }
        return null;
    }

    public String getReadString() {
        switch(uber.readType) {
            case FOCUS:
                return getFocusEventString();
            case KEYBOARD:
                return getKeyEventString();
            case MOUSE:
                return getMouseEventString();
            case MENU:
                return getMenuEventString();
        }
        return "";
    }

    protected UberEvent getUberEvent() {
        return uber;
    }

    public void setUberEvent(UberEvent event) {
        uber = event;
        setObject(event.object);
    }

    @Override
    public void magnify() {
        if(uber != null && uber.readType == ReadType.FOCUS 
           && uber.object != null && uber.object instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) uber.object;
            if(magnifier.isStarted()) {
                Point loc = comp.getLocationOnScreen();
                
                // Focus on the control
                magnifier.setFocusCenter(loc.x, loc.y);
                // If we're not full screened, move the magnifier over the control.
                // (with proper centering).
                if (!magnifier.isFullScreen())
                    magnifier.setLocation(loc.x - (magnifier.getWidth()/2), loc.y - (magnifier.getHeight()/2));
            }
        }
    }

    protected String getFocusEventString() {return "";}
    protected String getKeyEventString() {return "";}
    protected String getMouseEventString() {return "";}
    protected String getMenuEventString() {return "";}
    protected SpeechProcessor getFocusEventProcessor() {return new NullProcessor();}
    protected SpeechProcessor getKeyEventProcessor() {return new NullProcessor();}
    protected SpeechProcessor getMouseEventProcessor() {return new NullProcessor();}
    protected SpeechProcessor getMenuEventProcessor() {return new NullProcessor();}
}
