<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 4';
</script>

<h1>Chapter 4: Actions</h1>
<h2>Questions</h2>
<ol><li>Why is a large Main action in a project a bad programming practice? List and explain each reason. 
</li><li>What is the purpose of an action? Give an example of what an action might do in a program.
</li><li>Distinguish between an implicit main action and an explicit main action.  List one advantage for each technique and provide an example in Quorum.
</li><li>List three general rules for naming actions.  
</li><li>In Quorum, it is recommended that Pascal case be used. Give an example, in Quorum, of a valid action signature using Pascal case.
</li><li>Distinguish between an implicit call and an explicit call to an action.  Give an example, in Quorum, to call the <tt>SayHelloWorld()</tt> action for each.
</li><li>Define the terms <i>argument</i> and <i>parameter</i>.
</li><li>What is the difference between an argument and a parameter? Give an example of each in Quorum code.
</li><li>What is meant by the return value of an action? What is the purpose of returning a values?
</li><li>What does the <tt>return now</tt> keyword do?  Give an example in plain English of when it should be used.
</li><li>What is action overloading?
</li><li>What is recursion?  Explain in both general and computer science language.
</li><li>Why is it necessary to set up a base case when using recursion?
</li><li>Suppose the <tt>Dog</tt> object exists in the <tt>Libraries.Animal</tt> package.  We would like to add a new action to the <tt>Dog</tt> class to instruct the dog to bark in a more detailed way.  Write an action that accepts the values of the pitch of a bark, how many times a dog barks, and the length of time it takes a dog to bark.
</li><li><strong>Hard:</strong> Actions are useful in separating individual tasks and organize code to control a variety of software behaviors in the program.  Think of an advantage of using the actions that is not discussed in the chapter.  Explain your reasoning.
</li></ol>
<h2>Essay Questions</h2>
<ol><li>Actions are a very important aspect of writing and organizing code to accomplish tasks.  Explain why actions are relevant to use and provide an example of a situation for each feature of an action(e.g. parameters, returns, etc.).  For each feature of an action, explain what they should be used for and why it is important to know how to use them.
</li></ol><ol start="2"><li><strong>Hard:</strong> Programs function the same with and without actions.  Write a small program with actions and the same program without actions.  Provide the code for the program with and without actions.  Write at least one paragraph to explain the difference between those programs. The programs should be functionally equivalent, that is, they both do the same thing.
</li></ol>
<h2>In Class Lab Assignments</h2>
<ul>
<li> <a href="lab4_1">Lab Manual 4.1</a></li>
<ul>
<li>Introduce the fundamentals of actions</li>
<li>Write simple actions</li>
<li>Write actions with parameters and a return value</li>
<li>Call actions</li>
</ul>
</ul>
<ul>
<li><a href="lab4_2">Lab Manual 4.2</a></li>
<ul>
<li>Write a Rock Paper Scissors game utilizing actions</li>
<li>Gain hands-on experience organizing programs into actions</li>
<li>Write a program given a specification or outline of code.</li>
</ul>
</ul>
<ul>
<li><a href="lab4_3">Lab Manual 4.3</a></li>
<ul>
<li>Write a trip cost calculation program in Quorum</li>
<li>More hands-on experience using actions</li>
<li>Write actions given a description of the actions</li>
</ul>
</ul>
<h2>Short Programming Projects</h2>
<ul>
<li><a href="assignment4_1">Assignment Manual 4.1</a></li>
<ul>
<li>Write a calculator for common mathematical functions such as addition, subtraction, multiplication, division, exponentiation</li>
<li>Gain experience using parameters and returning values</li>
</ul>
</ul>
<ul>
<li><a href="assignment4_2">Assignment Manual 4.2</a></li>
<ul>
<li>Write a program to calculate the total time required to travel a certain distance at a specific speed</li>
<li>Understand and use action overloading</li>
</ul>
</ul>
<h2>Challenge Programming Projects</h2>
<ul>
<li><a href="assignment4_3">Assignment Manual 4.3</a></li>
<ul>
<li>Modify the existing travel reservation program.</li>
<li>Use actions to break down the travel reservation program into more logical pieces.</li>

<?php include("../../include/footer.php"); ?>