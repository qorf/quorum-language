/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import org.quorum.execution.ExecutionStep;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.Structure;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author Andreas Stefik and Jeff Wilson
 */
public class MusicPlugin implements Plugin {

    public static final String KEY = "Libraries.Sound.Music";
    public static final String PLAY = "Play:integer:number:number";
    public static final String GET_TEMPO_NATIVE = "GetTempoNative";
    public static final String SET_TEMPO_NATIVE = "SetTempoNative:integer";
    public static final String ADD_NOTE_FOR_CHORD = "AddNoteForChord:integer";
    public static final String PLAY_CHORD = "PlayChord:number:number:number";
    public static final String START_SONG = "StartSong";
    public static final String START_TRACK = "StartTrack:integer";
    public static final String ADD_NOTE_TO_TRACK = "AddNoteToTrack:integer:number:number:number";
    public static final String PLAY_SONG_NATIVE = "PlaySongNative";
    public static final String GET_CURRENT_INSTRUMENT_NAME = "GetCurrentInstrumentName";
    public static final String GET_CURRENT_INSTRUMENT_NUMBER = "GetCurrentInstrumentNumber";
    public static final String GET_INSTRUMENT_NAME = "GetInstrumentName:integer";
    public static final String SET_CURRENT_INSTRUMENT = "SetCurrentInstrument:integer";
    
    private HashMap<Integer, QuorumMusic> instances;
    private Synthesizer synthesizer;
    
    public MusicPlugin() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(MusicPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void reset() {
        instances = new HashMap<Integer, QuorumMusic>();
    }
    

    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        QuorumMusic inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumMusic(synthesizer);
            instances.put(call.getCallingObject().getHashKey(), inst);
        }

        if (call.getActionName().equals(PLAY)) {
            ExpressionValue note = call.getArgument("note");
            ExpressionValue duration = call.getArgument("duration");
            ExpressionValue volume = call.getArgument("volume");
            int n = note.getResult().integer;
            double d = duration.getResult().number;
            inst.Play(n, d, volume.getResult().number);
        }
        else if (call.getActionName().equals(GET_TEMPO_NATIVE)) {
            setPluginReturnValue(ret, inst.getBeatsPerMinute());
        }
        else if (call.getActionName().equals(SET_TEMPO_NATIVE)) {
            ExpressionValue bpm = call.getArgument("beatsPerMinute");
            inst.setBeatsPerMinute(bpm.getResult().integer);
        }
        else if (call.getActionName().equals(ADD_NOTE_FOR_CHORD)) {
            int note = call.getArgument("note").getResult().integer;
            inst.AddNoteForChord(note);
        }
        else if (call.getActionName().equals(PLAY_CHORD)) {
            long length = (long)call.getArgument("length").getResult().number;
            double volume = call.getArgument("volume").getResult().number;
            long lengthInSeconds = (long)call.getArgument("lengthInSeconds").getResult().number;
            
            inst.PlayChord(length, volume, lengthInSeconds);
        }
        else if (call.getActionName().equals(START_SONG)) {
            inst.StartSong();
        }
        else if (call.getActionName().equals(START_TRACK)) {
            int instrument = call.getArgument("instrument").getResult().integer;
            
            inst.StartTrack(instrument);
        }
        else if (call.getActionName().equals(ADD_NOTE_TO_TRACK)) {
            int note = call.getArgument("note").getResult().integer;
            double volume = call.getArgument("volume").getResult().number;
            long onPos = (long)call.getArgument("onPos").getResult().number;
            long offPos = (long)call.getArgument("offPos").getResult().number;
            inst.AddNoteToTrack(note, volume, onPos, offPos);
        }
        else if (call.getActionName().equals(PLAY_SONG_NATIVE)) {
            inst.PlaySong();
        }
        else if (call.getActionName().equals(GET_CURRENT_INSTRUMENT_NAME)) {
            String name = inst.GetCurrentInstrumentName();
            
            setPluginReturnValue(ret, name);
        }
        else if (call.getActionName().equals(GET_CURRENT_INSTRUMENT_NUMBER)) {
            int num = inst.GetCurrentInstrumentNumber();
            
            setPluginReturnValue(ret, num);
        }
        else if (call.getActionName().equals(GET_INSTRUMENT_NAME)) {
            int index = call.getArgument("index").getResult().integer;
            String name = inst.GetInstrumentName(index);
            
            setPluginReturnValue(ret, name);
        }
        else if (call.getActionName().equals(SET_CURRENT_INSTRUMENT)) {
            int index = call.getArgument("index").getResult().integer;
            
            setPluginReturnValue(ret, index);
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
    
    protected void setPluginReturnValue(PluginReturn ret, int num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().integer = num;
        ret.setReturnValue(value);
    }
    
    protected void setPluginReturnValue(PluginReturn ret, double num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = num;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, String str){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().text = str;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, boolean bool){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        value.getResult().boolean_value = bool;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, Structure obj){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().structure = obj;
        ret.setReturnValue(value);
    }

    protected void setExpressionValue(ExpressionValue exp, String str){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        exp.getResult().text = str;
    }

    protected void setExpressionValue(ExpressionValue exp, double num){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        exp.getResult().number = num;
    }

    protected void setExpressionValue(ExpressionValue exp, Boolean bool){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        exp.getResult().boolean_value = bool;
    }
}
