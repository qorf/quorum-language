<?php require_once("static/templates/pageheader.template.php"); ?>
<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>The Quorum Programming Language</h1>
		<p>The world's first <a href="evidence.php">evidence-oriented programming language.</a></p>
                <div class="fb-like fb_iframe_widget" data-href="https://www.facebook.com/quorumlanguage" data-layout="standard" data-action="like" data-show-faces="true" data-share="true" fb-xfbml-state="rendered" fb-iframe-plugin-query="action=like&amp;app_id=&amp;href=https%3A%2F%2Fwww.facebook.com%2Fquorumlanguage&amp;layout=standard&amp;locale=en_US&amp;sdk=joey&amp;share=true&amp;show_faces=true">
                    <span style="vertical-align: bottom; width: 450px; height: 25px;"><iframe name="f11630dfb" width="1000px" height="1000px" frameborder="0" allowtransparency="true" scrolling="no" title="fb:like Facebook Social Plugin" src="http://www.facebook.com/v2.0/plugins/like.php?action=like&amp;app_id=&amp;channel=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FDhmkJ2TR0QN.js%3Fversion%3D41%23cb%3Df2337e286%26domain%3Dquorumlanguage.com%26origin%3Dhttp%253A%252F%252Fquorumlanguage.com%252Ff39ca920c%26relation%3Dparent.parent&amp;href=https%3A%2F%2Fwww.facebook.com%2Fquorumlanguage&amp;layout=standard&amp;locale=en_US&amp;sdk=joey&amp;share=true&amp;show_faces=true" 
                         style="border: none; visibility: visible; width: 450px; height: 25px;" class=""></iframe></span></div>     
                  
        </div>
</div>


<div class="content index-content">

<div id="IDE" class="small-IDE floating">
                    <select id="buttonGroup">
                        <option class="code-example" value="Hello, World!"> Hello, World! </option>
                        <option class="code-example" value="Conditionals"> Conditionals </option>
                        <option class="code-example" value="Conditionals"> Loops </option>
                        <option class="code-example" value="Conditionals"> Actions </option>
                        <option class="code-example" value="Conditionals"> Classes </option>
                    </select>
<h2>Try Quorum! <span class="IDE-subtitle small-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>

   <textarea id="IDE-input" class="ide inputArea small-IDE-input" role="textbox" aria-multiline="true">Hello World!</textarea><div id="IDE-output-container">
        <pre id="IDE-output" class="outputArea  small-IDE-output" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success small-IDE-run-button" href="#">Run</a>
        </div>
</div>    
        
</div>    
    
<h1>Introducing Quorum</h1>
<p>
    Current Version: Quorum 2.1 (see the <a href="documents/release.php">Quorum release notes</a>)
</p>
 
<ul>
    <li>Quorum is a Java Virtual Machine language, like JRuby, Jython, or Groovy, with
    a clean syntax, a <a href="documents/syntax/plugins.php">plugin system</a> for extensions, and a growing 
    <a href="libraries.php">Standard Library</a>.</li>
    <li>Quorum has been heavily vetted for ease of use in formal 
        <a href="http://dl.acm.org/citation.cfm?id=2534973">scientific peer-reviewed studies</a>. 
        For details, listen to a recent <a href="http://goo.gl/DB2RtQ">podcast</a>.</li>
    
    <li>Comes with an Integrated Development Environment (IDE) called 
        <a href="http://localhost/download.php">Sodbeans,</a>
        based on <a href="netbeans.org">NetBeans</a>.</li>
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
        to the <a href="documents/console.php">command line reference page</a>. </li>
    <li>Get help on the 
        <a href="https://groups.google.com/forum/#!forum/quorum-language">Quorum Google Group</a> 
        or post about it on the 
        <a href="https://www.facebook.com/quorumlanguage">Quorum Facebook page</a></li>
  
</ul>

<ul class="learn-index-grid">
    <li class="learn-grid-item-large grid-item-hour-of-code front-page-grid-item"><a href="/documents/hourofcode/introduction.php"><h2 class="index_package_title"><i></i>Learn how to use Quorum with Mary</h2></a>
</ul>

</div>

<?php require_once("static/templates/pagefooter.template.php"); ?>