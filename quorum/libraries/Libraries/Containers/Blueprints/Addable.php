<?php $classPageTitle = "Addable"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Addable</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Addable" /><h2> <span class="controllable" data-componentType="class-name">Class Addable&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Addable class provides a basic blueprint for adding and removing something from an object.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.Addable
class MyClass is Addable
   action Add(Type value)
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object">public blueprint action Add(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object"><p>This action adds a value to an object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be inserted.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Has:Libraries.Language.Object">public blueprint action Has(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Has:Libraries.Language.Object"><p>This action determines if an addable object contains a certain item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was in the Addable object and false if it was not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Language.Object">public blueprint action Remove(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Language.Object"><p>This action removes the first occurrence of an item that is found in the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAll:Libraries.Language.Object">public blueprint action RemoveAll(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAll:Libraries.Language.Object"><p>This action removes all occurrences of an item from the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>