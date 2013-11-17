<?php $classPageTitle = "Audio"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Audio</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Audio" /><h2> <span class="controllable" data-componentType="class-name">Class Audio</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Audio class represents HTML's (Hypertext Markup Language) audio tag which is used to add an audio player/content to a page. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_audio.asp">The audio attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      Audio audio
      audio:SetAddress("http://www.moviesoundclips.net/download.php?id=1483&amp;ft=wav")
      audio:SetControls(true)
      audio:SetAutoPlay(true)
      
      page:AddToBody(audio)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/MediaAttributeAccepter.php">Libraries.Web.MediaAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>preloadAuto</em>: This is a tag that can be used in the preload option for audio.
        Auto  specifies that the audio should be loaded automatically when the page
        loads.</li>
<li class = "package_alternate" ><strong>text</strong> <em>preloadMetadata</em>: This is a tag that can be used in the preload option for audio.         
        The Metadata tag means that the page should load information about the audio
        file to be played, but that the actual audio should not be loaded.</li>
<li class = "package_standard" ><strong>text</strong> <em>preloadNone</em>: This is a tag that can be used in the preload option for audio. 
        The "none" tag specifies that no information about the audio should be 
        loaded when the page loads.</li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>This action generates text for a particular web page. Subclasses with a Generate method should be sure to honor nested tags or attributes if it is appropriate for a particular tag.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: This returns text in hypertext markup language (HTML)
            representing the tag.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Generate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
WebPage page
text result = page:Generate()
//output out the web page
output result</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAddress">public action GetAddress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action returns the address where the audio file resides. This address is relative to the root of the server.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: This returns an attribute representing the address.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
use Libraries.Web.Attribute
Audio audio
Attribute play = audio:GetAddress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoPlay">public action GetAutoPlay()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoPlay"><p>This action returns true if the web page will automatically try to play the specified audio file as soon as it is ready. Ready, in this context means that a sufficient amount of audio has been buffered.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if the page will automatically
            play the file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutoPlay"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
boolean play = audio:GetAutoPlay()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetControls">public action GetControls()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetControls"><p>This action returns true if the web page will display controls for playing an audio file.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if controls for adjusting the 
        playback of the audio file will be displayed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetControls"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
boolean play = audio:GetControls()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLoop">public action GetLoop()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLoop"><p>This action returns true if the audio file played on this web page will automatically repeat itself after it is complete.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if the file will play itself
            again after it is finished.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLoop"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
boolean play = audio:GetLoop()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPreload">public action GetPreload()</span></h3>

	<p>This action returns information on how audio should be loaded from this tag. There are three options for passing information to this action, which are written into the constants : 1) preloadAuto, 2) preloadMetadata, and 3) preloadNone.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetPreload"><p><p> Auto specifies that the audio should be loaded automatically when the page loads. The Metadata tag means that the page should load information about the audio file to be played, but that the actual audio should not be loaded. The "none" tag specifies that no information about the audio should be loaded when the page loads. </p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: This returns an attribute representing the preload
            preference, if one exists.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPreload"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
use Libraries.Web.Attribute
Audio audio
Attribute play = audio:GetPreload()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the address where the audio file resides. This address is relative to the root of the server.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
audio:SetAddress("resources/test.ogg")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoPlay:boolean">public action SetAutoPlay(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoPlay:boolean"><p>This action should be set to true if the web page should automatically try to play the specified audio file as soon as it is ready. Ready, in this context means that a sufficient amount of audio has been buffered. Setting this to false will remove autoplay from the page.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutoPlay:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
audio:SetAutoPlay(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetControls:boolean">public action SetControls(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetControls:boolean"><p>This action should be set to true if the web page should display controls for playing an audio file. Setting this to false will remove contols from the page.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetControls:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
audio:SetControls(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLoop:boolean">public action SetLoop(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLoop:boolean"><p>This action is set to true if the audio file played on this web page should automatically repeat itself after it is complete. Setting this to false will remove looping from the page.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLoop:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
audio:SetLoop(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPreload:text">public action SetPreload(text value)</span></h3>

	<p>This action sets information on how audio should be loaded from this tag. There are three options for passing information to this action, which are written into the constants : 1) preloadAuto, 2) preloadMetadata, and 3) preloadNone.</p><span class="controllable" data-componentType="action-description" data-actionkey="SetPreload:text"><p><p> Auto specifies that the audio should be loaded automatically when the page loads. The Metadata tag means that the page should load information about the audio file to be played, but that the actual audio should not be loaded. The "none" tag specifies that no information about the audio should be loaded when the page loads. </p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPreload:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Audio
Audio audio
audio:SetPreload(audio:preloadAuto)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/MediaAttributeAccepter.php">Libraries.Web.MediaAttributeAccepter</a> public action GetOnAbort(), public action GetOnCanPlay(), public action GetOnCanPlayThrough(), public action GetOnDurationChange(), public action GetOnEmptied(), public action GetOnEnded(), public action GetOnLoadStart(), public action GetOnLoadedData(), public action GetOnLoadedMetaData(), public action GetOnMediaError(), public action GetOnPause(), public action GetOnPlay(), public action GetOnPlaying(), public action GetOnProgress(), public action GetOnRateChange(), public action GetOnReadyStateChange(), public action GetOnSeeked(), public action GetOnSeeking(), public action GetOnStalled(), public action GetOnSuspend(), public action GetOnTimeUpdate(), public action GetOnVolumeChange(), public action GetOnWaiting(), public action SetOnAbort(text value), public action SetOnCanPlay(text value), public action SetOnCanPlayThrough(text value), public action SetOnDurationChange(text value), public action SetOnEmptied(text value), public action SetOnEnded(text value), public action SetOnLoadStart(text value), public action SetOnLoadedData(text value), public action SetOnLoadedMetaData(text value), public action SetOnMediaError(text value), public action SetOnPause(text value), public action SetOnPlay(text value), public action SetOnPlaying(text value), public action SetOnProgress(text value), public action SetOnRateChange(text value), public action SetOnReadyStateChange(text value), public action SetOnSeeked(text value), public action SetOnSeeking(text value), public action SetOnStalled(text value), public action SetOnSuspend(text value), public action SetOnTimeUpdate(text value), public action SetOnVolumeChange(text value), public action SetOnWaiting(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>