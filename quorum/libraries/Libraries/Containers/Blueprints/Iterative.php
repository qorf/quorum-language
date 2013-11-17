<?php $classPageTitle = "Iterative"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Iterative</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Iterative" /><h2> <span class="controllable" data-componentType="class-name">Class Iterative&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Iterative class provides a basic blueprint for accessing an iterator.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.Iterative
class MyClass&lt;Type&gt; is Iterative&lt;Type&gt;
   action GetIterator returns Iterator&lt;Type&gt;
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetIterator">public blueprint action GetIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetIterator"><p>This action gets an iterator for the object and returns that iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: Returns the iterator for an object.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>