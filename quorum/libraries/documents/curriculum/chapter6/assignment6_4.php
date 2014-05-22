<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 6.4: Battle Simulator | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 6.4</h1>
		<p>Battle Simulator</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>Challenge Assignment 6.4: Battle Simulator</h1>-->
<h2>Objectives</h2>
<p>
    The goal of this assignment is to learn the following:
</p>
<ul>
    <li>
        How to use inheritance
    </li>
    <li>
        How to create class actions and use derived actions
    </li>
    <li>
        How to use the audio library to record and play sounds
    </li>
</ul>
<h2>Overview</h2>
<p>
    In this assignment you will create an interactive battle simulator. Users will be given the chance to record their own sound effects using Quorum's <code>Record()</code> action. They will be given a description of three different types of champions, and will get to choose their champion and their opponent. Then, the two will fight to the death, with the user's own sound effects detailing each hit and miss in this epic battle.
</p>
<h2>Requirements</h2>
<p>
    You will need six classes: <code>Equipment</code>, <code>Sounds</code>, <code>Stats</code>, <code>Warrior</code>, <code>Wizard</code>, and <code>Ranger</code>. Class <code>Stats</code> will be your base class, and will contain actions and blueprint actions to be used in each class that inherits from <code>Stats</code>.
</p>
<h2>Class <code>Equipment</code></h2>
<p>
    In this class, you will implement a getter and a setter a class variable of type text called <code>name</code>:
</p>
<ul>
    <li>
        <b>
            action SetEquipment(text equipment)
        </b>
    </li>
</ul>
<p>
    Setter for <code>name</code>.
</p>
<ul>
    <li>
        <b>
            action GetEquipment returns text
        </b>
    </li>
</ul>
<p>
    Getter for <code>name</code>.
</p>
<h2>Class <code>Stats</code></h2>
<p>
    This class will need the following libraries:
</p>
<ul>
    <li>
        <b>
            use Libraries.Containers.Array
        </b>
    </li>
</ul>
<p>
    As you did in <a href="lab6_2.php">lab 6_2</a>, you will need to create an array of type <code>Equipment</code>. Name this array inventory. Class <code>Stats</code> will use the following actions:
</p>
<ul>
    <li>
        <b>
            action AddEquipment(Equipment item)
        </b>
    </li>
</ul>
<p>
    This action adds the input parameter to the array inventory.
</p>
<ul>
    <li>
        <b>
            action SayInventory
        </b>
    </li>
</ul>
<p>
    This action should iterate through inventory until it reaches the end and say to the user each item held in it.
</p>
<ul>
    <li>
        <b>
            blueprint action BuildCharacter
        </b>
    </li>
</ul>
<p>
    This is a blueprint that will be later implemented in classes <code>Warrior</code>, <code>Wizard</code>, and <code>Ranger</code>.
</p>
<h2>Class <code>Sounds</code></h2>
<p>
    This class will contain several actions to record your own sound effects to be used in the simulator. Implement the following actions:
</p>
<ul>
    <li>
        <b>
            action RecordHit
        </b>
    </li>
</ul>
<p>
    Records for 2 seconds a "hit" sound. To be used when your champion or your enemy successfully makes a hit.
</p>
<ul>
    <li>
        <b>
            action RecordMiss
        </b>
    </li>
</ul>
<p>
    Records for 2 seconds a "miss" sound. To be used when your champion or your enemy misses a hit.
</p>
<ul>
    <li>
        <b>
            action RecordOuch
        </b>
    </li>
</ul>
<p>
    Records for 2 seconds an "ouch" sound. To be used when you champion or your enemy gets hit.
</p>
<ul>
    <li>
        <b>
            action RecordTaunt
        </b>
    </li>
</ul>
<p>
    Records for 2 seconds a taunting sound. To be used when you champion misses the enemy, or the enemy misses your champion.
</p>
<ul>
    <li>
        <b>
            action RecordVictory
        </b>
    </li>
</ul>
<p>
    Records for 2 seconds a victory song. To be used if your champion deafeats the enemy.
</p>
<ul>
    <li>
        <b>
            action RecordDefeat
        </b>
    </li>
</ul>
<p>
    Records for 2 seconds a "defeat" sound. To be used if your enemy defeats your champion.
</p>
<h2>Class <code>Ranger</code></h2>
<p>
    Class <code>Ranger</code> will inherit from class <code>Stats</code>. It will have one action:
</p>
<ul>
    <li>
        <b>
            action BuildCharacter
        </b>
    </li>
