<?php $classPageTitle = "Container"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Container</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Container" /><h2> <span class="controllable" data-componentType="class-name">Class Container</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Containers class provides a basic blueprint for container objects.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.Container
class MyClass is Container
   action GetSize returns integer
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public blueprint action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empty's the list, clearing out all of the items contained within it.</p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public blueprint action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action retrieves the number of elements in the container.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns an integer value representing the size of the container.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public blueprint action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns a boolean value, true if the container is empty and false if it contains any items.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true when the container is empty and false when it is not.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>