<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 3.3 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<h1>Challenge Assignment: Updated Travel Reservation</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>More practice with conditionals and lexical scoping</li>
<li>More practice with loops</li>
<li>How to analyze a problem statement in plain English</li>
<li>How to modify an existing program</li>
</ul>
<h2>Overview</h2>
<p>
Modify the existing travel reservation program to make new changes to the logic and add to the program to comply with the growing demand at the travel agency.  The program checks for the user input.  The program then asks the user to enter the value again if an incorrect value has been entered.  The program also offers better options to give a client some flexibility with making a travel reservation.
</p>
<h2>Task 1</h2>
<p>
A small travel agency in St. Louis is growing and adding a couple of new destinations.  More and more clients flock to the travel agency to make reservations.  The travel agency needs to improve its existing program for better functionality and flexibility.
</p>
<p>
Before getting started with this assignment, create a new project and name it <b>NewTravel</b>.  Copy the existing code from the <b>Travel</b> project and paste it into a new project.  All code should be in the main class as before.  Run the program to make sure it works properly before moving on to make changes to the program.
</p>
<p>
Make changes to the code that handles invalid input by implementing the following:
</p>
<ul>
<li>If the user enters zero or less for the number of travelers, have the program prompt the user to enter the value again.  The program should keep prompting the user until the correct value is entered.  Otherwise, the correct value is entered and the program moves on.</li>
<li>If the user enters a value that is outside the range of destination numbers, have the program keep prompting the user to enter a value until it is within the range.  The user must enter a value between 1 and 5 to select a destination for the flight reservation.  Once the correct value is checked, the program will store the city name and price as before.  Otherwise, the program will keep asking the user to enter the correct value.</li>
<li>When the user is asked to include the hotel option in the package, the user must enter Y, y, N, or N.  If the user enters Y or y, the program will ask the user a couple of hotel questions.  If the user enters N or n, the hotel reservation will not be made.  If the user enters an invalid value (other than Y, y, N, or n), the program will keep prompting the user to enter the correct value.</li>
<li>For the hotel type prompt, if the user enters a value that is outside the range of the hotel type number, the program will keep prompting the user to enter a value until it is within the range.  The user must enter a value between 1 and 3 to select a hotel type.  Once the correct value is checked, the program will store the hotel type and price as before.  Otherwise, the program will keep asking the user for the correct value.</li>
<li>For the number of hotel rooms, if the user enters zero or less, the program will keep prompting the user to enter the correct value.  Otherwise, the correct value is entered and the program moves on.</li>
<li>For the number of nights to stay at the hotel, if the user enters zero or less, the program will keep prompting the user to enter the correct value.  Otherwise, the correct value is entered and the program moves on.</li>
</ul>
<p>
The travel agency wants to offer better flexibility by allowing the client an option of booking any reservation or just opting out of the program.  Make changes to the program in the following ways:
</p>
<ul>
<li>The client is given an option to book a flight.  If the client does not want to make a reservation for flights, the program will skip the flight reservation and move on to the hotel reservation.</li>
<li>The new logic will allow the client to make a reservation for one of the following choices: 1) flights only, 2) hotel only, 3) both flights and hotel, and 4) no reservation at all.  For example, the process will look like this:</li>
<ul>
<li>The client is asked to make a reservation for flights.</li>
<li>The client says yes and makes a reservation.</li>
<li>The client is asked to make a reservation for hotel.</li>
<li>The client says no and skips it.</li>
</ul>
</ul>
<p>
Review the following logic to ensure that the program will provide the appropriate output to the client:
</p>
<ul>
<li>If the client makes a reservation for both flights and hotel, the price and information for flights and hotel will be provided.</li>
<li>If the client books flights only, the flight details will be provided.</li>
<li>If the client books hotel only, the hotel details will be provided.</li>
<li>If the client does not make a reservation for flights and hotel, the program will announce that no reservation has been made.</li>
</ul>
<p>
The travel agency has added a couple of new destinations, so add the following destinations to the list:
</p>
<table class="table">
<tr><td><strong>Destination</strong></td><td><strong>Round Trip Fare</strong>
</td></tr><tr><td>Honolulu</td><td>$650.00
</td></tr><tr><td>New York City</td><td>$290.00
</td></tr><tr><td>San Francisco</td><td>$359.00
</td></tr></table>
<h2>Sample Output</h2>
<p>
The input dialog statements are included in the sample output.
</p>
<p><pre class="code"><code>
Welcome to the travel agency. You offer travel packages for your vacation destinations.
How many people will be traveling? 
0
Error: The number of travelers must be one or more. Please try again. You entered 0
How many people will be traveling? 
2
Select one of the destinations to book a flight: 1 - Chicago ($200), 2 - Honolulu ($650), 3 - Los Angeles ($360), 4 - Miami ($320), 5 - New York City ($290), 6 - Orlando ($310), 7 - San Francisco (5359), 8 - Seattle ($330)
Would you like to make a reservation for flights (Y/N)? 
w
Error: The input must be Y, y, N, or n. Please try again. You entered w
Would you like to make a reservation for flights (Y/N)? 
Y
Enter the destination number: 
9
Error: The destination number must be between 1 and 8. Please try again. You entered 9
Enter the destination number: 
Would you like to include hotel in your package (Y/N)? 
yy
Error: The input must be Y, y, N, or n. Please try again. You entered yy
Would you like to include hotel in your package (Y/N)? 
y
What kind of hotel would you like to book? 1 - Motel ($49.99), 2 - Standard Hotel ($98.50), 3 - Luxury Hotel ($199.75
Enter the hotel number: 
5
Error: The hotel type number must be between 1 and 3. Please try again. You entered 5
Enter the hotel number: 
3
Enter a number of hotel rooms: 
-1
Error: The number of hotel room must be one or more. Please try again. You entered -1
Enter a number of hotel rooms: 
1
Enter a number of nights to stay at the hotel: 
0
Error: The number of nights must be one or more. Please try again. You entered 0
Enter a number of nights to stay at the hotel: 
5
The reservation for flights and hotel has been booked.
Total flight price to Honolulu for 2 traveler(s) is $1300.0
Total hotel price for 1 room(s) at Luxury Hotel for 5 night(s) is $998.75
Total package price for flights and hotel is $2298.75
</code></pre></p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>