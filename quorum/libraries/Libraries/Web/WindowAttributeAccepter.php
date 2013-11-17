<?php $classPageTitle = "WindowAttributeAccepter"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.WindowAttributeAccepter</h1>
<input type="hidden" id="classkey" value="Libraries.Web.WindowAttributeAccepter" /><h2> <span class="controllable" data-componentType="class-name">Class WindowAttributeAccepter</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The WindowAttributeAccepter class is designed as a helper to ease adding and removing window event attributes from particular WebTag objects. While there is no harm in creating an object of this type, it is used most commonly by sub-classes that need to use attributes. The example for this class shows how to subclass the WindowAttributeAccepter class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
class body is WindowAttributeAccepter, WebGenerator
   action Generate returns text
      text result = "&lt;body "
      result = result + GenerateAttributes()
      result = result + "&gt;
      "
      result = result + me:GenerateNestedTags()
      result = result + "&lt;/body&gt;"
      return result
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnAHasChange">public action GetOnAHasChange()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnAHasChange"><p>Returns the OnAHasChange attribute. If a window change event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnAHasChange attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnAHasChange"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnAHasChange()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnAfterPrint">public action GetOnAfterPrint()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnAfterPrint"><p>Returns the OnAfterPrint attribute. After a output event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnAfterPrint attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnAfterPrint"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnAfterPrint()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnBeforePrint">public action GetOnBeforePrint()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnBeforePrint"><p>Returns the OnBeforePrint attribute. Before a output event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnBeforePrint attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnBeforePrint"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnBeforePrint()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnError">public action GetOnError()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnError"><p>Returns the OnError attribute. When an error event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnError attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnError"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnError()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnLoad">public action GetOnLoad()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnLoad"><p>Returns the OnLoad attribute. If a load event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnLoad attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnLoad"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnLoad()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnMessage">public action GetOnMessage()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnMessage"><p>Returns the OnMessage attribute. If a message event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnMessage attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnMessage"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnMessage()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnOffline">public action GetOnOffline()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnOffline"><p>Returns the OnOffline attribute. If a window offline event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnOffline attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnOffline"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnOffline()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnOnline">public action GetOnOnline()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnOnline"><p>Returns the OnOnline attribute. If an online event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnOnline attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnOnline"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnOnline()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnPageHide">public action GetOnPageHide()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnPageHide"><p>Returns the OnPageHide attribute. When a page is hidden event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnPageHide attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnPageHide"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnPageHide()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnPageShow">public action GetOnPageShow()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnPageShow"><p>Returns the OnPageShow attribute. When a page show event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnPageShow attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnPageShow"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnPageShow()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnPopState">public action GetOnPopState()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnPopState"><p>Returns the OnPopState attribute. When a windows history change event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnPopState attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnPopState"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnPopState()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnRedo">public action GetOnRedo()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnRedo"><p>Returns the OnRedo attribute. When a page redo event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnRedo attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnRedo"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnRedo()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnResize">public action GetOnResize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnResize"><p>Returns the OnResize attribute. After a window resize event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnResize attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnResize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnResize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnStorage">public action GetOnStorage()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnStorage"><p>Returns the OnStorage attribute. If a window storage area is updated on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnStorage attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnStorage"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnStorage()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnUndo">public action GetOnUndo()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnUndo"><p>Returns the OnUndo attribute. If a window undo event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnUndo attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnUndo"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnUndo()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOnUnload">public action GetOnUnload()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOnUnload"><p>Returns the OnUnload attribute. After a browser window close event occurs on a specified element a script is fired based on this attribute.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The current OnUnload attribute.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOnUnload"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
Attribute attribute = accept:GetOnUnload()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnAfterPrint:text">public action SetOnAfterPrint(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnAfterPrint:text"><p>Sets the OnAfterPrint attribute script when the OnAfterPrint event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnAfterPrint attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnAfterPrint:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnAfterPrint("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnBeforePrint:text">public action SetOnBeforePrint(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnBeforePrint:text"><p>Sets the OnBeforePrint attribute script when the OnBeforePrint event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnBeforePrint attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnBeforePrint:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnBeforePrint("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnBeforeUnload:text">public action SetOnBeforeUnload(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnBeforeUnload:text"><p>Sets the OnBeforeUnload attribute script when the OnBeforeUnload event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnBeforeUnload attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnBeforeUnload:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnBeforeUnload("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnError:text">public action SetOnError(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnError:text"><p>Sets the OnError attribute script when the OnError event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnError attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnError:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnError("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnHasChange:text">public action SetOnHasChange(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnHasChange:text"><p>Sets the OnHasChange attribute script when the OnHasChange event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnHasChange attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnHasChange:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnHasChange("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnLoad:text">public action SetOnLoad(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnLoad:text"><p>Sets the OnLoad attribute script when the OnLoad event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnLoad attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnLoad:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnLoad("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnMessage:text">public action SetOnMessage(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnMessage:text"><p>Sets the OnMessage attribute script when the OnMessage event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnMessage attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnMessage:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnMessage("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnOffline:text">public action SetOnOffline(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnOffline:text"><p>Sets the OnOffline attribute script when the OnOffline event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnOffline attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnOffline:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnOffline("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnOnline:text">public action SetOnOnline(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnOnline:text"><p>Sets the OnOnline attribute script when the OnOnline event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnOnline attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnOnline:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnOnline("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnPageHide:text">public action SetOnPageHide(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnPageHide:text"><p>Sets the OnPageHide attribute script when the OnPageHide event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnPageHide attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnPageHide:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnPageHide("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnPageShow:text">public action SetOnPageShow(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnPageShow:text"><p>Sets the OnPageShow attribute script when the OnPageShow event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnPageShow attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnPageShow:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnPageShow("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnPopState:text">public action SetOnPopState(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnPopState:text"><p>Sets the OnPopState attribute script when the OnPopState event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnPopState attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnPopState:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnPopState("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnRedo:text">public action SetOnRedo(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnRedo:text"><p>Sets the OnRedo attribute script when the OnRedo event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnRedo attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnRedo:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnRedo("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnResize:text">public action SetOnResize(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnResize:text"><p>Sets the OnResize attribute script when the OnResize event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnResize attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnResize:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnResize("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnStorage:text">public action SetOnStorage(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnStorage:text"><p>Sets the OnStorage attribute script when the OnStorage event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnStorage attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnStorage:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnStorage("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnUndo:text">public action SetOnUndo(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnUndo:text"><p>Sets the OnUndo attribute script when the OnUndo event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnUndo attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnUndo:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnUndo("doSomething()")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOnUnload:text">public action SetOnUnload(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOnUnload:text"><p>Sets the OnUnload attribute script when the OnUnload event is triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The current OnUnload attribute.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOnUnload:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WindowAttributeAccepter
use Libraries.Web.Attribute
WindowAttributeAccepter accept
accept:SetOnUnload("doSomething()")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>