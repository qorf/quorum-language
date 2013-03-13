<?php include("../../static/templates/pageheader.template.php"); ?> <?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Finding and using libraries with Quorum';
</script>
<h1>Use Statements</h1>
<p>
Whenever you need to include an API or class you have already written, into a 
project you are currently working on, you will need to add a use statement. A 
use statement will include and reference those classes you wish to use. For 
example, suppose you were writing a program that generates a random number and 
prints it out to the user. We don't need to write the code to generate a random 
number because the Quorum standard library already includes a class that 
generates a random number for us.
</p>
<h2>Format and Finding Libraries</h2>
<p>
The use statement consists of the &quot;use&quot; keyword followed by the full 
name of the class or library to include. So, to use the random class we would 
used the following statement:
</p>
<p><pre class="code"><code>
use Libraries.Compute.Random
</code></pre></p>
<p>
One important thing to remember about the use statement is that it must be 
placed at the top of file. A class definition and action must always come 
after the list of use statements. In order to find a library in the Quorum 
standard library you can use code completion, If you are using Windows or 
OSX the shortcut is CTRL + SPACE, to find any available library classes. In 
addition to code completion there is a 
<a href="../../libraries.php">Quorum Standard Library Reference</a> available.
</p>
<h2>Random Number Example</h2>
<p>
A complete solution to the above problem is as follows:
</p>
<p><pre class="code"><code>
use Libraries.Compute.Random
class Main
   action Main
      Random random
      print random:RandomIntegerBetween(1, 100)
   end
end
</code></pre></p>
<h2>The .All Command</h2>
<p>
Sometimes you may want to use all of the classes under a given file. For example, 
suppose we wanted to include all of the classes in &quot;Libraries.Compute&quot;, 
this includes the Random class and the Math class. Simply add All to the end of 
the use statement to include all of the classes within a file, see the following:
</p>
<p><pre class="code"><code>
use Libraries.Compute.All
class Main
   action Main
      Random random
      Math math
      print random:RandomIntegerBetween(1, 100)
      print math:SquareRoot(6)
   end
end
</code></pre></p>
 <?php include("../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../static/templates/pageheader.template.php"); ?>