<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 6_1';
</script>

<h1>Short Assignment: Geometry Calculations using Inheritance</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul><li>Using inheritance
</li><li>Creating class actions and using derived actions
</li><li>Having source code in multiple files
</li></ul><h2>Overview</h2>
<p>
In this assignment we will be creating a program that computes area, volume, and other geometric attributes for a variety of 2D and 3D shapes.  In this program, you will create a class hierarchy through inheritance, so that your base class, <tt>Shape</tt>, has derived classes <tt>TwoDShape</tt> and <tt>ThreeDShape</tt>, and <tt>TwoDShape</tt> has derived classes <tt>Circle</tt>, <tt>Square</tt>, and <tt>Triangle</tt>, and <tt>3DShape</tt> has derived classes <tt>Sphere</tt>, <tt>Cube</tt>, and <tt>Pyramid</tt>.  Because the 2D shapes and 3D shapes require many of the same things to complete the area and volume formulas, we can use inheritance to reuse the same variables and actions, creating a more robust program.
</p>
<h2>Design Criteria</h2>
<ul><li>Create a new assignment and label it <strong>Assignment6_1</strong>
</li><li>Create a source file named Shape.quorum
</li><li>Create a source file named TwoDShape.quorum
</li><li>Create a source file named ThreeDShape.quorum
</li><li>Create a source file named Circle.quorum
</li><li>Create a source file named Square.quorum
</li><li>Create a source file named Triangle.quorum
</li><li>Create a source file named Sphere.quorum
</li><li>Create a source file named Cube.quorum
</li><li>Create a source file named Pyramid.quorum
</li></ul><h3>Class <tt>Shape</tt></h3>
<p>
Class <tt>Shape</tt> will contain Getter and Setter actions for the following variables:
</p>
<ul><li><tt>number side</tt> //the length of a side of a square, or cube.
</li><li><tt>number height</tt> //the height of a triangle
</li><li><tt>number radius</tt>
</li><li><tt>number base</tt> //the length of the base of a triangle
</li></ul><p>
This class should also have a static variable, number pi = 3.1415.
</p>
<h3>Class <tt>TwoDShape</tt></h3>
<p>
Class <tt>TwoDShape</tt> will contain actions that will ask for user input and use that input as the arguments for the Setter actions from base class <tt>Employee</tt>.  Note: you will need to cast the input as type number, otherwise you will receive a compiler error.
</p>
<h3>Class <tt>ThreeDShape</tt></h3>
<p>
Class <tt>ThreeDShape</tt> will do the same as class <tt>TwoDShape</tt>, except that the user should be queried to enter input for cube, pyramid, and sphere, instead of for square, triangle, and circle.
</p>
<h3>Class <tt>Circle</tt></h3>
<p>
Class <tt>Circle</tt> is a derived class from class <tt>TwoDShape</tt>.  It will have two actions:
</p>
<pre class="code">action CalculateArea(number radius) returns number
</pre><p>
and
</p>
<pre class="code">action CalculateCircumference(number radius) returns number
</pre><h3>Class <tt>Square</tt></h3>
<p>
Class <tt>Square</tt> is a derived class from <tt>TwoDShape</tt>. It will have two actions:
</p>
<pre class="code">action CalculateArea(number side) returns number
</pre><p>
and
</p>
<pre class="code">action CalculatePerimeter(number side) returns number
</pre><h3>Class <tt>Triangle</tt></h3>
<p>
Class <tt>Triangle</tt> is a derived class from <tt>TwoDShape</tt>. It will have one action:
</p>
<pre class="code">action CalculateArea(number base, number height) returns number
</pre><h3>Class <tt>Cube</tt></h3>
<p>
Class <tt>Cube</tt> is derived from <tt>ThreeDShape</tt>.  It will have two actions, one to calculate the volume of a cube, and one to calculate surface area of a cube.
</p>
<h3>Class <tt>Sphere</tt></h3>
<p>
Class <tt>Sphere</tt> is derived from <tt>ThreeDShape</tt>.  It will have two actions, one to calculate the volume of a sphere, and one to calculate the surface area of a sphere. Note: the variable <tt>pi</tt> that you made in class <tt>Shape</tt> can be accessed using the <tt>parent:</tt> keyword.
</p>
<h3>Class <tt>Pyramid</tt></h3>
<p>
Class <tt>Pyramid</tt> is derived from <tt>ThreeDShape</tt>.  It will have one action that calculates the volume of a pyramid.
</p>
<p>
<h3>Class <tt>Main</tt></h3>
</p>
<p>
Class <tt>Main</tt> will have three actions. One will call all the actions from <tt>Circle</tt>, <tt>Square</tt>, and <tt>Triangle</tt>, and will report the calculations of each to the user.  For example:
</p>
<pre class="code"> say "The area of a square with side lengths of" + side + "is" + squareArea
 say "The perimeter of a square with side lengths" + side + "is" + squarePerimeter
 say "The area of a triangle with a base of" + base + "and height of" + height + "is" + triangleArea
 say "The area of a circle with radius of" + radius + "is" + circleArea
 say "The circumference of a circle with a diameter of" + (2*radius) + "is" + circumference
</pre><p>
The second action will do the same as above, except will call the <tt>Sphere</tt>, <tt>Cube</tt>, and <tt>Pyramid</tt> actions, and report those calculations to the user.
</p>
<p>
The third is action Main, which will call the previous two actions.  When ran, the user should be asked to input numbers for the variables created in class <tt>Shape</tt>.  After receiving input, the program will do the various calculations, then output (say) the results to the user.
</p>
<h2>Formulas</h2>
<ul><li>Area of a square: <strong>side * side</strong>
</li><li>Area of a circle: <strong>pi * radius<sup>2</sup></strong><sup>
</li><li>Circumference of a circle: <strong>pi * 2 * radius</strong>
</li><li>Area of a triangle: <strong>1/2base * height</strong>
</li><li>Volume of a cube: <strong>side</strong></sup><strong>3</strong>
</li><li>Surface area of a cube: <strong>6 * side<sup>2</sup></strong><sup>
</li><li>Volume of a sphere: <strong>4/3 * pi * radius</strong></sup><strong>3</strong>
</li><li>Surface area of a sphere: <strong>4 * pi * radius<sup>2</sup></strong><sup>
</li><li>Volume of a pyramid: <strong>1/3 * (area of the base) * height</strong>
</li></ul>

<?php include("../../include/footer.php"); ?>