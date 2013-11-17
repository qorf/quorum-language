<?php $classPageTitle = "Abbreviation"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Abbreviation</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Abbreviation" /><h2> <span class="controllable" data-componentType="class-name">Class Abbreviation</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Abbrevation class represents HTML's (Hypertext Markup Language) abbr tag which is used to create tooltips of the full text of an abbrevation. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_abbr.asp">The abbr attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Abbreviation abbr
      abbr:SetTitle("National Football League")
      abbr:SetDescription("NFL")
      Bold boldAbbrText
      boldAbbrText:SetDescription("The "+abbr:Generate()+" is fun")
      page:AddToBody(boldAbbrText)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTitle">public action GetTitle()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTitle"><p>This action gets the title attribute of the Abberviation.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The full title of the abbreviation</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTitle"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Abbreviation
use Libraries.Web.Attribute
Abbreviation abbr
Attribute abbrTitle = abbr:GetTitle()
if abbrTitle not= undefined
   text title = abbrTitle:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTitle:text">public action SetTitle(text abbrTitle)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTitle:text"><p>This action sets the title attribute of the Abberviation. The title is the unabbreviated form of the abbreviation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="abbrTitle"><strong>text</strong> <em>abbrTitle</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="abbrTitle"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTitle:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Abbreviation
Abbreviation abbr
abbr:SetTitle("National Football League")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>