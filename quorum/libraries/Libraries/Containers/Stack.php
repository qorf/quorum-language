<?php $classPageTitle = "Stack"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Stack</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Stack" /><h2> <span class="controllable" data-componentType="class-name">Class Stack&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Stack class is a data structure that stores items as if you were "stacking" them. It adds items to the top of the Stack, and when an item is requested to be removed, the top item is pulled from the Stack (Last In First Out). The Stack class is similar to the List class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Stack
class Main
   action Main
      //make the queue
      Stack&lt;integer&gt; myStack
      //add a value
      myStack:Push(12)
      myStack:Push(13)
      myStack:Push(14)
      //remove the top item (14)
      integer value = myStack:Pop()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Addable.php">Libraries.Containers.Blueprints.Addable</a>, <a href="../../Libraries/Containers/Blueprints/Container.php">Libraries.Containers.Blueprints.Container</a>, <a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Containers/Blueprints/Iterative.php">Libraries.Containers.Blueprints.Iterative</a>, <a href="../../Libraries/Containers/Blueprints/StackBlueprint.php">Libraries.Containers.Blueprints.StackBlueprint</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Language.Object">public action Add(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Language.Object"><p>This action adds a value at the end of the stack.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be added to the top of the stack.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Add(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>This action copies an object and returns the copy.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: Returns a copy of this object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
Stack&lt;integer&gt; copyStack
myStack:Add(12)
Object o = myStack:Copy()
copyStack = cast(Stack&lt;integer&gt;, o)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empty's the stack, clearing out all of the items contained within it.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Add(12)
myStack:Empty() //the item we added is now gone</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action gets an iterator for the object and returns that iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: Returns the iterator for an object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
use Libraries.Containers.Blueprints.Iterator
Stack&lt;integer&gt; myStack
myStack:Add(12)
myStack:Add(13)
myStack:Add(12)
Iterator&lt;integer&gt; StackIterator = myStack:GetIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action retrieves the number of elements or nodes in a stack.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns an integer value representing the size of the stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Add(12)
integer size = myStack:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Has:Libraries.Language.Object">public action Has(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Has:Libraries.Language.Object"><p>This action determines if an addable object contains a certain item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was in the Addable object and false if it was not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Has:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Add(12)
myStack:Add(1)
boolean hasItem = myStack:Has(12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns a boolean value, true if the container is empty and false if it contains any items.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true when the container is empty and false when it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
if(myStack:IsEmpty())
output "The stack is empty."
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Peek">public action Peek()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Peek"><p>This action gets the item at the top of the stack(the item will remain in the stack).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the top of the stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Peek"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Push(1)
myStack:Push(2)
integer result = myStack:Pop()
integer topResult = myStack:Peek()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Pop">public action Pop()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Pop"><p>This action removes the item at the top of the stack.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the top of the stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Pop"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Push(1)
myStack:Push(2)
integer result = myStack:Pop()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Push:Libraries.Language.Object">public action Push(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Push:Libraries.Language.Object"><p>This action adds an item to the top of the stack.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to the top of the stack.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Push:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Push(1)
myStack:Push(2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Language.Object">public action Remove(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Language.Object"><p>This action removes the first occurrence of an item that is found in the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Add(43)
myStack:Add(13)
myStack:Add(43)
boolean removed = myStack:Remove(43)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAll:Libraries.Language.Object">public action RemoveAll(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAll:Libraries.Language.Object"><p>This action removes all occurrences of an item from the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAll:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Stack
Stack&lt;integer&gt; myStack
myStack:Add(43)
myStack:Add(13)
myStack:Add(43)
boolean removed = myStack:RemoveAll(43)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>