<?php $classPageTitle = "Music"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Music</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Music" /><h2> <span class="controllable" data-componentType="class-name">Class Music</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class generates music from the Music Instrument Digital Interface (MIDI) standard. This class can be used to play invidual notes and individual chords, as well as used to compose multi-track songs. This class abstracts away much of the MIDI interface, greatly simplifying song composition. For song composition, see the Playable and Track classes, as well as the AddTrack() and PlaySong() methods of this class.  Throughout this class and its related classes, individual pitches are represented by integers. "Middle C" is note 60. A recommend resource for understanding these numbers is the following page:  http://tomscarff.110mb.com/midi_analyser/midi_note_numbers_for_octaves.htm  This class also defines some constants that can be used throughout music-related classes. These constants include: breve, wholeNote, halfNote, quarterNote, eighthNote , sixteenthNote, thirtySecondthNote, sixtyFourthNote  These constants can all be used to specify note start times and length. In addition, mathematical manipulation of these constants is permitted. For example, if we wanted a double whole note, we could type m:wholeNote*2, as well as m:breve.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Sound.Music
Music music
//play me a chromatic scale
integer note = 60
repeat 12 times
   music:Play(note, 0.25)
   note = note + 1
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>integer</strong> <em>breve</em>: A note with two times the duration of a whole note.</li>
<li class = "package_alternate" ><strong>integer</strong> <em>eighthNote</em>: A note with an eighth the duration of a whole note.</li>
<li class = "package_standard" ><strong>integer</strong> <em>halfNote</em>: A note with half the duration of a whole note.</li>
<li class = "package_alternate" ><strong>integer</strong> <em>quarterNote</em>: A note with a quarter the duration of a whole note.</li>
<li class = "package_standard" ><strong>integer</strong> <em>sixteenthNote</em>: A note with a sixteenth the duration of a whole note.</li>
<li class = "package_alternate" ><strong>integer</strong> <em>sixtyFourthNote</em>: A note with a sixty-fourth the duration of a whole note.</li>
<li class = "package_standard" ><strong>integer</strong> <em>thirtySecondthNote</em>: A note with a thirty-second the duration of a whole note.</li>
<li class = "package_alternate" ><strong>integer</strong> <em>wholeNote</em>: A whole note.</li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddTrack">public action AddTrack()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddTrack"><p>Add a new track to this composition. A new track is created (with the appropriate parameters) and returned. Notes can be added to this track to be played. See the PlaySong() action.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Sound.Track</strong>: a new track</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="AddTrack"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Track
Music muse

// Add three tracks to our composition.
Track t1 = muse:AddTrack()
Track t2 = muse:AddTrack()
Track t3 = muse:AddTrack()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public system action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p>Closes all of the resources being used by the Music instance. This method *must* be called, or the program will not exit properly.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Close"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
Music muse
muse:Close()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetInstrument:integer">public action GetInstrument(integer index)</span></h3>

	<p>Returns the requested instrument. This instrument can be used to set the instrument of individual tracks, or of this Music object. The General MIDI specification defines a number of instruments which can be expected on most systems. For a complete list, see:</p><span class="controllable" data-componentType="action-description" data-actionkey="GetInstrument:integer"><p>http://www.midi.org/techspecs/gm1sound.php</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index">the requested instrument number (zero based).</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Sound.Instrument</strong>: the requested instrument</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetInstrument:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Instrument
Music muse
Instrument i = muse:GetInstrument(0) // get the default piano specified by the General MIDI standard.
output i:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetInstruments">public action GetInstruments()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetInstruments"><p>Returns an iterator containing all available instruments on the system.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: the available instruments.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetInstruments"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Instrument
use Libraries.Containers.Blueprints.Iterator
Music muse
Iterator&lt;Instrument&gt; instruments = muse:GetInstruments()
output "Here are all the available instruments:"
repeat while instruments:HasNext()
   Instrument i = instruments:Next()
   output i:GetName()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTempo">public action GetTempo()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTempo"><p>Gets the current tempo of the song being created in beats per minute. The default tempo is 120 bpm.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTempo"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
