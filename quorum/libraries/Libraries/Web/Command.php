<?php $classPageTitle = "Command"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Command</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Command" /><h2> <span class="controllable" data-componentType="class-name">Class Command</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This is only supported in Internet Explorer 9 (IE9). Only features that are supported have been added.  The Command class represents HTML's (Hypertext Markup Language) command tag which is used to add a command script to a radio button, checkbox, or button. Using the Menu class it can be added to the page directly. The command script can also be added to a context menu or toolbar. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_command.asp">The command attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      Command command
      command:SetType(command:commandType)
      command:SetDescription("Save")
      page:AddToBody(command)
      
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>checkBoxType</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>commandType</em></li>
<li class = "package_standard" ><strong>text</strong> <em>radioButtonType</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabledCommand">public action GetDisabledCommand()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabledCommand"><p>This action returns true if the command on the page is set to be disabled.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if the command is disabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabledCommand"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
Command command
boolean result = command:GetDisabledCommand()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the MIME type attribute of the command. The type attribute maps to a MIME type of the command.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The MIME type attribute of the command.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Command
use Libraries.Web.Attribute
Command command
Attribute type = command:GetType()
if type not= undefined
   text mimeType = type:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabledCommand:boolean">public action SetDisabledCommand(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabledCommand:boolean"><p>This action should be set to true if the web page should disable the command.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabledCommand:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
Command command
command:SetDisabledCommand(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the Type attribute of the command. The type attribute maps to a MIME type of the command</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The MIME type for the command.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Command
Command command
command:SetType("text/html")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>