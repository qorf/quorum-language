<?php $classPageTitle = "Attributes"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Attributes</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Attributes" /><h2> <span class="controllable" data-componentType="class-name">Class Attributes</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Attributes class stores a number of attribute objects. Effectively, this class is just a wrapper for the HashTable class, adding an action related to automatically printing out the attributes.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.Attributes
Attribute attributes
Attribute attribute
attribute:SetName("src")
attribute:SetValue("http://www.google.com")
attributes:Add(attribute)</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/WebGenerator.php">Libraries.Web.WebGenerator</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Web.Attribute">public action Add(Libraries.Web.Attribute attribute)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Web.Attribute"><p>This action adds an Attribute to the object. If an attribute already exists for a given key, that attribute is replaced with the current one.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="attribute"><strong>Libraries.Web.Attribute</strong> <em>attribute</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="attribute">The attribute to add or replace.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Web.Attribute"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attributes
use Libraries.Web.Attribute
Attributes attributes
Attribute attribute
attribute:SetName("src")
attribute:SetValue("http://www.google.com")
attributes:Add(attribute)</pre>

</div><div class="action">
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAttribute:text">public action GetAttribute(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAttribute:text"><p>This action returns an attribute. If no attribute exists for a given key, this action returns undefined.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The name value in this case is described in more
        detail in the documentation for this class's Add actions.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAttribute:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attributes
use Libraries.Web.Attribute
Attributes attributes
//as we have not set any attribute, this would
//return undefined
Attribute attribute = attributes:GetAttribute("src")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action returns an iterator of all attributes stored in this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attributes
use Libraries.Web.Attribute
use Libraries.Containers.Blueprints.Iterator
Attributes attributes
Attribute attribute
attribute:SetName("src")
attribute:SetValue("http://www.google.com")
attributes:Add(attribute)
//output out all of the attributes
Iterator&lt;Attribute&gt; it =  attributes:GetIterator()
repeat while it:HasNext()
   Attribute at = it:Next()
   output at:Generate()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action returns the number of attributes that are currently stored in this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attributes
Attributes attributes
//this would return zero
integer num = attributes:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasAttribute:text">public action HasAttribute(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasAttribute:text"><p>This action returns whether or not an attribute exists for the key passed in as a parameter.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The key value in this case is described in more
        detail in the documentation for this class's Add actions.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HasAttribute:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attributes
Attributes attributes
//this would return false
boolean exists = attributes:HasAttribute("src")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:text">public action Remove(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:text"><p>This action removes an attribute with a particular key. If that attribute does not exist, then this action returns undefined.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The name of the attribute that should be
        removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Attributes
use Libraries.Web.Attribute
Attributes attributes
Attribute attribute
attribute:SetName("src")
attribute:SetValue("http://www.google.com")
attributes:Add(attribute)
Attribute value = attributes:Remove("src")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>