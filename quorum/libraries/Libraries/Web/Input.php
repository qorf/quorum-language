<?php $classPageTitle = "Input"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Input</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Input" /><h2> <span class="controllable" data-componentType="class-name">Class Input</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Input class represents HTML's (Hypertext Markup Language) input tag which defines an input control within a Form. The type of control is defined by the type attribute. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_input.asp">The input attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      //make a web page
      WebPage page
      //make a Form
      Form myForm
      //make a input
      Input myInput
      myInput:SetType("tel")
      myForm:Add(myInput)
      page:AddToBody(myForm)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/KeyboardAttributeAccepter.php">Libraries.Web.KeyboardAttributeAccepter</a>, <a href="../../Libraries/Web/MouseAttributeAccepter.php">Libraries.Web.MouseAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAccept">public action GetAccept()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAccept"><p>This action gets the accept attribute. The value can be accessed by calling the GetValue() action on the returned accept attribute. It specifies the file types that will be accepted by the server. Multiple values are to be seperated by a coma. Accptable values: audio/* video/* image/* MIME_type</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The accept attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAccept"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute accept = myInput:GetAccept()
text acceptValue = ""
if accept not= undefined
   acceptValue = accept:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAlternate">public action GetAlternate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAlternate"><p>This action gets the alternate attribute. The value can be accessed by calling the GetValue() action on the returned alternate attribute. The alternate specifies alternate text for images.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The alternate attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAlternate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute alternate = myInput:GetAlternate()
text alternateValue = ""
if alternate not= undefined
   alternateValue = alternate:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutocomplete">public action GetAutocomplete()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutocomplete"><p>This action gets the value of the autocomplete attribute. Autocomplete is true by default. When true the browser will attempt to autocomplete the input field.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The autocomplete attribute value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutocomplete"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
boolean inputAutocomplete = myInput:GetAutocomplete()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutofocus">public action GetAutofocus()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutofocus"><p>This action gets the value of the autofocus attribute. Autofocus is false by default. When true the browser place the focus on the input value when the page loads.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The autofocus attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutofocus"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputAutofocus = myInput:GetAutofocus()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChecked">public action GetChecked()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChecked"><p>This action gets the value of the checked attribute. The checked attribute is only valid when the input control is of type checkbox or radio. The default value is false. When true the radio button or checkbox will be checked after the page loads.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The checked attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChecked"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputChecked = myInput:GetChecked()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDefaultValue">public action GetDefaultValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDefaultValue"><p>This action gets the defaultValue attribute. The value can be accessed by calling the GetValue() action on the returned defaultValue attribute. The defaultValue attribute specifies the default value of the input control.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The defaultValue attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDefaultValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute defaultValue = myInput:GetDefaultValue()
text defaultValueValue = ""
if defaultValue not= undefined
   defaultValueValue = defaultValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action gets the value of the disabled attribute. The default value is false. When true the input control will not be clickable. This is ignored when the input control is of type hidden.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The disabled attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputDisabled = myInput:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormAction">public action GetFormAction()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormAction"><p>This action gets the formAction attribute. The value can be accessed by calling the GetValue() action on the returned formAction attribute. The formAction attribute specifies the address of the file that will process the input control when the form is submitted.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formAction attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormAction"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute formAction = myInput:GetFormAction()
text formActionValue = ""
if formAction not= undefined
   formActionValue = formAction:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormEncodedType">public action GetFormEncodedType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormEncodedType"><p>This action gets the formEncodedType attribute. The value can be accessed by calling the GetValue() action on the returned formEncodedType attribute. The formEncodedType specifies the form encoding. Acceptable values: application/x-www-form-urlencoded multipart/form-data text/plain</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formEncodedType attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormEncodedType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute formEncodedType = myInput:GetFormEncodedType()
