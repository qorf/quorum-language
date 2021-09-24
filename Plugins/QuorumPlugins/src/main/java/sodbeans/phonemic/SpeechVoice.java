/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Jeff Wilson
 */
public class SpeechVoice {
    private String name;
    private SpeechLanguage language;
    private static final Logger logger = Logger.getLogger(SpeechVoice.class.getName());

    public SpeechVoice() {
        name = "";
        language = SpeechLanguage.ENGLISH_US;
    }
    
    public SpeechVoice(String name, SpeechLanguage lang) {
        this.name = name;
        this.language = lang;
    }

    public String getName() {
        return this.name;
    }

    public SpeechLanguage getLanguage() {
        return this.language;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /** This is a cheap hack. Better XML parsing techniques should be used.
     *  The only reason I used it is because it was the technique used in
     *  http://www.quadmore.com/JAVA_to_SAPI/
     *  It works, though.
     * @param xml
     */
    static public ArrayList<SpeechVoice> parseNativeVoicesString(String strVoiceList) {
        ArrayList<SpeechVoice> voices = new ArrayList<SpeechVoice>();
        int intPosition = 0;

        try {
            intPosition = strVoiceList.indexOf("<voice>");

            while (intPosition > 0) {
                strVoiceList = strVoiceList.substring(intPosition + 7);
                intPosition = strVoiceList.indexOf("</voice>");

                String vname = strVoiceList.substring(0, intPosition);

                SpeechVoice voice = new SpeechVoice(vname, SpeechLanguage.ENGLISH_US);
                voices.add(voice);
                intPosition = strVoiceList.indexOf("<voice>");
            }
        }
        catch(Exception exception) {
            logger.log(Level.INFO, "Voices could not be fully loaded. The voice XML"
                    + " file looked as follows: " +
                    strVoiceList, exception);
        }

        return voices;
    }
}