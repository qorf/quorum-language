<?php $classPageTitle = "MetaData"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.MetaData</h1>
<input type="hidden" id="classkey" value="Libraries.Web.MetaData" /><h2> <span class="controllable" data-componentType="class-name">Class MetaData</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The MetaData class represents HTML's (Hypertext Markup Language) meta tag which is used to store data about the webpage that is not displayed. An example might be the author name. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_meta.asp">The meta attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      WebPageHeader header
      MetaData meta
      meta:SetContent("Andreas Stefik")
      header:Add(meta)
      page:AddToBody(header)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>encodingLatin</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>encodingUnicode</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCharacterSet">public action GetCharacterSet()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCharacterSet"><p>This action gets the current HTML encoding for the document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The HTML encoding in the meta data.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCharacterSet"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
use Libraries.Web.Attribute
MetaData md
Attribute charSet = md:GetCharacterSet()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetContent">public action GetContent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetContent"><p>This action gets the HTML http-equiv or name stored in the meta data.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name stored in the meta data.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetContent"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
use Libraries.Web.Attribute
MetaData md
Attribute charSet = md:GetContent()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeaderInformation">public action GetHeaderInformation()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeaderInformation"><p>This action gets the current HTML http-equiv for the document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The HTML http-equiv in the meta data.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeaderInformation"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
use Libraries.Web.Attribute
MetaData md
Attribute charSet = md:GetHeaderInformation()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the current name for the document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name in the meta data.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
use Libraries.Web.Attribute
MetaData md
Attribute charSet = md:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCharacterSet:text">public action SetCharacterSet(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCharacterSet:text"><p>This action sets the current HTML encoding for the document. Some commont values are "UTF-8"(Unicode) and "ISO-8859-1"(latin alphabet).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The HTML encoding.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCharacterSet:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
MetaData md
md:SetCharacterSet("UTF-8")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetContent:text">public action SetContent(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetContent:text"><p>This action sets the content in the meta data. This data is labeled or named by setting the name in meta data.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The content in the meta data tag.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetContent:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
MetaData md
md:SetContent("Andreas Stefik")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeaderInformation:text">public action SetHeaderInformation(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeaderInformation:text"><p>This action sets the contents type value. Valid values include: content-type, default-style, and refresh</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The content information type.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeaderInformation:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
MetaData md
md:SetHeaderInformation("refresh")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name of the content. Possible values include: application-name, author, description, generator, and keywords.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MetaData
MetaData md
md:SetName("author")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>