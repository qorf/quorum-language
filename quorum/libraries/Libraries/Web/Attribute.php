<?php $classPageTitle = "Attribute"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Attribute</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Attribute" /><h2> <span class="controllable" data-componentType="class-name">Class Attribute</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Attribute class represents a property of a particular tag in the web libraries. For example, the Hypertext Markup Language (HTML) 5 standard, we might define an attribute that represents a web link, or the location of an image or move file. Most of the time, users will not need to interact with the Attribute class, unless you need to gather information from an existing web page. Generally, most WebTag objects allow you to set attributes automatically, without needing to instantiate an Attribute directly.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.Attribute
Attribute attribute
text result = attribute:Generate()
output result</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/WebGenerator.php">Libraries.Web.WebGenerator</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>Generate method converts web page information into raw text that can be sent to a web server or otherwise printed out.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: A text representation of the item being generated.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Generate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
//by default, this would output a blank web page
WebPage page
output page:Generate()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>The GetName action returns the name of the attribute. The name in this context matches with what is called an "Attribute" in the Hypertext Markup Language (HTML) 5 standard. For example, in HTML, the "href" tag indicates where to link to in web links.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The name of the attribute (e.g., href).</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
//by default, this would output an empty text value
Attribute attribute
text result = attribute:GetName()
output result</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>The GetValue action returns the value of the attribute. The name in this context matches with what is called an "Value" in the Hypertext Markup Language (HTML) 5 standard. For example, the value might indicate the location of a file or an image, or where to link to on the web.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The value of the attribute (e.g., http://www.google.com).</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
//by default, this would output an empty text value
Attribute attribute
text result = attribute:GetValue()
output result</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text newName)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>The SetName action sets the name of the attribute. The name in this context matches with what is called an "Attribute" in the Hypertext Markup Language (HTML) 5 standard. For example, in HTML, the "href" tag indicates where to link to in web links.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newName"><strong>text</strong> <em>newName</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newName">The name of the attribute (e.g., href).</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
//by default, this would output an empty text value
Attribute attribute
attribute:SetName("src")
text result = attribute:GetName()
output result</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:text">public action SetValue(text newValue)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:text"><p>The SetValue action returns the value of the attribute. The name in this context matches with what is called an "Value" in the Hypertext Markup Language (HTML) 5 standard. For example, the value might indicate the location of a file or an image, or where to link to on the web.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newValue"><strong>text</strong> <em>newValue</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newValue"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attribute
//by default, this would output an empty text value
Attribute attribute
attribute:SetValue("http://www.google.com")
text result = attribute:GetValue()
output result</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>