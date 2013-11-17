<?php $classPageTitle = "PerformanceGauge"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.PerformanceGauge</h1>
<input type="hidden" id="classkey" value="Libraries.Web.PerformanceGauge" /><h2> <span class="controllable" data-componentType="class-name">Class PerformanceGauge</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The PerformanceGauge class represents HTML's (Hypertext Markup Language) meter tag which is used to display a bar gauge that shows performace. This is not to show progress, use the ProgressBar class for that. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_meter.asp">The meter attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      PerformanceGauge pGauge
      pGauge:SetHighValue("8")
      pGauge:SetLowValue("2")
      pGauge:SetMaxValue("10")
      pGauge:SetMinValue("0")
      pGauge:SetCurrentValue("5")
      page:AddToBody(pGauge)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCurrentValue">public action GetCurrentValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCurrentValue"><p>This action gets the set current value for the PerformanceGauge.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current value for the PerformanceGauge.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCurrentValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
Attribute currentValue = pGauge:GetCurrentValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHighValue">public action GetHighValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHighValue"><p>This action gets the set high value for the PerformanceGauge.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The high value for the PerformanceGauge.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHighValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
Attribute highValue = pGauge:GetHighValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLowValue">public action GetLowValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLowValue"><p>This action gets the set low value for the PerformanceGauge.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The low value for the PerformanceGauge.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLowValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.PerformanceGauge
use Libraries.Web.Attribute
PerformanceGauge pGauge
Attribute lowValue = pGauge:GetLowValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxValue">public action GetMaxValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxValue"><p>This action gets the set max value for the PerformanceGauge.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The max value for the PerformanceGauge.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaxValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
Attribute maxValue = pGauge:GetMaxValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMinValue">public action GetMinValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMinValue"><p>This action gets the set min value for the PerformanceGauge.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The min value for the PerformanceGauge.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMinValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
Attribute minValue = pGauge:GetMinValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCurrentValue:text">public action SetCurrentValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCurrentValue:text"><p>This action sets the current value. The current value is value that will be shown where it falls within the max and min values to make the PerformanceGauge. The current value will be divided by the max number minus the min value to fill in the bar that percentage amount.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCurrentValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
pGauge:SetCurrentValue("5")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHighValue:text">public action SetHighValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHighValue:text"><p>This action sets the high value. The high value is a limit that will change the color of the PerformanceGauge if the current value exceeds the high value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHighValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
pGauge:SetHighValue("8")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLowValue:text">public action SetLowValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLowValue:text"><p>This action sets the low value. The low value is a limit that will change the color of the PerformanceGauge if the current value exceeds the low value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLowValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
pGauge:SetLowValue("2")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMaxValue:text">public action SetMaxValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMaxValue:text"><p>This action sets the max value. The max value is the upper limit of the PerformanceGauge. The current value will be divided by this number minus the min value to fill in the bar that percentage amount.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMaxValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
pGauge:SetMaxValue("10")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMinValue:text">public action SetMinValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMinValue:text"><p>This action sets the min value. The min value is the lower limit of the PerformanceGauge. The current value will be divided by the max number minus the this value to fill in the bar that percentage amount.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMinValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.PerformanceGauge
PerformanceGauge pGauge
pGauge:SetMinValue("0")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>