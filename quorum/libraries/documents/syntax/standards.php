<?php include("../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Coding Standards in Quorum';
</script>
<h1>Code formatting conventions</h1>
<p>
    On this page, we discuss code conventions for writing code in Quorum. 
    This includes conventions for code writing, choosing names, writing comments, 
    and other common issues.
</p>
<h2>Conventions for naming classes, actions, and parameters</h2>
<p>
    We recommend the following conventions for naming classes, actions, and parameters in 
    Quorum:
</p>
<p>
    Do's:
</p>
<ul>
    <li>Do use short and meaningful names</li>
    <li>Do use names that are common in English</li>
    <li>Do use names that best represent what it is your class, action, or parameter 
        does or is</li>
    <li>Do use the most obvious name you can think of for your libraries (a thesaurus is 
        helpful).</li>
</ul>
<p>
    Do not's:
</p>
<ul>
    <li>Do not use acronyms (e.g., awk, sed, ascii, io)</li>
    <li>Do not use shortened names (e.g. use AbsoluteValue instead of Abs, or 
        CharacterToInteger instead of atoi)</li>
    <li>Do not use single letters for parameter names (e.g., use index 
        instead of i)</li>
    <li>Do not use computer science or technical jargon (e.g., virtual 
        void abs() = 0;, BTree, or LinkedList)</li>
    <li>Do not make names excessively verbose (e.g., 
        ThisActionIsReallyAwesomeButHardToType())</li>
    <li>Do not give your libraries arbitrary names (e.g., call your library 
        MailServer instead of BobsGem)</li>
</ul>
<h2>Naming and Layout Conventions</h2>
<p>
    In Quorum, there are a few conventions for class, action and variable naming 
    that we recommend following in your code.
</p>
<h3>Class Names</h3>
<p>
    Class names in Quorum should be in the pascal case format, e.g., PascalCase, 
    with the P and C capitalized.
</p>
<h3>Action Names</h3>
<p>
    Action names in Quorum should also be in the pascal case format, e.g., 
    PascalCase, with the P and C capitalized. In addition, actions that do not 
    have any parameters do not require parentheses. By convention, leave out any 
    syntax that is not required.
</p>
<h3>Variable Names</h3>
<p>
    Variable names in Quorum should follow the camel case format, e.g., camelCase, 
    with the first c lowercase and the second C capitalized.
</p>
<h3>Indentation</h3>
<p>
    The Quorum compiler does not pay attention to whitespace in code. However, we 
    ask that you use consistent indentation.
</p>
<p>
    Consistent indentation:
</p>
<p><pre class="code"><code>
class Main
	action Main
		print &quot;hello world.&quot;
	end
end
</code></pre></p>
<p>
    Inconsistent indentation:
</p>
<p><pre class="code"><code>
class Main
	action Main
	print &quot;hello world.&quot;
end
end
</code></pre></p>
<h2>Comments and Documentation</h2>
<p>
    In Quorum, there are two types of comments: single line and multi-line comments. 
    For single line comments, we recommend a space after the beginning two forward 
    slashes, like so:
</p>
<p>
    Good single-line comment:
</p>
<p><pre class="code"><code>
// Print a message to the user.
print &quot;hello world.&quot;
</code></pre></p>
<p>
    Multi-line comments should be formatted such that the beginning forward star 
    and ending star forward are on their own lines,  like so:
</p>
<p><pre class="code"><code>
/*
the main entry point to the program
<ul>

*/
</ul>

action Main
end
</code></pre></p>
<p>
    However, if on only one line, as a single line comment is sufficient:
</p>
<p><pre class="code"><code>
/* May as well use a single line comment */
action Main
end
</code></pre></p>
<h3>Documentation</h3>
<p>
    Please fully document each action and class in Quorum
    standard library files. In each case, please be sure to include at least one
    example, in all case, of code to execute that action or use that class. Here is an
    example comment from the array class:
</p>
<p><pre class="code"><code>
/*
The Array class is a data structure that stores items in contiguous memory. An
item is typically stored and accessed through an index or location. This location
always starts at 0, this means the first item in the array is at location 0, the
second is at location 1, etc. The default maximum size is set to 10, but can be
changed by using the SetSize(value) method or the array will automatically
make itself large when the space is needed (note: it is possible to turn the 
resizing off with the SetAutoResize(false) method).

Attribute: Example

use Libraries.Containers.Array
class Main
   action Main
      //make the array
      Array&lt;integer&gt; myArray
      //add a value
      myArray:Add(12)
      //get it back
      integer value = myArray:Get(0)
   end
end
*/
</code></pre></p>
<?php include("../include/footer.php"); ?>