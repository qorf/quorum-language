<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 7.4: Errors | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 7.4</h1>
		<p>Errors</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>Short Assignment 7.4: Errors</h1>-->
<h2>Objectives</h2>
<p>
    The goal of this lab is to learn the following:
</p>
<ul>
    <li>
        How to perform error checking
    </li>
    <li>
        How to perform error handling
    </li>
    <li>
        Common Errors
    </li>
</ul>
<h2>
    Overview
</h2>
<p>
    In this assignment, you will be creating several independent actions and classes showcasing Quorum's Errors class. When creating robust programs, it's important to think about where errors may be thrown by the compiler, and to then create an intelligent way to handle those errors, so that users can continue to use your program. As stated in Lab 7_3, most errors occur from user input, so this is a good place to start looking for where errors may be thrown. The <code>Errors</code> class in Quorum has several specific classes that handle more specific errors. In this assignment, you will get the chance to create and fix errors from these different classes.
</p>
<h2>Getting Started</h2>
<p>
    Create a new “Quorum Application” project, and name it <b>Assignment 7_4</b>. Create a second class called <code>MyErrors</code>. You will need to use the following libraries for this lab:
</p>
<pre class="code">
    use Libraries.Language.Errors.!CastError
    use Libraries.Language.Errors.Error
    use Libraries.Language.Errors.!InvalidArgumentError
    use Libraries.Language.Errors.!InvalidLocationError
    use Libraries.Language.Errors.!OutOfBoundsError
    use Libraries.Language.Errors.!UndefinedObjectError
    use Libraries.Containers.List
    use Libraries.Containers.Array
    use Libraries.Compute.Random
</pre>
<h2>Class <code>MyErrors</code></h2>
<p>
    In class <code>MyErrors</code>, create the following actions:
</p>
<ul>
    <li>
        <b>
            action UndefinedObjects
        </b>
    </li>
</ul>
<p>
    The error <code>UndefinedObjects</code> is thrown when an action is called on an undefined object. For example, if you were to have a list or an array that was undefined, then tried to use any of the actions from the <code>List</code> or <code>Array</code> class, this error would be thrown:
</p>
<pre class="code">
    List &lt integer&gt myList = undefined
    myList:Get(0) //results in an UndefinedObjectsError
</pre>
<p>
    Write this action in such a way as to cause the compiler to throw an <code>UndefinedObjectsError</code>, and then correct the error with a check/detect that will tell the user an error had occurred, output the error, and then fix it. In the case above, you could add an item to <code>myList</code>. Then, the Get call wouldn't result in an error.
</p>
<ul>
    <li>
        <b>
            action Cast
        </b>
    </li>
</ul>
<p>
    The <code>CastError</code> is thrown when there is an error while casting values. Write this action in such a way as to cause the compiler to throw a <code>CastError</code>, then correct the error with a check/detect that will tell the user an error had occurred, output out the error, and then fix it.
</p>
<ul>
    <li>
        <b>
            action InvalidLocation
        </b>
    </li>
</ul>
<p>
    The <code>InvalidLocationError</code> is thrown when an invalid memory location is accessed. For example, trying to Set a value in an array to an invalid index. As in Lab 7_3, write this action in such a way as to cause the compiler to throw an <code>InvalidLocationError</code>, then correct the error with a check/detect that will tell the user an error had occurred, output the error, and then fix it.
</p>
<ul>
    <li>
        <b>
            action OutOfBounds
        </b>
    </li>
</ul>
<p>
    The <code>OutOfBoundsError</code> is thrown when a parameter is outside of the specified range for an action. For this action, create a random number generator that makes random integers up to a maximum integer (you can use the <code>RandomInteger(integer)</code> action to do this). As an argument to this, ask the user for a maximum number that is negative. When ran, this will result in an <code>OutOfBoundsErrror</code>. Correct the error with a check/detect that will tell the user an error had occurred, output the error, and then fix it.
</p>
<h2>Sample Output</h2>
<p>
    In <code>Main</code>, call each of the above actions. Each action should be created so that the user is being told some value. This way, we know that the detect blocks worked correctly and the error was handled. An example output for the action <code>OutOfBounds</code> could look like this:
</p>
<pre class="code">
    enter a negative number
    -10
    Error detected.  The maximum number must be positive.
    enter a positive number
    20
    the random integer is 2
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>