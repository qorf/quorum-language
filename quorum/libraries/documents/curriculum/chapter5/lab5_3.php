<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 5.3: An Introduction to Arrays | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Lab 5.3</h1>
                <p>An Introduction to Arrays</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<h1>Lab 5.3: An Introduction to Arrays</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following:
</p>
<ul>
<li>How to understand the basic concepts of array container (data structure)</li>
<li>How to create and modify arrays</li>
<li>How to apply the generic concept to the array</li>
<li>More practice with class and object concepts</li>
</ul>
<h2>Overview</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <strong>Lab5_3A</strong>.  You will focus on practicing arrays in a <code>Main</code> class.  You will write two different arrays: the first array will give you an idea of how arrays work, and the second array will show you how to add and manipulate data in an array.
</p>
<p>
Before you get started, let’s review the definition of an array.  An array is an indexed data structure or container.  The rules of using an array are the following:
</p>
<ul><li>It can hold any number of items.
</li><li>It must use the same type (primitive or object) for items in the array.
</li><li>It always starts with 0 for an index.  Items are stored in sequence and associated with an index or location in the array.
</li></ul><p>
Here is an example of why an array should be used:
</p>
<pre class="code">integer i0 = 0
integer i1 = 1
integer i2 = 2
integer i3 = 3
integer i4 = 4
</pre><p>
Although this example works, it has significant disadvantages such as keeping track of several variables.  The next example shows how an array replaces the code in the previous example:
</p>
<pre class="code">use Libraries.Containers.Array
class Main
    action Main
        Array&lt;integer&gt; arrayOfIntegers
        
        integer counter = 0
        repeat 5 times
            arrayOfIntegers:Add(counter)
            counter = counter + 1
        end
    end
end
</pre><p>
The example uses a primitive type for an <code>integer</code>, which uses a lower case letter for the first letter.  You also can use an object for an <code>integer</code> that starts with an upper case letter for the first letter.  You are using primitive types for this lab.
</p>
<p>
Now, it’s time to start working with arrays!  In a <code>Main</code> class, use the array example above to create an array to store 10 integers.  Then, display all integers from an array.  In order to get all integers, you will need to get the size of an array and use it to write a repeat loop. The repeat loop should output <code>integer</code> values from 0 to 9.  
</p>
<p>
Create a new array that stores <code>text</code> values.  Give the array a name, <strong>weekdays</strong>.  Add four values to the array: Monday, Tuesday, Thursday, and Friday.  Output the result as shown below:
</p>
<pre class="code">Monday
Tuesday
Thursday
Friday
</pre><p>
You want to modify this array to add three more weekdays.  Use a code completion to find three appropriate actions to accomplish the following:
</p>
<ul><li>Add Sunday to the front of an array.
</li><li>Add Saturday to the end of an array.
</li><li>Add Wednesday to a specific location in the array.
</li></ul><p>
The output of an updated list of weekdays should match the following:
</p>
<p><pre class="code"><code>
Sunday
Monday
Tuesday
Wednesday
Thursday
Friday
Saturday
</code></pre></p>
<h2>Task 2: Using Generics</h2>
<p>
This task introduces the concepts of generics and how to use them.  A generic represents a type of item that a container will contain.  An <code>Array</code> object needs to specify what type of item it will store with &lt; and &gt;.  The type is placed between the angle brackets.  The type can also be a custom object.  For this lab, you will be using a generic for an <code>Array</code>, but a generic also works with any container or data structure.  The example of using a custom object in a generic array is shown below:
</p>
<pre class="code">Array&lt;Player&gt; arrayOfPlayers
</pre><p>
You will create a new program that keeps track of information for each basketball player and coach.  Create a new “Quorum Application” project, and name it <strong>Lab5_3B</strong>.  Before you could use a generic for an array, you are going to create a new class first.  Create a new “Quorum Class” file, and name it <strong>Player</strong>.
</p>
<p>
In the <code>Player</code> class, create the following class variables:
</p>
<ul><li><strong>name:</strong> a <code>text</code> that stores the player’s name.
</li><li><strong>uniformNumber:</strong> an <code>integer</code> that stores the player’s number on their uniform.
</li><li><strong>points:</strong> an <code>integer</code> that stores a number of points that a player earns in the game.
</li></ul><p>
Create a constructor and initialize all class variables.  Set <code>name</code> to Unknown and <code>uniformNumber</code> and <code>points</code> to 0.  Then, create accessor actions to return an appropriate value for all class variables.
</p>
<p>
You are creating access modifiers a little differently this time.  Let’s take a look at an example of the access modifier:
</p>
<pre class="code">action SetName(text name)
    me:name = name
end
</pre><p>
It uses the <code>me</code> keyword to indicate that the current instance is used to access a local variable in the current instance of a class.  Create access modifiers for three class variables that follow this technique.
</p>
<p>
You are done with the <code>Player</code> class, so let's move to the <code>Main</code> class.  Use the code snippet below to help you get started with writing the code in the <code>Main</code> class:
</p>
<pre class="code">use Libraries.Containers.Array
class Main
    Array&lt;Player&gt; arrayOfPlayers = undefined
    action Main
        Array&lt;Player&gt; newArray
        arrayOfPlayers = newArray
        
        // Insert the code here
    end
    
    /* Action SetPlayerInfo has three parameters and no return type. The first
     * parameter is the player's name. The second parameter is the player's
     * uniform number. The third parameter is the points a player earns in the
     * game.
     * 
     * This action sets the player information and adds a Player object to
     * a generic array.
     */
    action SetPlayerInfo(text name, integer num, integer points)
        // Insert the code here
    end
    
    /* Action DisplayPlayerInfo has one parameter and no return type.
     * The parameter is a location of an idea in the array.
     * 
     * This action gets a Player object from an array and displays the player
     * information.
     */
    action DisplayPlayerInfo(integer index)
        // Insert the code here
    end
