<?php $classPageTitle = "Form"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Form</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Form" /><h2> <span class="controllable" data-componentType="class-name">Class Form</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Form class represents HTML's (Hypertext Markup Language) form tag which allows a form to be generated that accepts user input. The form can contain one or more FieldSet, Input, TextArea, Button, Select, Option, OptionGroup, or Label elements. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_form.asp">The form attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      
      //create a form
      Form form
      //create a fieldset
      FieldSet field
      //create a column
      Input input
      //setup that input
      input:SetType("text")
      //add the input to the fieldset
      field:Add(input)
      //add the fieldset to the form
      form:Add(field)
      page:AddToBody(form)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>newTab</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>parentFrame</em></li>
<li class = "package_standard" ><strong>text</strong> <em>sameFrame</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>sameWindow</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAcceptCharset">public action GetAcceptCharset()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAcceptCharset"><p>This action gets the character encodings of the form that will be used to submit the form. The default value is "UNKNOWN".</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: "UNKNOWN" or the type of char encoding of the form.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAcceptCharset"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
text attr = form:GetAcceptCharset()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoComplete">public action GetAutoComplete()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoComplete"><p>This action gets</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutoComplete"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
boolean isAutoComplete = form:GetAutoComplete()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEncodedType">public action GetEncodedType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEncodedType"><p>This action gets the encoded type of the form data to be submitted to the server. Either application/x-www-form-urlencoded, multipart/form-data, or text/plain.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The encoded type of the form.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetEncodedType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
text encodedType = form:GetEncodedType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormAction">public action GetFormAction()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormAction"><p>This action gets the location the form is to be submitted to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: Submition location.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormAction"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
use Libraries.Web.Attribute
Form form
Attribute attr = form:GetFormAction()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name of the form.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name of the form.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
use Libraries.Web.Attribute
Form form
Attribute name = form:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTarget">public action GetTarget()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTarget"><p>This action gets the target of the response from the submitted data. The response can be sent to _blank, _self, _parent, _top, or a framename.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The target.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTarget"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
use Libraries.Web.Attribute
Form form
Attribute target = form:GetTarget()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValidate">public action GetValidate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValidate"><p>This action gets whether the input data in the form should be validated.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if input is validated and false if it is not validated.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValidate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
boolean attr = form:GetValidate()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsPost">public action IsPost()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsPost"><p>This action gets the post mehtod that is set for sending form data. if true the HTTP method is "post" if false it is "get".</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if "post" and false if "get"</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsPost"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
boolean attr = form:IsPost()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAcceptCharset:text">public action SetAcceptCharset(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAcceptCharset:text"><p>This action sets the character encoding of the form data that will be submitted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The character encoding.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAcceptCharset:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetAcceptCharset("ISO-8859-1")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoComplete:boolean">public action SetAutoComplete(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoComplete:boolean"><p>This action sets the autocompletion on or off for a form and its elements.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True for autocomplete and false for it to turn off.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutoComplete:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetAutoComplete(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetEncodedType:text">public action SetEncodedType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetEncodedType:text"><p>This action sets the encoded type of the form data to be submitted to the server. Either application/x-www-form-urlencoded, multipart/form-data, or text/plain.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">encoded type.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetEncodedType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetEncodedType("text/plain")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormAction:text">public action SetFormAction(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormAction:text"><p>This action sets the submit location of the form.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Submit location.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormAction:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetFormAction("test.asp")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetGet:boolean">public action SetGet(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetGet:boolean"><p>This action sets the HTTP method to get. See <a>http://www.w3schools.com/tags/att_form_method.asp</a></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">true if using the HTTP method, get.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetGet:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetGet(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name of the form.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name of the form.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetName("myForm")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPost:boolean">public action SetPost(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPost:boolean"><p>This action sets the HTTP method to post. See <a>http://www.w3schools.com/tags/att_form_method.asp</a></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">true if using the HTTP method, post.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPost:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetPost(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTarget:text">public action SetTarget(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTarget:text"><p>This action sets the target of the response from the submitted data. The response can be sent to _blank, _self, _parent, _top, or a framename.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">_blank, _self, _parent, _top, or a framename</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTarget:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetTarget("_self")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValidate:boolean">public action SetValidate(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValidate:boolean"><p>This action sets the validation for input.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True will validate and false will not validate the input.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValidate:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Form
Form form
form:SetValidate(false)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/FormAttributeAccepter.php">Libraries.Web.FormAttributeAccepter</a> public action GetOnBlur(), public action GetOnChange(), public action GetOnContextMenu(), public action GetOnFocus(), public action GetOnFormChange(), public action GetOnFormInput(), public action GetOnInput(), public action GetOnInvalid(), public action GetOnSelect(), public action GetOnSubmit(), public action SetOnBlur(text value), public action SetOnChange(text value), public action SetOnContextMenu(text value), public action SetOnFocus(text value), public action SetOnFormChange(text value), public action SetOnFormInput(text value), public action SetOnInput(text value), public action SetOnInvalid(text value), public action SetOnSelect(text value), public action SetOnSubmit(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>