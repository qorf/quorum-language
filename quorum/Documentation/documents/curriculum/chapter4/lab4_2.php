<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 4_2';
</script>

<h1>Lab 4.2: Rock Paper Scissors</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
    <li>How to implement actions without parameters and return types</li>
<li>How to read and understand requirements to write basic actions</li>
</ul>
<h2>Overview</h2>
<p>
In this lab, you will be writing a Rock Paper Scissors game. In this game a human player will be competing with the computer.  The human player will choose rock, paper, or scissors and then the computer will randomly select one of the options. Then your program will tell the player who won the round. The use of actions will allow you to organize the game and make the program look organized.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new &quot;Quorum Application&quot; project, and name it <b>RockPaperScissors</b>. As in the previous labs, this will leave you with a skeleton <code>Main.quorum</code> file, containing a <code>Main</code> class and <code>Main</code> action. After your project is created, you can use the following code skeleton(replace the code in your project with the following code):
</p>
<p><pre class="code""><code>
use Libraries.Compute.Random
class Main
    /* Action Main has no parameters or return value.
       This is where the program begins executing. Within this method you should
       give the instructions to the user and then play the game.
    */
    action Main
    end
    
    /* Action GiveInstructions has no parameters or return value.
        It handles giving all game rules and instructions to the user before
        the game begins.
    */
    action GiveInstructions
    end
    
    /* Action PlayGame has no parameters or return value.
       It handles anything involving playing the game. This includes asking the player
       for their choice, generating the computers choice, determining the winner,
       and determining if the player wishes to quit the game or continue playing.
    */
    action PlayGame
    end
    
    /* Action PlayersTurn has no parameters and returns an integer value.
       This action handles asking the user for their choice of rock, paper, or
       scissors. It also checks the input for errors. Then it returns the valid
       user choice.
    */
    action PlayersTurn returns integer
       integer choice = 1
       //your code goes here
       return choice
    end
    
    /* Action ComputersTurn has no parameters and returns an integer value.
       This action handles randomly generating the computers choice. Then it
       returns that value.
    */
    action ComputersTurn returns integer
       integer choice = 1
       //your code goes here
       return choice
    end
    
    /* Action DetermineWinner has two parameters and no return value. The first
       parameter is the players choice and the second is the computers choice.
       This action handles determining the winner of a round in the game. It
       also says who won the round
    */
    action DetermineWinner(integer playerChoice, integer computerChoice)
    end
end
</code></pre></p>
<p>
You will start by writing the <code>GiveInstructions</code> action. In this action you should say or output the rules and instructions for the rock, paper, scissors game. That message might be something like the following:
</p>
<p><pre class="code"><code>
Welcome to the Rock Paper Scissors game!
Here are the rules:
The player and the computer will pick rock, paper, or scissors.
Paper beats rock.
Rock beats scissors.
Scissors beat paper.
Enter a 1 for rock, a 2 for paper, or a 3 for scissors.
</code></pre></p>
<p>
Once the <code>GiveInstructions</code> action is written, call the action in the <code>Main</code> action. Remember <code>Main</code> is where your program begins executing or running and the <code>end</code> following the <code>Main</code> action is where it ends. Run the program and it should give the instructions you wrote in the <code>GiveInstructions</code> action.
</p>
<h2>Task 2: Create A Game with Actions</h2>
<p>
You are going to create a game with actions.  You will separate each action so that they serve their own purpose. The first of these actions will be the <code>PlayersTurn</code> action. This action should ask the user to choose rock, paper, or scissors and it should continue to ask the user this until a valid input is given.
</p>
<p>
Go to the <code>PlayersTurn</code> action. Within that action start writing code at the comment that says "your code goes here". You will need your code to do the following:
</p>
<ul><li>Declare a boolean and initialize it to false to track valid input.
</li><li>Create a loop that loops until the input is valid.
</li><li>Within that loop, ask the user to choose rock, paper, or scissors.
</li><li>Cast the input from the user to an integer and store it in the <strong>choice</strong> variable(has already been declared in this action).
</li><li>Check if the input is valid; if it is valid the loop should stop.
</li></ul><p>
The existing code then returns the user's choice from the action, <code>return choice</code>, is already written into the action. Don't worry about calling and testing this action right now. You will come back to this when you write the <code>PlayGame</code> action. Instead move onto writing the <code>ComputersTurn</code> action.
</p>
<p>
Go to the <code>ComputersTurn</code> action. Within that action start writing code at the comment that says "your code goes here". You will need your code to do the following:
</p>
<ul><li>Declare a Random object. e.g. <code>Random random</code>.
</li><li>Generate a random integer between 1 and 3, including 1 and 3.
</li><li>Assign the random integer to the <code>choice</code> variable.
</li></ul><p>
The computers choice is then returned(the code for this is already included). 
</p>
<p>
The next action you are going to write is the <code>DetermineWinner</code> action. This action has two parameters playerChoice and computerChoice. For this to be a general solution you won't worry about the value of each parameter; those are determined when you call the <code>DetermineWinner</code> action. In this action you want to use conditional statements to determine if there is a tie, if the computer won, or if the player won. 
</p>
<p>
To start writing this action you can use the following code:
</p>
<p><pre class="code"><code>
if playerChoice = computerChoice then
   say &quot;It's a tie!&quot;
