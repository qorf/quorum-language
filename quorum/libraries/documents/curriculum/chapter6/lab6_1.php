<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 6.1: An Introduction to Inheritance | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Lab 6.1</h1>
		<p>An Introduction to Inheritance</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>Lab 6.1: An Introduction to Inheritance</h1>-->
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following concepts:
</p>
<ul>
<li>How to create and use a class hierarchical system</li>
<li>How a class inherits actions from another</li>
<li>How accessor actions and access modifiers work</li>
<li>How classes work</li>
</ul>
<h2>Overview</h2>
<p>
  In this lab, you will explore the notion of inheritance. Inheritance allows one class to be used as a basis for defining another related class. When a class that is derived from another class it inherits the data and actions of that class. This way, you are able to reuse code and build more compact and maintainable programs.

  You will create two classes, Circle and Sphere, and observe how the actions declared in one can be accessed and used in another. You will start with creating a radius class variable, then will work up to using that radius class variable in multiple classes and in multiple actions. By the end of the lab, you will see how the Sphere class can use the actions defined in the Circle class to calculate various properties of a sphere.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <b>Lab6_1</b>.  In the <code>Main.quorum</code> file, it should contain a <code>Main</code> class and <code>Main</code> action.
</p>
<p>
You will create two additional classes in this project. In a New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively. You can also access the New File dialog with the keyboard shortcut Ctrl + N. Then, name the new file <code>Circle</code> in a New Quorum Class dialog. Repeat the above steps to create a Class named <code>Sphere.</code>
</p>
<p>
  You will specify code for <code>Circle</code>, <code>Sphere</code>, and <code>Main</code> classes in the next three tasks.
</p>
<h2>Task 2: Creating Class Variables</h2>
<p>
  Now create a class variable and experiment with how inheritance works.
</p>
<p>
In the <code>Circle</code> class, add the class variable <b>radius</b> of type <code>number</code>.  Recall, type <code>number</code> will allow us more precision than integer, so you can have a radius of 1.5 instead of 1 or 2.  Set its default value to zero.
</p>
<p>
  Now add a <code>GetRadius</code> and <code>SetRadius</code> action to help get a value for radius. Just as you learned in chapter 5, create an access action and access modifier for radius. Creating actions such as these to obtain values can help make your programs easy to modify and resilient to user errors. In task 3, you will be using inheritance to access these actions in class <code>Sphere</code>. Thanks to inheritance, you only need to write the actions once, and can then use them in both classes to obtain a value for radius.
</p>
<h2>Task 3: Inheriting Actions From Another Class</h2>
<p>
  Now that you have your getter and setter for radius, you need to inherit those methods in class <code>Sphere</code>. To inherit the actions of another class, tell Sodbeans that "class A" is a "B". The keywords here are "is" which is used to inherit one class into another. Lets rewrite class <code>Sphere</code> so that Sodbeans knows class <code>Sphere</code> is inheriting the actions of class <code>Circle</code>. Your code should look similar to the following:
</p>
<p><pre class="code"><code>
class Sphere is Circle
end
</code></pre></p>
<p>
  Great! Now use the <code>SetRadius</code> method you declared in class <code>Circle</code> to create a radius for Sphere. This time, you will get user input for the radius, and use that input as an argument to <code>SetRadius</code>:
</p>
<p><pre class="code"><code>
  class Sphere is Circle    //creates a hierarchy, where sphere is inheriting from Circle
  action SphereSetRadius
  SetRadius(cast(number, input("Enter a radius")))  //this is a method declared in our Circle class
  end
  end
</code></pre></p>
<p>
  Now use the different actions to create diameters for <code>Circle</code> and <code>Sphere</code>. In Main, create <code>Circle</code> and <code>Sphere</code> objects. Next, create number variables <code>circleRadius</code> and <code>sphereRadius</code>. Using the set and get actions in class <code>Circle</code>, assign a radius value to <code>circleRadius</code>. Recall that in action <code>SphereSetRadius</code>, you've already set the value of radius to whatever the user entered, so now you only need to get that value. Using the <code>GetRadius</code> method from class <code>Sphere</code>, assign the <code>sphereRadius</code> variable a value. Next, multiply both values by 2 to get the circle and sphere diameters, then output or say their respective values.
</p>
<h2>Task 4: Using Inherited Actions</h2>
<p>
  You'll now create actions that will calculate various circle and sphere properties. The sphere calculations will require a radius, which can only be obtained through inheritance from class <code>Circle</code>
</p>
<p>
  <li>
    <b>action CalculateVolume(number radius) returns number</b>
  </li>
</p>
<p>
<code>CalculateVolume</code> requires a <code>number</code> type parameter and returns a number. As the name indicates, this action will calculate the volume of a sphere given its radius. The equation for sphere volume is: (4/3)*pi*radius cubed.
</p>
<p>
  <li>
    <b>action CalculateSurfaceArea(number radius) returns number</b>
  </li>
</p>
<p>
  <code>CalculateSurfaceArea</code> requires a <code>number</code> type parameter and returns a number. This action calculates the surface area of a sphere given its radius. The equation for sphere surface area is: 4*pi*radius squared.
</p>
<p>
  Now it's time to use these actions. In <code>Main</code>, call the actions you created above, using the radius you obtained from the <code>Sphere</code> class as the arguments. Create a say statement that tells the user what the radius is that was used, and what the calculated number is.
</p>
<p>
  Next you'll create some actions to calculate the properties of a circle.
</p>
<p>
  <li>
    <b>
      action CalculateArea(number radius) returns number
    </b>
  </li>
</p>
<p>
  <code>CalculateArea</code> uses a number type parameter and returns a number. This action will calculate the area of a circle given its radius. The equation for circle area is: pi*radius squared.
</p>
<p>
  <li>
    <b>
      action CalculateCircumference(number radius) returns number
    </b>
  </li>
</p>
<p>
  <code>CalculateCircumference</code> uses a number type parameter and returns a number. This action will calculate the circumference of a circle given its radius. The equation for circumference is 2*pi*radius
</p>
<p>
  Now, just as you did with the sphere actions, call the actions you created above in <code>Main</code>, using the radius obtained from the <code>Circle</code> actions. Create a say statement that tells the user what the radius is that was used, and what the calculated number is.
</p>
<h2>
  Sample Output
</h2>
<p>
  Run the program and notice that a different radius is used for the circle and sphere calculations. This is because you are able to set different values using the same method. With inheritance, you were able to use the get and set actions created in class <code>Circle</code> to create a unique radius for the sphere calculations. At the same time, you could still use those same actions to create a unique radius for the circle calculations. In the case of class <code>Sphere</code>, inheritance allowed you to reuse code that had already been created. When run, your program should look similar to this:
</p>
<p>
  <pre class="code">
    <code>
      Circle diameter is 10.
      Area of a circle with a radius of 5 is 78.537
      Circumference of a circle with a radius of 5 is 31.415
      Sphere diameter is 25
      Sphere volume with a radius of 12.5 is 8181.223
      Sphere surface area with a radius of 12.5 is 1963.493
    </code>
  </pre>
</p>
<p>
  When you're done with this lab, show your instructor your code.
</p>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 