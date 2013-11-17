<?php $classPageTitle = "Details"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Details</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Details" /><h2> <span class="controllable" data-componentType="class-name">Class Details</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Details class represents HTML's (Hypertext Markup Language) details tag which is used to show or hide additional details about an item with a collapsible view.  The Summary class can be used to add a title to Details. Details can be set to be open by default using SetOpen. It is set to be closed otherwise.  You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_details.asp">The details attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      WebPage page
      Details details
      details:SetOpen("The URL(web address) you are citing")
      quote:SetDescription("Insert your quotation here")
      page:AddToBody(quote)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOpen">public action GetOpen()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOpen"><p>This action gets the open value. If true the detail is visible to the user.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if visible and false if not visible.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOpen"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Details
use Libraries.Web.Attribute
Details details
details:SetOpen(true)
boolean isOpen = details:GetOpen()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOpen:boolean">public action SetOpen(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOpen:boolean"><p>This action sets the open field. When open is true the detail is visible to the user.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True for visible and false for invisible to the user.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOpen:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Details
use Libraries.Web.Attribute
Details detail
detail:SetOpen(true)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>