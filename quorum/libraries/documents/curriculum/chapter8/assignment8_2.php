<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 8.2: Bubble Sort | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 8.2</h1>
		<p>Bubble Sort</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<!--<h1>Assignment 8.2: Bubble Sort</h1>-->
<h2>Objectives</h2>
<p>The goal of this assignment is to learn the following:</p>
<ul>
    <li>
        How to use nested loops and condition statements
    </li>
    <li>
        Common sorting algorithms
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this assignment you will you will write a sorting algorithm known Bubble sort. A sorting algorithm is an algorithm that puts elements of a list in a certain order. Depending on the expected the number items to be sorted, it's important to use an efficient sorting methods because this can lead to efficient use of other algorithms, such as search or merge algorithms. Bubble sort is a type of comparison sort, where the algorithm repeatedly steps through a list (or array) of items, compares each pair of adjacent items, and swaps them if they're in the wrong order. This continues until no swaps are needed, indicating that the list is sorted.
</p>
<table class="table">
    <tr>
        <td>
            <b>
                1.)
            </b>
        </td>
        <td>
            4
        </td>
        <td>
            2
        </td>
        <td>
            1
        </td>
        <td>
            3
        </td>
    </tr>
    <tr>
        <td>
            <b>
                2.)
            </b>
        </td>
        <td>
            2
        </td>
        <td>
            4
        </td>
        <td>
            1
        </td>
        <td>
            3
        </td>
    </tr>
    <tr>
        <td>
            <b>
                3.)
            </b>
        </td>
        <td>
            2
        </td>
        <td>
            1
        </td>
        <td>
            4
        </td>
        <td>
            3
        </td>
    </tr>
    <tr>
        <td>
            <b>
                4.)
            </b>
        </td>
        <td>
            2
        </td>
        <td>
            1
        </td>
        <td>
            3
        </td>
        <td>
            4
        </td>
    </tr>
    <tr>
        <td>
            <b>
                5.)
            </b>
        </td>
        <td>
            1
        </td>
        <td>
            2
        </td>
        <td>
            3
        </td>
        <td>
            4
        </td>
    </tr>
</table>
<p>
    The example above shows how an array of size 4 would get sorted by bubble sort. In step 2, the the 2 and 4 have been swapped. In step 3, the 1 and 4 have been swapped. In step 4, the 3 and 4 have been swapped. At this point, the algorithm has run through the entire list once, and has sorted most of the elements correctly, however, the first 2 elements, now 2 and 1, are not in order, so the entire list must be iterated through again, as can be seen in step 5.
</p>
<p>
    This algorithm gets its name from the way smaller elements "bubble" to the top of the list. Because of the number of times a given list must be stepped through to be sorted, the performance of this sort is sub-par, with an average case and worst case performance being the exact same O(n2). The best case is O(n), which can only occur if the list is already sorted.
</p>
<p>
    Create a new project and name it <b>Assignment8_2</b>. All of the code for this program will be done in <code>Main</code>, so no additional classes need to be created.
</p>
<h2>Main</h2>
<p>
    You will need the following libraries for this assignment:
</p>
<ul>
    <li>
        use Libraries.Compute.Random
    </li>
    <li>
        use Libraries.Containers.Array
    </li>
</ul>
<p>
    The algorithm for Bubble sort looks like this:
</p>
<pre class="code">
    Given an array, myArray, with any given number of elements:
    If myArray:Get(0) &gt myArray:Get(1)
    integer temp = myArray:Get(0)
    Set the value of myArray:Get(0) with the value of myArray:Get(1)
    Set the value of myArray:Get(1) with the value of temp
    Repeat until sorted
</pre>
<p>
    Remember, as seen in the example under <b>Overview</b>, this algorithm must first iterate through the entire array, then iterate through <b>N</b> number of times more in order to perform the swaps again.
</p>
<p>
    To populate the array, create a random integer generator to generate integers no larger than 500. Ask the user how many items they want to sort, and populate the array with that many random integers. Next, output all the items in the array before the sort, perform the Bubble sort, then output all the items in the array again to show the user the sorted elements.
</p>
<h2>Sample Output</h2>
<p>
    When run, you should get output similar to the following:
</p>
<pre class="code">
    How many items do you want to sort?
    5
    Values before the sort:
    239
    422
    101
    62
    500
    Values after the sort:
    62
    101
    239
    422
    500
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>