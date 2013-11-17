<?php $classPageTitle = "Note"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Note</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Note" /><h2> <span class="controllable" data-componentType="class-name">Class Note</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents a single note to be played.</span></p>
<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Sound/MusicEvent.php">Libraries.Sound.MusicEvent</a>, <a href="../../Libraries/Sound/Playable.php">Libraries.Sound.Playable</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>Perform a deep copy of this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: the new Note</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPitch">public action GetPitch()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPitch"><p>Get the pitch for this particular note. See the Music class's documentation on specifying pitches.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the pitch (0 to 127)</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPitch:integer">public action SetPitch(integer pitch)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPitch:integer"><p>Set the pitch for this particular note. See the Music class's documenation on specifying pitches.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="pitch"><strong>integer</strong> <em>pitch</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="pitch"></span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Sound/MusicEvent.php">Libraries.Sound.MusicEvent</a> public action GetEventType(), public action SetEventType(integer eventType)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Sound/Playable.php">Libraries.Sound.Playable</a> public action GetConstantPitchBend(), public action GetLength(), public action GetPrePitchBend(), public action GetPrePitchBendLength(), public action GetStartTime(), public action GetVolume(), public action SetConstantPitchBend(integer constantBend), public action SetLength(number len), public action SetPrePitchBend(integer preBend), public action SetPrePitchBendLength(integer preBendLength), public action SetStartTime(integer time), public action SetVolume(number vol)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>