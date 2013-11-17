<?php $classPageTitle = "WebTable"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.WebTable</h1>
<input type="hidden" id="classkey" value="Libraries.Web.WebTable" /><h2> <span class="controllable" data-componentType="class-name">Class WebTable</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The WebTable class represents HTML's (Hypertext Markup Language) table tag which is a class that builds a table. This table contains any number of rows that have been added to the table.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      WebTable table
      //add a TableRow
      TableRow row
      table:AddRow(row)
      page:AddToBody(table)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddRow:Libraries.Web.TableRow">public action AddRow(Libraries.Web.TableRow value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddRow:Libraries.Web.TableRow"><p>This action adds a row to the table element. See TableRow for the construction of a row in a table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.TableRow</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">A TableRow to be added to the table.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddRow:Libraries.Web.TableRow"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebTable
use Libraries.Web.TableRow
use Libraries.Web.TableHeader
WebTable table
TableRow row
TableHeader header
header:SetDescription("name")
row:Add(header)
table:AddRow(row)</pre>

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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetBorder">public action GetBorder()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetBorder"><p>This action gets the border attribute of the web table and returns true if there is a border around the cells and false if it does not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the cells have a border and false if not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetBorder"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebTable
WebTable table
boolean hasBorder = table:GetBorder()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetColumnGroup">public action GetColumnGroup()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetColumnGroup"><p>This action gets the Column group attribute of the web table and returns it. The column group is used to format an entire column or group of columns.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.ColumnGroup</strong>: The column group.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetColumnGroup"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebTable
use Libraries.Web.ColumnGroup
WebTable table
ColumnGroup cg = table:GetColumnGroup()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetBorder:boolean">public action SetBorder(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetBorder:boolean"><p>This action sets the border attribute of the web table. If set to true there is a border around the cells and if false the cells do not have a a border.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True for a border and false for no border.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetBorder:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebTable
WebTable table
table:SetBorder(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetColumnGroup:Libraries.Web.ColumnGroup">public action SetColumnGroup(Libraries.Web.ColumnGroup value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetColumnGroup:Libraries.Web.ColumnGroup"><p>This action sets the column group attribute of the web table. The column group allows a column or group of columns to be controlled and formatted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.ColumnGroup</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The column group.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetColumnGroup:Libraries.Web.ColumnGroup"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebTable
use Libraries.Web.ColumnGroup
WebTable table
ColumnGroup cg
table:SetColumnGroup(cg)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>