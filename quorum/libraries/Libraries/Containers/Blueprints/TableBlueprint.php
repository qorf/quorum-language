<?php $classPageTitle = "TableBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.TableBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.TableBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class TableBlueprint&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the TableBlueprint class provides a basic blueprint for a 2D array data structure. This table has a flag to indicate wither the array is dynamic or not. A dynamic table automatically resized the table when it has been filled.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.TableBlueprint
class MyTable&lt;Type&gt; is TableBlueprint&lt;Type&gt;
   action SetSize(integer rows, integer columns)
      //implementation goes here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoResize">public blueprint action GetAutoResize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoResize"><p>This action returns true if the table is dynamic(resizable) or false if the table does not automatically resize.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the table is resizable and false if it is not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoResize:boolean">public blueprint action SetAutoResize(boolean resizable)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoResize:boolean"><p>This action changes the flag that tells the structure if it is a dynamic table or not. If it is dynamic then resizable is true and if it is not then resizable is false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="resizable"><strong>boolean</strong> <em>resizable</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="resizable">The value to set the resizable flag to.</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>