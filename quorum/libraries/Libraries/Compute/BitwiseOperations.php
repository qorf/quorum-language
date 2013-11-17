<?php $classPageTitle = "BitwiseOperations"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Compute.BitwiseOperations</h1>
<input type="hidden" id="classkey" value="Libraries.Compute.BitwiseOperations" /><h2> <span class="controllable" data-componentType="class-name">Class BitwiseOperations</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The BitwiseOperations class provides a way for a programmer to conduct bitwise operations on integer values. Common operations include bitwise and, bitwise or, shift operators, exclusive or, and negation.  While these operators are provided for convenience, it should be noted that conducting bitwise operations in Quorum is slightly slower than conducting similar operations in other programming languages, due to the fact that such operations are conducted as system functions here. This makes the meaning of the calls more obvious than traditional, but makes them pass through the system plugin architecture, which makes several function calls.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer left = 10
integer right = 25
integer result = ops:And(left, right)</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="And:integer:integer">public system action And(integer left,integer right)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="And:integer:integer"><p>This action provides a bitwise and operation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="left"><strong>integer</strong> <em>left</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="left"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="right"><strong>integer</strong> <em>right</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="right"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="And:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer left = 10
integer right = 25
integer result = ops:And(left, right)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ExclusiveOr:integer:integer">public system action ExclusiveOr(integer left,integer right)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ExclusiveOr:integer:integer"><p>This action provides a bitwise exclusive or operation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="left"><strong>integer</strong> <em>left</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="left"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="right"><strong>integer</strong> <em>right</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="right"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ExclusiveOr:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer left = 10
integer right = 25
integer result = ops:ExclusiveOr(left, right)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Negate:integer">public system action Negate(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Negate:integer"><p>This action provides a bitwise negate operation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Negate:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer value = 10
integer result = ops:Negate(value)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Or:integer:integer">public system action Or(integer left,integer right)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Or:integer:integer"><p>This action provides a bitwise or operation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="left"><strong>integer</strong> <em>left</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="left"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="right"><strong>integer</strong> <em>right</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="right"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Or:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer left = 10
integer right = 25
integer result = ops:Or(left, right)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ShiftLeft:integer:integer">public system action ShiftLeft(integer value,integer amount)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ShiftLeft:integer:integer"><p>This action provides a bitwise shift left operation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="amount"><strong>integer</strong> <em>amount</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="amount"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ShiftLeft:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer value = 10
integer amount = 3
integer result = ops:ShiftLeft(value, amount)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ShiftRight:integer:integer">public system action ShiftRight(integer value,integer amount)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ShiftRight:integer:integer"><p>This action provides a bitwise shift right operation. This version of the operation does not keep the sign bit on shift.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="amount"><strong>integer</strong> <em>amount</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="amount"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ShiftRight:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer value = 10
integer amount = 3
integer result = ops:ShiftRight(value, amount)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ShiftRightKeepSign:integer:integer">public system action ShiftRightKeepSign(integer value,integer amount)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ShiftRightKeepSign:integer:integer"><p>This action provides a bitwise shift right operation. This version does keep the sign bit on shift.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="amount"><strong>integer</strong> <em>amount</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="amount"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ShiftRightKeepSign:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.BitwiseOperations
BitwiseOperations ops
integer value = 10
integer amount = 3
integer result = ops:ShiftRightKeepSign(value, amount)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>