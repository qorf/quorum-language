<?php $classPageTitle = "ListBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.ListBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.ListBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class ListBlueprint&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the ListBlueprint class provides a basic blueprint for a linked list data structure.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.ListBlueprint
class MyList&lt;Type&gt; is ListBlueprint&lt;Type&gt;
   action AddToFront(Type value)
      //implementation goes here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../../Libraries/Containers/Blueprints/Indexed.php">Libraries.Containers.Blueprints.Indexed</a>, <a href="../../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../../Libraries/Containers/Blueprints/Sortable.php">Libraries.Containers.Blueprints.Sortable</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToEnd:Libraries.Language.Object">public blueprint action AddToEnd(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToEnd:Libraries.Language.Object"><p>This action adds an item to the end of the list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the list.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToFront:Libraries.Language.Object">public blueprint action AddToFront(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToFront:Libraries.Language.Object"><p>This action adds an item to the front of the list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the list.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CopyToArray">public blueprint action CopyToArray()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CopyToArray"><p>This action copies the list to an array data structure.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: This returns an array of the list.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromEnd">public blueprint action GetFromEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromEnd"><p>This action gets the item at the end of the list(the item will remain in the list).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the list.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromFront">public blueprint action GetFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromFront"><p>This action gets the item at the front of the list(the item will remain in the list).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the list.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromEnd">public blueprint action RemoveFromEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromEnd"><p>This action removes the item at the end of the list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the list.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromFront">public blueprint action RemoveFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromFront"><p>This action removes the item at the front of the list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the list.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>