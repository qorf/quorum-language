<!--Edited by Brandon Spencer-->
<!--1/2/13-->
<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 2';
</script>

<h1><b>Lab 2.3: Music</b></h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to use the Music Library</li>
<li>How to create Music objects</li>
<li>How to play a song for the appropriate length of time</li>
<li>How to use code completion to quickly find actions in classes</li>
</ul>
<h2>Overview</h2>
<p>
This lab demonstrates the use of the <tt>Music</tt> library in Quorum. The <tt>Music</tt> library can be used to play individual notes, chords, or even entire musical compositions. You will write a program that plays a song of yyour own composition, and you will share this song with yyour classmates. While building yyour musical piece, you will also take advantage of a feature of Sodbeans known as <i>code completion</i>, which allows us to find and read about actions and classes you are unfamiliar with. 
</p>
<h2>Task 1: Getting Started</h2>
<p>
You will start by creating a new blank Quorum project in Sodbeans. Name this project <b>Lab2_3</b>. Open the <b>main.quorum</b> file in the Sodbeans editor.
</p>
<p>
Before you can begin using the <tt>Music</tt> library, you must first understand a new keyword. Quorum, in a new project, is only aware of the code you place inside the file main.quorum. When writing programs, you often desire to make use of code that others have already written, to save yourselves time and allow yourselves to focus on the true problems you wish to solve. You can tell Quorum you wish to make use of other code by using the <tt>use</tt> statement. 
</p>
<p>
Begin by typing the word <tt>use</tt> followed by a space. You will use the code completion feature of Sodbeans to find the music library. Let's use code completion to find the Music library. After typing <tt>use</tt> followed by a space, press CTRL + SPACE to bring up the code completion menu. There should be one option to select, namely, <strong><tt>Libraries</tt></strong>. To select this option, press Enter. Now type a single period and a new code completion window will appear with new options including:
</p>
<p><pre class="code"><code>
Compute
Containers
Language
Robots
Sound
System
</code></pre></p>
<p>
Select <strong><tt>Sound</tt></strong> by using the arrow keys and pressing Enter. Type a period to bring up the code completion menu again. You should see the library you are looking for, called <strong><tt>Music</tt></strong>.
</p>
<p>
Quorum now knows that you want to use the <tt>Music</tt> library.  To put the <tt>Music</tt> library to use, you need to instantiate a new <tt>Music</tt> object.  In object-oriented programming languages, like Quorum, you have the idea of creating multiple objects from the same class.  The action of creating new objects of a given class (like the Music class) is called instantiating. Add a line of code that instantiates a <strong><tt>Music</tt></strong> object, named <tt>muse</tt>.  
</p>
<p>
Next, let’s find an action that appears to play a note in your <tt>muse</tt> object. In Quorum, you use the colon character to indicate that you wish to call an action on a variable. On a new line, type <tt>muse</tt> followed by a colon. The code completion window will again appear. Notice the number of available actions in the code completion window for a <strong><tt>Music</tt></strong> object. Go through this list of actions using the arrow keys until finding the action named <tt>Play(integer note, number duration)</tt>. This action will allow us to play a given note for a specified length of time, in seconds. Press enter to accept this option.
</p>
<p>
To tell the <tt>Play</tt> action what notes you wish to hear, you must pass it this information in the form of arguments.  In programming, arguments are values (or variable names) that are passed to an action. Much like the <tt>input</tt> command discussed in Lab 2.2, you will make use of left and right parenthesis. The only difference is, this time, you will also make use of a comma to tell the <tt>Music</tt> library the desired note and duration, respectively. 
</p>
<p>
An example use of the <tt>Play</tt> action might look like this:
</p>
<p><pre class="code"><code>
muse:Play(60, 1)
</code></pre></p>
<p>
This line of code will play a middle C note for a total of one second. The note will sound on a piano. Add this line of code now, and run the program. There should be no errors, and sound should be heard from the computer speakers (make sure the volume is turned up)! If yyour program does not compile, fix the compiler errors and try again.
</p>
<p>
Create a word document and answer the following questions:
</p>
<ol><li>What is the full line of code that tells Quorum that you want to use the <tt>Music</tt> library?
</li><li>How many options does the <tt>Sound</tt> package have (hint: pull up code completion)?
</li><li>How many different actions does the <tt>Music</tt> class have?
</li><li>What is the action that plays a note? Include its parameters, and provide an example different from code shown in the lab. Explain in plain english what yyour example does.
</li></ol>
<h2>Task 2: Creating a Custom Song</h2>
<p>
It’s time to create a custom song!  Write a series of play statements that play a given note.  Use any number between 0 and 127 for a note with any amount of seconds for the duration.  Take time in creating a song and keep testing the program to ensure that the program plays a melody. If you are confident enough, attempt to re-create the melody of a song you know.
</p>
<p>
When the song is finished and ready to show off, play the song for the instructor, some friends, or the class.
</p>

<?php include("../../include/footer.php"); ?>