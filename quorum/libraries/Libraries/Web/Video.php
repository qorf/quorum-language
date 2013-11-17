<?php $classPageTitle = "Video"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Video</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Video" /><h2> <span class="controllable" data-componentType="class-name">Class Video</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Video class represents HTML's (Hypertext Markup Language) video tag which is used to add a video to the page. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_video.asp">The video attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      WebPage page
      Video video
      video:SetWidth("350")
      video:SetHeight("270")
      video:SetControlsVisible(true)
      page:AddToBody(video)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>autoPreload</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>metaDataPreload</em></li>
<li class = "package_standard" ><strong>text</strong> <em>noPreload</em></li>
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

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the URL or location of the video.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The URL or location of the video.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
use Libraries.Web.Attribute
Video video
video:SetAddress("myMovie.ogg")
Attribute source = video:GetAddress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoPlay">public action GetAutoPlay()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoPlay"><p>This action gets a boolean of whether autoplay is enable or not. If autoplay it enable true is returned and if autoplay is disabled false is returned.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The autoplay state.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutoPlay"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetAutoPlay(true)
boolean autoplay = video:GetAutoPlay()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetControlsVisible">public action GetControlsVisible()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetControlsVisible"><p>This action gets a boolean of whether the video controls are visible or not. Passing a value of true will make the video controls visible and a value of false will make the video controls invisible to the user.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The video control's state of visiblity.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetControlsVisible"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetControlsVisible(true)
boolean controls = video:GetControlsVisible()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeight">public action GetHeight()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeight"><p>This action gets the height of the video viewing area.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The height of the video viewing area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeight"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
use Libraries.Web.Attribute
Video video
video:SetHeight("250")
Attribute height = video:GetHeight()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLoop">public action GetLoop()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLoop"><p>This action gets a boolean of whether the video should loop or not. If true the video playback will be looped and if false video playback will play once.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the video will loop and false if it will play once.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLoop"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetLoop(true)
boolean loop = video:GetLoop()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMuted">public action GetMuted()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMuted"><p>This action gets a boolean of whether sound is muted or not. If muted the value of true will be returned and false if the sound is not muted.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if muted and false if not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMuted"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetMuted(true)
boolean muted = video:GetMuted()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPoster">public action GetPoster()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPoster"><p>This action gets the URL or location of the image to be displayed while the video is loading or before the play button has been pressed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The URL or location of the image to display during load.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPoster"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
use Libraries.Web.Attribute
Video video
video:SetPoster("poster.gif")
Attribute poster = video:GetPoster()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPreload">public action GetPreload()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPreload"><p>This action gets the way the video is to be loaded. The options include, "auto" to load the video when the page loads, "metadata" to load only meta data when the page loads, and "none" if the video should not be loaded when the page loads.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The preload specifications.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPreload"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
use Libraries.Web.Attribute
Video video
video:SetPreload("metadata")
Attribute preload = video:GetPreload()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWidth">public action GetWidth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWidth"><p>This action gets the width of the video viewing area.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The width of the video viewing area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWidth"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
use Libraries.Web.Attribute
Video video
video:SetWidth("250")
Attribute width = video:GetWidth()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the URL or location of the video.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The URL or location of the video.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetAddress("myMovie.ogg")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoPlay:boolean">public action SetAutoPlay(boolean isAutoPlay)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoPlay:boolean"><p>This action sets the autoplay of a video. If autoplay is set to true autoplay will be enabled. If autoplay is set to false autoplay will be disabled.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="isAutoPlay"><strong>boolean</strong> <em>isAutoPlay</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="isAutoPlay"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutoPlay:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetAutoPlay(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetControlsVisible:boolean">public action SetControlsVisible(boolean controlsVisible)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetControlsVisible:boolean"><p>This action sets the controls of a video to visible. If controls are set to true the controls will be visible in the video. If controls are set to false they will not be displayed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="controlsVisible"><strong>boolean</strong> <em>controlsVisible</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="controlsVisible">True to make the video conrols visible and false to make them invisible.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetControlsVisible:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetControlsVisible(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeight:text">public action SetHeight(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeight:text"><p>This action sets the height of the video viewing area.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The height of the video viewing area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeight:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetHeight("250")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLoop:boolean">public action SetLoop(boolean loopVideo)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLoop:boolean"><p>This action sets the loop of a video. If loop is set to true the video will loop. If loop is set to false the video will not loop the video.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="loopVideo"><strong>boolean</strong> <em>loopVideo</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="loopVideo">True to loop and false to not loop the video.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLoop:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetLoop(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMuted:boolean">public action SetMuted(boolean isMuted)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMuted:boolean"><p>This action sets a boolean of whether sound is muted or not. If muted the value of true will be returned and false if the sound is not muted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="isMuted"><strong>boolean</strong> <em>isMuted</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="isMuted">True if muted and false if not.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMuted:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetMuted(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPoster:text">public action SetPoster(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPoster:text"><p>This action sets the URL or location of the image to be displayed while the video is loading or before the play button has been pressed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The URL or location of the image to display during load.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPoster:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetPoster("poster.gif")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPreload:text">public action SetPreload(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPreload:text"><p>This action sets the way the video is to be loaded. The options include, "auto" to load the video when the page loads, "metadata" to load only meta data when the page loads, and "none" if the video should not be loaded when the page loads.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPreload:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetPreload("none")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWidth:text">public action SetWidth(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWidth:text"><p>This action sets the width of the video viewing area.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The width of the video viewing area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWidth:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Video
Video video
video:SetWidth("250")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>