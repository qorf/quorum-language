<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 6_2';
</script>

<h1>Short Assignment: Random Super Hero generator using Blueprints</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul><li>Creating and using blueprint actions
</li><li>Using derived classes with blueprint actions
</li><li>Using the <tt>Libraries.Compute.Random</tt> class
</li></ul><h2>Overview</h2>
<p>
In lab 6_2 we used blueprint actions and inheritance to create specific super heroes.  In this assignment, we will be creating a class that has several blueprint actions, then inheriting that class and implementing those blueprints in a way so that we create several random superheroes, each with different random attributes from our blueprint class.  In order to make the heroes with random attributes, we will be using a random number generator, obtained from the Libraries.Compute.Random class.
</p>
<h2>Requirements</h2>
<p>
You will need three classes: <tt>SuperHeroParts</tt>, <tt>SuperHeroGenerator</tt>, and <tt>Main</tt>. Class <tt>SuperHeroParts</tt> will be the base class in which all of our blueprint actions are created.  Recall from Lab 6_2 that we had three categories: super powers, costume, and weapons.  For this program, create no less than 9 blueprint actions for each of the above categories.  Each of these blueprint actions will take one argument, of type text. For example:
</p>
<pre class="code"> //super powers
    blueprint action Fly(text name)
    blueprint action Strength(text name)
    blueprint action LaserVision(text name)
</pre><p>
You will also need 1 blueprint action that returns the name of a superhero.
</p>
<h2>SuperHeroGenerator</h2>
<p>
In this class, you implement all of the blueprint actions inherited from class <tt>SuperHeroParts</tt>.  Recall that the blueprint actions all take 1 argument or type text.  This argument will be the name of a character.  Your blueprint action that returns the name of a superhero should ask the user to input a name, and then return that text.  For example:
</p>
<pre class="code">    action Fly(text name)
        say name + "can fly"
    end
    action Strength(text name)
        say name + "has super human strength!"
    end
    action LaserVision(text name)
        say name + "can use laser vision"
    end
</pre><p>
This class will contain four more actions:
</p>
<ul><li>action GiveSuperPower(integer value, text name)
This action should call one of the blueprint actions you implemented earlier if value is in a certain range. For example:
<pre class="code">if value &gt; 0 and value &lt; 5
 Fly(name)
</pre></li><li><tt>action GiveCostume(integer value, text name)</tt>
This action will do the same as <tt>GiveSuperPower</tt>, but will call one of the actions from your costume list instead.
</li><li><tt>action GiveWeapon(integer value, text name)</tt>
This action does the same as <tt>GiveSuperPower</tt>, but will instead call one of the actions from your weapons list.
</li><li><tt>action CreateHero(integer value, text name)</tt>
This action calls the above three actions, and uses the arguments passed into it as the arguments for each of the three actions it calls.
</li></ul><h2>Main</h2>
<p>
Class <tt>Main</tt> will have one action, Main, which will instantiate an object of class <tt>Random</tt> and <tt>SuperHeroGenerator</tt>.  You will need to use the <tt>Libraries.Compute.Random</tt> in order to create an object of Random.  Use the Random class action <tt>RandomIntegerBetween(integer min, integer max)</tt>, which will return a random integer in the range of (min, max). Set the min to 0, and the max to 50.  Main should also call the action <tt>CreateHero(integer value, text name)</tt> from the derived class <tt>SuperHeroGenerator</tt>.
</p>
<h2>Sample Output</h2>
<p>
When ran, the program should ask the user for the name of their superhero, and then should assign the superhero three attributes, one from powers, one from costume, and one from weapons.  It will then tell the user what attributes their heroes have.  For example:
</p>
<p>
<pre class="code">
[Output] Enter the name of your Super Hero:
[Input] Captain Brandon
[Output] Captain Brandon can fly. Captain Brandon wears a mask. Captain Brandon Uses a Hammer.
</pre>
</p>

<?php include("../../include/footer.php"); ?>