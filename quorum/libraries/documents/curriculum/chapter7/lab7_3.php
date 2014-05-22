<!--Developed and written by Brandon Spencer-->
<!--10/16/2012-->
<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 7.3: Musical Chairs | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Lab 7.3</h1>
		<p>Musical Chairs</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<!--<h1>  Lab 7.3: Musical Chairs</h1>-->
<h2>
  Objectives
</h2>
<p>
  The goal of this lab is to learn the following:
</p>
<ul>
  <li>
    How to use arrays and lists together
  </li>
  <li>
    How to add and delete items from strings and arrays
  </li>
  <li>
    Big O notation and algorithm analysis
  </li>
  <li>
    Error checking and error handling
  </li>
</ul>
<h2>
  Overview
</h2>
<p>
  In this lab, you will be using arrays and lists to simulate a game of musical chairs. The user will be asked to enter the names of players, then the players will be randomly selected and pulled from the array. At the end, the user will be told who the game winner was. You will also get the chance to work with Quorum's error class. Errors are typically caused by invalid user input. For example, if a user tries to divide by zero, then a <code>DivideByZeroError</code> will be generated. When errors occur, the program will stop running and the user will get an error message, indicating what error occurred. Usually you want Quorum to do something more than this when an error is encountered. This is what error handling will allow you to do. To handle an error, use the keywords <code>check</code> and <code>detect</code>. Here is an example of a <code>DivideByZeroError</code>, and how you can use check/detect to handle that error:
</p>
<pre class="code">
  check
  integer result = 0
  integer divisor = cast(integer, input("divide by?"))
  result = 12/divisor
  detect e is DivideByZeroError
  say "Error detected" + e:GetErrorMessage() //prints the error that occurred
  result = 12/1
  end
</pre>
<p>
  In the above example, you enclose code that might cause an error to be thrown in the check block, and use the detect block to specify the type of error to handle, and how to handle it (set result = 12/1). Detect uses the format detect &lt name &gt is &lt error type="" &gt where name is a name of your choosing, and the error type is the error you expect to be thrown. As with if/elseif/else, you end a check/detect with the keyword end.
</p>
<h2>Getting Started (Part A)</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <b>Lab7_3a</b>. Create a second class called <code>MusicalChairs</code>. You will need to use the following libraries for this lab:
</p>
<pre class="code">
  use Libraries.Containers.List
  use Libraries.Containers.Array
  use Libraries.Compute.Random
  use Libraries.Language.Errors.Error
  use Libraries.Language.Errors.InvalidLocationError
</pre>
<h2>
  Class <code>MusicalChairs</code>
</h2>
<p>
  In class <code>MusicalChairs</code>, create a list of type integer and an array of type text. Next, you will create actions as follows:
</p>
<ul>
  <li>
      <b>
          action Names
      </b>
  </li>
</ul>
<p>
  <code>Names</code> should ask the user to enter the name of a player and should input this name into an array. The array should be populated with at least 5 players.
</p>
<ul>
  <li>
      <b>
          action RandomList
      </b>
  </li>
</ul>
<p>
  <code>RandomList</code> needs to populate a list with 1 less integer than there are players. Note that the random integers must be between 1 and <code>n-1</code>, where <code>n</code> is the number of players in your game.
</p>
<ul>
  <li>
      <b>
          action GetPlayers
      </b>
  </li>
</ul>
<p>
  One of Quorum's built-in errors is the <code>InvalidLocationError</code> error. This error is thrown when an invalid memory location is accessed. GetPlayers will count the number of players in the game (by iterating through your array of names), and will tell the user how many are playing. However, you're going to try and iterate through the array an impossible number of times, given that the array should hold no more than 9 players, and therefore should be no larger than 10. Try repeating through the array 20 times.
</p>
<p>
  Run the program, and see what happens. This is what is called an <code>InvalidLocationError</code>, and it happened because you tried to access invalid memory locations. Now put in a check/detect, so that the "detect" will output "Error detected" and follow with the error message. Then, correct the problem so that your repeat loop no longer tries to access invalid memory locations.
</p>
<ul>
  <li>
      <b>
          action Play
      </b>
  </li>
