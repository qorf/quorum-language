<?php $classPageTitle = "GlobalAttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.GlobalAttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.GlobalAttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class GlobalAttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The GlobalAttributeAccepter class is designed as a helper to ease adding and removing attributes from particular WebTag objects. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the GlobalAttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
class WebTag is GlobalAttributeAccepter, WebGenerator
   action Generate returns text
      text result = "&lt;button "
      Attributes attributes = parent:WebTag:GetAttributes()
      attributeText = attributes:Generate()
      result = result + attributeText + "&gt;"
      result = result + me:GenerateNestedTags()
      result = result + GetDescription() + "&lt;/button&gt;"
      return result
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAccessKey">public action GetAccessKey()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAccessKey"><p>Returns the AccessKey attribute. Specifies a shortcut key for the web element.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current AccessKey attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAccessKey"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetAccessKey()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetClassAttribute">public action GetClassAttribute()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetClassAttribute"><p>Returns the ClassAttribute attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current ClassAttribute attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetClassAttribute"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetClassAttribute()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetContentEditable">public action GetContentEditable()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetContentEditable"><p>Returns the ContentEditable attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current ContentEditable attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetContentEditable"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetContentEditable()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetContextMenu">public action GetContextMenu()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetContextMenu"><p>Returns the ContextMenu attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current ContextMenu attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetContextMenu"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetContextMenu()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDraggable">public action GetDraggable()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDraggable"><p>Returns the Draggable attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Draggable attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDraggable"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetDraggable()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDropZone">public action GetDropZone()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDropZone"><p>Returns the DropZone attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current DropZone attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDropZone"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetDropZone()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHidden">public action GetHidden()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHidden"><p>Returns the Hidden attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Hidden attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHidden"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetHidden()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIdentifier">public action GetIdentifier()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIdentifier"><p>Returns the Identifier attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Identifier attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIdentifier"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetIdentifier()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLanguage">public action GetLanguage()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLanguage"><p>Returns the Language attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Language attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLanguage"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetLanguage()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSpellcheck">public action GetSpellcheck()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSpellcheck"><p>Returns the Spellcheck attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Spellcheck attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSpellcheck"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetSpellcheck()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetStyle">public action GetStyle()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetStyle"><p>Returns the Style attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Style attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetStyle"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetStyle()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTabIndex">public action GetTabIndex()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTabIndex"><p>Returns the TabIndex attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current TabIndex attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTabIndex"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetTabIndex()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTextDirection">public action GetTextDirection()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTextDirection"><p>Returns the TextDirection attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current TextDirection attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTextDirection"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetTextDirection()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTitle">public action GetTitle()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTitle"><p>Returns the Title attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current Title attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTitle"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
Attribute attribute = accept:GetTitle()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAccessKey:text">public action SetAccessKey(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAccessKey:text"><p>Sets the AccessKey attribute. Specifies a shortcut key for the web element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current AccessKey.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAccessKey:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetAccessKey("z")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetClassAttribute:text">public action SetClassAttribute(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetClassAttribute:text"><p>Sets the ClassAttribute attribute. Specifies a class name for the element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current ClassAttribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetClassAttribute:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetClassAttribute("myClass")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetContentEditable:text">public action SetContentEditable(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetContentEditable:text"><p>Sets the ContentEditable attribute. Specifies wether content in an element is editable.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current ContentEditable.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetContentEditable:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetContentEditable("true")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetContextMenu:text">public action SetContextMenu(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetContextMenu:text"><p>Sets the ContextMenu attribute. Specifies a context menu that contains the element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current ContextMenu.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetContextMenu:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetContextMenu("parentmenu")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDraggable:boolean">public action SetDraggable(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDraggable:boolean"><p>Sets the Draggable attribute. Specifies wether an element is draggable or not.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current Draggable.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDraggable:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetDraggable(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDropZone:text">public action SetDropZone(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDropZone:text"><p>Sets the DropZone attribute. This element specifies what happens when an element is dropped on a web page. The three valid values are copy, move, and link.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current DropZone.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDropZone:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetDropZone("move")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHidden:boolean">public action SetHidden(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHidden:boolean"><p>Sets the AccessKey attribute. Specifies wether an element is hidden or not.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">To true to hide element and false to make it visible.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHidden:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetHidden(false)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetIdentifier:text">public action SetIdentifier(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetIdentifier:text"><p>Sets the Identifier attribute. Specifies a unique name for the element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current Identifier.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetIdentifier:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetIdentifier("myElement")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLanguage:text">public action SetLanguage(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLanguage:text"><p>Creates and sets an attribute for language. As these codes are esoteric in the standard on the web (HTML), we recommend using the LanguageCode class to help.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLanguage:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.LanguageCode
use Libraries.Web.Link
LanguageCode code
//use any WebItem, in this case a link
Link link
link:SetLanguage(code:english)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSpellcheck:boolean">public action SetSpellcheck(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSpellcheck:boolean"><p>Sets the Spellcheck attribute. Specifies wether spellcheck is on or off.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current Spellcheck.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSpellcheck:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetSpellcheck(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetStyle:text">public action SetStyle(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetStyle:text"><p>Sets the Style attribute. Specifies inline css formatting for an element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current Style.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetStyle:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetStyle("color:orange")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTabIndex:text">public action SetTabIndex(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTabIndex:text"><p>Sets the TabIndex attribute. Specifies a tab order for elements.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current TabIndex.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTabIndex:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetTabIndex("0")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTextDirection:text">public action SetTextDirection(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTextDirection:text"><p>Sets the TextDirection attribute. Specifies a direction for the text to be written. For example, "ltr" is left-to-right.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current TextDirection.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTextDirection:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetTextDirection("rtl")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTitle:text">public action SetTitle(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTitle:text"><p>Sets the Title attribute. Specifies title information for the web element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current Title.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTitle:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.GlobalAttributeAccepter
use Libraries.Web.Attribute
GlobalAttributeAccepter accept
accept:SetTitle("MyTitle")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>