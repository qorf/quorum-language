    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 2 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">A new day.</h5><ul><li role=\"listitem\">Today Dr. Day told me to clone DNA.</li><li>Oh yeah, I also got this awesome keychain!</li><li>Anyways, I had to clone this DNA 10 times, which might taken a while to do.</li><li>So, I chose to do it the shorter way with programming.</li>";
    slideArray[1] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">I used the Quorum programming language, kind of like you are!</li><li>So to clone the DNA 10 times I just repeat it.</li><li>It seemed like it was working pretty well for a while.</li>";
    slideArray[2] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">Oh goodness!</li><li>During the DNA replication process an error had occured which caused a mutation.</li><li>A mutation is an error in the replication process that damaged the DNA.</li>";
    slideArray[3] = "<h5 role=\"heading\">Instructions.</h5>";
    slideArray[4] = "";
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 2</h1>
                <p>Mutations!</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">var DNA = "GATTACA";

for (var i=0; i < 100; i++) {
	CloneDNA(DNA);
};</textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>