</ul>
<p>
  Play will conduct the game of musical chairs. Using the <code>RemoveAt(value)</code> action for both arrays and lists, this action should use the random integers held in your list as indexes to remove players from your array. After each player that is removed, tell the user who that person was. When the game is over, tell the user who won.
</p>
<p>
  The <code>RemoveAt(value)</code> action removes the item in the array(or list, this action is the same for both data structures). It returns the item that was removed, and takes the index of the item to be removed as a parameter. It can be used like this:
</p>
<pre class="code">
  List &lt integer &gt myList
  myList:Add(21)
  myList:Add(22)
  myList:Add(32)
  integer removedItem = myList:RemoveAt(0) //removedItem now == 21
</pre>
<h2>
  Sample Output
</h2>
<p>
  In Main, call the appropriate actions to play a game of musical chairs. Example output may look similar to this:
</p>
<pre class="code">
  Enter the name of a player
  Brandon
  Enter the name of a player
  Chris
  Enter the name of a player
  Andy
  There are three players in this game.

  The first person to get out was Chris
  The second person to get out was Andy
  The winner is Brandon!
</pre>
<p>
  &#160;
</p>
<h2>Class <code>Musical Chairs</code>revised: (7_3 part B)</h2>
<p>
  Now that you have a working Musical Chairs game, you're going to make it so the user can decide how many players are going to be playing. When run, the program should prompt the user to enter the number of players. After entered, they will need to supply the names of the players. Then, the program will play the game of musical chairs and will output, in order, who was removed from the game, and lastly, who the winner was.
</p>
<p>
  In order to accomplish this autonomously, each action will require at least one parameter(the number of players in the game). You also have a unique problem that you didn't address in 7_3A: the integers populated into your list were not unique. They were random, but may contain multiple instances of the same integer. To make this program more robust, you will need to find a solution to check for unique, but random integers, and be able to populate the list with as many integers as there are people playing. This can be done relatively quickly using the <code>Knuth-Fisher-Yates</code> algorithm. The idea behind this algorithm is to populate a list with an ordered number of integers N, starting with 0. If the user says that 1000 people are playing, then you need to populate a list of ordered integers from 0 to 1000, so it would be 1001 nodes long. Once you have an ordered list, you grab the Max value (1000), and a random value, and then swap them in the list. In the next iteration, you decrement Max by 1 and pick another random integer and swap the two in the list. This continues until Max is equal to 0. You can see how this works in the following figure:
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/Knuth-Fisher-Yates.PNG" alt="Knuth-Fisher-Yates Algorithm"></img>
<p>
  This algorithm is especially nice in this case because it is highly efficient at creating an array of random, unique integers. In computer science, you can measure this efficiency. Typically, efficiency of a given algorithm is measured by how many comparisons or assignments must be done. In the algorithm above, it takes O(n) (Big-Oh of n) time to fill the array with n number of integers. This is called linear time, since the time it takes changes with the size of n. To shuffle those integers, it takes O(1) time, meaning it is done in near constant time. Big O notation is used to show the upper bound constraints on run time (the worst case). It's important to analyze your algorithms for run time complexities. Doing so allows a programmer to estimate time and memory usage, identify bottlenecks so they can work to reduce run time, compare different implementation approaches, or to speed up critical algorithms.
</p>
<p>
  Action <code>Play(integer value)</code> needs to be modified as well. Since you are performing many of these operations in loops, you will need a second array to hold the text values being removed (the players that lost). Then, in a different loop, you can get those text values and output them for the user to see who lost. Make sure that you're telling the user the correct order in which players lost the game. Again, at the end of the game, tell the user who the winner was.
</p>
<h2>
  Sample Output
</h2>
<p>
  When run, output should look similar to the following:
</p>
<pre class="code">
  Enter how many players will be playing
  3
  Enter the name of a player
  Brandon
  Enter the name of a player
  Chris
  Enter the name of a player
  Andy
  The first person to get out was Chris
  The second person to get out was Andy
  The winner is Brandon!
</pre>
<p>
  When you're done with the lab, show your code to your instructor.
</p>