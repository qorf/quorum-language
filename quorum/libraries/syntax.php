<?php require_once("static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>On this page, we describe how to write executing
                    Quorum programs. We focus 
                    our attention on the core language features, namely 
                    1) creating and using variables, 2) control structures, 
                    3) classes and actions, and 4) libraries and extensions.</p>
	</div>
</div>

<div class="content index-content">
	<h3>Variables and Types</h3>
	<p>
	    It is often helpful to be able to tell the computer to remember 
	    information while it executes a computer program. For example, 
	    you might ask the computer to remember how many dollars are in a 
	    bank account or grades on an exam. In this section, we discuss the 
	    various ways of storing data in the Quorum programming language.
	</p>
	<ul>
	    <li><a href="documents/syntax/types.php">Types and Variables</a>: Understanding Quorum's type system.</li>
	    <li><a href="documents/syntax/operators.php">Operators</a>: Adding, subtracting, and multiplying, oh my!</li>
	    <li><a href="documents/syntax/casting.php">Type Casting</a>: Convert one type of thing to another.</li>
	    <li><a href="documents/syntax/comments.php">Comments</a>: Writing notes to yourself 
	        (or others) to help understand the code.</li>
	    <li><a href="documents/syntax/arrays.php">Arrays</a>: Storing collections of data in memory.</li>
	</ul>
	<h3>Control Structures</h3>
	<p>
	    Often times we want to control how the computer processes information. 
	    For example, we might want the computer to do one operation if a 
	    variable is greater than 10 and another if it is less then 10. Or, 
	    we might want the computer to take the same steps repeatedly. 
	    Together, these ideas are called control structures. In this section, 
	    we talk about the control structures that are available in the 
	    Quorum language.
	</p>
	<ul>
	    <li><a href="documents/syntax/repeat.php">Repeat</a>: Doing things zero or more times</li>
	    <li><a href="documents/syntax/if.php">If</a>: Do something only when a condition 
	        is true</li>            
	</ul>
	<h3>Classes and Actions</h3>
	<p>
	    As computer programs become larger, it often helps to organize 
	    computer code in a variety of ways. Probably the two most common 
	    ways to organize code are to use structures that hold a collection 
	    of data (called &quot;classes&quot; in Quorum), and a way for code 
	    to take a certain behavior (called &quot;actions&quot; in Quorum). 
	    Below are links to how these organizational mechanisms are used in 
	    the Quorum Programming Language:
	</p>
	<ul>
	    <li><a href="documents/syntax/actions.php">Actions</a>: Telling Quorum to take certain behaviors.</li>
	    <li><a href="documents/syntax/classes.php">Classes</a>: Classes allow you to make your own custom types.</li>
	    <li><a href="documents/syntax/use.php">Use statements</a>: Use statements make it 
	        easier to use classes from the standard library.</li>
	    <li><a href="documents/syntax/inheritance.php">Inheritance</a>: Create relationships between classes.</li>
	    <li><a href="documents/syntax/is.php">is</a>: A special operator that determines 
	        whether an object is a subclass of a parent.</li>
	    <li><a href="documents/syntax/blueprints.php">Blueprints</a>: Blueprint actions
	        allow you to specify that child classes must implement an action.</li>
	    <li><a href="documents/syntax/generics.php">Generics</a>: Generics allow you to specify 
	        types in container classes.</li>
	    <li><a href="documents/syntax/autoboxing.php">Auto-boxing</a>: Automatic conversion 
	        between primitive types and Object types.</li>
	    <li><a href="documents/syntax/errors.php">Error Handling</a>: This section shows how to 
	        deal with errors (called exceptions in other languages).</li>
	</ul>
	<h3>Libraries and Extensions</h3>
	<p>
	    As Quorum is a new programming language, we are still building the
	    standard library. In this section, we discuss this library, how you
	    add to it, and general recommended coding standards.
	</p>
	<ul>
	    <li><a href="documents/syntax/standards.php">Coding Standards</a>: This section describes
	        coding standards for Quorum classes.</li>
	    <li><a href="libraries.php">The Quorum Standard Library</a>: The Quorum standard library 
	        provides additional functionality for the programming language.</li>
	    <li><a href="documents/syntax/plugins.php">Writing Plugins to connect to 
                Java or C++ from Quorum</a>: This page describes how we can use
                system actions to call down to Java, and from there, other languages
                like C++.</li>
	</ul>
</div>

<?php require_once("static/templates/pagefooter.template.php"); ?>