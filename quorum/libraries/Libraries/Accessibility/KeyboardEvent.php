<?php $classPageTitle = "KeyboardEvent"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.KeyboardEvent</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.KeyboardEvent" /><h2> <span class="controllable" data-componentType="class-name">Class KeyboardEvent</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The KeyboardEvent class is used to represent an event that happened on the keyboard. It has three components (category, act, and key) to describe the event. These components are typically used as described in the Legam Screen Reader XML documentation.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">KeyboardEvent kevent
kevent:SetCategory("Keyboard")
kevent:SetAction("KeyPress")
kevent:SetKey("Alt")
say(kevent:GetDescription())</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Accessibility/AccessibilityEvent.php">Libraries.Accessibility.AccessibilityEvent</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddChild:Libraries.Accessibility.AccessibleChild">public action AddChild(Libraries.Accessibility.AccessibleChild newChild)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddChild:Libraries.Accessibility.AccessibleChild"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newChild"><strong>Libraries.Accessibility.AccessibleChild</strong> <em>newChild</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newChild"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAction">public action GetAction()</span></h3>

	<p>This action implements the inherited GetName action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetAction"><p>This action gets the act of the keyboard event. The acts are used to define what keyboard event happened (e.g. KeyPress, KeyRelease).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the act is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAction"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
text act = event:GetAction()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCategory">public action GetCategory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCategory"><p>This action gets the category of the keyboard event. The category for keyboard events should be "Keyboard".</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the category is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCategory"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
text category = event:GetCategory()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChild:integer">public action GetChild(integer index)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChild:integer"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Accessibility.AccessibleChild</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChildCount">public action GetChildCount()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChildCount"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDescription">public action GetDescription()</span></h3>

	<p>This action implements the inherited GetDescription action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetDescription"><p>This action gets a string to describe the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the text of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDescription"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
text result = event:GetDescription()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKey">public action GetKey()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKey"><p>This action gets the key of the keyboard event. The keys are used to define what button on the keyboard was used (e.g. A, Shift, F5...).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the key is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKey"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
text key = event:GetKey()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyboardShortcut">public action GetKeyboardShortcut()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyboardShortcut"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMoreActionInformation">public action GetMoreActionInformation()</span></h3>

	<p>This action implements the inherited GetMoreActionInformation action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetMoreActionInformation"><p>There is no further detail available for keyboard events, so this returns an empty text object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the text of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMoreActionInformation"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
text result = event:GetMoreActionInformation()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<p>This action implements the inherited GetName action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>For a keyboard event this returns the name of the key that was pressed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the text of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
text result = event:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAction:text">public action SetAction(text newAct)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAction:text"><p>This action sets the act of the keyboard event. The acts are used to define what keyboard event happened (e.g. KeyPress, KeyRelease).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newAct"><strong>text</strong> <em>newAct</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newAct"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAction:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
event:SetAction("KeyPress")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCategory:text">public action SetCategory(text newCategory)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCategory:text"><p>This action sets the category of the keyboard event. The category for mouse events should be "Keyboard".</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newCategory"><strong>text</strong> <em>newCategory</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newCategory"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCategory:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
event:SetCategory("Keyboard")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetKey:text">public action SetKey(text newKey)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetKey:text"><p>This action sets the key of the keyboard event. The keys are used to define what button on the keyboard was used (e.g. A, Shift, F5...).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newKey"><strong>text</strong> <em>newKey</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newKey"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetKey:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
KeyboardEvent event
event:SetKey("Alt")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>