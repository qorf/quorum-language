<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 4_3';
</script>

<h1>Challenge Assignment: Travel Reservation with Actions</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul>
<li>More practice breaking applications up into logical sections using actions</li>
<li>Using the returned values from actions</li>
<li>Passing arguments into actions</li>
<li>Modifying an existing program</li>
</ul>
<h2>Overview</h2>
<p>
Modify the existing travel reservation program to make use of actions. The program should use control structures and validate user input as in <a href="../chapter3/assignment3_3.php">Assignment 3.3</a>. The behavior of this application will be identical to that of <a href="../chapter3/assignment3_3.php">Assignment 3.3</a>.
</p>
<h2>Design Criteria</h2>
<ul><li>Create a new project and name it TravelWithActions.
</li><li>All code should be in the <tt>main.quorum</tt> file.
</li><li>Use appropriate variables to store and keep track of values.
</li><li>The input dialogs and output should look similar to the sample output. (see <a href="../chapter3/assignment3_3.php">Assignment 3.3</a>).
</li><li>The program should use loops and conditionals.
</li><li>The program should have the following actions. Determining the appropriate return types and parameters is left as an exercise to the reader.
<ul><li><tt>Main</tt> - the main entry point of the application.
</li><li><tt>GetHotelNumber</tt> - Get the user's requested hotel number.
</li><li><tt>ShowSummary</tt> - Give the user a summary of all of their costs.
</li><li><tt>GetNumberOfTravelers</tt> - Ask the user for the number of travelers going on the trip.
</li><li><tt>GetDestinationFlightClost</tt> - Get the cost of a particular destination (e.g. Chicago).
</li><li><tt>GetHotelCost</tt> - Get the cost of a particular hotel type.
</li><li><tt>AskYesOrNo</tt> - Ask the user to enter Y or N, with a message. (For example, "Are you sure? (Y/N)"
</li><li><tt>GetDestinationNumber</tt> - Ask the user where they are going (for example, 1 is Chicago).
</li><li><tt>CalculateHotelCost</tt> - Calculate the total cost of the hotel, taking into account the hotel type, number of nights staying and number of rooms requested.
</li><li><tt>CalculateFlightCost</tt> - Calculate the flight cost, taking into account the flight destination and number of people needing tickets.
</li></ul></li></ul>

<?php include("../../include/footer.php"); ?>