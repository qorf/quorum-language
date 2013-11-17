<?php $classPageTitle = "Sortable"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Sortable</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Sortable" /><h2> <span class="controllable" data-componentType="class-name">Class Sortable&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Sortable class provides a basic blueprint for a sorting action.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.ArrayBlueprint
class MyType is Sortable
   action Sort()
      //implementation goes here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Sort">public blueprint action Sort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Sort"><p>This action sorts the values of the array. The example below sorts an array. Implementors may use any known sorting algorithm. Objects that should be sorted in a custom fashion should override the default Compare action in Libraries.Language.Object.</p></span>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>