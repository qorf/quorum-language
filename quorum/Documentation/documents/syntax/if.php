<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Doing things conditionally in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Conditional Statements with "if"</h1>
		<p>How to use the "if" keyword to control the flow of a program.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>The if Conditional</h1>
<p>
When programming a computer, you often want the machine to make decisions about 
data. For example, you might want the computer to add some numbers, then 
determine if the sum is greater than 10. You accomplish this in Quorum using 
the command if. For example, suppose we wanted to test whether an integer is 
greater than 10, we could do this in Quorum like so:
</p>
<p><pre class="code"><code>
if a &gt; 10
end
</code></pre></p>
<p>
In this case, if a happens to be greater than 10, then Quorum will execute any 
computer code that is between the top line of the if and the &quot;end&quot; 
at the bottom.
</p>
<p>
Sometimes, however, you want to test multiple conditions to see if any of them 
are true, like so:
</p>
<p><pre class="code"><code>
integer a = 1
integer b = 1000
integer c = 0
if a &gt; 100 
     c = 1
elseif b = 100
     c = 2
end
</code></pre></p>
<p>
or
</p>
<p><pre class="code"><code>
integer a = 1
integer b = 1000
integer c = 0
if a &gt; 100
     c = 1
elseif b = 100
     c = 2
else
     c = 3
end
</code></pre></p>
<p>
In this case, the above code will first test to see if a is greater than 100. 
If that is so, it will execute the statement c = 1. However, if the value of a 
is not greater than 100, this code will then test to see if b is greater than 
100. If that is the case, c will be set to 2. Finally, if neither a, nor b, are 
greater than 100, c will be set to 3.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>