<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 4.3 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<h1>Lab 4.3: Trip Calculator</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to implement actions with parameters and return types</li>
<li>How to call actions by passing in arguments and storing return values</li>
<li>Action reusability</li>
</ul>
<h2>Overview</h2>
<p>
The car trip calculator will calculate the trip time and the total gas price of the trip for the user.  The user will enter the average speed of the car and the trip distance to calculate the total time and price of the trip.  In this lab, you will create a series of actions to help you calculate time in hours and minutes and the total gas price of a specific vehicle.  You will also make calls to the actions to tell the programs to perform a specific task and get the value returned by the action.
</p>
<h2>Task 1: Creating Actions with Parameters and Return Types</h2>
<p>
Start up the Sodbeans program.  Create a new project and name it <strong>TripCalculator</strong>.  Don’t forget to select the Quorum Application project type.  In this task, you will add variables in the <code>Main</code> action that will get the return values from actions and set up five additional actions.  The actions will help you calculate the trip time in hours and minutes and perform the gas price calculation.
</p>
<p>
Inside the <code>Main</code> action, add the following variables:
</p>
<ul><li><strong>userInput</strong> is used to get user inputs when the user is prompted to enter a value.
</li><li><strong>carSpeed</strong> is an integer that gets the average speed of the car in terms of miles per hour.
</li><li><strong>tripLength</strong> is a number that gets the distance of the trip in terms of miles.
</li><li><strong>tripTime</strong> is a number that gets and keeps track of the calculated trip time in terms of hours.
</li></ul><p>
Before you can start adding actions to your program, you want to take a quick look at how the actions with parameters and return types are used.  The example of the action with two parameters and a return type is shown below:
</p>
<p><pre class="code"><code>
action DoExample(integer a, integer b) returns integer
    integer total = a + b
    return total
end
</code></pre></p>
<p>
Let’s add five actions that will calculate the trip time and the total gas price.  The actions should be defined as follows:
</p>
<ul><li><strong>GetTripTime</strong> accepts two parameter values of an <code>integer</code> and a <code>number</code>.  Name an <code>integer</code> <strong>speed</strong> and a <code>number</code> <strong>distance</strong>.  This action returns a <code>number</code>.  Within the <code>GetTime</code> action, calculate the total trip time in terms of hours by dividing the distance by the speed of the car.  This action should be able to return the total number of hours.
</li><li><strong>GetTimeInMinutes</strong> has one parameter value of a <code>number</code>.  Give the parameter a name, <strong>hours</strong>.  The action returns an <code>integer</code>.  Inside the action, calculate the total number of minutes by multiplying the total number of hours and 60 minutes.  This action should be able to return the total number of minutes.
</li><li><strong>GetTotalHours</strong> gets the parameter value of a <code>number</code>.  Give the parameter a name, <strong>hours</strong>.  The action returns an <code>integer</code>.  Inside the action, calculate the total hours by dividing the total number of hours by 60 minutes.  This action returns the total number of hours in terms of the hour number only.  For example, when the total trip time is 2.6, the action will return a value of 2 for the hour value.  More details will be explained n the next task.
</li><li><strong>GetRemainingMinutes</strong> accepts the parameter value of a <code>number</code>.  Name the parameter <strong>hours</strong>.  The action returns an <code>integer</code>.  Inside the action, perform the calculation to find the number of minutes that are outside of the complete hours calculated in the <code>GetTotalHours</code> action.  This action returns the remaining number of minutes; for example, 2.5 hours will get 30 minutes after the number of hours has been calculated.  More details will be explained in the next task. (Hint: use the <code>mod</code> operator to accomplish the calculation).
</li><li><strong>CalculateGasPrice</strong> accepts two parameter values of a <code>number</code> and an <code>integer</code>.  Name a <code>number</code> <strong>distance</strong> and an <code>integer</code> <strong>milesPerGallon</strong>.  The action returns a <code>number</code>.  Inside the action, calculate the total gas price by dividing distance and miles per gallon, and then multiply it by the gas price for each gallon.  The price for gas per gallon is $3.59.  The action should be able to return the total gas price for the duration of the trip.
</li></ul><p>
Make sure that the program runs without any problem.  Nothing should have happened since you have not made any action calls yet.
</p>
<h2>Task 2: Calling Actions</h2>
<p>
It’s time to give the <code>Main</code> action some attention!  Before you can get started with action calls, let’s take a quick look at how to call an action that has parameters and a return type.
</p>
<p><pre class="code"><code>
integer total = DoExample(2, 3)
</code></pre></p>
<p>
Before the first action call can be made, you are going to have the user enter the average speed of the car and the distance of the trip.  Do the following to achieve those user requests:
</p>
<ul><li>The user must enter the value of between 1 and 100 miles per hour for the average speed of the car.  Otherwise, the program will ask the user to enter the correct value again.  The reason is that this trip calculator is strictly designed for the ground vehicles only.  There is no way that you could drive a car at the speed of 500 miles per hour!
</li><li>The user must enter the value of at least one mile for the length of the trip.  Otherwise, the program will ask the user to enter the correct value again.  It would be silly to start a trip without moving around.
</li><li>The user can choose one of the available vehicles to calculate the total gas price.  First, inform the user of the vehicle choice: 1) Jeep, 2) Pickup Truck, 3) Sedan, and 4) Van.  The program should accept an <code>integer</code> from the user input and re-prompt the user to enter the correct value if a value is outside the range.
</li></ul><p>
Now that you have received the user inputs of the average speed, the trip distance, and the type of car, it’s time to invoke actions!  Call the <code>GetTripTime</code> action to get the total number of hours for the trip.  Then, call the <code>GetTimeInMinutes</code> to get the total number of minutes for the trip.
</p>
<p>
The program then should determine which vehicle to compute the total gas price.  Invoke the <code>CalculateGasPrice</code> action to calculate the total gas price of a specific vehicle based on its mile per gallon.  The table that shows each vehicle with its respective miles per gallon is shown below:
</p>
<table class="table">
<tr><td><strong>Vehicle Type</strong></td><td><strong>Miles Per Gallon</strong>
</td></tr><tr><td>Jeep</td><td>28
</td></tr><tr><td>Pickup Truck</td><td>24
</td></tr><tr><td>Sedan</td><td>40
</td></tr><tr><td>Van</td><td>29
</td></tr></table>
<p>
You can notice that the same action has been re-used by passing different arguments in the action.  This ensures that you do not have to re-type or copy paste the same calculation.
</p>
<p>
You want to call two more actions.  Those actions are used to help you get the total trip time in the hour and minute format.  For example, the trip will take you about 2 hours and 36 minutes to reach your destination.  Invoke both actions that you have not used yet to get the trip time in a user friendly way.
</p>
<h2>Sample Output</h2>
<p>
The output should match the sample output shown below.
</p>
<p><pre class="code"><code>
Welcome to the Car Trip Calculator.
Enter the average car speed in miles per hour: 
500
Error: You have to drive the car at the speed of between 1 and 100. Please try again. You entered 500
Enter the average car speed in miles per hour: 
70
Enter the length of the trip in miles: 
0
Error: The distance needs to be at least one mile. Please try again. You entered 0.0
Enter the length of the trip in miles: 
201
Choose one of the vehicles: 1 - Jeep, 2 - Pickup Truck, 3 - Sedan, 4 - Van
Enter the vehicle number: 
5
Error: The vehicle number must be between 1 and 4. Please try again. You entered 5
Enter the vehicle number: 
3
The trip will take 2 hours and 52 minutes
The total gas price for the trip is $18.03975
</code></pre></p>
<p>
Show the work to the instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>