text formEncodedTypeValue = ""
if formEncodedType not= undefined
   formEncodedTypeValue = formEncodedType:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormMethod">public action GetFormMethod()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormMethod"><p>This action gets the formMethod attribute. The value can be accessed by calling the GetValue() action on the returned formMethod attribute. The formMethod attribute specifies the protocol method in which the form data will be sent to the formAction address.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formMethod attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormMethod"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute formMethod = myInput:GetFormMethod()
text formMethodValue = ""
if formMethod not= undefined
   formMethodValue = formMethod:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormName">public action GetFormName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormName"><p>This action gets the formName attribute. The value can be accessed by calling the GetValue() action on the returned formName attribute. The formId attribute specifies the form the input is accociated with.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formName attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute formName = myInput:GetFormName()
text formNameValue = ""
if formName not= undefined
   formNameValue = formName:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormNovalidate">public action GetFormNovalidate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormNovalidate"><p>This action gets the value of the formNovalidate attribute. The default value is false. When true the form will not be validated upon submission. This attribute is only used for input controls of type submit.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The formNovalidate attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormNovalidate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputFormNovalidate = myInput:GetFormNovalidate()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormTarget">public action GetFormTarget()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormTarget"><p>This action gets the formTarget attribute. The value can be accessed by calling the GetValue() action on the returned formTarget attribute. The formTarget attribute specifies which frame the form response is to be opened in. Acceptable Values: _blank _parent _top _self existing frame name</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formTarget attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormTarget"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute formTarget = myInput:GetFormTarget()
text formTarGetValue = ""
if formTarget not= undefined
   formTarGetValue = formTarget:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeight">public action GetHeight()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeight"><p>This action gets the height attribute. The value can be accessed by calling the GetValue() action on the returned height attribute. The height attribute is only used for input controls of type image and specifies the height of the image.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The height attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeight"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute height = myInput:GetHeight()
text heightValue = ""
if height not= undefined
   heightValue = height:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetImageAddress">public action GetImageAddress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetImageAddress"><p>This action gets the imageAddress attribute. The value can be accessed by calling the GetValue() action on the returned imageAddress attribute. The imageAddress attribute specifies the address of the image input control.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The imageAddress attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetImageAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute imageAddress = myInput:GetImageAddress()
text imageAddressValue = ""
if imageAddress not= undefined
   imageAddressValue = imageAddress:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetList">public action GetList()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetList"><p>This action gets the list attribute. The value can be accessed by calling the GetValue() action on the returned list attribute. The list attribute specifies the pre-defined datalist that is to be used by the input control.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The list attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetList"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute list = myInput:GetList()
text listValue = ""
if list not= undefined
   listValue = list:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMax">public action GetMax()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMax"><p>This action gets the max attribute. The value can be accessed by calling the GetValue() action on the returned max attribute. The max attribute specifies the maximum value for controls of type number, range, date, datetime, datetime-local, month, time and week.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The max attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMax"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute max = myInput:GetMax()
text maxValue = ""
if max not= undefined
   maxValue = max:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxLength">public action GetMaxLength()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxLength"><p>This action gets the maxLength attribute. The value can be accessed by calling the GetValue() action on the returned maxLength attribute. The maxLength attribute specifies the maximum number of characters allowed in an input control.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The maxLength attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaxLength"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute maxLength = myInput:GetMaxLength()
text maxLengthValue = ""
if maxLength not= undefined
   maxLengthValue = maxLength:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMin">public action GetMin()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMin"><p>This action gets the min attribute. The value can be accessed by calling the GetValue() action on the returned min attribute. The min attribute specifies the minimum value for controls of type number, range, date, datetime, datetime-local, month, time and week.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The min attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMin"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute min = myInput:GetMin()
text minValue = ""
if min not= undefined
   minValue = min:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMultiple">public action GetMultiple()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMultiple"><p>This action gets the value of the multiple attribute. The default value is false. When true the email or file control will accept multiple values.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The multiple attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMultiple"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputMultiple = myInput:GetMultiple()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name attribute. The value can be accessed by calling the GetValue() action on the returned name attribute. The name attribute specifies the name of the input control.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute name = myInput:GetName()
