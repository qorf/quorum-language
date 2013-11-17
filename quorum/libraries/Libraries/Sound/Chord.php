<?php $classPageTitle = "Chord"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Chord</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Chord" /><h2> <span class="controllable" data-componentType="class-name">Class Chord</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents a chord. A chord is a collection of notes that are played together, at the same time.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Chord
// Play a C major chord.
Music muse
Chord c
c:Add(60) // C
c:Add(64) // E
c:Add(67) // G
muse:Play(c)</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Sound/MusicEvent.php">Libraries.Sound.MusicEvent</a>, <a href="../../Libraries/Sound/Playable.php">Libraries.Sound.Playable</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:integer">public action Add(integer pitch)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:integer"><p>Add a new pitch to this chord. When the chord is played, all the pitches will sound at once.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="pitch"><strong>integer</strong> <em>pitch</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="pitch">the pitch to add</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Chord
Music muse
Chord c
c:Add(60) // C</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>Perform a deep copy of this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: the new Chord</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Chord
Music muse
Chord c
c:Add(60) // C
Object newChord = c:Copy()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPitches">public action GetPitches()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPitches"><p>Get all the pitches from this chord.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an iterator of the pitches.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPitches"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Music
use Libraries.Sound.Chord
use Libraries.Containers.Blueprints.Iterator
Music muse
Chord c
c:Add(60) // C
Iterator&lt;integer&gt; notes = c:GetPitches()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Sound/MusicEvent.php">Libraries.Sound.MusicEvent</a> public action GetEventType(), public action SetEventType(integer eventType)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Sound/Playable.php">Libraries.Sound.Playable</a> public action GetConstantPitchBend(), public action GetLength(), public action GetPrePitchBend(), public action GetPrePitchBendLength(), public action GetStartTime(), public action GetVolume(), public action SetConstantPitchBend(integer constantBend), public action SetLength(number len), public action SetPrePitchBend(integer preBend), public action SetPrePitchBendLength(integer preBendLength), public action SetStartTime(integer time), public action SetVolume(number vol)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>