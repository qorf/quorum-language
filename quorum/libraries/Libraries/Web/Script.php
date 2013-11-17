<?php $classPageTitle = "Script"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Script</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Script" /><h2> <span class="controllable" data-componentType="class-name">Class Script</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Script class represents HTML's (Hypertext Markup Language) script tag which is used to point to a client-side script (e.g. javascript). This can be used for dynamic changes to content or validation reasons.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Script script
      script:SetCode("//insert javascript here")
      page:AddToBody(script)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>charSetLatin</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>charSetUnicode</em></li>
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAddress">public action GetAddress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the address of the external javascript file.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The address of the external javascript file.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
use Libraries.Web.Attribute
Script script
Attribute addressValue = script:GetAddress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAsync">public action GetAsync()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAsync"><p>This action returns true if the web page will run the external script asynchronously of the rest of the page.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if asych has been set.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAsync"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
boolean asych = script:GetAsync()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCharSet">public action GetCharSet()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCharSet"><p>This action gets the charset that has been set to the script.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current charset to use.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCharSet"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
use Libraries.Web.Attribute
Script script
Attribute charSetValue = script:GetCharSet()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCode">public action GetCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCode"><p>This action gets the script code that has been entered into the script tag.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The script code.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
text code = script:GetCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDefer">public action GetDefer()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDefer"><p>This action returns true if the web page will defer running the external script until of the rest of the page is done loading.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: This returns true if defer has been set.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDefer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
boolean defer = script:GetDefer()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the MIME type attribute of the script. The type attribute maps to a MIME type of the script.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The MIME type attribute of the script.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
use Libraries.Web.Attribute
Script script
Attribute type = script:GetType()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the address attribute of script if the page will be running an external script.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The address of a external javascript file.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
script:SetAddress("someJavaScriptFile.js")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAsync:boolean">public action SetAsync(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAsync:boolean"><p>This action should be set to true if the the script should be run asynchronously with the rest of the page. This will not wait for the rest of the page to load before it runs. This is also only for external scripts and should have an address set with it as well.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAsync:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
script:SetAsync(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCharSet:text">public action SetCharSet(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCharSet:text"><p>This action sets charset that will be used with the script. The charset is used when the character encoding in an external script file differs from the encoding in the HTML document.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCharSet:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
script:SetCharSet(script:charSetUnicode)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCode:text">public action SetCode(text code)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCode:text"><p>This action sets the script code within the script tag.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="code"><strong>text</strong> <em>code</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="code">The script code.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCode:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
text quote = ""
quote = quote:GetDoubleQuote()
script:SetCode("write.document(" + quote + "My webpage!" + quote +")")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDefer:boolean">public action SetDefer(boolean value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDefer:boolean"><p>This action should be set to true if the the script should be run after the rest of the page is done loading. This is also only for external scripts and should have an address set with it as well.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>boolean</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDefer:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
script:SetDefer(true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the Type attribute of the script. The type attribute maps to a MIME type of the script</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The MIME type for the script.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Script
Script script
script:SetType("text/html")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>