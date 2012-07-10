<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 3';
</script>

<h1>Chapter 3: Control Structures</h1>
<h2>Questions</h2>
<ol><li>What are control structures?  List two types of control structures and explain what they do.  Give an example for each structure.
</li><li>List three conditional clauses and explain what they are used for.
</li><li>Given the block of code below, what does the computer do?  Explain in plain English what each line does and what a line is known as.
</li></ol>
<p><pre class="code"><code>
integer a = 6
if a &lt; 4 then
   a = a - 1
end
else if a &gt; 4 then
   a = a + 1
end
</code></pre></p>
<ol start="4"><li>What are the differences between the three conditional clauses?
</li><li>What are nesting conditionals?  Give an example of the nesting conditionals in Quorum.
</li><li>Why should the <tt>else if</tt> statements be used over nesting conditionals?
</li><li>How many <tt>else if</tt> statements can we have?
</li><li>List four kinds of loops and explain what they are used for.
</li><li>What is an infinite loop?
</li><li>What is lexical scoping?
</li><li>What is a debugger?
</li><li>Why is a debugger useful and what makes Quorum’s debugger unique to the other debuggers?
</li><li>What is a breakpoint?
</li><li>Can keywords, such as <tt>repeat</tt> and <tt>if</tt>, be used in the variable names?  Why or why not?
</li><li><strong>Hard:</strong> The debugger is used to help us remove any errors in our programs.  Come up with an alternative technique for debugging a program.  Give one example of how it might be done.
</li></ol>
<h2>Essay Questions</h2>
<ol><li>There are situations where a specific loop type is more appropriate to use.  Think of one example for each of the Quorum loop types.  Explain why the specific loop type would be used in the situation and not one of the others
</li></ol><ol start="2"><li>Geometry involves conditional statements. Write a paragraph explaining how this relate to the object-oriented paradigm. Then, write another paragraph explaining the differences and similarities between this abstract concept and the physical aspects of geometry.
</li></ol><ol start="3"><li><strong>Hard:</strong> Various programming languages have their own loop syntax.  Some programming languages even share similar syntax for specific loop types.  Compare and contrast the loop syntax for each of the loop types in Quorum to the other programming languages.  Provide the comparison of at least three different programming languages.  You can choose Java, Ruby, and any other programming languages to compare different loop syntax.  For example, <tt>repeat 10 times</tt> in Quorum is compared to <tt>for(int i = 0; i &lt; 10; i++) {...</tt>} in Java.
</li></ol>
<h2>In Class Lab Assignments</h2>
<ul>
<li><a href="lab3_1.php">Lab Manual 3.1</a></li>
<ul>
<li>Write a car dealership program in Quorum.</li>
<li>Use conditional statements</li>
<li>Calculate prices based on user input.</li>
</ul>
</ul>
<ul>
<li><a href="lab3_2.php">Lab Manual 3.2</a></li>
<ul>
<li>Write a program in Quorum that uses different loop types.</li>
<li>Use loop statements.</li>
<li>Use the Random library.</li>
</ul>
</ul>
<ul>
<li><a href="lab3_3.php">Lab Manual 3.3</a></li>
<ul>
<li>Write a program in Quorum that gets the current date and time.</li>
<li>Use Conditionals, Loops, and Debugging.</li>
<li>Learn how to debug programs in Sodbeans.</li>
</ul>
</ul>
<h2>Short Programming Projects</h2>
<ul>
<li><a href="assignment3_1.php">Assignment Manual 3.1</a></li>
<ul>
<li>Write a travel reservation program.</li>
<li>Use conditionals to solve a problem.</li>
<li>Check user input for errors.</li>
</ul>
</ul>
<ul>
<li><a href="assignment3_2.php">Assignment Manual 3.2</a></li>
<ul>
<li>Write a number guessing game.</li>
<li>Use conditionals to solve a problem.</li>
<li>Use loops to solve a problem.</li>
</ul>
</ul>
<h2>Challenge Programming Projects</h2>
<ul>
<li><a href="assignment3_3.php">Assignment Manual 3.3</a></li>
<ul>
<li>Modify the existing travel reservation program.</li>
<li>Use repeat statements to improve the reservation program.</li>
<li>Check user input for errors.</li>

<?php include("../../include/footer.php"); ?>