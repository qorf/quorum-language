<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab 5_2';
</script>

<h1>Lab 5.2: An Introduction to Class Variables and Access Modifiers</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to understand the following concepts:
</p>
<ul>
<li>Apply class scoping to class variables and access class variables outside the class</li>
<li>Create and use accessor actions and access modifiers</li>
<li>Set up a constructor to initialize class variables</li>
<li>More practice with classes</li>
</ul>
<h2>Overview</h2>
<p>
In this lab, we will learn how to use class variables, accessor actions, access modifiers, and a constructor.  We will create a basic Car class to learn and observe how they work with a Main class.  We will start with defining and experimenting class variables, then we will work our way up to using class variables with accessor actions and access modifiers.  Then, we will make action calls to a Car object in a Main class and understand how a constructor works.  At the end of the lab, we will fine-tune our program by organizing the code and practicing more with actions.  The goal of this lab is to help us learn and master object-oriented programming, as it is a very important part of programming.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <strong>Lab5_2</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action as shown below:
</p>
<p><pre class="code"><code>
class Main
    action Main
    end
end
</code></pre></p>
<p>
We will create an additional class in a project.  In a New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name a new file <b>Car</b> in a New Quorum Class dialog.  The <tt>Car</tt> class will appear with the following code:
</p>
<p><pre class="code"><code>
class Car
end
</code></pre></p>
<p>
We will fill <tt>Car</tt> and <tt>Main</tt> classes with the code in the next four tasks.
</p>
<h2>Task 2: Creating Class Variables</h2>
<p>
We are going to create class variables and experiment with how they work between two classes.  There are two ways to control access to class variables: we can use access modifiers and accessor actions (getters and setters) or by making the class variables public.
In programming, we typically want to write code that is secure and encapsulated.  Encapsulation is one of the four fundamental object oriented programming concepts. It is the technique of making class variables private and providing access to them with public actions.
If a variable is private, then it can't be accessed by anything outside of the class is was created in, except through the use of getters and setters.  This is also known as data hiding. Encapsualtion gives maintainability, flexibility, and extensibility to our code.
</p>
<p>
In a <tt>Car</tt> class, add two class variables:
</p>
<ul><li><strong>numberOfDoors</strong> is an <tt>integer</tt> that keeps track of the number of doors on a car.  Define it as a <tt>public</tt> variable and set it to four.
</li><li><strong>color</strong> is a <tt>text</tt> that holds the color of a car.  Define it as a <tt>private</tt> variable and set it to Blue.
</li></ul><p>
The code should look similar to the following:
</p><pre class="code"><code>
class Car
    public integer numberOfDoors = 4
    private text color = &quot;Blue&quot;
end
</code></pre></p>
<p>
Then, in a <tt>Main</tt> class, we will add the code that will access class variables from a <tt>Car</tt> class.  We will need to create a <tt>Car</tt> object in order to use its variables and actions.  We only have two variables for now, so do the following to access them:
</p>
<ul><li>Instantiate a <tt>Car</tt> object.
</li><li>Call the <tt>numberOfDoors</tt> variable and set it to four.
</li><li>Call the <tt>color</tt> variable and set it to Blue.
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
<ol><li>Which variable was accessible to a <tt>Main</tt> class?
</li><li>Which variable was not accessible to a <tt>Main</tt> class?
</li><li>What did the compiler error say about an inaccessible variable?  Why is it happening?
</li></ol>
<h2>Task 3: Creating and Using Access Modifiers and Accessor Actions</h2>
<p>
Now that we know how to use class variables and control access to them, we are going to apply the class scoping concept to class variables.  Class scoping allows us to use class variables anywhere in a class to pass values around and make any changes to the stored values in the variables.  We are going to learn how to take advantage of class scoping by creating and calling accesor actions and access modifiers (getters and setters).
</p>
<p>
Before we get started with accessor actions and access modifiers, we will need to make changes to class variables in a <tt>Car</tt> class.  Since we are going to use getters and setters, we don’t want variables to be defined <tt>public</tt>, so we will make them <tt>private</tt>.  The <tt>private</tt> keyword is optional in Quorum, because Quorum automatically defines class variables as private variables.  We will let Quorum do the work for us.  Make changes to a <tt>Car</tt> class to match the following code sample:
</p>
<p><pre class="code"><code>
class Car
    integer numberOfDoors = 4
    text color = “Blue”
