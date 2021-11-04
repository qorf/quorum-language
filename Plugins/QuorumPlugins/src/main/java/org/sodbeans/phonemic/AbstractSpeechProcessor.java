/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic;

/**
 * The class that SpeechProcessor objects should extend to implement their own
 * custom speech processing.
 *
 * By default, the request type is TEXT, the priority is MEDIUM and the
 * call is non-blocking.
 *
 * @author jeff
 */
public abstract class AbstractSpeechProcessor implements SpeechProcessor {
    protected String text = "";
    protected RequestType type = RequestType.TEXT;
    protected SpeechPriority priority = SpeechPriority.MEDIUM;
    protected boolean blocking = false;
    /**
     * Set the text to be processed.
     *
     * @param text
     */
    public void setText(String text) {
        if (text != null)
            this.text = text;
    }

    /**
     * Get the original, unprocessed text.
     *
     * @return String
     */
    public String getText() {
        return text;
    }

    /**
     * Set the request type to be sent to the engine.
     *
     * @param req
     */
    public void setRequestType(RequestType req) {
        type = req;
    }

    /**
     * Get the request type.
     *
     * @return RequestType
     */
    public RequestType getRequestType() {
        return type;
    }

    /**
     * Set the priority to be sent to the engine.
     *
     * @param pri
     */
    public void setPriority(SpeechPriority pri) {
        priority = pri;
    }

    /**
     * Get the priority to be sent to the engine.
     *
     * @return SpeechPriority
     */
    public SpeechPriority getPriority() {
        return priority;
    }

    /**
     * Process the input text.
     * 
     * @return
     */
    abstract public String process();
}
