<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'assignment 5_1';
</script>

<h1>Short Assignment: Radio</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul><li>Validating user input
</li><li>Creating class methods
</li><li>Having source code in multiple files
</li></ul><h2>Overview</h2>
<p>
In this assignment we will be creating a program that mimics a radio. The user will be able to select from 1 of 4 stations and each station will play a different tune. The user will also be able to set how long the tune plays for. 
</p>
<p>
The duration and station input from the user must be checked for correctness. These values will be verified in the Main class, and then passed to the Station class. The Station class will be responsible for playing the music after receiving the station and duration input. The Main class will be responsible for asking the user for these values. 
 
We will learn about how to use separate files, and how to utilize the classes we define in them.
</p>
<h2>Sample Output</h2>
<p>
<strong>Entering an invalid time</strong>
</p>
<pre class="code">[output] "Please select your station (1-4):"
[input] "3"
[output] "How long do you want to play? (1-20):"
[input] "0"
[output] "Incorrect input. Please try again."
[output] "How long do you want to play? (1-20):""
[input] "6"
</pre><p>
<strong>Entering an invalid station</strong>
</p>
<pre class="code">[output] "Please select your station (1-4):"
[input] "5"
[output] "Incorrect input. Please try again."
output] "Please select your station (1-4):"
[input] "4"
[output] "How long do you want to play? (1-20):"
[input] "10"
</pre><p>
<strong>Entering valid information
</strong></p>
<pre class="code">[output] "Please select your station (1-4):"
[input] "2"
[output] "How long do you want to play? (1-20):"
[input] "19"
</pre><h2>Design Criteria</h2>
<ul><li>Create a new assignment and label it Assignment 5_1
</li><li>Create a source file named main.quorum
</li><li>Create a source file named Station.quorum
</li><li>Define the following actions in main.quorum:
</li></ul><blockquote>
<blockquote>
<p>
Main <br />
getStation <br />
getTime <br />
stationCheck <br />
timeCheck<br />
</p>
</blockquote>
</blockquote>
<ul><li>Define the following actions in Station.quorum:
</li></ul><blockquote>
<blockquote>
<p>
Play<br />
Station<br />
</p>
</blockquote>
</blockquote>
<ul><li>Note that you have to include "use Music.Players.Station"
</li><li>You will use the Play action of the imported Music class.
</li></ul>
<h2>Using Separate Files</h2>
<ul><li>put "package Music.Players" at the top of the Station.quorum file.
<pre class="code">package Music.Players
</pre></li><li>put "use Music.Players.Station" at the top of the main.quorum file.
<pre class="code">use Music.Players.Station
</pre></li><li>What the first statement is doing is saving the class Station into the Music.Players library.
</li><li>The second statement is responsible for making that specific class available to the main.quorum file. This way the user can call the class by simply typing the class name, then a variable name.
<pre class="code">Station radio
</pre></li><li>Now any public methods of the Station class can be called by typing the variable of the Station class, in this case radio, like so:
<pre class="code">radio:&lt;action name&gt;(parameter(s))
</pre></li></ul>
<h2>Other Information</h2>
<ul><li>Before you begin, think about how you might change the note that's played based only on the user's input<br />
</li><li>If you use a repeat statement, and don't change the variable you're using for note it will play the same sound for as long as the user specified<br />
</li><li>Within the repeat structure you're able to change the variable, so consider how you might change it to make it a more complex sound, rather than playing one note over and over again<br />
</li><li>Another consideration is that you want to make unique sounds for each station selected, but the human ear has a limited range of pitches it's able to hear. So it might take some tinkering to make it so every station played for an unknown amount of time produces an audible sound<br />
</li><li>When you want to speak a word in the Sodbeans environment it's common to use the Print statement. This is beneficial because it prints to the Sodbeans Command Window, and it also speaks it in an auditory format. It should be noted that when using quorum from anywhere else, like the command line for example, this isn't the case. The Print statement will only print to a Command Window. The Say statement, however, will speak the command in an auditory format in other environments. So for this program it's acceptable to use the Print statement to accomplish both tasks, as you are working in Sodbeans, but if you choose to work in a different environment down the road, this is something to be aware of. 
</li></ul>

<?php include("../../include/footer.php"); ?>