<?php require_once("static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>The Quorum Download page</h1>
		<p>Quorum can be downloaded on its own as a console compiler or
                    as part of our integrated development 
                    environment: <i><strong>Sodbeans</strong></i>.</p>
	</div>
</div>

<div class="content download-content">
	<ul class="download-links">
		<li class="links-windows"><h2><a href="http://sourceforge.net/projects/quorum/files/Quorum%201.7/Quorum%201.7.exe/download">Download Quorum 2.0 for Windows</a></h2></li>
		<li class="links-macosx"><h2><a href="http://sourceforge.net/projects/quorum/files/Quorum%201.7/Quorum%201.7.pkg/download">Download Quorum 2.0 for Mac OS X</a></h2></li>
	</ul>
        <ul class="download-links">
		<li class="links-windows"><h2><a href="http://sourceforge.net/projects/quorum/files/Quorum%201.7/Quorum%201.7.exe/download">Download Sodbeans 4.0 for Windows</a></h2></li>
		<li class="links-macosx"><h2><a href="http://sourceforge.net/projects/quorum/files/Quorum%201.7/Quorum%201.7.pkg/download">Download Sodbeans 4.0 for Mac OS X</a></h2></li>
	</ul>
        <ul class="download-links">
		<li class="links-windows"><h2><a href="http://sourceforge.net/projects/quorum/files/Quorum%201.7/Quorum%201.7.exe/download">Download Sodbeans 4.0 for Windows (School)</a></h2></li>
		<li class="links-macosx"><h2><a href="http://sourceforge.net/projects/quorum/files/Quorum%201.7/Quorum%201.7.pkg/download">Download Sodbeans 4.0 for Mac OS X (School)</a></h2></li>
	</ul>

	<h1>Getting started with Quorum</h1>
	<p align="justify">There are two ways to download and use Quorum: 
            1) through the console, or 2) through an integrated
            development environment (Sodbeans). First, 
            we can run Quorum through a console (such as cmd.exe on 
            windows, or Terminal on Mac). Second, we can develop in Quorum using
            an integrated development environment called Sodbeans 4.0. This environment
            includes the latest  version of Quorum and a number of advanced features like code completion, 
            syntax highlighting, brace matching, a talking omniscient debugger, and many optional
            features for blind and visually impaired users. Sodbeans is a plugin
            for Oracle's NetBeans.
        </p>
        <p><strong>Note:</strong> Want to <strong><i><u>Help Science!</u></i></strong> by contributing data on how you are
            using Quorum? Download Sodbeans 4.0 School Version! <i>We promise we won't use
        the data for world domination.</i></p>
	<h2>Creating Hello World</h2>
	<p>Let's build a program that prints out &quot;Hello World&quot;
            to the console. Here's the code for our program:</p>
	<pre class="code"><code>output &quot;Hello world&quot;</code></pre>
        
        <p>We can compile and run this program using the following command:</p>
	<pre class="code"><code>quorum helloWorld.quorum</code></pre>

	<p>followed by</p>
	<pre class="code"><code>java -jar ./Run/Default.jar</code></pre>
     
	<p>Instead of compiling a program, we can also interpret it
            on the fly by using the following command:</p>
	<pre class="code"><code>quorum -interpret helloWorld.quorum</code></pre>

	
	<p>Finally, a list of all Quorum commands can be found from the 
            console on the 
            <a href="documents/console.php">console argument page</a>,
	or by using the command:</p>
	<pre class="code"><code>quorum -help</code></pre>
</div>

<?php require_once("static/templates/pagefooter.template.php"); ?>