end
</pre><p>
The <code>arrayOfPlayers</code> is declared as a class variable because you need to use it in both <code>SetPlayerInfo</code> and <code>DisplayPlayerInfo</code> actions.  It is undefined because you want to create an array inside the <code>Main</code> action.
</p>
<p>
In a <code>SetPlayerInfo</code> action, in order to store individual <code>Player</code> object in an array, do the following:
</p>
<ul><li>Instantiate a new <code>Player</code> object.
</li><li>Set all parameters for the player information.
</li><li>Add a <code>Player</code> object to an array.
</li></ul><p>
In the <code>Main</code> action, call a <code>SetPlayerInfo</code> to set the information for three players with the data provided in the table below:
</p>
<table class="table">
<tr><td><strong>Player Name</strong></td><td><strong>Uniform Number</strong></td><td><strong>Points</strong>
</td></tr><tr><td>James</td><td>42</td><td>38
</td></tr><tr><td>Dwyane</td><td>28</td><td>31
</td></tr><tr><td>Chris</td><td>78</td><td>18
</td></tr></table>
<p>
In the <code>DisplayPlayerInfo</code> action, do the following:
</p>
<ul><li>Instantiate a <code>Player</code> object and get a location of an index from an array.
</li><li>Display the player information as shown in the output below.
</li></ul><p>
Back to the <code>Main</code> action, add the code that loops through an array to display the player information as shown in the output below:
</p>
<pre class="code">James #42 scored 38 points in the game.
Dwyane #28 scored 31 points in the game.
Chris #78 scored 18 points in the game.
</pre>
<h2>Task 3: More Practice with Object-Oriented Programming</h2>
<p>
You are almost done! You are going to practice a little more with object-oriented programming in this task.  You will create a new class and use it as a class variable in the <code>Main</code> class.  It will be explained shortly after you set up a new class.
</p>
<p>
Create a new “Quorum Class” file and name it <strong>Coach</strong>.  Use the code snippet below to help you get started:
</p>
<pre class="code">use Libraries.Containers.Array
class Coach 
    Array&lt;text&gt; arrayOfCoaches = undefined
    
    on create
        Array&lt;text&gt; newCoaches
        arrayOfCoaches = newCoaches
    end
    
    // Returns a list of coach names from an array.
    action GetCoachNames returns Array&lt;text&gt;
        // Insert the code here
    end
    
    // Adds a name of the coach to an array.
    action AddCoachName(text name)
        // Insert the code here
    end
end
</pre><p>
You are using <code>arrayOfCoaches</code> as a class variable so that you could use it in any actions in this class.  It is instantiated in a constructor so that it will be ready when you need it.  Do the following to complete the <code>Coach</code> class:
</p>
<ul><li>The <code>GetCoachNames</code> action returns an array of coaches.
</li><li>The <code>AddCoachName</code> action adds a name of the coach to an array.
</li></ul><p>
Move to the  <code>Main</code> class.  Declare a <code>Coach</code> object as a class variable but do not define it.  The sample code is shown below:
</p>
<pre class="code">use Libraries.Containers.Array
class Main
    Coach coach = undefined
    Array&lt;Player&gt; arrayOfPlayers = undefined
    action Main
</pre><p>
Having an object declared as a class variable allows you to use it and call actions from an object anywhere in this class.  In the <code>Main</code> action, instantiate a <code>Coach</code> object and assign that value to the <code>coach</code> class variable.  If you do not do that, then an error will occur because an object has not been created.  Use the code snippet below to create two actions.  Read the comments for a hint to write the code within each action.
</p>
<pre class="code">/* Action AddCoach has one parameter and no return type.
 * The parameter is a name of the coach.
 * 
 * It adds a name of the coach to an array.
 */
action AddCoach(text name)
end
    
/* Action DisplayCoachInfo has no parameter and return type.
 * 
 * It gets a list of coach names from an array and displays all names.
 */
action DisplayCoachInfo
end
</pre><p>
Make action calls in the <code>Main</code> class and the output should match the following:
</p>
<pre class="code">James #42 scored 38 points in the game.
Dwyane #28 scored 31 points in the game.
Chris #78 scored 18 points in the game.
Andy is the coach.
Jeff is the coach.
</pre><p>
There is one more thing to learn about object-oriented programming.  An action that uses an internal logic needs to be used within a class it is located in.  It needs to be hidden from other classes, so it is defined as a private action.  This technique is known as encapsulation.  
</p>
<pre class="code">private action DisplayCoachInfo
end
</pre><p>
Make changes to the <code>Main</code> class so that all actions, not including the <code>Main</code> action, are hidden from <code>Player</code> and <code>Coach</code> classes.  Run the program and it will function the same as before.  Show both Lab5_3A and Lab5_3B programs to the instructor.
</p>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 