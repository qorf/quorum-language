<?php $classPageTitle = "OptionGroup"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.OptionGroup</h1>
<input type="hidden" id="classkey" value="Libraries.Web.OptionGroup" /><h2> <span class="controllable" data-componentType="class-name">Class OptionGroup</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The OptionGroup class represents HTML's (Hypertext Markup Language) optiongroup tag which is used to group options in a select or data list. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_optgroup.asp">The optgroup attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Select select
      OptionGroup og
      og:SetLabel("States")
      select:AddOptionGroup(og)
      page:AddToBody(select)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddOption:Libraries.Web.Option">public action AddOption(Libraries.Web.Option option)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddOption:Libraries.Web.Option"><p>This action adds an option to the option group.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="option"><strong>Libraries.Web.Option</strong> <em>option</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="option"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddOption:Libraries.Web.Option"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Option
OptionGroup og
og:SetLabel("States")
Option option
option:SetLabel("Alabama")
og:AddOption(option)</pre>

</div><div class="action">
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action gets whether the option group in a datalist or select is disabled (true) or enabled(false).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if diabled and false if enabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Attribute
OptionGroup og
boolean result = og:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLabel">public action GetLabel()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLabel"><p>This action gets the option groups labeling text.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: Labeling text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLabel"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Attribute
OptionGroup og
Attribute result = og:GetLabel()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfOptions">public action GetNumberOfOptions()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfOptions"><p>This action gets the number of options in an option group.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of options in an option group.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfOptions"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Attribute
OptionGroup og
integer result = og:GetNumberOfOptions()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveOption:Libraries.Web.Option">public action RemoveOption(Libraries.Web.Option option)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveOption:Libraries.Web.Option"><p>This action removes an option from the option group.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="option"><strong>Libraries.Web.Option</strong> <em>option</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="option"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveOption:Libraries.Web.Option"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Option
OptionGroup og
og:SetLabel("States")
Option option
option:SetLabel("Alabama")
og:AddOption(option)

og:RemoveOption(option)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action sets the option group to be disabled if true and enabled if false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True to disable and false to enable the option.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Attribute
OptionGroup og
og:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLabel:text">public action SetLabel(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLabel:text"><p>This action sets the option group label.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The labeling text.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLabel:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.OptionGroup
use Libraries.Web.Attribute
OptionGroup og
og:SetLabel("states")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>