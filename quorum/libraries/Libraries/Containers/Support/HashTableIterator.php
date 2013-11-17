<?php $classPageTitle = "HashTableIterator"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Support.HashTableIterator</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Support.HashTableIterator" /><h2> <span class="controllable" data-componentType="class-name">Class HashTableIterator&lt;Key,Value&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
</span></p>
<em>Inherits from</em>:
<a href="../../../Libraries/Containers/Blueprints/Iterator.php">Libraries.Containers.Blueprints.Iterator</a>, <a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetArray">public action GetArray()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetArray"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCurrent">public action GetCurrent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCurrent"><p>This action gets the current item and does not move onto the next item to be iterated over.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Support.HashNode</strong>: Returns the object.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HasNext">public action HasNext()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HasNext"><p>This action determines if there is a next item in the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if there is a next item and false if there is not.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Next">public action Next()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Next"><p>This action gets the next item in the iteration and continues the iteration.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Support.HashNode</strong>: Returns the object.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Rewind">public action Rewind()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Rewind"><p>This action starts the iteration over from the beginning.</p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetArray:Libraries.Containers.Array">public action SetArray(Libraries.Containers.Array array)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetArray:Libraries.Containers.Array"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="array"><strong>Libraries.Containers.Array</strong> <em>array</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="array"></span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>