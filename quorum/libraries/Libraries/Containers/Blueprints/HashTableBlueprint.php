<?php $classPageTitle = "HashTableBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.HashTableBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.HashTableBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class HashTableBlueprint&lt;Key,Value&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the HashTableBlueprint class provides a basic blueprint for a hash table data structure.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.HashTableBlueprint
class MyHashTable&lt;Key, Value&gt; is HashTableBlueprint&lt;Key, Value&gt;
   action GetValue(Key key) returns Value
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../../Libraries/Containers/Blueprints/KeyedAddable.php">Libraries.Containers.Blueprints.KeyedAddable</a>, <a href="../../../Libraries/Containers/Blueprints/KeyedIterative.php">Libraries.Containers.Blueprints.KeyedIterative</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKey:Libraries.Language.Object">public blueprint action GetKey(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKey:Libraries.Language.Object"><p>This action gets the key from the hash table given a matching value. Important Note: this is highly inefficient and you should use GetValue(key) in most cases.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to access the matching key.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Key</strong>: Returns the key in the key-value pair.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue:Libraries.Language.Object">public blueprint action GetValue(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue:Libraries.Language.Object"><p>This action gets the value from the hash table given a matching key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key to access the matching value.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Value</strong>: Returns the object.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>