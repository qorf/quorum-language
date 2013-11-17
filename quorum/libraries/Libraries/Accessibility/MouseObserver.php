<?php $classPageTitle = "MouseObserver"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.MouseObserver</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.MouseObserver" /><h2> <span class="controllable" data-componentType="class-name">Class MouseObserver</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The MouseObserver class provides functionality to output any mouse event from the system.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">AccessibilityManager manager
MouseObserver mObserver
manager:Add(mObserver)
manager:Start()
manager:Stop()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReceiveEvent:Libraries.Accessibility.MouseEvent">public blueprint action ReceiveEvent(Libraries.Accessibility.MouseEvent event)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ReceiveEvent:Libraries.Accessibility.MouseEvent"><p>This action is a blueprint function that receives an event from the system for the programmer to use.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="event"><strong>Libraries.Accessibility.MouseEvent</strong> <em>event</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="event">The event from the system.</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>