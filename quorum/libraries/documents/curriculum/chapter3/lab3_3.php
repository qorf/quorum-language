<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab3_3';
</script>

<h1>Lab 3.3: More Conditionals, Loops, and Debugging</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to use conditionals and loops</li>
<li>How to use the debugger tool in Sodbeans</li>
</ul>
<h2>Overview</h2>
<p>
The Sodbeans debugger is a useful tool to step through code, see what is happening, and help you solve any errors.  For this lab, you will practice more with conditionals and loops. You will also use the debugger to test your code.  This will give you an idea of how your code works at runtime.
</p>
<h2>Task 1: DateTime Library Explained</h2>
<p>
Before you start writing code, let's go over the DateTime class.  The DateTime class is used to get the date/time information from the computer.  The sample code and explanations for each code snippet is provided below.
</p>
<p><pre class="code"><code>
DateTime dateTime
say dateTime:GetMonth() + &quot;/&quot; + dateTime:GetDayOfMonth() + &quot;/&quot; + dateTime:GetYear()
</code></pre></p>
<p>
In the sample code above, the DateTime object is being instantiated in the first line.  Then, on the second line, the DateTime object is used to get the month, day of month, and year.  All three methods return an integer value from the system.  The output in the month/day/year format is shown below:
</p>
<p><pre class="code"><code>
1/17/2012
</code></pre></p>
<p>
In the second line in the sample code below, the DateTime object is used to get integer values of hour, minute, and second.  All three of these methods are used to get the time information from the system.
</p>
<p><pre class="code"><code>
DateTime dateTime
say dateTime:GetHour() + &quot;:&quot; + dateTime:GetMinute() + &quot;:&quot; + dateTime:GetSecond()
</code></pre></p>
<p>
The output in hour:minute:second format is shown below:
</p>
<p><pre class="code"><code>
12:28:54
</code></pre></p>
<p>
For more information about the DateTime class, check out the documentation for the <a href="../../../../Libraries/System/DateTime.php">DateTime class</a>.
</p>
<h2>Task 2: Write a Date/Time Program</h2>
<p>
Start up the Sodbeans program.  Create a new project and name the project <b>Lab3_3</b>. Write a program that outputs the current date and time as shown in the sample output:
</p>
<p><pre class="code"><code>
12:28 PM Tuesday, January 17, 2012
</code></pre></p>
<p>
To get started, you are going to leverage what you have mastered so far in both Chapters 2 and 3.  First, you’ll need to instantiate the object that gets date and time.  Declare integer variables to get the values for the following date information: day of week, month, day, and year.
</p>
<p>
Add code that allows the computer to determine the name of the day of week (example: a value of 1 is Sunday, 2 is Monday).  Use the given variables to write the logic.  Declare any additional variables to store the values.  Then, do the same to write a separate logic to determine the name of the month.
</p>
<p>
When the logic for both the day of week and the month is completed, run and test the program to ensure that the values are outputted correctly.  If not, go back and fix the logic.
</p>
<p>
Now, you are going to get the time in a standard time format.  Keep in mind that Quorum uses a 24-hour clock, so you want to convert the 24-hour clock to the standard 12-hour clock.  For example, 15:00 should be converted to 3:00 PM.
</p>
<p>
Add code that gets the hour and minute.  Follow the description below to get the correct time format in plain English.  Add any variables when necessary.
</p>
<ul>
<li>If the hour is within the value that starts one o’clock in the afternoon and ends before midnight, the hour needs to be converted into the standard hour.  Otherwise, the hour in the morning stays as it is.</li>
<li>The program needs to determine if the hour is in the AM or PM.</li>
</ul>
<p>
Finally, if the output ends up looking like this:
</p>
<p><pre class="code"><code>
12:1 PM Tuesday, January 17, 2012
</code></pre></p>
<p>
when it should have been
</p>
<p><pre class="code"><code>
12:01 PM Tuesday, January 17, 2012
</code></pre></p>
<p>
Fix this problem by adding an extra logic at the end of the program.
</p>
<p>
The output should look similar to the sample output.  If the obtained date and time does not match the current date and time on the computer, fix the code and ensure that it matches the computer’s date and time.
</p>
<h2>Task 3: Meeting Dates and Loops</h2>
<p>
Now that you have the current date and time, assume the user has a meeting every seven days from today. Output the month and day for the next three meetings(don't worry about leap years). There are a few things to remember:
</p>
<ul>
<li>You can't just add 7 to the day; you need to roll over to a new month when you exceed the number of days in that month.</li>
<li>Month 2 has 28 days (don't consider leap years)</li>
<li>Months 4,6,9,and 11 have 30 days</li>
<li>All other months have 31 days</li>
</ul>
<p>
You will need to use a <tt>repeat times</tt> loop to output the next three meetings. Within that loop you will need to calculate the date of the meeting (hint: use a conditional statement). Given the current date from the sample output, the output of your program should look something like:
</p>
<p><pre class="code"><code>
meeting date: 1/24
meeting date: 1/31
meeting date: 2/7
</code></pre></p>
<p>
The meetings given by your program should list the next three meetings, every 7 days from the current date. To test your program for more dates increase the number of times the loop executes. Make sure the number isn't too high, it could take a long time to say all those dates!
</p>
<h2>Task 4: Use the Debugger</h2>
<p>
It’s time to try out the Sodbeans debugger!  The goal of this task is to help you become familiar with the functionality of the debugger in Sodbeans.  The debugger has controls, such as pause, continue, breakpoints, and step over, to navigate through the code in debugger mode.
</p>
<p>
The debugger is a tool in Quorum that helps you solve complicated logic problems in your code.  It executes the program line by line so that you can keep track of what is going on at runtime and observe the behavior of your code.  The debugger issues steps to check the current state of a program and the value of variables.  Steps are commands that execute one line of code or part of the program.
</p>
<p>
Quorum's debugger offers different types of functionality to help you debug your code.
</p>
<ul>
<li><b>Breakpoint:</b> Inserts a stop location into the debugger at a specific line number in the editor to tell the debugger where to stop.</li>
<li><b>Continue:</b> Runs the program or continues the debugger until a breakpoint is found or the end of the program is reached.</li>
<li><b>Rewind:</b> Moves the debugger back to the beginning of the program or back to the last breakpoint that was found.</li>
<li><b>Run Back To Cursor:</b> Moves the debugger backwards to the point in the program that matches the line the cursor is currently located on.</li>
<li><b>Run To Cursor:</b> Keeps moving the debugger forward to the end of the program or to the cursor.</li>
<li><b>Start:</b> Starts the debugger and runs the program after the build is successful and the focus is set to the editor.</li>
<li><b>Step Back Into:</b> Moves the debugger's active line into part of the previous line of code.</li>
<li><b>Step Back Over:</b> Moves the debugger's active line over one complete previous line of code.</li>
<li><b>Step Into:</b> Moves the debugger's active line into part of the next line of code. If that line has an action call the debugger steps into that action.</li>
<li><b>Step Over:</b> Moves the debugger's active line over one complete next line of code. If that line has an action call the debugger executes over all of the code in that action.</li>
<li><b>Stop:</b> Turns off the debugger and stops the program from running.</li>
</ul>
<p>
This task is divided in a variety of goals to help you accomplish a specific task with the debugger.  There are questions in each goal, so type answers in a word document.  For your reference, the debugger keyboard shortcuts table is at the end of this task.
</p>
<p>
<b>Goal 1: Start and stop debugger</b>
</p>
<p>
First, you need to start the debugger.  To start the debugger, do one of the three following options:
</p>
<ul>
<li>Visually click on the debugger button on the toolbar.</li>
<li>Use the menu bar by going to Debugger -&gt; Debug Main Project.</li>
<li>Use the keyboard shortcut by pressing CTRL + F5.</li>
</ul>
<p>
To finish the debugger session, either click on the Finish Debugger Session button, go to the menu, or use the keyboard shortcuts(Shift + F5).
</p>
<ol><li>When starting the debugger, what does the debugger say?
</li><li>When stopping the debugger, what does the debugger say?
</li></ol><p>
<strong>Goal 2: Setting breakpoints</strong>
</p>
<p>
Now, let’s try setting breakpoints.  Set at least three breakpoints in the editor, preferably at the start of each conditional and loop.  To set a breakpoint, click on the line number on the side of the editor or use the shortcut Ctrl + F8.  For the sighted users, a colored line will appear on the line where a breakpoint has been set.  When you start debugging, the program will run until it hits a breakpoint that you set in the editor by using continue (F5).
</p>
<p>
Start the debugger.  Tell the debugger to continue and the program will then halt at the first breakpoint you just set.
</p>
<ol start="3"><li>Is there a limit to setting a number of breakpoints?
</li><li>When setting a breakpoint, what does the debugger say?
</li></ol><p>
<strong>Goal 3: Step controls</strong>
</p>
<p>
Use the Step Over to move the active line to the next line.  Use the step controls, such as Step Back Over, Step Back Into, Step Over, and so on, to move the active line around in the editor.  Watch and/or listen to how the active line moves into the part of the code.  For instance, notice how it jumps into the proper conditional.  It only skips a block of the code in the conditional because it was checked as false, and it only enters a block of the code in the conditional that returns true.  In the case of a loop, notice that the active line keeps moving around until the proper action in the program has been taken.
</p>
<ol start="5"><li>What happens when a programmer tells the debugger to continue and rewind?
</li><li>Using either Step Over or Step Into function, what happens after the active line enters a block of code in the conditional when it returns true?  Will it continue checking more <tt>else-if</tt> statements?
</li><li>Using either Step Over or Step Into function, will the program continue going through the loop or exit the loop after the appropriate value has been matched in the conditional inside the loop that runs a block of code a number of times?
</li></ol><p>
<strong>Goal 4: Variables window</strong>
</p>
<p>
You can also use the Variables window to observe the use of variables in the case of lexical scoping.  Open the Variables window and use any of the step controls in the debugger to observe variables and their respective values.  The Variables window can be helpful in some cases; for example, keeping track of each time the program goes through a loop.  Try this in the loop to see what happens in the Variables window.
</p>
<p>
Step through all lines in the editor to finish the debugger, even though you could just simply stop the debugger without taking steps.  The step controls in the debugger should be finished when the active line reaches the end of the program.  The date and time result should be output.
</p>
<ol start="8"><li>What do the variables for the names of the day of week and the month display in the Variables window before storing appropriate values?
</li><li>At the line where the program outputs the current date and time, how many variables are listed in the Variables window?
</li></ol><p>
Show the work to the instructor.  Optionally, if there is enough time and if more practice is needed, you can practice more with the debugger by going through the Debugging Programs tutorial in the Sodbeans Tutorials.
</p>
<table class="table">
<tr><td><strong>Task</strong></td><td><strong>Hotkey</strong></td><td><strong>Action</strong>
</td></tr><tr><td>Breakpoint</td><td>CTRL + F8</td><td>Inserts a stop location into the debugger.
</td></tr><tr><td>Continue</td><td>F5</td><td>Runs the program.
</td></tr><tr><td>Rewind</td><td>CTRL + F10</td><td>Runs the program to the beginning.
</td></tr><tr><td>Run Back To Cursor</td><td>ALT + SHIFT + F4</td><td>Runs backwards to the beginning of the cursor.
</td></tr><tr><td>Run To Cursor</td><td>F4</td><td>Runs the program forever or to the cursor.
</td></tr><tr><td>Start</td><td>CTRL + F5</td><td>Begins execution of a program.
</td></tr><tr><td>Step Back Into</td><td>ALT + SHIFT + F7</td><td>Opposite of step into.
</td></tr><tr><td>Step Back Over</td><td>ALT + SHIFT + F8</td><td>Opposite of step over.
</td></tr><tr><td>Step Into</td><td>F7</td><td>Steps into part of a line of code.
</td></tr><tr><td>Step Over</td><td>F8</td><td>Steps over one complete line of code.
</td></tr><tr><td>Stop</td><td>SHIFT + F5</td><td>Turns the debugger off.
</td></tr></table>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>