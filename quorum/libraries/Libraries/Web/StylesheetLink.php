<?php $classPageTitle = "StylesheetLink"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.StylesheetLink</h1>
<input type="hidden" id="classkey" value="Libraries.Web.StylesheetLink" /><h2> <span class="controllable" data-componentType="class-name">Class StylesheetLink</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Option class represents HTML's (Hypertext Markup Language) link tag which is a link established between the webpages and its css(stylesheet). You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_link.asp">The link attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      StylesheetLink link
      link:SetAddress("style.css")
      page:AddToBody(link)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAddress">public action GetAddress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the address of the stylesheet.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The stylesheet address.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink link
Attribute result = link:GetAddress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLanguageCode">public action GetLanguageCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLanguageCode"><p>This action gets the language code of the webpage.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The language code.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLanguageCode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink link
Attribute result = link:GetLanguageCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLinkedResourceSize">public action GetLinkedResourceSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLinkedResourceSize"><p>This action gets the size of a linked resource (eg. icons).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The resource size.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLinkedResourceSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink link
Attribute result = link:GetLinkedResourceSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMedia">public action GetMedia()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMedia"><p>This action gets displaying media type.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The displaying media type.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMedia"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink link
Attribute result = link:GetMedia()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRelationship">public action GetRelationship()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRelationship"><p>This action gets the specified relationship between the stylesheet and the document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The relationship.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRelationship"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink link
Attribute result = link:GetRelationship()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the MIME type.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The MIME type.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink link
Attribute result = link:GetType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the location of the linked stylesheet.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The location of the linked stylesheet.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink s
s:SetAddress("style.css")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLanguageCode:text">public action SetLanguageCode(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLanguageCode:text"><p>This action sets the language code of the linked stylesheet.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The language code.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLanguageCode:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink s
s:SetLanguageCode("en")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLinkedResourceSize:text">public action SetLinkedResourceSize(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLinkedResourceSize:text"><p>This action sets the size of the linked resources.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The size.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLinkedResourceSize:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink s
s:SetLinkedResourceSize("5x5")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMedia:text">public action SetMedia(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMedia:text"><p>This action sets the media of the linked stylesheet.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The media of the linked stylesheet.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMedia:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink s
s:SetMedia("print")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetRelationship:text">public action SetRelationship(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetRelationship:text"><p>This action sets the relationship between that of the document and that of the linked stylesheet.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The relationship.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetRelationship:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink s
s:SetRelationship("stylesheet")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the MIME type of the linked stylesheet.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The MIME type of the linked stylesheet.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.StylesheetLink
use Libraries.Web.Attribute
StylesheetLink s
s:SetType("text/css")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>