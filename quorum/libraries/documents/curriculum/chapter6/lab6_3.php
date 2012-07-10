<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab6_3';
</script>

          <h1>Lab 6.3 More Inheritance</h1>
<h2">Objectives</h2>
<p>
The goal of this lab is to understand the following concepts:
</p>
<ul><li>Use inheritance across multiple classes
</li><li>Create and use a class hierarchical system
</li><li>Use polymorphism 
</li></ul><h2 id="Overview">Overview</h2>
<p>
In this lab, we will create a program to ask the user about their pets. It will collect some information from then, and then tell them about their pet based on the info provided.  Because many pets have some of the same attributes (they eat, play, sleep, etc.), we can use inheritance to create multiple subclasses of different pets.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <strong>Lab6_2</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
We will create four additional classes in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new file <strong>Pet</strong> in the New Quorum Class dialog.  Repeat the above steps to create Classes named <strong>Cat</strong>, <strong>Dog</strong>, and <strong>Lizard</strong>.
</p>
<h2>Task 2: Creating Your Parent Class</h2>
<p>
In your <strong>Pet</strong> class, create variables to store the following data:
</p>
<ul><li>a number type to store the weight
</li><li>a text type to store food
</li><li>a text type to store gender
</li><li>an integer type to store number of hours of sleep
</li><li>an integer type to store number of hours of playtime
</li><li>an integer type to store number of toys
</li></ul><p>
Remember to follow good naming convention practices when creating your variables.
</p>
<p>
Next, create access modifiers and accessor actions for weight, sleep, playtime, and toys.  Also, create an action called PetNoise that takes no arguments and returns nothing.  When called, the action should say "generic pet noise."  Later, we will use polymorphism to change this default message into something else for each of our pet classes.  Polymorphism is the idea that some variables, operations, or objects behave differently in different contexts.  For example, in Sodbeans, the + (plus) operator can be used in different ways:
</p>
<ul><li>4 + 5  //integer addition
</li><li>5.1 + 5.0   //floating point addition (number addition)
</li><li>string1 + "hello!"   //string concatenation
</li></ul><p>
In the above example, the operator + is being used in several different ways, depending on the context in which is called.
</p>
<h2>Task 3: Inheriting Your Pet Class</h2>
<p>
Now that our parent class has the needed actions, we can write code for our Cat, Dog, and Lizard classes.  Using inheritance, write actions for each of the before mentioned classes that will prompt the user to input a value that will be used for each of the setter actions you made in the Pet class (weight in pounds, sleep in hours, playtime in hours, and number of toys).  Also, implement the PetNoise action in such a way that it overrides the default message with a noise that each of the respective animals would make (woof, meow, etc.).  Recall that we had two variables of type text, one for food, and one for gender in our parent class.  For each of the child classes, create actions that will return the text entered by the user for these two variables.  An example of such an action might look like this:
</p>
<pre class="code">class Cat is a Pet

    action AskWeight()
        number pounds = cast(number, input("How heavy is your cat?")) //casting to type number is done in one go with an input statement.
        parent:Pet:SetWeight(pounds) //uses the keyword parent to access actions in the Pet class.
    end
</pre><p>
First, notice that the variable pounds has to be cast to type number. In the above example, this is being done on the same line as the input statement, but it doesn't have to be.  Second, notice that the action SetWeight, which was declared in class Pet, is being accessed by the keyword parent.  The parent keyword allows us to reference a class directly.
</p>
<h2>Task 4: Using inherited actions</h2>
<p>
In Main, instantiate objects of your Cat, Dog, and Lizard classes.  Next, ask the user what kind of pet they have, 1 for cat, 2 for dog, or 3 for lizard.  You will now need to create control structures that perform like the following:
</p>
<p>
If cat
</p>
<ul><li>get values for weight, sleep, play, and toys
</li><li>get food type
</li><li>get gender
</li><li>tell user in complete sentences about their pet
</li></ul><p>
if dog
</p>
<blockquote>
<p>
do the same as above
</p>
</blockquote>
<p>
if lizard
</p>
<blockquote>
<p>
do the same as above
</p>
</blockquote>
<p>
If the user inputs anything but 1, 2, or 3, prompt them again to enter 1 for cat, 2 for dog, or 3 for lizard.
Also, add control structures so that if the pet is male, it is referred to as "he", and if it is female, it is referred to as "she."  Regardless of which derived class is called, make sure to call the derived action PetNoise.  If polymorphism is used correctly, then the user should hear what noise their pet makes, instead of the default message.
</p>
<p>
When you are done, debug and fix any errors, then show your code to your instructor.
</p>

<?php include("../../include/footer.php"); ?>