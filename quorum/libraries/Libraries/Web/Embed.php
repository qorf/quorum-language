<?php $classPageTitle = "Embed"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Embed</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Embed" /><h2> <span class="controllable" data-componentType="class-name">Class Embed</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Embed class represents HTML's (Hypertext Markup Language) embed tag which is used to define a container for an application or interactive content. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_embed.asp">The embed attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Embed embed
      embed:SetAddress("http://images.free-extras.com/pics/s/smile-1613.jpg")
      page:AddToBody(embed)
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

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the address attribute of the . The address is the location of the linked webpage or document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The address of the link.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
use Libraries.Web.Attribute
Embed embed
Attribute address = embed:GetAddress()
if address not= undefined
   text href = address:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeight">public action GetHeight()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeight"><p>This action gets the height attribute of the embeded content.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The height in pixels of the embeded content.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeight"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
use Libraries.Web.Attribute
Embed embed
Attribute heightInPixels = embed:GetAddress()
if heightInPixels not= undefined
   text height = heightInPixels:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the MIME type attribute of the embeded content. The type attribute maps to a MIME type of the content.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The MIME type attribute of the embeded content.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
use Libraries.Web.Attribute
Embed embed
Attribute type = embed:GetType()
if type not= undefined
   text mimeType = type:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWidth">public action GetWidth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWidth"><p>This action gets the width attribute of the embeded content.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The width in pixels of the embeded content.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWidth"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
use Libraries.Web.Attribute
Embed embed
Attribute widthInPixels = embed:GetAddress()
if widthInPixels not= undefined
   text width = widthInPixels:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the address attribute of the embeded content. The address is the location of the linked webpage or document.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The address of a webpage or document.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
Embed embed
embed:SetAddress("http://www.google.com")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeight:text">public action SetHeight(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeight:text"><p>This action sets the height attribute of the embeded content.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The height in pixel to set to the embeded content.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeight:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
Embed embed
embed:SetHeight("150")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the Type attribute of the embeded content. The type attribute maps to a MIME type of the content.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The MIME type for the content.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
Embed embed
embed:SetType("text/html")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWidth:text">public action SetWidth(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWidth:text"><p>This action sets the width attribute of the embeded content.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The width in pixel to set to the embeded content.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWidth:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Embed
Embed embed
embed:SetWidth("150")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>