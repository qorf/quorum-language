<?php $classPageTitle = "MediaAttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.MediaAttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.MediaAttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class MediaAttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The MediaAttributeAccepter class is designed as a helper to ease adding and removing keyboard event attributes from particular WebTag objects. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the MediaAttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
class audio is MediaAttributeAccepter, WebGenerator
   action Generate returns text
      text result = "&lt;audio "
      Attributes attributes = parent:WebTag:GetAttributes()
      attributeText = attributes:Generate()
      result = result + attributeText + "&gt;"
      result = result + me:GenerateNestedTags()
      result = result + GetDescription() + "&lt;/audio&gt;"
      return result
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnAbort">public action GetOnAbort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnAbort"><p>Returns the OnAbort attribute. If a on abort event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnAbort attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnAbort"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnAbort()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnCanPlay">public action GetOnCanPlay()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnCanPlay"><p>Returns the OnCanPlay attribute. If a can play event (a file has buffered enough to play) occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnCanPlay attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnCanPlay"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnCanPlay()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnCanPlayThrough">public action GetOnCanPlayThrough()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnCanPlayThrough"><p>Returns the OnCanPlayThrough attribute. If a play all the way through (the file can play without pausing for buffering) event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnCanPlayThrough attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnCanPlayThrough"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnCanPlayThrough()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDurationChange">public action GetOnDurationChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDurationChange"><p>Returns the OnDurationChange attribute. If the durration of a a media file changes, this event will occur on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDurationChange attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDurationChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnDurationChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnEmptied">public action GetOnEmptied()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnEmptied"><p>Returns the OnEmptied attribute. If the file becomes unavailable this event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnEmptied attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnEmptied"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnEmptied()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnEnded">public action GetOnEnded()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnEnded"><p>Returns the OnEnded attribute. If the media file reaches the end event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnEnded attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnEnded"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnEnded()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnLoadStart">public action GetOnLoadStart()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnLoadStart"><p>Returns the OnLoadStart attribute. If a load event is about to start on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnLoadStart attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnLoadStart"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnLoadStart()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnLoadedData">public action GetOnLoadedData()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnLoadedData"><p>Returns the OnLoadedData attribute. If a media loaded event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnLoadedData attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnLoadedData"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnLoadedData()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnLoadedMetaData">public action GetOnLoadedMetaData()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnLoadedMetaData"><p>Returns the OnLoadedMetaData attribute. If the meta data is loaded event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnLoadedMetaData attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnLoadedMetaData"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnLoadedMetaData()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMediaError">public action GetOnMediaError()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMediaError"><p>Returns the OnMediaError attribute. If a load error event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMediaError attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMediaError"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnMediaError()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnPause">public action GetOnPause()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnPause"><p>Returns the OnPause attribute. If a pause event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnPause attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnPause"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnPause()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnPlay">public action GetOnPlay()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnPlay"><p>Returns the OnPlay attribute. If a play event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnPlay attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnPlay"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnPlay()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnPlaying">public action GetOnPlaying()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnPlaying"><p>Returns the OnPlaying attribute. If a play event is currently firing on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnPlaying attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnPlaying"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnPlaying()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnProgress">public action GetOnProgress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnProgress"><p>Returns the OnProgress attribute. If a on progress event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnProgress attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnProgress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnProgress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnRateChange">public action GetOnRateChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnRateChange"><p>Returns the OnRateChange attribute. If a on play rate change event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnRateChange attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnRateChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnRateChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnReadyStateChange">public action GetOnReadyStateChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnReadyStateChange"><p>Returns the OnReadyStateChange attribute. If the ready state event changes on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnReadyStateChange attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnReadyStateChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnReadyStateChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnSeeked">public action GetOnSeeked()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnSeeked"><p>Returns the OnSeeked attribute. If a seek event has occured on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnSeeked attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnSeeked"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnSeeked()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnSeeking">public action GetOnSeeking()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnSeeking"><p>Returns the OnSeeking attribute. If a seek event is currently occuring on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnSeeking attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnSeeking"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnSeeking()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnStalled">public action GetOnStalled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnStalled"><p>Returns the OnStalled attribute. If a stall event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnAbort OnStalled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnStalled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnStalled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnSuspend">public action GetOnSuspend()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnSuspend"><p>Returns the OnSuspend attribute. If a on suspend event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnSuspend attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnSuspend"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnSuspend()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnTimeUpdate">public action GetOnTimeUpdate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnTimeUpdate"><p>Returns the OnTimeUpdate attribute. If the playing position changes on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnTimeUpdate attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnTimeUpdate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnTimeUpdate()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnVolumeChange">public action GetOnVolumeChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnVolumeChange"><p>Returns the OnVolumeChange attribute. If a volume change event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnVolumeChange attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnVolumeChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnVolumeChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnWaiting">public action GetOnWaiting()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnWaiting"><p>Returns the OnWaiting attribute. If a pause event occurs and it is expected to continue on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnWaiting attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnWaiting"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
Attribute attribute = accept:GetOnWaiting()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnAbort:text">public action SetOnAbort(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnAbort:text"><p>Sets the OnAbort attribute script when the OnAbort event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnAbort attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnAbort:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnAbort("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnCanPlay:text">public action SetOnCanPlay(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnCanPlay:text"><p>Sets the OnCanPlay attribute script when the OnCanPlay event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnCanPlay attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnCanPlay:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnCanPlay("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnCanPlayThrough:text">public action SetOnCanPlayThrough(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnCanPlayThrough:text"><p>Sets the OnCanPlayThrough attribute script when the OnCanPlayThrough event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnCanPlayThrough attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnCanPlayThrough:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnCanPlayThrough("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDurationChange:text">public action SetOnDurationChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDurationChange:text"><p>Sets the OnDurationChange attribute script when the OnDurationChange event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDurationChange attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDurationChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnDurationChange("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnEmptied:text">public action SetOnEmptied(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnEmptied:text"><p>Sets the OnEmptied attribute script when the OnEmptied event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnEmptied attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnEmptied:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnEmptied("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnEnded:text">public action SetOnEnded(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnEnded:text"><p>Sets the OnEnded attribute script when the OnEnded event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnEnded attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnEnded:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnEnded("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnLoadStart:text">public action SetOnLoadStart(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnLoadStart:text"><p>Sets the OnLoadStart attribute script when the OnLoadStart event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnLoadStart attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnLoadStart:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnLoadStart("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnLoadedData:text">public action SetOnLoadedData(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnLoadedData:text"><p>Sets the OnLoadedData attribute script when the OnLoadedData event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnLoadedData attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnLoadedData:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnLoadedData("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnLoadedMetaData:text">public action SetOnLoadedMetaData(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnLoadedMetaData:text"><p>Sets the OnLoadedMetaData attribute script when the OnLoadedMetaData event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnLoadedMetaData attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnLoadedMetaData:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnLoadedMetaData("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMediaError:text">public action SetOnMediaError(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMediaError:text"><p>Sets the OnMediaError attribute script when the OnMediaError event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMediaError attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMediaError:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnMediaError("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnPause:text">public action SetOnPause(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnPause:text"><p>Sets the OnPause attribute script when the OnPause event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnPause attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnPause:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnPause("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnPlay:text">public action SetOnPlay(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnPlay:text"><p>Sets the OnPlay attribute script when the OnPlay event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnPlay attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnPlay:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnPlay("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnPlaying:text">public action SetOnPlaying(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnPlaying:text"><p>Sets the OnPlaying attribute script when the OnPlaying event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnPlaying attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnPlaying:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnPlaying("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnProgress:text">public action SetOnProgress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnProgress:text"><p>Sets the OnProgress attribute script when the OnProgress event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnProgress attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnProgress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnProgress("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnRateChange:text">public action SetOnRateChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnRateChange:text"><p>Sets the OnRateChange attribute script when the OnRateChange event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnRateChange attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnRateChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnRateChange("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnReadyStateChange:text">public action SetOnReadyStateChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnReadyStateChange:text"><p>Sets the OnReadyStateChange attribute script when the OnReadyStateChange event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnReadyStateChange attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnReadyStateChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnReadyStateChange("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnSeeked:text">public action SetOnSeeked(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnSeeked:text"><p>Sets the OnSeeked attribute script when the OnSeeked event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnSeeked attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnSeeked:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnSeeked("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnSeeking:text">public action SetOnSeeking(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnSeeking:text"><p>Sets the OnSeeking attribute script when the OnSeeking event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnSeeking attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnSeeking:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnSeeking("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnStalled:text">public action SetOnStalled(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnStalled:text"><p>Sets the OnStalled attribute script when the OnStalled event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnStalled attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnStalled:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnStalled("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnSuspend:text">public action SetOnSuspend(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnSuspend:text"><p>Sets the OnSuspend attribute script when the OnSuspend event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnSuspend attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnSuspend:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnSuspend("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnTimeUpdate:text">public action SetOnTimeUpdate(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnTimeUpdate:text"><p>Sets the OnTimeUpdate attribute script when the OnTimeUpdate event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnTimeUpdate attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnTimeUpdate:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnTimeUpdate("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnVolumeChange:text">public action SetOnVolumeChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnVolumeChange:text"><p>Sets the OnVolumeChange attribute script when the OnVolumeChange event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnVolumeChange attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnVolumeChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnVolumeChange("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnWaiting:text">public action SetOnWaiting(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnWaiting:text"><p>Sets the OnWaiting attribute script when the OnWaiting event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnWaiting attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnWaiting:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MediaAttributeAccepter
use Libraries.Web.Attribute
MediaAttributeAccepter accept
accept:SetOnWaiting("doSomething()")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>