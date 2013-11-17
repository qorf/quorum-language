<?php $classPageTitle = "Select"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Select</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Select" /><h2> <span class="controllable" data-componentType="class-name">Class Select</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Select class represents HTML's (Hypertext Markup Language) select tag which is a selection list of options or items. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_select.asp">The select attribute</a>.</span></p>

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

	<span class="controllable" data-componentType="action-description" data-actionkey="AddOption:Libraries.Web.Option"><p>This action adds an option to the slecet list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="option"><strong>Libraries.Web.Option</strong> <em>option</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="option"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddOption:Libraries.Web.Option"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.Option
Select s
s:SetName("States")
Option option
option:SetLabel("Alabama")
s:AddOption(option)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddOptionGroup:Libraries.Web.OptionGroup">public action AddOptionGroup(Libraries.Web.OptionGroup optionGroup)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddOptionGroup:Libraries.Web.OptionGroup"><p>This action adds an option group to the slecet list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="optionGroup"><strong>Libraries.Web.OptionGroup</strong> <em>optionGroup</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="optionGroup"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddOptionGroup:Libraries.Web.OptionGroup"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.OptionGroup
Select s
s:SetName("States")
OptionGroup og
og:SetLabel("Alabama")
s:AddOptionGroup(og)</pre>

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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutofocus">public action GetAutofocus()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutofocus"><p>This action gets whether the Select list is automatically got focus (true) or not(false).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if auto focused and false if not auto focused.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutofocus"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
boolean result = select:GetAutofocus()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action gets whether the Select list is disabled (true) or enabled(false).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if diabled and false if enabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
boolean result = select:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormId">public action GetFormId()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormId"><p>This action gets a form that the select belongs to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The form a select list belongs to.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormId"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.Attribute
Select select
Attribute result = select:GetFormId()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMultiple">public action GetMultiple()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMultiple"><p>This action gets whether the Select list can have more than one option selected (true) or not(false).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if more than one can be selected and false if not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMultiple"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
boolean result = select:GetMultiple()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name of the select list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: Name of the select list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.Attribute
Select select
Attribute result = select:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action gets the number of visible options in the select list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The number of options.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.Attribute
Select select
Attribute result = select:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveOption:Libraries.Web.Option">public action RemoveOption(Libraries.Web.Option option)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveOption:Libraries.Web.Option"><p>This action removes an option from the slecet list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="option"><strong>Libraries.Web.Option</strong> <em>option</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="option"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveOption:Libraries.Web.Option"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.Option
Select s
s:SetName("States")
Option option
option:SetLabel("Alabama")
s:AddOption(option)
s:RemoveOption(option)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveOptionGroup:Libraries.Web.OptionGroup">public action RemoveOptionGroup(Libraries.Web.OptionGroup optionGroup)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveOptionGroup:Libraries.Web.OptionGroup"><p>This action removes an option group from the slecet list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="optionGroup"><strong>Libraries.Web.OptionGroup</strong> <em>optionGroup</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="optionGroup"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveOptionGroup:Libraries.Web.OptionGroup"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
use Libraries.Web.OptionGroup
Select s
s:SetName("States")
OptionGroup og
og:SetLabel("Alabama")
s:AddOptionGroup(og)
s:RemoveOptionGroup(og)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutofocus:boolean">public action SetAutofocus(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutofocus:boolean"><p>This action sets the select list to be auto focused if true and not if false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True to auto focused and false to not auto focus the select.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutofocus:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
select:SetAutofocus(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action sets the select list to be disabled if true and enabled if false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True to disable and false to enable the select.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
select:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormId:text">public action SetFormId(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormId:text"><p>This action sets the form a select list is associated with.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name of the associated form.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormId:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
select:SetFormId("myForm")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMultiple:boolean">public action SetMultiple(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMultiple:boolean"><p>This action sets the select list to be able to select more than one option if true and not if false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True to multiple select and false to not allow 
        multiple option to be selected.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMultiple:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
select:SetMultiple(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name of the select list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name of the select list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
select:SetName("mySelect")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSize:text">public action SetSize(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSize:text"><p>This action sets the number of visible options in the select list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The size.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSize:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Select
Select select
select:SetName("5")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>