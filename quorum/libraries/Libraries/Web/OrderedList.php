<?php $classPageTitle = "OrderedList"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.OrderedList</h1>
<input type="hidden" id="classkey" value="Libraries.Web.OrderedList" /><h2> <span class="controllable" data-componentType="class-name">Class OrderedList</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The OrderedList class represents HTML's (Hypertext Markup Language) ol tag which is used to create an ordered list that can be ordered numerically or alphabetically. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_ol.asp">The ol attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      WebPage page
      OrderedList ol
      ListItem li
      li:SetDescription("A")
      
      ol:Add(li)
      page:AddToBody(ol)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>alphabeticalLowerCaseType</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>alphabeticalUpperCaseType</em></li>
<li class = "package_standard" ><strong>text</strong> <em>numericalType</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>romanNumberLowerCaseType</em></li>
<li class = "package_standard" ><strong>text</strong> <em>romanNumberUpperCaseType</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetReversed">public action GetReversed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetReversed"><p>This action gets wether the ordered list is in descending order or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True for descending order false for assending order.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetReversed"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.OrderedList
OrderedList ol
ol:SetReversed(true)
boolean isDescending = ol:GetReversed()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetStartValue">public action GetStartValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetStartValue"><p>This action gets the start value of the ordered list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The start value of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetStartValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.OrderedList
OrderedList ol
ol:SetStartValue("3")
Attribute startVal = ol:GetStartValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the type of marker on the ordered list(e.g. 1, A, a, I, i)</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The marker of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.OrderedList
OrderedList ol
ol:SetType("A")
Attribute type = ol:GetType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetReversed:boolean">public action SetReversed(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetReversed:boolean"><p>This action sets the ordered list to descending order when true.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True for descending order false for assending order.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetReversed:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.OrderedList
OrderedList ol
ol:SetReversed(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetStartValue:text">public action SetStartValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetStartValue:text"><p>This action sets the start value of the ordered list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The start value of the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetStartValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.OrderedList
OrderedList ol
ol:SetStartValue("3")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the type of marker on the ordered list(e.g. 1, A, a, I, i)</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The marker of the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
use Libraries.Web.OrderedList
OrderedList ol
ol:SetType("A")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>