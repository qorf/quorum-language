<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 5.2 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Learn Quorum</h1>
		<p>These pages provide extra curricular material that can be 
        freely used in the classroom.</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<h1>Lab 5.2: An Introduction to Class Variables and Access Modifiers</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to apply class scoping to class variables and access class variables outside the class</li>
<li>How to create and use accessor actions and access modifiers</li>
<li>How to set up a constructor to initialize class variables</li>
<li>More practice with classes</li>
</ul>
<h2>Overview</h2>
<p>
In this lab, you will learn how to use class variables, accessor actions, access modifiers, and a constructor.  You will create a basic Car class to learn and observe how they work with a Main class.  You will start with defining and experimenting class variables, then you will work your way up to using class variables with accessor actions and access modifiers.  Then, you will make action calls to a Car object in the Main class and understand how a constructor works.  At the end of the lab, you will fine-tune your program by organizing the code and practicing more with actions.  The goal of this lab is to help you learn and master object-oriented programming, as it is a very important part of programming.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <strong>Lab5_2</strong>.  In the <code>Main.quorum</code> file, it should contain a <code>Main</code> class and <code>Main</code> action as shown below:
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<p>
You will create an additional class in the project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name the new file <b>Car</b> in the New Quorum Class dialog.  The <code>Car</code> class will appear with the following code:
</p>
<p><pre class="code"><code>
class Car
end
</code></pre></p>
<p>
You will fill <code>Car</code> and <code>Main</code> classes with the code in the next four tasks.
</p>
<h2>Task 2: Creating Class Variables</h2>
<p>
You are going to create class variables and experiment with how they work between two classes.  There are two ways to control access to class variables: you can use access modifiers and accessor actions (getters and setters) or by making the class variables public.
In programming, you typically want to write code that is secure and encapsulated.  Encapsulation is one of the four fundamental object oriented programming concepts. It is the technique of making class variables private and providing access to them with public actions.
If a variable is private, then it can't be accessed by anything outside of the class is was created in, except through the use of getters and setters.  This is also known as data hiding. Encapsualtion gives maintainability, flexibility, and extensibility to your code.
</p>
<p>
In the <code>Car</code> class, add two class variables:
</p>
<ul><li><strong>numberOfDoors</strong> is an <code>integer</code> that keeps track of the number of doors on a car.  Define it as a <code>public</code> variable and set it to four.
</li><li><strong>color</strong> is a <code>text</code> that holds the color of a car.  Define it as a <code>private</code> variable and set it to Blue.
</li></ul><p>
The code should look similar to the following:
</p><pre class="code"><code>
class Car
    public integer numberOfDoors = 4
    private text color = &quot;Blue&quot;
end
</code></pre></p>
<p>
Then, in the <code>Main</code> class, you will add the code that will access class variables from the <code>Car</code> class.  You will need to create a <code>Car</code> object in order to use its variables and actions.  You only have two variables for now, so do the following to access them:
</p>
<ul><li>Instantiate a <code>Car</code> object.
</li><li>Call the <code>numberOfDoors</code> variable and set it to four.
</li><li>Call the <code>color</code> variable and set it to Blue.
</li></ul><p>
It should look like the sample code shown below:
</p>
<p><pre class="code"><code>
class Main
    action Main
        Car car
        car:numberOfDoors = 4
        car:color = &quot;Blue&quot;
    end
end
</code></pre></p>
<p>
Run the program and observe what happens.  Answer the following questions:
</p>
<ol><li>Which variable was accessible to the <code>Main</code> class?
</li><li>Which variable was not accessible to the <code>Main</code> class?
</li><li>What did the compiler error say about an inaccessible variable?  Why is it happening?
</li></ol>
<h2>Task 3: Creating and Using Access Modifiers and Accessor Actions</h2>
<p>
Now that you know how to use class variables and control access to them, you are going to apply the class scoping concept to class variables.  Class scoping allows you to use class variables anywhere in a class to pass values around and make any changes to the stored values in the variables.  You are going to learn how to take advantage of class scoping by creating and calling accesor actions and access modifiers (getters and setters).
</p>
<p>
Before you get started with accessor actions and access modifiers, you will need to make changes to class variables in the <code>Car</code> class.  Since you are going to use getters and setters, you don’t want variables to be defined <code>public</code>, so you will make them <code>private</code>.  The <code>private</code> keyword is optional in Quorum, because Quorum automatically defines class variables as private variables.  You will let Quorum do the work for you.  Make changes to a <code>Car</code> class to match the following code sample:
</p>
<p><pre class="code"><code>
class Car
    integer numberOfDoors = 4
    text color = “Blue”
end
</code></pre></p>
<p>
Let’s use private class variables to set up accessor actions and access modifiers to allow a <code>Main</code> class to access them.  You will start with the <code>numberOfDoors</code> variable.  First, create an accessor action and name it <strong>GetNumberOfDoors</strong>.  It has no parameter and returns an <code>integer</code> value for a number of doors on a car.  Next, create an access modifier to modify a number of doors on a car.  Name it <strong>SetNumberOfDoors</strong>.  It has a parameter and no return value.  The parameter is an <code>integer</code> value used for setting a new value for a number of doors.  The code should look like the following:
</p>
<p><pre class="code"><code>
class Car
    integer numberOfDoors = 4
    text color = &quot;Blue&quot;
    action GetNumberOfDoors returns integer
        return numberOfDoors
    end
    action SetNumberOfDoors(integer value)
        numberOfDoors = value
    end
