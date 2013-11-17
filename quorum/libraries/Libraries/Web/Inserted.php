<?php $classPageTitle = "Inserted"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Inserted</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Inserted" /><h2> <span class="controllable" data-componentType="class-name">Class Inserted</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Inserted class represents HTML's (Hypertext Markup Language) ins tag which is used to show text that has been inserted into a document. This underlines the inserted text. Citation of the text can be added, as well as, a time stamp of when the text was inserted.  You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_ins.asp">The ins attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      Paragraph p
      Deleted deleted
      Inserted inserted
      deleted:SetDescription("apples")
      inserted:SetDescription("bananas")
      p:SetDescription("I love " + deleted:Generate() + inserted:Generate())
      
      page:AddToBody(p)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCitation">public action GetCitation()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCitation"><p>This action gets the citation URL or web address.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The citation address.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCitation"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Inserted
use Libraries.Web.Attribute
Inserted inserted
inserted:SetCitation("http://www.w3schools.com/tags/tag_ins.asp")
Attribute address = inserted:GetCitation()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetInsertedTime">public action GetInsertedTime()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetInsertedTime"><p>This action gets the set inserted time of the text.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The date time of the inserted text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetInsertedTime"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Inserted
use Libraries.Web.Attribute
Inserted inserted
inserted:SetInsertedTime("2011-11-15T22:55:03Z")
Attribute dateTime = inserted:GetInsertedTime()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCitation:text">public action SetCitation(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCitation:text"><p>This action sets the citation URL or web address.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The citation URL or web address.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCitation:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Inserted
Inserted inserted
inserted:SetCitation("http://www.w3schools.com/tags/tag_ins.asp")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetInsertedTime:text">public action SetInsertedTime(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetInsertedTime:text"><p>This action sets the inserted time of the text.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The date and time.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetInsertedTime:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Inserted
Inserted inserted
inserted:SetInsertedTime("2011-11-15T22:55:03Z")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>