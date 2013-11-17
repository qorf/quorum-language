<?php $classPageTitle = "Speech"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Speech</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Speech" /><h2> <span class="controllable" data-componentType="class-name">Class Speech</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The speech class does effectively the same thing as the say command in Quorum, except that it provides for more advanced functionality. Specifically, by using the speech class, you can change the volume, pitch, speed, and other properties of the current text-to-speech engine. You can also issue blocking calls, that prevent the program from taking other action until the speaking is complete.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Sound.Speech
class Main
   action Main
      //Gain access to the speech object
      Speech speech
      //Get the current volume
      number volume = speech:GetVolume()
      number speed = speech:GetSpeed()
      number pitch = speech:GetPitch()
      //issue a blocking speech call
      speech:Say("Hello, world!", true)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CanBlock">public system action CanBlock()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CanBlock"><p>Determines whether or not the currently loaded text-to-speech engine can issue a speech blocking call.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether the speech engine can block.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CanBlock"><em>Example Code:</em></span>
<pre class="code">Speech speech
boolean block = speech:CanBlock()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CanPause">public system action CanPause()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CanPause"><p>Determines whether or not the currently loaded text-to-speech engine can issue a speech pause call.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether the speech engine can pause.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CanPause"><em>Example Code:</em></span>
<pre class="code">Speech speech
boolean pause = speech:CanPause()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CanResume">public system action CanResume()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CanResume"><p>Determines whether or not the currently loaded text-to-speech engine can issue a speech resume call. This only matters if the speech engine is currently paused.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether the speech engine can resume from a pause.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CanResume"><em>Example Code:</em></span>
<pre class="code">Speech speech
boolean resume = speech:CanResume()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CanSetSpeed">public system action CanSetSpeed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CanSetSpeed"><p>Determines whether or not the currently loaded text-to-speech engine can have its speed changed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether the speech engine can have a speed change.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CanSetSpeed"><em>Example Code:</em></span>
<pre class="code">Speech speech
boolean speed = speech:CanSetSpeed()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CanSetVoice">public system action CanSetVoice()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CanSetVoice"><p>Determines whether or not the currently loaded text-to-speech engine can have its voice changed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether the speech engine can have a voice change.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CanSetVoice"><em>Example Code:</em></span>
<pre class="code">Speech speech
boolean voice = speech:CanSetVoice()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CanSetVolume">public system action CanSetVolume()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CanSetVolume"><p>Determines whether or not the currently loaded text-to-speech engine can have its volume changed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: whether the speech engine can have a volume change.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CanSetVolume"><em>Example Code:</em></span>
<pre class="code">Speech speech
boolean volume = speech:CanSetVolume()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPitch">public system action GetPitch()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPitch"><p>Sets the current pitch of the text-to-speech engine. The legal values are from 0.0, lowest pitch, to 1.0 maximum pitch.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The current pitch on the system.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPitch"><em>Example Code:</em></span>
<pre class="code">Speech speech
number pitch = speech:GetPitch()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSpeed">public system action GetSpeed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSpeed"><p>Sets the current speed of the text-to-speech engine. The legal values are from 0.0, lowest speed, to 1.0 maximum speed. Each speech engine implementation calculates speed slightly different, but this speech engine should sound approximately equal across implementations.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The current volume on the system.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSpeed"><em>Example Code:</em></span>
<pre class="code">Speech speech
number speed = speech:GetSpeed()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetVolume">public system action GetVolume()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetVolume"><p>Sets the current volume of the text-to-speech engine. The legal values are from 0.0, no volume, to 1.0 maximum volume.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The current volume on the system.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetVolume"><em>Example Code:</em></span>
<pre class="code">Speech speech
number volume = speech:GetVolume()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Say:text">public system action Say(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Say:text"><p>Instructs the system to speak a particular phrase through the current text-to-speech engine.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The text to be spoken.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Say:text"><em>Example Code:</em></span>
<pre class="code">Speech speech
speech:Say("Hello, World!")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Say:text:boolean">public system action Say(text value,boolean block)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Say:text:boolean"><p>Instructs the system to speak a particular phrase through the current text-to-speech engine.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The text to be spoken.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="block"><strong>boolean</strong> <em>block</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="block">Whether or not this is a blocking call, which will
        cause the rest of the program to pause as it is executed.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Say:text:boolean"><em>Example Code:</em></span>
<pre class="code">Speech speech
speech:Say("Hello, World!")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPitch:number">public system action SetPitch(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPitch:number"><p>Sets the current pitch of the text-to-speech engine. The legal values are from 0.0, lowest pitch, to 1.0 maximum pitch.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The new pitch.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPitch:number"><em>Example Code:</em></span>
<pre class="code">Speech speech
speech:SetPitch(1.0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSpeed:number">public system action SetSpeed(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSpeed:number"><p>Sets the current speed of the text-to-speech engine. The legal values are from 0.0, slowest speed, to 1.0 maximum speed. Each speech engine implementation calculates speed slightly different, but this speech engine should sound approximately equal across implementations.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The new volume</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSpeed:number"><em>Example Code:</em></span>
<pre class="code">Speech speech
speech:SetSpeed(1.0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetVolume:number">public system action SetVolume(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetVolume:number"><p>Sets the current volume of the text-to-speech engine. The legal values are from 0.0, no volume, to 1.0 maximum volume.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The new volume</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetVolume:number"><em>Example Code:</em></span>
<pre class="code">Speech speech
speech:SetVolume(1.0)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>