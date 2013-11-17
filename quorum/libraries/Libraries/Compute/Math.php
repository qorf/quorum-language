<?php $classPageTitle = "Math"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Compute.Math</h1>
<input type="hidden" id="classkey" value="Libraries.Compute.Math" /><h2> <span class="controllable" data-componentType="class-name">Class Math</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Math class has a number of math actions you might find on a scientific calculator. For example, this class has Sine, Tangent, and Cosine actions, as well as a few different round actions.  Authors: Most math functions call down to Java's java.lang.math class. The plugin calls and custom methods were written by Elliot Motl and Melissa Stefik.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Compute.Math
class Main
   action main
      Math math
      number result = math:Power(2, 4)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>number</strong> <em>e</em></li>
<li class = "package_alternate" ><strong>number</strong> <em>pi</em></li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AbsoluteValue:integer">public action AbsoluteValue(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AbsoluteValue:integer"><p>This action calculates the absolute value of an integer and returns the result.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value that the absolute value will be calculated from.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns the absolute value of the parameter value. The type returned is integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="AbsoluteValue:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
integer absValue = math:AbsoluteValue(-2443)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AbsoluteValue:number">public action AbsoluteValue(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AbsoluteValue:number"><p>This action calculates the absolute value of a number and returns the result.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value that the absolute value will be calculated from.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the absolute value of the parameter value. The type returned is number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="AbsoluteValue:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number absValue = math:AbsoluteValue(-2443.4)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Cosine:number">public system action Cosine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Cosine:number"><p>This action calculates the cosine of a number and returns the result.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value that the cosine will be calculated from.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the cosine of the parameter value. The type returned is number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Cosine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number cosine = math:Cosine(4.32)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HyperbolicCosine:number">public system action HyperbolicCosine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HyperbolicCosine:number"><p>This action calculates the hyperbolic cosine of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the hyperbolic cosine of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HyperbolicCosine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:HyperbolicCosine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HyperbolicSine:number">public system action HyperbolicSine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HyperbolicSine:number"><p>This action calculates the hyperbolic sine of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the hyperbolic sine of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HyperbolicSine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:HyperbolicSine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HyperbolicTangent:number">public system action HyperbolicTangent(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HyperbolicTangent:number"><p>This action calculates the hyperbolic tangent of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the hyperbolic tangent of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HyperbolicTangent:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:HyperbolicTangent(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="InverseCosine:number">public system action InverseCosine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="InverseCosine:number"><p>This action calculates the inverse cosine of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the inverse cosine of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="InverseCosine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:InverseCosine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="InverseHyperbolicCosine:number">public system action InverseHyperbolicCosine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="InverseHyperbolicCosine:number"><p>This action calculates the inverse hyperbolic cosine of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the inverse hyperbolic cosine of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="InverseHyperbolicCosine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:InverseHyperbolicCosine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="InverseHyperbolicSine:number">public system action InverseHyperbolicSine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="InverseHyperbolicSine:number"><p>This action calculates the inverse hyperbolic sine of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the inverse hyperbolic sine of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="InverseHyperbolicSine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:InverseHyperbolicSine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="InverseHyperbolicTangent:number">public system action InverseHyperbolicTangent(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="InverseHyperbolicTangent:number"><p>This action calculates the inverse hyperbolic tangent of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the inverse hyperbolic tangent of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="InverseHyperbolicTangent:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:InverseHyperbolicCosine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="InverseSine:number">public system action InverseSine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="InverseSine:number"><p>This action calculates the inverse sine of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the inverse sine of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="InverseSine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:InverseSine(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="InverseTangent:number">public system action InverseTangent(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="InverseTangent:number"><p>This action calculates the inverse tangent of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the inverse tangent of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="InverseTangent:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:InverseTangent(1.3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Logarithm:number">public system action Logarithm(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Logarithm:number"><p>This action calculates the logarithm with base 10 of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the logarithm of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Logarithm:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:Logarithm(17.2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="NaturalLogarithm:number">public system action NaturalLogarithm(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="NaturalLogarithm:number"><p>This action calculates the natural logarithm with base ''e'' of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the natural logarithm of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="NaturalLogarithm:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:NaturalLogarithm(17.2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RaiseToPower:number:number">public system action RaiseToPower(number value,number power)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RaiseToPower:number:number"><p>This action raises a value to the power of some number(for example "value^power").</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to raise.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="power"><strong>number</strong> <em>power</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="power">The power.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RaiseToPower:number:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:RaiseToPower(13,2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Round:number">public system action Round(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Round:number"><p>This action rounds a number to the closest integer value. A midpoint value (5) will round up.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to round.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Round:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:Round(11.932356)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Round:number:boolean">public action Round(number value,boolean roundUp)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Round:number:boolean"><p>This action rounds a number to the nearest integer and will either round up or down when at a midpoint.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to round.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="roundUp"><strong>boolean</strong> <em>roundUp</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="roundUp">When roundUp is true rounding from a midpoint will round up. When it is false the number will be round down.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Round:number:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:Round(11.532356, true)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Round:number:integer">public action Round(number value,integer decimalPlace)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Round:number:integer"><p>This action rounds a number to the given number of decimal places.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to round.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="decimalPlace"><strong>integer</strong> <em>decimalPlace</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="decimalPlace">The number of decimal places to round to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Round:number:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:Round(11.932356, 3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Round:number:integer:boolean">public action Round(number value,integer decimalPlace,boolean roundUp)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Round:number:integer:boolean"><p>This action rounds a number to the given number of decimal places and will either round up or down when at a midpoint.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to round.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="decimalPlace"><strong>integer</strong> <em>decimalPlace</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="decimalPlace">The number of decimal places to round to.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="roundUp"><strong>boolean</strong> <em>roundUp</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="roundUp">When roundUp is true rounding from a midpoint will round up. When it is false the number will be round down.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Round:number:integer:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:Round(11.932356, 3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Sine:number">public system action Sine(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Sine:number"><p>This action calculates the sine of a number and returns the result.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value that the sine will be calculated from.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the sine of the parameter value. The type returned is number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Sine:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:Sine(11.9)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SquareRoot:number">public system action SquareRoot(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SquareRoot:number"><p>This action calculates the square root of a number value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value to take the square root of.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the number result.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="SquareRoot:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number result = math:SquareRoot(6)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Tangent:number">public system action Tangent(number value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Tangent:number"><p>This action calculates the tangent of a number and returns the result.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>number</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The value that the tangent will be calculated from.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the tangent of the parameter value. The type returned is number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Tangent:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Math
Math math
number cosine = math:Tangent(11.9)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>