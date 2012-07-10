<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab6_1';
</script>
<h1>Lab 6.1: An Introduction to Inheritance</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to understand the following concepts:
</p>
<ul>
<li>Create and use a class hierarchical  system</li>
<li>Understand how a class inherits actions from another</li>
<li>More practice with accessor actions and access modifiers</li>
<li>More practice with classes</li>
</ul>
<h2>Overview</h2>
<p>
In this lab, we will learn how to use inheritance. Inheritance is the idea of being able to access actions and fields in one class from another class. In this way, we are able to reuse code to build better programs. We will create two classes, Circle and Sphere, and observe how the actions declared in one can be accessed and used in another. We will start with creating a radius class variable, then will work up to using that radius class variable in multiple classes and in multiple actions.  By the end of the lab, we will see how our Sphere class can use the actions defined in our Circle class to calculate various properties of a sphere.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <b>Lab6_1</b>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
We will create two additional classes in this project.  In a New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new file <b>Circle</b> in a New Quorum Class dialog.  Repeat the above steps to create a Class named <b>Sphere</b>.
</p>
<p>
We will fill <tt>Circle</tt>, <tt>Sphere</tt>, and <tt>Main</tt> classes with code in the next three tasks.
</p>
<h2>Task 2: Creating Class Variables</h2>
<p>
We are will start by creating a class variable.
</p>
<p>
In the <tt>Circle</tt> class, add the class variable <b>radius</b> of type <tt>number</tt>.  Recall, type <tt>number</tt> will allow us more precision than integer, so we can have a radius of 1.5 instead of 1 or 2.  Set its default value to zero.
</p>
<p>
Now lets add a GetRadius and SetRadius action to help get a value for radius.  Just as you learned from chapter 5, create an access action and access modifier for radius. Creating actions such as these to obtain values helps keep your program modular and robust.  In task 3, we will be using inheritance to access these actions in our Sphere class.  Thanks to inheritance, we only need to write the actions once, and can then use them in both classes to obtain a value for radius.
</p>
<h2>Task 3: Inheriting Actions From Another Class</h2>
<p>
Now that we have our getter and setter for radius, lets inherit that method in our Sphere class. To inherit the actions of another class,  tell Sodbeans that &quot;class A&quot; is a &quot;B&quot;.  The keywords here are &quot;is a&quot; which is used to inherit one class into another.  Lets rewrite our Sphere class so that Sodbeans knows that class Sphere is inheriting the actions of class Circle.  Your code should look similar to the following:
</p>
<p><pre class="code"><code>
class Sphere is a Circle
end
</code></pre></p>
<p>
Great  Now lets use the SetRadius method we declared in our Circle class to create a radius for our Sphere:
</p>
<p><pre class="code"><code>
class Sphere is a Circle    //creates a hierarchy, where sphere is inheriting from Circle
    action SphereSetRadius
        SetRadius(12.5)  //this is a method declared in our Circle class
    end
end
</code></pre></p>
<p>
Now let's use the different actions to create diameters for our Circle and Sphere.  In Main, create Circle and Sphere objects.  Next, create number variables circleRadius and sphereRadius. Using the set and get actions in our Circle class, assign a radius value to circleRadius.  Recall that in our Sphere class, in action  SphereSetRadius, we've already set the value of radius to 12.5, so now we only need to get that value.  Using the GetRadius method from our Sphere class, assign the sphereRadius variable a value.  Next, multiply both values by 2 to get the circle and sphere diameters, then print or say their respective values.
</p>
<h2>Task 4: Using Inherited Actions</h2>
<p>
We are going to create actions that will calculate various circle and sphere properties.  The sphere calculations will require a radius, which can only be obtained through inheritance from our Circle class.
</p>
<p>
Create an action called  CalculateVolume that requires a number type parameter (radius) and returns a number.  As the name indicates, this action will calculate the volume of a sphere given its radius.  The equation for sphere volume is: (4/3)*pi*radius^3
</p>
<p>
Next create another action called  CalculateSurfaceArea that requires a number type parameter (radius) and returns a number.  This action calculates the surface area of a sphere given its radius.  The equation for sphere surface area is: 4*pi*radius^2
</p>
<p>
Now let's use these actions.  In Main, call the actions you created above, using the radius you obtained from the Sphere class as the arguments.  Create a say statement that tells the user what the radius is that was used, and what the calculated number is.
</p>
<p>
Now create some actions to calculate the properties of a circle.  In our Circle class, create an action called  CalculateArea that uses a number type parameter (radius) and returns a number.  This action will calculate the are of a circle given its radius.  The equation for circle area is: pi*radius^2
</p>
<p>
Create another action called  CalculateCircumference that uses a number type parameter (radius) and returns a number.  This action will calculate the circumference of a circle given its radius.  The equation for circumference is 2*pi*radius
</p>
<p>
Now, just as you did with the sphere actions, call the actions you created above in Main, using the radius obtained from the Circle actions.  Create a say statement that tells the user what the radius is that was used, and what the calculated number is.
</p>
<p>
Run the program and notice that a different radius is used for the circle and sphere calculations. This is because we were able to set different values using the same method.  With inheritance, we were able to use the get and set actions created in our Circle class to create a unique radius for our sphere calculations.  At the same time, we could still use those same actions to create a unique radius for our circle calculations.  In the case of our Sphere class, inheritance allowed us to reuse code that had already been created.  When you're done with this lab, show your instructor your code.
</p>
<?php include("../../include/footer.php"); ?>