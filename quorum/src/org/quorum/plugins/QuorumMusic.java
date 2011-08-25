/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

/**
 * This is the class for handling music in Quorum.
 * 
 * @author jeff
 */
public class QuorumMusic {
    private final int TICKS_PER_WHOLE_NOTE = 384; // 96 * 4 - MUST MATCH QUORUM CODE!
    
    private Synthesizer synthesizer; // the music synthesizer
    private Soundbank soundbank; // the sound bank
    private MidiChannel[] channels; // all the channels
    private MidiChannel channel;
    private Sequencer sequencer;
    private Sequence sequence;
    private Sequence songSequence;
    private Instrument[] instruments;
    private Instrument currentInstrument;
    private int currentInstrumentIndex;
    
    private int beatsPerMinute = 120; // a standard tempo
    private Track currentTrack = null;
    
    private ArrayList<Integer> chordNotes = new ArrayList<Integer>();
    
    public QuorumMusic(Synthesizer synthesizer) {
        this.synthesizer = synthesizer;
        
        try {
            synthesizer.open();
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 10);
        } catch (Exception ex) {
        }
        
        if (synthesizer != null) {
            Soundbank sb = synthesizer.getDefaultSoundbank();
            if (sb != null) {
                instruments = synthesizer.getDefaultSoundbank().getInstruments();
                synthesizer.loadInstrument(instruments[0]);
                this.currentInstrument = instruments[0];
                this.currentInstrumentIndex = 0;
            }
            channels = synthesizer.getChannels();
            channel = channels[0];
        }
        
        // TODO: Sane alternative for null synthesizer?
    }
    
    /**
     * Get the current instrument's name.
     * @return 
     */
    public String GetCurrentInstrumentName() {
        return this.currentInstrument.getName();
    }
    
    /**
     * Get the current instrument's number.
     * @return 
     */
    public int GetCurrentInstrumentNumber() {
        return currentInstrumentIndex;
    }
    
    public void SetCurrentInstrument(int index) {
        if (instruments.length <= index)
            return;
        
        this.currentInstrument = instruments[index];
        this.currentInstrumentIndex = index;
        synthesizer.loadInstrument(this.currentInstrument);
        channel.programChange(this.currentInstrumentIndex);
    }
    
    /**
     * Get the instrument's name by index.
     * 
     * @param index
     * @return 
     */
    public String GetInstrumentName(int index) {
        if (index >= instruments.length)
            return "";
        
        return instruments[index].getName();
    }
    
    /**
     * Plays the specified note for a given duration.
     * 
     * @param note
     * @param duration 
     * @param volume
     */
    public void Play(int note, double duration, double volume) {
        duration = duration * 1000;
        
        try {
            channel.noteOn(note, computeVolume(volume));
            Thread.sleep((int)Math.ceil(duration));
            channel.noteOff(note, computeVolume(volume));
        } catch (InterruptedException e) {
        }
    }
    
    /**
     * Adds a note for a chord play. This is only for the Play method.
     * 
     * @param note
     */
    public void AddNoteForChord(int note) {
        chordNotes.add(note);
    }
    
    /**
     * Play the given chord.
     * 
     * @param length
     * @param volume 
     */
    public void PlayChord(long length, double volume, long lengthInSeconds) {
        try {
            sequence = new Sequence(Sequence.PPQ, TICKS_PER_WHOLE_NOTE/4);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Track track = sequence.createTrack();
        
        Iterator<Integer> notes = chordNotes.iterator();
        
        while (notes.hasNext()) {
            ShortMessage on_message = new ShortMessage();
            ShortMessage off_message = new ShortMessage();
            on_message = new ShortMessage();
            off_message = new ShortMessage();
            int currentNote = notes.next();
            try {
                
                on_message.setMessage(ShortMessage.NOTE_ON, 0, currentNote, computeVolume(volume));
                off_message.setMessage(ShortMessage.NOTE_OFF, 0, currentNote, computeVolume(volume));
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            
            track.add(new MidiEvent(on_message, 0));
            track.add(new MidiEvent(off_message, length));
        }
        sequencer.setTempoInBPM(beatsPerMinute);
        
        try {
            sequencer.setSequence(sequence);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sequencer.start();
        try {
            Thread.sleep(lengthInSeconds * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Flush out the notes cache for chords.
        chordNotes.clear();
    }
    
    /**
     * Start creating a new song. Clears all old tracks.
     */
    public void StartSong() {
        currentTrack = null;

        try {
            songSequence = new Sequence(Sequence.PPQ, TICKS_PER_WHOLE_NOTE/4);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    /**
     * Create a new track.
     * 
     * @param instrument - the instrument to set for this track.
     */
    public void StartTrack(int instrument) {
        if (songSequence == null)
            return;
        
        currentTrack = songSequence.createTrack();
        ShortMessage inst_message = new ShortMessage();
        
        try {
            inst_message.setMessage(ShortMessage.PROGRAM_CHANGE, instrument, 0);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Set the instrument at the start of the track.
        currentTrack.add(new MidiEvent(inst_message, 0));
    }
    
    /**
     * Adds a new note to the current track, if one is set.
     * @param note
     * @param volume 
     * @param onPos where to put the NOTE ON event
     * @param offPos where to put the NOTE OFF event
     */
    public void AddNoteToTrack(int note, double volume, long onPos, long offPos) {
        
        if (currentTrack == null)
            return;
        
            ShortMessage on_message = new ShortMessage();
            ShortMessage off_message = new ShortMessage();
            
            try {
                
                on_message.setMessage(ShortMessage.NOTE_ON, 0, note, computeVolume(volume));
                off_message.setMessage(ShortMessage.NOTE_OFF, 0, note, computeVolume(volume));
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            currentTrack.add(new MidiEvent(on_message, onPos));
            currentTrack.add(new MidiEvent(off_message, offPos));
    }
    
    /**
     * Play the song built using StartSong, StartTrack and AddNoteToTrack.
     */
    public void PlaySong() {
        if (songSequence == null)
            return;
        
        try {
            sequencer.setSequence(songSequence);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(QuorumMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
        sequencer.setTempoInBPM(beatsPerMinute);
        sequencer.start();
    }
    
    private int computeVolume(double volume) {
        return (int)Math.ceil(127 * volume);
    }

    /**
     * @return the beatsPerMinute
     */
    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    /**
     * @param beatsPerMinute the beatsPerMinute to set
     */
    public void setBeatsPerMinute(int beatsPerMinute) {
        this.beatsPerMinute = beatsPerMinute;
    }
}
