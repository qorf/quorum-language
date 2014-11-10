<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 2.3: Musical Piece | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 2.3</h1>
                <p>Musical Piece</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


<!--<h1>Challenge Programming Assignment: Musical Piece</h1>-->
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>How to use the Music library to play chords</li>
<li>How to use multiple non-primitive data types in a single program</li>
</ul>
<h2>Overview</h2>
<p>
  In this assignment, you will write a program that plays music with a combination of notes and chords.  This assignment allows you to create your own songs by combining single notes and chords. Create a new project and name it <strong>Assignment2_3</strong>.
</p>
<p>
So far, you have focused on using the <code>Play</code> feature in the <code>Music</code> class to play single notes. This feature can also be used to play multiple notes at once, called <i>chords</i>. In this assignment, you will create a piece of music utilizing both single notes and chords to create a piece of music that lasts at least one minute.
</p>
<h2>Task 1</h2>
<p>
To play chords in Quorum, you must use the <code>Chord</code> type. To include this type for use in your program, use the following <strong><code>use</code></strong> statement:
</p>
<p><pre class="code"><code>
use Libraries.Sound.Chord
</code></pre></p>
<p>
To create a chord, you might use code like the following. This code creates a C Major chord.
</p>
<p><pre class="code"><code>
Chord c
c:Add(60)
c:Add(64)
c:Add(67)
</code></pre></p>
<p>
Once the chord has been created make sure the chord is played.
</p>
<h2>Task 2: Analysis</h2>
<ul><li>Compose a piece of music consisting of notes and chords.
</li><li>The piece of music must last at least one minute.
</li><li>Use the <code>chord</code> type.
</li></ul>
<h2>Sample Output</h2>
<p>
  Your output should be a song of at least 1 minute in length.  All students' outputs will be different, since you all have different creative inspirations!
</p>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 