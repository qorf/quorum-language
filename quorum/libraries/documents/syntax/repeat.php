<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Doing things over and over again in Quorum';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Repeating statements multiple times</h1>
		<p>How to do things over and over and over and ...</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h2>repeat &lt;expression&gt; times</h2>
<p>
The Quorum programming language uses the word repeat to describe the idea of 
doing something 0 or more times. On this short page, we describe how to do 
things several times in quorum. Here is a first example:
</p>
<p><pre class="code"><code>
repeat 10 times
end
</code></pre></p>
<p>
While repeat times requires that we use integers, they can be simple or complex. 
For example, we might use constructs like the following:
</p>
<p><pre class="code"><code>
integer a = complicatedMathAction()
integer b = anotherComplexAction()
repeat (b / a - (b + 5)) times
end
</code></pre></p>
<h2>repeat while &lt;expression&gt;</h2>
<p><pre class="code"><code>
integer a
repeat while a &lt; 15
end
</code></pre></p>
<p>
In the above version of repeat, this syntax tells Quorum to continue executing 
as long as a happens to be smaller than 15. One common example for how to use 
such a loop is to change the value of a as it executes, for example, like so:
</p>
<p><pre class="code"><code>
integer a = 0
repeat while a &lt; 15
     a = a + 1
end
</code></pre></p>
<p>
Eventually, a will become larger than 15 and this repeat will stop executing.
</p>
<h2>repeat until &lt;expression&gt;</h2>
<p><pre class="code"><code>
integer a
repeat until a &lt; 15
end
</code></pre></p>
<p>
In the above version of repeat, this syntax tells Quorum to continue executing 
until the value of a is smaller than 15.
</p>
<p><pre class="code"><code>
integer a = 0
repeat until a &lt; 15
     a = a + 1
end
</code></pre></p>
<p>
Since a is less than 15 this loop will execute 0 times.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>