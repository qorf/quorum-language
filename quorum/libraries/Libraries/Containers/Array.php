<?php $classPageTitle = "Array"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Array</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Array" /><h2> <span class="controllable" data-componentType="class-name">Class Array&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Array class is a data structure that stores items in contiguous memory. An item is typically stored and accessed through an index or location. This location always starts at 0, this means the first item in the array is at location 0, the second is at location 1, etc. The default maximum size is set to 10, but can be changed by using the SetSize(value) method or the array will automatically make itself large when the space is needed (note: it is possible to turn the resizing off with the SetAutoResize(false) method).</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Array
class Main
   action Main
      //make the array
      Array&lt;integer&gt; myArray
      //add a value
      myArray:Add(12)
      //get it back
      integer value = myArray:Get(0)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../Libraries/Containers/Blueprints/ArrayBlueprint.php">Libraries.Containers.Blueprints.ArrayBlueprint</a>, <a href="../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Containers/Blueprints/Indexed.php">Libraries.Containers.Blueprints.Indexed</a>, <a href="../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../Libraries/Containers/Blueprints/ListBlueprint.php">Libraries.Containers.Blueprints.ListBlueprint</a>, <a href="../../Libraries/Containers/Blueprints/Sortable.php">Libraries.Containers.Blueprints.Sortable</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object">public action Add(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object"><p>This action adds a value to the end of the array.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be inserted.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:integer:Libraries.Language.Object">public action Add(integer location,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:integer:Libraries.Language.Object"><p>This action adds a value at a location in the indexed object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the indexed object.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(0, 22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToEnd:Libraries.Language.Object">public action AddToEnd(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToEnd:Libraries.Language.Object"><p>This action adds an item to the end of the array. If the max size has been reached an the array is not re-sizable an InvalidLocationError will be alerted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the array.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToEnd:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:AddToEnd(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToFront:Libraries.Language.Object">public action AddToFront(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToFront:Libraries.Language.Object"><p>This action adds an item to the front of the array at index 0. Then moves all other items down one index. If the max size is already reached and the array is not re-sizable an InvalidLocationError will be alerted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the array.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToFront:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:AddToFront(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>This action returns a deep copy of the array in question. As such, all elements are copied from one array to the other. While the array is a deep copy, the elements inside of the array are not copied.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: Returns a deep copy of the array.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">//the array class is Copyable
use Libraries.Containers.Array
Array&lt;integer&gt; array
Object o = array:Copy()
Array&lt;integer&gt; copy = cast(Array&lt;integer&gt;, o)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public system action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empty's the list, clearing out all of the items contained within it.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
myArray:Empty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Get:integer">public action Get(integer location)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Get:integer"><p>This action gets the item at a given location in an array.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value is located at.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the given location.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Get:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
integer result = myArray:Get(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoResize">public system action GetAutoResize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoResize"><p>This action returns true if the array is dynamic(resizable) or false if the array does not automatically resize.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the array is resizable and false if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutoResize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
boolean result = myArray:GetAutoResize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFirstLocation:Libraries.Language.Object">public action GetFirstLocation(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFirstLocation:Libraries.Language.Object"><p>This action gets the first occurrence of the item and returns its location. If the item was not found -1 is returned.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item being searched for.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the first occurrence of the item.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFirstLocation:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
integer location = myArray:GetFirstLocation(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromEnd">public action GetFromEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromEnd"><p>This action gets the item at the end of the array(the item will remain in the array).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the array.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromEnd"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(4)
myArray:Add(13)
myArray:Add(12)
integer value = myArray:GetFromEnd()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromFront">public action GetFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromFront"><p>This action gets the item at the front of the array(the item will remain in the array).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the array.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromFront"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(4)
myArray:Add(13)
myArray:Add(12)
integer value = myArray:GetFromFront()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action gets an iterator for the object and returns that iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: Returns the iterator for an object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
Array&lt;integer&gt; myArray
myArray:SetSize(4)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
Iterator&lt;integer&gt; it = myArray:GetIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLastLocation:Libraries.Language.Object">public action GetLastLocation(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLastLocation:Libraries.Language.Object"><p>This action gets the last occurrence of the item and returns its location. If the item was not found -1 is returned.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item being searched for.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the last occurrence of the item.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLastLocation:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
integer location = myArray:GetLastLocation(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxSize">public system action GetMaxSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxSize"><p>This action gets the number of items that can be stored in the array(max size).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaxSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
integer maxSize = myArray:GetMaxSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public system action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action gets the size of the array.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
integer size = myArray:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Has:Libraries.Language.Object">public action Has(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Has:Libraries.Language.Object"><p>This action determines if an addable object contains a certain item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was in the Addable object and false if it was not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Has:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
boolean result = myArray:Has(33)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public system action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns a boolean value, true if the container is empty and false if it contains any items.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true when the container is empty and false when it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
boolean result = myArray:IsEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Language.Object">public action Remove(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Language.Object"><p>This action removes the first occurrence of an item that is found in the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
boolean result = myArray:Remove(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAll:Libraries.Language.Object">public action RemoveAll(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAll:Libraries.Language.Object"><p>This action removes all occurrences of an item from the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAll:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
boolean result = myArray:RemoveAll(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAt:integer">public action RemoveAt(integer location)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAt:integer"><p>This action removes an item from an indexed object and returns that item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location of the item to remove.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item that was removed from the indexed object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAt:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)
myArray:Set(1, 33)
myArray:Set(2, 45)
myArray:Set(3, 22)
integer item = myArray:RemoveAt(2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromEnd">public action RemoveFromEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromEnd"><p>This action removes the item at the end of the array.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the array.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromEnd"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(33)
myArray:Add(13)
myArray:Add(43)
integer removed = myArray:RemoveFromEnd()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromFront">public action RemoveFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromFront"><p>This action removes the item at the front of the list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromFront"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(33)
myArray:Add(13)
myArray:Add(43)
integer removed = myArray:RemoveFromFront()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:integer:Libraries.Language.Object">public action Set(integer location,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:integer:Libraries.Language.Object"><p>This action sets the item at a given location in the indexed object to a new item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the indexed object.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)
myArray:Set(0, 22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoResize:boolean">public system action SetAutoResize(boolean resizable)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoResize:boolean"><p>This action changes the flag that tells the structure if it is a dynamic array or not. If it is dynamic(an array list) then resizable is true and if it is a standard array(not dynamic) then resizable is false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="resizable"><strong>boolean</strong> <em>resizable</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="resizable">The value to set the resizable flag to.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutoResize:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetAutoResize(false)
myArray:SetSize(10)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMaxSize:integer">public system action SetMaxSize(integer size)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMaxSize:integer"><p>This action sets the number of items that can be stored in the array(max size). The max size can only be increased, any value that is lower will leave the array with the same max size it had.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="size"><strong>integer</strong> <em>size</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="size">The max size to set for the array.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMaxSize:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetMaxSize(20)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSize:integer">public action SetSize(integer size)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSize:integer"><p>This action sets the size of the array and fills it with undefined items. Changing the size of the array means any items already in the array must be copied over.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="size"><strong>integer</strong> <em>size</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="size">The size of the array.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSize:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:SetSize(10)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Sort">public action Sort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Sort"><p>This action sorts the values of the array using a merge sort algorithm. It is guaranteed to execute in O(n log n).</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Sort"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
Array&lt;integer&gt; myArray
myArray:Add(33)
myArray:Add(13)
myArray:Add(43)
myArray:Sort()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Containers/Blueprints/ListBlueprint.php">Libraries.Containers.Blueprints.ListBlueprint</a> public blueprint action CopyToArray()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>