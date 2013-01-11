<!--Edited by Brandon Spencer-->
<!--1/8/13-->
<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'assignment 5_4';
</script>

<h1>Challenge Assignment:  The Game of Pig</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul><li>How to design your program using object-oriented programming
</li><li>How to build multiple classes
</li><li>How to design your own actions within each class.
</li><li>How to use class variables
</li><li>How to use objects
</li></ul><h2>Overview</h2>
<p>
The game of pig is a dice rolling game. In this game a player will play against the computer in an attempt to bank a total of 100 or more points. Sounds easy, but there is a catch, if either a player or the computer rolls a 1 their turn ends and no points are added onto the players total.
</p>
<p>
Here are the rules of the game:
</p>
<ul><li>The players will roll a six sided die (values 1 to 6).
</li><li>If a player rolls a 1 that players turn ends and their turn points are lost.
</li><li>The player will select when to bank their points and when to roll.
</li><li>The computer will continue to roll until they roll a 1 or their turn total is greater than or equal to 20.
</li><li>If the computer's turn total is greater than or equal to 20 the computer will bank those points.
</li><li>The human player will always play first. 
<ul><li>If the computer and the player reach 100 points or more at the same time it will be a tie.
</li><li>If one player reaches 100 they will win(only if the other player does not reach 100 in the same number of turns).
</li></ul></li></ul><p>
Create a Quorum application and name it <strong>Pig</strong>. This project will need 4 additional classes, <strong><tt>PigGame</tt></strong>, <strong><tt>Die</tt></strong>, <strong><tt>ComputerPlayer</tt></strong>, and <strong><tt>UserPlayer</tt></strong>. Design each class such that <tt>PigGame</tt> is responsible for playing the entire game and setting up the rules. <tt>Die</tt> should represent a 6 sided die and you should be able to roll that die. The <tt>ComputerPlayer</tt> and <tt>UserPlayer</tt> classes should be similar. They should allow the player to take a turn in the game, store the turn and total score for that player, access the total score, and determine if the player is in a winning state.
</p>
<h2>Sample Output</h2>
<pre class="code">Welcome to the game of pig!
During your turn you can roll and add those points to your turn total or hold to bank your turn points.
If a 1 is rolled you will bust and no points will be banked.
The first player to bank 100 points is the winner.
Your score: 0
Computer score: 0
It is your turn.
You rolled a 3
Your turn total is 3. Enter nothing to roll. Enter anything to bank.
You rolled a 3
Your turn total is 6. Enter nothing to roll. Enter anything to bank.
You rolled a 5
Your turn total is 11. Enter nothing to roll. Enter anything to bank.
You rolled a 1
Busted!
Your score: 0
Computer score: 0
It is the computers turn.
The computer rolled a 5
The computer rolled a 5
The computer rolled a 6
The computer rolled a 5
The computer's turn total is 21
Your score: 0
Computer score: 21
It is your turn.
You rolled a 5
Your turn total is 5. Enter nothing to roll. Enter anything to bank.
You rolled a 2
Your turn total is 7. Enter nothing to roll. Enter anything to bank.
You rolled a 6
Your turn total is 13. Enter nothing to roll. Enter anything to bank.
You rolled a 3
Your turn total is 16. Enter nothing to roll. Enter anything to bank.
You rolled a 5
Your turn total is 21. Enter nothing to roll. Enter anything to bank.
Your score: 21
Computer score: 21
It is the computers turn.
The computer rolled a 1
The computer's turn total is 0
Your score: 21
Computer score: 21
It is your turn.
You rolled a 6
Your turn total is 6. Enter nothing to roll. Enter anything to bank.
You rolled a 5
Your turn total is 11. Enter nothing to roll. Enter anything to bank.
You rolled a 6
Your turn total is 17. Enter nothing to roll. Enter anything to bank.
You rolled a 6
Your turn total is 23. Enter nothing to roll. Enter anything to bank.
You rolled a 5
Your turn total is 28. Enter nothing to roll. Enter anything to bank.
You rolled a 5
Your turn total is 33. Enter nothing to roll. Enter anything to bank.
You rolled a 6
Your turn total is 39. Enter nothing to roll. Enter anything to bank.
You rolled a 5
Your turn total is 44. Enter nothing to roll. Enter anything to bank.
Your score: 65
Computer score: 21
It is the computers turn.
The computer rolled a 6
The computer rolled a 3
The computer rolled a 6
The computer rolled a 1
The computer's turn total is 0
Your score: 65
Computer score: 21
</pre><p>
.
.
.
</p>
<pre class="code">Your score: 102
Computer score: 21
You are the winner!
</pre><h2>Design Criteria</h2>
<ul><li>Create a new project and name it Pig.
</li><li>There should be a total of 5 files.
<ul><li>main.quorum
</li><li>ComputerPlayer.quorum
</li><li>UserPlayer.quorum
</li><li>Die.quorum
</li><li>PigGame.quorum
</li></ul></li><li>Each file should have a corresponding class defined in the file.
</li><li>Use appropriate variables to store and keep track of values (including class variables).
</li><li>The input dialogs and output should look similar to the sample output.
</li><li>You should define actions in each class (the design is up to you).
</li></ul>

<?php include("../../include/footer.php"); ?>