</ul>
<p>
    Similar to <a href="lab6_2.php">lab 6_2</a>, <code>BuildCharacter</code> will describe the <code>Ranger</code> in terms of his equipment and weapons. Using the inherited actions from class <code>Stats</code>, add at least 5 items to describe the Ranger. Example:
</p>
<pre class="code">
    action BuildCharacter
    Equipment bow
    bow:SetEquipment("uses a two hand bow with steel arrows")
    parent:Stats:AddEquipment(bow)
    end
</pre>
<h2>Class <code>Warrior</code></h2>
<p>
    Class <code>Warrior</code> will inherit from class <code>Stats</code>. It will have one action:
</p>
<ul>
    <li>
        <b>
            action BuildCharacter
        </b>
    </li>
</ul>
<p>
    <code>BuildCharacter</code> will do the same in class <code>Warrior</code> as it did in class <code>Ranger</code>.
</p>
<h2>Class <code>Wizard</code></h2>
<p>
    Class <code>Wizard</code> will inherit from class <code>Stats</code>. It will have one action:
</p>
<ul>
    <li>
        <b>
            action BuildCharacter
        </b>
    </li>
</ul>
<p>
    <code>BuildCharacter</code> will do the same in class <code>Wizard</code> as it did in class <code>Warrior</code>.
</p>
<h2>Class <code>Main</code></h2>
<p>
    Class <code>Main</code> will have two actions:
</p>
<ul>
    <li>
        <b>
            action Damage(integer HP) returns integer
        </b>
    </li>
</ul>
<p>
    <code>Damage</code> will use a random number generator (named <code>value</code> to generate integers no larger than 50). It will then follow this format:
</p>
<pre class="code">
    if value is between 0 and 10, decrement HP by 10.
    if value is between 10 and 20, decrement HP by 20.
    //Continue this up until 50, then return HP.
</pre>
<ul>
    <li>
        <b>
          action Main
        </b>
    </li>
</ul>
<p>
    <code>Main</code>should call all the actions to describe the three champions. It should also call all of the actions so the user can record their own sound effects. After this, the user should be prompted to choose a champion. If the user selects the warrior, tell them they selected the warrior, and using the audio files that came with this project, play "Warrior.wav". Do the same if the user selects the Ranger or the Wizard, playing the appropriate files for each.
</p>
<p>
    Next, prompt the user to choose an opponent. Do the same for the opponent selection as you did for the champion selection: telling the user which opponent they selected, and playing the appropriate sound.
</p>
<p>
    Now you will simulate a battle between the champion and the opponent. Create a random number generator (named <code>decider</code>) that will generate integers between 0 and 3. Repeat the following until either the champion or the opponent has less than or equal to 0 hp:
</p>
<pre class="code">
    if decider = 0
    Play "Hit.wav". Decrement the opponent's hp.  Play "Ouch.wav". Tell the user
    their champion made a successful hit, and tell them the enemies current hp.

    if decider = 1
    Tell the user their champion missed the enemy. Play "Miss.wav". Play "Taunt.wav".

    if decider = 2
    Play "Hit.wav". Decrement the champion's hp. Play "Ouch.wav". Tell the user
    their champion was successfully hit by their opponent, and tell them the champions current hp.

    if decider = 3
    Tell the user the opponent missed their attack on the champion. Play "Miss.wav". Play "Taunt.wav"
</pre>
<p>
    Lastly, check to see if either the champion or the opponent has died. If the champion died, play "Defeat.wav" and tell the user the opponent was the victor. If the opponent died, play "Victory.wav" and tell the user the champion was the victor.
</p>
<h2>Sample Output</h2>
<p>
    When run, the program should describe the three champions to the user. Then, the user will select a champion and an opponent. The battle will ensue, and the user will be told who the victor was in the end. The following is an example of when the program is run after the champions are described:
</p>
<pre class="code">
    Choose a Champion: Enter 1 for the Warrior, 2 for the Ranger, or 3 for the Wizard
    1
    You have chosen the Warrior as your champion.
    *Play Warrior.wav*
    Choose an opponent: Enter 1 for the Warrior, 2 for the Ranger, or 3 for the Wizard
    2
    You have chosen the Ranger as your opponent
    *Play Ranger.wav*

    *Play Hit.wav*
    *Play Ouch.wav*
    Successfully hit the enemy! His HP is 50

    Your opponent missed their attack on you.
    *Play Miss.wav*
    *Play Taunt.wav*

    *Play Hit.wav*
    *Play Ouch.wav*
    Successfully hit the enemy! His HP is 0

    Your champion is the victor!!!
    *Play Victory.wav*
</pre>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>