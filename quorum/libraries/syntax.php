<?php include("documents/include/header.php"); ?>
<script type="text/javascript">
    document.title += 'The Syntax of the Quorum Programming Language';
</script>
<h1>The Syntax of Quorum</h1>
<p>
    On this page, we describe the syntax, what a programmer actually types,
    to make Quorum programs execute. We focus our attention on the core language features,
    namely 1) creating and using variables, 2) control structures, 3) 
    classes and actions, and 4) libraries and extensions. 


</p>
<h3>Variables and Types</h3>
<p>
    It is often helpful to be able to tell the computer to remember 
    information while it executes a computer program. For example, 
    you might ask the computer to remember how many dollars are in a 
    bank account or grades on an exam. In this section, we discuss the 
    various ways of storing data in the Quorum programming language.
</p>
<ul>
    <li><a href="variables.html">Variables</a>: Storing information in memory.</li>
    <li><a href="operators.html">Operators</a>: Adding, subtracting, and multiplying, oh my!</li>
    <li><a href="types.html">Types</a>: Understanding Quorum's type system.</li>
    <li><a href="casting.html">Type Casting</a>: Convert one type of thing to another.</li>
    <li><a href="comments.html">Comments</a>: Writing notes to yourself 
        (or others) to help understand the code.</li>
    <li><a href="arrays.html">Arrays</a>: Storing collections of data in memory.</li>
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
    <li><a href="repeat.html">Repeat</a>: Doing things zero or more times</li>
    <li><a href="if.html">If</a>: Do something only when a condition 
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
    <li><a href="actions.html">Actions</a>: Telling Quorum to take certain behaviors.</li>
    <li><a href="classes.html">Classes</a>: Classes allow you to make your own custom types.</li>
    <li><a href="use.html">Use statements</a>: Use statements make it 
        easier to use classes from the standard library.</li>
    <li><a href="inheritance.html">Inheritance</a>: Create relationships between classes.</li>
    <li><a href="isa.html">is a</a>: A special operator that determines 
        whether an object is a subclass of a parent.</li>
    <li><a href="blueprints.html">Blueprints</a>: Blueprint actions
        allow you to specify that child classes must implement an action.</li>
    <li><a href="generics.html">Generics</a>: Generics allow you to specify 
        types in container classes.</li>
    <li><a href="autoboxing.html">Auto-boxing</a>: Automatic conversion 
        between primitive types and Object types.</li>
    <li><a href="errors.html">Error Handling</a>: This section shows how to 
        deal with errors (called exceptions in other languages).</li>
</ul>
<h3>Libraries and Extensions</h3>
<p>
    As Quorum is a new programming language, we are still building the
    standard library. In this section, we discuss this library, how you
    add to it, and general recommended coding standards.
</p>
<ul>
    <li><a href="standards.html">Coding Standards</a>: This section describes
        coding standards for Quorum classes.</li>
    <li><a href="libraries.html">The Quorum Standard Library</a>: The Quorum standard library 
        provides additional functionality for the programming language.</li>
    <li><a href="index.html">The Quorum Standard Library Application 
            Programming Interface (API) Index</a>: This page gives a raw 
        index of the classes available in the standard library.</li>
</ul>
    <?php include("documents/include/footer.php"); ?>
