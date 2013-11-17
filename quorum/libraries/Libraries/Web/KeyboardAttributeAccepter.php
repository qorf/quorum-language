<?php $classPageTitle = "KeyboardAttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.KeyboardAttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.KeyboardAttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class KeyboardAttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The KeyboardAttributeAccepter class is designed as a helper to ease adding and removing keyboard event attributes from particular WebTag objects. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the KeyboardAttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
class Input is KeyboardAttributeAccepter, WebGenerator
   action Generate returns text
      text result = "&lt;input "
      Attributes attributes = parent:WebTag:GetAttributes()
      attributeText = attributes:Generate()
      result = result + attributeText + "&gt;"
      result = result + me:GenerateNestedTags()
      result = result + GetDescription() + "&lt;/input&gt;"
      return result
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnKeyDown">public action GetOnKeyDown()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnKeyDown"><p>Returns the OnKeyDown attribute. If a key down event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnKeyDown attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnKeyDown"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
use Libraries.Web.Attribute
KeyboardAttributeAccepter accept
Attribute attribute = accept:GetOnKeyDown()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnKeyPress">public action GetOnKeyPress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnKeyPress"><p>Returns the OnKeyPress attribute. If a key press event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnKeyPress attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnKeyPress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
use Libraries.Web.Attribute
KeyboardAttributeAccepter accept
Attribute attribute = accept:GetOnKeyPress()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnKeyUp">public action GetOnKeyUp()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnKeyUp"><p>Returns the OnKeyUp attribute. If a key up event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnKeyUp attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnKeyUp"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
use Libraries.Web.Attribute
KeyboardAttributeAccepter accept
Attribute attribute = accept:GetOnKeyUp()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnKeyDown:text">public action SetOnKeyDown(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnKeyDown:text"><p>Sets the OnKeyDown attribute. If a key down event occurs on a specified element a script is fired based on this attribute.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnKeyDown attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnKeyDown:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
use Libraries.Web.Attribute
KeyboardAttributeAccepter accept
accept:SetOnKeyDown("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnKeyPress:text">public action SetOnKeyPress(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnKeyPress:text"><p>Sets the OnKeyPress attribute. If a key press event occurs on a specified element a script is fired based on this attribute.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnKeyPress attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnKeyPress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
use Libraries.Web.Attribute
KeyboardAttributeAccepter accept
accept:SetOnKeyPress("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnKeyUp:text">public action SetOnKeyUp(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnKeyUp:text"><p>Sets the OnKeyUp attribute. If a key up event occurs on a specified element a script is fired based on this attribute.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnKeyUp attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnKeyUp:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.KeyboardAttributeAccepter
use Libraries.Web.Attribute
KeyboardAttributeAccepter accept
accept:SetOnKeyUp("doSomething()")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>