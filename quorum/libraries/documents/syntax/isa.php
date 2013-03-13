<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'The operator is a';
</script>
<h1>The &quot;is a&quot; keyword</h1>
<p>
The keyword &quot;is a&quot; can be used to specify an 
<a href="inheritance.php">inheritance</a> relationship and it can be used to 
determine if an object is of a certain type. Consider the following example code:
</p>
<p><pre class="code"><code>
class Main
    action Main
        B b
        if b is a Object then
            print &quot;An object has been found&quot;
        end
    end
end
</code></pre></p>
<p>
In the above code the &quot;is a&quot; statement follows a format, 
&lt;variable&gt; is a &lt;class name&gt;, where the variable is the item you 
are evaluating and the class name is the type you are comparing the variable to. 
Therefore, the expression &quot;b is a Object&quot; will evaluate to either 
true or false.
</p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>