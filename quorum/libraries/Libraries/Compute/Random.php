<?php $classPageTitle = "Random"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Compute.Random</h1>
<input type="hidden" id="classkey" value="Libraries.Compute.Random" /><h2> <span class="controllable" data-componentType="class-name">Class Random</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Random class permits generation of pseudorandom numbers that can be used for a variety of applications.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Compute.Random
class Main
   action main
      // Print a list of ten random integers, each within the range
      // 5 to 9.
      Random random
      repeat 10 times
         output random:RandomIntegerBetween(5, 9)
      end
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomBoolean">public system action RandomBoolean()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomBoolean"><p>This action returns a random boolean value. (True or False)</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns the random boolean.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomBoolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
Random random
boolean randomBool = random:RandomBoolean()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomInteger">public system action RandomInteger()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomInteger"><p>This action returns a random integer within the range 0 to +2,147,483,646 (lower bound inclusive, upper bound inclusive).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns the random number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomInteger"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
Random random
integer randomNumber = random:RandomInteger()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomInteger:integer">public action RandomInteger(integer maximum)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomInteger:integer"><p>This action returns a random integer that is between zero (inclusive) and +2,147,483,646 (inclusive).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="maximum"><strong>integer</strong> <em>maximum</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="maximum">- The maximum integer that can be generated.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns the random integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomInteger:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
Random random
integer randomNumber = random:RandomInteger(5)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomIntegerBetween:integer:integer">public action RandomIntegerBetween(integer minimum,integer maximum)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomIntegerBetween:integer:integer"><p>This action returns a random integer between minimum and maximum. Minimum and maximum are inclusive.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="minimum"><strong>integer</strong> <em>minimum</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="minimum">The minimum number to be generated.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="maximum"><strong>integer</strong> <em>maximum</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="maximum">The maximum number to be generated.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: Returns the random integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomIntegerBetween:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
Random random
integer randomNumber = random:RandomIntegerBetween(1, 5)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomListOfIntegers:integer">public action RandomListOfIntegers(integer length)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomListOfIntegers:integer"><p>This action returns a list of randomly generated integers in the range 0 to +2,147,483,646 (lower bound inclusive, upper bound inclusive).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="length"><strong>integer</strong> <em>length</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="length">the length of the list to be generated.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.List</strong>: Returns the list of random integers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomListOfIntegers:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
use Libraries.Containers.List
Random random
List&lt;Integer&gt; list
list = random:RandomListOfIntegers(15)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomListOfNumbers:integer">public action RandomListOfNumbers(integer length)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomListOfNumbers:integer"><p>This action returns a list of randomly generated numbers in the range 0 to 1.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="length"><strong>integer</strong> <em>length</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="length">the length of the list to be generated.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.List</strong>: Returns the list of random numbers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomListOfNumbers:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
use Libraries.Containers.List
Random random
List&lt;number&gt; list
list = random:RandomListOfNumbers(15)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RandomNumber">public system action RandomNumber()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RandomNumber"><p>This action returns a random number within the range 0 to 1.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: Returns the random number.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RandomNumber"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
Random random
number randomNumber = random:RandomNumber()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSeed:number">public system action SetSeed(number seed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSeed:number"><p>This action sets the seed used to generate random numbers. On creation of a Random object, the seed is set to the current system time in milliseconds.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="seed"><strong>number</strong> <em>seed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="seed">The number that should be used as a seed. Note that
    the fractional portion of the number will be ignored.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSeed:number"><em>Example Code:</em></span>
<pre class="code">use Libraries.Compute.Random
Random random
random:SetSeed(1010001) // set the seed to 1010001</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>