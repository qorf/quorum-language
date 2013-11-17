<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'The operator is';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>The &quot;is&quot; keyword</h1>
		<p>For detecting types and using <a href="inheritance.php">inheritance</a>.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<p>
While the is keyword is used for <a href="inheritance.php">inheritance</a>,
on this page, we give a short example of how the keyword can be used to
detect the type of an object at runtime. Consider the following example code:
</p>
<p><pre class="code"><code>
B b
if b is Object
    output &quot;An object has been found&quot;
end
</code></pre></p>
<p>
In the above code the &quot;is&quot; statement follows a format, 
&lt;variable&gt; is a &lt;class name&gt;, where the variable is the item you 
are evaluating and the class name is the type you are comparing the variable to. 
Therefore, the expression &quot;b is Object&quot; will evaluate to either 
true or false.
</p><?php require_once("../../static/templates/pagefooter.template.php"); ?>