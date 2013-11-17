<?php $classPageTitle = "Track"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Track</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Track" /><h2> <span class="controllable" data-componentType="class-name">Class Track</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Track class represents HTML's (Hypertext Markup Language) track tag which is used to text tracks (subtitle or lyrics) to audio or video. This is currently not supported in any browser. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_track.asp">The track attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      WebPage page
      Video video
      video:SetWidth("350")
      video:SetHeight("270")
      video:SetControlsVisible(true)
      Track track
      track:SetAddress("subtitles.vtt")
      video:Add(track)
      page:AddToBody(video)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>captionsKind</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>chaptersKind</em></li>
<li class = "package_standard" ><strong>text</strong> <em>descriptionsKind</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>metadataKind</em></li>
<li class = "package_standard" ><strong>text</strong> <em>subtitlesKind</em></li>
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

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the source or URL of the track.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The URL or the location and name of the track file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetAddress("myTrack.vtt")
Attribute source = track:GetAddress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDefault">public action GetDefault()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDefault"><p>This action gets wether a track as defualt or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the track is default and false if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDefault"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetDefault(true)
boolean default = track:GetDefault()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKind">public action GetKind()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKind"><p>This action gets the kind of track. The track can be one of the follow: captions (closed captions suitable for deaf users), chapters(defines chapter titles), descriptions(describes the video and is suitable for a blind or visually impaired user), metadata (defines content for scripts and is not visible to the user), or subtitles.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKind"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetKind("captions")
Attribute trackType = track:GetKind()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLabel">public action GetLabel()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLabel"><p>This action gets the title of the track.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The track title.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLabel"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetLabel("my title")
Attribute label = track:GetLabel()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSourceLanguageCode">public action GetSourceLanguageCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSourceLanguageCode"><p>This action sets the language associated with the track. This is required if the track "kind" is subtitle.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The track language.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSourceLanguageCode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetSourceLanguageCode("en")
Attribute language = track:GetSourceLanguageCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the source or URL of the track.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The URL or the location and name of the track file.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetAddress("myTrack.vtt")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDefault:boolean">public action SetDefault(boolean isDefault)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDefault:boolean"><p>This action sets this track as defualt, as long as the users preferences do not dictate an alternative.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="isDefault"><strong>boolean</strong> <em>isDefault</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="isDefault">Sets the track to be default if true.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDefault:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetDefault(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetKind:text">public action SetKind(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetKind:text"><p>This action sets the kind of track. The track can be one of the follow: captions (closed captions suitable for deaf users), chapters(defines chapter titles), descriptions(describes the video and is suitable for a blind or visually impaired user), metadata (defines content for scripts and is not visible to the user), or subtitles.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The track type.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetKind:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetKind("captions")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLabel:text">public action SetLabel(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLabel:text"><p>This action sets the title of the track.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The track title.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLabel:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetLabel("my title")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSourceLanguageCode:text">public action SetSourceLanguageCode(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSourceLanguageCode:text"><p>This action sets the language associated with the track. This is required if the track "kind" is subtitle.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The track language.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSourceLanguageCode:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Track
use Libraries.Web.Attribute
Track track
track:SetSourceLanguageCode("en")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>