<?php $classPageTitle = "Number"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Types.Number</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Types.Number" /><h2> <span class="controllable" data-componentType="class-name">Class Number</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Number class is the object representation of the primitive type number.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">class Main
   action Main
      number age = 15.5
      Number result = test(age)
   end
   action test(Number num) returns Number
      return num
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Compare:Libraries.Language.Object">public action Compare(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Compare:Libraries.Language.Object"><p>This action compares two object values and returns a CompareResult. The compare result is either larger if this hash code is larger than the object passed as a parameter, smaller, or equal.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The object to compare to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Support.CompareResult</strong>: The Comprare result, Smaller, Equal, or Larger.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Compare:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Support.CompareResult
Number o
Number t
CompareResult result = o:Compare(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Equals:Libraries.Language.Object">public action Equals(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Equals:Libraries.Language.Object"><p>This action determines if two objects are equal based on their values.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The to be compared.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the values are equal and false if they
        are not equal.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Equals:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">Number o
Number t
boolean result = o:Equals(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHashCode">public system action GetHashCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHashCode"><p>This action gets the hash code for an object. In this case, GetHashCode is overriden and matches the integer value of the equivalent primitive in the Java programming language.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The integer hash code of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHashCode"><em>Example Code:</em></span>
<pre class="code">Object o
integer hash = o:GetHashCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHex">public system action GetHex()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHex"><p>This action gets the hex representation of the number value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The hex representation of the number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHex"><em>Example Code:</em></span>
<pre class="code">number i = 3
text result = i:GetHex()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetInteger">public action GetInteger()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetInteger"><p>This action gets the value from the number object and casts it to an integer.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The value of the object converted to an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetInteger"><em>Example Code:</em></span>
<pre class="code">Number age
integer result = age:GetInteger()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaximumValue">public system action GetMaximumValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaximumValue"><p>This action gets the maximum number value, (2-2^-52)*2^1023.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The maximum number value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaximumValue"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
output i:GetMaximumValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMinimumValue">public system action GetMinimumValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMinimumValue"><p>This action gets the minimum number value(2^-1074).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The minimum number value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMinimumValue"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
output i:GetMinimumValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNegativeInfinityValue">public system action GetNegativeInfinityValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNegativeInfinityValue"><p>This action gets the negative infinity of a number value(0xfff0000000000000L).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Negative infinity of a number value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNegativeInfinityValue"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
output i:GetNegativeInfinityValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNotANumberValue">public system action GetNotANumberValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNotANumberValue"><p>This action gets the not-a-number representation of a number value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The Not-a-Number of a number value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNotANumberValue"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
output i:GetNotANumberValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfBits">public system action GetNumberOfBits()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfBits"><p>This action gets the number of bits representing a number value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of bits of a number value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfBits"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
output i:GetNumberOfBits()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPositiveInfinityValue">public system action GetPositiveInfinityValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPositiveInfinityValue"><p>This action gets the positive infinity number value(0x7ff0000000000000L).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The positive infinity of a number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPositiveInfinityValue"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
output i:GetPositiveInfinityValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetText">public action GetText()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetText"><p>This action gets the value from the number object and casts it to a text value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The value of the object converted to text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetText"><em>Example Code:</em></span>
<pre class="code">Number age
text result = age:GetText()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action gets the value from the number object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The value of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">Number age
number result = age:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsInfinite">public system action IsInfinite()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsInfinite"><p>This action returns true if the number is infinite.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the number is infinitely large and false if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsInfinite"><em>Example Code:</em></span>
<pre class="code">number i = 333.999999999999999999
boolean result = i:IsInfinite()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsNotANumber">public system action IsNotANumber()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsNotANumber"><p>This action returns true if the number is not a number(NaN).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if NaN.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsNotANumber"><em>Example Code:</em></span>
<pre class="code">number i = 3.5
boolean result = i:IsNotANumber()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:number">public action SetValue(number i)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:number"><p>This action sets the value of the number object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="i"><strong>number</strong> <em>i</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="i">The number value.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:number"><em>Example Code:</em></span>
<pre class="code">Number age
age:SetValue(15.5)</pre>

</div><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>