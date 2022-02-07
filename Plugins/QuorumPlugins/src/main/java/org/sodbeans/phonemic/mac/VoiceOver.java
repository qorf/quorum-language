/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sodbeans.phonemic.mac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.sodbeans.phonemic.RequestType;
import org.sodbeans.phonemic.SpeechLanguage;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.SpeechProcessor;
import org.sodbeans.phonemic.SpeechVoice;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.sodbeans.phonemic.tts.TextToSpeechEngine;

/**
 *
 * @author stefika
 */
public class VoiceOver implements TextToSpeech, Runnable
{
    private String script = null;
    private ArrayList<SpeechVoice> voices = new ArrayList<SpeechVoice>();
    private ArrayList<TextToSpeechEngine> engines = new ArrayList<TextToSpeechEngine>();
    static ScriptEngineManager engineManager = new ScriptEngineManager();
    static volatile ScriptEngine applescript = engineManager.getEngineByName("AppleScriptEngine");
    SpeechVoice currentVoice = null;
    
    public VoiceOver() {
        SpeechVoice voice = new SpeechVoice("Alex", SpeechLanguage.ENGLISH_US);
        voices.add(voice);
        currentVoice = voice;
        engines.add(TextToSpeechEngine.VOICEOVER);
    }
    
    @Override
    public void run() 
    {
        try
        {
            applescript.eval(script);
        }
        catch( ScriptException ex )
        {
           Logger.getGlobal().log(Level.SEVERE, "Could not send script to Voice Over");
        }

    }
    
    public static boolean isAppleScriptEnabled()
    {

        String VOAppleScriptStatusScript = "on isVoiceOverRunning()\n" +
                                                "set isRunning to false\n" +
                                                "tell application \"System Events\"\n" +
                                                    "set isRunning to (name of processes) contains \"VoiceOver\"\n" +
                                                "end tell\n" +
                                                "return isRunning\n" +
                                            "end isVoiceOverRunning\n" +
                                            "on isVoiceOverRunningWithAppleScript()\n" +
                                                "if isVoiceOverRunning() then\n" +
                                                    "set isRunningWithAppleScript to true\n" +
                                                    "tell application \"VoiceOver\"\n" +
                                                        "try\n" +
                                                            "set x to bounds of vo cursor\n" +
                                                        "on error\n" +
                                                            "set isRunningWithAppleScript to false\n" +
                                                        "end try\n" +
                                                    "end tell\n" +
                                                    "return isRunningWithAppleScript\n" +
                                                "end if\n" +
                                                "return false\n" +
                                            "end isVoiceOverRunningWithAppleScript\n" +
                                            "if not isVoiceOverRunningWithAppleScript() then\n" +
                                                "display dialog \"VoiceOver must be allowed to be controlled with Applescript. Please enable this feature through the VoiceOver Utility and restart the IDE. \" buttons {\"OK\"}\n" +
                                                "return false\n" +
                                            "end if\n" +
                                            "return true\n";
        
        boolean AppleScriptEnabled = false;
        
        String[] args = {"osascript", "-e", VOAppleScriptStatusScript};

        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);

        Process process;
        try 
        {
            process = builder.start();
            process.waitFor();
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            line = reader.readLine();
            
            AppleScriptEnabled = Boolean.valueOf(line);
        } catch (IOException ex) {
            Logger.getLogger(VoiceOver.class.getName()).log(Level.SEVERE, "Failed to determine Applescript status for VoiceOver. Applescript status is assumed to be off.", ex);
            return false;
        } catch (InterruptedException ex) {
            Logger.getLogger(VoiceOver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return AppleScriptEnabled;
        
    }
    
    public static boolean isVoiceOverRunning() 
    {
        String VOEnabledScript = 
                            " on isVoiceOverRunning()\n" +
                            "	set isRunning to false\n" +
                            "	tell application \"System Events\"\n" +
                            "		set isRunning to (name of processes) contains \"VoiceOver\"\n" +
                            "	end tell\n" +
                            "	return isRunning\n" +
                            "end isVoiceOverRunning \n" +
                            "isVoiceOverRunning()";        

        String[] args = {"osascript", "-e", VOEnabledScript};
        
        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);
        
        boolean VOEnabled = false;
        
        Process process;
        try 
        {
            process = builder.start();
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            line = reader.readLine();
            
            VOEnabled = Boolean.valueOf(line);
        } catch (IOException ex) {
            Logger.getLogger(VoiceOver.class.getName()).log(Level.SEVERE, "Failed to determine VoiceOver status. VoiceOver is assumed to be off.", ex);
            return false;
        }

        return VOEnabled;
              
    }

