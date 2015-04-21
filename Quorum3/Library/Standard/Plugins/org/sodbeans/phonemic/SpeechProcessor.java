/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic;

/**
 *
 * @author jeff
 */
public interface SpeechProcessor {
    /**
     * Set the text to be processed.
     * @param text
     */
    public void setText(String text);

    /**
     * Get the original, unprocessed text.
     * @return String
     */
    public String getText();

    /**
     * Set the request type to be sent to the engine.
     * @param req
     */
    public void setRequestType(RequestType req);

    /**
     * Get the request type.
     * @return RequestType
     */
    public RequestType getRequestType();

    /**
     * Set the priority to be sent to the engine.
     * @param pri
     */
    public void setPriority(SpeechPriority pri);

    /**
     * Get the priority to be sent to the engine.
     * @return SpeechPriority
     */
    public SpeechPriority getPriority();

    /**
     * Process the input text.
     * @return
     */
    public String process();
}