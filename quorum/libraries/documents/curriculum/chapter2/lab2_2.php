<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 2';
</script>

<h1>Lab 2.2: Math, Print and User Input</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to understand the following concepts:
</p>
<ul>
<li>Basic mathematical operations in Quorum</li>
<li>Using the print statement and the Sodbeans Output window</li>
<li>Getting input from the user, and using it in computations</li>
<li>Analyzing problem statements and translating them into Quorum code</li>
</ul>
<h2>Overview</h2>
<p>
In this lab we will write a program in Quorum that performs basic mathematical operations and solves written math problems. In this lab, we will demonstrate how to work with numbers by performing addition, subtraction, multiplication, division, and remainder operations in Quorum. We will also analyze written problems and solve them using Quorum code.
</p>
<h2>Task 1: Basic Math</h2>
<p>
For task 1, we will concentrate on performing basic mathematical operations in Quorum. Then we will move onto solving problem statements in task 2. Let's start by opening Sodbeans and creating a new blank Quorum project. Name the project <b>Lab2_2</b>.
</p>
<p>
Declare two <tt>integer</tt> variables <strong>a</strong> and <strong>b</strong>.  Initialize <strong>a</strong> to 7 and <strong>b</strong> to 4.  Next, add two <tt>number</tt> variables <strong>c</strong> and <strong>d</strong>.  Then initialize <strong>c</strong> to 8.5 and <strong>d</strong> to 9.2.  We will use these variables to perform mathematical operations and later print the results to the Sodbeans Output window. 
</p>
<p>
Starting with addition, create the following two code statements:
</p>
<ul>
<li>The variable <b>firstAddition</b> should be assigned the result of adding a and b.</li>
<li>The variable <b>secondAddition</b> should be assigned the result of adding a, b, c, and d.</li>
</ul>
<p>
Determine the appropriate types for the firstAddition and secondAddition variables.
</p>
<p>
Once the addition has occurred, print each of these variables to the Sodbeans Output window. This can be done by using two <strong><tt>print</tt></strong> statement to output the value of firstAddition and secondAddition. If you run your program(F6), the Sodbeans Output window(CTRL + 5) should have, assuming there are no errors in our code, the following output listed:
</p>
<p><pre class="code"><code>
firstAddition: 11
secondAddition: 28.7
</code></pre></p>
<p>
Notice the variable types of all of the variables in the statement assigning to the secondAddition variable.
</p>
<p>
We will apply the same concepts to the other mathematical operations. Let's move onto subtraction by doing the following:
</p>
<ul>
<li>The variable <b>firstSubtraction</b> is assigned the result of subtracting a and b.</li>
<li>The variable <b>secondSubtraction</b> is assigned the result of subtracting a, b, c, and d in that order.</li>
</ul>
<p>
Once the subtraction has occurred, print each of these variables to the Sodbeans Output Window. If you run your program(F6) again the Sodbeans Output Window(CTRL + 5) should have, assuming there are no errors in our code, the following output listed:
</p>
<p><pre class="code"><code>
firstAddition: 11
secondAddition: 28.7
firstSubtraction: 3
secondSubtraction: -14.7
</code></pre></p>
<p>
Moving onto multiplication, create the following statements in code:
</p>
<ul>
<li>The variable <b>firstProduct</b> is assigned the result of multiplying a and b.</li>
<li>The variable <b>secondProduct</b> is assigned the result of multiplying a, b, c, and d.</li>
</ul>
<p>
Once the multiplication has occurred, print each of these variables to the Sodbeans Output Window. If you run your program(F6) again the Sodbeans Output window(CTRL + 5) should have, assuming there are no errors in our code, the following output listed:
</p>
<p><pre class="code"><code>
First Addition: 11
Second Addition: 28.7
First Subtraction: 3
Second Subtraction: -14.7
First Product: 28
Second Product: 2189.6
</code></pre></p>
<p>
Now we'll move onto division by creating the following statements:
</p>
<ul>
<li>The variable <b>firstQuotient</b> is assigned the result of dividing a by b.</li>
<li>The variable <b>secondQuotient</b> is assigned the result of dividing c by d.</li>
</ul>
<p>
Once the division has occurred print each of these variables to the Sodbeans Output Window. If you run your program(F6) again the Sodbeans Output window(CTRL + 5) should have, assuming there are no errors in our code, the following output:
</p>
<p><pre class="code"><code>
First Addition: 11
Second Addition: 28.7
First Subtraction: 3
Second Subtraction: -14.7
First Product: 28
Second Product: 2189.6
First Quotient: 1
Second Quotient: 0.923913043478261
</code></pre></p>
<p>
Why was firstQuotient assigned the value of 1? Consider the type of the firstQuotient variable, and the types of the variables involved in the division. Change the firstQuotient statement so that is has the correct value of 1.75. Why were the modifications you made necessary to achieve the value 1.75? Our output should now look like the following:
</p>
<p><pre class="code"><code>
First Addition: 11
Second Addition: 28.7
First Subtraction: 3
Second Subtraction: -14.7
First Product: 28
Second Product: 2189.6
First Quotient: 1.75
Second Quotient: 0.923913043478261
</code></pre></p>
<p>
The final mathematical operation available in Quorum is the modulus, or remainder, operation. Create the following statements in code:
</p>
<ul>
<li>The variable <b>firstRemainder</b> gets the remainder of dividing a by b.</li>
<li>Do the same for the variable <b>secondRemainder</b> with the remainder of dividing c by d.</li>
</ul>
<p>
Print these values to the output window. We will see the following output:
</p>
<p><pre class="code"><code>
First Addition: 11
Second Addition: 28.7
First Subtraction: 3
Second Subtraction: -14.7
First Product: 28
Second Product: 2189.6
First Quotient: 1.75
Second Quotient: 0.923913043478261
First Remainder: 3
Second Remainder: 0.6999999999999993
</code></pre></p>
<p>
Let’s wrap up what we have done with the mathematical operations.  Write code that combines all four operations into one statement:  addition, subtraction, multiplication, and division.  Name a variable <b>result</b> and perform the following operations:
</p>
<p><pre class="code"><code>
(a + b) * (c - b) + (d / b)
</code></pre></p>
<p>
Compute this answer for yourself, given the values we have assigned to the variables a, b, c, and d. Then, check your answer by adding another print statement for the result variable. The output should be the following:
</p>
<p><pre class="code"><code>
First Addition: 11
Second Addition: 28.7
First Subtraction: 3
Second Subtraction: -14.7
First Product: 28
Second Product: 2189.6
First Quotient: 1.75
Second Quotient: 0.923913043478261
First Remainder: 3
Second Remainder: 0.6999999999999993
More Math: 51.8
</code></pre></p>
<h2>Task 2: User Input</h2>
<p>
When we write programs, we don't always merely perform computation on data we already know, as in the first part of this lab. Most of the time, we need to get input from the user to perform our calculations. As an example, a desktop calculator is a program taking input from the user (via the keypad), and providing output (the answer to the equation we entered).
</p>
<p>
We may get input from the user in Quorum using the <tt>input</tt> keyword. The code below asks the user for their name; inside the parenthesis, we tell the user what information we are requesting. Copy this code into your Sodbeans project, and then have Sodbeans speak the contents of the variable firstName using <tt>say</tt>.
</p>
<p><pre class="code"><code>
text firstName = input(&quot;Please enter your first name:&quot;)
</code></pre></p>
<p>
When we run this program, an input dialog will appear prompting us to &quot;Please enter your name.&quot; Enter your name and press Enter, or click the &quot;OK&quot; button. Sodbeans will speak the text you entered into the input dialog.
</p>
<p>
We can do more than ask for input such as a name. We can also request numerical values. Let's create a second input statement that asks us to enter an integer value. The code should look something like the following:
</p>
<p><pre class="code"><code>
text numberOfEggsInput = input(&quot;How many eggs do you have?&quot;)
</code></pre></p>
<p>
Notice, input will always be of type <tt>text</tt>. However, we can convert the <tt>text</tt> value of the <tt>numberOfEggsInput</tt> variable to any other type we desire, such as <tt>integer</tt> or <tt>number</tt>. We do this using the <tt>cast</tt> statement, as below. Here we desire to have the number of eggs as a whole number, so we will use the <tt>integer</tt> type.
</p>
<p><pre class="code"><code>
integer numberOfEggs = cast(integer, numberOfEggsInput)
</code></pre></p>
<p>
Now write the Quorum code to speak the numberOfEggs result. It should say "You have this many eggs: " followed by the <tt>numberOfEggs</tt> value.
</p>
<h2>Task 3: Problem Statements</h2>
<p>
There are real-world problems that we can solve using math. In this lab we will focus on analyzing problem statements and turn them into the Quorum code. Before we jump into problem-solving situations, we will need to have a new project in Sodbeans.  Create a new project and name it <strong>Lab2_2_1</strong>.  Be sure to comment and label each solution with the problem statement number (comments can be created by typing <tt>//</tt> in front of the comment we are making).  For each problem statement, assign values to the appropriate variables and have your program speak the results.
</p>
<ol><li>A car travels 510 miles on a freeway for 7.5 hours. Given that speed = distance / time, write code to calculate the car’s speed in terms of miles per hour.
</li></ol><ol start="2"><li>A class goes on a field trip to the museum.  A school bus can hold up to 40 passengers.  A class of 25 students, a teacher, and two teacher assistants board the bus for the trip.  Write code to calculate what percentage of the bus is filled.
</li></ol><ol start="3"><li>Someone has ordered a pizza for you, free of charge. However, before you can eat the pizza, you are asked to find the area of the top of the pizza box. The box is 12 inches by 14 inches. Write a program that outputs the area of the pizza box in the form, "The pizza box, in square inches, has an area of: "
</li></ol><ol start="4"><li>A thrift store manager wishes to write a program that allows him to calculate the total cost of an order of shirts. He wishes to enter the name of a shirt, the number of shirts, and the price per shirt. Write code that accepts user input for the shirt name, the number of shirts, and the price of each shirt. The program should calculate and output the total cost of the order using either the <tt>print</tt> or <tt>say</tt> statement. For testing purposes, use the input: shirt name = Navy Polo Shirt, number of shirts = 11, and price = $8.99.  The result should match the following output:
</li></ol>
<p><pre class="code"><code>
The total cost of 11 Navy Polo Shirt items is $98.89. Each shirt costs $8.99.
</code></pre></p>

<?php include("../../include/footer.php"); ?>