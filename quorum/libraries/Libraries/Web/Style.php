<?php $classPageTitle = "Style"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Style</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Style" /><h2> <span class="controllable" data-componentType="class-name">Class Style</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Style class represents HTML's (Hypertext Markup Language) style tag which is used to define style or css information to the document. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_style.asp">The style attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      WebPage page
      Style style
      style:SetType("text/css")
      style:SetDescription("h1 {color:blue;}")
      page:SetWebPageHeader(style)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMedia">public action GetMedia()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMedia"><p>This action gets media that the style is optimized for. More information can be found at: <a href="http://www.w3schools.com/tags/att_style_media.asp>The style media attribute</a></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The media type for the style.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMedia"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Style
use Libraries.Web.Attribute
Style style
style:SetMedia("braille")
Attribute media = style:GetMedia()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetScoped">public action GetScoped()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetScoped"><p>This action gets the style to apply to only the parent and child of the element the style is being applied to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: If true the style will aply to the parent and child of the element.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetScoped"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Style
use Libraries.Web.Attribute
Style style
style:SetScoped(true)
boolean scoped = style:GetScoped()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the type of information in the style object (description).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The type of information in the stlye object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Style
use Libraries.Web.Attribute
Style style
style:SetType("text/css")
Attribute type = style:GetType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMedia:text">public action SetMedia(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMedia:text"><p>This action sets media that the style is optimized for. More information can be found at: <a href="http://www.w3schools.com/tags/att_style_media.asp>The style media attribute</a></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Sets the media type for the style.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMedia:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Style
use Libraries.Web.Attribute
Style style
style:SetMedia("braille")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetScoped:boolean">public action SetScoped(boolean isScoped)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetScoped:boolean"><p>This action sets the style to apply to only the parent and child of the element the style is being applied to.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="isScoped"><strong>boolean</strong> <em>isScoped</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="isScoped">If true the style will aply to the parent and child of the element.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetScoped:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Style
use Libraries.Web.Attribute
Style style
style:SetScoped(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the type of information in the style object (description).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Currently the only applicable type is "text/css".</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Style
use Libraries.Web.Attribute
Style style
style:SetType("text/css")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>