<?php $classPageTitle = "Object"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Object</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Object" /><h2> <span class="controllable" data-componentType="class-name">Class Object</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Object class is the basic building block of all classes. This means everything is an Object and some of it's basic functionality is available to all classes.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">class Main
   action Main
      Object o
      Object i
      if o:Equals(i)
         output "Equal objects"
      end
   end
end</pre>


 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Compare:Libraries.Language.Object">public action Compare(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Compare:Libraries.Language.Object"><p>This action compares two object hash codes and returns a CompareResult. The compare result is either larger if this hash code is larger than the object passed as a parameter, smaller, or equal.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The object to compare to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Support.CompareResult</strong>: The Compare result, Smaller, Equal, or Larger.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Compare:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Support.CompareResult
Object o
Object t
CompareResult result = o:Compare(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Equals:Libraries.Language.Object">public action Equals(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Equals:Libraries.Language.Object"><p>This action determines if two objects are equal based on their hash code values.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The to be compared.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the hash codes are equal and false if they
        are not equal.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Equals:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Object
use Libraries.Language.Types.Text
Object o
Text t
boolean result = o:Equals(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHashCode">public system action GetHashCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHashCode"><p>This action gets the hash code for an object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The integer hash code of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHashCode"><em>Example Code:</em></span>
<pre class="code">Object o
integer hash = o:GetHashCode()</pre>

</div><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>