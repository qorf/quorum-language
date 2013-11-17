<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 4_3';
</script>

<h1>Challenge Assignment: Travel Reservation with Actions</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>More practice breaking applications up into logical sections using actions</li>
<li>How to use the returned values from actions</li>
<li>How to pass arguments into actions</li>
<li>How to modify an existing program</li>
</ul>
<h2>Overview</h2>
<p>
Modify the existing travel reservation program to make use of actions. The program should use control structures and validate user input as in <a href="../chapter3/assignment3_3.php">Assignment 3.3</a>. The behavior of this application will be identical to that of <a href="../chapter3/assignment3_3.php">Assignment 3.3</a>.
</p>
<h2>Design Criteria</h2>
<ul><li>Create a new project and name it TravelWithActions.
</li><li>All code should be in the <code>main.quorum</code> file.
</li><li>Use appropriate variables to store and keep track of values.
</li><li>The input dialogs and output should look similar to the sample output. (see <a href="../chapter3/assignment3_3.php">Assignment 3.3</a>).
</li><li>The program should use loops and conditionals.
</li><li>The program should have the following actions. Determining the appropriate return types and parameters is left as an exercise to the reader.
<ul><li><code>Main</code> - the main entry point of the application.
</li><li><code>GetHotelNumber</code> - Get the user's requested hotel number.
</li><li><code>ShowSummary</code> - Give the user a summary of all of their costs.
</li><li><code>GetNumberOfTravelers</code> - Ask the user for the number of travelers going on the trip.
</li><li><code>GetDestinationFlightClost</code> - Get the cost of a particular destination (e.g. Chicago).
</li><li><code>GetHotelCost</code> - Get the cost of a particular hotel type.
</li><li><code>AskYesOrNo</code> - Ask the user to enter Y or N, with a message. (For example, "Are you sure? (Y/N)"
</li><li><code>GetDestinationNumber</code> - Ask the user where they are going (for example, 1 is Chicago).
</li><li><code>CalculateHotelCost</code> - Calculate the total cost of the hotel, taking into account the hotel type, number of nights staying and number of rooms requested.
</li><li><code>CalculateFlightCost</code> - Calculate the flight cost, taking into account the flight destination and number of people needing tickets.
</li></ul></li></ul>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>