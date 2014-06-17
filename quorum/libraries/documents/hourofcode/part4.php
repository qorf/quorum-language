    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 4 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">Lucky 13.</h5><ul><li role=\"listitem\">This is my 13th day working here.</li><li>I named the rat we got Rufus and I even taught him how to type, kinda.</li><li>My Goal for the day was to find the parent DNA of a certain mutant DNA that was created.</li>";
    slideArray[1] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">I typed my code in to figure out which parent made this mutant DNA.</li><li>That created less work for me, making it easy to change from testing one parent to another.</li><li>There was a slight problem in the lab stopping me from my work for a bit.</li>";
    slideArray[2] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">I recieved an awesome mouse pad from Dr. Day and it's pretty rad!</li><li>When I got back to work, I started by using an if statement.</li><li>And with a little help from Rufus (by pressing the spacebar) I figured out the code.</li>";
    slideArray[3] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">I sat through my lunch and worked so hard to figure out which parent DNA matched.</li><li>Six hours and a lot of anger, I finally found it.</li><li>The match was with Parent5.</li>";
    slideArray[4] = "<h5 role=\"heading\">Instructions.</h5>";
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Introduction</h1>
                <p>Hi... Mom?</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">if bio:IsParent(mutantDNA, parent5)
    output "true"
else
    output "false"
end</textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>