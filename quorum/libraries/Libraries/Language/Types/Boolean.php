<?php $classPageTitle = "Boolean"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Types.Boolean</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Types.Boolean" /><h2> <span class="controllable" data-componentType="class-name">Class Boolean</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Boolean class is the object representation of the primitive type boolean.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">class Main
   action Main
      boolean isTrue = true
      Boolean result = test(isTrue)
   end
   action test(Boolean bool) returns Boolean
      return bool
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
Boolean o
Boolean t

CompareResult result = o:Compare(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Equals:Libraries.Language.Object">public action Equals(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Equals:Libraries.Language.Object"><p>This action determines if two objects are equal based on their values(true or false).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The to be compared.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the values are equal and false if they
        are not equal.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Equals:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">Boolean o
Boolean t
boolean result = o:Equals(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHashCode">public system action GetHashCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHashCode"><p>This action gets the hash code for an object. In this case, GetHashCode is overriden to return the integer 1231 if the boolean is true and 1237 if the boolean is false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The integer hash code of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHashCode"><em>Example Code:</em></span>
<pre class="code">Object o
integer hash = o:GetHashCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetText">public action GetText()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetText"><p>This action gets the value from the boolean object and casts it to a text value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The value of the object converted to text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetText"><em>Example Code:</em></span>
<pre class="code">Boolean isTrue
text result = isTrue:GetText()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action gets the value from the boolean object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The value of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">Boolean isTrue
boolean result = isTrue:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:boolean">public action SetValue(boolean i)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:boolean"><p>This action sets the value of the boolean object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="i"><strong>boolean</strong> <em>i</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="i">The boolean value(true or false).</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:boolean"><em>Example Code:</em></span>
<pre class="code">Boolean isTrue
isTrue:SetValue(true)</pre>

</div><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>