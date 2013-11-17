<?php $classPageTitle = "AccessibilityEvent"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.AccessibilityEvent</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.AccessibilityEvent" /><h2> <span class="controllable" data-componentType="class-name">Class AccessibilityEvent</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The AccessibilityEvent class is used to represent an event that happened on the system. It can be used to represent other, more specific types of events, such as mouse, keyboard, and system events. An event is typically used to describe parsed XML with the specifications in the Legam Screen Reader XML documentation.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">AccessibilityEvent kevent
say(kevent:GetDescription())</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCategory">public blueprint action GetCategory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCategory"><p>This action gets the category of the event. This will be one of the following: Focus, Keyboard, Mouse, Window, Notification, Menu, or PropertyChange.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text of what the category is.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDescription">public blueprint action GetDescription()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDescription"><p>This action should return a string to describe the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>