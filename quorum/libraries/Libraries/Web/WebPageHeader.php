<?php $classPageTitle = "WebPageHeader"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.WebPageHeader</h1>
<input type="hidden" id="classkey" value="Libraries.Web.WebPageHeader" /><h2> <span class="controllable" data-componentType="class-name">Class WebPageHeader</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The WebPageHeader class represents HTML's (Hypertext Markup Language) head tag which is a container class for any WebTags that belong in the web pages header. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_head.asp">The head attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      //add a TextArea
      WebPageHeader header
      page:SetWebPageHeader(header)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebGenerator.php">Libraries.Web.WebGenerator</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Web.Base">public action Add(Libraries.Web.Base value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Web.Base"><p>This action adds Base to the header. There can only be one base on a page.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.Base</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The header base data.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Web.Base"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Base
WebPageHeader header
Base base
header:Add(base)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Web.MetaData">public action Add(Libraries.Web.MetaData value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Web.MetaData"><p>This action adds MetaData to the header.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.MetaData</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The header meta data.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Web.MetaData"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.MetaData
WebPageHeader header
MetaData md
header:Add(md)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Web.Script">public action Add(Libraries.Web.Script value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Web.Script"><p>This action adds a script to the header of a webpage.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.Script</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The script to add to the header.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Web.Script"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Script
WebPageHeader header
Script s
header:Add(s)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>Generate method converts web page information into raw text that can be sent to a web server or otherwise printed out.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: A text representation of the item being generated.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Generate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
//by default, this would output a blank web page
WebPage page
output page:Generate()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfMetaDataItems">public action GetNumberOfMetaDataItems()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfMetaDataItems"><p>This action gets the number of meta data items in the header.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of meta data items in the header.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfMetaDataItems"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
WebPageHeader header
integer val = header:GetNumberOfMetaDataItems()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfScriptItems">public action GetNumberOfScriptItems()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfScriptItems"><p>This action gets the number of script items in the header.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of script items in the header.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfScriptItems"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Script
WebPageHeader header
Script s
header:Add(s)
integer val = header:GetNumberOfScriptItems()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTitleHeader">public action GetTitleHeader()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTitleHeader"><p>This action gets the headers title.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Title</strong>: The headers title.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTitleHeader"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Title
WebPageHeader header
Title title
header:SetTitleHeader(title)
Title t = header:GetTitleHeader()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTitleHeaderValue">public action GetTitleHeaderValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTitleHeaderValue"><p>This action gets the title text of the header.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The title.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTitleHeaderValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
WebPageHeader header
output header:GetTitleHeaderValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Web.Base">public action Remove(Libraries.Web.Base value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Web.Base"><p>This action removes the base data from the header.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.Base</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The meta data element.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Web.Base"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Base
WebPageHeader header
Base item
header:Remove(item)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Web.MetaData">public action Remove(Libraries.Web.MetaData value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Web.MetaData"><p>This action removes the first instance of the meta data value found in the meta list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.MetaData</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The meta data element.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Web.MetaData"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.MetaData
WebPageHeader header
MetaData item
header:Remove(item)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Web.Script">public action Remove(Libraries.Web.Script value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Web.Script"><p>This action removes the first instance of the script from the headers list of scripts.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Libraries.Web.Script</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The script to be removed from the header.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Web.Script"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Script
WebPageHeader header
Script s
header:Add(s)
header:Remove(s)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTitleHeader:Libraries.Web.Title">public action SetTitleHeader(Libraries.Web.Title title)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTitleHeader:Libraries.Web.Title"><p>This action sets the title for the header. This field is required.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="title"><strong>Libraries.Web.Title</strong> <em>title</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="title">The title.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTitleHeader:Libraries.Web.Title"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
use Libraries.Web.Title
WebPageHeader header
Title title
header:SetTitleHeader(title)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTitleHeaderValue:text">public action SetTitleHeaderValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTitleHeaderValue:text"><p>This action sets the text of the title in the header.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The title text.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTitleHeaderValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPageHeader
WebPageHeader header
header:SetTitleHeaderValue("My Title")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>