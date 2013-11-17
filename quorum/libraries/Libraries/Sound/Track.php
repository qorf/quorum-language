<?php $classPageTitle = "Track"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Track</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Track" /><h2> <span class="controllable" data-componentType="class-name">Class Track</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents a track. A track is a series of notes and chords. A collection of tracks makes up a song. When tracks are played, all tracks begin at the exact same time.</span></p>
<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Sound.Chord">public action Add(Libraries.Sound.Chord chord)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Sound.Chord"><p>Add a new chord to this track.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="chord"><strong>Libraries.Sound.Chord</strong> <em>chord</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="chord">the chord to add.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Sound.MusicEvent">public action Add(Libraries.Sound.MusicEvent event)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Sound.MusicEvent"><p>Add a new event to this track.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="event"><strong>Libraries.Sound.MusicEvent</strong> <em>event</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="event">the event to add</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Sound.Note">public action Add(Libraries.Sound.Note note)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Sound.Note"><p>Add a new note to this track.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="note"><strong>Libraries.Sound.Note</strong> <em>note</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="note">the note to add</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEvents">public action GetEvents()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEvents"><p>Get the events on this track.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: the events.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetInstrument">public action GetInstrument()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetInstrument"><p>Get the instrument used for this track.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Sound.Instrument</strong>: the instrument</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:integer">public action Remove(integer index)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:integer"><p>Remove the specified event.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index">the index to remove.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetInstrument:Libraries.Sound.Instrument">public action SetInstrument(Libraries.Sound.Instrument instrument)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetInstrument:Libraries.Sound.Instrument"><p>Set the instrument used for this track.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="instrument"><strong>Libraries.Sound.Instrument</strong> <em>instrument</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="instrument">the instrument to use.</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>