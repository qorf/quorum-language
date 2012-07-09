<?php include("documents/include/header.php"); ?>
<script type="text/javascript">
    document.title = 'Introduction to the Quorum Programming Language';
</script>
<h1>The Quorum Programming Language</h1>
<p>
    Quorum is a general purpose, object-oriented, programming language that 
    offers a new approach to programming language design. Designed in formal 
    empirical studies with human subjects, Quorum has been iteratively 
    refined for ease of understanding and use in lab work and field tests 
    with novices and experts. Quorum is available as part of the <a href="http://sodbeans.sourceforge.net/"> Sodbeans project</a> and 
    additionally as a stand-alone programming 
    language. The Quorum 1.5 release (see the <a href="documents/release.php">Quorum release notes</a>), 
    has the following features:
</p>
<ul>
    <li>Source code is compiled to Java bytecode</li>
    <li>Static type checking</li>
    <li>Unique control structures, heavily tested to be easy to understand by novices and professionals</li>
    <li>Object-oriented language</li>
    <li><a href="libraries.php">Standard Library</a> (e.g., data structures, music generation, text-to-speech)</li>
    <li>Plugin system for extending Quorum</li>
    <li>Integration with the NetBeans IDE</li>
    <li>Talking Omniscient debugger (The debugger literally talks to you and tells you what is going on).</li>
</ul>
<h2>Getting Started with Quorum</h2>
<ul>
    <li>Here is the <a href="documents/download.php">download and install</a> page. Quorum also comes included in <a href="http://sodbeans.sourceforge.net/download.php">Sodbeans</a>.</li>
    <li>For further instructions on installation, you can go to the <a href="documents/installation.php">Quorum installation and getting started</a> page.</li>
    <li>If you are new to Quorum and want to learn how to write programs, check out information on the <a href="syntax.php">syntax.</a></li>
    <li>If you are taking a class on Quorum, <a href="curriculum.php">labs, programming assignments, and curriculum</a> is available.</li>

</ul>
<h2>Example Code in Quorum</h2>
<p>
    Below are some full examples of programs written in Quorum. These examples are complete standalone programs. Here's hello, world:
</p>
<p><pre class="code"><code>
print &quot;Hello, world!&quot;
</code></pre></p>
<p>
    Here's how you make Quorum talk:
</p>
<p><pre class="code"><code>
say &quot;Hello, World!&quot;
</code></pre></p>
<p>
    and here's how you create a chromatic musical scale (starting at middle C) play out of your computer's speakers:
</p>
<p><pre class="code"><code>
use Libraries.Sound.Music
Music muse
integer i = 0
repeat 12 times
   muse:Play(60 + i,1)
   i = i + 1
end
</code></pre></p>
<?php include("documents/include/footer.php"); ?>