<?php $classPageTitle = "StackTraceItem"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.StackTraceItem</h1>
<input type="hidden" id="classkey" value="Libraries.System.StackTraceItem" /><h2> <span class="controllable" data-componentType="class-name">Class StackTraceItem</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The StackTraceItem is a helper class that represents an item on the call stack. It is used by the Error classes to generate stack traces.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.StackTraceItem
class MyCallStack&lt;Type&gt;
   StackTraceItem item
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Equals:Libraries.Language.Object">public action Equals(Libraries.Language.Object o)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Equals:Libraries.Language.Object"><p>Determines if StackTraceItem's have equivalent values(class, method, file, and line number).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="o"><strong>Libraries.Language.Object</strong> <em>o</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="o">The object to compare the StackTraceItem to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the StackTraceItem's are equal and false if they are not equal.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Equals:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.StackTraceItem
StackTraceItem item
StackTraceItem item2
item:Equals(item2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetClassName">public action GetClassName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetClassName"><p>Get the name of the class that the activation record originates from.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The name of the class on the call stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetClassName"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.StackTraceItem
StackTraceItem item
text clazz = item:GetClassName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFileName">public action GetFileName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFileName"><p>Get the name of the file that the activation record originates from.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The name of the file on the call stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFileName"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.StackTraceItem
StackTraceItem item
text file = item:GetFileName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLineNumber">public action GetLineNumber()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLineNumber"><p>Get the line number that the activation record originates from.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The line number of the item on the call stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLineNumber"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.StackTraceItem
StackTraceItem item
integer line = item:GetLineNumber()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMethodName">public action GetMethodName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMethodName"><p>Get the name of the method that the activation record originates from.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The name of the method on the call stack.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMethodName"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.StackTraceItem
StackTraceItem item
text method = item:GetMethodName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Init:text:text:text:integer">public action Init(text clazz,text method,text file,integer line)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Init:text:text:text:integer"><p>Initialize the stack trace items' class, method, file, and line number.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="clazz"><strong>text</strong> <em>clazz</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="clazz">The name of the class on the call stack.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="method"><strong>text</strong> <em>method</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="method">The name of the method (from the activation record) on the call stack.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="file"><strong>text</strong> <em>file</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="file">The file path of the class on the call stack.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="line"><strong>integer</strong> <em>line</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="line">The line number that the activation record originates from.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Init:text:text:text:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.StackTraceItem
StackTraceItem item
item:Init("MyCallStack", "Call", "Libraries.System.CallStack", 15)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>