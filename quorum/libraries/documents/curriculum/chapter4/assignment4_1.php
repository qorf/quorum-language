<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 4_1';
</script>

<h1>Short Assignment: Calculator</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>How to write actions to perform specific tasks</li>
<li>How to utilize parameters and returning values</li>
<li>How to pass arguments and using return values</li>
</ul>
<h2>Overview</h2>
<p>
In this assignment, you will write a calculator that performs common mathematical functions. This calculator will support the following:
</p>
<ul><li>Addition
</li><li>Subtraction
</li><li>Multiplication
</li><li>Division
</li><li>Exponentiation
</li><li>Factorials
</li></ul>
<li>
    Define the following actions with necessary parameters and return values:
    <ul>
        <li>
            <tt>action Add</tt>
        </li>
        <li>
            <tt>action Subtract</tt>
        </li>
        <li>
            <tt>action Multiply</tt>
        </li>
        <li>
            <tt>action Divide</tt>
        </li>
        <li>
            <tt>action Exponent</tt>
        </li>
        <li>
            <tt>action Factorial</tt>
        </li>
    </ul>
</li>
<p>
    The application should start by asking the user to pick one of the operations outlined above. After the user has selected an option, the application should prompt the user to enter any appropriate values. For the first five operations, two values are needed from the user. For the last, only one value is required from the user. After the user enters any required values, the application should show the result of the operation. Create a new project and name it <strong>Assignment4_1</strong>. You may use the <tt>Add</tt>, <tt>Subtract</tt>, <tt>Multiply</tt> and <tt>Divide</tt> methods you wrote in lab 4.1 for this project.
</p>
<h2>Exponentiation</h2>
<p>
In mathematics, a common operation is used known as <strong>exponentiation</strong>. This is often denoted using the caret symbol (<tt>^</tt>). When you exponentiate a number, you multiply that number by itself a certain number of times. This is also commonly stated as "raised to the power of." For example, 3 raised to the power of 2 is written as:
</p>
<p><pre class="code"><code>
3^2 = 3 * 3
</code></pre></p>
<p>
which is 9. 2 raised to the power of 4 would be written as:
</p>
<p><pre class="code"><code>
2^4 = 2 <em> 2 </em> 2 * 2
</code></pre></p>
<p>
which is 16.
</p>
<p>
There is one notable exception. Any number raised to the power of zero is one. For example,
</p>
<p><pre class="code"><code>
5^0 = 1
8^0 = 1
</code></pre></p>
<p>
Before writing this program, work out the following problems to gain an understanding of the concept:
</p>
<ul><li>What is <tt>5^2</tt>? Show your work.
</li><li>What is <tt>3^3</tt>? Show your work.
</li><li>What is <tt>2^5</tt>? Show your work.
</li><li>What is <tt>1^0</tt>?
</li><li>What is <tt>4^0</tt>?
</li></ul><p>
For this assignment, calculate the exponential using conditionals and loops.
</p>
<h2>Factorials</h2>
<p>
In mathematics, a <b>factorial</b> is the multiplication of all positive numbers less than or equal to a given value. Factorials are indicated using an exclamation point after the number. For example, the factorial of the number 5 would be written as:
</p>
<p><pre class="code"><code>
5! = 5 <em> 4 </em> 3 <em> 2 </em> 1
</code></pre></p>
<p>
which is 120. The factorial of the number 0 is 1, and the factorial of the number 1 is also 1. Before writing this program, work out the following factorials to gain an understanding of the concept:
</p>
<ul>
<li>What is the factorial of 3? Show your work.</li>
<li>What is the factorial of 6? Show your work.</li>
<li>What is the factorial of 2? Show your work.</li>
<li>What is the factorial of 0?</li>
<li>What is the factorial of 1?</li>
</ul>
<p>
For this assignment, calculate the factorial using conditionals and loops.
</p>
<h2>Sample Output</h2>
<p>
The input dialog statements are included in the sample output.
</p>
<p>
<b>Operation Selection</b>
</p>
<p><pre class="code"><code>
Select an operation: 1 - Addition, 2 - Subtraction, 3 - Multiplication, 4 - Division, 5 - Exponentiation, 6 - Factorial
Select an operation (1-6):
</code></pre></p>
<p>
<b>Addition</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
1
Enter the first value: 
1
Enter the second value: 
2
1 plus 2 is 3
</code></pre></p>
<p>
<b>Subtraction</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
2
Enter the first value: 
1
Enter the second value: 
2
1 minus 2 is -1
</code></pre></p>
<p>
<b>Multiplication</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
3
Enter the first value: 
1
Enter the second value: 
2
1 times 2 is 2
</code></pre></p>
<p>
<b>Division</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
4
Enter the first value: 
6
Enter the second value: 
3
6 divided by 3 is 2
</code></pre></p>
<p>
<b>Exponentiation</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
5
Enter the first value: 
2
Enter the second value: 
3
2 raised to the power of 3 is 8
</code></pre></p>
<p>
<b>Factorial</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
6
Enter the first value: 
4
4 factorial is 24
</code></pre></p>
<p>
<b>Entering an invalid option</b>
</p>
<p><pre class="code"><code>
Select an operation (1-6): 
7
The value you entered is not valid. You must choose a value between 1 and 6.
</code></pre></p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>