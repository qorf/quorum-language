<?php $classPageTitle = "FileRandomAccessBlueprint"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.Blueprints.FileRandomAccessBlueprint</h1>
<input type="hidden" id="classkey" value="Libraries.System.Blueprints.FileRandomAccessBlueprint" /><h2> <span class="controllable" data-componentType="class-name">Class FileRandomAccessBlueprint</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from FileRandomAccessBlueprint provides a standard interface for reading and writing files in a "random access" order. In this context, "random access" implies that the reading and writing does not have to occur in a sequential order. It is possible to, for example, write a character to the beginning of a file, and then jump to the end and read the last character, skipping all of the characters in-between. Random access is commonly used for manipulating binary data.  See the FileRandomAccess class for the standard Quorum implementation of this blueprint.</span></p>
<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Close">public blueprint action Close()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Close"><p></p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPosition">public blueprint action GetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPosition"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GoToBeginning">public blueprint action GoToBeginning()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GoToBeginning"><p></p></span>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsAtEndOfFile">public blueprint action IsAtEndOfFile()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsAtEndOfFile"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="OpenForRandomAccess:Libraries.System.File">public blueprint action OpenForRandomAccess(Libraries.System.File file)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="OpenForRandomAccess:Libraries.System.File"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>Libraries.System.File</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file"></span></li>

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
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPosition:number">public blueprint action SetPosition(number position)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPosition:number"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>number</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position"></span></li>

</ul>
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