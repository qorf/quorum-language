<?php $classPageTitle = "Copyable"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Copyable</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Copyable" /><h2> <span class="controllable" data-componentType="class-name">Class Copyable</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Copyable class provides a basic blueprint for copying a particular object.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.Copyable
class MyClass is Copyable
   action Copy returns Object
      MyClass mine
      //set whatever properties you want
      return mine
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public blueprint action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>This blueprint action copies an object and returns the copy.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: Returns a copy of this object. This copy is not guaranteed
        to be a deep copy.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>