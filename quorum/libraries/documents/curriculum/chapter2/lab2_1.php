<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 2';
</script>

<h1>Lab 2.1: Types</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to use the Sodbeans Integrated Development Environment (IDE)</li>
<li>How to declare variables</li>
<li>Naming rules for variable declarations</li>
<li>How to concatenate text using the + operator</li>
</ul>
<h2>Overview</h2>
<p>
This lab will guide you through declaring variables with the appropriate name, primitive data type, and matching value. In addition to declaring variables you will practice following the guidelines listed in chapter 2, including variable naming and variable initialization conventions. Then, you will learn to use the say command and the + operator. You will also revisit creating a new project and navigating the Sodbeans IDE.
</p>
<h2>Task 1: Getting Started with Sodbeans</h2>
<p>
Start by opening Sodbeans and creating a new project. Instructions for creating a new project can be found in either <a href="../chapter1/lab1_1">Lab Manual 1.1</a> or Chapter 1 of the book.  In the New Project dialog, make sure &quot;Quorum&quot; is selected in the Categories list.  Then select the &quot;Blank Quorum Application&quot; project in the Projects list and press Enter.  On the next step, name the new project “Lab2_1” in the Project Name text field. Press Enter or click the Finish button to create a new project. Before you begin writing any code, be sure to check the new project is the main project.
</p>
<p>
For this lab, you will use the following windows in Sodbeans:  1) the Projects window, 2) the Sodbeans Output window, and 3) the Editor.  The Projects window holds a list of folders and files that allows you to organize and navigate the project.  This is where source files and other files will be found.  The Sodbeans Output window displays any compiler errors you may have and it displays any output statements executed during the programs runtime. Finally, the Editor window is where you will write your code.
</p>
<p>
Below is a list of shortcut keys that may be helpful when completing this lab:
</p>
<p>
General
</p>
<table class="wiki">
<tr><td><strong>Task</strong></td><td><strong>Hotkey</strong></td><td><strong>Action</strong>
</td></tr><tr><td>Create Project</td><td>CTRL + SHIFT + N</td><td>Creates a new project
</td></tr></table>
<p>
Tree Views
</p>
<table class="wiki">
<tr><td><strong>Task</strong></td><td><strong>Hotkey</strong></td><td><strong>Action</strong>
</td></tr><tr><td>Collapse Folder</td><td>Left Arrow</td><td>Collapses the folder
</td></tr><tr><td>Expand Folder</td><td>Right Arrow</td><td>Expands the folder
</td></tr></table>
<p>
New Project Dialog
</p>
<table class="wiki">
<tr><td><strong>Task</strong></td><td><strong>Hotkey</strong></td><td><strong>Action</strong>
</td></tr><tr><td>Categories List</td><td>CTRL + SHIFT + Left Arrow</td><td>Changes the focus to the Categories list
</td></tr><tr><td>Projects List</td><td>CTRL + SHIFT + Right Arrow</td><td>Changes the focus to the Projects list
</td></tr></table>
<p>
Using Project
</p>
<table class="wiki">
<tr><td><strong>Task</strong></td><td><strong>Hotkey</strong></td><td><strong>Action</strong>
</td></tr><tr><td>Build Project</td><td>F11</td><td>Builds a main project
</td></tr><tr><td>Editor Window</td><td>CTRL + 0</td><td>Changes the focus to the editor window
</td></tr><tr><td>Output Window</td><td>CTRL + SHIFT + 5</td><td>Changes the focus to the Sodbeans output window
</td></tr><tr><td>Projects Window</td><td>CTRL + 1</td><td>Changes the focus to the Projects window
</td></tr><tr><td>Run Project</td><td>F6</td><td>Runs a main project
</td></tr></table>
<p>
Editor Window
</p>
<table class="wiki">
<tr><td><strong>Task</strong></td><td><strong>Hotkey</strong></td><td><strong>Action</strong>
</td></tr><tr><td>Code Completion</td><td>CTRL + SPACE</td><td>Activates the code completion window
</td></tr></table>
<h2>Task 2: Write and Test a Program</h2>
<p>
You will start by writing code in the main.quorum file.  In the Projects window, use arrow keys to navigate to the "main.quorum" file and press Enter.
</p>
<p>
To build a program that declares several variables we should first make sure main.quorum is blank (go ahead and delete anything that might have been generated in this file). The first line should declare the variable <strong>myInteger</strong> with the <strong><tt>integer</tt></strong> type and assign the variable the value of 4. Once this variable is declared build your project and check for any compiler errors.
</p>
<p>
Build the project by going to Run -&gt; Build Main Project or by pressing F11.  The program should compile with no errors. If it does have errors there is a problem with the line of code in your project. The message in the Sodbeans Output window should give you a clue as to what went wrong. In addition, Sodbeans will automatically display a line under the code that has an error so that you will know where something went wrong. Remember, compiling this code will not check that you assigned the value 4 to the variable <strong>myInteger</strong>. Instead, it checks that the format of the code is valid: <strong><tt>&lt;type&gt; &lt;unique name&gt; = &lt;value&gt;</tt></strong>.  Sodbeans compiles the code and checks for any errors in the background, so there is no need to keep building the project when you want to compile your code again. Once all of the compiler errors are gone run the program by going to Run -&gt; Run Main Project or by pressing F6.  When the project runs, nothing should have happened because you have not gone that far yet!
</p>
<p>
Next, add two lines of code (you can copy and paste from here):
</p>
<p><pre class="code"><code>
integer 2myInteger1 = 5
integer _myInteger2 = 6
</code></pre></p>
<p>
Run the program.  The compiler errors will appear in the Sodbeans Output window as shown below:
</p>
<p><pre class="code"><code>
Incomplete or invalid statement '_'
extraneous input ‘2’
</code></pre></p>
<p>
You are receiving those compiler errors because variable names must start with a letter(they do not follow the appropriate format).  After the required first letter, variable names can have any number of letters, numbers, or underscores.  Fix two incorrect variable names and run the program again. The compiler errors should go away.
</p>
<p>
Now add more variables and types to the program.  Do the following steps to add types:
</p>
<ul><li>Define an <strong><tt>integer</tt></strong> type and give the variable name <strong>firstInteger</strong>. Assign firstInteger to 4.
</li><li>In the next line, define a <strong><tt>number</tt></strong> type and give the variable name <strong>secondNumber</strong>. Assign secondNumber to 8.9.
</li><li>In the next line, define a <strong><tt>boolean</tt></strong> type and give the variable name <strong>checkResult</strong>. Assign checkResult to true.
</li><li>In the next line, define a <strong><tt>text</tt></strong> type and give the variable name <strong>quorumText</strong>. Assign quorumText to “Quorum is fun.”
</li></ul>
<p>
Now that you have defined these four new variables, have Sodbeans output each variable out to the user. You can make the computer print your variables the the Sodbeans output window by using the <strong><tt>output</tt></strong> statement. Once this is completed the output should say the following:
</p>
<p><pre class="code"><code>
4
8.9
true
Quorum is fun
</code></pre></p>
<p>
Now have your program output another sentence but this time the variables should be used in the sentence. Try to use only one <strong><tt>output</tt></strong> statement to print the following text (hint:  use concatenation). The output should say the following:
</p>
<p><pre class="code"><code>
The program returns true when 8.9 is greater than 4. Quorum is fun.
</code></pre></p>
<p>
Once you have done this, you are done! Show your work to the instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>