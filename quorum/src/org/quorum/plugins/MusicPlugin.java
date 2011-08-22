/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Andreas Stefik
 */
public class MusicPlugin implements Plugin {

    public static final String KEY = "Libraries.Sound.Music";
    public static final String PLAY = "Play:integer:number";
    private Synthesizer synthesizer; // the music synthesizer
    private Soundbank soundbank; // the sound bank
    private MidiChannel[] channels; // all the channels
    private MidiChannel channel;
    private Sequencer sequencer;
    private Sequence sequence;
    private Instrument[] instruments;

    public MusicPlugin() {
        try {
            if (synthesizer == null) {
                if ((synthesizer = MidiSystem.getSynthesizer()) == null) {
                }
            } 
            synthesizer.open();
            sequencer = MidiSystem.getSequencer();
            sequence = new Sequence(Sequence.PPQ, 10);
        } catch (Exception ex) {
        }
        
        if (synthesizer != null) {
            Soundbank sb = synthesizer.getDefaultSoundbank();
            if (sb != null) {
                instruments = synthesizer.getDefaultSoundbank().getInstruments();
                synthesizer.loadInstrument(instruments[0]);
            }
            channels = synthesizer.getChannels();
            channel = channels[0];
        }
        
        // TODO: Sane alternative for null synthesizer?
    }

    private void playNote(int note, int duration, int intensity) {
        try {
            channel.noteOn(note, intensity);
            Thread.sleep(duration);
            channel.noteOff(note, intensity);
        } catch (InterruptedException e) {
        }
    }

    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        if (call.getActionName().equals(PLAY)) {
            ExpressionValue note = call.getArgument("note");
            ExpressionValue duration = call.getArgument("duration");
            int n = note.getResult().integer;
            int d = (int)(duration.getResult().number * 1000);
            playNote(n, d, 64);
        }
        return ret;
    }

    public PluginReturn unexecute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        return ret;
    }

    public boolean isValidCall(PluginCall call) {
        return true;
    }

    public boolean canDebugBackward() {
        return true;
    }

    public boolean isDebugPlugin() {
        return true;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public String getName() {
        return "Music";
    }

    public int getVersion() {
        return 1;
    }

    public String getKey() {
        return KEY;
    }

    public void reset() {
    }
}
