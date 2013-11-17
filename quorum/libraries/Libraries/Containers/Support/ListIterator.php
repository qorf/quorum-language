<?php $classPageTitle = "ListIterator"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Support.ListIterator</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Support.ListIterator" /><h2> <span class="controllable" data-componentType="class-name">Class ListIterator&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The ListIterator class for Lists, enables iteration for the list data structure.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Blueprints.ListIterator
class Main
   action main
      List&lt;integer&gt; myList
      ListIterator&lt;List&lt;integer&gt;&gt; listIt = myList:GetIterator()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Iterator.php">Libraries.Containers.Blueprints.Iterator</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCurrent">public action GetCurrent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCurrent"><p>This action gets the current item in the iteration without continuing the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The current item in the iteration.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCurrent"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Blueprints.Iterator
List&lt;integer&gt; myList
Iterator&lt;integer&gt; listIterator = myList:GetIterator()
integer item = listIterator:GetCurrent()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetList">public action GetList()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetList"><p>This action gets the list that the iterator has.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.List</strong>: The list.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetList"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Support.ListIterator
ListIterator&lt;List&lt;integer&gt;&gt; listIterator
listIterator:GetList()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasNext">public action HasNext()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasNext"><p>This action determines if there is a next item in the iteration. It returns true if there is a next and false if there is not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if there is a next in the iteration and false
        if there is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HasNext"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Blueprints.Iterator
List&lt;integer&gt; myList
Iterator&lt;integer&gt; listIterator = myList:GetIterator()

if listIterator:HasNext()
   listIterator:Next()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Next">public action Next()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Next"><p>This action get the next item in the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The next item in the iteration.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Next"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Blueprints.Iterator
List&lt;integer&gt; myList
Iterator&lt;integer&gt; listIterator = myList:GetIterator()
integer item = listIterator:Next()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Rewind">public action Rewind()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Rewind"><p>This action starts the iteration over from the beginning.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Rewind"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Blueprints.Iterator
List&lt;integer&gt; myList
Iterator&lt;integer&gt; listIterator = myList:GetIterator()

if listIterator:HasNext()
   listIterator:Next()
end
listIterator:Rewind()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:Libraries.Containers.List:Libraries.Containers.Support.ListNode:Libraries.Containers.Support.ListNode">public action Set(Libraries.Containers.List newList,Libraries.Containers.Support.ListNode newHead,Libraries.Containers.Support.ListNode newTail)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:Libraries.Containers.List:Libraries.Containers.Support.ListNode:Libraries.Containers.Support.ListNode"><p>This action sets the list parameters up for the iterator.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newList"><strong>Libraries.Containers.List</strong> <em>newList</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newList"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newHead"><strong>Libraries.Containers.Support.ListNode</strong> <em>newHead</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newHead"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="newTail"><strong>Libraries.Containers.Support.ListNode</strong> <em>newTail</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="newTail"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:Libraries.Containers.List:Libraries.Containers.Support.ListNode:Libraries.Containers.Support.ListNode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.List
use Libraries.Containers.Support.ListIterator
use Libraries.Containers.Support.ListNode
List&lt;integer&gt; myList
ListNode&lt;integer&gt; head
ListNode&lt;integer&gt; tail
ListIterator&lt;List&lt;integer&gt;&gt; listIterator
listIterator:Set(myList, head, tail)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:Libraries.Language.Object">public action Set(Type t)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:Libraries.Language.Object"><p>This action sets the value of the current point in the list. Generally, this action should not be used outside of the Libraries.Containers.List class.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="t"><strong>Type</strong> <em>t</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="t"></span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>