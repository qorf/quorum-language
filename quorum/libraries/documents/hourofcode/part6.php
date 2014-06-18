    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 6 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">It's my birthday!</h5><ul><li role=\"listitem\">Sadly last week, our lab pet Rufus didn't make it.</li><li>But today is my birthday and I am trying to think positive, for starters Dr. Day remembered my birthday.</li><li>For my birthday he told me I can pick a couple more letters to insert into the corresponding nucleotides into the small specimens around the lab.</li><li>He said this will change life as we know it, which means I get to create superpowers!</li>";
    slideArray[1] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\"><li>I chose to use letters X and Y to add into the nucleotides.</li><li>It looks like GATTACA is the only DNA structure compatible to create superbugs, so that will stay the same.</li><li>DNA is also only capable of accepting one of the synthetic nucleotides before it becomes unstable… so I can't just add X's and Y's everywhere.</li><li>I just have to inject an X or a Y into the mutant DNA sequence and see how the Superbug will turn out.</li>";
    slideArray[2] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\"><li>To find out how the Superbug will turn out I put the DNA sequence into the GenerateBug action.</li><li>I put the DNA into the generator by typing the GenerateBug action with my DNA variable inside the parentheses.</li>";
    slideArray[3] = "";
    slideArray[4] = "";
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
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">Biology bio

text mutantBug = "XGATTACA"

text opponentBug = "GATTAXCA"

text result = bio:BattleBugs(mutantBug, opponentBug)

output result</textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>