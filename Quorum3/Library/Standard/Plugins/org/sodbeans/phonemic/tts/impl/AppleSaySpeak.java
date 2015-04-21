/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts.impl;

import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.tts.*;
import java.util.Iterator;
import java.io.*;
import java.util.ArrayList;

/**
 * Mac OS X Text To Speech Support (`say' command)
 * @author Andreas Stefik, Louie Most, and Jeff Wilson
*/
public class AppleSaySpeak extends AbstractTextToSpeech {
    private String Voice = "Alex";
    private String Rate = " [[rate 250]]";
    private String Volume = " [[volm 0.7]]";
    private ArrayList<SpeechVoice> voices = new ArrayList<SpeechVoice>();

    public AppleSaySpeak() {
        // Our supported features...
        blockingSupported = true;
        pauseSupported = false;
        resumeSupported = false;
        stopSupported = true;
        voiceChangeSupported = true;
        volumeChangeSupported = true;
        speedChangeSupported = true;
        pitchChangeSupported = false; // not supported by `say' command

        // A nice default speed and volume.
        volume = 1.0;
        speed = 0.5;

        loadVoices();
    }

    private void loadVoices() {
        // All available system voices are listed in
        // /system/library/speech/voices/
        File voicesDir = new File("/system/library/speech/voices/");
        FileFilter voiceFilter = new FileFilter() {
            public boolean accept(File file) {
                return (file.getName().endsWith(".SpeechVoice") &&
                        !file.isDirectory());
            }
        };

        String[] voiceFiles = voicesDir.list();
        for (int i = 0; i < voiceFiles.length; i++)
        {
            String voiceName = voiceFiles[i].split("\\.")[0];
            SpeechVoice newVoice = new SpeechVoice(voiceName, SpeechLanguage.ENGLISH_US);
            voices.add(newVoice);
        }
    }

    @Override
    public boolean speak(String text, SpeechPriority priority, RequestType type) {
        while (isSpeaking()) {
            stop();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        text = " " + text;

        String config = Voice + Rate + Volume;
        // start the say command running

        Runtime runtime = Runtime.getRuntime();
        try {
            Process proc = runtime.exec(
                "say -v " + config + text);
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }
    
    @Override
    public boolean speak(char c, SpeechPriority priority) {
        String text = Character.toString(c);
        return speak(text, priority, RequestType.CHARACTER);
    }

    @Override
    public boolean speakBlocking(char c, SpeechPriority priority) {
        String text = Character.toString(c);
        return speak(text, priority, RequestType.CHARACTER);
    }

    @Override
    public boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
        text = " " + text;

        String config = Voice + Rate + Volume;
        // start the say command running

        Runtime runtime = Runtime.getRuntime();
        Process proc;
        try {
            proc = runtime.exec(
                "say -v " + config + text);

        }
        catch (IOException e) {
            return false;
        }

        // check for say failure

        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " +
                        proc.exitValue());
            }
        } catch (InterruptedException e) {
            System.err.println(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean isSpeaking() {
        //start the ps command running
        Runtime runtime = Runtime.getRuntime();
        Process proc;
        try {
            proc = runtime.exec(
                "ps -x");
        }
        catch (IOException e) {
            return false;
        }
        // put a BufferedReader on the ps output
        InputStream inputstream =
                proc.getInputStream();
        InputStreamReader inputstreamreader =
                new InputStreamReader(inputstream);
        BufferedReader bufferedreader =
                new BufferedReader(inputstreamreader);
        // read the ps output
        String line;
        try {
            while ((line = bufferedreader.readLine()) != null) {
                if (line.contains("say")) {
                    return (true);
                }
            }
        }
        catch (IOException e) {
            return false;
        }
        return (false);
    }

    @Override
    public Iterator<SpeechVoice> getAvailableVoices() {
        return voices.iterator();
    }

    @Override
    public boolean setVolume(double vol)
    {
        if (vol < 0.0)
            vol = 0.0;
        else if (vol > 1.0)
            vol = 1.0;

        Volume = " [[volm " + vol + "]]";
        this.volume = vol;
        return true;
    }

    @Override
    public boolean setSpeed(double speed) {
        if (speed < 0.0)
            speed = 0.0;
        else if (speed > 1.0)
            speed = 1.0;

        double newSpeed = (Math.pow(10.0, speed) * 50) + 100;
        Rate = " [[rate " + newSpeed + "]]";
        this.speed = speed;
        return true;
    }

    @Override
    public boolean setPitch(double pitch) {
        // not supported
        return false;
    }

    @Override
    public boolean setVoice(SpeechVoice voice)
    {
        this.Voice = voice.getName();
        this.voice = voice;
        return true;
    }

    @Override
    public void reinitialize() {
        // do nothing--can't reinitialize.
    }

    @Override
    public boolean respeak() {
        // Implemented in SpeechBridge.
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public boolean resume() {
        return true;
    }

    @Override
    public boolean stop() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("killall say");
        }
        catch(Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public TextToSpeechEngine getTextToSpeechEngine() {
        return TextToSpeechEngine.APPLE_SAY;
    }
}