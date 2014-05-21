<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 7.2 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<h1>Short Assignment 7.2: Binary Search Trees</h1>
<h2>Objectives</h2>
<p>
    The goal of this assignment is to learn the following:
</p>
<ul>
    <li>
        When to use Binary Trees vs Binary Search Trees
    </li>
    <li>
        Tree efficiencies
    </li>
    <li>
        How to create BST's and insert into them
    </li>
    <li>
        How to manipulate various nodes in a BST.
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this assignment you will create a program that builds binary search trees. You will add actions that allow the tree to be manipulated, such as adding more values and deleting values. Recall that a binary tree is made of multiple nodes, where each node contains a "left" reference, a "right" reference, and a data element. The root is the topmost node in the tree. The left and right references point to smaller "subtrees" on either side of a node, if they exist. If not, they point to nothing(undefined). A binary search tree(BST), then, is an ordered binary tree. The nodes in a BST are ordered so that for each node, all elements in its left subtree are less than or equal(&#8804;) to the node, and all the elements in its right subtree are greater than the node(&gt). For instance, if you had a BST with the values 1, 2, and 3, if 2 was the root node, then 1 would be placed to the left, and 3 would placed to the right. The major advantage of binary search trees over other data structures is that the time it takes to sort, search, insert, and delete items can be very efficient. This is possible because of their "tree-like" structure. The table below shows the average case and the worst case time complexities for different actions done to a BST.
</p>
<table class="table">
    <tr>
        <td>
            &#160;
        </td>
        <td>
            Average Case
        </td>
        <td>
            Worst Case
        </td>
    </tr>
    <tr>
        <td>
            Search
        </td>
        <td>
            O(log n)
        </td>
        <td>
            O(n)
        </td>
    </tr>
    <tr>
        <td>
            Insert
        </td>
        <td>
            O(log n)
        </td>
        <td>
            O(n)
        </td>
    </tr>
    <tr>
        <td>
            Delete
        </td>
        <td>
            O(log n)
        </td>
        <td>
            O(n)
        </td>
    </tr>
</table>
<h2>Design Requirements</h2>
<p>
    The general form of a BST can be made like this:
</p>
<pre class="code">
    class Node

    public Node left = undefined
    public Node right = undefined
    public integer data = 0

    action Node(integer newData)
    left = undefined
    right = undefined
    data = newData
    end
    end
</pre>
<p>
    A binary tree is built so that each node has a left and right reference, and a data element. The class above can be used to build a basic binary tree. In this case, class Node is a "dumb" class, it's used for creating and storing values in the nodes of the tree. Using the <code>Node</code> class above, create the following actions:
</p>
<ul>
    <li>
        <b>
            action BinaryTree
        </b>
    </li>
</ul>
<p>
    Creates an empty binary tree by creating an undefined root reference.
</p>
<ul>
    <li>
        <b>
            action Lookup(integer value) returns boolean
        </b>
    </li>
</ul>
<p>
    Returns true if the given value is in the tree. This action uses a recursive helper action:
</p>
<ul>
    <li>
        <b>
            private action Lookup(Node node, integer value) returns boolean
        </b>
    </li>
</ul>
<p>
    Given a node, recursively search for the given value.
</p>
<ul>
    <li>
        <b>
            action Insert(integer value)
        </b>
    </li>
</ul>
<p>
    Inserts the given value into the bst in the proper place. Uses a recursive helper:
</p>
<ul>
    <li>
        <b>
            private action Insert(Node node, integer data) returns Node
        </b>
    </li>
</ul>
<p>
    Given a node, recursively go down the tree and insert the given data into the tree.
</p>
<ul>
    <li>
        <b>
            action SayTree
        </b>
    </li>
</ul>
<p>
    Says (or prints) the node values of the tree "inorder." So the tree...
</p>
<img src="https://quorum.svn.sourceforge.net/svnroot/quorum/trunk/quorum/libraries/documents/curriculum/chapter7/tree.PNG" alt="Tree"></img>
<p>
    would produce the output "1 2 3 4 5."
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
            action Remove(integer value) returns boolean
        </b>
    </li>
</ul>
<p>
    This action removes a given value from the bst. There are 3 cases for removing a value from a bst.
</p>
<li>
    1: Node to be removed has no children
</li>
<p>
    If there are no children attached to the value, then only delete that value.
</p>
<li>
    2: Node to be removed has one child
</li>
<p>
    In this case, node is cut from the tree and the algorithm links the single child node (with it's tree, if it has one) directly to the the parent of the removed node.
</p>
<li>
    3: Node to be removed has 2 children
</li>
<p>
    This is the most complex case. First, find the minimum element in the right sub-tree of the element you want to remove. Next, replace the value of that minimum element with the value of your target node. Lastly, remove the minimum node from the bst. *Hint* First, check if the root exists. If not, the tree is empty and the value that should be removed can't. Then, check if the root value is the one to be removed. If it is, remove it.
</p>
<ul>
    <li>
        <b>
            action RightMinValue returns integer
        </b>
    </li>
</ul>
<p>
    This action should return the minimum value in the right sub-tree. *Hint* uses a recursive call.
</p>
<h2>Class <code>Main</code></h2>
<p>
    In Main, populate a tree with random integers between 0 and 500. Let the user input the size of the tree, that is, how many integers will be placed into the tree. After populated, prompt the user for integer values to search the tree for. Perform the search, and then tell the user if the value(s) were present. Then tell the user the values in the tree in order. Next, prompt the user for integers to delete from the tree. If the integers are present, delete them. If they are not present, then tell the user those integers are not in the tree. Lastly, tell the user the values in the tree in order once again.
</p>
<h2>Sample Output</h2>
<p>
    When run, your output may be similar to the following:
</p>
<pre class="code">
    How many nodes should our tree have?
    3
    The tree looks like 100 200 300
    Enter an integer to search for (between 0 and 500)
    100
    100 was found
    Enter an integer to add to the tree
    400
    Now the tree looks like 100 200 300 400
    Enter an integer to add to the tree
    500
    Now the tree looks like 100 200 300 400 500
    Enter an integer to delete from the tree
    100
    deleted successfully
    Enter an integer to delete from the tree
    500
    deleted successfully
    Enter an integer to delete from the tree
    245
    The integer 245 is not present in the tree.
    Now the tree looks like 200 300 400
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>