<?php $classPageTitle = "Queue"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Queue</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Queue" /><h2> <span class="controllable" data-componentType="class-name">Class Queue&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Queue class is a data structure that stores items as if the items were in a line, or "Queue." The first items that go into the Queue are the first items to be removed from the Queue (First In First Out). The Queue class is similar to the List class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Queue
class Main
   action Main
      //make the Queue
      Queue&lt;integer&gt; myQueue
      //add a value
      myQueue:Add(12)
      myQueue:Add(13)
      myQueue:Add(14)
      myQueue:Add(15)
      //remove the first item (12)
      integer value = myQueue:RemoveFromFront()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../Libraries/Containers/Blueprints/QueueBlueprint.php">Libraries.Containers.Blueprints.QueueBlueprint</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object">public action Add(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object"><p>This action adds a value at the end of the queue.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be inserted at the back of the queue.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToEnd:Libraries.Language.Object">public action AddToEnd(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToEnd:Libraries.Language.Object"><p>This action adds an item to the end of the queue.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the queue.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToEnd:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:AddToEnd(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>This action copies an object and returns the copy.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: Returns a copy of this object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
Queue&lt;integer&gt; copyQueue
myQueue:Add(12)
Object o = myQueue:Copy()
copyQueue = cast(Queue&lt;integer&gt;, o)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empty's the queue, clearing out all of the items contained within it.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(12)
myQueue:Empty() //the item we added is now gone</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromFront">public action GetFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromFront"><p>This action gets the item at the front of the queue(the item will remain in the queue).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the queue.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromFront"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(4)
myQueue:Add(13)
myQueue:Add(12)
integer value = myQueue:GetFromFront()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action gets an iterator for the object and returns that iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: Returns the iterator for an object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
use Libraries.Containers.Blueprints.Iterator
Queue&lt;integer&gt; myQueue
myQueue:Add(12)
myQueue:Add(13)
myQueue:Add(12)
Iterator&lt;integer&gt; QueueIterator = myQueue:GetIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action retrieves the number of elements or nodes in a queue.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns an integer value representing the size of the queue.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(12)
integer size = myQueue:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Has:Libraries.Language.Object">public action Has(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Has:Libraries.Language.Object"><p>This action determines if an addable object contains a certain item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was in the Addable object and false if it was not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Has:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(12)
myQueue:Add(1)
boolean hasItem = myQueue:Has(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns a boolean value, true if the container is empty and false if it contains any items.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true when the container is empty and false when it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
if myQueue:IsEmpty()
   output "The Queue is empty."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Language.Object">public action Remove(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Language.Object"><p>This action removes the first occurrence of an item that is found in the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(43)
myQueue:Add(13)
myQueue:Add(43)
boolean removed = myQueue:Remove(43)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAll:Libraries.Language.Object">public action RemoveAll(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAll:Libraries.Language.Object"><p>This action removes all occurrences of an item from the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAll:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(43)
myQueue:Add(13)
myQueue:Add(43)
boolean removed = myQueue:RemoveAll(43)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromFront">public action RemoveFromFront()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromFront"><p>This action removes the item at the front of the queue.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the queue.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromFront"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Queue
Queue&lt;integer&gt; myQueue
myQueue:Add(33)
myQueue:Add(13)
myQueue:Add(43)
integer removed = myQueue:RemoveFromFront()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>