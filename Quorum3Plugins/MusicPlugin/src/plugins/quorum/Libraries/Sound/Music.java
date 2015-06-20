/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Jeff Wilson
 */
public class Music {
    public java.lang.Object me_ = null;
    private QuorumMusic inst = null;
    private static Synthesizer synthesizer = null;
    
    public Music() {
        // Has the synthesizer been initialized yet?
        if (synthesizer == null) {
            try {
                synthesizer = MidiSystem.getSynthesizer();
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        inst = new QuorumMusic(synthesizer);
    }
    
    @Override
    public void finalize() {
        inst = null;
    }
    
    public void Close() {
        inst.Close();
    }
    public void Play(int note, double duration, double volume) {
        inst.Play(note, duration, volume);    
    }
    
    public void AddNoteForChord(int note) {
        inst.AddNoteForChord(note);
    }
    
    public void PlayChord(double length, double volume, double lengthInSeconds) {
        inst.PlayChord((long)length, volume, (long)lengthInSeconds);
    }
    
    public void StartSong() {
        inst.StartSong();
    }
    
    public void PlayMidiNative(String path) {
        inst.PlayMidi(path);
    }
    
    public void StartTrack(int instrument) {
        inst.StartTrack(instrument);
    }
    
    public void AddNoteToTrack(int note, double volume, int constantBend, int preBend, int preBendLength, double onPos, double offPos) {
        inst.AddNoteToTrack(note, volume, constantBend, preBend, preBendLength, (long)onPos, (long)offPos);
    }
    
    public void PlaySongNative() {
        inst.PlaySong();
    }
    
    public int GetTempoNative() {
        return inst.getBeatsPerMinute();
    }
    
    public void SetTempoNative(int beatsPerMinute) {
        inst.setBeatsPerMinute(beatsPerMinute);
    }
    
    public String GetCurrentInstrumentName() {
        return inst.GetCurrentInstrumentName();
    }
    
    public int GetCurrentInstrumentNumber() {
        return inst.GetCurrentInstrumentNumber();
    }
    
    public void SetCurrentInstrument(int index) {
        inst.SetCurrentInstrument(index);
    }
    
    public String GetInstrumentName(int index) {
        return inst.GetInstrumentName(index);
    }
    
    public void StopNative() {
        inst.Stop();
    }
}
