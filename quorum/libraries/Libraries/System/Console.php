<?php $classPageTitle = "Console"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.Console</h1>
<input type="hidden" id="classkey" value="Libraries.System.Console" /><h2> <span class="controllable" data-componentType="class-name">Class Console</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Console is a helper class that represents printing and input windows. In addition to these helper methods, the console class also allows the user to retrieve any console arguments (also called command line arguments) that were passed to the program.</span></p>
<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetConsoleArguments">public action GetConsoleArguments()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetConsoleArguments"><p>This action retrieves a copy of the console arguments that were passed to the system.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Input">public system action Input()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Input"><p>generates an input window and returns the text that was input.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Input"><em>Example Code:</em></span>
<pre class="code">text value = Input()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Input:text">public system action Input(text message)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Input:text"><p>This action generates an input window and returns the text that was input.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="message"><strong>text</strong> <em>message</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="message">The message to display in the input box.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Input:text"><em>Example Code:</em></span>
<pre class="code">text value = Input("What is your favorite color?")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Print:text">public system action Print(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Print:text"><p>Prints a message to the sodbeans output window</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The text to output to the output console.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Print:text"><em>Example Code:</em></span>
<pre class="code">Print "some message"</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>