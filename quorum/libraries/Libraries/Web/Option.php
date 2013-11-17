<?php $classPageTitle = "Option"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Option</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Option" /><h2> <span class="controllable" data-componentType="class-name">Class Option</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Option class represents HTML's (Hypertext Markup Language) option tag which is used to define an option item inside a select or datalist. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_option.asp">The option attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Select select
      Option option
      option:SetLabel("Alabama")
      select:AddOption(option)
      page:AddToBody(select)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDefaultValue">public action GetDefaultValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDefaultValue"><p>This action gets the default value of the option.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The default value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDefaultValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
use Libraries.Web.Attribute
Option opt
Attribute result = opt:GetDefaultValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action gets wether the option in a datalist or select is disabled (true) or enabled(false).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if diabled and false if enabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
Option opt
boolean result = opt:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLabel">public action GetLabel()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLabel"><p>This action gets the options labeling text.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The label.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLabel"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
use Libraries.Web.Attribute
Option opt
Attribute result = opt:GetLabel()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSelected">public action GetSelected()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSelected"><p>This action gets wether the option in a datalist or select is selected or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The attribute containing the select information.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSelected"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
Option opt
boolean result = opt:GetSelected()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDefaultValue:text">public action SetDefaultValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDefaultValue:text"><p>This action sets the option to have a default value that is sent to the server.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be sent to the server.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDefaultValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
Option opt
opt:SetDefaultValue("my text")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action sets the option to be disabled if true and enabled if false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True to disable and false to enable the option.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
Option opt
opt:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLabel:text">public action SetLabel(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLabel:text"><p>This action sets the options labeling text.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The labeling text.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLabel:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
Option opt
opt:SetLabel("Alabama")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSelected:boolean">public action SetSelected(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSelected:boolean"><p>This action sets the option to be selected if true and unselected if false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True to select and false to unselect the option.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSelected:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Option
Option opt
opt:SetSelected(true)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>