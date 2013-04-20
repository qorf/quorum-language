<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab3_2';
</script>

<h1>Lab 3.2: Loops</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to use loops to perform repetitive tasks</li>
<li>The repeat times structure</li>
<li>The repeat while and repeat until structures</li>
<li>How to use the Random library in Quorum</li>
</ul>
<h2>Overview</h2>
<p>
Algorithms often include steps such as &quot;keep moving all books onto the bookshelf until no more books remain on the cart.&quot; Loops allow you to perform the same task repeatedly until some condition is met. Loops, in addition to conditional statements, are a fundamental tool in computer programming. They not only allow you to reduce the amount of code you write, but also improve readability and make it possible to perform far more complex operations, such as sorting numbers.
</p>
<p>
In this lab, you will discuss the types of loops available in Quorum, provide examples of how to use them and solve some problems that require the use of loops.
</p>
<h2>Task 1: Repeat Times Loop</h2>
<p>
Create a new empty Quorum project and name it <strong>Lab3_2</strong>.  You will work with all three kinds of loops to get a grasp of each loop. Once each task is completed, check over the solution then comment out your solution and move onto the next task (this will make debugging easier).
</p>
<p>
Also, for this task, you will be using the <tt>Random</tt> class.  Add a use statement for the <tt>Random</tt> class, found in the Compute library, at the top of the file. To help you become familiar with the <tt>Random</tt> class, sample code using the <tt>Random</tt> class is provided below. Study this piece of code briefly, but do not be too concerned with the details for now.
</p>
<p><pre class="code"><code>
use Libraries.Compute.Random
Random random
integer randomNumber = 0
randomNumber = random:RandomInteger(100)
</code></pre></p>
<p>
To get started, you will need to declare three variables.  Create the <tt>Random</tt> object and name it <strong>random</strong>.  Next, declare two new <tt>integer</tt> variables and name them <strong>counter</strong> and <strong>randomValue</strong>.  Initialize both of these integers to 0.
</p>
<p>
Write a loop that repeats a block of code 10 times.  The sample code of the <tt>repeat times</tt> loop is shown below:
</p>
<p><pre class="code"><code>
repeat 10 times
end
</code></pre></p>
<p>
Inside the body of the loop, use the <strong>randomValue</strong> variable to store a randomly generated <tt>integer</tt> value. Line 4 of the above <tt>Random</tt> example shows how this is done. In the next line of code, use the <strong>counter</strong> variable to keep track of each iteration of the loop by incrementing it by one each time the loop runs. Output the value of the counter and randomValue variables.The output should look similar to the following (the random values will be different):
</p>
<p><pre class="code"><code>
counter =  0; randomValue = 81
counter = 1; randomValue = 11
counter = 2; randomValue = 72
counter =  3; randomValue = 81
counter = 4; randomValue = 42
counter = 5; randomValue = 94
counter =  6; randomValue = 81
counter = 7; randomValue = 44
counter = 8; randomValue = 92
counter = 9; randomValue = 82
</code></pre></p>
<h2>Task 2: Repeat While Loop</h2>
<p>
The <tt>repeat while</tt> loop works much differently than the <tt>repeat times</tt> loop. <tt>repeat while</tt> loops work much like an if statement, but at the <tt>end</tt> keyword, Quorum evaluates and determines if the loop should go again. An everyday example might be sleeping while the sun is down.
</p>
<p>
Re-write the above <tt>repeat times</tt> loop using the <tt>repeat while</tt> loop structure. Hint: The loop should repeat itself as long as the <tt>counter</tt> variable is less than the number ten.
</p>
<p>
The output of this loop should be identical to the output of the <tt>repeat times</tt> loop above, but of course, the random values will be different.
</p>
<h2>Task 3: Repeat Until Loop</h2>
<p>
The final loop type in Quorum is the <tt>repeat until</tt> loop. <tt>repeat until</tt> functions much in the same way as <tt>repeat while</tt>, but the interpretation of your condition is slightly different. Our above everyday example was "sleeping while the sun is down." Another way to say this is "sleeping until the sun is up."
</p>
<p>
Write a <tt>repeat until</tt> loop that behaves identically to the <tt>repeat while</tt> loop constructed above. The output should be identical.
</p>
<h2>Task 4: Loops and Conditionals</h2>
<p>
You are going to practice the <tt>repeat until</tt> loop one more time. You will start by creating a loop that asks you for a number between 1 and 10. If the input is valid you will output "You entered a correct number!" and you will stop looping. If the input is not between 1 and 10 then you will output an error, "Error: The number is out of range" and you will continue looping until a valid number is entered. This type of loop is very common in programming. What you are doing here is commonly referred to as <i>input checking</i>, to protect users from making mistakes by entering invalid data.
</p>
<p>
The output should be similar to the following. Experiment by entering a wide range of numbers.
</p>
<p><pre class="code"><code>
Error: The number is out of range.
Error: The number is out of range.
You entered a correct number!
</code></pre></p>
<p>
To begin implementing this loop, you will add three variables, <strong>integerInput</strong> with a type of <tt>text</tt>, <strong>inputValue</strong> of type <tt>integer</tt> initialized to 0, and <strong>finished</strong> of type <tt>boolean</tt> initialized to false. Then begin the repeat until loop, you want to repeat until finished is true.
</p>
<p>
Within the loop body, ask the user to input a number and store that value in <strong>integerInput</strong>. Then cast that value to an integer to be stored in <strong>inputValue</strong>. After that, use a conditional statement to check if the user's input is between 1 and 10. If the user input, <strong>inputValue</strong>, is valid, set <strong>finished</strong> to true and output the successful message to the user. Otherwise, output the appropriate error message to the user.
</p>
<p>
Once you have the <tt>repeat until</tt> loop working, have the work checked. Once this loop is working try converting the loop into a <tt>repeat while</tt> loop. Hint: You will will need to use the <tt>not</tt> keyword.
</p>
<p>
Show your work to the instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>