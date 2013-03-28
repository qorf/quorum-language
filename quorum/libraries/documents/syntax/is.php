<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'The operator is';
</script>
<h1>The &quot;is&quot; keyword</h1>
<p>
The keyword &quot;is&quot; can be used to specify an 
<a href="inheritance.php">inheritance</a> relationship and it can be used to 
determine if an object is of a certain type. Consider the following example code:
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
</p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  
 <?php include("../../static/templates/pageheader.template.php"); ?>