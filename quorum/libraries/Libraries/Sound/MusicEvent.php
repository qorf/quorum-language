<?php $classPageTitle = "MusicEvent"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.MusicEvent</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.MusicEvent" /><h2> <span class="controllable" data-componentType="class-name">Class MusicEvent</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents an event in a music track. This is the top level interface describing anything that can be put on a music track, such as Notes and Chords.  This class defines constants to describe the various event types. Currently, the only event type supported is "noteOn", which is an event that will sound one or more notes.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Sound.MusicEvent
MusicEvent event
event:SetEventType(0)</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>integer</strong> <em>noteOn</em></li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>Perform a deep copy of this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: the new MusicEvent</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.MusicEvent
MusicEvent event
Object newEvent = event:Copy()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEventType">public action GetEventType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEventType"><p>Returns the event type for this event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the event type.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetEventType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.MusicEvent
MusicEvent event
output event:GetEventType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetEventType:integer">public action SetEventType(integer eventType)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetEventType:integer"><p>Set the event type for this event.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="eventType"><strong>integer</strong> <em>eventType</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="eventType">the event type for this event.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetEventType:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.MusicEvent
MusicEvent event
event:SetEventType(0)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>