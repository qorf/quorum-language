<?php $classPageTitle = "BiDirectionalOverride"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.BiDirectionalOverride</h1>
<input type="hidden" id="classkey" value="Libraries.Web.BiDirectionalOverride" /><h2> <span class="controllable" data-componentType="class-name">Class BiDirectionalOverride</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The BiDirectionalOverride class represents HTML's (Hypertext Markup Language) bdo tag which is used to set the direction that should be displayed  You can find more information about this tag at: .<a href="http://www.w3schools.com/tags/tag_bdo.asp">The bdo attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      Paragraph p
      Paragraph p2
      BiDirectionalOverride bdo
      bdo:SetDirection(bdo:leftToRight)
      bdo:SetDescription("This will have the BiDirectionalOverride going left to right on it")
      p:Add(bdo)
      BiDirectionalOverride bdo2
      bdo2:SetDirection(bdo:rightToLeft)
      bdo2:SetDescription("This will have the BiDirectionalOverride going right to left on it")
      p2:Add(bdo2)
      
      page:AddToBody(p)
      page:AddToBody(p2)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>leftToRight</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>rightToLeft</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDirection">public action GetDirection()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDirection"><p>This action gets the direction attribute of the BiDirectionalOverride.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The coordinates of specified area</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDirection"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.BiDirectionalOverride
use Libraries.Web.Attribute
BiDirectionalOverride bdo
Attribute dir = bdo:GetDirection()
if dir not= undefined
   text direction = dir:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDirection:text">public action SetDirection(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDirection:text"><p>This action sets the diection attribute of the area. The diection is used to set on what side of the page the text should be set to. Use the constants leftToRight and rightToLeft when setting.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDirection:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.BiDirectionalOverride
BiDirectionalOverride bdo
bdo:SetDirection(bdo:leftToRight)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>