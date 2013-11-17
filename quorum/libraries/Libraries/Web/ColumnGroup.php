<?php $classPageTitle = "ColumnGroup"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.ColumnGroup</h1>
<input type="hidden" id="classkey" value="Libraries.Web.ColumnGroup" /><h2> <span class="controllable" data-componentType="class-name">Class ColumnGroup</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The ColumnGroup class represents HTML's (Hypertext Markup Language) colgroup tag which allows one or more columns to be selected for formatting. Columns should be added to the ColumnGroup. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_colgroup.asp">The colgroup attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      
      //create a table
      Table table
      //create a column group
      ColumnGroup cg
      //create a column
      Column col
      //setup that column
      col:SetSpan(2)
      //add the column to the column group
      cg:Add(col)
      //add the column group to the table
      table:SetColumnGroup(cg)
      page:AddToBody(table)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSpan">public action GetSpan()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSpan"><p>This action gets the span attribute of the ColumnGroup. The span is the number of units the column group encompasses.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The span of the column group.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSpan"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.ColumnGroup
ColumnGroup cg
Attribute span = cg:GetSpan()
if span not= undefined
   text s = span:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSpan:integer">public action SetSpan(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSpan:integer"><p>This action sets the span attribute of the column group. The span attribute is the number of units the column group encompasses.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The span.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSpan:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.ColumnGroup
ColumnGroup cg
cg:SetSpan(2)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>