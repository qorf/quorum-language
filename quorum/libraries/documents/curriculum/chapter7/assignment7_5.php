<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 7.5: Binary Search Tree with Sounds | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 7.5</h1>
		<p>Binary Search Tree with Sounds</p>
	</div>
</div>

    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>

<!--<h1>Challenge Assignment 7.5: Binary Search Tree with Sounds</h1>-->
<h2>
    Objectives
</h2>
<p>
    The goal of this assignment is to learn the following:
</p>
<ul>
    <li>
        Binary Search Trees with extended actions
    </li>
    <li>
        How to use the Libraries.Sound.Music library
    </li>
    <li>
        How to traverse a tree with sounds
    </li>
    <li>
        How to manipulate various nodes in a BST.
    </li>
</ul>
<h2>Overview</h2>
<p>
    This assignment will build off of <a href="assignment7_2.php">assignment 7_2</a>: you will create a BST and will implement several more advanced actions that perform interesting modifications to your BST. Also, instead of being told the values of a given node in the BST, you will create an action that plays notes based on the values of a given node. This way, your tree will create a kind of melody when you traverse it, and you will get to "hear" the values in the tree.
</p>
<h2>Design Requirements</h2>
<p>
    In <a href="assignment7_2.php">assignment 7_2</a>, you created actions that built a BST. For this assignment, you can copy/paste the following classes and actions:
</p>
<pre class="code">
    * All of class Node (You do not need to include action RightMinValue)
    * action BinaryTree()
    * action Lookup(integer data) returns boolean
    * private action Lookup(Node node, integer data) returns boolean
    * action Insert(integer data)
    * private action Insert(Node node, integer data) returns Node
</pre>
<p>
    Using the <code>Node</code> class above, implement the following actions:
</p>
<ul>
    <li>
        <b>
            action Size() returns integer
        </b>
    </li>
</ul>
<p>
    Returns the number of nodes in the tree. Uses a recursive helper that goes down the tree and counts each node.
</p>
<ul>
    <li>
        <b>
            private Size(Node node) returns integer
        </b>
    </li>
</ul>
<p>
    Helper action for <code>Size()</code>. Should traverse the tree and count each node it hits.
</p>
<ul>
    <li>
        <b>
            action SayTree
        </b>
    </li>
</ul>
<p>
    This is implemented much the same as the <code>SayTree</code> action from <a href="assignment7_2.php">assignment 7_2</a>, except this action will use the <code>Music</code> class to play MIDI notes based on the integer value of the nodes "in order." So the tree...
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/tree.PNG" alt="Tree"></img>
<p>
    would play the MIDI notes for "1 2 3 4 5." This can be done using the <code>Play(integer note, number duration)</code> action from class <code>Music</code>. For example, the following:
</p>
<pre class="code">
    use Libraries.Sound.Music
    Music music
    music:Play(60, 0.5)
    music:Close()
</pre>
<p>
    would play a "Middle C" for half of a second. Notice that the <code>Close()</code> action was called after all music was done being played. This action has to be called or the program will not exit properly.
</p>
<ul>
    <li>
        <b>
            private action SayTree(Node node)
        </b>
    </li>
</ul>
<p>
    Traverses the tree recursively to each node
</p>
<ul>
    <li>
        <b>
             action SayPostOrder()
        </b>
    </li>
</ul>
<p>
    This action will use the <code>Music</code> class to play MIDI notes based on the integer value of the nodes in "postorder." so the tree...
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/tree.PNG" alt="Tree"></img>
<p>
    would play the MIDI notes for "1 3 2 5 4."
</p>
<ul>
    <li>
        <b>
            private action SayPostOrder(Node node)
        </b>
    </li>
</ul>
<p>
    Recursive helper action for <code>SayPostOrder()</code>. Traverses the tree to each node.
</p>
<ul>
    <li>
        <b>
             action HasPathSum(integer sum) returns boolean
        </b>
    </li>
