/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic;

/**
 * Represents a text to speech request on the system.
 *
 * Note that a request consists of two types of text: raw text and processed
 * text. If a request has been through a SpeechProcessor object, the processed
 * text will not be null and is likely different from the raw text. Raw text
 * is kept around so that it may be used for things such as copyToClipboard().
 * @author jeff
 */

public class SpeechRequest implements Comparable<SpeechRequest> {
    private String text = "";
    private SpeechPriority speechPriority = SpeechPriority.MEDIUM;
    private RequestType type = RequestType.TEXT;
    private SpeechProcessor proc = null;

    public String getText() {
        return text;
    }
    
    public SpeechPriority getPriority() {
        return speechPriority;
    }

    public RequestType getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SpeechProcessor getProcessor() {
        return this.proc;
    }

    public void setPriority(SpeechPriority priority) {
        this.speechPriority = priority;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setProcessor(SpeechProcessor p) {
        this.proc = p;
    }
    
    @Override
    public int compareTo(SpeechRequest r) {
        return this.speechPriority.compareTo(r.getPriority());
    }
}
