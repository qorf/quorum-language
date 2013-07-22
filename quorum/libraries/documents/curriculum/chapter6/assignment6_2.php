<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Assignment 6_2';
</script>

<h1>Assignment 6.2: Random Super Hero Generator Using Blueprints</h1>
<h2>Objectives</h2>
<p>
    The goal of this assignment is to learn the following:
</p>
<ul>
    <li>
        How to create and use blueprint actions
    </li>
    <li>
        How to use derived classes with blueprint actions
    </li>
    <li>
        How to use the <code>Libraries.Compute.Random</code> class
    </li>
</ul>
<h2>Overview</h2>
<p>
    In <a href="lab6_2.php">lab 6.2</a> you used blueprint actions and inheritance to create specific super heroes. In this assignment, you will create a class that has several blueprint actions, then inheriting that class and implementing those blueprints in a way so that we create several random superheroes, each with different random attributes from our blueprint class. In order to make the heroes with random attributes, you will use a random number generator, obtained from the Libraries.Compute.Random class.
</p>
<h2>Requirements</h2>
<p>
    You will need three classes: <code>SuperHeroParts</code>, <code>SuperHeroGenerator</code>, and <code>Main</code>. Class <code>SuperHeroParts</code> will be the base class in which all of the blueprint actions are created. Recall that in Lab 6_2 two categories were defined: super powers and equipment. For this program, create 5 blueprint actions for each of the above categories. As a developer, opportunities will arise where you are able to add your own flare and zest to a program. For this task, feel free to flex your creative muscle and create unique and interesting actions to fulfill the requirements above. Each of these blueprint actions will take one argument, of type text. For example:
</p>
<pre class="code">
    //super powers
    blueprint action Fly(text name)
    blueprint action Strength(text name)
    blueprint action LaserVision(text name)
</pre>
<p>
    You will also need 1 blueprint action that returns the name of a superhero.
</p>
<h2>Class <code>SuperHeroGenerator</code></h2>
<p>
    In this class, you implement all of the blueprint actions inherited from class SuperHeroParts. Recall that the blueprint actions all take 1 argument or type text. This argument will be the name of a character. Your blueprint action that returns the name of a superhero should ask the user to input a name, and then return that text. For example:
</p>
<pre class="'code">
    action Character() returns text
    text name = input("Enter the name of your superhero")
    return name
    end
    action Fly(text name)
    say name + "can fly"
    end
    action Strength(text name)
    say name + "has super human strength!"
    end
    action LaserVision(text name)
    say name + "can use laser vision"
    end
</pre>
<p>
    This class will contain four more actions:
</p>
<ul>
    <li>
        <b>
            action GiveSuperPower(integer value, text name)
        </b>
    </li>
</ul>
<p>
    This action should call one of the blueprint actions you implemented earlier if value is in a certain range. The input value will be a random integer. This way, super powers will be selected randomly, so there will be a high chance of different powers being selected each time the program is run. For example:
</p>
<pre class="code">
    if value > 0 and value < 5
    Fly(name)
    if value > 5 and value < 10
    Strength(name)
</pre>
<ul>
    <li>
        <b>
            action GiveCostume(integer value, text name)
        </b>
    </li>
</ul>
<p>
    This action will do the same as GiveSuperPower, but will call one of the actions from your costume list instead.
</p>
<ul>
    <li>
        <b>
            action GiveWeapon(integer value, text name)
        </b>
    </li>
</ul>
<p>
    This action does the same as GiveSuperPower, but will instead call one of the actions from your weapons list.
</p>
<ul>
    <li>
        <b>
            action CreateHero(integer value, text name)
        </b>
    </li>
</ul>
<p>
    This action calls the above three actions, and uses the arguments passed into it as the arguments for each of the three actions it calls.
</p>
<h2>Class <code>Main</code></h2>
<p>
    Class <code>Main</code> should have one action, <code>Main</code>, which instantiates an object of class <code>Random</code> and <code>SuperHeroGenerator</code>. Use the Libraries.Compute.Random in order to create an object of class <code>Random</code>. Use the <code>Random</code> class action <code>RandomIntegerBetween(integer min, integer max)</code>, which returns a random integer in the range of (min, max). Set the min to 0, and the max to 50. <code>Main</code> should also call the action <code>CreateHero(integer value, text name)</code> from the derived class <code>SuperHeroGenerator</code>.
</p>
<h2>Sample Output</h2>
<p>
    When run, the program should ask the user for the name of a superhero, and then should assign the superhero three attributes: one from powers, one from costume, and one from weapons. It will then tell the user what attributes their heroes have. For example:
</p>
<pre class="code">
    Enter the name of your Super Hero:
    Captain Brandon
    Captain Brandon can fly. Captain Brandon wears a mask. Captain Brandon Uses a Hammer.
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>