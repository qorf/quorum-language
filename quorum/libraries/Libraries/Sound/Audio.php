<?php $classPageTitle = "Audio"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Sound.Audio</h1>
<input type="hidden" id="classkey" value="Libraries.Sound.Audio" /><h2> <span class="controllable" data-componentType="class-name">Class Audio</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Audio class provides basic functionality for both playing and recording sound files on the system. To use the audio class, we must first instantiate an object of type Audio, then call SetFile, with either a valid file for playback or the location of a new file for recording. If this file or location is valid, we can then call actions to either start playback or recording. The the file or location is not valid, a generic runtime Error will be thrown.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Sound.Audio
use Libraries.System.File
//first create audio and File objects
Audio audio
File file
//set the path to the file you want to play back
//in this case, test.wav should be replaced with your file.
file:SetPath("test.wav")
//tell the audio playback library what file you want to play
audio:SetFile(file)
//playback the file. By default, this is a non-blocking call.
audio:Play()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFile">public action GetFile()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFile"><p>This action sets the file that will be used by the system for recording or playback. If used for playback, this must be a valid file. If this is used for recording, a new file will be created.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.System.File</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play">public action Play()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play"><p>The Play action plays a sound file. Before we can call this action, we must call the SetFile action. This file must point to a valid sound file.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Audio
use Libraries.System.File
//first create audio and File objects
Audio audio
File file
//set the path to the file you want to play back
//in this case, test.wav should be replaced with your file.
file:SetPath("test.wav")
//tell the audio playback library what file you want to play
audio:SetFile(file)
//playback the file. By default, this is a non-blocking call.
audio:Play()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Play:boolean">public action Play(boolean block)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Play:boolean"><p>The Play action plays a sound file. Before we can call this action, we must call the SetFile action. This file must point to a valid sound file. This version of the Play action allows us to pass a parameter. If this is true, this action will not return until the sound file has finished playing.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="block"><strong>boolean</strong> <em>block</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="block"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Play:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Audio
use Libraries.System.File
//first create audio and File objects
Audio audio
File file
//set the path to the file you want to play back
//in this case, test.wav should be replaced with your file.
file:SetPath("test.wav")
//tell the audio playback library what file you want to play
audio:SetFile(file)
//playback the file. By default, this is a non-blocking call.
audio:Play()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Record">public action Record()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Record"><p>This action tells the system to record from its default recording device. On most systems, this will be set by the operating system, and will include a microphone or other device. On systems where no such device exists this action will return and do nothing. The record action always records in the background, and as such, calling Record() and then immediately StopRecording() will do nothing.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Record"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Audio
use Libraries.System.File
//first create audio and File objects
Audio audio
File file
//set the path to the file you want to play back
//in this case, test.wav should be replaced with your file.
file:SetPath("test.wav")
//tell the audio system where the data from the microphone should be
//stored.
audio:SetFile(file)
//Start recording. This call is always non-blocking.
audio:Record()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFile:Libraries.System.File">public action SetFile(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFile:Libraries.System.File"><p>This action sets the file that will be used by the system for recording or playback. If used for playback, this must be a valid file. If this is used for recording, a new file will be created.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="StopPlaying">public action StopPlaying()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="StopPlaying"><p>This action stops the system from playing a sound file. It is only relevant if it is called from a non-blocking call to Play, which is either Play(false) or Play().</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="StopPlaying"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Audio
use Libraries.System.File
//first create audio and File objects
Audio audio
File file
//set the path to the file you want to play back
//in this case, test.wav should be replaced with your file.
file:SetPath("test.wav")
//tell the audio playback library what file you want to play
audio:SetFile(file)
//playback the file. By default, this is a non-blocking call.
audio:Play()
//now stop the playback. In this case, no sound will be heard (or very little)
//because playback begins immediately before stopping
audio:StopPlaying()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="StopRecording">public action StopRecording()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="StopRecording"><p>This action tells this Audio object to stop recording. If the system is not currently recording, it does nothing.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="StopRecording"><em>Example Code:</em></span>
<pre class="code">use Libraries.Sound.Audio
use Libraries.System.File
//first create audio and File objects
Audio audio
File file
//set the path to the file you want to play back
//in this case, test.wav should be replaced with your file.
file:SetPath("test.wav")
//tell the audio system where the data from the microphone should be
//stored.
audio:SetFile(file)
//Start recording. This call is always non-blocking.
//perhaps we let it go for a while or put this in a user interface
audio:Record()
//Stop recording. In this example, very little would be recorded,
//as we are telling the system to stop immediately after we start.
audio:StopRecording()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>