end
</code></pre></p>
<p>
The accessor actions and access modifiers always start with “Get” and “Set” in their names, respectively.  This helps you recognize a type of an action and the purpose of its name.
</p>
<p>
It’s time to make calls to a <code>Car</code> object!  Switch to the <code>Main</code> class.  Use a <code>Car</code> object to call both <code>GetNumberOfDoors</code> and <code>SetNumberOfDoors</code> actions.  The <code>Main</code> class should look similar to the following:
</p>
<p><pre class="code"><code>
class Main
    action Main
        Car car
        car:GetNumberOfDoors()
        car:SetNumberOfDoors(2)
    end
end
</code></pre></p>
<p>
This basically gives you an idea of how to make action calls to the getters and setters.  Modify the code so that it matches the output shown below:
</p>
<p><pre class="code"><code>
The car has 4 doors.
The car has 2 doors.
</code></pre><p>
The first call should get the current value of a number of doors.  The second call should change a number of doors from four to two.  The final call displays a new change that has been made to the <code>numberOfDoors</code> variable.
</p>
<p>
Add a getter and setter for the <code>color</code> variable in the <code>Car</code> class.  Do the same that you did for the <code>numberOfDoors</code> variable in the <code>Main</code> class.  The color of a car should be set to “Black.”  The output should match the following:
</p>
<p><pre class="code"><code>
The car has 4 doors.
The color of a car is Blue.
The car has 2 doors.
The color of a car is Black.
</code></pre></p>
<h2>Task 4: Defining a Constructor</h2>
<p>
You are going to define a constructor to initialize class variables.  When an object is instantiated, a constructor is immediately called to execute the code within a constructor.  The constructor can do a variety of things, but you will focus on initializing class variables for this lab.  It is always a good practice to initialize variables to the default values in a constructor.  For example, if a user does not choose a number of doors and the color of a car, the default values will be used.
</p>
<p>
In a <code>Car</code> class, create a constructor between class variables and the first accessor action.  In a constructor, initialize <code>numberOfDoors</code> to four and <code>color</code> to Silver.  Since you are initializing variables in a constructor, you no longer need to initialize class variables outside the constructor and actions.  The code should match the following part of a <code>Car</code> class:
</p>
<p><pre class="code"><code>
class Car
    integer numberOfDoors
    text color
    on create
        numberOfDoors = 4
        color = &quot;Silver&quot;
    end
    action GetNumberOfDoors returns integer
        return numberOfDoors
    end
    …
end
</code></pre></p>
<p>
You don’t have to make any changes to the <code>Main</code> class.  Run the program and it should still function as before.
</p>
<h2>Task 5: Fine-tuning Car and Main Classes</h2>
<p>
In this final task, you are going to fine-tune a program to add user input checking to the access modifiers in the <code>Car</code> class and enhance the code in the <code>Main</code> class.
</p>
<p>
In the <code>Car</code> class, add the user input checking code to two setters based on the following descriptions:
</p>
<ul><li>The car must have two or four doors.  If a user enters an invalid value, then a value will be set to two.
</li><li>The color of a car must be Black, Red, or Silver.  If a user enters an invalid value, then a value will be set to Red.
</li></ul><p>
In the <code>Main</code> class, make action calls to set a number of doors to three and the color of a car to Purple.  Output or speak the output to see what happens to the invalid values.  The output should look like the following:
</p>
<p><pre class="code"><code>
The car has 4 doors.
The color of a car is Silver.
The car has 2 doors.
The color of a car is Black.
The car has 2 doors.
The color of a car is Red.
</code></pre></p>
<p>
You are going to make one more change to a <code>Main</code> class.  You are making a series of action calls with the same code, so you are going to convert them into two separate actions for a better reusability.  
</p>
<p>
Use the code snippet below to add new actions in a <code>Main</code> class:
</p>
<p><pre class="code"><code>
/* Action Change has three parameters and no return value. The first
 * parameter is a Car object, the second parameter is a number of doors,
 * and the third parameter is the color of a car.
 * This action modifies the existing values of a number of doors and the
 * color of a car.
 */
action Change(Car vehicle, integer numDoors, text color)
    // Insert the code here
end

/* Action Display has one parameter and no return value. The first parameter
 * is a Car object.
 * This action outputs a number of doors and the color of a car to the user.
 */
action Display(Car vehicle)
    // Insert the code here
end
</code></pre></p>
<p>
Notice that you are passing a <code>Car</code> object into actions.  This is one of ways to pass an object around in the class.  You will learn how to apply the class scoping concept to an object in the next lab.
</p>
<p>
Fill these actions with the appropriate code.  Call both <code>Change</code> and <code>Display</code> actions with the same values.  The program should still function the same with same results in the output as before.  Show the work to the instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>