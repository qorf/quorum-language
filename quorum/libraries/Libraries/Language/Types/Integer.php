<?php $classPageTitle = "Integer"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Types.Integer</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Types.Integer" /><h2> <span class="controllable" data-componentType="class-name">Class Integer</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Integer class is the object representation of the primitive type integer.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">class Main
   action Main
      integer age = 15
      Integer result = test(age)
   end
   action test(Integer int) returns Integer
      return int
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>integer</strong> <em>maximumValue</em></li>
<li class = "package_alternate" ><strong>integer</strong> <em>minimumValue</em></li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="BitCount">public system action BitCount()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="BitCount"><p>This action determines the number of one bits in the integer value. This integer value is represented as two's compliment binary representation.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of one bits in the integer value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="BitCount"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:BitCount()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Compare:Libraries.Language.Object">public action Compare(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Compare:Libraries.Language.Object"><p>This action compares two object values and returns a CompareResult. The compare result is either larger if this hash code is larger than the object passed as a parameter, smaller, or equal.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The object to compare to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Support.CompareResult</strong>: The Comprare result, Smaller, Equal, or Larger.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Compare:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Support.CompareResult
Integer o
Integer t
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
<pre class="code">Integer o
Integer t
boolean result = o:Equals(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetBinary">public system action GetBinary()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetBinary"><p>This action gets the binary representation of the integer value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The binary representation of the integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetBinary"><em>Example Code:</em></span>
<pre class="code">integer i = 3
text result = i:GetBinary()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHashCode">public system action GetHashCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHashCode"><p>This action gets the hash code for an object. In this case, GetHashCode is overriden to be equivalent to the hash code of its containing object, value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The integer hash code of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHashCode"><em>Example Code:</em></span>
<pre class="code">Object o
integer hash = o:GetHashCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHex">public system action GetHex()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHex"><p>This action gets the hex representation of the integer value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The hex representation of the integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHex"><em>Example Code:</em></span>
<pre class="code">integer i = 3
text result = i:GetHex()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaximumValue">public action GetMaximumValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaximumValue"><p>This action gets the maximum value allowed for an integer variable(2147483647).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The maximum value that can be stored in an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaximumValue"><em>Example Code:</em></span>
<pre class="code">Integer size
integer max = size:GetMaximumValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMinimumValue">public action GetMinimumValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMinimumValue"><p>This action gets the minimum value allowed for an integer variable(-2147483648).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The minimum value that can be stored in an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMinimumValue"><em>Example Code:</em></span>
<pre class="code">Integer size
integer min = size:GetMinimumValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumber">public action GetNumber()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumber"><p>This action gets the value from the Integer object and casts it to a number.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The value of the object converted to a number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumber"><em>Example Code:</em></span>
<pre class="code">Integer age
number result = age:GetNumber()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOctal">public system action GetOctal()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOctal"><p>This action gets the octal representation of the integer value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The octal representation of the integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOctal"><em>Example Code:</em></span>
<pre class="code">integer i = 3
text result = i:GetOctal()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetText">public action GetText()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetText"><p>This action gets the value from the integer object and casts it to a text value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The value of the object converted to text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetText"><em>Example Code:</em></span>
<pre class="code">Integer age
text result = age:GetText()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action gets the value from the integer object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The value of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">Integer age
integer result = age:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="HighestOneBit">public system action HighestOneBit()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="HighestOneBit"><p>This action determines the highest(left most) one bit in the two's compliment binary representation of the integer.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the highest one bit in the integer value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="HighestOneBit"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:HighestOneBit()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="LeadingZeros">public system action LeadingZeros()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="LeadingZeros"><p>This action determines the number of leading zeros on the two's compliment binary representation of the integer value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of leading zeros.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="LeadingZeros"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:LeadingZeros()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="LowestOneBit">public system action LowestOneBit()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="LowestOneBit"><p>This action determines the lowest(right most) one bit in the two's compliment binary representation of the integer.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the lowest one bit in the integer value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="LowestOneBit"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:LowestOneBit()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Reverse">public system action Reverse()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Reverse"><p>This action determines the reverse of the two's compliment binary representation of the integer value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The reverse of the two's compliment of the integer value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Reverse"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:Reverse()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RotateLeft:integer">public system action RotateLeft(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RotateLeft:integer"><p>This action rotates left the two's compliment binary representation of the integer value by a specified number of positions.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">the number of position to rotate.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The rotated left integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RotateLeft:integer"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:RotateLeft(2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RotateRight:integer">public system action RotateRight(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RotateRight:integer"><p>This action rotates right the two's compliment binary representation of the integer value by a specified number of positions.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">the number of position to rotate.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The rotated right integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RotateRight:integer"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:RotateRight(2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:integer">public action SetValue(integer i)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:integer"><p>This action sets the value of the integer object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="i"><strong>integer</strong> <em>i</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="i">The integer value.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:integer"><em>Example Code:</em></span>
<pre class="code">Integer age
age:SetValue(15)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TrailingZeros">public system action TrailingZeros()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TrailingZeros"><p>This action determines the number of trailing zeros on the two's compliment binary representation of the integer value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The number of trailing zeros.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="TrailingZeros"><em>Example Code:</em></span>
<pre class="code">integer i = 3
integer result = i:TrailingZeros()</pre>

</div><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>