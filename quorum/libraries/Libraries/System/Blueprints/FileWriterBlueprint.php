<?php $classPageTitle = "FileWriterBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.Blueprints.FileWriterBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.System.Blueprints.FileWriterBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class FileWriterBlueprint</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from FileWriterBlueprint provides a standard interface for writing files in sequential order.  See the FileWriter class for the standard Quorum implementation of this blueprint.</span></p>
<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public blueprint action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p></p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForWrite:Libraries.System.File">public blueprint action OpenForWrite(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForWrite:Libraries.System.File"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForWriteAppend:Libraries.System.File">public blueprint action OpenForWriteAppend(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForWriteAppend:Libraries.System.File"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="PushToDisk">public blueprint action PushToDisk()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="PushToDisk"><p></p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Write:text">public blueprint action Write(text textToWrite)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Write:text"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite"></span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="WriteLine:text">public blueprint action WriteLine(text textToWrite)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="WriteLine:text"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="textToWrite"><strong>text</strong> <em>textToWrite</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="textToWrite"></span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>