    @Override
    public boolean canBlock() 
    {
        return true; // Not implemented yet.
    }

    @Override
    public boolean canPause() {
        return false; // Can be done by user through VoiceOver commander
    }

    @Override
    public boolean canResume() {
        return false; // Can be done by user through VoiceOver commander
    }

    @Override
    public boolean canStop() {
        return false; // Can be done by user through VoiceOver commander
    }

    @Override
    public boolean canSetVoice() {
        return false; // Can be done by user through VoiceOver utility
    }

    @Override
    public boolean canSetVolume() {
        return false; // Can be done by user through VoiceOver utility
    }

    @Override
    public boolean canSetSpeed() {
        return false; // Can be done by user through VoiceOver utility
    }

    @Override
    public boolean canSetPitch() {
        return false; // Can be done by user through VoiceOver utility
    }

    @Override
    public Iterator<SpeechVoice> getAvailableVoices() {
        return voices.iterator();
    }

    @Override
    public SpeechVoice getCurrentVoice() {
        return currentVoice;
    }

    @Override
    public double getSpeed() {
        return 1.0;
    }

    @Override
    public TextToSpeechEngine getTextToSpeechEngine() {
        return TextToSpeechEngine.VOICEOVER;
    }

    @Override
    public boolean setTextToSpeechEngine(TextToSpeechEngine engine) {
        return false;
    }

    @Override
    public Iterator<TextToSpeechEngine> getAvailableEngines() {
        return engines.iterator();
    }

    @Override
    public double getVolume() {
        return 1.0;
    }

    @Override
    public double getPitch() {
        return 1.0;
    }

    @Override
    public boolean isSpeaking() {
        return false;
    }

    @Override
    public boolean pause() {
        return false;
    }

    @Override
    public void reinitialize() {
    }

    @Override
    public boolean respeak() {
        
        
        this.script = "tell application \"VoiceOver\"\n" +
                          "tell commander to perform command \"repeat last phrase\"" + "\n" +
                      "end tell";
        
        // Create a thread to run the VoiceOver applescript
        Thread voThread = new Thread(this);
        voThread.setName("VoiceOver Speech Thread");
        voThread.start();
        
        return true;
    }

    @Override
    public boolean copyToClipboard() {
        
        this.script = "tell application \"VoiceOver\"\n" +
                          "tell commander to perform command \"copy last phrase to clipboard\"" + "\n" +
                      "end tell";
        
        // Create a thread to run the VoiceOver applescript
        Thread voThread = new Thread(this);
        voThread.setName("VoiceOver Speech Thread");
        voThread.start();
        
        return true;
        
    }

    @Override
    public boolean resume() {
        return true;
    }

    @Override
    public boolean setVolume(double vol) {
        return false;
    }

    @Override
    public boolean setSpeed(double speed) {
        return false;
    }

    @Override
    public boolean setPitch(double pitch) {
        return false;
    }

    @Override
    public boolean setVoice(SpeechVoice voice) {
        return false;
    }
    
    @Override
    public boolean speak(String text) 
    {
        
        this.script = "tell application \"VoiceOver\"\n" +
                          "output \"" + text + "\"" + "\n" +
                      "end tell";
        
        // Create a thread to run the VoiceOver applescript
        Thread voThread = new Thread(this);
        voThread.setName("VoiceOver Speech Thread");
        voThread.start();
        
        return true;
    }

    @Override
    public boolean speak(String text, SpeechPriority priority) {
        return speak(text);
    }

    @Override
    public boolean speak(String text, SpeechPriority priority, RequestType type) {
        return speak(text);
    }

    @Override
    public boolean speak(char c) {
        return speak("" + c);
    }

    @Override
    public boolean speak(char c, SpeechPriority priority) {
        return speak("" + c);
    }

    @Override
    public boolean speak(SpeechProcessor proc) {
        String process = proc.process();
        return speak(process);
    }

    @Override
    public boolean speakBlocking(String text) {
        
        return speak(text);
    }

    @Override
    public boolean speakBlocking(String text, SpeechPriority priority) {
        
        return speakBlocking(text);
    }

    @Override
    public boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
        return speakBlocking(text);
    }

    @Override
    public boolean speakBlocking(char c) {
        return speakBlocking("" + c);
    }

    @Override
    public boolean speakBlocking(char c, SpeechPriority priority) {
        return speakBlocking("" + c);
    }

    @Override
    public boolean stop() 
    {
        return true;
    }

    @Override
    public void setSpeechEnabled(boolean enabled) {
    }

    @Override
    public boolean isSpeechEnabled() {
        return true;
    }

    @Override
    public double getVersion() {
        return 1.0;
    }
}
