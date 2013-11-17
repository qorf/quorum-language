<?php $classPageTitle = "Instrument"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Instrument</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Instrument" /><h2> <span class="controllable" data-componentType="class-name">Class Instrument</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This class represents a MIDI instrument. A MIDI instrument consists of a number and a name. For a complete list of MIDI instruments specified by the General MIDI standard, see:  http://www.midi.org/techspecs/gm1sound.php  On most systems, these will be the only instruments available.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Sound.Instrument
use Libraries.Sound.Music
Music music
Instrument piano = music:GetInstrument(0)
output piano:GetName()
output piano:GetValue()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>Get the name of the instrument.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the name of the instrument.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Instrument
use Libraries.Sound.Music
Music music
Instrument piano = music:GetInstrument(0)
output piano:GetName()
output piano:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>Get the number of this instrument.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the number of this instrument.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Instrument
use Libraries.Sound.Music
Music music
Instrument piano = music:GetInstrument(0)
output piano:GetName()
output piano:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>Sets the name of the instrument. This should only be called by helper music classes.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">the name to set.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Instrument
use Libraries.Sound.Music
Music music
Instrument piano = music:GetInstrument(0)
piano:SetName("piano")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:integer">public action SetValue(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:integer"><p>Sets the number of this instrument. This should only be called by helper music classes.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">the number to set.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Instrument
use Libraries.Sound.Music
Music music
Instrument piano = music:GetInstrument(0)
piano:SetValue(0)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>