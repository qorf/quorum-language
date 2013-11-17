<?php $classPageTitle = "Button"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Button</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Button" /><h2> <span class="controllable" data-componentType="class-name">Class Button</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Button class represents HTML's (Hypertext Markup Language) button tag which is a button control. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_button.asp">The button attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      Button myButton
      
      myButton:SetDescription("Quorum")
      myButton:SetOnClick("alert('Hello, World!')"
      myButton:SetAutofocus(true)
      page:AddToBody(myButton)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutofocus">public action GetAutofocus()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutofocus"><p>This action gets the autofocus Attribute. The autofocus attribute tells the Button whether or not it will get focus when a webPage is loaded.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if autofocus is enabled or false if it is disabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutofocus"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
boolean isAutofocusOn = myButton:GetAutofocus()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action gets the disabled attribute of the button. If false is returned the button is enabled and if true is returned it is disabled.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the button is disabled and false if it not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
boolean disabled = myButton:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormAction">public action GetFormAction()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormAction"><p>This action gets the formAction attribute of the button. The specific address of the formAction is stored in the Value of the attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formAction attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormAction"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute formAction = myButton:GetFormAction()
text formActionAddress = ""
if formAction not= undefined
   formActionAddress = formAction:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormEncodedType">public action GetFormEncodedType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormEncodedType"><p>This action gets the formEncodedType attribute of the button. The value of the formEncodedType is stored in the value of the attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormEncodedType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute formEncodedType = myButton:GetFormEncodedType()
text encodedType = ""
if formEncodedType not= undefined
   encodedType = formEncodedType:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormMethod">public action GetFormMethod()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormMethod"><p>This action gets the formMethod attribute of the button. The value of the formMethod attribute is stored in the value of the attribute and is either "get" or "post".</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formMethod attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormMethod"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute formMethod = myButton:GetFormMethod()
text getOrPost = ""
if formMethod not= undefined
   getOrPost = formMethod:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormName">public action GetFormName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormName"><p>This action gets the formName attribute of the button. The name of the formName is stored as the value in the attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formName attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute formName = myButton:GetFormName()
text valueFormName = ""
if formName not= undefined
   valueFormName = formName:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormNotValidated">public action GetFormNotValidated()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormNotValidated"><p>This action gets the formNotValidated attribute of the button. If true the form will not be validated on submission. If false the form will be validated on submission.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The boolean value of the formNotValidated attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormNotValidated"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
boolean noValidate = myButton:GetFormNotValidated()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormTarget">public action GetFormTarget()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormTarget"><p>This action gets the formTarget attribute of the button. This determines the target window of the owning form's action. Acceptable values: "_blank" --> new tab "_parent" --> parent frame "_self" --> same frame "_top" --> same window existing frame name --> specified frame</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formTarget attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormTarget"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute formTarget = myButton:GetFormTarget()
text target= ""
if formTarget not= undefined
   target = formTarget:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name attribute of the button. the value of the name is stored in the value of the name attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute buttonName = myButton:GetName()
text name = ""
if buttonName not= undefined
   name = buttonName:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSubmitValue">public action GetSubmitValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSubmitValue"><p>This action gets the submitValue attribute of the button. The value of submitValue is stored in the value of the submitValue attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The submitValue attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSubmitValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute value = myButton:GetSubmitValue()
text sValue = ""
if value not= undefined
   sValue = value:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the type attribute of the button. The value of the type is stored in the value of the type attribute. Acceptable values: "button" --> a clickable button "reset" --> clears form data "submit" --> submits form data</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The type attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
use Libraries.Web.Attribute
Button myButton
Attribute buttonType = myButton:GetType()
text type = ""
if buttonType not= undefined
   type = buttonType:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutofocus:boolean">public action SetAutofocus(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutofocus:boolean"><p>This action sets the autofocus Attribute. The autofocus attribute tells the Button whether or not it will get focus when a webPage is loaded.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The autofocus is enable with a value of true or is not enabled with a value of false.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutofocus:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetAutofocus(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action sets the disabled attribute of the button. The value of true will disable the button and false will enable the button.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True if the button is disabled and false if it not.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormAction:text">public action SetFormAction(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormAction:text"><p>This action sets the formAction attribute of the button. The value of the formAction attribute should map to a defined address.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Pre-defined form action address.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormAction:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetFormAction("myFormAction.quorum")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormEncodedType:text">public action SetFormEncodedType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormEncodedType:text"><p>This action sets the formEncodedType attribute of the button. The value of the formEncodedType attribute should should be a known encoding. Acceptable types are: multipart/form-data --> No characters are encoded. text/plain --> Spaces are converted to + symbols, but no characters are encoded. application/x-www-form-urlencoded --> Default. All characters will be encoded.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Pre-defined form action address.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormEncodedType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetFormEncodedType("multipart/form-data")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormMethod:text">public action SetFormMethod(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormMethod:text"><p>This action sets the formMethod attribute. The formMethod can be either get or post.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The containing form's method.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormMethod:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetFormMethod("post")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormName:text">public action SetFormName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormName:text"><p>This action sets the form attribute of the button. The value of formName attribute should map to a defined form name.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Name of the form.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetFormName("myForm")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormNotValidated:boolean">public action SetFormNotValidated(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormNotValidated:boolean"><p>This action sets the formNotValidated attribute of the button. If true the form will not be validated on submission. If false the form will be validated on submission.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formNotValidated attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormNotValidated:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetFormNotValidated(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormTarget:text">public action SetFormTarget(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormTarget:text"><p>This action sets the formTarget attribute of the button. This determines the target window of the owning form's action. Acceptable values: "_blank" --> new tab "_parent" --> parent frame "_self" --> same frame "_top" --> same window existing frame name --> specified frame</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formTarget attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormTarget:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetFormTarget("_blank")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name attribute of the button.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetName("SubmitButton")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSubmitValue:text">public action SetSubmitValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSubmitValue:text"><p>This action sets the submitValue attribute of the button.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The submitValue attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSubmitValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetSubmitValue("default_submit_value")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the type attribute of the button. Acceptable values: "button" --> a clickable button "reset" --> clears form data "submit" --> submits form data</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The type attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Button
Button myButton
myButton:SetType("reset")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a> public action GetOnBlur(), public action GetOnChange(), public action GetOnContextMenu(), public action GetOnFocus(), public action GetOnFormChange(), public action GetOnFormInput(), public action GetOnInput(), public action GetOnInvalid(), public action GetOnSelect(), public action GetOnSubmit(), public action SetOnBlur(text value), public action SetOnChange(text value), public action SetOnContextMenu(text value), public action SetOnFocus(text value), public action SetOnFormChange(text value), public action SetOnFormInput(text value), public action SetOnInput(text value), public action SetOnInvalid(text value), public action SetOnSelect(text value), public action SetOnSubmit(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/MouseAttributeAccepter.php">Libraries.Web.MouseAttributeAccepter</a> public action GetOnClick(), public action GetOnDoubleClick(), public action GetOnDrag(), public action GetOnDragEnd(), public action GetOnDragEnter(), public action GetOnDragLeave(), public action GetOnDragOver(), public action GetOnDragStart(), public action GetOnDrop(), public action GetOnMouseDown(), public action GetOnMouseMove(), public action GetOnMouseOut(), public action GetOnMouseOver(), public action GetOnMouseUp(), public action GetOnMouseWheel(), public action GetOnScroll(), public action SetOnClick(text value), public action SetOnDoubleClick(text value), public action SetOnDrag(text value), public action SetOnDragEnd(text value), public action SetOnDragEnter(text value), public action SetOnDragLeave(text value), public action SetOnDragOver(text value), public action SetOnDragStart(text value), public action SetOnDrop(text value), public action SetOnMouseDown(text value), public action SetOnMouseMove(text value), public action SetOnMouseOut(text value), public action SetOnMouseOver(text value), public action SetOnMouseUp(text value), public action SetOnMouseWheel(text value), public action SetOnScroll(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>