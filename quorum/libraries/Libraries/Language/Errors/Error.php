<?php $classPageTitle = "Error"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Errors.Error</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Errors.Error" /><h2> <span class="controllable" data-componentType="class-name">Class Error</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Error class is a general error or exception that has been thrown. All errors inherit from Error, which means error can be used as a catch all error used by the exception handling system.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Language.Errors.Error
class Main
   action Main
      check
         integer i = cast(integer, "1.4")
      detect e is Error
         output "Error detected: " + e:GetErrorMessage()
      end
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetErrorMessage">public action GetErrorMessage()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetErrorMessage"><p>This action gets the error message of an object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The errors message.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetErrorMessage"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Errors.Error
Error error
text message = error:GetErrorMessage()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetStackTrace">public action GetStackTrace()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetStackTrace"><p>This action gets the stack trace of an object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: The errors stack trace.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetStackTrace"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Language.Errors.Error
use Libraries.System.StackTraceItem
Array&lt;StackTraceItem&gt; callStack
Error error
callStack = error:GetStackTrace()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="PrintStackTrace">public action PrintStackTrace()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="PrintStackTrace"><p>This action prints the errors stack trace to the sodbeans output window.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="PrintStackTrace"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Errors.Error
Error error
error:PrintStackTrace()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetErrorMessage:text">public action SetErrorMessage(text message)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetErrorMessage:text"><p>This action sets the error message for the error.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="message"><strong>text</strong> <em>message</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="message">The error message.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetErrorMessage:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Errors.Error
Error error
error:SetErrorMessage("An error has occurred.")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetStackTrace:Libraries.Containers.Array">public action SetStackTrace(Libraries.Containers.Array trace)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetStackTrace:Libraries.Containers.Array"><p>This action sets the stack trace of an error.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="trace"><strong>Libraries.Containers.Array</strong> <em>trace</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="trace">The generated stack trace.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetStackTrace:Libraries.Containers.Array"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Array
use Libraries.Language.Errors.Error
use Libraries.System.StackTraceItem
Array&lt;StackTraceItem&gt; callStack
Error error
error:SetStackTrace(callStack)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>