<?php $classPageTitle = "Iterator"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Iterator</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Iterator" /><h2> <span class="controllable" data-componentType="class-name">Class Iterator&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Iterator class provides a basic blueprint for iterating over an item.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.Iterator
class MyClass&lt;Type&gt; is Iterator&lt;Type&gt;
   action HasNext returns boolean
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCurrent">public blueprint action GetCurrent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCurrent"><p>This action gets the current item and does not move onto the next item to be iterated over.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: Returns the object.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasNext">public blueprint action HasNext()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasNext"><p>This action determines if there is a next item in the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if there is a next item and false if there is not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Next">public blueprint action Next()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Next"><p>This action gets the next item in the iteration and continues the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: Returns the object.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Rewind">public blueprint action Rewind()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Rewind"><p>This action starts the iteration over from the beginning.</p></span>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>