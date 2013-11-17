<?php $classPageTitle = "CompareResult"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Support.CompareResult</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Support.CompareResult" /><h2> <span class="controllable" data-componentType="class-name">Class CompareResult</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The CompareResult class provides constants to represent results of a comparison. The possible results include LARGER, EQUAL, SMALLER, and INVALID. Each corresponds to an integer value, 1, 0, -1, and -2, respectively.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Language.Support.CompareResult
class Main
   action Main
      Number one
      one:SetValue(10.3)
      Number two
      two:SetValue(9.6)
      if one:Compare(two) = CompareResult:LARGER
         output "one is larger than two"
      end
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>integer</strong> <em>EQUAL</em></li>
<li class = "package_alternate" ><strong>integer</strong> <em>INVALID</em></li>
<li class = "package_standard" ><strong>integer</strong> <em>LARGER</em></li>
<li class = "package_alternate" ><strong>integer</strong> <em>SMALLER</em></li>
<li class = "package_standard" ><strong>integer</strong> <em>result</em></li>
</ul><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>