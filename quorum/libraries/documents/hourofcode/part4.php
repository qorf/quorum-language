<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 3 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">Hello again.</h5><ul><li role=\"listitem\">Dr. Day saw the mutation and told me to run more tests.</li><li>My new goal is to find out how many times the mutation occurs, which could take a while.</li><li>But he gave me Cheetos, which of course are my favorite kind of chips.</li>";
    slideArray[1] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">He taught me that in Quorum, I can use an if statement.</li><li>The if statement should tell me whether the clone is a mutant or not.</li><li>Then I think I can use an integer variable to store how many mutants I come across.</li>";
    slideArray[2] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">The integer variable is named mutationCount.</li><li>And I could then put in the if statement, whatever that is.</li><li>If it does not equal GATTACA, its a mutant.</li>";
    slideArray[3] = "<h5 role=\"heading\">Today's Notes.</h5><ul><li role=\"listitem\">I ended up with a TON of mutants.</li><li>It seemed pretty surprising but I didn't tell Dr. Day yet.</li><li>I guess we will find out what happens tomorrow.</li><li>I almost forgot, we got a pet rat.</li>";
    slideArray[4] = "<h5 role=\"heading\">Instructions.</h5>";
    
    $(document).ready(function(){
        $('#IDE-input').text('');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 3</h1>
                <p>Count the Mutants</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/j4Bjfu1TaEE?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>