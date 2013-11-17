<?php $classPageTitle = "AttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.AttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.AttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class AttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The AttributeAccepter class is designed as a helper to ease adding and removing attributes from particular WebTag objects. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the AttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.AttributeAccepter
class MyNewTag is AttributeAccepter, WebGenerator
   action Generate returns text
      text result = "&lt;myTagName "
      Attributes attributes = GetAttributes()
      attributeText = attributes:Generate()
      result = result + attributeText + "&gt;"
      result = result + me:GenerateNestedTags()
      result = result + GetDescription() + "&lt;/myTagName&gt;"
      return result
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Web.Attribute">public action Add(Libraries.Web.Attribute attribute)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Web.Attribute"><p>This action adds an attribute to this object. If an attribute with the same name as</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="attribute"><strong>Libraries.Web.Attribute</strong> <em>attribute</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="attribute">The attribute that will be stored.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Web.Attribute"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
Attribute attribute
attribute:SetName("src")
attribute:SetValue("http://www.google.com")
AttributeAccepter accept
accept:Add(attribute)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddAttribute:text:text">public action AddAttribute(text name,text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddAttribute:text:text"><p>This action adds an attribute to this object. This action is a helper action, which essentially does the same thing as the Add(Attribute) action. If an attribute with the same name as</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The name of the attribute functions as a key. For
        example, in a link, we might have a "src" and an actual address, like google.com.
        In this case, the name would be equivalent to the src.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">As an example of link, imagine we have a value of
         "src" and an actual address, like google.com.  In this case, the value 
        would be equivalent to google.com.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddAttribute:text:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GenerateAttributes">public action GenerateAttributes()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GenerateAttributes"><p>This action returns a text description of all attributes stored.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GenerateAttributes"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")
text result = accept:GenerateAttributes()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAttribute:text">public action GetAttribute(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAttribute:text"><p>This action returns an attribute from this object's Attributes object. If no attribute exists for a given key, this action returns undefined.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAttribute:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
//as we have not set any attribute, this would
//return undefined
Attribute attribute = accept:GetAttribute("src")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAttributeValue:text">public action GetAttributeValue(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAttributeValue:text"><p>This action returns an attribute from this object's Attributes object. If no attribute exists for a given key, this action returns undefined.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The key value in this case is described in more
        detail in the documentation for this class's Add actions.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAttributeValue:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")
//This helper action would throw an error if "src" were not set.
text value = accept:GetAttributeValue("src")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAttributes">public action GetAttributes()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAttributes"><p>Returns a list of all attributes on the system.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attributes</strong>: The attributes class contains all of the attributes
            that have been set for this object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAttributes"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attributes
AttributeAccepter accept
Attributes attributes = accept:GetAttributes()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action returns an iterator of all attributes stored in this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
use Libraries.Containers.Blueprints.Iterator
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")
//output out all of the attributes
Iterator&lt;Attribute&gt; it =  accept:GetIterator()
repeat while it:HasNext()
   Attribute at = it:Next()
   output at:Generate()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfAttributes">public action GetNumberOfAttributes()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfAttributes"><p>This action returns the number of attributes that are currently stored in this object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfAttributes"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")
integer num = accept:GetNumberOfAttributes()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasAttribute:text">public action HasAttribute(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasAttribute:text"><p>This action returns whether or not an attribute exists for the key passed in as a parameter.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The key value in this case is described in more
        detail in the documentation for this class's Add actions.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HasAttribute:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")
//This helper action would throw an error if "src" were not set.
boolean exists = accept:HasAttribute("src")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAttribute:text">public action RemoveAttribute(text name)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAttribute:text"><p>This action removes an attribute with a particular key. If that attribute does not exist, then this action returns undefined.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="name"><strong>text</strong> <em>name</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="name">The name of the attribute that should be
        removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAttribute:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.AttributeAccepter
use Libraries.Web.Attribute
AttributeAccepter accept
accept:AddAttribute("src", "http://www.google.com")
//This helper action would throw an error if "src" were not set.
accept:RemoveAttribute("src")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>