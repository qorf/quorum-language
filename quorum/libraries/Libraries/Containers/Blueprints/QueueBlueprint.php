<?php $classPageTitle = "QueueBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.QueueBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.QueueBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class QueueBlueprint&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the QueueBlueprint class provides a basic blueprint for a queue data structure.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.QueueBlueprint
class MyQueue&lt;Type&gt; is QueueBlueprint&lt;Type&gt;
   action AddToEnd(Type value)
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToEnd:Libraries.Language.Object">public blueprint action AddToEnd(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToEnd:Libraries.Language.Object"><p>This action adds an item to the end of the queue.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the queue.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromFront">public blueprint action GetFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromFront"><p>This action gets the item at the front of the queue(the item will remain in the queue).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the queue.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromFront">public blueprint action RemoveFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromFront"><p>This action removes the item at the front of the queue.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the queue.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>