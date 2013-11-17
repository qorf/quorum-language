<?php $classPageTitle = "Connectable"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.Connectable</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.Connectable" /><h2> <span class="controllable" data-componentType="class-name">Class Connectable</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
This is a parent class representing the types of objects, usually sensors that are able to be connected to the robot. This class has only two blueprint methods, for getting and setting a port.</span></p>
<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPort">public blueprint action GetPort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPort"><p>Sets what port the connectable can connect to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: This returns which port the item is connected to.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPort:integer">public blueprint action SetPort(integer port)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPort:integer"><p>Sets what port the connectable can connect to.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="port"><strong>integer</strong> <em>port</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="port">This is the port that can be connected to.</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>