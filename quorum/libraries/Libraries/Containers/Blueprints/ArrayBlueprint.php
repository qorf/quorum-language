<?php $classPageTitle = "ArrayBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.ArrayBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.ArrayBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class ArrayBlueprint&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the ArrayBlueprint class provides a basic blueprint for a array data structure. This array has a flag to indicate wither the array is dynamic or not. A dynamic array automatically resized the array when it has been filled.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.ArrayBlueprint
class MyArray&lt;Type&gt; is ArrayBlueprint&lt;Type&gt;
   action SetSize(integer size)
      //implementation goes here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../../Libraries/Containers/Blueprints/Indexed.php">Libraries.Containers.Blueprints.Indexed</a>, <a href="../../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../../Libraries/Containers/Blueprints/ListBlueprint.php">Libraries.Containers.Blueprints.ListBlueprint</a>, <a href="../../../Libraries/Containers/Blueprints/Sortable.php">Libraries.Containers.Blueprints.Sortable</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoResize">public blueprint action GetAutoResize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoResize"><p>This action returns true if the array is dynamic(resizable) or false if the array does not automatically resize.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the array is resizable and false if it is not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxSize">public blueprint action GetMaxSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxSize"><p>This action gets the number of items that can be stored in the array(max size).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public blueprint action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action gets the size of the array.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoResize:boolean">public blueprint action SetAutoResize(boolean resizable)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoResize:boolean"><p>This action changes the flag that tells the structure if it is a dynamic array or not. If it is dynamic(an array list) then resizable is true and if it is a standard array(not dynamic) then resizable is false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="resizable"><strong>boolean</strong> <em>resizable</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="resizable">The value to set the resizable flag to.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMaxSize:integer">public blueprint action SetMaxSize(integer size)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMaxSize:integer"><p>This action sets the number of items that can be stored in the array(max size). The max size can only be increased, any value that is lower will leave the array with the same max size it had.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="size"><strong>integer</strong> <em>size</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="size">The max size to set for the array.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSize:integer">public blueprint action SetSize(integer size)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSize:integer"><p>This action sets the size of the array. Changing the size of the array means any items already in the array must be copied over.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="size"><strong>integer</strong> <em>size</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="size">The size of the array.</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a> public blueprint action Add(Type value), public blueprint action Has(Type value), public blueprint action Remove(Type value), public blueprint action RemoveAll(Type value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a> public blueprint action Empty(), public blueprint action IsEmpty()</li>
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a> public blueprint action Copy()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../../Libraries/Containers/Blueprints/Indexed.php">Libraries.Containers.Blueprints.Indexed</a> public blueprint action Add(integer location,Type value), public blueprint action Get(integer location), public blueprint action GetFirstLocation(Type value), public blueprint action GetLastLocation(Type value), public blueprint action RemoveAt(integer location), public blueprint action Set(integer location,Type value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a> public blueprint action GetIterator()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../../Libraries/Containers/Blueprints/Sortable.php">Libraries.Containers.Blueprints.Sortable</a> public blueprint action Sort()</li>
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>