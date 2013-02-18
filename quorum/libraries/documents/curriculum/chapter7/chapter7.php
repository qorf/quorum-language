<!--Developed and written by Brandon Spencer-->
<!--10/4/2012-->
<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 7';
</script>

<h1>Chapter 7: Errors and Data</h1>
<h2>Questions</h2>
<ol>
    <li>
        Which data structure is contiguous in memory: arrays, or lists?
    </li>
    <li>
        What happens when an array gets filled up?  Can you resize an array?
    </li>
    <li>
        What library must you include to use data structures such as arrays or lists?
    </li>
    <li>
        What is the difference between a list and an array in Quorum?
    </li>
    <li>
        What is at the end of myList at the end of this code:
        <pre class="code">
            myList:Add(21)
            myList:Add(21)
            myList:Add(0, 22)
            myList:Add(3, 23)
            myList:AddToEnd(23)
            myList:Add(0, 24)
            myList:Remove(23)
        </pre>
    </li>
    <li>
        In what ways do lists perform better than arrays?
    </li>
    <li>
        What’s the difference between a Binary Tree and a Binary Search Tree?
    </li>
    <li>
        How does the “tree” structure of binary search trees affect actions such as insert or delete?
    </li>
    <li>
        Show the trees that would be formed for the following data items if the data items were inserted in that order.  You only need to draw the final trees and no intermediate steps.
    </li>
    <ol>
        <li>
            45, 30, 15, 50, 60, 20, 25, 90
        </li>
        <li>
            90, 30, 15, 50, 60, 20, 25, 45
        </li>
    </ol>
    <li>
        Starting with an empty bst, in what order must you insert the items to get the following tree?
    </li>
    <li>
        What is a hash function?
    </li>
    <li>
        What is chaining(in terms of hash tables)?
    </li>
    <li>
        List 3 pros to chaining.
    </li>
    <li>
        List 3 cons to chaining.
    </li>
</ol>
<h2>Essay Questions</h2>
<ol>
    <li>
        Describe an algorithm for shuffling an array of size N that is filled with integers, in order, from 0 to N – 1. In terms of big O, what’s the runtime of the shuffle.  If you’d like, pseudo code would be acceptable.
    </li>
    <li>
        Let’s say you were told by your employer to create a program that searches for and randomly chooses an ID number of a faculty member for your company.  Your company is large and has a large number of employees, and this program is going to be used like a raffle ticket for an extra day of vacation.  Which data structure (binary search tree, hash table, linked list, or array) would you use for this program, and why?
    </li>
</ol>
<h2>In Class Lab Assignments</h2>
<ul>
    <li>
        <a href="lab7_1.php">Lab Manual 7.1</a>
    </li>
    <li>
        Create and use arrays
    </li>
    <li>
        Adding, deleting, and printing items from an array
    </li>
    <li>
        Using strings in arrays
    </li>
</ul>
<ul>
    <li>
        <a href="lab7_2.php">Lab Manual 7.2</a>
    </li>
    <li>
        Create and use arrays
    </li>
    <li>
        Create and use lists
    </li>
    <li>
        Understand lists vs arrays, and when to use each
    </li>
    <li>
        Adding and deleting items from strings and arrays
    </li>
</ul>
<ul>
    <li>
        <a href="lab7_3.php">Lab Manual 7.3</a>
    </li>
    <li>
        Using arrays and lists together
    </li>
    <li>
        Adding and deleting items from strings and arrays
    </li>
    <li>
        Big O notation and algorithm analysis
    </li>
</ul>
<h2>Short Programming Projects</h2>
<ul>
    <li>
        <a href="assignment7_1.php">Assignment Manual 7.1</a>
    </li>
    <li>
        Creating Class actions and using derived actions
    </li>
    <li>
        Using Inheritance
    </li>
</ul>
<ul>
    <li>
        <a href="assignment7_2.php">Assignment Manual 7.2</a>
    </li>
    <li>
        Creating Binary Search Trees
    </li>
    <li>
        Asymptotic analysis for inserting, deleting, and finding nodes
    </li>
</ul>
<ul>
    <li>
        <a href="assignment7_3.php">Assignment Manual 7.3</a>
    </li>
    <li>
        Creating Hash tables
    </li>
    <li>
        Collision resolution
    </li>
    <li>
        Adding and searching for items in a hash table
    </li>
</ul>
<ul>
    <li>
        <a href="assignment7_4.php">Assignment Manual 7.4</a>
    </li>
    <li>
        Error Checking
    </li>
    <li>
        Error handling
    </li>
    <li>
        Common Errors
    </li>
</ul>
<h2>Challenge Programming Projects</h2>
<ul>
    <li>
        <a href="assignment7_5.php">Assignment Manual 7.5</a>
    </li>
    <li>
        BST Trees extended actions
</li>
    <li>
        Use the Libraries.Sound.Music library
    </li>
    <li>
        Traversing a tree with sounds
    </li>
</ul>

<?php include("../../include/footer.php"); ?>