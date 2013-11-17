<?php $classPageTitle = "TextArea"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.TextArea</h1>
<input type="hidden" id="classkey" value="Libraries.Web.TextArea" /><h2> <span class="controllable" data-componentType="class-name">Class TextArea</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The TextArea class represents HTML's (Hypertext Markup Language) textarea tag which is a multi-line text input box that is capable of containing an unlimited number of caracters. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_textarea.asp">The textarea attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      //add a TextArea
      TextArea myTextArea
      page:AddToBody(myTextArea)
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutofocus">public action GetAutofocus()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutofocus"><p>This action gets the autofocus Attribute. The autofocus attribute tells the TextArea whether or not it will get focus when a webPage is loaded.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if autofocus is enabled or false if it is disabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutofocus"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
boolean isAutofocusOn = myTextArea:GetAutofocus()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action gets the disabled attribute of the textarea. If false is returned the textarea is enabled and if true is returned it is disabled.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the textarea is disabled and false if it not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
boolean disabled = myTextArea:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormName">public action GetFormName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormName"><p>This action gets the formName attribute of the TextArea. The name of the formName is stored as the value in the attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The formName attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
Attribute formName = myTextArea:GetFormName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHeight">public action GetHeight()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHeight"><p>This action gets the height attribute of the textarea. This attribute specifies the height in lines of the textarea. The default value is 2 lines.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The height of the textarea.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHeight"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
Attribute height = myTextArea:GetHeight()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxLength">public action GetMaxLength()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxLength"><p>This action gets the max length attribute of the textarea. The max length of the form the text area is stored as the value in the attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The max length of the characters in the text area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaxLength"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
Attribute max = myTextArea:GetMaxLength()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name attribute of the textarea. The name of the the text area is stored as the value in the attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
Attribute textAreaName = myTextArea:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPlaceHolder">public action GetPlaceHolder()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPlaceHolder"><p>This action gets the placeholder attribute of the textarea. The placeholder is the description of what should be placed in the text area. This placeholder text is displayed in the text area.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The placeholder attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPlaceHolder"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
Attribute placeHolder = myTextArea:GetPlaceHolder()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetReadOnly">public action GetReadOnly()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetReadOnly"><p>This action gets the read-only attribute of the textarea. If the textarea is readonly then the textarea does not allow text to be written to the textarea.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the textarea is read-only and false if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetReadOnly"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
boolean readOnly = myTextArea:GetReadOnly()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRequired">public action GetRequired()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRequired"><p>This action gets the require attribute of the textarea. If the textarea is required the value true will be returned and false if it is not required. Required means the textarea must be filled out.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the textarea is required and false if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRequired"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
boolean required = myTextArea:GetRequired()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTextWrap">public action GetTextWrap()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTextWrap"><p>This action gets the text wrap attribute of the textarea. There are two types of text wrap that can be specified, "hard" or "soft". A soft wrap means the text is not wrapped in the text area(this is the default). A hard wrap means the text will be wrapped (contains newlines) and a width must be specifed in this case.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTextWrap"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
Attribute wrap = myTextArea:GetTextWrap()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWidth">public action GetWidth()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWidth"><p>This action gets the width of the textarea. The width attribute defines the width of the TextArea in columns. It corresponds to the following html attribute: <textarea cols="10"> and defaults to a value of 20.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The width of the text area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWidth"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
integer width = myTextArea:GetWidth()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutofocus:boolean">public action SetAutofocus(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutofocus:boolean"><p>This action sets the autofocus Attribute. The autofocus attribute tells the TextArea whether or not it will get focus when a webPage is loaded.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The autofocus is enable with a value of true or is not enabled with a value of false.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutofocus:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetAutofocus(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action sets the disabled attribute of the textarea. The value of true will disable the textarea and false will enable the textarea.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True if the textarea is disabled and false if it not.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormName:text">public action SetFormName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormName:text"><p>This action sets the form attribute of the textarea. The value of formName attribute should map to a defined form name.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Name of the form.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetFormName("myForm")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetHeight:integer">public action SetHeight(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetHeight:integer"><p>This action sets the required attribute of the textarea. This attribute specifies the height in lines of the textarea. The default value is 2 lines.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The height in lines of the text area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetHeight:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetHeight(5)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMaxLength:integer">public action SetMaxLength(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMaxLength:integer"><p>This action sets the max length attribute of the textarea. The max length is the max number of characters that the text area will hold.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The max length of characters in the text area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMaxLength:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetMaxLength(150)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name attribute of the textarea. The name variable sets the name of the textarea.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name of the text area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetName("myTextArea")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPlaceHolder:text">public action SetPlaceHolder(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPlaceHolder:text"><p>This action sets the placeholder attribute of the textarea. The placeholder is the description of what should be placed in the text area. This placeholder text is displayed in the text area.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The placeholder text in the text area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPlaceHolder:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetPlaceHolder("Enter your full name...")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetReadOnly:boolean">public action SetReadOnly(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetReadOnly:boolean"><p>This action sets the read-only attribute of the textarea. If the textarea is readonly then the textarea does not allow text to be written to the textarea.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True if the textarea is read-only and false if it is not.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetReadOnly:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetReadOnly(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetRequired:boolean">public action SetRequired(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetRequired:boolean"><p>This action sets the required attribute of the textarea. If the textarea is required (true) then the textarea must be filled out.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">True if the textarea is required and false if it is not.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetRequired:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetRequired(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTextWrap:text">public action SetTextWrap(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTextWrap:text"><p>This action sets the text wrap attribute of the textarea. There are two types of text wrap that can be specified, "hard" or "soft". A soft wrap means the text is not wrapped in the text area(this is the default). A hard wrap means the text will be wrapped (contains newlines) and a width must be specifed in this case.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">Hard or soft wrap (default:soft).</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTextWrap:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetTextWrap(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetWidth:integer">public action SetWidth(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetWidth:integer"><p>This action sets the width Attribute. The width is the width of the TextArea and it is defaulted to a value of 20.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The width of the textarea.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetWidth:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.TextArea
use Libraries.Web.Attribute
TextArea myTextArea
myTextArea:SetWidth(25)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>