text nameValue = ""
if name not= undefined
   nameValue = name:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPattern">public action GetPattern()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPattern"><p>This action gets the pattern attribute. The value can be accessed by calling the GetValue() action on the returned pattern attribute. The pattern attribute defines a regular expression for the input control when it is of type text, search, url, tel, email, or password.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The pattern attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPattern"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute pattern = myInput:GetPattern()
text patternValue = ""
if pattern not= undefined
   patternValue = pattern:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPlaceHolder">public action GetPlaceHolder()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPlaceHolder"><p>This action gets the placeHolder attribute. The value can be accessed by calling the GetValue() action on the returned placeHolder attribute. The placeHolder attribute acts as a description of the desired input. It will be shown upon page load and dissappear when the value is edited. The placeHolder attribute is only used when the input control is of type text, search, url, tel, email, or password.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The placeHolder attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPlaceHolder"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute placeHolder = myInput:GetPlaceHolder()
text placeHolderValue = ""
if placeHolder not= undefined
   placeHolderValue = placeHolder:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetReadOnly">public action GetReadOnly()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetReadOnly"><p>This action gets the value of the readOnly attribute. The default value is false. When true the input control's value cannot be modified.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The readOnly attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetReadOnly"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputReadOnly = myInput:GetReadOnly()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRequired">public action GetRequired()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRequired"><p>This action gets the value of the required attribute. The default value is false. When true the input control's value must be edited before submission.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The required attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRequired"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
boolean inputRequired = myInput:GetRequired()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action gets the size attribute. The value can be accessed by calling the GetValue() action on the returned size attribute. The size specifies the character width of an input control if it is of type text, search, tel, url, email, or password.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The size attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute size = myInput:GetSize()
text sizeValue = ""
if size not= undefined
   sizeValue = size:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetStep">public action GetStep()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetStep"><p>This action gets the step attribute. The value can be accessed by calling the GetValue() action on the returned step attribute. The step specifies the legal number intervals for the input control when it is of type number, range, date, datetime, datetime-local, month, time or week.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The step attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetStep"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute step = myInput:GetStep()
text stepValue = ""
if step not= undefined
   stepValue = step:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the type attribute. The value can be accessed by calling the GetValue() action on the returned type attribute. The type attribute specifies the type of the input control. Acceptable values: button checkbox color date datetime datetime-local email file hidden image month number password radio range reset search submit tel text time url week</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The type attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute type = myInput:GetType()
text typeValue = ""
if type not= undefined
   typeValue = type:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWidth">public action GetWidth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWidth"><p>This action gets the width attribute. The value can be accessed by calling the GetValue() action on the returned width attribute. The width attribute specifies the width of an image input control.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The width attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWidth"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
