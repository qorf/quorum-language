<?php $classPageTitle = "Label"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Label</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Label" /><h2> <span class="controllable" data-componentType="class-name">Class Label</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Label class represents HTML's (Hypertext Markup Language) label tag which specifies labeling text for an input tag. This label can be bound to an input tag by either setting the for attribute to the label id or by placing the input tag inside the label tag. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_label.asp">The label attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Form form
      form:SetName("myForm")
      Label label
      label:SetElementId("idOfInput")
      label:SetDescription("I'm a label")
      Input formInput
      formInput:SetIdentifier("idOfInput")
      form:Add(label)
      form:Add(formInput)
      page:AddToBody(form)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/MouseAttributeAccepter.php">Libraries.Web.MouseAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetElementId">public action GetElementId()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetElementId"><p>This action gets the element id attribute of the label. The element id allows the label to be associated with an input element.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The element id of the corresponding input element.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetElementId"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Label
use Libraries.Web.Attribute
Label label
Attribute id = label:GetElementId()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormId">public action GetFormId()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormId"><p>This action gets the form id attribute of the label. The form id allows the label to be associated with a form element.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The element id of the corresponding input element.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormId"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Label
use Libraries.Web.Attribute
Label label
Attribute id = label:GetFormId()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetElementId:text">public action SetElementId(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetElementId:text"><p>This action sets the element id attribute of the label. The element id allows the label to be associated with an input element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The element id that the label is associated with.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetElementId:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Label
use Libraries.Web.Input
Label label
label:SetElementId("male")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormId:text">public action SetFormId(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormId:text"><p>This action sets the form id attribute of the label. The form id allows the label to be associated with a form element.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The form id that the label is associated with.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormId:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
use Libraries.Web.Label
Form form
form:SetName("form1")
Label label
label:SetFormId("form1")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a> public action GetOnBlur(), public action GetOnChange(), public action GetOnContextMenu(), public action GetOnFocus(), public action GetOnFormChange(), public action GetOnFormInput(), public action GetOnInput(), public action GetOnInvalid(), public action GetOnSelect(), public action GetOnSubmit(), public action SetOnBlur(text value), public action SetOnChange(text value), public action SetOnContextMenu(text value), public action SetOnFocus(text value), public action SetOnFormChange(text value), public action SetOnFormInput(text value), public action SetOnInput(text value), public action SetOnInvalid(text value), public action SetOnSelect(text value), public action SetOnSubmit(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/MouseAttributeAccepter.php">Libraries.Web.MouseAttributeAccepter</a> public action GetOnClick(), public action GetOnDoubleClick(), public action GetOnDrag(), public action GetOnDragEnd(), public action GetOnDragEnter(), public action GetOnDragLeave(), public action GetOnDragOver(), public action GetOnDragStart(), public action GetOnDrop(), public action GetOnMouseDown(), public action GetOnMouseMove(), public action GetOnMouseOut(), public action GetOnMouseOver(), public action GetOnMouseUp(), public action GetOnMouseWheel(), public action GetOnScroll(), public action SetOnClick(text value), public action SetOnDoubleClick(text value), public action SetOnDrag(text value), public action SetOnDragEnd(text value), public action SetOnDragEnter(text value), public action SetOnDragLeave(text value), public action SetOnDragOver(text value), public action SetOnDragStart(text value), public action SetOnDrop(text value), public action SetOnMouseDown(text value), public action SetOnMouseMove(text value), public action SetOnMouseOut(text value), public action SetOnMouseOver(text value), public action SetOnMouseUp(text value), public action SetOnMouseWheel(text value), public action SetOnScroll(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>