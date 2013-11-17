<?php $classPageTitle = "AccessibleChild"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.AccessibleChild</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.AccessibleChild" /><h2> <span class="controllable" data-componentType="class-name">Class AccessibleChild</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The AccessibleChild stores information about a child of an accessible event.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">AccessibleChild child
child:SetName("Edit")
child:SetComponentType("MenuItem")
child:SetKeyboardShortcut("e")
output child:GetName()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetComponentType">public action GetComponentType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetComponentType"><p>This action gets the component type of the child item. The component is used to define what object the event happened to (e.g. Button, Window, Toolbar...).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the component is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetComponentType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
AccessibleChild child
text componentType = child:GetComponent()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyboardShortcut">public action GetKeyboardShortcut()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyboardShortcut"><p>This action gets the keyboard shortcut of the child item.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of the keyboard shortcut of the child.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKeyboardShortcut"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
AccessibleChild child
text keyboardShortcut = child:GetKeyboardShortcut()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>This action gets the name of the child item.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of the name of the child.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
AccessibleChild child
text name = child:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetComponentType:text">public action SetComponentType(text newComponent)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetComponentType:text"><p>This action sets the component of the child item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newComponent"><strong>text</strong> <em>newComponent</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newComponent"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetComponentType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
AccessibleChild child
child:SetComponentType("MenuItem")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetKeyboardShortcut:text">public action SetKeyboardShortcut(text newKeyboardShortcut)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetKeyboardShortcut:text"><p>This action sets the keyboard shortcut of the child item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newKeyboardShortcut"><strong>text</strong> <em>newKeyboardShortcut</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newKeyboardShortcut"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetKeyboardShortcut:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
AccessibleChild child
child:SetKeyboardShortcut("Ctrl+N")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetName:text">public action SetName(text newName)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetName:text"><p>This action sets the name of the child item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newName"><strong>text</strong> <em>newName</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newName"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetName:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
AccessibleChild child
child:SetName("Close")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>