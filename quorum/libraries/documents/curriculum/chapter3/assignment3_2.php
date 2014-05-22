<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 3.2: Number Guessing Game | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 3.2</h1>
                <p>Number Guessing Game</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>

<!--<h1>Short Assignment: Number Guessing Game</h1>-->
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>More practice with conditionals and lexical scoping</li>
<li>More practice with loops</li>
<li>How to analyze a problem statement in plain English</li>
</ul>
<h2>Overview</h2>
<p>
  Write a number guessing game that generates a random number between 1 and 100, inclusively (meaning 1 and 100 should be included). Once a number is generated, ask the user to guess that number and continue asking the until they guess the correct number. If the number the user guesses is less than or greater than the programs random number, tell the user. When the user guesses the correct number the game will end and the user will be told they guessed the correct number.
</p>
<h2>Task 1</h2>
<p>
Before getting started with this assignment, create a new project and name it <b>GuessingGame</b>. All code should be written in the main.quorum file, as before.
</p>
<p>
Your program should:
</p>
<ul>
<li>Use a repeat loop, so that game will continue until the game is over.</li>
<li>Have a conditional to determine if the users guess is less than, greater than, or equal to the random number.</li>
<li>Ask the user to enter a number between 1 and 100.</li>
</ul>
<h2>Sample Output</h2>
<p>
The input dialog statements are included in the sample output. They are identified as [Input Dialog].
</p>
<p><pre class="code"><code>
Welcome to the number guessing game. Can you guess my number?
[Input Dialog] Guess a number between 1 and 100: 50
My number is less than 50
[Input Dialog] Guess a number between 1 and 100: 25
My number is greater than 25
[Input Dialog] Guess a number between 1 and 100: 38
You guessed my number!
Game over.
</code></pre></p>
<h2>Short Answer</h2>
<p>
Can you think of an efficient way to guess the number every time? Describe a guessing strategy for this game.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>