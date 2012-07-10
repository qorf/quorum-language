<?php include("../../include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Chapter 2';
</script>

<h1>Challenge Programming Assignment: Musical Piece</h1>
<h2>Objectives</h2>
<p>
The goal of this assignment is to understand the following concepts:
</p>
<ul>
<li>Using the Music library to play chords</li>
<li>Using multiple non-primitive data types in a single program</li>
</ul>
<h2>Overview</h2>
<p>
Write a program that plays music with a combination of notes and chords.  This assignment allows the students to create their own songs by combining single notes and chords.
</p>
<h2>Description</h2>
<p>
So far, we have focused on using the <tt>Play</tt> feature in the <tt>Music</tt> class to play single notes. This feature can also be used to play multiple notes at once, called <i>chords</i>. In this assignment, create a piece of music utilizing both single notes and chords to create a piece of music that lasts at least one minute.
</p>
<p>
To play chords in Quorum, we must use the <tt>Chord</tt> type. To include this type for use in your program, use the following <strong><tt>use</tt></strong> statement:
</p>
<p><pre class="code"><code>
use Libraries.Sound.Chord
</code></pre></p>
<p>
To create a chord, we might use code like the following. This code creates a C Major chord.
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
<h2>Analysis</h2>
<ul><li>Compose a piece of music consisting of notes and chords.
</li><li>The piece of music must last at least one minute.
</li><li>Use the <tt>chord</tt> type.
</li></ul>
<h2>Design Criteria</h2>
<ul><li>Create a new project and name it <strong>Assignment2_3</strong>.
</li><li>The song must play for at least one minute.
</li></ul>

<?php include("../../include/footer.php"); ?>