<?php $classPageTitle = "MouseAttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.MouseAttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.MouseAttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class MouseAttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The MouseAttributeAccepter class is designed as a helper to ease adding and removing mouse event attributes from particular WebTag objects. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the MouseAttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
class audio is MouseAttributeAccepter, WebGenerator
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
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnClick">public action GetOnClick()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnClick"><p>Returns the OnClick attribute. If a on click event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnClick attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnClick"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnClick()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDoubleClick">public action GetOnDoubleClick()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDoubleClick"><p>Returns the OnDoubleClick attribute. If a on double click event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDoubleClick attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDoubleClick"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDoubleClick()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDrag">public action GetOnDrag()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDrag"><p>Returns the OnDrag attribute. If a on drag event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDrag attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDrag"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDrag()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDragEnd">public action GetOnDragEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDragEnd"><p>Returns the OnDragEnd attribute. If a on drag end event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDragEnd attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDragEnd"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDragEnd()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDragEnter">public action GetOnDragEnter()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDragEnter"><p>Returns the OnDragEnter attribute. If a on drag into area event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDragEnter attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDragEnter"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDragEnter()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDragLeave">public action GetOnDragLeave()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDragLeave"><p>Returns the OnDragLeave attribute. If a on drag out of area event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDragLeave attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDragLeave"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDragLeave()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDragOver">public action GetOnDragOver()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDragOver"><p>Returns the OnDragOver attribute. If a on drag over event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDragOver attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDragOver"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDragOver()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDragStart">public action GetOnDragStart()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDragStart"><p>Returns the OnDragStart attribute. If a on drag start event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDragStart attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDragStart"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDragStart()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnDrop">public action GetOnDrop()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnDrop"><p>Returns the OnDrop attribute. If a on drop item event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnDrop attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnDrop"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnDrop()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMouseDown">public action GetOnMouseDown()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMouseDown"><p>Returns the OnMouseDown attribute. If a on mouse button down event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMouseDown attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMouseDown"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnMouseDown()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMouseMove">public action GetOnMouseMove()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMouseMove"><p>Returns the OnMouseMove attribute. If a on mouse move event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMouseMove attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMouseMove"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnMouseMove()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMouseOut">public action GetOnMouseOut()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMouseOut"><p>Returns the OnMouseOut attribute. If a on mouse out of area event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMouseOut attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMouseOut"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnMouseOut()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMouseOver">public action GetOnMouseOver()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMouseOver"><p>Returns the OnMouseOver attribute. If a on mouse over event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMouseOver attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMouseOver"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnMouseOver()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMouseUp">public action GetOnMouseUp()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMouseUp"><p>Returns the OnMouseUp attribute. If a on mouse button up event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMouseUp attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMouseUp"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnMouseUp()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMouseWheel">public action GetOnMouseWheel()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMouseWheel"><p>Returns the OnMouseWheel attribute. If a mouse wheel scroll event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMouseWheel attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMouseWheel"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnMouseWheel()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnScroll">public action GetOnScroll()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnScroll"><p>Returns the OnScroll attribute. If a on scroll event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnScroll attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnScroll"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
Attribute attribute = accept:GetOnScroll()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnClick:text">public action SetOnClick(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnClick:text"><p>Sets the OnClick attribute script when the OnClick event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnClick attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnClick:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnClick("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDoubleClick:text">public action SetOnDoubleClick(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDoubleClick:text"><p>Sets the OnDoubleClick attribute script when the OnDoubleClick event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDoubleClick attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDoubleClick:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDoubleClick("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDrag:text">public action SetOnDrag(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDrag:text"><p>Sets the OnDrag attribute script when the OnDrag event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDrag attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDrag:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDrag("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDragEnd:text">public action SetOnDragEnd(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDragEnd:text"><p>Sets the OnDragEnd attribute script when the OnDragEnd event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDragEnd attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDragEnd:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDragEnd("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDragEnter:text">public action SetOnDragEnter(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDragEnter:text"><p>Sets the OnDragEnter attribute script when the OnDragEnter event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDragEnter attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDragEnter:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDragEnter("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDragLeave:text">public action SetOnDragLeave(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDragLeave:text"><p>Sets the OnDragLeave attribute script when the OnDragLeave event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDragLeave attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDragLeave:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDragLeave("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDragOver:text">public action SetOnDragOver(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDragOver:text"><p>Sets the OnDragOver attribute script when the OnDragOver event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDragOver attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDragOver:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDragOver("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDragStart:text">public action SetOnDragStart(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDragStart:text"><p>Sets the OnDragStart attribute script when the OnDragStart event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDragStart attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDragStart:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDragStart("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnDrop:text">public action SetOnDrop(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnDrop:text"><p>Sets the OnDrop attribute script when the OnDrop event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnDrop attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnDrop:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnDrop("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMouseDown:text">public action SetOnMouseDown(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMouseDown:text"><p>Sets the OnMouseDown attribute script when the OnMouseDown event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMouseDown attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMouseDown:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnMouseDown("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMouseMove:text">public action SetOnMouseMove(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMouseMove:text"><p>Sets the OnMouseMove attribute script when the OnMouseMove event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMouseMove attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMouseMove:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnMouseMove("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMouseOut:text">public action SetOnMouseOut(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMouseOut:text"><p>Sets the OnMouseOut attribute script when the OnMouseOut event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMouseOut attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMouseOut:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnMouseOut("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMouseOver:text">public action SetOnMouseOver(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMouseOver:text"><p>Sets the OnMouseOver attribute script when the OnMouseOver event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMouseOver attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMouseOver:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnMouseOver("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMouseUp:text">public action SetOnMouseUp(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMouseUp:text"><p>Sets the OnMouseUp attribute script when the OnMouseUp event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMouseUp attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMouseUp:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnMouseUp("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMouseWheel:text">public action SetOnMouseWheel(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMouseWheel:text"><p>Sets the OnMouseWheel attribute script when the OnMouseWheel event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMouseWheel attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMouseWheel:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnMouseWheel("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnScroll:text">public action SetOnScroll(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnScroll:text"><p>Sets the OnScroll attribute script when the OnScroll event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnScroll attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnScroll:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.MouseAttributeAccepter
use Libraries.Web.Attribute
MouseAttributeAccepter accept
accept:SetOnScroll("doSomething()")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>