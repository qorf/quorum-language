    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Intro | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">What is programming?</h5><ul><li role=\"listitem\">A gigantic headache</li></ul>";
    slideArray[1] = "<h5 role=\"heading\">What is programming good for?</h5><ul><li role=\"listitem\">Impressing the ladies</li></ul>";
    slideArray[2] = "<h5 role=\"heading\">How does programming work?</h5><ul><li role=\"listitem\">Through witchcraft and wizardry</li></ul>";
    slideArray[3] = "<h5 role=\"heading\">Practical applications of programming</h5><ul><li role=\"listitem\">Saving the world from the bad guys</li></ul>";
    slideArray[4] = "<h5 role=\"heading\">But... we have to start somewhere</h5><ul><li role=\"listitem\">So let's tell the computer to say hello to our friend, science</li><li>Type <code>output \"Hello science!\"</code> into the text area and click the Run button</li><li>We can use the <code>output</code> command whenever we want the computer to talk to us</li></ul>";
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Introduction</h1>
                <p>Hello Science!</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">var output = "Hello science!";
output;</textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>