<?php $classPageTitle = "FormAttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.FormAttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.FormAttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class FormAttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The FormAttributeAccepter class is designed as a helper to ease adding and removing attributes from particular WebTag objects belonging to a form. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the FormAttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
class Button is FormAttributeAccepter, WebGenerator
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnBlur">public action GetOnBlur()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnBlur"><p>Returns the onblur attribute. If the onblur attribute is enabled the form element has lost focus.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current onblur attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnBlur"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnBlur()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnChange">public action GetOnChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnChange"><p>Returns the OnChange attribute. If the OnChange event occurs when a form elements state, text, or selection is changed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnChange event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnContextMenu">public action GetOnContextMenu()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnContextMenu"><p>Returns the OnContextMenu attribute. If the OnContextMenu specifies the script to run when a context menu is triggered.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnContextMenu attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnContextMenu"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnContextMenu()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnFocus">public action GetOnFocus()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnFocus"><p>Returns the OnFocus attribute. If the OnFocus event occurs when a form element gains focus.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnFocus event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnFocus"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnFocus()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnFormChange">public action GetOnFormChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnFormChange"><p>Returns the OnFormChange attribute. If the OnFormChange stores the script to be run when a form change occurs.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnFormChange script attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnFormChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnFormChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnFormInput">public action GetOnFormInput()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnFormInput"><p>Returns the OnFormInput attribute. If the OnFormInput is triggered, by a form recieving input, the specified script will be run.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnFormInput event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnFormInput"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnFormInput()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnInput">public action GetOnInput()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnInput"><p>Returns the OnInput attribute. If the OnInput is triggered, by a form element recieving input, the specified script will be run.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnInput event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnInput"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnInput()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnInvalid">public action GetOnInvalid()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnInvalid"><p>Returns the OnInvalid attribute. If the OnInvalid is triggered, by a form element recieving invalid input, the specified script will be run.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnInvalid event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnInvalid"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnInvalid()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnSelect">public action GetOnSelect()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnSelect"><p>Returns the OnSelect attribute. OnSelect is triggered by the selection of an elements text on the form.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnSelect event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnSelect"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnSelect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnSubmit">public action GetOnSubmit()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnSubmit"><p>Returns the OnSubmit attribute. The OnSubmit is triggered by a submit action on the form.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnSubmit event attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnSubmit"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
Attribute attribute = accept:GetOnSubmit()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnBlur:text">public action SetOnBlur(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnBlur:text"><p>Sets the onblur attribute. The onblur attribute is the script to be triggered when the onblur even occurs. OnBlur is fired the moment an element loses focus.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current onblur script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnBlur:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnBlur("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnChange:text">public action SetOnChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnChange:text"><p>Sets the OnChange attribute. The OnChange attribute is the script to be triggered when the OnChange event occurs. OnChange is fired the moment an element content is changed on a form.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnChange script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnChange("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnContextMenu:text">public action SetOnContextMenu(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnContextMenu:text"><p>Sets the OnContextMenu attribute. The OnContextMenu attribute is the script to be triggered when the OnContextMenu event occurs. OnContextMenu is fired when a context menu is selected.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnContextMenu script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnContextMenu:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnContextMenu("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnFocus:text">public action SetOnFocus(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnFocus:text"><p>Sets the OnFocus attribute. The OnFocus attribute is the script to be triggered when the OnFocus event occurs. OnFocus is fired the moment an element gains focus.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnFocus script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnFocus:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnFocus("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnFormChange:text">public action SetOnFormChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnFormChange:text"><p>Sets the OnFormChange attribute. The OnFormChange attribute is the script to be triggered when the OnFormChange event occurs. OnFormChange is fired the moment the form is changed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnFormChange script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnFormChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnFormChange("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnFormInput:text">public action SetOnFormInput(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnFormInput:text"><p>Sets the OnFormInput attribute. The OnFormInput attribute is the script to be triggered when the OnFormInput event occurs. OnFormInput is fired the moment the form is given input.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnFormInput script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnFormInput:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnFormInput("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnInput:text">public action SetOnInput(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnInput:text"><p>Sets the OnInput attribute. The OnInput attribute is the script to be triggered when the OnInput event occurs. OnInput is fired the moment a form element is given input.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnInput script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnInput:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnInput("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnInvalid:text">public action SetOnInvalid(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnInvalid:text"><p>Sets the OnInvalid attribute. The OnInvalid attribute is the script to be triggered when the OnInvalid event occurs. OnInvalid is fired the moment a form element is invalid.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnInvalid script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnInvalid:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnInvalid("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnSelect:text">public action SetOnSelect(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnSelect:text"><p>Sets the OnSelect attribute. The OnSelect attribute is the script to be triggered when the OnSelect event occurs. OnSelect is fired the moment a form elements text is selected.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnSelect script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnSelect:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnSelect("upperCase()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnSubmit:text">public action SetOnSubmit(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnSubmit:text"><p>Sets the OnSubmit attribute. The OnSubmit attribute is the script to be triggered when the OnSubmit event occurs. OnSubmit is fired the moment a submit action occurs.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnSubmit script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnSubmit:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.FormAttributeAccepter
use Libraries.Web.Attribute
FormAttributeAccepter accept
accept:SetOnSubmit("upperCase()")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>