</ul>
<p>
    Given a tree and a sum value, returns true if there is a path starting from the root so that the accumulated values along that path equals the given sum. So, for the tree... 
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/tree.PNG" alt="Tree"></img>
<p>
    if the input sum was "9" then this action would return true, since 4+5 = 9.
</p>
<ul>
    <li>
        <b>
            action HasPathSum(Node node, integer sum) returns boolean
        </b>
    </li>
</ul>
<p>
    The helper action for <code>HasPathSum(integer sum)</code>. Hint: subtract the current node value from the sum as you traverse down the tree.
</p>
<ul>
    <li>
        <b>
             action Mirror()
        </b>
    </li>
</ul>
<p>
    Changes the tree into its mirror image. This can be done by swapping the left and right references of the tree for every subtree. So the tree...
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/tree.PNG" alt="Tree"></img>
<p>
    should be changed to...
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/reverse.PNG" alt="Reverse"></img>
<ul>
    <li>
        <b>
            private action Mirror(Node node)
        </b>
    </li>
</ul>
<p>
    The recursive helper for <code>Mirror()</code>. It should traverse the tree and swap the left and right references of the tree for every subtree.
</p>
<ul>
    <li>
        <b>
             action CountTrees(integer numValues) returns integer
        </b>
    </li>
</ul>
<p>
    This recursive action should return the number of structurally unique BST's that can be made with <code>numValue</code> number of nodes. Hint: if each value could the root, then find the size of the left and right subtrees.
</p>
<h2>Class <code>Main</code></h2>
<p>
    In <code>Main</code>, create a random number generator to create integers between 30 and 120. These number work well, since on the chromatic scale of 1 to 127, the notes represented by integers less than 30 and greater than 120 are generally painful to listen to. Next, ask the user how large of a tree they want to create, and then build a tree of that size using your random number generator. After, call the action <code>SayTree()</code>.
</p>
<p>
    Next, ask the user to enter an integer to search for in the tree. Preform the search, and if tell the user if the integer was present or not.
</p>
<p>
    Next, create a loop that will ask the user for an integer to add to the tree, add that integer, and call <code>SayTree()</code>. This should be done 3 times.
</p>
<p>
    Next, tell the user the size of the tree. After, tell the user the nodes in order, and then in post order.
</p>
<p>
    Next, mirror the tree. Tell the user the tree is being mirrored, and then tell the user the nodes in order, and then in post order.
</p>
<p>
    Next, create a loop that will ask the user for an integer value for a path sum. Call the action <code>HasPathSum()</code> and tell the user if the sum they entered was present in the tree or not. This should be done 3 times.
</p>
<p>
    Lastly, ask the user to enter an integer value to find the number of structurally unique trees given that number of nodes. Call <code>CountTrees()</code> and report to the user the number of unique trees that can be built.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following:
</p>
<pre class="code">
    How many nodes should our tree have?
    10
    *plays notes*

    Enter an integer to search for (between 30 and 120)
    60
    60 was found!

    Enter an integer to add to the tree
    75
    The tree looks like
    *plays notes*
    Enter an integer to add to the tree
    76
    The tree looks like
    *plays notes*
    Enter an integer to add to the tree
    77
    The tree looks like
    *plays notes*

    The tree currently has a size of 13
    The nodes in order are
    *plays notes*
    The nodes in post order are
    *plays notes*

    Mirroring the tree now...
    The nodes in order are
    *plays notes*
    The nodes in post order are
    *plays notes*

    Enter an integer value for a path sum
    125
    The sum 125 does not exist in the tree
    Enter an integer value for a path sum
    200
    The sum 200 does not exist in the tree
    Enter an integer value for a path sum
    221
    The sum 221 is a sum of the integers on some path in the tree!

    Enter a number of nodes to find structurally unique trees
    8
    The number of structurally unique trees that can be built from 8 nodes is 27
    Enter a number of nodes to find structurally unique trees
    20
    The number of structurally unique trees that can be built from 20 nodes is 216
</pre>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 