end
</code></pre></p>
<p>
Add onto this conditional so that, if:
</p>
<ul><li>Player chooses rock and computer chooses paper the output is, "Paper beats rock - the computer wins!"
</li><li>Player chooses rock and computer chooses scissors the output is, "Rock beats scissors - you win!"
</li><li>Player chooses paper and computer chooses rock the output is, "Paper beats rock - you win!"
</li><li>Player chooses paper and computer chooses scissors the output is, "Scissors beats paper - the computer wins!"
</li><li>Player chooses scissors and computer chooses paper the output is, "Scissors beats paper - you win!"
</li><li>Player chooses scissors and computer chooses rock the output is, "Rock beat scissors - the computer wins!"
</li></ul><p>
The next action you will write is the <code>PlayGame</code> action. This action handles all of the game play, including, getting the players choice, randomly generating the computers choice, determining a winner of the round, and asking the player if they want to continue playing or stop the game.
</p>
<p>
In this action, start by defining a text variable called <strong>playAgain</strong> and initialize it to "Y". Create a loop that will continue to loop when <strong>playAgain</strong> is equal to "Y" or "y". Inside this loop you need to call the <code>PlayersTurn</code> action and store the returned value in a variable named <strong>player</strong> with a type of integer. Then do the same with the <code>ComputersTurn</code> action. Store the returned value in a variable named <strong>computer</strong> with a type of integer. Call <code>DetermineWinner</code> passing the arguments <strong>player</strong> and <strong>computer</strong>. Finally, ask the user, "Would you like to play again (Y/N)?", and assign the result to the <strong>playAgain</strong> variable.
</p>
<p>
When you are done the <code>PlayGame</code> action should look like the following code:
</p>
<p><pre class="code"><code>
    action PlayGame
        text playAgain = &quot;Y&quot;
        repeat while playAgain = &quot;Y&quot; or playAgain = &quot;y&quot;
            integer player = PlayersTurn()
            integer computer = ComputersTurn()
            DetermineWinner(player, computer)
            playAgain = input(&quot;Would you like to play again (Y/N)?&quot;)
        end
    end
</code></pre></p>
<p>
The final step is to finish writing the <code>Main</code> action. The <code>Main</code> action should give the instructions, play the game, and say "Game over" when the game is done. 
</p>
<h2>Sample Output</h2>
<p>
The following is sample output from the game:
</p>
<p><pre class="code"><code>
Welcome to the Rock Paper Scissors game!
Here are the rules:
The player and the computer will pick rock, paper, or scissors.
Paper beats rock.
Rock beats scissors.
Scissors beat paper.
Enter a 1 for rock, a 2 for paper, or a 3 for scissors.
Rock (1), Paper (2), or Scissors (3)? 
4
Error: The value needs to be between 1 and 3. Please try again. You entered 4
Rock (1), Paper (2), or Scissors (3)? 
1
Rock beats scissors - you win!
Would you like to play again (Y/N)? 
Y
Rock (1), Paper (2), or Scissors (3)? 
1
Paper beats rock - the computer wins!
Would you like to play again (Y/N)? 
y
Rock (1), Paper (2), or Scissors (3)? 
3
It's a tie!
Would you like to play again (Y/N)? 
N
Game over
</code></pre></p>
<p>
Debug your program and fix any compiler errors there might be. Now would be a great time to use the debugger to test your program. When an action is called use step into to see what happens when a particular action is called. Did your program run correctly? If not, fix any problems. Show the work to the instructor.
</p>
 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>