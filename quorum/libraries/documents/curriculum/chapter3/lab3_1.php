<!--Edited by Brandon Spencer-->
<!--1/4/13-->
<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab3_1';
</script>

<h1>Lab 3.1: Conditionals</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to use conditionals to make decisions</li>
<li>Lexical scoping</li>
<li>How to translate problem statements in English to Quorum code</li>
</ul>
<h2>Overview</h2>
<p>
Conditionals are one of the most important aspects of programming.  Conditional statements allow the program to make decisions, changing the behavior of the software during runtime. So far, your code has run from top to bottom, with the same behavior each time. Conditional statements allow your program to alter its behavior depending on any condition you specify. Conditionals can be used for a variety of tasks such as determining if a number given by a user is less than the number 100, or if a nuclear reactor is about to go critical. The goal of this lab is to learn the fundamentals of conditional statements and learn how to write Quorum programs utilizing them.
</p>
<h2>Task 1: Basic Conditionals</h2>
<p>
Create a new blank Quorum project and name it <strong>Lab3_1</strong>.  In this task, you will concentrate on working with basic conditionals and decision making.
</p>
<p>
This program will allow the user to place an order for a given number of cars. The program will then make decisions based on the car dealership’s business rules and calculate the total price of the order.  Before you start working with conditionals, let’s add the code that gets the user input. Write code that prompts the user for the number of cars to purchase.  Get the user input and store it in a variable named <strong>carNumber</strong>.  Then, cast the value of the number of cars to an <tt>integer</tt> variable named <strong>numberOfCars</strong>.
</p>
<p>
Write an if statement that checks if the variable <strong>numberOfCars</strong> is equal to one.  In the body of the if-statement, declare a new <tt>integer</tt> and name it <strong>price</strong>.  Assign 20,000 to the <strong>price</strong> variable.  The example of this code is shown below:
</p>
<p><pre class="code"><code>
if numberOfCars = 1
    integer price = 20000
end
</code></pre></p>
<p>
Run the program and there should not be any errors.  The input dialog will appear.
</p>
<p>
Enter a value of 1 in the input dialog.  Nothing should have happened.  Add the code to tell Sodbeans to speak the price right after the line starting with <tt>integer price =</tt>. Run the program again. This time, notice that the number "20,000" is only spoken if you enter the number 1.
</p>
<p>
One problem with this solution is the user only knows the price of their order if they order a single car. To correct this, move the say statement you wrote to after the line starting with <tt>end</tt>. This puts the code outside of the if statement, so it will always execute. Run the program. Whoops!  Your are getting the following compiler errors:
</p>
<p><pre class="code"><code>
The variable ‘price’ has not been defined.  Did you spell the variable’s name correctly?
The operation “text implicitCast void,” is not allowed.
</code></pre></p>
<p>
The problem is, you defined the <tt>price</tt> variable inside of the if statement. In Quorum, this means it only exists inside the <i>scope</i> of the if statement, between the <tt>if</tt> and <tt>end</tt> keywords. This is what is called <i>lexical scoping</i>, and it is very useful to keep a program organized. In this case, however, you want the <tt>price</tt> variable to be accessible outside of the if statement's scope. To fix this, define the <tt>price</tt> variable above the if statement, and remove the <tt>integer</tt> keyword from the line inside the if statement. The compiler error you witnessed before should no longer be present. Before you run the program, add a statement to print the value of the <tt>price</tt> variable after the line containing the <tt>end</tt> keyword. When you run the program, the input dialog will appear as before.
</p>
<p>
Our program behaves properly when you enter a value of one. However, you have not yet considered what happens when you enter a value greater than one. Run the program and enter a larger value. Note the program's behavior. Since you did not account for this condition, the price is set to $0. When users want to purchase multiple cars, you need to multiply the price of $20,000 by the number of cars purchased. This is done by first using an else statement, as shown below. The else-statement should be executed only if the program skips the first if-statement, which occurs when <tt>numberOfCars</tt> does not equal 1. Our else statement looks like so:
</p>
<p><pre class="code"><code>
else
    price = 20000 * numberOfCars
end
</code></pre></p>
<p>
Run the program again, and note that the else statement is executed when you enter a value greater than one. Experiment by modifying the constant value 20000 inside the <tt>else</tt> block so that customers get a discount when purchasing multiple cars. What happens when you wish to purchase two cars? Justify to yourself that this behavior is different than if you had a constant price for all cases.
</p>
<h2>Task 2: More Work with Conditionals</h2>
<p>
What you have written so far isn’t enough for the car dealership to meet its business requirements.  Your will need to make changes to your current code to comply with the business rules.  Use the business rules below and translate the descriptions in plain English to the code.
</p>
<ol><li>If the client only requests one car, then the car should be sold at the price of $20,000.
</li><li>If the client wants to place an order for two cars at the same time, then the car can be sold at a slight discount.  Both cars should be sold at the price of $19,950 a peice.  The client should receive the total price of the order (2 * 19,950).
</li><li>If the client wants to purchase three cars, then each car is sold at the slightly larger discount.  Each car should be sold for $19,900 and the client should receive the total price of the order.
</li><li>If the client wants to order more than three cars, then each car should be sold at the price of $19,800.  The client will need to know the total price of all cars.
</li></ol>
<p>
Run the program and there should not be any errors.  Use the following output to test each run of the program to ensure that the code is executed correctly.
</p>
<p><pre class="code"><code>
The price for the order of 1 car(s) is $20000
The price for the order of 2 car(s) is $39900
The price for the order of 3 car(s) is $59700
The price for the order of 4 car(s) is $79200
The price for the order of 12 car(s) is $237600
</code></pre></p>
<p>
There are a few problems with the logic side of the program.  They will need to be fixed before the car dealership starts having some problems.
</p>
<p>
First, you need to implement additional checking on the user’s numerical input.  Run the program and enter any negative value in the input dialog. Notice that the price you give the user is a negative value. This would imply that the car dealership is giving away money! The application should not allow this to occur. In order to fix this problem, write an if statement immediately before the <tt>if</tt> block you created earlier. This if statement should determine if <tt>numberOfCars</tt> is less than one. If <tt>numberOfCars</tt> is less than one, set <tt>numberOfCars</tt> to 1.
</p>
<p>
Second, when a client tries to place an order for 1,000 cars, there is no way for the car dealership to supply this huge number of cars.  In order to prevent this problem from occurring, they decided to limit the purchase quantity of cars to 20.  In the same if statement, add an <tt>elseif</tt> clause that checks for an excessive number of cars and set the number of cars to 20.
</p>
<p>
Last but not least, the user does not know when a mistake has been made.  For example, if the user enters 100 instead of 10 for the number of cars, the program will automatically reset the number of cars from 100 to 20 without informing the user of the mistake.  To fix this problem, you will need to add code in the body of the if-statements for two problems that you have solved above.  Use the following output to have the program announce the user’s mistake with a reason for the error.
</p>
<p><pre class="code"><code>
Error: The number of cars must be betyouen 1 and 20. The value has been reset to 1. You entered -1
The price for the order of 1 car(s) is $20000
</code></pre></p>
<p><pre class="code"><code>
Error: The number of cars cannot be more than 20. The value has been reset to 20. You entered 100
The price for the order of 20 car(s) is $396000
</code></pre></p>
<p>
The program should be functional and ready for the clients to use after solving these problems.  Show your work to the instructor.
</p>

<?php include("../../include/footer.php"); ?>