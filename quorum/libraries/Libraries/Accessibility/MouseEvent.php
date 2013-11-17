<?php $classPageTitle = "MouseEvent"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.MouseEvent</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.MouseEvent" /><h2> <span class="controllable" data-componentType="class-name">Class MouseEvent</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The MouseEvent class is used to represent an event that happened using the mouse. It has four components (category, act, position, and button) to describe the event. These components are typically used as described in the Legam Screen Reader XML documentation.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">KeyboardEvent kevent
kevent:SetCategory("Mouse")
kevent:SetAction("Click")
kevent:SetPosition(123,456)
kevent:SetButton("Left")
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

	<p>This action implements the inherited GetAction action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetAction"><p>This action gets the act of the mouse event. The acts are used to define what mouse event happened (e.g. Move, Click, Release, Wheel).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the act is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAction"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
text act = event:GetAction()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetButton">public action GetButton()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetButton"><p>This action gets the button of the mouse event. The button represents which button on the mouse was used (e.g. Left, Right, Middle, X).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of the button.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetButton"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
text button = event:GetButton()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCategory">public action GetCategory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCategory"><p>This action gets the category of the mouse event. The category for mouse events should be "Mouse".</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the category is.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCategory"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
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
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDescription"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
text result = event:GetDescription()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyboardShortcut">public action GetKeyboardShortcut()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyboardShortcut"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMoreActionInformation">public action GetMoreActionInformation()</span></h3>

	<p>This action implements the inherited GetMoreActionInformation action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetMoreActionInformation"><p>For a mouse event this returns the position on the screen of the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the text of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMoreActionInformation"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
text result = event:GetMoreActionInformation()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetName">public action GetName()</span></h3>

	<p>This action implements the inherited GetName action from the AccessibilityEvent class.</p><span class="controllable" data-componentType="action-description" data-actionkey="GetName"><p>For a mouse event this returns the name of the button that was used, if no button was used then it will return an empty string.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the text of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetName"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
text result = event:GetName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPosition">public action GetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPosition"><p>This action gets the positon of the mouse event. The position is the "x,y" location on the screen.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of the "x,y" location.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPosition"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
text position = event:GetPosition()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAction:text">public action SetAction(text newAct)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAction:text"><p>This action sets the act of the mouse event. The acts are used to define what mouse event happened (e.g. Move, Click, Release, Wheel).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newAct"><strong>text</strong> <em>newAct</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newAct"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAction:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
event:SetAction("Click")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetButton:text">public action SetButton(text newButton)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetButton:text"><p>This action sets the button of the mouse event. The button represents which button on the mouse was used (e.g. Left, Right, Middle, X).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newButton"><strong>text</strong> <em>newButton</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newButton"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetButton:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
event:SetButton("Left")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCategory:text">public action SetCategory(text newCategory)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCategory:text"><p>This action sets the category of the mouse event. The category for mouse events should be "Mouse".</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newCategory"><strong>text</strong> <em>newCategory</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newCategory"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCategory:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
event:SetCategory("Mouse")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPosition:integer:integer">public action SetPosition(integer x,integer y)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPosition:integer:integer"><p>This action sets the position of the mouse event. The position is the "x,y" location on the screen.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="x"><strong>integer</strong> <em>x</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="x"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="y"><strong>integer</strong> <em>y</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="y"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPosition:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
event:SetAction("300 , 523")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPosition:text">public action SetPosition(text newPosition)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPosition:text"><p>This action sets the position of the mouse event. The position is the "x,y" location on the screen.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newPosition"><strong>text</strong> <em>newPosition</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newPosition"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPosition:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Accessibility.all
MouseEvent event
event:SetAction("300 , 523")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>