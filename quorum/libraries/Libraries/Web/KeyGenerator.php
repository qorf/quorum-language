<?php $classPageTitle = "KeyGenerator"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.KeyGenerator</h1>
<input type="hidden" id="classkey" value="Libraries.Web.KeyGenerator" /><h2> <span class="controllable" data-componentType="class-name">Class KeyGenerator</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The KeyGenerator class represents HTML's (Hypertext Markup Language) keygen tag which is used to make a key-pair generator for forms. When the form is submitted two keys are created. One key, the private key, is stored locally while the other key, the public key is sent to the server. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_keygen.asp">The keygen attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      KeyGenerator keyGen
      keyGen:SetName("secure")
      page:AddToBody(keyGen)
      
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>dsaKeyType</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>ecKeyType</em></li>
<li class = "package_standard" ><strong>text</strong> <em>rsaKeyType</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoFocus">public action GetAutoFocus()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoFocus"><p>This action returns true if the KeyGenerator should automatically get focus as the page loads.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if KeyGenerator automatically gets 
        focus.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutoFocus"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
KeyGenerator keyGen
boolean autoFocus = keyGen:GetAutoFocus()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChallenge">public action GetChallenge()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChallenge"><p>This action gets the string that will be used if the KeyGenerator is challenged.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The string used for challenge</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChallenge"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
Attribute challengeValue = keyGen:GetChallenge()
if challengeValue not= undefined
   text challenge = challengeValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDisabled">public action GetDisabled()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDisabled"><p>This action returns true if the KeyGenerator should be disabled.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true should be disabled.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDisabled"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
KeyGenerator keyGen
boolean isDisabled = keyGen:GetDisabled()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFormId">public action GetFormId()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFormId"><p>This action gets form id attribute for the form that the KeyGenerator is set to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The form id for form the KeyGenerator is connected to.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFormId"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
Attribute formIdValue = keyGen:GetFormId()
if formIdValue not= undefined
   text formId = formIdValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyType">public action GetKeyType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyType"><p>This action gets key type algorithm that will be used to generate the key.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The key type algorithm that will be used.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKeyType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
Attribute keyTypeValue = keyGen:GetKeyType()
if keyTypeValue not= undefined
   text keyType = keyTypeValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name attribute of the KeyGenerator. the value of the name is stored in the value of the name attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The name attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
Attribute keyGenNameValue = keyGen:GetName()
if keyGenNameValue not= undefined
   text name = keyGenNameValue:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoFocus:boolean">public action SetAutoFocus(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoFocus:boolean"><p>This action should be set to true if the KeyGenerator should automatically get focus as the page loads.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutoFocus:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
KeyGenerator keyGen
keyGen:SetAutoFocus(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetChallenge:text">public action SetChallenge(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetChallenge:text"><p>This action sets text used if the KeyGenerator is challenged.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetChallenge:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
keyGen:SetChallenge("challenge")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDisabled:boolean">public action SetDisabled(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDisabled:boolean"><p>This action should be set to true if the KeyGenerator should be disabled.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDisabled:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.All
KeyGenerator keyGen
keyGen:SetDisabled(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetFormId:text">public action SetFormId(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetFormId:text"><p>This action sets the form id that the KeyGenerator will be connected to.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetFormId:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
keyGen:SetFormId("aForm")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetKeyType:text">public action SetKeyType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetKeyType:text"><p>This action sets key type algorithm that will be used to generate the key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetKeyType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
keyGen:SetFormId(keyGen:rsaKeyType)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name attribute of the KeyGenerator.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The name attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyGenerator
use Libraries.Web.Attribute
KeyGenerator keyGen
keyGen:SetName("name")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>