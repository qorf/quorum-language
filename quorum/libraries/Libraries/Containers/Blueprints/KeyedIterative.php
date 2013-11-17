<?php $classPageTitle = "KeyedIterative"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.KeyedIterative</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.KeyedIterative" /><h2> <span class="controllable" data-componentType="class-name">Class KeyedIterative&lt;Key,Value&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the KeyedIterative class provides a basic blueprint for getting iterators for keyed objects.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.KeyedIterative
class MyHashTable&lt;Key, Value&gt; is KeyedIterative&lt;Key, Value&gt;
   action GetKeyIterator returns Iterator&lt;Key&gt;
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyIterator">public blueprint action GetKeyIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyIterator"><p>This action gets an iterator that iterates over all the keys in the keyed object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: The iterator of keys.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValueIterator">public blueprint action GetValueIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValueIterator"><p>This action gets an iterator that iterates over all the values in the keyed object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: The iterator of values.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>