use Libraries.Web.Attribute
Input myInput
Attribute width = myInput:GetWidth()
text widthValue = ""
if width not= undefined
   widthValue = width:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAccept:text">public action SetAccept(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAccept:text"><p>This action sets the accept attribute. This value is only considered when the input type is file. It specifies the file types that will be accepted by the server. Multiple values are to be seperated by a coma. Accptable values: audio/* video/* image/* MIME_type</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The accept of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAccept:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetAccept("audio/*,text/txt")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAlternate:text">public action SetAlternate(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAlternate:text"><p>This action sets the alternate attribute. The alternate specifies alternate text for images.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The alternate of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAlternate:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetAlternate("Submit Button")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutocomplete:boolean">public action SetAutocomplete(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutocomplete:boolean"><p>This action sets the autocomplete attribute. Autocomplete is true by default. When true the browser will attempt to autocomplete the input field.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Boolean value that represents the autocomplete of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutocomplete:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetAutocomplete(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutofocus:boolean">public action SetAutofocus(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutofocus:boolean"><p>This action sets the autofocus attribute. Autofocus is false by default. When true the browser place the focus on the input value when the page loads.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The autofocus of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutofocus:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetAutofocus(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetChecked:boolean">public action SetChecked(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetChecked:boolean"><p>This action sets the checked attribute. The checked attribute is only valid when the input control is of type checkbox or radio. The default value is false. When true the radio button or checkbox will be checked after the page loads.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The checked of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetChecked:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetChecked(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDefaultValue:text">public action SetDefaultValue(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDefaultValue:text"><p>This action sets the defaultValue attribute. The defaultValue attribute specifies the default value of the input control.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The defaultValue of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDefaultValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetDefaultValue("gender_female")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action sets the disabled attribute. The default value is false. When true the input control will not be clickable. This is ignored when the input control is of type hidden.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The disabled of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormAction:text">public action SetFormAction(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormAction:text"><p>This action sets the formAction attribute. The formAction attribute specifies the address of the file that will process the input control when the form is submitted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formAction of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormAction:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetFormAction("inputFormAction.quorum")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormEncodedType:text">public action SetFormEncodedType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormEncodedType:text"><p>This action sets the formEncodedType attribute. The formEncodedType specifies the form encoding. Acceptable values: application/x-www-form-urlencoded multipart/form-data text/plain</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formEncodedType of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormEncodedType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetFormEncodedType("application/x-www-form-urlencoded")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormMethod:text">public action SetFormMethod(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormMethod:text"><p>This action sets the formMethod attribute. The formMethod attribute specifies the protocol method in which the form data will be sent to the formAction address.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formMethod of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormMethod:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetFormMethod("get")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormName:text">public action SetFormName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormName:text"><p>This action sets the formName attribute. The formId attribute specifies the form the input is accociated with.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formName of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetFormName("Input Form")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormNovalidate:boolean">public action SetFormNovalidate(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormNovalidate:boolean"><p>This action sets the formNovalidate attribute. The default value is false. When true the form will not be validated upon submission. This attribute is only used for input controls of type submit.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formNovalidate of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormNovalidate:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetFormNovalidate(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormTarget:text">public action SetFormTarget(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormTarget:text"><p>This action sets the formTarget attribute. The formTarget attribute specifies which frame the form response is to be opened in. Acceptable Values: _blank _parent _top _self existing frame name</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The formTarget of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormTarget:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetFormTarget("_blank")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeight:text">public action SetHeight(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeight:text"><p>This action sets the height attribute. The height attribute is only used for input controls of type image and specifies the height of the image.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The height of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeight:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetHeight("50")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetImageAddress:text">public action SetImageAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetImageAddress:text"><p>This action sets the imageAddress attribute. The imageAddress attribute specifies the address of the image input control.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The imageAddress of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetImageAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetImageAddress("go_button.jpg")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetList:text">public action SetList(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetList:text"><p>This action sets the list attribute. The list attribute specifies the pre- defined datalist that is to be used by the input control.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The list of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetList:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetList("input_list")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMax:text">public action SetMax(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMax:text"><p>This action sets the max attribute. The max attribute specifies the maximum value for controls of type number, range, date, datetime, datetime-local, month, time and week.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The max of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMax:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetMax("100")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMaxLength:text">public action SetMaxLength(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMaxLength:text"><p>This action sets the maxLength attribute. The maxLength attribute specifies the maximum number of characters allowed in an input control.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The maxLength of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMaxLength:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetMaxLength("55")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMin:text">public action SetMin(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMin:text"><p>This action sets the min attribute. The min attribute specifies the minimum value for controls of type number, range, date, datetime, datetime-local, month, time and week.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The min of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMin:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetMin("9")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMultiple:boolean">public action SetMultiple(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMultiple:boolean"><p>This action sets the multiple attribute. The default value is false. When true the email or file control will accept multiple values.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The multiple of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMultiple:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetMultiple(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name attribute. The name attribute specifies the name of the control.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetName("Male_Gender_Input")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPattern:text">public action SetPattern(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPattern:text"><p>This action sets the pattern attribute.The pattern attribute defines a regular expression for the input control when it is of type text, search, url, tel, email, or password.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The pattern of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPattern:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetPattern("1?{{0..9}^3(-?)}^2{0..9}^4")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPlaceHolder:text">public action SetPlaceHolder(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPlaceHolder:text"><p>This action sets the placeHolder attribute. The placeHolder attribute acts as a description of the desired input. It will be shown upon page load and dissappear when the value is edited. The placeHolder attribute is only used when the input control is of type text, search, url, tel, email, or password.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The placeHolder of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPlaceHolder:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetPlaceHolder("First Name")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetReadOnly:boolean">public action SetReadOnly(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetReadOnly:boolean"><p>This action sets the readOnly attribute. The default value is false. When true the input control's value cannot be modified.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The readOnly of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetReadOnly:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetReadOnly(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetRequired:boolean">public action SetRequired(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetRequired:boolean"><p>This action sets the required attribute. The default value is false. When true the input control's value must be edited before submission.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The required of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetRequired:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetRequired(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSize:text">public action SetSize(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSize:text"><p>This action sets the size attribute. The size specifies the character width of an input control if it is of type text, search, tel, url, email, or password.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The size of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSize:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetSize("50")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetStep:text">public action SetStep(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetStep:text"><p>This action sets the step attribute. The step specifies the legal number intervals for the input control when it is of type number, range, date, datetime, datetime-local, month, time or week.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The step of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetStep:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetStep("5")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the type attribute. The type attribute specifies the type of the input control. Acceptable values: button checkbox color date datetime datetime-local email file hidden image month number password radio range reset search submit tel text time url week</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The type of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetType("button")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWidth:text">public action SetWidth(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWidth:text"><p>This action sets the width attribute. The width attribute specifies the width of an image input control.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The width of the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWidth:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Input
Input myInput
myInput:SetWidth("15")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a> public action GetOnBlur(), public action GetOnChange(), public action GetOnContextMenu(), public action GetOnFocus(), public action GetOnFormChange(), public action GetOnFormInput(), public action GetOnInput(), public action GetOnInvalid(), public action GetOnSelect(), public action GetOnSubmit(), public action SetOnBlur(text value), public action SetOnChange(text value), public action SetOnContextMenu(text value), public action SetOnFocus(text value), public action SetOnFormChange(text value), public action SetOnFormInput(text value), public action SetOnInput(text value), public action SetOnInvalid(text value), public action SetOnSelect(text value), public action SetOnSubmit(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/KeyboardAttributeAccepter.php">Libraries.Web.KeyboardAttributeAccepter</a> public action GetOnKeyDown(), public action GetOnKeyPress(), public action GetOnKeyUp(), public action SetOnKeyDown(text value), public action SetOnKeyPress(text value), public action SetOnKeyUp(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/MouseAttributeAccepter.php">Libraries.Web.MouseAttributeAccepter</a> public action GetOnClick(), public action GetOnDoubleClick(), public action GetOnDrag(), public action GetOnDragEnd(), public action GetOnDragEnter(), public action GetOnDragLeave(), public action GetOnDragOver(), public action GetOnDragStart(), public action GetOnDrop(), public action GetOnMouseDown(), public action GetOnMouseMove(), public action GetOnMouseOut(), public action GetOnMouseOver(), public action GetOnMouseUp(), public action GetOnMouseWheel(), public action GetOnScroll(), public action SetOnClick(text value), public action SetOnDoubleClick(text value), public action SetOnDrag(text value), public action SetOnDragEnd(text value), public action SetOnDragEnter(text value), public action SetOnDragLeave(text value), public action SetOnDragOver(text value), public action SetOnDragStart(text value), public action SetOnDrop(text value), public action SetOnMouseDown(text value), public action SetOnMouseMove(text value), public action SetOnMouseOut(text value), public action SetOnMouseOver(text value), public action SetOnMouseUp(text value), public action SetOnMouseWheel(text value), public action SetOnScroll(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>