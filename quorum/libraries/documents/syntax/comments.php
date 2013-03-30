<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Creating notes and comments in Quorum';
</script>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Commenting Code</h1>
		<p>How to write notes to remind ourselves of what code does.</p>
	</div>
</div>
<?php include("../../static/templates/contentwrapperheader.template.php"); ?>
<h1>Comments</h1>
<p>
    Since computer code can sometimes be esoteric, it sometimes helps to write
    notes to help sort out what a piece of code does in the future. We do this 
    using comments. In Quorum, two types of comments are allowed: 1) end-of-line 
    and 2) multiline. Comments in Quorum are the same as those in C++ and Java.
</p>
<h2>End-of-line comments</h2>
<p>
    An end-of-line comment, in general, goes at the end of a line.  You can also 
    put an end-of-line comment on its own line.  For an end-of-line comment, 
    start the comment with two forward slashes, like so:
</p>
<p><pre class="code"><code>
// This is a comment.
</code></pre></p>
<p>
    Anything after the slashes will be ignored by the compiler.
</p>
<h2>Multiline</h2>
<p>
    A multiline comment can include anywhere from a few characters to many lines.  
    You can start and end a multiline comment in the middle of other code, and 
    the compiler will simply skip the code in the comment.  To insert a 
    multiline comment, start the comment with a forward slash and an asterisk 
    and end the comment with an asterisk and a forward slash, like so:
</p>
<p><pre class="code"><code>
/* This is
a comment. */
</code></pre></p>
<p>
    The compiler will ignore any text between the beginning of the comment and 
    the end of the comment, even if it spans multiple lines.
</p>
<h2>Uses of Comments</h2>
<p>
    In addition to explaining code, comments can serve other purposes.  One such 
    purpose is hiding code that you don't want the compiler to see.  For instance, 
    suppose you wrote the following Quorum code:
</p>
<p><pre class="code"><code>
class main
    action main
        integer i = 15
        integer j = 25
        integer k = 35
    end
end
</code></pre></p>
<p>
    Suppose you weren't sure if you were going to use j and k anymore, but you 
    didn't want to erase the code yet because you might use them later.  Instead 
    of erasing them or starting a new project, you could simply comment them out.  
    You could do this with two end-of-line comments:
</p>
<p><pre class="code"><code>
class main
    action main
        integer i = 15
        //integer j = 25
        //integer k = 35
    end
end
</code></pre></p>
<p>
    or with a multiline comment:
</p>
<p><pre class="code"><code>
class main
    action main
        integer i = 15
        /*integer j = 25
        integer k = 35 */
    end
end
</code></pre></p>
<p>
    This way, the compiler won't see the code but it will still be in your file.
</p>
<?php require_once("../../static/templates/pagefooter.template.php"); ?>