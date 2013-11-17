<?php $classPageTitle = "SystemEvent"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.SystemEvent</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.SystemEvent" /><h2> <span class="controllable" data-componentType="class-name">Class SystemEvent</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The SystemEvent class is used to represent an event that happened on the system. It has four components (category, act, component, and component name) to describe the event. These components are typically used as described in the Legam Screen Reader XML documentation.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">KeyboardEvent kevent
kevent:SetCategory("Window")
kevent:SetAction("MoveOrResize")
kevent:SetComponent("Window")
kevent:SetComponentName("Sodbeans")
say(kevent:GetDescription())</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Accessibility/AccessibilityEvent.php">Libraries.Accessibility.AccessibilityEvent</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddChild:Libraries.Accessibility.AccessibleChild">public action AddChild(Libraries.Accessibility.AccessibleChild newChild)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddChild:Libraries.Accessibility.AccessibleChild"><p>This action adds an AccessibleChild to the event. These children represent all of the children that component that triggered the event has (e.g. the "Save" menu item is a child of the "File" menu).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newChild"><strong>Libraries.Accessibility.AccessibleChild</strong> <em>newChild</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newChild"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddChild:Libraries.Accessibility.AccessibleChild"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
AccessibleChild child
child:SetName("Save")
child:SetComponentType("MenuItem")
child:SetKeyboardShortcut("cmd + S")
event:AddChild(child)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAction">public action GetAction()</span></h3>

	<p>This action implements the inherited GetAction action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetAction"><p>This action gets the act of the system event. The acts are used to define what event happened (e.g. Minimize, MenuOpen, ElementTriggered...).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the act is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAction"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
text act = event:GetAction()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCategory">public action GetCategory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCategory"><p>This action gets the category of the system event. The categories are used to distinguish events (e.g. Focus, Window, Menu...).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the category is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCategory"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
text category = event:GetCategory()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChild:integer">public action GetChild(integer index)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChild:integer"><p>This action gets an AccessibleChild from the event. These children represent all of the children that component that triggered the event has (e.g. the "Save" menu item is a child of the "File" menu).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Accessibility.AccessibleChild</strong>: AccessibleChild at the index given.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChild:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
integer i = 0
repeat while i &lt; event:GetChildCount()
   AccessibleChild c = e:GetChild(i)
   output c:GetName()
   i = i + 1
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChildCount">public action GetChildCount()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChildCount"><p>This action gets the number of children the component that triggered the event had.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Integer to represent the number of children.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChildCount"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
integer result = event:GetChildCount()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetComponent">public action GetComponent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetComponent"><p>This action gets the component of the system event. The component is used to define what object the event happened to (e.g. Button, Window, Toolbar...).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the component is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetComponent"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
text component = event:GetComponent()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetComponentName">public action GetComponentName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetComponentName"><p>This action gets the component name of the system event. The component name is the title given to the object the event happened to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the component name is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetComponentName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
text componentName = event:GetComponentName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDescription">public action GetDescription()</span></h3>

	<p>This action implements the inherited GetDescription action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetDescription"><p>This action gets a string to describe the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: Text to describe the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDescription"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
text result = event:GetDescription()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyboardShortcut">public action GetKeyboardShortcut()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyboardShortcut"><p>This action gets a string that represents the keyboard shortcut of the component that triggered the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: Text to represent the keyboard shortcut.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKeyboardShortcut"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
text result = event:GetKeyboardShortcut()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAction:text">public action SetAction(text newAct)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAction:text"><p>This action sets the act of the system event. The acts are used to define what event happened (e.g. Minimize, MenuOpen, ElementTriggered...).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newAct"><strong>text</strong> <em>newAct</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newAct"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAction:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
event:SetAction("CreateComponent")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCategory:text">public action SetCategory(text newCategory)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCategory:text"><p>This action sets the category of the system event. The categories are used to distinguish events (e.g. Focus, Window, Menu...).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newCategory"><strong>text</strong> <em>newCategory</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newCategory"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCategory:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
event:SetCategory("Focus")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetChildCount:integer">public action SetChildCount(integer newChildCount)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetChildCount:integer"><p>This action sets the number of children the component that triggered the event had.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newChildCount"><strong>integer</strong> <em>newChildCount</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newChildCount"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetChildCount:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
event:SetChildCount(7)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetComponent:text">public action SetComponent(text newComponent)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetComponent:text"><p>This action sets the component of the system event. The component is used to define what object the event happened to (e.g. Button, Window, Toolbar...).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newComponent"><strong>text</strong> <em>newComponent</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newComponent"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetComponent:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
event:SetComponent("List")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetComponentName:text">public action SetComponentName(text newComponentName)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetComponentName:text"><p>This action sets the component name of the system event. The component name is the title given to the object the event happened to.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newComponentName"><strong>text</strong> <em>newComponentName</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newComponentName"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetComponentName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
event:SetComponent("Files")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetKeyboardShortcut:text">public action SetKeyboardShortcut(text newShortcut)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetKeyboardShortcut:text"><p>This action sets the string that represents the keyboard shortcut of the component that triggered the event.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newShortcut"><strong>text</strong> <em>newShortcut</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newShortcut"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetKeyboardShortcut:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
SystemEvent event
event:SetKeyboardShortcut("cmd + M")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>