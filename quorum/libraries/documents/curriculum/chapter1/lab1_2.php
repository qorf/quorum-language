<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 1.2 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>

<h1>Lab 1.2: Hangman Game</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to find and open sample projects</li>
<li>How to run and debug a program</li>
<li>How to analyze and modify code</li>
</ul>
<h2>Overview</h2>
<p>
The goal of this lab is to demonstrate opening a sample project and running that project in Sodbeans.  We have provided a series of sample Quorum projects for you to use.  For this lab, you will open and use the Hangman sample project.  Hangman is a letter guessing game written in Quorum.  The game is designed to show you how to run a project.  It also demonstrates what the kinds of programs you will be able to write in the future.  At the end of the lab, two students can work together, making one modification to the code to play the game.
</p>
<h2>Task 1: Find and Open the Hangman Project</h2>
<p>
First, start Sodbeans. The main tutorial window may appear, go ahead and close this window or select the check box to prevent it from showing up every time you start Sodbeans.  
</p>
<p>
Create a new project by using the keyboard shortcuts or going to the File menu.  The New Project dialog appears.  Use the arrow keys to navigate to the Samples folder in the Categories tree view.  Then, make sure the Quorum folder is selected under the Samples folder.  Use the keyboard shortcuts to switch the focus to the Projects tree view.  Select the Hangman project and press Enter.  In lieu of the keyboard shortcuts, the mouse can be used to select the project.  Leave the project name and location at default for now and press Enter (if the project name already exists on the computer make sure a unique name is chosen).
</p>
<p>
The project should now open.  The main.quorum file should appear in the editor.  If the file does not appear in the editor, navigate to the Projects window and use the arrow keys to locate the file then press Enter.  The main.quorum file is where all the code is located in the Hangman project. Be sure to set the Hangman project as the main project (bring up the context menu and select &quot;Set as Main Project&quot;). However, this should already be the default of the project.
</p>
<h2>Task 2: Take a Quick Tour of the Code</h2>
<p>
Now dive into the code and see how the program works.  In the main class, take some time going through the code.  Don’t feel overwhelmed!  There are a lot of things going on in the program, it is not important to understand all of the code for this lab.  You will learn to make programs like the Hangman program later in the class.
</p>
<p>
Navigate to the editor for main.quorum and arrow through the code. Listen and read through the code line by line and try to guess what is happening on each line. Notice that the program uses variable declarations and conditionals.  The variables and conditionals will be covered in Chapters 2 and 3, respectively.
</p>
<p>
Create a word document and answer the following questions:
</p>
<ol><li>What lines start with the keywords <code>repeat while</code>?
</li><li>What lines start with the keyword <code>if</code>?
</li></ol>
<h2>Task 3: Run the Program</h2>
<p>
It’s time to do the first run of the game!  To run the program, press the F6 key or go to Run -&gt; Run Project (Hangman).  The game has begun!  The input dialog appears.  It prompts you to guess a letter, so type any one letter to make your guess and press Enter.  Continue doing the same step until the game ends with the following statement:  “Hooray! The word is quorum.”
</p>
<p>
After the program finishes running, try to find and guess the code that accepts the user input and announces the current and final results.  Answer the following questions:
</p>
<ol><li>Where is the keyword <code>input</code> that accepts the user input?  Provide both line number and code. (Hint: Look for the line that starts with the word <code>guess</code>.)
</li><li>Where are the three lines of code that use keyword <code>say</code> to announce the current and final results?  Provide line numbers.
</li><li>Near the end of the program, there is a line that says "if game over is equal to true."  What do you think that code does?
</li></ol><h2>Task 4: Play Your Partner’s Game</h2>
<p>
Before playing each other’s game, the answer needs to be changed to something new.  To change the answer, find the following code:
</p>
<p><pre class="code"><code>
// This is where the word can be changed for the game
text answer = &quot;quorum&quot;
</code></pre></p>
<p>
Replace the word <i>quorum</i> with any word.  Note that the program accepts any number, letter, and character (space, question mark, etc) EXCEPT for the double quote character.  The answer is case sensitive so be sure to keep your answer in all lower case letters (case sensitive means the letter 's' is not the same as 'S' in the computer). Also be sure to put your answer <i>inside</i> of the double quotes!
</p>
<p>
To make the changes take affect, run the program.  It is now ready to use. Switch computers and play your partner’s game.  Try to guess the answer that the partner has decided on.  Let’s not forget to hide the answer!  No cheating and have fun!
</p>
<p>
Feel free to keep playing the game.  Change the answer to something different, then run the program again.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>