Music muse
output "The tempo is " + muse:GetTempo() + " beats per minute."</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTicksPerWholeNote">public action GetTicksPerWholeNote()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTicksPerWholeNote"><p>Returns the number of midi "clock ticks" per whole note. The MIDI specification uses clock ticks to keep all MIDI devices synchronized, and MIDI uses Pulses Per Quarter Note (PPQ) to clearly indicate to MIDI devices how long each note should last, based on the tempo and number of clock ticks it spans. In this implementation, Ticks Per Whole Note is used to simplify calculations. To get PPQ (if desired), simply divide the result by four. The default PPQ used by this class is 96, meaning the number of ticks per whole note is likely to be 384.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the number of clock ticks per whole note.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTrack:integer">public action GetTrack(integer index)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTrack:integer"><p>Gets the track at the specified index.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index">the index of the requested track (zero based).</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Sound.Track</strong>: the requested track</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTrack:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Track
Music muse
Track myTrack = muse:AddTrack()
Track aCopyOfMyTrack = muse:GetTrack(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTracks">public action GetTracks()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTracks"><p>Get all the tracks from this song.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an iterator containing all tracks (if any).</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTracks"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Track
use Libraries.Containers.Blueprints.Iterator
Music muse
Iterator&lt;Track&gt; tracks = muse:GetTracks()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play:Libraries.Sound.Chord">public action Play(Libraries.Sound.Chord chord)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play:Libraries.Sound.Chord"><p>Plays a chord. See the "Chord" class for specifics on how to use these objects.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="chord"><strong>Libraries.Sound.Chord</strong> <em>chord</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="chord">the chord object to play.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play:Libraries.Sound.Chord"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Chord
Music muse
Chord chord
// Play a major C chord.
chord:Add(60)
chord:Add(64)
chord:Add(72)
chord:SetLength(muse:quarterNote)

muse:Play(chord)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play:Libraries.Sound.Note">public action Play(Libraries.Sound.Note note)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play:Libraries.Sound.Note"><p>Plays a note. See the "Note" class for specifics on how to use these objects.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="note"><strong>Libraries.Sound.Note</strong> <em>note</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="note">the note object to play</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play:Libraries.Sound.Note"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Note
Music muse
Note note
note:SetPitch(60)
note:SetLength(muse:quarterNote)
muse:Play(note)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play:Libraries.System.File">public action Play(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play:Libraries.System.File"><p>Play the given MIDI file. See the "Stop" method to stop playback.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play:Libraries.System.File"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.System.File
Music muse
File file
muse:Play(file)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play:integer:number">public action Play(integer note,number duration)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play:integer:number"><p>Plays a note for the specified duration at maximum volume.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="note"><strong>integer</strong> <em>note</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="note">the note number (0 to 127)</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="duration"><strong>number</strong> <em>duration</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="duration">how long to play the note (in seconds)</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play:integer:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
Music muse
muse:Play(60, 1) // middle C, for 1 second, full volume</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play:integer:number:number">public system action Play(integer note,number duration,number volume)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play:integer:number:number"><p>Plays a note for the specified duration, at the given volume.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="note"><strong>integer</strong> <em>note</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="note">the note number (0 to 127)</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="duration"><strong>number</strong> <em>duration</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="duration">how long to play the note (in seconds)</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="volume"><strong>number</strong> <em>volume</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="volume">the volume to play the note at (0 to 1).</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play:integer:number:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
Music muse
muse:Play(60, 1, 0.5) // middle C for 1 second, half volume</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="PlaySong">public action PlaySong()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="PlaySong"><p>Play the song that was constructed using the AddTrack() method. A "song" consists of one or more tracks of notes and/or chords. If no tracks exist, this method will do nothing. See the "Stop" method to stop playback.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="PlaySong"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Note
use Libraries.Sound.Chord
use Libraries.Sound.Track
// Play a simple C arpeggio followed by a C chord.

Music muse
Track t = muse:AddTrack()
Note n1
Note n2
Note n3
Chord c1
n1:SetPitch(60)
n2:SetPitch(64)
n3:SetPitch(67)
n2:SetStartTime(muse:quarterNote)
n3:SetStartTime(muse:quarterNote)
n1:SetLength(muse:quarterNote)
n2:SetLength(muse:quarterNote)
n3:SetLength(muse:quarterNote)
c1:Add(60)
c1:Add(64)
c1:Add(67)
c1:SetStartTime(muse:quarterNote)
c1:SetLength(muse:breve)
t:Add(n1)
t:Add(n2)
t:Add(n3)
t:Add(c1)
muse:PlaySong()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetInstrument:Libraries.Sound.Instrument">public action SetInstrument(Libraries.Sound.Instrument instrument)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetInstrument:Libraries.Sound.Instrument"><p>Set the default instrument. This will affect any new created track (it will be set to this instrument), and it will also affect the instrument used for the various Play() actions.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="instrument"><strong>Libraries.Sound.Instrument</strong> <em>instrument</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="instrument">the instrument to set</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetInstrument:Libraries.Sound.Instrument"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Instrument
Music muse

// Select a different instrument and set it as the default, then play a note.
Instrument i = muse:GetInstrument(10)
muse:SetInstrument(i)
muse:Play(60, 1)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTempo:integer">public action SetTempo(integer beatsPerMinute)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTempo:integer"><p>Set the tempo of this song, specified in beats per minute. The default tempo is 120 beats per minute. Note that this will only affect the PlaySong() action and the Play() actions that accept a relative time value (such as quarterNote).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="beatsPerMinute"><strong>integer</strong> <em>beatsPerMinute</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="beatsPerMinute">the tempo of the song, in beats per minute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTempo:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
Music muse
muse:SetTempo(240) // speed it up</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Stop">public action Stop()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Stop"><p>Stops any currently playing music. This includes individual notes, individual chords, created songs and MIDI files.</p></span>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>