<?php require_once("static/templates/pageheader.template.php"); ?>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>The Quorum Programming Language</h1>
		<p>The world's first <a href="evidence.php">evidence-oriented programming language.</a></p>
	</div>
</div>

<div class="content index-content">
<h1>Introducing Quorum</h1>
<p>
    Quorum 2.1 (see 
    the <a href="documents/release.php">Quorum release notes</a>), 
    has the following features and more:
</p>
 
 <?php require("ide.php"); ?>
 <div class="ide-embed-info">
    <label>Embed to your website: </label>
    <input type="text" value='<iframe src="http://www.quorumlanguage.com/embedded-ide.php" height="432" width="582" scrolling="no" class="quorum-ide" style="border:0; box-shadow: 2px 2px 2px #999999; -webkit-box-shadow: 2px 2px 2px #999999; -moz-box-shadow: 2px 2px 2px #999999; ">'>
 </div>

<ul>
    <li>A language design that is heavily tested for ease of use in formal 
        <a href="http://dl.acm.org/citation.cfm?id=2534973">scientific peer-reviewed studies</a>.</li>
    <li>NetBeans Integration (see the <a href="documents/releaseIDE.php">Sodbeans release notes</a>) </li>
    <li>Source code is compiled to Java bytecode</li>
    <li>A Static type system that conforms to the latest scientific evidence on ease
        of use from the academic peer reviewed literature.</li>
    <li>Object-orientation</li>
    <li>A <a href="submit_library.php">Library
            Submission System</a> for users to contribute to the language or request changes.</li>
    <li>A growing <a href="libraries.php">Standard Library</a> (e.g., data structures, 
        music generation, text-to-speech)</li>
    <li>A <a href="documents/syntax/plugins.php">Plugin system</a> for extending Quorum with Java or C++</li>
    <li>Full Java Debugging Interface (JDI) support for Quorum in NetBeans</li>
</ul>
<h2>Getting Started</h2>
<ul>
    <li>Here is the <a href="download.php">download and installation</a> page. 
        Quorum also comes included in 
        <a href="http://sodbeans.sourceforge.net/download.php">Sodbeans</a>.</li>
    <li>If you are new to Quorum and want to learn how to write programs, check 
        out information on the <a href="syntax.php">syntax.</a></li>
    <li>If you are taking a class on Quorum, <a href="curriculum.php">labs, 
            programming assignments, and curriculum</a> is available.</li>
    <li>If you want a quick reference for Quorum's console arguments, go
        to the <a href="documents/console.php">command line reference page</a>.

</ul>
<h2>Example Code in Quorum</h2>
<p>
    Below are some full examples of programs written in Quorum. These examples 
    are complete standalone programs. Here's hello, world:
</p>
<p><pre class="code"><code>
output &quot;Hello, world!&quot;
</code></pre></p>
<p>
    Here's how you make Quorum talk:
</p>
<p><pre class="code"><code>
say &quot;Hello, World!&quot;
</code></pre></p>
<p>
    and here's how you create a chromatic musical scale (starting at middle C) 
    play out of your computer's speakers:
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
</div>
<?php require_once("static/templates/pagefooter.template.php"); ?>