<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 5.1: Radio | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 5.1:</h1>
                <p>Radio</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<!--<h1>Short Assignment: Radio</h1>-->
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul><li>How to validate user input
</li><li>How to create class methods
</li><li>How to place source code in multiple files
</li></ul><h2>Overview</h2>
<p>
In this assignment you will be creating a program that mimics a radio. The user will be able to select from 1 of 4 stations and each station will play a different tune. The user will also be able to set how long the tune plays for. 
</p>
<p>
    The duration and station input from the user must be checked for correctness. These values will be verified in the Main class, and then passed to the Station class. The Station class will be responsible for playing the music after receiving the station and duration input. The Main class will be responsible for asking the user for these values.

    You will learn about how to use separate files, and how to utilize the classes you define in them.  Create a new assignment and label it Assignment 5_1.
</p>
<h2>Class <code>Main</code></h2>
<p>
    Class <code>Main</code> should have the following actions:
</p>

<ul>
    <li>
        action StationCheck(integer station) returns boolean
    </li>
</ul>
<p>
    <code>StationCheck</code> should check to make sure the integer value passed in is between 1 and 4, inclusively.  This means the value can be either 1, 2, 3, or 4.  If the value passed in is valid, then this action should return true.  If the value is not valid, it should output an error message and then return false.
</p>
<ul>
    <li>
        action GetStation returns integer
    </li>
</ul>
<p>
    <code>GetStation</code> should ask the user to input a station (1-4) and then return the integer they enter.  It should continue to ask for a station until the user enters a valid station number.
</p>
<ul>
    <li>
        action TimeCheck(integer time) returns boolean
    </li>
</ul>
<p>
    <code>TimeCheck</code> will do the same things that <code>StationCheck</code> does: it should check if the integer passed in is between 1 and 20, inclusively.  If it is, then it should return true.  If not, output an error message and return false.
</p>
<ul>
    <li>
        action GetTime() returns integer
    </li>
</ul>
<p>
    <code>GetTime</code> should ask the user to input an integer between 1 and 20 inclusively, and then return that integer value.  It should continue to ask the user for a value until a valid one is given.
</p>
<h2>Class <code>Station</code></h2>
<p>
    Class <code>Station</code> should have the following actions:
</p>
<ul>
    <li>
        private action Play(integer time, integer note)
    </li>
</ul>
<p>
    <code>Play</code> should use the <code>Play</code> action from the <code>Music</code> library to play the note passed for the duration of <strong>time</strong>(the first integer passed in).
</p>
<ul>
    <li>
        action Station(integer note, integer time)
    </li>
</ul>
<p>
    <code>Station</code> should use action <code>Play</code> from above using the parameters passed in to <code>Station</code> as the parameters needed for <code>Play</code>.
</p>
<h2>Using Separate Files</h2>
<ul>
    <li>
        put "package Music.Players" at the top of the Station.quorum file.
        <pre class="code">
            package Music.Players
        </pre>
    </li>
    <li>
        put "use Music.Players.Station" at the top of the main.quorum file.
        <pre class="code">
            use Music.Players.Station
        </pre>
    </li>
    <li>
        What the first statement is doing is saving the class Station into the Music.Players library.
    </li>
    <li>
        The second statement is responsible for making that specific class available to the main.quorum file. This way the user can call the class by simply typing the class name, then a variable name.
        <pre class="code">
            Station radio
        </pre>
    </li>
    <li>
        Now any public methods of the Station class can be called by typing the variable of the Station class, in this case radio, like so:
        <pre class="code">
            radio:&lt;action name&gt;(parameter(s))
        </pre>
    </li>
</ul>
<h2>Other Information</h2>
<ul>
    <li>
        Before you begin, think about how you might change the note that's played based only on the user's input<br />
    </li>
    <li>
        If you use a repeat statement, and don't change the variable you're using for note, it will play the same sound for as long as the user specified<br />
    </li>
    <li>
        Within the repeat structure you're able to change the variable, so consider how you might change it to make it a more complex sound, rather than playing one note over and over again<br />
    </li>
    <li>
        Another consideration is that you want to make unique sounds for each station selected, but the human ear has a limited range of pitches it's able to hear. So it might take some tinkering to make it so every station played for an unknown amount of time produces an audible sound<br />
    </li>
    <li>
        When you want to speak a word in the Sodbeans environment, you can use the <code>say</code> statement. However, if you want to output a word to the Sodbeans output window, you must use an <code>output</code> statement. Be aware of these differences when completing this assignment.<br />
    </li>
</ul>
<h2>Sample Output</h2>
<p>
<strong>Entering an invalid time</strong>
</p>
<pre class="code">"Please select your station (1-4):"
"3"
"How long do you want to play? (1-20):"
"0"
"Incorrect input. Please try again."
"How long do you want to play? (1-20):""
"6"
</pre><p>
<strong>Entering an invalid station</strong>
</p>
<pre class="code">"Please select your station (1-4):"
"5"
"Incorrect input. Please try again."
"Please select your station (1-4):"
"4"
"How long do you want to play? (1-20):"
"10"
</pre><p>
<strong>Entering valid information
</strong></p>
<pre class="code">"Please select your station (1-4):"
"2"
"How long do you want to play? (1-20):"
"19"
</pre>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 