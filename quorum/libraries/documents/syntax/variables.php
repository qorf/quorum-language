<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Creating Variables in Quorum';
</script>

<h1>Variables</h1>
<p>
    In programming a variable is used to store data. Every variable has a 
    <a href="types.php">type</a> and a unique name. 
    The data type specifies what kind information can be stored in the 
    variable (e.g. A number, integer, text). The 
    variable name references the value stored in the variable.
</p>
<h2>Creating Variables</h2>
<p>
    In order to create a variable, you need to tell Quorum what kind you need. 
    There are many choices, but the most common ones are integer, number, text, 
    and boolean (also called primitive types). We can start by creating an 
    integer variable called a:
</p>
<p><pre class="code"><code>
integer a = 5
</code></pre></p>
<p>
    This tells Quorum that we want an integer value and that we want it to 
    have the value of 5. Next, we can create numbers with decimal points:
</p>
<p><pre class="code"><code>
number a = 5.5
</code></pre></p>
<p>
    We can also create text values, which are a group of characters:
</p>
<p><pre class="code"><code>
text a = &quot;Hello, how are you?&quot;
</code></pre></p>
<p>
    And finally, if we want to create a value that can only be true or false, 
    we can create a boolean:
</p>
<p><pre class="code"><code>
boolean a = true
</code></pre></p>
<h2>Variable names</h2>
<p>
    While you can create variables of many types, the names are constrained. 
    First, any variable you name must start with a letter, and it can be 
    followed by any number of characters, numbers, or underscore characters. 
    For example, any of the following names would be allowed:
</p>
<p><pre class="code"><code>
integer sally = 5
integer billy_likes_sally = 10
integer billy_likes_sally2 = 15
</code></pre></p>
<p>
    However, all of the following names are not allowed:
</p>
<p><pre class="code"><code>
integer 5sally = 5 //a number cannot be the first character
integer _sally = 10 //an underscore cannot be the first character
integer sall&amp;&amp;%%^$*#(y //not all characters are allowed to be used in names
</code></pre></p>
<p>
    Besides the variable names and types mentioned here, we can also create our 
    own custom types. More information can be found on this in the section 
    on <a href="classes.php">Classes</a>.
</p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>