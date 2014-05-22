<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 7.1: Array Manipulation | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 7.1</h1>
		<p>Array Manipulation</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>  Short Assignment 7.1: Array Manipulation</h1>-->
<h2>
  Objectives
</h2>
<p>
  The goal of this assignment is to learn the following:
</p>
<ul>
  <li>
    How to create and use arrays
  </li>
  <li>
    How to manipulate arrays
  </li>
  <li>
    How to pass arrays as arguments
  </li>
  <li>
    How to use generics
  </li>
</ul>
<h2>
  Overview
</h2>
<p>
  In this assignment you will create a program that performs various manipulations on a single array, depending on what the user decides. When run, the user will be asked to enter the size of an array(an even-numbered array), and then asked if the array contains numbers or integers. If the array is of type integer, then all manipulations will be done using integers. If the array is of type number, then then all manipulations will be done using numbers. After receiving input, the user will be shown an initialized array and will be presented with a list of options for manipulating the array. After receiving input again, the program will perform the chosen manipulation on the array and will output the results to the user, along with the list of options for more array manipulation. This will repeat 10 times, and then the program will close.
</p>
<h2>
  Design Requirements
</h2>
<p>
  The transformations are described in detail below, with all the action prototypes given. For most actions, the first argument is the array, and the second is the size of the array. Create a new quorum application named <b>Assignment 7_1</b>. Next, create a second class named <code>ArrayModification</code>
</p>
<ul>
  <li>
    <b>
      action Initialize(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  Initializes the array to contain values from 0 to size - 1. It then prints out the array. The following shows what it may look like:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9
</pre>
<ul>
  <li>
    <b>
      action Square(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  Square every value in the array:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  1
  Now the array is:
  0 1 4 9 16 25 36 49 64 81
</pre>
<ul>
  <li>
    <b>
      action Cube(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  Cubes every value in the array:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  2
  Now the array is:
  0 1 8 27 64 125 216 343 512 729
</pre>
<ul>
  <li>
    <b>
      action Halve(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  Halve every value in the array:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  3
  Now the array is:
  0 0 1 1 2 2 3 3 4 4
</pre>
<ul>
  <li>
    <b>
      action Accumulate(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  For every array index N, set the value at array index N to be the sum of the values from array indexes 0 through N, as seen below:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  4
  Now the array is:
  0 1 3 6 10 15 21 28 36 45
</pre>
<ul>
  <li>
    <b>
      action Transpose(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  For every pair of neighbors in the array, switch their values. So, a[0] swaps values with a[1], and a[2] swaps values with a[3]:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  5
  Now the array is:
  1 0 3 2 5 4 7 6 9 8
</pre>
<ul>
  <li>
    <b>
      action Shift(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  Move every value in the array to the next position. For example, the value of a[5] is moved to a[6]. Make sure to move the value of the last index to the first index. Shifting can be done in either direction, depending on your preference:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  6
  Now the array is:
  9 0 1 2 3 4 5 6 7 8
</pre>
<ul>
  <li>
    <b>
      action Reverse(Array &lt Type &gt a, integer size)
    </b>
  </li>
</ul>
<p>
  Reverses the values in the array:
</p>
<pre class="code">
  Initially the array is:
  0 1 2 3 4 5 6 7 8 9

  Please select an option
  0 initialize
  1 square
  2 cube
  3 halve
  4 accumulate
  5 transpose
  6 shift
  7 reverse
  7
  Now the array is:
  9 8 7 6 5 4 3 2 1 0
</pre>
<ul>
  <li>
    <b>
      action ChooseManipulation() returns integer
    </b>
  </li>
</ul>
<p>
  When called, <code>ChooseManipulation</code> will present the user with list of available manipulations. It will ask for their input, and then return it.
</p>
<h2>Generics</h2>
<p>
  In computer programming, you often want to create a class that generically specifies an algorithm. For example, you might want to create a list of numbers, integers, or anything else. There are two ways you could accomplish this: you could create separate implementations for each data type, or you could create a "generic" class. In Quorum, you can do just that. In order to specify a generic type, you need to tell Quorum that your class is of generic type. To do this, use the keyword <code>Type</code> enclosed in angle brackets:
</p>
<pre class="code">
  class ArrayModification &lt Type &gt;
</pre>
<p>
  You'll notice that each of the action prototypes above took as an argument a generic type array. In this way, you can use the same implementation to create and manipulate arrays of type integer and number. To create an object of a generic type class, just specify the type you want the object to be:
</p>
<pre class="code">
  ArrayModification &lt integer &gt myInteger
  ArrayModification &lt number &gt myNumber
</pre>
<p>
  If you didn't have the ability to create generic types, then this program would have to contain separate actions for integer array manipulations and number array manipulations, making this program a lot longer, and more cluttered.
</p>
<h2>
  Constraints
</h2>
<ul>
  <li>
    The menu should display, or be told to the user, after each iteration
  </li>
  <li>
    Use the initialize action to first initialize the array.
  </li>
  <li>
    Each transformation must be implemented in the above actions only
  </li>
  <li>
    Ensure that your actions only assign values within the array (do not do "out-of-bounds" array assignments, or use temp arrays).
  </li>
</ul>
<h2>Sample Output</h2>
<p>
  Output should look similiar to the output shown in the above examples.
</p>