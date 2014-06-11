    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 1 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">Mary's Notes.</h5><ul><li role=\"listitem\">My first experiment is to test some code.</li><li>Dr. Day already gave me some variables to test, he's so awesome.</li><li>Some strands aren't DNA(they have more than A, C, G, or T) but I will test them anyways.</li>";
    slideArray[1] = "<h5 role=\"heading\">Mary's Notes.</h5><ul><li role=\"listitem\">Paul created something called an action.</li><li>I think that will perform the execution of his code when I tell the computer to do so.</li><li>So the action is called IsDNA, to tell me if the action is valid or not, seems pretty neat.</li>After that I'll put the variable I want the action to use in parantheses.</li>";
    slideArray[2] = "<h5 role=\"heading\">Mary's Notes.</h5><ul><li role=\"listitem\">Hmmm. What else do I need to know?</li><li>When an action uses a variable, that variable is said to be a parameter of the action.</li><li>Alright. Once I run Paul's code it seems I will get something back.</li><li>It should give me a boolean which can only be true and false, I think.</li>";
    slideArray[3] = "<h5 role=\"heading\">Mary's Notes.</h5><ul><li role=\"listitem\">I have to put Boolean in front of my variable, I called it validDNA, so I know a valid DNA will give me true.</li><li>So I set that variable equal to the variable IsDNA to receive the result.</li><li>To check the variable I'll do that thing called an output statement to the IsDNA variable.</li>";
    slideArray[4] = "<h5 role=\"heading\">Instructions.</h5>";
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Introduction</h1>
                <p>Building Blocks of Life</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true"></textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>