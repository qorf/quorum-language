<?php $classPageTitle = "WebTag"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.WebTag</h1>
<input type="hidden" id="classkey" value="Libraries.Web.WebTag" /><h2> <span class="controllable" data-componentType="class-name">Class WebTag</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The WebTag class is a generic helper class which manages Hypertext Markup Language (HTML) 5 tags. Tags are stored in a list of WebTags, thus, allowing tags to be nested within eachother. This class should not be used to generate webpages. It's subclasses should be used instead.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
WebPage page
//bold is a WebTag
Bold bold
bold:SetDescription("Quorum")
//automatically add to the body of a WebPage object.
//if you want to share bodies between web pages, you can instantiate a body
//class and pass it to various WebPage objects.
page:AddToBody(bold)
output page:Generate()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Web.WebTag">public action Add(Libraries.Web.WebTag tag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Web.WebTag"><p>This action adds a WebTag to the list of WebTags contained within the current WebTag.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="tag"><strong>Libraries.Web.WebTag</strong> <em>tag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="tag">The WebTag to be added to the list of sub-WebTags.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Web.WebTag"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableRow
use Libraries.Web.TableData
//TableRow and TableData are both WebTags
TableRow row
TableData cell
cell:SetDescription("name")
row:Add(cell)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public blueprint action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>This action generates text for a particular web page. Subclasses with a Generate method should be sure to honor nested tags or attributes if it is appropriate for a particular tag.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: This returns text in hypertext markup language (HTML)
            representing the tag.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GenerateNestedTags">public action GenerateNestedTags()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GenerateNestedTags"><p>This action generates the HTML output text of all of the nested WebTags.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The text including all webtags within this WebTag.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GenerateNestedTags"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableRow
use Libraries.Web.TableData
//TableRow and TableData are both WebTags
TableRow row
TableData cell
cell:SetDescription("name")
row:Add(cell)
output row:GenerateNestedTags()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDescription">public action GetDescription()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDescription"><p>This action gets the description text set for this WebTag.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The text contained in the WebTag.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDescription"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableRow
use Libraries.Web.TableData
//TableRow and TableData are both WebTags
TableRow row
TableData cell
cell:SetDescription("name")
row:Add(cell)
output row:GetDescription()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfNestedTags">public action GetNumberOfNestedTags()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfNestedTags"><p>This action gets the number of nested tags within the current WebTag.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of nested tags.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfNestedTags"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableRow
use Libraries.Web.TableData
//TableRow and TableData are both WebTags
TableRow row
TableData cell
cell:SetDescription("name")
row:Add(cell)
integer numTags = row:GetNumberOfNestedTags()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Web.WebTag">public action Remove(Libraries.Web.WebTag tag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Web.WebTag"><p>This action removes the first instance of a webtag found in the list of sub-WebTags.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="tag"><strong>Libraries.Web.WebTag</strong> <em>tag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="tag">The WebTag to remove from the list of WebTags.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Web.WebTag"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableRow
use Libraries.Web.TableData
//TableRow and TableData are both WebTags
TableRow row
TableData cell
cell:SetDescription("name")
row:Add(cell)
row:Remove(cell)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDescription:text">public action SetDescription(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDescription:text"><p>This action sets the description text for this WebTag.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The text contained in the WebTag.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDescription:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TableRow
use Libraries.Web.TableData
//TableRow and TableData are both WebTags
TableRow row
TableData cell
cell:SetDescription("name")
row:Add(cell)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>