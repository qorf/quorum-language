<?php $classPageTitle = "InlineFrame"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.InlineFrame</h1>
<input type="hidden" id="classkey" value="Libraries.Web.InlineFrame" /><h2> <span class="controllable" data-componentType="class-name">Class InlineFrame</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The InlineFrame class represents HTML's (Hypertext Markup Language) iframe tag which is used to add an another page into the current webpage. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_iframe.asp">The iframe attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      InlineFrame inlineFrame
      inlineFrame:SetAddress("http://www.w3schools.com")
      inlineFrame:SetHeight("550")
      inlineFrame:SetWidth("550")
      inlineFrame:SetSeamless(true)
      
      page:AddToBody(inlineFrame)
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

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the address attribute of the inline frame. The address is the location of the linked webpage or document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The address of the inline frame.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
use Libraries.Web.Attribute
InlineFrame inlineFrame
Attribute address = inlineFrame:GetAddress()
if address not= undefined
   text href = address:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeight">public action GetHeight()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeight"><p>This action gets the height attribute of the inline frame.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The height in pixels of the inline frame.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeight"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
use Libraries.Web.Attribute
InlineFrame inlineFrame
Attribute heightInPixels = inlineFrame:GetHeight()
if heightInPixels not= undefined
   text height = heightInPixels:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name attribute of the inline frame.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name of the inline frame.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
use Libraries.Web.Attribute
InlineFrame inlineFrame
Attribute nameOfFrame = inlineFrame:GetName()
if nameOfFrame not= undefined
   text name = nameOfFrame:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSandbox">public action GetSandbox()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSandbox"><p>This action gets the sandbox attribute of the inline frame.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The sandbox restriction on the inline frame.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSandbox"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
use Libraries.Web.Attribute
InlineFrame inlineFrame
Attribute sandboxFrame = inlineFrame:GetSandbox()
if sandboxFrame not= undefined
   text sandbox = sandboxFrame:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSeamless">public action GetSeamless()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSeamless"><p>This action returns true if the inline frame has been set to be seamless meaning it will have no border between it and the rest of the page.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if it is seamless</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSeamless"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
InlineFrame inlineFrame
boolean isSeemless = inlineFrame:GetSeamless()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWidth">public action GetWidth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWidth"><p>This action gets the width attribute of the inline frame.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The width in pixels of the inline frame.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWidth"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
use Libraries.Web.Attribute
InlineFrame inlineFrame
Attribute widthInPixels = inlineFrame:GetWidth()
if widthInPixels not= undefined
   text width = widthInPixels:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the address attribute of the inline frame. The address is the location of the linked webpage or document.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The address of a webpage or document.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
InlineFrame inlineFrame
inlineFrame:SetAddress("http://www.google.com")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeight:text">public action SetHeight(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeight:text"><p>This action sets the height attribute of the inline frame.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The height in pixel to set to the inline frame.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeight:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
InlineFrame inlineFrame
inlineFrame:SetHeight("150")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name attribute of the inline frame.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name to set to the inline frame.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
InlineFrame inlineFrame
inlineFrame:SetName("someFrame")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSandbox:text">public action SetSandbox(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSandbox:text"><p>This action sets the sanbox attribute of the inline frame. This is used to add extra restrictions to the inlined frame.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The type of restriction to set to the inline frame.
                   These can be - "" (Applies all restricions)
                                - allow-same-origin (Allows the iframe content to be treated as being from the same origin as the containing document)
                                - allow-top-navigation (Allows the iframe content to navigate (load) content from the containing document)
                                - allow-forms (Allows form submission)
                                - allow-scipts (Allows script execution)</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSandbox:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
InlineFrame inlineFrame
inlineFrame:SetSandbox("allow-scripts")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSeamless:boolean">public action SetSeamless(boolean isSeamless)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSeamless:boolean"><p>This action sets the seamless attribute of the inline frame. Set this to be true if you want the inline frame to have no borders around it.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="isSeamless"><strong>boolean</strong> <em>isSeamless</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="isSeamless"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSeamless:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
InlineFrame inlineFrame
inlineFrame:SetSeamless(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWidth:text">public action SetWidth(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWidth:text"><p>This action sets the width attribute of the inline frame.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The width in pixel to set to the inline frame.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWidth:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.InlineFrame
InlineFrame inlineFrame
inlineFrame:SetWidth("150")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>