<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assingment 3.1 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>

<h1>Short Assignment: Travel Reservation</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>More practice with conditionals</li>
<li>More practice with lexical scoping</li>
<li>How to analyze problem statements in plain English</li>
</ul>
<h2>Overview</h2>
<p>
  Write a small travel reservation program for a travel agency to book vacation packages for clients.  The program will ask the user to pick one of the offered destinations for flights and have an option of booking a hotel room.  At the end of the reservation process provide the user with all of the booking information. Create a new project and name it <b>Travel</b>.
</p>
<h2>Description</h2>
<p>
A small travel agency in St. Louis offers vacation packages to clients in the St. Louis area.  Since the travel agency is small, it offers only a small number of destinations and hotels at the selected destination.  Write a program to help the travel agency make flight and hotel reservations for their clients.
</p>
<p>
The travel agency wants to know the number of travelers planning to go on the trip. Prompt the client for the number of people who will be traveling.  The program should only accept the input value of one or more travelers.  There is no limit for the number of travelers. If the client enters a value of zero or less, the program should reset the value to one traveler and inform the client of an error.
</p>
<p>
After the user has entered the number of travelers the program should provide a list of offered destinations with a price(per person) for each destination for the client to book a round trip flight.  Prompt the client to select a destination number by entering an <code>integer</code> value.  The program will determine which destination to get and store the correct city name and price.  A list of cities with prices is shown in the table below:
</p>
<table class="wiki">
<tr><td><strong>Destination</strong></td><td><strong>Round Trip Fare</strong>
</td></tr><tr><td>Chicago</td><td>$200.00
</td></tr><tr><td>Los Angeles</td><td>$360.00
</td></tr><tr><td>Miami</td><td>$320.00
</td></tr><tr><td>Orlando</td><td>$310.00
</td></tr><tr><td>Seattle</td><td>$330.00
</td></tr></table>
<p>
<p>
The program should also test if the client entered the correct value for the destination.  If the client enters a value that is not within the range of offered destinations, the program will assume that the client does not want any of the listed destinations. 
</p>
<p>
After obtaining the client’s flight reservation request, the program will calculate a total price of the flight itinerary with the round trip fare and a number of travelers.
</p>
<p>
If the flight reservation has been made successfully, the program will offer the client an option of booking a hotel room.  If the flights were not booked, the hotel option should be skipped.  The client will be asked to book a hotel room or opt out of the hotel option.  The program should ask the client the question, “Would you like to include hotel in your package (Y/N)?”  Both upper and lower cases (Y, y, N, n) are acceptable.  If the client chooses to include hotel in the package, the program will proceed and ask the client a couple of questions.  If the client does not want to include hotel in the package, the hotel option is not included.  If an incorrect value is entered, no hotel reservation will also be made.
</p>
<p>
When the client wants to include hotel in the package, the program will do the following:
</p>
<ul>
<li>Ask the client to select one of the three following hotel types with a price for each to get and store the hotel type and price:</li>
</ul>
<table class="table">
<tr><td><strong>Hotel Type</strong></td><td><strong>Price</strong>
</td></tr><tr><td>Motel</td><td>$49.99
</td></tr><tr><td>Standard Hotel</td><td>$98.50
</td></tr><tr><td>Luxury Hotel</td><td>$199.75
</td></tr></table>
<ul><li>If the client does not enter a correct <code>integer</code> value for any of three hotel types, the program will automatically reset the hotel type to Standard Hotel and price to $98.50.
</li><li>Ask the client to enter a number of hotel rooms.  If the input is zero or less, then the number of hotel rooms will be reset to one.
</li><li>Ask the client to enter a number of nights to stay at the hotel.  If the input is zero or less, then the number of nights will be reset to one.
</li><li>Calculate the total hotel price with the hotel price, a number of rooms, and a number of nights.
</li></ul><p>
At the end of the program, the program will determine if the reservation for flights and/or hotel is booked.  The program should have the following logic:
</p>
<ul><li>If both flights and hotel have been booked, the program will calculate the total price of flights and hotel and output the package details with flights and hotel information and the total price.
</li><li>If the flights were only booked and the hotel option was not chosen, the program will output the flight itinerary details with information and price.
</li><li>If the flights were not booked, then the program will announce that no reservation has been made.
</li></ul>
<h2>Sample Output</h2>
<p>
The input dialog statements are included in the sample output.  They are identified as [Input Dialog].
</p>
<p>
<b>Flights and Hotel Reservation</b>
</p>
<p><pre class="code"><code>
Welcome to the travel agency. You offer travel packages for your vacation destinations.
[Input Dialog] How many people will be traveling? 4
Select one of the destinations to book a flight: 1 - Chicago ($200), 2 - Los Angeles ($360), 3 - Miami ($320), 4 - Orlando ($310), 5 - Seattle ($330)
[Input Dialog] Enter the destination number: 3
[Input Dialog] Would you like to include hotel in your package (Y/N)? Y
What kind of hotel would you like to book? 1 - Motel ($49.99), 2 - Standard Hotel ($98.50), 3 - Luxury Hotel ($199.75
[Input Dialog] Enter the hotel number: 2
[Input Dialog] Enter a number of hotel rooms: 1
[Input Dialog] Enter a number of nights to stay at the hotel: 3
The reservation for flights and hotel has been booked.
Total flight price to Miami for 4 traveler(s) is $1280.0
Total hotel price for 1 room(s) at Standard Hotel for 3 night(s) is $295.5
Total package price for flights and hotel is $1575.5
</code></pre></p>
<p>
<b>Flights Reservation Only</b>
</p>
<p><pre class="code"><code>
Welcome to the travel agency. You offer travel packages for your vacation destinations.
[Input Dialog] How many people will be traveling? 3
Select one of the destinations to book a flight: 1 - Chicago ($200), 2 - Los Angeles ($360), 3 - Miami ($320), 4 - Orlando ($310), 5 - Seattle ($330)
[Input Dialog] Enter the destination number: 5
[Input Dialog] Would you like to include hotel in your package (Y/N)? n
The reservation for flights has been booked.
Total flight price to Seattle for 3 traveler(s) is $990.0
</code></pre></p>
<p>
<b>No Reservation</b>
</p>
<p><pre class="code"><code>
Welcome to the travel agency. You offer travel packages for your vacation destinations.
[Input Dialog] How many people will be traveling? 1
Select one of the destinations to book a flight: 1 - Chicago ($200), 2 - Los Angeles ($360), 3 - Miami ($320), 4 - Orlando ($310), 5 - Seattle ($330)
[Input Dialog] Enter the destination number: 6
No reservation has been made. Please come back again!
</code></pre></p>
<p>
<b>Reservation With Errors and Automatic Resets</b>
</p>
<p><pre class="code"><code>
Welcome to the travel agency. You offer travel packages for your vacation destinations.
[Input Dialog] How many people will be traveling? 0
Error: The number of travelers has been reset to 1. You entered 0
Select one of the destinations to book a flight: 1 - Chicago ($200), 2 - Los Angeles ($360), 3 - Miami ($320), 4 - Orlando ($310), 5 - Seattle ($330)
[Input Dialog] Enter the destination number: 1
[Input Dialog] Would you like to include hotel in your package (Y/N)? Y
What kind of hotel would you like to book? 1 - Motel ($49.99), 2 - Standard Hotel ($98.50), 3 - Luxury Hotel ($199.75
[Input Dialog] Enter the hotel number: 4
Error: The hotel type has been assigned to (2) Standard Hotel. You entered 4
[Input Dialog] Enter a number of hotel rooms: 0
Error: The number of hotel room has been reset to 1. You entered 0
[Input Dialog] Enter a number of nights to stay at the hotel: -1
Error: The number of nights has been reset to 1. You entered -1
The reservation for flights and hotel has been booked.
Total flight price to Chicago for 1 traveler(s) is $200.0
Total hotel price for 1 room(s) at Standard Hotel for 1 night(s) is $98.5
Total package price for flights and hotel is $298.5
</code></pre></p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>