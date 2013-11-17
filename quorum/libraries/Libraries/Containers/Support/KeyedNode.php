<?php $classPageTitle = "KeyedNode"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Support.KeyedNode</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Support.KeyedNode" /><h2> <span class="controllable" data-componentType="class-name">Class KeyedNode&lt;Key,Value&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The KeyedNode class is a support class for keyed data structures such as the Tree.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Support.KeyedNode
class Main
   action main
      KeyedNode&lt;text, integer&gt; node
      node:Set("Melissa", 29)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>Key</strong> <em>key</em></li>
<li class = "package_alternate" ><strong>Libraries.Containers.Support.KeyedNode</strong>&lt;Key,Value&gt; <em>next</em></li>
<li class = "package_standard" ><strong>Value</strong> <em>value</em></li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKey">public action GetKey()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKey"><p>This action gets the key stored in the keyed node.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Key</strong>: The key stored in the keyed node.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKey"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.KeyedNode
KeyedNode&lt;text, integer&gt; node
text k = node:GetKey()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNext">public action GetNext()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNext"><p>This action gets the next node.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Support.KeyedNode</strong>: The next node of type KeyedNode<Key, Value></li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNext"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.KeyedNode
KeyedNode&lt;text, integer&gt; node
node:GetNext()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action gets the value stored in the keyed node.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Value</strong>: The value stored in the keyed node.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.KeyedNode
KeyedNode&lt;text, integer&gt; node
integer val = node:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:Libraries.Language.Object">public action Set(Key key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:Libraries.Language.Object"><p>This action sets the key in the keyed node.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key to be stored.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.KeyedNode
KeyedNode&lt;text, integer&gt; node
node:Set("Melissa")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:Libraries.Language.Object:Libraries.Language.Object">public action Set(Key key,Value value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:Libraries.Language.Object:Libraries.Language.Object"><p>This action sets the key-value pair in the keyed node.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>Key</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">The key to be stored.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Value</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to be stored.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:Libraries.Language.Object:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Support.KeyedNode
KeyedNode&lt;text, integer&gt; node
node:Set("Melissa", 29)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>