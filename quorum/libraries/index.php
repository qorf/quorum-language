<?php require_once("static/templates/pageheader.template.php"); ?>
<div class="hero-unit">
    <div class="hero-unit-container">
        <h2 id="pageTitle">The Quorum Programming Language</h2>
        <h4 class="sub-heading">The world's first <a href="evidence.php">evidence-oriented programming language.</a></h4>
        <h3 class ="screenReaderContent">Facebook Controls</h3>
        <div id="facebook-links" class="fb-like fb_iframe_widget" data-href="https://www.facebook.com/quorumlanguage" data-layout="standard" data-action="like" data-show-faces="true" data-share="true" fb-xfbml-state="rendered" fb-iframe-plugin-query="action=like&amp;app_id=&amp;href=https%3A%2F%2Fwww.facebook.com%2Fquorumlanguage&amp;layout=standard&amp;locale=en_US&amp;sdk=joey&amp;share=true&amp;show_faces=true">
            <span id="facebook-links"><iframe id="facebook-iframe" name="f11630dfb" width="1000px" height="1000px" frameborder="0" allowtransparency="true" scrolling="no" title="fb:like Facebook Social Plugin" src="http://www.facebook.com/v2.0/plugins/like.php?action=like&amp;app_id=&amp;channel=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FDhmkJ2TR0QN.js%3Fversion%3D41%23cb%3Df2337e286%26domain%3Dquorumlanguage.com%26origin%3Dhttp%253A%252F%252Fquorumlanguage.com%252Ff39ca920c%26relation%3Dparent.parent&amp;href=https%3A%2F%2Fwww.facebook.com%2Fquorumlanguage&amp;layout=standard&amp;locale=en_US&amp;sdk=joey&amp;share=true&amp;show_faces=true">
                </iframe></span></div>
    </div>
</div>


<div class="content index-content">
        <h2 class = "underline">Introducing Quorum 2.1</h2>
    <div id="homePageContent">

        <nav class="tileContainer"><ul><li><a href ="download.php" class="tile">Download<img src="static/img/download.png" alt="download" /></a></li><li><a href ="syntax.php" class="tile">Reference<img src="static/img/reference.png" alt="reference" /></a></li><li><a href ="libraries.php" class="tile">Libraries<img src="static/img/libraries.png" alt="libraries" /></a></li><li><a href ="curriculum.php" class="tile">Exercises<img src="static/img/tutorials.png" alt="tutorials" /></a></li><li><a href ="developers.php" class="tile">Developers<img src="static/img/developers.png" alt="developers" /></a></li><li><a href ="https://www.facebook.com/quorumlanguage" class="tile">Social<img src="static/img/social.png" alt="social" /></a></li><li><a href ="epiq.php" class="tile">EPIQ 2015<img src="static/img/epiq.png" alt="EPIQ Logo" /></a></li><li><a href ="about.php" class="tile">About Quorum<img src="static/img/more.png" alt="about Quorum" /></a></li>
        
                <li id="HOC-banner"><a href="/documents/hourofcode/part1.php" id="HOC-banner-a"><img src="static/img/gridicons/hourofcode.png" alt="" /><h2>Learn how to use Quorum with Mary</h2></a></ul></nav>
    </div>

    <main id="IDE" class="front-page-IDE">
        <h1>Try Quorum!</h1><p class="IDE-subtitle small-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</p>
        <h3><select id="buttonGroup" aria-label="examples dropdown menu">
            <option class="code-example" value="Hello, World!"> Hello, World! </option>
            <option class="code-example" value="Conditionals"> Conditionals </option>
            <option class="code-example" value="Loops"> Loops </option>
            <option class="code-example" value="Actions"> Actions </option>
            <option class="code-example" value="Classes"> Classes </option>
            </select></h3>

        <textarea id="front-page-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">output "Hello World!"</textarea>
        
        <button id="run-button" class="btn btn-success" href="#">Run</button>
        
        <div id="IDE-output-container">
            <h2 id="output-label" class="hidden">Output</h2>
            <pre id="IDE-output" class="outputArea  front-page-IDE-output" role="log" aria-labeledby="output-label" aria-live="polite"></pre>
        </div>   

    </main>

</div>

<?php require_once("static/templates/pagefooter.template.php"); ?>