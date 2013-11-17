<?php $classPageTitle = "WebPage"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.WebPage</h1>
<input type="hidden" id="classkey" value="Libraries.Web.WebPage" /><h2> <span class="controllable" data-componentType="class-name">Class WebPage</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The WebPage class represents HTML's (Hypertext Markup Language) option tag which defines the document as an html document. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_html.asp">The html attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Body body
      page:SetBody(body)
      page:AddToBody(body)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebGenerator.php">Libraries.Web.WebGenerator</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToBody:Libraries.Web.WebTag">public action AddToBody(Libraries.Web.WebTag tag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToBody:Libraries.Web.WebTag"><p>This action add to the body of the webpage directly and in order.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="tag"><strong>Libraries.Web.WebTag</strong> <em>tag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="tag">The web tag object that should be added to the body of this webpage.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToBody:Libraries.Web.WebTag"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Link
WebPage page
Link link
page:AddToBody(link)</pre>

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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetBody">public action GetBody()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetBody"><p>This action gets the body of the webpage.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Body</strong>: The body of the webpage.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetBody"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Body
WebPage page
Body result = page:GetBody()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDocumentType">public action GetDocumentType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDocumentType"><p>This action gets the type of document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.DocumentType</strong>: The type of document.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDocumentType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.DocumentType
WebPage page
DocumentType result = page:GetDocumentType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetManifest">public action GetManifest()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetManifest"><p>This action gets the address of the documents cache.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The document cache address.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetManifest"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Attribute
WebPage page
text result = page:GetManifest()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWebPageHeader">public action GetWebPageHeader()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWebPageHeader"><p>This action gets the header of this webpage.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.WebPageHeader</strong>: The header.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWebPageHeader"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.WebPageHeader
WebPage page
WebPageHeader result = page:GetWebPageHeader()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWebPageTitle">public action GetWebPageTitle()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWebPageTitle"><p>This action gets the webpages title.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The title.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWebPageTitle"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Attribute
WebPage page
text result = page:GetWebPageTitle()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetBody:Libraries.Web.Body">public action SetBody(Libraries.Web.Body body)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetBody:Libraries.Web.Body"><p>This action sets the body of the webpage.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="body"><strong>Libraries.Web.Body</strong> <em>body</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="body">The body.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetBody:Libraries.Web.Body"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Body
WebPage page
Body body
page:SetBody(body)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDocumentType:Libraries.Web.DocumentType">public action SetDocumentType(Libraries.Web.DocumentType doc)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDocumentType:Libraries.Web.DocumentType"><p>This action sets the DocumentType of the webpage.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="doc"><strong>Libraries.Web.DocumentType</strong> <em>doc</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="doc">The document type.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDocumentType:Libraries.Web.DocumentType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.DocumentType
WebPage page
DocumentType document
page:SetDocumentType(document)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetManifest:text">public action SetManifest(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetManifest:text"><p>This action sets the address of the documents cache.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The document cache address.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetManifest:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Attribute
WebPage page
page:SetManifest("test.appcache")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWebPageHeader:Libraries.Web.WebPageHeader">public action SetWebPageHeader(Libraries.Web.WebPageHeader header)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWebPageHeader:Libraries.Web.WebPageHeader"><p>This action sets the address of the documents cache.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="header"><strong>Libraries.Web.WebPageHeader</strong> <em>header</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="header">The header.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWebPageHeader:Libraries.Web.WebPageHeader"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.WebPageHeader
WebPage page
WebPageHeader header
page:SetWebPageHeader(header)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWebPageTitle:text">public action SetWebPageTitle(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWebPageTitle:text"><p>This action sets the title of the webpage.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The title.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWebPageTitle:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
use Libraries.Web.Attribute
WebPage page
page:SetWebPageTitle("My First Webpage")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>