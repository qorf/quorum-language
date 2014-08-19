<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 1 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">Variables</h5><ul><li>In programming, a <code>variable</code> is used as a container to store information that can be used at a later time.</li><li>There are different kinds of variables that will hold different kinds of information.</li><li>For this example, we will create a <code>text</code> variable, which can hold anything you want to type in.</li></ul>";
    slideArray[1] = "<h5 role=\"heading\">Creating Variables</h5><ul><li>To create a variable in Quorum, we start by telling the computer what type of variable we want to make, in this case it's a text variable, followed by a space and a name for the variable.  Let's create a variable named dna by typing the code: <code>text dna</code></li><li>When naming a variable, we start with a lowercase letter followed by more letters or numbers.</li><li><span class=\"bold\">Note: </span>Names are case sensitive; dna is not the same as DNA.</li></ul>";
    slideArray[2] = "<h5 role=\"heading\">Storing Information in Variables: Basics</h5><ul><li>We have made a variable, but we haven't stored any information in it yet.</li><li>To put something into a variable we use the <code>=</code> operator followed by the information we want stored.</li><li><span class=\"bold\">Note: </span>When we store something in a text variable, we need to let the computer know whether it should store something we type in directly or if it should store information from another variable.  To make this distinction, we put double quotes around what we type.  Without double quotes, the computer will treat whatever we type as a variable.</li></ul>";
    slideArray[3] = "<h5 role=\"heading\">Storing Information in Variables: Example</h5><ul><li>For this example, let's store the text GATTACA by adding on to what we have: <code>text dna = \"GATTACA\"</code></li><li><span class=\"bold\">Note: </span>When working with text variables, always be sure to use double quotes when not storing information from another variable.</li></ul>";
    slideArray[4] = "<h5 role=\"heading\">Using Variables for Output</h5><ul><li>Now that we've stored something in our variable, we can refer to its contents whenever we need.</li><li>To demonstrate this, let's grab what we have stored in our variable and display it to the screen.</li><li>In Quorum, to output information to the screen we type the word <code>output</code> followed by a space and then whatever we want to show up on screen.</li><li>In this case, we want to output the contents of our variable named dna, so to do this we would type: <code>output dna</code>.</li></ul>";
    slideArray[5] = "<h5 role=\"heading\">Additional Information Regarding Variables</h5><ul><li>Because variables are just containers, if we were to go back and change what was stored inside of dna we could still use the same output statement and get a different result on screen.  If you've been following along, you can test this by changing what is stored in the dna variable.</li></ul>";

$(document).ready(function(){
    $('#IDE-input').text('text dna = "GATTACA"\noutput dna');
});
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 1</h1>
                <p>Got a New Gig</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>