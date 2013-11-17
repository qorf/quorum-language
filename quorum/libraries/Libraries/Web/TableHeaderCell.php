<?php $classPageTitle = "TableHeaderCell"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.TableHeaderCell</h1>
<input type="hidden" id="classkey" value="Libraries.Web.TableHeaderCell" /><h2> <span class="controllable" data-componentType="class-name">Class TableHeaderCell</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The TableHeaderCell class represents HTML's (Hypertext Markup Language) th tag which is a header cell table element often contained in a TableRow. A TableHeader object represents a header and singular cell in a table. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_th.asp">The th attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      WebTable table
      //add a TableRow
      TableRow row
      TableHeader cell
      cell:SetDescription("Name")
      row:Add(cell)
      table:AddRow(row)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetColumnSpan">public action GetColumnSpan()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetColumnSpan"><p>This action gets the number of columns a cell spans in the web table. the default span is 1.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of columns a cell spans.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetColumnSpan"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
TableHeaderCell th
integer span = th:GetColumnSpan()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeaders">public action GetHeaders()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeaders"><p>This action gets the header id of a header in a table.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The header id.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeaders"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
use Libraries.Web.Attribute
TableHeaderCell th
Attribute headerID = th:GetHeaders()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRowSpan">public action GetRowSpan()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRowSpan"><p>This action gets the number of rows a cell spans in the web table. the default span is 1.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of rows a cell spans.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRowSpan"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
TableHeaderCell th
integer rowSpan = th:GetRowSpan()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetScope">public action GetScope()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetScope"><p>This action gets the scope of the header. The scope specifies if the header is for a row, column, group of rows, or group of columns.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The scope attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetScope"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
use Libraries.Web.Attribute
TableHeaderCell th
Attribute scope = th:GetHeaders()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetColumnSpan:integer">public action SetColumnSpan(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetColumnSpan:integer"><p>This action sets the column span for a cell in a table. The column span should be a positive integer. In some browsers a span of 0 means to span all columns to the end of the group.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The number of columns a cell should span.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetColumnSpan:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
TableHeaderCell th
th:SetColumnSpan(2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeaders:text">public action SetHeaders(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeaders:text"><p>This action sets the header id that links this header to a cell.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The header id.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeaders:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
//generate a header with an id of name
TableHeaderCell th
th:SetHeaders("name")
//link the cell to the header by its id
TableHeaderCell td
td:SetHeaders("name")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetRowSpan:integer">public action SetRowSpan(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetRowSpan:integer"><p>This action sets the row span for a cell in a table. The column span should be a positive integer. In some browsers a span of 0 means to span all rows to the end of the table section.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The number of rows a cell should span.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetRowSpan:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
TableHeaderCell th
th:SetRowSpan(2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetScope:text">public action SetScope(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetScope:text"><p>This action sets the scope of the header. The scope specifies if the header is for a row("row"), column("col"), group of rows("rowgroup"), or group of columns("colgroup").</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The scope of this header.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetScope:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableHeaderCell
TableHeaderCell th
th:SetScope("colgroup")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>