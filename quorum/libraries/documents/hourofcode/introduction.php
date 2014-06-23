    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Intro | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">My first job.</h5><ul><li role=\"listitem\">Hi, Mary here. I just finished my biology degree and got my first gig, workiing with Dr. Day.</li><li>Not only do I get to use biology in his lab but he also needs a programmer.</li><li>The cool thing is, he wants us to video blog for the job. Which will be easy since I already video blog.</li>";
    slideArray[1] = "<h5 role=\"heading\">The plan for the summer.</h5><ul><li role=\"listitem\">Dr. Day's plan for the summer is to work with nucleotides, which are the building blocks of DNA.</li><li>There are four nucleotides that make up all life and we abbreviate them with their beginning letters, A, C, G and T.</li><li>The organisms we are working with are simple and have only a few nucleotides in their DNA sequence.</li><li>To make this easier he said we will do our analyses in a web browser.</li></ul>";
    slideArray[2] = "<h5 role=\"heading\">Using Quorum.</h5><ul><li role=\"listitem\">To make our analysis we will be using the programming languange called Quorum, to tell the computer what to do.</li><li>Dr. Day told me anyone could learn to use Quorum, even someone like me who has never programmed before.</li><li>I guess no matter what language I learn, I have always wanted to code, because you can do so much with it.</li><li>With programming, we can make a self-driving car, or a video game, or maybe even analyze genetics.</li></ul>";
    slideArray[3] = "<h5 role=\"heading\">A lot to learn.</h5><ul><li role=\"listitem\">Today I have a lot to learn.</li><li>My first task is to figure out how to ouput a small gene sequence to the screen.</li><li>Next, I have to store the same sequence into something called a variable and then tell the computer to output whatever is stored in that variable.</li></ul>";
    slideArray[4] = "<h5 role=\"heading\">Instructions: Output</h5><ul><li>To output something to the screen we just type <code>output</code>, followed by a space and then whatever text I want surrounded by double quotes.</li><li>Something within double quotes is called a <code>literal</code> because the computer will treat it as text exactly how we type it.</li></ul>";
    slideArray[5] = "<h5 role=\"heading\">Instructions: Outputting Variables</h5><ul><li>In contrast to a literal, we can also make a <code>variable</code>, which can store information, including information we want to output.</li><li>There are different types of variables that will hold different types of information.</li><li>Since we want to store text in this variable, we should tell the computer that this variable stores text by declaring the variable as <code>text</code> before the variable's name, which we can name whatever we want.</li><li>We can then tell the computer to output the contents of the variable by typing <code>output</code> followed by a space and the variable's name.</li><li>Remember, when outputting a variable we do not use double quotes because it is not a literal.</li></ul>";
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 0</h1>
                <p>Meeting Mary</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">text dna = "GATTACA"
output dna</textarea>
        <pre id="IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>