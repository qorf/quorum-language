<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 2.1 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>

<h1>Short Assignment: Type Casts</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>Implicit type casting</li>
<li>Explicit type casting</li>
</ul>
<h2>Overview</h2>
<p>
    Create a new project and name it <strong>Assignment2_1</strong>.  In this assignment, you will write a program that has at least 10 combinations of implicit type casts and five examples of explicit type casts.  Implicit type casting takes various combinations of primitive types.  For example, the implicit type cast below uses the <code>text</code> primitive and the <code>integer</code> primitive. When concatenated using the <code>+</code> operator, the result is a <code>text</code> primitive.
</p>
<p><pre class="code"><code>
text value = &quot;Hello there THX&quot; + 1138
</code></pre></p>
<p>
The output of the code above shows the following:
</p>
<p><pre class="code"><code>
Hello there THX1138
</code></pre></p>
<p>
<br />
Explicit type casting makes a conversion between types with the <code>cast</code> operator.  For example, the explicit type cast below converts a <code>text</code> value into an <code>integer</code> value.
</p>
<p><pre class="code"><code>
integer a = cast(integer, &quot;1138&quot;)
</code></pre></p>
<p>
The output of the code above shows the following:
</p>
<p><pre class="code"><code>
1138
</code></pre></p>
<h2>Sample Output</h2>
<p>
For each cast, put a comment using // on the top of each line identifying each case of implicit and explicit type casts. An example of this might be:
</p>
<p><pre class="code"><code>
//Implicit type casting of an integer to a text type
text value = &quot;Hello there THX&quot; + 1138
say value
</code></pre></p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>