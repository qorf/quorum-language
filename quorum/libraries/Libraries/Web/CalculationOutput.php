<?php $classPageTitle = "CalculationOutput"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.CalculationOutput</h1>
<input type="hidden" id="classkey" value="Libraries.Web.CalculationOutput" /><h2> <span class="controllable" data-componentType="class-name">Class CalculationOutput</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The CalculationOutput class represents HTML's (Hypertext Markup Language) output tag which is used display a calculation that was preformed by a script. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_output.asp">The output attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Form form
      form:SetOnInput("x.value=parseInt(a.value)+parseInt(b.value)")
      form:SetDescription("0")
      Input formInput
      formInput:SetType("range")
      formInput:SetIdentifier("a")
      formInput:SetDefaultValue("50")
      formInput:SetDescription("100 +")
      Input formInput2
      formInput2:SetType("number")
      formInput2:SetIdentifier("b")
      formInput2:SetDefaultValue("50")
      formInput2:SetDescription("= ")
      CalculationOutput calcOutput
      calcOutput:SetName("x")
      calcOutput:SetElementIds("a b")
      
      form:Add(formInput)
      form:Add(formInput2)
      form:Add(calcOutput)
      page:AddToBody(form)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetElementIds">public action GetElementIds()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetElementIds"><p>This action gets the element ids of the CalculationOutput.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetElementIds"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.CalculationOutput
use Libraries.Web.Attribute
CalculationOutput calcOutput
Attribute elementIdsValue = calcOutput:GetElementIds()
if elementIdsValue not= undefined
   text elementIds = elementIdsValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name of the CalculationOutput.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.CalculationOutput
use Libraries.Web.Attribute
CalculationOutput calcOutput
Attribute nameValue = calcOutput:GetName()
if nameValue not= undefined
   text name = nameValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetElementIds:text">public action SetElementIds(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetElementIds:text"><p>This action sets the element ids. The element ids are used to specify the relationship between the result of the calculation, and the elements used in the calculation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The element ids of the for the 
        CalculationOutput to use.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetElementIds:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.CalculationOutput
CalculationOutput calcOutput
calcOutput:SetElementIds("a b")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name of the CalculationOutput so that it can be referencd other tags.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name value of the CalculationOutput.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.CalculationOutput
CalculationOutput calcOutput
calcOutput:SetName("x")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>