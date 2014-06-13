    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Intro | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">What is programming?</h5><ul><li role=\"listitem\">Programming is the way for us to tell the computer to do something awesome!</li><li>Because computers aren’t people, we need to talk to them in special languages, called “Programming Languages.”</li><li>There are many different programming languages that enable us to talk with computers.</li><li>For this Hour of Code, we’ll be using the programming language called Quorum.</li></ul>";
    slideArray[1] = "<h5 role=\"heading\">What is programming good for?</h5><ul><li role=\"listitem\">Almost anything!</li><li>Programming is used in every electronic device that we have, like phones, cars, computers, you name it.</li><li>We can also use it  to help advance modern science and solve today's biggest problems.</li></ul>";
    slideArray[2] = "<h5 role=\"heading\">How does programming work?</h5><ul><li role=\"listitem\">People from all over the world are learning how to program every day.</li><li>No matter how old you are or how much you've used a computer, you can learn programming, too.</li><li>It may look hard at first, but with enough practice, anyone can do it.</li></ul>";
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
        <textarea id="hour-of-code-IDE-input" class="ide inputArea" role="textbox" aria-multiline="true">var DNA = "GATTACA";
DNA;</textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea" role="log" aria-live="polite"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>