<?php $classPageTitle = "KeyedAddable"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.KeyedAddable</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.KeyedAddable" /><h2> <span class="controllable" data-componentType="class-name">Class KeyedAddable&lt;Key,Value&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the KeyedAddable class provides a basic blueprint for adding and removing from an KeyedAddable object.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.KeyedAddable
class MyHashTable&lt;Key, Value&gt; is KeyedAddable&lt;Key, Value&gt;
   action Add(Key key, Value value)
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object:Libraries.Language.Object">public blueprint action Add(Key key,Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object:Libraries.Language.Object"><p>This action adds an item to the KeyedAddable object, given the key-value pair.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key used to access the value.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be stored.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasKey:Libraries.Language.Object">public blueprint action HasKey(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasKey:Libraries.Language.Object"><p>This action determines if the KeyedAddable object contains a certain key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key to find.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the key is in the KeyedAddable object and false
        if it is not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasValue:Libraries.Language.Object">public blueprint action HasValue(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasValue:Libraries.Language.Object"><p>This action determines if the KeyedAddable object contains a certain value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to find.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the value is in the KeyedAddable object and false
        if it is not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAllKeys:Libraries.Language.Object">public blueprint action RemoveAllKeys(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAllKeys:Libraries.Language.Object"><p>This action removes a key-value pair given a key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key of the key-value pair to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if any key-value pairs were removed and false 
        if none were removed.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAllValues:Libraries.Language.Object">public blueprint action RemoveAllValues(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAllValues:Libraries.Language.Object"><p>This action removes all instances of a key-value pair given a key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if any key-value pairs were removed and false
        if none were removed.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveKey:Libraries.Language.Object">public blueprint action RemoveKey(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveKey:Libraries.Language.Object"><p>This action removes a key-value pair given a key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key of the key-value pair to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Value</strong>: The value that was removed.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveValue:Libraries.Language.Object">public blueprint action RemoveValue(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveValue:Libraries.Language.Object"><p>This action removes a key-value pair given a value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value of the key-value pair to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Key</strong>: The key that was removed.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>