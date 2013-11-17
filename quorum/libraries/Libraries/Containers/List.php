<?php $classPageTitle = "List"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.List</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.List" /><h2> <span class="controllable" data-componentType="class-name">Class List&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The List class is a data structure that stores items in nodes. Each of these nodes stores an item and a reference to the next and previous node in the sequence. Because of this, the list data structure does not need to re-size itself like an Array would when the structure is filled. One downside to this is, the nodes can only be accessed through the first or last nodes. Some basic examples and explanations of Lists can be found below.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.List
class Main
   action Main
      //make the list
      List&lt;integer&gt; myList
      //add a value
      myList:Add(12)
      //get it back
      integer value = myList:GetFromFront()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Containers/Blueprints/Indexed.php">Libraries.Containers.Blueprints.Indexed</a>, <a href="../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../Libraries/Containers/Blueprints/ListBlueprint.php">Libraries.Containers.Blueprints.ListBlueprint</a>, <a href="../../Libraries/Containers/Blueprints/Sortable.php">Libraries.Containers.Blueprints.Sortable</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object">public action Add(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object"><p>This action adds a value at the end of the list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be inserted at the back or last index of the linked list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:integer:Libraries.Language.Object">public action Add(integer location,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:integer:Libraries.Language.Object"><p>This action adds a value at a location in List.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the List.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12, 0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToEnd:Libraries.Language.Object">public action AddToEnd(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToEnd:Libraries.Language.Object"><p>This action adds an item to the end of the list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToEnd:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:AddToEnd(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToFront:Libraries.Language.Object">public action AddToFront(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToFront:Libraries.Language.Object"><p>This action adds an item to the front of the list.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToFront:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:AddToFront(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>This action copies an object and returns the copy.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: Returns a copy of this object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
List&lt;integer&gt; copyList
myList:Add(12)
Object o = myList:Copy()
copyList = cast(List&lt;integer&gt;, o)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CopyToArray">public action CopyToArray()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CopyToArray"><p>This action copies the list to an array data structure.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: This returns an array of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CopyToArray"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myArray
myArray:Add(33)
myArray:Add(13)
myArray:Add(43)
myArray:Sort()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empty's the list, clearing out all of the items contained within it.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)
myList:Empty() //the item we added is now gone</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Get:integer">public action Get(integer location)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Get:integer"><p>This action gets the item at a given location in an List.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value is located at.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the given location.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Get:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)
myList:Add(13)
integer value = myList:Get(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFirstLocation:Libraries.Language.Object">public action GetFirstLocation(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFirstLocation:Libraries.Language.Object"><p>This action gets the first occurrence of the item and returns its location.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item being searched for.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the first occurrence of the item.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFirstLocation:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)
myList:Add(13)
myList:Add(12)
integer index = myList:GetFirstLocation(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromEnd">public action GetFromEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromEnd"><p>This action gets the item at the end of the list(the item will remain in the list).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromEnd"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(4)
myList:Add(13)
myList:Add(12)
integer value = myList:GetFromEnd()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromFront">public action GetFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromFront"><p>This action gets the item at the front of the list(the item will remain in the list).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromFront"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(4)
myList:Add(13)
myList:Add(12)
integer value = myList:GetFromFront()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action gets an iterator for the object and returns that iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: Returns the iterator for an object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Blueprints.Iterator
List&lt;integer&gt; myList
myList:Add(12)
myList:Add(13)
myList:Add(12)
Iterator&lt;integer&gt; listIterator = myList:GetIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLastLocation:Libraries.Language.Object">public action GetLastLocation(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLastLocation:Libraries.Language.Object"><p>This action gets the last occurrence of the item and returns its location.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item being searched for.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the last occurrence of the item.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLastLocation:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)
myList:Add(13)
myList:Add(12)
integer index = myList:GetLastLocation(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action retrieves the number of elements or nodes in a list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns an integer value representing the size of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)
integer size = myList:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Has:Libraries.Language.Object">public action Has(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Has:Libraries.Language.Object"><p>This action determines if an addable object contains a certain item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was in the Addable object and false if it was not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Has:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(12)
myList:Add(1)
boolean hasItem = myList:Has(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns a boolean value, true if the container is empty and false if it contains any items.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true when the container is empty and false when it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
if myList:IsEmpty()
   output "List is empty."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Language.Object">public action Remove(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Language.Object"><p>This action removes the first occurrence of an item that is found in the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(43)
myList:Add(13)
myList:Add(43)
boolean removed = myList:Remove(43)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAll:Libraries.Language.Object">public action RemoveAll(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAll:Libraries.Language.Object"><p>This action removes all occurrences of an item from the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAll:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(43)
myList:Add(13)
myList:Add(43)
boolean removed = myList:RemoveAll(43)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAt:integer">public action RemoveAt(integer location)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAt:integer"><p>This action removes an item from an list and returns that item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location of the item to remove.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item that was removed from the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAt:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(43)
myList:Add(13)
myList:Add(43)
integer value = myList:RemoveAt(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromEnd">public action RemoveFromEnd()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromEnd"><p>This action removes the item at the end of the list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromEnd"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(33)
myList:Add(13)
myList:Add(43)
integer removed = myList:RemoveFromEnd()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromFront">public action RemoveFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromFront"><p>This action removes the item at the front of the list.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromFront"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(33)
myList:Add(13)
myList:Add(43)
integer removed = myList:RemoveFromFront()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:integer:Libraries.Language.Object">public action Set(integer location,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:integer:Libraries.Language.Object"><p>This action sets the item at a given location in the list to a new item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(43)
myList:Add(13)
myList:Add(43)
myList:Set(22, 2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Sort">public action Sort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Sort"><p>This action sorts the values of the array. The example below sorts an array. Implementors may use any known sorting algorithm. Objects that should be sorted in a custom fashion should override the default Compare action in Libraries.Language.Object.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Sort"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
List&lt;integer&gt; myList
myList:Add(33)
myList:Add(13)
myList:Add(43)
myList:Sort()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>