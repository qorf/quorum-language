<!--Edited by Brandon Spencer-->
<!--1/2/13-->
<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 2';
</script>

<h1>Lab 2.2: Math, Print and User Input</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to use basic mathematical operations in Quorum</li>
<li>How to use the print statement and the Sodbeans Output window</li>
<li>How to get input from the user, and use it in computations</li>
<li>How to analyze problem statements and translate them into Quorum code</li>
</ul>
<h2>Overview</h2>
<p>
In this lab you will write a program in Quorum that performs basic mathematical operations and solves written math problems. You will demonstrate how to work with numbers by performing addition, subtraction, multiplication, division, and remainder operations in Quorum. You will also analyze written problems and solve them using Quorum code.
</p>
<h2>Task 1: Basic Math</h2>
<p>
For task 1, you will concentrate on performing basic mathematical operations in Quorum. Then, you will move onto solving problem statements in task 2.  Start by opening Sodbeans and creating a new blank Quorum project. Name the project <b>Lab2_2</b>.
</p>
<p>
Declare two <tt>integer</tt> variables <strong>a</strong> and <strong>b</strong>.  Initialize <strong>a</strong> to 7 and <strong>b</strong> to 4.  Next, add two <tt>number</tt> variables <strong>c</strong> and <strong>d</strong>.  Then initialize <strong>c</strong> to 8.5 and <strong>d</strong> to 9.2.  You will use these variables to perform mathematical operations and later print the results to the Sodbeans Output window. 
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
Notice the variable types of all of the variables in the statement assigning to the secondAddition variable. The variable <b>secondAdditions</b> holds a number with a decimal while <b>firstAddition</b> is only a variable.  When adding the values of <b>a</b>, <b>b</b>, <b>c</b>, and <b>d</b>, you are adding together two different types: an integer type and a number type.  When adding these together in Quorum, the integer types are automatically
type cast to a number type, even if you don't see it. So for example, 7 becomes 7.0.  Hoyouver, if variable <b>secondAddition</b> youre to be of type integer, you would get an error, since it's impossible to assign a number value to a variable that is type integer.  Thus the need for explicit casting.
</p>
<p>
You will apply the same concepts to the other mathematical operations. Now move onto subtraction by doing the following:
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
Now move onto division by creating the following statements:
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
Why was firstQuotient assigned the value of 1? Consider the type of the firstQuotient variable, and the types of the variables involved in the division. Change the firstQuotient statement so that is has the correct value of 1.75. Why youre the modifications you made necessary to achieve the value 1.75? Your output should now look like the following:
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
Print these values to the output window. You will see the following output:
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
Let’s wrap up what you have done with the mathematical operations.  Write code that combines all four operations into one statement:  addition, subtraction, multiplication, and division.  Name a variable <b>result</b> and perform the following operations:
</p>
<p><pre class="code"><code>
(a + b) * (c - b) + (d / b)
</code></pre></p>
<p>
Compute this ansyour for yourself, given the values you have assigned to the variables a, b, c, and d. Then, check your ansyour by adding another print statement for the result variable. The output should be the following:
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
When you write programs, you don't always merely perform computation on data you already know, as in the first part of this lab. Most of the time, you need to get input from the user to perform your calculations. As an example, a desktop calculator is a program taking input from the user (via the keypad), and providing output (the ansyour to the equation you entered).
</p>
<p>
You may get input from the user in Quorum using the <tt>input</tt> keyword. The code below asks the user for their name; inside the parenthesis, you tell the user what information you are requesting. Copy this code into your Sodbeans project, and then have Sodbeans speak the contents of the variable firstName using <tt>say</tt>.
</p>
<p><pre class="code"><code>
text firstName = input(&quot;Please enter your first name:&quot;)
</code></pre></p>
<p>
When you run this program, an input dialog will appear prompting us to &quot;Please enter your name.&quot; Enter your name and press Enter, or click the &quot;OK&quot; button. Sodbeans will speak the text you entered into the input dialog.
</p>
<p>
You can do more than ask for input such as a name. You can also request numerical values. Let's create a second input statement that asks us to enter an integer value. The code should look something like the following:
</p>
<p><pre class="code"><code>
text numberOfEggsInput = input(&quot;How many eggs do you have?&quot;)
</code></pre></p>
<p>
Notice, input will always be of type <tt>text</tt>. However, you can convert the <tt>text</tt> value of the <tt>numberOfEggsInput</tt> variable to any other type you desire, such as <tt>integer</tt> or <tt>number</tt>. You do this using the <tt>cast</tt> statement, as below. Here, you desire to have the number of eggs as a whole number, so you will use the <tt>integer</tt> type.
</p>
<p><pre class="code"><code>
integer numberOfEggs = cast(integer, numberOfEggsInput)
</code></pre></p>
<p>
Now write the Quorum code to speak the numberOfEggs result. It should say "You have this many eggs: " followed by the <tt>numberOfEggs</tt> value.
Next, modify <b>a</b>, <b>b</b>, <b>c</b>, and <b>d</b> to use an input statement to obtain their values from the user.  Remember to prompt the user for integer values for <b>a</b> and <b>b</b>, and prompt the user for decimal values for <b>c</b> and <b>d</b>.
</p>
<h2>Task 3: Problem Statements</h2>
<p>
There are real-world problems that you can solve using math. In this lab you will focus on analyzing problem statements and turn them into the Quorum code. Before you jump into problem-solving situations, you will need to have a new project in Sodbeans.  Create a new project and name it <strong>Lab2_2_1</strong>.  Be sure to comment and label each solution with the problem statement number (comments can be created by typing <tt>//</tt> in front of the comment you are making).  For each problem statement, assign values to the appropriate variables and have your program speak the results. Get user input for the values listed
in each problem, indicated by <b>integer value</b> or <b>number value</b> and remember to cast them to the appropriate type if needed.
</p>
<ol><li>A car travels <b>integer value</b> miles on a freeway for <b>number value</b> hours. Given that speed = distance / time, write code to calculate the car’s speed in terms of miles per hour.
</li></ol><ol start="2"><li>A class goes on a field trip to the museum.  A school bus can hold up to <b>integer value</b> passengers.  A class of <b>integer value</b> students, a teacher, and two teacher assistants board the bus for the trip.  Write code to calculate what percentage of the bus is filled.
</li></ol><ol start="3"><li>Someone has ordered a pizza for you, free of charge. However, before you can eat the pizza, you are asked to find the area of the top of the pizza box. The box is <b>integer value</b> inches by <b>integer value</b> inches. Write a program that outputs the area of the pizza box in the form, "The pizza box, in square inches, has an area of: "
</li></ol><ol start="4"><li>A thrift store manager wishes to write a program that allows him to calculate the total cost of an order of shirts. He wishes to enter the name of a shirt, the number of shirts, and the price per shirt. Write code that accepts user input for the shirt name, the number of shirts, and the price of each shirt. The program should calculate and output the total cost of the order using either the <tt>print</tt> or <tt>say</tt> statement. For testing purposes, use the input: shirt name = Navy Polo Shirt, number of shirts = 11, and price = $8.99.  The result should match the following output:
</li></ol>
<h2>Sample Output</h2>
<p>
When run, your program should look similar to the following:
</p>
<p><pre class="code"><code>
Enter the number of miles a car has to travel:
100
Enter the number of hours he has to drive for:
1.5
The car was traveling at 66.66 miles per hour.
How many passengers can a school bus hold?
50
How big is the class getting on the bus?
25
The bus would be 56% full with a class size of 25, a teacher, and two teaching assistants.
Enter the width of of a pizza box(as a whole number)
10
Enter the length of a pizza box(as a whole number)
15
The pizza box, in inches, has an area of 150.
Enter the name of a shirt:
Navy Polo Shirt
Enter how many shirts to order:
11
Enter the cost of each shirt:
8.99
The total cost of 11 Navy Polo Shirt items is $98.89. Each shirt costs $8.99.
</code></pre></p>

<?php include("../../include/footer.php"); ?>