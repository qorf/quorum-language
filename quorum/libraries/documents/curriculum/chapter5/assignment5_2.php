<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 5.2: Modifying the Radio project to use Recursion | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 5.2</h1>
                <p>Modifying the Radio project to use Recursion</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<!--<h1>Short Assignment:Modifying the Radio project to use Recursion</h1>-->
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul><li>Recursion
</li><li>How to identify reusable components from existing code
</li></ul><h2 id="Overview">Overview</h2>
<p>
In this assignment you will be modifying Assignment 5.1, where you had to create a Radio which played a user-determined station a user-determined length of time. It also had you create two separate files for your source code, and taught you how to save the Station class to an existing package.
</p>
<p>
You will be changing the play action in the Station class to use recursion instead of a repeat statement. Recursion is when a function calls itself, and each time it calls itself it moves closer and closer to meeting its end condition. An end condition is a condition that, when met, the recursive action stops calling itself. An example of this is having an end condition be when some integer variable is 0. This end condition is technically termed the <i>base case</i>.
</p>
<h2>Factorial Example</h2>
<p>
The code below calculates the factorial of a given number using recursion. The factorial of a number is the multiplication of that number with all integers smaller than it, but greater than or equal to 1. For example, in Quorum, one might hard code the factorial of 5 as:
</p>
<pre class="code">    integer FactorialOfFive = 5 * 4 * 3 * 2 * 1
</pre><p>
which is the integer value 120.
</p>
<pre class="code">action Main
     integer a = Factorial(5)
     output "a is equal to" + a

end

action Factorial (integer num) returns integer
     // This is the "base case" condition. You want to stop multiplying integers when you reach one. Otherwise, you will compute the wrong answer, particularly if you mistakenly multiply by 0!
     if num &lt;= 1
          return 1
     else
          // This is the recursive step.
          return Factorial(num-1) * num 
     end
end
</pre><pre class="code">"a is equal to 120"
</pre><p>
What's happening in this example is that the end condition, also called a base case is:
</p>
<pre class="code">if num &lt;= 1 then
     return 1
</pre><p>
That is the condition that will stop the recursive calls when it's met. 
The original call to that action, 5 in this case, got passed into the factorial function and started the recursive chain. After it ran through the function the first time it got to:
</p>
<pre class="code">return Factorial(num-1) * num
</pre><p>
Thus calling itself, the Factorial action, with num -1. Num is now one step closer to the base case, which is equal to or less than one. Consider what would happen if you used an addition operator in place of the subtraction operator. Would the program ever end? What about if num wasn't changed in the function?
</p>
<p>
An important take-away from this is that recursive action have to have parameters to communicate with itself. Without parameters there is no way for a recursive action to call itself while working towards a base case. What would happen if the recursive action was instead:
</p>
<pre class="code">action Factorial() returns integer
     if num &lt;= 1 then
          return 1
     end else then
          num-1
          return Factorial() * num 
     end
end
</pre><p>
   
In that code example there is no way for the recursive action to pass the information that num got decremented by 1 to the next action call. It would go into an infinite loop and crash the program. 
</p>
<h2>Design Criteria</h2>
<ul><li>Open your project from 5.1
</li><li>In your Station.quorum file delete the contents of the Play action, keeping the action declaration and end statement. Leaving you with:
<pre class="code">private action Play(integer time, integer note) returns integer

end
</pre></li><li>Inside this action use a Music object to play a series of notes, up to 10. For example:
<pre class="code">music:Play(note, .1)
music:Play(note+3, .1)
...
</pre></li></ul>
<ul>
    <li>
        Establish a base case that one of your parameters will meet
    </li>
    <li>
        Complete the recursive function by appropriately calling the Play action
    </li>
</ul>
<h2>Sample Output</h2>
<p>
<strong>Entering an invalid time</strong>
</p>
<pre class="code">"Please select your station (1-4):"
"3"
"How long do you want to play? (1-20):"
"0"
"Incorrect input. Please try again."
"How long do you want to play? (1-20):""
"6"
</pre><p>
<strong>Entering an invalid station</strong>
</p>
<pre class="code">"Please select your station (1-4):"
"5"
"Incorrect input. Please try again."
output] "Please select your station (1-4):"
"4"
"How long do you want to play? (1-20):"
"10"
</pre><p>
<strong>Entering valid information
</strong></p>
<pre class="code">
    "Please select your station (1-4):"
    "2"
    "How long do you want to play? (1-20):"
    "19"
</pre>