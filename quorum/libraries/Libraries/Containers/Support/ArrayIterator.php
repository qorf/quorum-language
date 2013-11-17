<?php $classPageTitle = "ArrayIterator"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Support.ArrayIterator</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Support.ArrayIterator" /><h2> <span class="controllable" data-componentType="class-name">Class ArrayIterator&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The ArrayIterator class for Arrays, enables iteration for the array data structure. Generally, there is no need to use this class directly, as most users will only need an instance of the parent, the Iterator class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.ArrayIterator
class Main
   action main
      Array&lt;integer&gt; myArray
      ArrayIterator&lt;Array&lt;integer&gt;&gt; arrayIt = myArray:GetIterator()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Iterator.php">Libraries.Containers.Blueprints.Iterator</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetArray">public action GetArray()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetArray"><p>This action gets the array that the iterator has.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: The array.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetArray"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
use Libraries.Containers.Support.ArrayIterator
Array&lt;integer&gt; myArray
ArrayIterator&lt;integer&gt; arrayIterator = cast(ArrayIterator, myArray:GetIterator())
Array&lt;integer&gt; array = arrayIterator:GetArray()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCurrent">public action GetCurrent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCurrent"><p>This action gets the current item in the iteration without continuing the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The current item in the iteration.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCurrent"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
Array&lt;integer&gt; myArray
Iterator&lt;integer&gt; arrayIterator = myArray:GetIterator()
integer item = arrayIterator:GetCurrent()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasNext">public action HasNext()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasNext"><p>This action determines if there is a next item in the iteration. It returns true if there is a next and false if there is not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if there is a next in the iteration and false
        if there is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HasNext"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
Array&lt;Array&lt;integer&gt;&gt; myArray
Iterator&lt;Array&lt;integer&gt;&gt; arrayIterator = myArray:GetIterator()

if arrayIterator:HasNext()
   arrayIterator:Next()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Next">public action Next()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Next"><p>This action get the next item in the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The next item in the iteration.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Next"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
Array&lt;integer&gt; myArray
Iterator&lt;integer&gt; arrayIterator = myArray:GetIterator()
integer item = arrayIterator:Next()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Rewind">public action Rewind()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Rewind"><p>This action starts the iteration over from the beginning.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Rewind"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
Array&lt;Array&lt;integer&gt;&gt; myArray
Iterator&lt;Array&lt;integer&gt;&gt; arrayIterator = myArray:GetIterator()

if arrayIterator:HasNext()
   arrayIterator:Next()
end
arrayIterator:Rewind()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:Libraries.Containers.Array">public action Set(Libraries.Containers.Array newArray)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:Libraries.Containers.Array"><p>This action sets the array parameters up for the iterator.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newArray"><strong>Libraries.Containers.Array</strong> <em>newArray</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newArray"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:Libraries.Containers.Array"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
use Libraries.Containers.Support.ArrayIterator

Array&lt;integer&gt; myArray
ArrayIterator&lt;integer&gt; iterator
iterator:Set(myArray)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>