<?php $classPageTitle = "ListNode"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Support.ListNode</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Support.ListNode" /><h2> <span class="controllable" data-componentType="class-name">Class ListNode&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The ListNode class is a support class for List data structure.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Support.ListNode
class Main
   action main
      ListNode&lt;integer&gt; node
      node:Set(29)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>Libraries.Containers.Support.ListNode</strong>&lt;Type&gt; <em>next</em></li>
<li class = "package_alternate" ><strong>Libraries.Containers.Support.ListNode</strong>&lt;Type&gt; <em>previous</em></li>
<li class = "package_standard" ><strong>Type</strong> <em>value</em></li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNext">public action GetNext()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNext"><p>This action gets the next node.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Support.ListNode</strong>: The next node of type ListNode<Type></li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNext"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.ListNode
ListNode&lt;integer&gt; node
node:GetNext()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPrevious">public action GetPrevious()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPrevious"><p>This action gets the previous node.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Support.ListNode</strong>: The previous node of type ListNode<Type></li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPrevious"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.ListNode
ListNode&lt;integer&gt; node
node:GetPrevious()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action gets the value stored in the node.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The value stored in the node.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.ListNode
ListNode&lt;integer&gt; node
integer val = node:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetNext:Libraries.Containers.Support.ListNode">public action SetNext(Libraries.Containers.Support.ListNode node)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetNext:Libraries.Containers.Support.ListNode"><p>This action sets the next node.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="node"><strong>Libraries.Containers.Support.ListNode</strong> <em>node</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="node">The next node in the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetNext:Libraries.Containers.Support.ListNode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.ListNode
ListNode&lt;integer&gt; node
ListNode&lt;integer&gt; nextNode
nextNode:SetValue(30)
node:SetNext(nextNode)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPrevious:Libraries.Containers.Support.ListNode">public action SetPrevious(Libraries.Containers.Support.ListNode node)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPrevious:Libraries.Containers.Support.ListNode"><p>This action sets the previous node.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="node"><strong>Libraries.Containers.Support.ListNode</strong> <em>node</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="node">The previous node in the list.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPrevious:Libraries.Containers.Support.ListNode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.ListNode
ListNode&lt;integer&gt; node
ListNode&lt;integer&gt; prevNode
prevNode:SetValue(28)
node:SetPrevious(prevNode)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:Libraries.Language.Object">public action SetValue(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:Libraries.Language.Object"><p>This action sets the value stored in the node.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be stored by this node.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.ListNode
ListNode&lt;integer&gt; node
node:SetValue(29)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>