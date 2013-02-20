<!--Developed and written by Brandon Spencer-->
<!--9/22/2012-->
<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Lab6_2';
</script>

<h1>Lab 6.2: Blueprints</h1>
<h2>Objectives</h2>
<p>
The goal of this lab is to learn the following concepts:
</p>
<ul><li>How to create and use blueprint actions</li>
<li>How to create and use a class hierarchical system</li>
<li>Apply blueprint actions across multiple classes</li>
</ul>
<h2>Overview</h2>
<p>
  In this lab, you will learn how to use blueprints in classes. Blueprints are descriptions of what kind of functionality a class will provide. The idea behind blueprints is to specify the structure of a class without providing an implementation. Using inheritance, you can then use the blueprints to make similar classes use the same actions with the same names to do similar things. In this lab, you are going to create a <tt>SuperHero</tt> class to create superhero characters. Then, you will use a blueprint action to build each character's inventory and powers. Since the inventory and powers of superheros are different from other superheros, a blueprint action makes good sense to use.
</p>
<h2>Task 1: Getting Started</h2>
<p>
Start Sodbeans.  Create a new “Quorum Application” project, and name it <strong>Lab6_2</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
Create six additional classes in this project.  Name  the classes <tt>SuperHero</tt>, <tt>Equipment</tt>, <tt>SuperPower</tt>, <tt>Spiderman</tt>, <tt>Superman</tt>, and <tt>CaptainAmerica</tt>.
</p>
<h2>Task 2: Building Characters</h2>
<p>
  You are going implement two helper classes that will help set and get the names of equipment and superpowers for the heroes. Both classes should have the following:
</p>
<li>
  A text type variable to hold the name of the equipment or power
</li>
<li>
  A setter and a getter to set the name of the equipment or power and to get the name back.
</li>
<p>
  In class <tt>SuperHero</tt>, you can use the helper classes above to create arrays that will hold the names of equipment and powers for our heroes. The arrays can be created like so:
</p>
<p>
  <pre class="code">
    <code>
      Array<Equipment> inventory
      Array<SuperPower> powers
    </code>
  </pre>
</p>
<p>
  The arrays above are of type <tt>Equipment</tt> and <tt>SuperPower</tt>. That is, they will be made of they types that are present in those classes, instead of just type integer or type number. In this case, the arrays will be holding text values. Remember that you must use Libraries.Containers.Array to access the array class. Next, create the following actions:
</p>
<p>
  <li>
    <b>
      action AddSuperPower(SuperPower power)
    </b>
  </li>
</p>
<p>
  This action takes as an argument an object of class <tt>SuperPower</tt>. It should add to the powers array the object passed in. Recall that adding to an array can be done likes this:
</p>
<p>
  <pre class="code">
    <code>
      myArray:Add(21) //adds the integer 21 into the array called myArray
    </code>
  </pre>
</p>
<p>
  <li>
    <b>
      action SaySuperPowers
    </b>
  </li>
</p>
<p>
  This action should iterate through the powers array until it reaches the end and say to the user each item held in it. Recall that you can get the size of an array like this:
</p>
<p>
  <pre class="code">
    <code>
      myArray:Add(21)
      integer size = myArray:GetSize() //size would be assigned the integer value 1
    </code>
  </pre>
</p>
<p>
  <li>
    <b>
      action AddEquipment(Equipment item)
    </b>
  </li>
</p>
<p>
  This action should do the same as action <tt>AddSuperPower</tt>, but instead should add items to the inventory array.
</p>
<p>
  <li>
    <b>
      action SayInventory
    </b>
  </li>
</p>
<p>
  This action should do the same as action <tt>SaySuperPowers</tt>, but instead should iterate through the inventory array and say to the user each item held in it.
</p>
<p>
  <li>
    <b>
      blueprint action BuildCharacter
    </b>
  </li>
</p>
<p>
  This is a blueprint action that each class(<tt>Spiderman</tt>, <tt>Superman</tt>, and <tt>CaptainAmerica</tt>) will inherit from class <tt>SuperHero</tt> and implement in a different way. By using blueprints and inheritance in this program, you are able to create a general action that can be used across multiple classes in different ways. This saves you the time of creating this specific action for every class. Next, in <tt>Main</tt>, try to instantiate an object of type <tt>SuperHero</tt>. Notice that this resulted in a compiler error. This is because Sodbeans doesn't know what the blueprint actions do.
</p>
<h2>
  Task 3: Inheriting blueprint actions from another class
</h2>
<p>
  In this task you will be implementing the inherited blueprint action <tt>BuildCharacter</tt> for classes <tt>Superman</tt>, <tt>Spiderman</tt>, and <tt>CaptainAmerica</tt>. The <tt>BuildCharacter</tt> action should do the same thing for each class: add equipment and powers to the hero using the setters from classes <tt>Equipment</tt> and <tt>SuperPower</tt>, and add the values to the proper array using the <tt>AddEquipment</tt> action from class <tt>SuperHero</tt>. For each superhero (Superman, Spiderman, and Captain America), add powers and pieces of equipment that describe that particular hero. i.e:
</p>
<p>
  <pre class="code">
    <code>
      //Superman
      action BuildCharacter
      Equipment cape
      cape:SetEquipment("has a cape")
      parent:SuperHero:AddEquipment(cape)

      SuperPower vision
      vision:SetPower("can use laser vision")
      parent:SuperHero:AddSuperPower(vision)
      end
    </code>
  </pre>
</p>
<p>
  In the above example, I described that Superman has a cape and can use laser vision. For each superhero, create a combination of at least 5 powers and/or equipment that that superhero has.
</p>
<h2>
  Task 4: Using inherited actions
</h2>
<p>
  After you have action <tt>BuildCharacter</tt> implemented for each superhero, go into <tt>Main</tt> and instantiate an object of each superhero and call <tt>BuildCharacter</tt>. Then, using the <tt>SaySuperPowers</tt> and <tt>SayInventory</tt> actions, list the powers and inventory of each superhero for the user. Notice the <tt>BuildCharacter</tt> action that is called is unique to each superhero.
</p>
<h2>
  Sample Output
</h2>
<p>
  When run, the user should be told which superhero is being described, and then told that superhero's powers and equipment. Here is a sample output for Superman:
</p>
<p>
  <pre class="code">
    <code>
      Superman
      has a cape
      has a blue costume
      can use laser vision
      can use super strength
      can fly
    </code>
  </pre>
</p>
<p>
  When finished, debug and fix any errors, then show your instructor you code.
</p>

<?php include("../../include/footer.php"); ?>