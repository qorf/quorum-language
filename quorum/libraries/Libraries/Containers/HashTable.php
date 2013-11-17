<?php $classPageTitle = "HashTable"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.HashTable</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.HashTable" /><h2> <span class="controllable" data-componentType="class-name">Class HashTable&lt;Key,Value&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The HashTable class is a data structure that stores and allows access to items through the use of a key. In the hash table keys and values are paired. Some basic examples and explanations of Lists can be found below.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.HashTable
class Main
   action Main
      //make the hash table
      HashTable&lt;text, integer&gt; phoneBook
      //add a value(2626984) with a key(Jane)
      phoneBook:Add("Jane", 2626984)
      //get it back
      integer value = phoneBook:GetValue("Jane")
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Containers/Blueprints/HashTableBlueprint.php">Libraries.Containers.Blueprints.HashTableBlueprint</a>, <a href="../../Libraries/Containers/Blueprints/KeyedAddable.php">Libraries.Containers.Blueprints.KeyedAddable</a>, <a href="../../Libraries/Containers/Blueprints/KeyedIterative.php">Libraries.Containers.Blueprints.KeyedIterative</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object:Libraries.Language.Object">public action Add(Key key,Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object:Libraries.Language.Object"><p>This action adds an item to the hash table, given the key-value pair. The implementation of this action is identical to that of Set.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key used to access the value.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be stored.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Language.Object:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CopyToKeyArray">public action CopyToKeyArray()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CopyToKeyArray"><p>This action gets an array that contains all the keys in the hash table. This method requires iterating over all elements in the array, and as such, should be used sparing.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: The iterator of keys.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CopyToKeyArray"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
use Libraries.Containers.Blueprints.Iterator
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
Array&lt;text&gt; keysIterator = phoneBook:GetKeyArray()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CopyToValueArray">public action CopyToValueArray()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CopyToValueArray"><p>This action gets an array that contains all the values in the hash table. This method requires iterating over all elements in the array, and as such, should be used sparingly.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: The iterator of keys.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CopyToValueArray"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
use Libraries.Containers.Blueprints.Iterator
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
Array&lt;text&gt; keysIterator = phoneBook:GetKeyArray()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empties or clears out the hash table.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
phoneBook:Empty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKey:Libraries.Language.Object">public action GetKey(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKey:Libraries.Language.Object"><p>This action gets the key that matches the value. Warning: this method is inefficient, you should access items in a hash table through their keys.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value that matches up to a key.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Key</strong>: The key that matches the key-value pair.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKey:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
text key = phoneBook:GetKey(2626984)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyIterator">public action GetKeyIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyIterator"><p>This action gets an iterator that iterates over all the keys in the hash table.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: The iterator of keys.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKeyIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
use Libraries.Containers.Blueprints.Iterator
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
Iterator&lt;text&gt; keysIterator = phoneBook:GetKeyIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action gets the number of items in the hash table.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of items in the hash table .</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
integer size = phoneBook:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue:Libraries.Language.Object">public action GetValue(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue:Libraries.Language.Object"><p>This action gets a value with a given key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key that matches up to a value.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Value</strong>: The value that matches the key-value pair.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
integer value = phoneBook:GetValue("Jane")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValueIterator">public action GetValueIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValueIterator"><p>This action gets an iterator that iterates over all the values in the hash table.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: The iterator of values.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValueIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
use Libraries.Containers.Blueprints.Iterator
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
Iterator&lt;integer&gt; keysIterator = phoneBook:GetValueIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasKey:Libraries.Language.Object">public action HasKey(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasKey:Libraries.Language.Object"><p>This action determines if the hash table contains a certain key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key to find.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the key is in the hash table and false
        if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HasKey:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
phoneBook:HasKey("Jane")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasValue:Libraries.Language.Object">public action HasValue(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasValue:Libraries.Language.Object"><p>This action determines if the hash table contains a certain value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to find.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the value is in the hash table and false
        if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HasValue:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
phoneBook:HasValue(2626984)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action determines if a hash table is empty.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the hash table is empty and false if it
        contains any items.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
boolean empty = phoneBook:IsEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAllKeys:Libraries.Language.Object">public action RemoveAllKeys(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAllKeys:Libraries.Language.Object"><p>This action removes a key-value pair given a key. As the HashTable class requires unique values, this action really only removes the one value</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key of the key-value pair to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if any key-value pairs were removed and false 
        if none were removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAllKeys:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
boolean removed = phoneBook:RemoveAllKeys("Jane")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAllValues:Libraries.Language.Object">public action RemoveAllValues(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAllValues:Libraries.Language.Object"><p>This action removes all instances of a key-value pair given a key. As the HashTable class requires all values are unique, this only removes at most one value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if any key-value pairs were removed and false
        if none were removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAllValues:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
boolean removed = phoneBook:RemoveAllValues(2626984)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveKey:Libraries.Language.Object">public action RemoveKey(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveKey:Libraries.Language.Object"><p>This action removes a key-value pair given a key.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key of the key-value pair to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Value</strong>: The value that was removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveKey:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
integer value = phoneBook:RemoveKey("Jane")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveValue:Libraries.Language.Object">public action RemoveValue(Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveValue:Libraries.Language.Object"><p>This action removes a key-value pair given a value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value of the key-value pair to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Key</strong>: The key that was removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveValue:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)
text key = phoneBook:RemoveValue(2626984)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:Libraries.Language.Object:Libraries.Language.Object">public action Set(Key key,Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:Libraries.Language.Object:Libraries.Language.Object"><p>This action adds an item to the hash table, given the key-value pair. It functions identically to the add action and exists only as a convencience action, given that there are also actions named with the prefix Get.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key used to access the value.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be stored.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:Libraries.Language.Object:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.HashTable
HashTable&lt;text, integer&gt; phoneBook
phoneBook:Add("Jane", 2626984)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>