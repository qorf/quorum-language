<?php $classPageTitle = "Playable"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Playable</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Playable" /><h2> <span class="controllable" data-componentType="class-name">Class Playable</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents any kind of playable event in a music track. Playable events include any event that will sound one or more notes on the track.  A Playable object has many properties, including:  Pitch bends: A pitch bend can be constant, or occur before a note is played. Pitch bends are described using cents in the positive or negative direction. Bends between -200 and +200 cents are supported.  Start time: The time at which the playing begins for this particular event. Start times are given using the constants defined in the Music class. In this implementation, start times are given as a delta time. In other words, it is the time *after* the last note was entered. For example, if we enter one note at time music:quarterNote and another at time music:wholeNote, the second note will be played at time music:quarterNote + music:wholeNote after the first one.  Length: The duration that this note will sound. Lengths are also specified using the constants defined in the Music class.  Volume: How loud the sound is (also known as velocity in MIDI terms). Volume is specified as a number between 0.0 and 1.0, inclusive.</span></p>
<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Sound/MusicEvent.php">Libraries.Sound.MusicEvent</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>Perform a deep copy of this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: the new Playable</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetConstantPitchBend">public action GetConstantPitchBend()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetConstantPitchBend"><p>Get the constant pitch bend of this event in cents.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the pitch bend</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLength">public action GetLength()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLength"><p>Get the length of the note.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the length</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPrePitchBend">public action GetPrePitchBend()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPrePitchBend"><p>Get the pre pitch bend of this event in cents.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the pre pitch bend in cents.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPrePitchBendLength">public action GetPrePitchBendLength()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPrePitchBendLength"><p>Get the duration of pre pitch bend. The duration specifies how long it takes the bend to reach its target note.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the duration of the pre pitch bend.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetStartTime">public action GetStartTime()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetStartTime"><p>Get the start time of the note.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the start time</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetVolume">public action GetVolume()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetVolume"><p>Get the volume of the note.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the volume of the note.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetConstantPitchBend:integer">public action SetConstantPitchBend(integer constantBend)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetConstantPitchBend:integer"><p>Sets the constant pitch bend of this event in cents.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="constantBend"><strong>integer</strong> <em>constantBend</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="constantBend"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLength:number">public action SetLength(number len)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLength:number"><p>Set the length of the note.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="len"><strong>number</strong> <em>len</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="len">the length of the note.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPrePitchBend:integer">public action SetPrePitchBend(integer preBend)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPrePitchBend:integer"><p>Sets the pre pitch bend of this event in cents.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="preBend"><strong>integer</strong> <em>preBend</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="preBend">the pre pitch bend in cents (-200 to +200).</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPrePitchBendLength:integer">public action SetPrePitchBendLength(integer preBendLength)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPrePitchBendLength:integer"><p>Set the duration of the pre pitch bend. The duration specifies how long it takes the bend to reach its target note.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="preBendLength"><strong>integer</strong> <em>preBendLength</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="preBendLength">the duration of the pre pitch bend.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetStartTime:integer">public action SetStartTime(integer time)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetStartTime:integer"><p>Set the start time of the note.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="time"><strong>integer</strong> <em>time</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="time">the start time.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetVolume:number">public action SetVolume(number vol)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetVolume:number"><p>Set the volume of the note.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="vol"><strong>number</strong> <em>vol</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="vol">the volume of the note. (0.0 to 1.0).</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Sound/MusicEvent.php">Libraries.Sound.MusicEvent</a> public action GetEventType(), public action SetEventType(integer eventType)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>