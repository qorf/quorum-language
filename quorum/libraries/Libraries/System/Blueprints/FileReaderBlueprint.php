<?php $classPageTitle = "FileReaderBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.Blueprints.FileReaderBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.System.Blueprints.FileReaderBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class FileReaderBlueprint</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from FileReaderBlueprint provides a standard interface for reading files in sequential order.  See the FileReader class for the standard Quorum implementation of this blueprint.</span></p>
<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public blueprint action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p></p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsAtEndOfFile">public blueprint action IsAtEndOfFile()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsAtEndOfFile"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForRead:Libraries.System.File">public blueprint action OpenForRead(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForRead:Libraries.System.File"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read">public blueprint action Read()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Read"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Read:integer">public blueprint action Read(integer numberOfBytes)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Read:integer"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="numberOfBytes"><strong>integer</strong> <em>numberOfBytes</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="numberOfBytes"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReadLine">public blueprint action ReadLine()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ReadLine"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ReadLines">public blueprint action ReadLines()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ReadLines"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Indexed</strong>: </li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>