end
</code></pre></p>
<p>
Let’s use private class variables to set up accessor actions and access modifiers to allow a <tt>Main</tt> class to access them.  We will start with the <tt>numberOfDoors</tt> variable.  First, create an accessor action and name it <strong>GetNumberOfDoors</strong>.  It has no parameter and returns an <tt>integer</tt> value for a number of doors on a car.  Next, create an access modifier to modify a number of doors on a car.  Name it <strong>SetNumberOfDoors</strong>.  It has a parameter and no return value.  The parameter is an <tt>integer</tt> value used for setting a new value for a number of doors.  The code should look like the following:
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
The accessor actions and access modifiers always start with “Get” and “Set” in their names, respectively.  This helps us recognize a type of an action and the purpose of its name.
</p>
<p>
It’s time to make calls to a <tt>Car</tt> object!  Switch to a <tt>Main</tt> class.  Use a <tt>Car</tt> object to call both <tt>GetNumberOfDoors</tt> and <tt>SetNumberOfDoors</tt> actions.  The <tt>Main</tt> class should look similar to the following:
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
This basically gives us an idea of how to make action calls to the getters and setters.  Modify the code so that it matches the output shown below:
</p>
<p><pre class="code"><code>
The car has 4 doors.
The car has 2 doors.
</code></pre><p>
The first call should get the current value of a number of doors.  The second call should change a number of doors from four to two.  The final call displays a new change that has been made to the <tt>numberOfDoors</tt> variable.
</p>
<p>
Add a getter and setter for the <tt>color</tt> variable in a <tt>Car</tt> class.  Do the same that we did for the <tt>numberOfDoors</tt> variable in a <tt>Main</tt> class.  The color of a car should be set to “Black.”  The output should match the following:
</p>
<p><pre class="code"><code>
The car has 4 doors.
The color of a car is Blue.
The car has 2 doors.
The color of a car is Black.
</code></pre></p>
<h2>Task 4: Defining a Constructor</h2>
<p>
We are going to define a constructor to initialize class variables.  When an object is instantiated, a constructor is immediately called to execute the code within a constructor.  The constructor can do a variety of things, but we will focus on initializing class variables for this lab.  It is always a good practice to initialize variables to the default values in a constructor.  For example, if a user does not choose a number of doors and the color of a car, the default values will be used.
</p>
<p>
In a <tt>Car</tt> class, create a constructor between class variables and the first accessor action.  In a constructor, initialize <tt>numberOfDoors</tt> to four and <tt>color</tt> to Silver.  Since we are initializing variables in a constructor, we no longer need to initialize class variables outside the constructor and actions.  The code should match the following part of a <tt>Car</tt> class:
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
We don’t have to make any changes to a <tt>Main</tt> class.  Run the program and it should still function as before.
</p>
<h2>Task 5: Fine-tuning Car and Main Classes</h2>
<p>
In this final task, we are going to fine-tune a program to add user input checking to the access modifiers in a <tt>Car</tt> class and enhance the code in a <tt>Main</tt> class.
</p>
<p>
In a <tt>Car</tt> class, add the user input checking code to two setters based on the following descriptions:
</p>
<ul><li>The car must have two or four doors.  If a user enters an invalid value, then a value will be set to two.
</li><li>The color of a car must be Black, Red, or Silver.  If a user enters an invalid value, then a value will be set to Red.
</li></ul><p>
In a <tt>Main</tt> class, make action calls to set a number of doors to three and the color of a car to Purple.  Print or speak the output to see what happens to the invalid values.  The output should look like the following:
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
We are going to make one more change to a <tt>Main</tt> class.  We are making a series of action calls with the same code, so we are going to convert them into two separate actions for a better reusability.  
</p>
<p>
Use the code snippet below to add new actions in a <tt>Main</tt> class:
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
Notice that we are passing a <tt>Car</tt> object into actions.  This is one of ways to pass an object around in the class.  We will learn how to apply the class scoping concept to an object in the next lab.
</p>
<p>
Fill these actions with the appropriate code.  Call both <tt>Change</tt> and <tt>Display</tt> actions with the same values.  The program should still function the same with same results in the output as before.  Show the work to the instructor.
</p>

<?php include("../../include/footer.php"); ?>